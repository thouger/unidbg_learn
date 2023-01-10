package cn.missfresh.sherlock.trace.f;

import android.content.Context;
import cn.missfresh.sherlock.a.a.a;
import cn.missfresh.sherlock.a.a.b;
import cn.missfresh.sherlock.tool.j;

/* compiled from: TracePluginListener */
public class e implements b {
    public e(Context context) {
    }

    @Override // cn.missfresh.sherlock.a.a.b
    public void a(a aVar) {
        j.b("TracePluginListener", "%s plugin is started", aVar.d());
    }

    @Override // cn.missfresh.sherlock.a.a.b
    public void b(a aVar) {
        j.b("TracePluginListener", "%s plugin is stopped", aVar.d());
    }
}
