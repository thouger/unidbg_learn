package cn.missfresh.module.base.payment.recharge.bean;

import cn.missfresh.module.base.bean.BannerEntity;
import com.bangcle.andjni.JniLib;
import java.util.List;

public class Balance {
    public int arrivedAmount;
    public int arrivingAmount;
    public List<BannerEntity> banner;
    public String bannerTitle;
    public int card_balance;
    public String descUrl;
    public String giftExplain;
    public String giftExplainTitle;
    public int money;
    public int recharge_balance_new;
    public String recharge_explain;

    public Balance() {
        JniLib.cV(this, 411);
    }
}
