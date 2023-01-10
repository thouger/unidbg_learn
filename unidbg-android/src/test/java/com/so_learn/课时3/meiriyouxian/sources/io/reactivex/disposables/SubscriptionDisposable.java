package io.reactivex.disposables;

import org.a.d;

final class SubscriptionDisposable extends ReferenceDisposable<d> {
    private static final long serialVersionUID = -707001650852963139L;

    SubscriptionDisposable(d dVar) {
        super(dVar);
    }

    /* access modifiers changed from: protected */
    public void onDisposed(d dVar) {
        dVar.cancel();
    }
}
