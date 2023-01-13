package com.so_learn;

import com.github.unidbg.AndroidEmulator;
import com.github.unidbg.Module;
import com.github.unidbg.linux.android.AndroidEmulatorBuilder;
import com.github.unidbg.linux.android.AndroidResolver;
import com.github.unidbg.linux.android.dvm.*;
import com.github.unidbg.memory.Memory;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author zhoutianxing
 * @Date 2021/8/26 13:30
 * @Description
 */
public class DongchediResponseDecrypt extends AbstractJni {
    private final VM vm;
    private final Module module;
    private final AndroidEmulator emulator;

    public static void main(String[] args) {
        Logger.getLogger(DalvikVM.class).setLevel(Level.DEBUG);
        Logger.getLogger(BaseVM.class).setLevel(Level.DEBUG);

        DongchediResponseDecrypt dongchediResponseDecrypt = new DongchediResponseDecrypt();
        String result = dongchediResponseDecrypt.tfccDecrypt(31, 1, "14zRM+40n2UGVx0DlI7hqDFjsxGR6eJsnnxUME5ZDT8=", "AQAAAN2jNvABWgWgENyxrfXPzJpMKs1R83DSBwVW9AVwp2yJJgPUqGUknSuAF1LySiic1HFymuBs49mC4Tp8baexYSM0roVtSX6F6D/a0Ll3BayKHCoTJcBUhKSdwahJh7yRx8LYZuuE2p3SDaMXAtKBGtzcuc2Om5mPuhQD5Hxx/Q7nLUlcrDlGh1WxzaK0Td4hzagFP9O/uvERYY+uqhNDN/DXvZuzN6GyLPntLKE\n" +
                "IwS7rzDUdsSq/m+8Ur4SgrjMOlWPdxSTdzRpAujk8Ff7h5bOVK2jJchor9kWx1UyEkGrHZWhuM6625fj/hS3bOZlHdX+rkCsxzkw9F6ncOTW4ecBfO4jgX/jLUcBJQ+fFUeBOyNw9X/Vx4u/MLUz9h8n2U+sci/Z4lZqZfPwSJHhrxEE2I4CZZfTRPFf5vkjN16TNL+/7z4oy8nIsx/cVZdRFTHjAQ3rkKH4yLs0hYb\n" +
                "4PIi075zaM20az5pvTE0+nyht2vy6S93l9NX+hP73L+5Pf+/CBb+q9wsvYoQIyofmyo/AUZcLJuTnmFfc4Q7K8M3RBERNUK1N04RCm+EEhHPura214oIiVEU/kKgPvlLKhJszZkdJQ2F4KJcrtzOUQYDdAKdtaMFenBhPqBTwWMmya1hR8/EOGDWxMIsak5SwpgVDXkpEYpDaRErSVMN3u17g+yZDpDfpI4Bb9cLdo6\n" +
                "xOEcwdzix4yN8F+/MKK9bxFmufl64XigeSNvzkDZuKtXlExXIUFVph/dQRdmKktO6oZc6GGNwUc32IHkYvB9fpItQ9etbF3r10vti3k0o3pVLOUcZ5sNHM564940fhdtEfbfEpkEwVJ3RcKXaEQ+7+i4tM3jQoCGpFT9gMex3w8Ve0ynWugRBOEr/yrQqSgjxdj9JnLaE5DbyFHWVLi7+m/Y4+/iaH/VTKfKYxEea6q\n" +
                "Ncxep2YNQ+SDn0caQK+Vgj9+ozYdRq0OlUHxTfhzJSHtXAzHIXJLZMQD7/F4lAGFVbUsygnoRZQM03w91pWdmpbwmpgj2YJvA27xiEFhm5MStA2Y8kPgkVbZs4ZqgXPLMkOKVrsK2am6mZML83gT2IjjSZEvjvn3gP1JAu+ytnKe6/pkv7BfvV3NDqlm9QIQhTvqGUuSeg/PnIoLxoggNuu1uh8+a/i6x5iWChkdFeP\n" +
                "AVdzV+iE9qVactV4u1gR83i/2R2hk0I22eA9Orl/RUxpv6dbi0R0qT46szbhj0cL9Xg5d6QFmwVr38zeKXrg3vCoVo1H90latSgoYB9Urd3XTe5WVRO6cyRv33oK7a7/59DaZ4rl9eEmo5IDBj4NZPj7FMWlgo76e9seoc2LedJLPPrZPdVsYlF+vHhT2HMPZ4mDTVHWgYatF09TduI7uiIlObN2nWnypArkhLIj6BO\n" +
                "XaNyWfrwGVwwo/xzD1NONNR23awSbJl7yAICeEE7OLtVGTZ40ZIkrWh5LVttnl3TZ6yVJ6Oere0BSugIgnju9+3gXSxunE3Zh8utz7E0VZmm3xcPNCrn3p4U3cgc+gOxER9pMJkqbl4+t7lfJAZFWyWsEsohchmozzaQwsNnj1W0tdE535AFnJ6OV85NI2ADxgKW3TZMQDvEpvTHEKz0ZbyFz/jUNVWoRaKPpllFeiu\n" +
                "hNIQ1skbcpTEbNquz5pSOFuVX7gauMHUOA2gyh3wmG3J4WITMwcVkyxBHRSYxhu4ONMQmrB9nFHAiN2HiE8VsTuIyxLb0prw2E+kdSEJ7w4u0kod1qbD2nAVoYvtR7JbX7ATGCyZUm/ZtQQfQsqGfTgCGjEeC67BEPsE0f1DmFQ+uSyOzTCcDY2UJkrQqC4pf0+VRQxcc5FvJGskeMk5B0Wl2TKGNCKrmRKFemjqguX\n" +
                "GRaxjoXiVCQyoEaTufMk+dUnbFFX9xPjy6llxvy/o+ZQ8vN0Gqha5QzW5xaOxo0q6khMfBd2rjwvuVGWyPs+L5AJWoooTTWY9axs4KF61es2PTVXiI6OE651mTR7c/u5jriAdnCsNF9PlgXyq3uan848uR6ppw1STYO1FP7oCO4SD83Diax13U9GvM5olYs0WHtZeR2hzS7xsiy+w9kTDzvLvQul6IhmBnXGy2ktIot\n" +
                "8vRdPgp9z3czfQ/HU1TLYNEk7409mo4HYu//HTIp9HION+N4NrM4bciu8z1L4bwbGSHJ2Tqv6rZHbcT9KxBZW2lwnBiQ/9jSiGq3VfFRGSqPXPXTbIf64dlfXoclW//7U5SZ1Ken3Uhh7WK5EYT3pLYfEFvAVflNRfAOErbAhU6hUT5YM3r9YjAjnYt+ULJsZbuZeLYYauR7wsHx+D0e96TEUQrRVZkRm0OFRvLB1/G\n" +
                "B1f/DDS6F+fY67RVsY8KRTFR8MmewfeGCMilc+IreiUXAfeKwGXDEczVJZ9lLSvWmr4+QS8em9E5TPzfzfFZYjoAknTP90DjmBwWhmCg8Bx/tZhn4NKgGXF+bfrJJ74rfac/BA+lCu+gIXUAgqJF3dPlnd1Uqc7WlmSS4GlAa1QUmUW5tVI8MTeRtIjpbVibJfrIqFqqm4ckXP0La9JqImF8Z0XosRn9h38OzzAmRsb\n" +
                "qB2wt/Kb9DdLIP8kEUZwYjiXmVpLIcuXi1kK8vQZzmAM/F/FayOKIF/3kjW/0+LYEy2OaE/9mNAffpDrdyBe9p6oQo/0rZ4h0g0bvBugp+IP2IStGh1EofobG5cpevKYFVxt6aiicohmfj0hAOIf09Bkgjs9LXPaM3gzRrI8b9lPIhW2VTw0UIhgsqS5ehnipaapO1OFRvET2ShMnxKDDPYBPnAVvnhSh4Nuzlp2TJo\n" +
                "VlCRxeh+QXntAF9swBuIAfeW9/x5MKInq9ayZQXgK8BoInjZvS6PQJR0YO/ohXnm6TJx+84yBZogvGIss+lDAKez0enTdcsxzeywbf3Q1vmS6nOlssjfjJQYh1/e58bL2vB6rrUwM/bx7QMlzORu/dgBH12tBfaUAFCeNguWGYWYstqwxa3Pon54Yb04ArQ+Mdfj5YNMjFmIpWMRXZS9fLhUHNuOgfgCX81Ah2Y5/xW\n" +
                "Ylu8G8J5kWuhU7kgj34AEC3g/m+RSB2cibSnmjSGcYWDCHu3R6/8/+GXDx0aQy/HDwmbZJAyn9uEmanEiP3fozSYpWBDP7RZpFkM0VIgTqziX3/LMa+0dOQxQvmLSI8S0U87Dg84AfuFAMWYEdmwpOIggY3KVjDEEimMENPic5QpgI4tCUoUZUAtGhME7F5pHFGWonurtBCPMJYoGgUPqdowLYFknBSxfww3mnmUqyD\n" +
                "njHoY7mOL4fVommCxrKlPIs3yp4w5wsVMESQwvPfBj7n6OfrJa5T8HUcb5QQk9+8nGD39SrPjbeImdqpQ6f9OB7FJBMkfr8wxy/jKB+RbsoqcIEfnz9TyY2wtu8FAkw0Ru0SoR8zzI05sUflO53KxM7wqfWM56UfQVm7LxTNSDNyM05g5+Xi4qSxj6elu/HNFLMRySCLM+M8ACxyOdX3f4aM64K/igULDFVWIy33jJR\n" +
                "9kVAK3DfPf5RnnkSzD7zqraDL8yqEyUjj8Q1Qz1YgVsCKyN1z7Y3FkBilaaISNxWcNOwqpYeJlE6Q3R/bPduP0tvC6dR1yTUIVTRs8WqEz41LJ55Gj1hBwhsutX7x3oqSniUP4N8ajsi8d1q6VD0qNyQIUNTmpsbuFucO+zi5/bh3SmQTZpDJ7DDE/X7Fg1tjoeRwopwbeomE/qBlzNXPrPNwXtpqPs3RxxA9IosOSl\n" +
                "A6QQwE/GeJD3o+WmAvAajivSkq3uIk105un0UldHCAqOIWJK8vIfUjfp+4jdnvCuwhoRCfjRqUmuAaEwspdfO/b/H/H61OrPh5btpLmH5MFloa8myakUY0aJRmiWmFIb/6MVun/VTmC4E3d+AmQ/Oglhu8hHSeGqPRYJNnGB5NW1CmyXE9lZ7cGcDKrI13TAgkyf+qWhrWqju9IG/7Ktt9vPhQaxUUiJQLUu6SeGbk2\n" +
                "L01YVcW4wcwmeUIatZFPQ8xZfePaRJ39KJq/5EOSL1S0KoA3TZl0gu0mtB97LIhFgrYRCb0iCmq0iLCxIgr9fctsscqo9a3GMq6dRkYW6UXIjfLi1mEOY4b9Y1AWOpRBRdJvFQVZc6Gsn1qmpKyBEF8rgX4wgPKtrY3S1p11lBDvLjDL2hv7VKB1AgdtVk3Lmqw7H34/XIclgHwXK5EZaPtsNi2SvAkI5Ng9t5rxnzy\n" +
                "+EHXIBkuv008tOAbbg9/DLLjBwd+EfMuueenoRfqUt3zEy2HZdA86oefarV+J/k6iFl5yVFhmP+QBGNMQD3ZIOWV5lsa+Nlk/JBbIGwNirI1lUyzbngZiZZ67lVE/pL9SukmoUlb6/Z9Jpo7XyF0lDoq/FI7pfGiNVWfQRH1iN8H08F5vBqsOw+Ypv1o2ed2cL48NayxC6Iuv0rSSGDZ4tgSbfXIIrYtshohZSRbVlX\n" +
                "e4p5aaaVJ5MG6EqrHwF5Aw/KsftQlqhaZKCoRkOWIX1Mox8G78Ni+Ca3fiwklAdyDZUQk3Hyf+KOEE/LnILkZXd9S8mMiHWxLu8WmBixvq8uwfVlrROr8o1hD8iv9xstfzMzTtJ0luri4ry1GOA82B1lTxVBp9qwGqKGmQOny82VhlcRBYG62ykMzMnsx5GC/NEep1L9SGvH+2jR0HZ+AAcXo8mDwerF2b1AhCFO+oZ\n" +
                "S5J6D8+cigvGiCA267W6Hz5r+LrHmJYKGR0V48BV3NX6IT2pVpy1Xrlp3nW6X1CgWwecPba7Ou07JNQYmtYLGfn2QssqFDRF0RiP0pvFVP0OvLzBM6bvLgx4E/oPtnwIocacetGLncVl5EX7VniGAiYUCVpipfceTiQWp655f6BQmLi8GLPNJKWrD0acS6ELq6bReHp7UF5AVjLfhNSGLIe8oDxm5+ezjlQqP0vaQ6p\n" +
                "AgrKhZvyYZl0GheHwzmTyKBo1pM/SLsMwG8yg+Hrn4oV3FrRJpc8ghWdPEmpBKcq2XPnOWvMPuxKMKXrKIVH2829tIYC9/jcMIHSC44IPXsA5QXD1PwzIHslOxHQekEnjY4vKV6CYrQ2dnUuWidguHgXIYUxoOFxIF9c8/8ng5W/8TX19//l8n88/llkjOvVYweRpV3X09My8cVIJTqyGvjo1F2ZcbnzS5ia5WUSWwd\n" +
                "TGX7LO/q8E/2ko2LAMeKYeoS2eXQeq8ki5SsrqYnhEoB/NW2JESVRC3ULvQEPDRASM6D33P58u48cKko5dN+PJ1+glxp6elFLNOgRT5JZgDj+fhcHMJnkEiIuX54npbC5U2+SO//gqFH5TudysTO8Kn1jOelH0Fcun54nugTU4gQzGNbUx4nCnL1lVAJ9slKcainGyYSgrMS6cErMXn0/XcPkeljWuC7OeIzIe54PJ9\n" +
                "GF55F7+t6ROq7ocvmaSmxIeokNY5Sbuzn7ads+CBnKP2qw5uO1lY58CAVnXXzd5s7mRHlaaEsefD7Mx4KD4RKMN0g/84/3dk+x/CZZBsiNlXYgWP1JlDKSNdHfOz1md/5omAQ3zn33RG4czkzPpilEtefe5lvLde8R+sehf7iX5mhe9WMOaOQJy2AEPSHCfj7/W8BBl+MkuOvBzCiZtP2Wc/HqlAOq4iVrzVz9yDOMg\n" +
                "jgMMThobcvZrM4kUcA3TGFuWq+Y9UfGpZIAntAwNXTuqmaIYyQ9Sn0Ac+6IaZU6Bv+BhBOVOiBEDW7bC69qHPydBL/eFN5KgD7eBQZIWysjA09gqkbJfx3jkcolr4SPI1csJL/FUwUx8s54HyVpnvZXSkHYTNSGxQUSihxINtqDuzZBpfcL5ifI+THkA7d6XhyR5JOelZo7RMYITr5DmJSHiAWUrhYsaPUuLaDWLicE\n" +
                "NtS6zQvB++eX9B60T7A3ja6yZfEPEosMZU4bWd2GrrbB1yNimZWPP1J7PyAiNrCW5VW6y/upAIKzah3F1MoZxXCtg1LvjsJkVpqIu4LtKlpwMz2K1p1H82Ru8gKeNlHTmaB4vFSKmoYKoet2xifrjYrRLZPdLFQ==");
        System.out.println("************************");
        System.out.println(result);
        System.out.println("########################");
    }

