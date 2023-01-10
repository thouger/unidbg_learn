package io.flutter.embedding.engine.systemchannels;

import android.view.KeyEvent;
import io.flutter.plugin.common.BasicMessageChannel;

/* compiled from: lambda */
/* renamed from: io.flutter.embedding.engine.systemchannels.-$$Lambda$KeyEventChannel$ks_N3mtKlEyd7XxJih2Mvfzln-Y  reason: invalid class name */
public final /* synthetic */ class $$Lambda$KeyEventChannel$ks_N3mtKlEyd7XxJih2MvfzlnY implements BasicMessageChannel.Reply {
    private final /* synthetic */ KeyEventChannel f$0;
    private final /* synthetic */ KeyEvent f$1;

    public /* synthetic */ $$Lambda$KeyEventChannel$ks_N3mtKlEyd7XxJih2MvfzlnY(KeyEventChannel keyEventChannel, KeyEvent keyEvent) {
        this.f$0 = keyEventChannel;
        this.f$1 = keyEvent;
    }

    @Override // io.flutter.plugin.common.BasicMessageChannel.Reply
    public final void reply(Object obj) {
        this.f$0.lambda$createReplyHandler$0$KeyEventChannel(this.f$1, obj);
    }
}
