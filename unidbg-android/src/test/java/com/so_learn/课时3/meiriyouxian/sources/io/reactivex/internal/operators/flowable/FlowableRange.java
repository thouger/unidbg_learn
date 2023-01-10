package io.reactivex.internal.operators.flowable;

import io.reactivex.g;
import io.reactivex.internal.a.a;
import io.reactivex.internal.subscriptions.BasicQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.b;
import org.a.c;

public final class FlowableRange extends g<Integer> {
    final int b;
    final int c;

    @Override // io.reactivex.g
    public void a(c<? super Integer> cVar) {
        if (cVar instanceof a) {
            cVar.onSubscribe(new RangeConditionalSubscription((a) cVar, this.b, this.c));
        } else {
            cVar.onSubscribe(new RangeSubscription(cVar, this.b, this.c));
        }
    }

    static abstract class BaseRangeSubscription extends BasicQueueSubscription<Integer> {
        private static final long serialVersionUID = -2252972430506210021L;
        volatile boolean cancelled;
        final int end;
        int index;

        /* access modifiers changed from: package-private */
        public abstract void fastPath();

        @Override // io.reactivex.internal.a.d
        public final int requestFusion(int i) {
            return i & 1;
        }

        /* access modifiers changed from: package-private */
        public abstract void slowPath(long j);

        BaseRangeSubscription(int i, int i2) {
            this.index = i;
            this.end = i2;
        }

        @Override // io.reactivex.internal.a.h
        public final Integer poll() {
            int i = this.index;
            if (i == this.end) {
                return null;
            }
            this.index = i + 1;
            return Integer.valueOf(i);
        }

        @Override // io.reactivex.internal.a.h
        public final boolean isEmpty() {
            return this.index == this.end;
        }

        @Override // io.reactivex.internal.a.h
        public final void clear() {
            this.index = this.end;
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

    static final class RangeSubscription extends BaseRangeSubscription {
        private static final long serialVersionUID = 2587302975077663557L;
        final c<? super Integer> downstream;

        RangeSubscription(c<? super Integer> cVar, int i, int i2) {
            super(i, i2);
            this.downstream = cVar;
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableRange.BaseRangeSubscription
        public void fastPath() {
            int i = this.end;
            c<? super Integer> cVar = this.downstream;
            for (int i2 = this.index; i2 != i; i2++) {
                if (!this.cancelled) {
                    cVar.onNext(Integer.valueOf(i2));
                } else {
                    return;
                }
            }
            if (!this.cancelled) {
                cVar.onComplete();
            }
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableRange.BaseRangeSubscription
        public void slowPath(long j) {
            int i = this.end;
            int i2 = this.index;
            c<? super Integer> cVar = this.downstream;
            long j2 = j;
            do {
                long j3 = 0;
                while (true) {
                    if (j3 == j2 || i2 == i) {
                        if (i2 != i) {
                            j2 = get();
                            if (j3 == j2) {
                                this.index = i2;
                                j2 = addAndGet(-j3);
                            }
                        } else if (!this.cancelled) {
                            cVar.onComplete();
                            return;
                        } else {
                            return;
                        }
                    } else if (!this.cancelled) {
                        cVar.onNext(Integer.valueOf(i2));
                        j3++;
                        i2++;
                    } else {
                        return;
                    }
                }
            } while (j2 != 0);
        }
    }

    static final class RangeConditionalSubscription extends BaseRangeSubscription {
        private static final long serialVersionUID = 2587302975077663557L;
        final a<? super Integer> downstream;

        RangeConditionalSubscription(a<? super Integer> aVar, int i, int i2) {
            super(i, i2);
            this.downstream = aVar;
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableRange.BaseRangeSubscription
        public void fastPath() {
            int i = this.end;
            a<? super Integer> aVar = this.downstream;
            for (int i2 = this.index; i2 != i; i2++) {
                if (!this.cancelled) {
                    aVar.tryOnNext(Integer.valueOf(i2));
                } else {
                    return;
                }
            }
            if (!this.cancelled) {
                aVar.onComplete();
            }
        }

        /* access modifiers changed from: package-private */
        @Override // io.reactivex.internal.operators.flowable.FlowableRange.BaseRangeSubscription
        public void slowPath(long j) {
            int i = this.end;
            int i2 = this.index;
            a<? super Integer> aVar = this.downstream;
            long j2 = j;
            do {
                long j3 = 0;
                while (true) {
                    if (j3 == j2 || i2 == i) {
                        if (i2 != i) {
                            j2 = get();
                            if (j3 == j2) {
                                this.index = i2;
                                j2 = addAndGet(-j3);
                            }
                        } else if (!this.cancelled) {
                            aVar.onComplete();
                            return;
                        } else {
                            return;
                        }
                    } else if (!this.cancelled) {
                        if (aVar.tryOnNext(Integer.valueOf(i2))) {
                            j3++;
                        }
                        i2++;
                    } else {
                        return;
                    }
                }
            } while (j2 != 0);
        }
    }
}
