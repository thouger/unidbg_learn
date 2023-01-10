package io.reactivex.internal.operators.observable;

import io.reactivex.c.c;
import io.reactivex.c.h;
import io.reactivex.disposables.a;
import io.reactivex.disposables.b;
import io.reactivex.internal.operators.observable.ObservableGroupJoin;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.q;
import io.reactivex.t;
import io.reactivex.v;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableJoin<TLeft, TRight, TLeftEnd, TRightEnd, R> extends a<TLeft, R> {
    final t<? extends TRight> b;
    final h<? super TLeft, ? extends t<TLeftEnd>> c;
    final h<? super TRight, ? extends t<TRightEnd>> d;
    final c<? super TLeft, ? super TRight, ? extends R> e;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super R> vVar) {
        JoinDisposable joinDisposable = new JoinDisposable(vVar, this.c, this.d, this.e);
        vVar.onSubscribe(joinDisposable);
        ObservableGroupJoin.LeftRightObserver leftRightObserver = new ObservableGroupJoin.LeftRightObserver(joinDisposable, true);
        joinDisposable.disposables.a(leftRightObserver);
        ObservableGroupJoin.LeftRightObserver leftRightObserver2 = new ObservableGroupJoin.LeftRightObserver(joinDisposable, false);
        joinDisposable.disposables.a(leftRightObserver2);
        this.a.subscribe(leftRightObserver);
        this.b.subscribe(leftRightObserver2);
    }

    static final class JoinDisposable<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements b, ObservableGroupJoin.a {
        static final Integer LEFT_CLOSE = 3;
        static final Integer LEFT_VALUE = 1;
        static final Integer RIGHT_CLOSE = 4;
        static final Integer RIGHT_VALUE = 2;
        private static final long serialVersionUID = -6071216598687999801L;
        final AtomicInteger active;
        volatile boolean cancelled;
        final a disposables = new a();
        final v<? super R> downstream;
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final h<? super TLeft, ? extends t<TLeftEnd>> leftEnd;
        int leftIndex;
        final Map<Integer, TLeft> lefts = new LinkedHashMap();
        final io.reactivex.internal.queue.a<Object> queue = new io.reactivex.internal.queue.a<>(q.c());
        final c<? super TLeft, ? super TRight, ? extends R> resultSelector;
        final h<? super TRight, ? extends t<TRightEnd>> rightEnd;
        int rightIndex;
        final Map<Integer, TRight> rights = new LinkedHashMap();

        JoinDisposable(v<? super R> vVar, h<? super TLeft, ? extends t<TLeftEnd>> hVar, h<? super TRight, ? extends t<TRightEnd>> hVar2, c<? super TLeft, ? super TRight, ? extends R> cVar) {
            this.downstream = vVar;
            this.leftEnd = hVar;
            this.rightEnd = hVar2;
            this.resultSelector = cVar;
            this.active = new AtomicInteger(2);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.cancelled;
        }

        /* access modifiers changed from: package-private */
        public void cancelAll() {
            this.disposables.dispose();
        }

        /* access modifiers changed from: package-private */
        public void errorAll(v<?> vVar) {
            Throwable a = ExceptionHelper.a(this.error);
            this.lefts.clear();
            this.rights.clear();
            vVar.onError(a);
        }

        /* access modifiers changed from: package-private */
        public void fail(Throwable th, v<?> vVar, io.reactivex.internal.queue.a<?> aVar) {
            io.reactivex.exceptions.a.b(th);
            ExceptionHelper.a(this.error, th);
            aVar.clear();
            cancelAll();
            errorAll(vVar);
        }

        /* JADX DEBUG: Multi-variable search result rejected for r6v3, resolved type: java.util.Map<java.lang.Integer, TLeft> */
        /* JADX DEBUG: Multi-variable search result rejected for r7v8, resolved type: java.util.Map<java.lang.Integer, TRight> */
        /* JADX WARN: Multi-variable type inference failed */
        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                io.reactivex.internal.queue.a<Object> aVar = this.queue;
                v<? super R> vVar = this.downstream;
                int i = 1;
                while (!this.cancelled) {
                    if (this.error.get() != null) {
                        aVar.clear();
                        cancelAll();
                        errorAll(vVar);
                        return;
                    }
                    boolean z = this.active.get() == 0;
                    Integer num = (Integer) aVar.poll();
                    boolean z2 = num == null;
                    if (z && z2) {
                        this.lefts.clear();
                        this.rights.clear();
                        this.disposables.dispose();
                        vVar.onComplete();
                        return;
                    } else if (z2) {
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
                                t tVar = (t) io.reactivex.internal.functions.a.a(this.leftEnd.apply(poll), "The leftEnd returned a null ObservableSource");
                                ObservableGroupJoin.LeftRightEndObserver leftRightEndObserver = new ObservableGroupJoin.LeftRightEndObserver(this, true, i2);
                                this.disposables.a(leftRightEndObserver);
                                tVar.subscribe(leftRightEndObserver);
                                if (this.error.get() != null) {
                                    aVar.clear();
                                    cancelAll();
                                    errorAll(vVar);
                                    return;
                                }
                                for (TRight tright : this.rights.values()) {
                                    try {
                                        vVar.onNext(io.reactivex.internal.functions.a.a(this.resultSelector.apply(poll, tright), "The resultSelector returned a null value"));
                                    } catch (Throwable th) {
                                        fail(th, vVar, aVar);
                                        return;
                                    }
                                }
                            } catch (Throwable th2) {
                                fail(th2, vVar, aVar);
                                return;
                            }
                        } else if (num == RIGHT_VALUE) {
                            int i3 = this.rightIndex;
                            this.rightIndex = i3 + 1;
                            this.rights.put(Integer.valueOf(i3), poll);
                            try {
                                t tVar2 = (t) io.reactivex.internal.functions.a.a(this.rightEnd.apply(poll), "The rightEnd returned a null ObservableSource");
                                ObservableGroupJoin.LeftRightEndObserver leftRightEndObserver2 = new ObservableGroupJoin.LeftRightEndObserver(this, false, i3);
                                this.disposables.a(leftRightEndObserver2);
                                tVar2.subscribe(leftRightEndObserver2);
                                if (this.error.get() != null) {
                                    aVar.clear();
                                    cancelAll();
                                    errorAll(vVar);
                                    return;
                                }
                                for (TLeft tleft : this.lefts.values()) {
                                    try {
                                        vVar.onNext(io.reactivex.internal.functions.a.a(this.resultSelector.apply(tleft, poll), "The resultSelector returned a null value"));
                                    } catch (Throwable th3) {
                                        fail(th3, vVar, aVar);
                                        return;
                                    }
                                }
                            } catch (Throwable th4) {
                                fail(th4, vVar, aVar);
                                return;
                            }
                        } else if (num == LEFT_CLOSE) {
                            ObservableGroupJoin.LeftRightEndObserver leftRightEndObserver3 = (ObservableGroupJoin.LeftRightEndObserver) poll;
                            this.lefts.remove(Integer.valueOf(leftRightEndObserver3.index));
                            this.disposables.b(leftRightEndObserver3);
                        } else {
                            ObservableGroupJoin.LeftRightEndObserver leftRightEndObserver4 = (ObservableGroupJoin.LeftRightEndObserver) poll;
                            this.rights.remove(Integer.valueOf(leftRightEndObserver4.index));
                            this.disposables.b(leftRightEndObserver4);
                        }
                    }
                }
                aVar.clear();
            }
        }

        @Override // io.reactivex.internal.operators.observable.ObservableGroupJoin.a
        public void innerError(Throwable th) {
            if (ExceptionHelper.a(this.error, th)) {
                this.active.decrementAndGet();
                drain();
                return;
            }
            io.reactivex.e.a.a(th);
        }

        @Override // io.reactivex.internal.operators.observable.ObservableGroupJoin.a
        public void innerComplete(ObservableGroupJoin.LeftRightObserver leftRightObserver) {
            this.disposables.c(leftRightObserver);
            this.active.decrementAndGet();
            drain();
        }

        @Override // io.reactivex.internal.operators.observable.ObservableGroupJoin.a
        public void innerValue(boolean z, Object obj) {
            synchronized (this) {
                this.queue.a(z ? LEFT_VALUE : RIGHT_VALUE, (Integer) obj);
            }
            drain();
        }

        @Override // io.reactivex.internal.operators.observable.ObservableGroupJoin.a
        public void innerClose(boolean z, ObservableGroupJoin.LeftRightEndObserver leftRightEndObserver) {
            synchronized (this) {
                this.queue.a(z ? LEFT_CLOSE : RIGHT_CLOSE, (Integer) leftRightEndObserver);
            }
            drain();
        }

        @Override // io.reactivex.internal.operators.observable.ObservableGroupJoin.a
        public void innerCloseError(Throwable th) {
            if (ExceptionHelper.a(this.error, th)) {
                drain();
            } else {
                io.reactivex.e.a.a(th);
            }
        }
    }
}
