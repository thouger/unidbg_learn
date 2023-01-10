package com.befovy.fijkplayer;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import io.flutter.plugin.common.EventChannel;
import java.util.ArrayList;
import java.util.Iterator;

/* compiled from: QueuingEventSink */
/* access modifiers changed from: package-private */
public final class f implements EventChannel.EventSink {
    private EventChannel.EventSink a;
    private final ArrayList<Object> b = new ArrayList<>();
    private boolean c = false;

    f() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_HIDE_SETTINGS_SUGGESTION, false);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_HIDE_SETTINGS_SUGGESTION);
    }

    public void a(EventChannel.EventSink eventSink) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_SETTINGS_DISMISS_SUGGESTION, false);
        this.a = eventSink;
        a();
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_SETTINGS_DISMISS_SUGGESTION);
    }

    @Override // io.flutter.plugin.common.EventChannel.EventSink
    public void endOfStream() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_WINDOW_UNDOCK_MAX, false);
        a(new a());
        a();
        this.c = true;
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_WINDOW_UNDOCK_MAX);
    }

    @Override // io.flutter.plugin.common.EventChannel.EventSink
    public void error(String str, String str2, Object obj) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_TUNER_POWER_NOTIFICATION_CONTROLS, false);
        a(new b(str, str2, obj));
        a();
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_TUNER_POWER_NOTIFICATION_CONTROLS);
    }

    @Override // io.flutter.plugin.common.EventChannel.EventSink
    public void success(Object obj) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_DATA_SAVER_BLACKLIST, false);
        a(obj);
        a();
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_DATA_SAVER_BLACKLIST);
    }

    private void a(Object obj) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_REMOTE_INPUT_SEND, false);
        if (this.c) {
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_REMOTE_INPUT_SEND);
            return;
        }
        this.b.add(obj);
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_REMOTE_INPUT_SEND);
    }

    private void a() {
        AppMethodBeat.i(400, false);
        if (this.a == null) {
            AppMethodBeat.o(400);
            return;
        }
        Iterator<Object> it2 = this.b.iterator();
        while (it2.hasNext()) {
            Object next = it2.next();
            if (next instanceof a) {
                this.a.endOfStream();
            } else if (next instanceof b) {
                b bVar = (b) next;
                this.a.error(bVar.a, bVar.b, bVar.c);
            } else {
                this.a.success(next);
            }
        }
        this.b.clear();
        AppMethodBeat.o(400);
    }

    /* compiled from: QueuingEventSink */
    /* access modifiers changed from: private */
    public static class a {
        private a() {
        }
    }

    /* compiled from: QueuingEventSink */
    /* access modifiers changed from: private */
    public static class b {
        final String a;
        final String b;
        final Object c;

        b(String str, String str2, Object obj) {
            this.a = str;
            this.b = str2;
            this.c = obj;
        }
    }
}
