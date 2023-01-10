package cn.missfresh.module.base.bean;

import android.os.Parcel;
import android.os.Parcelable;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSON;
import java.io.Serializable;

public class QRInfo implements Parcelable, Serializable {
    public static final Parcelable.Creator<QRInfo> CREATOR = new AnonymousClass1();
    public String bg_i_url;
    public int qr_size;
    public int qr_x;
    public int qr_y;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public QRInfo() {
    }

    protected QRInfo(Parcel parcel) {
        AppMethodBeat.i(6662, false);
        this.bg_i_url = parcel.readString();
        this.qr_size = parcel.readInt();
        this.qr_x = parcel.readInt();
        this.qr_y = parcel.readInt();
        AppMethodBeat.o(6662);
    }

    /* renamed from: cn.missfresh.module.base.bean.QRInfo$1  reason: invalid class name */
    static class AnonymousClass1 implements Parcelable.Creator<QRInfo> {
        AnonymousClass1() {
        }

        @Override // android.os.Parcelable.Creator
        public QRInfo createFromParcel(Parcel parcel) {
            AppMethodBeat.i(6658, false);
            QRInfo qRInfo = new QRInfo(parcel);
            AppMethodBeat.o(6658);
            return qRInfo;
        }

        @Override // android.os.Parcelable.Creator
        public QRInfo[] newArray(int i) {
            return new QRInfo[i];
        }
    }

    static {
        AppMethodBeat.i(6677, false);
        AppMethodBeat.o(6677);
    }

    public String getBg_i_url() {
        return this.bg_i_url;
    }

    public void setBg_i_url(String str) {
        this.bg_i_url = str;
    }

    public int getQr_size() {
        return this.qr_size;
    }

    public void setQr_size(int i) {
        this.qr_size = i;
    }

    public int getQr_x() {
        return this.qr_x;
    }

    public void setQr_x(int i) {
        this.qr_x = i;
    }

    public int getQr_y() {
        return this.qr_y;
    }

    public void setQr_y(int i) {
        this.qr_y = i;
    }

    @Override // java.lang.Object
    public String toString() {
        AppMethodBeat.i(6673, false);
        String jSONString = JSON.toJSONString(this);
        AppMethodBeat.o(6673);
        return jSONString;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        AppMethodBeat.i(6675, false);
        parcel.writeString(this.bg_i_url);
        parcel.writeInt(this.qr_size);
        parcel.writeInt(this.qr_x);
        parcel.writeInt(this.qr_y);
        AppMethodBeat.o(6675);
    }
}
