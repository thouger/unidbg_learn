package com.hmt.analytics.android;

import android.content.Context;
import android.text.TextUtils;
import com.hmt.analytics.util.g;
import com.xiaomi.mipush.sdk.Constants;
import java.io.BufferedReader;
import java.io.IOException;

/* compiled from: NetworkUitlity */
public class i {
    private static final String a = i.class.getSimpleName();

    /* JADX WARNING: Removed duplicated region for block: B:65:0x0174  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0182  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(android.content.Context r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, java.lang.String r21) {
        /*
        // Method dump skipped, instructions count: 529
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hmt.analytics.android.i.a(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String):boolean");
    }

    private static void a(Context context, BufferedReader bufferedReader) {
        String str;
        StringBuilder sb;
        try {
            String[] split = bufferedReader.readLine().split(Constants.ACCEPT_TIME_SEPARATOR_SP);
            if (split == null || split.length <= 1) {
                com.hmt.analytics.util.i.a(context, "hmt_is_in_location", "0");
            } else {
                String substring = split[1].substring(0, 1);
                if (!TextUtils.isEmpty(substring)) {
                    String str2 = a;
                    a.a(str2, "isFit = " + substring);
                    com.hmt.analytics.util.i.a(context, "hmt_is_in_location", substring);
                }
            }
            try {
                bufferedReader.close();
                return;
            } catch (IOException e) {
                e = e;
                str = a;
                sb = new StringBuilder();
            }
            sb.append("Collected:");
            sb.append(e.getMessage());
            a.a(str, sb.toString());
        } catch (IOException e2) {
            String str3 = a;
            a.a(str3, "Collected:" + e2.getMessage());
            try {
                bufferedReader.close();
            } catch (IOException e3) {
                e = e3;
                str = a;
                sb = new StringBuilder();
            }
        } catch (Throwable th) {
            try {
                bufferedReader.close();
            } catch (IOException e4) {
                String str4 = a;
                a.a(str4, "Collected:" + e4.getMessage());
            }
            throw th;
        }
    }

    private static String a(String str, String str2) throws IOException {
        String a2 = l.a(l.b(str));
        String d = h.d("3266326432643430353239616363393831323131646435303261343662393333");
        String a3 = g.a(a2 + d + str2);
        StringBuilder sb = new StringBuilder();
        sb.append("contents=");
        sb.append(a2);
        String sb2 = sb.toString();
        return sb2 + "&sign=" + a3;
    }
}
