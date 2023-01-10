package com.google.android.exoplayer2.text.b;

import com.google.android.exoplayer2.text.b;
import com.google.android.exoplayer2.util.o;
import java.util.List;

/* compiled from: DvbDecoder */
public final class a extends b {
    private final b a;

    public a(List<byte[]> list) {
        super("DvbDecoder");
        o oVar = new o(list.get(0));
        this.a = new b(oVar.i(), oVar.i());
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public c a(byte[] bArr, int i, boolean z) {
        if (z) {
            this.a.a();
        }
        return new c(this.a.a(bArr, i));
    }
}
