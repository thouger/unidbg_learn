package cn.missfresh.sherlock.bo;

public class SherlockBO {
    private String common;
    private int eventType;
    private String extend;

    public String getCommon() {
        return this.common;
    }

    public int getEventType() {
        return this.eventType;
    }

    public String getExtend() {
        return this.extend;
    }

    public void setCommon(String str) {
        this.common = str;
    }

    public void setEventType(int i) {
        this.eventType = i;
    }

    public void setExtend(String str) {
        this.extend = str;
    }
}
