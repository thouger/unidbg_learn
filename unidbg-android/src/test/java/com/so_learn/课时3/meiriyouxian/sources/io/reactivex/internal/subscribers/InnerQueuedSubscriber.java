package io.reactivex.internal.subscribers;

import io.reactivex.internal.a.e;
import io.reactivex.internal.a.h;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicReference;
import org.a.d;

public final class InnerQueuedSubscriber<T> extends AtomicReference<d> implements j<T>, d {
    private static final long serialVersionUID = 22876611072430776L;
    volatile boolean done;
    int fusionMode;
    final int limit;
    final c<T> parent;
    final int prefetch;
    long produced;
    volatile h<T> queue;

    public InnerQueuedSubscriber(c<T> cVar, int i) {
        this.parent = cVar;
        this.prefetch = i;
        this.limit = i - (i >> 2);
    }

    @Override // io.reactivex.j
    public void onSubscribe(d dVar) {
        if (SubscriptionHelper.setOnce(this, dVar)) {
            if (dVar instanceof e) {
                e eVar = (e) dVar;
                int requestFusion = eVar.requestFusion(3);
                if (requestFusion == 1) {
                    this.fusionMode = requestFusion;
                    this.queue = eVar;
                    this.done = true;
                    this.parent.innerComplete(this);
                    return;
                } else if (requestFusion == 2) {
                    this.fusionMode = requestFusion;
                    this.queue = eVar;
                    io.reactivex.internal.util.h.a(dVar, this.prefetch);
                    return;
                }
            }
            this.queue = io.reactivex.internal.util.h.a(this.prefetch);
            io.reactivex.internal.util.h.a(dVar, this.prefetch);
        }
    }

    public void onNext(T t) {
        if (this.fusionMode == 0) {
            this.parent.innerNext(this, t);
        } else {
            this.parent.drain();
        }
    }

    public void onError(Throwable th) {
        this.parent.innerError(this, th);
    }

    public void onComplete() {
        this.parent.innerComplete(this);
    }

    public void request(long j) {
        if (this.fusionMode != 1) {
            long j2 = this.produced + j;
            if (j2 >= ((long) this.limit)) {
                this.produced = 0;
                get().request(j2);
                return;
            }
            this.produced = j2;
        }
    }

    public void requestOne() {
        if (this.fusionMode != 1) {
            long j = this.produced + 1;
            if (j == ((long) this.limit)) {
                this.produced = 0;
                get().request(j);
                return;
            }
            this.produced = j;
        }
    }

    public void cancel() {
        SubscriptionHelper.cancel(this);
    }

    public boolean isDone() {
        return this.done;
    }

    public void setDone() {
        this.done = true;
    }

    public h<T> queue() {
        return this.queue;
    }
}
