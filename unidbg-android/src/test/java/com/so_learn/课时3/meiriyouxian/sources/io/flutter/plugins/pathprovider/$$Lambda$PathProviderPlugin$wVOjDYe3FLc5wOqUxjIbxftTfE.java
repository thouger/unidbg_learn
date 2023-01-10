package io.flutter.plugins.pathprovider;

import com.google.common.util.concurrent.u;
import java.util.concurrent.Callable;

/* compiled from: lambda */
/* renamed from: io.flutter.plugins.pathprovider.-$$Lambda$PathProviderPlugin$wVOjDYe3FLc5wOqUxj-IbxftTfE  reason: invalid class name */
public final /* synthetic */ class $$Lambda$PathProviderPlugin$wVOjDYe3FLc5wOqUxjIbxftTfE implements Runnable {
    private final /* synthetic */ u f$0;
    private final /* synthetic */ Callable f$1;

    public /* synthetic */ $$Lambda$PathProviderPlugin$wVOjDYe3FLc5wOqUxjIbxftTfE(u uVar, Callable callable) {
        this.f$0 = uVar;
        this.f$1 = callable;
    }

    @Override // java.lang.Runnable
    public final void run() {
        PathProviderPlugin.lambda$executeInBackground$0(this.f$0, this.f$1);
    }
}
