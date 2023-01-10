package cn.missfresh.module.base.network.a;

import com.bangcle.andjni.JniLib;
import java.io.IOException;
import java.util.HashMap;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* compiled from: StatisticsPostBodyInterceptor */
public class h implements Interceptor {
    public h() {
        JniLib.cV(this, 53);
    }

    private static synchronized String a() {
        return (String) JniLib.cL(54);
    }

    private String a(RequestBody requestBody) {
        return (String) JniLib.cL(this, requestBody, 55);
    }

    private void a(Request request, Request.Builder builder) throws IOException {
        JniLib.cV(this, request, builder, 56);
    }

    private HashMap<String, String> b() {
        return (HashMap) JniLib.cL(this, 57);
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        return (Response) JniLib.cL(this, chain, 52);
    }
}
