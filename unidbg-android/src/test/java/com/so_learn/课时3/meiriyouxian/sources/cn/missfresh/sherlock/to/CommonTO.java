package cn.missfresh.sherlock.to;

import java.io.Serializable;

public class CommonTO implements Serializable {
    private static final long serialVersionUID = 8419358156669569209L;
    private int eventType;
    private int isFlutter;
    protected int network;
    private int networkOperator;
    private String phoneNumber;
    protected String region;
    private boolean release;
    private Long serialNum;
    protected Long timestamp;
    private String userId;
    protected String vc;

    public int getEventType() {
        return this.eventType;
    }

    public int getIsFlutter() {
        return this.isFlutter;
    }

    public int getNetwork() {
        return this.network;
    }

    public int getNetworkOperator() {
        return this.networkOperator;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public String getRegion() {
        return this.region;
    }

    public Long getSerialNum() {
        return this.serialNum;
    }

    public Long getTimestamp() {
        return this.timestamp;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getVc() {
        return this.vc;
    }

    public boolean isRelease() {
        return this.release;
    }

    public void setEventType(int i) {
        this.eventType = i;
    }

    public void setIsFlutter(int i) {
        this.isFlutter = i;
    }

    public void setNetwork(int i) {
        this.network = i;
    }

    public void setNetworkOperator(int i) {
        this.networkOperator = i;
    }

    public void setPhoneNumber(String str) {
        this.phoneNumber = str;
    }

    public void setRegion(String str) {
        this.region = str;
    }

    public void setRelease(boolean z) {
        this.release = z;
    }

    public void setSerialNum(Long l) {
        this.serialNum = l;
    }

    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public void setVc(String str) {
        this.vc = str;
    }
}
