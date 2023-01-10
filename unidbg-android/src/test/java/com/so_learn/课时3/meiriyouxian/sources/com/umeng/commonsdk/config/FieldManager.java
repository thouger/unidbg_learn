package com.umeng.commonsdk.config;

import android.content.Context;
import android.util.Pair;
import com.umeng.commonsdk.config.d;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;

public class FieldManager {
    private static final String a = "cfgfd";
    private static b b = b.b();
    private static boolean c = false;
    private static Object d = new Object();

    private FieldManager() {
    }

    /* access modifiers changed from: private */
    public static class a {
        private static final FieldManager a = new FieldManager();

        private a() {
        }
    }

    public static FieldManager a() {
        return a.a;
    }

    public static boolean allow(String str) {
        synchronized (d) {
            if (!c) {
                return false;
            }
            b bVar = b;
            return b.a(str);
        }
    }

    public static boolean b() {
        boolean z;
        synchronized (d) {
            z = c;
        }
        return z;
    }

    public void a(Context context) {
        String str;
        String[] strArr = {d.a.class.getName(), d.b.class.getName(), d.c.class.getName(), d.EnumC0180d.class.getName()};
        String imprintProperty = UMEnvelopeBuild.imprintProperty(context, "cfgfd", "1001@3758096383,2147483647,262143,2047");
        synchronized (d) {
            Pair<Long, String> a2 = a(imprintProperty);
            if (a2.first.longValue() <= 1000 || (str = a2.second) == null || str.length() <= 0) {
                str = "1001@3758096383,2147483647,262143,2047";
            }
            String[] split = str.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
            int length = split.length;
            if (length > 0) {
                ArrayList arrayList = new ArrayList();
                g gVar = new g();
                for (int i = 0; i < length; i++) {
                    arrayList.add(gVar);
                    ((e) arrayList.get(i)).a(split[i], b, d.b(strArr[i]));
                }
            }
            c = true;
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x004d, code lost:
        if (r12.length() > 0) goto L_0x0053;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(android.content.Context r11, java.lang.String r12) {
        /*
            r10 = this;
            r11 = 4
            java.lang.String[] r11 = new java.lang.String[r11]
            java.lang.Class<com.umeng.commonsdk.config.d$a> r0 = com.umeng.commonsdk.config.d.a.class
            java.lang.String r0 = r0.getName()
            r1 = 0
            r11[r1] = r0
            java.lang.Class<com.umeng.commonsdk.config.d$b> r0 = com.umeng.commonsdk.config.d.b.class
            java.lang.String r0 = r0.getName()
            r2 = 1
            r11[r2] = r0
            java.lang.Class<com.umeng.commonsdk.config.d$c> r0 = com.umeng.commonsdk.config.d.c.class
            java.lang.String r0 = r0.getName()
            r3 = 2
            r11[r3] = r0
            java.lang.Class<com.umeng.commonsdk.config.d$d> r0 = com.umeng.commonsdk.config.d.EnumC0180d.class
            java.lang.String r0 = r0.getName()
            r3 = 3
            r11[r3] = r0
            java.lang.Object r0 = com.umeng.commonsdk.config.FieldManager.d
            monitor-enter(r0)
            com.umeng.commonsdk.config.b r3 = com.umeng.commonsdk.config.FieldManager.b     // Catch:{ all -> 0x0086 }
            r3.a()     // Catch:{ all -> 0x0086 }
            if (r12 == 0) goto L_0x0050
            android.util.Pair r12 = a(r12)     // Catch:{ all -> 0x0086 }
            F r3 = r12.first     // Catch:{ all -> 0x0086 }
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ all -> 0x0086 }
            long r3 = r3.longValue()     // Catch:{ all -> 0x0086 }
            r5 = 1000(0x3e8, double:4.94E-321)
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 <= 0) goto L_0x0050
            S r12 = r12.second     // Catch:{ all -> 0x0086 }
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ all -> 0x0086 }
            if (r12 == 0) goto L_0x0050
            int r3 = r12.length()     // Catch:{ all -> 0x0086 }
            if (r3 <= 0) goto L_0x0050
            goto L_0x0053
        L_0x0050:
            java.lang.String r12 = "1001@3758096383,2147483647,262143,2047"
        L_0x0053:
            java.lang.String r3 = ","
            java.lang.String[] r12 = r12.split(r3)
            int r3 = r12.length
            if (r3 <= 0) goto L_0x0082
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            com.umeng.commonsdk.config.g r5 = new com.umeng.commonsdk.config.g
            r5.<init>()
        L_0x0067:
            if (r1 >= r3) goto L_0x0082
            r4.add(r5)
            java.lang.Object r6 = r4.get(r1)
            com.umeng.commonsdk.config.e r6 = (com.umeng.commonsdk.config.e) r6
            r7 = r12[r1]
            com.umeng.commonsdk.config.b r8 = com.umeng.commonsdk.config.FieldManager.b
            r9 = r11[r1]
            java.lang.String[] r9 = com.umeng.commonsdk.config.d.b(r9)
            r6.a(r7, r8, r9)
            int r1 = r1 + 1
            goto L_0x0067
        L_0x0082:
            com.umeng.commonsdk.config.FieldManager.c = r2
            monitor-exit(r0)
            return
        L_0x0086:
            r11 = move-exception
            monitor-exit(r0)
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.config.FieldManager.a(android.content.Context, java.lang.String):void");
    }

    private static Pair<Long, String> a(String str) {
        Pair<Long, String> pair = new Pair<>(-1L, null);
        if (str != null && str.length() >= 2) {
            String[] split = str.split("@");
            if (split.length < 2) {
                return pair;
            }
            try {
                long parseLong = Long.parseLong(split[0]);
                return new Pair<>(Long.valueOf(parseLong), split[1]);
            } catch (Throwable unused) {
            }
        }
        return pair;
    }
}
