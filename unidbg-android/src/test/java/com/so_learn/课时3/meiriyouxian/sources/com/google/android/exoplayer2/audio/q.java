package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.util.z;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.ShortBuffer;

/* compiled from: SonicAudioProcessor */
public final class q implements AudioProcessor {
    private int b = -1;
    private int c = -1;
    private float d = 1.0f;
    private float e = 1.0f;
    private int f = -1;
    private int g = -1;
    private p h;
    private ByteBuffer i = a;
    private ShortBuffer j = this.i.asShortBuffer();
    private ByteBuffer k = a;
    private long l;
    private long m;
    private boolean n;

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int c() {
        return 2;
    }

    public float a(float f) {
        float a = z.a(f, 0.1f, 8.0f);
        if (this.d != a) {
            this.d = a;
            this.h = null;
        }
        h();
        return a;
    }

    public float b(float f) {
        float a = z.a(f, 0.1f, 8.0f);
        if (this.e != a) {
            this.e = a;
            this.h = null;
        }
        h();
        return a;
    }

    public long a(long j) {
        long j2 = this.m;
        if (j2 < 1024) {
            return (long) (((double) this.d) * ((double) j));
        }
        int i = this.f;
        int i2 = this.c;
        if (i == i2) {
            return z.d(j, this.l, j2);
        }
        return z.d(j, this.l * ((long) i), j2 * ((long) i2));
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean a(int i, int i2, int i3) throws AudioProcessor.UnhandledFormatException {
        if (i3 == 2) {
            int i4 = this.g;
            if (i4 == -1) {
                i4 = i;
            }
            if (this.c == i && this.b == i2 && this.f == i4) {
                return false;
            }
            this.c = i;
            this.b = i2;
            this.f = i4;
            this.h = null;
            return true;
        }
        throw new AudioProcessor.UnhandledFormatException(i, i2, i3);
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean a() {
        return this.c != -1 && (Math.abs(this.d - 1.0f) >= 0.01f || Math.abs(this.e - 1.0f) >= 0.01f || this.f != this.c);
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int b() {
        return this.b;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int d() {
        return this.f;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void a(ByteBuffer byteBuffer) {
        a.b(this.h != null);
        if (byteBuffer.hasRemaining()) {
            ShortBuffer asShortBuffer = byteBuffer.asShortBuffer();
            int remaining = byteBuffer.remaining();
            this.l += (long) remaining;
            this.h.a(asShortBuffer);
            byteBuffer.position(byteBuffer.position() + remaining);
        }
        int c = this.h.c() * this.b * 2;
        if (c > 0) {
            if (this.i.capacity() < c) {
                this.i = ByteBuffer.allocateDirect(c).order(ByteOrder.nativeOrder());
                this.j = this.i.asShortBuffer();
            } else {
                this.i.clear();
                this.j.clear();
            }
            this.h.b(this.j);
            this.m += (long) c;
            this.i.limit(c);
            this.k = this.i;
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void e() {
        a.b(this.h != null);
        this.h.a();
        this.n = true;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public ByteBuffer f() {
        ByteBuffer byteBuffer = this.k;
        this.k = a;
        return byteBuffer;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean g() {
        p pVar;
        return this.n && ((pVar = this.h) == null || pVar.c() == 0);
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void h() {
        if (a()) {
            p pVar = this.h;
            if (pVar == null) {
                this.h = new p(this.c, this.b, this.d, this.e, this.f);
            } else {
                pVar.b();
            }
        }
        this.k = a;
        this.l = 0;
        this.m = 0;
        this.n = false;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void i() {
        this.d = 1.0f;
        this.e = 1.0f;
        this.b = -1;
        this.c = -1;
        this.f = -1;
        this.i = a;
        this.j = this.i.asShortBuffer();
        this.k = a;
        this.g = -1;
        this.h = null;
        this.l = 0;
        this.m = 0;
        this.n = false;
    }
}
