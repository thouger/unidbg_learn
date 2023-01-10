package com.sobot.chat.api.model;

import android.text.format.DateFormat;
import java.io.Serializable;
import java.util.List;

public class OrderCardContentModel implements Serializable {
    private static final long serialVersionUID = 1;
    private String createTime;
    private List<Goods> goods;
    private String goodsCount;
    private boolean isAutoSend;
    private String orderCode;
    private int orderStatus;
    private String orderUrl;
    private int totalFee;

    public OrderCardContentModel() {
    }

    public OrderCardContentModel(int i, String str, String str2, String str3, String str4, int i2, List<Goods> list) {
        this.orderStatus = i;
        this.orderCode = str;
        this.createTime = str2;
        this.orderUrl = str3;
        this.goodsCount = str4;
        this.totalFee = i2;
        this.goods = list;
    }

    public static class Goods implements Serializable {
        private String name;
        private String pictureUrl;

        public Goods() {
        }

        public Goods(String str, String str2) {
            this.name = str;
            this.pictureUrl = str2;
        }

        public String getName() {
            return this.name;
        }

        public void setName(String str) {
            this.name = str;
        }

        public String getPictureUrl() {
            return this.pictureUrl;
        }

        public void setPictureUrl(String str) {
            this.pictureUrl = str;
        }

        @Override // java.lang.Object
        public String toString() {
            return "{name='" + this.name + DateFormat.QUOTE + ", pictureUrl='" + this.pictureUrl + DateFormat.QUOTE + '}';
        }
    }

    public int getOrderStatus() {
        return this.orderStatus;
    }

    public void setOrderStatus(int i) {
        this.orderStatus = i;
    }

    public String getOrderCode() {
        return this.orderCode;
    }

    public void setOrderCode(String str) {
        this.orderCode = str;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String str) {
        this.createTime = str;
    }

    public String getOrderUrl() {
        return this.orderUrl;
    }

    public void setOrderUrl(String str) {
        this.orderUrl = str;
    }

    public String getGoodsCount() {
        return this.goodsCount;
    }

    public void setGoodsCount(String str) {
        this.goodsCount = str;
    }

    public int getTotalFee() {
        return this.totalFee;
    }

    public void setTotalFee(int i) {
        this.totalFee = i;
    }

    public List<Goods> getGoods() {
        return this.goods;
    }

    public void setGoods(List<Goods> list) {
        this.goods = list;
    }

    public boolean isAutoSend() {
        return this.isAutoSend;
    }

    public void setAutoSend(boolean z) {
        this.isAutoSend = z;
    }

    @Override // java.lang.Object
    public String toString() {
        return "OrderCardContentModel{orderStatus=" + this.orderStatus + ", orderCode='" + this.orderCode + DateFormat.QUOTE + ", createTime='" + this.createTime + DateFormat.QUOTE + ", orderUrl='" + this.orderUrl + DateFormat.QUOTE + ", goodsCount='" + this.goodsCount + DateFormat.QUOTE + ", totalFee=" + this.totalFee + ", goods=" + this.goods + ", isAutoSend=" + this.isAutoSend + '}';
    }
}
