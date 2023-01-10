package cn.missfresh.sherlock.okhttp;

import cn.missfresh.sherlock.config.Config;
import cn.missfresh.sherlock.tool.j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: SherlockGlobalInterceptor */
public class c implements Interceptor {
    public Response intercept(Interceptor.Chain chain) {
        Request request = chain.request();
        long nanoTime = System.nanoTime();
        j.a("SherlockGlobalIntercept", String.format("Sending request %s on connection %s%n method %s%n headers %s%n body %s%n tag %s", request.url(), chain.connection(), request.method(), request.headers(), request.body(), request.tag()));
        Response proceed = chain.proceed(request);
        double nanoTime2 = ((double) (System.nanoTime() - nanoTime)) / 1000000.0d;
        j.a("SherlockGlobalIntercept", String.format("Received response for %s in %.1fms%n%s", proceed.request().url(), Double.valueOf(nanoTime2), proceed.headers()));
        if (Config.getInstance().enableHttpSwitch()) {
            a.a().a(proceed, (long) nanoTime2);
        }
        return proceed;
    }
}
