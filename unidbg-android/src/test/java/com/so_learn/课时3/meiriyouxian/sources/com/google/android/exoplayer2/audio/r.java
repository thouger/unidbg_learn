package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.util.z;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* compiled from: TrimmingAudioProcessor */
final class r implements AudioProcessor {
    private boolean b;
    private int c;
    private int d;
    private int e = -1;
    private int f = -1;
    private int g;
    private boolean h;
    private int i;
    private ByteBuffer j = a;
    private ByteBuffer k = a;
    private byte[] l = z.f;
    private int m;
    private boolean n;
    private long o;

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int c() {
        return 2;
    }

    public void a(int i, int i2) {
        this.c = i;
        this.d = i2;
    }

    public void j() {
        this.o = 0;
    }

    public long k() {
        return this.o;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean a(int i, int i2, int i3) throws AudioProcessor.UnhandledFormatException {
        if (i3 == 2) {
            int i4 = this.m;
            if (i4 > 0) {
                this.o += (long) (i4 / this.g);
            }
            this.e = i2;
            this.f = i;
            this.g = z.b(2, i2);
            int i5 = this.d;
            int i6 = this.g;
            this.l = new byte[(i5 * i6)];
            this.m = 0;
            int i7 = this.c;
            this.i = i6 * i7;
            boolean z = this.b;
            this.b = (i7 == 0 && i5 == 0) ? false : true;
            this.h = false;
            if (z != this.b) {
                return true;
            }
            return false;
        }
        throw new AudioProcessor.UnhandledFormatException(i, i2, i3);
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean a() {
        return this.b;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int b() {
        return this.e;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public int d() {
        return this.f;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void a(ByteBuffer byteBuffer) {
        int position = byteBuffer.position();
        int limit = byteBuffer.limit();
        int i = limit - position;
        if (i != 0) {
            this.h = true;
            int min = Math.min(i, this.i);
            this.o += (long) (min / this.g);
            this.i -= min;
            byteBuffer.position(position + min);
            if (this.i <= 0) {
                int i2 = i - min;
                int length = (this.m + i2) - this.l.length;
                if (this.j.capacity() < length) {
                    this.j = ByteBuffer.allocateDirect(length).order(ByteOrder.nativeOrder());
                } else {
                    this.j.clear();
                }
                int a = z.a(length, 0, this.m);
                this.j.put(this.l, 0, a);
                int a2 = z.a(length - a, 0, i2);
                byteBuffer.limit(byteBuffer.position() + a2);
                this.j.put(byteBuffer);
                byteBuffer.limit(limit);
                int i3 = i2 - a2;
                this.m -= a;
                byte[] bArr = this.l;
                System.arraycopy(bArr, a, bArr, 0, this.m);
                byteBuffer.get(this.l, this.m, i3);
                this.m += i3;
                this.j.flip();
                this.k = this.j;
            }
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void e() {
        this.n = true;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public ByteBuffer f() {
        ByteBuffer byteBuffer = this.k;
        if (this.n && this.m > 0 && byteBuffer == a) {
            int capacity = this.j.capacity();
            int i = this.m;
            if (capacity < i) {
                this.j = ByteBuffer.allocateDirect(i).order(ByteOrder.nativeOrder());
            } else {
                this.j.clear();
            }
            this.j.put(this.l, 0, this.m);
            this.m = 0;
            this.j.flip();
            byteBuffer = this.j;
        }
        this.k = a;
        return byteBuffer;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public boolean g() {
        return this.n && this.m == 0 && this.k == a;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void h() {
        this.k = a;
        this.n = false;
        if (this.h) {
            this.i = 0;
        }
        this.m = 0;
    }

    @Override // com.google.android.exoplayer2.audio.AudioProcessor
    public void i() {
        h();
        this.j = a;
        this.e = -1;
        this.f = -1;
        this.l = z.f;
    }
}
