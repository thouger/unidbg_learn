package com.google.android.exoplayer2.audio;

import android.util.TimeUtils;
import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.util.z;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: SilenceSkippingAudioProcessor */
public final class o implements AudioProcessor {
    private int b = -1;
    private int c = -1;
    private int d;
    private boolean e;
    private ByteBuffer f = a;
    private ByteBuffer g = a;
    private boolean h;
    private byte[] i = z.f;
    private byte[] j = z.f;
    private int k;
    private int l;
    private int m;
    private boolean n;
    private long o;

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int c() {
        return 2;
    }

    public void a(boolean z) {
        this.e = z;
        h();
    }

    public long j() {
        return this.o;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean a(int i, int i2, int i3) throws AudioProcessor.UnhandledFormatException {
        if (i3 != 2) {
            throw new AudioProcessor.UnhandledFormatException(i, i2, i3);
        } else if (this.c == i && this.b == i2) {
            return false;
        } else {
            this.c = i;
            this.b = i2;
            this.d = i2 * 2;
            return true;
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean a() {
        return this.c != -1 && this.e;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int b() {
        return this.b;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int d() {
        return this.c;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void a(ByteBuffer byteBuffer) {
        while (byteBuffer.hasRemaining() && !this.g.hasRemaining()) {
            int i = this.k;
            if (i == 0) {
                b(byteBuffer);
            } else if (i == 1) {
                c(byteBuffer);
            } else if (i == 2) {
                d(byteBuffer);
            } else {
                throw new IllegalStateException();
            }
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void e() {
        this.h = true;
        int i = this.l;
        if (i > 0) {
            a(this.i, i);
        }
        if (!this.n) {
            this.o += (long) (this.m / this.d);
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public ByteBuffer f() {
        ByteBuffer byteBuffer = this.g;
        this.g = a;
        return byteBuffer;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean g() {
        return this.h && this.g == a;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void h() {
        if (a()) {
            int a = a(150000L) * this.d;
            if (this.i.length != a) {
                this.i = new byte[a];
            }
            this.m = a(20000L) * this.d;
            int length = this.j.length;
            int i = this.m;
            if (length != i) {
                this.j = new byte[i];
            }
        }
        this.k = 0;
        this.g = a;
        this.h = false;
        this.o = 0;
        this.l = 0;
        this.n = false;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void i() {
        this.e = false;
        h();
        this.f = a;
        this.b = -1;
        this.c = -1;
        this.m = 0;
        this.i = z.f;
        this.j = z.f;
    }

    private void b(ByteBuffer byteBuffer) {
        int limit = byteBuffer.limit();
        byteBuffer.limit(Math.min(limit, byteBuffer.position() + this.i.length));
        int g = g(byteBuffer);
        if (g == byteBuffer.position()) {
            this.k = 1;
        } else {
            byteBuffer.limit(g);
            e(byteBuffer);
        }
        byteBuffer.limit(limit);
    }

    private void c(ByteBuffer byteBuffer) {
        int limit = byteBuffer.limit();
        int f = f(byteBuffer);
        int position = f - byteBuffer.position();
        byte[] bArr = this.i;
        int length = bArr.length;
        int i = this.l;
        int i2 = length - i;
        if (f >= limit || position >= i2) {
            int min = Math.min(position, i2);
            byteBuffer.limit(byteBuffer.position() + min);
            byteBuffer.get(this.i, this.l, min);
            this.l += min;
            int i3 = this.l;
            byte[] bArr2 = this.i;
            if (i3 == bArr2.length) {
                if (this.n) {
                    a(bArr2, this.m);
                    this.o += (long) ((this.l - (this.m * 2)) / this.d);
                } else {
                    this.o += (long) ((i3 - this.m) / this.d);
                }
                a(byteBuffer, this.i, this.l);
                this.l = 0;
                this.k = 2;
            }
            byteBuffer.limit(limit);
            return;
        }
        a(bArr, i);
        this.l = 0;
        this.k = 0;
    }

    private void d(ByteBuffer byteBuffer) {
        int limit = byteBuffer.limit();
        int f = f(byteBuffer);
        byteBuffer.limit(f);
        this.o += (long) (byteBuffer.remaining() / this.d);
        a(byteBuffer, this.j, this.m);
        if (f < limit) {
            a(this.j, this.m);
            this.k = 0;
            byteBuffer.limit(limit);
        }
    }

    private void a(byte[] bArr, int i) {
        a(i);
        this.f.put(bArr, 0, i);
        this.f.flip();
        this.g = this.f;
    }

    private void e(ByteBuffer byteBuffer) {
        a(byteBuffer.remaining());
        this.f.put(byteBuffer);
        this.f.flip();
        this.g = this.f;
    }

    private void a(int i) {
        if (this.f.capacity() < i) {
            this.f = ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
        } else {
            this.f.clear();
        }
        if (i > 0) {
            this.n = true;
        }
    }

    private void a(ByteBuffer byteBuffer, byte[] bArr, int i) {
        int min = Math.min(byteBuffer.remaining(), this.m);
        int i2 = this.m - min;
        System.arraycopy(bArr, i - i2, this.j, 0, i2);
        byteBuffer.position(byteBuffer.limit() - min);
        byteBuffer.get(this.j, i2, min);
    }

    private int a(long j) {
        return (int) ((j * ((long) this.c)) / TimeUtils.NANOS_PER_MS);
    }

    private int f(ByteBuffer byteBuffer) {
        for (int position = byteBuffer.position() + 1; position < byteBuffer.limit(); position += 2) {
            if (Math.abs((int) byteBuffer.get(position)) > 4) {
                int i = this.d;
                return i * (position / i);
            }
        }
        return byteBuffer.limit();
    }

    private int g(ByteBuffer byteBuffer) {
        for (int limit = byteBuffer.limit() - 1; limit >= byteBuffer.position(); limit -= 2) {
            if (Math.abs((int) byteBuffer.get(limit)) > 4) {
                int i = this.d;
                return ((limit / i) * i) + i;
            }
        }
        return byteBuffer.position();
    }
}
