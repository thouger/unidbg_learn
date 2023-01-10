package cn.missfresh.module.base.support.modelsupport.event;

import cn.missfresh.module.base.support.modelsupport.event.base.BaseReqEvent;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class EventVipReqdata extends BaseReqEvent {
    private int integralType;
    private String sku;

    public interface IAciontType {
        public static final int t_check_if_canbuy = 3;
        public static final int t_queryIntegralPageInfo = 2;
        public static final int t_queryVipInfo = 1;
        public static final int t_to_buy_virtual_pro = 4;
    }

    public EventVipReqdata() {
        AppMethodBeat.i(22060, false);
        setReqType(1003);
        AppMethodBeat.o(22060);
    }

    public int getIntegralType() {
        return this.integralType;
    }

    public void setIntegralType(int i) {
        this.integralType = i;
    }

    public String getSku() {
        return this.sku;
    }

    public void setSku(String str) {
        this.sku = str;
    }

    public void postEvent(int i) {
        AppMethodBeat.i(22063, false);
        setReqDetailType(i);
        post();
        AppMethodBeat.o(22063);
    }
}
