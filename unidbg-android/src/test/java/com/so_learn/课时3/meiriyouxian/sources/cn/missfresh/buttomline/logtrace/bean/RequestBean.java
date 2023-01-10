package cn.missfresh.buttomline.logtrace.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class RequestBean extends RequestBaseBean implements Serializable {
    private DeviceBean device;
    private List<Map<String, String>> logs;
    private String obj = null;
    private String tag = null;
    private String ver = "1.0";

    public String getTag() {
        return this.tag;
    }

    public void setTag(String str) {
        this.tag = str;
    }

    public String getVer() {
        return this.ver;
    }

    public void setVer(String str) {
        this.ver = str;
    }

    public String getObj() {
        return this.obj;
    }

    public void setObj(String str) {
        this.device = null;
        this.logs = null;
        this.obj = str;
    }

    public DeviceBean getDevice() {
        return this.device;
    }

    public void setDevice(DeviceBean deviceBean) {
        this.device = deviceBean;
    }

    public List<Map<String, String>> getLogs() {
        return this.logs;
    }

    public void setLogs(List<Map<String, String>> list) {
        this.logs = list;
    }
}
