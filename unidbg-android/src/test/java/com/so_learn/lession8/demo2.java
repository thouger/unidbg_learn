package com.so_learn.lession8;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Module;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.virtualmodule.android.AndroidModule;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class demo2 extends AbstractJni {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    demo2(){
        // 防止进程名检测
        emulator = AndroidEmulatorBuilder.for32Bit().setProcessName("com.readAssets").build(); // 创建模拟器实例，要模拟32位或者64位，在这里区分
        final Memory memory = emulator.getMemory(); // 模拟器的内存操作接口
        memory.setLibraryResolver(new AndroidResolver(23)); // 设置系统类库解析
        vm = emulator.createDalvikVM(new File("unidbg-android\\src\\test\\java\\com\\so_learn\\lession8\\demo2.apk"));

        //   注册libandroid.so虚拟模块
        new AndroidModule(emulator, vm).register(memory);

        DalvikModule dm = vm.loadLibrary(new File("unidbg-android\\src\\test\\java\\com\\so_learn\\lession8\\readassets.so"), true);
        module = dm.getModule();

        emulator.traceCode(module.base, module.base + module.size);
        vm.setJni(this);
        vm.setVerbose(true);
    }


    public String call(){
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv()); // 第一个参数是env
        list.add(0); // 第二个参数，实例方法是jobject，静态方法是jclazz，直接填0，一般用不到。
        Object custom = null;
        DvmObject<?> assetManager = vm.resolveClass("android/content/res/AssetManager").newObject(custom);// context
        list.add(vm.addLocalObject(assetManager));

        Number number = module.callFunction(emulator, 0x207C + 1, list.toArray());
        String result = vm.getObject(number.intValue()).getValue().toString();
        return result;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Logger.getLogger("com.github.unidbg.linux.ARM32SyscallHandler").setLevel(Level.DEBUG);
        Logger.getLogger("com.github.unidbg.unix.UnixSyscallHandler").setLevel(Level.DEBUG);
        Logger.getLogger("com.github.unidbg.AbstractEmulator").setLevel(Level.DEBUG);
        Logger.getLogger("com.github.unidbg.linux.android.dvm.DalvikVM").setLevel(Level.DEBUG);
        Logger.getLogger("com.github.unidbg.linux.android.dvm.BaseVM").setLevel(Level.DEBUG);
        Logger.getLogger("com.github.unidbg.linux.android.dvm").setLevel(Level.DEBUG);
        demo2 test = new demo2();
        System.out.println("call demo2");
        System.out.println(test.call());
    }

}
