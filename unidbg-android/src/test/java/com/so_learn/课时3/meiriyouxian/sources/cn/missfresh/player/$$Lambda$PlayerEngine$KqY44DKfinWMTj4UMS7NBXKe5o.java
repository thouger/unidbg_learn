package cn.missfresh.player;

/* compiled from: lambda */
/* renamed from: cn.missfresh.player.-$$Lambda$PlayerEngine$KqY44DKfinWMT-j4UMS7NBXKe5o  reason: invalid class name */
public final /* synthetic */ class $$Lambda$PlayerEngine$KqY44DKfinWMTj4UMS7NBXKe5o implements Runnable {
    private final /* synthetic */ PlayerEngine f$0;
    private final /* synthetic */ int f$1;

    public /* synthetic */ $$Lambda$PlayerEngine$KqY44DKfinWMTj4UMS7NBXKe5o(PlayerEngine playerEngine, int i) {
        this.f$0 = playerEngine;
        this.f$1 = i;
    }

    @Override // java.lang.Runnable
    public final void run() {
        this.f$0.lambda$onBufferingUpdate$9$PlayerEngine(this.f$1);
    }
}
