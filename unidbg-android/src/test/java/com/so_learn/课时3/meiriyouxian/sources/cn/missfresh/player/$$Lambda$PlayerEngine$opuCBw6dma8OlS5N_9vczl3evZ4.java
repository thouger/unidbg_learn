package cn.missfresh.player;

/* compiled from: lambda */
/* renamed from: cn.missfresh.player.-$$Lambda$PlayerEngine$opuCBw6dma8OlS5N_9vczl3evZ4  reason: invalid class name */
public final /* synthetic */ class $$Lambda$PlayerEngine$opuCBw6dma8OlS5N_9vczl3evZ4 implements Runnable {
    private final /* synthetic */ PlayerEngine f$0;
    private final /* synthetic */ long f$1;

    public /* synthetic */ $$Lambda$PlayerEngine$opuCBw6dma8OlS5N_9vczl3evZ4(PlayerEngine playerEngine, long j) {
        this.f$0 = playerEngine;
        this.f$1 = j;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.lambda$seekTo$4$PlayerEngine(this.f$1);
    }
}
