package com.vivo.push.b;

import com.vivo.push.a;

/* compiled from: OnVerifyReceiveCommand */
public abstract class v extends s {
    public String d;
    public long e;

    public v(int i) {
        super(i);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public void b(a aVar) {
        super.b(aVar);
        aVar.a("OnVerifyCallBackCommand.EXTRA_SECURITY_CONTENT", this.d);
        aVar.a("notify_id", this.e);
    }

    /* access modifiers changed from: protected */
    @Override // com.vivo.push.b.s, com.vivo.push.g
    public void c(a aVar) {
        super.c(aVar);
        this.d = aVar.a("OnVerifyCallBackCommand.EXTRA_SECURITY_CONTENT");
        this.e = aVar.b("notify_id", -1L);
    }
}
