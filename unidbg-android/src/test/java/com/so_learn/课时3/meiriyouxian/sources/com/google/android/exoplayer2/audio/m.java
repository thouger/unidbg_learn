package com.google.android.exoplayer2.audio;

import android.content.Context;
import android.content.pm.PackageManager;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Handler;
import android.view.Surface;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.AudioSink;
import com.google.android.exoplayer2.audio.f;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.e;
import com.google.android.exoplayer2.mediacodec.MediaCodecRenderer;
import com.google.android.exoplayer2.mediacodec.MediaCodecUtil;
import com.google.android.exoplayer2.mediacodec.b;
import com.google.android.exoplayer2.mediacodec.c;
import com.google.android.exoplayer2.q;
import com.google.android.exoplayer2.util.i;
import com.google.android.exoplayer2.util.k;
import com.google.android.exoplayer2.util.l;
import com.google.android.exoplayer2.util.z;
import java.util.Collections;
import java.util.List;

/* compiled from: MediaCodecAudioRenderer */
public class m extends MediaCodecRenderer implements k {
    private final Context b;
    private final f.a c;
    private final AudioSink d;
    private final long[] e;
    private int f;
    private boolean g;
    private boolean h;
    private boolean i;
    private MediaFormat j;
    private int k;
    private int l;
    private int m;
    private int n;
    private long o;
    private boolean p;
    private boolean q;
    private long r;
    private int s;

    /* access modifiers changed from: protected */
    public void a(int i, long j, long j2) {
    }

    /* access modifiers changed from: protected */
    public void b(int i) {
    }

    @Override // com.google.android.exoplayer2.b, com.google.android.exoplayer2.t
    public k c() {
        return this;
    }

    /* access modifiers changed from: protected */
    public void w() {
    }

    public m(Context context, b bVar, com.google.android.exoplayer2.drm.b<e> bVar2, boolean z, Handler handler, f fVar, c cVar, AudioProcessor... audioProcessorArr) {
        this(context, bVar, bVar2, z, handler, fVar, new DefaultAudioSink(cVar, audioProcessorArr));
    }

