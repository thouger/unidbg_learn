package cn.missfresh.next;

import cn.missfresh.next.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: SmartManager */
class d$2 implements a.a {
    final /* synthetic */ d a;

    d$2(d dVar) {
        this.a = dVar;
    }

    public void a(String str, long j) {
        AppMethodBeat.i(1680, false);
        c.a("SmartManager", "Task [" + str + "] finished, cost time: " + j);
        if (d.a(this.a) != null) {
            d.a(this.a).a(str, j);
        }
        AppMethodBeat.o(1680);
    }

    public void a(String str, String str2) {
        AppMethodBeat.i(1685, false);
        c.a("SmartManager", "Task [" + str + "] exception, message is: " + str2);
        StringBuffer b = d.b(this.a);
        b.append(str);
        b.append(" exception message: ");
        b.append(str2);
        b.append("\n");
        if (d.a(this.a) != null) {
            d.a(this.a).a(str, str2);
        }
        AppMethodBeat.o(1685);
    }

    public void b(String str, long j) {
        AppMethodBeat.i(1688, false);
        c.a("SmartManager", "Project [" + str + "] finished, cost time: " + j);
        if (d.a(this.a) != null) {
            d.a(this.a).b(str, j);
        }
        AppMethodBeat.o(1688);
    }
}
