package com.so_learn.Anti;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Module;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.virtualmodule.android.AndroidModule;

import java.io.File;

public class EnvTrap {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    EnvTrap() {
        emulator = AndroidEmulatorBuilder
                .for32Bit()
                .build();
        final Memory memory = emulator.getMemory();
        memory.setLibraryResolver(new AndroidResolver(23));
        vm = emulator.createDalvikVM(new File("unidbg-android/src/test/java/com/so_learn/Anti-Unidbg1/app-debug.apk"));
        new AndroidModule(emulator, vm).register(memory);
        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/java/com/so_learn/Anti-Unidbg1/libgetenv.so"), true);
        module = dm.getModule();
        vm.setVerbose(true); // 打印日志
    };

    public static void main(String[] args) {
        EnvTrap demo = new EnvTrap();
        demo.call();
    }

    public void call(){
        DvmClass dvmClass = vm.resolveClass("com/example/getenv/MainActivity");
        String methodSign = "stringFromJNI()Ljava/lang/String;";
        DvmObject<?> dvmObject = dvmClass.newObject(null);

        StringObject obj = dvmObject.callJniMethodObject(emulator, methodSign);
        System.out.println(obj.getValue());
    }
}
