package cn.missfresh.risk.exceptions;

import cn.missfresh.buttomline.logtrace.bean.LogBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.e;

/* compiled from: RiskErrorHelper */
public class a {
    public static void a(String str) {
        AppMethodBeat.i(2206, false);
        try {
            LogBean logBean = new LogBean();
            logBean.setType("risk");
            logBean.setInfo_str(str);
            logBean.setStr_value_0("init");
            d.a(logBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(2206);
    }

    public static void a(String str, String str2) {
        AppMethodBeat.i(2210, false);
        if (e.a(str)) {
            try {
                LogBean logBean = new LogBean();
                logBean.setType("risk");
                logBean.setInfo_str("token is null from " + str2);
                logBean.setStr_value_0(str);
                d.a(logBean);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (str.contains("%00")) {
            try {
                LogBean logBean2 = new LogBean();
                logBean2.setType("risk");
                logBean2.setInfo_str("token contains %00 from " + str2);
                logBean2.setStr_value_0(str);
                d.a(logBean2);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        AppMethodBeat.o(2210);
    }
}
