package io.reactivex.subjects;

import io.reactivex.a;
import io.reactivex.c;
import io.reactivex.disposables.b;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class CompletableSubject extends a implements c {
    static final CompletableDisposable[] b = new CompletableDisposable[0];
    static final CompletableDisposable[] c = new CompletableDisposable[0];
    final AtomicReference<CompletableDisposable[]> a = new AtomicReference<>(b);
    final AtomicBoolean d = new AtomicBoolean();
    Throwable e;

    CompletableSubject() {
    }

    @Override // io.reactivex.c
    public void onSubscribe(b bVar) {
        if (this.a.get() == c) {
            bVar.dispose();
        }
    }

    @Override // io.reactivex.c
    public void onError(Throwable th) {
        io.reactivex.internal.functions.a.a(th, "onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        if (this.d.compareAndSet(false, true)) {
            this.e = th;
            for (CompletableDisposable completableDisposable : this.a.getAndSet(c)) {
                completableDisposable.downstream.onError(th);
            }
            return;
        }
        io.reactivex.e.a.a(th);
    }

    @Override // io.reactivex.c
    public void onComplete() {
        if (this.d.compareAndSet(false, true)) {
            for (CompletableDisposable completableDisposable : this.a.getAndSet(c)) {
                completableDisposable.downstream.onComplete();
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // io.reactivex.a
    public void b(c cVar) {
        CompletableDisposable completableDisposable = new CompletableDisposable(cVar, this);
        cVar.onSubscribe(completableDisposable);
        if (!a(completableDisposable)) {
            Throwable th = this.e;
            if (th != null) {
                cVar.onError(th);
            } else {
                cVar.onComplete();
            }
        } else if (completableDisposable.isDisposed()) {
            b(completableDisposable);
        }
    }

    /* access modifiers changed from: package-private */
    public boolean a(CompletableDisposable completableDisposable) {
        CompletableDisposable[] completableDisposableArr;
        CompletableDisposable[] completableDisposableArr2;
        do {
            completableDisposableArr = this.a.get();
            if (completableDisposableArr == c) {
                return false;
            }
            int length = completableDisposableArr.length;
            completableDisposableArr2 = new CompletableDisposable[(length + 1)];
            System.arraycopy(completableDisposableArr, 0, completableDisposableArr2, 0, length);
            completableDisposableArr2[length] = completableDisposable;
        } while (!this.a.compareAndSet(completableDisposableArr, completableDisposableArr2));
        return true;
    }

    /* access modifiers changed from: package-private */
    public void b(CompletableDisposable completableDisposable) {
        CompletableDisposable[] completableDisposableArr;
        CompletableDisposable[] completableDisposableArr2;
        do {
            completableDisposableArr = this.a.get();
            int length = completableDisposableArr.length;
            if (length != 0) {
                int i = -1;
                int i2 = 0;
                while (true) {
                    if (i2 >= length) {
                        break;
                    } else if (completableDisposableArr[i2] == completableDisposable) {
                        i = i2;
                        break;
                    } else {
                        i2++;
                    }
                }
                if (i >= 0) {
                    if (length == 1) {
                        completableDisposableArr2 = b;
                    } else {
                        CompletableDisposable[] completableDisposableArr3 = new CompletableDisposable[(length - 1)];
                        System.arraycopy(completableDisposableArr, 0, completableDisposableArr3, 0, i);
                        System.arraycopy(completableDisposableArr, i + 1, completableDisposableArr3, i, (length - i) - 1);
                        completableDisposableArr2 = completableDisposableArr3;
                    }
                } else {
                    return;
                }
            } else {
                return;
            }
        } while (!this.a.compareAndSet(completableDisposableArr, completableDisposableArr2));
    }

    /* access modifiers changed from: package-private */
    public static final class CompletableDisposable extends AtomicReference<CompletableSubject> implements b {
        private static final long serialVersionUID = -7650903191002190468L;
        final c downstream;

        CompletableDisposable(c cVar, CompletableSubject completableSubject) {
            this.downstream = cVar;
            lazySet(completableSubject);
        }

        @Override // io.reactivex.disposables.b
        public void dispose() {
            CompletableSubject andSet = getAndSet(null);
            if (andSet != null) {
                andSet.b(this);
            }
        }

        @Override // io.reactivex.disposables.b
        public boolean isDisposed() {
            return get() == null;
        }
    }
}
