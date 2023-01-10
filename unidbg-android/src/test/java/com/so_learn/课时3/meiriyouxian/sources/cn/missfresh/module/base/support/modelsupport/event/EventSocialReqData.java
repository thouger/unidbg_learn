package cn.missfresh.module.base.support.modelsupport.event;

import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.support.modelsupport.event.base.BaseReqEvent;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class EventSocialReqData extends BaseReqEvent {
    private int adImgType = -1;
    private String mAdConfigUrl = i.aH;
    private String originUrl;

    public interface IAciontType {
        public static final int t_get_ad_img_url = 2;
        public static final int t_get_inviet_mainpage_info = 3;
        public static final int t_get_invite_friend_info = 1;
    }

    public interface IGetAdType {
        public static final int defaultType = -1;
        public static final int orderAdType = 1;
    }

    public EventSocialReqData() {
        AppMethodBeat.i(22050, false);
        setReqType(1003);
        AppMethodBeat.o(22050);
    }

    public int getAdImgType() {
        return this.adImgType;
    }

    public void setAdImgType(int i) {
        this.adImgType = i;
    }

    public String getOriginUrl() {
        return this.originUrl;
    }

    public void setOriginUrl(String str) {
        this.originUrl = str;
    }

    public void setAdConfigUrl(String str) {
        this.mAdConfigUrl = str;
    }

    public String getAdConfigUrl() {
        return this.mAdConfigUrl;
    }

    public void postEvent(int i) {
        AppMethodBeat.i(22054, false);
        setReqDetailType(i);
        post();
        AppMethodBeat.o(22054);
    }
}
