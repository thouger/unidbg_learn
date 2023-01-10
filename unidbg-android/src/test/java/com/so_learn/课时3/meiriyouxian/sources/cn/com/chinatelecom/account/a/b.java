package cn.com.chinatelecom.account.a;

import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.File;

public class b {
    /* JADX WARNING: Removed duplicated region for block: B:37:0x005f A[SYNTHETIC, Splitter:B:37:0x005f] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0069 A[SYNTHETIC, Splitter:B:42:0x0069] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0073  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String a(android.content.Context r6) {
        /*
            r0 = 8394(0x20ca, float:1.1762E-41)
            r1 = 0
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r1)
            java.io.File r6 = c(r6)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            if (r6 == 0) goto L_0x00a6
            boolean r2 = r6.exists()
            if (r2 != 0) goto L_0x0019
            goto L_0x00a6
        L_0x0019:
            r2 = 0
            java.io.FileInputStream r3 = new java.io.FileInputStream     // Catch:{ all -> 0x0057 }
            r3.<init>(r6)     // Catch:{ all -> 0x0057 }
            java.io.InputStreamReader r6 = new java.io.InputStreamReader     // Catch:{ all -> 0x0053 }
            r6.<init>(r3)     // Catch:{ all -> 0x0053 }
            java.io.BufferedReader r4 = new java.io.BufferedReader     // Catch:{ all -> 0x004d }
            r4.<init>(r6)     // Catch:{ all -> 0x004d }
        L_0x0029:
            java.lang.String r2 = r4.readLine()     // Catch:{ all -> 0x0047 }
            if (r2 == 0) goto L_0x0033
            r1.append(r2)     // Catch:{ all -> 0x0047 }
            goto L_0x0029
        L_0x0033:
            r4.close()     // Catch:{ Exception -> 0x0037 }
            goto L_0x003b
        L_0x0037:
            r2 = move-exception
            r2.printStackTrace()
        L_0x003b:
            r6.close()     // Catch:{ Exception -> 0x003f }
            goto L_0x0043
        L_0x003f:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0043:
            r3.close()     // Catch:{ Exception -> 0x0077 }
            goto L_0x007b
        L_0x0047:
            r2 = move-exception
            r5 = r3
            r3 = r6
            r6 = r2
            r2 = r4
            goto L_0x0051
        L_0x004d:
            r4 = move-exception
            r5 = r3
            r3 = r6
            r6 = r4
        L_0x0051:
            r4 = r5
            goto L_0x005a
        L_0x0053:
            r6 = move-exception
            r4 = r3
            r3 = r2
            goto L_0x005a
        L_0x0057:
            r6 = move-exception
            r3 = r2
            r4 = r3
        L_0x005a:
            r6.printStackTrace()     // Catch:{ all -> 0x0083 }
            if (r2 == 0) goto L_0x0067
            r2.close()     // Catch:{ Exception -> 0x0063 }
            goto L_0x0067
        L_0x0063:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0067:
            if (r3 == 0) goto L_0x0071
            r3.close()     // Catch:{ Exception -> 0x006d }
            goto L_0x0071
        L_0x006d:
            r6 = move-exception
            r6.printStackTrace()
        L_0x0071:
            if (r4 == 0) goto L_0x007b
            r4.close()
            goto L_0x007b
        L_0x0077:
            r6 = move-exception
            r6.printStackTrace()
        L_0x007b:
            java.lang.String r6 = r1.toString()
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return r6
        L_0x0083:
            r6 = move-exception
            if (r2 == 0) goto L_0x008e
            r2.close()     // Catch:{ Exception -> 0x008a }
            goto L_0x008e
        L_0x008a:
            r1 = move-exception
            r1.printStackTrace()
        L_0x008e:
            if (r3 == 0) goto L_0x0098
            r3.close()     // Catch:{ Exception -> 0x0094 }
            goto L_0x0098
        L_0x0094:
            r1 = move-exception
            r1.printStackTrace()
        L_0x0098:
            if (r4 == 0) goto L_0x00a2
            r4.close()     // Catch:{ Exception -> 0x009e }
            goto L_0x00a2
        L_0x009e:
            r1 = move-exception
            r1.printStackTrace()
        L_0x00a2:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            throw r6
        L_0x00a6:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            java.lang.String r6 = ""
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.chinatelecom.account.a.b.a(android.content.Context):java.lang.String");
    }

    public static void a(Context context, String str) {
        AppMethodBeat.i(8391, false);
        File c = c(context);
        if (c == null || !c.exists()) {
            a(b(context), str);
        } else {
            a(c, str);
        }
        AppMethodBeat.o(8391);
    }

    /* JADX WARNING: Removed duplicated region for block: B:31:0x0046 A[SYNTHETIC, Splitter:B:31:0x0046] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x005d A[SYNTHETIC, Splitter:B:42:0x005d] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0067 A[SYNTHETIC, Splitter:B:47:0x0067] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(java.io.File r4, java.lang.String r5) {
        /*
            r0 = 0
            r1 = 8389(0x20c5, float:1.1755E-41)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r0)
            if (r4 == 0) goto L_0x0073
            boolean r2 = r4.exists()
            if (r2 == 0) goto L_0x0073
            r2 = 0
            java.io.FileWriter r3 = new java.io.FileWriter     // Catch:{ Exception -> 0x003f, all -> 0x003b }
            r3.<init>(r4, r0)     // Catch:{ Exception -> 0x003f, all -> 0x003b }
            java.io.BufferedWriter r4 = new java.io.BufferedWriter     // Catch:{ Exception -> 0x0039 }
            r4.<init>(r3)     // Catch:{ Exception -> 0x0039 }
            boolean r0 = android.text.TextUtils.isEmpty(r5)     // Catch:{ Exception -> 0x0036, all -> 0x0034 }
            if (r0 == 0) goto L_0x0022
            java.lang.String r5 = ""
        L_0x0022:
            r4.write(r5)     // Catch:{ Exception -> 0x0036, all -> 0x0034 }
            r4.flush()     // Catch:{ Exception -> 0x0036, all -> 0x0034 }
            r4.close()     // Catch:{ Exception -> 0x002c }
            goto L_0x0030
        L_0x002c:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0030:
            r3.close()     // Catch:{ Exception -> 0x0054 }
            goto L_0x0073
        L_0x0034:
            r5 = move-exception
            goto L_0x005b
        L_0x0036:
            r5 = move-exception
            r2 = r4
            goto L_0x0041
        L_0x0039:
            r5 = move-exception
            goto L_0x0041
        L_0x003b:
            r5 = move-exception
            r4 = r2
            r3 = r4
            goto L_0x005b
        L_0x003f:
            r5 = move-exception
            r3 = r2
        L_0x0041:
            r5.printStackTrace()     // Catch:{ all -> 0x0059 }
            if (r2 == 0) goto L_0x004e
            r2.close()     // Catch:{ Exception -> 0x004a }
            goto L_0x004e
        L_0x004a:
            r4 = move-exception
            r4.printStackTrace()
        L_0x004e:
            if (r3 == 0) goto L_0x0073
            r3.close()
            goto L_0x0073
        L_0x0054:
            r4 = move-exception
            r4.printStackTrace()
            goto L_0x0073
        L_0x0059:
            r5 = move-exception
            r4 = r2
        L_0x005b:
            if (r4 == 0) goto L_0x0065
            r4.close()     // Catch:{ Exception -> 0x0061 }
            goto L_0x0065
        L_0x0061:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0065:
            if (r3 == 0) goto L_0x006f
            r3.close()     // Catch:{ Exception -> 0x006b }
            goto L_0x006f
        L_0x006b:
            r4 = move-exception
            r4.printStackTrace()
        L_0x006f:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            throw r5
        L_0x0073:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.chinatelecom.account.a.b.a(java.io.File, java.lang.String):void");
    }

    private static File b(Context context) {
        AppMethodBeat.i(8384, false);
        if (context != null) {
            try {
                File file = new File(context.getFilesDir() + "/eAccount/Log/");
                if (!file.exists()) {
                    file.mkdirs();
                }
                File file2 = new File(file, "ipa_ol.ds");
                if (file2.exists()) {
                    file2.delete();
                }
                file2.createNewFile();
                AppMethodBeat.o(8384);
                return file2;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        AppMethodBeat.o(8384);
        return null;
    }

    private static File c(Context context) {
        AppMethodBeat.i(8387, false);
        if (context != null) {
            try {
                File file = new File(context.getFilesDir() + "/eAccount/Log/");
                if (!file.exists()) {
                    AppMethodBeat.o(8387);
                    return null;
                }
                File file2 = new File(file, "ipa_ol.ds");
                boolean exists = file2.exists();
                AppMethodBeat.o(8387);
                if (!exists) {
                    return null;
                }
                return file2;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        AppMethodBeat.o(8387);
        return null;
    }
}
