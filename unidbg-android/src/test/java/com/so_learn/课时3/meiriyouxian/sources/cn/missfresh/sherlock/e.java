package cn.missfresh.sherlock;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import cn.missfresh.sherlock.config.Config;
import cn.missfresh.sherlock.config.Type;
import cn.missfresh.sherlock.crash.LimitedQueue;
import cn.missfresh.sherlock.e.c;
import cn.missfresh.sherlock.to.BusinessBO;
import cn.missfresh.sherlock.to.CommonTO;
import cn.missfresh.sherlock.to.CrashTO;
import cn.missfresh.sherlock.to.NetworkTO;
import cn.missfresh.sherlock.to.PerformLogTO;
import cn.missfresh.sherlock.tool.Utils;
import cn.missfresh.sherlock.tool.j;
import cn.missfresh.sherlock.tool.k;
import cn.missfresh.sherlock.trace.a.a;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: SherlockHelper */
public class e {
    private static Application a;
    private static String b;
    private static String c;
    private static String d;
    private static String e;
    private static List<String> f;
    private static String g;
    private static d h;
    private static List<SherlockViewClickListener> i = new ArrayList();
    private static boolean j;
    private static LimitedQueue<String> k = new LimitedQueue<>(20);
    private static AtomicInteger l = new AtomicInteger();
    private static Handler m = new a(Looper.getMainLooper());

