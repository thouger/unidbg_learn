package com.sobot.chat.core.http;

import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Response;

/* compiled from: SobotInternetPermissionExceptionInterceptor */
public class b implements Interceptor {
    public Response intercept(Interceptor.Chain chain) throws IOException {
        try {
            return chain.proceed(chain.request());
        } catch (Throwable th) {
            if (th instanceof IOException) {
                throw th;
            }
            throw new IOException(th);
        }
    }
}
