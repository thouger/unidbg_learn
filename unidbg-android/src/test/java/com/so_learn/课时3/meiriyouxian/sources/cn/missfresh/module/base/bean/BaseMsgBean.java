package cn.missfresh.module.base.bean;

import cn.missfresh.module.base.common.interfaces.t;

public abstract class BaseMsgBean implements t {
    public static final int TYPE_ACTION = 1;
    public static final int TYPE_GIFT = 4;
    public static final int TYPE_ORDER = 2;
    public static final int TYPE_SYSTEM = 3;
    public static final int TYPE_VIP = 5;
    protected int msgType;

    /* access modifiers changed from: protected */
    public void setMsgType(int i) {
        this.msgType = i;
    }

    @Override // cn.missfresh.module.base.common.interfaces.t
    public int getMultiType() {
        return this.msgType;
    }
}
