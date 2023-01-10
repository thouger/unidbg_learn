package io.reactivex.internal.util;

import io.reactivex.c;
import io.reactivex.disposables.b;
import io.reactivex.e.a;
import io.reactivex.j;
import io.reactivex.m;
import io.reactivex.v;
import io.reactivex.z;
import org.a.d;

public enum EmptyComponent implements c, b, j<Object>, m<Object>, v<Object>, z<Object>, d {
    INSTANCE;

    public void cancel() {
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return true;
    }

    @Override // io.reactivex.c
    public void onComplete() {
    }

    @Override // io.reactivex.v
    public void onNext(Object obj) {
    }

    @Override // io.reactivex.m
    public void onSuccess(Object obj) {
    }

    public void request(long j) {
    }

    public static <T> org.a.c<T> asSubscriber() {
        return INSTANCE;
    }

    public static <T> v<T> asObserver() {
        return INSTANCE;
    }

    @Override // io.reactivex.c
    public void onSubscribe(b bVar) {
        bVar.dispose();
    }

    @Override // io.reactivex.j
    public void onSubscribe(d dVar) {
        dVar.cancel();
    }

    @Override // io.reactivex.c
    public void onError(Throwable th) {
        a.a(th);
    }
}
