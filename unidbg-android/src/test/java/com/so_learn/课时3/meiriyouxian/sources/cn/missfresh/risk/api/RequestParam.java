package cn.missfresh.risk.api;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;

public class RequestParam<T> {
    T param;

    public T getParam() {
        return this.param;
    }

    public RequestParam<T> setParam(T t) {
        this.param = t;
        return this;
    }

    public RequestParam setDefaultParam() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.FIELD_SETTINGS_SMART_SUGGESTIONS_ENABLED, false);
        this.param = (T) new Object();
        AppMethodBeat.o(MetricsProto.MetricsEvent.FIELD_SETTINGS_SMART_SUGGESTIONS_ENABLED);
        return this;
    }
}
