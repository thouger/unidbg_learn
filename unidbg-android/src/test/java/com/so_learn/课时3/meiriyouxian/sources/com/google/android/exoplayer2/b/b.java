package com.google.android.exoplayer2.b;

import android.media.MediaCodec;
import com.google.android.exoplayer2.util.z;

/* compiled from: CryptoInfo */
public final class b {
    public byte[] a;
    public byte[] b;
    public int c;
    public int[] d;
    public int[] e;
    public int f;
    public int g;
    public int h;
    private final MediaCodec.CryptoInfo i;
    private final a j;

    public b() {
        this.i = z.a >= 16 ? b() : null;
        this.j = z.a >= 24 ? new a(this.i) : null;
    }

    public void a(int i, int[] iArr, int[] iArr2, byte[] bArr, byte[] bArr2, int i2, int i3, int i4) {
        this.f = i;
        this.d = iArr;
        this.e = iArr2;
        this.b = bArr;
        this.a = bArr2;
        this.c = i2;
        this.g = i3;
        this.h = i4;
        if (z.a >= 16) {
            c();
        }
    }

    public MediaCodec.CryptoInfo a() {
        return this.i;
    }

    private MediaCodec.CryptoInfo b() {
        return new MediaCodec.CryptoInfo();
    }

    private void c() {
        MediaCodec.CryptoInfo cryptoInfo = this.i;
        cryptoInfo.numSubSamples = this.f;
        cryptoInfo.numBytesOfClearData = this.d;
        cryptoInfo.numBytesOfEncryptedData = this.e;
        cryptoInfo.key = this.b;
        cryptoInfo.iv = this.a;
        cryptoInfo.mode = this.c;
        if (z.a >= 24) {
            this.j.a(this.g, this.h);
        }
    }

    /* compiled from: CryptoInfo */
    /* access modifiers changed from: private */
    public static final class a {
        private final MediaCodec.CryptoInfo a;
        private final MediaCodec.CryptoInfo.Pattern b;

        private a(MediaCodec.CryptoInfo cryptoInfo) {
            this.a = cryptoInfo;
            this.b = new MediaCodec.CryptoInfo.Pattern(0, 0);
        }

        /* access modifiers changed from: private */
        /* access modifiers changed from: public */
        private void a(int i, int i2) {
            this.b.set(i, i2);
            this.a.setPattern(this.b);
        }
    }
}
