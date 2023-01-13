package com.so_learn.Anti_Unidbg2;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Module;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.virtualmodule.android.AndroidModule;

import java.io.File;

public class findclass {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    findclass() {
        emulator = AndroidEmulatorBuilder
                .for32Bit()
                .build();
        final Memory memory = emulator.getMemory();
        memory.setLibraryResolver(new AndroidResolver(23));
        vm = emulator.createDalvikVM(new File("unidbg-android/src/test/java/com/so_learn/Anti-Unidbg2/app-debug.apk"));
        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/java/com/so_learn/Anti-Unidbg2/libfindmyclass.so"), true);
        module = dm.getModule();
        vm.setVerbose(true); // 打印日志

        // 添加类到《不存在的类列表》
//        vm.addNotFoundClass("my/fake/class");
    };

    public static void main(String[] args) {
        findclass demo = new findclass();
        demo.call();
    }

    public void call(){
        DvmClass dvmClass = vm.resolveClass("com/example/findmyclass/MainActivity");
        String methodSign = "stringFromJNI()Ljava/lang/String;";
        DvmObject<?> dvmObject = dvmClass.newObject(null);


        StringObject obj = dvmObject.callJniMethodObject(emulator, methodSign);
        System.out.println(obj.getValue());
    }
}
