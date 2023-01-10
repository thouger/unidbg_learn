package cn.missfresh.module.base.support.modelsupport.event;

import android.os.Parcel;
import android.os.Parcelable;
import cn.missfresh.module.base.bean.ShareInfo;
import cn.missfresh.module.base.manager.bean.VipProgress;
import cn.missfresh.module.base.support.modelsupport.event.base.BaseReqEvent;
import cn.missfresh.module.base.support.modelsupport.event.base.BaseResEvent;
import cn.missfresh.module.base.support.modelsupport.event.bean.VipInfoBeans;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSONObject;

public class EventVipResData extends BaseResEvent {
    private VipInfoBeans.CheckResSuc checkSucInfo;
    private VipInfoBeans.IntegralPageInfo integralPageInfo;
    private VipInfo vipInfo;
    private VipInfoBeans.VirtualProBuySuc virtualProBuySucInfo;

    public EventVipResData(BaseReqEvent baseReqEvent) {
        super(baseReqEvent);
    }

    public VipInfoBeans.CheckResSuc getCheckSucInfo() {
        return this.checkSucInfo;
    }

    public void setCheckSucInfo(VipInfoBeans.CheckResSuc checkResSuc) {
        this.checkSucInfo = checkResSuc;
    }

    public VipInfoBeans.VirtualProBuySuc getVirtualProBuySucInfo() {
        return this.virtualProBuySucInfo;
    }

    public void setVirtualProBuySucInfo(VipInfoBeans.VirtualProBuySuc virtualProBuySuc) {
        this.virtualProBuySucInfo = virtualProBuySuc;
    }

    public VipInfo getVipInfo() {
        return this.vipInfo;
    }

    public void setVipInfo(VipInfo vipInfo) {
        this.vipInfo = vipInfo;
    }

    public VipInfoBeans.IntegralPageInfo getIntegralPageInfo() {
        return this.integralPageInfo;
    }

    public void setIntegralPageInfo(VipInfoBeans.IntegralPageInfo integralPageInfo) {
        this.integralPageInfo = integralPageInfo;
    }

    public static class VipInfo extends BaseResEvent.baseNetRes implements Parcelable {
        public static final Parcelable.Creator<VipInfo> CREATOR = new AnonymousClass1();
        private String bg_img;
        private String content_down;
        private String content_up;
        private GroupInfo growth_info;
        private String invitor_profit;
        private boolean is_vip;
        private int memberLevel;
        private ShareInfo share_invite_content;
        private VipProgress vip_progress;
        private int vip_type;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public VipInfo() {
        }

        protected VipInfo(Parcel parcel) {
            boolean z = false;
            AppMethodBeat.i(22075, false);
            this.is_vip = parcel.readByte() != 0 ? true : z;
            this.memberLevel = parcel.readInt();
            this.content_down = parcel.readString();
            this.vip_progress = (VipProgress) parcel.readParcelable(VipProgress.class.getClassLoader());
            this.content_up = parcel.readString();
            this.invitor_profit = parcel.readString();
            this.bg_img = parcel.readString();
            this.vip_type = parcel.readInt();
            this.growth_info = (GroupInfo) parcel.readParcelable(GroupInfo.class.getClassLoader());
            this.share_invite_content = (ShareInfo) parcel.readParcelable(ShareInfo.class.getClassLoader());
            AppMethodBeat.o(22075);
        }

        /* renamed from: cn.missfresh.module.base.support.modelsupport.event.EventVipResData$VipInfo$1  reason: invalid class name */
        static class AnonymousClass1 implements Parcelable.Creator<VipInfo> {
            AnonymousClass1() {
            }

            @Override // android.os.Parcelable.Creator
            public VipInfo createFromParcel(Parcel parcel) {
                AppMethodBeat.i(22072, false);
                VipInfo vipInfo = new VipInfo(parcel);
                AppMethodBeat.o(22072);
                return vipInfo;
            }

            @Override // android.os.Parcelable.Creator
            public VipInfo[] newArray(int i) {
                return new VipInfo[i];
            }
        }

