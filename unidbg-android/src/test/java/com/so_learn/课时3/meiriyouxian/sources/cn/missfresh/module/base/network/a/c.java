package cn.missfresh.module.base.network.a;

import com.bangcle.andjni.JniLib;
import java.io.IOException;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: HeaderInterceptor */
public class c implements Interceptor {
    public c() {
        JniLib.cV(this, 25);
    }

    private String a() {
        return (String) JniLib.cL(this, 26);
    }

    private HttpUrl a(Request request) {
        return (HttpUrl) JniLib.cL(this, request, 27);
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        return (Response) JniLib.cL(this, chain, 24);
    }
}
