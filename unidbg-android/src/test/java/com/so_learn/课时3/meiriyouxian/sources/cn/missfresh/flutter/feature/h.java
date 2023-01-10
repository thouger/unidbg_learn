package cn.missfresh.flutter.feature;

import androidx.fragment.app.FragmentActivity;
import cn.missfresh.flutter.feature.base.a;
import cn.missfresh.module.base.b.c;
import cn.missfresh.module.base.common.config.BottomTabEnum;
import cn.missfresh.module.base.helper.o;
import cn.missfresh.module.base.im.IMManager;
import cn.missfresh.module.base.manager.bean.UserInfo;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.payment.pwd.view.BindPayPhoneActivity;
import cn.missfresh.module.base.utils.ag;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;
import com.taobao.accs.common.Constants;
import com.umeng.message.common.inter.ITagManager;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;

/* compiled from: UserFlutterFeature */
public class h extends a {
    public h(FragmentActivity fragmentActivity, MethodChannel methodChannel) {
        super(fragmentActivity, methodChannel);
    }

    public void a(MethodCall methodCall, MethodChannel.Result result) {
        AppMethodBeat.i(21201, false);
        if (methodCall.method.equals("isLogin")) {
            result.success(e.o() + "");
        } else if (methodCall.method.equals(Constants.KEY_USER_ID)) {
            UserInfo p = e.p();
            p.user_icon = p.getPortrait();
            p.wechatPayIcon = e.ac();
            p.wechatPayText = e.ab();
            result.success(JSON.toJSONString(p));
        } else if (methodCall.method.equals("httpBindPhone")) {
            o.a(false, b().getClass().getSimpleName());
            e.k(false);
        } else if (methodCall.method.equals("login")) {
            o.a(1);
        } else if (methodCall.method.equals("setPwd")) {
            BindPayPhoneActivity.a(b(), (String) methodCall.argument("phoneNumber"));
        } else if (methodCall.method.equals("settingLogout")) {
            e.r();
            IMManager.a().b();
            c.a().d();
            b().finish();
            com.alibaba.android.arouter.b.a.a().a("/main/mall").withInt("INTENT_EXTRA_POSITION", BottomTabEnum.INDEX.getPos()).addFlags(67108864).navigation();
        } else if (methodCall.method.equals("balanceBindAction")) {
            if (!e.v()) {
                o.a(b(), true, "balance_page", "");
            } else if (!e.y()) {
                o.a(true, "balance_page");
            }
        } else if (methodCall.method.equals("balanceBindText")) {
            if (!e.v()) {
                result.success(e.w());
            } else if (!e.y()) {
                result.success(e.x());
            } else {
                result.success("");
            }
        } else if (methodCall.method.equals("userInviteCodeSuccess")) {
            cn.missfresh.flutter.c.b = true;
        } else if (methodCall.method.equals("allowNotification")) {
            if (ag.a(b()) != 0) {
                result.success(ITagManager.STATUS_TRUE);
            } else {
                result.success(ITagManager.STATUS_FALSE);
            }
        } else if (methodCall.method.equals("openNotification")) {
            ag.b(b());
        }
        AppMethodBeat.o(21201);
    }
}
