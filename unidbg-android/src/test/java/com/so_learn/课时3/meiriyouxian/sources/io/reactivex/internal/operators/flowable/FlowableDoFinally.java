package io.reactivex.internal.operators.flowable;

import io.reactivex.c.a;
import io.reactivex.internal.a.e;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import org.a.c;
import org.a.d;

public final class FlowableDoFinally<T> extends a<T, T> {
    final a c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        if (cVar instanceof io.reactivex.internal.a.a) {
            this.b.a((j) new DoFinallyConditionalSubscriber((io.reactivex.internal.a.a) cVar, this.c));
        } else {
            this.b.a((j) new DoFinallySubscriber(cVar, this.c));
        }
    }

    static final class DoFinallySubscriber<T> extends BasicIntQueueSubscription<T> implements j<T> {
        private static final long serialVersionUID = 4109457741734051389L;
        final c<? super T> downstream;
        final a onFinally;
        e<T> qs;
        boolean syncFused;
        d upstream;

        DoFinallySubscriber(c<? super T> cVar, a aVar) {
            this.downstream = cVar;
            this.onFinally = aVar;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                if (dVar instanceof e) {
                    this.qs = (e) dVar;
                }
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
            runFinally();
        }

        public void onComplete() {
            this.downstream.onComplete();
            runFinally();
        }

        public void cancel() {
            this.upstream.cancel();
            runFinally();
        }

        public void request(long j) {
            this.upstream.request(j);
        }

        @Override // io.reactivex.internal.a.d
        public int requestFusion(int i) {
            e<T> eVar = this.qs;
            if (eVar == null || (i & 4) != 0) {
                return 0;
            }
            int requestFusion = eVar.requestFusion(i);
            if (requestFusion != 0) {
                boolean z = true;
                if (requestFusion != 1) {
                    z = false;
                }
                this.syncFused = z;
            }
            return requestFusion;
        }

        @Override // io.reactivex.internal.a.h
        public void clear() {
            this.qs.clear();
        }

        @Override // io.reactivex.internal.a.h
        public boolean isEmpty() {
            return this.qs.isEmpty();
        }

        @Override // io.reactivex.internal.a.h
        public T poll() throws Exception {
            T poll = this.qs.poll();
            if (poll == null && this.syncFused) {
                runFinally();
            }
            return poll;
        }

        /* access modifiers changed from: package-private */
        public void runFinally() {
            if (compareAndSet(0, 1)) {
                try {
                    this.onFinally.a();
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    io.reactivex.e.a.a(th);
                }
            }
        }
    }

    static final class DoFinallyConditionalSubscriber<T> extends BasicIntQueueSubscription<T> implements io.reactivex.internal.a.a<T> {
        private static final long serialVersionUID = 4109457741734051389L;
        final io.reactivex.internal.a.a<? super T> downstream;
        final a onFinally;
        e<T> qs;
        boolean syncFused;
        d upstream;

        DoFinallyConditionalSubscriber(io.reactivex.internal.a.a<? super T> aVar, a aVar2) {
            this.downstream = aVar;
            this.onFinally = aVar2;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.upstream, dVar)) {
                this.upstream = dVar;
                if (dVar instanceof e) {
                    this.qs = (e) dVar;
                }
                this.downstream.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        @Override // io.reactivex.internal.a.a
        public boolean tryOnNext(T t) {
            return this.downstream.tryOnNext(t);
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
            runFinally();
        }

        public void onComplete() {
            this.downstream.onComplete();
            runFinally();
        }

        public void cancel() {
            this.upstream.cancel();
            runFinally();
        }

        public void request(long j) {
            this.upstream.request(j);
        }

        @Override // io.reactivex.internal.a.d
        public int requestFusion(int i) {
            e<T> eVar = this.qs;
            if (eVar == null || (i & 4) != 0) {
                return 0;
            }
            int requestFusion = eVar.requestFusion(i);
            if (requestFusion != 0) {
                boolean z = true;
                if (requestFusion != 1) {
                    z = false;
                }
                this.syncFused = z;
            }
            return requestFusion;
        }

        @Override // io.reactivex.internal.a.h
        public void clear() {
            this.qs.clear();
        }

        @Override // io.reactivex.internal.a.h
        public boolean isEmpty() {
            return this.qs.isEmpty();
        }

        @Override // io.reactivex.internal.a.h
        public T poll() throws Exception {
            T poll = this.qs.poll();
            if (poll == null && this.syncFused) {
                runFinally();
            }
            return poll;
        }

        /* access modifiers changed from: package-private */
        public void runFinally() {
            if (compareAndSet(0, 1)) {
                try {
                    this.onFinally.a();
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    io.reactivex.e.a.a(th);
                }
            }
        }
    }
}
