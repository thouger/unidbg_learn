package cn.missfresh.module.base.bean;

import cn.missfresh.module.base.base.a;

public class EventVipChanged extends a {
    public boolean ifVip;
    private int memberLevel;

    public EventVipChanged() {
    }

    public EventVipChanged(boolean z) {
        this.ifVip = z;
    }

    public int getMemberLevel() {
        return this.memberLevel;
    }

    public void setMemberLevel(int i) {
        this.memberLevel = i;
    }
}
