package cn.missfresh.module.base.bean;

import android.os.Parcel;
import android.os.Parcelable;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.Serializable;

public class PricePro implements Parcelable, Serializable {
    public static final Parcelable.Creator<PricePro> CREATOR = new AnonymousClass1();
    public static final int DELETE = 2;
    public static final int HIDE = 0;
    public static final int NORMAL = 1;
    private Price noVip;
    public Price price_down;
    public Price price_up;
    private Price vip;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public PricePro() {
    }

    protected PricePro(Parcel parcel) {
        AppMethodBeat.i(5814, false);
        this.price_up = (Price) parcel.readParcelable(Price.class.getClassLoader());
        this.price_down = (Price) parcel.readParcelable(Price.class.getClassLoader());
        this.vip = (Price) parcel.readParcelable(Price.class.getClassLoader());
        this.noVip = (Price) parcel.readParcelable(Price.class.getClassLoader());
        AppMethodBeat.o(5814);
    }

    /* renamed from: cn.missfresh.module.base.bean.PricePro$1  reason: invalid class name */
    static class AnonymousClass1 implements Parcelable.Creator<PricePro> {
        AnonymousClass1() {
        }

        @Override // android.os.Parcelable.Creator
        public PricePro createFromParcel(Parcel parcel) {
            AppMethodBeat.i(5735, false);
            PricePro pricePro = new PricePro(parcel);
            AppMethodBeat.o(5735);
            return pricePro;
        }

        @Override // android.os.Parcelable.Creator
        public PricePro[] newArray(int i) {
            return new PricePro[i];
        }
    }

    static {
        AppMethodBeat.i(5830, false);
        AppMethodBeat.o(5830);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        AppMethodBeat.i(5820, false);
        parcel.writeParcelable(this.price_up, i);
        parcel.writeParcelable(this.price_down, i);
        parcel.writeParcelable(this.vip, i);
        parcel.writeParcelable(this.noVip, i);
        AppMethodBeat.o(5820);
    }

    public static class Price implements Parcelable, Serializable {
        public static final Parcelable.Creator<Price> CREATOR = new AnonymousClass1();
        private int color;
        public String name;
        private int name_color;
        public int price;
        private int price_color;
        public String price_value;
        private int showStyle;
        public int showTag = 0;
        public int show_type = 1;

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public Price() {
        }

        protected Price(Parcel parcel) {
            AppMethodBeat.i(5771, false);
            this.name = parcel.readString();
            this.price = parcel.readInt();
            this.show_type = parcel.readInt();
            this.color = parcel.readInt();
            this.showStyle = parcel.readInt();
            this.name_color = parcel.readInt();
            this.price_color = parcel.readInt();
            this.showTag = parcel.readInt();
            AppMethodBeat.o(5771);
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel parcel, int i) {
            AppMethodBeat.i(5774, false);
            parcel.writeString(this.name);
            parcel.writeInt(this.price);
            parcel.writeInt(this.show_type);
            parcel.writeInt(this.color);
            parcel.writeInt(this.showStyle);
            parcel.writeInt(this.name_color);
            parcel.writeInt(this.price_color);
            parcel.writeInt(this.showTag);
            AppMethodBeat.o(5774);
        }

        /* renamed from: cn.missfresh.module.base.bean.PricePro$Price$1  reason: invalid class name */
        static class AnonymousClass1 implements Parcelable.Creator<Price> {
            AnonymousClass1() {
            }

            @Override // android.os.Parcelable.Creator
            public Price createFromParcel(Parcel parcel) {
                AppMethodBeat.i(5750, false);
                Price price = new Price(parcel);
                AppMethodBeat.o(5750);
                return price;
            }

            @Override // android.os.Parcelable.Creator
            public Price[] newArray(int i) {
                return new Price[i];
            }
        }

        static {
            AppMethodBeat.i(5803, false);
            AppMethodBeat.o(5803);
        }

        public int getPrice_color() {
            return this.price_color;
        }

        public void setPrice_color(int i) {
            this.price_color = i;
        }

        public int getName_color() {
            return this.name_color;
        }

        public void setName_color(int i) {
            this.name_color = i;
        }

        public int getColor() {
            AppMethodBeat.i(5788, false);
            int a = q.a(this.color);
            AppMethodBeat.o(5788);
            return a;
        }

        public void setColor(int i) {
            this.color = i;
        }

        public int getPrice() {
            return this.price;
        }

        public void setPrice(int i) {
            this.price = i;
        }

        public int getShowStyle() {
            return this.showStyle;
        }

        public void setShowStyle(int i) {
            this.showStyle = i;
        }
    }

    public Price getVip() {
        return this.vip;
    }

    public void setVip(Price price) {
        this.vip = price;
    }

    public Price getNoVip() {
        return this.noVip;
    }

    public void setNoVip(Price price) {
        this.noVip = price;
    }
}
