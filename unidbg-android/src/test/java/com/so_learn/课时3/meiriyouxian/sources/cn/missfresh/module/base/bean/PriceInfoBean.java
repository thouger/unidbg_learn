package cn.missfresh.module.base.bean;

import android.os.Parcel;
import android.os.Parcelable;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class PriceInfoBean implements Parcelable {
    public static final Parcelable.Creator<PriceInfoBean> CREATOR = new AnonymousClass1();
    public CommonPrice commonPrice;
    public LinePrice linePrice;
    public int vip = 0;
    public VipPrice vipPrice;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PriceInfoBean() {
    }

    protected PriceInfoBean(Parcel parcel) {
        AppMethodBeat.i(5690, false);
        this.vipPrice = (VipPrice) parcel.readParcelable(VipPrice.class.getClassLoader());
        this.commonPrice = (CommonPrice) parcel.readParcelable(CommonPrice.class.getClassLoader());
        this.linePrice = (LinePrice) parcel.readParcelable(LinePrice.class.getClassLoader());
        this.vip = parcel.readInt();
        AppMethodBeat.o(5690);
    }

    /* renamed from: cn.missfresh.module.base.bean.PriceInfoBean$1  reason: invalid class name */
    static class AnonymousClass1 implements Parcelable.Creator<PriceInfoBean> {
        AnonymousClass1() {
        }

        @Override // android.os.Parcelable.Creator
        public PriceInfoBean createFromParcel(Parcel parcel) {
            AppMethodBeat.i(5602, false);
            PriceInfoBean priceInfoBean = new PriceInfoBean(parcel);
            AppMethodBeat.o(5602);
            return priceInfoBean;
        }

        @Override // android.os.Parcelable.Creator
        public PriceInfoBean[] newArray(int i) {
            return new PriceInfoBean[i];
        }
    }

    static {
        AppMethodBeat.i(5732, false);
        AppMethodBeat.o(5732);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        AppMethodBeat.i(5731, false);
        parcel.writeParcelable(this.vipPrice, i);
        parcel.writeParcelable(this.commonPrice, i);
        parcel.writeParcelable(this.linePrice, i);
        parcel.writeInt(this.vip);
        AppMethodBeat.o(5731);
    }

    public static class VipPrice implements Parcelable {
        public static final Parcelable.Creator<VipPrice> CREATOR = new AnonymousClass1();
        public int color;
        public int price;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public VipPrice() {
        }

        protected VipPrice(Parcel parcel) {
            AppMethodBeat.i(5680, false);
            this.color = parcel.readInt();
            this.price = parcel.readInt();
            AppMethodBeat.o(5680);
        }

        /* renamed from: cn.missfresh.module.base.bean.PriceInfoBean$VipPrice$1  reason: invalid class name */
        static class AnonymousClass1 implements Parcelable.Creator<VipPrice> {
            AnonymousClass1() {
            }

            @Override // android.os.Parcelable.Creator
            public VipPrice createFromParcel(Parcel parcel) {
                AppMethodBeat.i(5659, false);
                VipPrice vipPrice = new VipPrice(parcel);
                AppMethodBeat.o(5659);
                return vipPrice;
            }

            @Override // android.os.Parcelable.Creator
            public VipPrice[] newArray(int i) {
                return new VipPrice[i];
            }
        }

        static {
            AppMethodBeat.i(5684, false);
            AppMethodBeat.o(5684);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            AppMethodBeat.i(5683, false);
            parcel.writeInt(this.color);
            parcel.writeInt(this.price);
            AppMethodBeat.o(5683);
        }
    }

    public static class CommonPrice implements Parcelable {
        public static final Parcelable.Creator<CommonPrice> CREATOR = new AnonymousClass1();
        public int color;
        public int price;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public CommonPrice() {
        }

        protected CommonPrice(Parcel parcel) {
            AppMethodBeat.i(5612, false);
            this.color = parcel.readInt();
            this.price = parcel.readInt();
            AppMethodBeat.o(5612);
        }

        /* renamed from: cn.missfresh.module.base.bean.PriceInfoBean$CommonPrice$1  reason: invalid class name */
        static class AnonymousClass1 implements Parcelable.Creator<CommonPrice> {
            AnonymousClass1() {
            }

            @Override // android.os.Parcelable.Creator
            public CommonPrice createFromParcel(Parcel parcel) {
                AppMethodBeat.i(5607, false);
                CommonPrice commonPrice = new CommonPrice(parcel);
                AppMethodBeat.o(5607);
                return commonPrice;
            }

            @Override // android.os.Parcelable.Creator
            public CommonPrice[] newArray(int i) {
                return new CommonPrice[i];
            }
        }

        static {
            AppMethodBeat.i(5620, false);
            AppMethodBeat.o(5620);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            AppMethodBeat.i(5617, false);
            parcel.writeInt(this.color);
            parcel.writeInt(this.price);
            AppMethodBeat.o(5617);
        }
    }

    public static class LinePrice implements Parcelable {
        public static final Parcelable.Creator<LinePrice> CREATOR = new AnonymousClass1();
        public int color;
        public int price;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public LinePrice() {
        }

        protected LinePrice(Parcel parcel) {
            AppMethodBeat.i(5644, false);
            this.color = parcel.readInt();
            this.price = parcel.readInt();
            AppMethodBeat.o(5644);
        }

        /* renamed from: cn.missfresh.module.base.bean.PriceInfoBean$LinePrice$1  reason: invalid class name */
        static class AnonymousClass1 implements Parcelable.Creator<LinePrice> {
            AnonymousClass1() {
            }

            @Override // android.os.Parcelable.Creator
            public LinePrice createFromParcel(Parcel parcel) {
                AppMethodBeat.i(5628, false);
                LinePrice linePrice = new LinePrice(parcel);
                AppMethodBeat.o(5628);
                return linePrice;
            }

            @Override // android.os.Parcelable.Creator
            public LinePrice[] newArray(int i) {
                return new LinePrice[i];
            }
        }

        static {
            AppMethodBeat.i(5652, false);
            AppMethodBeat.o(5652);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            AppMethodBeat.i(5649, false);
            parcel.writeInt(this.color);
            parcel.writeInt(this.price);
            AppMethodBeat.o(5649);
        }
    }
}
