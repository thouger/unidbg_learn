package cn.missfresh.sherlock.to;

import java.io.Serializable;

public class StartAppTO extends CommonTO implements Serializable {
    private static final long serialVersionUID = -5088987783108018589L;
    private long adPageCreateTime;
    private long applicationCreateTime;
    private int availRam;
    private int cpuFreqKHz;
    private String cpuName;
    private int cpuNum;
    private int density;
    private int height;
    private long homePageCreateTime;
    private int launchType;
    private String levelName;
    private String scene;
    private double size;
    private String stack;
    private String stackKey;
    private int totalRam;
    private int width;

    public long getAdPageCreateTime() {
        return this.adPageCreateTime;
    }

    public long getApplicationCreateTime() {
        return this.applicationCreateTime;
    }

    public int getAvailRam() {
        return this.availRam;
    }

    public int getCpuFreqKHz() {
        return this.cpuFreqKHz;
    }

    public String getCpuName() {
        return this.cpuName;
    }

    public int getCpuNum() {
        return this.cpuNum;
    }

    public int getDensity() {
        return this.density;
    }

    public int getHeight() {
        return this.height;
    }

    public long getHomePageCreateTime() {
        return this.homePageCreateTime;
    }

    public int getLaunchType() {
        return this.launchType;
    }

    public String getName() {
        return this.levelName;
    }

    public String getScene() {
        return this.scene;
    }

    public double getSize() {
        return this.size;
    }

    public String getStack() {
        return this.stack;
    }

    public String getStackKey() {
        return this.stackKey;
    }

    public int getTotalRam() {
        return this.totalRam;
    }

    public int getWidth() {
        return this.width;
    }

    public void setAdPageCreateTime(long j) {
        this.adPageCreateTime = j;
    }

    public void setApplicationCreateTime(long j) {
        this.applicationCreateTime = j;
    }

    public void setAvailRam(int i) {
        this.availRam = i;
    }

    public void setCpuFreqKHz(int i) {
        this.cpuFreqKHz = i;
    }

    public void setCpuName(String str) {
        this.cpuName = str;
    }

    public void setCpuNum(int i) {
        this.cpuNum = i;
    }

    public void setDensity(int i) {
        this.density = i;
    }

    public void setHeight(int i) {
        this.height = i;
    }

    public void setHomePageCreateTime(long j) {
        this.homePageCreateTime = j;
    }

    public void setLaunchType(int i) {
        this.launchType = i;
    }

    public void setName(String str) {
        this.levelName = str;
    }

    public void setScene(String str) {
        this.scene = str;
    }

    public void setSize(double d) {
        this.size = d;
    }

    public void setStack(String str) {
        this.stack = str;
    }

    public void setStackKey(String str) {
        this.stackKey = str;
    }

    public void setTotalRam(int i) {
        this.totalRam = i;
    }

    public void setWidth(int i) {
        this.width = i;
    }
}
