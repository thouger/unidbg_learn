package cn.missfresh.module.base.bean;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import com.xiaomi.mipush.sdk.Constants;

public class UserAddress implements Parcelable {
    public static String[] ADDRESS_TYPES = {"COMPANY", "HOME", "SCHOOL", "OTHER", "PARENT"};
    public static final Parcelable.Creator<UserAddress> CREATOR = new AnonymousClass1();
    public String address_1;
    public String address_2;
    public String address_detail;
    public String area;
    public String area_code;
    public String city;
    @JSONField(name = "default")
    public boolean defaultAddress;
    public String full_address;
    public int gender;
    public int id;
    public boolean isAdd = false;
    public int is_open;
    public Boolean is_valid;
    public String latLng;
    public int matchScope;
    public String name;
    public int optimal;
    public String phone_number;
    public String poi_id;
    public String province;
    public String tag;
    public String title = "";
    public boolean transport;
    public int usable;

    public static class LatAndLng {
        public String lat;
        public String lng;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    /* renamed from: cn.missfresh.module.base.bean.UserAddress$1  reason: invalid class name */
    static class AnonymousClass1 implements Parcelable.Creator<UserAddress> {
        AnonymousClass1() {
        }

        @Override // android.os.Parcelable.Creator
        public UserAddress createFromParcel(Parcel parcel) {
            AppMethodBeat.i(9635, false);
            UserAddress userAddress = new UserAddress(parcel);
            AppMethodBeat.o(9635);
            return userAddress;
        }

        @Override // android.os.Parcelable.Creator
        public UserAddress[] newArray(int i) {
            return new UserAddress[i];
        }
    }

    static {
        AppMethodBeat.i(9715, false);
        AppMethodBeat.o(9715);
    }

    public UserAddress() {
    }

    protected UserAddress(Parcel parcel) {
        boolean z = false;
        AppMethodBeat.i(9664, false);
        this.phone_number = parcel.readString();
        this.province = parcel.readString();
        this.area_code = parcel.readString();
        this.name = parcel.readString();
        this.city = parcel.readString();
        this.defaultAddress = parcel.readByte() != 0;
        this.area = parcel.readString();
        this.full_address = parcel.readString();
        this.is_valid = (Boolean) parcel.readValue(Boolean.class.getClassLoader());
        this.tag = parcel.readString();
        this.latLng = parcel.readString();
        this.address_1 = parcel.readString();
        this.address_2 = parcel.readString();
        this.address_detail = parcel.readString();
        this.id = parcel.readInt();
        this.matchScope = parcel.readInt();
        this.transport = parcel.readByte() != 0 ? true : z;
        this.is_open = parcel.readInt();
        this.poi_id = parcel.readString();
        this.gender = parcel.readInt();
        this.optimal = parcel.readInt();
        this.usable = parcel.readInt();
        AppMethodBeat.o(9664);
    }

    public String getDetailAddress() {
        AppMethodBeat.i(9666, false);
        StringBuilder sb = new StringBuilder();
        String str = "";
        sb.append(str);
        sb.append(b.a(this.address_1) ? str : this.address_1);
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(sb2);
        if (!b.a(this.address_2)) {
            str = this.address_2;
        }
        sb3.append(str);
        String sb4 = sb3.toString();
        AppMethodBeat.o(9666);
        return sb4;
    }

    public String getDetailRegion() {
        AppMethodBeat.i(9669, false);
        if (b.a(this.area)) {
            String str = this.province + this.city;
            AppMethodBeat.o(9669);
            return str;
        }
        String str2 = this.province + this.city + this.area;
        AppMethodBeat.o(9669);
        return str2;
    }

    public String getRealTag() {
        AppMethodBeat.i(9674, false);
        String realTag = getRealTag(false);
        AppMethodBeat.o(9674);
        return realTag;
    }

    public String getTagFormat() {
        AppMethodBeat.i(9678, false);
        if (getRealTag().equalsIgnoreCase("\u5bb6")) {
            AppMethodBeat.o(9678);
            return "1";
        } else if (getRealTag().equalsIgnoreCase("\u7236\u6bcd\u5bb6")) {
            AppMethodBeat.o(9678);
            return "2";
        } else if (getRealTag().equalsIgnoreCase("\u516c\u53f8")) {
            AppMethodBeat.o(9678);
            return "3";
        } else if (getRealTag().equalsIgnoreCase("\u5b66\u6821")) {
            AppMethodBeat.o(9678);
            return "4";
        } else if (getRealTag().equalsIgnoreCase("\u5176\u5b83")) {
            AppMethodBeat.o(9678);
            return "5";
        } else {
            AppMethodBeat.o(9678);
            return "";
        }
    }

    public String getRealTag(boolean z) {
        String str;
        AppMethodBeat.i(9683, false);
        if (ADDRESS_TYPES[0].equals(this.tag)) {
            str = "\u516c\u53f8";
        } else if (ADDRESS_TYPES[1].equals(this.tag)) {
            str = "\u5bb6";
        } else if (ADDRESS_TYPES[2].equals(this.tag)) {
            str = "\u5b66\u6821";
        } else if (ADDRESS_TYPES[3].equals(this.tag)) {
            str = "\u5176\u5b83";
        } else {
            str = ADDRESS_TYPES[4].equals(this.tag) ? "\u7236\u6bcd\u5bb6" : "";
        }
        AppMethodBeat.o(9683);
        return str;
    }

