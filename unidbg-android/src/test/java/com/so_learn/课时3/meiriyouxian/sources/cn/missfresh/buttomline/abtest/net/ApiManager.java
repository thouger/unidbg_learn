package cn.missfresh.buttomline.abtest.net;

import cn.missfresh.basiclib.net.a;
import cn.missfresh.basiclib.tool.SystemUtils;
import cn.missfresh.basiclib.tool.b;
import cn.missfresh.basiclib.tool.c;
import cn.missfresh.basiclib.tool.d;
import cn.missfresh.buttomline.abtest.ABTest;
import cn.missfresh.buttomline.abtest.log.Logger;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import retrofit2.adapter.rxjava2.g;
import retrofit2.r;

public class ApiManager {
    private static String API_URL = "https://abtest.missfresh.net/";
    private static final String PUBLIC_RSA = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjnE7pBMHnDDqmrp39z+0TyI2oQHpbZJSEOW5DC8WA9MA3MNCzDeIsVwlAI2c6jxwIQAUDgjY66SugsBfhv/Gdi4YL7Tuky7wY5kP3JRUs1YWe1mT2sYe/q/ev7284zhxxEUMnWMh3p6fSWSn+BKIOHfl9zJpwF5jxxErAQUPAzA+aZSMQ9FfDQexzSlXG9UscQ1J3bgGMLgFcKiYNPdaGf9vGj/eNqdwZI61mVwSevDo/ciqU4JD/JjRLLPGI17b/5GChalF367Y2wXsjAU1Q7dOBXslf1/Fm01Ap04I8GIngsytpkGzcgTtBtYgGSxeAsjviOTp5QIfMOYmPbBrwQIDAQAB";
    private static AbtestApi api;

    public static AbtestApi geApi() {
        AppMethodBeat.i(7462, false);
        if (api == null) {
            synchronized (AbtestApi.class) {
                try {
                    if (api == null) {
                        r.a a = a.a().c().a(API_URL);
                        a.a(g.a());
                        a.a(cn.missfresh.lib.b.a.a());
                        api = (AbtestApi) a.a().a(AbtestApi.class);
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(7462);
                    throw th;
                }
            }
        }
        AbtestApi abtestApi = api;
        AppMethodBeat.o(7462);
        return abtestApi;
    }

    public static void changeApi(String str) {
        API_URL = str;
        api = null;
    }

    public static void getAbtestResult(AbtestRequestParam abtestRequestParam, BaseAbtestApiResult baseAbtestApiResult) {
        String str;
        String str2;
        AppMethodBeat.i(7467, false);
        String str3 = "";
        String replace = SystemUtils.d().replace("-", str3);
        if (!getBoolean(RequestParams.getParams().get(ABTest.KEY_ENCRYPT), true)) {
            str2 = "0";
            str = str3;
        } else {
            try {
                String c = SystemUtils.c();
                str3 = b.a(d.a(c.getBytes("UTF-8"), d.a(PUBLIC_RSA)));
                abtestRequestParam.setObj(cn.missfresh.basiclib.tool.a.a(c.a.toJson(abtestRequestParam), c));
            } catch (Exception e) {
                Logger.e(e);
            }
            str = str3;
            str2 = "1";
        }
        geApi().getAbtestResult(replace, str2, str, "0", abtestRequestParam).a(cn.missfresh.basiclib.net.e.a.a).subscribe(new cn.missfresh.basiclib.net.c(baseAbtestApiResult));
        AppMethodBeat.o(7467);
    }

    public static boolean getBoolean(String str, boolean z) {
        AppMethodBeat.i(7469, false);
        try {
            z = Boolean.valueOf(str).booleanValue();
        } catch (Exception e) {
            Logger.e(e);
        }
        AppMethodBeat.o(7469);
        return z;
    }
}
