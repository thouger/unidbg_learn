package cn.missfresh.module.user.address.widget.indexlib.IndexBar.bean;

import cn.missfresh.module.user.address.widget.indexlib.suspension.a;

public abstract class BaseIndexBean implements a {
    private String baseIndexTag;

    public boolean isShowSuspension() {
        return true;
    }

    public String getBaseIndexTag() {
        return this.baseIndexTag;
    }

    public BaseIndexBean setBaseIndexTag(String str) {
        this.baseIndexTag = str;
        return this;
    }

    public String getSuspensionTag() {
        return this.baseIndexTag;
    }
}
