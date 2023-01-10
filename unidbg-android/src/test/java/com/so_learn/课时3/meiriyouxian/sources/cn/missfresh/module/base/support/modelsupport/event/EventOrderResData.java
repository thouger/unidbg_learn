package cn.missfresh.module.base.support.modelsupport.event;

import cn.missfresh.module.base.bean.ShareInfo;
import cn.missfresh.module.base.support.modelsupport.event.base.BaseReqEvent;
import cn.missfresh.module.base.support.modelsupport.event.base.BaseResEvent;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSONObject;

public class EventOrderResData extends BaseResEvent {
    private ChargeSucInfo chargeSucInfo;
    private VipTipsRes vipTipsRes;

    public VipTipsRes getVipTipsRes() {
        return this.vipTipsRes;
    }

    public void setVipTipsRes(VipTipsRes vipTipsRes) {
        this.vipTipsRes = vipTipsRes;
    }

    public ChargeSucInfo getChargeSucInfo() {
        return this.chargeSucInfo;
    }

    public void setChargeSucInfo(ChargeSucInfo chargeSucInfo) {
        this.chargeSucInfo = chargeSucInfo;
    }

    public EventOrderResData(BaseReqEvent baseReqEvent) {
        super(baseReqEvent);
    }

    public static class MryxPayRes {
        private String error_code;
        private String error_msg;
        private int status = -1;

        public int getStatus() {
            return this.status;
        }

        public void setStatus(int i) {
            this.status = i;
        }

        public String getError_code() {
            return this.error_code;
        }

        public void setError_code(String str) {
            this.error_code = str;
        }

        public String getError_msg() {
            return this.error_msg;
        }

        public void setError_msg(String str) {
            this.error_msg = str;
        }
    }

    public static class ChargeSucInfo {
        private String amount_content;
        private ShareInfo share_content;
        private String vip_content;

        public String getVip_content() {
            return this.vip_content;
        }

        public void setVip_content(String str) {
            this.vip_content = str;
        }

        public String getAmount_content() {
            return this.amount_content;
        }

        public void setAmount_content(String str) {
            this.amount_content = str;
        }

        public ShareInfo getShare_content() {
            return this.share_content;
        }

        public void setShare_content(ShareInfo shareInfo) {
            this.share_content = shareInfo;
        }

        public String toString() {
            AppMethodBeat.i(22043, false);
            String jSONString = JSONObject.toJSONString(this);
            AppMethodBeat.o(22043);
            return jSONString;
        }
    }

    public static class VipTipsRes {
        private String content_down;
        private String content_up;
        private String firsttime_content;
        private boolean need_popup;

        public boolean isNeed_popup() {
            return this.need_popup;
        }

        public void setNeed_popup(boolean z) {
            this.need_popup = z;
        }

        public String getContent_up() {
            return this.content_up;
        }

        public void setContent_up(String str) {
            this.content_up = str;
        }

        public String getContent_down() {
            return this.content_down;
        }

        public void setContent_down(String str) {
            this.content_down = str;
        }

        public String getFirsttime_content() {
            return this.firsttime_content;
        }

        public void setFirsttime_content(String str) {
            this.firsttime_content = str;
        }

        public String toString() {
            AppMethodBeat.i(22044, false);
            String jSONString = JSONObject.toJSONString(this);
            AppMethodBeat.o(22044);
            return jSONString;
        }
    }
}
