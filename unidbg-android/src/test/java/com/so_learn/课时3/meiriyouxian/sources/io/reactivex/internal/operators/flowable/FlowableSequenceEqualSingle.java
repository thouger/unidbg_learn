package io.reactivex.internal.operators.flowable;

import io.reactivex.c.d;
import io.reactivex.exceptions.a;
import io.reactivex.internal.a.h;
import io.reactivex.internal.operators.flowable.FlowableSequenceEqual;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.x;
import io.reactivex.z;
import java.util.concurrent.atomic.AtomicInteger;
import org.a.b;

public final class FlowableSequenceEqualSingle<T> extends x<Boolean> {
    final b<? extends T> a;
    final b<? extends T> b;
    final d<? super T, ? super T> c;
    final int d;

    @Override // io.reactivex.x
    public void b(z<? super Boolean> zVar) {
        EqualCoordinator equalCoordinator = new EqualCoordinator(zVar, this.d, this.c);
        zVar.onSubscribe(equalCoordinator);
        equalCoordinator.subscribe(this.a, this.b);
    }

    static final class EqualCoordinator<T> extends AtomicInteger implements io.reactivex.disposables.b, FlowableSequenceEqual.a {
        private static final long serialVersionUID = -6178010334400373240L;
        final d<? super T, ? super T> comparer;
        final z<? super Boolean> downstream;
        final AtomicThrowable error = new AtomicThrowable();
        final FlowableSequenceEqual.EqualSubscriber<T> first;
        final FlowableSequenceEqual.EqualSubscriber<T> second;
        T v1;
        T v2;

        EqualCoordinator(z<? super Boolean> zVar, int i, d<? super T, ? super T> dVar) {
            this.downstream = zVar;
            this.comparer = dVar;
            this.first = new FlowableSequenceEqual.EqualSubscriber<>(this, i);
            this.second = new FlowableSequenceEqual.EqualSubscriber<>(this, i);
        }

        /* access modifiers changed from: package-private */
        public void subscribe(b<? extends T> bVar, b<? extends T> bVar2) {
            bVar.subscribe(this.first);
            bVar2.subscribe(this.second);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.first.cancel();
            this.second.cancel();
            if (getAndIncrement() == 0) {
                this.first.clear();
                this.second.clear();
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.first.get() == SubscriptionHelper.CANCELLED;
        }

        /* access modifiers changed from: package-private */
        public void cancelAndClear() {
            this.first.cancel();
            this.first.clear();
            this.second.cancel();
            this.second.clear();
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableSequenceEqual.a
        public void drain() {
            if (getAndIncrement() == 0) {
                int i = 1;
                do {
                    h<T> hVar = this.first.queue;
                    h<T> hVar2 = this.second.queue;
                    if (hVar != null && hVar2 != null) {
                        while (!isDisposed()) {
                            if (this.error.get() != null) {
                                cancelAndClear();
                                this.downstream.onError(this.error.terminate());
                                return;
                            }
                            boolean z = this.first.done;
                            T t = this.v1;
                            if (t == null) {
                                try {
                                    t = (T) hVar.poll();
                                    this.v1 = t;
                                } catch (Throwable th) {
                                    a.b(th);
                                    cancelAndClear();
                                    this.error.addThrowable(th);
                                    this.downstream.onError(this.error.terminate());
                                    return;
                                }
                            }
                            boolean z2 = t == null;
                            boolean z3 = this.second.done;
                            T t2 = this.v2;
                            if (t2 == null) {
                                try {
                                    t2 = (T) hVar2.poll();
                                    this.v2 = t2;
                                } catch (Throwable th2) {
                                    a.b(th2);
                                    cancelAndClear();
                                    this.error.addThrowable(th2);
                                    this.downstream.onError(this.error.terminate());
                                    return;
                                }
                            }
                            boolean z4 = t2 == null;
                            if (z && z3 && z2 && z4) {
                                this.downstream.onSuccess(true);
                                return;
                            } else if (z && z3 && z2 != z4) {
                                cancelAndClear();
                                this.downstream.onSuccess(false);
                                return;
                            } else if (!z2 && !z4) {
                                try {
                                    if (!this.comparer.a(t, t2)) {
                                        cancelAndClear();
                                        this.downstream.onSuccess(false);
                                        return;
                                    }
                                    this.v1 = null;
                                    this.v2 = null;
                                    this.first.request();
                                    this.second.request();
                                } catch (Throwable th3) {
                                    a.b(th3);
                                    cancelAndClear();
                                    this.error.addThrowable(th3);
                                    this.downstream.onError(this.error.terminate());
                                    return;
                                }
                            }
                        }
                        this.first.clear();
                        this.second.clear();
                        return;
                    } else if (isDisposed()) {
                        this.first.clear();
                        this.second.clear();
                        return;
                    } else if (this.error.get() != null) {
                        cancelAndClear();
                        this.downstream.onError(this.error.terminate());
                        return;
                    }
                    i = addAndGet(-i);
                } while (i != 0);
            }
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableSequenceEqual.a
        public void innerError(Throwable th) {
            if (this.error.addThrowable(th)) {
                drain();
            } else {
                io.reactivex.e.a.a(th);
            }
        }
    }
}
