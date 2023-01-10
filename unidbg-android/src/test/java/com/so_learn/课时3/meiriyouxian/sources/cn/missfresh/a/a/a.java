package cn.missfresh.a.a;

import cn.missfresh.basiclib.net.b;
import cn.missfresh.module.base.network.g;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import okhttp3.OkHttpClient;

/* compiled from: LibraryHttpModuleImpl */
public final class a extends b {
    private final g a = new g();

    public a() {
        AppMethodBeat.i(24471, false);
        AppMethodBeat.o(24471);
    }

    @Override // cn.missfresh.basiclib.net.b
    public void a(OkHttpClient.Builder builder) {
        AppMethodBeat.i(24472, false);
        this.a.a(builder);
        AppMethodBeat.o(24472);
    }
}
