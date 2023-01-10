package io.reactivex.internal.operators.mixed;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.e;
import io.reactivex.g;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.j;
import java.util.concurrent.atomic.AtomicReference;
import org.a.d;

public final class FlowableSwitchMapCompletable<T> extends a {
    final g<T> a;
    final h<? super T, ? extends e> b;
    final boolean c;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.a
    public void b(c cVar) {
        this.a.a((j) new SwitchMapCompletableObserver(cVar, this.b, this.c));
    }

    static final class SwitchMapCompletableObserver<T> implements b, j<T> {
        static final SwitchMapInnerObserver f = new SwitchMapInnerObserver(null);
        final c a;
        final h<? super T, ? extends e> b;
        final boolean c;
        final AtomicThrowable d = new AtomicThrowable();
        final AtomicReference<SwitchMapInnerObserver> e = new AtomicReference<>();
        volatile boolean g;
        d h;

        SwitchMapCompletableObserver(c cVar, h<? super T, ? extends e> hVar, boolean z) {
            this.a = cVar;
            this.b = hVar;
            this.c = z;
        }

        @Override // io.reactivex.j
        public void onSubscribe(d dVar) {
            if (SubscriptionHelper.validate(this.h, dVar)) {
                this.h = dVar;
                this.a.onSubscribe(this);
                dVar.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            SwitchMapInnerObserver switchMapInnerObserver;
            try {
                e eVar = (e) io.reactivex.internal.functions.a.a(this.b.apply(t), "The mapper returned a null CompletableSource");
                SwitchMapInnerObserver switchMapInnerObserver2 = new SwitchMapInnerObserver(this);
                do {
                    switchMapInnerObserver = this.e.get();
                    if (switchMapInnerObserver == f) {
                        return;
                    }
                } while (!this.e.compareAndSet(switchMapInnerObserver, switchMapInnerObserver2));
                if (switchMapInnerObserver != null) {
                    switchMapInnerObserver.dispose();
                }
                eVar.a(switchMapInnerObserver2);
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.h.cancel();
                onError(th);
            }
        }

        public void onError(Throwable th) {
            if (!this.d.addThrowable(th)) {
                io.reactivex.e.a.a(th);
            } else if (this.c) {
                onComplete();
            } else {
                a();
                Throwable terminate = this.d.terminate();
                if (terminate != ExceptionHelper.a) {
                    this.a.onError(terminate);
                }
            }
        }

        public void onComplete() {
            this.g = true;
            if (this.e.get() == null) {
                Throwable terminate = this.d.terminate();
                if (terminate == null) {
                    this.a.onComplete();
                } else {
                    this.a.onError(terminate);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public void a() {
            SwitchMapInnerObserver andSet = this.e.getAndSet(f);
            if (andSet != null && andSet != f) {
                andSet.dispose();
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.h.cancel();
            a();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.e.get() == f;
        }

        /* access modifiers changed from: package-private */
        public void a(SwitchMapInnerObserver switchMapInnerObserver, Throwable th) {
            if (!this.e.compareAndSet(switchMapInnerObserver, null) || !this.d.addThrowable(th)) {
                io.reactivex.e.a.a(th);
            } else if (!this.c) {
                dispose();
                Throwable terminate = this.d.terminate();
                if (terminate != ExceptionHelper.a) {
                    this.a.onError(terminate);
                }
            } else if (this.g) {
                this.a.onError(this.d.terminate());
            }
        }

        /* access modifiers changed from: package-private */
        public void a(SwitchMapInnerObserver switchMapInnerObserver) {
            if (this.e.compareAndSet(switchMapInnerObserver, null) && this.g) {
                Throwable terminate = this.d.terminate();
                if (terminate == null) {
                    this.a.onComplete();
                } else {
                    this.a.onError(terminate);
                }
            }
        }

        /* access modifiers changed from: package-private */
        public static final class SwitchMapInnerObserver extends AtomicReference<b> implements c {
            private static final long serialVersionUID = -8003404460084760287L;
            final SwitchMapCompletableObserver<?> parent;

            SwitchMapInnerObserver(SwitchMapCompletableObserver<?> switchMapCompletableObserver) {
                this.parent = switchMapCompletableObserver;
            }

            @Override // io.reactivex.c
            public void onSubscribe(b bVar) {
                DisposableHelper.setOnce(this, bVar);
            }

            @Override // io.reactivex.c
            public void onError(Throwable th) {
                this.parent.a(this, th);
            }

            @Override // io.reactivex.c
            public void onComplete() {
                this.parent.a(this);
            }

            /* access modifiers changed from: package-private */
            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }
    }
}
