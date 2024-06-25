package tutorial;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Emulator;
import com.github.unidbg.Module;
import com.github.unidbg.arm.context.Arm32RegisterContext;
import com.github.unidbg.arm.context.RegisterContext;
import com.github.unidbg.hook.hookzz.*;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.utils.Inspector;
import com.sun.jna.Pointer;

import java.io.File;

public class hookInUnidbg extends AbstractJni {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    hookInUnidbg() {

        // 创建模拟器实例
        emulator = AndroidEmulatorBuilder.for32Bit().build();

        // 模拟器的内存操作接口
        final Memory memory = emulator.getMemory();
        // 设置系统类库解析
        memory.setLibraryResolver(new AndroidResolver(23));
        // 创建Android虚拟机
        vm = emulator.createDalvikVM(new File("unidbg-android/src/test/resources/tutorial/hookinunidbg.apk"));

//        emulator.attach().addBreakPoint(0x40000000+0xa80);

        // 加载so到虚拟内存
        DalvikModule dm = vm.loadLibrary("hookinunidbg", true);
        // 加载好的 libhookinunidbg.so对应为一个模块
        module = dm.getModule();
        // 执行JNIOnLoad（如果有的话）
        dm.callJNI_OnLoad(emulator);
        vm.setVerbose(true);
        vm.setJni(this);

//        emulator.attach().addBreakPoint(module.findSymbolByName("base64_encode").getAddress());
    }

    public void hook(){
        IHookZz hookZz = HookZz.getInstance(emulator);
        hookZz.instrument(module.base + 0xb48 + 1, new InstrumentCallback<RegisterContext>() {
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

    public void call(){
        DvmClass dvmClass = vm.resolveClass("com/example/hookinunidbg/MainActivity");
        String methodSign = "call()V";
        DvmObject<?> dvmObject = dvmClass.newObject(null);

        dvmObject.callJniMethodObject(emulator, methodSign);

    }

    public static void main(String[] args) {
        hookInUnidbg mydemo = new hookInUnidbg();
//        mydemo.HookByUnicorn();
//        mydemo.hookhookByHookZz4SingleLine();
        mydemo.hook();
        mydemo.call();
    }
}