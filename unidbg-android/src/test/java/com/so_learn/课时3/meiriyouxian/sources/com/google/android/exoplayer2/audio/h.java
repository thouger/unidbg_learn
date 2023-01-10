package com.google.android.exoplayer2.audio;

import android.media.AudioTrack;
import android.os.SystemClock;
import android.util.TimeUtils;
import com.google.android.exoplayer2.c;
import com.google.android.exoplayer2.util.z;
import java.lang.reflect.Method;

/* compiled from: AudioTrackPositionTracker */
final class h {
    private final a a;
    private final long[] b;
    private AudioTrack c;
    private int d;
    private int e;
    private g f;
    private int g;
    private boolean h;
    private long i;
    private long j;
    private long k;
    private Method l;
    private long m;
    private boolean n;
    private boolean o;
    private long p;
    private long q;
    private long r;
    private long s;
    private int t;
    private int u;
    private long v;
    private long w;
    private long x;
    private long y;

    /* compiled from: AudioTrackPositionTracker */
    public interface a {
        void a(int i, long j);

        void a(long j);

        void a(long j, long j2, long j3, long j4);

        void b(long j, long j2, long j3, long j4);
    }

    public h(a aVar) {
        this.a = (a) com.google.android.exoplayer2.util.a.a(aVar);
        if (z.a >= 18) {
            try {
                this.l = AudioTrack.class.getMethod("getLatency", null);
            } catch (NoSuchMethodException unused) {
            }
        }
        this.b = new long[10];
    }

    public void a(AudioTrack audioTrack, int i, int i2, int i3) {
        this.c = audioTrack;
        this.d = i2;
        this.e = i3;
        this.f = new g(audioTrack);
        this.g = audioTrack.getSampleRate();
        this.h = a(i);
        this.o = z.c(i);
        this.i = this.o ? g((long) (i3 / i2)) : -9223372036854775807L;
        this.q = 0;
        this.r = 0;
        this.s = 0;
        this.n = false;
        this.v = -9223372036854775807L;
        this.w = -9223372036854775807L;
        this.m = 0;
    }

    public long a(boolean z) {
        long j;
        if (((AudioTrack) com.google.android.exoplayer2.util.a.a(this.c)).getPlayState() == 3) {
            e();
        }
        long nanoTime = System.nanoTime() / 1000;
        g gVar = (g) com.google.android.exoplayer2.util.a.a(this.f);
        if (gVar.c()) {
            long g = g(gVar.g());
            if (!gVar.d()) {
                return g;
            }
            return g + (nanoTime - gVar.f());
        }
        if (this.u == 0) {
            j = h();
        } else {
            j = nanoTime + this.j;
        }
        return !z ? j - this.m : j;
    }

    public void a() {
        ((g) com.google.android.exoplayer2.util.a.a(this.f)).e();
    }

    public boolean b() {
        return ((AudioTrack) com.google.android.exoplayer2.util.a.a(this.c)).getPlayState() == 3;
    }

    public boolean a(long j) {
        a aVar;
        int playState = ((AudioTrack) com.google.android.exoplayer2.util.a.a(this.c)).getPlayState();
        if (this.h) {
            if (playState == 2) {
                this.n = false;
                return false;
            } else if (playState == 1 && i() == 0) {
                return false;
            }
        }
        boolean z = this.n;
        this.n = e(j);
        if (z && !this.n && playState != 1 && (aVar = this.a) != null) {
            aVar.a(this.e, c.a(this.i));
        }
        return true;
    }

    public int b(long j) {
        return this.e - ((int) (j - (i() * ((long) this.d))));
    }

    public boolean c(long j) {
        return this.w != -9223372036854775807L && j > 0 && SystemClock.elapsedRealtime() - this.w >= 200;
    }

    public void d(long j) {
        this.x = i();
        this.v = SystemClock.elapsedRealtime() * 1000;
        this.y = j;
    }

    public boolean e(long j) {
        return j > i() || g();
    }

