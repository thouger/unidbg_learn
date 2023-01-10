package cn.missfresh.module.base.common.config.b;

import cn.missfresh.module.base.utils.r;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: UrlConvertInterceptro */
public class a implements Interceptor {
    public Response intercept(Interceptor.Chain chain) throws IOException {
        int lastIndexOf;
        AppMethodBeat.i(11219, false);
        Request request = chain.request();
        String httpUrl = request.url().toString();
        if (!a(httpUrl) && (lastIndexOf = httpUrl.lastIndexOf(".")) > -1 && httpUrl.length() - lastIndexOf < 5) {
            httpUrl = r.b(httpUrl);
        }
        Response proceed = chain.proceed(request.newBuilder().url(httpUrl).build());
        AppMethodBeat.o(11219);
        return proceed;
    }

    private boolean a(String str) {
        AppMethodBeat.i(11221, false);
        if (b.a(str)) {
            AppMethodBeat.o(11221);
            return false;
        }
        boolean endsWith = str.toUpperCase().endsWith("GIF");
        AppMethodBeat.o(11221);
        return endsWith;
    }
}
