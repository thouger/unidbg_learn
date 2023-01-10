package cn.missfresh.module.base.bean;

import android.os.Parcel;
import android.os.Parcelable;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;
import java.io.Serializable;

public class ShareInfo implements Parcelable, Serializable {
    public static final Parcelable.Creator<ShareInfo> CREATOR = new AnonymousClass1();
    public String bottom_image_url;
    public String content;
    public int friend_share_type;
    public String friend_url;
    public String image_url;
    public MiniAppShareInfo miniAppShareInfo;
    public String origin_image_url;
    public PosterInfo posterInfo;
    public String poster_img_url;
    public int qqFriendShareType;
    public String qqFriendUrl;
    public QRInfo qrInfo;
    public String sinaImageUrl;
    public String sinaMsg;
    public String sina_url;
    public String title;
    public int wx_share_type;
    public String wx_url;

    public interface IshareType {
        public static final int img = 1;
        public static final int link = 0;
        public static final int smallRoutine = 2;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public ShareInfo() {
    }

    protected ShareInfo(Parcel parcel) {
        AppMethodBeat.i(6991, false);
        this.title = parcel.readString();
        this.content = parcel.readString();
        this.wx_url = parcel.readString();
        this.image_url = parcel.readString();
        this.friend_url = parcel.readString();
        this.origin_image_url = parcel.readString();
        this.sina_url = parcel.readString();
        this.wx_share_type = parcel.readInt();
        this.friend_share_type = parcel.readInt();
        this.qrInfo = (QRInfo) parcel.readParcelable(QRInfo.class.getClassLoader());
        this.miniAppShareInfo = parcel.readParcelable(MiniAppShareInfo.class.getClassLoader());
        this.qqFriendShareType = parcel.readInt();
        this.qqFriendUrl = parcel.readString();
        this.posterInfo = parcel.readParcelable(PosterInfo.class.getClassLoader());
        AppMethodBeat.o(6991);
    }

    /* renamed from: cn.missfresh.module.base.bean.ShareInfo$1  reason: invalid class name */
    static class AnonymousClass1 implements Parcelable.Creator<ShareInfo> {
        AnonymousClass1() {
        }

        @Override // android.os.Parcelable.Creator
        public ShareInfo createFromParcel(Parcel parcel) {
            AppMethodBeat.i(6982, false);
            ShareInfo shareInfo = new ShareInfo(parcel);
            AppMethodBeat.o(6982);
            return shareInfo;
        }

        @Override // android.os.Parcelable.Creator
        public ShareInfo[] newArray(int i) {
            return new ShareInfo[i];
        }
    }

    static {
        AppMethodBeat.i(7029, false);
        AppMethodBeat.o(7029);
    }

    public QRInfo getQrInfo() {
        return this.qrInfo;
    }

    public void setQrInfo(QRInfo qRInfo) {
        this.qrInfo = qRInfo;
    }

    public String getSina_url() {
        return this.sina_url;
    }

    public void setSina_url(String str) {
        this.sina_url = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String str) {
        this.content = str;
    }

    public String getWx_url() {
        return this.wx_url;
    }

    public void setWx_url(String str) {
        this.wx_url = str;
    }

    public String getImage_url() {
        return this.image_url;
    }

    public void setImage_url(String str) {
        this.image_url = str;
    }

    public String getFriend_url() {
        return this.friend_url;
    }

    public void setFriend_url(String str) {
        this.friend_url = str;
    }

    public String getOrigin_image_url() {
        return this.origin_image_url;
    }

    public void setOrigin_image_url(String str) {
        this.origin_image_url = str;
    }

    public int getWx_share_type() {
        return this.wx_share_type;
    }

    public int getFriend_share_type() {
        return this.friend_share_type;
    }

    public int getQqFriendShareType() {
        return this.qqFriendShareType;
    }

    public void setQqFriendShareType(int i) {
        this.qqFriendShareType = i;
    }

    public String getQqFriendUrl() {
        return this.qqFriendUrl;
    }

    public void setQqFriendUrl(String str) {
        this.qqFriendUrl = str;
    }

    @Override // java.lang.Object
    public String toString() {
        AppMethodBeat.i(7020, false);
        String jSONString = JSON.toJSONString(this);
        AppMethodBeat.o(7020);
        return jSONString;
    }

    public MiniAppShareInfo getMiniAppShareInfo() {
        return this.miniAppShareInfo;
    }

    public void setMiniAppShareInfo(MiniAppShareInfo miniAppShareInfo) {
        this.miniAppShareInfo = miniAppShareInfo;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        AppMethodBeat.i(7026, false);
        parcel.writeString(this.title);
        parcel.writeString(this.content);
        parcel.writeString(this.wx_url);
        parcel.writeString(this.image_url);
        parcel.writeString(this.friend_url);
        parcel.writeString(this.origin_image_url);
        parcel.writeString(this.sina_url);
        parcel.writeInt(this.wx_share_type);
        parcel.writeInt(this.friend_share_type);
        parcel.writeParcelable(this.qrInfo, i);
        parcel.writeParcelable(this.miniAppShareInfo, i);
        parcel.writeInt(this.qqFriendShareType);
        parcel.writeString(this.qqFriendUrl);
        parcel.writeParcelable(this.posterInfo, i);
        AppMethodBeat.o(7026);
    }
}
