package io.flutter.plugin.platform;

import android.view.View;
import io.flutter.embedding.engine.systemchannels.PlatformViewsChannel;
import io.flutter.plugin.platform.PlatformViewsController;

/* compiled from: lambda */
/* renamed from: io.flutter.plugin.platform.-$$Lambda$PlatformViewsController$1$i4ojtb0pOVFKsQcwzERs83K3E8M  reason: invalid class name */
public final /* synthetic */ class $$Lambda$PlatformViewsController$1$i4ojtb0pOVFKsQcwzERs83K3E8M implements View.OnFocusChangeListener {
    private final /* synthetic */ PlatformViewsController.AnonymousClass1 f$0;
    private final /* synthetic */ PlatformViewsChannel.PlatformViewCreationRequest f$1;

    public /* synthetic */ $$Lambda$PlatformViewsController$1$i4ojtb0pOVFKsQcwzERs83K3E8M(PlatformViewsController.AnonymousClass1 r1, PlatformViewsChannel.PlatformViewCreationRequest platformViewCreationRequest) {
        this.f$0 = r1;
        this.f$1 = platformViewCreationRequest;
    }

    @Override // android.view.View.OnFocusChangeListener
    public final void onFocusChange(View view, boolean z) {
        this.f$0.lambda$createVirtualDisplayForPlatformView$0$PlatformViewsController$1(this.f$1, view, z);
    }
}
