package cn.missfresh.utils.a;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Process;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.Thread;
import java.text.SimpleDateFormat;
import java.util.Date;

/* compiled from: LogSaveUtils */
public final class c implements Thread.UncaughtExceptionHandler {
    public static String a = "MissFresh";
    private static String b = "";
    private static String c = "";
    private static String d = "";
    private static String e = "";
    private static boolean f = false;
    private static final SimpleDateFormat g = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final SimpleDateFormat h = new SimpleDateFormat("yyyyMMdd");
    private static c k;
    private Thread.UncaughtExceptionHandler i = Thread.getDefaultUncaughtExceptionHandler();
    private Context j;

    static {
        AppMethodBeat.i(14000, false);
        AppMethodBeat.o(14000);
    }

    private c(Context context, boolean z, String str) {
        AppMethodBeat.i(13964, false);
        Thread.setDefaultUncaughtExceptionHandler(this);
        this.j = context;
        f = z;
        a = str;
        String absolutePath = cn.missfresh.utils.c.a(context, (String) null).getAbsolutePath();
        b = absolutePath + File.separator + a + File.separator + "CrashLog";
        StringBuilder sb = new StringBuilder();
        sb.append(b);
        sb.append(File.separator);
        c = sb.toString();
        d = absolutePath + File.separator + a + File.separator + "PrintLog";
        StringBuilder sb2 = new StringBuilder();
        sb2.append(d);
        sb2.append(File.separator);
        e = sb2.toString();
        AppMethodBeat.o(13964);
    }

    public static void a(Context context, boolean z, String str) {
        AppMethodBeat.i(13966, false);
        if (k == null) {
            k = new c(context, z, str);
        }
        AppMethodBeat.o(13966);
    }

    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        Thread.UncaughtExceptionHandler uncaughtExceptionHandler;
        AppMethodBeat.i(13968, false);
        if (a(th) || (uncaughtExceptionHandler = this.i) == null) {
            Process.killProcess(Process.myPid());
        } else {
            uncaughtExceptionHandler.uncaughtException(thread, th);
        }
        AppMethodBeat.o(13968);
    }

    private boolean a(Throwable th) {
        AppMethodBeat.i(13972, false);
        if (th == null) {
            AppMethodBeat.o(13972);
            return false;
        }
        if (f) {
            b(th);
        }
        AppMethodBeat.o(13972);
        return true;
    }

    private void b(Throwable th) {
        AppMethodBeat.i(13975, false);
        File file = new File(b);
        if (!file.exists()) {
            file.mkdirs();
        }
        String format = g.format(new Date(System.currentTimeMillis()));
        try {
            PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(new File(c + ("Crash_" + format + ".txt")))));
            a(printWriter);
            printWriter.println(format);
            th.printStackTrace(printWriter);
            printWriter.close();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        AppMethodBeat.o(13975);
    }

    public static synchronized void a(String str, String str2, String str3) {
        synchronized (c.class) {
            AppMethodBeat.i(13978, false);
            Date date = new Date();
            String format = h.format(date);
            String str4 = g.format(date) + ":" + str + ":" + str2 + ":" + str3;
            File file = new File(d);
            if (!file.exists()) {
                file.mkdirs();
            }
            try {
                FileWriter fileWriter = new FileWriter(new File(d, format), true);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(str4);
                bufferedWriter.newLine();
                bufferedWriter.close();
                fileWriter.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            AppMethodBeat.o(13978);
        }
    }

    private void a(PrintWriter printWriter) throws PackageManager.NameNotFoundException {
        AppMethodBeat.i(13981, false);
        PackageInfo packageInfo = this.j.getPackageManager().getPackageInfo(this.j.getPackageName(), 1);
        printWriter.println("version_name=" + packageInfo.versionName);
        printWriter.println("version_code=" + packageInfo.versionCode);
        AppMethodBeat.o(13981);
    }

    public static void a() {
        File[] listFiles;
        File[] listFiles2;
        AppMethodBeat.i(13996, false);
        File file = new File(d);
        File file2 = new File(d);
        if (file.exists() && file.isDirectory() && (listFiles2 = file.listFiles()) != null && listFiles2.length > 0) {
            for (File file3 : listFiles2) {
                a(file3);
            }
        }
        if (file2.exists() && file2.isDirectory() && (listFiles = file2.listFiles()) != null && listFiles.length > 0) {
            for (File file4 : listFiles) {
                a(file4);
            }
        }
        AppMethodBeat.o(13996);
    }

    private static void a(File file) {
        AppMethodBeat.i(13998, false);
        if (System.currentTimeMillis() - file.lastModified() > DevicePolicyManager.DEFAULT_STRONG_AUTH_TIMEOUT_MS) {
            file.delete();
        }
        AppMethodBeat.o(13998);
    }
}
