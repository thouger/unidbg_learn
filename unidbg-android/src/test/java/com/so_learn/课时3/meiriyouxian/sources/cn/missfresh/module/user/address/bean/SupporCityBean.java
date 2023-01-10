package cn.missfresh.module.user.address.bean;

import cn.missfresh.module.user.address.widget.indexlib.IndexBar.bean.BaseIndexPinyinBean;

public class SupporCityBean extends BaseIndexPinyinBean {
    private String code;
    private String initial;
    private int isChromeCity;
    private String name;
    private String pinyin;

    public SupporCityBean(String str, String str2, String str3, String str4, int i) {
        this.name = str;
        this.code = str2;
        this.initial = str3;
        this.pinyin = str4;
        this.isChromeCity = i;
    }

    public SupporCityBean() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getCode() {
        return this.code;
    }

    public void setCode(String str) {
        this.code = str;
    }

    public String getInitial() {
        return this.initial;
    }

    public void setInitial(String str) {
        this.initial = str;
    }

    public String getPinyin() {
        return this.pinyin;
    }

    public void setPinyin(String str) {
        this.pinyin = str;
    }

    public int getIsChromeCity() {
        return this.isChromeCity;
    }

    public void setIsChromeCity(int i) {
        this.isChromeCity = i;
    }

    @Override // cn.missfresh.module.user.address.widget.indexlib.IndexBar.bean.BaseIndexPinyinBean
    public String getTarget() {
        return this.name;
    }
}
