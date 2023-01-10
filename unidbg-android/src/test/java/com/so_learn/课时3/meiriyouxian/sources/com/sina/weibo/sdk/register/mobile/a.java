package com.sina.weibo.sdk.register.mobile;

import android.content.Context;
import android.service.notification.ZenModeConfig;
import android.text.TextUtils;
import cn.missfresh.buttomline.logtrace.bean.ConstantKey;
import com.android.internal.telephony.PhoneConstants;
import com.umeng.analytics.pro.c;
import com.unionpay.tsmservice.data.Constant;

/* compiled from: PinyinUtils */
public class a {
    private static a a;
    private static short[] b;
    private static final String[] c = {"a", "ai", "an", "ang", "ao", "ba", "bai", "ban", "bang", "bao", "bei", "ben", "beng", "bi", "bian", "biao", "bie", "bin", "bing", "bo", "bu", "ca", "cai", "can", "cang", "cao", "ce", "cen", "ceng", "cha", "chai", "chan", "chang", "chao", "che", "chen", "cheng", "chi", "chong", "chou", "chu", "chuai", "chuan", "chuang", "chui", "chun", "chuo", "ci", "cong", "cou", "cu", "cuan", "cui", "cun", "cuo", "da", "dai", "dan", "dang", "dao", "de", "deng", "di", "dia", "dian", "diao", "die", "ding", "diu", "dong", "dou", c.W, "duan", "dui", PhoneConstants.APN_TYPE_DUN, "duo", "e", "ei", "en", "er", "fa", "fan", "fang", "fei", "fen", "feng", "fo", "fou", "fu", "ga", "gai", "gan", "gang", "gao", "ge", "gei", "gen", "geng", "gong", "gou", "gu", "gua", "guai", "guan", "guang", "gui", "gun", "guo", "ha", "hai", "han", "hang", "hao", "he", "hei", "hen", "heng", "hong", "hou", "hu", "hua", "huai", "huan", "huang", "hui", "hun", "huo", "ji", "jia", "jian", "jiang", "jiao", "jie", "jin", "jing", "jiong", "jiu", "ju", "juan", "jue", "jun", "ka", "kai", "kan", "kang", "kao", "ke", "ken", "keng", "kong", "kou", "ku", "kua", "kuai", "kuan", "kuang", "kui", "kun", "kuo", "la", "lai", "lan", "lang", "lao", "le", "lei", "leng", "li", "lia", "lian", "liang", "liao", "lie", "lin", "ling", "liu", "long", "lou", "lu", "luan", "lun", "luo", "lv", "lve", "m", "ma", "mai", "man", "mang", "mao", "me", "mei", "men", "meng", "mi", "mian", "miao", "mie", "min", "ming", "miu", "mo", "mou", "mu", "na", "nai", "nan", "nang", "nao", "ne", "nei", "nen", "neng", "ng", "ni", "nian", "niang", "niao", "nie", "nin", "ning", "niu", "none", "nong", "nou", "nu", "nuan", "nuo", "nv", "nve", "o", "ou", "pa", "pai", "pan", "pang", "pao", "pei", "pen", "peng", "pi", "pian", "piao", "pie", Constant.KEY_PIN, "ping", "po", "pou", "pu", "qi", "qia", "qian", "qiang", "qiao", "qie", "qin", "qing", "qiong", "qiu", "qu", "quan", "que", "qun", "ran", "rang", "rao", "re", "ren", "reng", "ri", "rong", "rou", "ru", "ruan", "rui", "run", "ruo", "sa", "sai", "san", "sang", "sao", "se", "sen", "seng", "sha", "shai", "shan", "shang", "shao", "she", "shei", "shen", "sheng", "shi", "shou", "shu", "shua", "shuai", "shuan", "shuang", "shui", "shun", "shuo", ConstantKey.SI, "song", "sou", "su", "suan", "sui", "sun", "suo", "ta", "tai", "tan", "tang", "tao", "te", "teng", "ti", "tian", "tiao", "tie", "ting", "tong", "tou", "tu", "tuan", "tui", "tun", "tuo", "wa", "wai", "wan", "wang", "wei", "wen", "weng", "wo", "wu", "xi", "xia", "xian", "xiang", "xiao", "xie", "xin", "xing", "xiong", "xiu", "xu", "xuan", "xue", "xun", "ya", "yan", "yang", "yao", "ye", "yi", "yiao", "yin", "ying", "yo", "yong", "you", "yu", "yuan", "yue", "yun", "za", "zai", "zan", "zang", "zao", "ze", "zei", ZenModeConfig.ZEN_TAG, "zeng", "zha", "zhai", "zhan", "zhang", "zhao", "zhe", "zhei", "zhen", "zheng", "zhi", "zhong", "zhou", "zhu", "zhua", "zhuai", "zhuan", "zhuang", "zhui", "zhun", "zhuo", "zi", "zong", "zou", "zu", "zuan", "zui", "zun", "zuo"};
    private static volatile boolean d;

    private a() {
    }

