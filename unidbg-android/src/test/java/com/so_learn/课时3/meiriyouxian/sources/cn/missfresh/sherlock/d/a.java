package cn.missfresh.sherlock.d;

import android.content.Context;
import android.text.TextUtils;
import cn.missfresh.sherlock.config.Config;
import cn.missfresh.sherlock.e;
import cn.missfresh.sherlock.to.ApmTO;
import cn.missfresh.sherlock.to.CommonTO;
import cn.missfresh.sherlock.to.NetworkParamTO;
import cn.missfresh.sherlock.tool.Utils;
import cn.missfresh.sherlock.tool.h;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ApmTOHelper */
public class a {
    private static int a = -1;

    public static int a(Context context) {
        int i = a;
        if (i >= 0) {
            return i;
        }
        Utils.NetworkType c = Utils.c(context);
        if (c == Utils.NetworkType.NETWORK_WIFI) {
            a = 1;
            return a;
        } else if (c == Utils.NetworkType.NETWORK_2G) {
            a = 2;
            return a;
        } else if (c == Utils.NetworkType.NETWORK_3G) {
            a = 3;
            return a;
        } else if (c == Utils.NetworkType.NETWORK_4G) {
            a = 4;
            return a;
        } else if (c == Utils.NetworkType.NETWORK_NO) {
            a = 9;
            return a;
        } else {
            a = 0;
            return a;
        }
    }

    public static ApmTO a() {
        ApmTO apmTO = new ApmTO();
        apmTO.setAppKey(e.f());
        apmTO.setAppName(Utils.b(e.e()));
        apmTO.setAppVersion(Utils.a(e.e()));
        apmTO.setDeviceMode(Utils.a());
        apmTO.setDeviceManufacturer(Utils.b());
        apmTO.setOsVersion(Utils.c());
        apmTO.setImei(Utils.e(e.e()));
        apmTO.setPlatform(1);
        apmTO.setSdkVersion("7.0.7");
        return apmTO;
    }

    public static ApmTO a(List<CommonTO> list) {
        ApmTO a2 = a();
        a2.setData(list);
        return a2;
    }

    public static int b(Context context) {
        String d = Utils.d(context);
        if (TextUtils.isEmpty(d)) {
            return 0;
        }
        if (d.contains("\u79fb\u52a8")) {
            return 1;
        }
        if (d.contains("\u8054\u901a")) {
            return 2;
        }
        if (d.contains("\u7535\u4fe1")) {
            return 3;
        }
        return 0;
    }

    public static List<NetworkParamTO> b(String str) {
        List<NetworkParamTO> c = cn.missfresh.sherlock.okhttp.a.a().c();
        if (TextUtils.isEmpty(str) || Utils.a(c)) {
            return null;
        }
        int i = 50;
        if (Config.getInstance().getShotNetowrkCount() <= 50) {
            i = Config.getInstance().getShotNetowrkCount();
        }
        ArrayList arrayList = new ArrayList();
        for (int size = c.size() - 1; size >= 0; size--) {
            if (str.equals(c.get(size).getVc()) && arrayList.size() < i) {
                arrayList.add(c.get(size));
            }
        }
        if (arrayList.size() == 0) {
            for (int size2 = c.size() - 1; size2 >= 0; size2--) {
                NetworkParamTO networkParamTO = c.get(size2);
                if (arrayList.size() < 5) {
                    arrayList.add(networkParamTO);
                }
            }
        }
        return arrayList;
    }

    public static String b() {
        String d = cn.missfresh.sherlock.e.a.a().d();
        String c = cn.missfresh.sherlock.e.a.a().c();
        if (TextUtils.isEmpty(d) && TextUtils.isEmpty(c)) {
            return null;
        }
        if (TextUtils.isEmpty(c)) {
            return d;
        }
        return d + "-" + c;
    }

    public static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return h.a(str, Config.getInstance().fileSize);
    }
}
