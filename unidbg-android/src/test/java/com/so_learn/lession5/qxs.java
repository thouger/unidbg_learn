package com.so_learn.lession5;

import capstone.Capstone;
import capstone.api.Instruction;
import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Emulator;
import com.github.unidbg.Module;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.listener.TraceCodeListener;
import com.github.unidbg.memory.Memory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Arrays;



public class qxs extends AbstractJni{
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;
    public TraceCodeListener listener;

    qxs() throws FileNotFoundException {
        emulator = AndroidEmulatorBuilder.for32Bit().setProcessName("com.qxs").build(); // 创建模拟器实例，要模拟32位或者64位，在这里区分
        final Memory memory = emulator.getMemory(); // 模拟器的内存操作接口
        memory.setLibraryResolver(new AndroidResolver(23)); // 设置系统类库解析

        vm = emulator.createDalvikVM(new File("unidbg-android\\src\\test\\java\\com\\so_learn\\lession5\\轻小说.apk")); // 创建Android虚拟机
        vm.setVerbose(true); // 设置是否打印Jni调用细节
        DalvikModule dm = vm.loadLibrary(new File("unidbg-android\\src\\test\\java\\com\\so_learn\\lession5\\libsfdata.so"), false); // 加载libttEncrypt.so到unicorn虚拟内存，加载成功以后会默认调用init_array等函数
        module = dm.getModule();

        // 保存的path
        String traceFile = "unidbg-android\\src\\test\\java\\com\\so_learn\\lession5\\qxstrace.txt";
        PrintStream traceStream = new PrintStream(new FileOutputStream(traceFile), true);
        emulator.traceCode(module.base, module.base+module.size).setRedirect(traceStream);

//        emulator.traceCode(module.base, module.base + module.size);
        // 先把JNI Onload跑起来，里面做了大量的初始化工作
        vm.setJni(this);
        dm.callJNI_OnLoad(emulator);

//        final Debugger debugger = emulator.attach();

        listener = new TraceCodeListener() {
            @Override
            public void onInstruction(Emulator<?> emulator, long address, Instruction insn) {

            }

            @Override
            public void onInstruction(Emulator<?> emulator, long address, Capstone.CsInsn insn) {
                // things can do here
                // 1.stop on target address
                // 2.dynamic Findcrypt based on reg value
                // 3.record BLX generate liucheng pic
                // 4.print changed value
                // eg.2
                // way 1,print changed reg value
                if(address==module.base + 0xb300){
                    System.out.println("here");
                }
                int result1 = insn.mnemonic.indexOf("add");
                if(result1 != -1){
                    // if write reg and number == 0x67452301 ,print it !
                    if (insn.regsAccess().regsRead.length > 0){
                        for (int i = 0; i < insn.regsAccess().regsRead.length; i++) {
                            int num = emulator.getBackend().reg_read(insn.regsAccess().regsRead[i]).intValue();
                            String[] md5MagicNumber = {"67452301", "efcdab89"};
                            boolean isContains = Arrays.asList(md5MagicNumber).contains(Integer.toHexString(num));
                            if(isContains){
//                                debugger.addBreakPoint(address);
                            };
                        }
                    }
                }

            }
        };
    }

    public static void main(String[] args) throws Exception {
        qxs test = new qxs();
//        test.hook_Maybe_MD5();
        System.out.println(test.getSFsecurity());
    }

    public String getSFsecurity(){
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv()); // 第一个参数是env
        list.add(0); // 第二个参数，实例方法是jobject，静态方法是jclazz，直接填0，一般用不到。
        Object custom = null;
        DvmObject<?> context = vm.resolveClass("android/content/Context").newObject(custom);// context
        list.add(vm.addLocalObject(context));
        list.add(vm.addLocalObject(new StringObject(vm, "r0ysue")));

        Number number = module.callFunction(emulator, 0xA944 + 1, list.toArray());
        String result = vm.getObject(number.intValue()).getValue().toString();
        return result;
    }

    public void hook_Maybe_MD5(){
        emulator.traceCode(module.base, module.base+module.size, listener);
    };

    @Override
    public DvmObject<?> callStaticObjectMethodV(BaseVM vm, DvmClass dvmClass, String signature, VaList vaList) {
        switch (signature) {
            case "java/util/UUID->randomUUID()Ljava/util/UUID;":{
                return dvmClass.newObject(UUID.randomUUID());
            }
        }
        return super.callStaticObjectMethodV(vm, dvmClass, signature, vaList);
    };

    @Override
    public DvmObject<?> callObjectMethodV(BaseVM vm, DvmObject<?> dvmObject, String signature, VaList vaList) {
        switch (signature) {
            case "java/util/UUID->toString()Ljava/lang/String;":{
                String uuid = dvmObject.getValue().toString();
                return new StringObject(vm, uuid);
            }
        }
        return super.callObjectMethodV(vm, dvmObject, signature, vaList);
    }
}
