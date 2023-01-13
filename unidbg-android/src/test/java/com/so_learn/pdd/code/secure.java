package com.so_learn.pdd.code;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Emulator;
import com.github.unidbg.Module;
import com.github.unidbg.file.FileResult;
import com.github.unidbg.file.IOResolver;
import com.github.unidbg.file.linux.AndroidFileIO;
import com.github.unidbg.linux.android.*;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.linux.android.dvm.Enumeration;
import com.github.unidbg.linux.android.dvm.api.ServiceManager;
import com.github.unidbg.linux.android.dvm.array.ArrayObject;
import com.github.unidbg.linux.android.dvm.array.ByteArray;
import com.github.unidbg.linux.file.SimpleFileIO;
import com.github.unidbg.memory.Memory;
import com.github.unidbg.memory.SvcMemory;
import com.github.unidbg.unix.UnixSyscallHandler;
import java.io.File;
import java.util.*;

public class secure extends AbstractJni implements IOResolver {
    private final AndroidEmulator emulator;
    private final VM vm;
    private final Module module;


    secure()  {
        AndroidEmulatorBuilder builder = new AndroidEmulatorBuilder(false) {
            public AndroidEmulator build() {
                return new AndroidARMEmulator(processName, rootDir,
                        backendFactories) {
                    @Override
                    protected UnixSyscallHandler<AndroidFileIO>
                    createSyscallHandler(SvcMemory svcMemory) {
                        return new MyARM32SyscallHandler(svcMemory);
                    }
                };
            }
        };


        emulator = builder.setRootDir(new File("target/rootfs")).build();

        final Memory memory = emulator.getMemory(); // 模拟器的内存操作接口
        memory.setLibraryResolver(new AndroidResolver(23)); // 设置系统类库解析


        vm = emulator.createDalvikVM(new File("unidbg-android/src/test/resources/PDD_8/拼多多官方.apk")); // 创建Android虚拟机

        SystemPropertyHook systemPropertyHook = new SystemPropertyHook(emulator);
        systemPropertyHook.setPropertyProvider(new SystemPropertyProvider() {
            @Override
            public String getProperty(String key) {
                System.out.println("fuckkey:"+key);
                switch (key){
                    case "ro.build.version.sdk": {
                        return "23";
                    }

                    case "ro.serialno":{
                        return "010a29a00c61ae27";
                    }

                    case "ro.build.id":{
                        return "OPM7.181205.001";
                    }

                    case "ro.build.fingerprint":{
                        return "google/bullhead/bullhead:8.1.0/OPM7.181205.001/5080180:user/release-keys";
                    }

                    case "ro.build.characteristics":{
                        return "nosdcard";
                    }

                    case "sys.oem_unlock_allowed":{
                        return "0";
                    }
                }
                return "";
            };
        });
        memory.addHookListener(systemPropertyHook);
        emulator.getSyscallHandler().addIOResolver(this);
        emulator.getSyscallHandler().setVerbose(true);
        vm.setVerbose(true); // 设置是否打印Jni调用细节
        vm.setJni(this);


        new UserEnvModule(emulator).register(memory);
        DalvikModule dm_libUserEnv = vm.loadLibrary(new File("unidbg-android/src/test/java/com/so_learn/pdd/files/PDD_8/libUserEnv.so"),true);
        dm_libUserEnv.callJNI_OnLoad(emulator);
        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/java/com/so_learn/pdd/files/PDD_8/libpdd_secure.so"), true);

        module = dm.getModule();
        dm.callJNI_OnLoad(emulator);
    }

    public static void main(String[] args) {
        secure demo = new secure();
//        demo.nativeGenerate();
        System.out.printf("call nativeGenerate2");
        demo.nativeGenerate2();
    }

    @Override
    public void callStaticVoidMethodV(BaseVM vm, DvmClass dvmClass, String signature, VaList vaList) {
        switch (signature){
            case "com/tencent/mars/xlog/PLog->i(Ljava/lang/String;Ljava/lang/String;)V":{
                return;
            }
        }
        super.callStaticVoidMethodV(vm, dvmClass, signature, vaList);
    }

