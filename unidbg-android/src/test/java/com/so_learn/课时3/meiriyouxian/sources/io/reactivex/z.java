package io.reactivex;

import io.reactivex.disposables.b;

/* compiled from: SingleObserver */
public interface z<T> {
    void onError(Throwable th);

    void onSubscribe(b bVar);

    void onSuccess(T t);
}
