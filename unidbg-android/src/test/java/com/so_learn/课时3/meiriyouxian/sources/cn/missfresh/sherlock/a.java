package cn.missfresh.sherlock;

import android.os.Build;
import android.text.TextUtils;
import cn.missfresh.sherlock.bo.ConfigAesBO;
import cn.missfresh.sherlock.bo.ConfigBO;
import cn.missfresh.sherlock.bo.ConfigKeyBO;
import cn.missfresh.sherlock.config.Config;
import cn.missfresh.sherlock.tool.Utils;
import cn.missfresh.sherlock.tool.b;
import cn.missfresh.sherlock.tool.c;
import cn.missfresh.sherlock.tool.e;
import cn.missfresh.sherlock.tool.f;
import cn.missfresh.sherlock.tool.i;
import cn.missfresh.sherlock.tool.j;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/* compiled from: ApiHelper */
public class a {
    public static OkHttpClient a = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).readTimeout(5, TimeUnit.SECONDS).writeTimeout(5, TimeUnit.SECONDS).retryOnConnectionFailure(false).build();
    private static final MediaType b = MediaType.parse("application/json;charset=utf-8");

    public static Call a(String str, boolean z) {
        String str2;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            Request.Builder builder = new Request.Builder();
            builder.addHeader("request-id", Utils.e().replace("-", ""));
            builder.addHeader("encrypted", "1");
            String d = Utils.d();
            builder.addHeader("sign", b.a(i.a(d.getBytes("UTF-8"), i.a("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnGFRhWCoq6+yhfmxlS36Nm2HnE3/c1eFqHRlX9VPf270u9VcZLCR724kXe432sb8MrbHJzKsrzs7jGpoYlJ8j/p1sgLjKH7xjbczt4dpCqSsgRH/yDXQ9XcDfCDAaE5vFB1TAZHzEhRkqJyvll22X2mfCIVTkE5I/ReJMuGUJM2SF69/LZjQRRvUn1ZKNh84zo+BFxAXjixB6EZdfwDHc6XBsEI8+J4ed7JPeqpYN1uA0uBuLdwT0C7gY5/n+HeuaNTsI0akwfyfBHzX0Z+SFIA31V2Ek6RmSQTubkXRRZGbotytdzM4Tf9UzLzdmCP/GjcPUXe9+eUIcR1VUws2WQIDAQAB"))));
            if (z) {
                builder.addHeader("gzip", "1");
                str2 = e.a.toJson(new ConfigAesBO(cn.missfresh.sherlock.tool.a.a(f.a(str), d)));
            } else {
                str2 = e.a.toJson(new ConfigAesBO(cn.missfresh.sherlock.tool.a.a(str, d)));
            }
            builder.url(z ? "https://apm.missfresh.net/api/apm/collect" : "https://apm.missfresh.net/api/apm/configs").post(RequestBody.create(b, str2));
            return a.newCall(builder.build());
        } catch (Exception e) {
            j.b("ApiHelper", "create request call exception, message : " + e.getMessage());
            return null;
        }
    }

    public static void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                ConfigKeyBO configKeyBO = new ConfigKeyBO(str);
                configKeyBO.setAppVersion(Utils.a(e.e()));
                configKeyBO.setSdkVersion("7.0.7");
                configKeyBO.setDeviceMode(Utils.b());
                configKeyBO.setOsVersion(Build.VERSION.RELEASE);
                configKeyBO.setRegion(c.a(e.e()));
                Call a2 = a(e.a.toJson(configKeyBO), false);
                if (a2 != null) {
                    Response execute = a2.execute();
                    if (execute == null || !execute.isSuccessful() || execute.body() == null) {
                        a();
                        j.b("ApiHelper", "obtian config failure response");
                        return;
                    }
                    j.b("ApiHelper", "obtian config success");
                    ConfigBO configBO = (ConfigBO) e.a.fromJson(execute.body().string(), (Class<Object>) ConfigBO.class);
                    if (configBO == null || !configBO.isSuccessful()) {
                        a();
                        j.b("ApiHelper", "obtian config failure config BO");
                    } else if (configBO.getData() != null) {
                        Config.getInstance().update(configBO.getData().getItems());
                    } else {
                        a();
                        j.b("ApiHelper", "obtian config failure config data empty");
                    }
                }
            } catch (Exception e) {
                a();
                j.b("ApiHelper", "obtian config exception message : " + e.getMessage());
            }
        }
    }

    private static void a() {
        e.c();
    }
}
