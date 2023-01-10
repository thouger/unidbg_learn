package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.ArrayDeque;
import org.a.c;
import org.a.d;

public final class FlowableSkipLast<T> extends a<T, T> {
    final int c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        this.b.a((j) new SkipLastSubscriber(cVar, this.c));
    }

    static final class SkipLastSubscriber<T> extends ArrayDeque<T> implements j<T>, d {
        private static final long serialVersionUID = -3807491841935125653L;
        final c<? super T> downstream;
        final int skip;
        d upstream;

        SkipLastSubscriber(c<? super T> cVar, int i) {
            super(i);
            this.downstream = cVar;
            this.skip = i;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (this.skip == size()) {
                this.downstream.onNext(poll());
            } else {
                this.upstream.request(1);
            }
            offer(t);
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
        }

        public void onComplete() {
            this.downstream.onComplete();
        }

        public void request(long j) {
            this.upstream.request(j);
        }

        public void cancel() {
            this.upstream.cancel();
        }
    }
}
