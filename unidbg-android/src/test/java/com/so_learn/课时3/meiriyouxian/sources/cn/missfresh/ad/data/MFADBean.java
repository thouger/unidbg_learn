package cn.missfresh.ad.data;

import android.text.TextUtils;
import android.text.format.DateFormat;
import cn.missfresh.ad.b.a;
import cn.missfresh.ad.data.b;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.Serializable;

public class MFADBean implements Serializable {
    private static final long serialVersionUID = 842277698;
    private int code;
    private int countdown_num;
    private String image;
    private String link;
    private String name;
    private String path;
    private String pre_load_path;
    private String promotion_id;
    private b.a resourceData;
    private boolean success;
    private String type;

    public String getPath() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = str;
    }

    public int getCode() {
        return this.code;
    }

    public void setCode(int i) {
        this.code = i;
    }

    public int getCountdown_num() {
        return this.countdown_num;
    }

    public void setCountdown_num(int i) {
        this.countdown_num = i;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }

    public String getLink() {
        return this.link;
    }

    public void setLink(String str) {
        this.link = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getPromotion_id() {
        return this.promotion_id;
    }

    public void setPromotion_id(String str) {
        this.promotion_id = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public String getPre_load_path() {
        return this.pre_load_path;
    }

    public void setPre_load_path(String str) {
        this.pre_load_path = str;
    }

    public b.a getResourceData() {
        return this.resourceData;
    }

    public void setResourceData(b.a aVar) {
        this.resourceData = aVar;
    }

    public String getImage() {
        AppMethodBeat.i(5891, false);
        String validUrl = getValidUrl();
        AppMethodBeat.o(5891);
        return validUrl;
    }

    public void setImage(String str) {
        this.image = str;
    }

    @Override // java.lang.Object
    public String toString() {
        AppMethodBeat.i(5895, false);
        String str = "MFADBean{path='" + this.path + DateFormat.QUOTE + ", image='" + this.image + DateFormat.QUOTE + ", code=" + this.code + ", countdown_num=" + this.countdown_num + ", success=" + this.success + ", link='" + this.link + DateFormat.QUOTE + ", name='" + this.name + DateFormat.QUOTE + ", promotion_id='" + this.promotion_id + DateFormat.QUOTE + ", type='" + this.type + DateFormat.QUOTE + ", pre_load_path='" + this.pre_load_path + DateFormat.QUOTE + ", resourceData=" + this.resourceData + '}';
        AppMethodBeat.o(5895);
        return str;
    }

    public String getValidUrl() {
        String str;
        AppMethodBeat.i(5897, false);
        if (TextUtils.isEmpty(this.image)) {
            AppMethodBeat.o(5897);
            return "";
        }
        try {
            int indexOf = this.image.indexOf(63);
            if (indexOf != -1) {
                str = this.image.substring(0, indexOf);
            } else {
                str = this.image;
            }
        } catch (Exception e) {
            a.a("MFSDK", e.getMessage());
            str = this.image;
        }
        AppMethodBeat.o(5897);
        return str;
    }
}
