package io.reactivex.internal.operators.flowable;

import io.reactivex.c.c;
import io.reactivex.c.h;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.g;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.j;
import io.reactivex.processors.UnicastProcessor;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.b;
import org.a.d;

public final class FlowableGroupJoin<TLeft, TRight, TLeftEnd, TRightEnd, R> extends a<TLeft, R> {
    final b<? extends TRight> c;
    final h<? super TLeft, ? extends b<TLeftEnd>> d;
    final h<? super TRight, ? extends b<TRightEnd>> e;
    final c<? super TLeft, ? super g<TRight>, ? extends R> f;

    /* access modifiers changed from: package-private */
    public interface a {
        void innerClose(boolean z, LeftRightEndSubscriber leftRightEndSubscriber);

        void innerCloseError(Throwable th);

        void innerComplete(LeftRightSubscriber leftRightSubscriber);

        void innerError(Throwable th);

        void innerValue(boolean z, Object obj);
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(org.a.c<? super R> cVar) {
        GroupJoinSubscription groupJoinSubscription = new GroupJoinSubscription(cVar, this.d, this.e, this.f);
        cVar.onSubscribe(groupJoinSubscription);
        LeftRightSubscriber leftRightSubscriber = new LeftRightSubscriber(groupJoinSubscription, true);
        groupJoinSubscription.disposables.a(leftRightSubscriber);
        LeftRightSubscriber leftRightSubscriber2 = new LeftRightSubscriber(groupJoinSubscription, false);
        groupJoinSubscription.disposables.a(leftRightSubscriber2);
        this.b.a((j) leftRightSubscriber);
        this.c.subscribe(leftRightSubscriber2);
    }

    static final class GroupJoinSubscription<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements a, d {
        static final Integer LEFT_CLOSE = 3;
        static final Integer LEFT_VALUE = 1;
        static final Integer RIGHT_CLOSE = 4;
        static final Integer RIGHT_VALUE = 2;
        private static final long serialVersionUID = -6071216598687999801L;
        final AtomicInteger active;
        volatile boolean cancelled;
        final io.reactivex.disposables.a disposables = new io.reactivex.disposables.a();
        final org.a.c<? super R> downstream;
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final h<? super TLeft, ? extends b<TLeftEnd>> leftEnd;
        int leftIndex;
        final Map<Integer, UnicastProcessor<TRight>> lefts = new LinkedHashMap();
        final io.reactivex.internal.queue.a<Object> queue = new io.reactivex.internal.queue.a<>(g.a());
        final AtomicLong requested = new AtomicLong();
        final c<? super TLeft, ? super g<TRight>, ? extends R> resultSelector;
        final h<? super TRight, ? extends b<TRightEnd>> rightEnd;
        int rightIndex;
        final Map<Integer, TRight> rights = new LinkedHashMap();

