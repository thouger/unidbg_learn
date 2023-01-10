package cn.missfresh.flutter.feature;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import androidx.fragment.app.FragmentActivity;
import cn.missfresh.buttomline.logtrace.e.c;
import cn.missfresh.flutter.feature.base.a;
import cn.missfresh.module.base.manager.bean.UserInfo;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.utils.k;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/* compiled from: MePageFlutterFeature */
public class d extends a {
    public d(FragmentActivity fragmentActivity, MethodChannel methodChannel) {
        super(fragmentActivity, methodChannel);
    }

    public void a(MethodCall methodCall, MethodChannel.Result result) {
        AppMethodBeat.i(20917, false);
        cn.missfresh.utils.a.d.c("MePageFlutterFeature=>", methodCall.method);
        if (methodCall.method.equals("longPressMeMessage")) {
            a();
        }
        AppMethodBeat.o(20917);
    }

    private void a() {
        AppMethodBeat.i(20920, false);
        String cVar = c.a().toString();
        UserInfo p = e.p();
        cn.missfresh.utils.a.d.d("onLongClick", "txt=" + cVar);
        if (p != null && p.getUser_id() > 0) {
            cVar = cVar + ",uid=" + String.valueOf(p.getUser_id());
        }
        if (p != null && !TextUtils.isEmpty(p.getPhone_number())) {
            cVar = cVar + ",Phone=" + p.getPhone_number();
        }
        ((ClipboardManager) b().getSystemService(Context.CLIPBOARD_SERVICE)).setPrimaryClip(ClipData.newPlainText("info", new String(k.a((((cVar + ",AndroidId=" + c.a().a(b().getApplicationContext())) + ",AppVersionName=" + c.a().c(b().getApplicationContext())) + ",AppVersionCode=" + c.a().e(b().getApplicationContext())).getBytes()))));
        AppMethodBeat.o(20920);
    }
}
