package cn.missfresh.linksdk.b;

import android.util.Log;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* compiled from: MFInterceptor */
public class c implements Interceptor {
    private String a = getClass().getSimpleName();

    public c() {
        AppMethodBeat.i(13871, false);
        AppMethodBeat.o(13871);
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        AppMethodBeat.i(13877, false);
        Request request = chain.request();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("-----------------------------------------------------------------------------");
        stringBuffer.append("\u8bf7\u6c42\u65b9\u5f0f\uff1a" + request.method());
        stringBuffer.append(request.url());
        stringBuffer.append(request.body() == null ? "" : request.body().toString());
        stringBuffer.append("\u8bf7\u6c42\u65b9\u5f0f Ending...");
        Response proceed = chain.proceed(request.newBuilder().method(request.method(), request.body()).url(request.url()).build());
        ResponseBody peekBody = proceed.peekBody(1048576);
        stringBuffer.append("\u8bf7\u6c42\u8fd4\u56de\uff1a");
        stringBuffer.append(peekBody.string());
        stringBuffer.append("------------------------------------------------------------------------------");
        Log.d(this.a, stringBuffer.toString());
        AppMethodBeat.o(13877);
        return proceed;
    }
}
