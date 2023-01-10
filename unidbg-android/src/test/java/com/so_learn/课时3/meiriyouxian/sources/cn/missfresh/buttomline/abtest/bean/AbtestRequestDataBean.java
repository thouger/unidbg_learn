package cn.missfresh.buttomline.abtest.bean;

import android.text.format.DateFormat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.io.Serializable;

public class AbtestRequestDataBean implements Serializable {
    private String business;
    private String deviceId;
    private String expKey;
    private String platform;
    private String stationCode;
    private String userId;
    private String version;

    public String getExpKey() {
        return this.expKey;
    }

    public void setExpKey(String str) {
        this.expKey = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String str) {
        this.platform = str;
    }

    public String getStationCode() {
        return this.stationCode;
    }

    public void setStationCode(String str) {
        this.stationCode = str;
    }

    public String getVersion() {
        return this.version;
    }

    public void setVersion(String str) {
        this.version = str;
    }

    public String getBusiness() {
        return this.business;
    }

    public void setBusiness(String str) {
        this.business = str;
    }

    @Override // java.lang.Object
    public String toString() {
        AppMethodBeat.i(7275, false);
        String str = "AbtestRequestDataBean{expKey='" + this.expKey + DateFormat.QUOTE + ", userId='" + this.userId + DateFormat.QUOTE + ", deviceId='" + this.deviceId + DateFormat.QUOTE + ", platform='" + this.platform + DateFormat.QUOTE + ", stationCode='" + this.stationCode + DateFormat.QUOTE + ", version='" + this.version + DateFormat.QUOTE + ", business='" + this.business + DateFormat.QUOTE + '}';
        AppMethodBeat.o(7275);
        return str;
    }
}
