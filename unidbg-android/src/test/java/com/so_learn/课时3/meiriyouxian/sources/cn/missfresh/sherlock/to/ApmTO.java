package cn.missfresh.sherlock.to;

import java.io.Serializable;
import java.util.List;

public class ApmTO implements Serializable {
    private static final long serialVersionUID = -2531742678088752397L;
    private String appKey;
    private String appName;
    private String appVersion;
    private List<CommonTO> data;
    private String deviceManufacturer;
    private String deviceMode;
    private String imei;
    private String osVersion;
    private int platform = 1;
    private String sdkVersion;

    public String getAppKey() {
        return this.appKey;
    }

    public String getAppName() {
        return this.appName;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public List<CommonTO> getData() {
        return this.data;
    }

    public String getDeviceManufacturer() {
        return this.deviceManufacturer;
    }

    public String getDeviceMode() {
        return this.deviceMode;
    }

    public String getImei() {
        return this.imei;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public int getPlatform() {
        return this.platform;
    }

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public void setAppKey(String str) {
        this.appKey = str;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public void setData(List<CommonTO> list) {
        this.data = list;
    }

    public void setDeviceManufacturer(String str) {
        this.deviceManufacturer = str;
    }

    public void setDeviceMode(String str) {
        this.deviceMode = str;
    }

    public void setImei(String str) {
        this.imei = str;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public void setPlatform(int i) {
        this.platform = i;
    }

    public void setSdkVersion(String str) {
        this.sdkVersion = str;
    }
}
