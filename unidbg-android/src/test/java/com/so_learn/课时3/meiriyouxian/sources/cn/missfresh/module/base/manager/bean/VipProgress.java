package cn.missfresh.module.base.manager.bean;

import android.os.Parcel;
import android.os.Parcelable;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class VipProgress implements Parcelable {
    public static final Parcelable.Creator<VipProgress> CREATOR = new AnonymousClass1();
    int current_order_num;
    int vip_order_num;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public VipProgress() {
    }

    protected VipProgress(Parcel parcel) {
        AppMethodBeat.i(15113, false);
        this.vip_order_num = parcel.readInt();
        this.current_order_num = parcel.readInt();
        AppMethodBeat.o(15113);
    }

    /* renamed from: cn.missfresh.module.base.manager.bean.VipProgress$1  reason: invalid class name */
    static class AnonymousClass1 implements Parcelable.Creator<VipProgress> {
        AnonymousClass1() {
        }

        @Override // android.os.Parcelable.Creator
        public VipProgress createFromParcel(Parcel parcel) {
            AppMethodBeat.i(15106, false);
            VipProgress vipProgress = new VipProgress(parcel);
            AppMethodBeat.o(15106);
            return vipProgress;
        }

        @Override // android.os.Parcelable.Creator
        public VipProgress[] newArray(int i) {
            return new VipProgress[i];
        }
    }

    static {
        AppMethodBeat.i(15116, false);
        AppMethodBeat.o(15116);
    }

    public int getVip_order_num() {
        return this.vip_order_num;
    }

    public void setVip_order_num(int i) {
        this.vip_order_num = i;
    }

    public int getCurrent_order_num() {
        return this.current_order_num;
    }

    public void setCurrent_order_num(int i) {
        this.current_order_num = i;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        AppMethodBeat.i(15114, false);
        parcel.writeInt(this.vip_order_num);
        parcel.writeInt(this.current_order_num);
        AppMethodBeat.o(15114);
    }
}
