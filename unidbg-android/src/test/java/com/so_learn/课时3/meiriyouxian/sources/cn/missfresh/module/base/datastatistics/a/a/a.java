package cn.missfresh.module.base.datastatistics.a.a;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.Map;

/* compiled from: ScmEvent */
public class a {
    public String a;
    public String b;
    public String c;
    public String d;
    public String e;
    public Map f;

    public String toString() {
        AppMethodBeat.i(12858, false);
        String str = "{\"type\":\"" + this.a + "\",\"label\":\"" + this.b + "\",\"module\":\"" + this.c + "\",\"rscId\":\"" + this.e + "\",\"paramsMap\":" + this.f + '}';
        AppMethodBeat.o(12858);
        return str;
    }
}
