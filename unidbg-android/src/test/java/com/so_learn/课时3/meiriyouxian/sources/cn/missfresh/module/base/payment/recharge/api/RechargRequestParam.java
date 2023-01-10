package cn.missfresh.module.base.payment.recharge.api;

import com.bangcle.andjni.JniLib;

public class RechargRequestParam {
    private String nonceStr;
    private String orderNo;
    private String sign;

    public RechargRequestParam() {
        JniLib.cV(this, 338);
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String str) {
        this.orderNo = str;
    }

    public String getNonceStr() {
        return this.nonceStr;
    }

    public void setNonceStr(String str) {
        this.nonceStr = str;
    }

    public String getSign() {
        return this.sign;
    }

    public void setSign(String str) {
        this.sign = str;
    }
}
