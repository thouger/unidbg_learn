package io.reactivex.internal.operators.maybe;

import io.reactivex.e.a;
import io.reactivex.g;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.internal.util.b;
import io.reactivex.m;
import io.reactivex.o;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.c;
import org.a.d;

public final class MaybeConcatArrayDelayError<T> extends g<T> {
    final o<? extends T>[] b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        ConcatMaybeObserver concatMaybeObserver = new ConcatMaybeObserver(cVar, this.b);
        cVar.onSubscribe(concatMaybeObserver);
        concatMaybeObserver.drain();
    }

    static final class ConcatMaybeObserver<T> extends AtomicInteger implements m<T>, d {
        private static final long serialVersionUID = 3520831347801429610L;
        final AtomicReference<Object> current = new AtomicReference<>(NotificationLite.COMPLETE);
        final SequentialDisposable disposables = new SequentialDisposable();
        final c<? super T> downstream;
        final AtomicThrowable errors = new AtomicThrowable();
        int index;
        long produced;
        final AtomicLong requested = new AtomicLong();
        final o<? extends T>[] sources;

        ConcatMaybeObserver(c<? super T> cVar, o<? extends T>[] oVarArr) {
            this.downstream = cVar;
            this.sources = oVarArr;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                b.a(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            this.disposables.dispose();
        }

        @Override // io.reactivex.m
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            this.disposables.replace(bVar);
        }

        @Override // io.reactivex.m
        public void onSuccess(T t) {
            this.current.lazySet(t);
            drain();
        }

        @Override // io.reactivex.m
        public void onError(Throwable th) {
            this.current.lazySet(NotificationLite.COMPLETE);
            if (this.errors.addThrowable(th)) {
                drain();
            } else {
                a.a(th);
            }
        }

        @Override // io.reactivex.m
        public void onComplete() {
            this.current.lazySet(NotificationLite.COMPLETE);
            drain();
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                AtomicReference<Object> atomicReference = this.current;
                c<? super T> cVar = this.downstream;
                SequentialDisposable sequentialDisposable = this.disposables;
                while (!sequentialDisposable.isDisposed()) {
                    Object obj = atomicReference.get();
                    if (obj != null) {
                        boolean z = true;
                        if (obj != NotificationLite.COMPLETE) {
                            long j = this.produced;
                            if (j != this.requested.get()) {
                                this.produced = j + 1;
                                atomicReference.lazySet(null);
                                cVar.onNext(obj);
                            } else {
                                z = false;
                            }
                        } else {
                            atomicReference.lazySet(null);
                        }
                        if (z && !sequentialDisposable.isDisposed()) {
                            int i = this.index;
                            o<? extends T>[] oVarArr = this.sources;
                            if (i != oVarArr.length) {
                                this.index = i + 1;
                                oVarArr[i].a(this);
                            } else if (this.errors.get() != null) {
                                cVar.onError(this.errors.terminate());
                                return;
                            } else {
                                cVar.onComplete();
                                return;
                            }
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
                atomicReference.lazySet(null);
            }
        }
    }
}
