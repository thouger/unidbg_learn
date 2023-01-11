package com.so_learn.WTF1;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Emulator;
import com.github.unidbg.Module;
import com.github.unidbg.debugger.BreakPointCallback;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.pointer.UnidbgPointer;
import com.sun.jna.Pointer;
import keystone.Keystone;
import keystone.KeystoneArchitecture;
import keystone.KeystoneEncoded;
import keystone.KeystoneMode;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import unicorn.ArmConst;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WTF1 extends AbstractJni {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    public WTF1() {
        emulator = AndroidEmulatorBuilder
                .for32Bit()
                .setProcessName("com.cbgc")
                .build();
        final Memory memory = emulator.getMemory();
        memory.setLibraryResolver(new AndroidResolver(23));
// 创建Android虚拟机,传入APK，Unidbg可以替我们处理许多问题
        File apkFile = new File("unidbg-android/src/test/java/com/so_learn/WTF1/cbgc.apk");
        vm = emulator.createDalvikVM(apkFile);
// 加载目标SO
        File file = new File("unidbg-android/src/test/java/com/so_learn/WTF1/libwtf.so");
        DalvikModule dm = vm.loadLibrary(file, true);
        module = dm.getModule();
        vm.setJni(this); // 设置JNI
        vm.setVerbose(true); // 打印日志
        dm.callJNI_OnLoad(emulator);// 调用JNI OnLoad
    }

    public static void main(String[] args) {
        Logger.getLogger("com.github.unidbg.linux.ARM32SyscallHandler").setLevel(Level.DEBUG);
        Logger.getLogger("com.github.unidbg.unix.UnixSyscallHandler").setLevel(Level.DEBUG);
        Logger.getLogger("com.github.unidbg.AbstractEmulator").setLevel(Level.DEBUG);
        Logger.getLogger("com.github.unidbg.linux.android.dvm.DalvikVM").setLevel(Level.DEBUG);
        Logger.getLogger("com.github.unidbg.linux.android.dvm.BaseVM").setLevel(Level.DEBUG);
        Logger.getLogger("com.github.unidbg.linux.android.dvm").setLevel(Level.DEBUG);
        WTF1 wtf = new WTF1();
        wtf.patchLog();
        System.out.println(wtf.getSign());
    }

    public String getSign() {
        List<Object> list = new ArrayList<>(10);
// arg1 env
        list.add(vm.getJNIEnv());
// arg2 jobject/jclazz 一般用不到，直接填0
        list.add(0);
        list.add(vm.addLocalObject(new StringObject(vm, "")));
        list.add(vm.addLocalObject(new StringObject(vm, "")));
        list.add(vm.addLocalObject(new StringObject(vm, "1628093856262")));
// 参数准备完成
// call function
        Number number = module.callFunction(emulator, 0x931, list.toArray());
        String result = vm.getObject(number.intValue()).getValue().toString();
        return result;
    }

    @Override
    public DvmObject<?> callStaticObjectMethodV(BaseVM vm, DvmClass dvmClass,
                                                String signature, VaList vaList) {
        if (signature.equals("com/sichuanol/cbgc/util/LogShutDown->getAppSign()Ljava/lang/String;")) {
            return new StringObject(vm, "0093CB6721DAF15D31CFBC9BBE3A2B79");
        }
        return super.callStaticObjectMethodV(vm, dvmClass, signature, vaList);
    }

    public void patchLog(){
        int patchCode = 0x46004600;
        emulator.getMemory().pointer(module.base + 0xABE).setInt(0,patchCode);
    }

    public void patchLog1(){
        Pointer pointer = UnidbgPointer.pointer(emulator, module.base + 0xABE);
        assert pointer != null;
        try (Keystone keystone = new Keystone(KeystoneArchitecture.Arm,
                KeystoneMode.ArmThumb)) {
            KeystoneEncoded encoded = keystone.assemble("nop");
            byte[] patch = encoded.getMachineCode();
            pointer.write(0, patch, 0, patch.length);
            pointer.write(2, patch, 0, patch.length);
        }
    };

    public void patchLog2(){
        emulator.attach().addBreakPoint(module.base + 0xABE, new
                BreakPointCallback() {
                    @Override
                    public boolean onHit(Emulator<?> emulator, long address) {
                        emulator.getBackend().reg_write(ArmConst.UC_ARM_REG_PC,
                                (address)+5);
                        return true;
                    }
                });
    }

}