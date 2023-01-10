package com.google.android.exoplayer2.audio;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.audio.f;

/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.audio.-$$Lambda$f$a$slQ75LeEPZozpuZt0z6SCGb5oRo  reason: invalid class name */
public final /* synthetic */ class $$Lambda$f$a$slQ75LeEPZozpuZt0z6SCGb5oRo implements Runnable {
    private final /* synthetic */ f.a f$0;
    private final /* synthetic */ Format f$1;

    public /* synthetic */ $$Lambda$f$a$slQ75LeEPZozpuZt0z6SCGb5oRo(f.a aVar, Format format) {
        this.f$0 = aVar;
        this.f$1 = format;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.b(this.f$1);
    }
}
