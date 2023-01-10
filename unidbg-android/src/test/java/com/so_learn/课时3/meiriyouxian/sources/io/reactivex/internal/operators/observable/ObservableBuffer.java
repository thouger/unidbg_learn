package io.reactivex.internal.operators.observable;

import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.t;
import io.reactivex.v;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ObservableBuffer<T, U extends Collection<? super T>> extends a<T, U> {
    final int b;
    final int c;
    final Callable<U> d;

    public ObservableBuffer(t<T> tVar, int i, int i2, Callable<U> callable) {
        super(tVar);
        this.b = i;
        this.c = i2;
        this.d = callable;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super U> vVar) {
        int i = this.c;
        int i2 = this.b;
        if (i == i2) {
            a aVar = new a(vVar, i2, this.d);
            if (aVar.a()) {
                this.a.subscribe(aVar);
                return;
            }
            return;
        }
        this.a.subscribe(new BufferSkipObserver(vVar, this.b, this.c, this.d));
    }

    static final class a<T, U extends Collection<? super T>> implements b, v<T> {
        final v<? super U> a;
        final int b;
        final Callable<U> c;
        U d;
        int e;
        b f;

        a(v<? super U> vVar, int i, Callable<U> callable) {
            this.a = vVar;
            this.b = i;
            this.c = callable;
        }

        /* access modifiers changed from: package-private */
        public boolean a() {
            try {
                this.d = (U) ((Collection) io.reactivex.internal.functions.a.a(this.c.call(), "Empty buffer supplied"));
                return true;
            } catch (Throwable th) {
                io.reactivex.exceptions.a.b(th);
                this.d = null;
                b bVar = this.f;
                if (bVar == null) {
                    EmptyDisposable.error(th, this.a);
                    return false;
                }
                bVar.dispose();
                this.a.onError(th);
                return false;
            }
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.f, bVar)) {
                this.f = bVar;
                this.a.onSubscribe(this);
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.f.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.f.isDisposed();
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            U u = this.d;
            if (u != null) {
                u.add(t);
                int i = this.e + 1;
                this.e = i;
                if (i >= this.b) {
                    this.a.onNext(u);
                    this.e = 0;
                    a();
                }
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.d = null;
            this.a.onError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            U u = this.d;
            if (u != null) {
                this.d = null;
                if (!u.isEmpty()) {
                    this.a.onNext(u);
                }
                this.a.onComplete();
            }
        }
    }

    static final class BufferSkipObserver<T, U extends Collection<? super T>> extends AtomicBoolean implements b, v<T> {
        private static final long serialVersionUID = -8223395059921494546L;
        final Callable<U> bufferSupplier;
        final ArrayDeque<U> buffers = new ArrayDeque<>();
        final int count;
        final v<? super U> downstream;
        long index;
        final int skip;
        b upstream;

        BufferSkipObserver(v<? super U> vVar, int i, int i2, Callable<U> callable) {
            this.downstream = vVar;
            this.count = i;
            this.skip = i2;
            this.bufferSupplier = callable;
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.upstream.dispose();
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.upstream.isDisposed();
        }

        /* JADX DEBUG: Multi-variable search result rejected for r1v4, resolved type: java.util.ArrayDeque<U extends java.util.Collection<? super T>> */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // io.reactivex.v
        public void onNext(T t) {
            long j = this.index;
            this.index = 1 + j;
            if (j % ((long) this.skip) == 0) {
                try {
                    this.buffers.offer((Collection) io.reactivex.internal.functions.a.a(this.bufferSupplier.call(), "The bufferSupplier returned a null collection. Null values are generally not allowed in 2.x operators and sources."));
                } catch (Throwable th) {
                    this.buffers.clear();
                    this.upstream.dispose();
                    this.downstream.onError(th);
                    return;
                }
            }
            Iterator<U> it2 = this.buffers.iterator();
            while (it2.hasNext()) {
                U next = it2.next();
                next.add(t);
                if (this.count <= next.size()) {
                    it2.remove();
                    this.downstream.onNext(next);
                }
            }
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.buffers.clear();
            this.downstream.onError(th);
        }

        @Override // io.reactivex.v
        public void onComplete() {
            while (!this.buffers.isEmpty()) {
                this.downstream.onNext(this.buffers.poll());
            }
            this.downstream.onComplete();
        }
    }
}
