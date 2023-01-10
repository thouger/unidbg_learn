package com.google.android.exoplayer2.audio;

import android.media.AudioAttributes;
import android.media.AudioFormat;
import android.media.AudioTrack;
import android.os.ConditionVariable;
import android.os.SystemClock;
import android.util.TimeUtils;
import com.google.android.exoplayer2.audio.AudioProcessor;
import com.google.android.exoplayer2.audio.AudioSink;
import com.google.android.exoplayer2.audio.h;
import com.google.android.exoplayer2.q;
import com.google.android.exoplayer2.util.i;
import com.google.android.exoplayer2.util.z;
import com.tencent.qcloud.tim.uikit.component.video.JCameraView;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public final class DefaultAudioSink implements AudioSink {
    public static boolean a;
    public static boolean b;
    private q A;
    private long B;
    private long C;
    private ByteBuffer D;
    private int E;
    private int F;
    private long G;
    private long H;
    private int I;
    private long J;
    private long K;
    private int L;
    private int M;
    private long N;
    private float O;
    private AudioProcessor[] P;
    private ByteBuffer[] Q;
    private ByteBuffer R;
    private ByteBuffer S;
    private byte[] T;
    private int U;
    private int V;
    private boolean W;
    private boolean X;
    private int Y;
    private i Z;
    private boolean aa;
    private long ab;
    private final c c;
    private final a d;
    private final boolean e;
    private final j f;
    private final r g;
    private final AudioProcessor[] h;
    private final AudioProcessor[] i;
    private final ConditionVariable j;
    private final h k;
    private final ArrayDeque<c> l;
    private AudioSink.a m;
    private AudioTrack n;
    private AudioTrack o;
    private boolean p;
    private boolean q;
    private int r;
    private int s;
    private int t;
    private int u;
    private b v;
    private boolean w;
    private boolean x;
    private int y;
    private q z;

    public interface a {
        long a(long j);

        q a(q qVar);

        AudioProcessor[] a();

        long b();
    }

    public static final class InvalidAudioTrackTimestampException extends RuntimeException {
        /* synthetic */ InvalidAudioTrackTimestampException(String str, AnonymousClass1 r2) {
            this(str);
        }

        private InvalidAudioTrackTimestampException(String str) {
            super(str);
        }
    }

    public static class b implements a {
        private final AudioProcessor[] a;
        private final o b = new o();
        private final q c = new q();

        public b(AudioProcessor... audioProcessorArr) {
            this.a = (AudioProcessor[]) Arrays.copyOf(audioProcessorArr, audioProcessorArr.length + 2);
            AudioProcessor[] audioProcessorArr2 = this.a;
            audioProcessorArr2[audioProcessorArr.length] = this.b;
            audioProcessorArr2[audioProcessorArr.length + 1] = this.c;
        }

        @Override // com.google.android.exoplayer2.audio.DefaultAudioSink.a
        public AudioProcessor[] a() {
            return this.a;
        }

        @Override // com.google.android.exoplayer2.audio.DefaultAudioSink.a
        public q a(q qVar) {
            this.b.a(qVar.d);
            return new q(this.c.a(qVar.b), this.c.b(qVar.c), qVar.d);
        }

        @Override // com.google.android.exoplayer2.audio.DefaultAudioSink.a
        public long a(long j) {
            return this.c.a(j);
        }

        @Override // com.google.android.exoplayer2.audio.DefaultAudioSink.a
        public long b() {
            return this.b.j();
        }
    }

    public DefaultAudioSink(c cVar, AudioProcessor[] audioProcessorArr) {
        this(cVar, audioProcessorArr, false);
    }

    public DefaultAudioSink(c cVar, AudioProcessor[] audioProcessorArr, boolean z) {
        this(cVar, new b(audioProcessorArr), z);
    }

    public DefaultAudioSink(c cVar, a aVar, boolean z) {
        this.c = cVar;
        this.d = (a) com.google.android.exoplayer2.util.a.a(aVar);
        this.e = z;
        this.j = new ConditionVariable(true);
        this.k = new h(new d(this, null));
        this.f = new j();
        this.g = new r();
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, new n(), this.f, this.g);
        Collections.addAll(arrayList, aVar.a());
        this.h = (AudioProcessor[]) arrayList.toArray(new AudioProcessor[arrayList.size()]);
        this.i = new AudioProcessor[]{new l()};
        this.O = 1.0f;
        this.M = 0;
        this.v = b.a;
        this.Y = 0;
        this.Z = new i(0, 0.0f);
        this.A = q.a;
        this.V = -1;
        this.P = new AudioProcessor[0];
        this.Q = new ByteBuffer[0];
        this.l = new ArrayDeque<>();
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void a(AudioSink.a aVar) {
        this.m = aVar;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public boolean a(int i, int i2) {
        if (z.c(i2)) {
            return i2 != 4 || z.a >= 21;
        }
        c cVar = this.c;
        return cVar != null && cVar.a(i2) && (i == -1 || i <= this.c.a());
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public long a(boolean z) {
        if (!r() || this.M == 0) {
            return Long.MIN_VALUE;
        }
        return this.N + c(b(Math.min(this.k.a(z), e(t()))));
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void a(int i, int i2, int i3, int i4, int[] iArr, int i5, int i6) throws AudioSink.ConfigurationException {
        int i7;
        int i8;
        boolean z;
        this.r = i3;
        this.p = z.c(i);
        boolean z2 = true;
        this.q = this.e && a(i2, 4) && z.d(i);
        if (this.p) {
            this.F = z.b(i, i2);
        }
        boolean z3 = this.p && i != 4;
        if (!z3 || this.q) {
            z2 = false;
        }
        this.x = z2;
        if (z.a < 21 && i2 == 8 && iArr == null) {
            iArr = new int[6];
            for (int i9 = 0; i9 < iArr.length; i9++) {
                iArr[i9] = i9;
            }
        }
        if (z3) {
            this.g.a(i5, i6);
            this.f.a(iArr);
            AudioProcessor[] w = w();
            i7 = i3;
            i8 = i;
            z = false;
            for (AudioProcessor audioProcessor : w) {
                try {
                    z |= audioProcessor.a(i7, i2, i8);
                    if (audioProcessor.a()) {
                        i2 = audioProcessor.b();
                        i7 = audioProcessor.d();
                        i8 = audioProcessor.c();
                    }
                } catch (AudioProcessor.UnhandledFormatException e) {
                    throw new AudioSink.ConfigurationException(e);
                }
            }
        } else {
            i7 = i3;
            i8 = i;
            z = false;
        }
        int a2 = a(i2, this.p);
        if (a2 == 0) {
            throw new AudioSink.ConfigurationException("Unsupported channel count: " + i2);
        } else if (z || !r() || this.u != i8 || this.s != i7 || this.t != a2) {
            i();
            this.w = z3;
            this.s = i7;
            this.t = a2;
            this.u = i8;
            this.I = this.p ? z.b(this.u, i2) : -1;
            if (i4 == 0) {
                i4 = k();
            }
            this.y = i4;
        }
    }

    private int k() {
        if (this.p) {
            int minBufferSize = AudioTrack.getMinBufferSize(this.s, this.t, this.u);
            com.google.android.exoplayer2.util.a.b(minBufferSize != -2);
            return z.a(minBufferSize * 4, ((int) f(250000)) * this.I, (int) Math.max((long) minBufferSize, f(750000) * ((long) this.I)));
        }
        int c2 = c(this.u);
        if (this.u == 5) {
            c2 *= 2;
        }
        return (int) ((((long) c2) * 250000) / TimeUtils.NANOS_PER_MS);
    }

    private void l() {
        ArrayList arrayList = new ArrayList();
        AudioProcessor[] w = w();
        for (AudioProcessor audioProcessor : w) {
            if (audioProcessor.a()) {
                arrayList.add(audioProcessor);
            } else {
                audioProcessor.h();
            }
        }
        int size = arrayList.size();
        this.P = (AudioProcessor[]) arrayList.toArray(new AudioProcessor[size]);
        this.Q = new ByteBuffer[size];
        m();
    }

    private void m() {
        int i = 0;
        while (true) {
            AudioProcessor[] audioProcessorArr = this.P;
            if (i < audioProcessorArr.length) {
                AudioProcessor audioProcessor = audioProcessorArr[i];
                audioProcessor.h();
                this.Q[i] = audioProcessor.f();
                i++;
            } else {
                return;
            }
        }
    }

    private void n() throws AudioSink.InitializationException {
        this.j.block();
        this.o = u();
        int audioSessionId = this.o.getAudioSessionId();
        if (a && z.a < 21) {
            AudioTrack audioTrack = this.n;
            if (!(audioTrack == null || audioSessionId == audioTrack.getAudioSessionId())) {
                q();
            }
            if (this.n == null) {
                this.n = b(audioSessionId);
            }
        }
        if (this.Y != audioSessionId) {
            this.Y = audioSessionId;
            AudioSink.a aVar = this.m;
            if (aVar != null) {
                aVar.a(audioSessionId);
            }
        }
        this.A = this.x ? this.d.a(this.A) : q.a;
        l();
        this.k.a(this.o, this.u, this.I, this.y);
        p();
        if (this.Z.a != 0) {
            this.o.attachAuxEffect(this.Z.a);
            this.o.setAuxEffectSendLevel(this.Z.b);
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void a() {
        this.X = true;
        if (r()) {
            this.k.a();
            this.o.play();
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void b() {
        if (this.M == 1) {
            this.M = 2;
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public boolean a(ByteBuffer byteBuffer, long j) throws AudioSink.InitializationException, AudioSink.WriteException {
        ByteBuffer byteBuffer2 = this.R;
        com.google.android.exoplayer2.util.a.a(byteBuffer2 == null || byteBuffer == byteBuffer2);
        if (!r()) {
            n();
            if (this.X) {
                a();
            }
        }
        if (!this.k.a(t())) {
            return false;
        }
        if (this.R == null) {
            if (!byteBuffer.hasRemaining()) {
                return true;
            }
            if (!this.p && this.L == 0) {
                this.L = a(this.u, byteBuffer);
                if (this.L == 0) {
                    return true;
                }
            }
            if (this.z != null) {
                if (!o()) {
                    return false;
                }
                q qVar = this.z;
                this.z = null;
                this.l.add(new c(this.d.a(qVar), Math.max(0L, j), e(t()), null));
                l();
            }
            if (this.M == 0) {
                this.N = Math.max(0L, j);
                this.M = 1;
            } else {
                long d2 = this.N + d(s() - this.g.k());
                if (this.M == 1 && Math.abs(d2 - j) > 200000) {
                    i.d("AudioTrack", "Discontinuity detected [expected " + d2 + ", got " + j + "]");
                    this.M = 2;
                }
                if (this.M == 2) {
                    long j2 = j - d2;
                    this.N += j2;
                    this.M = 1;
                    AudioSink.a aVar = this.m;
                    if (!(aVar == null || j2 == 0)) {
                        aVar.a();
                    }
                }
            }
            if (this.p) {
                this.G += (long) byteBuffer.remaining();
            } else {
                this.H += (long) this.L;
            }
            this.R = byteBuffer;
        }
        if (this.w) {
            a(j);
        } else {
            b(this.R, j);
        }
        if (!this.R.hasRemaining()) {
            this.R = null;
            return true;
        } else if (!this.k.c(t())) {
            return false;
        } else {
            i.c("AudioTrack", "Resetting stalled audio track");
            i();
            return true;
        }
    }

    private void a(long j) throws AudioSink.WriteException {
        ByteBuffer byteBuffer;
        int length = this.P.length;
        int i = length;
        while (i >= 0) {
            if (i > 0) {
                byteBuffer = this.Q[i - 1];
            } else {
                byteBuffer = this.R;
                if (byteBuffer == null) {
                    byteBuffer = AudioProcessor.a;
                }
            }
            if (i == length) {
                b(byteBuffer, j);
            } else {
                AudioProcessor audioProcessor = this.P[i];
                audioProcessor.a(byteBuffer);
                ByteBuffer f = audioProcessor.f();
                this.Q[i] = f;
                if (f.hasRemaining()) {
                    i++;
                }
            }
            if (!byteBuffer.hasRemaining()) {
                i--;
            } else {
                return;
            }
        }
    }

    private void b(ByteBuffer byteBuffer, long j) throws AudioSink.WriteException {
        if (byteBuffer.hasRemaining()) {
            ByteBuffer byteBuffer2 = this.S;
            boolean z = true;
            int i = 0;
            if (byteBuffer2 != null) {
                com.google.android.exoplayer2.util.a.a(byteBuffer2 == byteBuffer);
            } else {
                this.S = byteBuffer;
                if (z.a < 21) {
                    int remaining = byteBuffer.remaining();
                    byte[] bArr = this.T;
                    if (bArr == null || bArr.length < remaining) {
                        this.T = new byte[remaining];
                    }
                    int position = byteBuffer.position();
                    byteBuffer.get(this.T, 0, remaining);
                    byteBuffer.position(position);
                    this.U = 0;
                }
            }
            int remaining2 = byteBuffer.remaining();
            if (z.a < 21) {
                int b2 = this.k.b(this.J);
                if (b2 > 0 && (i = this.o.write(this.T, this.U, Math.min(remaining2, b2))) > 0) {
                    this.U += i;
                    byteBuffer.position(byteBuffer.position() + i);
                }
            } else if (this.aa) {
                if (j == -9223372036854775807L) {
                    z = false;
                }
                com.google.android.exoplayer2.util.a.b(z);
                i = a(this.o, byteBuffer, remaining2, j);
            } else {
                i = a(this.o, byteBuffer, remaining2);
            }
            this.ab = SystemClock.elapsedRealtime();
            if (i >= 0) {
                if (this.p) {
                    this.J += (long) i;
                }
                if (i == remaining2) {
                    if (!this.p) {
                        this.K += (long) this.L;
                    }
                    this.S = null;
                    return;
                }
                return;
            }
            throw new AudioSink.WriteException(i);
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void c() throws AudioSink.WriteException {
        if (!this.W && r() && o()) {
            this.k.d(t());
            this.o.stop();
            this.E = 0;
            this.W = true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x0021  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x003c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private boolean o() throws com.google.android.exoplayer2.audio.AudioSink.WriteException {
        /*
            r9 = this;
            int r0 = r9.V
            r1 = -1
            r2 = 1
            r3 = 0
            if (r0 != r1) goto L_0x0014
            boolean r0 = r9.w
            if (r0 == 0) goto L_0x000d
            r0 = r3
            goto L_0x0010
        L_0x000d:
            com.google.android.exoplayer2.audio.AudioProcessor[] r0 = r9.P
            int r0 = r0.length
        L_0x0010:
            r9.V = r0
        L_0x0012:
            r0 = r2
            goto L_0x0015
        L_0x0014:
            r0 = r3
        L_0x0015:
            int r4 = r9.V
            com.google.android.exoplayer2.audio.AudioProcessor[] r5 = r9.P
            int r6 = r5.length
            r7 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            if (r4 >= r6) goto L_0x0038
            r4 = r5[r4]
            if (r0 == 0) goto L_0x0028
            r4.e()
        L_0x0028:
            r9.a(r7)
            boolean r0 = r4.g()
            if (r0 != 0) goto L_0x0032
            return r3
        L_0x0032:
            int r0 = r9.V
            int r0 = r0 + r2
            r9.V = r0
            goto L_0x0012
        L_0x0038:
            java.nio.ByteBuffer r0 = r9.S
            if (r0 == 0) goto L_0x0044
            r9.b(r0, r7)
            java.nio.ByteBuffer r0 = r9.S
            if (r0 == 0) goto L_0x0044
            return r3
        L_0x0044:
            r9.V = r1
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.audio.DefaultAudioSink.o():boolean");
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public boolean d() {
        return !r() || (this.W && !e());
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public boolean e() {
        return r() && this.k.e(t());
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public q a(q qVar) {
        if (!r() || this.x) {
            q qVar2 = this.z;
            if (qVar2 == null) {
                qVar2 = !this.l.isEmpty() ? this.l.getLast().a : this.A;
            }
            if (!qVar.equals(qVar2)) {
                if (r()) {
                    this.z = qVar;
                } else {
                    this.A = this.d.a(qVar);
                }
            }
            return this.A;
        }
        this.A = q.a;
        return this.A;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public q f() {
        return this.A;
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void a(b bVar) {
        if (!this.v.equals(bVar)) {
            this.v = bVar;
            if (!this.aa) {
                i();
                this.Y = 0;
            }
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void a(i iVar) {
        if (!this.Z.equals(iVar)) {
            int i = iVar.a;
            float f = iVar.b;
            if (this.o != null) {
                if (this.Z.a != i) {
                    this.o.attachAuxEffect(i);
                }
                if (i != 0) {
                    this.o.setAuxEffectSendLevel(f);
                }
            }
            this.Z = iVar;
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void a(int i) {
        com.google.android.exoplayer2.util.a.b(z.a >= 21);
        if (!this.aa || this.Y != i) {
            this.aa = true;
            this.Y = i;
            i();
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void g() {
        if (this.aa) {
            this.aa = false;
            this.Y = 0;
            i();
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void a(float f) {
        if (this.O != f) {
            this.O = f;
            p();
        }
    }

    private void p() {
        if (r()) {
            if (z.a >= 21) {
                a(this.o, this.O);
            } else {
                b(this.o, this.O);
            }
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void h() {
        this.X = false;
        if (r() && this.k.c()) {
            this.o.pause();
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void i() {
        if (r()) {
            this.G = 0;
            this.H = 0;
            this.J = 0;
            this.K = 0;
            this.L = 0;
            q qVar = this.z;
            if (qVar != null) {
                this.A = qVar;
                this.z = null;
            } else if (!this.l.isEmpty()) {
                this.A = this.l.getLast().a;
            }
            this.l.clear();
            this.B = 0;
            this.C = 0;
            this.g.j();
            this.R = null;
            this.S = null;
            m();
            this.W = false;
            this.V = -1;
            this.D = null;
            this.E = 0;
            this.M = 0;
            if (this.k.b()) {
                this.o.pause();
            }
            AudioTrack audioTrack = this.o;
            this.o = null;
            this.k.d();
            this.j.close();
            new AnonymousClass1(audioTrack).start();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.android.exoplayer2.audio.DefaultAudioSink$1  reason: invalid class name */
    public class AnonymousClass1 extends Thread {
        final /* synthetic */ AudioTrack a;

        AnonymousClass1(AudioTrack audioTrack) {
            this.a = audioTrack;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            try {
                this.a.flush();
                this.a.release();
            } finally {
                DefaultAudioSink.this.j.open();
            }
        }
    }

    @Override // com.google.android.exoplayer2.audio.AudioSink
    public void j() {
        i();
        q();
        for (AudioProcessor audioProcessor : this.h) {
            audioProcessor.i();
        }
        for (AudioProcessor audioProcessor2 : this.i) {
            audioProcessor2.i();
        }
        this.Y = 0;
        this.X = false;
    }

    private void q() {
        AudioTrack audioTrack = this.n;
        if (audioTrack != null) {
            this.n = null;
            new AnonymousClass2(audioTrack).start();
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.google.android.exoplayer2.audio.DefaultAudioSink$2  reason: invalid class name */
    public class AnonymousClass2 extends Thread {
        final /* synthetic */ AudioTrack a;

        AnonymousClass2(AudioTrack audioTrack) {
            this.a = audioTrack;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            this.a.release();
        }
    }

    private long b(long j) {
        long j2;
        long a2;
        c cVar = null;
        while (!this.l.isEmpty() && j >= this.l.getFirst().c) {
            cVar = this.l.remove();
        }
        if (cVar != null) {
            this.A = cVar.a;
            this.C = cVar.c;
            this.B = cVar.b - this.N;
        }
        if (this.A.b == 1.0f) {
            return (j + this.B) - this.C;
        }
        if (this.l.isEmpty()) {
            j2 = this.B;
            a2 = this.d.a(j - this.C);
        } else {
            j2 = this.B;
            a2 = z.a(j - this.C, this.A.b);
        }
        return j2 + a2;
    }

    private long c(long j) {
        return j + e(this.d.b());
    }

    private boolean r() {
        return this.o != null;
    }

    private long d(long j) {
        return (j * TimeUtils.NANOS_PER_MS) / ((long) this.r);
    }

    private long e(long j) {
        return (j * TimeUtils.NANOS_PER_MS) / ((long) this.s);
    }

    private long f(long j) {
        return (j * ((long) this.s)) / TimeUtils.NANOS_PER_MS;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private long s() {
        return this.p ? this.G / ((long) this.F) : this.H;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private long t() {
        return this.p ? this.J / ((long) this.I) : this.K;
    }

    private AudioTrack u() throws AudioSink.InitializationException {
        AudioTrack audioTrack;
        if (z.a >= 21) {
            audioTrack = v();
        } else {
            int h = z.h(this.v.d);
            int i = this.Y;
            if (i == 0) {
                audioTrack = new AudioTrack(h, this.s, this.t, this.u, this.y, 1);
            } else {
                audioTrack = new AudioTrack(h, this.s, this.t, this.u, this.y, 1, i);
            }
        }
        int state = audioTrack.getState();
        if (state == 1) {
            return audioTrack;
        }
        try {
            audioTrack.release();
        } catch (Exception unused) {
        }
        throw new AudioSink.InitializationException(state, this.s, this.t, this.y);
    }

    private AudioTrack v() {
        AudioAttributes audioAttributes;
        if (this.aa) {
            audioAttributes = new AudioAttributes.Builder().setContentType(3).setFlags(16).setUsage(1).build();
        } else {
            audioAttributes = this.v.a();
        }
        AudioFormat build = new AudioFormat.Builder().setChannelMask(this.t).setEncoding(this.u).setSampleRate(this.s).build();
        int i = this.Y;
        if (i == 0) {
            i = 0;
        }
        return new AudioTrack(audioAttributes, build, this.y, 1, i);
    }

    private AudioTrack b(int i) {
        return new AudioTrack(3, 4000, 4, 2, 2, 0, i);
    }

    private AudioProcessor[] w() {
        return this.q ? this.i : this.h;
    }

    private static int a(int i, boolean z) {
        if (z.a <= 28 && !z) {
            if (i == 7) {
                i = 8;
            } else if (i == 3 || i == 4 || i == 5) {
                i = 6;
            }
        }
        if (z.a <= 26 && "fugu".equals(z.b) && !z && i == 1) {
            i = 2;
        }
        return z.e(i);
    }

    private static int c(int i) {
        if (i == 5) {
            return JCameraView.MEDIA_QUALITY_SORRY;
        }
        if (i == 6) {
            return 768000;
        }
        if (i == 7) {
            return AudioFormat.SAMPLE_RATE_HZ_MAX;
        }
        if (i == 8) {
            return 2250000;
        }
        if (i == 14) {
            return 3062500;
        }
        throw new IllegalArgumentException();
    }

    private static int a(int i, ByteBuffer byteBuffer) {
        if (i == 7 || i == 8) {
            return k.a(byteBuffer);
        }
        if (i == 5) {
            return a.a();
        }
        if (i == 6) {
            return a.a(byteBuffer);
        }
        if (i == 14) {
            int b2 = a.b(byteBuffer);
            if (b2 == -1) {
                return 0;
            }
            return a.a(byteBuffer, b2) * 16;
        }
        throw new IllegalStateException("Unexpected audio encoding: " + i);
    }

    private static int a(AudioTrack audioTrack, ByteBuffer byteBuffer, int i) {
        return audioTrack.write(byteBuffer, i, 1);
    }

    private int a(AudioTrack audioTrack, ByteBuffer byteBuffer, int i, long j) {
        if (this.D == null) {
            this.D = ByteBuffer.allocate(16);
            this.D.order(ByteOrder.BIG_ENDIAN);
            this.D.putInt(1431633921);
        }
        if (this.E == 0) {
            this.D.putInt(4, i);
            this.D.putLong(8, j * 1000);
            this.D.position(0);
            this.E = i;
        }
        int remaining = this.D.remaining();
        if (remaining > 0) {
            int write = audioTrack.write(this.D, remaining, 1);
            if (write < 0) {
                this.E = 0;
                return write;
            } else if (write < remaining) {
                return 0;
            }
        }
        int a2 = a(audioTrack, byteBuffer, i);
        if (a2 < 0) {
            this.E = 0;
            return a2;
        }
        this.E -= a2;
        return a2;
    }

    private static void a(AudioTrack audioTrack, float f) {
        audioTrack.setVolume(f);
    }

    private static void b(AudioTrack audioTrack, float f) {
        audioTrack.setStereoVolume(f, f);
    }

    /* access modifiers changed from: private */
    public static final class c {
        private final q a;
        private final long b;
        private final long c;

        /* synthetic */ c(q qVar, long j, long j2, AnonymousClass1 r6) {
            this(qVar, j, j2);
        }

        private c(q qVar, long j, long j2) {
            this.a = qVar;
            this.b = j;
            this.c = j2;
        }
    }

    private final class d implements h.a {
        private d() {
        }

        /* synthetic */ d(DefaultAudioSink defaultAudioSink, AnonymousClass1 r2) {
            this();
        }

        @Override // com.google.android.exoplayer2.audio.h.a
        public void a(long j, long j2, long j3, long j4) {
            String str = "Spurious audio timestamp (frame position mismatch): " + j + ", " + j2 + ", " + j3 + ", " + j4 + ", " + DefaultAudioSink.this.s() + ", " + DefaultAudioSink.this.t();
            if (!DefaultAudioSink.b) {
                i.c("AudioTrack", str);
                return;
            }
            throw new InvalidAudioTrackTimestampException(str, null);
        }

        @Override // com.google.android.exoplayer2.audio.h.a
        public void b(long j, long j2, long j3, long j4) {
            String str = "Spurious audio timestamp (system clock mismatch): " + j + ", " + j2 + ", " + j3 + ", " + j4 + ", " + DefaultAudioSink.this.s() + ", " + DefaultAudioSink.this.t();
            if (!DefaultAudioSink.b) {
                i.c("AudioTrack", str);
                return;
            }
            throw new InvalidAudioTrackTimestampException(str, null);
        }

        @Override // com.google.android.exoplayer2.audio.h.a
        public void a(long j) {
            i.c("AudioTrack", "Ignoring impossibly large audio latency: " + j);
        }

        @Override // com.google.android.exoplayer2.audio.h.a
        public void a(int i, long j) {
            if (DefaultAudioSink.this.m != null) {
                DefaultAudioSink.this.m.a(i, j, SystemClock.elapsedRealtime() - DefaultAudioSink.this.ab);
            }
        }
    }
}
