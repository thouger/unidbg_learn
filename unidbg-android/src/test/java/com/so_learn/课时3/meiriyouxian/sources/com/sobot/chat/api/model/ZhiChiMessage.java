package com.sobot.chat.api.model;

import android.os.Parcel;
import android.os.Parcelable;

public class ZhiChiMessage extends BaseCode<ZhiChiMessageBase> implements Parcelable {
    private String msg;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
    }

    @Override // com.sobot.chat.api.model.BaseCode
    public String getMsg() {
        return this.msg;
    }

    @Override // com.sobot.chat.api.model.BaseCode
    public void setMsg(String str) {
        this.msg = str;
    }
}
