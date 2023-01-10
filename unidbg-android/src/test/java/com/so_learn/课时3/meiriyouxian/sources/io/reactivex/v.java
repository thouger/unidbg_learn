package io.reactivex;

import io.reactivex.disposables.b;

/* compiled from: Observer */
public interface v<T> {
    void onComplete();

    void onError(Throwable th);

    void onNext(T t);

    void onSubscribe(b bVar);
}
