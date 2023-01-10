package cn.missfresh.module.base.support.modelsupport.event.base;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.alibaba.fastjson.JSONObject;
import de.greenrobot.event.EventBus;

public class BaseResEvent {
    private int errCode = 0;
    private String errMsg;
    private boolean ifsuc = true;
    private BaseReqEvent originReq;

    public interface IResCode {
        public static final int c_data_err = -2;
        public static final int c_net_err = -1;
        public static final int c_suc = 0;
    }

    public int getErrCode() {
        return this.errCode;
    }

    public void setErrCode(int i) {
        this.errCode = i;
    }

    public boolean isIfsuc() {
        return this.ifsuc && this.errCode == 0;
    }

    public void setIfsuc(boolean z) {
        this.ifsuc = z;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public void setErrMsg(String str) {
        this.errMsg = str;
    }

    public BaseReqEvent getOriginReq() {
        return this.originReq;
    }

    public void setOriginReq(BaseReqEvent baseReqEvent) {
        this.originReq = baseReqEvent;
    }

    public BaseResEvent(BaseReqEvent baseReqEvent) {
        this.originReq = baseReqEvent;
    }

    public void setNetErr() {
        this.errCode = -1;
        this.errMsg = "\u7f51\u7edc\u8fde\u63a5\u5f02\u5e38\uff0c\u8bf7\u7a0d\u540e\u91cd\u8bd5~";
    }

    public void setDataErr() {
        this.errCode = -2;
    }

    public void post() {
        AppMethodBeat.i(22116, false);
        d.d(getClass().getSimpleName(), "post");
        EventBus.getDefault().post(this);
        AppMethodBeat.o(22116);
    }

    public static class baseNetRes {
        private int code;
        private String msg;

        public int getCode() {
            return this.code;
        }

        public void setCode(int i) {
            this.code = i;
        }

        public String getMsg() {
            return this.msg;
        }

        public void setMsg(String str) {
            this.msg = str;
        }
    }

    public String toString() {
        AppMethodBeat.i(22117, false);
        String jSONString = JSONObject.toJSONString(this);
        AppMethodBeat.o(22117);
        return jSONString;
    }
}
