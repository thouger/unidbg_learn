package com.google.android.exoplayer2.source;

import com.google.android.exoplayer2.source.n;
import java.io.IOException;

/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.source.-$$Lambda$n$a$83lcLht6Mv_IBCLzNghsxEDNdLA  reason: invalid class name */
public final /* synthetic */ class $$Lambda$n$a$83lcLht6Mv_IBCLzNghsxEDNdLA implements Runnable {
    private final /* synthetic */ n.a f$0;
    private final /* synthetic */ n f$1;
    private final /* synthetic */ n.b f$2;
    private final /* synthetic */ n.c f$3;
    private final /* synthetic */ IOException f$4;
    private final /* synthetic */ boolean f$5;

    public /* synthetic */ $$Lambda$n$a$83lcLht6Mv_IBCLzNghsxEDNdLA(n.a aVar, n nVar, n.b bVar, n.c cVar, IOException iOException, boolean z) {
        this.f$0 = aVar;
        this.f$1 = nVar;
        this.f$2 = bVar;
        this.f$3 = cVar;
        this.f$4 = iOException;
        this.f$5 = z;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.a(this.f$1, this.f$2, this.f$3, this.f$4, this.f$5);
    }
}
