package cn.missfresh.module.base.network.a;

import com.alibaba.fastjson.JSONObject;
import com.bangcle.andjni.JniLib;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: SecurityInterceptor */
public class g implements Interceptor {
    public g() {
        JniLib.cV(this, 49);
    }

    private Request a(Request request) throws IOException {
        return (Request) JniLib.cL(this, request, 50);
    }

    private void a(JSONObject jSONObject) {
        JniLib.cV(this, jSONObject, 51);
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        return (Response) JniLib.cL(this, chain, 48);
    }
}
