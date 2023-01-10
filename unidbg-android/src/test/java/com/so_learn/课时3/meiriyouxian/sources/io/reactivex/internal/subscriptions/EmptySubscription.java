package io.reactivex.internal.subscriptions;

import io.reactivex.internal.a.e;
import org.a.c;

public enum EmptySubscription implements e<Object> {
    INSTANCE;

    public void cancel() {
    }

    @Override // io.reactivex.internal.a.h
    public void clear() {
    }

    @Override // io.reactivex.internal.a.h
    public boolean isEmpty() {
        return true;
    }

    @Override // io.reactivex.internal.a.h
    public Object poll() {
        return null;
    }

    @Override // io.reactivex.internal.a.d
    public int requestFusion(int i) {
        return i & 2;
    }

    @Override // java.lang.Enum, java.lang.Object
    public String toString() {
        return "EmptySubscription";
    }

    public void request(long j) {
        SubscriptionHelper.validate(j);
    }

    public static void error(Throwable th, c<?> cVar) {
        cVar.onSubscribe(INSTANCE);
        cVar.onError(th);
    }

    public static void complete(c<?> cVar) {
        cVar.onSubscribe(INSTANCE);
        cVar.onComplete();
    }

    @Override // io.reactivex.internal.a.h
    public boolean offer(Object obj) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public boolean offer(Object obj, Object obj2) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
