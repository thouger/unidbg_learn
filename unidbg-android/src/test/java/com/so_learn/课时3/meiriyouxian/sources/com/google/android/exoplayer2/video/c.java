package com.google.android.exoplayer2.video;

import android.app.job.JobInfo;
import android.bluetooth.le.AdvertisingSetParameters;
import android.content.Context;
import android.graphics.Point;
import android.hardware.camera2.legacy.LegacyCameraDevice;
import android.media.MediaCodec;
import android.media.MediaCodecInfo;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Surface;
import com.android.internal.logging.nano.MetricsProto;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.e;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.util.i;
import com.google.android.exoplayer2.util.l;
import com.google.android.exoplayer2.util.x;
import com.google.android.exoplayer2.util.z;
import com.google.android.exoplayer2.video.f;
import java.nio.ByteBuffer;
import java.util.List;

/* compiled from: MediaCodecVideoRenderer */
public class c extends MediaCodecRenderer {
    private static final int[] c = {LegacyCameraDevice.MAX_DIMEN_FOR_ROUNDING, AdvertisingSetParameters.INTERVAL_HIGH, 1440, 1280, 960, MetricsProto.MetricsEvent.FIELD_SETTINGS_PREFERENCE_CHANGE_NAME, 640, MetricsProto.MetricsEvent.DIALOG_HIGH_POWER_DETAILS, 480};
    private static boolean d;
    private static boolean e;
    private int A;
    private float B;
    private int C;
    private int D;
    private int E;
    private float F;
    private int G;
    private int H;
    private int I;
    private float J;
    private boolean K;
    private int L;
    private long M;
    private long N;
    private int O;
    private d P;
    b b;
    private final Context f;
    private final VideoFrameReleaseTimeHelper g = new VideoFrameReleaseTimeHelper(this.f);
    private final f.a h;
    private final long i;
    private final int j;
    private final boolean k;
    private final long[] l;
    private final long[] m;
    private a n;
    private boolean o;
    private Surface p;
    private Surface q;
    private int r;
    private boolean s;
    private long t;
    private long u;
    private long v;
    private int w;
    private int x;
    private int y;
    private long z;

    private static boolean f(long j) {
        return j < -30000;
    }

    private static boolean g(long j) {
        return j < -500000;
    }

