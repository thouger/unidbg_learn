package com.so_learn.Anti_Unidbg3;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Module;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.memory.Memory;

import java.io.File;

public class showMethod extends AbstractJni {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    showMethod() {
        emulator = AndroidEmulatorBuilder
                .for32Bit()
                .build();
        final Memory memory = emulator.getMemory();
        memory.setLibraryResolver(new AndroidResolver(23));
        vm = emulator.createDalvikVM(new File("unidbg-android/src/test/java/com/so_learn/Anti_Unidbg3/1/app-debug.apk"));
        vm.setJni(this);
        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/java/com/so_learn/Anti_Unidbg3/1/libgetmethodid.so"), true);
        module = dm.getModule();
        vm.setVerbose(true); // 打印日志
// 添加类到《不存在的类列表》
        vm.addNotFoundClass("my/fake/class");
    }

    ;

    public static void main(String[] args) {
        showMethod demo = new showMethod();
        demo.call();
    }

    public void call() {
        DvmClass dvmClass =
                vm.resolveClass("com/example/getmethodid/MainActivity");
        String methodSign = "stringFromJNI()Ljava/lang/String;";
        DvmObject<?> dvmObject = dvmClass.newObject(null);
        StringObject obj = dvmObject.callJniMethodObject(emulator, methodSign);
        System.out.println(obj);
    }

    @Override
    public DvmObject<?> callObjectMethodV(BaseVM vm, DvmObject<?> dvmObject, String
            signature, VaList vaList) {
        switch (signature) {
            case "com/example/getmethodid/MainActivity->getName()Ljava/lang/String;": {
                return new StringObject(vm, "lilac");
            }
        }
        return super.callObjectMethodV(vm, dvmObject, signature, vaList);
    }
}