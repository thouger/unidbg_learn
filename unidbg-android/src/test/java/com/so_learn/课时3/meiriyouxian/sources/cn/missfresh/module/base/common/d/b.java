package cn.missfresh.module.base.common.d;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;

/* compiled from: Category */
public class b {
    private String a;
    private String b;
    private int c;

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public int c() {
        return this.c;
    }

    public String toString() {
        AppMethodBeat.i(12253, false);
        String jSONString = JSON.toJSONString(this);
        AppMethodBeat.o(12253);
        return jSONString;
    }
}
