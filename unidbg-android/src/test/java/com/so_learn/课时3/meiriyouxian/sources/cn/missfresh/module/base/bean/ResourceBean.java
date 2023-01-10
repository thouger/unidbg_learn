package cn.missfresh.module.base.bean;

import cn.missfresh.module.base.utils.q;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class ResourceBean extends BaseCellBean {
    private int bizFingerprintType;
    private String image;
    private String internalId;
    private String link;
    private String name;
    private int nameColor;
    private ParamsBean params;
    private int position;
    private String promotionId;
    private ShareInfo shareInfo;
    private String type;

    public void setBizFingerprintType(int i) {
        this.bizFingerprintType = i;
    }

    public int getBizFingerprintType() {
        return this.bizFingerprintType;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public ShareInfo getShareInfo() {
        return this.shareInfo;
    }

    public void setShareInfo(ShareInfo shareInfo) {
        this.shareInfo = shareInfo;
    }

    public ParamsBean getParams() {
        return this.params;
    }

    public void setParams(ParamsBean paramsBean) {
        this.params = paramsBean;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }

    public String getPromotionId() {
        return this.promotionId;
    }

    public void setPromotionId(String str) {
        this.promotionId = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getNameColor() {
        AppMethodBeat.i(6891, false);
        int i = this.nameColor;
        if (i != 0) {
            int a = q.a(i);
            AppMethodBeat.o(6891);
            return a;
        }
        AppMethodBeat.o(6891);
        return i;
    }

    public void setNameColor(int i) {
        this.nameColor = i;
    }

    public String getInternalId() {
        return this.internalId;
    }

    public void setInternalId(String str) {
        this.internalId = str;
    }

    public int getPosition() {
        return this.position;
    }

    public ResourceBean setPosition(int i) {
        this.position = i;
        return this;
    }
}