        GroupJoinSubscription(org.a.c<? super R> cVar, h<? super TLeft, ? extends b<TLeftEnd>> hVar, h<? super TRight, ? extends b<TRightEnd>> hVar2, c<? super TLeft, ? super g<TRight>, ? extends R> cVar2) {
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
            for (UnicastProcessor<TRight> unicastProcessor : this.lefts.values()) {
                unicastProcessor.onError(a);
            }
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

        /* JADX DEBUG: Multi-variable search result rejected for r7v10, resolved type: java.util.Map<java.lang.Integer, TRight> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                io.reactivex.internal.queue.a<Object> aVar = this.queue;
                org.a.c<? super R> cVar = this.downstream;
                int i = 1;
                while (!this.cancelled) {
                    if (this.error.get() != null) {
                        aVar.clear();
                        cancelAll();
                        errorAll(cVar);
                        return;
                    }
                    boolean z = this.active.get() == 0;
                    Integer num = (Integer) aVar.poll();
                    boolean z2 = num == null;
                    if (z && z2) {
                        for (UnicastProcessor<TRight> unicastProcessor : this.lefts.values()) {
                            unicastProcessor.onComplete();
                        }
                        this.lefts.clear();
                        this.rights.clear();
                        this.disposables.dispose();
                        cVar.onComplete();
                        return;
                    } else if (z2) {
                        i = addAndGet(-i);
                        if (i == 0) {
                            return;
                        }
                    } else {
                        Object poll = aVar.poll();
                        if (num == LEFT_VALUE) {
                            UnicastProcessor<TRight> h = UnicastProcessor.h();
                            int i2 = this.leftIndex;
                            this.leftIndex = i2 + 1;
                            this.lefts.put(Integer.valueOf(i2), h);
                            try {
                                b bVar = (b) io.reactivex.internal.functions.a.a(this.leftEnd.apply(poll), "The leftEnd returned a null Publisher");
                                LeftRightEndSubscriber leftRightEndSubscriber = new LeftRightEndSubscriber(this, true, i2);
                                this.disposables.a(leftRightEndSubscriber);
                                bVar.subscribe(leftRightEndSubscriber);
                                if (this.error.get() != null) {
                                    aVar.clear();
                                    cancelAll();
                                    errorAll(cVar);
                                    return;
                                }
                                try {
                                    Object a = io.reactivex.internal.functions.a.a(this.resultSelector.apply(poll, h), "The resultSelector returned a null value");
                                    if (this.requested.get() != 0) {
                                        cVar.onNext(a);
                                        io.reactivex.internal.util.b.c(this.requested, 1);
                                        for (TRight tright : this.rights.values()) {
                                            h.onNext(tright);
                                        }
                                    } else {
                                        fail(new MissingBackpressureException("Could not emit value due to lack of requests"), cVar, aVar);
                                        return;
                                    }
                                } catch (Throwable th) {
                                    fail(th, cVar, aVar);
                                    return;
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
                                LeftRightEndSubscriber leftRightEndSubscriber2 = new LeftRightEndSubscriber(this, false, i3);
                                this.disposables.a(leftRightEndSubscriber2);
                                bVar2.subscribe(leftRightEndSubscriber2);
                                if (this.error.get() != null) {
                                    aVar.clear();
                                    cancelAll();
                                    errorAll(cVar);
                                    return;
                                }
                                for (UnicastProcessor<TRight> unicastProcessor2 : this.lefts.values()) {
                                    unicastProcessor2.onNext(poll);
                                }
                            } catch (Throwable th3) {
                                fail(th3, cVar, aVar);
                                return;
                            }
                        } else if (num == LEFT_CLOSE) {
                            LeftRightEndSubscriber leftRightEndSubscriber3 = (LeftRightEndSubscriber) poll;
                            UnicastProcessor<TRight> remove = this.lefts.remove(Integer.valueOf(leftRightEndSubscriber3.index));
                            this.disposables.b(leftRightEndSubscriber3);
                            if (remove != null) {
                                remove.onComplete();
                            }
                        } else if (num == RIGHT_CLOSE) {
                            LeftRightEndSubscriber leftRightEndSubscriber4 = (LeftRightEndSubscriber) poll;
                            this.rights.remove(Integer.valueOf(leftRightEndSubscriber4.index));
                            this.disposables.b(leftRightEndSubscriber4);
                        }
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
        public void innerComplete(LeftRightSubscriber leftRightSubscriber) {
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
        public void innerClose(boolean z, LeftRightEndSubscriber leftRightEndSubscriber) {
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

    static final class LeftRightSubscriber extends AtomicReference<d> implements io.reactivex.disposables.b, j<Object> {
        private static final long serialVersionUID = 1883890389173668373L;
        final boolean isLeft;
        final a parent;

        LeftRightSubscriber(a aVar, boolean z) {
            this.parent = aVar;
            this.isLeft = z;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            SubscriptionHelper.cancel(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() == SubscriptionHelper.CANCELLED;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            SubscriptionHelper.setOnce(this, dVar, Long.MAX_VALUE);
        }

        public void onNext(Object obj) {
            this.parent.innerValue(this.isLeft, obj);
        }

        public void onError(Throwable th) {
            this.parent.innerError(th);
        }

        public void onComplete() {
            this.parent.innerComplete(this);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class LeftRightEndSubscriber extends AtomicReference<d> implements io.reactivex.disposables.b, j<Object> {
        private static final long serialVersionUID = 1883890389173668373L;
        final int index;
        final boolean isLeft;
        final a parent;

        LeftRightEndSubscriber(a aVar, boolean z, int i) {
            this.parent = aVar;
            this.isLeft = z;
            this.index = i;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            SubscriptionHelper.cancel(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() == SubscriptionHelper.CANCELLED;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            SubscriptionHelper.setOnce(this, dVar, Long.MAX_VALUE);
        }

        public void onNext(Object obj) {
            if (SubscriptionHelper.cancel(this)) {
                this.parent.innerClose(this.isLeft, this);
            }
        }

        public void onError(Throwable th) {
            this.parent.innerCloseError(th);
        }

        public void onComplete() {
            this.parent.innerClose(this.isLeft, this);
        }
    }
}
