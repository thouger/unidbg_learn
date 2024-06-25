package utils;

import com.github.unidbg.*;
import com.github.unidbg.Module;
import com.github.unidbg.arm.HookStatus;
import com.github.unidbg.arm.backend.DynarmicFactory;
import com.github.unidbg.arm.backend.KvmFactory;
import com.github.unidbg.arm.context.Arm32RegisterContext;
import com.github.unidbg.arm.context.RegisterContext;
import com.github.unidbg.hook.ReplaceCallback;
import com.github.unidbg.hook.hookzz.HookEntryInfo;
import com.github.unidbg.hook.hookzz.HookZz;
import com.github.unidbg.hook.hookzz.IHookZz;
import com.github.unidbg.hook.hookzz.InstrumentCallback;
import com.github.unidbg.hook.whale.IWhale;
import com.github.unidbg.hook.whale.Whale;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.linux.android.dvm.jni.ProxyClassFactory;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.utils.Inspector;
import com.github.unidbg.virtualmodule.android.AndroidModule;
import com.sun.jna.Pointer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class hook extends AbstractJni {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;
    Module target_so;

    hook() {

        // 创建模拟器实例
        emulator = AndroidEmulatorBuilder.for64Bit()
                .setProcessName("com.spotify.lite")
                .addBackendFactory(new DynarmicFactory(true))
                .addBackendFactory(new KvmFactory(true))
                .build();

        // 模拟器的内存操作接口
        final Memory memory = emulator.getMemory();
        // 设置系统类库解析
        memory.setLibraryResolver(new AndroidResolver(23));
        // 创建Android虚拟机
        vm = emulator.createDalvikVM(new File("unidbg-android/src/test/resources/spotify/spotify-lite.apk"));
        vm.setDvmClassFactory(new ProxyClassFactory());

        vm.setVerbose(true);
        vm.setJni(this);
//        emulator.attach().addBreakPoint(0x40000000+0xa80);

        // 加载so到虚拟内存
//        DalvikModule dm = vm.loadLibrary("limited-jni-spotify", true);

        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/resources/spotify/liblimited-jni-spotify.so"), true);
        target_so = emulator.getMemory().findModule("liblimited-jni-spotify.so");

        // 加载好的 libhookinunidbg.so对应为一个模块
        module = dm.getModule();
        Module module = new AndroidModule(emulator, vm).register(memory);
        assert module != null;
//        new OpenSLES(emulator, vm).register(memory);

        // 执行JNIOnLoad（如果有的话）
        dm.callJNI_OnLoad(emulator);
//        emulator.attach().addBreakPoint(module.findSymbolByName("asio::error::make_error_code").getAddress());
    }

    public void test(){
//        IHookZz hookZz = HookZz.getInstance(emulator);
//        hookZz.instrument(module.base + 0x55A02C, new InstrumentCallback<RegisterContext>() {
//            @Override
//            public void dbiCall(Emulator<?> emulator, RegisterContext ctx, HookEntryInfo info) {
//                int length = ctx.getIntArg(1);
//                Pointer input = ctx.getPointerArg(0);
//                System.out.println("hookByIHookZz base64 入参arg0:"+input.getString(0));
//                Inspector.inspect(input.getByteArray(0, length), "hookByIHookZz base64 input打印二进制内容");
//
//                System.out.println(ctx.getIntArg(0));
//            }
//        });
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv()); // 第一个参数是env
//        list.add(0); // 第二个参数，实例方法是jobject，静态方法是jclass，直接填0，一般用不到。
        int address = (int) module.findSymbolByName("start").getAddress();
        Number result = module.callFunction(emulator,address);
//        Number result = module.callFunction(emulator,target_so.base+0xA2591C);

        System.out.println(result.intValue());
    }

    public void hook(){
        IHookZz hookZz = HookZz.getInstance(emulator);
        hookZz.instrument(module.base + 0x5D3564, new InstrumentCallback<RegisterContext>() {
            @Override
            public void dbiCall(Emulator<?> emulator, RegisterContext ctx, HookEntryInfo info) {
                int length = ctx.getIntArg(1);
                Pointer input = ctx.getPointerArg(0);
                System.out.println("hookByIHookZz base64 入参arg0:"+input.getString(0));
                Inspector.inspect(input.getByteArray(0, length), "hookByIHookZz base64 input打印二进制内容");

                System.out.println(ctx.getIntArg(0));
            }
        });
    }

    public void hookhookByHookZz4SingleLine(){
        long base64_encode_address = module.findSymbolByName("base64_encode").getAddress();
        System.out.println(base64_encode_address);
        IHookZz hookZz = HookZz.getInstance(emulator); // 加载HookZz，支持inline utils.hook，文档看https://github.com/jmpews/HookZz
        hookZz.instrument(base64_encode_address, new InstrumentCallback<Arm32RegisterContext>() {
            @Override
            public void dbiCall(Emulator<?> emulator, Arm32RegisterContext context, HookEntryInfo info) { // 通过base+offset inline wrap内部函数，在IDA看到为sub_xxx那些
                Pointer input = context.getPointerArg(0);
                int length = context.getIntArg(1);
                System.out.println("hookByIHookZz base64 入参arg0:"+input.getString(0));
                System.out.println("hookByIHookZz base64 入参arg1:"+length);
                //                打印输入内容
                Inspector.inspect(input.getByteArray(0, length), "hookByIHookZz base64 input打印二进制内容");
            }
        });
    }

//    public void HookByUnicorn(){
//        long start = module.base+0xb48;
//        long end = module.base+0xb48+0x17A;
//        emulator.getBackend().hook_add_new(new CodeHook() {
//            @Override
//            public void utils.hook(Backend backend, long address, int size, Object user) {
//                RegisterContext registerContext = emulator.getContext();
//                if(address == module.base + 0xb48){
//                    int r0 = registerContext.getIntByReg(ArmConst.UC_ARM_REG_R0);
//                    System.out.println("0x97C 处 r0:"+Integer.toHexString(r0));
//                }
//                if(address == module.base + 0xb48 + 2){
//                    int r2 = registerContext.getIntByReg(ArmConst.UC_ARM_REG_R2);
//                    System.out.println("0x97C +2 处 r2:"+Integer.toHexString(r2));
//                }
//                if(address == module.base + 0xb48 + 4){
//
//                    int r4 = registerContext.getIntByReg(ArmConst.UC_ARM_REG_R4);
//                    System.out.println("0x97C +4 处 r4:"+Integer.toHexString(r4));
//                }
//            }
//
//            @Override
//            public void onAttach(UnHook unHook) {
//
//            }
//
//            @Override
//            public void detach() {
//
//            }
//        }, start, end, null);
//    }
//    private void initCall(){
//        dvmClass.callStaticJniMethod(emulator,"native_init()V");
//    }

    public void destroy() throws IOException {
        emulator.close();
    }

    public static void main(String[] args) {
        hook demo = new hook();
        demo.test();
//        mydemo.HookByUnicorn();
//        mydemo.hookhookByHookZz4SingleLine();
//        mydemo.utils.hook();
    }
    public void hookLibc(){
        IWhale whale = Whale.getInstance(emulator);
        Symbol strlen = emulator.getMemory().findModule("libc.so").findSymbolByName("strlen");
        whale.inlineHookFunction(strlen, new ReplaceCallback() {
            @Override
            public HookStatus onCall(Emulator<?> emulator, long originFunction) {
                System.out.println(("hookLibc strlen=" + emulator.getContext().getPointerArg(0).getString(0)));
                return HookStatus.RET(emulator, originFunction);
            }
        });
    }
}
