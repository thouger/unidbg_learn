package io.reactivex.internal.operators.flowable;

import io.reactivex.c.h;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.subscribers.SinglePostCompleteSubscriber;
import io.reactivex.j;
import java.util.concurrent.Callable;
import org.a.c;

public final class FlowableMapNotification<T, R> extends a<T, R> {
    final h<? super T, ? extends R> c;
    final h<? super Throwable, ? extends R> d;
    final Callable<? extends R> e;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super R> cVar) {
        this.b.a((j) new MapNotificationSubscriber(cVar, this.c, this.d, this.e));
    }

    static final class MapNotificationSubscriber<T, R> extends SinglePostCompleteSubscriber<T, R> {
        private static final long serialVersionUID = 2757120512858778108L;
        final Callable<? extends R> onCompleteSupplier;
        final h<? super Throwable, ? extends R> onErrorMapper;
        final h<? super T, ? extends R> onNextMapper;

        MapNotificationSubscriber(c<? super R> cVar, h<? super T, ? extends R> hVar, h<? super Throwable, ? extends R> hVar2, Callable<? extends R> callable) {
            super(cVar);
            this.onNextMapper = hVar;
            this.onErrorMapper = hVar2;
            this.onCompleteSupplier = callable;
        }

        public void onNext(T t) {
            try {
                Object a = a.a(this.onNextMapper.apply(t), "The onNext publisher returned is null");
                this.produced++;
                this.downstream.onNext(a);
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.downstream.onError(th);
            }
        }

        public void onError(Throwable th) {
            try {
                complete(a.a(this.onErrorMapper.apply(th), "The onError publisher returned is null"));
            } catch (Throwable th2) {
                io.reactivex.exceptions.a.b(th2);
                this.downstream.onError(new CompositeException(th, th2));
            }
        }

        public void onComplete() {
            try {
                complete(a.a(this.onCompleteSupplier.call(), "The onComplete publisher returned is null"));
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.downstream.onError(th);
            }
        }
    }
}
