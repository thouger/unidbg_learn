package com.google.android.exoplayer2.video;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.video.f;

/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.video.-$$Lambda$f$a$GaKCmYcF-5zW9mkaH8HpR7DE0vY  reason: invalid class name */
public final /* synthetic */ class $$Lambda$f$a$GaKCmYcF5zW9mkaH8HpR7DE0vY implements Runnable {
    private final /* synthetic */ f.a f$0;
    private final /* synthetic */ Format f$1;

    public /* synthetic */ $$Lambda$f$a$GaKCmYcF5zW9mkaH8HpR7DE0vY(f.a aVar, Format format) {
        this.f$0 = aVar;
        this.f$1 = format;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.b(this.f$1);
    }
}
