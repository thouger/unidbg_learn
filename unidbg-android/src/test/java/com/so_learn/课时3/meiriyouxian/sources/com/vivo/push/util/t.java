package com.vivo.push.util;

import android.content.Context;
import android.os.Environment;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.File;
import java.io.IOException;

/* compiled from: SdcardCache */
/* access modifiers changed from: package-private */
public final class t implements c {
    private static final String a = (Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + ".vivo/pushsdk/config");
    private static final String b = (a + File.separator + "config.txt");
    private static String c = "SdcardCache";
    private File d;

    t() {
    }

    static {
        AppMethodBeat.i(1724, false);
        AppMethodBeat.o(1724);
    }

    @Override // com.vivo.push.util.c
    public final boolean a(Context context) {
        boolean z = false;
        AppMethodBeat.i(1711, false);
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File file = new File(a);
            boolean mkdirs = !file.exists() ? file.mkdirs() : true;
            if (mkdirs) {
                this.d = new File(b);
                if (!this.d.exists()) {
                    try {
                        this.d.createNewFile();
                        z = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    AppMethodBeat.o(1711);
                    return z;
                }
            }
            z = mkdirs;
            AppMethodBeat.o(1711);
            return z;
        }
        AppMethodBeat.o(1711);
        return false;
    }

    @Override // com.vivo.push.util.c
    public final String a(String str, String str2) {
        AppMethodBeat.i(1714, false);
        String property = a().getProperty(str, str2);
        AppMethodBeat.o(1714);
        return property;
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0034 A[SYNTHETIC, Splitter:B:21:0x0034] */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0042 A[SYNTHETIC, Splitter:B:29:0x0042] */
    @Override // com.vivo.push.util.c
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b(java.lang.String r5, java.lang.String r6) {
        /*
            r4 = this;
            r0 = 1719(0x6b7, float:2.409E-42)
            r1 = 0
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r1)
            java.util.Properties r1 = a()
            java.lang.String r2 = com.vivo.push.util.t.b
            r3 = 0
            r1.setProperty(r5, r6)     // Catch:{ Exception -> 0x002e }
            java.io.FileOutputStream r5 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x002e }
            r5.<init>(r2)     // Catch:{ Exception -> 0x002e }
            r1.store(r5, r3)     // Catch:{ Exception -> 0x0029, all -> 0x0026 }
            r5.flush()     // Catch:{ Exception -> 0x0029, all -> 0x0026 }
            r5.close()     // Catch:{ Exception -> 0x0022 }
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        L_0x0022:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        L_0x0026:
            r6 = move-exception
            r3 = r5
            goto L_0x0040
        L_0x0029:
            r6 = move-exception
            r3 = r5
            goto L_0x002f
        L_0x002c:
            r6 = move-exception
            goto L_0x0040
        L_0x002e:
            r6 = move-exception
        L_0x002f:
            r6.printStackTrace()     // Catch:{ all -> 0x002c }
            if (r3 == 0) goto L_0x003c
            r3.close()     // Catch:{ Exception -> 0x0038 }
            goto L_0x003c
        L_0x0038:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        L_0x003c:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        L_0x0040:
            if (r3 == 0) goto L_0x0045
            r3.close()     // Catch:{ Exception -> 0x0045 }
        L_0x0045:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.util.t.b(java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0038 A[SYNTHETIC, Splitter:B:21:0x0038] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static java.util.Properties a() {
        /*
            r0 = 1722(0x6ba, float:2.413E-42)
            r1 = 0
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r1)
            java.util.Properties r1 = new java.util.Properties
            r1.<init>()
            r2 = 0
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x0029 }
            java.io.FileInputStream r4 = new java.io.FileInputStream     // Catch:{ Exception -> 0x0029 }
            java.lang.String r5 = com.vivo.push.util.t.b     // Catch:{ Exception -> 0x0029 }
            r4.<init>(r5)     // Catch:{ Exception -> 0x0029 }
            r3.<init>(r4)     // Catch:{ Exception -> 0x0029 }
            r1.load(r3)     // Catch:{ Exception -> 0x0022, all -> 0x001f }
            r3.close()     // Catch:{ IOException -> 0x0032 }
            goto L_0x0032
        L_0x001f:
            r1 = move-exception
            r2 = r3
            goto L_0x0036
        L_0x0022:
            r2 = move-exception
            r6 = r3
            r3 = r2
            r2 = r6
            goto L_0x002a
        L_0x0027:
            r1 = move-exception
            goto L_0x0036
        L_0x0029:
            r3 = move-exception
        L_0x002a:
            r3.printStackTrace()     // Catch:{ all -> 0x0027 }
            if (r2 == 0) goto L_0x0032
            r2.close()
        L_0x0032:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return r1
        L_0x0036:
            if (r2 == 0) goto L_0x003b
            r2.close()     // Catch:{ IOException -> 0x003b }
        L_0x003b:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.util.t.a():java.util.Properties");
    }
}
