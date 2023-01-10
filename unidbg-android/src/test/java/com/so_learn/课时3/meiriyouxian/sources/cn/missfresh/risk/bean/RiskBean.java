package cn.missfresh.risk.bean;

import java.util.List;

public class RiskBean {
    private String abtmac;
    private int accEnable;
    private List<String> accService;
    private String adId;
    private String ainfoAbi;
    private String ainfoApmac;
    private String ainfoAppproc;
    private String ainfoAppproc32;
    private String ainfoAppproc64;
    private String ainfoDsMd5;
    private String ainfoIsVpn;
    private long ainfoResett;
    private String ainfoRoot;
    private String ainfoSfMd5;
    private String ainfoSyspropsRoBootBaseband;
    private String ainfoSyspropsRoBootBootloader;
    private String ainfoSyspropsRoBootHardware;
    private String ainfoSyspropsRoBuildDateUtc;
    private String ainfoSyspropsRoBuildDisplayId;
    private String ainfoSyspropsRoBuildVersionRelease;
    private String ainfoSyspropsRoBuildVersionSdk;
    private String ainfoSyspropsRoDebuggable;
    private String ainfoSyspropsRoKernelQemu;
    private String ainfoSyspropsRoProductBoard;
    private String ainfoSyspropsRoProductBrand;
    private String ainfoSyspropsRoProductDevice;
    private String ainfoSyspropsRoProductManufacturer;
    private String ainfoSyspropsRoProductModel;
    private String ainfoSyspropsRoProductName;
    private String ainfoSyspropsRoSecure;
    private String ainfoSyspropsRoSerialno;
    private String ainfoTmprFw;
    private String ainfoVlMd5;
    private String ainfoWifimac;
    private String appName;
    private String appVersion;
    private List<AppInfo> apps;
    private List<String> aps;
    private String band;
    private double batteryLevel;
    private String batteryStatus;
    private float batteryTemperature;
    private String bootId;
    private long bootTime;
    private String brand;
    private int brightness;
    private String bssid;
    private String btmac;
    private int camcnt;
    private int camlight;
    private String campermi;
    private List<CellBean> cell;
    private int cpuCount;
    private String cpuFreq;
    private String cpuModel;
    private String cpuNum;
    private long currentTime;
    private String files;
    private long freeSpace;
    private String gps;
    private int gpsAuthStatus;
    private int gpsSwitch;
    private String iccid;
    private String imei;
    private String imei1;
    private String imei2;
    private String imsi;
    private List<String> input;
    private String ip;
    private String kernelVersion;
    private String mac;
    private List<String> maps;
    private long memory;
    private String model;
    private String name;
    private List<NetBean> net;
    private String network;
    private String operator;
    private float[] orientation;
    private String os;
    private String osVersion;
    private String proc;
    private String propsGsmNetworkType;
    private String propsGsmSimState;
    private String propsNetDns1;
    private String propsNetHostname;
    private String propsPersistSysCountry;
    private String propsPersistSysLanguage;
    private String propsSysUsbState;
    private String screen;
    private String sdkVersion;
    private List<String> sensor;
    private String signMd5;
    private String signdn;
    private String simPhoneNumber;
    private String ssid;
    private String sysBoard;
    private String sysBootloader;
    private String sysBrand;
    private String sysCpuabi;
    private String sysCpuabi2;
    private String sysDevice;
    private String sysDisplay;
    private String sysFingerprint;
    private String sysHardware;
    private String sysHost;
    private String sysManufacturer;
    private String sysModel;
    private String sysProduct;
    private String sysSerial;
    private String sysTags;
    private String sysVersionIncremental;
    private String sysVersionRelease;
    private String sysVersionSdk;
    private long totalSpace;
    private long upTime;
    private String widevineId;
    private String wifiIp;

    public String getSdkVersion() {
        return this.sdkVersion;
    }

    public void setSdkVersion(String str) {
        this.sdkVersion = str;
    }

    public String getKernelVersion() {
        return this.kernelVersion;
    }

    public void setKernelVersion(String str) {
        this.kernelVersion = str;
    }

    public String getAdId() {
        return this.adId;
    }

    public void setAdId(String str) {
        this.adId = str;
    }

    public String getImei() {
        return this.imei;
    }

    public void setImei(String str) {
        this.imei = str;
    }

    public String getImei1() {
        return this.imei1;
    }

    public void setImei1(String str) {
        this.imei1 = str;
    }

    public String getImei2() {
        return this.imei2;
    }

