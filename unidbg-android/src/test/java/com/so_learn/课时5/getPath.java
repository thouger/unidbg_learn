package com.so_learn.课时5;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Emulator;
import com.github.unidbg.Module;
import com.github.unidbg.Symbol;
import com.github.unidbg.arm.context.EditableArm32RegisterContext;
import com.github.unidbg.debugger.BreakPointCallback;
import com.github.unidbg.hook.hookzz.HookEntryInfo;
import com.github.unidbg.hook.hookzz.HookZz;
import com.github.unidbg.hook.hookzz.IHookZz;
import com.github.unidbg.hook.hookzz.WrapCallback;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.AbstractJni;
import com.github.unidbg.linux.android.dvm.DalvikModule;
import com.github.unidbg.linux.android.dvm.VM;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.memory.MemoryBlock;
import com.github.unidbg.pointer.UnidbgPointer;
import unicorn.ArmConst;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class getPath extends AbstractJni {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;


    getPath(){
        // 创建模拟器实例，要模拟32位或者64位，在这里区分
        emulator = AndroidEmulatorBuilder.for32Bit().build();
        // 模拟器的内存操作接口
        final Memory memory = emulator.getMemory();
        // 设置系统类库解析
        memory.setLibraryResolver(new AndroidResolver(23));
        // 创建Android虚拟机
        vm = emulator.createDalvikVM();
        // 设置是否打印相关调用细节
        vm.setVerbose(true);

        EnvHook envHook = new EnvHook(emulator);
        memory.addHookListener(envHook);

        // 加载so到虚拟内存，加载成功以后会默认调用init_array等函数
        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/java/com/so_learn/课时5/libenvget.so"), true);
        module = dm.getModule();
        // 设置JNI
        vm.setJni(this);
        System.out.println("call JNIOnLoad");
        dm.callJNI_OnLoad(emulator);
    }

    public void setEnv(){
        Symbol setenv = module.findSymbolByName("setenv", true);
        setenv.call(emulator, "PATH", "2", 0);
    };


    public static void main(String[] args) {
        getPath demo = new getPath();
//        demo.setEnv();
//        demo.hookgetEnvByHookZz();
//        demo.hookgetEnvByBreakPointer();
        demo.call();
    }

    public void hookgetEnvByBreakPointer(){
        emulator.attach().addBreakPoint(module.base + 0x7FE, new BreakPointCallback() {
            @Override
            public boolean onHit(Emulator<?> emulator, long address) {
                EditableArm32RegisterContext registerContext = emulator.getContext();
                registerContext.getPointerArg(0).setString(0, "4");
                emulator.getBackend().reg_write(ArmConst.UC_ARM_REG_PC, (address)+5);
                return true;
            }
        });
    }


    public void hookgetEnvByHookZz(){
        IHookZz hookZz = HookZz.getInstance(emulator);

        hookZz.wrap(module.findSymbolByName("getenv"), new WrapCallback<EditableArm32RegisterContext>() {
            String name;
            @Override
            public void preCall(Emulator<?> emulator, EditableArm32RegisterContext ctx, HookEntryInfo info) {
                name = ctx.getPointerArg(0).getString(0);
            }
            @Override
            public void postCall(Emulator<?> emulator, EditableArm32RegisterContext ctx, HookEntryInfo info) {
                switch (name){
                    case "PATH":{
                        MemoryBlock replaceBlock = emulator.getMemory().malloc(0x100, true);
                        UnidbgPointer replacePtr = replaceBlock.getPointer();
                        String pathValue = "3";
                        replacePtr.write(0, pathValue.getBytes(StandardCharsets.UTF_8), 0, pathValue.length());
                        ctx.setR0(replacePtr.toIntPeer());
                    }
                }

            }
        });
    };

    public void call() {
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv());
        list.add(0);
        Number number = module.callFunction(emulator, 0x7f1, list.toArray());
        System.out.println("result:"+vm.getObject(number.intValue()).getValue().toString());
    }
}