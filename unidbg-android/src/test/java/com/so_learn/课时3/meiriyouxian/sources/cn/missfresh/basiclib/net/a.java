package cn.missfresh.basiclib.net;

import android.util.Log;
import cn.missfresh.basiclib.net.b.c.b;
import cn.missfresh.basiclib.net.bean.ApiServiceInfo;
import cn.missfresh.datastatistics.service.ApiService;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import okhttp3.ConnectionSpec;
import okhttp3.OkHttpClient;
import retrofit2.r;

/* compiled from: HttpManager */
public class a {
    private static r a;
    private Map<String, ApiServiceInfo> b;
    private cn.missfresh.basiclib.net.b.c.a c;

    public static a a() {
        AppMethodBeat.i(3273, false);
        a aVar = C0016a.a;
        AppMethodBeat.o(3273);
        return aVar;
    }

    /* compiled from: HttpManager */
    /* renamed from: cn.missfresh.basiclib.net.a$a  reason: collision with other inner class name */
    private static final class C0016a {
        private static final a a = new a();

        static {
            AppMethodBeat.i(3269, false);
            AppMethodBeat.o(3269);
        }
    }

    private a() {
        AppMethodBeat.i(3276, false);
        this.b = new HashMap();
        this.c = null;
        OkHttpClient.Builder connectionSpecs = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.SECONDS).readTimeout(5, TimeUnit.SECONDS).writeTimeout(5, TimeUnit.SECONDS).retryOnConnectionFailure(true).connectionSpecs(Arrays.asList(ConnectionSpec.CLEARTEXT, ConnectionSpec.MODERN_TLS));
        b e = e();
        if (e != null) {
            e.a(connectionSpecs);
        }
        a = new r.a().a(connectionSpecs.build()).a("http://localhost/").a();
        AppMethodBeat.o(3276);
    }

    public void b() {
        ApiService.init();
    }

    public void a(ApiServiceInfo apiServiceInfo) {
        AppMethodBeat.i(3281, false);
        this.b.put(apiServiceInfo.getPath(), apiServiceInfo);
        AppMethodBeat.o(3281);
    }

    public ApiServiceInfo a(String str) {
        AppMethodBeat.i(3284, false);
        ApiServiceInfo apiServiceInfo = this.b.get(str);
        AppMethodBeat.o(3284);
        return apiServiceInfo;
    }

    public r.a c() {
        AppMethodBeat.i(3287, false);
        r.a a2 = a.a();
        AppMethodBeat.o(3287);
        return a2;
    }

    private void a(String str, Exception exc) {
        AppMethodBeat.i(3289, false);
        IllegalStateException illegalStateException = new IllegalStateException(str + " is implemented incorrectly. If you've manually implemented this class, remove your implementation. The Annotation processor will generate a correct implementation.", exc);
        AppMethodBeat.o(3289);
        throw illegalStateException;
    }

    private b e() {
        b bVar;
        AppMethodBeat.i(3292, false);
        try {
            bVar = (b) Class.forName("cn.missfresh.a.a.a").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (ClassNotFoundException unused) {
            if (Log.isLoggable("HttpManager", 5)) {
                Log.w("HttpManager", "Failed to find LibraryHttpModule");
            }
        } catch (NoSuchMethodException e) {
            a("LibraryHttpModuleImpl", e);
        } catch (InstantiationException e2) {
            a("LibraryHttpModuleImpl", e2);
        } catch (IllegalAccessException e3) {
            a("LibraryHttpModuleImpl", e3);
        } catch (InvocationTargetException e4) {
            a("LibraryHttpModuleImpl", e4);
        }
        AppMethodBeat.o(3292);
        return bVar;
        bVar = null;
        AppMethodBeat.o(3292);
        return bVar;
    }

    public void a(cn.missfresh.basiclib.net.b.c.a aVar) {
        this.c = aVar;
    }

    public final cn.missfresh.basiclib.net.b.c.a d() {
        AppMethodBeat.i(3299, false);
        cn.missfresh.basiclib.net.b.c.a aVar = this.c;
        if (aVar == null) {
            aVar = new b();
        }
        AppMethodBeat.o(3299);
        return aVar;
    }
}