    public static synchronized a a(Context context) {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            b(context);
            aVar = a;
        }
        return aVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0066 A[SYNTHETIC, Splitter:B:44:0x0066] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x006b A[Catch:{ IOException -> 0x006e }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:54:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void b(android.content.Context r6) {
        /*
            r0 = 0
            r1 = 0
            boolean r2 = com.sina.weibo.sdk.register.mobile.a.d     // Catch:{ IOException -> 0x0055, Exception -> 0x004a, all -> 0x0045 }
            if (r2 == 0) goto L_0x0007
            return
        L_0x0007:
            android.content.res.AssetManager r6 = r6.getAssets()     // Catch:{ IOException -> 0x0055, Exception -> 0x004a, all -> 0x0045 }
            java.lang.String r2 = "pinyinindex"
            java.io.InputStream r6 = r6.open(r2)     // Catch:{ IOException -> 0x0055, Exception -> 0x004a, all -> 0x0045 }
            java.io.DataInputStream r2 = new java.io.DataInputStream     // Catch:{ IOException -> 0x0056, Exception -> 0x004b }
            r2.<init>(r6)     // Catch:{ IOException -> 0x0056, Exception -> 0x004b }
            int r0 = r2.available()     // Catch:{ IOException -> 0x0043, Exception -> 0x0041, all -> 0x003f }
            r3 = 1
            int r0 = r0 >> r3
            long r4 = (long) r0     // Catch:{ IOException -> 0x0043, Exception -> 0x0041, all -> 0x003f }
            int r0 = (int) r4     // Catch:{ IOException -> 0x0043, Exception -> 0x0041, all -> 0x003f }
            short[] r0 = new short[r0]     // Catch:{ IOException -> 0x0043, Exception -> 0x0041, all -> 0x003f }
            com.sina.weibo.sdk.register.mobile.a.b = r0     // Catch:{ IOException -> 0x0043, Exception -> 0x0041, all -> 0x003f }
            r0 = r1
        L_0x0024:
            short[] r4 = com.sina.weibo.sdk.register.mobile.a.b     // Catch:{ IOException -> 0x0043, Exception -> 0x0041, all -> 0x003f }
            int r4 = r4.length     // Catch:{ IOException -> 0x0043, Exception -> 0x0041, all -> 0x003f }
            if (r0 < r4) goto L_0x0034
            com.sina.weibo.sdk.register.mobile.a.d = r3     // Catch:{ IOException -> 0x0043, Exception -> 0x0041, all -> 0x003f }
            r2.close()     // Catch:{ IOException -> 0x0060 }
            if (r6 == 0) goto L_0x0060
        L_0x0030:
            r6.close()     // Catch:{ IOException -> 0x0060 }
            goto L_0x0060
        L_0x0034:
            short[] r4 = com.sina.weibo.sdk.register.mobile.a.b
            short r5 = r2.readShort()
            r4[r0] = r5
            int r0 = r0 + 1
            goto L_0x0024
        L_0x003f:
            r0 = move-exception
            goto L_0x0064
        L_0x0041:
            r0 = r2
            goto L_0x004b
        L_0x0043:
            r0 = r2
            goto L_0x0056
        L_0x0045:
            r6 = move-exception
            r2 = r0
            r0 = r6
            r6 = r2
            goto L_0x0064
        L_0x004a:
            r6 = r0
        L_0x004b:
            com.sina.weibo.sdk.register.mobile.a.d = r1     // Catch:{ all -> 0x0061 }
            if (r0 == 0) goto L_0x0052
            r0.close()
        L_0x0052:
            if (r6 == 0) goto L_0x0060
            goto L_0x0030
        L_0x0055:
            r6 = r0
        L_0x0056:
            com.sina.weibo.sdk.register.mobile.a.d = r1
            if (r0 == 0) goto L_0x005d
            r0.close()
        L_0x005d:
            if (r6 == 0) goto L_0x0060
            goto L_0x0030
        L_0x0060:
            return
        L_0x0061:
            r1 = move-exception
            r2 = r0
            r0 = r1
        L_0x0064:
            if (r2 == 0) goto L_0x0069
            r2.close()     // Catch:{ IOException -> 0x006e }
        L_0x0069:
            if (r6 == 0) goto L_0x006e
            r6.close()     // Catch:{ IOException -> 0x006e }
        L_0x006e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.register.mobile.a.b(android.content.Context):void");
    }

    private String a(char c2) {
        if (!d) {
            return "";
        }
        if (c2 == '\u3007') {
            return "LING";
        }
        if (c2 < '\u4e00' || c2 > '\u9fa5') {
            return String.valueOf(c2);
        }
        String str = c[b[c2 - '\u4e00']];
        if (str == null) {
            return "";
        }
        return str;
    }

    public String a(String str) {
        if (TextUtils.isEmpty(str) || !d) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        for (int i = 0; i < length; i++) {
            sb.append(a(str.charAt(i)));
        }
        return sb.toString();
    }

    public static a a() {
        return a;
    }
}
