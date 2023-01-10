package cn.missfresh.module.base.network;

import cn.missfresh.basiclib.net.a.a;
import com.bangcle.andjni.JniLib;

/* compiled from: MFResultCallback */
public abstract class j<T> implements a<T> {
    public j() {
        JniLib.cV(this, 168);
    }

    public abstract void a(int i, String str);

    @Override // cn.missfresh.basiclib.net.a.a
    public void onComplete() {
        JniLib.cV(this, 166);
    }

    @Override // cn.missfresh.basiclib.net.a.a
    public void onFail(int i, String str) {
        JniLib.cV(this, Integer.valueOf(i), str, 167);
    }
}