    public void nativeGenerate2() {
        List<Object> list = new ArrayList<>(10);
        list.add(vm.getJNIEnv());
        list.add(0);
        DvmObject<?> context = vm.resolveClass("android/content/Context").newObject(null);
        list.add(vm.addLocalObject(context));
        String str1 = "";
        StringObject stringObject1 = new StringObject(vm, str1);
        list.add(vm.addLocalObject(stringObject1));
        String str2 = "ChUTbmEXjiUpjF5Q8W40Ag==";
        StringObject stringObject2 = new StringObject(vm, str2);
        list.add(vm.addLocalObject(stringObject2));
        String str3 = "Iqy8ijyU";
        StringObject stringObject3 = new StringObject(vm, str3);
        list.add(vm.addLocalObject(stringObject3));
        String str4 = "/storage/emulated/0";
        StringObject stringObject4 = new StringObject(vm, str4);
        list.add(vm.addLocalObject(stringObject4));
        String str5 = "version=134&info=g6iUSuzNlWeDi%2FxPng%2FN%2B8ZyQEP%2FnQuHC42hkmSWvCOg79IqfkRW5Lu3jsAh0QwizbgZZSg1FOEI%0Ao4R%2F6pw6XXsv%2FxH%2FzUDXzxJ5UXUYGMSYhF%2BULFIhbWMihyiUWSRA%2FamuTFPOOd17oppNLL6QvlSp%0A9rC2BHcgOMfMaYgq0uuiVDJB4cbPFUim1a9Vfz4tw82kh%2B%2Bbih06TdARfapqaqHrpoVtGP%2FC8yQY%0AuAHzn7rCorHuZDP8tyvStvBqpdDxO92eeEt%2BprLDqsM1HfA%2BX3ItGURbaT4%2BQSMCOA2dU2JVAv8V%0A4%2Fs%2BAghe%2BdfHFWF6Uy1GuRr%2BSZyh6WI0OLTdc10lh0N0t1cykJ99zGYnmLCR4cIgXWA0oVm4z6xL%0AhsQ0lbtVSZXGsKbRgW7DLZnOZc0V%2BQis2Sjg0JmZwwZxn%2BIk71YgQlcZpPP%2F6pCuAvvVIjFIIs2y%0AmW8glVdWM%2FLUGdIRo%2BCYdHScnKmAHeZKRr%2B0WvDvlEaEEOqETrDrRboRW%2FMSwTmvCAlFG2v4m1xg%0AkAFUPHuNsgWuf9%2BPeRFVTNIkhEJdU6I%2FDR0B4qKzN55vYJargGIXRlqh2aC8cd5twjAdRZvQ%2BDbe%0AG1BtdhdT3riGXHrtiX7ghYPXpQjdD1e%2BctDUhGnuYgUkD8R%2FE5FwovFPLfMPp3YLla0hib9fyEhZ%0AcxpmwCaeMJFCsasneB%2FySCV%2FG2dLM3kHLtg8FHD45O78iALmXjM8eJX3xMETPg450Pl%2F8PPty6vo%0Af%2Fr10muJZj%2FV1haU%2FlOkfFPPtN4eTy16j5Xh2hztPxCzslumebkJZvg7UkaDZOBk9lB8CKhN0ukK%0AQHEHUiUwhJ2gpgOIFGZq6aEBC2aoQSDANgycUKmp1oSl8%2BFjvhACEcbvE%2BZkPccSFwqOiiJT7iBt%0APGaSp5E7zr%2BeNbzkYGxs53Yr4juNZyF8NTFsaq1fZLTNAgoFXyCGjVEL5jOsCsAz6H4oMYjMSXqo%0A00%2FcX476o9GVR4IgnfnEX0dQJCxhWIp49Y55FvF9fCnzBwJTLtuRP5JDX4jEbGs904pkEYdbCa8t%0ASktMg9PlVbVqvC%2BCi%2F7B%2BvlHeIF58%2BHfuVDWSs9jh8beXJ6o9jtuRdWQWW7mXQVgA5oGW5uOo1fU%0AgF%2Bv1LDXRK9s%2B%2F8I4TTb5P7VVaSWZqm95jrUs0lNZzTxc9Qcak%2BuFnPjnSfgPRc4AVlf8tjinv%2FX%0AeUGOv%2BhgaUgno6OIXM6fFSQ%2BwJ7QXJmLolSmxgCYwBKxF7wIc9Icht66wHUk3Z08DEGbvioiQaAI%0ABfM15W3bQYVR3oq775TnYwPr5TQ7ipiPWh2hnwkQDxZNxjz6oIJFgv%2FgI0fUU8GFS9kknL0umMyu%0ANcFYsCgnTfKzZ%2BbiEX4qBzgoBmjYreJXRPhjon1ef8g73OXGxZaqyjNgeYAjiFbss05iS0jic%2Fta%0Ad%2B2DMR5%2FdsqFWGmdk9QWhRR%2FHqeg9E%2BKWmiYIpkQnAki7BSjgBgAY7kdvJCTXbbkISbKdZrLrF%2Bt%0A3KnZdf7EaUUxZt7NIXhoCbWXS7u9eEtvE5eVCy%2BsTnHbE9RsvZqIb9NuXePm5jNHbYB4wIWno2FX%0ALnOZ%2FkbXUH4i8B0n%2FW6sKPywzq49MH5KWEpNoH%2BtXV5iONwmsuYg5WvDAo4EArt1Ih582HlhMdrd%0AFblckB%2FPBUm0oeHiML1P4WSVPeF1oBDyYCE4W9OKIoFmqOduObuokCoeX7mUqUtiB40Q65pw%2FZ8E%0ATbqqMbojGh2cE9rzdfnHky8nRc%2B6GuItbvdVvYCGR3WcKJvpYABfVTLaihnKOJLLtBY9vwgtx7LW%0AJZphjFyhtFyciQVg9nAiSR%2FJ%2FawUL70hnhLlfy6avml1LEF48BhXUVDsIILUwUvfOFVtjX3hd%2FL6%0AKB0Y%2Buc8KID8eTWw5vmzIQD59g%2FL8OQjMwqpEMD%2B5apiKh36eJq%2FscyNJSno5QgDBW1JmQXJCxn%2F%0A4YTbswPBfxa446g%2BObii8vP%2B0pHNyfcuo1zZw0xL%2BbQO20HSXBiLRwlh0oWkKDoe9fPvAtHAb%2FLs%0Ad%2FaWz%2BfdkvpAe8CyRUNsmC0Fz6lFVrsoa8wKRuUgj07%2BoM0MkPFoIIPqQ%2BFKp0XeyqRFvt%2Bcx9GM%0AKbF6whk7kYZOJ4L0DPnSeX9wPGvB6LN1A3CENwIaRwegI4JnSGjxUeoRMrrtTdgxZ%2F53I0%2BYxeg1%0AsBnc71YGG89q7p4FXjWQkzs2Y2FztRQtc7MAa%2F6DNih0z9fb9pCqZo1G5ZH3aUQn49Y9g06EusWZ%0AHGGC2rzZKb44ah2sxXY5JsvjnV5I3k8YL9eWPUZq2bcr6LECtSq6r%2B7jJoPF0C03GyNT5zk9wGel%0A7UiDbQX8LZPbvAiJHXgrgOssUaX2CuB8TFnZf5FDtnycVpqGdbet5a728bl9G5n%2Fi%2B%2BIMVxhH5WL%0ARZWF%2FvLlH%2BJ06Viw%2B0NZbnmf8X3WJNN9bESHO46%2Btv0NEx1fyf29%2FaW7Z4TJd%2BO6WmGpPE%2FOXzAu%0A4mgE2idSMHp60ibCMic5ZJq2Mr8MtSuUqJ7mfADZ8A7x2cxQda%2BGFEgqezuokeUZ1JsWydbWSEJM%0AUsDnY6Qh0ZMNegFtf70hfB0PrcJXGDSYdAnuPMnTzVnXLJVnSL81eukYTpdn6qudDb1seLpBUd6T%0AXSC3VuBgRURnVFcc9Rp4vbWF3hJHil8G6uRqGdLPPjIJgqualWLDGJ4xaefMf7QMyoUVZJBaKuPY%0Als62wBquusynM6Tfkpf3v6VQVY%2BCZFmjRuShtgGZ5F9T2hg4ngONgZSja6bbPhxdbxIXfLhLOvDa%0AOOhtcezYVyEwyCmEaDu4QgWudeCWQZPEjnGaG6nNeZHrCvRtXbEJGbmfbxmAjBmeNKrEjYOkFVtv%0AhGUt3dFMOj2oiFXwz79sESN2N54jq8vcegj%2B7xJYSYZSUDsU3bRWiIDtCfNoGqyyf%2FJN0t0n%2BR%2Fp%0AQka9ufhBQOGXrB%2FRBLISkdyE3Kd2QhLzNX%2FXFzvO%2FOYLDi0XFNfWtq7KHxG2Vra7cdHbDipDVdv8%0A2hOq3sAWpWEY7xiPUxucxUup7i1fItdhcy4937CnI%2B%2BLICaxgiWMyFwNFRpoRGKgQs6zg5q07mi0%0AMSazNgBJJajX2%2Bv%2FWpjRHzBR%2FP77JdbhSmuMqxy5nC5eBDPKYvOxxzP46d0v8TD8evW4Gyxk%2BHRk%0AanvvCpOpwmV0JerceSEGTixEXTX5ixM50ns1mjF3b5uPNdGq2KPb2I50dkFuLHEMC0iOcASasqwx%0AHnA1Ka%2BBjU8ss98%2BA0ds5Gbdj1825R4RbvT5BT4zsUj73WFgQgfnEUcFNeDBxjetMb8yNiqAQICn%0AWgu%2FhLQfLMIkAq%2FkEUckM66gzczUnV937fGbtUbXkLgtuOx4Z0XapHG5Ma7kqE2DhXcq0a%2BQvPk4%0A00fKJpL3eRWvZCw7ziz3wEMbN%2FqEpJXESb79GIzxYBc2k7L%2FkRwmRwnCpofwAHqYkcCyrYhgkSTd%0AShRMJMkJ3Qd4%2FpLIrOfFaxQwK1D3vurS8%2FLBVXvaVhaV58lEHjyOQ7bCyBk3iPlKzUUwq6v9NgWr%0AU1hE4snln%2FArBCBTQTvCbPSDuyEmJEE%2FrzXHHxt2oWRKBCc0m7rXcrmvOswIT19j9iYBURDNeMH8%0AHz8iB2E9D6hUsy5rHsPGraKEpfIQbUMj2twgQRaia2%2B8wjK5mSO6%2BHa3PYhAgR6x0GqQTkk0IW0O%0A8iBkHaZm5xKp8CGo4Srk6VYO0UjxXmIRDZc9ATbI6Y3kQa4bn8Cg62Ywhyv%2BMe%2B%2BnpcoW1oQg927%0AhPyu6GpGfMwF6O9kk29l3Vd0QwngrrGLP%2FCWArQOYktuvT5YT2iDIieGhRy26MhJRp018Y%2BGIS%2BC%0ASnL96Sd37fJcuyeQp5l6CDyT2PeoXYmcRdLHc3KwAbvyvee2q1RmapVDl7VEnk3jHRTDvSWGJYck%0Akf2kK8HDPpvG2cyQwXgPRImgA%2Fynk4nZkad0Iwy2mr43dF768gZ0ST%2FRKNamI%2FSJQbHezROgSATC%0AMgec2ORsXLUr4pm4exEvn6J3%2BbFd7YRLFQyzA8nFnf6e2q8guZ221TG5DJ9OO92AwsUqto7bGLoK%0AdMZ21R2G5Qc2i2ia1GiHkirmWIzlHs3Zkg%2FliExdUgu4hOXJeb0DpYd3G%2BVo9sebvX7b1GSi8Z1z%0AxxeiErNblSCLaevND216kYOgpRyoj6UKeNwtudQrIAk%2B94q0fEIS4PRo6c%2Fde%2BoFJorH1SniUG03%0APahww41A0Oh3xs%2FbyHHxaJLnGj7mwdrWQ5OYTrc73aV7ZtKTrSprpiZ7vHUrA1k%2BOkRKM7dgi%2F5n%0A5w0kwmattWJeb8%2FHtcAwdB0%2B8wK0jwO7KF9HlZvZOe3%2BCvSgSuSA62oIy7XAyO3cXtT8sFW9lt4%2F%0AXeQAmwDpGK4TT2zoZabOOqbPL4wzyjYrHHGEZAr35Gc7a4iwDghgCHo6vk800OKSpOjiiYS7qFmv%0AbLuye0sAmTlVgixmJS52AZgH%2FRxLvetD2GZFnXnzt28qPic4FrEniL5o7PA6VXRYGGI%2BJm%2FZDD%2F3%0ACohOqb7AvE5uQUX%2Fp%2Fs5YvVlNREtwslINIf71Db1Y2SD26ZagKef2HWzwLcu3j3r2tZmqglGg5nY%0A9in%2FqzMbta%2FO%2BGxstKOIMQgELidei6A5aNg7wY9Mv2A%2BlPYtF8hHsOtWYiAfCtYq8cldH%2BMGVcr6%0AKd%2BrZkl48CNn4C%2FhvUK6a47Bcg7Q6LzE1zYK7G%2F7hWaBT48bykjGlKN6qccXXWvlziiwW54olIak%0AtHJ8%2BrzfKZvif51V%2F1DcXQOMNTAhsj85E%2F2bay8Y6a%2B2Zb2PNeO%2BQG5VfDgOXzKQtA2OwsKNSQfS%0A9%2Fw3aDRHsgzN%2FmwSY3pfk1a1kHorQrm5bb7Sv90QSTy0envdrcA32pMCJ41dsGuaFUKzoI1ZfcTx%0Ac335uRsdKo4hlaip%2Bq3jvffW2LKtGuRzcBBGf%2BQ8JAHbRKqEvRYgrraoNevkpzVydt01n9lO7Iaw%0AwEwR3IrpuoY36ESHM38lz9qF%2BMVnqjt1XjKVOgKX7NJc4qbSZVWNkQ9Re9C7dqbUuj%2BhaWaMt2qM%0A5V%2Bibtx01rR7LDV%2FCqRDGakSIDfWrPGqxcmRMwXbORUtfMllcxQC3IukYrNjjP5ZvYTYUIoHvA0N%0AVeWH4%2BDZyMSkMFHgRVPj88m4FRTphgQJ2D8p0fS%2FD1EDtG54IzC1u7xbghyZmJuRhJwWo7JNWwpU%0AQl91CgL0YPFuPsoq6Bz7qb4%2Bcd0uaJNcr%2FAuwgryj3FlmJchU7oFVXo6GP7lcsrW3tY%2FuTKdcIzP%0ApmuWGTzO9cWckoJ6Mr2pe6i%2FeKcQZ6hVvVX%2BdfOpInsFtZuBeZ5YADMXC0e1rVrI456yLeLSWlBD%0AzsDumKD%2B4oOQIgp%2FfkSbG9OUrT63CWoifn7bPzv0gOPWAE%2Fd6OsOehX2L7Y82Z7ArwgBvgNgghnU%0AVMqx155cG0UqLXoSOq%2BpAphAEMZu907O8rlA%2BlCGqEsK%2BFWc5WZNQamqk9xyObmGBZ86tKiZcYdh%0APoZvVArhia4eVP8LN33oqQHfAGTAGdz%2BZfyzcjHr%2FnNK5DTfmzIkPKEBNPTt0w9J2sGypz2zCWHy%0A0UvLwDIsVDK3FWRG4aYMIq3AmFBaRom2TS%2FfJrfv2bAZTsP7z%2FjHtHILMzz%2FyJrIIC6Zuh3%2B1B4e%0AGC2YImrwPy80ETUZzkbHi3v1VAVuurGSIyZV0m%2BhBABq4YfDS5TQ%2FX%2F%2FAeORu9gBZyCDumNUDPW5%0Ahse4FjtwSZU3rA04FisFJmLF5s20UI9qtnFR8r7HNGeYDgsldMiVA%2BkaHWAvT9DigROqvuh8li88%0AJjstRuyCocGPqyJrM9wgSYS6TKATubvZ63vclNT4d1RIno%2BzyH2MoQ0UuX%2FcN%2Fpmj62EN3jDK7Dz%0AJ8wEFeICtORkyFaGj378bZDeadChGfBFKo%2FO9Bmb4ZMzDxXqIOUQcUcq%2BkW9c4AQr6ced4D%2FwqLR%0A7Vgn9q%2BHRk81fwpmD7tfTzBYJM5uNCnsCuev76UAdx4BnO%2FuzsEHvGbFipRLtNts9ax0toHpPoot%0AE581y8BGn01eQJ6mLa9z3QRj%2FJI7%2FDxmVDZa%2B37RfPko08ZQKVk19bU%2Fw8hU%2Fw82LlymbqDceeRr%0AzktwtRrE2E6iCtgPkI7V3tk3V%2FXuzUjMrzCfSAgUEva74ehACyH3aBNHsLO82Sy7yb03V7e4UBi2%0APJgxYcWfy%2BWKdJTYkdbWORaytRDQRTMaAZ61V%2Bji1X1DoiE4B9jCD5nSl0i711F8gPl42zQC%2Bxz%2B%0AxpPziU%2BPwVoFixO9rZXfXWlWtaphgmTalSX7RU8CXUEJvPSTD2azgBIDWIhtcgAxDniiIaZWeFu%2B%0AS22w%2B8wUytkNh%2B9R6UStw2jrmpko20eUHKsUb%2BlVWvlDeszZQGLrLIVp7T37TFEUhBT3%2FymakY%2Fd%0A%2BDn3n2L3XGme1O80muYVlk8e6a0AJnUqEJKxIXorf9n1ARQBCceyrHYITo1JwjLOFNqboHMCrfoH%0AGPnFNZhh8tFclpnGjZvw4BahucSt0QMaTwbYPge%2FDxtUvOFzOBsKFRRuE0IgEJsOrXwMPqe2IfEE%0ADjmHy7K%2Fdt0YkA1lpi9%2BaSCtHObKQBNuFzKm3kfKNabcn9vz%2FunwyyGKWJkYrOfaMNk%2Bz%2FnPCXco%0A9lGpD6sld0F%2BNrvsfvn6QkewbnvDh%2BV8Op6ZczL3aanb5vJ2cq60FfPBHYEZqAoN2knGcepeu0Gu%0AGohzCN5cpchRO5WfB54WU%2F1YhAv%2BtPGapigHIzwkVmf0jzSli3T%2FvHjw7CZTjE1wEz3GH0N0QVLo%0AxoeOs4qYR2W2cUGSM1p%2Fw7OkX1fHjto7UqzrdMuSRYXSP%2BC8WM12wT%2Bv9chxCpfCdvmMSPPoDQoH%0ABrZzZ8%2FbWz%2BZouu3h6QYSH34HQXIry8h2EdRKaVIDJJByo0ToG7XrVf1gjgnNeVhJTy7rLWvs3QZ%0AC24I1D2qsc83E72IBZxLX4IDpRCmeiSs1l9G7dGnsDTKL1p%2ButpVSx83cdLT5atGiTOouqgC1x0u%0ASPlEXzueu2ouJpmfc%2BwrpTV0zrug74ksCEWSsFX3XMF3X10Wz67Had3b%2Bg8fsthJI7twORY2Cqje%0AWImP%2BQ0S5l6Ai1%2FBuVJ40eSkmtOxgDSw%2Fy13h5%2B5p5N9aSC9eSOyobuyZbN%2FN2hAhmAysnYEPbzK%0At3Hhee4Z70ft8w6m0qkkRHbngnaU0FAKC5mcQIcjS9hmiOw9jz6l%2FbHlN4NAJ%2F9xJ0XFUKj66iSa%0AfMvP7LNOCjTb4wT9I%2BJHDWVzex2UAW%2FjoDg8XZSa22DykMKFeLfW3KSdwtJ6JB6q1MYse%2B8IhXhh%0A9CQVrFQ%2Fm%2B6nd%2BeBxQt%2BvSc%2Bl3sTcVFfN7Wy20kgGq04Pow1rDG9msyes%2BdVSOrA2QtnP1JOGsRM%0A5vnN1XkuH9pSK57I7iDX1A%2B4Es%2FJe29nHizoVVgQ3UD0IPA5a0RNYS%2Fucaawi%2B%2Fp2vhzwyRN9rZb%0AYnbh3Q6d0xPEnvxnXRoLqI2gyTMq3X85vpMeF4WzYlHIMI9rSz2dOKJzdfhYwymV%2BMCxfNjYCLLv%0A95wsZygz5MiySUmknIxw11GAyP0ZdNWECsdEUNwx17JeFiM9IAn0BpKogLJA%2BZ46mM97FaisG6JJ%0APZzoDuBAO9uBVMIkBqGz%2Fb2I9Z%2BX1ARlypEUgMzylBeJsu%2BVTsEC0QhZ22mSd81vH1QgI4z71dK6%0AsGPfCTPqW6Qvnhm9T7BHSbxaG4LgvO7W%2BFdWcUjTxWaTddu6c%2BW2x9q5pEZ0oq0d3LfE8STgi5m%2B%0ARVsbxhMSQK%2ByYEBlBhB9m3HNxPOLDDgnnwksAArPZOD25Zjb%2ByCUObpB%2BhVN16uC3Gv1TiWVSv6M%0A8VsByD87fsyasqDp1cIJo052sWdvmpY5z8FsbcToyC%2FQOGKiynRzJrOmzT4NTgTW4sU0xHHFFldr%0A1iLPseDj83ABHLNHSaV0YYU%2Bh7c%2B%2FRdzTS92wFtNxvqYdO5HrV2SCi7KzdoQifQ9xpyYngBc%2B1ET%0A9l5VoILkcBnFrbz5jREDSFEtL5Yc8NOJvpVmn7ojwlw%2BgTpT%2FVZeVXT4e%2BeNgL6i4dIsuxQeplcY%0A5Dk36buB%2BwHCTueg1SYLRsRuDFkZ4jebViFHEGGGl8eV6JI3%2FXvfkyUznh2FyvhVQcMAuI%2FyI8CP%0A4Ok%2F01Vgrfk18v4DCMEpb5SBkeBidgvDglG7wRrD5I92GCG%2FTa0lCFOTXOZkYfM8w0R01lCdLYwo%0A1KIxLa2PZaXoZkxmZqUKADbQw7m8cN37jwFAKms7ZzX4b6uTzY8b6spASpTok4GkFZfh7VGTjOLt%0AimAdA7y%2BGqg%2BNDM4gU013G4tXOWvU6QhnWaU8BN8%2FXcNbj2xKXc1ttCoENs3ko8%2FMWBCulvffk6r%0ACvpqbgpMac%2FgyVBb6PGbwhuVs3fzYR4PypxXY5KR488npFxu8l9MEFlrCb2qv%2BtuyO5p%2BILfj392%0AxIaZnpS%2BZHw4bfIcOk1Q7b4DbcKznkdcwZfEs4WcHSfseg83MG%2BClrzQmUHOTRCslB3UYg1G%2FOSa%0AoE2xwQ0vFEBfKVmvmhWHvkYZnTZVlKIcSdIJway1MVEkv9DxOQW6vAAvmQ9B65mGOeSvF9kPt9th%0AvFPxwH735SIq1WPPTR0rL0ExPD8uyQoBPb0DNIsBHeqxf4t7pP0Dxo%2BQJ%2FneOnW%2F5TWsat8qgsFY%0AjNuwSEWseN1VHv48Hlju6cdxU5B8mjU0%2Fo7IBL6BpEON9p0xsV9Njw7O9EpmA74vGnqWoy1K0Vmk%0AVYDVBTw%2FTYuJPlvLxjv8yyt0XXDR9kLk8uVFkt45I32%2FXijNBWDvZaou9euuEgpTU0NkkvZMpD9V%0Arr0HGZOA0HXz6HiaXrmi2T3rMmco3EibB9NV13mzD%2BrKftcsBWFEUWXk6Zl5FDRyQCR%2Bkp0ztZt9%0A7PHd2SIoAd7HstEJwSdEFwZARKC55FEn1aT9FE%2FxbkVnOCGK4lIAB1kVTrAzzITzdXN%2BJyrEq7Pa%0ALvHcB8YyWHOrGFQI0isxLpp%2BEyAaABQanbO3zQ6xcZf8kiwMIDb3vY8tlRAIpDWpzENCmblVigAY%0AKBPfYf9PrcIfHteCM6MywOzWlsIy6Mop4bt3ealcOOQiuHsfkdgzQYr6BMkgfclbF86sVnP3n79o%0AobUmH9YRufGuONaahnQJaFxJgHXtgBnuzlVDRweAw0injGZGe7KKRbHHDbFOY6Qv%2FZ4UhGco9Gkh%0AVPETTZS%2B2ejU0s1IwqpQWl1N0vyTD7uVnPPlk0FOh%2FYyQoI7MNUgrjxMSFContSWKe8B3CLNznzh%0AYYUwhOSiE7Aher1UziLMjqoWOIWFDkrGMmXrrinXFcg7VuB%2FLV%2Fp%2BjjgVUo4ThJ7NY3ZGhzC1mun%0A%2FmxVWQGqjPvReElO1s0icAWOyV5jrudfpCIcOaE%2Fta0TL7FUJQ7vvwPPuxUHubrgJ3NtuKIq%2F%2F%2Bp%0ADWLFs1bviNtmZ9aFSsqKt2JavPkQBb5L%2BhI7%2F4HiJ%2F9%2B6vQ93E83l6%2BbJve1Buqugn4hOtA61R%2B0%0A0FXkrw0jtfA6pz69UCW8uuHWg0D%2B2U374ud1Ey1ixtSt2u%2BpPKazk0Wb9MdVsAG4ILqeuHOheU4E%0As7tc8S%2FZpfPqVlzl8GAIN510olDBNL1ZKZ94vb3fzfH%2B2azFaTRrKLqv8tjtjPLxhOwiXJTjuaPY%0AvN%2F705%2FvKM6fUV8gTnLkEmALZ9RwtEnqUX9h6DQceo5OHDwLg7Hmdk%2BBVfz4NRsxii9trMy6Of1W%0AK%2FGrkyTXg%2FUUoW6rOhQXfT5MO%2B%2FDWwaVpsMeMCWMmSkX0xyntb6ID5%2B80YyG7dBKBrc6%2FHTWHp%2Fj%0ASkZVxn8DbgjntXlVyYA%2BD5%2BlJA8cMcKQqZa5YwcOkAX85S4fT9EYM%2BPLY96TN6rjofrY2DMWjh%2Bk%0Ae7%2Bdz8u05kq9%2FlxwuSllI2GiO8gLD9mmy4BRIbX0vI9jGVXM%2BUO9m1yyTtAnbJLbDq5vQxdOKTyX%0A1g63yYkT8MrOTbNyXAyUeKnH%2F5V5kdJqhA98e%2Fbeao0cyhUlgOlrygMuIiD3Yy7cftr1jvsxoN8x%0AjX36avuVD0Z06noXM7VWNz5c%2BFu8nvY7QWXjmd42MV8MTM9ijAECkqqiLYH0nxC2LLn%2FFpksQus9%0Az6s5n2O%2BtZOwj4EC7u2deKMTh9VoXn9VgkILo8n8PRRm0XQ2OY9G5I59DP%2BFpz0QAklKPZLjJyQi%0AfX6pSVPUu3yX975GrJVlxRonYO78Fj1xgl2UXPqIMHF150B0TZ6HjaCDiJANjKE1pzGKMbTNWBjP%0A9NbJy2JMDwc6DwfgRUYNFjhKC5xJpyQBXqZpNaOxEzbC2X39WfvNBT3M759JKp6pML5y6HbpTddR%0AWIhqO6MVHFoFuwUqXghto8VQy5EfyO08s%2FzY0jsqqGJH4OiyM6TW6VgSFNeGpTus2qucrzERn%2BM%2F%0AneoF2KpgUqMQQX1GKTEKBIwF7opmT67X8FFHgez5j3QGtWtCONCiL68xq3YdexslYqwKQh3I6N5E%0ATfoM%2FIu0u%2FWNQAKV9k8sOoYGq7anUN1sSwaU1Sk4JklVutAJjvdyUFl6RP6BNEwVCMFva%2F8oJSl2%0AQPSh1uZqt83kLHW%2B9GWAgaJgqYx2p0n6eRwwW%2BNpVmP1FvW6RRbTRx2G7qsHuz6FQHcrdPB36H%2BZ%0Abx0HJvtwx1%2FDbAomMCikLBFXHML60Eluusd8MNJRIoqfdBUTB4SSWfasmSSDVasd5LedQQlQKSuQ%0Awc4e9fh4sLw2hA%3D%3D%0A";
        StringObject stringObject5 = new StringObject(vm, str5);
        list.add(vm.addLocalObject(stringObject5));

        list.add(0x44082347);
        list.add(0x17b);


        Number number = module.callFunction(emulator, 0x247d, list.toArray());
        String result = vm.getObject(number.intValue()).getValue().toString();
        System.out.println("result:"+result);
    }

