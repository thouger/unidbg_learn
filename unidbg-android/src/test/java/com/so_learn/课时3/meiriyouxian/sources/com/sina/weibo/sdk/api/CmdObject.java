package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable;

public class CmdObject extends BaseMediaObject {
    public static final String CMD_HOME = "home";
    public static final Parcelable.Creator<CmdObject> CREATOR = new AnonymousClass1();
    public String cmd;

    @Override // com.sina.weibo.sdk.api.BaseMediaObject, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.sina.weibo.sdk.api.BaseMediaObject
    public int getObjType() {
        return 7;
    }

    /* access modifiers changed from: protected */
    @Override // com.sina.weibo.sdk.api.BaseMediaObject
    public BaseMediaObject toExtraMediaObject(String str) {
        return this;
    }

    /* access modifiers changed from: protected */
    @Override // com.sina.weibo.sdk.api.BaseMediaObject
    public String toExtraMediaString() {
        return "";
    }

    /* renamed from: com.sina.weibo.sdk.api.CmdObject$1  reason: invalid class name */
    class AnonymousClass1 implements Parcelable.Creator<CmdObject> {
        AnonymousClass1() {
        }

        @Override // android.os.Parcelable.Creator
        public CmdObject createFromParcel(Parcel parcel) {
            return new CmdObject(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public CmdObject[] newArray(int i) {
            return new CmdObject[i];
        }
    }

    public CmdObject() {
    }

    public CmdObject(Parcel parcel) {
        this.cmd = parcel.readString();
    }

    @Override // com.sina.weibo.sdk.api.BaseMediaObject, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.cmd);
    }

    @Override // com.sina.weibo.sdk.api.BaseMediaObject
    public boolean checkArgs() {
        String str = this.cmd;
        return (str == null || str.length() == 0 || this.cmd.length() > 1024) ? false : true;
    }
}
