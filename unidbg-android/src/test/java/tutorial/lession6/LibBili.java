package tutorial.lession6;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Module;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.memory.Memory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class LibBili extends AbstractJni {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;

    LibBili() {
        emulator = AndroidEmulatorBuilder.for32Bit().setProcessName("com.bilibili.app").build(); // 创建模拟器实例
        final Memory memory = emulator.getMemory(); // 模拟器的内存操作接口
        memory.setLibraryResolver(new AndroidResolver(23)); // 设置系统类库解析

        vm = emulator.createDalvikVM(new File("unidbg-android/src/test/java/tutorial/lession6/bilibili.apk")); // 创建Android虚拟机
        vm.setJni(this);
        vm.setVerbose(true); // 设置是否打印Jni调用细节
        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/java/tutorial/lession6/libbili.so"), true);
        module = dm.getModule(); //
        dm.callJNI_OnLoad(emulator);
    }

    public void s() {
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv()); // 第一个参数是env
        list.add(0); // 第二个参数，实例方法是jobject，静态方法是jclazz，直接填0，一般用不到。

        TreeMap<String, String> keymap = new TreeMap<String, String>();
        keymap.put("ad_extra", "E1133C23F36571A3F1FDE6B325B17419AAD45287455E5292A19CF51300EAF0F2664C808E2C407FBD9E50BD48F8ED17334F4E2D3A07153630BF62F10DC5E53C42E32274C6076A5593C23EE6587F453F57B8457654CB3DCE90FAE943E2AF5FFAE78E574D02B8BBDFE640AE98B8F0247EC0970D2FD46D84B958E877628A8E90F7181CC16DD22A41AE9E1C2B9CB993F33B65E0B287312E8351ADC4A9515123966ACF8031FF4440EC4C472C78C8B0C6C8D5EA9AB9E579966AD4B9D23F65C40661A73958130E4D71F564B27C4533C14335EA64DD6E28C29CD92D5A8037DCD04C8CCEAEBECCE10EAAE0FAC91C788ECD424D8473CAA67D424450431467491B34A1450A781F341ABB8073C68DBCCC9863F829457C74DBD89C7A867C8B619EBB21F313D3021007D23D3776DA083A7E09CBA5A9875944C745BB691971BFE943BD468138BD727BF861869A68EA274719D66276BD2C3BB57867F45B11D6B1A778E7051B317967F8A5EAF132607242B12C9020328C80A1BBBF28E2E228C8C7CDACD1F6CC7500A08BA24C4B9E4BC9B69E039216AA8B0566B0C50A07F65255CE38F92124CB91D1C1C39A3C5F7D50E57DCD25C6684A57E1F56489AE39BDBC5CFE13C540CA025C42A3F0F3DA9882F2A1D0B5B1B36F020935FD64D58A47EF83213949130B956F12DB92B0546DADC1B605D9A3ED242C8D7EF02433A6C8E3C402C669447A7F151866E66383172A8A846CE49ACE61AD00C1E42223");
        keymap.put("appkey", "1d8b6e7d45233436");
        keymap.put("autoplay_card", "11");
        keymap.put("banner_hash", "10687342131252771522");
        keymap.put("build", "6180500");
        keymap.put("c_locale", "zh_CN");
        keymap.put("channel", "shenma117");
        keymap.put("column", "2");
        keymap.put("device_name", "MIX2S");
        keymap.put("device_type", "0");
        keymap.put("flush", "6");
        keymap.put("ts", "1612693177");


        DvmClass Map = vm.resolveClass("java/util/Map");
        DvmClass AbstractMap = vm.resolveClass("java/util/AbstractMap", Map);
        DvmObject<?> input_map = vm.resolveClass("java/util/TreeMap", AbstractMap).newObject(keymap);
        list.add(vm.addLocalObject(input_map));
        Number number = module.callFunction(emulator, 0x1c97, list.toArray());
        DvmObject result = vm.getObject(number.intValue());
        System.out.println(result.toString());
    }

    @Override
    public boolean callBooleanMethod(BaseVM vm, DvmObject<?> dvmObject, String signature, VarArg varArg) {
        if ("java/util/Map->isEmpty()Z".equals(signature)) {
            TreeMap<String, String> treeMap = (TreeMap<String, String>) dvmObject.getValue();
            return treeMap.isEmpty();
        }
        return super.callBooleanMethod(vm, dvmObject, signature, varArg);
    }

    @Override
    public DvmObject<?> callObjectMethod(BaseVM vm, DvmObject<?> dvmObject, String signature, VarArg varArg) {
        switch (signature) {
            case "java/util/Map->get(Ljava/lang/Object;)Ljava/lang/Object;":
                StringObject keyobject = varArg.getObjectArg(0);
                String key = keyobject.getValue();
                TreeMap<String, String> treeMap = (TreeMap<String, String>) dvmObject.getValue();
                String value = treeMap.get(key);
                return new StringObject(vm, value);
        }
        switch (signature) {
            case "java/util/Map->get(Ljava/lang/Object;)Ljava/lang/Object;":
                StringObject keyobject = varArg.getObjectArg(0);
                String key = keyobject.getValue();
                TreeMap<String, String> treeMap = (TreeMap<String, String>) dvmObject.getValue();
                String value = treeMap.get(key);
                return new StringObject(vm, value);
        }
        return super.callObjectMethod(vm, dvmObject, signature, varArg);
    }

    @Override
    public DvmObject<?> callStaticObjectMethod(BaseVM vm, DvmClass dvmClass, String signature, VarArg varArg) {
        switch (signature) {
            case "com/bilibili/nativelibrary/SignedQuery->r(Ljava/util/Map;)Ljava/lang/String;": {
                DvmObject<?> mapObject = varArg.getObjectArg(0);
                TreeMap<String, String> mymap = (TreeMap<String, String>) mapObject.getValue();
                String result = utils.r(mymap);
                return new StringObject(vm, result);
            }
        }
        return super.callStaticObjectMethod(vm, dvmClass, signature, varArg);
    }

    @Override
    public DvmObject<?> newObject(BaseVM vm, DvmClass dvmClass, String signature, VarArg varArg) {
        switch (signature) {
            case "com/bilibili/nativelibrary/SignedQuery-><init>(Ljava/lang/String;Ljava/lang/String;)V":
                StringObject stringObject1 = varArg.getObjectArg(0);
                StringObject stringObject2 = varArg.getObjectArg(1);
                String str1 = stringObject1.getValue();
                String str2 = stringObject2.getValue();
                return vm.resolveClass("com/bilibili/nativelibrary/SignedQuery").newObject(new SignedQuery(str1, str2));
        }

        return super.newObject(vm, dvmClass, signature, varArg);
    }

    public class SignedQuery {
        public final String a;
        public final String b;

        public SignedQuery(String str, String str2) {
            this.a = str;
            this.b = str2;
        }
    }

    public static void main(String[] args) {
        LibBili test = new LibBili();
        test.s();
    }
}


