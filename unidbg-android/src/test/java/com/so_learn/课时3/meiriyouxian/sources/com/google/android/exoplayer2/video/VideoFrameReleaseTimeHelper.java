package com.google.android.exoplayer2.video;

import android.content.Context;
import android.hardware.display.DisplayManager;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Choreographer;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.exoplayer2.util.z;

public final class VideoFrameReleaseTimeHelper {
    private final WindowManager a;
    private final b b;
    private final a c;
    private long d;
    private long e;
    private long f;
    private long g;
    private long h;
    private boolean i;
    private long j;
    private long k;
    private long l;

    public VideoFrameReleaseTimeHelper() {
        this(null);
    }

    public VideoFrameReleaseTimeHelper(Context context) {
        a aVar = null;
        if (context != null) {
            context = context.getApplicationContext();
            this.a = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        } else {
            this.a = null;
        }
        if (this.a != null) {
            this.c = z.a >= 17 ? a(context) : aVar;
            this.b = b.a();
        } else {
            this.c = null;
            this.b = null;
        }
        this.d = -9223372036854775807L;
        this.e = -9223372036854775807L;
    }

    public void a() {
        this.i = false;
        if (this.a != null) {
            this.b.b();
            a aVar = this.c;
            if (aVar != null) {
                aVar.a();
            }
            c();
        }
    }

    public void b() {
        if (this.a != null) {
            a aVar = this.c;
            if (aVar != null) {
                aVar.b();
            }
            this.b.c();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0047  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long a(long r11, long r13) {
        /*
            r10 = this;
            r0 = 1000(0x3e8, double:4.94E-321)
            long r0 = r0 * r11
            boolean r2 = r10.i
            if (r2 == 0) goto L_0x0041
            long r2 = r10.f
            int r2 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r2 == 0) goto L_0x0018
            long r2 = r10.l
            r4 = 1
            long r2 = r2 + r4
            r10.l = r2
            long r2 = r10.h
            r10.g = r2
        L_0x0018:
            long r2 = r10.l
            r4 = 6
            int r4 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            r5 = 0
            if (r4 < 0) goto L_0x0039
            long r6 = r10.k
            long r6 = r0 - r6
            long r6 = r6 / r2
            long r2 = r10.g
            long r2 = r2 + r6
            boolean r4 = r10.b(r2, r13)
            if (r4 == 0) goto L_0x0032
            r10.i = r5
            goto L_0x0041
        L_0x0032:
            long r4 = r10.j
            long r4 = r4 + r2
            long r6 = r10.k
            long r4 = r4 - r6
            goto L_0x0043
        L_0x0039:
            boolean r2 = r10.b(r0, r13)
            if (r2 == 0) goto L_0x0041
            r10.i = r5
        L_0x0041:
            r4 = r13
            r2 = r0
        L_0x0043:
            boolean r6 = r10.i
            if (r6 != 0) goto L_0x0052
            r10.k = r0
            r10.j = r13
            r13 = 0
            r10.l = r13
            r13 = 1
            r10.i = r13
        L_0x0052:
            r10.f = r11
            r10.h = r2
            com.google.android.exoplayer2.video.VideoFrameReleaseTimeHelper$b r11 = r10.b
            if (r11 == 0) goto L_0x0077
            long r12 = r10.d
            r0 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r12 = (r12 > r0 ? 1 : (r12 == r0 ? 0 : -1))
            if (r12 != 0) goto L_0x0066
            goto L_0x0077
        L_0x0066:
            long r6 = r11.a
            int r11 = (r6 > r0 ? 1 : (r6 == r0 ? 0 : -1))
            if (r11 != 0) goto L_0x006d
            return r4
        L_0x006d:
            long r8 = r10.d
            long r11 = a(r4, r6, r8)
            long r13 = r10.e
            long r11 = r11 - r13
            return r11
        L_0x0077:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.video.VideoFrameReleaseTimeHelper.a(long, long):long");
    }

    private a a(Context context) {
        DisplayManager displayManager = (DisplayManager) context.getSystemService(Context.DISPLAY_SERVICE);
        if (displayManager == null) {
            return null;
        }
        return new a(displayManager);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void c() {
        Display defaultDisplay = this.a.getDefaultDisplay();
        if (defaultDisplay != null) {
            this.d = (long) (1.0E9d / ((double) defaultDisplay.getRefreshRate()));
            this.e = (this.d * 80) / 100;
        }
    }

    private boolean b(long j, long j2) {
        return Math.abs((j2 - this.j) - (j - this.k)) > 20000000;
    }

    private static long a(long j, long j2, long j3) {
        long j4;
        long j5 = j2 + (((j - j2) / j3) * j3);
        if (j <= j5) {
            j4 = j5 - j3;
        } else {
            j5 = j3 + j5;
            j4 = j5;
        }
        return j5 - j < j - j4 ? j5 : j4;
    }

    /* access modifiers changed from: private */
    public final class a implements DisplayManager.DisplayListener {
        private final DisplayManager b;

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayAdded(int i) {
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayRemoved(int i) {
        }

        public a(DisplayManager displayManager) {
            this.b = displayManager;
        }

        public void a() {
            this.b.registerDisplayListener(this, null);
        }

        public void b() {
            this.b.unregisterDisplayListener(this);
        }

        @Override // android.hardware.display.DisplayManager.DisplayListener
        public void onDisplayChanged(int i) {
            if (i == 0) {
                VideoFrameReleaseTimeHelper.this.c();
            }
        }
    }

    /* access modifiers changed from: private */
    public static final class b implements Handler.Callback, Choreographer.FrameCallback {
        private static final b b = new b();
        public volatile long a = -9223372036854775807L;
        private final Handler c;
        private final HandlerThread d = new HandlerThread("ChoreographerOwner:Handler");
        private Choreographer e;
        private int f;

        public static b a() {
            return b;
        }

        private b() {
            this.d.start();
            this.c = z.a(this.d.getLooper(), (Handler.Callback) this);
            this.c.sendEmptyMessage(0);
        }

        public void b() {
            this.c.sendEmptyMessage(1);
        }

        public void c() {
            this.c.sendEmptyMessage(2);
        }

        @Override // android.view.Choreographer.FrameCallback
        public void doFrame(long j) {
            this.a = j;
            this.e.postFrameCallbackDelayed(this, 500);
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                d();
                return true;
            } else if (i == 1) {
                e();
                return true;
            } else if (i != 2) {
                return false;
            } else {
                f();
                return true;
            }
        }

        private void d() {
            this.e = Choreographer.getInstance();
        }

        private void e() {
            this.f++;
            if (this.f == 1) {
                this.e.postFrameCallback(this);
            }
        }

        private void f() {
            this.f--;
            if (this.f == 0) {
                this.e.removeFrameCallback(this);
                this.a = -9223372036854775807L;
            }
        }
    }
}
