package cn.missfresh.module.base.network;

import cn.missfresh.module.mvp.a.a;
import com.bangcle.andjni.JniLib;
import io.reactivex.disposables.b;
import io.reactivex.v;

/* compiled from: MFObserver */
public abstract class i<T> implements v<T> {
    private a a;

    public <T> i(a aVar) {
        JniLib.cV(this, aVar, 165);
    }

    /* access modifiers changed from: protected */
    public abstract void a(int i, String str);

    @Override // io.reactivex.v
    public void onError(Throwable th) {
        JniLib.cV(this, th, 163);
    }

    @Override // io.reactivex.v
    public void onSubscribe(b bVar) {
        JniLib.cV(this, bVar, 164);
    }
}