    public m(Context context, b bVar, com.google.android.exoplayer2.drm.b<e> bVar2, boolean z, Handler handler, f fVar, AudioSink audioSink) {
        super(1, bVar, bVar2, z, 44100.0f);
        this.b = context.getApplicationContext();
        this.d = audioSink;
        this.r = -9223372036854775807L;
        this.e = new long[10];
        this.c = new f.a(handler, fVar);
        audioSink.a(new a());
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public int a(b bVar, com.google.android.exoplayer2.drm.b<e> bVar2, Format format) throws MediaCodecUtil.DecoderQueryException {
        boolean z;
        String str = format.g;
        if (!l.a(str)) {
            return 0;
        }
        int i = z.a >= 21 ? 32 : 0;
        boolean a2 = a(bVar2, format.j);
        int i2 = 4;
        int i3 = 8;
        if (a2 && a(format.t, str) && bVar.a() != null) {
            return i | 8 | 4;
        }
        if ((MediaFormat.MIMETYPE_AUDIO_RAW.equals(str) && !this.d.a(format.t, format.v)) || !this.d.a(format.t, 2)) {
            return 1;
        }
        DrmInitData drmInitData = format.j;
        if (drmInitData != null) {
            z = false;
            for (int i4 = 0; i4 < drmInitData.b; i4++) {
                z |= drmInitData.a(i4).d;
            }
        } else {
            z = false;
        }
        List<com.google.android.exoplayer2.mediacodec.a> a3 = bVar.a(format.g, z);
        if (a3.isEmpty()) {
            if (!z || bVar.a(format.g, false).isEmpty()) {
                return 1;
            }
            return 2;
        } else if (!a2) {
            return 2;
        } else {
            com.google.android.exoplayer2.mediacodec.a aVar = a3.get(0);
            boolean a4 = aVar.a(format);
            if (a4 && aVar.b(format)) {
                i3 = 16;
            }
            if (!a4) {
                i2 = 3;
            }
            return i3 | i | i2;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public List<com.google.android.exoplayer2.mediacodec.a> a(b bVar, Format format, boolean z) throws MediaCodecUtil.DecoderQueryException {
        com.google.android.exoplayer2.mediacodec.a a2;
        if (!a(format.t, format.g) || (a2 = bVar.a()) == null) {
            return super.a(bVar, format, z);
        }
        return Collections.singletonList(a2);
    }

    /* access modifiers changed from: protected */
    public boolean a(int i, String str) {
        return this.d.a(i, l.h(str));
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void a(com.google.android.exoplayer2.mediacodec.a aVar, MediaCodec mediaCodec, Format format, MediaCrypto mediaCrypto, float f) {
        this.f = a(aVar, format, q());
        this.h = a(aVar.a);
        this.i = b(aVar.a);
        this.g = aVar.g;
        MediaFormat a2 = a(format, aVar.b == null ? MediaFormat.MIMETYPE_AUDIO_RAW : aVar.b, this.f, f);
        mediaCodec.configure(a2, (Surface) null, mediaCrypto, 0);
        if (this.g) {
            this.j = a2;
            this.j.setString(MediaFormat.KEY_MIME, format.g);
            return;
        }
        this.j = null;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public int a(MediaCodec mediaCodec, com.google.android.exoplayer2.mediacodec.a aVar, Format format, Format format2) {
        if (a(aVar, format2) <= this.f && aVar.a(format, format2, true) && format.w == 0 && format.x == 0 && format2.w == 0 && format2.x == 0) {
            return 1;
        }
        return 0;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public float a(float f, Format format, Format[] formatArr) {
        int i = -1;
        for (Format format2 : formatArr) {
            int i2 = format2.u;
            if (i2 != -1) {
                i = Math.max(i, i2);
            }
        }
        if (i == -1) {
            return -1.0f;
        }
        return f * ((float) i);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void a(String str, long j, long j2) {
        this.c.a(str, j, j2);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void b(Format format) throws ExoPlaybackException {
        super.b(format);
        this.c.a(format);
        this.k = MediaFormat.MIMETYPE_AUDIO_RAW.equals(format.g) ? format.v : 2;
        this.l = format.t;
        this.m = format.w;
        this.n = format.x;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void a(MediaCodec mediaCodec, MediaFormat mediaFormat) throws ExoPlaybackException {
        int i;
        int[] iArr;
        int i2;
        MediaFormat mediaFormat2 = this.j;
        if (mediaFormat2 != null) {
            i = l.h(mediaFormat2.getString(MediaFormat.KEY_MIME));
            mediaFormat = this.j;
        } else {
            i = this.k;
        }
        int integer = mediaFormat.getInteger(MediaFormat.KEY_CHANNEL_COUNT);
        int integer2 = mediaFormat.getInteger(MediaFormat.KEY_SAMPLE_RATE);
        if (!this.h || integer != 6 || (i2 = this.l) >= 6) {
            iArr = null;
        } else {
            iArr = new int[i2];
            for (int i3 = 0; i3 < this.l; i3++) {
                iArr[i3] = i3;
            }
        }
        try {
            this.d.a(i, integer, integer2, 0, iArr, this.m, this.n);
        } catch (AudioSink.ConfigurationException e) {
            throw ExoPlaybackException.createForRenderer(e, s());
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.b
    public void a(boolean z) throws ExoPlaybackException {
        super.a(z);
        this.c.a(this.a);
        int i = r().b;
        if (i != 0) {
            this.d.a(i);
        } else {
            this.d.g();
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.b
    public void a(Format[] formatArr, long j) throws ExoPlaybackException {
        super.a(formatArr, j);
        if (this.r != -9223372036854775807L) {
            int i = this.s;
            if (i == this.e.length) {
                i.c("MediaCodecAudioRenderer", "Too many stream changes, so dropping change at " + this.e[this.s - 1]);
            } else {
                this.s = i + 1;
            }
            this.e[this.s - 1] = this.r;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.b
    public void a(long j, boolean z) throws ExoPlaybackException {
        super.a(j, z);
        this.d.i();
        this.o = j;
        this.p = true;
        this.q = true;
        this.r = -9223372036854775807L;
        this.s = 0;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.b
    public void n() {
        super.n();
        this.d.a();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.b
    public void o() {
        F();
        this.d.h();
        super.o();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.b
    public void p() {
        try {
            this.r = -9223372036854775807L;
            this.s = 0;
            this.d.j();
            try {
                super.p();
            } finally {
                this.a.a();
                this.c.b(this.a);
            }
        } catch (Throwable th) {
            super.p();
            throw th;
        } finally {
            this.a.a();
            this.c.b(this.a);
        }
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.t
    public boolean v() {
        return super.v() && this.d.d();
    }

    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer, com.google.android.exoplayer2.t
    public boolean u() {
        return this.d.e() || super.u();
    }

    @Override // com.google.android.exoplayer2.util.k
    public long d() {
        if (ac_() == 2) {
            F();
        }
        return this.o;
    }

    @Override // com.google.android.exoplayer2.util.k
    public q a(q qVar) {
        return this.d.a(qVar);
    }

    @Override // com.google.android.exoplayer2.util.k
    public q e() {
        return this.d.f();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void a(com.google.android.exoplayer2.b.e eVar) {
        if (this.p && !eVar.af_()) {
            if (Math.abs(eVar.c - this.o) > 500000) {
                this.o = eVar.c;
            }
            this.p = false;
        }
        this.r = Math.max(eVar.c, this.r);
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void c(long j) {
        while (this.s != 0 && j >= this.e[0]) {
            this.d.b();
            this.s--;
            long[] jArr = this.e;
            System.arraycopy(jArr, 1, jArr, 0, this.s);
        }
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0017, code lost:
        if (r1 != -9223372036854775807L) goto L_0x001b;
     */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(long r1, long r3, android.media.MediaCodec r5, java.nio.ByteBuffer r6, int r7, int r8, long r9, boolean r11, com.google.android.exoplayer2.Format r12) throws com.google.android.exoplayer2.ExoPlaybackException {
        /*
            r0 = this;
            boolean r1 = r0.i
            if (r1 == 0) goto L_0x001a
            r1 = 0
            int r1 = (r9 > r1 ? 1 : (r9 == r1 ? 0 : -1))
            if (r1 != 0) goto L_0x001a
            r1 = r8 & 4
            if (r1 == 0) goto L_0x001a
            long r1 = r0.r
            r3 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r3 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r3 == 0) goto L_0x001a
            goto L_0x001b
        L_0x001a:
            r1 = r9
        L_0x001b:
            boolean r3 = r0.g
            r4 = 0
            r9 = 1
            if (r3 == 0) goto L_0x0029
            r3 = r8 & 2
            if (r3 == 0) goto L_0x0029
            r5.releaseOutputBuffer(r7, r4)
            return r9
        L_0x0029:
            if (r11 == 0) goto L_0x003b
            r5.releaseOutputBuffer(r7, r4)
            com.google.android.exoplayer2.b.d r1 = r0.a
            int r2 = r1.f
            int r2 = r2 + r9
            r1.f = r2
            com.google.android.exoplayer2.audio.AudioSink r1 = r0.d
            r1.b()
            return r9
        L_0x003b:
            com.google.android.exoplayer2.audio.AudioSink r3 = r0.d     // Catch:{ InitializationException -> 0x0051, WriteException -> 0x004f }
            boolean r1 = r3.a(r6, r1)     // Catch:{ InitializationException -> 0x0051, WriteException -> 0x004f }
            if (r1 == 0) goto L_0x004e
            r5.releaseOutputBuffer(r7, r4)     // Catch:{ InitializationException -> 0x0051, WriteException -> 0x004f }
            com.google.android.exoplayer2.b.d r1 = r0.a     // Catch:{ InitializationException -> 0x0051, WriteException -> 0x004f }
            int r2 = r1.e     // Catch:{ InitializationException -> 0x0051, WriteException -> 0x004f }
            int r2 = r2 + r9
            r1.e = r2     // Catch:{ InitializationException -> 0x0051, WriteException -> 0x004f }
            return r9
        L_0x004e:
            return r4
        L_0x004f:
            r1 = move-exception
            goto L_0x0052
        L_0x0051:
            r1 = move-exception
        L_0x0052:
            int r2 = r0.s()
            com.google.android.exoplayer2.ExoPlaybackException r1 = com.google.android.exoplayer2.ExoPlaybackException.createForRenderer(r1, r2)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.m.a(long, long, android.media.MediaCodec, java.nio.ByteBuffer, int, int, long, boolean, com.google.android.exoplayer2.Format):boolean");
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.mediacodec.MediaCodecRenderer
    public void x() throws ExoPlaybackException {
        try {
            this.d.c();
        } catch (AudioSink.WriteException e) {
            throw ExoPlaybackException.createForRenderer(e, s());
        }
    }

    @Override // com.google.android.exoplayer2.b, com.google.android.exoplayer2.s.b
    public void a(int i, Object obj) throws ExoPlaybackException {
        if (i == 2) {
            this.d.a(((Float) obj).floatValue());
        } else if (i == 3) {
            this.d.a((b) obj);
        } else if (i != 5) {
            super.a(i, obj);
        } else {
            this.d.a((i) obj);
        }
    }

    /* access modifiers changed from: protected */
    public int a(com.google.android.exoplayer2.mediacodec.a aVar, Format format, Format[] formatArr) {
        int a2 = a(aVar, format);
        if (formatArr.length == 1) {
            return a2;
        }
        int i = a2;
        for (Format format2 : formatArr) {
            if (aVar.a(format, format2, false)) {
                i = Math.max(i, a(aVar, format2));
            }
        }
        return i;
    }

    private int a(com.google.android.exoplayer2.mediacodec.a aVar, Format format) {
        PackageManager packageManager;
        if (z.a < 24 && "OMX.google.raw.decoder".equals(aVar.a)) {
            boolean z = true;
            if (z.a == 23 && (packageManager = this.b.getPackageManager()) != null && packageManager.hasSystemFeature(PackageManager.FEATURE_LEANBACK)) {
                z = false;
            }
            if (z) {
                return -1;
            }
        }
        return format.h;
    }

    /* access modifiers changed from: protected */
    public MediaFormat a(Format format, String str, int i, float f) {
        MediaFormat mediaFormat = new MediaFormat();
        mediaFormat.setString(MediaFormat.KEY_MIME, str);
        mediaFormat.setInteger(MediaFormat.KEY_CHANNEL_COUNT, format.t);
        mediaFormat.setInteger(MediaFormat.KEY_SAMPLE_RATE, format.u);
        c.a(mediaFormat, format.i);
        c.a(mediaFormat, MediaFormat.KEY_MAX_INPUT_SIZE, i);
        if (z.a >= 23) {
            mediaFormat.setInteger("priority", 0);
            if (f != -1.0f) {
                mediaFormat.setFloat(MediaFormat.KEY_OPERATING_RATE, f);
            }
        }
        return mediaFormat;
    }

    private void F() {
        long a2 = this.d.a(v());
        if (a2 != Long.MIN_VALUE) {
            if (!this.q) {
                a2 = Math.max(this.o, a2);
            }
            this.o = a2;
            this.q = false;
        }
    }

    private static boolean a(String str) {
        return z.a < 24 && "OMX.SEC.aac.dec".equals(str) && "samsung".equals(z.c) && (z.b.startsWith("zeroflte") || z.b.startsWith("herolte") || z.b.startsWith("heroqlte"));
    }

    private static boolean b(String str) {
        return z.a < 21 && "OMX.SEC.mp3.dec".equals(str) && "samsung".equals(z.c) && (z.b.startsWith("baffin") || z.b.startsWith("grand") || z.b.startsWith("fortuna") || z.b.startsWith("gprimelte") || z.b.startsWith("j2y18lte") || z.b.startsWith("ms01"));
    }

    /* compiled from: MediaCodecAudioRenderer */
    private final class a implements AudioSink.a {
        private a() {
        }

        @Override // com.google.android.exoplayer2.audio.AudioSink.a
        public void a(int i) {
            m.this.c.a(i);
            m.this.b(i);
        }

        @Override // com.google.android.exoplayer2.audio.AudioSink.a
        public void a() {
            m.this.w();
            m.this.q = true;
        }

        @Override // com.google.android.exoplayer2.audio.AudioSink.a
        public void a(int i, long j, long j2) {
            m.this.c.a(i, j, j2);
            m.this.a(i, j, j2);
        }
    }
}
