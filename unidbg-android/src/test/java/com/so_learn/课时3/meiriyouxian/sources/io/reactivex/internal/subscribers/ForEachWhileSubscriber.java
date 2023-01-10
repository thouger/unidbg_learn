package io.reactivex.internal.subscribers;

import io.reactivex.c.a;
import io.reactivex.c.g;
import io.reactivex.c.k;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicReference;
import org.a.d;

public final class ForEachWhileSubscriber<T> extends AtomicReference<d> implements b, j<T> {
    private static final long serialVersionUID = -4403180040475402120L;
    boolean done;
    final a onComplete;
    final g<? super Throwable> onError;
    final k<? super T> onNext;

    public ForEachWhileSubscriber(k<? super T> kVar, g<? super Throwable> gVar, a aVar) {
        this.onNext = kVar;
        this.onError = gVar;
        this.onComplete = aVar;
    }

    @Override // io.reactivex.j
    public void onSubscribe(d dVar) {
        SubscriptionHelper.setOnce(this, dVar, Long.MAX_VALUE);
    }

    public void onNext(T t) {
        if (!this.done) {
            try {
                if (!this.onNext.test(t)) {
                    dispose();
                    onComplete();
                }
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                dispose();
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
        try {
            this.onError.accept(th);
        } catch (Throwable th2) {
            io.reactivex.exceptions.a.b(th2);
            io.reactivex.e.a.a(new CompositeException(th, th2));
        }
    }

    public void onComplete() {
        if (!this.done) {
            this.done = true;
            try {
                this.onComplete.a();
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                io.reactivex.e.a.a(th);
            }
        }
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        SubscriptionHelper.cancel(this);
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return get() == SubscriptionHelper.CANCELLED;
    }
}