    @Override
    public DvmObject<?> callObjectMethod(BaseVM vm, DvmObject<?> dvmObject, String signature, VarArg varArg) {
        switch (signature){
            case "android/content/Context->getSharedPreferences(Ljava/lang/String;I)Landroid/content/SharedPreferences;":{
                String spName = varArg.getObjectArg(0).getValue().toString();
                System.out.println("getSharedPreferences sp Name:"+spName);
                return vm.resolveClass("android/content/SharedPreferences").newObject(spName);
            }
            case "android/content/Context->getSystemService(Ljava/lang/String;)Ljava/lang/Object;": {
                ServiceManager serviceManager = new ServiceManager(vm, signature);
                StringObject serviceName = varArg.getObjectArg(0);
                assert serviceName != null;
                return serviceManager.getService(vm, serviceName.getValue());
//                String serviceName = varArg.getObjectArg(0).getValue().toString();
//                assert serviceName != null;
//                if(serviceName.equals("window")){
//                    return vm.resolveClass("android/view/WindowManager").newObject(signature);
//                }
//                return vm.resolveClass("android/telephony/TelephonyManager").newObject(signature);
            }
            case "android/content/SharedPreferences->getString(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;": {
                if((dvmObject.getValue().toString()).equals("pdd_config")){
                    System.out.println("pdd_config表中取值："+varArg.getObjectArg(0).getValue().toString());
                    if (varArg.getObjectArg(0).getValue().equals("secure_sharedpreference_id")) {
                        return new StringObject(vm, "5f41908145dc4f69b8cb6b001071c993");
                    }
                    if (varArg.getObjectArg(0).getValue().equals("secure_mac_address")) {
                        return new StringObject(vm, "F4:60:E2:96:DB:64");
                    }
                }
            }
            case "android/telephony/TelephonyManager->getDeviceId()Ljava/lang/String;": {
                return new StringObject(vm, "");
            }
            case "android/telephony/TelephonyManager->getSubscriberId()Ljava/lang/String;":
                return new StringObject(vm, "");

            case "android/view/WindowManager->getDefaultDisplay()Landroid/view/Display;":
                return vm.resolveClass("android/view/Display").newObject(signature);

            case "java/util/Enumeration->nextElement()Ljava/lang/Object;":
                return ((Enumeration) dvmObject).nextElement();

            case "android/content/SharedPreferences->edit()Landroid/content/SharedPreferences$Editor;":{
                return vm.resolveClass("android/content/SharedPreferences$Editor").newObject(signature);
            }

            case "android/content/SharedPreferences$Editor->putString(Ljava/lang/String;Ljava/lang/String;)Landroid/content/SharedPreferences$Editor;":{
                return vm.resolveClass("android/content/SharedPreferences$Editor").newObject(signature);
            }

            case "android/content/Context->getContentResolver()Landroid/content/ContentResolver;":
                return vm.resolveClass("android/content/ContentResolver").newObject(signature);

            case "android/content/Context->registerReceiver(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;":
                return vm.resolveClass("android/content/Intent").newObject(varArg.getObjectArg(1).getValue());

            case "com/android/internal/telephony/ITelephony->getInterfaceDescriptor()Ljava/lang/String;":
                return new StringObject(vm, "");


            case "android/content/SharedPreferences$Editor->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;":
                return dvmObject;

            case "java/lang/StringBuilder->append(Ljava/lang/String;)Ljava/lang/StringBuilder;":
                StringBuilder stringBuilder = (StringBuilder) dvmObject.getValue();
                stringBuilder.append(varArg.getObjectArg(0).getValue().toString());
                return vm.resolveClass("java/lang/StringBuilder").newObject(stringBuilder);

            case "java/lang/StringBuilder->toString()Ljava/lang/String;":{
                StringBuilder stringBuilder1 = (StringBuilder) dvmObject.getValue();
                return new StringObject(vm, stringBuilder1.toString());
            }
        }
        return super.callObjectMethod(vm, dvmObject, signature, varArg);
    }

