package com.google.android.gles_jni;

import com.android.internal.logging.nano.MetricsProto;
import javax.microedition.khronos.egl.EGLSurface;

public class EGLSurfaceImpl extends EGLSurface {
    long mEGLSurface;

    public EGLSurfaceImpl() {
        this.mEGLSurface = 0;
    }

    public EGLSurfaceImpl(long surface) {
        this.mEGLSurface = surface;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.mEGLSurface == ((EGLSurfaceImpl) o).mEGLSurface;
    }

    public int hashCode() {
        return ((int) (this.mEGLSurface ^ (this.mEGLSurface >>> 32))) + MetricsProto.MetricsEvent.DIALOG_SUPPORT_PHONE;
    }
}
