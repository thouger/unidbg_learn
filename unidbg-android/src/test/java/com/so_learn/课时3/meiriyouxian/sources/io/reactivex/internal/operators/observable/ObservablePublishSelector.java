package io.reactivex.internal.operators.observable;

import io.reactivex.c.h;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.q;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.t;
import io.reactivex.v;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservablePublishSelector<T, R> extends a<T, R> {
    final h<? super q<T>, ? extends t<R>> b;

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super R> vVar) {
        PublishSubject a2 = PublishSubject.a();
        try {
            t tVar = (t) io.reactivex.internal.functions.a.a(this.b.apply(a2), "The selector returned a null ObservableSource");
            TargetObserver targetObserver = new TargetObserver(vVar);
            tVar.subscribe(targetObserver);
            this.a.subscribe(new a(a2, targetObserver));
        } catch (Throwable th) {
            io.reactivex.exceptions.a.b(th);
            EmptyDisposable.error(th, vVar);
        }
    }

    static final class a<T, R> implements v<T> {
        final PublishSubject<T> a;
        final AtomicReference<b> b;

        a(PublishSubject<T> publishSubject, AtomicReference<b> atomicReference) {
            this.a = publishSubject;
            this.b = atomicReference;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            DisposableHelper.setOnce(this.b, bVar);
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            this.a.onNext(t);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.a.onError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.a.onComplete();
        }
    }

    static final class TargetObserver<T, R> extends AtomicReference<b> implements b, v<R> {
        private static final long serialVersionUID = 854110278590336484L;
        final v<? super R> downstream;
        b upstream;

        TargetObserver(v<? super R> vVar) {
            this.downstream = vVar;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.v
        public void onNext(R r) {
            this.downstream.onNext(r);
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            DisposableHelper.dispose(this);
            this.downstream.onError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            DisposableHelper.dispose(this);
            this.downstream.onComplete();
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.upstream.dispose();
            DisposableHelper.dispose(this);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }
    }
}
