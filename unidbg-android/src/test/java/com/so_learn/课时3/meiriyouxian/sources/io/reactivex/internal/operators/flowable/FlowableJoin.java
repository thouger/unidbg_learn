package io.reactivex.internal.operators.flowable;

import io.reactivex.c.c;
import io.reactivex.c.h;
import io.reactivex.disposables.a;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.g;
import io.reactivex.internal.operators.flowable.FlowableGroupJoin;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.j;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.d;

public final class FlowableJoin<TLeft, TRight, TLeftEnd, TRightEnd, R> extends a<TLeft, R> {
    final b<? extends TRight> c;
    final h<? super TLeft, ? extends b<TLeftEnd>> d;
    final h<? super TRight, ? extends b<TRightEnd>> e;
    final c<? super TLeft, ? super TRight, ? extends R> f;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(org.a.c<? super R> cVar) {
        JoinSubscription joinSubscription = new JoinSubscription(cVar, this.d, this.e, this.f);
        cVar.onSubscribe(joinSubscription);
        FlowableGroupJoin.LeftRightSubscriber leftRightSubscriber = new FlowableGroupJoin.LeftRightSubscriber(joinSubscription, true);
        joinSubscription.disposables.a(leftRightSubscriber);
        FlowableGroupJoin.LeftRightSubscriber leftRightSubscriber2 = new FlowableGroupJoin.LeftRightSubscriber(joinSubscription, false);
        joinSubscription.disposables.a(leftRightSubscriber2);
        this.b.a((j) leftRightSubscriber);
        this.c.subscribe(leftRightSubscriber2);
    }

    static final class JoinSubscription<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements FlowableGroupJoin.a, d {
        static final Integer LEFT_CLOSE = 3;
        static final Integer LEFT_VALUE = 1;
        static final Integer RIGHT_CLOSE = 4;
        static final Integer RIGHT_VALUE = 2;
        private static final long serialVersionUID = -6071216598687999801L;
        final AtomicInteger active;
        volatile boolean cancelled;
        final a disposables = new a();
        final org.a.c<? super R> downstream;
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final h<? super TLeft, ? extends b<TLeftEnd>> leftEnd;
        int leftIndex;
        final Map<Integer, TLeft> lefts = new LinkedHashMap();
        final io.reactivex.internal.queue.a<Object> queue = new io.reactivex.internal.queue.a<>(g.a());
        final AtomicLong requested = new AtomicLong();
        final c<? super TLeft, ? super TRight, ? extends R> resultSelector;
        final h<? super TRight, ? extends b<TRightEnd>> rightEnd;
        int rightIndex;
        final Map<Integer, TRight> rights = new LinkedHashMap();

