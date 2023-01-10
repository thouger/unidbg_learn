package cn.missfresh.flutter.feature;

import android.content.Intent;
import android.net.Uri;
import androidx.fragment.app.FragmentActivity;
import cn.missfresh.flutter.feature.base.a;
import cn.missfresh.module.base.common.config.c;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.manager.s;
import cn.missfresh.module.base.utils.ad;
import cn.missfresh.module.base.utils.r;
import cn.missfresh.picture.luban.CompressManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import java.io.File;

/* compiled from: SettingFlutterFeature */
public class f extends a {
    public f(FragmentActivity fragmentActivity, MethodChannel methodChannel) {
        super(fragmentActivity, methodChannel);
    }

    public void a(MethodCall methodCall, MethodChannel.Result result) {
        AppMethodBeat.i(21172, false);
        if (methodCall.method.equals("settingNeedUpgrade")) {
            result.success(e.G() + "");
        } else if (methodCall.method.equals("settingShowSuperOptions")) {
            result.success(e.b() + "");
        } else if (methodCall.method.equals("settingSetShowSuperOptions")) {
            e.a(true);
        } else if (methodCall.method.equals("settingGetVersionCode")) {
            result.success(r.h(b()) + "");
        } else if (methodCall.method.equals("settingScore")) {
            d();
        } else if (methodCall.method.equals("settingClean")) {
            a();
        } else if (methodCall.method.equals("settingCheckUpgrade")) {
            if (!e.G()) {
                cn.missfresh.ui.a.a.a("\u5df2\u662f\u6700\u65b0\u7248\u672c");
            } else {
                s.a().a(b(), false);
            }
        } else if (methodCall.method.equals("settingNeedShowUpgrade")) {
            result.success(e.G() + "");
        }
        AppMethodBeat.o(21172);
    }

    private void a() {
        String str;
        AppMethodBeat.i(21173, false);
        String str2 = null;
        try {
            File file = new File(c.e);
            str2 = cn.missfresh.module.base.utils.s.b(file);
            cn.missfresh.module.base.utils.s.a(file.toString(), false);
            CompressManager.clearCompressDir(b().getApplicationContext());
        } catch (Exception unused) {
        }
        if (str2 == null) {
            str = "\u6e05\u9664\u7f13\u5b58\u6210\u529f";
        } else {
            str = String.format("\u6e05\u9664\u4e86%s\u7684\u7f13\u5b58", str2);
        }
        cn.missfresh.ui.a.a.a(str);
        AppMethodBeat.o(21173);
    }

    private void d() {
        boolean z = false;
        AppMethodBeat.i(21175, false);
        try {
            Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + b().getPackageName()));
            intent.addFlags(268435456);
            if (b().getPackageManager().queryIntentActivities(intent, 65536).size() > 0) {
                z = true;
            }
            if (z) {
                b().startActivity(ad.a(b(), intent));
            } else {
                cn.missfresh.ui.a.a.a("\u64cd\u4f5c\u5931\u8d25\uff0c\u8bf7\u53bb\u76f8\u5e94\u7684\u5e94\u7528\u5e02\u573a\u8bc4\u5206");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(21175);
    }
}
