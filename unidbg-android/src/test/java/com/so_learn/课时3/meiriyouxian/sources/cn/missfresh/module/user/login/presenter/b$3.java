package cn.missfresh.module.user.login.presenter;

import android.text.TextUtils;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.network.k;
import cn.missfresh.module.base.utils.ac;
import cn.missfresh.module.user.login.b.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSONObject;
import com.huawei.hms.support.api.entity.core.JosStatusCodes;
import com.taobao.accs.common.Constants;
import okhttp3.Request;

/* compiled from: LoginPresenter */
class b$3 extends k {
    final /* synthetic */ b a;

    b$3(b bVar) {
        this.a = bVar;
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(int i) {
        AppMethodBeat.i(JosStatusCodes.RTN_CODE_COMMON_ERROR, false);
        super.a(i);
        b.a(this.a).m_("\u7f51\u7edc\u8fde\u63a5\u5931\u8d25");
        ac.a("\u7528\u6237\u6a21\u5757", "\u624b\u673a\u53f7\u767b\u5f55\u5931\u8d25", "code:" + i);
        AppMethodBeat.o(JosStatusCodes.RTN_CODE_COMMON_ERROR);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(Request request, Exception exc) {
        AppMethodBeat.i(8002, false);
        super.a(request, exc);
        b.a(this.a).m_("\u7f51\u7edc\u8fde\u63a5\u5931\u8d25");
        ac.a("\u7528\u6237\u6a21\u5757", "\u624b\u673a\u53f7\u767b\u5f55\u5931\u8d25", "\u7f51\u7edc\u8fde\u63a5\u5931\u8d25");
        AppMethodBeat.o(8002);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.network.k
    public void a(JSONObject jSONObject) {
        AppMethodBeat.i(8005, false);
        if (jSONObject.getIntValue(Constants.KEY_HTTP_CODE) == 0) {
            String string = jSONObject.getString(com.tencent.connect.common.Constants.PARAM_ACCESS_TOKEN);
            if (!TextUtils.isEmpty(string)) {
                e.g(string);
                a.a().a(0);
                b.a(this.a).d();
            } else {
                b.a(this.a).m_(jSONObject.getString("\u767b\u5f55\u5931\u8d25\uff0c \u8bf7\u91cd\u8bd5"));
            }
        } else {
            b.a(this.a).m_(jSONObject.getString("msg"));
            ac.a("\u7528\u6237\u6a21\u5757", "\u624b\u673a\u53f7\u767b\u5f55\u5931\u8d25", jSONObject.getString("msg"));
        }
        AppMethodBeat.o(8005);
    }
}
