package cn.missfresh.module.base.bean;

public class ShareExtraDescBean {
    private String cutText;
    private String newUserText;
    private String pageType;
    private String shareHint;

    public String getNewUserText() {
        return this.newUserText;
    }

    public void setNewUserText(String str) {
        this.newUserText = str;
    }

    public String getCutText() {
        return this.cutText;
    }

    public void setCutText(String str) {
        this.cutText = str;
    }

    public String getShareHint() {
        return this.shareHint;
    }

    public void setShareHint(String str) {
        this.shareHint = str;
    }

    public String getPageType() {
        return this.pageType;
    }

    public void setPageType(String str) {
        this.pageType = str;
    }
}
