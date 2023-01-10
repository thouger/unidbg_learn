package com.google.android.exoplayer2.drm;

import android.media.MediaCrypto;

/* compiled from: FrameworkMediaCrypto */
public final class e implements d {
    private final MediaCrypto a;
    private final boolean b;

    public MediaCrypto a() {
        return this.a;
    }

    public boolean a(String str) {
        return !this.b && this.a.requiresSecureDecoderComponent(str);
    }
}