    /* compiled from: SherlockHelper */
    static class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message != null && message.what == 17) {
                e.v();
            }
        }
    }

    /* compiled from: SherlockHelper */
    static class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            e.s();
            a.a(e.b);
        }
    }

    private static synchronized void A() {
        synchronized (e.class) {
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(a);
            int i2 = defaultSharedPreferences.getInt("serial_num", 0);
            String string = defaultSharedPreferences.getString("serial_time", null);
            String a2 = k.a();
            if (i2 != 0) {
                if (TextUtils.equals(string, a2)) {
                    l.set(i2);
                }
            }
            l.set(0);
            defaultSharedPreferences.edit().putString("serial_time", a2).commit();
        }
    }

    public static synchronized void a() {
        synchronized (e.class) {
            try {
                if (!Config.getInstance().enableSherlock()) {
                    j.b("SherlockHelper", "sherlock disable");
                    return;
                }
                if (Config.getInstance().enableTraceSwitch()) {
                    j.b("SherlockHelper", "trace function able");
                    cn.missfresh.sherlock.trace.b.i().b();
                }
                A();
                y();
                if (Config.getInstance().enableHttpSwitch()) {
                    x();
                }
                if (Config.getInstance().enableCrashSwitch()) {
                    w();
                }
                z();
                b(Config.getInstance().mLogSwitch);
                if (Config.getInstance().enableScreenSwitch()) {
                    Message obtain = Message.obtain();
                    obtain.what = 17;
                    i().sendMessage(obtain);
                }
                j.b("SherlockHelper", "init sherlock success");
                return;
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public static void a(String str) {
        c = str;
    }

    public static void b() {
        boolean enableSherlock = Config.getInstance().enableSherlock();
        j.b("SherlockHelper", "on trim memory, enable : " + enableSherlock);
        if (enableSherlock) {
            c.a().b();
            cn.missfresh.sherlock.d.e.a().a(true);
        }
        if (Config.getInstance().enableScreenSwitch()) {
            c.a().d();
        }
    }

    public static boolean d() {
        return j;
    }

    public static Application e() {
        return a;
    }

    public static void e(String str) {
        g = str;
    }

    public static String f() {
        return b;
    }

    public static String g() {
        return c;
    }

    public static String h() {
        return d;
    }

    public static Handler i() {
        return m;
    }

    public static List<String> j() {
        return f;
    }

    public static String k() {
        return e;
    }

    public static LimitedQueue<String> l() {
        return k;
    }

    public static d m() {
        return h;
    }

    public static List<SherlockViewClickListener> n() {
        return i;
    }

    public static AtomicInteger o() {
        return l;
    }

    /* access modifiers changed from: private */
    public static void s() {
        try {
            j.b("SherlockHelper", "init trace");
            cn.missfresh.sherlock.trace.b.a(new a.b().a(true).b(true).c(true).d(true).a(g).e(false).f(false).a(), a);
            cn.missfresh.sherlock.trace.b.i().j();
        } catch (Exception e2) {
            j.b("SherlockHelper", "init trace exception message :" + e2.getMessage());
        }
    }

    private static void u() {
        cn.missfresh.sherlock.e.a.a().b();
    }

    /* access modifiers changed from: private */
    public static void v() {
        c.a().b();
    }

    private static void w() {
        cn.missfresh.sherlock.crash.a.a().b();
    }

    private static void x() {
        cn.missfresh.sherlock.okhttp.a.a().b();
    }

    private static void y() {
        cn.missfresh.sherlock.b.a.a().b();
    }

    private static void z() {
        cn.missfresh.sherlock.d.e.a().b();
    }

    public static void a(Application application, String str, String str2, String str3) {
        if (application == null || TextUtils.isEmpty(str)) {
            throw new IllegalStateException("sherlock init failure, ctx or appkey is null");
        }
        j.b("SherlockHelper", "init sherlock");
        a = application;
        b = str;
        c = str2;
        d = str3;
        cn.missfresh.sherlock.trace.b.a.a();
        u();
        c.a().b(new b());
    }

    public static void b(String str) {
        d = str;
    }

    public static synchronized void c() {
        synchronized (e.class) {
            t();
            boolean enableSherlock = Config.getInstance().enableSherlock();
            j.b("SherlockHelper", "clear, enable : " + enableSherlock);
            if (enableSherlock) {
                c.a().c();
            }
        }
    }

    public static void c(String str) {
        e = str;
    }

    private static void t() {
        try {
            cn.missfresh.sherlock.trace.b.i().c();
        } catch (Exception e2) {
            j.b("SherlockHelper", "close trace exception message :" + e2.getMessage());
        }
    }

    public static void a(boolean z) {
        j = z;
    }

    public static void d(String str) {
        if (!TextUtils.isEmpty(str)) {
            k.add(str);
        }
    }

    public static void a(int i2) {
        cn.missfresh.sherlock.okhttp.a.a().a(i2);
    }

    public static void a(NetworkTO networkTO) {
        cn.missfresh.sherlock.okhttp.a.a().a(networkTO);
    }

    public static void a(Map<String, Object> map, int i2) {
        cn.missfresh.sherlock.okhttp.a.a().a(map, i2);
    }

    public static void a(double d2, String str) {
        cn.missfresh.sherlock.c.a.a().a(d2, str);
    }

    public static void a(String str, String str2, String str3) {
        if (Config.getInstance().sherlockSwitch != 1) {
            j.b("SherlockHelper", "sherlock disable by report business");
        } else if (TextUtils.isEmpty(str)) {
            j.b("SherlockHelper", "report business name empty");
        } else {
            try {
                BusinessBO businessBO = new BusinessBO();
                businessBO.setName(str);
                businessBO.setType(str2);
                businessBO.setContent(str3);
                businessBO.setContentMd5(Utils.a(str + str2 + str3));
                businessBO.setEventType(Type.BUSINESS.getValue());
                businessBO.setTimestamp(Long.valueOf(System.currentTimeMillis()));
                businessBO.setNetwork(cn.missfresh.sherlock.d.a.a(a));
                businessBO.setNetworkOperator(cn.missfresh.sherlock.d.a.b(a));
                if (TextUtils.isEmpty(g())) {
                    businessBO.setUserId(Utils.e(a));
                } else {
                    businessBO.setUserId(g());
                }
                businessBO.setRegion(cn.missfresh.sherlock.tool.c.a(a));
                businessBO.setVc(cn.missfresh.sherlock.d.a.b());
                businessBO.setPhoneNumber(h());
                c.a().a((CommonTO) businessBO);
            } catch (Exception unused) {
            }
        }
    }

    public static void a(List<String> list) {
        if (!Utils.a(list)) {
            f = new ArrayList();
            f.addAll(list);
        }
    }

    public static void a(d dVar) {
        h = dVar;
    }

    public static void a(SherlockViewClickListener sherlockViewClickListener) {
        i.add(sherlockViewClickListener);
    }

    public static void a(String str, long j2) {
        a(str, cn.missfresh.sherlock.d.a.b(), j2, 0, false);
    }

    public static void a(String str, String str2, long j2, long j3, boolean z) {
        if (Config.getInstance().resouceSwitch != 1) {
            j.b("SherlockHelper", "sherlock disable by report log");
        } else if (j2 <= 9999) {
            PerformLogTO performLogTO = new PerformLogTO();
            performLogTO.setEventType(Type.LOG.getValue());
            performLogTO.setTimestamp(Long.valueOf(System.currentTimeMillis()));
            performLogTO.setNetwork(cn.missfresh.sherlock.d.a.a(a));
            performLogTO.setNetworkOperator(cn.missfresh.sherlock.d.a.b(a));
            if (TextUtils.isEmpty(g())) {
                performLogTO.setUserId(Utils.e(a));
            } else {
                performLogTO.setUserId(g());
            }
            performLogTO.setRegion(cn.missfresh.sherlock.tool.c.a(a));
            performLogTO.setVc(str2);
            performLogTO.setPhoneNumber(h());
            performLogTO.setPageType(str);
            performLogTO.setCostedMillis(Long.valueOf(j2));
            performLogTO.setIsFlutter(z ? 1 : 0);
            j.b("SherlockHelper", "send perform log");
            c.a().a((CommonTO) performLogTO);
        }
    }

    public static void a(String str, CrashTO crashTO) {
        if (!Config.getInstance().enableSherlock() || !Config.getInstance().enableCrashSwitch()) {
            j.b("SherlockHelper", "sherlock disable by report flutter crash");
        } else if (crashTO != null) {
            crashTO.setNativeCrash(0);
            crashTO.setEventType(Type.CRASH.getValue());
            crashTO.setCrashType(2);
            crashTO.setTimestamp(Long.valueOf(System.currentTimeMillis()));
            crashTO.setNetwork(cn.missfresh.sherlock.d.a.a(a));
            crashTO.setNetworkOperator(cn.missfresh.sherlock.d.a.b(a));
            if (TextUtils.isEmpty(g())) {
                crashTO.setUserId(Utils.e(a));
            } else {
                crashTO.setUserId(g());
            }
            if (!TextUtils.isEmpty(crashTO.getExceptionInfo())) {
                if (TextUtils.isEmpty(crashTO.getException())) {
                    try {
                        String[] split = crashTO.getExceptionInfo().split(":");
                        if (split.length > 0) {
                            crashTO.setException(split[0]);
                        }
                    } catch (Exception unused) {
                    }
                }
                crashTO.setExceptionMD5(Utils.a(crashTO.getExceptionInfo()));
            }
            crashTO.setPoints(cn.missfresh.sherlock.crash.a.a().c());
            crashTO.setRegion(cn.missfresh.sherlock.tool.c.a(e()));
            if (TextUtils.isEmpty(str)) {
                crashTO.setVc(cn.missfresh.sherlock.d.a.b());
            } else {
                crashTO.setVc(str);
            }
            crashTO.setPhoneNumber(h());
            c.a().a((CommonTO) crashTO);
        }
    }

    private static void b(boolean z) {
        j.a(z);
    }
}
