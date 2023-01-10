package io.reactivex.internal.operators.observable;

import io.reactivex.c.c;
import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.q;
import io.reactivex.subjects.UnicastSubject;
import io.reactivex.t;
import io.reactivex.v;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableGroupJoin<TLeft, TRight, TLeftEnd, TRightEnd, R> extends a<TLeft, R> {
    final t<? extends TRight> b;
    final h<? super TLeft, ? extends t<TLeftEnd>> c;
    final h<? super TRight, ? extends t<TRightEnd>> d;
    final c<? super TLeft, ? super q<TRight>, ? extends R> e;

    /* access modifiers changed from: package-private */
    public interface a {
        void innerClose(boolean z, LeftRightEndObserver leftRightEndObserver);

        void innerCloseError(Throwable th);

        void innerComplete(LeftRightObserver leftRightObserver);

        void innerError(Throwable th);

        void innerValue(boolean z, Object obj);
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super R> vVar) {
        GroupJoinDisposable groupJoinDisposable = new GroupJoinDisposable(vVar, this.c, this.d, this.e);
        vVar.onSubscribe(groupJoinDisposable);
        LeftRightObserver leftRightObserver = new LeftRightObserver(groupJoinDisposable, true);
        groupJoinDisposable.disposables.a(leftRightObserver);
        LeftRightObserver leftRightObserver2 = new LeftRightObserver(groupJoinDisposable, false);
        groupJoinDisposable.disposables.a(leftRightObserver2);
        this.a.subscribe(leftRightObserver);
        this.b.subscribe(leftRightObserver2);
    }

    static final class GroupJoinDisposable<TLeft, TRight, TLeftEnd, TRightEnd, R> extends AtomicInteger implements b, a {
        static final Integer LEFT_CLOSE = 3;
        static final Integer LEFT_VALUE = 1;
        static final Integer RIGHT_CLOSE = 4;
        static final Integer RIGHT_VALUE = 2;
        private static final long serialVersionUID = -6071216598687999801L;
        final AtomicInteger active;
        volatile boolean cancelled;
        final io.reactivex.disposables.a disposables = new io.reactivex.disposables.a();
        final v<? super R> downstream;
        final AtomicReference<Throwable> error = new AtomicReference<>();
        final h<? super TLeft, ? extends t<TLeftEnd>> leftEnd;
        int leftIndex;
        final Map<Integer, UnicastSubject<TRight>> lefts = new LinkedHashMap();
        final io.reactivex.internal.queue.a<Object> queue = new io.reactivex.internal.queue.a<>(q.c());
        final c<? super TLeft, ? super q<TRight>, ? extends R> resultSelector;
        final h<? super TRight, ? extends t<TRightEnd>> rightEnd;
        int rightIndex;
        final Map<Integer, TRight> rights = new LinkedHashMap();

        GroupJoinDisposable(v<? super R> vVar, h<? super TLeft, ? extends t<TLeftEnd>> hVar, h<? super TRight, ? extends t<TRightEnd>> hVar2, c<? super TLeft, ? super q<TRight>, ? extends R> cVar) {
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
            for (UnicastSubject<TRight> unicastSubject : this.lefts.values()) {
                unicastSubject.onError(a);
            }
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

        /* JADX DEBUG: Multi-variable search result rejected for r7v10, resolved type: java.util.Map<java.lang.Integer, TRight> */
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
                        for (UnicastSubject<TRight> unicastSubject : this.lefts.values()) {
                            unicastSubject.onComplete();
                        }
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
                            UnicastSubject<TRight> a = UnicastSubject.a();
                            int i2 = this.leftIndex;
                            this.leftIndex = i2 + 1;
                            this.lefts.put(Integer.valueOf(i2), a);
                            try {
                                t tVar = (t) io.reactivex.internal.functions.a.a(this.leftEnd.apply(poll), "The leftEnd returned a null ObservableSource");
                                LeftRightEndObserver leftRightEndObserver = new LeftRightEndObserver(this, true, i2);
                                this.disposables.a(leftRightEndObserver);
                                tVar.subscribe(leftRightEndObserver);
                                if (this.error.get() != null) {
                                    aVar.clear();
                                    cancelAll();
                                    errorAll(vVar);
                                    return;
                                }
                                try {
                                    vVar.onNext(io.reactivex.internal.functions.a.a(this.resultSelector.apply(poll, a), "The resultSelector returned a null value"));
                                    for (TRight tright : this.rights.values()) {
                                        a.onNext(tright);
                                    }
                                } catch (Throwable th) {
                                    fail(th, vVar, aVar);
                                    return;
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
                                LeftRightEndObserver leftRightEndObserver2 = new LeftRightEndObserver(this, false, i3);
                                this.disposables.a(leftRightEndObserver2);
                                tVar2.subscribe(leftRightEndObserver2);
                                if (this.error.get() != null) {
                                    aVar.clear();
                                    cancelAll();
                                    errorAll(vVar);
                                    return;
                                }
                                for (UnicastSubject<TRight> unicastSubject2 : this.lefts.values()) {
                                    unicastSubject2.onNext(poll);
                                }
                            } catch (Throwable th3) {
                                fail(th3, vVar, aVar);
                                return;
                            }
                        } else if (num == LEFT_CLOSE) {
                            LeftRightEndObserver leftRightEndObserver3 = (LeftRightEndObserver) poll;
                            UnicastSubject<TRight> remove = this.lefts.remove(Integer.valueOf(leftRightEndObserver3.index));
                            this.disposables.b(leftRightEndObserver3);
                            if (remove != null) {
                                remove.onComplete();
                            }
                        } else if (num == RIGHT_CLOSE) {
                            LeftRightEndObserver leftRightEndObserver4 = (LeftRightEndObserver) poll;
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
        public void innerComplete(LeftRightObserver leftRightObserver) {
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
        public void innerClose(boolean z, LeftRightEndObserver leftRightEndObserver) {
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

    static final class LeftRightObserver extends AtomicReference<b> implements b, v<Object> {
        private static final long serialVersionUID = 1883890389173668373L;
        final boolean isLeft;
        final a parent;

        LeftRightObserver(a aVar, boolean z) {
            this.parent = aVar;
            this.isLeft = z;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        @Override // io.reactivex.v
        public void onNext(Object obj) {
            this.parent.innerValue(this.isLeft, obj);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.parent.innerError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.parent.innerComplete(this);
        }
    }

    /* access modifiers changed from: package-private */
    public static final class LeftRightEndObserver extends AtomicReference<b> implements b, v<Object> {
        private static final long serialVersionUID = 1883890389173668373L;
        final int index;
        final boolean isLeft;
        final a parent;

        LeftRightEndObserver(a aVar, boolean z, int i) {
            this.parent = aVar;
            this.isLeft = z;
            this.index = i;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return DisposableHelper.isDisposed(get());
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this, bVar);
        }

        @Override // io.reactivex.v
        public void onNext(Object obj) {
            if (DisposableHelper.dispose(this)) {
                this.parent.innerClose(this.isLeft, this);
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.parent.innerCloseError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.parent.innerClose(this.isLeft, this);
        }
    }
}
