package com.google.android.exoplayer2.source.ads;

import com.google.android.exoplayer2.source.ads.AdsMediaSource;

/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.source.ads.-$$Lambda$AdsMediaSource$690BcMxdEwPrc9QZfVFQohCKSGA  reason: invalid class name */
public final /* synthetic */ class $$Lambda$AdsMediaSource$690BcMxdEwPrc9QZfVFQohCKSGA implements Runnable {
    private final /* synthetic */ AdsMediaSource f$0;
    private final /* synthetic */ AdsMediaSource.b f$1;

    public /* synthetic */ $$Lambda$AdsMediaSource$690BcMxdEwPrc9QZfVFQohCKSGA(AdsMediaSource adsMediaSource, AdsMediaSource.b bVar) {
        this.f$0 = adsMediaSource;
        this.f$1 = bVar;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.a(this.f$1);
    }
}
