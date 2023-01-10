package cn.missfresh.module.base.support.modelsupport.event;

import cn.missfresh.module.base.support.modelsupport.event.base.BaseReqEvent;
import cn.missfresh.module.base.support.modelsupport.event.base.BaseResEvent;
import cn.missfresh.module.base.support.modelsupport.event.bean.SocialBeans;

public class EventSocialResData extends BaseResEvent {
    private SocialBeans.getShareImgUrlRes imgUrlRes;
    private SocialBeans.InviteFriendsInfo inviteFriendsInfo;
    private SocialBeans.GetInviteMainPageRes mainPageRes;

    public EventSocialResData(BaseReqEvent baseReqEvent) {
        super(baseReqEvent);
    }

    public SocialBeans.InviteFriendsInfo getInviteFriendsInfo() {
        return this.inviteFriendsInfo;
    }

    public void setInviteFriendsInfo(SocialBeans.InviteFriendsInfo inviteFriendsInfo) {
        this.inviteFriendsInfo = inviteFriendsInfo;
    }

    public SocialBeans.getShareImgUrlRes getImgUrlRes() {
        return this.imgUrlRes;
    }

    public void setImgUrlRes(SocialBeans.getShareImgUrlRes getshareimgurlres) {
        this.imgUrlRes = getshareimgurlres;
    }

    public SocialBeans.GetInviteMainPageRes getMainPageRes() {
        return this.mainPageRes;
    }

    public void setMainPageRes(SocialBeans.GetInviteMainPageRes getInviteMainPageRes) {
        this.mainPageRes = getInviteMainPageRes;
    }
}
