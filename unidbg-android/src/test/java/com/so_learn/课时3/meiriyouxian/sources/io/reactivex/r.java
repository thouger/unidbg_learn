package io.reactivex;

import io.reactivex.c.f;
import io.reactivex.disposables.b;

/* compiled from: ObservableEmitter */
public interface r<T> extends f<T> {
    boolean isDisposed();

    void setCancellable(f fVar);

    void setDisposable(b bVar);
}
