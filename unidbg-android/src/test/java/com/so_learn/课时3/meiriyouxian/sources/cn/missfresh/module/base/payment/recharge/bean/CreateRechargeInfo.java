package cn.missfresh.module.base.payment.recharge.bean;

import com.bangcle.andjni.JniLib;

public class CreateRechargeInfo {
    public int actual_amount;
    public CardInfo card_info;
    public String card_internal_id;
    public int card_price;
    public int id;
    public String name;
    public String pay_type;
    public String recharge_no;
    public String status;

    public class CardInfo {
        public boolean active;
        public int actual_amount;
        public String background_img;
        public String background_img_big;
        public int card_price;
        public String description;
        public String internal_id;
        public int is_default;
        public int limit;
        public String name;
        public String tag;
        final /* synthetic */ CreateRechargeInfo this$0;

        public CardInfo(CreateRechargeInfo createRechargeInfo) {
            JniLib.cV(this, createRechargeInfo, 419);
        }
    }

    public CreateRechargeInfo() {
        JniLib.cV(this, 420);
    }
}