    public c(Context context, com.google.android.exoplayer2.mediacodec.b bVar, long j, com.google.android.exoplayer2.drm.b<e> bVar2, boolean z, Handler handler, f fVar, int i) {
        super(2, bVar, bVar2, z, 30.0f);
        this.i = j;
        this.j = i;
        this.f = context.getApplicationContext();
        this.h = new f.a(handler, fVar);
        this.k = M();
        this.l = new long[10];
        this.m = new long[10];
        this.N = -9223372036854775807L;
        this.M = -9223372036854775807L;
        this.u = -9223372036854775807L;
        this.C = -1;
        this.D = -1;
        this.F = -1.0f;
        this.B = -1.0f;
        this.r = 1;
        I();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public int a(com.google.android.exoplayer2.mediacodec.b bVar, com.google.android.exoplayer2.drm.b<e> bVar2, Format format) throws MediaCodecUtil.DecoderQueryException {
        boolean z;
        int i = 0;
        if (!l.b(format.g)) {
            return 0;
        }
        DrmInitData drmInitData = format.j;
        if (drmInitData != null) {
            z = false;
            for (int i2 = 0; i2 < drmInitData.b; i2++) {
                z |= drmInitData.a(i2).d;
            }
        } else {
            z = false;
        }
        List<com.google.android.exoplayer2.mediacodec.a> a2 = bVar.a(format.g, z);
        if (a2.isEmpty()) {
            if (!z || bVar.a(format.g, false).isEmpty()) {
                return 1;
            }
            return 2;
        } else if (!a(bVar2, drmInitData)) {
            return 2;
        } else {
            com.google.android.exoplayer2.mediacodec.a aVar = a2.get(0);
            boolean a3 = aVar.a(format);
            int i3 = aVar.b(format) ? 16 : 8;
            if (aVar.e) {
                i = 32;
            }
            return (a3 ? 4 : 3) | i3 | i;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.b
    public void a(boolean z) throws ExoPlaybackException {
        super.a(z);
        this.L = r().b;
        this.K = this.L != 0;
        this.h.a(this.a);
        this.g.a();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.b
    public void a(Format[] formatArr, long j) throws ExoPlaybackException {
        if (this.N == -9223372036854775807L) {
            this.N = j;
        } else {
            int i = this.O;
            if (i == this.l.length) {
                i.c("MediaCodecVideoRenderer", "Too many stream changes, so dropping offset: " + this.l[this.O - 1]);
            } else {
                this.O = i + 1;
            }
            long[] jArr = this.l;
            int i2 = this.O;
            jArr[i2 - 1] = j;
            this.m[i2 - 1] = this.M;
        }
        super.a(formatArr, j);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.b
    public void a(long j, boolean z) throws ExoPlaybackException {
        super.a(j, z);
        G();
        this.t = -9223372036854775807L;
        this.x = 0;
        this.M = -9223372036854775807L;
        int i = this.O;
        if (i != 0) {
            this.N = this.l[i - 1];
            this.O = 0;
        }
        if (z) {
            F();
        } else {
            this.u = -9223372036854775807L;
        }
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.t
    public boolean u() {
        Surface surface;
        if (super.u() && (this.s || (((surface = this.q) != null && this.p == surface) || A() == null || this.K))) {
            this.u = -9223372036854775807L;
            return true;
        } else if (this.u == -9223372036854775807L) {
            return false;
        } else {
            if (SystemClock.elapsedRealtime() < this.u) {
                return true;
            }
            this.u = -9223372036854775807L;
            return false;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.b
    public void n() {
        super.n();
        this.w = 0;
        this.v = SystemClock.elapsedRealtime();
        this.z = SystemClock.elapsedRealtime() * 1000;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.b
    public void o() {
        this.u = -9223372036854775807L;
        L();
        super.o();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.b
    public void p() {
        this.C = -1;
        this.D = -1;
        this.F = -1.0f;
        this.B = -1.0f;
        this.N = -9223372036854775807L;
        this.M = -9223372036854775807L;
        this.O = 0;
        I();
        G();
        this.g.b();
        this.b = null;
        this.K = false;
        try {
            super.p();
        } finally {
            this.a.a();
            this.h.b(this.a);
        }
    }

    @Override // com.google.android.exoplayer2.b, com.google.android.exoplayer2.s.b
    public void a(int i, Object obj) throws ExoPlaybackException {
        if (i == 1) {
            a((Surface) obj);
        } else if (i == 4) {
            this.r = ((Integer) obj).intValue();
            MediaCodec A = A();
            if (A != null) {
                A.setVideoScalingMode(this.r);
            }
        } else if (i == 6) {
            this.P = (d) obj;
        } else {
            super.a(i, obj);
        }
    }

    private void a(Surface surface) throws ExoPlaybackException {
        if (surface == null) {
            Surface surface2 = this.q;
            if (surface2 != null) {
                surface = surface2;
            } else {
                com.google.android.exoplayer2.mediacodec.a B = B();
                if (B != null && b(B)) {
                    this.q = DummySurface.a(this.f, B.f);
                    surface = this.q;
                }
            }
        }
        if (this.p != surface) {
            this.p = surface;
            int ac_ = ac_();
            if (ac_ == 1 || ac_ == 2) {
                MediaCodec A = A();
                if (z.a < 23 || A == null || surface == null || this.o) {
                    C();
                    y();
                } else {
                    a(A, surface);
                }
            }
            if (surface == null || surface == this.q) {
                I();
                G();
                return;
            }
            K();
            G();
            if (ac_ == 2) {
                F();
            }
        } else if (surface != null && surface != this.q) {
            K();
            H();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public boolean a(com.google.android.exoplayer2.mediacodec.a aVar) {
        return this.p != null || b(aVar);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public boolean z() {
        return this.K;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void a(com.google.android.exoplayer2.mediacodec.a aVar, MediaCodec mediaCodec, Format format, MediaCrypto mediaCrypto, float f) throws MediaCodecUtil.DecoderQueryException {
        this.n = a(aVar, format, q());
        MediaFormat a2 = a(format, this.n, f, this.k, this.L);
        if (this.p == null) {
            com.google.android.exoplayer2.util.a.b(b(aVar));
            if (this.q == null) {
                this.q = DummySurface.a(this.f, aVar.f);
            }
            this.p = this.q;
        }
        mediaCodec.configure(a2, this.p, mediaCrypto, 0);
        if (z.a >= 23 && this.K) {
            this.b = new b(mediaCodec);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public int a(MediaCodec mediaCodec, com.google.android.exoplayer2.mediacodec.a aVar, Format format, Format format2) {
        if (!aVar.a(format, format2, true) || format2.l > this.n.a || format2.m > this.n.b || b(aVar, format2) > this.n.c) {
            return 0;
        }
        if (format.b(format2)) {
            return 1;
        }
        return 3;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void C() {
        int i = 0;
        Surface surface = null;
        try {
            super.C();
        } finally {
            this.y = i;
            Surface surface2 = this.q;
            if (surface2 != null) {
                if (this.p == surface2) {
                    this.p = surface;
                }
                this.q.release();
                this.q = surface;
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void D() throws ExoPlaybackException {
        super.D();
        this.y = 0;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public float a(float f, Format format, Format[] formatArr) {
        float f2 = -1.0f;
        for (Format format2 : formatArr) {
            float f3 = format2.n;
            if (f3 != -1.0f) {
                f2 = Math.max(f2, f3);
            }
        }
        if (f2 == -1.0f) {
            return -1.0f;
        }
        return f2 * f;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void a(String str, long j, long j2) {
        this.h.a(str, j, j2);
        this.o = a(str);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void b(Format format) throws ExoPlaybackException {
        super.b(format);
        this.h.a(format);
        this.B = format.p;
        this.A = format.o;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void a(com.google.android.exoplayer2.b.e eVar) {
        this.y++;
        this.M = Math.max(eVar.c, this.M);
        if (z.a < 23 && this.K) {
            e(eVar.c);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void a(MediaCodec mediaCodec, MediaFormat mediaFormat) {
        int i;
        int i2;
        boolean z = mediaFormat.containsKey("crop-right") && mediaFormat.containsKey("crop-left") && mediaFormat.containsKey("crop-bottom") && mediaFormat.containsKey("crop-top");
        if (z) {
            i = (mediaFormat.getInteger("crop-right") - mediaFormat.getInteger("crop-left")) + 1;
        } else {
            i = mediaFormat.getInteger("width");
        }
        if (z) {
            i2 = (mediaFormat.getInteger("crop-bottom") - mediaFormat.getInteger("crop-top")) + 1;
        } else {
            i2 = mediaFormat.getInteger("height");
        }
        a(mediaCodec, i, i2);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public boolean a(long j, long j2, MediaCodec mediaCodec, ByteBuffer byteBuffer, int i, int i2, long j3, boolean z, Format format) throws ExoPlaybackException {
        if (this.t == -9223372036854775807L) {
            this.t = j;
        }
        long j4 = j3 - this.N;
        if (z) {
            a(mediaCodec, i, j4);
            return true;
        }
        long j5 = j3 - j;
        if (this.p != this.q) {
            long elapsedRealtime = SystemClock.elapsedRealtime() * 1000;
            boolean z2 = ac_() == 2;
            if (!this.s || (z2 && d(j5, elapsedRealtime - this.z))) {
                long nanoTime = System.nanoTime();
                a(j4, nanoTime, format);
                if (z.a >= 21) {
                    b(mediaCodec, i, j4, nanoTime);
                    return true;
                }
                c(mediaCodec, i, j4);
                return true;
            }
            if (z2 && j != this.t) {
                long nanoTime2 = System.nanoTime();
                long a2 = this.g.a(j3, ((j5 - (elapsedRealtime - j2)) * 1000) + nanoTime2);
                long j6 = (a2 - nanoTime2) / 1000;
                if (c(j6, j2) && a(mediaCodec, i, j4, j)) {
                    return false;
                }
                if (b(j6, j2)) {
                    b(mediaCodec, i, j4);
                    return true;
                } else if (z.a >= 21) {
                    if (j6 < 50000) {
                        a(j4, a2, format);
                        b(mediaCodec, i, j4, a2);
                        return true;
                    }
                } else if (j6 < 30000) {
                    if (j6 > 11000) {
                        try {
                            Thread.sleep((j6 - JobInfo.MIN_BACKOFF_MILLIS) / 1000);
                        } catch (InterruptedException unused) {
                            Thread.currentThread().interrupt();
                            return false;
                        }
                    }
                    a(j4, a2, format);
                    c(mediaCodec, i, j4);
                    return true;
                }
            }
            return false;
        } else if (!f(j5)) {
            return false;
        } else {
            a(mediaCodec, i, j4);
            return true;
        }
    }

    private void a(MediaCodec mediaCodec, int i, int i2) {
        this.C = i;
        this.D = i2;
        this.F = this.B;
        if (z.a >= 21) {
            int i3 = this.A;
            if (i3 == 90 || i3 == 270) {
                int i4 = this.C;
                this.C = this.D;
                this.D = i4;
                this.F = 1.0f / this.F;
            }
        } else {
            this.E = this.A;
        }
        mediaCodec.setVideoScalingMode(this.r);
    }

    private void a(long j, long j2, Format format) {
        d dVar = this.P;
        if (dVar != null) {
            dVar.a(j, j2, format);
        }
    }

    /* access modifiers changed from: protected */
    public void e(long j) {
        Format d2 = d(j);
        if (d2 != null) {
            a(A(), d2.l, d2.m);
        }
        J();
        w();
        c(j);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void c(long j) {
        this.y--;
        while (true) {
            int i = this.O;
            if (i != 0 && j >= this.m[0]) {
                long[] jArr = this.l;
                this.N = jArr[0];
                this.O = i - 1;
                System.arraycopy(jArr, 1, jArr, 0, this.O);
                long[] jArr2 = this.m;
                System.arraycopy(jArr2, 1, jArr2, 0, this.O);
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: protected */
    public boolean b(long j, long j2) {
        return f(j);
    }

    /* access modifiers changed from: protected */
    public boolean c(long j, long j2) {
        return g(j);
    }

    /* access modifiers changed from: protected */
    public boolean d(long j, long j2) {
        return f(j) && j2 > 100000;
    }

    /* access modifiers changed from: protected */
    public void a(MediaCodec mediaCodec, int i, long j) {
        x.a("skipVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        x.a();
        this.a.f++;
    }

    /* access modifiers changed from: protected */
    public void b(MediaCodec mediaCodec, int i, long j) {
        x.a("dropVideoBuffer");
        mediaCodec.releaseOutputBuffer(i, false);
        x.a();
        b(1);
    }

    /* access modifiers changed from: protected */
    public boolean a(MediaCodec mediaCodec, int i, long j, long j2) throws ExoPlaybackException {
        int b2 = b(j2);
        if (b2 == 0) {
            return false;
        }
        this.a.i++;
        b(this.y + b2);
        D();
        return true;
    }

    /* access modifiers changed from: protected */
    public void b(int i) {
        this.a.g += i;
        this.w += i;
        this.x += i;
        this.a.h = Math.max(this.x, this.a.h);
        int i2 = this.j;
        if (i2 > 0 && this.w >= i2) {
            L();
        }
    }

    /* access modifiers changed from: protected */
    public void c(MediaCodec mediaCodec, int i, long j) {
        J();
        x.a("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, true);
        x.a();
        this.z = SystemClock.elapsedRealtime() * 1000;
        this.a.e++;
        this.x = 0;
        w();
    }

    /* access modifiers changed from: protected */
    public void b(MediaCodec mediaCodec, int i, long j, long j2) {
        J();
        x.a("releaseOutputBuffer");
        mediaCodec.releaseOutputBuffer(i, j2);
        x.a();
        this.z = SystemClock.elapsedRealtime() * 1000;
        this.a.e++;
        this.x = 0;
        w();
    }

    private boolean b(com.google.android.exoplayer2.mediacodec.a aVar) {
        return z.a >= 23 && !this.K && !a(aVar.a) && (!aVar.f || DummySurface.a(this.f));
    }

    private void F() {
        this.u = this.i > 0 ? SystemClock.elapsedRealtime() + this.i : -9223372036854775807L;
    }

    private void G() {
        MediaCodec A;
        this.s = false;
        if (z.a >= 23 && this.K && (A = A()) != null) {
            this.b = new b(A);
        }
    }

    /* access modifiers changed from: package-private */
    public void w() {
        if (!this.s) {
            this.s = true;
            this.h.a(this.p);
        }
    }

    private void H() {
        if (this.s) {
            this.h.a(this.p);
        }
    }

    private void I() {
        this.G = -1;
        this.H = -1;
        this.J = -1.0f;
        this.I = -1;
    }

    private void J() {
        if (this.C != -1 || this.D != -1) {
            if (this.G != this.C || this.H != this.D || this.I != this.E || this.J != this.F) {
                this.h.a(this.C, this.D, this.E, this.F);
                this.G = this.C;
                this.H = this.D;
                this.I = this.E;
                this.J = this.F;
            }
        }
    }

    private void K() {
        if (this.G != -1 || this.H != -1) {
            this.h.a(this.G, this.H, this.I, this.J);
        }
    }

    private void L() {
        if (this.w > 0) {
            long elapsedRealtime = SystemClock.elapsedRealtime();
            this.h.a(this.w, elapsedRealtime - this.v);
            this.w = 0;
            this.v = elapsedRealtime;
        }
    }

    private static void a(MediaCodec mediaCodec, Surface surface) {
        mediaCodec.setOutputSurface(surface);
    }

    private static void a(MediaFormat mediaFormat, int i) {
        mediaFormat.setFeatureEnabled(MediaCodecInfo.CodecCapabilities.FEATURE_TunneledPlayback, true);
        mediaFormat.setInteger(MediaFormat.KEY_AUDIO_SESSION_ID, i);
    }

    /* access modifiers changed from: protected */
    public MediaFormat a(Format format, a aVar, float f, boolean z, int i) {
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString(MediaFormat.KEY_MIME, format.g);
        mediaFormat.setInteger("width", format.l);
        mediaFormat.setInteger("height", format.m);
        com.google.android.exoplayer2.mediacodec.c.a(mediaFormat, format.i);
        com.google.android.exoplayer2.mediacodec.c.a(mediaFormat, MediaFormat.KEY_FRAME_RATE, format.n);
        com.google.android.exoplayer2.mediacodec.c.a(mediaFormat, MediaFormat.KEY_ROTATION, format.o);
        com.google.android.exoplayer2.mediacodec.c.a(mediaFormat, format.s);
        mediaFormat.setInteger(MediaFormat.KEY_MAX_WIDTH, aVar.a);
        mediaFormat.setInteger(MediaFormat.KEY_MAX_HEIGHT, aVar.b);
        com.google.android.exoplayer2.mediacodec.c.a(mediaFormat, MediaFormat.KEY_MAX_INPUT_SIZE, aVar.c);
        if (z.a >= 23) {
            mediaFormat.setInteger("priority", 0);
            if (f != -1.0f) {
                mediaFormat.setFloat(MediaFormat.KEY_OPERATING_RATE, f);
            }
        }
        if (z) {
            mediaFormat.setInteger("no-post-process", 1);
            mediaFormat.setInteger("auto-frc", 0);
        }
        if (i != 0) {
            a(mediaFormat, i);
        }
        return mediaFormat;
    }

    /* access modifiers changed from: protected */
    public a a(com.google.android.exoplayer2.mediacodec.a aVar, Format format, Format[] formatArr) throws MediaCodecUtil.DecoderQueryException {
        int a2;
        int i = format.l;
        int i2 = format.m;
        int b2 = b(aVar, format);
        if (formatArr.length == 1) {
            if (!(b2 == -1 || (a2 = a(aVar, format.g, format.l, format.m)) == -1)) {
                b2 = Math.min((int) (((float) b2) * 1.5f), a2);
            }
            return new a(i, i2, b2);
        }
        int i3 = i2;
        int i4 = b2;
        boolean z = false;
        int i5 = i;
        for (Format format2 : formatArr) {
            if (aVar.a(format, format2, false)) {
                z |= format2.l == -1 || format2.m == -1;
                i5 = Math.max(i5, format2.l);
                i3 = Math.max(i3, format2.m);
                i4 = Math.max(i4, b(aVar, format2));
            }
        }
        if (z) {
            i.c("MediaCodecVideoRenderer", "Resolutions unknown. Codec max resolution: " + i5 + "x" + i3);
            Point a3 = a(aVar, format);
            if (a3 != null) {
                i5 = Math.max(i5, a3.x);
                i3 = Math.max(i3, a3.y);
                i4 = Math.max(i4, a(aVar, format.g, i5, i3));
                i.c("MediaCodecVideoRenderer", "Codec max resolution adjusted to: " + i5 + "x" + i3);
            }
        }
        return new a(i5, i3, i4);
    }

    private static Point a(com.google.android.exoplayer2.mediacodec.a aVar, Format format) throws MediaCodecUtil.DecoderQueryException {
        boolean z = format.m > format.l;
        int i = z ? format.m : format.l;
        int i2 = z ? format.l : format.m;
        float f = ((float) i2) / ((float) i);
        int[] iArr = c;
        for (int i3 : iArr) {
            int i4 = (int) (((float) i3) * f);
            if (i3 <= i || i4 <= i2) {
                break;
            }
            if (z.a >= 21) {
                int i5 = z ? i4 : i3;
                if (!z) {
                    i3 = i4;
                }
                Point a2 = aVar.a(i5, i3);
                if (aVar.a(a2.x, a2.y, (double) format.n)) {
                    return a2;
                }
            } else {
                int a3 = z.a(i3, 16) * 16;
                int a4 = 16 * z.a(i4, 16);
                if (a3 * a4 <= MediaCodecUtil.b()) {
                    int i6 = z ? a4 : a3;
                    if (z) {
                        a4 = a3;
                    }
                    return new Point(i6, a4);
                }
            }
        }
        return null;
    }

    private static int b(com.google.android.exoplayer2.mediacodec.a aVar, Format format) {
        if (format.h == -1) {
            return a(aVar, format.g, format.l, format.m);
        }
        int size = format.i.size();
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            i += format.i.get(i2).length;
        }
        return format.h + i;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    private static int a(com.google.android.exoplayer2.mediacodec.a aVar, String str, int i, int i2) {
        boolean z;
        int i3;
        if (i == -1 || i2 == -1) {
            return -1;
        }
        int i4 = 4;
        switch (str.hashCode()) {
            case -1664118616:
                if (str.equals(MediaFormat.MIMETYPE_VIDEO_H263)) {
                    z = false;
                    break;
                }
                z = true;
                break;
            case -1662541442:
                if (str.equals(MediaFormat.MIMETYPE_VIDEO_HEVC)) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 1187890754:
                if (str.equals(MediaFormat.MIMETYPE_VIDEO_MPEG4)) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 1331836730:
                if (str.equals(MediaFormat.MIMETYPE_VIDEO_AVC)) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 1599127256:
                if (str.equals(MediaFormat.MIMETYPE_VIDEO_VP8)) {
                    z = true;
                    break;
                }
                z = true;
                break;
            case 1599127257:
                if (str.equals(MediaFormat.MIMETYPE_VIDEO_VP9)) {
                    z = true;
                    break;
                }
                z = true;
                break;
            default:
                z = true;
                break;
        }
        if (z && !z) {
            if (!z) {
                if (!z) {
                    if (!z && !z) {
                        return -1;
                    }
                    i3 = i * i2;
                    return (i3 * 3) / (i4 * 2);
                }
            } else if ("BRAVIA 4K 2015".equals(z.d) || ("Amazon".equals(z.c) && ("KFSOWI".equals(z.d) || ("AFTS".equals(z.d) && aVar.f)))) {
                return -1;
            } else {
                i3 = z.a(i, 16) * z.a(i2, 16) * 16 * 16;
                i4 = 2;
                return (i3 * 3) / (i4 * 2);
            }
        }
        i3 = i * i2;
        i4 = 2;
        return (i3 * 3) / (i4 * 2);
    }

    private static boolean M() {
        return "NVIDIA".equals(z.c);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:410:0x06af A[ADDED_TO_REGION] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.lang.String r7) {
        /*
        // Method dump skipped, instructions count: 2476
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.c.a(java.lang.String):boolean");
    }

    /* compiled from: MediaCodecVideoRenderer */
    /* access modifiers changed from: protected */
    public static final class a {
        public final int a;
        public final int b;
        public final int c;

        public a(int i, int i2, int i3) {
            this.a = i;
            this.b = i2;
            this.c = i3;
        }
    }

    /* compiled from: MediaCodecVideoRenderer */
    /* access modifiers changed from: private */
    public final class b implements MediaCodec.OnFrameRenderedListener {
        private b(MediaCodec mediaCodec) {
            mediaCodec.setOnFrameRenderedListener(this, new Handler());
        }

        @Override // android.media.MediaCodec.OnFrameRenderedListener
        public void onFrameRendered(MediaCodec mediaCodec, long j, long j2) {
            if (this == c.this.b) {
                c.this.e(j);
            }
        }
    }
}
