package com.so_learn.课时2;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Emulator;
import com.github.unidbg.Module;
import com.github.unidbg.arm.backend.Backend;
import com.github.unidbg.arm.backend.CodeHook;
import com.github.unidbg.arm.backend.UnHook;
import com.github.unidbg.arm.context.Arm32RegisterContext;
import com.github.unidbg.arm.context.RegisterContext;
import com.github.unidbg.debugger.Debugger;
import com.github.unidbg.hook.hookzz.*;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.utils.Inspector;
import com.sun.jna.Pointer;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class encrypt extends AbstractJni {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    private Pointer buffer;

    encrypt() {
        // 创建模拟器实例,进程名建议依照实际进程名填写，可以规避针对进程名的校验
        emulator = AndroidEmulatorBuilder.for32Bit().setProcessName("com.sina.oasis").build();
        // 获取模拟器的内存操作接口
        final Memory memory = emulator.getMemory();
        // 设置系统类库解析
        memory.setLibraryResolver(new AndroidResolver(23));
        // 创建Android虚拟机,传入APK，Unidbg可以替我们做部分签名校验的工作
        vm = emulator.createDalvikVM(new File("unidbg-android/src/test/java/com/so_learn/sougou/sougou.apk"));
        // 加载目标SO
        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/java/com/so_learn/sougou/libSCoreTools.so"), true); // 加载so到虚拟内存
        //获取本SO模块的句柄,后续需要用它
        module = dm.getModule();
        vm.setJni(this); // 设置JNI
        vm.setVerbose(true); // 打印日志

        emulator.traceWrite(module.base+0x3a0c0,module.base+0x3a0c0);
    }

    public void HookByConsoleDebugger(){
        Debugger debugger = emulator.attach();
        debugger.addBreakPoint(module.base+0xb300);
    }

    public static void main(String[] args) {
        encrypt demo = new encrypt();
        // hookZz hook function
//        demo.hookEncryptWallEncode();
        // hookZz inline hook
//        demo.inlinehookEncryptWallEncode();


        demo.HookByConsoleDebugger();
        // hook by unicorn origin api
        demo.hookByUnicorn();
        // 初始化函数
        demo.native_init();
        // 目标函数
        demo.encryptDemo();

    }

    public void native_init(){
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv()); // 第一个参数是env
        list.add(0); // 第二个参数，实例方法是jobject，静态方法是jclass，直接填0，一般用不到。
        DvmObject<?> context = vm.resolveClass("android/content/Context").newObject(null); // context
        list.add(vm.addLocalObject(context));
        module.callFunction(emulator, 0x9565, list.toArray());
    };


    public void encryptDemo(){
        List<Object> list = new ArrayList<>(10);
        // arg1 env
        list.add(vm.getJNIEnv());
        // arg2 jobject/jclass 一般用不到 可以直接填0
        list.add(0);
        String str = "http://app.weixin.sogou.com/api/searchapp";
        String str2 = "type=2&ie=utf8&page=1&query=%E5%A5%8B%E9%A3%9E%E5%AE%89%E5%85%A8&select_count=1&tsn=1&usip=";
        String str3 = "lilac";
        list.add(vm.addLocalObject(new StringObject(vm,str)));
        list.add(vm.addLocalObject(new StringObject(vm,str2)));
        list.add(vm.addLocalObject(new StringObject(vm,str3)));
        Number number = module.callFunction(emulator,0x9ca1,list.toArray());
        String result = vm.getObject(number.intValue()).getValue().toString();
        System.out.println(result);
    }

    // HookZz hook Sc_EncryptWallEncode
    public void hookEncryptWallEncode(){
        // 获取HookZz对象
        IHookZz hookZz = HookZz.getInstance(emulator); // 加载HookZz，支持inline hook，文档看https://github.com/jmpews/HookZz
        // enable hook
        hookZz.enable_arm_arm64_b_branch(); // 测试enable_arm_arm64_b_branch
        hookZz.wrap(module.base + 0xA284 + 1, new WrapCallback<HookZzArm32RegisterContext>() {
            Pointer buffer;
            @Override
            // 方法执行前
            public void preCall(Emulator<?> emulator, HookZzArm32RegisterContext ctx, HookEntryInfo info) {
                System.out.println("HookZz hook EncryptWallEncode");
                Pointer input1 = ctx.getPointerArg(0);
                Pointer input2 = ctx.getPointerArg(1);
                Pointer input3 = ctx.getPointerArg(2);
                // getString的参数i代表index,即input[i:]
                System.out.println("参数1："+input1.getString(0));
                System.out.println("参数2："+input2.getString(0));
                System.out.println("参数3："+input3.getString(0));

                buffer = ctx.getPointerArg(3);
            };

            @Override
            // 方法执行后
            public void postCall(Emulator<?> emulator, HookZzArm32RegisterContext ctx, HookEntryInfo info) {
                // getByteArray参数1是起始index，参数2是长度，我们不知道结果多长，就先设置0x100吧
                byte[] outputhex = buffer.getByteArray(0, 0x100);
                Inspector.inspect(outputhex, "EncryptWallEncode output");
            }
        });
        hookZz.disable_arm_arm64_b_branch();

    }

    public void inlinehookEncryptWallEncode(){
        IHookZz hookZz = HookZz.getInstance(emulator);
        hookZz.enable_arm_arm64_b_branch();

        hookZz.instrument(module.base + 0x9d24 + 1, new InstrumentCallback<Arm32RegisterContext>() {
            @Override
            public void dbiCall(Emulator<?> emulator, Arm32RegisterContext ctx, HookEntryInfo info) {
                System.out.println("HookZz inline hook EncryptWallEncode");
                Pointer input1 = ctx.getPointerArg(0);
                Pointer input2 = ctx.getPointerArg(1);
                Pointer input3 = ctx.getPointerArg(2);
                // getString的参数i代表index,即input[i:]
                System.out.println("参数1："+input1.getString(0));
                System.out.println("参数2："+input2.getString(0));
                System.out.println("参数3："+input3.getString(0));

                buffer = ctx.getPointerArg(3);
            }
        });

        hookZz.instrument(module.base + 0x9d28 + 1, new InstrumentCallback<Arm32RegisterContext>() {
            @Override
            public void dbiCall(Emulator<?> emulator, Arm32RegisterContext ctx, HookEntryInfo info) {
                Inspector.inspect(buffer.getByteArray(0,0x100), "inline hook EncryptWallEncode");
            }
        });
    }

    public void hookByUnicorn(){
        emulator.getBackend().hook_add_new(new CodeHook() {

            @Override
            public void onAttach(UnHook unHook) {

            }

            @Override
            public void detach() {

            }

            @Override
            public void hook(Backend backend, long address, int size, Object user) {
                if(address == (module.base+0x9d24)){
                    System.out.println("Hook By Unicorn");
                    RegisterContext ctx = emulator.getContext();
                    Pointer input1 = ctx.getPointerArg(0);
                    Pointer input2 = ctx.getPointerArg(1);
                    Pointer input3 = ctx.getPointerArg(2);
                    // getString的参数i代表index,即input[i:]
                    System.out.println("参数1："+input1.getString(0));
                    System.out.println("参数2："+input2.getString(0));
                    System.out.println("参数3："+input3.getString(0));

                    buffer = ctx.getPointerArg(3);
                }
                if(address == (module.base+0x9d28)){
                    Inspector.inspect(buffer.getByteArray(0,0x100), "Unicorn hook EncryptWallEncode");
                }


            }
        },module.base + 0x9d24, module.base + 0x9d28, null);
    }
}