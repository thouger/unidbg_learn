package cn.missfresh.module.base.common.event;

import cn.missfresh.module.base.base.a;
import cn.missfresh.module.base.bean.ShoppingCartNotice;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;

/* compiled from: EventShoppingCartChanged */
@Deprecated
public class g extends a {
    public final int a;
    public ShoppingCartNotice b;
    public int c = -1;
    private String d;

    public g(int i) {
        this.a = i;
    }

    public g(int i, ShoppingCartNotice shoppingCartNotice, int i2) {
        this.a = i;
        this.b = shoppingCartNotice;
        this.c = i2;
    }

    public void a(String str) {
        this.d = str;
    }

    public String toString() {
        AppMethodBeat.i(11697, false);
        String jSONString = JSON.toJSONString(this);
        AppMethodBeat.o(11697);
        return jSONString;
    }
}
