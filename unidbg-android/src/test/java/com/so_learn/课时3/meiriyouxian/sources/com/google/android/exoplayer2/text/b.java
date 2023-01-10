package com.google.android.exoplayer2.text;

import com.google.android.exoplayer2.b.g;
import java.nio.ByteBuffer;

/* compiled from: SimpleSubtitleDecoder */
public abstract class b extends g<g, h, SubtitleDecoderException> implements e {
    private final String a;

    /* access modifiers changed from: protected */
    public abstract d a(byte[] bArr, int i, boolean z) throws SubtitleDecoderException;

    @Override // com.google.android.exoplayer2.text.e
    public void a(long j) {
    }

    protected b(String str) {
        super(new g[2], new h[2]);
        this.a = str;
        a(1024);
    }

    /* access modifiers changed from: protected */
    /* renamed from: i */
    public final g g() {
        return new g();
    }

    /* access modifiers changed from: protected */
    /* renamed from: j */
    public final h h() {
        return new c(this);
    }

    /* access modifiers changed from: protected */
    /* renamed from: b */
    public final SubtitleDecoderException a(Throwable th) {
        return new SubtitleDecoderException("Unexpected decode error", th);
    }

    /* access modifiers changed from: protected */
    public final void a(h hVar) {
        super.a((b) hVar);
    }

    /* access modifiers changed from: protected */
    public final SubtitleDecoderException a(g gVar, h hVar, boolean z) {
        try {
            ByteBuffer byteBuffer = gVar.b;
            hVar.a(gVar.c, a(byteBuffer.array(), byteBuffer.limit(), z), gVar.d);
            hVar.c(Integer.MIN_VALUE);
            return null;
        } catch (SubtitleDecoderException e) {
            return e;
        }
    }
}
