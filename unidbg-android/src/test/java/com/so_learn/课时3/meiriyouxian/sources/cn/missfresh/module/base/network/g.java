package cn.missfresh.module.base.network;

import cn.missfresh.basiclib.net.b;
import cn.missfresh.basiclib.net.d.a;
import cn.missfresh.module.base.network.a.c;
import cn.missfresh.module.base.network.a.e;
import cn.missfresh.module.base.network.a.f;
import cn.missfresh.module.base.network.a.h;
import cn.missfresh.module.base.network.a.i;
import cn.missfresh.module.base.network.a.j;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

/* compiled from: MFHttpModule */
public class g extends b {
    @Override // cn.missfresh.basiclib.net.b
    public void a(OkHttpClient.Builder builder) {
        AppMethodBeat.i(15523, false);
        builder.connectTimeout(5, TimeUnit.SECONDS).readTimeout(5, TimeUnit.SECONDS).writeTimeout(5, TimeUnit.SECONDS).addInterceptor(new a()).addNetworkInterceptor(new c()).addNetworkInterceptor(new e()).addNetworkInterceptor(new h()).addNetworkInterceptor(new i()).addNetworkInterceptor(new cn.missfresh.module.base.network.a.g()).addNetworkInterceptor(new f()).addNetworkInterceptor(new j()).eventListenerFactory(b.a);
        e.a(builder);
        AppMethodBeat.o(15523);
    }
}
