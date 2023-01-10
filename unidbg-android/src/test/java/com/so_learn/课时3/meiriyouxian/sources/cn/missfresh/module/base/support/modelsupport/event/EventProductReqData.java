package cn.missfresh.module.base.support.modelsupport.event;

import cn.missfresh.module.base.support.modelsupport.event.base.BaseReqEvent;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;

public class EventProductReqData extends BaseReqEvent {
    private String feedBackMsg;
    private String productSku;
    private int toGetPage;

    public interface IAciontType {
        public static final int t_getPreSaleList = 1;
        public static final int t_getPreSaleProductDetail = 2;
        public static final int t_product_feedback = 3;
        public static final int t_product_get_shared_privilege = 4;
    }

    public EventProductReqData() {
        AppMethodBeat.i(22045, false);
        setReqType(1002);
        AppMethodBeat.o(22045);
    }

    public String getFeedBackMsg() {
        return this.feedBackMsg;
    }

    public void setFeedBackMsg(String str) {
        this.feedBackMsg = str;
    }

    public int getToGetPage() {
        return this.toGetPage;
    }

    public void setToGetPage(int i) {
        this.toGetPage = i;
    }

    public String getProductSku() {
        return this.productSku;
    }

    public void setProductSku(String str) {
        this.productSku = str;
    }

    public void postEvent(int i) {
        AppMethodBeat.i(22046, false);
        setReqDetailType(i);
        post();
        AppMethodBeat.o(22046);
    }

    @Override // cn.missfresh.module.base.support.modelsupport.event.base.BaseReqEvent
    public String toString() {
        AppMethodBeat.i(22047, false);
        String jSONString = JSON.toJSONString(this);
        AppMethodBeat.o(22047);
        return jSONString;
    }
}
