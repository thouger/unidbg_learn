package tutorial.lession5;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Module;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.memory.Memory;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class qxs extends AbstractJni{
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    qxs() throws FileNotFoundException {
        emulator = AndroidEmulatorBuilder.for32Bit().setProcessName("com.qxs").build(); // 创建模拟器实例，要模拟32位或者64位，在这里区分
        final Memory memory = emulator.getMemory(); // 模拟器的内存操作接口
        memory.setLibraryResolver(new AndroidResolver(23)); // 设置系统类库解析

        vm = emulator.createDalvikVM(new File("unidbg-android/src/test/java/tutorial/lession5/轻小说.apk")); // 创建Android虚拟机
        vm.setVerbose(true); // 设置是否打印Jni调用细节
        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/java/tutorial/lession5/libsfdata.so"), false); // 加载libttEncrypt.so到unicorn虚拟内存，加载成功以后会默认调用init_array等函数
        module = dm.getModule(); //

//        String traceFile = "unidbg-android/src/test/java/tutorial/lession5/qxstrace.txt";
//        PrintStream traceStream = new PrintStream(new FileOutputStream(traceFile), true);
//        emulator.traceCode(module.base, module.base+module.size).setRedirect(traceStream);

        // 先把JNI Onload跑起来，里面做了大量的初始化工作
        vm.setJni(this);
        dm.callJNI_OnLoad(emulator);

    }

    public static void main(String[] args) throws Exception {
        qxs test = new qxs();
        System.out.println(test.getSFsecurity());
    }

    public String getSFsecurity(){
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv()); // 第一个参数是env
        list.add(0); // 第二个参数，实例方法是jobject，静态方法是jclazz，直接填0，一般用不到。
        Object custom = null;
        DvmObject<?> context = vm.resolveClass("android/content/Context").newObject(custom);// context
        list.add(vm.addLocalObject(context));
        list.add(vm.addLocalObject(new StringObject(vm, "F1517503-9779-32B7-9C78-F5EF501102BC")));

        Number number = module.callFunction(emulator, 0xA944 + 1, list.toArray());
        String result = vm.getObject(number.intValue()).getValue().toString();
        return result;
    }

}


