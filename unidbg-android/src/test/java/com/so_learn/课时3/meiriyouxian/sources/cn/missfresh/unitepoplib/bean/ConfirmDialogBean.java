package cn.missfresh.unitepoplib.bean;

public class ConfirmDialogBean extends DialogBean {
    private String cancelTxt;
    private String confirmTxt;
    private String content;
    private String title;

    public ConfirmDialogBean title(String str) {
        this.title = str;
        return this;
    }

    public ConfirmDialogBean content(String str) {
        this.content = str;
        return this;
    }

    public ConfirmDialogBean confirmTxt(String str) {
        this.confirmTxt = str;
        return this;
    }

    public ConfirmDialogBean cancelTxt(String str) {
        this.cancelTxt = str;
        return this;
    }

    @Override // cn.missfresh.unitepoplib.bean.DialogBean
    public ConfirmDialogBean templateId(String str) {
        this.templateId = str;
        return this;
    }

    @Override // cn.missfresh.unitepoplib.bean.DialogBean
    public ConfirmDialogBean styleType(int i) {
        this.styleType = i;
        return this;
    }

    @Override // cn.missfresh.unitepoplib.bean.DialogBean
    public ConfirmDialogBean status(int i) {
        this.status = i;
        return this;
    }

    @Override // cn.missfresh.unitepoplib.bean.DialogBean
    public ConfirmDialogBean blockingUp(boolean z) {
        this.blockingUp = z;
        return this;
    }

    @Override // cn.missfresh.unitepoplib.bean.DialogBean
    public ConfirmDialogBean priority(String str) {
        this.priority = str;
        return this;
    }

    @Override // cn.missfresh.unitepoplib.bean.DialogBean
    public ConfirmDialogBean moudleName(String str) {
        this.moudleName = str;
        return this;
    }

    @Override // cn.missfresh.unitepoplib.bean.DialogBean
    public ConfirmDialogBean popStyle(String str) {
        this.popStyle = str;
        return this;
    }

    @Override // cn.missfresh.unitepoplib.bean.DialogBean
    public ConfirmDialogBean layoutId(int i) {
        this.layoutId = i;
        return this;
    }

    @Override // cn.missfresh.unitepoplib.bean.DialogBean
    public ConfirmDialogBean cancelable(boolean z) {
        this.cancelable = z;
        return this;
    }

    @Override // cn.missfresh.unitepoplib.bean.DialogBean
    public ConfirmDialogBean autoDismiss(boolean z) {
        this.autoDismiss = z;
        return this;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public String getConfirmTxt() {
        return this.confirmTxt;
    }

    public String getCancelTxt() {
        return this.cancelTxt;
    }
}
