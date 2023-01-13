package com.so_learn.Anti_Unidbg3;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Module;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.memory.Memory;

import java.io.File;

public class getmethodidexample1 extends AbstractJni {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    getmethodidexample1() {
        emulator = AndroidEmulatorBuilder
                .for32Bit()
                .build();
        final Memory memory = emulator.getMemory();
        memory.setLibraryResolver(new AndroidResolver(23));
        vm = emulator.createDalvikVM(new File("unidbg-android/src/test/java/com/so_learn/Anti_Unidbg3/2/app-debug.apk"));
        vm.setJni(this);
        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/java/com/so_learn/Anti_Unidbg3/2/libgetmethodidexample0.so"), true);
        module = dm.getModule();
        vm.setVerbose(true); // 打印日志

        // 声明XiaoMi继承自Phone
        vm.resolveClass("com/example/getmethodidexample0/XiaoMi", vm.resolveClass("com/example/getmethodidexample0/Phone"));
    };

    public static void main(String[] args) {
        getmethodidexample1 demo = new getmethodidexample1();
        demo.call();
    }

    public void call(){
        DvmClass dvmClass = vm.resolveClass("com/example/getmethodidexample0/MainActivity");
        String methodSign = "stringFromJNI()Ljava/lang/String;";
        DvmObject<?> dvmObject = dvmClass.newObject(null);

        StringObject obj = dvmObject.callJniMethodObject(emulator, methodSign);
        System.out.println(obj);
    }

    @Override
    public DvmObject<?> newObjectV(BaseVM vm, DvmClass dvmClass, String signature, VaList vaList) {
        switch (signature){
            case "com/example/getmethodidexample0/XiaoMi-><init>()V":{
                return vm.resolveClass("com/example/getmethodidexample0/XiaoMi").newObject(null);
            }
        }
        return super.newObjectV(vm, dvmClass, signature, vaList);
    }
}