    @Override
    public int callIntMethod(BaseVM vm, DvmObject<?> dvmObject, String signature, VarArg varArg) {
        switch (signature){
            case "android/content/Context->checkSelfPermission(Ljava/lang/String;)I":{
                String input = varArg.getObjectArg(0).getValue().toString();
                System.out.println("permission name:"+input);
                return 0;
            }
            case "android/telephony/TelephonyManager->getPhoneType()I":
                return 1;
            case "android/telephony/TelephonyManager->getSimState()I":
                return 5;
            case "android/telephony/TelephonyManager->getNetworkType()I":
                return 13;
            case "android/telephony/TelephonyManager->getDataState()I":
                return 2;
            case "android/telephony/TelephonyManager->getDataActivity()I":
                return 4;
        }
        return super.callIntMethod(vm, dvmObject, signature, varArg);
    }

    @Override
    public DvmObject<?> callStaticObjectMethodV(BaseVM vm, DvmClass dvmClass, String signature, VaList vaList) {
        switch (signature){
            case "java/util/UUID->randomUUID()Ljava/util/UUID;":{
                return vm.resolveClass("java/util/UUID").newObject(UUID.randomUUID());
            }
            case "java/net/NetworkInterface->getNetworkInterfaces()Ljava/util/Enumeration;":{
                DvmObject<?> dvmObject = vm.resolveClass("java/net/NetworkInterface").newObject("dummy0");
                DvmObject<?> dvmObject1 = vm.resolveClass("java/net/NetworkInterface").newObject("r_rmnet_data2");
                DvmObject<?> dvmObject2 = vm.resolveClass("java/net/NetworkInterface").newObject("r_rmnet_data3");
                DvmObject<?> dvmObject3 = vm.resolveClass("java/net/NetworkInterface").newObject("r_rmnet_data0");
                DvmObject<?> dvmObject4 = vm.resolveClass("java/net/NetworkInterface").newObject("ip_vti0");
                DvmObject<?> dvmObject5 = vm.resolveClass("java/net/NetworkInterface").newObject("lo");
                DvmObject<?> dvmObject6 = vm.resolveClass("java/net/NetworkInterface").newObject("r_rmnet_data1");
                DvmObject<?> dvmObject7 = vm.resolveClass("java/net/NetworkInterface").newObject("wlan1");
                DvmObject<?> dvmObject8 = vm.resolveClass("java/net/NetworkInterface").newObject("wlan0");
                DvmObject<?> dvmObject9 = vm.resolveClass("java/net/NetworkInterface").newObject("ip6tnl0");
                DvmObject<?> dvmObject10 = vm.resolveClass("java/net/NetworkInterface").newObject("rmnet_data10");
                DvmObject<?> dvmObject11 = vm.resolveClass("java/net/NetworkInterface").newObject("bond0");
                DvmObject<?> dvmObject12 = vm.resolveClass("java/net/NetworkInterface").newObject("ip6_vti0");
                DvmObject<?> dvmObject13 = vm.resolveClass("java/net/NetworkInterface").newObject("sit0");
                DvmObject<?> dvmObject14 = vm.resolveClass("java/net/NetworkInterface").newObject("rmnet_data8");



                List obj = new ArrayList<DvmObject>();

                obj.add(dvmObject);
                obj.add(dvmObject1);
                obj.add(dvmObject2);
                obj.add(dvmObject3);
                obj.add(dvmObject4);
                obj.add(dvmObject5);
                obj.add(dvmObject6);
                obj.add(dvmObject7);
                obj.add(dvmObject8);
                obj.add(dvmObject9);
                obj.add(dvmObject10);
                obj.add(dvmObject11);
                obj.add(dvmObject12);
                obj.add(dvmObject13);
                obj.add(dvmObject14);
                return new Enumeration(vm,  obj);
            }
            case "com/xunmeng/pinduoduo/service_hook/SystemServiceHooker->getService(Ljava/lang/String;)Landroid/os/IBinder;":{
                return vm.resolveClass("android/os/IBinder").newObject(signature);
            }
            case "android/os/Parcel->obtain()Landroid/os/Parcel;":{
                return dvmClass.newObject(signature);
            }
            case "android/provider/Settings$Secure->getString(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;":{
                return new StringObject(vm,"android_id");
            }
        }
        return super.callStaticObjectMethodV(vm, dvmClass, signature, vaList);
    }

