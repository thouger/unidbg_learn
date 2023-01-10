package cn.missfresh.module.base.payment.pwd.b;

import android.os.CountDownTimer;
import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.network.m;
import cn.missfresh.module.base.payment.pwd.view.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSONObject;
import com.bangcle.andjni.JniLib;
import com.taobao.accs.common.Constants;
import okhttp3.Request;

/* compiled from: BindPayPhonePresenter */
public class a {
    private final String a;
    private cn.missfresh.module.base.payment.pwd.view.a b;
    private cn.missfresh.module.base.payment.pwd.a.a c;
    private String d;
    private CountDownTimer e;

    /* compiled from: BindPayPhonePresenter */
    /* renamed from: cn.missfresh.module.base.payment.pwd.b.a$a  reason: collision with other inner class name */
    private class C0026a implements c {
        String a;
        final /* synthetic */ a b;

        public C0026a(a aVar, String str) {
            JniLib.cV(this, aVar, str, 248);
        }

        @Override // cn.missfresh.module.base.payment.pwd.view.c
        public void a(boolean z, String str, String str2) {
            JniLib.cV(this, Boolean.valueOf(z), str, str2, 245);
        }

        @Override // cn.missfresh.module.base.payment.pwd.view.c
        public void a(boolean z, boolean z2, String str, String str2, String str3) {
            JniLib.cV(this, Boolean.valueOf(z), Boolean.valueOf(z2), str, str2, str3, 246);
        }

        @Override // cn.missfresh.module.base.payment.pwd.view.c
        public void b_(String str) {
            JniLib.cV(this, str, 247);
        }
    }

    public a(cn.missfresh.module.base.payment.pwd.view.a aVar) {
        JniLib.cV(this, aVar, 252);
    }

    public void a(String str) {
        JniLib.cV(this, str, 249);
    }

    public void b() {
        JniLib.cV(this, 250);
    }

    public void c() {
        JniLib.cV(this, 251);
    }

    /* compiled from: BindPayPhonePresenter */
    /* renamed from: cn.missfresh.module.base.payment.pwd.b.a$1  reason: invalid class name */
    class AnonymousClass1 extends CountDownTimer {
        @Override // android.os.CountDownTimer
        public void onFinish() {
            JniLib.cV(this, 234);
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            JniLib.cV(this, Long.valueOf(j), 235);
        }

        AnonymousClass1(long j, long j2) {
            super(j, j2);
        }
    }

    public void a(String str, String str2) {
        AppMethodBeat.i(16417, false);
        cn.missfresh.module.base.network.c.b(this, i.am, cn.missfresh.module.base.network.c.a("user_id", String.valueOf(e.p().getUser_id()), "sp_no", this.d, "mobile_no", str, "type", "app_set", Constants.KEY_SEND_TYPE, str2), new AnonymousClass2(str2));
        AppMethodBeat.o(16417);
    }

    /* compiled from: BindPayPhonePresenter */
    /* renamed from: cn.missfresh.module.base.payment.pwd.b.a$2  reason: invalid class name */
    class AnonymousClass2 extends m {
        final /* synthetic */ String a;

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            JniLib.cV(this, Integer.valueOf(i), 236);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            JniLib.cV(this, str, 237);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            JniLib.cV(this, request, exc, 238);
        }

        AnonymousClass2(String str) {
            this.a = str;
        }
    }

    public void b(String str, String str2) {
        AppMethodBeat.i(16420, false);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("phone_number", str);
        jSONObject.put("sms_type", "4");
        jSONObject.put("auth_code", "");
        jSONObject.put("is_auth", "0");
        jSONObject.put(Constants.KEY_SEND_TYPE, str2);
        cn.missfresh.module.base.network.c.a(this.a, i.ad, null, jSONObject, new AnonymousClass3(str2));
        AppMethodBeat.o(16420);
    }

    /* compiled from: BindPayPhonePresenter */
    /* renamed from: cn.missfresh.module.base.payment.pwd.b.a$3  reason: invalid class name */
    class AnonymousClass3 extends m {
        final /* synthetic */ String a;

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            JniLib.cV(this, Integer.valueOf(i), 239);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            JniLib.cV(this, str, 240);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            JniLib.cV(this, request, exc, 241);
        }

        AnonymousClass3(String str) {
            this.a = str;
        }
    }

    public void c(String str, String str2) {
        AppMethodBeat.i(16421, false);
        JSONObject b = cn.missfresh.module.base.network.c.b("user_id", String.valueOf(e.p().getUser_id()), "sp_no", this.d, "mobile_no", str, "mobile_code", str2);
        this.b.r();
        cn.missfresh.module.base.network.c.b(this, i.an, null, b, new AnonymousClass4(str));
        AppMethodBeat.o(16421);
    }

    /* compiled from: BindPayPhonePresenter */
    /* renamed from: cn.missfresh.module.base.payment.pwd.b.a$4  reason: invalid class name */
    class AnonymousClass4 extends m {
        final /* synthetic */ String a;

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            JniLib.cV(this, Integer.valueOf(i), 242);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            JniLib.cV(this, str, 243);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            JniLib.cV(this, request, exc, 244);
        }

        AnonymousClass4(String str) {
            this.a = str;
        }
    }

    public String a() {
        return this.d;
    }

    public void b(String str) {
        this.d = str;
    }
}
