package cn.missfresh.module.base.payment.recharge.bean;

import com.bangcle.andjni.JniLib;
import com.tencent.smtt.sdk.TbsListener;

public class CreateCardInfo {
    private int actual_amount;
    private String card_internal_id;
    private int card_price;
    private int id;
    private String name;
    private String pay_type;
    private String recharge_no;
    private String status;
    public int user_type;

    public CreateCardInfo() {
        JniLib.cV(this, Integer.valueOf((int) TbsListener.ErrorCode.INFO_CORE_EXIST_NOT_LOAD));
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getPay_type() {
        return this.pay_type;
    }

    public void setPay_type(String str) {
        this.pay_type = str;
    }

    public String getRecharge_no() {
        return this.recharge_no;
    }

    public void setRecharge_no(String str) {
        this.recharge_no = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getCard_price() {
        return this.card_price;
    }

    public void setCard_price(int i) {
        this.card_price = i;
    }

    public int getActual_amount() {
        return this.actual_amount;
    }

    public void setActual_amount(int i) {
        this.actual_amount = i;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getCard_internal_id() {
        return this.card_internal_id;
    }

    public void setCard_internal_id(String str) {
        this.card_internal_id = str;
    }
}
