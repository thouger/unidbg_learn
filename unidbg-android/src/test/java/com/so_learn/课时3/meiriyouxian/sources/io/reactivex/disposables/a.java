package io.reactivex.disposables;

import io.reactivex.exceptions.CompositeException;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.f;
import java.util.ArrayList;

/* compiled from: CompositeDisposable */
public final class a implements b, io.reactivex.internal.disposables.a {
    f<b> a;
    volatile boolean b;

    @Override // io.reactivex.disposables.b
    public void dispose() {
        if (!this.b) {
            synchronized (this) {
                if (!this.b) {
                    this.b = true;
                    f<b> fVar = this.a;
                    this.a = null;
                    a(fVar);
                }
            }
        }
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return this.b;
    }

    @Override // io.reactivex.internal.disposables.a
    public boolean a(b bVar) {
        io.reactivex.internal.functions.a.a(bVar, "disposable is null");
        if (!this.b) {
            synchronized (this) {
                if (!this.b) {
                    f<b> fVar = this.a;
                    if (fVar == null) {
                        fVar = new f<>();
                        this.a = fVar;
                    }
                    fVar.a((f<b>) bVar);
                    return true;
                }
            }
        }
        bVar.dispose();
        return false;
    }

    @Override // io.reactivex.internal.disposables.a
    public boolean b(b bVar) {
        if (!c(bVar)) {
            return false;
        }
        bVar.dispose();
        return true;
    }

    @Override // io.reactivex.internal.disposables.a
    public boolean c(b bVar) {
        io.reactivex.internal.functions.a.a(bVar, "disposables is null");
        if (this.b) {
            return false;
        }
        synchronized (this) {
            if (this.b) {
                return false;
            }
            f<b> fVar = this.a;
            if (fVar != null) {
                if (fVar.b(bVar)) {
                    return true;
                }
            }
            return false;
        }
    }

    public void a() {
        if (!this.b) {
            synchronized (this) {
                if (!this.b) {
                    f<b> fVar = this.a;
                    this.a = null;
                    a(fVar);
                }
            }
        }
    }

    public int b() {
        int i = 0;
        if (this.b) {
            return 0;
        }
        synchronized (this) {
            if (this.b) {
                return 0;
            }
            f<b> fVar = this.a;
            if (fVar != null) {
                i = fVar.c();
            }
            return i;
        }
    }

    /* access modifiers changed from: package-private */
    public void a(f<b> fVar) {
        if (fVar != null) {
            Object[] b = fVar.b();
            ArrayList arrayList = null;
            for (Object obj : b) {
                if (obj instanceof b) {
                    try {
                        ((b) obj).dispose();
                    } catch (Throwable th) {
                        io.reactivex.exceptions.a.b(th);
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(th);
                    }
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
