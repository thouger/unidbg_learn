package com.vivo.push;

import android.content.Intent;
import android.os.Bundle;
import com.taobao.accs.common.Constants;
import com.vivo.push.util.n;

/* compiled from: PushCommand */
public abstract class g {
    public int a = -1;
    public String b;

    public boolean a() {
        return false;
    }

    public abstract void b(a aVar);

    public abstract void c(a aVar);

    public g(int i) {
        if (i >= 0) {
            this.a = i;
            return;
        }
        throw new IllegalArgumentException("PushCommand: the value of command must > 0.");
    }

    public final void a(a aVar) {
        String a = h.a(this.a);
        if (a == null) {
            a = "";
        }
        aVar.a("method", a);
        d(aVar);
    }

    public final void a(Intent intent) {
        a a = a.a(intent);
        if (a == null) {
            n.b("PushCommand", "bundleWapper is null");
            return;
        }
        a.a("method", this.a);
        d(a);
        Bundle bundle = a.a;
        if (bundle != null) {
            intent.putExtras(bundle);
        }
    }

    private final void d(a aVar) {
        aVar.a(Constants.KEY_COMMAND, this.a);
        aVar.a("client_pkgname", this.b);
        b(aVar);
    }

    public String toString() {
        return getClass().getSimpleName();
    }
}
