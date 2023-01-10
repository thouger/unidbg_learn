package com.google.android.exoplayer2.util;

import android.os.Handler;
import android.os.Looper;

/* compiled from: Clock */
public interface b {
    public static final b a = new t();

    long a();

    h a(Looper looper, Handler.Callback callback);

    long b();
}