    public boolean c() {
        f();
        if (this.v != -9223372036854775807L) {
            return false;
        }
        ((g) com.google.android.exoplayer2.util.a.a(this.f)).e();
        return true;
    }

    public void d() {
        f();
        this.c = null;
        this.f = null;
    }

    private void e() {
        long h = h();
        if (h != 0) {
            long nanoTime = System.nanoTime() / 1000;
            if (nanoTime - this.k >= 30000) {
                long[] jArr = this.b;
                int i = this.t;
                jArr[i] = h - nanoTime;
                this.t = (i + 1) % 10;
                int i2 = this.u;
                if (i2 < 10) {
                    this.u = i2 + 1;
                }
                this.k = nanoTime;
                this.j = 0;
                int i3 = 0;
                while (true) {
                    int i4 = this.u;
                    if (i3 >= i4) {
                        break;
                    }
                    this.j += this.b[i3] / ((long) i4);
                    i3++;
                }
            }
            if (!this.h) {
                a(nanoTime, h);
                f(nanoTime);
            }
        }
    }

    private void a(long j, long j2) {
        g gVar = (g) com.google.android.exoplayer2.util.a.a(this.f);
        if (gVar.a(j)) {
            long f = gVar.f();
            long g = gVar.g();
            if (Math.abs(f - j) > 5000000) {
                this.a.b(g, f, j, j2);
                gVar.a();
            } else if (Math.abs(g(g) - j2) > 5000000) {
                this.a.a(g, f, j, j2);
                gVar.a();
            } else {
                gVar.b();
            }
        }
    }

    private void f(long j) {
        Method method;
        if (this.o && (method = this.l) != null && j - this.p >= 500000) {
            try {
                this.m = (((long) ((Integer) z.a((Integer) method.invoke(com.google.android.exoplayer2.util.a.a(this.c), new Object[0]))).intValue()) * 1000) - this.i;
                this.m = Math.max(this.m, 0L);
                if (this.m > 5000000) {
                    this.a.a(this.m);
                    this.m = 0;
                }
            } catch (Exception unused) {
                this.l = null;
            }
            this.p = j;
        }
    }

    private long g(long j) {
        return (j * TimeUtils.NANOS_PER_MS) / ((long) this.g);
    }

    private void f() {
        this.j = 0;
        this.u = 0;
        this.t = 0;
        this.k = 0;
    }

    private boolean g() {
        return this.h && ((AudioTrack) com.google.android.exoplayer2.util.a.a(this.c)).getPlayState() == 2 && i() == 0;
    }

    private static boolean a(int i) {
        return z.a < 23 && (i == 5 || i == 6);
    }

    private long h() {
        return g(i());
    }

    private long i() {
        AudioTrack audioTrack = (AudioTrack) com.google.android.exoplayer2.util.a.a(this.c);
        if (this.v != -9223372036854775807L) {
            return Math.min(this.y, this.x + ((((SystemClock.elapsedRealtime() * 1000) - this.v) * ((long) this.g)) / TimeUtils.NANOS_PER_MS));
        }
        int playState = audioTrack.getPlayState();
        if (playState == 1) {
            return 0;
        }
        long playbackHeadPosition = 4294967295L & ((long) audioTrack.getPlaybackHeadPosition());
        if (this.h) {
            if (playState == 2 && playbackHeadPosition == 0) {
                this.s = this.q;
            }
            playbackHeadPosition += this.s;
        }
        if (z.a <= 28) {
            if (playbackHeadPosition == 0 && this.q > 0 && playState == 3) {
                if (this.w == -9223372036854775807L) {
                    this.w = SystemClock.elapsedRealtime();
                }
                return this.q;
            }
            this.w = -9223372036854775807L;
        }
        if (this.q > playbackHeadPosition) {
            this.r++;
        }
        this.q = playbackHeadPosition;
        return playbackHeadPosition + (this.r << 32);
    }
}
