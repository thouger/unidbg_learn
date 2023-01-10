package cn.missfresh.basiclib.net.d;

import cn.missfresh.basiclib.net.bean.ClientInfo;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: DynamicTimeoutInterceptor */
public final class a implements Interceptor {
    public Response intercept(Interceptor.Chain chain) throws IOException {
        AppMethodBeat.i(3781, false);
        Request request = chain.request();
        int a = a(request, ClientInfo.CONNECT_TIMEOUT);
        int a2 = a(request, ClientInfo.READ_TIMEOUT);
        int a3 = a(request, ClientInfo.WRITE_TIMEOUT);
        if (a > 0 || a2 > 0 || a3 > 0) {
            if (a <= 0) {
                a = chain.connectTimeoutMillis();
            }
            if (a2 <= 0) {
                a2 = chain.readTimeoutMillis();
            }
            if (a3 <= 0) {
                a3 = chain.writeTimeoutMillis();
            }
            chain = chain.withConnectTimeout(a, TimeUnit.MILLISECONDS).withReadTimeout(a2, TimeUnit.MILLISECONDS).withWriteTimeout(a3, TimeUnit.MILLISECONDS);
        }
        Response proceed = chain.proceed(request);
        AppMethodBeat.o(3781);
        return proceed;
    }

    private int a(Request request, String str) {
        AppMethodBeat.i(3786, false);
        if (request == null || b.a(str)) {
            AppMethodBeat.o(3786);
            return 0;
        }
        List headers = request.headers(str);
        if (headers == null || headers.size() <= 0) {
            AppMethodBeat.o(3786);
            return 0;
        } else if (headers.size() <= 1) {
            String str2 = (String) headers.get(0);
            if (b.a(str2)) {
                AppMethodBeat.o(3786);
                return 0;
            }
            int intValue = Integer.valueOf(str2).intValue();
            AppMethodBeat.o(3786);
            return intValue;
        } else {
            IllegalArgumentException illegalArgumentException = new IllegalArgumentException("\u8d85\u65f6\u65f6\u95f4\u4e0d\u80fd\u8bbe\u7f6e\u591a\u7ec4\u503c\uff01");
            AppMethodBeat.o(3786);
            throw illegalArgumentException;
        }
    }
}
