package cn.missfresh.module.base.payment.recharge.bean;

public interface IMyBalance {
    String geTradeNo();

    int getBalance();

    int getCardDonatePrice();

    int getCardId();

    int getCardPrice();

    String getChangeTypeName();

    String getTradeAmount();

    String getTradeTime();
}
