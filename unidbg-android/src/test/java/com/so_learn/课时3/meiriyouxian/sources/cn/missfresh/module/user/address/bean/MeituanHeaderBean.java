package cn.missfresh.module.user.address.bean;

import cn.missfresh.module.user.address.widget.indexlib.IndexBar.bean.BaseIndexPinyinBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

public class MeituanHeaderBean extends BaseIndexPinyinBean {
    private List<SupporCityBean> cityList;
    private String suspensionTag;

    @Override // cn.missfresh.module.user.address.widget.indexlib.IndexBar.bean.BaseIndexPinyinBean
    public String getTarget() {
        return null;
    }

    @Override // cn.missfresh.module.user.address.widget.indexlib.IndexBar.bean.BaseIndexPinyinBean
    public boolean isNeedToPinyin() {
        return false;
    }

    public MeituanHeaderBean() {
    }

    public MeituanHeaderBean(List<SupporCityBean> list, String str, String str2) {
        AppMethodBeat.i(3745, false);
        this.cityList = list;
        this.suspensionTag = str;
        setBaseIndexTag(str2);
        AppMethodBeat.o(3745);
    }

    public List<SupporCityBean> getCityList() {
        return this.cityList;
    }

    public MeituanHeaderBean setCityList(List<SupporCityBean> list) {
        this.cityList = list;
        return this;
    }

    public MeituanHeaderBean setSuspensionTag(String str) {
        this.suspensionTag = str;
        return this;
    }

    @Override // cn.missfresh.module.user.address.widget.indexlib.IndexBar.bean.BaseIndexBean
    public String getSuspensionTag() {
        return this.suspensionTag;
    }
}
