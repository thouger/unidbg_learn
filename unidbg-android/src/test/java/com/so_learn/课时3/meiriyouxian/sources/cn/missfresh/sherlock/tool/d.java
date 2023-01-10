package cn.missfresh.sherlock.tool;

import android.os.Debug;
import android.os.Process;
import java.io.File;
import java.io.FileFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: DeviceUtil */
public class d {

    /* compiled from: DeviceUtil */
    static class a implements FileFilter {
        a() {
        }

        @Override // java.io.FileFilter
        public boolean accept(File file) {
            return Pattern.matches("cpu[0-9]", file.getName());
        }
    }

    static {
        new a();
    }

    public static long a() {
        Runtime runtime = Runtime.getRuntime();
        return (runtime.totalMemory() - runtime.freeMemory()) / 1024;
    }

    public static long b() {
        return Debug.getNativeHeapAllocatedSize() / 1024;
    }

    public static long c() {
        try {
            String[] split = a(String.format("/proc/%s/status", Integer.valueOf(d()))).trim().split("\n");
            for (String str : split) {
                if (str.startsWith("VmSize")) {
                    Matcher matcher = Pattern.compile("\\d+").matcher(str);
                    if (matcher.find()) {
                        return Long.parseLong(matcher.group());
                    }
                }
            }
            if (split.length > 12) {
                Matcher matcher2 = Pattern.compile("\\d+").matcher(split[12]);
                if (matcher2.find()) {
                    return Long.parseLong(matcher2.group());
                }
            }
        } catch (Exception unused) {
        }
        return -1;
    }

    private static int d() {
        return Process.myPid();
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static java.lang.String a(java.io.InputStream r3) {
        /*
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ all -> 0x0028 }
            java.io.InputStreamReader r2 = new java.io.InputStreamReader     // Catch:{ all -> 0x0028 }
            r2.<init>(r3)     // Catch:{ all -> 0x0028 }
            r1.<init>(r2)     // Catch:{ all -> 0x0028 }
        L_0x000f:
            java.lang.String r3 = r1.readLine()     // Catch:{ all -> 0x0026 }
            if (r3 == 0) goto L_0x001e
            r0.append(r3)     // Catch:{ all -> 0x0026 }
            r3 = 10
            r0.append(r3)     // Catch:{ all -> 0x0026 }
            goto L_0x000f
        L_0x001e:
            r1.close()
            java.lang.String r3 = r0.toString()
            return r3
        L_0x0026:
            r3 = move-exception
            goto L_0x002a
        L_0x0028:
            r3 = move-exception
            r1 = 0
        L_0x002a:
            if (r1 == 0) goto L_0x002f
            r1.close()
        L_0x002f:
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.sherlock.tool.d.a(java.io.InputStream):java.lang.String");
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0019  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected static java.lang.String a(java.lang.String r1) {
        /*
            java.io.File r0 = new java.io.File
            r0.<init>(r1)
            java.io.FileInputStream r1 = new java.io.FileInputStream     // Catch:{ all -> 0x0014 }
            r1.<init>(r0)     // Catch:{ all -> 0x0014 }
            java.lang.String r0 = a(r1)     // Catch:{ all -> 0x0012 }
            r1.close()
            return r0
        L_0x0012:
            r0 = move-exception
            goto L_0x0017
        L_0x0014:
            r1 = move-exception
            r0 = r1
            r1 = 0
        L_0x0017:
            if (r1 == 0) goto L_0x001c
            r1.close()
        L_0x001c:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.sherlock.tool.d.a(java.lang.String):java.lang.String");
    }
}
