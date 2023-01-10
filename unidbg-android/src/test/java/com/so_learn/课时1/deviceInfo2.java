package com.so_learn.课时1;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Module;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.linux.android.dvm.array.ArrayObject;
import com.github.unidbg.linux.android.dvm.array.ByteArray;
import com.github.unidbg.memory.Memory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.zip.GZIPOutputStream;

public class deviceInfo2 extends AbstractJni {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    deviceInfo2() {
        emulator = AndroidEmulatorBuilder.for32Bit().setRootDir(new
                File("target/rootfs")).build(); // 创建模拟器实例，要模拟32位或者64位，在这里区分
        final Memory memory = emulator.getMemory(); // 模拟器的内存操作接口
        memory.setLibraryResolver(new AndroidResolver(23)); // 设置系统类库解析
        vm = emulator.createDalvikVM(new File("unidbg-android/src/test/java/com/so_learn/pdd/base.apk"));
        vm.setVerbose(true); // 设置是否打印Jni调用细节
        vm.setJni(this);

        DalvikModule dm_shared = vm.loadLibrary(new File("unidbg-android/src/test/java/com/so_learn/pdd/libc++_shared.so"), true);
        dm_shared.callJNI_OnLoad(emulator);
        DalvikModule dm_libUserEnv = vm.loadLibrary(new File("unidbg-android/src/test/java/com/so_learn/pdd/libUserEnv.so"), true);
        dm_libUserEnv.callJNI_OnLoad(emulator);
        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/java/com/so_learn/pdd/libpdd_secure.so"), true);
        dm.callJNI_OnLoad(emulator);

        module = dm.getModule();
    }