    public void updateTextViewColorAndBackgroud(Context context, TextView textView) {
        Drawable drawable;
        int i;
        AppMethodBeat.i(9687, false);
        if (ADDRESS_TYPES[0].equals(this.tag)) {
            i = ContextCompat.getColor(context, R.color.color_F84880);
            drawable = ContextCompat.getDrawable(context, R.drawable.company_radiobutton_background_checked);
        } else if (ADDRESS_TYPES[1].equals(this.tag)) {
            i = ContextCompat.getColor(context, R.color.color_419AF3);
            drawable = ContextCompat.getDrawable(context, R.drawable.home_radiobutton_background_checked);
        } else if (ADDRESS_TYPES[2].equals(this.tag)) {
            i = ContextCompat.getColor(context, R.color.color_F56916);
            drawable = ContextCompat.getDrawable(context, R.drawable.school_radiobutton_background_checked);
        } else if (ADDRESS_TYPES[4].equals(this.tag)) {
            i = ContextCompat.getColor(context, R.color.color_69BA0C);
            drawable = ContextCompat.getDrawable(context, R.drawable.parenthome_radiobutton_background_checked);
        } else if (ADDRESS_TYPES[3].equals(this.tag)) {
            i = ContextCompat.getColor(context, R.color.color_f18e07);
            drawable = ContextCompat.getDrawable(context, R.drawable.other_radiobutton_background_checked);
        } else {
            i = ContextCompat.getColor(context, R.color.color_419AF3);
            drawable = ContextCompat.getDrawable(context, R.drawable.parenthome_radiobutton_background_checked);
        }
        if (b.a(textView.getText().toString())) {
            textView.setVisibility(8);
        } else {
            textView.setVisibility(0);
            textView.setTextColor(i);
            textView.setBackgroundDrawable(drawable);
        }
        AppMethodBeat.o(9687);
    }

    public String getHistoryAddress() {
        AppMethodBeat.i(9690, false);
        StringBuilder sb = new StringBuilder();
        String str = "";
        sb.append(str);
        sb.append(b.a(this.address_1) ? str : this.address_1);
        String sb2 = sb.toString();
        StringBuilder sb3 = new StringBuilder();
        sb3.append(sb2);
        if (!b.a(this.address_2)) {
            str = this.address_2;
        }
        sb3.append(str);
        String sb4 = sb3.toString();
        AppMethodBeat.o(9690);
        return sb4;
    }

    public boolean isAvailable() {
        boolean z = false;
        AppMethodBeat.i(9693, false);
        if (!b.a(this.address_1) && !b.a(this.address_2)) {
            z = true;
        }
        AppMethodBeat.o(9693);
        return z;
    }

    public boolean isLastOrderAddressAvailable() {
        boolean z = false;
        AppMethodBeat.i(9695, false);
        if (!b.a(this.area_code) && !b.a(this.city)) {
            z = true;
        }
        AppMethodBeat.o(9695);
        return z;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        AppMethodBeat.i(9701, false);
        parcel.writeString(this.phone_number);
        parcel.writeString(this.province);
        parcel.writeString(this.area_code);
        parcel.writeString(this.name);
        parcel.writeString(this.city);
        parcel.writeByte(this.defaultAddress ? (byte) 1 : 0);
        parcel.writeString(this.area);
        parcel.writeString(this.full_address);
        parcel.writeValue(this.is_valid);
        parcel.writeString(this.tag);
        parcel.writeString(this.latLng);
        parcel.writeString(this.address_1);
        parcel.writeString(this.address_2);
        parcel.writeString(this.address_detail);
        parcel.writeInt(this.id);
        parcel.writeInt(this.matchScope);
        parcel.writeByte(this.transport ? (byte) 1 : 0);
        parcel.writeInt(this.is_open);
        parcel.writeString(this.poi_id);
        parcel.writeInt(this.gender);
        parcel.writeInt(this.optimal);
        parcel.writeInt(this.usable);
        AppMethodBeat.o(9701);
    }

    public String toString() {
        AppMethodBeat.i(9705, false);
        String jSONString = JSON.toJSONString(this);
        AppMethodBeat.o(9705);
        return jSONString;
    }

    public LatAndLng getLatAndLng() {
        LatAndLng latAndLng;
        String[] split;
        AppMethodBeat.i(9708, false);
        if (b.a(this.latLng) || (split = this.latLng.split(Constants.ACCEPT_TIME_SEPARATOR_SP)) == null || 2 != split.length) {
            latAndLng = null;
        } else {
            latAndLng = new LatAndLng();
            latAndLng.lat = split[0];
            latAndLng.lng = split[1];
        }
        AppMethodBeat.o(9708);
        return latAndLng;
    }

    public String getLat() {
        AppMethodBeat.i(9712, false);
        LatAndLng latAndLng = getLatAndLng();
        if (getLatAndLng() == null) {
            AppMethodBeat.o(9712);
            return "";
        }
        String str = latAndLng.lat;
        AppMethodBeat.o(9712);
        return str;
    }

    public String getLng() {
        AppMethodBeat.i(9713, false);
        LatAndLng latAndLng = getLatAndLng();
        if (getLatAndLng() == null) {
            AppMethodBeat.o(9713);
            return "";
        }
        String str = latAndLng.lng;
        AppMethodBeat.o(9713);
        return str;
    }
}
