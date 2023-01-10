package cn.missfresh.module.base.manager;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.HashSet;
import java.util.Set;

public class RedDotManager {
    private static RedDotManager h;
    public int a;
    public int b;
    public int c;
    public int d;
    public int e;
    public int f = 0;
    public int g = 0;
    private Set<OnEventCallback> i = new HashSet();
    private a j = new a();

    public interface OnEventCallback {
        void onEventResume();
    }

    private RedDotManager() {
        AppMethodBeat.i(14531, false);
        AppMethodBeat.o(14531);
    }

    public static RedDotManager a() {
        AppMethodBeat.i(14532, false);
        if (h == null) {
            h = new RedDotManager();
        }
        RedDotManager redDotManager = h;
        AppMethodBeat.o(14532);
        return redDotManager;
    }

    public void b() {
        AppMethodBeat.i(14533, false);
        for (OnEventCallback onEventCallback : this.i) {
            onEventCallback.onEventResume();
        }
        AppMethodBeat.o(14533);
    }

    public void a(OnEventCallback onEventCallback) {
        AppMethodBeat.i(14536, false);
        this.i.add(onEventCallback);
        AppMethodBeat.o(14536);
    }

    public class a {
        public a() {
        }
    }

    public void a(boolean z) {
        AppMethodBeat.i(14538, false);
        if (this.g == 0) {
            AppMethodBeat.o(14538);
            return;
        }
        e.B(z);
        b();
        AppMethodBeat.o(14538);
    }

    public boolean c() {
        AppMethodBeat.i(14539, false);
        boolean az = e.az();
        AppMethodBeat.o(14539);
        return az;
    }
}
