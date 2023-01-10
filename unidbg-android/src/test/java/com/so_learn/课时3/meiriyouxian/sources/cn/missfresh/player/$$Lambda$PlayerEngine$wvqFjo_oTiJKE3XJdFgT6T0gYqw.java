package cn.missfresh.player;

/* compiled from: lambda */
/* renamed from: cn.missfresh.player.-$$Lambda$PlayerEngine$wvqFjo_oTiJKE3XJdFgT6T0gYqw  reason: invalid class name */
public final /* synthetic */ class $$Lambda$PlayerEngine$wvqFjo_oTiJKE3XJdFgT6T0gYqw implements Runnable {
    private final /* synthetic */ PlayerEngine f$0;
    private final /* synthetic */ float f$1;
    private final /* synthetic */ float f$2;

    public /* synthetic */ $$Lambda$PlayerEngine$wvqFjo_oTiJKE3XJdFgT6T0gYqw(PlayerEngine playerEngine, float f, float f2) {
        this.f$0 = playerEngine;
        this.f$1 = f;
        this.f$2 = f2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.lambda$setVolume$6$PlayerEngine(this.f$1, this.f$2);
    }
}
