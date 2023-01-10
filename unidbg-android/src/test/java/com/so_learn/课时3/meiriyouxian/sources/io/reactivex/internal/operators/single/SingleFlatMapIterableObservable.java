package io.reactivex.internal.operators.single;

import io.reactivex.ab;
import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.exceptions.a;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.q;
import io.reactivex.v;
import io.reactivex.z;
import java.util.Iterator;

public final class SingleFlatMapIterableObservable<T, R> extends q<R> {
    final ab<T> a;
    final h<? super T, ? extends Iterable<? extends R>> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super R> vVar) {
        this.a.a(new FlatMapIterableObserver(vVar, this.b));
    }

    static final class FlatMapIterableObserver<T, R> extends BasicIntQueueDisposable<R> implements z<T> {
        private static final long serialVersionUID = -8938804753851907758L;
        volatile boolean cancelled;
        final v<? super R> downstream;

        /* renamed from: it  reason: collision with root package name */
        volatile Iterator<? extends R> f1344it;
        final h<? super T, ? extends Iterable<? extends R>> mapper;
        boolean outputFused;
        b upstream;

        FlatMapIterableObserver(v<? super R> vVar, h<? super T, ? extends Iterable<? extends R>> hVar) {
            this.downstream = vVar;
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
            v<? super R> vVar = this.downstream;
            try {
                Iterator<T> it2 = ((Iterable) this.mapper.apply(t)).iterator();
                if (!it2.hasNext()) {
                    vVar.onComplete();
                } else if (this.outputFused) {
                    this.f1344it = it2;
                    vVar.onNext(null);
                    vVar.onComplete();
                } else {
                    while (!this.cancelled) {
                        try {
                            vVar.onNext(it2.next());
                            if (!this.cancelled) {
                                try {
                                    if (!it2.hasNext()) {
                                        vVar.onComplete();
                                        return;
                                    }
                                } catch (Throwable th) {
                                    a.b(th);
                                    vVar.onError(th);
                                    return;
                                }
                            } else {
                                return;
                            }
                        } catch (Throwable th2) {
                            a.b(th2);
                            vVar.onError(th2);
                            return;
                        }
                    }
                }
            } catch (Throwable th3) {
                a.b(th3);
                this.downstream.onError(th3);
            }
        }

        @Override // io.reactivex.z
        public void onError(Throwable th) {
            this.upstream = DisposableHelper.DISPOSED;
            this.downstream.onError(th);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.cancelled = true;
            this.upstream.dispose();
            this.upstream = DisposableHelper.DISPOSED;
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.cancelled;
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
            this.f1344it = null;
        }

        @Override // io.reactivex.internal.a.h
        public boolean isEmpty() {
            return this.f1344it == null;
        }

        @Override // io.reactivex.internal.a.h
        public R poll() throws Exception {
            Iterator<? extends R> it2 = this.f1344it;
            if (it2 == null) {
                return null;
            }
            R r = (R) io.reactivex.internal.functions.a.a(it2.next(), "The iterator returned a null value");
            if (!it2.hasNext()) {
                this.f1344it = null;
            }
            return r;
        }
    }
}
