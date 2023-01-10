package cn.missfresh.flutter.feature;

import android.preference.PreferenceManager;
import androidx.fragment.app.FragmentActivity;
import cn.missfresh.module.base.utils.f;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.sherlock.Sherlock;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.util.Map;

/* compiled from: APMFlutterFeature */
public class a extends cn.missfresh.flutter.feature.base.a {
    public a(FragmentActivity fragmentActivity, MethodChannel methodChannel) {
        super(fragmentActivity, methodChannel);
    }

    public void a(MethodCall methodCall, MethodChannel.Result result) {
        AppMethodBeat.i(20800, false);
        if (methodCall.method.equals("reportAll")) {
            if (methodCall.arguments instanceof Map) {
                Sherlock.reportFlutterAllInfo((Map) methodCall.arguments);
            }
        } else if (methodCall.method.equals("obtainFrameRate")) {
            result.success(Double.valueOf(j.b(b().getApplicationContext())));
        } else if (methodCall.method.equals("updateFontIndex")) {
            a("fontSelectIndex", ((Integer) methodCall.argument("selectIndex")).intValue());
        } else if (methodCall.method.equals("obtainFontIndex")) {
            result.success(Integer.valueOf(c("fontSelectIndex")));
        } else if (methodCall.method.equals("isFontSetting")) {
            result.success(Boolean.valueOf(f.w()));
        }
        AppMethodBeat.o(20800);
    }

    private void a(String str, int i) {
        AppMethodBeat.i(20803, false);
        if (c(str) != i) {
            cn.missfresh.ui.a.a.a("\u5b57\u4f53\u8bbe\u7f6e\u6210\u529f\uff0c\u91cd\u65b0\u542f\u52a8APP\u751f\u6548");
            PreferenceManager.getDefaultSharedPreferences(b().getApplicationContext()).edit().putInt(str, i).commit();
        }
        AppMethodBeat.o(20803);
    }

    private int c(String str) {
        AppMethodBeat.i(20805, false);
        int i = PreferenceManager.getDefaultSharedPreferences(b().getApplicationContext()).getInt(str, 2);
        AppMethodBeat.o(20805);
        return i;
    }
}
