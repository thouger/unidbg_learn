package com.sobot.chat.utils;

import android.content.Context;
import android.graphics.Color;
import android.media.TtmlUtils;
import android.text.TextUtils;
import androidx.core.content.ContextCompat;
import java.io.File;
import org.json.JSONObject;

/* compiled from: ResourceUtils */
public class q {
    public static int a(Context context, String str, String str2) {
        Context applicationContext = context.getApplicationContext();
        return applicationContext.getResources().getIdentifier(str2, str, applicationContext.getPackageName());
    }

    public static int a(Context context, String str) {
        return a(context, TtmlUtils.TAG_LAYOUT, str);
    }

    public static int b(Context context, String str) {
        return a(context, "string", str);
    }

    public static int c(Context context, String str) {
        return a(context, "color", str);
    }

    public static int d(Context context, String str) {
        return ContextCompat.getColor(context, c(context, str));
    }

    public static int e(Context context, String str) {
        return a(context, "drawable", str);
    }

    public static String f(Context context, String str) {
        if (s.b(context, "sobot_use_language", false)) {
            String b = s.b(context, "sobot_language_string_path", "");
            if (!TextUtils.isEmpty(b) && new File(b).exists()) {
                try {
                    JSONObject jSONObject = new JSONObject(a(b));
                    if (jSONObject.has(str)) {
                        return jSONObject.optString(str);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return context.getResources().getString(b(context, str));
    }

    public static int g(Context context, String str) {
        return a(context, "id", str);
    }

    public static String h(Context context, String str) {
        if (context == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        int color = context.getResources().getColor(c(context, str));
        stringBuffer.append("#");
        stringBuffer.append(Integer.toHexString(Color.alpha(color)));
        stringBuffer.append(Integer.toHexString(Color.red(color)));
        stringBuffer.append(Integer.toHexString(Color.green(color)));
        stringBuffer.append(Integer.toHexString(Color.blue(color)));
        return stringBuffer.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0052 A[SYNTHETIC, Splitter:B:26:0x0052] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(java.lang.String r4) throws java.io.IOException {
        /*
            java.lang.StringBuffer r0 = new java.lang.StringBuffer
            r0.<init>()
            java.lang.String r1 = android.os.Environment.getExternalStorageState()
            java.lang.String r2 = "mounted"
            boolean r1 = r1.equals(r2)
            if (r1 == 0) goto L_0x005b
            r1 = 0
            java.io.FileInputStream r2 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0041 }
            r2.<init>(r4)     // Catch:{ Exception -> 0x0041 }
            java.io.InputStreamReader r4 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x003b, all -> 0x0039 }
            java.lang.String r1 = "UTF-8"
            r4.<init>(r2, r1)     // Catch:{ Exception -> 0x003b, all -> 0x0039 }
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ Exception -> 0x003b, all -> 0x0039 }
            r1.<init>(r4)     // Catch:{ Exception -> 0x003b, all -> 0x0039 }
        L_0x0025:
            java.lang.String r3 = r1.readLine()     // Catch:{ Exception -> 0x003b, all -> 0x0039 }
            if (r3 == 0) goto L_0x002f
            r0.append(r3)     // Catch:{ Exception -> 0x003b, all -> 0x0039 }
            goto L_0x0025
        L_0x002f:
            r1.close()     // Catch:{ Exception -> 0x003b, all -> 0x0039 }
            r4.close()     // Catch:{ Exception -> 0x003b, all -> 0x0039 }
            r2.close()     // Catch:{ IOException -> 0x004b }
            goto L_0x005b
        L_0x0039:
            r4 = move-exception
            goto L_0x0050
        L_0x003b:
            r4 = move-exception
            r1 = r2
            goto L_0x0042
        L_0x003e:
            r4 = move-exception
            r2 = r1
            goto L_0x0050
        L_0x0041:
            r4 = move-exception
        L_0x0042:
            r4.printStackTrace()     // Catch:{ all -> 0x003e }
            if (r1 == 0) goto L_0x005b
            r1.close()
            goto L_0x005b
        L_0x004b:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x005b
        L_0x0050:
            if (r2 == 0) goto L_0x005a
            r2.close()     // Catch:{ IOException -> 0x0056 }
            goto L_0x005a
        L_0x0056:
            r0 = move-exception
            r0.printStackTrace()
        L_0x005a:
            throw r4
        L_0x005b:
            java.lang.String r4 = r0.toString()
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.utils.q.a(java.lang.String):java.lang.String");
    }
}
