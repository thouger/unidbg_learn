package cn.missfresh.module.base.payment.pwd.b;

import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.network.c;
import cn.missfresh.module.base.network.m;
import cn.missfresh.module.base.utils.aq;
import cn.missfresh.module.base.utils.r;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSONObject;
import com.bangcle.andjni.JniLib;
import com.tencent.connect.common.Constants;
import okhttp3.Request;

/* compiled from: PassWordPresenter */
public class b {
    private cn.missfresh.module.base.payment.pwd.view.b a;
    private String b;
    private String c;

    public b(cn.missfresh.module.base.payment.pwd.view.b bVar) {
        JniLib.cV(this, bVar, 256);
    }

    public void a(String str) {
        this.b = str;
    }

    public void b(String str) {
        this.c = str;
    }

    public void c(String str) {
        AppMethodBeat.i(16442, false);
        JSONObject b = c.b("user_id", String.valueOf(e.p().getUser_id()), "sp_no", this.b, "mobile_no", e.p().getPhone_number(), Constants.PARAM_ACCESS_TOKEN, this.c, "password_v2", aq.a(str));
        this.a.a();
        Request.Builder builder = new Request.Builder();
        builder.addHeader("platform", "android");
        builder.addHeader("version", r.d());
        c.a(this, i.al, null, b, builder, new AnonymousClass1());
        AppMethodBeat.o(16442);
    }

    /* compiled from: PassWordPresenter */
    /* renamed from: cn.missfresh.module.base.payment.pwd.b.b$1  reason: invalid class name */
    class AnonymousClass1 extends m {
        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            JniLib.cV(this, Integer.valueOf(i), 253);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            JniLib.cV(this, str, 254);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            JniLib.cV(this, request, exc, 255);
        }

        AnonymousClass1() {
        }
    }
}
