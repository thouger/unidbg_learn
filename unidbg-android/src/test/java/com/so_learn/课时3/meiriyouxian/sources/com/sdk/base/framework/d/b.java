package com.sdk.base.framework.d;

import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.sdk.base.framework.a.a.c;
import com.sdk.base.framework.a.l;
import com.sdk.base.framework.f.h.a;
import com.taobao.accs.common.Constants;
import org.json.JSONObject;

final class b extends com.sdk.base.framework.b.b<String> {
    private /* synthetic */ a a;

    b(a aVar) {
        this.a = aVar;
    }

    /* JADX DEBUG: TODO: convert one arg to string using `String.valueOf()`, args: [(r5v0 java.lang.Object)] */
    public final void a(int i, Object obj) {
        AppMethodBeat.i(20202, false);
        a aVar = this.a;
        StringBuilder sb = new StringBuilder();
        sb.append(obj);
        aVar.a(i, 302002, sb.toString());
        Context context = this.a.a;
        a.a();
        AppMethodBeat.o(20202);
    }

    public final void a(l<String> lVar) {
        int i;
        Throwable th;
        AppMethodBeat.i(20201, false);
        try {
            JSONObject jSONObject = new JSONObject(lVar == null ? "" : (String) lVar.b());
            int optInt = jSONObject.optInt(Constants.KEY_HTTP_CODE);
            try {
                String optString = jSONObject.optString("msg");
                int optInt2 = jSONObject.optInt("status");
                String optString2 = jSONObject.optString("obj");
                String optString3 = jSONObject.optString("seq");
                if (!c.a(optString).booleanValue() || !c.a(optString3).booleanValue() || !c.a(optString2).booleanValue()) {
                    this.a.a(optInt, optString, optInt2, optString2, optString3);
                    Context context = this.a.a;
                    a.a();
                } else {
                    this.a.a(1, "\u670d\u52a1\u7aef\u6570\u636e\u683c\u5f0f\u51fa\u9519", 302003, (Object) null, com.sdk.base.framework.f.g.a.b().a());
                    c.b(a.b(), "\u8fd4\u56de\u6570\u636e\u4e3a\u7a7a", Boolean.valueOf(a.c()));
                }
            } catch (Throwable th2) {
                th = th2;
                i = optInt;
                com.sdk.base.framework.f.g.a.c(th.toString());
                this.a.a(i, "\u670d\u52a1\u7aef\u6570\u636e\u683c\u5f0f\u51fa\u9519", 302003, (Object) null, com.sdk.base.framework.f.g.a.b().a());
                c.b(a.b(), "\u8fd4\u56de\u6570\u636e\u89e3\u6790\u5f02\u5e38\uff1a" + th.toString(), Boolean.valueOf(a.c()));
                AppMethodBeat.o(20201);
            }
        } catch (Throwable th3) {
            th = th3;
            i = 1;
            com.sdk.base.framework.f.g.a.c(th.toString());
            this.a.a(i, "\u670d\u52a1\u7aef\u6570\u636e\u683c\u5f0f\u51fa\u9519", 302003, (Object) null, com.sdk.base.framework.f.g.a.b().a());
            c.b(a.b(), "\u8fd4\u56de\u6570\u636e\u89e3\u6790\u5f02\u5e38\uff1a" + th.toString(), Boolean.valueOf(a.c()));
            AppMethodBeat.o(20201);
        }
        AppMethodBeat.o(20201);
    }
}
