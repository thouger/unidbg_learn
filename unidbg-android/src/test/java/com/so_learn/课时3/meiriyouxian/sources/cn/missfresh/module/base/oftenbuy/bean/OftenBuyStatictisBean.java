package cn.missfresh.module.base.oftenbuy.bean;

public class OftenBuyStatictisBean {
    public int productLimit;
    public int quantity;
    public String sku;
    public int stock;

    public OftenBuyStatictisBean(String str, int i, int i2, int i3) {
        this.sku = str;
        this.quantity = i;
        this.stock = i2;
        this.productLimit = i3;
    }
}
