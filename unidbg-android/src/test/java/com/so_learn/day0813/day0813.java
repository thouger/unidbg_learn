package com.so_learn.day0813;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Emulator;
import com.github.unidbg.Module;
import com.github.unidbg.arm.backend.Backend;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.SystemPropertyHook;
import com.github.unidbg.linux.android.SystemPropertyProvider;
import com.github.unidbg.linux.android.dvm.AbstractJni;
import com.github.unidbg.linux.android.dvm.DalvikModule;
import com.github.unidbg.linux.android.dvm.VM;
import com.github.unidbg.linux.android.dvm.array.ByteArray;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.pointer.UnidbgPointer;
import com.sun.jna.Pointer;
import unicorn.ArmConst;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class day0813 extends AbstractJni {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;


    day0813() {
        emulator = AndroidEmulatorBuilder.for32Bit().build();

        final Memory memory = emulator.getMemory(); // 模拟器的内存操作接口
        memory.setLibraryResolver(new AndroidResolver(23)); // 设置系统类库解析

        vm = emulator.createDalvikVM(); // 创建Android虚拟机

        emulator.getSyscallHandler().setVerbose(true);
        vm.setVerbose(true); // 设置是否打印Jni调用细节
        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/java/com/so_learn/day0813/libnative-lib.so"), true);
        module = dm.getModule(); //
        vm.setJni(this);
    }

    public int call(){
        List<Object> objectList = new ArrayList<>();
        objectList.add(vm.getJNIEnv());
        objectList.add(0);
        Number number = module.callFunction(emulator, 0x7AD, objectList.toArray());
        return number.intValue();
    }

    public static void main(String[] args) {
        day0813 demo = new day0813();
        System.out.println(demo.call());
    }

    private static final int RUSAGE_SELF = 0;
    private static final int RUSAGE_CHILDREN = -1;
    private static final int RUSAGE_BOTH = -2;
    private static final int RUSAGE_THREAD = 1;
    private int getrusage(Backend backend, Emulator<?> emulator){
        int who = backend.reg_read(ArmConst.UC_ARM_REG_R0).intValue();
        Pointer usage = UnidbgPointer.register(emulator, ArmConst.UC_ARM_REG_R1);
        usage.setLong(0, 1);
        usage.setLong(8,0x10000);
        return 0;
    };

}
