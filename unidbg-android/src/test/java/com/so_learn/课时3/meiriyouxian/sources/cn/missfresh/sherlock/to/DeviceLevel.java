package cn.missfresh.sherlock.to;

public class DeviceLevel {
    private int availRam;
    private int cpuFreqKHz;
    private String cpuName;
    private int cpuNum;
    private int density;
    private int height;
    private String levelName;
    private int network;
    private double size;
    private int totalRam;
    private int width;

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

    public String getName() {
        return this.levelName;
    }

    public int getNetwork() {
        return this.network;
    }

    public double getSize() {
        return this.size;
    }

    public int getTotalRam() {
        return this.totalRam;
    }

    public int getWidth() {
        return this.width;
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

    public void setName(String str) {
        this.levelName = str;
    }

    public void setNetwork(int i) {
        this.network = i;
    }

    public void setSize(double d) {
        this.size = d;
    }

    public void setTotalRam(int i) {
        this.totalRam = i;
    }

    public void setWidth(int i) {
        this.width = i;
    }
}
