package cn.missfresh.sherlock.c;

import android.text.TextUtils;
import android.util.ArrayMap;
import cn.missfresh.sherlock.config.Config;
import cn.missfresh.sherlock.config.Type;
import cn.missfresh.sherlock.e;
import cn.missfresh.sherlock.to.CommonTO;
import cn.missfresh.sherlock.to.FlutterFpsTO;
import cn.missfresh.sherlock.to.FpsTO;
import cn.missfresh.sherlock.tool.Utils;
import cn.missfresh.sherlock.tool.c;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import java.util.Map;

/* compiled from: FlutterFpsHelper */
public class a {
    private Map<String, FlutterFpsTO> a;

    /* compiled from: FlutterFpsHelper */
    /* access modifiers changed from: private */
    public static class b {
        private static a a = new a();
    }

    public static a a() {
        return b.a;
    }

    private void b(double d, String str) {
        FpsTO fpsTO = new FpsTO();
        fpsTO.setEventType(Type.DROPFPS.getValue());
        fpsTO.setTimestamp(Long.valueOf(System.currentTimeMillis()));
        fpsTO.setNetwork(cn.missfresh.sherlock.d.a.a(e.e()));
        fpsTO.setNetworkOperator(cn.missfresh.sherlock.d.a.b(e.e()));
        if (TextUtils.isEmpty(e.g())) {
            fpsTO.setUserId(Utils.e(e.e()));
        } else {
            fpsTO.setUserId(e.g());
        }
        fpsTO.setPhoneNumber(e.h());
        fpsTO.setRegion(c.a(e.e()));
        String str2 = "FlutterPageActivity-" + str;
        fpsTO.setActivity(str2);
        fpsTO.setVc(str2);
        fpsTO.setFps((float) d);
        fpsTO.setIsFlutter(1);
        fpsTO.setCpu(cn.missfresh.sherlock.trace.tracer.b.a().a(e.e()));
        if (e.d() && e.m() != null) {
            e.m().a(fpsTO);
        }
        cn.missfresh.sherlock.c.a().a((CommonTO) fpsTO);
    }

    private a() {
        this.a = new ArrayMap();
    }

    public void a(double d, String str) {
        if (Config.getInstance().enableFpsSwitch() && Config.getInstance().flutterSwitch != 0 && !TextUtils.isEmpty(str) && d <= 121.0d) {
            if (str.startsWith(NotificationIconUtil.SPLIT_CHAR)) {
                str = str.substring(1, str.length());
            }
            FlutterFpsTO flutterFpsTO = this.a.get(str);
            if (flutterFpsTO == null) {
                FlutterFpsTO flutterFpsTO2 = new FlutterFpsTO();
                flutterFpsTO2.setFps(d);
                flutterFpsTO2.setCount(1);
                this.a.put(str, flutterFpsTO2);
            } else if (flutterFpsTO.getCount() >= 29) {
                b(d, str);
                this.a.remove(str);
            } else {
                flutterFpsTO.setCount(flutterFpsTO.getCount() + 1);
            }
        }
    }
}
