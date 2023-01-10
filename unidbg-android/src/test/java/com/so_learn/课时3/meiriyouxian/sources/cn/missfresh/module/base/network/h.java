package cn.missfresh.module.base.network;

import com.bangcle.andjni.JniLib;
import io.reactivex.q;
import io.reactivex.t;
import io.reactivex.u;

/* compiled from: MFObservableTransformer */
public final class h<T> implements u<T, T> {
    private int a;

    public <T> h() {
        JniLib.cV(this, 161);
    }

    public <T> h(int i) {
        JniLib.cV(this, Integer.valueOf(i), 162);
    }

    @Override // io.reactivex.u
    public t<T> a(q<T> qVar) {
        return (t) JniLib.cL(this, qVar, 160);
    }
}
