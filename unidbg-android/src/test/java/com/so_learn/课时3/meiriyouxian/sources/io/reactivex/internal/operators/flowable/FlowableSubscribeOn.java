package io.reactivex.internal.operators.flowable;

import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import io.reactivex.w;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.c;
import org.a.d;

public final class FlowableSubscribeOn<T> extends a<T, T> {
    final w c;
    final boolean d;

    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        w.c a = this.c.a();
        SubscribeOnSubscriber subscribeOnSubscriber = new SubscribeOnSubscriber(cVar, a, this.b, this.d);
        cVar.onSubscribe(subscribeOnSubscriber);
        a.a(subscribeOnSubscriber);
    }

    static final class SubscribeOnSubscriber<T> extends AtomicReference<Thread> implements j<T>, Runnable, d {
        private static final long serialVersionUID = 8094547886072529208L;
        final c<? super T> downstream;
        final boolean nonScheduledRequests;
        final AtomicLong requested = new AtomicLong();
        b<T> source;
        final AtomicReference<d> upstream = new AtomicReference<>();
        final w.c worker;

        SubscribeOnSubscriber(c<? super T> cVar, w.c cVar2, b<T> bVar, boolean z) {
            this.downstream = cVar;
            this.worker = cVar2;
            this.source = bVar;
            this.nonScheduledRequests = !z;
        }

        @Override // java.lang.Runnable
        public void run() {
            lazySet(Thread.currentThread());
            b<T> bVar = this.source;
            this.source = null;
            bVar.subscribe(this);
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.setOnce(this.upstream, dVar)) {
                long andSet = this.requested.getAndSet(0);
                if (andSet != 0) {
                    requestUpstream(andSet, dVar);
                }
            }
        }

        public void onNext(T t) {
            this.downstream.onNext(t);
        }

        public void onError(Throwable th) {
            this.downstream.onError(th);
            this.worker.dispose();
        }

        public void onComplete() {
            this.downstream.onComplete();
            this.worker.dispose();
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                d dVar = this.upstream.get();
                if (dVar != null) {
                    requestUpstream(j, dVar);
                    return;
                }
                io.reactivex.internal.util.b.a(this.requested, j);
                d dVar2 = this.upstream.get();
                if (dVar2 != null) {
                    long andSet = this.requested.getAndSet(0);
                    if (andSet != 0) {
                        requestUpstream(andSet, dVar2);
                    }
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void requestUpstream(long j, d dVar) {
            if (this.nonScheduledRequests || Thread.currentThread() == get()) {
                dVar.request(j);
            } else {
                this.worker.a(new a(dVar, j));
            }
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.upstream);
            this.worker.dispose();
        }

        /* access modifiers changed from: package-private */
        public static final class a implements Runnable {
            final d a;
            final long b;

            a(d dVar, long j) {
                this.a = dVar;
                this.b = j;
            }

            @Override // java.lang.Runnable
            public void run() {
                this.a.request(this.b);
            }
        }
    }
}
