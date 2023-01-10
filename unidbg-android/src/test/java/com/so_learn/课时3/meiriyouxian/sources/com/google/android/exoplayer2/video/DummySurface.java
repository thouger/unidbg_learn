package com.google.android.exoplayer2.video;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.view.Surface;
import com.google.android.exoplayer2.util.EGLSurfaceTexture;
import com.google.android.exoplayer2.util.i;
import com.google.android.exoplayer2.util.z;

public final class DummySurface extends Surface {
    private static int b;
    private static boolean c;
    public final boolean a;
    private final a d;
    private boolean e;

    public static synchronized boolean a(Context context) {
        boolean z;
        synchronized (DummySurface.class) {
            z = true;
            if (!c) {
                b = z.a < 24 ? 0 : b(context);
                c = true;
            }
            if (b == 0) {
                z = false;
            }
        }
        return z;
    }

    public static DummySurface a(Context context, boolean z) {
        a();
        int i = 0;
        com.google.android.exoplayer2.util.a.b(!z || a(context));
        a aVar = new a();
        if (z) {
            i = b;
        }
        return aVar.a(i);
    }

    private DummySurface(a aVar, SurfaceTexture surfaceTexture, boolean z) {
        super(surfaceTexture);
        this.d = aVar;
        this.a = z;
    }

    @Override // android.view.Surface
    public void release() {
        super.release();
        synchronized (this.d) {
            if (!this.e) {
                this.d.a();
                this.e = true;
            }
        }
    }

    private static void a() {
        if (z.a < 17) {
            throw new UnsupportedOperationException("Unsupported prior to API level 17");
        }
    }

    private static int b(Context context) {
        String eglQueryString;
        if (z.a < 26 && ("samsung".equals(z.c) || "XT1650".equals(z.d))) {
            return 0;
        }
        if ((z.a >= 26 || context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_VR_MODE_HIGH_PERFORMANCE)) && (eglQueryString = EGL14.eglQueryString(EGL14.eglGetDisplay(0), 12373)) != null && eglQueryString.contains("EGL_EXT_protected_content")) {
            return eglQueryString.contains("EGL_KHR_surfaceless_context") ? 1 : 2;
        }
        return 0;
    }

    /* access modifiers changed from: private */
    public static class a extends HandlerThread implements Handler.Callback {
        private EGLSurfaceTexture a;
        private Handler b;
        private Error c;
        private RuntimeException d;
        private DummySurface e;

        public a() {
            super("dummySurface");
        }

        public DummySurface a(int i) {
            boolean z;
            start();
            this.b = new Handler(getLooper(), this);
            this.a = new EGLSurfaceTexture(this.b);
            synchronized (this) {
                z = false;
                this.b.obtainMessage(1, i, 0).sendToTarget();
                while (this.e == null && this.d == null && this.c == null) {
                    try {
                        wait();
                    } catch (InterruptedException unused) {
                        z = true;
                    }
                }
            }
            if (z) {
                Thread.currentThread().interrupt();
            }
            RuntimeException runtimeException = this.d;
            if (runtimeException == null) {
                Error error = this.c;
                if (error == null) {
                    return (DummySurface) com.google.android.exoplayer2.util.a.a(this.e);
                }
                throw error;
            }
            throw runtimeException;
        }

        public void a() {
            com.google.android.exoplayer2.util.a.a(this.b);
            this.b.sendEmptyMessage(2);
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i = message.what;
            if (i == 1) {
                try {
                    b(message.arg1);
                    synchronized (this) {
                        notify();
                    }
                } catch (RuntimeException e) {
                    i.b("DummySurface", "Failed to initialize dummy surface", e);
                    this.d = e;
                    synchronized (this) {
                        notify();
                    }
                } catch (Error e2) {
                    try {
                        i.b("DummySurface", "Failed to initialize dummy surface", e2);
                        this.c = e2;
                        synchronized (this) {
                            notify();
                        }
                    } catch (Throwable th) {
                        synchronized (this) {
                            notify();
                            throw th;
                        }
                    }
                }
                return true;
            } else if (i != 2) {
                return true;
            } else {
                try {
                    b();
                } catch (Throwable th2) {
                    quit();
                    throw th2;
                }
                quit();
                return true;
            }
        }

        private void b(int i) {
            com.google.android.exoplayer2.util.a.a(this.a);
            this.a.a(i);
            this.e = new DummySurface(this, this.a.b(), i != 0);
        }

        private void b() {
            com.google.android.exoplayer2.util.a.a(this.a);
            this.a.a();
        }
    }
}
