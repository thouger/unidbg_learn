package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.d;
import java.util.Map;

public interface DrmSession<T extends d> {
    int e();

    DrmSessionException f();

    T g();

    Map<String, String> h();

    public static class DrmSessionException extends Exception {
        public DrmSessionException(Throwable th) {
            super(th);
        }
    }
}
