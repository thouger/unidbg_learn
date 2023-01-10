package com.google.android.exoplayer2.drm;

import android.os.Looper;
import com.google.android.exoplayer2.drm.d;

/* compiled from: DrmSessionManager */
public interface b<T extends d> {
    DrmSession<T> a(Looper looper, DrmInitData drmInitData);

    void a(DrmSession<T> drmSession);

    boolean a(DrmInitData drmInitData);
}
