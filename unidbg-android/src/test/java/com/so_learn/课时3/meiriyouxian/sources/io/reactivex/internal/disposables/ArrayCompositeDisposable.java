package io.reactivex.internal.disposables;

import io.reactivex.disposables.b;
import java.util.concurrent.atomic.AtomicReferenceArray;

public final class ArrayCompositeDisposable extends AtomicReferenceArray<b> implements b {
    private static final long serialVersionUID = 2746389416410565408L;

    public ArrayCompositeDisposable(int i) {
        super(i);
    }

    public boolean setResource(int i, b bVar) {
        b bVar2;
        do {
            bVar2 = get(i);
            if (bVar2 == DisposableHelper.DISPOSED) {
                bVar.dispose();
                return false;
            }
        } while (!compareAndSet(i, bVar2, bVar));
        if (bVar2 == null) {
            return true;
        }
        bVar2.dispose();
        return true;
    }

    public b replaceResource(int i, b bVar) {
        b bVar2;
        do {
            bVar2 = get(i);
            if (bVar2 == DisposableHelper.DISPOSED) {
                bVar.dispose();
                return null;
            }
        } while (!compareAndSet(i, bVar2, bVar));
        return bVar2;
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        b andSet;
        if (get(0) != DisposableHelper.DISPOSED) {
            int length = length();
            for (int i = 0; i < length; i++) {
                if (!(get(i) == DisposableHelper.DISPOSED || (andSet = getAndSet(i, DisposableHelper.DISPOSED)) == DisposableHelper.DISPOSED || andSet == null)) {
                    andSet.dispose();
                }
            }
        }
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return get(0) == DisposableHelper.DISPOSED;
    }
}
