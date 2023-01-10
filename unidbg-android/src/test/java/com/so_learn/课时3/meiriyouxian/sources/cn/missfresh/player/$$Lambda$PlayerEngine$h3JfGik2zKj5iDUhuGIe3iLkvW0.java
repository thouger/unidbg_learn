package cn.missfresh.player;

import android.media.MediaPlayer;
import android.os.HandlerThread;

/* compiled from: lambda */
/* renamed from: cn.missfresh.player.-$$Lambda$PlayerEngine$h3JfGik2zKj5iDUhuGIe3iLkvW0  reason: invalid class name */
public final /* synthetic */ class $$Lambda$PlayerEngine$h3JfGik2zKj5iDUhuGIe3iLkvW0 implements Runnable {
    private final /* synthetic */ MediaPlayer f$0;
    private final /* synthetic */ HandlerThread f$1;

    public /* synthetic */ $$Lambda$PlayerEngine$h3JfGik2zKj5iDUhuGIe3iLkvW0(MediaPlayer mediaPlayer, HandlerThread handlerThread) {
        this.f$0 = mediaPlayer;
        this.f$1 = handlerThread;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PlayerEngine.lambda$release$5(this.f$0, this.f$1);
    }
}
