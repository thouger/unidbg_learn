package com.a.a;

import android.os.Build;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Choreographer;

/* compiled from: AndroidSpringLooperFactory */
abstract class a {
    public static g a() {
        if (Build.VERSION.SDK_INT >= 16) {
            return C0056a.a();
        }
        return b.a();
    }

    /* compiled from: AndroidSpringLooperFactory */
    private static class b extends g {
        private final Handler b;
        private final Runnable c = new AnonymousClass1();
        private boolean d;
        private long e;

        public static g a() {
            return new b(new Handler());
        }

        public b(Handler handler) {
            this.b = handler;
        }

        /* compiled from: AndroidSpringLooperFactory */
        /* renamed from: com.a.a.a$b$1  reason: invalid class name */
        class AnonymousClass1 implements Runnable {
            AnonymousClass1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                if (b.this.d && b.this.a != null) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    b.this.a.b((double) (uptimeMillis - b.this.e));
                    b.this.e = uptimeMillis;
                    b.this.b.post(b.this.c);
                }
            }
        }

        @Override // com.a.a.g
        public void b() {
            if (!this.d) {
                this.d = true;
                this.e = SystemClock.uptimeMillis();
                this.b.removeCallbacks(this.c);
                this.b.post(this.c);
            }
        }

        @Override // com.a.a.g
        public void c() {
            this.d = false;
            this.b.removeCallbacks(this.c);
        }
    }

    /* compiled from: AndroidSpringLooperFactory */
    /* renamed from: com.a.a.a$a  reason: collision with other inner class name */
    private static class C0056a extends g {
        private final Choreographer b;
        private final Choreographer.FrameCallback c = new AnonymousClass1();
        private boolean d;
        private long e;

        public static C0056a a() {
            return new C0056a(Choreographer.getInstance());
        }

        public C0056a(Choreographer choreographer) {
            this.b = choreographer;
        }

        /* compiled from: AndroidSpringLooperFactory */
        /* renamed from: com.a.a.a$a$1  reason: invalid class name */
        class AnonymousClass1 implements Choreographer.FrameCallback {
            AnonymousClass1() {
            }

            @Override // android.view.Choreographer.FrameCallback
            public void doFrame(long j) {
                if (C0056a.this.d && C0056a.this.a != null) {
                    long uptimeMillis = SystemClock.uptimeMillis();
                    C0056a.this.a.b((double) (uptimeMillis - C0056a.this.e));
                    C0056a.this.e = uptimeMillis;
                    C0056a.this.b.postFrameCallback(C0056a.this.c);
                }
            }
        }

        @Override // com.a.a.g
        public void b() {
            if (!this.d) {
                this.d = true;
                this.e = SystemClock.uptimeMillis();
                this.b.removeFrameCallback(this.c);
                this.b.postFrameCallback(this.c);
            }
        }

        @Override // com.a.a.g
        public void c() {
            this.d = false;
            this.b.removeFrameCallback(this.c);
        }
    }
}
