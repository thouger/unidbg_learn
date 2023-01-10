package com.google.android.exoplayer2.util;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;

/* compiled from: SystemClock */
final class t implements b {
    t() {
    }

    @Override // com.google.android.exoplayer2.util.b
    public long a() {
        return SystemClock.elapsedRealtime();
    }

    @Override // com.google.android.exoplayer2.util.b
    public long b() {
        return SystemClock.uptimeMillis();
    }

    @Override // com.google.android.exoplayer2.util.b
    public h a(Looper looper, Handler.Callback callback) {
        return new u(new Handler(looper, callback));
    }
}