        JoinSubscription(org.a.c<? super R> cVar, h<? super TLeft, ? extends b<TLeftEnd>> hVar, h<? super TRight, ? extends b<TRightEnd>> hVar2, c<? super TLeft, ? super TRight, ? extends R> cVar2) {
            this.downstream = cVar;
            this.leftEnd = hVar;
            this.rightEnd = hVar2;
            this.resultSelector = cVar2;
            this.active = new AtomicInteger(2);
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.a(this.requested, j);
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void cancelAll() {
            this.disposables.dispose();
        }

        /* access modifiers changed from: package-private */
        public void errorAll(org.a.c<?> cVar) {
            Throwable a = ExceptionHelper.a(this.error);
            this.lefts.clear();
            this.rights.clear();
            cVar.onError(a);
        }

        /* access modifiers changed from: package-private */
        public void fail(Throwable th, org.a.c<?> cVar, io.reactivex.internal.a.h<?> hVar) {
            io.reactivex.exceptions.a.b(th);
            ExceptionHelper.a(this.error, th);
            hVar.clear();
            cancelAll();
            errorAll(cVar);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r7v3, resolved type: java.util.Map<java.lang.Integer, TRight> */
        /* JADX DEBUG: Multi-variable search result rejected for r7v9, resolved type: java.util.Map<java.lang.Integer, TLeft> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                io.reactivex.internal.queue.a<Object> aVar = this.queue;
                org.a.c<? super R> cVar = this.downstream;
                boolean z = true;
                int i = 1;
                while (!this.cancelled) {
                    if (this.error.get() != null) {
                        aVar.clear();
                        cancelAll();
                        errorAll(cVar);
                        return;
                    }
                    boolean z2 = this.active.get() == 0 ? z : false;
                    Integer num = (Integer) aVar.poll();
                    boolean z3 = num == null ? z : false;
                    if (z2 && z3) {
                        this.lefts.clear();
                        this.rights.clear();
                        this.disposables.dispose();
                        cVar.onComplete();
                        return;
                    } else if (z3) {
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        Object poll = aVar.poll();
                        if (num == LEFT_VALUE) {
                            int i2 = this.leftIndex;
                            this.leftIndex = i2 + 1;
                            this.lefts.put(Integer.valueOf(i2), poll);
                            try {
                                b bVar = (b) io.reactivex.internal.functions.a.a(this.leftEnd.apply(poll), "The leftEnd returned a null Publisher");
                                FlowableGroupJoin.LeftRightEndSubscriber leftRightEndSubscriber = new FlowableGroupJoin.LeftRightEndSubscriber(this, z, i2);
                                this.disposables.a(leftRightEndSubscriber);
                                bVar.subscribe(leftRightEndSubscriber);
                                if (this.error.get() != null) {
                                    aVar.clear();
                                    cancelAll();
                                    errorAll(cVar);
                                    return;
                                }
                                long j = this.requested.get();
                                long j2 = 0;
                                for (TRight tright : this.rights.values()) {
                                    try {
                                        Object a = io.reactivex.internal.functions.a.a(this.resultSelector.apply(poll, tright), "The resultSelector returned a null value");
                                        if (j2 != j) {
                                            cVar.onNext(a);
                                            j2++;
                                        } else {
                                            ExceptionHelper.a(this.error, new MissingBackpressureException("Could not emit value due to lack of requests"));
                                            aVar.clear();
                                            cancelAll();
                                            errorAll(cVar);
                                            return;
                                        }
                                    } catch (Throwable th) {
                                        fail(th, cVar, aVar);
                                        return;
                                    }
                                }
                                if (j2 != 0) {
                                    io.reactivex.internal.util.b.c(this.requested, j2);
                                }
                            } catch (Throwable th2) {
                                fail(th2, cVar, aVar);
                                return;
                            }
                        } else if (num == RIGHT_VALUE) {
                            int i3 = this.rightIndex;
                            this.rightIndex = i3 + 1;
                            this.rights.put(Integer.valueOf(i3), poll);
                            try {
                                b bVar2 = (b) io.reactivex.internal.functions.a.a(this.rightEnd.apply(poll), "The rightEnd returned a null Publisher");
                                FlowableGroupJoin.LeftRightEndSubscriber leftRightEndSubscriber2 = new FlowableGroupJoin.LeftRightEndSubscriber(this, false, i3);
                                this.disposables.a(leftRightEndSubscriber2);
                                bVar2.subscribe(leftRightEndSubscriber2);
                                if (this.error.get() != null) {
                                    aVar.clear();
                                    cancelAll();
                                    errorAll(cVar);
                                    return;
                                }
                                long j3 = this.requested.get();
                                long j4 = 0;
                                for (TLeft tleft : this.lefts.values()) {
                                    try {
                                        Object a2 = io.reactivex.internal.functions.a.a(this.resultSelector.apply(tleft, poll), "The resultSelector returned a null value");
                                        if (j4 != j3) {
                                            cVar.onNext(a2);
                                            j4++;
                                        } else {
                                            ExceptionHelper.a(this.error, new MissingBackpressureException("Could not emit value due to lack of requests"));
                                            aVar.clear();
                                            cancelAll();
                                            errorAll(cVar);
                                            return;
                                        }
                                    } catch (Throwable th3) {
                                        fail(th3, cVar, aVar);
                                        return;
                                    }
                                }
                                if (j4 != 0) {
                                    io.reactivex.internal.util.b.c(this.requested, j4);
                                }
                            } catch (Throwable th4) {
                                fail(th4, cVar, aVar);
                                return;
                            }
                        } else if (num == LEFT_CLOSE) {
                            FlowableGroupJoin.LeftRightEndSubscriber leftRightEndSubscriber3 = (FlowableGroupJoin.LeftRightEndSubscriber) poll;
                            this.lefts.remove(Integer.valueOf(leftRightEndSubscriber3.index));
                            this.disposables.b(leftRightEndSubscriber3);
                        } else if (num == RIGHT_CLOSE) {
                            FlowableGroupJoin.LeftRightEndSubscriber leftRightEndSubscriber4 = (FlowableGroupJoin.LeftRightEndSubscriber) poll;
                            this.rights.remove(Integer.valueOf(leftRightEndSubscriber4.index));
                            this.disposables.b(leftRightEndSubscriber4);
                        }
                        z = true;
                    }
                }
                aVar.clear();
            }
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableGroupJoin.a
        public void innerError(Throwable th) {
            if (ExceptionHelper.a(this.error, th)) {
                this.active.decrementAndGet();
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableGroupJoin.a
        public void innerComplete(FlowableGroupJoin.LeftRightSubscriber leftRightSubscriber) {
            this.disposables.c(leftRightSubscriber);
            this.active.decrementAndGet();
            drain();
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableGroupJoin.a
        public void innerValue(boolean z, Object obj) {
            synchronized (this) {
                this.queue.a(z ? LEFT_VALUE : RIGHT_VALUE, (Integer) obj);
            }
            drain();
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableGroupJoin.a
        public void innerClose(boolean z, FlowableGroupJoin.LeftRightEndSubscriber leftRightEndSubscriber) {
            synchronized (this) {
                this.queue.a(z ? LEFT_CLOSE : RIGHT_CLOSE, (Integer) leftRightEndSubscriber);
            }
            drain();
        }

        @Override // io.reactivex.internal.operators.flowable.FlowableGroupJoin.a
        public void innerCloseError(Throwable th) {
            if (ExceptionHelper.a(this.error, th)) {
                drain();
            } else {
                io.reactivex.e.a.a(th);
            }
        }
    }
}