    public String tfccDecrypt(int param1, int param2, String param3, String param4) {
        List<Object> params = new ArrayList<>();
        params.add(vm.getJNIEnv()); //第一个参数默认env
//        params.add(0); //第二个参数一般填0，一般用不到
        DvmClass Tfcc = vm.resolveClass("com/bdcaijing/tfccsdk/Tfcc");
        DvmObject<?> TfccObject = Tfcc.newObject(null);
        params.add(vm.addLocalObject(TfccObject));

        params.add(param1);
        params.add(param2);
        StringObject stringObject1 = new StringObject(vm, param3);
        params.add(vm.addLocalObject(stringObject1));
        StringObject stringObject2 = new StringObject(vm, param4);
        params.add(vm.addLocalObject(stringObject2));

        Number number = module.callFunction(emulator, 0xa2ac, params.toArray());
        System.out.println("number:" + number);
        return null;
    }

    DongchediResponseDecrypt() {
        emulator = AndroidEmulatorBuilder.for32Bit().build();
        final Memory memory = emulator.getMemory();
        memory.setLibraryResolver(new AndroidResolver(23));

        vm = emulator.createDalvikVM(new File("unidbg-android/src/test/java/com/so_learn/20210827/dongchedi_6_5_1.apk"));
        vm.setVerbose(true); //打印虚拟器日志
        vm.setJni(this);

        DalvikModule dm = vm.loadLibrary(new File("unidbg-android/src/test/java/com/so_learn/20210827/libcjtfcc.so"), true);
        module = dm.getModule();
        dm.callJNI_OnLoad(emulator);

//        // 下断点
//        emulator.attach().addBreakPoint(module.base+0x92A8);
//
//        // traceWrite
//        emulator.traceWrite(0xbffff6d8L,0xbffff6d8L);
//
//        // 下断点
//        emulator.attach().addBreakPoint(module.base+0x8F8C);


    }
}
