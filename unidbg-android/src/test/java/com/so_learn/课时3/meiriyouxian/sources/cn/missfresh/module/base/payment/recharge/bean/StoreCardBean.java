package cn.missfresh.module.base.payment.recharge.bean;

import com.bangcle.andjni.JniLib;
import java.io.Serializable;

public class StoreCardBean implements Serializable {
    private int balanceAmount;
    private String cardName;
    private String description;
    private int isDefault;
    private int payAmount;
    private String picUrl;
    private String skuId;
    private String successTxt;

    public StoreCardBean() {
        JniLib.cV(this, 423);
    }

    public String getSkuId() {
        return this.skuId;
    }

    public void setSkuId(String str) {
        this.skuId = str;
    }

    public String getCardName() {
        return this.cardName;
    }

    public void setCardName(String str) {
        this.cardName = str;
    }

    public String getPicUrl() {
        return this.picUrl;
    }

    public void setPicUrl(String str) {
        this.picUrl = str;
    }

    public int getPayAmount() {
        return this.payAmount;
    }

    public void setPayAmount(int i) {
        this.payAmount = i;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public int getIsDefault() {
        return this.isDefault;
    }

    public void setIsDefault(int i) {
        this.isDefault = i;
    }

    public String getSuccessTxt() {
        return this.successTxt;
    }

    public void setSuccessTxt(String str) {
        this.successTxt = str;
    }

    public int getBalanceAmount() {
        return this.balanceAmount;
    }

    public void setBalanceAmount(int i) {
        this.balanceAmount = i;
    }
}
