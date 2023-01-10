package com.google.android.exoplayer2.util;

import android.graphics.SurfaceTexture;
import android.opengl.EGL14;
import android.opengl.EGLConfig;
import android.opengl.EGLContext;
import android.opengl.EGLDisplay;
import android.opengl.EGLSurface;
import android.opengl.GLES20;
import android.os.Handler;

public final class EGLSurfaceTexture implements SurfaceTexture.OnFrameAvailableListener, Runnable {
    private static final int[] a = {12352, 4, 12324, 8, 12323, 8, 12322, 8, 12321, 8, 12325, 0, 12327, 12344, 12339, 4, 12344};
    private final Handler b;
    private final int[] c;
    private final a d;
    private EGLDisplay e;
    private EGLContext f;
    private EGLSurface g;
    private SurfaceTexture h;

    public interface a {
        void a();
    }

    public static final class GlException extends RuntimeException {
        private GlException(String str) {
            super(str);
        }
    }

    public EGLSurfaceTexture(Handler handler) {
        this(handler, null);
    }

    public EGLSurfaceTexture(Handler handler, a aVar) {
        this.b = handler;
        this.d = aVar;
        this.c = new int[1];
    }

    public void a(int i) {
        this.e = d();
        EGLConfig a2 = a(this.e);
        this.f = a(this.e, a2, i);
        this.g = a(this.e, a2, this.f, i);
        a(this.c);
        this.h = new SurfaceTexture(this.c[0]);
        this.h.setOnFrameAvailableListener(this);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [android.opengl.EGLContext, android.graphics.SurfaceTexture, android.opengl.EGLSurface, android.opengl.EGLDisplay] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a() {
        /*
            r7 = this;
            android.os.Handler r0 = r7.b
            r0.removeCallbacks(r7)
            r0 = 19
            r1 = 0
            android.graphics.SurfaceTexture r2 = r7.h     // Catch:{ all -> 0x006c }
            if (r2 == 0) goto L_0x0018
            android.graphics.SurfaceTexture r2 = r7.h     // Catch:{ all -> 0x006c }
            r2.release()     // Catch:{ all -> 0x006c }
            r2 = 1
            int[] r3 = r7.c     // Catch:{ all -> 0x006c }
            r4 = 0
            android.opengl.GLES20.glDeleteTextures(r2, r3, r4)     // Catch:{ all -> 0x006c }
        L_0x0018:
            android.opengl.EGLDisplay r2 = r7.e
            if (r2 == 0) goto L_0x002f
            android.opengl.EGLDisplay r3 = android.opengl.EGL14.EGL_NO_DISPLAY
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x002f
            android.opengl.EGLDisplay r2 = r7.e
            android.opengl.EGLSurface r3 = android.opengl.EGL14.EGL_NO_SURFACE
            android.opengl.EGLSurface r4 = android.opengl.EGL14.EGL_NO_SURFACE
            android.opengl.EGLContext r5 = android.opengl.EGL14.EGL_NO_CONTEXT
            android.opengl.EGL14.eglMakeCurrent(r2, r3, r4, r5)
        L_0x002f:
            android.opengl.EGLSurface r2 = r7.g
            if (r2 == 0) goto L_0x0042
            android.opengl.EGLSurface r3 = android.opengl.EGL14.EGL_NO_SURFACE
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L_0x0042
            android.opengl.EGLDisplay r2 = r7.e
            android.opengl.EGLSurface r3 = r7.g
            android.opengl.EGL14.eglDestroySurface(r2, r3)
        L_0x0042:
            android.opengl.EGLContext r2 = r7.f
            if (r2 == 0) goto L_0x004b
            android.opengl.EGLDisplay r3 = r7.e
            android.opengl.EGL14.eglDestroyContext(r3, r2)
        L_0x004b:
            int r2 = com.google.android.exoplayer2.util.z.a
            if (r2 < r0) goto L_0x0052
            android.opengl.EGL14.eglReleaseThread()
        L_0x0052:
            android.opengl.EGLDisplay r0 = r7.e
            if (r0 == 0) goto L_0x0063
            android.opengl.EGLDisplay r2 = android.opengl.EGL14.EGL_NO_DISPLAY
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0063
            android.opengl.EGLDisplay r0 = r7.e
            android.opengl.EGL14.eglTerminate(r0)
        L_0x0063:
            r7.e = r1
            r7.f = r1
            r7.g = r1
            r7.h = r1
            return
        L_0x006c:
            r2 = move-exception
            android.opengl.EGLDisplay r3 = r7.e
            if (r3 == 0) goto L_0x0084
            android.opengl.EGLDisplay r4 = android.opengl.EGL14.EGL_NO_DISPLAY
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0084
            android.opengl.EGLDisplay r3 = r7.e
            android.opengl.EGLSurface r4 = android.opengl.EGL14.EGL_NO_SURFACE
            android.opengl.EGLSurface r5 = android.opengl.EGL14.EGL_NO_SURFACE
            android.opengl.EGLContext r6 = android.opengl.EGL14.EGL_NO_CONTEXT
            android.opengl.EGL14.eglMakeCurrent(r3, r4, r5, r6)
        L_0x0084:
            android.opengl.EGLSurface r3 = r7.g
            if (r3 == 0) goto L_0x0097
            android.opengl.EGLSurface r4 = android.opengl.EGL14.EGL_NO_SURFACE
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L_0x0097
            android.opengl.EGLDisplay r3 = r7.e
            android.opengl.EGLSurface r4 = r7.g
            android.opengl.EGL14.eglDestroySurface(r3, r4)
        L_0x0097:
            android.opengl.EGLContext r3 = r7.f
            if (r3 == 0) goto L_0x00a0
            android.opengl.EGLDisplay r4 = r7.e
            android.opengl.EGL14.eglDestroyContext(r4, r3)
        L_0x00a0:
            int r3 = com.google.android.exoplayer2.util.z.a
            if (r3 < r0) goto L_0x00a7
            android.opengl.EGL14.eglReleaseThread()
        L_0x00a7:
            android.opengl.EGLDisplay r0 = r7.e
            if (r0 == 0) goto L_0x00b8
            android.opengl.EGLDisplay r3 = android.opengl.EGL14.EGL_NO_DISPLAY
            boolean r0 = r0.equals(r3)
            if (r0 != 0) goto L_0x00b8
            android.opengl.EGLDisplay r0 = r7.e
            android.opengl.EGL14.eglTerminate(r0)
        L_0x00b8:
            r7.e = r1
            r7.f = r1
            r7.g = r1
            r7.h = r1
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.util.EGLSurfaceTexture.a():void");
    }

    public SurfaceTexture b() {
        return (SurfaceTexture) a.a(this.h);
    }

    @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
    public void onFrameAvailable(SurfaceTexture surfaceTexture) {
        this.b.post(this);
    }

    @Override // java.lang.Runnable
    public void run() {
        c();
        SurfaceTexture surfaceTexture = this.h;
        if (surfaceTexture != null) {
            try {
                surfaceTexture.updateTexImage();
            } catch (RuntimeException unused) {
            }
        }
    }

    private void c() {
        a aVar = this.d;
        if (aVar != null) {
            aVar.a();
        }
    }

    private static EGLDisplay d() {
        EGLDisplay eglGetDisplay = EGL14.eglGetDisplay(0);
        if (eglGetDisplay != null) {
            int[] iArr = new int[2];
            if (EGL14.eglInitialize(eglGetDisplay, iArr, 0, iArr, 1)) {
                return eglGetDisplay;
            }
            throw new GlException("eglInitialize failed");
        }
        throw new GlException("eglGetDisplay failed");
    }

    private static EGLConfig a(EGLDisplay eGLDisplay) {
        EGLConfig[] eGLConfigArr = new EGLConfig[1];
        int[] iArr = new int[1];
        boolean eglChooseConfig = EGL14.eglChooseConfig(eGLDisplay, a, 0, eGLConfigArr, 0, 1, iArr, 0);
        if (eglChooseConfig && iArr[0] > 0 && eGLConfigArr[0] != null) {
            return eGLConfigArr[0];
        }
        throw new GlException(z.a("eglChooseConfig failed: success=%b, numConfigs[0]=%d, configs[0]=%s", Boolean.valueOf(eglChooseConfig), Integer.valueOf(iArr[0]), eGLConfigArr[0]));
    }

    private static EGLContext a(EGLDisplay eGLDisplay, EGLConfig eGLConfig, int i) {
        EGLContext eglCreateContext = EGL14.eglCreateContext(eGLDisplay, eGLConfig, EGL14.EGL_NO_CONTEXT, i == 0 ? new int[]{12440, 2, 12344} : new int[]{12440, 2, 12992, 1, 12344}, 0);
        if (eglCreateContext != null) {
            return eglCreateContext;
        }
        throw new GlException("eglCreateContext failed");
    }

    private static EGLSurface a(EGLDisplay eGLDisplay, EGLConfig eGLConfig, EGLContext eGLContext, int i) {
        EGLSurface eGLSurface;
        int[] iArr;
        if (i == 1) {
            eGLSurface = EGL14.EGL_NO_SURFACE;
        } else {
            if (i == 2) {
                iArr = new int[]{12375, 1, 12374, 1, 12992, 1, 12344};
            } else {
                iArr = new int[]{12375, 1, 12374, 1, 12344};
            }
            eGLSurface = EGL14.eglCreatePbufferSurface(eGLDisplay, eGLConfig, iArr, 0);
            if (eGLSurface == null) {
                throw new GlException("eglCreatePbufferSurface failed");
            }
        }
        if (EGL14.eglMakeCurrent(eGLDisplay, eGLSurface, eGLSurface, eGLContext)) {
            return eGLSurface;
        }
        throw new GlException("eglMakeCurrent failed");
    }

    private static void a(int[] iArr) {
        GLES20.glGenTextures(1, iArr, 0);
        int glGetError = GLES20.glGetError();
        if (glGetError != 0) {
            throw new GlException("glGenTextures failed. Error: " + Integer.toHexString(glGetError));
        }
    }
}