    @Override
    public DvmObject<?> callObjectMethodV(BaseVM vm, DvmObject<?> dvmObject, String signature, VaList vaList) {
        switch (signature){
            case "java/util/UUID->toString()Ljava/lang/String;":{
                UUID uuid = (UUID) dvmObject.getValue();
                return new StringObject(vm, uuid.toString());
            }
            case "java/lang/String->replaceAll(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;": {
                StringObject str = (StringObject) dvmObject;
                StringObject s1 = vaList.getObjectArg(0);
                StringObject s2 = vaList.getObjectArg(1);
                assert s1 != null;
                assert s2 != null;
                return new StringObject(vm, str.getValue().replaceAll(s1.getValue(), s2.getValue()));
            }
            case "android/telephony/TelephonyManager->getDeviceId()Ljava/lang/String;": {
                // 获取deviceid
                return new StringObject(vm, "");
            }
            case "android/telephony/TelephonyManager->getDeviceId(I)Ljava/lang/String;": {
                // 获取deviceid
                return new StringObject(vm, "");
            }
            case "android/telephony/TelephonyManager->getSubscriberId(I)Ljava/lang/String;": {
                return new StringObject(vm, "");
            }
            case "android/telephony/TelephonyManager->getSimSerialNumber()Ljava/lang/String;":
                return new StringObject(vm, "");

            case "android/telephony/TelephonyManager->getSimSerialNumber(I)Ljava/lang/String;":
                return new StringObject(vm, "");

            case "java/net/NetworkInterface->getName()Ljava/lang/String;":{
                return new StringObject(vm, dvmObject.getValue().toString());
            }
            case "java/net/NetworkInterface->getHardwareAddress()[B":
                byte[] result = string2bytes("F460E296DB64");
                return new ByteArray(vm, result);

            case "android/content/Intent->getExtras()Landroid/os/Bundle;":{
                return vm.resolveClass("android/os/Bundle").newObject(signature);
            }
            case "android/os/Bundle->keySet()Ljava/util/Set;":
                String[] s = new String[]{"technology", "icon-small", "max_charging_voltage",
                        "health","max_charging_current", "status","plugged", "present", "seq",
                        "charge_counter", "level", "scale", "temperature", "voltage",
                        "invalid_charger","battery_low"};
                Set<String> set = new HashSet<>(Arrays.asList(s));
                return vm.resolveClass("java/util/Set").newObject(set);

            case "android/os/Bundle->get(Ljava/lang/String;)Ljava/lang/Object;":{
                String key = vaList.getObjectArg(0).getValue().toString();
                System.out.println("this key:"+key);
                switch (key){
                    case "technology":{
                        return vm.resolveClass("java/lang/Object").newObject("Li-poly");
                    }
                    case "icon-small":{
                        return vm.resolveClass("java/lang/Object").newObject("17303585");
                    }
                    case "max_charging_voltage":{
                        return vm.resolveClass("java/lang/Object").newObject("5000000");
                    }
                    case "health":{
                        return vm.resolveClass("java/lang/Object").newObject("2");
                    }
                    case "max_charging_current":{
                        return vm.resolveClass("java/lang/Object").newObject("1500000");
                    }
                    case "status":{
                        return vm.resolveClass("java/lang/Object").newObject("2");
                    }
                    case "plugged":{
                        return vm.resolveClass("java/lang/Object").newObject("1");
                    }
                    case "present":{
                        return vm.resolveClass("java/lang/Object").newObject("true");
                    }
                    case "seq":{
                        return vm.resolveClass("java/lang/Object").newObject("287");
                    }
                    case "charge_counter":{
                        return vm.resolveClass("java/lang/Object").newObject("1496324");
                    }
                    case "level":{
                        return vm.resolveClass("java/lang/Object").newObject("71");
                    }
                    case "scale":{
                        return vm.resolveClass("java/lang/Object").newObject("100");
                    }
                    case "temperature":{
                        return vm.resolveClass("java/lang/Object").newObject("390");
                    }
                    case "voltage":{
                        return vm.resolveClass("java/lang/Object").newObject("4193");
                    }
                    case "invalid_charger":{
                        return vm.resolveClass("java/lang/Object").newObject("0");
                    }
                    case "battery_low":{
                        return vm.resolveClass("java/lang/Object").newObject("false");
                    }

                }
                break;
            }
            case "java/lang/Object->toString()Ljava/lang/String;":{
                return new StringObject(vm, dvmObject.getValue().toString());
            }
            case "java/lang/Object->getClass()Ljava/lang/Class;":{
                switch (dvmObject.getValue().toString()){
                    case "com/android/internal/telephony/ITelephony$Stub->asInterface(Landroid/os/IBinder;)Lcom/android/internal/telephony/ITelephony;":{
                        return vm.resolveClass("com/android/internal/telephony/ITelephony");
                    }
                }
            }
            case "java/lang/Class->getEnclosingClass()Ljava/lang/Class;":{
                return vm.resolveClass("com/android/internal/telephony/IPhoneSubInfo$Stub");
            }
            case "android/os/Parcel->readString()Ljava/lang/String;":{
                return new StringObject(vm, "");
            }
            case "java/lang/String->trim()Ljava/lang/String;":{
                String str = dvmObject.getValue().toString();
                return new StringObject(vm, str.trim());
            }
            case "android/net/ConnectivityManager->getActiveNetworkInfo()Landroid/net/NetworkInfo;":{
                return vm.resolveClass("android/net/NetworkInfo").newObject(signature);
            }
            case "android/app/ActivityThread->getApplication()Landroid/app/Application;":{
                return vm.resolveClass("android/app/Application", vm.resolveClass("android/content/ContextWrapper", vm.resolveClass("android/content/Context"))).newObject(signature);
            }
            case "android/content/Context->getContentResolver()Landroid/content/ContentResolver;":{
                return vm.resolveClass("android/content/ContentResolver").newObject(signature);
            }
            case "java/util/Set->toArray()[Ljava/lang/Object;":{
                Set thisSet = (Set) dvmObject.getValue();
                StringObject[] obj = new StringObject[thisSet.size()];

                for(int i=0;i<thisSet.size();i++){
                    obj[i] = new StringObject(vm, (String) thisSet.toArray()[i]);
                }

                return new ArrayObject(obj);
            }
        }
        return super.callObjectMethodV(vm, dvmObject, signature, vaList);
    }

