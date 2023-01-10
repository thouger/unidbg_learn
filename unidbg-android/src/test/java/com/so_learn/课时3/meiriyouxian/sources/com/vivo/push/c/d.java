package com.vivo.push.c;

import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.vivo.push.b.i;
import com.vivo.push.f;
import com.vivo.push.g;

/* compiled from: OnBindAppReceiveTask */
public final class d extends o {
    public d(g gVar) {
        super(gVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.e
    public final void a(g gVar) {
        AppMethodBeat.i(502, false);
        i iVar = (i) gVar;
        String str = iVar.d;
        com.vivo.push.d.a().a(iVar.f, iVar.g, str);
        if (TextUtils.isEmpty(iVar.f) && !TextUtils.isEmpty(str)) {
            com.vivo.push.d.a().a(str);
        }
        f.b(new AnonymousClass1(str, iVar));
        AppMethodBeat.o(502);
    }

    /* compiled from: OnBindAppReceiveTask */
    /* renamed from: com.vivo.push.c.d$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ i b;

        AnonymousClass1(String str, i iVar) {
            this.a = str;
            this.b = iVar;
        }

        @Override // java.lang.Runnable
        public final void run() {
            AppMethodBeat.i(494, false);
            if (!TextUtils.isEmpty(this.a)) {
                d.this.c.onReceiveRegId(d.this.a, this.a);
            }
            d.this.c.onBind(d.this.a, this.b.g, this.b.c);
            AppMethodBeat.o(494);
        }
    }
}
