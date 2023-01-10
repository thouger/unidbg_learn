package cn.missfresh.module.base.common.api;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class RequestParam<T> {
    private String algoId;
    T param;

    public T getParam() {
        return this.param;
    }

    public RequestParam<T> setParam(T t) {
        this.param = t;
        return this;
    }

    public RequestParam setDefaultParam() {
        AppMethodBeat.i(10225, false);
        this.param = (T) new Object();
        AppMethodBeat.o(10225);
        return this;
    }

    public String getAlgoId() {
        return this.algoId;
    }

    public void setAlgoId(String str) {
        this.algoId = str;
    }
}
