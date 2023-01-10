package io.reactivex.internal.operators.flowable;

import io.reactivex.disposables.b;
import io.reactivex.g;
import io.reactivex.q;
import io.reactivex.v;
import org.a.c;

/* compiled from: FlowableFromObservable */
public final class d<T> extends g<T> {
    private final q<T> b;

    public d(q<T> qVar) {
        this.b = qVar;
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super T> cVar) {
        this.b.subscribe(new a(cVar));
    }

    /* compiled from: FlowableFromObservable */
    static final class a<T> implements v<T>, org.a.d {
        final c<? super T> a;
        b b;

        public void request(long j) {
        }

        a(c<? super T> cVar) {
            this.a = cVar;
        }

        @Override // io.reactivex.v
        public void onComplete() {
            this.a.onComplete();
        }

        @Override // io.reactivex.v
        public void onError(Throwable th) {
            this.a.onError(th);
        }

        @Override // io.reactivex.v
        public void onNext(T t) {
            this.a.onNext(t);
        }

        @Override // io.reactivex.v
        public void onSubscribe(b bVar) {
            this.b = bVar;
            this.a.onSubscribe(this);
        }

        public void cancel() {
            this.b.dispose();
        }
    }
}
