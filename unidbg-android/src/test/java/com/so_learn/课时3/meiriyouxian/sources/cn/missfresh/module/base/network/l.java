package cn.missfresh.module.base.network;

import cn.missfresh.module.base.network.c;
import com.bangcle.andjni.JniLib;
import okhttp3.Request;

/* compiled from: MissFreshNetProxy */
public class l implements c.b {
    c.b a;
    Request b;
    a c;

    /* compiled from: MissFreshNetProxy */
    public interface a {
        void a(String str);

        void b(String str);
    }

    @Override // cn.missfresh.module.base.network.c.b
    public void a() {
        JniLib.cV(this, 176);
    }

    @Override // cn.missfresh.module.base.network.c.b
    public void a(int i) {
        JniLib.cV(this, Integer.valueOf(i), 177);
    }

    @Override // cn.missfresh.module.base.network.c.b
    public void a(String str) {
        JniLib.cV(this, str, 178);
    }

    @Override // cn.missfresh.module.base.network.c.b
    public void a(Request request, Exception exc) {
        JniLib.cV(this, request, exc, 179);
    }

    @Override // cn.missfresh.module.base.network.c.b
    public void b() {
        JniLib.cV(this, 180);
    }

    @Override // cn.missfresh.module.base.network.c.b
    public void b(String str) {
    }
}
