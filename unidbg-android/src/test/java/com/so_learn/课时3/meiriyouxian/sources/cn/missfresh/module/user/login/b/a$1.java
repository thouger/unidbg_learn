package cn.missfresh.module.user.login.b;

import cn.missfresh.module.base.helper.o;
import cn.missfresh.module.base.manager.bean.UserInfo;
import cn.missfresh.module.base.network.m;
import cn.missfresh.module.base.utils.ac;
import cn.missfresh.module.user.login.view.LoginActivity;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.alibaba.fastjson.JSONObject;
import okhttp3.Request;

/* compiled from: LoginInteractor */
class a$1 extends m {
    final /* synthetic */ boolean a;
    final /* synthetic */ int b;
    final /* synthetic */ boolean c;
    final /* synthetic */ a d;

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a() {
    }

    a$1(a aVar, boolean z, int i, boolean z2) {
        this.d = aVar;
        this.a = z;
        this.b = i;
        this.c = z2;
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(int i) {
        AppMethodBeat.i(7718, false);
        super.a(i);
        ac.a("\u7528\u6237\u6a21\u5757", "\u83b7\u53d6\u7528\u6237\u4fe1\u606f\u5931\u8d25", "errMsg:codeError errCode:" + i);
        AppMethodBeat.o(7718);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(Request request, Exception exc) {
        AppMethodBeat.i(7722, false);
        super.a(request, exc);
        StringBuilder sb = new StringBuilder();
        sb.append("errMsg:");
        sb.append(exc != null ? exc.getMessage() : "");
        ac.a("\u7528\u6237\u6a21\u5757", "\u83b7\u53d6\u7528\u6237\u4fe1\u606f\u5931\u8d25", sb.toString());
        AppMethodBeat.o(7722);
    }

    @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
    public void a(String str) {
        AppMethodBeat.i(7727, false);
        super.a(str);
        try {
            UserInfo userInfo = (UserInfo) JSONObject.parseObject(str, UserInfo.class);
            a.a(this.d, userInfo);
            if (!this.a || userInfo == null || userInfo.getIs_binding_phone() != 0) {
                this.d.b(this.b, this.c);
                AppMethodBeat.o(7727);
                return;
            }
            o.a(false, LoginActivity.class.getSimpleName());
            AppMethodBeat.o(7727);
        } catch (Exception e) {
            if (a.b() != null) {
                a.b().a();
            }
            d.a(a.a, e);
            ac.a("\u7528\u6237\u6a21\u5757", "\u83b7\u53d6\u7528\u6237\u4fe1\u606f\u5931\u8d25", "errMsg:" + e.getMessage());
        }
    }
}
