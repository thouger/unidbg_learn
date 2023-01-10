package io.reactivex.disposables;

import io.reactivex.c.a;
import io.reactivex.internal.util.ExceptionHelper;

final class ActionDisposable extends ReferenceDisposable<a> {
    private static final long serialVersionUID = -8219729196779211169L;

    ActionDisposable(a aVar) {
        super(aVar);
    }

    /* access modifiers changed from: protected */
    public void onDisposed(a aVar) {
        try {
            aVar.a();
        } catch (Throwable th) {
            throw ExceptionHelper.a(th);
        }
    }
}
