package io.reactivex.internal.operators.single;

import io.reactivex.ab;
import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.a;
import io.reactivex.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.z;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicLong;
import org.a.c;

public final class SingleFlatMapIterableFlowable<T, R> extends g<R> {
    final ab<T> b;
    final h<? super T, ? extends Iterable<? extends R>> c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super R> cVar) {
        this.b.a(new FlatMapIterableObserver(cVar, this.c));
    }

    static final class FlatMapIterableObserver<T, R> extends BasicIntQueueSubscription<R> implements z<T> {
        private static final long serialVersionUID = -8938804753851907758L;
        volatile boolean cancelled;
        final c<? super R> downstream;

        /* renamed from: it  reason: collision with root package name */
        volatile Iterator<? extends R> f1343it;
        final h<? super T, ? extends Iterable<? extends R>> mapper;
        boolean outputFused;
        final AtomicLong requested = new AtomicLong();
        b upstream;

        FlatMapIterableObserver(c<? super R> cVar, h<? super T, ? extends Iterable<? extends R>> hVar) {
            this.downstream = cVar;
            this.mapper = hVar;
        }

        @Override // io.reactivex.z
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.z
        public void onSuccess(T t) {
            try {
                Iterator<T> it2 = ((Iterable) this.mapper.apply(t)).iterator();
                if (!it2.hasNext()) {
                    this.downstream.onComplete();
                    return;
                }
                this.f1343it = it2;
                drain();
            } catch (Throwable th) {
                a.b(th);
                this.downstream.onError(th);
            }
        }

        @Override // io.reactivex.z
        public void onError(Throwable th) {
            this.upstream = DisposableHelper.DISPOSED;
            this.downstream.onError(th);
        }

        public void request(long j) {
            if (SubscriptionHelper.validate(j)) {
                io.reactivex.internal.util.b.a(this.requested, j);
                drain();
            }
        }

        public void cancel() {
            this.cancelled = true;
            this.upstream.dispose();
            this.upstream = DisposableHelper.DISPOSED;
        }

        /* access modifiers changed from: package-private */
        public void drain() {
            if (getAndIncrement() == 0) {
                c<? super R> cVar = this.downstream;
                Iterator<? extends R> it2 = this.f1343it;
                if (!this.outputFused || it2 == null) {
                    int i = 1;
                    while (true) {
                        if (it2 != null) {
                            long j = this.requested.get();
                            if (j == Long.MAX_VALUE) {
                                slowPath(cVar, it2);
                                return;
                            }
                            long j2 = 0;
                            while (j2 != j) {
                                if (!this.cancelled) {
                                    try {
                                        cVar.onNext(io.reactivex.internal.functions.a.a(it2.next(), "The iterator returned a null value"));
                                        if (!this.cancelled) {
                                            j2++;
                                            try {
                                                if (!it2.hasNext()) {
                                                    cVar.onComplete();
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
                                    } catch (Throwable th2) {
                                        a.b(th2);
                                        cVar.onError(th2);
                                        return;
                                    }
                                } else {
                                    return;
                                }
                            }
                            if (j2 != 0) {
                                io.reactivex.internal.util.b.c(this.requested, j2);
                            }
                        }
                        i = addAndGet(-i);
                        if (i != 0) {
                            if (it2 == null) {
                                it2 = this.f1343it;
                            }
                        } else {
                            return;
                        }
                    }
                } else {
                    cVar.onNext((Object) null);
                    cVar.onComplete();
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void slowPath(c<? super R> cVar, Iterator<? extends R> it2) {
            while (!this.cancelled) {
                try {
                    cVar.onNext(it2.next());
                    if (!this.cancelled) {
                        try {
                            if (!it2.hasNext()) {
                                cVar.onComplete();
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
                } catch (Throwable th2) {
                    a.b(th2);
                    cVar.onError(th2);
                    return;
                }
            }
        }

        @Override // io.reactivex.internal.a.d
        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        @Override // io.reactivex.internal.a.h
        public void clear() {
            this.f1343it = null;
        }

        @Override // io.reactivex.internal.a.h
        public boolean isEmpty() {
            return this.f1343it == null;
        }

        @Override // io.reactivex.internal.a.h
        public R poll() throws Exception {
            Iterator<? extends R> it2 = this.f1343it;
            if (it2 == null) {
                return null;
            }
            R r = (R) io.reactivex.internal.functions.a.a(it2.next(), "The iterator returned a null value");
            if (!it2.hasNext()) {
                this.f1343it = null;
            }
            return r;
        }
    }
}
