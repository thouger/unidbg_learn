package com.vivo.push.b;

import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.a;
import com.vivo.push.g;

/* compiled from: BaseAppCommand */
public class c extends g {
    private long c = -1;
    public String e = null;
    public String f;
    public int g = -1;
    public int h;
    public String i;

    @Override // com.vivo.push.g
    public String toString() {
        return "BaseAppCommand";
    }

    public c(int i, String str) {
        super(i);
        this.f = str;
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public void b(a aVar) {
        AppMethodBeat.i(2103, false);
        aVar.a("req_id", this.e);
        aVar.a("package_name", this.f);
        aVar.a("sdk_version", 305L);
        aVar.a("PUSH_APP_STATUS", this.g);
        if (!TextUtils.isEmpty(this.i)) {
            aVar.a("BaseAppCommand.EXTRA__HYBRIDVERSION", this.i);
        }
        AppMethodBeat.o(2103);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.g
    public void c(a aVar) {
        AppMethodBeat.i(2106, false);
        this.e = aVar.a("req_id");
        this.f = aVar.a("package_name");
        this.c = aVar.b("sdk_version", 0L);
        this.g = aVar.b("PUSH_APP_STATUS", 0);
        this.i = aVar.a("BaseAppCommand.EXTRA__HYBRIDVERSION");
        AppMethodBeat.o(2106);
    }
}
