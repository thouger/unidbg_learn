package com.google.android.exoplayer2.util;

import java.util.concurrent.ThreadFactory;

/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.util.-$$Lambda$z$unJuw63c1ZCqI1xA1GEKkR3iY24  reason: invalid class name */
public final /* synthetic */ class $$Lambda$z$unJuw63c1ZCqI1xA1GEKkR3iY24 implements ThreadFactory {
    private final /* synthetic */ String f$0;

    public /* synthetic */ $$Lambda$z$unJuw63c1ZCqI1xA1GEKkR3iY24(String str) {
        this.f$0 = str;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        return z.a(this.f$0, runnable);
    }
}
