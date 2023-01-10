package cn.missfresh.module.base.bean;

import android.os.Parcel;
import android.os.Parcelable;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.Serializable;

public class PromotionDeliveryTag implements Parcelable, Serializable {
    public static final Parcelable.Creator<PromotionDeliveryTag> CREATOR = new AnonymousClass1();
    private int bgColor = 0;
    private int borderColor = 0;
    private String name;
    private int nameColor = 0;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public int getNameColor() {
        return this.nameColor;
    }

    public void setNameColor(int i) {
        this.nameColor = i;
    }

    public int getBorderColor() {
        return this.borderColor;
    }

    public void setBorderColor(int i) {
        this.borderColor = i;
    }

    public int getBgColor() {
        return this.bgColor;
    }

    public void setBgColor(int i) {
        this.bgColor = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        AppMethodBeat.i(6626, false);
        parcel.writeString(this.name);
        parcel.writeInt(this.nameColor);
        parcel.writeInt(this.borderColor);
        parcel.writeInt(this.bgColor);
        AppMethodBeat.o(6626);
    }

    protected PromotionDeliveryTag(Parcel parcel) {
        AppMethodBeat.i(6629, false);
        this.name = parcel.readString();
        this.nameColor = parcel.readInt();
        this.borderColor = parcel.readInt();
        this.bgColor = parcel.readInt();
        AppMethodBeat.o(6629);
    }

    public PromotionDeliveryTag() {
    }

    /* renamed from: cn.missfresh.module.base.bean.PromotionDeliveryTag$1  reason: invalid class name */
    static class AnonymousClass1 implements Parcelable.Creator<PromotionDeliveryTag> {
        AnonymousClass1() {
        }

        @Override // android.os.Parcelable.Creator
        public PromotionDeliveryTag createFromParcel(Parcel parcel) {
            AppMethodBeat.i(6604, false);
            PromotionDeliveryTag promotionDeliveryTag = new PromotionDeliveryTag(parcel);
            AppMethodBeat.o(6604);
            return promotionDeliveryTag;
        }

        @Override // android.os.Parcelable.Creator
        public PromotionDeliveryTag[] newArray(int i) {
            return new PromotionDeliveryTag[i];
        }
    }

    static {
        AppMethodBeat.i(6636, false);
        AppMethodBeat.o(6636);
    }
}
