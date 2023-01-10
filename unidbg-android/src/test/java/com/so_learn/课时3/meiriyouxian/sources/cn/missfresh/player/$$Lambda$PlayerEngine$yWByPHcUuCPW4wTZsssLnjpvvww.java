package cn.missfresh.player;

/* compiled from: lambda */
/* renamed from: cn.missfresh.player.-$$Lambda$PlayerEngine$yWByPHcUuCPW4wTZsssLnjpvvww  reason: invalid class name */
public final /* synthetic */ class $$Lambda$PlayerEngine$yWByPHcUuCPW4wTZsssLnjpvvww implements Runnable {
    private final /* synthetic */ PlayerEngine f$0;
    private final /* synthetic */ int f$1;
    private final /* synthetic */ int f$2;

    public /* synthetic */ $$Lambda$PlayerEngine$yWByPHcUuCPW4wTZsssLnjpvvww(PlayerEngine playerEngine, int i, int i2) {
        this.f$0 = playerEngine;
        this.f$1 = i;
        this.f$2 = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.lambda$onError$11$PlayerEngine(this.f$1, this.f$2);
    }
}
