package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.sina.weibo.sdk.a.d;

public class TextObject extends BaseMediaObject {
    public static final Parcelable.Creator<TextObject> CREATOR = new AnonymousClass1();
    public String text;

    @Override // com.sina.weibo.sdk.api.BaseMediaObject, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.sina.weibo.sdk.api.BaseMediaObject
    public int getObjType() {
        return 1;
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

    public TextObject() {
    }

    public TextObject(Parcel parcel) {
        this.text = parcel.readString();
    }

    /* renamed from: com.sina.weibo.sdk.api.TextObject$1  reason: invalid class name */
    class AnonymousClass1 implements Parcelable.Creator<TextObject> {
        AnonymousClass1() {
        }

        @Override // android.os.Parcelable.Creator
        public TextObject createFromParcel(Parcel parcel) {
            return new TextObject(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public TextObject[] newArray(int i) {
            return new TextObject[i];
        }
    }

    @Override // com.sina.weibo.sdk.api.BaseMediaObject, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.text);
    }

    @Override // com.sina.weibo.sdk.api.BaseMediaObject
    public boolean checkArgs() {
        String str = this.text;
        if (str != null && str.length() != 0 && this.text.length() <= 1024) {
            return true;
        }
        d.c("Weibo.TextObject", "checkArgs fail, text is invalid");
        return false;
    }
}
