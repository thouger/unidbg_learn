package cn.missfresh.ad.a;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: MFADInterceptor */
public class c implements Interceptor {
    private g a;

    public Response intercept(Interceptor.Chain chain) throws IOException {
        AppMethodBeat.i(6090, false);
        Request request = chain.request();
        Response proceed = chain.proceed(request.newBuilder().method(request.method(), request.body()).url(request.url()).build());
        if (this.a != null) {
            Response build = proceed.newBuilder().body(new f(proceed.body(), this.a)).build();
            AppMethodBeat.o(6090);
            return build;
        }
        AppMethodBeat.o(6090);
        return proceed;
    }

    public void a(g gVar) {
        this.a = gVar;
    }
}
