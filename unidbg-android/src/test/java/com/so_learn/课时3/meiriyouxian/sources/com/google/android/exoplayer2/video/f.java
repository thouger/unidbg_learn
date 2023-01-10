package com.google.android.exoplayer2.video;

import android.os.Handler;
import android.view.Surface;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.b.d;

/* compiled from: VideoRendererEventListener */
public interface f {

    /* compiled from: VideoRendererEventListener */
    /* renamed from: com.google.android.exoplayer2.video.f$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$a(f fVar, int i, int i2, int i3, float f) {
        }

        public static void $default$a(f fVar, int i, long j) {
        }

        public static void $default$a(f fVar, Surface surface) {
        }

        public static void $default$a(f fVar, Format format) {
        }

        public static void $default$a(f fVar, d dVar) {
        }

        public static void $default$a(f fVar, String str, long j, long j2) {
        }

        public static void $default$b(f fVar, d dVar) {
        }
    }

    void a(int i, int i2, int i3, float f);

    void a(int i, long j);

    void a(Surface surface);

    void a(Format format);

    void a(d dVar);

    void a(String str, long j, long j2);

    void b(d dVar);

    /* compiled from: VideoRendererEventListener */
    public static final class a {
        private final Handler a;
        private final f b;

        public a(Handler handler, f fVar) {
            this.a = fVar != null ? (Handler) com.google.android.exoplayer2.util.a.a(handler) : null;
            this.b = fVar;
        }

        public void a(d dVar) {
            if (this.b != null) {
                this.a.post(new $$Lambda$f$a$CJj7ayVCNwsw7V6xM4ObDwkuZpk(this, dVar));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void d(d dVar) {
            this.b.a(dVar);
        }

        public void a(String str, long j, long j2) {
            if (this.b != null) {
                this.a.post(new $$Lambda$f$a$sqRChajp41LNewSuXbMioH8bCU(this, str, j, j2));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void b(String str, long j, long j2) {
            this.b.a(str, j, j2);
        }

        public void a(Format format) {
            if (this.b != null) {
                this.a.post(new $$Lambda$f$a$GaKCmYcF5zW9mkaH8HpR7DE0vY(this, format));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void b(Format format) {
            this.b.a(format);
        }

        public void a(int i, long j) {
            if (this.b != null) {
                this.a.post(new $$Lambda$f$a$NKzM4MXEsWLCh_oSJ73rqTVn0k(this, i, j));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void b(int i, long j) {
            this.b.a(i, j);
        }

        public void a(int i, int i2, int i3, float f) {
            if (this.b != null) {
                this.a.post(new $$Lambda$f$a$MNVRnpcVOnOwVvNeajyHGYNzHY(this, i, i2, i3, f));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void b(int i, int i2, int i3, float f) {
            this.b.a(i, i2, i3, f);
        }

        public void a(Surface surface) {
            if (this.b != null) {
                this.a.post(new $$Lambda$f$a$CTh8edPz548IUhOrDuQRqWpyc(this, surface));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void b(Surface surface) {
            this.b.a(surface);
        }

        public void b(d dVar) {
            if (this.b != null) {
                this.a.post(new $$Lambda$f$a$WJ85OXd4zCoMld9Ma2OKURT0nNQ(this, dVar));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void c(d dVar) {
            dVar.a();
            this.b.b(dVar);
        }
    }
}
