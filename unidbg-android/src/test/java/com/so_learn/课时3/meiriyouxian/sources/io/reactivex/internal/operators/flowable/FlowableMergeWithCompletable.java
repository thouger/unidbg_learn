package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.b;
import io.reactivex.e;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class FlowableMergeWithCompletable<T> extends a<T, T> {
    final e c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        MergeWithSubscriber mergeWithSubscriber = new MergeWithSubscriber(cVar);
        cVar.onSubscribe(mergeWithSubscriber);
        this.b.a((j) mergeWithSubscriber);
        this.c.a(mergeWithSubscriber.otherObserver);
    }

    static final class MergeWithSubscriber<T> extends AtomicInteger implements j<T>, d {
        private static final long serialVersionUID = -4592979584110982903L;
        final c<? super T> downstream;
        final AtomicThrowable error = new AtomicThrowable();
        volatile boolean mainDone;
        final AtomicReference<d> mainSubscription = new AtomicReference<>();
        volatile boolean otherDone;
        final OtherObserver otherObserver = new OtherObserver(this);
        final AtomicLong requested = new AtomicLong();

        MergeWithSubscriber(c<? super T> cVar) {
            this.downstream = cVar;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            SubscriptionHelper.deferredSetOnce(this.mainSubscription, this.requested, dVar);
        }

        public void onNext(T t) {
            io.reactivex.internal.util.e.a((c) this.downstream, (Object) t, (AtomicInteger) this, this.error);
        }

        public void onError(Throwable th) {
            DisposableHelper.dispose(this.otherObserver);
            io.reactivex.internal.util.e.a((c<?>) this.downstream, th, (AtomicInteger) this, this.error);
        }

        public void onComplete() {
            this.mainDone = true;
            if (this.otherDone) {
                io.reactivex.internal.util.e.a(this.downstream, this, this.error);
            }
        }

        public void request(long j) {
            SubscriptionHelper.deferredRequest(this.mainSubscription, this.requested, j);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.mainSubscription);
            DisposableHelper.dispose(this.otherObserver);
        }

        /* access modifiers changed from: package-private */
        public void otherError(Throwable th) {
            SubscriptionHelper.cancel(this.mainSubscription);
            io.reactivex.internal.util.e.a((c<?>) this.downstream, th, (AtomicInteger) this, this.error);
        }

        /* access modifiers changed from: package-private */
        public void otherComplete() {
            this.otherDone = true;
            if (this.mainDone) {
                io.reactivex.internal.util.e.a(this.downstream, this, this.error);
            }
        }

        static final class OtherObserver extends AtomicReference<b> implements io.reactivex.c {
            private static final long serialVersionUID = -2935427570954647017L;
            final MergeWithSubscriber<?> parent;

            OtherObserver(MergeWithSubscriber<?> mergeWithSubscriber) {
                this.parent = mergeWithSubscriber;
            }

            @Override // io.reactivex.c
            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            @Override // io.reactivex.c
            public void onError(Throwable th) {
                this.parent.otherError(th);
            }

            @Override // io.reactivex.c
            public void onComplete() {
                this.parent.otherComplete();
            }
        }
    }
}
