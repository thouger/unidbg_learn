package com.sina.weibo.sdk.statistic;

import android.os.Environment;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import com.sina.weibo.sdk.a.e;
import java.io.File;

/* compiled from: LogFileUtil */
class c {
    public static String a(String str) {
        String str2;
        if (d.a() != null) {
            str2 = String.valueOf(e.a(d.a())) + NotificationIconUtil.SPLIT_CHAR;
        } else {
            str2 = "";
        }
        return String.valueOf(a()) + "/sina/weibo/.applogs/" + str2 + str + ".txt";
    }

    private static String a() {
        File externalStorageDirectory = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ? Environment.getExternalStorageDirectory() : null;
        if (externalStorageDirectory != null) {
            return externalStorageDirectory.toString();
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00b4 A[SYNTHETIC, Splitter:B:44:0x00b4] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00bf A[SYNTHETIC, Splitter:B:51:0x00bf] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void a(java.lang.String r7, java.lang.String r8, boolean r9) {
        /*
            java.lang.Class<com.sina.weibo.sdk.statistic.c> r0 = com.sina.weibo.sdk.statistic.c.class
            monitor-enter(r0)
            boolean r1 = android.text.TextUtils.isEmpty(r7)     // Catch:{ all -> 0x00c9 }
            if (r1 == 0) goto L_0x000b
            monitor-exit(r0)
            return
        L_0x000b:
            java.lang.String r1 = "WBAgent"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "filePath:"
            r2.<init>(r3)
            r2.append(r7)
            java.lang.String r2 = r2.toString()
            com.sina.weibo.sdk.a.d.b(r1, r2)
            if (r8 == 0) goto L_0x00c7
            int r1 = r8.length()
            if (r1 != 0) goto L_0x002a
            goto L_0x00c7
        L_0x002a:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r8)
            r8 = 0
            char r2 = r1.charAt(r8)
            r3 = 91
            r4 = 1
            if (r2 != r3) goto L_0x003f
            java.lang.String r2 = ""
            r1.replace(r8, r4, r2)
        L_0x003f:
            int r2 = r1.length()
            int r2 = r2 - r4
            char r2 = r1.charAt(r2)
            r3 = 44
            if (r2 == r3) goto L_0x005b
            int r2 = r1.length()
            int r2 = r2 - r4
            int r3 = r1.length()
            java.lang.String r4 = ","
            r1.replace(r2, r3, r4)
        L_0x005b:
            java.io.File r2 = new java.io.File
            r2.<init>(r7)
            r7 = 0
            java.io.File r3 = r2.getParentFile()     // Catch:{ IOException -> 0x00bd, all -> 0x00b1 }
            boolean r4 = r3.exists()     // Catch:{ IOException -> 0x00bd, all -> 0x00b1 }
            if (r4 != 0) goto L_0x006e
            r3.mkdirs()     // Catch:{ IOException -> 0x00bd, all -> 0x00b1 }
        L_0x006e:
            boolean r3 = r2.exists()     // Catch:{ IOException -> 0x00bd, all -> 0x00b1 }
            if (r3 != 0) goto L_0x0078
            r2.createNewFile()     // Catch:{ IOException -> 0x00bd, all -> 0x00b1 }
            goto L_0x0093
        L_0x0078:
            long r3 = r2.lastModified()     // Catch:{ IOException -> 0x00bd, all -> 0x00b1 }
            r5 = 0
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 <= 0) goto L_0x0093
            long r3 = java.lang.System.currentTimeMillis()     // Catch:{ IOException -> 0x00bd, all -> 0x00b1 }
            long r5 = r2.lastModified()     // Catch:{ IOException -> 0x00bd, all -> 0x00b1 }
            long r3 = r3 - r5
            r5 = 86400000(0x5265c00, double:4.2687272E-316)
            int r3 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r3 <= 0) goto L_0x0093
            goto L_0x0094
        L_0x0093:
            r8 = r9
        L_0x0094:
            java.io.FileWriter r9 = new java.io.FileWriter     // Catch:{ IOException -> 0x00bd, all -> 0x00b1 }
            r9.<init>(r2, r8)     // Catch:{ IOException -> 0x00bd, all -> 0x00b1 }
            java.lang.String r7 = r1.toString()     // Catch:{ IOException -> 0x00af, all -> 0x00ac }
            r9.write(r7)     // Catch:{ IOException -> 0x00af, all -> 0x00ac }
            r9.flush()     // Catch:{ IOException -> 0x00af, all -> 0x00ac }
            r9.close()     // Catch:{ IOException -> 0x00a7 }
            goto L_0x00c5
        L_0x00a7:
            r7 = move-exception
        L_0x00a8:
            r7.printStackTrace()
            goto L_0x00c5
        L_0x00ac:
            r8 = move-exception
            r7 = r9
            goto L_0x00b2
        L_0x00af:
            r7 = r9
            goto L_0x00bd
        L_0x00b1:
            r8 = move-exception
        L_0x00b2:
            if (r7 == 0) goto L_0x00bc
            r7.close()     // Catch:{ IOException -> 0x00b8 }
            goto L_0x00bc
        L_0x00b8:
            r7 = move-exception
            r7.printStackTrace()
        L_0x00bc:
            throw r8
        L_0x00bd:
            if (r7 == 0) goto L_0x00c5
            r7.close()     // Catch:{ IOException -> 0x00c3 }
            goto L_0x00c5
        L_0x00c3:
            r7 = move-exception
            goto L_0x00a8
        L_0x00c5:
            monitor-exit(r0)
            return
        L_0x00c7:
            monitor-exit(r0)
            return
        L_0x00c9:
            r7 = move-exception
            monitor-exit(r0)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.statistic.c.a(java.lang.String, java.lang.String, boolean):void");
    }
}
