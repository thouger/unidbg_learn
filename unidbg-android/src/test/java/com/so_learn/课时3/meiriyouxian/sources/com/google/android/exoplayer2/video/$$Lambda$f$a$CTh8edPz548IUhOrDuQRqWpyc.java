package com.google.android.exoplayer2.video;

import android.view.Surface;
import com.google.android.exoplayer2.video.f;

/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.video.-$$Lambda$f$a$CTh8ed-Pz548-IUhOrDuQRqWpyc  reason: invalid class name */
public final /* synthetic */ class $$Lambda$f$a$CTh8edPz548IUhOrDuQRqWpyc implements Runnable {
    private final /* synthetic */ f.a f$0;
    private final /* synthetic */ Surface f$1;

    public /* synthetic */ $$Lambda$f$a$CTh8edPz548IUhOrDuQRqWpyc(f.a aVar, Surface surface) {
        this.f$0 = aVar;
        this.f$1 = surface;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.b(this.f$1);
    }
}
