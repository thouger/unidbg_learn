package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.d;
import com.google.android.exoplayer2.util.a;
import java.util.Map;

/* compiled from: ErrorStateDrmSession */
public final class c<T extends d> implements DrmSession<T> {
    private final DrmSession.DrmSessionException a;

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public int e() {
        return 1;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public T g() {
        return null;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public Map<String, String> h() {
        return null;
    }

    public c(DrmSession.DrmSessionException drmSessionException) {
        this.a = (DrmSession.DrmSessionException) a.a(drmSessionException);
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public DrmSession.DrmSessionException f() {
        return this.a;
    }
}
