package io.reactivex.internal.operators.observable;

import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.q;
import io.reactivex.v;

public final class ObservableRange extends q<Integer> {
    private final int a;
    private final long b;

    public ObservableRange(int i, int i2) {
        this.a = i;
        this.b = ((long) i) + ((long) i2);
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.q
    public void a(v<? super Integer> vVar) {
        RangeDisposable rangeDisposable = new RangeDisposable(vVar, (long) this.a, this.b);
        vVar.onSubscribe(rangeDisposable);
        rangeDisposable.run();
    }

    static final class RangeDisposable extends BasicIntQueueDisposable<Integer> {
        private static final long serialVersionUID = 396518478098735504L;
        final v<? super Integer> downstream;
        final long end;
        boolean fused;
        long index;

        RangeDisposable(v<? super Integer> vVar, long j, long j2) {
            this.downstream = vVar;
            this.index = j;
            this.end = j2;
        }

        /* access modifiers changed from: package-private */
        public void run() {
            if (!this.fused) {
                v<? super Integer> vVar = this.downstream;
                long j = this.end;
                for (long j2 = this.index; j2 != j && get() == 0; j2++) {
                    vVar.onNext(Integer.valueOf((int) j2));
                }
                if (get() == 0) {
                    lazySet(1);
                    vVar.onComplete();
                }
            }
        }

        @Override // io.reactivex.internal.a.h
        public Integer poll() throws Exception {
            long j = this.index;
            if (j != this.end) {
                this.index = 1 + j;
                return Integer.valueOf((int) j);
            }
            lazySet(1);
            return null;
        }

        @Override // io.reactivex.internal.a.h
        public boolean isEmpty() {
            return this.index == this.end;
        }

        @Override // io.reactivex.internal.a.h
        public void clear() {
            this.index = this.end;
            lazySet(1);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            set(1);
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() != 0;
        }

        @Override // io.reactivex.internal.a.d
        public int requestFusion(int i) {
            if ((i & 1) == 0) {
                return 0;
            }
            this.fused = true;
            return 1;
        }
    }
}
