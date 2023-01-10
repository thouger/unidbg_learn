package io.flutter.embedding.engine.deferredcomponents;

import com.google.android.play.core.tasks.OnFailureListener;

/* compiled from: lambda */
/* renamed from: io.flutter.embedding.engine.deferredcomponents.-$$Lambda$PlayStoreDeferredComponentManager$caXMJn3gSooh-8rU1uNn1IpLCi4  reason: invalid class name */
public final /* synthetic */ class $$Lambda$PlayStoreDeferredComponentManager$caXMJn3gSooh8rU1uNn1IpLCi4 implements OnFailureListener {
    private final /* synthetic */ PlayStoreDeferredComponentManager f$0;
    private final /* synthetic */ int f$1;
    private final /* synthetic */ String f$2;

    public /* synthetic */ $$Lambda$PlayStoreDeferredComponentManager$caXMJn3gSooh8rU1uNn1IpLCi4(PlayStoreDeferredComponentManager playStoreDeferredComponentManager, int i, String str) {
        this.f$0 = playStoreDeferredComponentManager;
        this.f$1 = i;
        this.f$2 = str;
    }

    public final void onFailure(Exception exc) {
        this.f$0.lambda$installDeferredComponent$1$PlayStoreDeferredComponentManager(this.f$1, this.f$2, exc);
    }
}
