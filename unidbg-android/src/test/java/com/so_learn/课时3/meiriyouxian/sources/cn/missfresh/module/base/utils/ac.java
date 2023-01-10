package cn.missfresh.module.base.utils;

import cn.missfresh.buttomline.logtrace.bean.LogBean;
import cn.missfresh.sherlock.Sherlock;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/* compiled from: MFErrorMonitor */
public class ac {
    public static void a(String str, String str2, String str3) {
        String str4;
        String str5;
        AppMethodBeat.i(23344, false);
        try {
            String format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS", Locale.getDefault()).format(new Date());
            String str6 = "\u672a\u77e5";
            if (Thread.currentThread().getStackTrace() == null || Thread.currentThread().getStackTrace().length <= 3) {
                str5 = str6;
                str4 = str5;
            } else {
                str6 = Thread.currentThread().getStackTrace()[3].getClassName();
                str5 = Thread.currentThread().getStackTrace()[3].getMethodName();
                str4 = Thread.currentThread().getStackTrace()[3].getLineNumber() + "";
            }
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append("MFBusinessLogType");
            sb.append("]");
            sb.append("[");
            sb.append(format);
            sb.append("]");
            sb.append("[");
            sb.append(str6);
            sb.append("-");
            sb.append(str5);
            sb.append("-");
            sb.append(str4);
            sb.append("]");
            sb.append("[");
            sb.append(str);
            sb.append("-");
            sb.append(str2);
            sb.append("-");
            sb.append(str3);
            sb.append("]");
            LogBean logBean = new LogBean();
            logBean.setType("MFBusinessLogType");
            logBean.setInfo_str("1.0.0");
            logBean.setStr_value_0(str);
            logBean.setStr_value_1(str2);
            logBean.setStr_value_2(str3);
            logBean.setStr_value_3(str6);
            logBean.setStr_value_4(str5);
            logBean.setStr_value_5(str4);
            logBean.setStr_value_6(sb.toString());
            d.a(logBean);
            d.d("MFBusinessLogType", sb.toString());
            Sherlock.reportBusinessInfo(str, str2, str3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(23344);
    }

    public static void a(String str, String str2, String str3, String str4) {
        String str5;
        String str6;
        AppMethodBeat.i(23345, false);
        try {
            String format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss:SSS", Locale.getDefault()).format(new Date());
            String str7 = "\u672a\u77e5";
            if (Thread.currentThread().getStackTrace() == null || Thread.currentThread().getStackTrace().length <= 3) {
                str6 = str7;
                str5 = str6;
            } else {
                str7 = Thread.currentThread().getStackTrace()[3].getClassName();
                str6 = Thread.currentThread().getStackTrace()[3].getMethodName();
                str5 = Thread.currentThread().getStackTrace()[3].getLineNumber() + "";
            }
            StringBuilder sb = new StringBuilder();
            sb.append("[");
            sb.append("MFBusinessLogType");
            sb.append("]");
            sb.append("[");
            sb.append(format);
            sb.append("]");
            sb.append("[");
            sb.append(str7);
            sb.append("-");
            sb.append(str6);
            sb.append("-");
            sb.append(str5);
            sb.append("]");
            sb.append("[");
            sb.append(str);
            sb.append("-");
            sb.append(str2);
            sb.append("-");
            sb.append(str3);
            sb.append("]");
            LogBean logBean = new LogBean();
            logBean.setType("MFBusinessLogType");
            logBean.setInfo_str("1.0.0");
            logBean.setStr_value_0(str);
            logBean.setStr_value_1(str2);
            logBean.setStr_value_2(str3);
            logBean.setStr_value_3(str7);
            logBean.setStr_value_4(str6);
            logBean.setStr_value_5(str5);
            logBean.setStr_value_6(sb.toString());
            logBean.setStr_value_7(str4);
            d.b(logBean);
            d.d("MFBusinessLogType", sb.toString());
            Sherlock.reportBusinessInfo(str, str2, str3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(23345);
    }
}
