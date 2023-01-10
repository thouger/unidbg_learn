package com.google.android.exoplayer2.drm;

import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.util.f;

/* compiled from: lambda */
/* renamed from: com.google.android.exoplayer2.drm.-$$Lambda$DefaultDrmSessionManager$dlGaPQEkG1T-1KVYIhiWlBuOEMM  reason: invalid class name */
public final /* synthetic */ class $$Lambda$DefaultDrmSessionManager$dlGaPQEkG1T1KVYIhiWlBuOEMM implements f.a {
    private final /* synthetic */ DefaultDrmSessionManager.MissingSchemeDataException f$0;

    public /* synthetic */ $$Lambda$DefaultDrmSessionManager$dlGaPQEkG1T1KVYIhiWlBuOEMM(DefaultDrmSessionManager.MissingSchemeDataException missingSchemeDataException) {
        this.f$0 = missingSchemeDataException;
    }

    @Override // com.google.android.exoplayer2.util.f.a
    public final void sendTo(Object obj) {
        ((a) obj).a(this.f$0);
    }
}
