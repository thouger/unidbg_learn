package com.so_learn.课时3;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Emulator;
import com.github.unidbg.Module;
import com.github.unidbg.arm.backend.Backend;
import com.github.unidbg.arm.backend.CodeHook;
import com.github.unidbg.arm.backend.UnHook;
import com.github.unidbg.arm.context.RegisterContext;
import com.github.unidbg.file.FileResult;
import com.github.unidbg.file.IOResolver;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.linux.android.dvm.api.AssetManager;
import com.github.unidbg.linux.android.dvm.array.ByteArray;
import com.github.unidbg.linux.file.SimpleFileIO;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.utils.Inspector;
import com.github.unidbg.virtualmodule.android.AndroidModule;
import com.sun.jna.Pointer;
import unicorn.ArmConst;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class nativeSignAnalyse extends AbstractJni implements IOResolver {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    nativeSignAnalyse() {
        emulator = AndroidEmulatorBuilder.for32Bit().build();
        final Memory memory = emulator.getMemory(); // 模拟器的内存操作接口
        memory.setLibraryResolver(new AndroidResolver(23)); // 设置系统类库解析
        vm = emulator.createDalvikVM(new File("unidbg-android/src/test/java/com/so_learn/课时3/meiriyouxian9942.apk")); // 创建Android虚拟机

        //   注册libandroid.so虚拟模块
        new AndroidModule(emulator, vm).register(memory);
        emulator.getSyscallHandler().addIOResolver(this);

        vm.setVerbose(true); // 设置是否打印Jni调用细节
        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/java/com/so_learn/课时3/libsign.so"), true);
        module = dm.getModule(); //
        vm.setJni(this);
        dm.callJNI_OnLoad(emulator);
    }

    private void callInit() {
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv()); // 第一个参数是env
        list.add(0); // 第二个参数，实例方法是jobject，静态方法是jclazz，直接填0，一般用
        DvmObject<?> context =
                vm.resolveClass("cn/missfresh/application/MissFreshApplication",
                        vm.resolveClass("android/content/Context")).newObject(null);// context
        list.add(vm.addLocalObject(context));
        list.add(vm.addLocalObject(new StringObject(vm, "01000002")));
        module.callFunction(emulator, 0x38bb4 + 1, list.toArray());
    }


    private String getSign() {
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv()); // 第一个参数是env
        list.add(0); // 第二个参数，实例方法是jobject，静态方法是jclazz，直接填0，一般用不到。
        DvmObject<?> context =
                vm.resolveClass("cn/missfresh/application/MissFreshApplication",
                        vm.resolveClass("android/content/Context")).newObject(null);// context
        list.add(vm.addLocalObject(context));
        list.add(null);
        list.add(0x17Ac4917cb5L);
        list.add(vm.addLocalObject(new
                ByteArray(vm, "version".getBytes(StandardCharsets.UTF_8))));
        Number number = module.callFunction(emulator, 0x38BF4 + 1, list.toArray());
        return vm.getObject(number.intValue()).getValue().toString();
    }

    @Override
    public FileResult resolve(Emulator emulator, String pathname, int oflags) {
        if (("/proc/self/maps").equals(pathname)) {
            return FileResult.success(new SimpleFileIO(oflags, new File("unidbg-android/src/test/java/com/so_learn/课时3/maps"), pathname));
        }
        return null;
    }

    @Override
    public DvmObject<?> callObjectMethodV(BaseVM vm, DvmObject<?> dvmObject, String
            signature, VaList vaList) {
        switch (signature) {
            case "android/content/Context->getAssets()Landroid/content/res/AssetManager;": {
                return new AssetManager(vm, signature);
            }
        }
        return super.callObjectMethodV(vm, dvmObject, signature, vaList);
    }

    public void ConsoleDebugger() {
//        emulator.attach().addBreakPoint(module.base+0x37f76);

        // trace 结果来源（第一层）
//         emulator.traceWrite(0x402e7000,0x402e7000+122);

        // breakPoint 2f9ee位置，118个字符来自这里 （第二层）
//        emulator.attach().addBreakPoint(module.base + 0x2f9ee);
//    // breakpoint 37f3c，疑似魔改base64 (第三层)
//        emulator.attach().addBreakPoint(module.base + 0x37f3c);
//    // 寻找 base64输入数据的来源（不包括时间戳）（第四层）
        emulator.traceWrite(0x402e4000, 0x402e4088);
//        emulator.traceWrite(0x402e4009, 0x402e4052, (emulator, address, size, value) -> {
//            emulator.getUnwinder().unwind();
//            return false;
//        });
        emulator.traceWrite(0x402a10f0, 0x402a10f0 + 0x4a);
//        emulator.attach().addBreakPoint(module.base + 0x364be);
//        emulator.attach().addBreakPoint(module.base + 0x3647c);

        // 继续寻找base64来源
//        emulator.attach().addBreakPoint(module.base + 0x2f638);
    }

    public void hook2f638yUnicorn() {
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void onAttach(UnHook unHook) {

            }
            @Override
            public void detach() {
            }

            @Override
            public void hook(Backend backend, long address, int size, Object
                    user) {
                if (address == (module.base + 0x2f638)) {
                    System.out.println("Hook By Unicorn");
                    RegisterContext ctx = emulator.getContext();
                    // 这儿用getXXXByReg才最准确，这里偷了个懒
                    Pointer r1 = ctx.getPointerArg(1);
                    int length = ctx.getIntArg(2);
                    System.out.println("source Address:0x" + Integer.toHexString(ctx.getPointerArg(1).toIntPeer()));
                    System.out.println("target Address:0x" + Integer.toHexString(ctx.getPointerArg(0).toIntPeer()));
                    Inspector.inspect(r1.getByteArray(0, length), "memcpy source");
                }
            }
        }, module.base + 0x2f638, module.base + 0x2f638, null);
    }


    public void hook363dcByUnicorn(){
        emulator.getBackend().hook_add_new(new CodeHook() {
            @Override
            public void onAttach(UnHook unHook) {

            }
            @Override
            public void detach() {
            }

            @Override
            public void hook(Backend backend, long address, int size, Object
                    user) {
                if(address == (module.base+0x3647c)){
                    RegisterContext ctx = emulator.getContext();
                    int r1 = ctx.getIntByReg(ArmConst.UC_ARM_REG_R1);
                    System.out.print("读取 B 表 index："+r1);
                }
                if(address == (module.base+0x364a4)){
                    RegisterContext ctx = emulator.getContext();
                    int r1 = ctx.getIntByReg(ArmConst.UC_ARM_REG_R1);
                    System.out.print("; 读取 C 表 index："+r1);
                }
                if(address == (module.base+0x364b8)){
                    RegisterContext ctx = emulator.getContext();
                    int r8 = ctx.getIntByReg(ArmConst.UC_ARM_REG_R8);
                    System.out.println("; 读取 A 表 index："+r8);
                }
            }
        },module.base+0x363dc, module.base+0x363dc+0x140, null);
    }

    public static void main(String[] args) {
        nativeSignAnalyse demo = new nativeSignAnalyse();
        demo.ConsoleDebugger();
        demo.hook2f638yUnicorn();
//        demo.hook363dcByUnicorn();
        demo.callInit();
        System.out.println(demo.getSign());
        ;
    }
}