        static {
            AppMethodBeat.i(22080, false);
            AppMethodBeat.o(22080);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            AppMethodBeat.i(22076, false);
            parcel.writeByte(this.is_vip ? (byte) 1 : 0);
            parcel.writeInt(this.memberLevel);
            parcel.writeString(this.content_down);
            parcel.writeParcelable(this.vip_progress, i);
            parcel.writeString(this.content_up);
            parcel.writeString(this.invitor_profit);
            parcel.writeString(this.bg_img);
            parcel.writeInt(this.vip_type);
            parcel.writeParcelable(this.growth_info, i);
            parcel.writeParcelable(this.share_invite_content, i);
            AppMethodBeat.o(22076);
        }

        public GroupInfo getGrowth_info() {
            return this.growth_info;
        }

        public String getInvitor_profit() {
            return this.invitor_profit;
        }

        public void setInvitor_profit(String str) {
            this.invitor_profit = str;
        }

        public void setGrowth_info(GroupInfo groupInfo) {
            this.growth_info = groupInfo;
        }

        public int getVip_type() {
            return this.vip_type;
        }

        public void setVip_type(int i) {
            this.vip_type = i;
        }

        public int getMemberLevel() {
            return this.memberLevel;
        }

        public void setMemberLevel(int i) {
            this.memberLevel = i;
        }

        public ShareInfo getShare_invite_content() {
            return this.share_invite_content;
        }

        public void setShare_invite_content(ShareInfo shareInfo) {
            this.share_invite_content = shareInfo;
        }

        public String getBg_img() {
            return this.bg_img;
        }

        public void setBg_img(String str) {
            this.bg_img = str;
        }

        public boolean is_vip() {
            return this.is_vip;
        }

        public void setIs_vip(boolean z) {
            this.is_vip = z;
        }

        public String getContent_down() {
            return this.content_down;
        }

        public void setContent_down(String str) {
            this.content_down = str;
        }

        public VipProgress getVip_progress() {
            return this.vip_progress;
        }

        public void setVip_progress(VipProgress vipProgress) {
            this.vip_progress = vipProgress;
        }

        public String getContent_up() {
            return this.content_up;
        }

        public void setContent_up(String str) {
            this.content_up = str;
        }

        public String toString() {
            AppMethodBeat.i(22079, false);
            String jSONString = JSONObject.toJSONString(this);
            AppMethodBeat.o(22079);
            return jSONString;
        }
    }

    public static class GroupInfo implements Parcelable {
        public static final Parcelable.Creator<GroupInfo> CREATOR = new AnonymousClass1();
        private String content;
        private String end_time;
        private String growth_discount;
        private String title;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public GroupInfo() {
        }

        protected GroupInfo(Parcel parcel) {
            AppMethodBeat.i(22069, false);
            this.title = parcel.readString();
            this.growth_discount = parcel.readString();
            this.content = parcel.readString();
            this.end_time = parcel.readString();
            AppMethodBeat.o(22069);
        }

        /* renamed from: cn.missfresh.module.base.support.modelsupport.event.EventVipResData$GroupInfo$1  reason: invalid class name */
        static class AnonymousClass1 implements Parcelable.Creator<GroupInfo> {
            AnonymousClass1() {
            }

            @Override // android.os.Parcelable.Creator
            public GroupInfo createFromParcel(Parcel parcel) {
                AppMethodBeat.i(22066, false);
                GroupInfo groupInfo = new GroupInfo(parcel);
                AppMethodBeat.o(22066);
                return groupInfo;
            }

            @Override // android.os.Parcelable.Creator
            public GroupInfo[] newArray(int i) {
                return new GroupInfo[i];
            }
        }

        static {
            AppMethodBeat.i(22071, false);
            AppMethodBeat.o(22071);
        }

        public String getTitle() {
            return this.title;
        }

        public void setTitle(String str) {
            this.title = str;
        }

        public String getGrowth_discount() {
            return this.growth_discount;
        }

        public void setGrowth_discount(String str) {
            this.growth_discount = str;
        }

        public String getContent() {
            return this.content;
        }

        public void setContent(String str) {
            this.content = str;
        }

        public String getEnd_time() {
            return this.end_time;
        }

        public void setEnd_time(String str) {
            this.end_time = str;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            AppMethodBeat.i(22070, false);
            parcel.writeString(this.title);
            parcel.writeString(this.growth_discount);
            parcel.writeString(this.content);
            parcel.writeString(this.end_time);
            AppMethodBeat.o(22070);
        }
    }
}
