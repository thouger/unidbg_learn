package cn.missfresh.ui.skeleton.auto;

import androidx.core.util.Pools;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class ViewPool implements Pools.Pool<a> {
    private final Object[] a;
    private int b;

    @Override // androidx.core.util.Pools.Pool
    public /* synthetic */ Object acquire() {
        AppMethodBeat.i(1885, false);
        a a = a();
        AppMethodBeat.o(1885);
        return a;
    }

    @Override // androidx.core.util.Pools.Pool
    public /* synthetic */ boolean release(Object obj) {
        AppMethodBeat.i(1882, false);
        boolean a = a((a) obj);
        AppMethodBeat.o(1882);
        return a;
    }

    public ViewPool(int i) {
        AppMethodBeat.i(1871, false);
        if (i > 0) {
            this.a = new Object[i];
            AppMethodBeat.o(1871);
            return;
        }
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException("The max pool size must be > 0");
        AppMethodBeat.o(1871);
        throw illegalArgumentException;
    }

    public a a() {
        AppMethodBeat.i(1875, false);
        int i = this.b;
        if (i > 0) {
            int i2 = i - 1;
            Object[] objArr = this.a;
            a aVar = (a) objArr[i2];
            objArr[i2] = null;
            this.b = i - 1;
            AppMethodBeat.o(1875);
            return aVar;
        }
        a aVar2 = new a();
        AppMethodBeat.o(1875);
        return aVar2;
    }

    public boolean a(a aVar) {
        AppMethodBeat.i(1878, false);
        if (!b(aVar)) {
            int i = this.b;
            Object[] objArr = this.a;
            if (i < objArr.length) {
                objArr[i] = aVar;
                this.b = i + 1;
                AppMethodBeat.o(1878);
                return true;
            }
            AppMethodBeat.o(1878);
            return false;
        }
        IllegalStateException illegalStateException = new IllegalStateException("Already in the pool!");
        AppMethodBeat.o(1878);
        throw illegalStateException;
    }

    private boolean b(a aVar) {
        for (int i = 0; i < this.b; i++) {
            if (this.a[i] == aVar) {
                return true;
            }
        }
        return false;
    }
}
