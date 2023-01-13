package com.so_learn.课时5;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Emulator;
import com.github.unidbg.Module;
import com.github.unidbg.arm.backend.Backend;
import com.github.unidbg.arm.backend.BlockHook;
import com.github.unidbg.arm.context.EditableArm32RegisterContext;
import com.github.unidbg.arm.context.RegisterContext;
import com.github.unidbg.debugger.BreakPointCallback;
import com.github.unidbg.file.FileResult;
import com.github.unidbg.file.IOResolver;
import com.github.unidbg.file.linux.AndroidFileIO;
import com.github.unidbg.linux.ARM32SyscallHandler;
import com.github.unidbg.linux.android.*;
import com.github.unidbg.linux.android.dvm.AbstractJni;
import com.github.unidbg.linux.android.dvm.DalvikModule;
import com.github.unidbg.linux.android.dvm.StringObject;
import com.github.unidbg.linux.android.dvm.VM;
import com.github.unidbg.linux.file.ByteArrayFileIO;
import com.github.unidbg.linux.file.DumpFileIO;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.memory.SvcMemory;
import com.github.unidbg.unix.UnixSyscallHandler;
import com.github.unidbg.utils.Inspector;
import com.sun.jna.Pointer;
import unicorn.Unicorn;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

class yodaSyscallHandler extends ARM32SyscallHandler {

    public yodaSyscallHandler(SvcMemory svcMemory) {
        super(svcMemory);
    }


    @Override
    protected boolean handleUnknownSyscall(Emulator emulator, int NR) {
        switch (NR) {
            case 190:
                vfork(emulator);
                return true;
            case 114:
                wait4(emulator);
                return true;
        }

        return super.handleUnknownSyscall(emulator, NR);
    }

    private void vfork(Emulator<?> emulator) {
        EditableArm32RegisterContext context = (EditableArm32RegisterContext) emulator.getContext();
        int childPid = emulator.getPid() + ThreadLocalRandom.current().nextInt(256);
        int r0 = childPid;
        System.out.println("vfork pid=" + r0);
        context.setR0(r0);
    }

    private void wait4(Emulator emulator) {
        EditableArm32RegisterContext context = (EditableArm32RegisterContext) emulator.getContext();
        int pid = context.getR0Int();
        Pointer wstatus = context.getR1Pointer();
        int options = context.getR2Int();
        Pointer rusage = context.getR3Pointer();
        System.out.println("wait4 pid=" + pid + ", wstatus=" + wstatus + ", options=0x" + Integer.toHexString(options) + ", rusage=" + rusage);
    }

    protected int pipe2(Emulator<?> emulator) {
        EditableArm32RegisterContext context = (EditableArm32RegisterContext) emulator.getContext();
        Pointer pipefd = context.getPointerArg(0);
        int flags = context.getIntArg(1);
        int write = getMinFd();
        this.fdMap.put(write, new DumpFileIO(write));
        int read = getMinFd();
        String stdout = "\n";
        // stdout中写入popen command 应该返回的结果
        String command = emulator.get("command");
        if (command == null) {
            return 0;
        }
        switch (command){
            case "uname -a":{
                stdout = "Linux localhost 4.9.186-perf-gd3d6708 #1 SMP PREEMPT Wed Nov 4 01:05:59 CST 2020 aarch64\n";
            }
            break;
            case "cd /system/bin && ls -l":{
                stdout = "total 25152\n" +
                        "-rwxr-xr-x 1 root   shell     128688 2009-01-01 08:00 abb\n" +
                        "lrwxr-xr-x 1 root   shell          6 2009-01-01 08:00 acpi -> toybox\n" +
                        "-rwxr-xr-x 1 root   shell      30240 2009-01-01 08:00 adbd\n" +
                        "-rwxr-xr-x 1 root   shell        207 2009-01-01 08:00 am\n" +
                        "-rwxr-xr-x 1 root   shell     456104 2009-01-01 08:00 apexd\n" +
                        "lrwxr-xr-x 1 root   shell         13 2009-01-01 08:00 app_process -> app_process64\n" +
                        "-rwxr-xr-x 1 root   shell      25212 2009-01-01 08:00 app_process32\n";
            }
            break;
            case "stat /root":{
                stdout = "stat: '/root': No such file or directory\n";
            }
            break;
            default:
                System.out.println("command do not match!");
        }

        this.fdMap.put(read, new ByteArrayFileIO(0, "pipe2_read_side", stdout.getBytes()));
        pipefd.setInt(0, read);
        pipefd.setInt(4, write);
        System.out.println("pipe2 pipefd=" + pipefd + ", flags=0x" + flags + ", read=" + read + ", write=" + write + ", stdout=" + stdout);
        context.setR0(0);
        return 0;
    }
}

public class yoda extends AbstractJni implements IOResolver {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    yoda(){
        // 创建模拟器实例，要模拟32位或者64位，在这里区分
//        emulator = AndroidEmulatorBuilder.for32Bit().build();
        AndroidEmulatorBuilder builder = new AndroidEmulatorBuilder(false) {
            public AndroidEmulator build() {
                return new AndroidARMEmulator(processName, rootDir,
                        backendFactories) {
                    @Override
                    protected UnixSyscallHandler<AndroidFileIO>
                    createSyscallHandler(SvcMemory svcMemory) {
                        return new yodaSyscallHandler(svcMemory);
                    }
                };
            }
        };
        emulator = builder.setRootDir(new File("target/rootfs")).build();

