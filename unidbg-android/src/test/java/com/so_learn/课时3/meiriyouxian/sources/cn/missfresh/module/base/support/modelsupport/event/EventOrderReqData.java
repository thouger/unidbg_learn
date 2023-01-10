package cn.missfresh.module.base.support.modelsupport.event;

import cn.missfresh.module.base.support.modelsupport.event.base.BaseReqEvent;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;

public class EventOrderReqData extends BaseReqEvent {
    private String internalId;
    private String orderNo;
    private String payPassword;

    public interface IAciontType {
        public static final int t_charge_suc = 3;
        public static final int t_get_vip_popup_tips = 4;
        public static final int t_mryx_balance_pay = 2;
        public static final int t_queryOrderDetail = 1;
    }

    public EventOrderReqData() {
        AppMethodBeat.i(22040, false);
        setReqType(1002);
        AppMethodBeat.o(22040);
    }

    public String getOrderNo() {
        return this.orderNo;
    }

    public void setOrderNo(String str) {
        this.orderNo = str;
    }

    public String getPayPassword() {
        return this.payPassword;
    }

    public void setPayPassword(String str) {
        this.payPassword = str;
    }

    public String getInternalId() {
        return this.internalId;
    }

    public void setInternalId(String str) {
        this.internalId = str;
    }

    public void postEvent(int i) {
        AppMethodBeat.i(22041, false);
        setReqDetailType(i);
        post();
        AppMethodBeat.o(22041);
    }

    @Override // cn.missfresh.module.base.support.modelsupport.event.base.BaseReqEvent
    public String toString() {
        AppMethodBeat.i(22042, false);
        String jSONString = JSON.toJSONString(this);
        AppMethodBeat.o(22042);
        return jSONString;
    }
}
