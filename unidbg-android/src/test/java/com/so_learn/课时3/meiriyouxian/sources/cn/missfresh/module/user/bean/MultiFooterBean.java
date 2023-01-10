package cn.missfresh.module.user.bean;

import cn.missfresh.module.base.common.interfaces.t;

public class MultiFooterBean implements t {
    public static final int ITEM_TYPE_FOOTER = 2000;
    public int noMoreData = -1;

    @Override // cn.missfresh.module.base.common.interfaces.t
    public int getMultiType() {
        return 2000;
    }
}