    public void setImei2(String str) {
        this.imei2 = str;
    }

    public String getImsi() {
        return this.imsi;
    }

    public void setImsi(String str) {
        this.imsi = str;
    }

    public String getIccid() {
        return this.iccid;
    }

    public void setIccid(String str) {
        this.iccid = str;
    }

    public String getBootId() {
        return this.bootId;
    }

    public void setBootId(String str) {
        this.bootId = str;
    }

    public String getWidevineId() {
        return this.widevineId;
    }

    public void setWidevineId(String str) {
        this.widevineId = str;
    }

    public List<AppInfo> getApps() {
        return this.apps;
    }

    public void setApps(List<AppInfo> list) {
        this.apps = list;
    }

    public String getAppVersion() {
        return this.appVersion;
    }

    public void setAppVersion(String str) {
        this.appVersion = str;
    }

    public String getAppName() {
        return this.appName;
    }

    public void setAppName(String str) {
        this.appName = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getProc() {
        return this.proc;
    }

    public void setProc(String str) {
        this.proc = str;
    }

    public long getBootTime() {
        return this.bootTime;
    }

    public void setBootTime(long j) {
        this.bootTime = j;
    }

    public long getCurrentTime() {
        return this.currentTime;
    }

    public void setCurrentTime(long j) {
        this.currentTime = j;
    }

    public long getUpTime() {
        return this.upTime;
    }

    public void setUpTime(long j) {
        this.upTime = j;
    }

    public String getBssid() {
        return this.bssid;
    }

    public void setBssid(String str) {
        this.bssid = str;
    }

    public String getMac() {
        return this.mac;
    }

    public void setMac(String str) {
        this.mac = str;
    }

    public List<NetBean> getNet() {
        return this.net;
    }

    public void setNet(List<NetBean> list) {
        this.net = list;
    }

    public String getNetwork() {
        return this.network;
    }

    public void setNetwork(String str) {
        this.network = str;
    }

    public String getSsid() {
        return this.ssid;
    }

    public void setSsid(String str) {
        this.ssid = str;
    }

    public String getWifiIp() {
        return this.wifiIp;
    }

    public void setWifiIp(String str) {
        this.wifiIp = str;
    }

    public String getIp() {
        return this.ip;
    }

    public void setIp(String str) {
        this.ip = str;
    }

    public List<String> getAps() {
        return this.aps;
    }

    public void setAps(List<String> list) {
        this.aps = list;
    }

    public String getOs() {
        return this.os;
    }

    public void setOs(String str) {
        this.os = str;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public void setOsVersion(String str) {
        this.osVersion = str;
    }

    public String getModel() {
        return this.model;
    }

    public void setModel(String str) {
        this.model = str;
    }

    public String getBrand() {
        return this.brand;
    }

    public void setBrand(String str) {
        this.brand = str;
    }

    public int getCpuCount() {
        return this.cpuCount;
    }

    public void setCpuCount(int i) {
        this.cpuCount = i;
    }

    public String getCpuModel() {
        return this.cpuModel;
    }

    public void setCpuModel(String str) {
        this.cpuModel = str;
    }

    public String getCpuFreq() {
        return this.cpuFreq;
    }

    public void setCpuFreq(String str) {
        this.cpuFreq = str;
    }

    public String getCpuNum() {
        return this.cpuNum;
    }

    public void setCpuNum(String str) {
        this.cpuNum = str;
    }

    public String getBtmac() {
        return this.btmac;
    }

    public void setBtmac(String str) {
        this.btmac = str;
    }

    public String getAbtmac() {
        return this.abtmac;
    }

    public void setAbtmac(String str) {
        this.abtmac = str;
    }

    public long getMemory() {
        return this.memory;
    }

    public void setMemory(long j) {
        this.memory = j;
    }

    public List<String> getSensor() {
        return this.sensor;
    }

    public void setSensor(List<String> list) {
        this.sensor = list;
    }

    public float[] getOrientation() {
        return this.orientation;
    }

    public void setOrientation(float[] fArr) {
        this.orientation = fArr;
    }

    public int getBrightness() {
        return this.brightness;
    }

    public void setBrightness(int i) {
        this.brightness = i;
    }

    public double getBatteryLevel() {
        return this.batteryLevel;
    }

    public void setBatteryLevel(double d) {
        this.batteryLevel = d;
    }

    public String getBatteryStatus() {
        return this.batteryStatus;
    }

    public void setBatteryStatus(String str) {
        this.batteryStatus = str;
    }

    public float getBatteryTemperature() {
        return this.batteryTemperature;
    }

    public void setBatteryTemperature(float f) {
        this.batteryTemperature = f;
    }

    public long getFreeSpace() {
        return this.freeSpace;
    }

    public void setFreeSpace(long j) {
        this.freeSpace = j;
    }

    public long getTotalSpace() {
        return this.totalSpace;
    }

    public void setTotalSpace(long j) {
        this.totalSpace = j;
    }

    public String getScreen() {
        return this.screen;
    }

    public void setScreen(String str) {
        this.screen = str;
    }

    public int getCamcnt() {
        return this.camcnt;
    }

    public void setCamcnt(int i) {
        this.camcnt = i;
    }

    public int getCamlight() {
        return this.camlight;
    }

    public void setCamlight(int i) {
        this.camlight = i;
    }

    public String getCampermi() {
        return this.campermi;
    }

    public void setCampermi(String str) {
        this.campermi = str;
    }

    public String getBand() {
        return this.band;
    }

    public void setBand(String str) {
        this.band = str;
    }

    public String getOperator() {
        return this.operator;
    }

    public void setOperator(String str) {
        this.operator = str;
    }

    public List<CellBean> getCell() {
        return this.cell;
    }

    public void setCell(List<CellBean> list) {
        this.cell = list;
    }

    public int getGpsSwitch() {
        return this.gpsSwitch;
    }

    public void setGpsSwitch(int i) {
        this.gpsSwitch = i;
    }

    public int getGpsAuthStatus() {
        return this.gpsAuthStatus;
    }

    public void setGpsAuthStatus(int i) {
        this.gpsAuthStatus = i;
    }

    public String getGps() {
        return this.gps;
    }

    public void setGps(String str) {
        this.gps = str;
    }

    public String getSigndn() {
        return this.signdn;
    }

    public void setSigndn(String str) {
        this.signdn = str;
    }

    public String getSignMd5() {
        return this.signMd5;
    }

    public void setSignMd5(String str) {
        this.signMd5 = str;
    }

    public String getFiles() {
        return this.files;
    }

    public void setFiles(String str) {
        this.files = str;
    }

    public List<String> getMaps() {
        return this.maps;
    }

    public void setMaps(List<String> list) {
        this.maps = list;
    }

    public List<String> getInput() {
        return this.input;
    }

    public void setInput(List<String> list) {
        this.input = list;
    }

    public String getAinfoAbi() {
        return this.ainfoAbi;
    }

    public void setAinfoAbi(String str) {
        this.ainfoAbi = str;
    }

    public String getAinfoWifimac() {
        return this.ainfoWifimac;
    }

    public void setAinfoWifimac(String str) {
        this.ainfoWifimac = str;
    }

    public String getAinfoApmac() {
        return this.ainfoApmac;
    }

    public void setAinfoApmac(String str) {
        this.ainfoApmac = str;
    }

    public long getAinfoResett() {
        return this.ainfoResett;
    }

    public void setAinfoResett(long j) {
        this.ainfoResett = j;
    }

    public String getAinfoIsVpn() {
        return this.ainfoIsVpn;
    }

    public void setAinfoIsVpn(String str) {
        this.ainfoIsVpn = str;
    }

    public String getAinfoAppproc() {
        return this.ainfoAppproc;
    }

    public void setAinfoAppproc(String str) {
        this.ainfoAppproc = str;
    }

    public String getAinfoAppproc32() {
        return this.ainfoAppproc32;
    }

    public void setAinfoAppproc32(String str) {
        this.ainfoAppproc32 = str;
    }

    public String getAinfoAppproc64() {
        return this.ainfoAppproc64;
    }

    public void setAinfoAppproc64(String str) {
        this.ainfoAppproc64 = str;
    }

    public String getAinfoDsMd5() {
        return this.ainfoDsMd5;
    }

    public void setAinfoDsMd5(String str) {
        this.ainfoDsMd5 = str;
    }

    public String getAinfoSfMd5() {
        return this.ainfoSfMd5;
    }

    public void setAinfoSfMd5(String str) {
        this.ainfoSfMd5 = str;
    }

    public String getAinfoVlMd5() {
        return this.ainfoVlMd5;
    }

    public void setAinfoVlMd5(String str) {
        this.ainfoVlMd5 = str;
    }

    public String getAinfoRoot() {
        return this.ainfoRoot;
    }

    public void setAinfoRoot(String str) {
        this.ainfoRoot = str;
    }

    public String getAinfoTmprFw() {
        return this.ainfoTmprFw;
    }

    public void setAinfoTmprFw(String str) {
        this.ainfoTmprFw = str;
    }

    public String getPropsGsmNetworkType() {
        return this.propsGsmNetworkType;
    }

    public void setPropsGsmNetworkType(String str) {
        this.propsGsmNetworkType = str;
    }

    public String getPropsGsmSimState() {
        return this.propsGsmSimState;
    }

    public void setPropsGsmSimState(String str) {
        this.propsGsmSimState = str;
    }

    public String getPropsPersistSysCountry() {
        return this.propsPersistSysCountry;
    }

    public void setPropsPersistSysCountry(String str) {
        this.propsPersistSysCountry = str;
    }

    public String getPropsPersistSysLanguage() {
        return this.propsPersistSysLanguage;
    }

    public void setPropsPersistSysLanguage(String str) {
        this.propsPersistSysLanguage = str;
    }

    public String getPropsSysUsbState() {
        return this.propsSysUsbState;
    }

    public void setPropsSysUsbState(String str) {
        this.propsSysUsbState = str;
    }

    public String getPropsNetDns1() {
        return this.propsNetDns1;
    }

    public void setPropsNetDns1(String str) {
        this.propsNetDns1 = str;
    }

    public String getPropsNetHostname() {
        return this.propsNetHostname;
    }

    public void setPropsNetHostname(String str) {
        this.propsNetHostname = str;
    }

    public String getSysSerial() {
        return this.sysSerial;
    }

    public void setSysSerial(String str) {
        this.sysSerial = str;
    }

    public String getSysModel() {
        return this.sysModel;
    }

    public void setSysModel(String str) {
        this.sysModel = str;
    }

    public String getSysDevice() {
        return this.sysDevice;
    }

    public void setSysDevice(String str) {
        this.sysDevice = str;
    }

    public String getSysDisplay() {
        return this.sysDisplay;
    }

    public void setSysDisplay(String str) {
        this.sysDisplay = str;
    }

    public String getSysCpuabi() {
        return this.sysCpuabi;
    }

    public void setSysCpuabi(String str) {
        this.sysCpuabi = str;
    }

    public String getSysCpuabi2() {
        return this.sysCpuabi2;
    }

    public void setSysCpuabi2(String str) {
        this.sysCpuabi2 = str;
    }

    public String getSysManufacturer() {
        return this.sysManufacturer;
    }

    public void setSysManufacturer(String str) {
        this.sysManufacturer = str;
    }

    public String getSysBrand() {
        return this.sysBrand;
    }

    public void setSysBrand(String str) {
        this.sysBrand = str;
    }

    public String getSysBoard() {
        return this.sysBoard;
    }

    public void setSysBoard(String str) {
        this.sysBoard = str;
    }

    public String getSysHardware() {
        return this.sysHardware;
    }

    public void setSysHardware(String str) {
        this.sysHardware = str;
    }

    public String getSysProduct() {
        return this.sysProduct;
    }

    public void setSysProduct(String str) {
        this.sysProduct = str;
    }

    public String getSysFingerprint() {
        return this.sysFingerprint;
    }

    public void setSysFingerprint(String str) {
        this.sysFingerprint = str;
    }

    public String getSysVersionRelease() {
        return this.sysVersionRelease;
    }

    public void setSysVersionRelease(String str) {
        this.sysVersionRelease = str;
    }

    public String getSysVersionSdk() {
        return this.sysVersionSdk;
    }

    public void setSysVersionSdk(String str) {
        this.sysVersionSdk = str;
    }

    public String getSysVersionIncremental() {
        return this.sysVersionIncremental;
    }

    public void setSysVersionIncremental(String str) {
        this.sysVersionIncremental = str;
    }

    public String getSysBootloader() {
        return this.sysBootloader;
    }

    public void setSysBootloader(String str) {
        this.sysBootloader = str;
    }

    public String getSysHost() {
        return this.sysHost;
    }

    public void setSysHost(String str) {
        this.sysHost = str;
    }

    public String getSysTags() {
        return this.sysTags;
    }

    public void setSysTags(String str) {
        this.sysTags = str;
    }

    public String getAinfoSyspropsRoBootBaseband() {
        return this.ainfoSyspropsRoBootBaseband;
    }

    public void setAinfoSyspropsRoBootBaseband(String str) {
        this.ainfoSyspropsRoBootBaseband = str;
    }

    public String getAinfoSyspropsRoBootBootloader() {
        return this.ainfoSyspropsRoBootBootloader;
    }

    public void setAinfoSyspropsRoBootBootloader(String str) {
        this.ainfoSyspropsRoBootBootloader = str;
    }

    public String getAinfoSyspropsRoBootHardware() {
        return this.ainfoSyspropsRoBootHardware;
    }

    public void setAinfoSyspropsRoBootHardware(String str) {
        this.ainfoSyspropsRoBootHardware = str;
    }

    public String getAinfoSyspropsRoBuildDateUtc() {
        return this.ainfoSyspropsRoBuildDateUtc;
    }

    public void setAinfoSyspropsRoBuildDateUtc(String str) {
        this.ainfoSyspropsRoBuildDateUtc = str;
    }

    public String getAinfoSyspropsRoBuildDisplayId() {
        return this.ainfoSyspropsRoBuildDisplayId;
    }

    public void setAinfoSyspropsRoBuildDisplayId(String str) {
        this.ainfoSyspropsRoBuildDisplayId = str;
    }

    public String getAinfoSyspropsRoBuildVersionRelease() {
        return this.ainfoSyspropsRoBuildVersionRelease;
    }

    public void setAinfoSyspropsRoBuildVersionRelease(String str) {
        this.ainfoSyspropsRoBuildVersionRelease = str;
    }

    public String getAinfoSyspropsRoBuildVersionSdk() {
        return this.ainfoSyspropsRoBuildVersionSdk;
    }

    public void setAinfoSyspropsRoBuildVersionSdk(String str) {
        this.ainfoSyspropsRoBuildVersionSdk = str;
    }

    public String getAinfoSyspropsRoDebuggable() {
        return this.ainfoSyspropsRoDebuggable;
    }

    public void setAinfoSyspropsRoDebuggable(String str) {
        this.ainfoSyspropsRoDebuggable = str;
    }

    public String getAinfoSyspropsRoKernelQemu() {
        return this.ainfoSyspropsRoKernelQemu;
    }

    public void setAinfoSyspropsRoKernelQemu(String str) {
        this.ainfoSyspropsRoKernelQemu = str;
    }

    public String getAinfoSyspropsRoProductBoard() {
        return this.ainfoSyspropsRoProductBoard;
    }

    public void setAinfoSyspropsRoProductBoard(String str) {
        this.ainfoSyspropsRoProductBoard = str;
    }

    public String getAinfoSyspropsRoProductBrand() {
        return this.ainfoSyspropsRoProductBrand;
    }

    public void setAinfoSyspropsRoProductBrand(String str) {
        this.ainfoSyspropsRoProductBrand = str;
    }

    public String getAinfoSyspropsRoProductDevice() {
        return this.ainfoSyspropsRoProductDevice;
    }

    public void setAinfoSyspropsRoProductDevice(String str) {
        this.ainfoSyspropsRoProductDevice = str;
    }

    public String getAinfoSyspropsRoProductManufacturer() {
        return this.ainfoSyspropsRoProductManufacturer;
    }

    public void setAinfoSyspropsRoProductManufacturer(String str) {
        this.ainfoSyspropsRoProductManufacturer = str;
    }

    public String getAinfoSyspropsRoProductModel() {
        return this.ainfoSyspropsRoProductModel;
    }

    public void setAinfoSyspropsRoProductModel(String str) {
        this.ainfoSyspropsRoProductModel = str;
    }

    public String getAinfoSyspropsRoProductName() {
        return this.ainfoSyspropsRoProductName;
    }

    public void setAinfoSyspropsRoProductName(String str) {
        this.ainfoSyspropsRoProductName = str;
    }

    public String getAinfoSyspropsRoSecure() {
        return this.ainfoSyspropsRoSecure;
    }

    public void setAinfoSyspropsRoSecure(String str) {
        this.ainfoSyspropsRoSecure = str;
    }

    public String getAinfoSyspropsRoSerialno() {
        return this.ainfoSyspropsRoSerialno;
    }

    public void setAinfoSyspropsRoSerialno(String str) {
        this.ainfoSyspropsRoSerialno = str;
    }

    public int getAccEnable() {
        return this.accEnable;
    }

    public void setAccEnable(int i) {
        this.accEnable = i;
    }

    public List<String> getAccService() {
        return this.accService;
    }

    public void setAccService(List<String> list) {
        this.accService = list;
    }

    public String getSimPhoneNumber() {
        return this.simPhoneNumber;
    }

    public void setSimPhoneNumber(String str) {
        this.simPhoneNumber = str;
    }
}
