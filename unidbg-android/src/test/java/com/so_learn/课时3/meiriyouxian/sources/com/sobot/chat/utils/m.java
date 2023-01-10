package com.sobot.chat.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;
import com.sobot.chat.api.apiUtils.GsonUtil;
import com.sobot.chat.api.apiUtils.SobotApp;
import com.sobot.chat.core.channel.a;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/* compiled from: LogUtils */
public class m {
    public static boolean a = false;
    public static boolean b = true;
    public static boolean c = false;
    public static boolean d = false;
    public static boolean e = false;
    public static boolean f = false;
    public static boolean g = false;
    public static boolean h = false;
    public static int i = 3;
    public static String j = null;
    private static String k = "sobot_chat";
    private static File l;

    private static String c() {
        return "sobot_log";
    }

    public static void a(Context context) {
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        try {
            k = (String) packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0));
        } catch (PackageManager.NameNotFoundException e2) {
            e2.printStackTrace();
        }
        if (b) {
            a(d.b(context));
        }
    }

    public static void a(String str) {
        String str2 = str + File.separator + k + File.separator + "sobot";
        j = str2 + File.separator + "sobot_log.txt";
        l = new File(str2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:22:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.lang.String r5, java.lang.String r6, java.lang.String r7, java.lang.Throwable r8) {
        /*
            java.lang.String r5 = com.sobot.chat.utils.m.j
            boolean r5 = android.text.TextUtils.isEmpty(r5)
            if (r5 != 0) goto L_0x005c
            r5 = 0
            java.io.File r6 = com.sobot.chat.utils.m.l
            boolean r6 = r6.exists()
            if (r6 != 0) goto L_0x0016
            java.io.File r6 = com.sobot.chat.utils.m.l
            r6.mkdirs()
        L_0x0016:
            java.io.PrintWriter r6 = new java.io.PrintWriter     // Catch:{ Exception -> 0x0054, all -> 0x004d }
            java.io.OutputStreamWriter r0 = new java.io.OutputStreamWriter     // Catch:{ Exception -> 0x0054, all -> 0x004d }
            java.io.FileOutputStream r1 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x0054, all -> 0x004d }
            java.lang.String r2 = com.sobot.chat.utils.m.j     // Catch:{ Exception -> 0x0054, all -> 0x004d }
            r3 = 1
            r1.<init>(r2, r3)     // Catch:{ Exception -> 0x0054, all -> 0x004d }
            java.lang.String r2 = "utf-8"
            r0.<init>(r1, r2)     // Catch:{ Exception -> 0x0054, all -> 0x004d }
            r6.<init>(r0)     // Catch:{ Exception -> 0x0054, all -> 0x004d }
            boolean r5 = android.text.TextUtils.isEmpty(r7)     // Catch:{ Exception -> 0x004b, all -> 0x0046 }
            if (r5 == 0) goto L_0x0034
            java.lang.String r7 = ""
        L_0x0034:
            r6.println(r7)     // Catch:{ Exception -> 0x004b, all -> 0x0046 }
            r6.flush()     // Catch:{ Exception -> 0x004b, all -> 0x0046 }
            if (r8 == 0) goto L_0x003f
            r8.printStackTrace(r6)     // Catch:{ Exception -> 0x004b, all -> 0x0046 }
        L_0x003f:
            r6.flush()     // Catch:{ Exception -> 0x004b, all -> 0x0046 }
            r6.close()
            goto L_0x0059
        L_0x0046:
            r5 = move-exception
            r4 = r6
            r6 = r5
            r5 = r4
            goto L_0x004e
        L_0x004b:
            r5 = r6
            goto L_0x0054
        L_0x004d:
            r6 = move-exception
        L_0x004e:
            if (r5 == 0) goto L_0x0053
            r5.close()
        L_0x0053:
            throw r6
        L_0x0054:
            if (r5 == 0) goto L_0x0059
            r5.close()
        L_0x0059:
            d()
        L_0x005c:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.utils.m.a(java.lang.String, java.lang.String, java.lang.String, java.lang.Throwable):void");
    }

    private static void d() {
        Context applicationContext = SobotApp.getApplicationContext();
        String b2 = s.b(applicationContext, "sobot_config_appkey", (String) null);
        if (i >= 0 && i.a(j, 3) > 2.0d && b2 != null) {
            a.a(applicationContext).a().logCollect(applicationContext, b2);
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a() {
        /*
            java.lang.Class<com.sobot.chat.utils.m> r0 = com.sobot.chat.utils.m.class
            monitor-enter(r0)
            java.io.File r1 = com.sobot.chat.utils.m.l     // Catch:{ Exception -> 0x0025, all -> 0x0022 }
            java.io.File[] r1 = r1.listFiles()     // Catch:{ Exception -> 0x0025, all -> 0x0022 }
            if (r1 == 0) goto L_0x0025
            int r2 = r1.length     // Catch:{ Exception -> 0x0025, all -> 0x0022 }
            if (r2 <= 0) goto L_0x0025
            r2 = 0
        L_0x000f:
            int r3 = r1.length     // Catch:{ Exception -> 0x0025, all -> 0x0022 }
            if (r2 >= r3) goto L_0x0025
            r3 = r1[r2]     // Catch:{ Exception -> 0x0025, all -> 0x0022 }
            boolean r3 = r3.isFile()     // Catch:{ Exception -> 0x0025, all -> 0x0022 }
            if (r3 == 0) goto L_0x001f
            r3 = r1[r2]     // Catch:{ Exception -> 0x0025, all -> 0x0022 }
            r3.delete()     // Catch:{ Exception -> 0x0025, all -> 0x0022 }
        L_0x001f:
            int r2 = r2 + 1
            goto L_0x000f
        L_0x0022:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        L_0x0025:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.utils.m.a():void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0048 A[SYNTHETIC, Splitter:B:28:0x0048] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x0056 A[SYNTHETIC, Splitter:B:35:0x0056] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.lang.String b() {
        /*
            java.lang.Class<com.sobot.chat.utils.m> r0 = com.sobot.chat.utils.m.class
            monitor-enter(r0)
            java.io.File r1 = new java.io.File     // Catch:{ all -> 0x005f }
            java.lang.String r2 = com.sobot.chat.utils.m.j     // Catch:{ all -> 0x005f }
            r1.<init>(r2)     // Catch:{ all -> 0x005f }
            boolean r2 = r1.exists()     // Catch:{ all -> 0x005f }
            r3 = 0
            if (r2 != 0) goto L_0x0013
            monitor-exit(r0)
            return r3
        L_0x0013:
            org.json.JSONArray r2 = new org.json.JSONArray
            r2.<init>()
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0042 }
            java.io.FileReader r5 = new java.io.FileReader     // Catch:{ Exception -> 0x0042 }
            r5.<init>(r1)     // Catch:{ Exception -> 0x0042 }
            r4.<init>(r5)     // Catch:{ Exception -> 0x0042 }
        L_0x0022:
            java.lang.String r1 = r4.readLine()     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            if (r1 == 0) goto L_0x0031
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            r3.<init>(r1)     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            r2.put(r3)     // Catch:{ Exception -> 0x003d, all -> 0x003a }
            goto L_0x0022
        L_0x0031:
            r4.close()     // Catch:{ IOException -> 0x0035 }
            goto L_0x004e
        L_0x0035:
            r1 = move-exception
        L_0x0036:
            r1.printStackTrace()
            goto L_0x004e
        L_0x003a:
            r1 = move-exception
            r3 = r4
            goto L_0x0054
        L_0x003d:
            r1 = move-exception
            r3 = r4
            goto L_0x0043
        L_0x0040:
            r1 = move-exception
            goto L_0x0054
        L_0x0042:
            r1 = move-exception
        L_0x0043:
            r1.printStackTrace()     // Catch:{ all -> 0x0040 }
            if (r3 == 0) goto L_0x004e
            r3.close()     // Catch:{ IOException -> 0x004c }
            goto L_0x004e
        L_0x004c:
            r1 = move-exception
            goto L_0x0036
        L_0x004e:
            java.lang.String r1 = r2.toString()
            monitor-exit(r0)
            return r1
        L_0x0054:
            if (r3 == 0) goto L_0x005e
            r3.close()     // Catch:{ IOException -> 0x005a }
            goto L_0x005e
        L_0x005a:
            r2 = move-exception
            r2.printStackTrace()
        L_0x005e:
            throw r1
        L_0x005f:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.utils.m.b():java.lang.String");
    }

    private static String e(String str) {
        return new SimpleDateFormat(str).format(new Date());
    }

    public static void b(String str) {
        if (a && c) {
            Log.d(c(), str);
        }
    }

    public static void c(String str) {
        if (a && d) {
            Log.e(c(), str);
        }
    }

    public static void d(String str) {
        if (a && e) {
            String c2 = c();
            if (str.length() > 3072) {
                String substring = str.substring(0, 3072);
                Log.i(c2 + "\u5206\u6bb5\u6253\u5370\u5f00\u59cb", substring);
                String substring2 = str.substring(3072, str.length());
                if (str.length() - 3072 > 3072) {
                    d(substring2);
                    return;
                }
                Log.i(c2 + "\u5206\u6bb5\u6253\u5370\u7ed3\u675f", substring2);
                return;
            }
            Log.i(c2, str);
        }
    }

    public static void a(String str, Throwable th) {
        if (a && e) {
            Log.i(c(), str, th);
        }
    }

    public static void b(String str, Throwable th) {
        if (a && g) {
            Log.w(c(), str, th);
        }
    }

    public static synchronized void a(Context context, Map<String, String> map, String str) {
        synchronized (m.class) {
            String e2 = e("yyyy-MM-dd HH:mm:ss");
            if (map != null) {
                if (context != null) {
                    String b2 = s.b(context, "sobot_appkey_chat", (String) null);
                    if (!TextUtils.isEmpty(b2)) {
                        map.put("appkey", b2);
                    }
                }
                map.put("type", str);
                map.put("time", e2);
                a(null, null, GsonUtil.map2Json(map), null);
            }
        }
    }

    public static synchronized void a(Map<String, String> map, String str) {
        synchronized (m.class) {
            a(null, map, str);
        }
    }
}
