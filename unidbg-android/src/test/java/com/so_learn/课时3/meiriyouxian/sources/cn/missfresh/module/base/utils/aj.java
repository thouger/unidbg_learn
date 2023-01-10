package cn.missfresh.module.base.utils;

import android.content.Context;
import android.os.SystemClock;
import cn.missfresh.buttomline.logtrace.bean.LogBean;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.alibaba.android.arouter.b.a;
import java.util.HashMap;

/* compiled from: PerformanceStatisticUtils */
public class aj {
    private static volatile aj b;
    private final String a = "PerformanceStatisticUtils";
    private HashMap<String, LogBean> c = new HashMap<>();
    private Context d;
    private boolean e;

    private boolean b() {
        return false;
    }

    public void b(String str) {
    }

    public static aj a() {
        AppMethodBeat.i(23384, false);
        if (b == null) {
            synchronized (aj.class) {
                try {
                    if (b == null) {
                        b = new aj();
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(23384);
                    throw th;
                }
            }
        }
        aj ajVar = b;
        AppMethodBeat.o(23384);
        return ajVar;
    }

    private aj() {
        AppMethodBeat.i(23385, false);
        AppMethodBeat.o(23385);
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void a(String str) {
        AppMethodBeat.i(23386, false);
        LogBean logBean = this.c.get(str);
        if (logBean == null || !"report_single".equals(logBean.getInfo_str())) {
            this.c.put(str, c(str));
            AppMethodBeat.o(23386);
            return;
        }
        AppMethodBeat.o(23386);
    }

    public void a(String str, String str2, String str3) {
        AppMethodBeat.i(23387, false);
        a(str, str2, "report_single", str3);
        AppMethodBeat.o(23387);
    }

    public void a(String str, String str2, String str3, String str4) {
        AppMethodBeat.i(23388, false);
        if (this.d == null) {
            this.d = ((IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()).getApplication();
        }
        d.c("PerformanceStatisticUtils", "is AB enable " + b() + " is first start " + this.e);
        AppMethodBeat.o(23388);
    }

    private LogBean c(String str) {
        AppMethodBeat.i(23389, false);
        LogBean logBean = new LogBean();
        logBean.setStr_value_8(str);
        logBean.setNum_value_0(Long.valueOf(SystemClock.elapsedRealtime()));
        AppMethodBeat.o(23389);
        return logBean;
    }
}
