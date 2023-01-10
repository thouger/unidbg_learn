package cn.missfresh.module.base.shoppingcart_settle.bean;

public class SettleRequestBean {
    public String from;
    public int index;

    public SettleRequestBean(int i, String str) {
        this.index = i;
        this.from = str;
    }
}
