package cn.missfresh.module.base.network.a;

import cn.missfresh.buttomline.logtrace.bean.LogBean;
import cn.missfresh.sherlock.to.NetworkTO;
import com.bangcle.andjni.JniLib;
import java.util.Map;

/* compiled from: BusinessMonitoringInterceptor */
public class a {
    public static void a(NetworkTO networkTO) {
        JniLib.cV(networkTO, 18);
    }

    private static void a(NetworkTO networkTO, LogBean logBean, Map.Entry<String, String> entry) {
        JniLib.cV(networkTO, logBean, entry, 19);
    }

    private static void b(NetworkTO networkTO, LogBean logBean, Map.Entry<String, String> entry) {
        JniLib.cV(networkTO, logBean, entry, 20);
    }
}
