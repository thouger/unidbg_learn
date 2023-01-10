package com.google.android.exoplayer2.source.a;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.upstream.f;
import com.google.android.exoplayer2.upstream.h;
import com.google.android.exoplayer2.util.z;
import java.io.IOException;
import java.util.Arrays;

/* compiled from: DataChunk */
public abstract class j extends d {
    private byte[] a;
    private volatile boolean b;

    /* access modifiers changed from: protected */
    public abstract void a(byte[] bArr, int i) throws IOException;

    public j(f fVar, h hVar, int i, Format format, int i2, Object obj, byte[] bArr) {
        super(fVar, hVar, i, format, i2, obj, -9223372036854775807L, -9223372036854775807L);
        this.a = bArr;
    }

    public byte[] c() {
        return this.a;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.d
    public final void a() {
        this.b = true;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.d
    public final void b() throws IOException, InterruptedException {
        try {
            this.j.a(this.c);
            int i = 0;
            int i2 = 0;
            while (i != -1 && !this.b) {
                a(i2);
                i = this.j.a(this.a, i2, 16384);
                if (i != -1) {
                    i2 += i;
                }
            }
            if (!this.b) {
                a(this.a, i2);
            }
        } finally {
            z.a((f) this.j);
        }
    }

    private void a(int i) {
        byte[] bArr = this.a;
        if (bArr == null) {
            this.a = new byte[16384];
        } else if (bArr.length < i + 16384) {
            this.a = Arrays.copyOf(bArr, bArr.length + 16384);
        }
    }
}
