package cn.missfresh.risk.c;

import cn.missfresh.risk.api.RiskApi;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.support.retrofit.Retrofit2ConverterFactory;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import okhttp3.Protocol;
import retrofit2.adapter.rxjava2.g;
import retrofit2.r;

/* compiled from: RiskRetrofitManager */
public class a {
    private OkHttpClient a;
    private r b;
    private RiskApi c;

    private a() {
        AppMethodBeat.i(2378, false);
        c();
        d();
        e();
        AppMethodBeat.o(2378);
    }

    private void c() {
        int i = 2384;
        AppMethodBeat.i(2384, false);
        if (this.a == null) {
            synchronized (a.class) {
                try {
                    if (this.a == null) {
                        this.a = new OkHttpClient.Builder().retryOnConnectionFailure(false).protocols(Collections.singletonList(Protocol.HTTP_1_1)).connectTimeout(5, TimeUnit.SECONDS).readTimeout(5, TimeUnit.SECONDS).writeTimeout(5, TimeUnit.SECONDS).connectionSpecs(Arrays.asList(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS)).build();
                    }
                } finally {
                    AppMethodBeat.o(i);
                }
            }
        }
    }

    private void d() {
        AppMethodBeat.i(2387, false);
        this.b = new r.a().a("https://as-vip.missfresh.cn").a(this.a).a(g.a()).a(new Retrofit2ConverterFactory()).a();
        AppMethodBeat.o(2387);
    }

    private void e() {
        AppMethodBeat.i(2391, false);
        this.c = (RiskApi) this.b.a(RiskApi.class);
        AppMethodBeat.o(2391);
    }

    public RiskApi a() {
        return this.c;
    }

    /* compiled from: RiskRetrofitManager */
    /* access modifiers changed from: private */
    /* renamed from: cn.missfresh.risk.c.a$a  reason: collision with other inner class name */
    public static class C0037a {
        private static final a a = new a();

        static {
            AppMethodBeat.i(2368, false);
            AppMethodBeat.o(2368);
        }
    }

    public static a b() {
        AppMethodBeat.i(2398, false);
        a aVar = C0037a.a;
        AppMethodBeat.o(2398);
        return aVar;
    }
}
