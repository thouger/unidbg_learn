package com.google.android.gles_jni;

import com.android.internal.logging.nano.MetricsProto;
import javax.microedition.khronos.egl.EGLContext;
import javax.microedition.khronos.opengles.GL;

public class EGLContextImpl extends EGLContext {
    long mEGLContext;
    private GLImpl mGLContext = new GLImpl();

    public EGLContextImpl(long ctx) {
        this.mEGLContext = ctx;
    }

    @Override // javax.microedition.khronos.egl.EGLContext
    public GL getGL() {
        return this.mGLContext;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        return this.mEGLContext == ((EGLContextImpl) o).mEGLContext;
    }

    public int hashCode() {
        return ((int) (this.mEGLContext ^ (this.mEGLContext >>> 32))) + MetricsProto.MetricsEvent.DIALOG_SUPPORT_PHONE;
    }
}
