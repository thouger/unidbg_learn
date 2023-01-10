package cn.missfresh.unitepoplib.bean;

public class PicDialogBean extends DialogBean {
    private boolean force;
    private String imgUrl;
    private String link;
    private int type;

    public PicDialogBean imgUrl(String str) {
        this.imgUrl = str;
        return this;
    }

    public PicDialogBean link(String str) {
        this.link = str;
        return this;
    }

    public PicDialogBean type(int i) {
        this.type = i;
        return this;
    }

    public PicDialogBean force(boolean z) {
        this.force = z;
        return this;
    }

    @Override // cn.missfresh.unitepoplib.bean.DialogBean
    public PicDialogBean templateId(String str) {
        this.templateId = str;
        return this;
    }

    @Override // cn.missfresh.unitepoplib.bean.DialogBean
    public PicDialogBean styleType(int i) {
        this.styleType = i;
        return this;
    }

    @Override // cn.missfresh.unitepoplib.bean.DialogBean
    public PicDialogBean status(int i) {
        this.status = i;
        return this;
    }

    @Override // cn.missfresh.unitepoplib.bean.DialogBean
    public PicDialogBean blockingUp(boolean z) {
        this.blockingUp = z;
        return this;
    }

    @Override // cn.missfresh.unitepoplib.bean.DialogBean
    public PicDialogBean priority(String str) {
        this.priority = str;
        return this;
    }

    @Override // cn.missfresh.unitepoplib.bean.DialogBean
    public PicDialogBean moudleName(String str) {
        this.moudleName = str;
        return this;
    }

    @Override // cn.missfresh.unitepoplib.bean.DialogBean
    public PicDialogBean popStyle(String str) {
        this.popStyle = str;
        return this;
    }

    @Override // cn.missfresh.unitepoplib.bean.DialogBean
    public PicDialogBean layoutId(int i) {
        this.layoutId = i;
        return this;
    }

    @Override // cn.missfresh.unitepoplib.bean.DialogBean
    public PicDialogBean cancelable(boolean z) {
        this.cancelable = z;
        return this;
    }

    @Override // cn.missfresh.unitepoplib.bean.DialogBean
    public PicDialogBean autoDismiss(boolean z) {
        this.autoDismiss = z;
        return this;
    }

    public boolean isForce() {
        return this.force;
    }

    public String getImgUrl() {
        return this.imgUrl;
    }

    public String getLink() {
        return this.link;
    }

    public int getType() {
        return this.type;
    }
}
