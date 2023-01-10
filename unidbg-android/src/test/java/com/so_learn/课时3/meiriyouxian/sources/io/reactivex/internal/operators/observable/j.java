package io.reactivex.internal.operators.observable;

import io.reactivex.internal.observers.b;
import io.reactivex.q;
import io.reactivex.v;

/* compiled from: ObservableFromArray */
public final class j<T> extends q<T> {
    final T[] a;

    public j(T[] tArr) {
        this.a = tArr;
    }

    @Override // io.reactivex.q
    public void a(v<? super T> vVar) {
        a aVar = new a(vVar, this.a);
        vVar.onSubscribe(aVar);
        if (!aVar.d) {
            aVar.a();
        }
    }

    /* compiled from: ObservableFromArray */
    static final class a<T> extends b<T> {
        final v<? super T> a;
        final T[] b;
        int c;
        boolean d;
        volatile boolean e;

        a(v<? super T> vVar, T[] tArr) {
            this.a = vVar;
            this.b = tArr;
        }

        @Override // io.reactivex.internal.a.d
        public int requestFusion(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            this.d = true;
            return 1;
        }

        @Override // io.reactivex.internal.a.h
        public T poll() {
            int i = this.c;
            T[] tArr = this.b;
            if (i == tArr.length) {
                return null;
            }
            this.c = i + 1;
            return (T) io.reactivex.internal.functions.a.a((Object) tArr[i], "The array element is null");
        }

        @Override // io.reactivex.internal.a.h
        public boolean isEmpty() {
            return this.c == this.b.length;
        }

        @Override // io.reactivex.internal.a.h
        public void clear() {
            this.c = this.b.length;
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            this.e = true;
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.e;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            T[] tArr = this.b;
            int length = tArr.length;
            for (int i = 0; i < length && !isDisposed(); i++) {
                T t = tArr[i];
                if (t == null) {
                    v<? super T> vVar = this.a;
                    vVar.onError(new NullPointerException("The element at index " + i + " is null"));
                    return;
                }
                this.a.onNext(t);
            }
            if (!isDisposed()) {
                this.a.onComplete();
            }
        }
    }
}
