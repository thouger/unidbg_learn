package cn.missfresh.sherlock.bo;

public class ConfigKeyBO {
    private String appKey;
    private String appVersion;
    private String deviceMode;
    private String osVersion;
    private String region;
    private String sdkVersion;

    public ConfigKeyBO(String str) {
        this.appKey = str;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public String getDeviceMode() {
        return this.deviceMode;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public String getRegion() {
        return this.region;
    }

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public void setDeviceMode(String str) {
        this.deviceMode = str;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public void setRegion(String str) {
        this.region = str;
    }

    public void setSdkVersion(String str) {
        this.sdkVersion = str;
    }
}
