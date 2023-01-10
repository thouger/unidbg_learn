package io.reactivex.internal.operators.flowable;

import io.reactivex.e.a;
import io.reactivex.internal.subscribers.SinglePostCompleteSubscriber;
import io.reactivex.j;
import io.reactivex.p;
import org.a.c;

public final class FlowableMaterialize<T> extends a<T, p<T>> {
    /* access modifiers changed from: protected */
    @Override // io.reactivex.g
    public void a(c<? super p<T>> cVar) {
        this.b.a((j) new MaterializeSubscriber(cVar));
    }

    static final class MaterializeSubscriber<T> extends SinglePostCompleteSubscriber<T, p<T>> {
        private static final long serialVersionUID = -3740826063558713822L;

        MaterializeSubscriber(c<? super p<T>> cVar) {
            super(cVar);
        }

        public void onNext(T t) {
            this.produced++;
            this.downstream.onNext(p.a((Object) t));
        }

        public void onError(Throwable th) {
            complete(p.a(th));
        }

        public void onComplete() {
            complete(p.c());
        }

        /* access modifiers changed from: protected */
        public void onDrop(p<T> pVar) {
            if (pVar.a()) {
                a.a(pVar.b());
            }
        }
    }
}
