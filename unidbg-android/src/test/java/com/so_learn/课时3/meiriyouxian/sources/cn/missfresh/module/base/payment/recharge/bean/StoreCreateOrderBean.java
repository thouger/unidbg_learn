package cn.missfresh.module.base.payment.recharge.bean;

import com.bangcle.andjni.JniLib;

public class StoreCreateOrderBean {
    private String name;
    private String orderId;
    private int payAmount;
    private String payType;
    private String sku;

    public StoreCreateOrderBean() {
        JniLib.cV(this, 424);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getPayAmount() {
        return this.payAmount;
    }

    public void setPayAmount(int i) {
        this.payAmount = i;
    }

    public String getPayType() {
        return this.payType;
    }

    public void setPayType(String str) {
        this.payType = str;
    }

    public String getSku() {
        return this.sku;
    }

    public void setSku(String str) {
        this.sku = str;
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }
}
