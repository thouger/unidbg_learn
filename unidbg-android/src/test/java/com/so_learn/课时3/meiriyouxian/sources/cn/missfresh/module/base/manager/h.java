package cn.missfresh.module.base.manager;

import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.network.c;
import cn.missfresh.module.base.network.k;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.alibaba.fastjson.JSONObject;
import java.util.HashMap;

/* compiled from: MiniStartManager */
public class h {
    private static volatile h a;
    private String b = getClass().getSimpleName();

    private h() {
        AppMethodBeat.i(14484, false);
        AppMethodBeat.o(14484);
    }

    public static h a() {
        AppMethodBeat.i(14485, false);
        if (a == null) {
            synchronized (h.class) {
                try {
                    if (a == null) {
                        a = new h();
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(14485);
                    throw th;
                }
            }
        }
        h hVar = a;
        AppMethodBeat.o(14485);
        return hVar;
    }

    public void a(String str, boolean z, String str2) {
        AppMethodBeat.i(14486, false);
        HashMap hashMap = new HashMap();
        if (b.a(str)) {
            str = "";
        }
        hashMap.put("umengToken", str);
        if (b.a(str2)) {
            str2 = "";
        }
        hashMap.put("accessToken", str2);
        hashMap.put("status", z ? "0" : "1");
        c.a(this.b, i.cp, hashMap, new AnonymousClass1());
        AppMethodBeat.o(14486);
    }

    /* compiled from: MiniStartManager */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.manager.h$1  reason: invalid class name */
    public class AnonymousClass1 extends k {
        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.base.network.k
        public void a(JSONObject jSONObject) {
        }

        AnonymousClass1() {
        }
    }

    public boolean b() {
        AppMethodBeat.i(14487, false);
        boolean U = e.U();
        AppMethodBeat.o(14487);
        return U;
    }
}
