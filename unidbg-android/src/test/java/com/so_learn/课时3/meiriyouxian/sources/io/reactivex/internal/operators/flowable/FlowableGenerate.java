package io.reactivex.internal.operators.flowable;

import io.reactivex.c.c;
import io.reactivex.exceptions.a;
import io.reactivex.f;
import io.reactivex.g;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;
import org.a.d;

public final class FlowableGenerate<T, S> extends g<T> {
    final Callable<S> b;
    final c<S, f<T>, S> c;
    final io.reactivex.c.g<? super S> d;

    @Override // io.reactivex.g
    public void a(org.a.c<? super T> cVar) {
        try {
            cVar.onSubscribe(new GeneratorSubscription(cVar, this.c, this.d, this.b.call()));
        } catch (Throwable th) {
            a.b(th);
            EmptySubscription.error(th, cVar);
        }
    }

    static final class GeneratorSubscription<T, S> extends AtomicLong implements f<T>, d {
        private static final long serialVersionUID = 7565982551505011832L;
        volatile boolean cancelled;
        final io.reactivex.c.g<? super S> disposeState;
        final org.a.c<? super T> downstream;
        final c<S, ? super f<T>, S> generator;
        boolean hasNext;
        S state;
        boolean terminate;

        GeneratorSubscription(org.a.c<? super T> cVar, c<S, ? super f<T>, S> cVar2, io.reactivex.c.g<? super S> gVar, S s) {
            this.downstream = cVar;
            this.generator = cVar2;
            this.disposeState = gVar;
            this.state = s;
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j) && b.a(this, j) == 0) {
                S s = this.state;
                c<S, ? super f<T>, S> cVar = this.generator;
                long j2 = j;
                do {
                    long j3 = 0;
                    while (true) {
                        if (j3 == j2) {
                            j2 = get();
                            if (j3 == j2) {
                                this.state = s;
                                j2 = addAndGet(-j3);
                            }
                        } else if (this.cancelled) {
                            this.state = null;
                            dispose(s);
                            return;
                        } else {
                            this.hasNext = false;
                            try {
                                s = (S) cVar.apply(s, this);
                                if (this.terminate) {
                                    this.cancelled = true;
                                    this.state = null;
                                    dispose(s);
                                    return;
                                }
                                j3++;
                            } catch (Throwable th) {
                                a.b(th);
                                this.cancelled = true;
                                this.state = null;
                                onError(th);
                                dispose(s);
                                return;
                            }
                        }
                    }
                } while (j2 != 0);
            }
        }

        private void dispose(S s) {
            try {
                this.disposeState.accept(s);
            } catch (Throwable th) {
                a.b(th);
                io.reactivex.e.a.a(th);
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                if (b.a(this, 1) == 0) {
                    S s = this.state;
                    this.state = null;
                    dispose(s);
                }
            }
        }

        @Override // io.reactivex.f
        public void onNext(T t) {
            if (this.terminate) {
                return;
            }
            if (this.hasNext) {
                onError(new IllegalStateException("onNext already called in this generate turn"));
            } else if (t == null) {
                onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            } else {
                this.hasNext = true;
                this.downstream.onNext(t);
            }
        }

        @Override // io.reactivex.f
        public void onError(Throwable th) {
            if (this.terminate) {
                io.reactivex.e.a.a(th);
                return;
            }
            if (th == null) {
                th = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
            }
            this.terminate = true;
            this.downstream.onError(th);
        }

        @Override // io.reactivex.f
        public void onComplete() {
            if (!this.terminate) {
                this.terminate = true;
                this.downstream.onComplete();
            }
        }
    }
}
