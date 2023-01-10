package io.reactivex;

/* compiled from: Emitter */
public interface f<T> {
    void onComplete();

    void onError(Throwable th);

    void onNext(T t);
}
