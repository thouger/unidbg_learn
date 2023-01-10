package io.flutter.embedding.engine.renderer;

import android.graphics.SurfaceTexture;

public class SurfaceTextureWrapper {
    private boolean released = false;
    private SurfaceTexture surfaceTexture;

    public SurfaceTextureWrapper(SurfaceTexture surfaceTexture) {
        this.surfaceTexture = surfaceTexture;
    }

    public SurfaceTexture surfaceTexture() {
        return this.surfaceTexture;
    }

    public void updateTexImage() {
        synchronized (this) {
            if (!this.released) {
                this.surfaceTexture.updateTexImage();
            }
        }
    }

    public void release() {
        synchronized (this) {
            if (!this.released) {
                this.surfaceTexture.release();
                this.released = true;
            }
        }
    }

    public void attachToGLContext(int i) {
        this.surfaceTexture.attachToGLContext(i);
    }

    public void detachFromGLContext() {
        this.surfaceTexture.detachFromGLContext();
    }

    public void getTransformMatrix(float[] fArr) {
        this.surfaceTexture.getTransformMatrix(fArr);
    }
}
