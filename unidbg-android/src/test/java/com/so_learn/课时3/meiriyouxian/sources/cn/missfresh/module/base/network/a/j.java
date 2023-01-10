package cn.missfresh.module.base.network.a;

import com.bangcle.andjni.JniLib;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

/* compiled from: WsgInterceptor */
public class j implements Interceptor {
    public j() {
        JniLib.cV(this, 66);
    }

    private void a(String str, String str2, String str3, String str4) {
        JniLib.cV(this, str, str2, str3, str4, 67);
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        return (Response) JniLib.cL(this, chain, 65);
    }
}
