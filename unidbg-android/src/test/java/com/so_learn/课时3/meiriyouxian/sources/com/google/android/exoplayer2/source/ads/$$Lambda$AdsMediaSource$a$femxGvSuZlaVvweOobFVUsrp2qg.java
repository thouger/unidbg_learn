package com.google.android.exoplayer2.source.ads;

import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import java.io.IOException;

/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.source.ads.-$$Lambda$AdsMediaSource$a$femxGvSuZlaVvweOobFVUsrp2qg  reason: invalid class name */
public final /* synthetic */ class $$Lambda$AdsMediaSource$a$femxGvSuZlaVvweOobFVUsrp2qg implements Runnable {
    private final /* synthetic */ AdsMediaSource.a f$0;
    private final /* synthetic */ IOException f$1;

    public /* synthetic */ $$Lambda$AdsMediaSource$a$femxGvSuZlaVvweOobFVUsrp2qg(AdsMediaSource.a aVar, IOException iOException) {
        this.f$0 = aVar;
        this.f$1 = iOException;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.a(this.f$1);
    }
}
