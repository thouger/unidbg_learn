package cn.missfresh.module.user.address.widget.indexlib.IndexBar.bean;

public abstract class BaseIndexPinyinBean extends BaseIndexBean {
    private String baseIndexPinyin;

    public abstract String getTarget();

    public boolean isNeedToPinyin() {
        return true;
    }

    public String getBaseIndexPinyin() {
        return this.baseIndexPinyin;
    }

    public BaseIndexPinyinBean setBaseIndexPinyin(String str) {
        this.baseIndexPinyin = str;
        return this;
    }
}
