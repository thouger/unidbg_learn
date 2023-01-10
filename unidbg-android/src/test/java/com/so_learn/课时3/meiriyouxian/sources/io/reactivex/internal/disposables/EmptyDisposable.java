package io.reactivex.internal.disposables;

import io.reactivex.internal.a.c;
import io.reactivex.m;
import io.reactivex.v;
import io.reactivex.z;

public enum EmptyDisposable implements c<Object> {
    INSTANCE,
    NEVER;

    @Override // io.reactivex.internal.a.h
    public void clear() {
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
    }

    @Override // io.reactivex.internal.a.h
    public boolean isEmpty() {
        return true;
    }

    @Override // io.reactivex.internal.a.h
    public Object poll() throws Exception {
        return null;
    }

    @Override // io.reactivex.internal.a.d
    public int requestFusion(int i) {
        return i & 2;
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return this == INSTANCE;
    }

    public static void complete(v<?> vVar) {
        vVar.onSubscribe(INSTANCE);
        vVar.onComplete();
    }

    public static void complete(m<?> mVar) {
        mVar.onSubscribe(INSTANCE);
        mVar.onComplete();
    }

    public static void error(Throwable th, v<?> vVar) {
        vVar.onSubscribe(INSTANCE);
        vVar.onError(th);
    }

    public static void complete(io.reactivex.c cVar) {
        cVar.onSubscribe(INSTANCE);
        cVar.onComplete();
    }

    public static void error(Throwable th, io.reactivex.c cVar) {
        cVar.onSubscribe(INSTANCE);
        cVar.onError(th);
    }

    public static void error(Throwable th, z<?> zVar) {
        zVar.onSubscribe(INSTANCE);
        zVar.onError(th);
    }

    public static void error(Throwable th, m<?> mVar) {
        mVar.onSubscribe(INSTANCE);
        mVar.onError(th);
    }

    @Override // io.reactivex.internal.a.h
    public boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public boolean offer(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
