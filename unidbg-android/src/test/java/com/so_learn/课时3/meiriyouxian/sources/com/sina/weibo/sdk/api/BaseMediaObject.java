package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.sina.weibo.sdk.a.d;

public abstract class BaseMediaObject implements Parcelable {
    public static final int MEDIA_TYPE_CMD = 7;
    public static final int MEDIA_TYPE_IMAGE = 2;
    public static final int MEDIA_TYPE_MUSIC = 3;
    public static final int MEDIA_TYPE_TEXT = 1;
    public static final int MEDIA_TYPE_VIDEO = 4;
    public static final int MEDIA_TYPE_VOICE = 6;
    public static final int MEDIA_TYPE_WEBPAGE = 5;
    public String actionUrl;
    public String description;
    public String identify;
    public String schema;
    public byte[] thumbData;
    public String title;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public abstract int getObjType();

    /* access modifiers changed from: protected */
    public abstract BaseMediaObject toExtraMediaObject(String str);

    /* access modifiers changed from: protected */
    public abstract String toExtraMediaString();

    public BaseMediaObject() {
    }

    public BaseMediaObject(Parcel parcel) {
        this.actionUrl = parcel.readString();
        this.schema = parcel.readString();
        this.identify = parcel.readString();
        this.title = parcel.readString();
        this.description = parcel.readString();
        this.thumbData = parcel.createByteArray();
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0039 A[SYNTHETIC, Splitter:B:20:0x0039] */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setThumbImage(android.graphics.Bitmap r4) {
        /*
            r3 = this;
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x001f }
            r1.<init>()     // Catch:{ Exception -> 0x001f }
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ Exception -> 0x001a, all -> 0x0017 }
            r2 = 85
            r4.compress(r0, r2, r1)     // Catch:{ Exception -> 0x001a, all -> 0x0017 }
            byte[] r4 = r1.toByteArray()     // Catch:{ Exception -> 0x001a, all -> 0x0017 }
            r3.thumbData = r4     // Catch:{ Exception -> 0x001a, all -> 0x0017 }
            r1.close()     // Catch:{ IOException -> 0x0032 }
            goto L_0x0036
        L_0x0017:
            r4 = move-exception
            r0 = r1
            goto L_0x0037
        L_0x001a:
            r4 = move-exception
            r0 = r1
            goto L_0x0020
        L_0x001d:
            r4 = move-exception
            goto L_0x0037
        L_0x001f:
            r4 = move-exception
        L_0x0020:
            r4.printStackTrace()     // Catch:{ all -> 0x001d }
            java.lang.String r4 = "Weibo.BaseMediaObject"
            java.lang.String r1 = "put thumb failed"
            com.sina.weibo.sdk.a.d.c(r4, r1)     // Catch:{ all -> 0x001d }
            if (r0 == 0) goto L_0x0036
            r0.close()
            goto L_0x0036
        L_0x0032:
            r4 = move-exception
            r4.printStackTrace()
        L_0x0036:
            return
        L_0x0037:
            if (r0 == 0) goto L_0x0041
            r0.close()     // Catch:{ IOException -> 0x003d }
            goto L_0x0041
        L_0x003d:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0041:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.api.BaseMediaObject.setThumbImage(android.graphics.Bitmap):void");
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.actionUrl);
        parcel.writeString(this.schema);
        parcel.writeString(this.identify);
        parcel.writeString(this.title);
        parcel.writeString(this.description);
        parcel.writeByteArray(this.thumbData);
    }

    /* access modifiers changed from: protected */
    public boolean checkArgs() {
        String str = this.actionUrl;
        if (str == null || str.length() > 512) {
            d.c("Weibo.BaseMediaObject", "checkArgs fail, actionUrl is invalid");
            return false;
        }
        String str2 = this.identify;
        if (str2 == null || str2.length() > 512) {
            d.c("Weibo.BaseMediaObject", "checkArgs fail, identify is invalid");
            return false;
        }
        byte[] bArr = this.thumbData;
        if (bArr == null || bArr.length > 32768) {
            StringBuilder sb = new StringBuilder("checkArgs fail, thumbData is invalid,size is ");
            byte[] bArr2 = this.thumbData;
            sb.append(bArr2 != null ? bArr2.length : -1);
            sb.append("! more then 32768.");
            d.c("Weibo.BaseMediaObject", sb.toString());
            return false;
        }
        String str3 = this.title;
        if (str3 == null || str3.length() > 512) {
            d.c("Weibo.BaseMediaObject", "checkArgs fail, title is invalid");
            return false;
        }
        String str4 = this.description;
        if (str4 != null && str4.length() <= 1024) {
            return true;
        }
        d.c("Weibo.BaseMediaObject", "checkArgs fail, description is invalid");
        return false;
    }
}
