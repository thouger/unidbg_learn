package io.reactivex.internal.operators.flowable;

import io.reactivex.g;
import io.reactivex.internal.a.a;
import io.reactivex.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import org.a.c;

public final class FlowableFromArray<T> extends g<T> {
    final T[] b;

    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        if (cVar instanceof a) {
            cVar.onSubscribe(new ArrayConditionalSubscription((a) cVar, this.b));
        } else {
            cVar.onSubscribe(new ArraySubscription(cVar, this.b));
        }
    }

    static abstract class BaseArraySubscription<T> extends BasicQueueSubscription<T> {
        private static final long serialVersionUID = -2252972430506210021L;
        final T[] array;
        volatile boolean cancelled;
        int index;

        /* access modifiers changed from: package-private */
        public abstract void fastPath();

        @Override // io.reactivex.internal.a.d
        public final int requestFusion(int i) {
            return i & 1;
        }

        /* access modifiers changed from: package-private */
        public abstract void slowPath(long j);

        BaseArraySubscription(T[] tArr) {
            this.array = tArr;
        }

        @Override // io.reactivex.internal.a.h
        public final T poll() {
            int i = this.index;
            T[] tArr = this.array;
            if (i == tArr.length) {
                return null;
            }
            this.index = i + 1;
            return (T) io.reactivex.internal.functions.a.a((Object) tArr[i], "array element is null");
        }

        @Override // io.reactivex.internal.a.h
        public final boolean isEmpty() {
            return this.index == this.array.length;
        }

        @Override // io.reactivex.internal.a.h
        public final void clear() {
            this.index = this.array.length;
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

    static final class ArraySubscription<T> extends BaseArraySubscription<T> {
        private static final long serialVersionUID = 2587302975077663557L;
        final c<? super T> downstream;

        ArraySubscription(c<? super T> cVar, T[] tArr) {
            super(tArr);
            this.downstream = cVar;
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableFromArray.BaseArraySubscription
        public void fastPath() {
            Object[] objArr = this.array;
            int length = objArr.length;
            c<? super T> cVar = this.downstream;
            for (int i = this.index; i != length; i++) {
                if (!this.cancelled) {
                    Object obj = objArr[i];
                    if (obj == null) {
                        cVar.onError(new NullPointerException("The element at index " + i + " is null"));
                        return;
                    }
                    cVar.onNext(obj);
                } else {
                    return;
                }
            }
            if (!this.cancelled) {
                cVar.onComplete();
            }
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableFromArray.BaseArraySubscription
        public void slowPath(long j) {
            Object[] objArr = this.array;
            int length = objArr.length;
            int i = this.index;
            c<? super T> cVar = this.downstream;
            long j2 = j;
            do {
                long j3 = 0;
                while (true) {
                    if (j3 == j2 || i == length) {
                        if (i != length) {
                            j2 = get();
                            if (j3 == j2) {
                                this.index = i;
                                j2 = addAndGet(-j3);
                            }
                        } else if (!this.cancelled) {
                            cVar.onComplete();
                            return;
                        } else {
                            return;
                        }
                    } else if (!this.cancelled) {
                        Object obj = objArr[i];
                        if (obj == null) {
                            cVar.onError(new NullPointerException("The element at index " + i + " is null"));
                            return;
                        }
                        cVar.onNext(obj);
                        j3++;
                        i++;
                    } else {
                        return;
                    }
                }
            } while (j2 != 0);
        }
    }

    static final class ArrayConditionalSubscription<T> extends BaseArraySubscription<T> {
        private static final long serialVersionUID = 2587302975077663557L;
        final a<? super T> downstream;

        ArrayConditionalSubscription(a<? super T> aVar, T[] tArr) {
            super(tArr);
            this.downstream = aVar;
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableFromArray.BaseArraySubscription
        public void fastPath() {
            Object[] objArr = this.array;
            int length = objArr.length;
            a<? super T> aVar = this.downstream;
            for (int i = this.index; i != length; i++) {
                if (!this.cancelled) {
                    Object obj = objArr[i];
                    if (obj == null) {
                        aVar.onError(new NullPointerException("The element at index " + i + " is null"));
                        return;
                    }
                    aVar.tryOnNext(obj);
                } else {
                    return;
                }
            }
            if (!this.cancelled) {
                aVar.onComplete();
            }
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableFromArray.BaseArraySubscription
        public void slowPath(long j) {
            Object[] objArr = this.array;
            int length = objArr.length;
            int i = this.index;
            a<? super T> aVar = this.downstream;
            long j2 = j;
            do {
                long j3 = 0;
                while (true) {
                    if (j3 == j2 || i == length) {
                        if (i != length) {
                            j2 = get();
                            if (j3 == j2) {
                                this.index = i;
                                j2 = addAndGet(-j3);
                            }
                        } else if (!this.cancelled) {
                            aVar.onComplete();
                            return;
                        } else {
                            return;
                        }
                    } else if (!this.cancelled) {
                        Object obj = objArr[i];
                        if (obj == null) {
                            aVar.onError(new NullPointerException("The element at index " + i + " is null"));
                            return;
                        }
                        if (aVar.tryOnNext(obj)) {
                            j3++;
                        }
                        i++;
                    } else {
                        return;
                    }
                }
            } while (j2 != 0);
        }
    }
}
