package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.sina.weibo.sdk.a.d;
import org.json.JSONException;
import org.json.JSONObject;

public class VoiceObject extends BaseMediaObject {
    public static final Parcelable.Creator<VoiceObject> CREATOR = new AnonymousClass1();
    public static final String EXTRA_KEY_DEFAULTTEXT = "extra_key_defaulttext";
    public String dataHdUrl;
    public String dataUrl;
    public String defaultText;
    public int duration;
    public String h5Url;

    @Override // com.sina.weibo.sdk.api.BaseMediaObject
    public int getObjType() {
        return 6;
    }

    /* renamed from: com.sina.weibo.sdk.api.VoiceObject$1  reason: invalid class name */
    class AnonymousClass1 implements Parcelable.Creator<VoiceObject> {
        AnonymousClass1() {
        }

        @Override // android.os.Parcelable.Creator
        public VoiceObject createFromParcel(Parcel parcel) {
            return new VoiceObject(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public VoiceObject[] newArray(int i) {
            return new VoiceObject[i];
        }
    }

    public VoiceObject() {
    }

    public VoiceObject(Parcel parcel) {
        super(parcel);
        this.h5Url = parcel.readString();
        this.dataUrl = parcel.readString();
        this.dataHdUrl = parcel.readString();
        this.duration = parcel.readInt();
    }

    @Override // com.sina.weibo.sdk.api.BaseMediaObject, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.h5Url);
        parcel.writeString(this.dataUrl);
        parcel.writeString(this.dataHdUrl);
        parcel.writeInt(this.duration);
    }

    @Override // com.sina.weibo.sdk.api.BaseMediaObject
    public boolean checkArgs() {
        if (!super.checkArgs()) {
            return false;
        }
        String str = this.dataUrl;
        if (str == null || str.length() <= 512) {
            String str2 = this.dataHdUrl;
            if (str2 != null && str2.length() > 512) {
                d.c("Weibo.VoiceObject", "checkArgs fail, dataHdUrl is invalid");
                return false;
            } else if (this.duration > 0) {
                return true;
            } else {
                d.c("Weibo.VoiceObject", "checkArgs fail, duration is invalid");
                return false;
            }
        } else {
            d.c("Weibo.VoiceObject", "checkArgs fail, dataUrl is invalid");
            return false;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.sina.weibo.sdk.api.BaseMediaObject
    public BaseMediaObject toExtraMediaObject(String str) {
        if (!TextUtils.isEmpty(str)) {
            try {
                this.defaultText = new JSONObject(str).optString("extra_key_defaulttext");
            } catch (JSONException unused) {
            }
        }
        return this;
    }

    /* access modifiers changed from: protected */
    @Override // com.sina.weibo.sdk.api.BaseMediaObject
    public String toExtraMediaString() {
        try {
            JSONObject jSONObject = new JSONObject();
            if (!TextUtils.isEmpty(this.defaultText)) {
                jSONObject.put("extra_key_defaulttext", this.defaultText);
            }
            return jSONObject.toString();
        } catch (JSONException unused) {
            return "";
        }
    }
}
