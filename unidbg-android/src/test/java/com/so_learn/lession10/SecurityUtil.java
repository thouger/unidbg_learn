package com.so_learn.lession10;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Emulator;
import com.github.unidbg.Module;
import com.github.unidbg.file.FileResult;
import com.github.unidbg.file.IOResolver;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.linux.android.dvm.api.AssetManager;
import com.github.unidbg.linux.android.dvm.array.ByteArray;
import com.github.unidbg.linux.file.ByteArrayFileIO;
import com.github.unidbg.linux.file.SimpleFileIO;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.virtualmodule.android.AndroidModule;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SecurityUtil extends AbstractJni implements IOResolver {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    public SecurityUtil(){
        emulator = AndroidEmulatorBuilder.for32Bit().build(); // 创建模拟器实例，要模拟32位或者64位，在这里区分
        emulator.getSyscallHandler().addIOResolver(this);
        final Memory memory = emulator.getMemory(); // 模拟器的内存操作接口
        memory.setLibraryResolver(new AndroidResolver(23)); // 设置系统类库解析
        vm = emulator.createDalvikVM(new File("unidbg-android/src/test/java/com/so_learn/lession10/xc-8-38-2.apk"));

        new AndroidModule(emulator, vm).register(memory);

        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/java/com/so_learn/lession10/libscmain.so"), true);
        module = dm.getModule();

        int pid = emulator.getPid();
        System.out.println("APP pid:"+pid);

        vm.setJni(this);
        vm.setVerbose(true);

        dm.callJNI_OnLoad(emulator);

    }

    public void callgetNameByPid(){
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv());
        list.add(0);
        list.add(emulator.getPid());
        Number number = module.callFunction(emulator, 0xee01, list.toArray());
        String name = vm.getObject(number.intValue()).getValue().toString();
        System.out.println(name);
    }

    public void callInit(){
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv());
        list.add(0);
        DvmObject<?> context = vm.resolveClass("android/content/Context").newObject(null);// context
        list.add(vm.addLocalObject(context));
        module.callFunction(emulator, 0x36a85, list.toArray());
    };

    public void callSimpleSign(){
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv());
        list.add(0);
        String input = "7be9f13e7f5426d139cb4e5dbb1fdba7";
        byte[] inputByte = input.getBytes(StandardCharsets.UTF_8);
        ByteArray inputByteArray = new ByteArray(vm,inputByte);
        list.add(vm.addLocalObject(inputByteArray));
        list.add(vm.addLocalObject(new StringObject(vm, "getdata")));
        Number number = module.callFunction(emulator, 0x869d9, list.toArray());
        System.out.println(vm.getObject(number.intValue()).getValue().toString());
    };

    public static void main(String[] args) {
        Logger.getLogger("com.github.unidbg.linux.ARM32SyscallHandler").setLevel(Level.DEBUG);
        Logger.getLogger("com.github.unidbg.unix.UnixSyscallHandler").setLevel(Level.DEBUG);
        Logger.getLogger("com.github.unidbg.AbstractEmulator").setLevel(Level.DEBUG);
        Logger.getLogger("com.github.unidbg.linux.android.dvm.DalvikVM").setLevel(Level.DEBUG);
        Logger.getLogger("com.github.unidbg.linux.android.dvm.BaseVM").setLevel(Level.DEBUG);
        Logger.getLogger("com.github.unidbg.linux.android.dvm").setLevel(Level.DEBUG);
        SecurityUtil test = new SecurityUtil();
        test.callInit();
        System.out.println("call SimpleSign");
        test.callSimpleSign();
    }


    @Override
    public DvmObject<?> callStaticObjectMethod(BaseVM vm, DvmClass dvmClass, String signature, VarArg varArg) {
        switch (signature) {
            case "android/os/Environment->getExternalStorageDirectory()Ljava/io/File;":{
                return vm.resolveClass("java/io/File").newObject(signature);
            }
            case "okio/zz->b(I)Ljava/lang/String;":{
                int key = varArg.getIntArg(0);
                switch (key){
                    case 1:{
                        return new StringObject(vm, "353626076466627");
                    }
                    case 0:{
                        return new StringObject(vm, "8cff8823cf19b5ec");
                    }
                    case 101:{
                        return new StringObject(vm, "25483");
                    }
                    case 103:{
                        return new StringObject(vm, "1920*1080");
                    }
                    case 104:{
                        return new StringObject(vm, "");
                    }
                    case 102:{
                        return new StringObject(vm, "17637");
                    }
                    case 105:{
                        return new StringObject(vm, "WIFI");
                    }
                    case 106:{
                        return new StringObject(vm, "0.0.0.0:0");
                    }
                    case 8:{
                        return new StringObject(vm, "0.0.0.0:0");
                    }
                    case 9:{
                        return new StringObject(vm, "");
                    }
                    case 10:{
                        return new StringObject(vm, "00:00:00:00:00:00");
                    }
                    case 107:{
                        return new StringObject(vm, "[full-100]");
                    }
                    case 108:{
                        return new StringObject(vm, "78");
                    }
                }
                System.out.println("okio/zz->b(I) Key:"+key);
            }
            case "java/net/NetworkInterface->getByName(Ljava/lang/String;)Ljava/net/NetworkInterface;":{
                String name = null;
                DvmObject<?> namedvm = varArg.getObjectArg(0);
                if(namedvm!=null){
                    name = (String) namedvm.getValue();
                }
                return vm.resolveClass("java/net/NetworkInterface").newObject(name);
            }
        }
        return super.callStaticObjectMethod(vm, dvmClass, signature, varArg);
    }

    @Override
    public DvmObject<?> callObjectMethod(BaseVM vm, DvmObject<?> dvmObject, String signature, VarArg varArg) {
        switch (signature) {
            case "java/io/File->getPath()Ljava/lang/String;":{
                System.out.println("PATH:"+dvmObject.getValue());
                if(dvmObject.getValue().equals("android/os/Environment->getExternalStorageDirectory()Ljava/io/File;")){
                    return new StringObject(vm, "/mnt/sdcard");
                }
                if(dvmObject.getValue()=="android/content/Context->getFilesDir()Ljava/io/File;"){
                    return new StringObject(vm, "/data/data/ctrip.android.view/files");
                }
            }
            case "android/content/Context->getPackageResourcePath()Ljava/lang/String;":
                return new StringObject(vm, "/data/app/ctrip.android.view-fM4xyjk_eygpJsiITNW4JA==/base.apk");
            case "android/content/Context->getFilesDir()Ljava/io/File;":
                return vm.resolveClass("java/io/File").newObject(signature);
            case "android/content/Context->getAssets()Landroid/content/res/AssetManager;":
                return new AssetManager(vm, signature);
            case "java/net/NetworkInterface->getHardwareAddress()[B":
                byte[] result = hexStringToByteArray("64BC0C65AA1E");
                return new ByteArray(vm, result);
        }

        return super.callObjectMethod(vm, dvmObject, signature, varArg);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i+1), 16));
        }
        return data;
    }

    @Override
    public FileResult resolve(Emulator emulator, String pathname, int oflags) {
        if (("/data/app/ctrip.android.view-fM4xyjk_eygpJsiITNW4JA==/base.apk").equals(pathname)) {
            return FileResult.success(new SimpleFileIO(oflags, new File("yourAPkpath\\xxx.apk"), pathname));
        }
        if (("proc/"+emulator.getPid()+"/cmdline").equals(pathname)) {
            return FileResult.success(new ByteArrayFileIO(oflags, pathname, "ctrip.android.view".getBytes()));
        }
        if (("proc/" + emulator.getPid() + "/status").equals(pathname)) {
            return FileResult.success(new ByteArrayFileIO(oflags, pathname, ("Name:   ip.android.view\n" +
                    "State:  R (running)\n" +
                    "Tgid:   "+emulator.getPid()+"\n" +
                    "Pid:    "+emulator.getPid()+"\n" +
                    "PPid:   17506\n" +
                    "TracerPid:      0\n" +
                    "Uid:    10148   10148   10148   10148\n" +
                    "Gid:    10148   10148   10148   10148\n" +
                    "FDSize: 512\n" +
                    "Groups: 3002 3003 9997 20148 50148\n" +
                    "VmPeak:  2224800 kB\n" +
                    "VmSize:  2185240 kB\n" +
                    "VmLck:         0 kB\n" +
                    "VmPin:         0 kB\n" +
                    "VmHWM:    354920 kB\n" +
                    "VmRSS:    324572 kB\n" +
                    "VmData:   379340 kB\n" +
                    "VmStk:      8192 kB\n" +
                    "VmExe:        20 kB\n" +
                    "VmLib:    209888 kB\n" +
                    "VmPTE:      2020 kB\n" +
                    "VmSwap:     3012 kB\n" +
                    "Threads:        127\n" +
                    "SigQ:   2/6517\n" +
                    "SigPnd: 0000000000000000\n" +
                    "ShdPnd: 0000000000000000\n" +
                    "SigBlk: 0000000000001204\n" +
                    "SigIgn: 0000000000000000\n" +
                    "SigCgt: 00000006400096fc\n" +
                    "CapInh: 0000000000000000\n" +
                    "CapPrm: 0000000000000000\n" +
                    "CapEff: 0000000000000000\n" +
                    "CapBnd: 0000000000000000\n" +
                    "CapAmb: 0000000000000000\n" +
                    "Seccomp:        2\n" +
                    "Cpus_allowed:   0f\n" +
                    "Cpus_allowed_list:      0-3\n" +
                    "Mems_allowed:   1\n" +
                    "Mems_allowed_list:      0\n" +
                    "voluntary_ctxt_switches:        21102\n" +
                    "nonvoluntary_ctxt_switches:     20849").getBytes()));
        }
        return null;
    }
}

