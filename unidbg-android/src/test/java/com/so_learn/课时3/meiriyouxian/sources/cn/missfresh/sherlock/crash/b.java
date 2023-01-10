package cn.missfresh.sherlock.crash;

import android.content.Context;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Process;
import android.os.storage.StorageManager;
import android.text.TextUtils;
import cn.missfresh.sherlock.config.Config;
import cn.missfresh.sherlock.config.Type;
import cn.missfresh.sherlock.d.a;
import cn.missfresh.sherlock.e;
import cn.missfresh.sherlock.to.CrashTO;
import cn.missfresh.sherlock.tool.Utils;
import cn.missfresh.sherlock.tool.c;
import cn.missfresh.sherlock.tool.j;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import com.umeng.message.proguard.l;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread;
import java.util.Date;
import java.util.regex.Pattern;
import xcrash.ICrashCallback;
import xcrash.Util;

/* compiled from: JavaCrashHandler */
/* access modifiers changed from: package-private */
public class b implements Thread.UncaughtExceptionHandler {
    private static final b a = new b();
    private Context b;
    private int c;
    private int d;
    private int e;
    private int f;
    private Thread.UncaughtExceptionHandler g = null;

    private b() {
        new Date();
    }

    static b a() {
        return a;
    }

    private String a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        try {
            String[] split = str2.split("\n");
            return a(c(str, split[0])) + a(split) + b(split);
        } catch (Exception e) {
            j.b("crash", e.getMessage());
            return null;
        }
    }

    private String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            String[] split = str.split(":");
            if (split.length > 0) {
                return split[0];
            }
        } catch (Exception unused) {
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void a(Context context, String str, String str2, String str3, int i, int i2, int i3, int i4, boolean z, int i5, String[] strArr, ICrashCallback iCrashCallback) {
        this.b = context;
        this.c = Process.myPid();
        Util.getProcessName(context, this.c);
        this.d = i2;
        this.e = i3;
        this.f = i4;
        this.g = Thread.getDefaultUncaughtExceptionHandler();
        try {
            Thread.setDefaultUncaughtExceptionHandler(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        a(thread, th);
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler = this.g;
        if (uncaughtExceptionHandler != null) {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
    }

    private String c(String str, String str2) {
        try {
            return (TextUtils.isEmpty(str) || !str.contains("OutOfMemoryError")) ? str2 : str;
        } catch (Exception e) {
            j.b("JavaCrashHandler", "getReasonSign: " + e.getMessage());
            return "";
        }
    }

    private String a(String[] strArr) {
        try {
            if (strArr.length > 3) {
                return d(strArr[1]) + d(strArr[2]) + d(strArr[3]);
            }
        } catch (Exception e) {
            j.b("JavaCrashHandler", "getSystemStack: " + e.getMessage());
        }
        return "";
    }

    private String d(String str, String str2) {
        try {
            return Utils.a(a(str, str2));
        } catch (Exception e) {
            j.b("JavaCrashHandler", "obtainExceptionMD5: " + e.getMessage());
            return null;
        }
    }

    public static String b(String str) {
        try {
            return Pattern.compile("[0-9]").matcher(str).replaceAll("").trim();
        } catch (Exception unused) {
            return str;
        }
    }

    private String c() {
        Util.MemoryInfo memoryInfo = Util.getMemoryInfo(this.b);
        try {
            return Utils.a((float) Long.valueOf(memoryInfo.systemMemoryUsedKb).longValue(), (float) Long.valueOf(memoryInfo.systemMemoryTotalKb).longValue());
        } catch (Exception unused) {
            return null;
        }
    }

    private String d(String str) {
        try {
            return b(str.split("\\(")[0]);
        } catch (Exception unused) {
            return str;
        }
    }

    private void a(Thread thread, Throwable th) {
        if (Config.getInstance().enableCrashSwitch()) {
            StringWriter stringWriter = new StringWriter();
            th.printStackTrace(new PrintWriter(stringWriter));
            String stringWriter2 = stringWriter.toString();
            CrashTO crashTO = new CrashTO();
            crashTO.setEventType(Type.CRASH.getValue());
            crashTO.setNativeCrash(0);
            crashTO.setCrashType(0);
            crashTO.setTimestamp(Long.valueOf(System.currentTimeMillis()));
            crashTO.setNetwork(a.a(this.b));
            crashTO.setNetworkOperator(a.b(this.b));
            if (TextUtils.isEmpty(e.g())) {
                crashTO.setUserId(Utils.e(this.b));
            } else {
                crashTO.setUserId(e.g());
            }
            crashTO.setMemoryRate(c());
            crashTO.setCpuRate(b());
            crashTO.setThreadName(thread.getName());
            crashTO.setProcessName(Util.getProcessName(this.b, this.c));
            crashTO.setException(c(stringWriter2));
            crashTO.setExceptionInfo(b(crashTO.getException(), stringWriter2));
            crashTO.setExceptionMD5(d(crashTO.getException(), stringWriter2));
            crashTO.setStack(stringWriter2);
            crashTO.setExtend(a(this.c));
            crashTO.setPoints(a.a().c());
            crashTO.setRegion(c.a(e.e()));
            crashTO.setVc(a.b());
            crashTO.setPhoneNumber(e.h());
            cn.missfresh.sherlock.b.a.a().a(crashTO);
        }
    }

    private String b(String str, String str2) {
        try {
            return c(str, str2.split("\n")[0]);
        } catch (Exception e) {
            j.b("JavaCrashHandler", "getReasonSign: " + e.getMessage());
            return null;
        }
    }

    private String b(String[] strArr) {
        StringBuilder sb = new StringBuilder();
        try {
            if (strArr.length < 1) {
                return null;
            }
            int i = 3;
            for (int i2 = 1; i2 < strArr.length; i2++) {
                if (i > 0 && i <= 3 && strArr[i2].contains("cn.missfresh")) {
                    sb.append(d(strArr[i2]));
                    i--;
                }
            }
            return sb.toString();
        } catch (Exception e) {
            j.b("JavaCrashHandler", "getAppStack: " + e.getMessage());
        }
    }

    public static String a(String str) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            if (!str.contains(l.s) || !str.contains(l.t)) {
                str2 = str;
            } else {
                int indexOf = str.indexOf(l.s);
                int lastIndexOf = str.lastIndexOf(l.t);
                str2 = str.substring(0, indexOf) + str.substring(lastIndexOf + 1, str.length());
            }
            if (str2.contains("{") && str2.contains("}")) {
                int indexOf2 = str2.indexOf("{");
                int lastIndexOf2 = str2.lastIndexOf("}");
                str2 = str2.substring(0, indexOf2) + str2.substring(lastIndexOf2 + 1, str2.length());
            }
            return b(str2);
        } catch (Exception unused) {
            return str;
        }
    }

    private String b() {
        try {
            String[] split = Util.readFileLine("/proc/loadavg").split(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
            if (split.length <= 3) {
                return null;
            }
            String[] split2 = split[3].split(NotificationIconUtil.SPLIT_CHAR);
            return Utils.a((float) Integer.parseInt(split2[0]), (float) Integer.parseInt(split2[1]));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String a(int i) {
        StringBuilder sb = new StringBuilder();
        LimitedQueue<String> l = e.l();
        if (l != null && !l.isEmpty()) {
            sb.append("extend info :\n");
            sb.append(String.join("", l));
        }
        sb.append("logcat :\n");
        int i2 = this.f;
        if (i2 > 0) {
            a(sb, i, "main", i2, 'D');
        }
        int i3 = this.d;
        if (i3 > 0) {
            a(sb, i, StorageManager.UUID_SYSTEM, i3, 'W');
        }
        if (this.e > 0) {
            a(sb, i, com.umeng.analytics.pro.c.ar, this.d, 'I');
        }
        sb.append("\n");
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ec A[SYNTHETIC, Splitter:B:36:0x00ec] */
    /* JADX WARNING: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.StringBuilder r10, int r11, java.lang.String r12, int r13, char r14) {
        /*
            r9 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 0
            r2 = 24
            if (r0 < r2) goto L_0x0009
            r0 = 1
            goto L_0x000a
        L_0x0009:
            r0 = r1
        L_0x000a:
            java.lang.String r11 = java.lang.Integer.toString(r11)
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = " "
            r2.append(r3)
            r2.append(r11)
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            java.lang.String r5 = "/system/bin/logcat"
            r4.add(r5)
            java.lang.String r5 = "-b"
            r4.add(r5)
            r4.add(r12)
            java.lang.String r5 = "-d"
            r4.add(r5)
            java.lang.String r5 = "-v"
            r4.add(r5)
            java.lang.String r5 = "threadtime"
            r4.add(r5)
            java.lang.String r5 = "-t"
            r4.add(r5)
            if (r0 == 0) goto L_0x0052
            goto L_0x005a
        L_0x0052:
            double r5 = (double) r13
            r7 = 4608083138725491507(0x3ff3333333333333, double:1.2)
            double r5 = r5 * r7
            int r13 = (int) r5
        L_0x005a:
            java.lang.String r13 = java.lang.Integer.toString(r13)
            r4.add(r13)
            if (r0 == 0) goto L_0x006c
            java.lang.String r13 = "--pid"
            r4.add(r13)
            r4.add(r11)
        L_0x006c:
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r13 = "*:"
            r11.append(r13)
            r11.append(r14)
            java.lang.String r11 = r11.toString()
            r4.add(r11)
            java.lang.Object[] r11 = r4.toArray()
            if (r11 == 0) goto L_0x00a3
            java.lang.String r13 = "--------- tail end of log "
            r10.append(r13)
            r10.append(r12)
            java.lang.String r12 = " ("
            r10.append(r12)
            java.lang.String r11 = android.text.TextUtils.join(r3, r11)
            r10.append(r11)
            java.lang.String r11 = ")\n"
            r10.append(r11)
        L_0x00a3:
            r11 = 0
            java.lang.ProcessBuilder r12 = new java.lang.ProcessBuilder     // Catch:{ Exception -> 0x00dd, all -> 0x00db }
            java.lang.String[] r13 = new java.lang.String[r1]     // Catch:{ Exception -> 0x00dd, all -> 0x00db }
            r12.<init>(r13)     // Catch:{ Exception -> 0x00dd, all -> 0x00db }
            java.lang.ProcessBuilder r12 = r12.command(r4)     // Catch:{ Exception -> 0x00dd, all -> 0x00db }
            java.lang.Process r12 = r12.start()     // Catch:{ Exception -> 0x00dd, all -> 0x00db }
            java.io.BufferedReader r13 = new java.io.BufferedReader     // Catch:{ Exception -> 0x00dd, all -> 0x00db }
            java.io.InputStreamReader r14 = new java.io.InputStreamReader     // Catch:{ Exception -> 0x00dd, all -> 0x00db }
            java.io.InputStream r12 = r12.getInputStream()     // Catch:{ Exception -> 0x00dd, all -> 0x00db }
            r14.<init>(r12)     // Catch:{ Exception -> 0x00dd, all -> 0x00db }
            r13.<init>(r14)     // Catch:{ Exception -> 0x00dd, all -> 0x00db }
        L_0x00c1:
            java.lang.String r11 = r13.readLine()     // Catch:{ Exception -> 0x00d9 }
            if (r11 == 0) goto L_0x00e4
            if (r0 != 0) goto L_0x00cf
            boolean r12 = r11.contains(r2)     // Catch:{ Exception -> 0x00d9 }
            if (r12 == 0) goto L_0x00c1
        L_0x00cf:
            r10.append(r11)     // Catch:{ Exception -> 0x00d9 }
            java.lang.String r11 = "\n"
            r10.append(r11)     // Catch:{ Exception -> 0x00d9 }
            goto L_0x00c1
        L_0x00d9:
            r10 = move-exception
            goto L_0x00df
        L_0x00db:
            r10 = move-exception
            goto L_0x00ea
        L_0x00dd:
            r10 = move-exception
            r13 = r11
        L_0x00df:
            r10.printStackTrace()     // Catch:{ all -> 0x00e8 }
            if (r13 == 0) goto L_0x00e7
        L_0x00e4:
            r13.close()     // Catch:{ IOException -> 0x00e7 }
        L_0x00e7:
            return
        L_0x00e8:
            r10 = move-exception
            r11 = r13
        L_0x00ea:
            if (r11 == 0) goto L_0x00ef
            r11.close()     // Catch:{ IOException -> 0x00ef }
        L_0x00ef:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.sherlock.crash.b.a(java.lang.StringBuilder, int, java.lang.String, int, char):void");
    }
}
