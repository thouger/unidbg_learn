package cn.missfresh.module.user.login.presenter;

import cn.missfresh.module.base.network.k;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.a.a;
import com.alibaba.fastjson.JSONObject;
import com.taobao.accs.common.Constants;
import okhttp3.Request;

/* compiled from: LoginPresenter */
class b$6 extends k {
    final /* synthetic */ String a;
    final /* synthetic */ b b;

    b$6(b bVar, String str) {
        this.b = bVar;
        this.a = str;
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(int i) {
        AppMethodBeat.i(8056, false);
        super.a(i);
        a.a("\u7f51\u7edc\u8fde\u63a5\u5931\u8d25");
        b.l(this.b);
        b.a(this.b).c(this.a);
        AppMethodBeat.o(8056);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(Request request, Exception exc) {
        AppMethodBeat.i(8059, false);
        super.a(request, exc);
        a.a("\u7f51\u7edc\u8fde\u63a5\u5931\u8d25");
        b.l(this.b);
        b.a(this.b).c(this.a);
        AppMethodBeat.o(8059);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.network.k
    public void a(JSONObject jSONObject) {
        AppMethodBeat.i(8062, false);
        b.l(this.b);
        if (jSONObject == null || jSONObject.size() == 0) {
            a.a("\u7f51\u7edc\u8fde\u63a5\u5931\u8d25,\u8bf7\u7a0d\u540e\u91cd\u8bd5");
            b.a(this.b).c(this.a);
            AppMethodBeat.o(8062);
            return;
        }
        b.a(this.b).c(this.a);
        if (jSONObject.getIntValue(Constants.KEY_HTTP_CODE) == 0) {
            if ("SMS".equals(this.a)) {
                a.a("\u9a8c\u8bc1\u7801\u53d1\u9001\u6210\u529f");
            } else {
                b.m(this.b);
            }
        } else if (jSONObject.getIntValue(Constants.KEY_HTTP_CODE) == 3) {
            if ("SMS".equals(this.a)) {
                b.k(this.b);
            }
        } else if (jSONObject.getIntValue(Constants.KEY_HTTP_CODE) == 208 || jSONObject.getIntValue(Constants.KEY_HTTP_CODE) == 209) {
            if ("SMS".equals(this.a)) {
                b.n(this.b);
            }
            a.a(jSONObject.getString("msg"));
        } else {
            a.a(jSONObject.getString("msg"));
        }
        AppMethodBeat.o(8062);
    }
}
