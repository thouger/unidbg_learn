package cn.missfresh.module.base.widget.integral.bean;

import android.os.Parcel;
import android.os.Parcelable;
import cn.missfresh.module.base.bean.ShareInfo;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.List;

public class DailySignIn implements Parcelable {
    public static final Parcelable.Creator<DailySignIn> CREATOR = new AnonymousClass1();
    public static final String EXTRA_COME_FROM_HOME = "fromHome";
    public static final String EXTRA_COME_FROM_INTEGRAL = "fromIntegral";
    private String already_sign_in_text;
    private String chest_img;
    private String comeFrom = "";
    private String integral;
    private String integral_amount;
    private String share_bottom_img;
    private String share_button_name;
    private List<MoodImage> share_img = new ArrayList();
    private ShareInfo share_invite_content;
    private int sign_in_tag;

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public String getComeFrom() {
        return this.comeFrom;
    }

    public void setComeFrom(String str) {
        this.comeFrom = str;
    }

    public DailySignIn() {
        AppMethodBeat.i(24143, false);
        AppMethodBeat.o(24143);
    }

    public String getIntegral_amount() {
        return this.integral_amount;
    }

    public void setIntegral_amount(String str) {
        this.integral_amount = str;
    }

    protected DailySignIn(Parcel parcel) {
        AppMethodBeat.i(24144, false);
        this.sign_in_tag = parcel.readInt();
        this.integral = parcel.readString();
        this.already_sign_in_text = parcel.readString();
        this.chest_img = parcel.readString();
        AppMethodBeat.o(24144);
    }

    /* renamed from: cn.missfresh.module.base.widget.integral.bean.DailySignIn$1  reason: invalid class name */
    static class AnonymousClass1 implements Parcelable.Creator<DailySignIn> {
        AnonymousClass1() {
        }

        @Override // android.os.Parcelable.Creator
        public DailySignIn createFromParcel(Parcel parcel) {
            AppMethodBeat.i(24140, false);
            DailySignIn dailySignIn = new DailySignIn(parcel);
            AppMethodBeat.o(24140);
            return dailySignIn;
        }

        @Override // android.os.Parcelable.Creator
        public DailySignIn[] newArray(int i) {
            return new DailySignIn[i];
        }
    }

    static {
        AppMethodBeat.i(24147, false);
        AppMethodBeat.o(24147);
    }

    public int getSign_in_tag() {
        return this.sign_in_tag;
    }

    public void setSign_in_tag(int i) {
        this.sign_in_tag = i;
    }

    public String getIntegral() {
        return this.integral;
    }

    public void setIntegral(String str) {
        this.integral = str;
    }

    public String getAlready_sign_in_text() {
        return this.already_sign_in_text;
    }

    public void setAlready_sign_in_text(String str) {
        this.already_sign_in_text = str;
    }

    public String getChest_img() {
        return this.chest_img;
    }

    public void setChest_img(String str) {
        this.chest_img = str;
    }

    public ShareInfo getShare_invite_content() {
        return this.share_invite_content;
    }

    public String getShare_button_name() {
        return this.share_button_name;
    }

    public void setShare_invite_content(ShareInfo shareInfo) {
        this.share_invite_content = shareInfo;
    }

    public void setShare_button_name(String str) {
        this.share_button_name = str;
    }

    public String getShare_bottom_img() {
        return this.share_bottom_img;
    }

    public void setShare_img(List<MoodImage> list) {
        AppMethodBeat.i(24145, false);
        if (list.isEmpty() || list.size() <= 1) {
            this.share_img = list;
        } else {
            this.share_img = new ArrayList();
            this.share_img.clear();
            this.share_img.add(list.get(0));
        }
        AppMethodBeat.o(24145);
    }

    public List<MoodImage> getShare_img() {
        return this.share_img;
    }

    public void setShare_bottom_img(String str) {
        this.share_bottom_img = str;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel parcel, int i) {
        AppMethodBeat.i(24146, false);
        parcel.writeInt(this.sign_in_tag);
        parcel.writeString(this.integral);
        parcel.writeString(this.already_sign_in_text);
        parcel.writeString(this.chest_img);
        parcel.writeString(this.share_bottom_img);
        AppMethodBeat.o(24146);
    }

    public class MoodImage {
        public String share_choose_img;
        public String share_main_img;
        public String share_smell_img;
        public String share_text;
        public String share_top_img;
        public String share_unchoose_img;

        public MoodImage() {
        }
    }
}
