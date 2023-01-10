package cn.missfresh.module.base.bean;

import android.os.Parcel;
import android.os.Parcelable;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.Serializable;

public class VipTag implements Parcelable, Serializable {
    public static final Parcelable.Creator<VipTag> CREATOR = new AnonymousClass1();
    private String bgImage;
    private String image;
    private String name;
    private int nameColor;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public int getNameColor() {
        AppMethodBeat.i(9894, false);
        int a = q.a(this.nameColor);
        AppMethodBeat.o(9894);
        return a;
    }

    public void setNameColor(int i) {
        this.nameColor = i;
    }

    public String getBgImage() {
        return this.bgImage;
    }

    public void setBgImage(String str) {
        this.bgImage = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String str) {
        this.image = str;
    }

    /* renamed from: cn.missfresh.module.base.bean.VipTag$1  reason: invalid class name */
    static class AnonymousClass1 implements Parcelable.Creator<VipTag> {
        AnonymousClass1() {
        }

        @Override // android.os.Parcelable.Creator
        public VipTag createFromParcel(Parcel parcel) {
            AppMethodBeat.i(9881, false);
            VipTag vipTag = new VipTag(parcel);
            AppMethodBeat.o(9881);
            return vipTag;
        }

        @Override // android.os.Parcelable.Creator
        public VipTag[] newArray(int i) {
            return new VipTag[i];
        }
    }

    static {
        AppMethodBeat.i(9954, false);
        AppMethodBeat.o(9954);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        AppMethodBeat.i(9911, false);
        parcel.writeInt(this.nameColor);
        parcel.writeString(this.bgImage);
        parcel.writeString(this.name);
        parcel.writeString(this.image);
        AppMethodBeat.o(9911);
    }

    protected VipTag(Parcel parcel) {
        AppMethodBeat.i(9950, false);
        this.nameColor = parcel.readInt();
        this.bgImage = parcel.readString();
        this.name = parcel.readString();
        this.image = parcel.readString();
        AppMethodBeat.o(9950);
    }

    public VipTag() {
    }
}