    private byte[] string2bytes(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    @Override
    public int getStaticIntField(BaseVM vm, DvmClass dvmClass, String signature) {
        switch (signature) {
            case "android/telephony/TelephonyManager->PHONE_TYPE_GSM:I":
                return 1;
            case "com/android/internal/telephony/IPhoneSubInfo$Stub->TRANSACTION_getDeviceId:I":
                return 142;
        }
        return super.getStaticIntField(vm, dvmClass, signature);
    }

    @Override
    public DvmObject<?> getStaticObjectField(BaseVM vm, DvmClass dvmClass, String signature) {
        switch (signature){
            case "android/content/Context->WINDOW_SERVICE:Ljava/lang/String;":{
                return new StringObject(vm, "window");
            }
            case "android/provider/Settings$Secure->ANDROID_ID:Ljava/lang/String;":{
                return new StringObject(vm, "android_id");
            }
            case "android/os/Build->SERIAL:Ljava/lang/String;":{
                return new StringObject(vm, "unknown");
            }
            // android.intent.action.BATTERY_CHANGED
            case "android/content/Intent->ACTION_BATTERY_CHANGED:Ljava/lang/String;":{
                return new StringObject(vm, "android.intent.action.BATTERY_CHANGED");
            }
        }
        return super.getStaticObjectField(vm, dvmClass, signature);
    }

    @Override
    public DvmObject<?> newObject(BaseVM vm, DvmClass dvmClass, String signature, VarArg varArg) {
        switch (signature){
            case "android/util/DisplayMetrics-><init>()V":{
                return dvmClass.newObject(signature);
            }
            case "android/content/IntentFilter-><init>(Ljava/lang/String;)V":{
                return dvmClass.newObject(varArg.getObjectArg(0).getValue().toString());
            }
            case "java/lang/StringBuilder-><init>()V":{
                return vm.resolveClass("java/lang/StringBuilder").newObject(new StringBuilder());
            }
        }
        return super.newObject(vm, dvmClass, signature, varArg);
    }

    @Override
    public void callVoidMethod(BaseVM vm, DvmObject<?> dvmObject, String signature, VarArg varArg) {
        switch (signature){
            case "android/view/Display->getRealMetrics(Landroid/util/DisplayMetrics;)V":{
                return;
            }
            case "android/content/SharedPreferences$Editor->apply()V":{
                return;
            }
        }
        super.callVoidMethod(vm, dvmObject, signature, varArg);
    }

    @Override
    public int getIntField(BaseVM vm, DvmObject<?> dvmObject, String signature) {
        switch (signature){
            case "android/util/DisplayMetrics->densityDpi:I":{
                return 440;
            }
            case "android/util/DisplayMetrics->widthPixels:I":{
                return 1080;
            }
            case "android/util/DisplayMetrics->heightPixels:I":{
                return 2160;
            }
        }
        return super.getIntField(vm, dvmObject, signature);
    }

    @Override
    public boolean callBooleanMethod(BaseVM vm, DvmObject<?> dvmObject, String signature, VarArg varArg) {
        switch (signature){
            case "java/util/Enumeration->hasMoreElements()Z":{
                return ((Enumeration) dvmObject).hasMoreElements();
            }
            case "android/os/IBinder->transact(ILandroid/os/Parcel;Landroid/os/Parcel;I)Z":{
                return true;
            }
        }
        return super.callBooleanMethod(vm, dvmObject, signature, varArg);
    }

    @Override
    public DvmObject<?> callStaticObjectMethod(BaseVM vm, DvmClass dvmClass, String signature, VarArg varArg) {
        switch (signature){
            case "android/provider/Settings$Secure->getString(Landroid/content/ContentResolver;Ljava/lang/String;)Ljava/lang/String;":{
                if(varArg.getObjectArg(1).getValue().equals("android_id")){
                    return new StringObject(vm, "63b489ab3358dd54");
                }
            }
            case "com/android/internal/telephony/ITelephony$Stub->asInterface(Landroid/os/IBinder;)Lcom/android/internal/telephony/ITelephony;":{
                return vm.resolveClass("com/android/internal/telephony/ITelephony", vm.resolveClass("java/lang/Object")).newObject(signature);
            }
        }
        return super.callStaticObjectMethod(vm, dvmClass, signature, varArg);
    }

    @Override
    public void callVoidMethodV(BaseVM vm, DvmObject<?> dvmObject, String signature, VaList vaList) {
        switch (signature){
            case "android/os/Parcel->writeInterfaceToken(Ljava/lang/String;)V":{
                return;
            }
            case "android/os/Parcel->writeString(Ljava/lang/String;)V":{
                return;
            }
            case "android/os/Parcel->readException()V":{
                return;
            }
            case "android/os/Parcel->recycle()V":{
                return;
            }
        }
        super.callVoidMethodV(vm, dvmObject, signature, vaList);
    }


    @Override
    public boolean callStaticBooleanMethodV(BaseVM vm, DvmClass dvmClass, String signature, VaList vaList) {
        switch (signature){
            case "com/xunmeng/pinduoduo/ut/util/Utils->hitSpng(Landroid/content/Context;)Z":{
                return false;
            }
        }
        return super.callStaticBooleanMethodV(vm, dvmClass, signature, vaList);
    }

    @Override
    public long getLongField(BaseVM vm, DvmObject<?> dvmObject, String signature) {
        switch (signature){
            case "android/content/pm/PackageInfo->firstInstallTime:J":{
                return 1628930489657L;
            }
            case "android/content/pm/PackageInfo->lastUpdateTime:J":{
                return 1628930489657L;
            }
        }
        return super.getLongField(vm, dvmObject, signature);
    }

    @Override
    public FileResult resolve(Emulator emulator, String pathname, int oflags) {
        System.out.println("fuckpath:"+pathname);
        if(pathname.equals("/proc/cpuinfo")){
            return FileResult.success(new SimpleFileIO(oflags, new File("unidbg-android/src/test/resources/PDD_8/cpuinfo"), pathname));
        }
        return null;
    }

    @Override
    public boolean callBooleanMethodV(BaseVM vm, DvmObject<?> dvmObject, String signature, VaList vaList) {
        switch (signature){
            case "android/net/NetworkInfo->isConnected()Z":{
                return false;
            }
            case "java/lang/String->startsWith(Ljava/lang/String;)Z":{
                String str = (String) dvmObject.getValue();
                return str.startsWith(vaList.getObjectArg(0).getValue().toString());
            }
        }
        return super.callBooleanMethodV(vm, dvmObject, signature, vaList);
    }

    @Override
    public int callIntMethodV(BaseVM vm, DvmObject<?> dvmObject, String signature, VaList vaList) {
        switch (signature){
            case "android/net/NetworkInfo->getType()I":{
                return 0;
            }
            case "android/content/SharedPreferences->getInt(Ljava/lang/String;I)I":{
                return 7;
            }
        }
        return super.callIntMethodV(vm, dvmObject, signature, vaList);
    }
}

//63b489ab3358dd54
