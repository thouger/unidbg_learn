package com.google.android.exoplayer2.audio;

import android.os.Handler;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.b.d;

/* compiled from: AudioRendererEventListener */
public interface f {

    /* compiled from: AudioRendererEventListener */
    /* renamed from: com.google.android.exoplayer2.audio.f$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$a(f fVar, int i) {
        }

        public static void $default$a(f fVar, int i, long j, long j2) {
        }

        public static void $default$b(f fVar, Format format) {
        }

        public static void $default$b(f fVar, String str, long j, long j2) {
        }

        public static void $default$c(f fVar, d dVar) {
        }

        public static void $default$d(f fVar, d dVar) {
        }
    }

    void a(int i);

    void a(int i, long j, long j2);

    void b(Format format);

    void b(String str, long j, long j2);

    void c(d dVar);

    void d(d dVar);

    /* compiled from: AudioRendererEventListener */
    public static final class a {
        private final Handler a;
        private final f b;

        public a(Handler handler, f fVar) {
            this.a = fVar != null ? (Handler) com.google.android.exoplayer2.util.a.a(handler) : null;
            this.b = fVar;
        }

        public void a(d dVar) {
            if (this.b != null) {
                this.a.post(new $$Lambda$f$a$xAQrYYW9ZnI1LZwsISdWNPblGg(this, dVar));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void d(d dVar) {
            this.b.c(dVar);
        }

        public void a(String str, long j, long j2) {
            if (this.b != null) {
                this.a.post(new $$Lambda$f$a$2AyA8mYYAr9x2ZAH3QFAE43P0TM(this, str, j, j2));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void b(String str, long j, long j2) {
            this.b.b(str, j, j2);
        }

        public void a(Format format) {
            if (this.b != null) {
                this.a.post(new $$Lambda$f$a$slQ75LeEPZozpuZt0z6SCGb5oRo(this, format));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void b(Format format) {
            this.b.b(format);
        }

        public void a(int i, long j, long j2) {
            if (this.b != null) {
                this.a.post(new $$Lambda$f$a$09JfFMmaNeB9hG7YrGa2GivzHo(this, i, j, j2));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void b(int i, long j, long j2) {
            this.b.a(i, j, j2);
        }

        public void b(d dVar) {
            if (this.b != null) {
                this.a.post(new $$Lambda$f$a$dFfuoFSMtivPRFkck2KAFnQXu0(this, dVar));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void c(d dVar) {
            dVar.a();
            this.b.d(dVar);
        }

        public void a(int i) {
            if (this.b != null) {
                this.a.post(new $$Lambda$f$a$DrzjeRfdVt_mDNU444_qAz5W0p4(this, i));
            }
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void b(int i) {
            this.b.a(i);
        }
    }
}
