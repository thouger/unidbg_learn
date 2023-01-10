package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import org.json.JSONException;
import org.json.JSONObject;

public class WebpageObject extends BaseMediaObject {
    public static final Parcelable.Creator<WebpageObject> CREATOR = new AnonymousClass1();
    public static final String EXTRA_KEY_DEFAULTTEXT = "extra_key_defaulttext";
    public String defaultText;

    @Override // com.sina.weibo.sdk.api.BaseMediaObject
    public int getObjType() {
        return 5;
    }

    /* renamed from: com.sina.weibo.sdk.api.WebpageObject$1  reason: invalid class name */
    class AnonymousClass1 implements Parcelable.Creator<WebpageObject> {
        AnonymousClass1() {
        }

        @Override // android.os.Parcelable.Creator
        public WebpageObject createFromParcel(Parcel parcel) {
            return new WebpageObject(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public WebpageObject[] newArray(int i) {
            return new WebpageObject[i];
        }
    }

    public WebpageObject() {
    }

    public WebpageObject(Parcel parcel) {
        super(parcel);
    }

    @Override // com.sina.weibo.sdk.api.BaseMediaObject, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
    }

    @Override // com.sina.weibo.sdk.api.BaseMediaObject
    public boolean checkArgs() {
        return super.checkArgs();
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
