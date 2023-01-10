package com.umeng.commonsdk.internal.utils;

import java.io.InputStream;

/* compiled from: CpuUtil */
public class d {

    /* compiled from: CpuUtil */
    public static class a {
        public String a;
        public String b;
        public int c;
        public String d;
        public String e;
        public String f;
        public String g;
        public String h;
        public String i;
        public String j;
        public String k;
        public String l;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:118:? */
    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:120:? */
    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: java.io.FileReader */
    /* JADX DEBUG: Multi-variable search result rejected for r3v1, resolved type: java.io.FileReader */
    /* JADX DEBUG: Multi-variable search result rejected for r3v2, resolved type: java.io.FileReader */
    /* JADX DEBUG: Multi-variable search result rejected for r3v7, resolved type: java.io.FileReader */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x012a, code lost:
        r1 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x012b, code lost:
        r3 = null;
        r4 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:?, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0139, code lost:
        r2 = null;
        r3 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x0142, code lost:
        if (r4 == null) goto L_0x0145;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x0121, code lost:
        r0 = th;
        r3 = r3;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:102:0x012a A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x0002] */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x0130 A[SYNTHETIC, Splitter:B:106:0x0130] */
    /* JADX WARNING: Removed duplicated region for block: B:110:0x0135 A[SYNTHETIC, Splitter:B:110:0x0135] */
    /* JADX WARNING: Removed duplicated region for block: B:121:0x013f A[SYNTHETIC, Splitter:B:121:0x013f] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0121 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:7:0x0014] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.umeng.commonsdk.internal.utils.d.a a() {
        /*
        // Method dump skipped, instructions count: 328
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.utils.d.a():com.umeng.commonsdk.internal.utils.d$a");
    }

    public static String b() {
        String str = "";
        try {
            InputStream inputStream = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_max_freq").start().getInputStream();
            byte[] bArr = new byte[24];
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
        } catch (Exception unused) {
        }
        return str.trim();
    }

    public static String c() {
        String str = "";
        try {
            InputStream inputStream = new ProcessBuilder("/system/bin/cat", "/sys/devices/system/cpu/cpu0/cpufreq/cpuinfo_min_freq").start().getInputStream();
            byte[] bArr = new byte[24];
            while (inputStream.read(bArr) != -1) {
                str = str + new String(bArr);
            }
            inputStream.close();
        } catch (Exception unused) {
        }
        return str.trim();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0023 A[SYNTHETIC, Splitter:B:13:0x0023] */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0029 A[SYNTHETIC, Splitter:B:19:0x0029] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String d() {
        /*
            r0 = 0
            java.io.FileReader r1 = new java.io.FileReader     // Catch:{ Exception -> 0x0027, all -> 0x0020 }
            java.lang.String r2 = "/sys/devices/system/cpu/cpu0/cpufreq/scaling_cur_freq"
            r1.<init>(r2)     // Catch:{ Exception -> 0x0027, all -> 0x0020 }
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0027, all -> 0x0020 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x0027, all -> 0x0020 }
            java.lang.String r0 = r2.readLine()     // Catch:{ Exception -> 0x001e, all -> 0x001a }
            java.lang.String r0 = r0.trim()     // Catch:{ Exception -> 0x001e, all -> 0x001a }
            r2.close()     // Catch:{ all -> 0x002f }
            goto L_0x002f
        L_0x001a:
            r0 = move-exception
            r1 = r0
            r0 = r2
            goto L_0x0021
        L_0x001e:
            r0 = r2
            goto L_0x0027
        L_0x0020:
            r1 = move-exception
        L_0x0021:
            if (r0 == 0) goto L_0x0026
            r0.close()     // Catch:{ all -> 0x0026 }
        L_0x0026:
            throw r1
        L_0x0027:
            if (r0 == 0) goto L_0x002c
            r0.close()     // Catch:{ all -> 0x002c }
        L_0x002c:
            java.lang.String r0 = ""
        L_0x002f:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.internal.utils.d.d():java.lang.String");
    }
}
