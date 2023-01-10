package cn.missfresh.wsg;

import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.huawei.hms.framework.common.ContainerUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/* compiled from: SecurityUtils */
public class b {
    public static String a(String str) {
        AppMethodBeat.i(22165, false);
        String a = a(c(str));
        AppMethodBeat.o(22165);
        return a;
    }

    public static String a(String str, byte[] bArr) {
        AppMethodBeat.i(22166, false);
        Map<String, String> c = c(str);
        int i = 2048;
        if (bArr.length <= 2048) {
            i = bArr.length;
        }
        byte[] bArr2 = new byte[i];
        System.arraycopy(bArr, 0, bArr2, 0, i);
        String a = a(c, bArr2);
        AppMethodBeat.o(22166);
        return a;
    }

    private static Map<String, String> c(String str) {
        AppMethodBeat.i(22168, false);
        if (str == null || str.length() == 0) {
            HashMap hashMap = new HashMap();
            AppMethodBeat.o(22168);
            return hashMap;
        }
        HashMap hashMap2 = new HashMap();
        try {
            String[] split = str.split("&");
            for (String str2 : split) {
                if (!TextUtils.isEmpty(str2)) {
                    String[] split2 = str2.split(ContainerUtils.KEY_VALUE_DELIMITER, 2);
                    if (split2.length == 2) {
                        StringBuilder sb = new StringBuilder();
                        String str3 = split2[0];
                        String str4 = split2[1];
                        sb.append(str3);
                        sb.append(str4);
                        hashMap2.put(sb.toString(), "");
                    } else {
                        hashMap2.put(split2[0], "");
                    }
                }
            }
            AppMethodBeat.o(22168);
            return hashMap2;
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(22168);
            return null;
        }
    }

    private static String a(Map<String, String> map) {
        AppMethodBeat.i(22169, false);
        StringBuilder sb = new StringBuilder();
        try {
            ArrayList<String> arrayList = new ArrayList(map.keySet());
            Collections.sort(arrayList, Collections.reverseOrder());
            for (String str : arrayList) {
                sb.append(str);
            }
            String sb2 = sb.toString();
            AppMethodBeat.o(22169);
            return sb2;
        } catch (Exception e) {
            e.printStackTrace();
            String valueOf = String.valueOf(c.h);
            AppMethodBeat.o(22169);
            return valueOf;
        }
    }

    private static String a(Map<String, String> map, byte[] bArr) {
        AppMethodBeat.i(22170, false);
        StringBuilder sb = new StringBuilder();
        try {
            ArrayList<String> arrayList = new ArrayList(map.keySet());
            arrayList.add(a(bArr));
            Collections.sort(arrayList, Collections.reverseOrder());
            for (String str : arrayList) {
                sb.append(str);
            }
            String sb2 = sb.toString();
            AppMethodBeat.o(22170);
            return sb2;
        } catch (Exception e) {
            e.printStackTrace();
            String valueOf = String.valueOf(c.h);
            AppMethodBeat.o(22170);
            return valueOf;
        }
    }

    private static String a(byte[] bArr) {
        AppMethodBeat.i(22171, false);
        if (bArr == null || bArr.length <= 0) {
            AppMethodBeat.o(22171);
            return null;
        }
        char[] charArray = "0123456789abcdef".toCharArray();
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            cArr[i3] = charArray[i2 >>> 4];
            cArr[i3 + 1] = charArray[i2 & 15];
        }
        String str = new String(cArr);
        AppMethodBeat.o(22171);
        return str;
    }

    public static boolean b(String str) {
        AppMethodBeat.i(22173, false);
        if (str == null || str.length() == 0) {
            AppMethodBeat.o(22173);
            return false;
        }
        byte[] bytes = str.getBytes();
        for (byte b : bytes) {
            if ((b < 97 || b > 122) && ((b < 65 || b > 90) && !((b >= 48 && b <= 57) || b == 43 || b == 45 || b == 47))) {
                AppMethodBeat.o(22173);
                return false;
            }
        }
        AppMethodBeat.o(22173);
        return true;
    }
}
