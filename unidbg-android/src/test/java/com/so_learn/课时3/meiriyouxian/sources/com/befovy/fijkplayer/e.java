package com.befovy.fijkplayer;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import java.util.HashMap;
import java.util.Map;

/* compiled from: HostOption */
final class e {
    private final Map<String, Integer> a = new HashMap();
    private final Map<String, String> b = new HashMap();

    e() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.SUW_ACCESSIBILITY, false);
        AppMethodBeat.o(MetricsProto.MetricsEvent.SUW_ACCESSIBILITY);
    }

    /* access modifiers changed from: package-private */
    public void a(String str, Integer num) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.SUW_ACCESSIBILITY_FONT_SIZE, false);
        this.a.put(str, num);
        AppMethodBeat.o(MetricsProto.MetricsEvent.SUW_ACCESSIBILITY_FONT_SIZE);
    }

    /* access modifiers changed from: package-private */
    public void a(String str, String str2) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_SETTINGS_CONDITION_DISMISS, false);
        this.b.put(str, str2);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SETTINGS_CONDITION_DISMISS);
    }

    /* access modifiers changed from: package-private */
    public int a(String str, int i) {
        Integer num;
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_SETTINGS_CONDITION_CLICK, false);
        if (this.a.containsKey(str) && (num = this.a.get(str)) != null) {
            i = num.intValue();
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SETTINGS_CONDITION_CLICK);
        return i;
    }
}
