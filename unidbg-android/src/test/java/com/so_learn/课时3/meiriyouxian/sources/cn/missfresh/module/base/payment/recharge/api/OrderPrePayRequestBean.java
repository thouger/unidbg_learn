package cn.missfresh.module.base.payment.recharge.api;

import com.bangcle.andjni.JniLib;

public class OrderPrePayRequestBean {
    private ChooseBalanceBean chooseBalance;
    private String orderId;
    private String payType;

    public OrderPrePayRequestBean() {
        JniLib.cV(this, 334);
    }

    public String getOrderId() {
        return this.orderId;
    }

    public void setOrderId(String str) {
        this.orderId = str;
    }

    public String getPayType() {
        return this.payType;
    }

    public void setPayType(String str) {
        this.payType = str;
    }

    public ChooseBalanceBean getChooseBalance() {
        return this.chooseBalance;
    }

    public void setChooseBalance(ChooseBalanceBean chooseBalanceBean) {
        this.chooseBalance = chooseBalanceBean;
    }

    public static class ChooseBalanceBean {
        private int giftCard;
        private int storageValue;

        public ChooseBalanceBean() {
            JniLib.cV(this, 333);
        }

        public int getStorageValue() {
            return this.storageValue;
        }

        public void setStorageValue(int i) {
            this.storageValue = i;
        }

        public int getGiftCard() {
            return this.giftCard;
        }

        public void setGiftCard(int i) {
            this.giftCard = i;
        }
    }
}
