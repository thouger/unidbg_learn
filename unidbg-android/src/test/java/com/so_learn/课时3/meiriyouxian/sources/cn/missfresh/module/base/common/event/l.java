package cn.missfresh.module.base.common.event;

import cn.missfresh.module.base.base.a;
import cn.missfresh.module.base.bean.UserAddress;
import java.util.List;

/* compiled from: EventUserDefaultAddress */
public class l extends a {
    public List<UserAddress> a;
    public int b;
    public int c = 0;
    public int d = 0;
    public int e = 0;
    public String f;

    public l(int i, List<UserAddress> list, int i2, int i3, int i4, String str) {
        this.b = i;
        this.a = list;
        this.c = i2;
        this.d = i3;
        this.e = i4;
        this.f = str;
    }

    public l(boolean z) {
        super(z);
    }
}
