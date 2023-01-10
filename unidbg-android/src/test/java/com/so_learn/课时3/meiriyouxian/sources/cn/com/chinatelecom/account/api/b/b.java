package cn.com.chinatelecom.account.api.b;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.content.Context;
import android.os.Process;
import android.text.TextUtils;
import cn.com.chinatelecom.account.api.c.d;
import cn.com.chinatelecom.account.api.c.e;
import cn.com.chinatelecom.account.api.c.f;
import cn.com.chinatelecom.account.api.c.g;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.taobao.accs.utl.BaseMonitor;
import java.net.InetAddress;

public class b {
    private static final String a = b.class.getSimpleName();
    private static String b = null;
    private static long c = 0;
    private static long d = AlarmManager.INTERVAL_HALF_HOUR;

    /* access modifiers changed from: package-private */
    /* renamed from: cn.com.chinatelecom.account.api.b.b$1  reason: invalid class name */
    public static class AnonymousClass1 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass1(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            e a;
            String str;
            int i = 7904;
            AppMethodBeat.i(7904, false);
            try {
                String a2 = d.a();
                f.a(a2).a(d.a(this.a)).c(BaseMonitor.COUNT_POINT_DNS).b(g.f(this.a));
                String a3 = g.a();
                String a4 = b.a(a3, a2, 0);
                if (TextUtils.isEmpty(a4)) {
                    a4 = b.a(a3, a2, 1);
                }
                synchronized (b.class) {
                    try {
                        if (!TextUtils.isEmpty(a4)) {
                            String unused = b.b = a4;
                            long unused2 = b.c = System.currentTimeMillis() + b.d;
                            a = f.a(a2).a(0);
                            str = "success";
                        } else {
                            a = f.a(a2).a(80011);
                            str = "\u524d\u7f6e\u57df\u540d\u89e3\u6790\u5931\u8d25";
                        }
                        a.e(str);
                    } finally {
                        AppMethodBeat.o(i);
                    }
                }
                f.b(a2);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    static {
        AppMethodBeat.i(7928, false);
        AppMethodBeat.o(7928);
    }

    public static synchronized String a() {
        synchronized (b.class) {
            AppMethodBeat.i(7912, false);
            if (System.currentTimeMillis() >= c || !d.a(b)) {
                AppMethodBeat.o(7912);
                return null;
            }
            String str = b;
            AppMethodBeat.o(7912);
            return str;
        }
    }

    static /* synthetic */ String a(String str, String str2, int i) {
        AppMethodBeat.i(7923, false);
        String b2 = b(str, str2, i);
        AppMethodBeat.o(7923);
        return b2;
    }

    public static void a(Context context) {
        AppMethodBeat.i(7910, false);
        if (!b(context) || b != null) {
            AppMethodBeat.o(7910);
            return;
        }
        g.a(new AnonymousClass1(context));
        AppMethodBeat.o(7910);
    }

    private static String b(String str, String str2, int i) {
        StringBuilder sb;
        String str3;
        AppMethodBeat.i(7916, false);
        try {
            f.a(str2).b(i);
            String hostAddress = InetAddress.getByName(str).getHostAddress();
            AppMethodBeat.o(7916);
            return hostAddress;
        } catch (Exception e) {
            if (i == 0) {
                sb = new StringBuilder();
                str3 = "first exception: ";
            } else {
                sb = new StringBuilder();
                str3 = "retry exception: ";
            }
            sb.append(str3);
            sb.append(e.getMessage());
            f.a(str2).g(sb.toString());
            AppMethodBeat.o(7916);
            return null;
        }
    }

    public static boolean b(Context context) {
        AppMethodBeat.i(7921, false);
        try {
            boolean equals = context.getPackageName().equals(c(context));
            AppMethodBeat.o(7921);
            return equals;
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(7921);
            return true;
        }
    }

    private static String c(Context context) {
        AppMethodBeat.i(7918, false);
        int myPid = Process.myPid();
        String str = "";
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : ((ActivityManager) context.getApplicationContext().getSystemService("activity")).getRunningAppProcesses()) {
            if (runningAppProcessInfo.pid == myPid) {
                str = runningAppProcessInfo.processName;
            }
        }
        AppMethodBeat.o(7918);
        return str;
    }
}
