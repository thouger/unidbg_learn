package io.flutter.embedding.engine.deferredcomponents;

import com.google.android.play.core.tasks.OnSuccessListener;

/* compiled from: lambda */
/* renamed from: io.flutter.embedding.engine.deferredcomponents.-$$Lambda$PlayStoreDeferredComponentManager$omduCs2FVXzWSeh_qx7q-N89fLE  reason: invalid class name */
public final /* synthetic */ class $$Lambda$PlayStoreDeferredComponentManager$omduCs2FVXzWSeh_qx7qN89fLE implements OnSuccessListener {
    private final /* synthetic */ PlayStoreDeferredComponentManager f$0;
    private final /* synthetic */ String f$1;
    private final /* synthetic */ int f$2;

    public /* synthetic */ $$Lambda$PlayStoreDeferredComponentManager$omduCs2FVXzWSeh_qx7qN89fLE(PlayStoreDeferredComponentManager playStoreDeferredComponentManager, String str, int i) {
        this.f$0 = playStoreDeferredComponentManager;
        this.f$1 = str;
        this.f$2 = i;
    }

    public final void onSuccess(Object obj) {
        this.f$0.lambda$installDeferredComponent$0$PlayStoreDeferredComponentManager(this.f$1, this.f$2, (Integer) obj);
    }
}
