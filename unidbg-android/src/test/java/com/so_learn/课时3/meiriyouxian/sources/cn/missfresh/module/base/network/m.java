package cn.missfresh.module.base.network;

import cn.missfresh.module.base.network.c;
import com.bangcle.andjni.JniLib;
import okhttp3.Request;

/* compiled from: MissFreshRequestCallBack */
public class m implements c.b {
    public m() {
        JniLib.cV(this, 173);
    }

    public static a c(String str) {
        return (a) JniLib.cL(str, 174);
    }

    @Override // cn.missfresh.module.base.network.c.b
    public void a() {
        JniLib.cV(this, 169);
    }

    @Override // cn.missfresh.module.base.network.c.b
    public void a(int i) {
    }

    @Override // cn.missfresh.module.base.network.c.b
    public void a(String str) {
    }

    @Override // cn.missfresh.module.base.network.c.b
    public void a(Request request, Exception exc) {
        JniLib.cV(this, request, exc, 170);
    }

    @Override // cn.missfresh.module.base.network.c.b
    public void b() {
        JniLib.cV(this, 171);
    }

    @Override // cn.missfresh.module.base.network.c.b
    public void b(String str) {
        JniLib.cV(this, str, 172);
    }
}
