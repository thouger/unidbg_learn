package cn.missfresh.module.user.login.presenter;

import android.text.TextUtils;
import cn.missfresh.module.base.manager.bean.UserInfo;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.network.k;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.alibaba.fastjson.JSONObject;
import com.taobao.accs.common.Constants;
import okhttp3.Request;

/* compiled from: BindingPresenter */
class a$1 extends k {
    final /* synthetic */ String a;
    final /* synthetic */ String b;
    final /* synthetic */ a c;

    a$1(a aVar, String str, String str2) {
        this.c = aVar;
        this.a = str;
        this.b = str2;
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.network.k
    public void a(JSONObject jSONObject) {
        AppMethodBeat.i(7924, false);
        if (jSONObject.getIntValue(Constants.KEY_HTTP_CODE) == 0) {
            if (("0".equals(this.a) || "2".equals(this.a)) && !b.a(this.b)) {
                UserInfo p = e.p();
                p.setPhone_number(this.b);
                e.a(p);
            }
            a.a(this.c).a(2);
            String string = jSONObject.getString("bind_success");
            if (TextUtils.isEmpty(string)) {
                a.b(this.c).a("\u7ed1\u5b9a\u6210\u529f");
            } else {
                a.b(this.c).a(string);
            }
        } else {
            a.b(this.c).b(jSONObject.getString("msg"));
        }
        AppMethodBeat.o(7924);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(int i) {
        AppMethodBeat.i(7925, false);
        super.a(i);
        a.b(this.c).b("\u53d1\u751f\u7f51\u7edc\u9519\u8bef,\u8bf7\u7a0d\u540e\u91cd\u8bd5");
        AppMethodBeat.o(7925);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(Request request, Exception exc) {
        AppMethodBeat.i(7926, false);
        super.a(request, exc);
        a.b(this.c).b("\u53d1\u751f\u7f51\u7edc\u9519\u8bef,\u8bf7\u7a0d\u540e\u91cd\u8bd5");
        AppMethodBeat.o(7926);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a() {
        AppMethodBeat.i(7929, false);
        super.a();
        a.b(this.c).b("\u53d1\u751f\u7f51\u7edc\u9519\u8bef,\u8bf7\u7a0d\u540e\u91cd\u8bd5");
        AppMethodBeat.o(7929);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void b() {
        AppMethodBeat.i(7931, false);
        a.b(this.c).b("\u53d1\u751f\u7f51\u7edc\u9519\u8bef,\u8bf7\u7a0d\u540e\u91cd\u8bd5");
        AppMethodBeat.o(7931);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void b(String str) {
        AppMethodBeat.i(7933, false);
        super.b(str);
        a.b(this.c).b("\u53d1\u751f\u7f51\u7edc\u9519\u8bef,\u8bf7\u7a0d\u540e\u91cd\u8bd5");
        AppMethodBeat.o(7933);
    }
}
