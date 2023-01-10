package io.reactivex;

import io.reactivex.disposables.b;

/* compiled from: CompletableObserver */
public interface c {
    void onComplete();

    void onError(Throwable th);

    void onSubscribe(b bVar);
}
