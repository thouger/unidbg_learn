package com.so_learn.lession9;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Emulator;
import com.github.unidbg.Module;
import com.github.unidbg.debugger.Debugger;
import com.github.unidbg.hook.hookzz.*;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.linux.android.dvm.api.Signature;
import com.github.unidbg.linux.android.dvm.array.ByteArray;
import com.github.unidbg.linux.android.dvm.wrapper.DvmInteger;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.utils.Inspector;
import com.sun.jna.Pointer;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class blackbox extends AbstractJni {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    blackbox() throws FileNotFoundException {
        emulator = AndroidEmulatorBuilder.for32Bit().setProcessName("com.blackbox").build(); // 创建模拟器实例，要模拟32位或者64位，在这里区分
        final Memory memory = emulator.getMemory(); // 模拟器的内存操作接口
        memory.setLibraryResolver(new AndroidResolver(23)); // 设置系统类库解析

        vm = emulator.createDalvikVM(new File("unidbg-android\\src\\test\\java\\com\\so_learn\\lession9\\小黑盒.apk")); // 创建Android虚拟机
        vm.setVerbose(true); // 设置是否打印Jni调用细节
        DalvikModule dm = vm.loadLibrary(new File("unidbg-android\\src\\test\\java\\com\\so_learn\\lession9\\libnative-lib.so"), true);

        module = dm.getModule(); //

        vm.setJni(this);
        dm.callJNI_OnLoad(emulator);

        Debugger debugger = emulator.attach();
        debugger.addBreakPoint(module.base+0x3d08+1);
//
//        emulator.traceWrite(0xbffff638L, 0xbffff638L+7L);
//        emulator.traceWrite(0xbffff645L, 0xbffff645L+0x10L);
    }

    public String callEncode(){
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv()); // 第一个参数是env
        list.add(0); // 第二个参数，实例方法是jobject，静态方法是jclazz，直接填0，一般用不到。
        Object custom = null;
        DvmObject<?> context = vm.resolveClass("android/content/Context").newObject(custom);// context
        list.add(vm.addLocalObject(context));
        list.add(vm.addLocalObject(new StringObject(vm, "r0env")));
        list.add(vm.addLocalObject(new StringObject(vm, "1622343722")));
        Number number = module.callFunction(emulator, 0x3b41, list.toArray());
        String result = vm.getObject(number.intValue()).getValue().toString();
        return result;
    };

    public int call37A4(int num1, int num2){
        List<Object> list = new ArrayList<>(10);
        list.add(num1);
        list.add(num2);
        Number number = module.callFunction(emulator, 0x37A4 + 1, list.toArray());
        return number.intValue();
    };

    @Override
    public int callIntMethod(BaseVM vm, DvmObject<?> dvmObject, String signature, VarArg varArg) {
        switch (signature) {
            case "android/content/pm/Signature->hashCode()I":
                if (dvmObject instanceof Signature) {
                    Signature sig = (Signature) dvmObject;
                    return sig.getHashCode();
                }
        }
        return super.callIntMethod(vm, dvmObject, signature, varArg);
    }

    public void hook37a0(){
        // 获取HookZz对象
        IHookZz hookZz = HookZz.getInstance(emulator); // 加载HookZz，支持inline hook，文档看https://github.com/jmpews/HookZz
        // enable hook
        hookZz.enable_arm_arm64_b_branch(); // 测试enable_arm_arm64_b_branch，可有可无
        // hook MDStringOld
        hookZz.wrap(module.base + 0x37a0 + 1, new WrapCallback<HookZzArm32RegisterContext>() { // inline wrap导出函数

            @Override
            // 方法执行前
            public void preCall(Emulator<?> emulator, HookZzArm32RegisterContext ctx, HookEntryInfo info) {
                System.out.println("input:"+ctx.getR0Int());
            };

            @Override
            // 方法执行后
            public void postCall(Emulator<?> emulator, HookZzArm32RegisterContext ctx, HookEntryInfo info) {
                System.out.println("output:"+ctx.getR0Int());
            }
        });
        hookZz.disable_arm_arm64_b_branch();

    }

    public static void main(String[] args) throws FileNotFoundException {
//        Logger.getLogger("com.github.unidbg.linux.ARM32SyscallHandler").setLevel(Level.DEBUG);
//        Logger.getLogger("com.github.unidbg.unix.UnixSyscallHandler").setLevel(Level.DEBUG);
        blackbox test = new blackbox();
        test.hook37a0();
        System.out.println(test.callEncode());

//        for(int i = 0; i<10000; i += 1){
//            final double d = Math.random();
//            final int temp = (int)(d*1000000);
//            if(test.call37A4(temp, 26)!=utils.sub_37A4(temp, 26)){
//                System.out.println(temp);
//            };
//        }

    }


}
