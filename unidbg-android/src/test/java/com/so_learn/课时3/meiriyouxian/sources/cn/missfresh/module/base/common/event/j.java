package cn.missfresh.module.base.common.event;

import cn.missfresh.module.base.base.a;
import cn.missfresh.module.base.bean.ShoppingCart;
import java.util.List;

/* compiled from: EventSyncLocalShoppingCart */
public class j extends a {
    public final List<ShoppingCart> a;
    public final int b;

    public j(List<ShoppingCart> list, int i) {
        this.a = list;
        this.b = i;
    }
}
