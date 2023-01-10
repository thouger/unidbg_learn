package io.reactivex.internal.operators.flowable;

import io.reactivex.c.g;
import io.reactivex.exceptions.a;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicLong;
import org.a.c;
import org.a.d;

public final class FlowableOnBackpressureDrop<T> extends a<T, T> implements g<T> {
    final g<? super T> c = this;

    @Override // io.reactivex.c.g
    public void accept(T t) {
    }

    public FlowableOnBackpressureDrop(io.reactivex.g<T> gVar) {
        super(gVar);
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        this.b.a((j) new BackpressureDropSubscriber(cVar, this.c));
    }

    static final class BackpressureDropSubscriber<T> extends AtomicLong implements j<T>, d {
        private static final long serialVersionUID = -6246093802440953054L;
        boolean done;
        final c<? super T> downstream;
        final g<? super T> onDrop;
        d upstream;

        BackpressureDropSubscriber(c<? super T> cVar, g<? super T> gVar) {
            this.downstream = cVar;
            this.onDrop = gVar;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
                dVar.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                if (get() != 0) {
                    this.downstream.onNext(t);
                    b.c(this, 1);
                    return;
                }
                try {
                    this.onDrop.accept(t);
                } catch (Throwable th) {
                    a.b(th);
                    cancel();
                    onError(th);
                }
            }
        }

        public void onError(Throwable th) {
            if (this.done) {
                io.reactivex.e.a.a(th);
                return;
            }
            this.done = true;
            this.downstream.onError(th);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.downstream.onComplete();
            }
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this, j);
            }
        }

        public void cancel() {
            this.upstream.cancel();
        }
    }
}
