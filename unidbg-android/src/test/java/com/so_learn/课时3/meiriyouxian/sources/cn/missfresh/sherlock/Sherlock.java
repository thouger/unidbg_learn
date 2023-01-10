package cn.missfresh.sherlock;

import android.app.Application;
import android.text.TextUtils;
import cn.missfresh.sherlock.to.CrashTO;
import cn.missfresh.sherlock.to.NetworkTO;
import cn.missfresh.sherlock.tool.j;
import java.util.List;
import java.util.Map;

public final class Sherlock {
    private static final String APM_REPORT_CRASH = "crashReport";
    private static final String APM_REPORT_FPS = "fpsReport";
    private static final String APM_REPORT_PAGE_TIME = "pageTimeReport";
    private static boolean initialized;
    public static long sRequestPermissionsTime;

    public static synchronized void addExtendInfo(String str) {
        synchronized (Sherlock.class) {
            e.d(str);
        }
    }

    public static synchronized void destory() {
        synchronized (Sherlock.class) {
            e.c();
        }
    }

    public static synchronized void init(Application application, String str, String str2, String str3) {
        synchronized (Sherlock.class) {
            if (!initialized) {
                initialized = true;
                e.a(application, str, str2, str3);
            }
        }
    }

    public static boolean isApmHost(String str) {
        return !TextUtils.isEmpty(str) && str.contains("apm.missfresh.net");
    }

    public static void openLocalFunction(boolean z) {
        e.a(z);
    }

    public static synchronized void registerEventListener(d dVar) {
        synchronized (Sherlock.class) {
            e.a(dVar);
        }
    }

    public static synchronized void registerViewClickListener(SherlockViewClickListener sherlockViewClickListener) {
        synchronized (Sherlock.class) {
            e.a(sherlockViewClickListener);
        }
    }

    public static void reportBusinessInfo(String str, String str2, String str3) {
        e.a(str, str2, str3);
    }

    public static void reportFlutterAllInfo(Map map) {
        try {
            String str = (String) map.get("reportType");
            String str2 = "FlutterPageActivity-" + ((String) map.get("vc"));
            if (APM_REPORT_CRASH.equals(str)) {
                CrashTO crashTO = new CrashTO();
                crashTO.setException((String) map.get("type"));
                crashTO.setExceptionInfo((String) map.get("message"));
                crashTO.setStack((String) map.get("stack"));
                crashTO.setVc(str2);
                e.a(str2, crashTO);
            } else if (APM_REPORT_FPS.equals(str)) {
                e.a(((Double) map.get("flutterFps")).doubleValue(), str2);
            } else if (APM_REPORT_PAGE_TIME.equals(str)) {
                e.a((String) map.get("flutterPageName"), str2, (long) ((Integer) map.get("costedMillis")).intValue(), (long) ((Integer) map.get("netMillis")).intValue(), true);
            }
        } catch (Exception e) {
            j.b("sherlock", "reportFlutterAllInfo exception" + e.getMessage());
        }
    }

    public static void reportNetworkInfo(NetworkTO networkTO) {
        e.a(networkTO);
    }

    public static synchronized void reportPerformLog(String str, long j) {
        synchronized (Sherlock.class) {
            e.a(str, j);
        }
    }

    public static synchronized void setAppVersionName(String str) {
        synchronized (Sherlock.class) {
            e.c(str);
        }
    }

    public static void setNetworkSuccessCode(int i) {
        e.a(i);
    }

    public static synchronized void setPhoneNumber(String str) {
        synchronized (Sherlock.class) {
            e.b(str);
        }
    }

    public static synchronized void setSplashActivities(String str) {
        synchronized (Sherlock.class) {
            e.e(str);
        }
    }

    public static synchronized void setUerId(String str) {
        synchronized (Sherlock.class) {
            e.a(str);
        }
    }

    public static synchronized void setWhiteList(List<String> list) {
        synchronized (Sherlock.class) {
            e.a(list);
        }
    }

    public static void reportNetworkInfo(Map<String, Object> map, int i) {
        e.a(map, i);
    }

    public static synchronized void init(Application application, String str) {
        synchronized (Sherlock.class) {
            init(application, str, null, null);
        }
    }

    public static synchronized void init(Application application, String str, String str2) {
        synchronized (Sherlock.class) {
            init(application, str, str2, null);
        }
    }
}
