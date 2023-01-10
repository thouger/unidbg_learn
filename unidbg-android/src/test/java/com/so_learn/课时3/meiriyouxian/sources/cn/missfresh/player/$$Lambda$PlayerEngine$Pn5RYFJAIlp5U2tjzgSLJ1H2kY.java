package cn.missfresh.player;

/* compiled from: lambda */
/* renamed from: cn.missfresh.player.-$$Lambda$PlayerEngine$P-n5RYFJAIlp5U2tjzgSLJ1H2kY  reason: invalid class name */
public final /* synthetic */ class $$Lambda$PlayerEngine$Pn5RYFJAIlp5U2tjzgSLJ1H2kY implements Runnable {
    private final /* synthetic */ PlayerEngine f$0;
    private final /* synthetic */ int f$1;
    private final /* synthetic */ int f$2;

    public /* synthetic */ $$Lambda$PlayerEngine$Pn5RYFJAIlp5U2tjzgSLJ1H2kY(PlayerEngine playerEngine, int i, int i2) {
        this.f$0 = playerEngine;
        this.f$1 = i;
        this.f$2 = i2;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.lambda$onVideoSizeChanged$13$PlayerEngine(this.f$1, this.f$2);
    }
}
