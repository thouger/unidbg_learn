package cn.missfresh.buttomline.abtest.net;

import android.text.format.DateFormat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.List;

public class AbtestRequestParam {
    private String brand = null;
    private String business = null;
    private String deviceId = null;
    private List<String> expIds = null;
    private String expKey = null;
    private String mobile = null;
    private String obj = null;
    private Integer platform = null;
    private String romVer = null;
    private String uiVer = null;
    private String userId = null;
    private String version = null;

    public AbtestRequestParam(String str) {
        this.obj = str;
    }

    public AbtestRequestParam(ArrayList<String> arrayList) {
        AppMethodBeat.i(7413, false);
        this.expIds = arrayList;
        this.platform = 1;
        AppMethodBeat.o(7413);
    }

    public String getBusiness() {
        return this.business;
    }

    public void setBusiness(String str) {
        this.business = str;
    }

    public String getExpKey() {
        return this.expKey;
    }

    public void setExpKey(String str) {
        this.expKey = str;
    }

    public List<String> getExpIds() {
        return this.expIds;
    }

    public void addExpId(String str) {
        AppMethodBeat.i(7425, false);
        if (this.expIds == null) {
            this.expIds = new ArrayList();
        }
        this.expIds.add(str);
        AppMethodBeat.o(7425);
    }

    public void setExpIds(List<String> list) {
        this.expIds = list;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public Integer getPlatform() {
        return this.platform;
    }

    public void setPlatform(int i) {
        AppMethodBeat.i(7433, false);
        this.platform = Integer.valueOf(i);
        AppMethodBeat.o(7433);
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String str) {
        this.mobile = str;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String str) {
        this.brand = str;
    }

    public String getUiVer() {
        return this.uiVer;
    }

    public void setUiVer(String str) {
        this.uiVer = str;
    }

    public String getRomVer() {
        return this.romVer;
    }

    public void setRomVer(String str) {
        this.romVer = str;
    }

    public void setPlatform(Integer num) {
        this.platform = num;
    }

    public String getObj() {
        return this.obj;
    }

    public void setObj(String str) {
        this.business = null;
        this.expKey = null;
        this.expIds = null;
        this.deviceId = null;
        this.platform = null;
        this.userId = null;
        this.version = null;
        this.mobile = null;
        this.brand = null;
        this.uiVer = null;
        this.romVer = null;
        this.obj = str;
    }

    public String toString() {
        AppMethodBeat.i(7455, false);
        String str = "AbtestRequestParam{business='" + this.business + DateFormat.QUOTE + ", expKey='" + this.expKey + DateFormat.QUOTE + ", expIds=" + this.expIds + ", deviceId='" + this.deviceId + DateFormat.QUOTE + ", platform=" + this.platform + ", userId='" + this.userId + DateFormat.QUOTE + ", version='" + this.version + DateFormat.QUOTE + ", mobile='" + this.mobile + DateFormat.QUOTE + ", brand='" + this.brand + DateFormat.QUOTE + ", uiVer='" + this.uiVer + DateFormat.QUOTE + ", romVer='" + this.romVer + DateFormat.QUOTE + ", obj='" + this.obj + DateFormat.QUOTE + '}';
        AppMethodBeat.o(7455);
        return str;
    }
}