        // 模拟器的内存操作接口
        final Memory memory = emulator.getMemory();
        // 设置系统类库解析
        memory.setLibraryResolver(new AndroidResolver(23));
        // 注册绑定IO重定向
        emulator.getSyscallHandler().addIOResolver(this);
        SystemPropertyHook systemPropertyHook = new SystemPropertyHook(emulator);
        systemPropertyHook.setPropertyProvider(new SystemPropertyProvider() {
            @Override
            public String getProperty(String key) {
                System.out.println("lilac Systemkey:"+key);
                switch (key){
                    case "ro.serialno": {
                        return "f8a995f5";
                    }
                    case "ro.product.manufacturer": {
                        return "Xiaomi";
                    }
                    case "ro.product.brand": {
                        return "Xiaomi";
                    }
                    case "ro.product.model": {
                        return "MIX 2S";
                    }
                }
                return "";
            };
        });
        memory.addHookListener(systemPropertyHook);
        // 创建Android虚拟机
        vm = emulator.createDalvikVM(new File("unidbg-android/src/test/java/com/so_learn/课时5/qtt_new.apk"));
        // 设置是否打印相关调用细节
        vm.setVerbose(true);

        DalvikModule dmLibc = vm.loadLibrary(new File("unidbg-android/src/main/resources/android/sdk23/lib/libc.so"), true);
        Module moduleLibc = dmLibc.getModule();
        // HOOK popen
        int popenAddress = (int) moduleLibc.findSymbolByName("popen").getAddress();
        // 函数原型：FILE *popen(const char *command, const char *type);
        emulator.attach().addBreakPoint(popenAddress, (emulator, address) -> {
            RegisterContext registerContext = emulator.getContext();
            String command = registerContext.getPointerArg(0).getString(0);
            System.out.println("lilac popen command:"+command);
            return true;
        });

        // 加载so到虚拟内存，加载成功以后会默认调用init_array等函数
        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/java/com/so_learn/课时5/libyoda.so"), true);
        module = dm.getModule();

        // 设置JNI
        vm.setJni(this);
        System.out.println("call JNIOnLoad");
        dm.callJNI_OnLoad(emulator);
    }

    @Override
    public FileResult resolve(Emulator emulator, String pathname, int oflags) {
        // emulator.attach().debug();  // 直接断下来
        System.out.println("lilac path:"+pathname);
        return null;
    }

    public void callbulwark() {
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv());
        list.add(0);
        String str1 = "{\"screen_brightness\":\"82\",\"tk\":\"ACHaSnpgYUzPHTlyVie2s7LThdzXV_vhfZ40NzUxNDk1MDg5NTIyNQ\",\"cpu_model\":\"AArch64 Processor rev 3 (aarch64) ,8,2016000\",\"carrier\":\"46007\",\"instance\":\"com.inno.yodasdk.info.Infos@ac3ab7c\",\"sim_state\":\"5\",\"sid\":\"56a91d6a-204d-48ea-b170-4c5cd713e05e\",\"imei\":\"869593867257804\",\"gyro\":\"0.02,0.0,1.0\",\"manufacturer\":\"HUAWEI\",\"screen_scale\":\"5.2\",\"android_id\":\"86ee835487a1f4e4\",\"boot_time\":\"1626514060336\",\"volume\":\"4,5,5,11,6\",\"serial_number\":\"LNX11WPJ5M627459\",\"bt_mac\":\"14:09:DC:99:DB:89\",\"wifi_mac\":\"08:40:f3:f6:9a:21\",\"mac\":\"14:09:dc:9b:1c:60\",\"cid\":\"47514950895225\",\"charge_state\":\"2\",\"apps_count\":\"2,120\",\"package_name\":\"com.jifen.qukan\",\"ext\":\"{\\\"author_id\\\":\\\"2328110\\\",\\\"content_id\\\":\\\"1624220959\\\",\\\"member_id\\\":\\\"1453484970\\\"}\",\"platform\":\"android\",\"sensor_count\":\"11\",\"app_version\":\"3.10.48.000.0714.1521\",\"screen_size\":\"1080,1920,3.0\",\"brand\":\"HUAWEI\",\"sdk_version\":\"1.0.7.210128\",\"wifi_name\":\"123\",\"os_version\":\"23\",\"hardware\":\"hi3635\",\"adb\":\"1\",\"scene\":\"qtt_article_readtimerreport\",\"model\":\"HUAWEI GRA-TL00\"}";
        StringObject stringObject1 = new StringObject(vm, str1);
        list.add(vm.addLocalObject(stringObject1));
        String str2 = "dubo";
        StringObject stringObject2 = new StringObject(vm, str2);
        list.add(vm.addLocalObject(stringObject2));
        String str3 = "1629280231";
        StringObject stringObject3 = new StringObject(vm, str3);
        list.add(vm.addLocalObject(stringObject3));

        Number number = module.callFunction(emulator, 0x8ff1, list.toArray());
        byte[] result = (byte[]) vm.getObject(number.intValue()).getValue();
        Inspector.inspect(result, "result");
    }

    public static void main(String[] args) {
        yoda demo = new yoda();
        demo.callbulwark();
    }

}