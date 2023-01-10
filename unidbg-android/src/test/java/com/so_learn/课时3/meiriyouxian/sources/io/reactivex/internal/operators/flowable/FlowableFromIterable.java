package io.reactivex.internal.operators.flowable;

import io.reactivex.exceptions.a;
import io.reactivex.g;
import io.reactivex.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import java.util.Iterator;
import org.a.c;

public final class FlowableFromIterable<T> extends g<T> {
    final Iterable<? extends T> b;

    public FlowableFromIterable(Iterable<? extends T> iterable) {
        this.b = iterable;
    }

    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        try {
            a(cVar, this.b.iterator());
        } catch (Throwable th) {
            a.b(th);
            EmptySubscription.error(th, cVar);
        }
    }

    public static <T> void a(c<? super T> cVar, Iterator<? extends T> it2) {
        try {
            if (!it2.hasNext()) {
                EmptySubscription.complete(cVar);
            } else if (cVar instanceof io.reactivex.internal.a.a) {
                cVar.onSubscribe(new IteratorConditionalSubscription((io.reactivex.internal.a.a) cVar, it2));
            } else {
                cVar.onSubscribe(new IteratorSubscription(cVar, it2));
            }
        } catch (Throwable th) {
            a.b(th);
            EmptySubscription.error(th, cVar);
        }
    }

    static abstract class BaseRangeSubscription<T> extends BasicQueueSubscription<T> {
        private static final long serialVersionUID = -2252972430506210021L;
        volatile boolean cancelled;

        /* renamed from: it  reason: collision with root package name */
        Iterator<? extends T> f1341it;
        boolean once;

        /* access modifiers changed from: package-private */
        public abstract void fastPath();

        @Override // io.reactivex.internal.a.d
        public final int requestFusion(int i) {
            return i & 1;
        }

        /* access modifiers changed from: package-private */
        public abstract void slowPath(long j);

        BaseRangeSubscription(Iterator<? extends T> it2) {
            this.f1341it = it2;
        }

        @Override // io.reactivex.internal.a.h
        public final T poll() {
            Iterator<? extends T> it2 = this.f1341it;
            if (it2 == null) {
                return null;
            }
            if (!this.once) {
                this.once = true;
            } else if (!it2.hasNext()) {
                return null;
            }
            return (T) io.reactivex.internal.functions.a.a(this.f1341it.next(), "Iterator.next() returned a null value");
        }

        @Override // io.reactivex.internal.a.h
        public final boolean isEmpty() {
            Iterator<? extends T> it2 = this.f1341it;
            return it2 == null || !it2.hasNext();
        }

        @Override // io.reactivex.internal.a.h
        public final void clear() {
            this.f1341it = null;
        }

        public final void request(long j) {
            if (SubscriptionHelper.validate(j) && b.a(this, j) == 0) {
                if (j == Long.MAX_VALUE) {
                    fastPath();
                } else {
                    slowPath(j);
                }
            }
        }

        public final void cancel() {
            this.cancelled = true;
        }
    }

    /* access modifiers changed from: package-private */
    public static final class IteratorSubscription<T> extends BaseRangeSubscription<T> {
        private static final long serialVersionUID = -6022804456014692607L;
        final c<? super T> downstream;

        IteratorSubscription(c<? super T> cVar, Iterator<? extends T> it2) {
            super(it2);
            this.downstream = cVar;
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableFromIterable.BaseRangeSubscription
        public void fastPath() {
            Iterator it2 = this.f1341it;
            c<? super T> cVar = this.downstream;
            while (!this.cancelled) {
                try {
                    Object next = it2.next();
                    if (!this.cancelled) {
                        if (next == null) {
                            cVar.onError(new NullPointerException("Iterator.next() returned a null value"));
                            return;
                        }
                        cVar.onNext(next);
                        if (!this.cancelled) {
                            try {
                                if (!it2.hasNext()) {
                                    if (!this.cancelled) {
                                        cVar.onComplete();
                                        return;
                                    }
                                    return;
                                }
                            } catch (Throwable th) {
                                a.b(th);
                                cVar.onError(th);
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable th2) {
                    a.b(th2);
                    cVar.onError(th2);
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableFromIterable.BaseRangeSubscription
        public void slowPath(long j) {
            Iterator it2 = this.f1341it;
            c<? super T> cVar = this.downstream;
            long j2 = j;
            do {
                long j3 = 0;
                while (true) {
                    if (j3 == j2) {
                        j2 = get();
                        if (j3 == j2) {
                            j2 = addAndGet(-j3);
                        }
                    } else if (!this.cancelled) {
                        try {
                            Object next = it2.next();
                            if (!this.cancelled) {
                                if (next == null) {
                                    cVar.onError(new NullPointerException("Iterator.next() returned a null value"));
                                    return;
                                }
                                cVar.onNext(next);
                                if (!this.cancelled) {
                                    try {
                                        if (it2.hasNext()) {
                                            j3++;
                                        } else if (!this.cancelled) {
                                            cVar.onComplete();
                                            return;
                                        } else {
                                            return;
                                        }
                                    } catch (Throwable th) {
                                        a.b(th);
                                        cVar.onError(th);
                                        return;
                                    }
                                } else {
                                    return;
                                }
                            } else {
                                return;
                            }
                        } catch (Throwable th2) {
                            a.b(th2);
                            cVar.onError(th2);
                            return;
                        }
                    } else {
                        return;
                    }
                }
            } while (j2 != 0);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class IteratorConditionalSubscription<T> extends BaseRangeSubscription<T> {
        private static final long serialVersionUID = -6022804456014692607L;
        final io.reactivex.internal.a.a<? super T> downstream;

        IteratorConditionalSubscription(io.reactivex.internal.a.a<? super T> aVar, Iterator<? extends T> it2) {
            super(it2);
            this.downstream = aVar;
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableFromIterable.BaseRangeSubscription
        public void fastPath() {
            Iterator it2 = this.f1341it;
            io.reactivex.internal.a.a<? super T> aVar = this.downstream;
            while (!this.cancelled) {
                try {
                    Object next = it2.next();
                    if (!this.cancelled) {
                        if (next == null) {
                            aVar.onError(new NullPointerException("Iterator.next() returned a null value"));
                            return;
                        }
                        aVar.tryOnNext(next);
                        if (!this.cancelled) {
                            try {
                                if (!it2.hasNext()) {
                                    if (!this.cancelled) {
                                        aVar.onComplete();
                                        return;
                                    }
                                    return;
                                }
                            } catch (Throwable th) {
                                a.b(th);
                                aVar.onError(th);
                                return;
                            }
                        } else {
                            return;
                        }
                    } else {
                        return;
                    }
                } catch (Throwable th2) {
                    a.b(th2);
                    aVar.onError(th2);
                    return;
                }
            }
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableFromIterable.BaseRangeSubscription
        public void slowPath(long j) {
            Iterator it2 = this.f1341it;
            io.reactivex.internal.a.a<? super T> aVar = this.downstream;
            long j2 = j;
            do {
                long j3 = 0;
                while (true) {
                    if (j3 == j2) {
                        j2 = get();
                        if (j3 == j2) {
                            j2 = addAndGet(-j3);
                        }
                    } else if (!this.cancelled) {
                        try {
                            Object next = it2.next();
                            if (!this.cancelled) {
                                if (next == null) {
                                    aVar.onError(new NullPointerException("Iterator.next() returned a null value"));
                                    return;
                                }
                                boolean tryOnNext = aVar.tryOnNext(next);
                                if (!this.cancelled) {
                                    try {
                                        if (!it2.hasNext()) {
                                            if (!this.cancelled) {
                                                aVar.onComplete();
                                                return;
                                            }
                                            return;
                                        } else if (tryOnNext) {
                                            j3++;
                                        }
                                    } catch (Throwable th) {
                                        a.b(th);
                                        aVar.onError(th);
                                        return;
                                    }
                                } else {
                                    return;
                                }
                            } else {
                                return;
                            }
                        } catch (Throwable th2) {
                            a.b(th2);
                            aVar.onError(th2);
                            return;
                        }
                    } else {
                        return;
                    }
                }
            } while (j2 != 0);
        }
    }
}
