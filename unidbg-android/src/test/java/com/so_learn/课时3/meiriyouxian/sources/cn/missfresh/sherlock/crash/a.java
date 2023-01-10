package cn.missfresh.sherlock.crash;

import android.text.TextUtils;
import cn.missfresh.buttomline.logtrace.bean.ConstantKey;
import cn.missfresh.sherlock.config.Config;
import cn.missfresh.sherlock.config.Type;
import cn.missfresh.sherlock.crash.c;
import cn.missfresh.sherlock.e;
import cn.missfresh.sherlock.to.CrashTO;
import cn.missfresh.sherlock.to.Point;
import cn.missfresh.sherlock.tool.Utils;
import cn.missfresh.sherlock.tool.j;
import com.taobao.accs.common.Constants;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import xcrash.FileManager;
import xcrash.ICrashCallback;

/* compiled from: CrashManager */
public class a {
    private LimitedQueue<Point> a;

    /* compiled from: CrashManager */
    /* renamed from: cn.missfresh.sherlock.crash.a$a  reason: collision with other inner class name */
    class C0040a implements ICrashCallback {
        C0040a() {
        }

        public void onCrash(String str, String str2) {
            a.this.b(str, str2);
        }
    }

    /* compiled from: CrashManager */
    public static class b {
        private static final a a = new a(null);
    }

    /* synthetic */ a(C0040a aVar) {
        this();
    }

    public static a a() {
        return b.a;
    }

    public void b() {
        j.b("CrashManager", "initCrashManager");
        c.a(e.e(), new c.a().a(new String[]{"^main$", "^Binder:.*", ".*Finalizer.*"}).b(10).a(50).a(true).c(10).b(new String[]{"^xcrash\\.sample$", "^Signal Catcher$", "^Jit thread pool$", ".*(R|r)ender.*", ".*Chrome.*"}).d(10).a((ICrashCallback) new C0040a()));
    }

    private a() {
        this.a = new LimitedQueue<>(20);
    }

    private String c(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return Utils.a(str);
        } catch (Exception e) {
            j.b("CrashManager", "obtainExceptionMD5: " + e.getMessage());
            return null;
        }
    }

    public void a(int i) {
        if (Config.getInstance().enableCrashSwitch()) {
            Point point = new Point();
            point.setMethodId((long) i);
            point.setTime(System.currentTimeMillis());
            this.a.add(point);
        }
    }

    public void a(String str, String str2) {
        if (Config.getInstance().enableCrashSwitch()) {
            Point point = new Point();
            point.setClassName(str);
            point.setMethodName(str2);
            point.setTime(System.currentTimeMillis());
            this.a.add(point);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(String str, String str2) {
        if (Config.getInstance().enableCrashSwitch()) {
            try {
                Map<String, String> a = CrashParser.a(str);
                CrashTO crashTO = new CrashTO();
                crashTO.setEventType(Type.CRASH.getValue());
                crashTO.setNativeCrash(1);
                crashTO.setCrashType(1);
                crashTO.setTimestamp(Long.valueOf(System.currentTimeMillis()));
                crashTO.setNetwork(cn.missfresh.sherlock.d.a.a(e.e()));
                crashTO.setNetworkOperator(cn.missfresh.sherlock.d.a.b(e.e()));
                if (TextUtils.isEmpty(e.g())) {
                    crashTO.setUserId(Utils.e(e.e()));
                } else {
                    crashTO.setUserId(e.g());
                }
                crashTO.setPhoneNumber(e.h());
                crashTO.setThreadName(a.get("tname"));
                crashTO.setProcessName(a.get("pname"));
                crashTO.setException(a.get(Constants.KEY_HTTP_CODE));
                crashTO.setRegion(cn.missfresh.sherlock.tool.c.a(e.e()));
                crashTO.setPid(a.get(ConstantKey.PID));
                crashTO.setTid(a.get("tid"));
                String str3 = a.get("backtrace");
                if (str3.length() > 2000) {
                    str3 = str3.substring(0, 2000);
                }
                crashTO.setBacktrace(str3);
                crashTO.setStack(a.get("java stacktrace"));
                crashTO.setExceptionInfo(b(crashTO.getStack()));
                crashTO.setExceptionMD5(c(crashTO.getExceptionInfo()));
                crashTO.setMemoryInfo(a.get("memory info"));
                crashTO.setOpenFiles(a.get("open files"));
                crashTO.setBuildId(a.get("build id"));
                crashTO.setSignal(a.get("signal"));
                crashTO.setPoints(c());
                crashTO.setVc(cn.missfresh.sherlock.d.a.b());
                cn.missfresh.sherlock.b.a.a().a(crashTO);
            } catch (IOException e) {
                e.printStackTrace();
            }
            a(str);
        }
    }

    public List c() {
        return this.a;
    }

    public static boolean a(String str) {
        return FileManager.getInstance().recycleLogFile(new File(str));
    }

    private String b(String str) {
        String str2 = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            str2 = str.split("\n")[0];
        } catch (Exception e) {
            j.b("CrashManager", "getReasonSign: " + e.getMessage());
        }
        return TextUtils.isEmpty(str2) ? str : str2;
    }
}
