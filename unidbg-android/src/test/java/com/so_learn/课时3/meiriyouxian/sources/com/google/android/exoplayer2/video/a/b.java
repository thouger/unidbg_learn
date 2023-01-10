package com.google.android.exoplayer2.video.a;

import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.b.e;
import com.google.android.exoplayer2.k;
import com.google.android.exoplayer2.util.o;
import com.google.android.exoplayer2.util.z;
import java.nio.ByteBuffer;

/* compiled from: CameraMotionRenderer */
public class b extends com.google.android.exoplayer2.b {
    private final k a = new k();
    private final e b = new e(1);
    private final o c = new o();
    private long d;
    private a e;
    private long f;

    @Override // com.google.android.exoplayer2.t
    public boolean u() {
        return true;
    }

    public b() {
        super(5);
    }

    @Override // com.google.android.exoplayer2.u
    public int a(Format format) {
        return "application/x-camera-motion".equals(format.g) ? 4 : 0;
    }

    @Override // com.google.android.exoplayer2.b, com.google.android.exoplayer2.s.b
    public void a(int i, Object obj) throws ExoPlaybackException {
        if (i == 7) {
            this.e = (a) obj;
        } else {
            super.a(i, obj);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.b
    public void a(Format[] formatArr, long j) throws ExoPlaybackException {
        this.d = j;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.b
    public void a(long j, boolean z) throws ExoPlaybackException {
        w();
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.b
    public void p() {
        w();
    }

    @Override // com.google.android.exoplayer2.t
    public void a(long j, long j2) throws ExoPlaybackException {
        float[] a;
        while (!g() && this.f < 100000 + j) {
            this.b.a();
            if (a(this.a, this.b, false) == -4 && !this.b.c()) {
                this.b.h();
                this.f = this.b.c;
                if (!(this.e == null || (a = a(this.b.b)) == null)) {
                    ((a) z.a(this.e)).a(this.f - this.d, a);
                }
            } else {
                return;
            }
        }
    }

    @Override // com.google.android.exoplayer2.t
    public boolean v() {
        return g();
    }

    private float[] a(ByteBuffer byteBuffer) {
        if (byteBuffer.remaining() != 16) {
            return null;
        }
        this.c.a(byteBuffer.array(), byteBuffer.limit());
        this.c.c(byteBuffer.arrayOffset() + 4);
        float[] fArr = new float[3];
        for (int i = 0; i < 3; i++) {
            fArr[i] = Float.intBitsToFloat(this.c.q());
        }
        return fArr;
    }

    private void w() {
        this.f = 0;
        a aVar = this.e;
        if (aVar != null) {
            aVar.a();
        }
    }
}
