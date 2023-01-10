package com.sina.weibo.sdk.api;

import android.os.Parcel;
import android.os.Parcelable;
import com.sina.weibo.sdk.a.d;
import java.io.File;

public class ImageObject extends BaseMediaObject {
    public static final Parcelable.Creator<ImageObject> CREATOR = new AnonymousClass1();
    private static final int DATA_SIZE = 2097152;
    public byte[] imageData;
    public String imagePath;

    @Override // com.sina.weibo.sdk.api.BaseMediaObject, android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // com.sina.weibo.sdk.api.BaseMediaObject
    public int getObjType() {
        return 2;
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

    /* renamed from: com.sina.weibo.sdk.api.ImageObject$1  reason: invalid class name */
    class AnonymousClass1 implements Parcelable.Creator<ImageObject> {
        AnonymousClass1() {
        }

        @Override // android.os.Parcelable.Creator
        public ImageObject createFromParcel(Parcel parcel) {
            return new ImageObject(parcel);
        }

        @Override // android.os.Parcelable.Creator
        public ImageObject[] newArray(int i) {
            return new ImageObject[i];
        }
    }

    public ImageObject() {
    }

    public ImageObject(Parcel parcel) {
        this.imageData = parcel.createByteArray();
        this.imagePath = parcel.readString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x002e  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0039 A[SYNTHETIC, Splitter:B:20:0x0039] */
    /* JADX WARNING: Removed duplicated region for block: B:26:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setImageObject(android.graphics.Bitmap r4) {
        /*
            r3 = this;
            r0 = 0
            java.io.ByteArrayOutputStream r1 = new java.io.ByteArrayOutputStream     // Catch:{ Exception -> 0x001f }
            r1.<init>()     // Catch:{ Exception -> 0x001f }
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.JPEG     // Catch:{ Exception -> 0x001a, all -> 0x0017 }
            r2 = 85
            r4.compress(r0, r2, r1)     // Catch:{ Exception -> 0x001a, all -> 0x0017 }
            byte[] r4 = r1.toByteArray()     // Catch:{ Exception -> 0x001a, all -> 0x0017 }
            r3.imageData = r4     // Catch:{ Exception -> 0x001a, all -> 0x0017 }
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
            java.lang.String r4 = "Weibo.ImageObject"
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
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.api.ImageObject.setImageObject(android.graphics.Bitmap):void");
    }

    @Override // com.sina.weibo.sdk.api.BaseMediaObject, android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByteArray(this.imageData);
        parcel.writeString(this.imagePath);
    }

    @Override // com.sina.weibo.sdk.api.BaseMediaObject
    public boolean checkArgs() {
        if (this.imageData == null && this.imagePath == null) {
            d.c("Weibo.ImageObject", "imageData and imagePath are null");
            return false;
        }
        byte[] bArr = this.imageData;
        if (bArr == null || bArr.length <= 2097152) {
            String str = this.imagePath;
            if (str == null || str.length() <= 512) {
                String str2 = this.imagePath;
                if (str2 == null) {
                    return true;
                }
                File file = new File(str2);
                try {
                    if (file.exists() && file.length() != 0 && file.length() <= 10485760) {
                        return true;
                    }
                    d.c("Weibo.ImageObject", "checkArgs fail, image content is too large or not exists");
                    return false;
                } catch (SecurityException unused) {
                    d.c("Weibo.ImageObject", "checkArgs fail, image content is too large or not exists");
                    return false;
                }
            } else {
                d.c("Weibo.ImageObject", "imagePath is too length");
                return false;
            }
        } else {
            d.c("Weibo.ImageObject", "imageData is too large");
            return false;
        }
    }
}
