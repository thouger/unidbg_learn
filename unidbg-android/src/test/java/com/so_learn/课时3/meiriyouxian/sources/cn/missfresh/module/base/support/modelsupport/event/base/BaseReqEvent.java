package cn.missfresh.module.base.support.modelsupport.event.base;

import cn.missfresh.module.base.support.modelsupport.DataHelper;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.alibaba.fastjson.JSONObject;
import de.greenrobot.event.EventBus;

public class BaseReqEvent {
    private long actionId = System.currentTimeMillis();
    private int reqDetailType;
    private int reqType;

    protected interface IreqType {
        public static final int t_order = 1002;
        public static final int t_product = 1001;
        public static final int t_search = 1004;
        public static final int t_vip_info = 1003;
    }

    public BaseReqEvent() {
        AppMethodBeat.i(22089, false);
        AppMethodBeat.o(22089);
    }

    public void post() {
        AppMethodBeat.i(22090, false);
        d.d(getClass().getSimpleName(), "post...");
        if (!DataHelper.isInited()) {
            DataHelper.init();
        }
        EventBus.getDefault().post(this);
        AppMethodBeat.o(22090);
    }

    public int getReqDetailType() {
        return this.reqDetailType;
    }

    public boolean validuteReq(int i, long j) {
        return this.reqDetailType == i && j == this.actionId;
    }

    public void setReqDetailType(int i) {
        this.reqDetailType = i;
    }

    public int getReqType() {
        return this.reqType;
    }

    /* access modifiers changed from: protected */
    public void setReqType(int i) {
        this.reqType = i;
    }

    public long getActionId() {
        return this.actionId;
    }

    public void setActionId(long j) {
        this.actionId = j;
    }

    public String toString() {
        AppMethodBeat.i(22096, false);
        String jSONString = JSONObject.toJSONString(this);
        AppMethodBeat.o(22096);
        return jSONString;
    }
}
