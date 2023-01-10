package io.reactivex.internal.disposables;

import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/* compiled from: ListCompositeDisposable */
public final class b implements io.reactivex.disposables.b, a {
    List<io.reactivex.disposables.b> a;
    volatile boolean b;

    @Override // io.reactivex.disposables.b
    public void dispose() {
        if (!this.b) {
            synchronized (this) {
                if (!this.b) {
                    this.b = true;
                    List<io.reactivex.disposables.b> list = this.a;
                    this.a = null;
                    a(list);
                }
            }
        }
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return this.b;
    }

    @Override // io.reactivex.internal.disposables.a
    public boolean a(io.reactivex.disposables.b bVar) {
        a.a(bVar, "d is null");
        if (!this.b) {
            synchronized (this) {
                if (!this.b) {
                    List list = this.a;
                    if (list == null) {
                        list = new LinkedList();
                        this.a = list;
                    }
                    list.add(bVar);
                    return true;
                }
            }
        }
        bVar.dispose();
        return false;
    }

    @Override // io.reactivex.internal.disposables.a
    public boolean b(io.reactivex.disposables.b bVar) {
        if (!c(bVar)) {
            return false;
        }
        bVar.dispose();
        return true;
    }

    @Override // io.reactivex.internal.disposables.a
    public boolean c(io.reactivex.disposables.b bVar) {
        a.a(bVar, "Disposable item is null");
        if (this.b) {
            return false;
        }
        synchronized (this) {
            if (this.b) {
                return false;
            }
            List<io.reactivex.disposables.b> list = this.a;
            if (list != null) {
                if (list.remove(bVar)) {
                    return true;
                }
            }
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(List<io.reactivex.disposables.b> list) {
        if (list != null) {
            ArrayList arrayList = null;
            for (io.reactivex.disposables.b bVar : list) {
                try {
                    bVar.dispose();
                } catch (Throwable th) {
                    io.reactivex.exceptions.a.b(th);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(th);
                }
            }
            if (arrayList == null) {
                return;
            }
            if (arrayList.size() == 1) {
                throw ExceptionHelper.a((Throwable) arrayList.get(0));
            }
            throw new CompositeException(arrayList);
        }
    }
}
