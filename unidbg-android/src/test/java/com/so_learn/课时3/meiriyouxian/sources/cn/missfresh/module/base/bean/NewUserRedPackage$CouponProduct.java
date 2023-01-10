package cn.missfresh.module.base.bean;

import android.os.Parcel;
import android.os.Parcelable;
import cn.missfresh.module.base.common.interfaces.t;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class NewUserRedPackage$CouponProduct implements Parcelable, t {
    public static final Parcelable.Creator<NewUserRedPackage$CouponProduct> CREATOR = new 1();
    public String image;
    public String name;
    public PricePro pricePro;
    public int seckillLimit;
    public String sku;
    public String skuCategory;
    public long skuCategoryId;
    public int stock;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    @Override // cn.missfresh.module.base.common.interfaces.t
    public int getMultiType() {
        return 0;
    }

    public NewUserRedPackage$CouponProduct() {
    }

    protected NewUserRedPackage$CouponProduct(Parcel parcel) {
        AppMethodBeat.i(5172, false);
        this.name = parcel.readString();
        this.image = parcel.readString();
        this.pricePro = (PricePro) parcel.readParcelable(PricePro.class.getClassLoader());
        this.sku = parcel.readString();
        this.skuCategoryId = parcel.readLong();
        this.skuCategory = parcel.readString();
        this.stock = parcel.readInt();
        this.seckillLimit = parcel.readInt();
        AppMethodBeat.o(5172);
    }

    static {
        AppMethodBeat.i(5182, false);
        AppMethodBeat.o(5182);
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        AppMethodBeat.i(5178, false);
        parcel.writeString(this.name);
        parcel.writeString(this.image);
        parcel.writeParcelable(this.pricePro, i);
        parcel.writeString(this.sku);
        parcel.writeLong(this.skuCategoryId);
        parcel.writeString(this.skuCategory);
        parcel.writeInt(this.stock);
        parcel.writeInt(this.seckillLimit);
        AppMethodBeat.o(5178);
    }
}
