package com.so_learn.shumei;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Emulator;
import com.github.unidbg.Module;
import com.github.unidbg.file.FileResult;
import com.github.unidbg.file.IOResolver;
import com.github.unidbg.file.linux.AndroidFileIO;
import com.github.unidbg.linux.android.AndroidARMEmulator;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.linux.android.dvm.array.ByteArray;
import com.github.unidbg.linux.file.ByteArrayFileIO;
import com.github.unidbg.linux.file.DumpFileIO;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.memory.SvcMemory;
import com.github.unidbg.unix.UnixSyscallHandler;
import com.github.unidbg.virtualmodule.android.AndroidModule;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.File;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.github.unidbg.Emulator;
import com.github.unidbg.arm.context.EditableArm32RegisterContext;
import com.github.unidbg.linux.ARM32SyscallHandler;
import com.github.unidbg.memory.SvcMemory;
import com.sun.jna.Pointer;

import java.util.concurrent.ThreadLocalRandom;

import static com.so_learn.lession10.SecurityUtil.hexStringToByteArray;

class MyARM32SyscallHandler extends ARM32SyscallHandler {
    public MyARM32SyscallHandler(SvcMemory svcMemory) {
        super(svcMemory);
    }

    @Override
    protected boolean handleUnknownSyscall(Emulator emulator, int NR) {
        switch (NR) {
            case 190:
                vfork(emulator);
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

    protected int pipe2(Emulator<?> emulator) {
        EditableArm32RegisterContext context = (EditableArm32RegisterContext) emulator.getContext();
        Pointer pipefd = context.getPointerArg(0);
        int flags = context.getIntArg(1);
        int write = getMinFd();
        this.fdMap.put(write, new DumpFileIO(write));
        int read = getMinFd();
        String stdout = "9ab5f193-ca2a-4d7e-8dc2-09e0ff1f257f\n";
        this.fdMap.put(read, new ByteArrayFileIO(0, "pipe2_read_side", stdout.getBytes()));
        pipefd.setInt(0, read);
        pipefd.setInt(4, write);
        System.out.println("pipe2 pipefd=" + pipefd + ", flags=0x" + flags + ", read=" + read + ", write=" + write + ", stdout=" + stdout);
        context.setR0(0);
        return 0;
    }
}

public class shumei extends AbstractJni implements IOResolver {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    shumei() {
        // 创建模拟器实例
//        emulator = AndroidEmulatorBuilder
//                .for32Bit()
//                .setRootDir(new File("target/rootfs"))
//                .build();

        AndroidEmulatorBuilder builder = new AndroidEmulatorBuilder(false) {
            @Override
            public AndroidEmulator build() {
                return new AndroidARMEmulator(processName, rootDir, backendFactories) {
                    @Override
                    protected UnixSyscallHandler<AndroidFileIO> createSyscallHandler(SvcMemory svcMemory) {
                        return new MyARM32SyscallHandler(svcMemory);
                    }
                };
            }

            ;
        };
        emulator = builder.setRootDir(new File("target/rootfs")).build();

        // 获取模拟器的内存操作接口
        final Memory memory = emulator.getMemory();
        // 设置系统类库解析
        memory.setLibraryResolver(new AndroidResolver(23));
        //  绑定重定向
        emulator.getSyscallHandler().addIOResolver(this);

        vm = emulator.createDalvikVM(new File("unidbg-android/src/test/java/com/so_learn/shumei/com.jiuwu_1.25.0_1002500.apk"));
        new AndroidModule(emulator, vm).register(memory);
        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/java/com/so_learn/shumei/libsmsdk.so"), true); // 加载so到虚拟内存
        //获取本SO模块的句柄,后续需要用它
        module = dm.getModule();
        vm.setJni(this);
        vm.setVerbose(true); // 打印日志

        dm.callJNI_OnLoad(emulator); // 调用JNI OnLoad
    }

    ;

    @Override
    public FileResult resolve(Emulator emulator, String pathname, int oflags) {
        System.out.println("lilac Path:" + pathname);
        // 具体的处理
        return null;
    }

    public static void main(String[] args) {
        shumei demo = new shumei();
        demo.w1();
    }

    public void w1() {
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv());
        list.add(0);
        DvmObject<?> obj = vm.resolveClass("android/content/Context").newObject(null);
        list.add(vm.addLocalObject(obj));
        String str2 = "{\"a1\":\"all\",\"a3\":\"none\",\"a4\":\"4\",\"a2\":\"SRcM3hsEtSjSE1fQv1CaresO925Tis1PYZFK58Ez2MNqdhO6k0RLGaCyM8NldbOl4bFXZOCiXTuZJ+Va9w5pRw==\",\"a5\":\"\",\"a7\":\"3.0.4\",\"a8\":\"\",\"a6\":\"android\",\"a44\":\"wifi\",\"a47\":[\"16,qualcomm\",\"4,qualcomm\",\"19,qualcomm\",\"19,qualcomm\",\"9,qualcomm\",\"18,qualcomm\",\"18,qualcomm\",\"17,qualcomm\",\"22,qualcomm\",\"2,akm\",\"10,qualcomm\",\"20,qualcomm\",\"3,xiaomi\",\"30,qualcomm\",\"30,qualcomm\",\"33171027,XiaoMi\",\"33171027,XiaoMi\",\"33171036,XiaoMi\",\"33171036,XiaoMi\",\"11,xiaomi\",\"5,Rohm\",\"5,Rohm\",\"6,Bosch\",\"29,qualcomm\",\"29,qualcomm\",\"1,qualcomm\",\"35,qualcomm\",\"15,qualcomm\",\"27,xiaomi\",\"27,xiaomi\",\"33171029,XiaoMi\",\"33171029,XiaoMi\",\"14,akm\",\"33171070,xiaomi\",\"33171070,xiaomi\",\"8,Elliptic Labs\",\"33171031,xiaomi\"],\"a46\":{\"cpu_abi\":\"armeabi-v7a\",\"serial\":\"unknown\",\"fingerprint\":\"Xiaomi\\/polaris\\/polaris:10\\/QKQ1.190828.002\\/V12.0.2.0.QDGCNXM:user\\/release-keys\",\"model\":\"MIX 2S\",\"cpu_abi2\":\"armeabi\",\"brand\":\"Xiaomi\",\"board\":\"sdm845\",\"serial_P\":\"unknown\",\"manufacturer\":\"Xiaomi\"},\"a38\":\"1.25.0\",\"a33\":\"ARMv8 Processor rev 13 (v8l)\",\"a103\":\"faf6c8c7ad942343\",\"a23\":\"\",\"a54\":\"0000010\",\"a48\":5905514496,\"a10\":\"10\",\"a11\":\"95fen\",\"a15\":\"false\",\"a17\":[\"wlan1,,f460e217db64,\",\"wlan0,172.16.16.12,f460e296db64,fe80::f660:e2ff:fe96:db64%wlan0\",\"p2p0,,f660e218db64,\"],\"a18\":{\"ro.boot.hardware\":\"qcom\",\"gsm.sim.state\":\"LOADED,LOADED\",\"sys.usb.state\":\"adb\",\"ro.debuggable\":\"0\"},\"a19\":\"02:00:00:00:00:00\",\"a9\":1628128282220,\"a39\":\"com.jiuwu\",\"a40\":1627972313927,\"a45\":\"46001\",\"a21\":\"\",\"a24\":\"6d9de21492b99db9\",\"a25\":\"\",\"a22\":\"\",\"a34\":2803200,\"a37\":1295,\"a27\":[\"1628127546162,com.jiuwu,,1,1002500,1.25.0,1628127546162\",\"1230768000000,com.android.cts.priv.ctsshim,,0,28,9-5374186,1230768000000\",\"1230768000000,com.miui.contentextension,,0,10164,2.4.2,1611338104848\",\"1230768000000,com.qualcomm.qti.qcolor,,0,29,10,1230768000000\",\"1230768000000,com.android.internal.display.cutout.emulation.corner,,0,1,1.0,1230768000000\",\"1230768000000,com.google.android.ext.services,,0,291900801,q_pr1-release_aml_291900801,1230768000000\",\"1230768000000,com.qualcomm.qti.improvetouch.service,,0,29,10,1230768000000\",\"1230768000000,com.android.internal.display.cutout.emulation.double,,0,1,1.0,1230768000000\",\"1230768000000,com.android.providers.telephony,,0,29,10,1230768000000\",\"1230768000000,com.android.dynsystem,,0,29,10,1230768000000\"],\"a29\":\"4.0.c2.6-00335-0914_2350_3c8fca6,4.0.c2.6-00335-0914_2350_3c8fca6\",\"a32\":8,\"a30\":\"<unknown ssid>\",\"a31\":\"172.16.16.12\",\"a90\":28,\"a105\":{},\"a108\":\"\",\"a109\":\"\",\"a110\":\"\",\"a111\":\"\",\"a107\":{\"java\\/lang\\/reflect\\/Modifier\":2,\"com\\/android\\/internal\\/telephony\\/PhoneProxy\":2,\"java\\/lang\\/ProcessBuilder\":2,\"com\\/android\\/internal\\/telephony\\/PhoneSubInfo\":2,\"android\\/location\\/LocationManager\":2,\"com\\/tencent\\/mapapi\\/service\\/LocationManager\":2,\"com\\/android\\/internal\\/telephony\\/gsm\\/GSMPhone\":2},\"a20\":\"\",\"a49\":\"\",\"a52\":{\"magisk\":1},\"a53\":{},\"a50\":{},\"a60\":\"u0_a349\",\"a62\":\"\\/data\\/user\\/0\\/com.jiuwu\\/files\",\"a55\":\"a58929cd0e3202053f6137261ecd3c40\",\"a57\":1160259262,\"a36\":\"1080,2030,440\",\"a56\":\"CN=jiuwu, OU=jiuwu, O=jiuwu, L=上海, ST=上海, C=CN\",\"a76\":\"\",\"a88\":\"locateServiceName:android.os.BinderProxy|phoneServiceName:android.os.BinderProxy\",\"a84\":\"3vDSuAiODgqAwBuSIqCEpuAFlJ+xKBFFJ383k+\\/M2+M=___\",\"a68\":[],\"a92\":\"1080,2030\",\"a93\":0,\"a95\":-1,\"a96\":\"dU56APPx0pvUizMTZXVP\",\"a97\":\"SRcM3hsEtSjSE1fQv1CaresO925Tis1PYZFK58Ez2MNqdhO6k0RLGaCyM8NldbOl4bFXZOCiXTuZJ+Va9w5pRw==\",\"a98\":\"\",\"a99\":\"\",\"a100\":\"\",\"a101\":\"\",\"a102\":[],\"a63\":[\"InputMethodInfo{com.sohu.inputmethod.sogou.xiaomi\\/.SogouIME, settings: com.sohu.inputmethod.sogou.SogouIMESettingsLauncher}\",\"InputMethodInfo{com.iflytek.inputmethod.miui\\/.FlyIME, settings: com.iflytek.inputmethod.LauncherSettingsActivity}\"],\"a67\":{},\"a64\":{\"suc\":\"1\",\"enable\":\"0\",\"service\":[]},\"a65\":39,\"a66\":{},\"a74\":0,\"a73\":0,\"a78\":[],\"a75\":0,\"a77\":{},\"a86\":\"1100100\",\"a79\":\"\",\"a80\":\"1628128282029-12295\",\"a83\":\"1001100\",\"a85\":[],\"a69\":15533490176,\"a71\":118982303744,\"a72\":{\"temp\":370,\"vol\":4095,\"level\":85,\"scale\":100,\"status\":2},\"a70\":15684485120}";
        String str3 = "{\"all_atamper\":true,\"core_atamper\":true,\"hook_java_switch\":true,\"hook_switch\":false,\"risk_apps\":[{\"xposed\":{\"pn\":\"de.robv.android.xposed.installer\",\"uri\":\"\"}},{\"controllers\":{\"pn\":\"com.soft.controllers\",\"uri\":\"\"}},{\"apk008v\":{\"pn\":\"com.soft.apk008v\",\"uri\":\"\"}},{\"apk008Tool\":{\"pn\":\"com.soft.apk008Tool\",\"uri\":\"\"}},{\"ig\":{\"pn\":\"com.doubee.ig\",\"uri\":\"\"}},{\"anjian\":{\"pn\":\"com.cyjh.mobileanjian\",\"uri\":\"\"}},{\"rktech\":{\"pn\":\"com.ruokuai.rktech\",\"uri\":\"\"}},{\"magisk\":{\"pn\":\"com.topjohnwu.magisk\",\"uri\":\"\"}},{\"kinguser\":{\"pn\":\"com.kingroot.kinguser\",\"uri\":\"\"}},{\"substrate\":{\"pn\":\"com.saurik.substrate\",\"uri\":\"\"}},{\"touchsprite\":{\"pn\":\"com.touchsprite.android\",\"uri\":\"\"}},{\"scriptdroid\":{\"pn\":\"com.stardust.scriptdroid\",\"uri\":\"\"}},{\"toolhero\":{\"pn\":\"com.mobileuncle.toolhero\",\"uri\":\"\"}},{\"huluxia\":{\"pn\":\"com.huluxia.gametools\",\"uri\":\"\"}},{\"apkeditor\":{\"pn\":\"com.gmail.heagoo.apkeditor.pro\",\"uri\":\"\"}},{\"xposeddev\":{\"pn\":\"com.sollyu.xposed.hook.model.dev\",\"uri\":\"\"}},{\"anywhere\":{\"pn\":\"com.txy.anywhere\",\"uri\":\"\"}},{\"burgerzwsm\":{\"pn\":\"pro.burgerz.wsm.manager\",\"uri\":\"\"}},{\"vdloc\":{\"pn\":\"com.virtualdroid.loc\",\"uri\":\"\"}},{\"vdtxl\":{\"pn\":\"com.virtualdroid.txl\",\"uri\":\"\"}},{\"vdwzs\":{\"pn\":\"com.virtualdroid.wzs\",\"uri\":\"\"}},{\"vdkit\":{\"pn\":\"com.virtualdroid.kit\",\"uri\":\"\"}},{\"vdwxg\":{\"pn\":\"com.virtualdroid.wxg\",\"uri\":\"\"}},{\"vdgps\":{\"pn\":\"com.virtualdroid.gps\",\"uri\":\"\"}},{\"a1024mloc\":{\"pn\":\"top.a1024bytes.mockloc.ca.pro\",\"uri\":\"\"}},{\"drhgz\":{\"pn\":\"com.deruhai.guangzi.noroot2\",\"uri\":\"\"}},{\"yggb\":{\"pn\":\"com.mcmonjmb.yggb\",\"uri\":\"\"}},{\"xsrv\":{\"pn\":\"xiake.xserver\",\"uri\":\"\"}},{\"fakeloc\":{\"pn\":\"com.dracrays.fakeloc\",\"uri\":\"\"}},{\"ultra\":{\"pn\":\"net.anylocation.ultra\",\"uri\":\"\"}},{\"locationcheater\":{\"pn\":\"com.wifi99.android.locationcheater\",\"uri\":\"\"}},{\"dwzs\":{\"pn\":\"com.dingweizshou\",\"uri\":\"\"}},{\"mockloc\":{\"pn\":\"top.a1024bytes.mockloc.ca.pro\",\"uri\":\"\"}},{\"anywhereclone\":{\"pn\":\"com.txy.anywhere.clone\",\"uri\":\"\"}},{\"fakelocc\":{\"pn\":\"com.dracrays.fakelocc\",\"uri\":\"\"}},{\"mockwxlocation\":{\"pn\":\"com.tandy.android.mockwxlocation\",\"uri\":\"\"}},{\"anylocation\":{\"pn\":\"net.anylocation\",\"uri\":\"\"}},{\"totalcontrol\":{\"pn\":\"com.sigma_rt.totalcontrol\",\"uri\":\"\"}},{\"ipjl2\":{\"pn\":\"com.chuangdian.ipjl2\",\"uri\":\"\"}}],\"risk_dirs\":[{\"008Mode\":{\"dir\":\".system/008Mode\",\"type\":\"sdcard\"}},{\"008OK\":{\"dir\":\".system/008OK\",\"type\":\"sdcard\"}},{\"008system\":{\"dir\":\".system/008system\",\"type\":\"sdcard\"}},{\"iGrimace\":{\"dir\":\"iGrimace\",\"type\":\"sdcard\"}},{\"touchelper\":{\"dir\":\"/data/data/net.aisence.Touchelper\",\"type\":\"absolute\"}},{\"elfscript\":{\"dir\":\"/mnt/sdcard/touchelf/scripts/\",\"type\":\"absolute\"}},{\"spritelua\":{\"dir\":\"/mnt/sdcard/TouchSprite/lua\",\"type\":\"absolute\"}},{\"spritelog\":{\"dir\":\"/mnt/sdcard/TouchSprite/log\",\"type\":\"absolute\"}},{\"assistant\":{\"dir\":\"/data/data/com.xxAssistant\",\"type\":\"absolute\"}},{\"assistantscript\":{\"dir\":\"/mnt/sdcard/com.xxAssistant/script\",\"type\":\"absolute\"}},{\"mobileanjian\":{\"dir\":\"/data/data/com.cyjh.mobileanjian\",\"type\":\"absolute\"}}],\"risk_file_switch\":true,\"risk_files\":\"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\",\"sensitive.ainfo\":true,\"sensitive.apps\":true,\"sensitive.aps\":true,\"sensitive.bssid\":true,\"sensitive.camera\":true,\"sensitive.cell\":true,\"sensitive.gps\":false,\"sensitive.iccid\":true,\"sensitive.imsi\":true,\"sensitive.mac\":true,\"sensitive.ssid\":true,\"sensitive.tel\":false,\"white_apps\":[]}";
        String str4 = "MIIDLzCCAhegAwIBAgIBMDANBgkqhkiG9w0BAQUFADAyMQswCQYDVQQGEwJDTjELMAkGA1UECwwCU00xFjAUBgNVBAMMDWUuaXNodW1laS5jb20wHhcNMjAxMjA3MDMzMDE4WhcNNDAxMjAyMDMzMDE4WjAyMQswCQYDVQQGEwJDTjELMAkGA1UECwwCU00xFjAUBgNVBAMMDWUuaXNodW1laS5jb20wggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEKAoIBAQCT947yNGa4EPvheGp6hsDoU4kBKvmwacn6tqfWit/jlXaZZBSPcw43jjxGuF4eXM4NPJJtMft/j0IIwJeEx0YHDCJIqU/1pEPsXYb0lBhwD5mq34c0RiRxlji+g+D4rFRO/XrefRJSeB3W1djvOAMkXoygp+813ZM6mzPd36zjbUIaJfzkC5LOeITUcC6Db98XiN/hNmvciWtiO1Sm9FEU1ip1fFb9NZ04vb2Z6Xt/ti/rUVzWyshZClqqVq4s9W4iGPqfTnBsxttiooRUproe2LtB+J73kKTgJJH6OpnOljqd+FaMsL/sddY6lggM+w4ePTe4HF+/dV2ZZp+w+8AtAgMBAAGjUDBOMB0GA1UdDgQWBBS83RQZA5/0RAVrhWrYFlnyreX4FjAfBgNVHSMEGDAWgBS83RQZA5/0RAVrhWrYFlnyreX4FjAMBgNVHRMEBTADAQH/MA0GCSqGSIb3DQEBBQUAA4IBAQCAayqoRv2uOwKT3mrkkZO6fn+mH124C8Djm15jCRjYqOISpgkgsReEX2FO0sxYqBuRPidycdsRNyQG44/i4PQrBwc9T/wLSOyHICaKbXXPhfWl4PLRNR0LtgmCLoIveDyjzTn3BEf57tZCYSmpHMUI0eJeV9o3yhluURv3Vbiqh+0ca2MqL9m7N49DkkgeZ04FAWUp9yG+p1jf5tA1wa6t1vvH1T8TKvWjGtBH3jVYenKBk+W+DWzZnDepgOl+8BXozO0JP5U1u68Sqf+cke0BwlRfsTFU4yAoEBsIIZ/Stx7Q82K8M4XucAFV8PTT8i30QoGcSduEj4zape1vNn7f";
        String str5 = "dU56APPx0pvUizMTZXVP";
        String str6 = "95fen";
        list.add(vm.addLocalObject(new StringObject(vm, str2)));
        list.add(vm.addLocalObject(new StringObject(vm, str3)));
        list.add(vm.addLocalObject(new StringObject(vm, str4)));
        list.add(vm.addLocalObject(new StringObject(vm, str5)));
        list.add(vm.addLocalObject(new StringObject(vm, str6)));
        Number number = module.callFunction(emulator, 0x16f1d, list.toArray());
        String result = vm.getObject(number.intValue()).getValue().toString();
        System.out.println(result);
    }


    @Override
    public DvmObject<?> callStaticObjectMethodV(BaseVM vm, DvmClass dvmClass, String signature, VaList vaList) {
        switch (signature) {
            case "android/os/Environment->getExternalStorageDirectory()Ljava/io/File;": {
                return vm.resolveClass("java/io/File").newObject(signature);
            }
        }
        return super.callStaticObjectMethodV(vm, dvmClass, signature, vaList);
    }

    @Override
    public DvmObject<?> callObjectMethodV(BaseVM vm, DvmObject<?> dvmObject, String signature, VaList vaList) {
        switch (signature){
            case "java/io/File->getAbsolutePath()Ljava/lang/String;":{
                String tag = dvmObject.getValue().toString();
                if(tag.equals("android/os/Environment->getExternalStorageDirectory()Ljava/io/File;")){
                    return new StringObject(vm, "/storage/emulated/0");
                }
            }
            case "java/security/cert/Certificate->getPublicKey()Ljava/security/PublicKey;":{
                return vm.resolveClass("java/security/PublicKey").newObject(signature);
            }
            case "javax/crypto/Cipher->doFinal([B)[B":{
                byte[] result = hexStringToByteArray("10067ad9102b18783bc3242d4a3781c15822b0e8098b8956fff207ec8b04746e15307d3b615bee9db188b4102d7c102becf6990afc95464aa987b6c83707af8e95f3137db46db900cd5ca68fd5b81fad0623950a24b42e39df031849e8130d33a26e0db637ff5fe7f14f3b527f7b2dd3f2fc8f2fdbadad2ca2d0327f0e10bfb7144e8b7bc40d7c5820c993fa17c139995946b2f1402e529ce7b4803f78f6026cb780f9a052a0ddd571a1ac13b06b27b4ad1432ce99efb93366dee4a67dccf302a612f902a03e06ebe1ba6eb8e84479cda22d6ad92834c4db0b6aa0ccea8a5fd0fc466fe595b3dc6038d9975bb7ab6e5df7840a330cf572f2ab3207b25f3270cd");
                return new ByteArray(vm, result);
            }

        }
        return super.callObjectMethodV(vm, dvmObject, signature, vaList);
    }

    @Override
    public DvmObject<?> newObjectV(BaseVM vm, DvmClass dvmClass, String signature, VaList vaList) {
        switch (signature){
            case "javax/crypto/spec/OAEPParameterSpec-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/security/spec/AlgorithmParameterSpec;Ljavax/crypto/spec/PSource;)V":{
                return vm.resolveClass("javax/crypto/spec/OAEPParameterSpec").newObject(signature);
            }
        }
        return super.newObjectV(vm, dvmClass, signature, vaList);
    }

    @Override
    public void callVoidMethodV(BaseVM vm, DvmObject<?> dvmObject, String signature, VaList vaList) {
        switch (signature){
            case "javax/crypto/Cipher->init(ILjava/security/Key;Ljava/security/spec/AlgorithmParameterSpec;)V":{
                vm.resolveClass("javax/crypto/Cipher").newObject(signature);
                return;
            }
        }
        super.callVoidMethodV(vm, dvmObject, signature, vaList);
    }


    @Override
    public DvmObject<?> getStaticObjectField(BaseVM vm, DvmClass dvmClass, String
            signature) {
        switch (signature) {
            case "java/security/spec/MGF1ParameterSpec->SHA256:Ljava/security/spec/MGF1ParameterSpec;": {
                return
                        vm.resolveClass("java/security/spec/MGF1ParameterSpec").newObject(signature);
            }
            case "javax/crypto/spec/PSource$PSpecified->DEFAULT:Ljavax/crypto/spec/PSource$PSpecified;": {
                return
                        vm.resolveClass("javax/crypto/spec/PSource$PSpecified").newObject(signature);
            }
        }
        return super.getStaticObjectField(vm, dvmClass, signature);
    }
}