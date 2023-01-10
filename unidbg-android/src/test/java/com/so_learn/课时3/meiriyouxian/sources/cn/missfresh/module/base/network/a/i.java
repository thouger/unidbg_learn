package cn.missfresh.module.base.network.a;

import com.bangcle.andjni.JniLib;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: TokenIntercepter */
public class i implements Interceptor {
    String a;

    public i() {
        JniLib.cV(this, 59);
    }

    @Deprecated
    private void a(String str) {
        JniLib.cV(this, str, 60);
    }

    private void a(Response response) throws IOException {
        JniLib.cV(this, response, 61);
    }

    private boolean a(ResponseBody responseBody) throws IOException {
        return JniLib.cZ(this, responseBody, 62);
    }

    private void b(String str) {
        JniLib.cV(this, str, 63);
    }

    private void c(String str) {
        JniLib.cV(this, str, 64);
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        return (Response) JniLib.cL(this, chain, 58);
    }
}
