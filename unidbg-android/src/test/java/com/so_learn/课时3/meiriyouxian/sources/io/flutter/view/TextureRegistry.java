package io.flutter.view;

import android.graphics.SurfaceTexture;

public interface TextureRegistry {

    public interface SurfaceTextureEntry {
        long id();

        void release();

        SurfaceTexture surfaceTexture();
    }

    SurfaceTextureEntry createSurfaceTexture();
}
