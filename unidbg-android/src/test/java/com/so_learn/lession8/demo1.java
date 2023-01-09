package com.so_learn.lession8;

import com.github.unidbg.Emulator;
import com.github.unidbg.file.FileResult;
import com.github.unidbg.file.IOResolver;
import com.github.unidbg.linux.android.dvm.AbstractJni;
import com.github.unidbg.Module;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.file.ByteArrayFileIO;
import com.github.unidbg.memory.Memory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.*;

public class demo1 extends AbstractJni implements IOResolver {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    demo1(){
        // 防止进程名检测
        emulator = AndroidEmulatorBuilder.for32Bit().setProcessName("com.readSp").build(); // 创建模拟器实例，要模拟32位或者64位，在这里区分
        final Memory memory = emulator.getMemory(); // 模拟器的内存操作接口
        memory.setLibraryResolver(new AndroidResolver(23)); // 设置系统类库解析
        vm = emulator.createDalvikVM();

        emulator.getSyscallHandler().addIOResolver(this);

        DalvikModule dm = vm.loadLibrary(new File("unidbg-android\\src\\test\\java\\com\\so_learn\\lession8\\libnative-lib.so"), true);
        module = dm.getModule();

        vm.setJni(this);
        vm.setVerbose(true);
    }


    public String call(){
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv()); // 第一个参数是env
        list.add(0); // 第二个参数，实例方法是jobject，静态方法是jclazz，直接填0，一般用不到。
        Object custom = null;
        DvmObject<?> context = vm.resolveClass("android/content/Context").newObject(custom);// context
        list.add(vm.addLocalObject(context));
        Number number = module.callFunction(emulator, 0xAAC + 1, list.toArray());
        String result = vm.getObject(number.intValue()).getValue().toString();
        return result;
    }

    @Override
    public DvmObject<?> callObjectMethodV(BaseVM vm, DvmObject<?> dvmObject, String signature, VaList vaList) {
        switch (signature) {
            case "android/content/Context->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;":
                return vm.resolveClass("android/content/SharedPreferences").newObject(vaList);
            case "android/content/SharedPreferences->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;": {
                // 如果是one.xml
                if(((StringObject) dvmObject.getValue()).getValue().equals("one")){
                    // 如果键是name
                    if (vaList.getObjectArg(0).equals("name")) {
                        return new StringObject(vm, "lilac");
                    }
                }
            }
        }

        return super.callObjectMethodV(vm, dvmObject, signature, vaList);
    }

    public static void main(String[] args) throws FileNotFoundException {
        Logger.getLogger("com.github.unidbg.linux.ARM32SyscallHandler").setLevel(Level.DEBUG);
        demo1 test = new demo1();
        System.out.println(test.call());
    }

    @Override
    public FileResult resolve(Emulator emulator, String pathname, int oflags) {
        if ("/data/data/com.roysue.readsp/shared_prefs/two.xml".equals(pathname)) {
            return FileResult.success(new ByteArrayFileIO(oflags, pathname, "mytest".getBytes()));
        }
        return null;
    }
}