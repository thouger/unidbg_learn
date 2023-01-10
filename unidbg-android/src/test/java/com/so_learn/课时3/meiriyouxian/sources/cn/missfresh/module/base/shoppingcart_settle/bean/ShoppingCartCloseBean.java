package cn.missfresh.module.base.shoppingcart_settle.bean;

public class ShoppingCartCloseBean {
    public static final int NOT_TO_CHECKP_REPARE = 2;
    public static final int REMOVE_CHROMEP_RODUCT = 1;
    private String cancelText;
    private String confirmText;
    private String text;
    private int windowType;

    public int getWindowType() {
        return this.windowType;
    }

    public void setWindowType(int i) {
        this.windowType = i;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getConfirmText() {
        return this.confirmText;
    }

    public void setConfirmText(String str) {
        this.confirmText = str;
    }

    public String getCancelText() {
        return this.cancelText;
    }

    public void setCancelText(String str) {
        this.cancelText = str;
    }
}
