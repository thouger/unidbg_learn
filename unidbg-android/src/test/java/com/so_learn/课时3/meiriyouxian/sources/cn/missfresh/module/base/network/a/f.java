package cn.missfresh.module.base.network.a;

import android.text.TextUtils;
import cn.missfresh.basiclib.net.a;
import cn.missfresh.basiclib.net.bean.ApiServiceInfo;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSONObject;
import com.bangcle.andjni.JniLib;
import java.io.IOException;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: RequestIDInterceptor */
public class f implements Interceptor {
    public f() {
        JniLib.cV(this, 47);
    }

    public Response intercept(Interceptor.Chain chain) throws IOException {
        AppMethodBeat.i(15935, false);
        Request request = chain.request();
        Response proceed = chain.proceed(request);
        if (proceed.code() == 200) {
            String header = request.header("request-id");
            HttpUrl url = request.url();
            String path = url.url().getPath();
            if (!TextUtils.isEmpty(header)) {
                ApiServiceInfo a = a.a().a(path);
                if (a != null) {
                    boolean z = true;
                    boolean z2 = (a.getType() & 1) != 0;
                    if ((a.getType() & 2) == 0) {
                        z = false;
                    }
                    String str = "";
                    String startNum = a.getStartNum();
                    if ("GET".equals(request.method())) {
                        if (z) {
                            String queryParameter = url.queryParameter(a.getFrom());
                            path = cn.missfresh.datastatistics.a.b(queryParameter) + path;
                        }
                        if (z2) {
                            str = url.queryParameter(a.getPage());
                        }
                    } else if ("POST".equals(request.method())) {
                        if (request.body() instanceof FormBody) {
                            FormBody body = request.body();
                            for (int i = 0; i < body.size(); i++) {
                                if (z && body.name(i).equals(a.getFrom())) {
                                    path = cn.missfresh.datastatistics.a.b(body.value(i));
                                }
                                if (z2 && body.name(i).equals(a.getPage())) {
                                    str = body.value(i);
                                }
                            }
                        } else {
                            JSONObject jSONObject = d.a().d(request.body()).getJSONObject("param");
                            if (jSONObject != null) {
                                if (z) {
                                    Object obj = jSONObject.get(a.getFrom());
                                    path = cn.missfresh.datastatistics.a.b(obj) + path;
                                }
                                if (z2) {
                                    str = jSONObject.getString(a.getPage());
                                }
                            }
                        }
                    }
                    if (z2) {
                        if (str != null && str.equals(startNum)) {
                            StatisticsManager.a(path);
                        }
                        StatisticsManager.b(path, header);
                    } else {
                        StatisticsManager.a(path, header);
                    }
                    AppMethodBeat.o(15935);
                    return proceed;
                }
                StatisticsManager.a(path, header);
            }
        }
        AppMethodBeat.o(15935);
        return proceed;
    }
}
