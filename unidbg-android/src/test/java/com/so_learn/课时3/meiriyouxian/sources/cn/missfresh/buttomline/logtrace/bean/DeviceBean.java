package cn.missfresh.buttomline.logtrace.bean;

import android.text.format.DateFormat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.google.gson.annotations.SerializedName;

public class DeviceBean {
    @SerializedName("_android_id")
    private String androidId;
    @SerializedName("_appversion")
    private String appVersion;
    @SerializedName("_build_version")
    private String buildVersion;
    @SerializedName("_cpu")
    private String cpu;
    @SerializedName("_deviceName")
    private String deviceName;
    @SerializedName("_from")
    private String from;
    @SerializedName("_height")
    private int height;
    @SerializedName("_iccid")
    private String iccid;
    @SerializedName("_imei")
    private String imei;
    @SerializedName("_imsi")
    private String imsi;
    @SerializedName("_is_root")
    private int isRoot;
    @SerializedName("_language")
    private String language;
    @SerializedName("_lat")
    private String latitude;
    @SerializedName("_lon")
    private String longitude;
    @SerializedName("_mac")
    private String mac;
    @SerializedName("_meid")
    private String meid;
    @SerializedName("_mem_size")
    private String memSize;
    @SerializedName("_model")
    private String model;
    @SerializedName("_pkgname")
    private String pkgName;
    @SerializedName("_sim_type")
    private String simType;
    @SerializedName("_sys_version")
    private String systemVersion;
    @SerializedName("_time_zone")
    private String timeZone;
    @SerializedName("_width")
    private int width;

    public String getSystemVersion() {
        return this.systemVersion;
    }

    public void setSystemVersion(String str) {
        this.systemVersion = str;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public void setDeviceName(String str) {
        this.deviceName = str;
    }

    public String getImei() {
        return this.imei;
    }

    public void setImei(String str) {
        this.imei = str;
    }

    public String getImsi() {
        return this.imsi;
    }

    public void setImsi(String str) {
        this.imsi = str;
    }

    public String getMeid() {
        return this.meid;
    }

    public void setMeid(String str) {
        this.meid = str;
    }

    public String getMac() {
        return this.mac;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public String getIccid() {
        return this.iccid;
    }

    public void setIccid(String str) {
        this.iccid = str;
    }

    public String getAndroidId() {
        return this.androidId;
    }

    public void setAndroidId(String str) {
        this.androidId = str;
    }

    public String getSimType() {
        return this.simType;
    }

    public void setSimType(String str) {
        this.simType = str;
    }

    public String getCpu() {
        return this.cpu;
    }

    public void setCpu(String str) {
        this.cpu = str;
    }

    public String getMemSize() {
        return this.memSize;
    }

    public void setMemSize(String str) {
        this.memSize = str;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int i) {
        this.width = i;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public int getIsRoot() {
        return this.isRoot;
    }

    public void setIsRoot(int i) {
        this.isRoot = i;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String str) {
        this.longitude = str;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String str) {
        this.latitude = str;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public String getTimeZone() {
        return this.timeZone;
    }

    public void setTimeZone(String str) {
        this.timeZone = str;
    }

    public String getPkgName() {
        return this.pkgName;
    }

    public void setPkgName(String str) {
        this.pkgName = str;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public String getBuildVersion() {
        return this.buildVersion;
    }

    public void setBuildVersion(String str) {
        this.buildVersion = str;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String str) {
        this.from = str;
    }

    public String toString() {
        AppMethodBeat.i(16718, false);
        String str = "DeviceBean{systemVersion='" + this.systemVersion + DateFormat.QUOTE + ", model='" + this.model + DateFormat.QUOTE + ", deviceName='" + this.deviceName + DateFormat.QUOTE + ", imei='" + this.imei + DateFormat.QUOTE + ", imsi='" + this.imsi + DateFormat.QUOTE + ", meid='" + this.meid + DateFormat.QUOTE + ", mac='" + this.mac + DateFormat.QUOTE + ", iccid='" + this.iccid + DateFormat.QUOTE + ", androidId='" + this.androidId + DateFormat.QUOTE + ", simType='" + this.simType + DateFormat.QUOTE + ", cpu='" + this.cpu + DateFormat.QUOTE + ", memSize='" + this.memSize + DateFormat.QUOTE + ", width=" + this.width + ", height=" + this.height + ", isRoot=" + this.isRoot + ", longitude='" + this.longitude + DateFormat.QUOTE + ", latitude='" + this.latitude + DateFormat.QUOTE + ", language='" + this.language + DateFormat.QUOTE + ", timeZone='" + this.timeZone + DateFormat.QUOTE + ", pkgName='" + this.pkgName + DateFormat.QUOTE + ", appVersion='" + this.appVersion + DateFormat.QUOTE + ", buildVersion='" + this.buildVersion + DateFormat.QUOTE + ", from='" + this.from + DateFormat.QUOTE + '}';
        AppMethodBeat.o(16718);
        return str;
    }
}
