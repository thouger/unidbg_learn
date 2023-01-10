package cn.missfresh.module.base.shoppingcart_settle.bean;

public class SettleSkuBean {
    private int quantity;
    private String sku;
    private String type;

    public String getSku() {
        return this.sku;
    }

    public void setSku(String str) {
        this.sku = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int i) {
        this.quantity = i;
    }
}
