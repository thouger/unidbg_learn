package io.reactivex.internal.operators.single;

import io.reactivex.ab;
import io.reactivex.c.h;
import io.reactivex.g;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class SingleFlatMapPublisher<T, R> extends g<R> {
    final ab<T> b;
    final h<? super T, ? extends b<? extends R>> c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super R> cVar) {
        this.b.a(new SingleFlatMapPublisherObserver(cVar, this.c));
    }

    static final class SingleFlatMapPublisherObserver<S, T> extends AtomicLong implements j<T>, z<S>, d {
        private static final long serialVersionUID = 7759721921468635667L;
        io.reactivex.disposables.b disposable;
        final c<? super T> downstream;
        final h<? super S, ? extends b<? extends T>> mapper;
        final AtomicReference<d> parent = new AtomicReference<>();

        SingleFlatMapPublisherObserver(c<? super T> cVar, h<? super S, ? extends b<? extends T>> hVar) {
            this.downstream = cVar;
            this.mapper = hVar;
        }

        @Override // io.reactivex.z
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            this.disposable = bVar;
            this.downstream.onSubscribe(this);
        }

        @Override // io.reactivex.z
        public void onSuccess(S s) {
            try {
                ((b) a.a(this.mapper.apply(s), "the mapper returned a null Publisher")).subscribe(this);
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.downstream.onError(th);
            }
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            SubscriptionHelper.deferredSetOnce(this.parent, this, dVar);
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        public void onComplete() {
            this.downstream.onComplete();
        }

        @Override // io.reactivex.z
        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        public void request(long j) {
            SubscriptionHelper.deferredRequest(this.parent, this, j);
        }

        public void cancel() {
            this.disposable.dispose();
            SubscriptionHelper.cancel(this.parent);
        }
    }
}