    public void getInfo2() {
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv());
        list.add(0);
        Object custom = null;
        DvmObject<?> context =
                vm.resolveClass("android/content/Context").newObject(custom);// context
        list.add(vm.addLocalObject(context));
//通过地址调用时，true填非0
        list.add(0x17AD420321AL);
        Number number = module.callFunction(emulator, 0xe3d5, list.toArray());
        String result = vm.getObject(number.intValue()).getValue().toString();
        System.out.println("result:" + result);
    }

    @Override
    public void callStaticVoidMethodV(BaseVM vm, DvmClass dvmClass, String
            signature, VaList vaList) {
        switch (signature) {
            case "com/tencent/mars/xlog/PLog->i(Ljava/lang/String;Ljava/lang/String;)V": {
                return;
            }
        }
        super.callStaticVoidMethodV(vm, dvmClass, signature, vaList);
    }

    @Override
    public DvmObject<?> getStaticObjectField(BaseVM vm, DvmClass dvmClass, String signature) {
        System.out.println("getStaticObjectField " + dvmClass + " " + signature);
        switch (signature) {
            case "android/provider/Settings$Secure->ANDROID_ID:Ljava/lang/String;":
                return new StringObject(vm, "");
        }
        return super.getStaticObjectField(vm, dvmClass, signature);
    }

    @Override
    public DvmObject<?> callObjectMethodV(BaseVM vm, DvmObject<?> dvmObject, String
            signature, VaList vaList) {
        switch (signature) {
            case "android/app/ActivityThread->getApplication()Landroid/app/Application;": {
                return vm.resolveClass("android/app/Application",
                        vm.resolveClass("android/content/ContextWrapper",
                                vm.resolveClass("android/content/Context"))).newObject(signature);
            }

            case "android/content/Context->getContentResolver()Landroid/content/ContentResolver;": {
                return
                        vm.resolveClass("android/content/ContentResolver").newObject(signature);
            }
            case "java/util/UUID->toString()Ljava/lang/String;": {
                return new StringObject(vm, dvmObject.getValue().toString());
            }
            case "java/lang/String->replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;": {
                StringObject str = (StringObject) dvmObject;
                StringObject s1 = vaList.getObjectArg(0);
                StringObject s2 = vaList.getObjectArg(1);
                assert s1 != null;
                assert s2 != null;
                return new StringObject(vm, str.getValue().replaceAll(s1.getValue(),
                        s2.getValue()));
            }
        }
        return super.callObjectMethodV(vm, dvmObject, signature, vaList);
    }

    @Override
    public DvmObject<?> callStaticObjectMethodV(BaseVM vm, DvmClass dvmClass, String signature, VaList vaList) {
        switch (signature) {
            case "android/provider/Settings$Secure->getString(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;": {
                return new StringObject(vm, "");
            }
            case "java/util/UUID->randomUUID()Ljava/util/UUID;": {
                return
                        vm.resolveClass("java/util/UUID").newObject(UUID.randomUUID());
            }
        }
        return super.callStaticObjectMethodV(vm, dvmClass, signature, vaList);
    }

    public DvmObject<?> callStaticObjectMethod(BaseVM vm, DvmClass dvmClass, String signature, VarArg varArg) {
        switch (signature) {
            case "android/provider/Settings$Secure->getString(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;": {
                return new StringObject(vm, "");
            }
        }
        return super.callStaticObjectMethod(vm, dvmClass, signature, varArg);
    }

    @Override
    public boolean callStaticBooleanMethod(BaseVM vm, DvmClass dvmClass, String signature, VarArg varArg) {
        switch (signature) {
            case "android/os/Debug->isDebuggerConnected()Z": {
                return false;
            }
        }
        return super.callStaticBooleanMethod(vm, dvmClass, signature, varArg);
    }

    @Override
    public DvmObject<?> newObject(BaseVM vm, DvmClass dvmClass, String signature,
                                  VarArg varArg) {
        switch (signature) {
            case "java/lang/Throwable-><init>()V": {
                return vm.resolveClass("java/lang/Throwable").newObject(new
                        Throwable());
            }
            case "java/io/ByteArrayOutputStream-><init>()V":{
                return vm.resolveClass("java/io/ByteArrayOutputStream").newObject(new
                                ByteArrayOutputStream());
            }
            case "java/util/zip/GZIPOutputStream-><init>(Ljava/io/OutputStream;)V":{
                OutputStream outputStream = (OutputStream)
                        varArg.getObjectArg(0).getValue();
                try {
                    return dvmClass.newObject(new GZIPOutputStream(outputStream));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return super.newObject(vm, dvmClass, signature, varArg);
    }

    @Override
    public void callVoidMethod(BaseVM vm, DvmObject<?> dvmObject, String signature, VarArg varArg) {
        switch (signature){
            case "java/util/zip/GZIPOutputStream->write([B)V":{
                GZIPOutputStream outputStream = (GZIPOutputStream) dvmObject.getValue();
                byte[] bytes = (byte[]) varArg.getObjectArg(0).getValue();
                try {
                    outputStream.write(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public int callIntMethod(BaseVM vm, DvmObject<?> dvmObject, String signature, VarArg varArg) {
        switch (signature) {
            case "android/content/Context->checkSelfPermission(Ljava/lang/String;)I": {
                return -1;
            }
            case "android/telephony/TelephonyManager->getSimState()I": {
                return 1;
            }
            case "android/telephony/TelephonyManager->getNetworkType()I": {
                return 13;
            }
            case "android/telephony/TelephonyManager->getDataState()I": {
                return 0;
            }
            case "android/telephony/TelephonyManager->getDataActivity()I": {
                return 4;
            }
        }
        return super.callIntMethod(vm, dvmObject, signature, varArg);
    }

    @Override
    public DvmObject<?> callObjectMethod(BaseVM vm, DvmObject<?> dvmObject, String signature, VarArg varArg) {
        switch (signature) {
            case "android/content/Context->getSystemService(Ljava/lang/String;)Ljava/lang/Object;": {
                String arg = varArg.getObjectArg(0).getValue().toString();
                System.out.println("getSystemService arg:" + arg);
                return vm.resolveClass("android/telephony/TelephonyManager").newObject(signature);
            }
            case "android/telephony/TelephonyManager->getSimOperatorName()Ljava/lang/String;": {
                return new StringObject(vm, "中国移动");
            }
            case "android/telephony/TelephonyManager->getSimCountryIso()Ljava/lang/String;": {
                return new StringObject(vm, "CN");
            }
            case "android/telephony/TelephonyManager->getNetworkOperator()Ljava/lang/String;": {
                return new StringObject(vm, "46001");
            }
            case "android/telephony/TelephonyManager->getNetworkOperatorName()Ljava/lang/String;": {
                return new StringObject(vm, "中国移动");
            }
            case "android/telephony/TelephonyManager->getNetworkCountryIso()Ljava/lang/String;": {
                return new StringObject(vm, "CN");
            }
            case "android/content/Context->getContentResolver()Landroid/content/ContentResolver;": {
                return vm.resolveClass("android/content/ContentResolver").newObject(signature);
            }
            case "java/lang/Throwable->getStackTrace()[Ljava/lang/StackTraceElement;": {
                StackTraceElement[] elements = {
                        new
                                StackTraceElement("com.xunmeng.pinduoduo.secure.DeviceNative", "", "", 0),
                        new
                                StackTraceElement("com.xunmeng.pinduoduo.secure.SecureNative", "", "", 0),
                        new StackTraceElement("com.xunmeng.pinduoduo.secure.s", "", "", 0),
                        new StackTraceElement("com.aimi.android.common.http.a", "", "", 0),
                        new StackTraceElement("com.aimi.android.common.http.j", "", "", 0),
                        new
                                StackTraceElement("com.aimi.android.common.http.unity.internal.interceptor.k", ""
                                , "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new
                                StackTraceElement("com.aimi.android.common.http.unity.internal.interceptor.PQuicInterceptor", "", "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new
                                StackTraceElement("com.aimi.android.common.http.unity.internal.interceptor.g", ""
                                , "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new
                                StackTraceElement("com.xunmeng.pinduoduo.arch.config.i$c", "", "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new
                                StackTraceElement("com.xunmeng.pinduoduo.basekit.http.manager.b$4", "", "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new
                                StackTraceElement("com.aimi.android.common.http.unity.internal.interceptor.o", ""
                                , "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new
                                StackTraceElement("com.aimi.android.common.http.unity.internal.interceptor.e", ""
                                , "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new
                                StackTraceElement("com.aimi.android.common.http.unity.internal.interceptor.b", ""
                                , "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new
                                StackTraceElement("com.aimi.android.common.http.unity.internal.interceptor.a", ""
                                , "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new
                                StackTraceElement("com.aimi.android.common.http.unity.internal.interceptor.m", ""
                                , "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new
                                StackTraceElement("com.aimi.android.common.http.unity.internal.interceptor.c", ""
                                , "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new
                                StackTraceElement("com.aimi.android.common.http.unity.internal.interceptor.j", ""
                                , "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new StackTraceElement("okhttp3.internal.b.g", "", "", 0),
                        new StackTraceElement("okhttp3.RealCall", "", "", 0),
                        new
                                StackTraceElement("com.aimi.android.common.http.unity.UnityCallFactory$a", "", "",
                                0),
                        new
                                StackTraceElement("com.xunmeng.pinduoduo.arch.quickcall.a.b.a", "", "", 0),
                        new
                                StackTraceElement("com.xunmeng.pinduoduo.arch.quickcall.a.a.a", "", "", 0),
                        new
                                StackTraceElement("com.xunmeng.pinduoduo.arch.quickcall.a.b.b", "", "", 0),
                        new
                                StackTraceElement("com.xunmeng.pinduoduo.arch.quickcall.a.a.a", "", "", 0),
                        new StackTraceElement("1", "", "", 0),
                        new
                                StackTraceElement("com.xunmeng.pinduoduo.arch.quickcall.g", "", "", 0),
                        new
                                StackTraceElement("com.xunmeng.pinduoduo.arch.quickcall.g$a", "", "", 0),
                        new
                                StackTraceElement("com.xunmeng.pinduoduo.arch.quickcall.a.b", "", "", 0),
                        new
                                StackTraceElement("java.util.concurrent.ThreadPoolExecutor", "", "", 0),
                        new
                                StackTraceElement("java.util.concurrent.ThreadPoolExecutor$Worker", "", "", 0),
                        new StackTraceElement("java.lang.Thread", "", "", 0),
                };
                DvmObject[] objs = new DvmObject[elements.length];
                for (int i = 0; i < elements.length; i++) {
                    objs[i] =
                            vm.resolveClass("java/lang/StackTraceElement").newObject(elements[i]);
                }
                return new ArrayObject(objs);
            }
            case "java/lang/StackTraceElement->getClassName()Ljava/lang/String;": {
                StackTraceElement element = (StackTraceElement) dvmObject.getValue();
                return new StringObject(vm, element.getClassName());
            }
            case "java/io/ByteArrayOutputStream->toByteArray()[B":
            {
                ByteArrayOutputStream byteArrayOutputStream = (ByteArrayOutputStream)
                        dvmObject.getValue();
                byte[] result = byteArrayOutputStream.toByteArray();
                return new ByteArray(vm, result);
            }
        }
        return super.callObjectMethod(vm, dvmObject, signature, varArg);
    }

    public static void main(String[] args) {
        Logger.getLogger(DalvikVM.class).setLevel(Level.DEBUG);
        Logger.getLogger(BaseVM.class).setLevel(Level.DEBUG);
        deviceInfo2 test = new deviceInfo2();
        test.getInfo2();
    }
}