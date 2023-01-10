package cn.missfresh.module.base.payment.recharge.bean;

import com.bangcle.andjni.JniLib;
import com.taobao.accs.flowcontrol.FlowControl;
import java.io.Serializable;

public class RechargeCard implements Serializable {
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
    public String success_txt;
    public String tag;

    public RechargeCard() {
        JniLib.cV(this, Integer.valueOf((int) FlowControl.STATUS_FLOW_CTRL_BRUSH));
    }
}
