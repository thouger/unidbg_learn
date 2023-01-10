package cn.missfresh.risk.bean;

public class BatteryBean {
    private int batteryLevel;
    private float batteryTemperature;
    private int batteryVoltage;
    private String state;

    public int getBatteryLevel() {
        return this.batteryLevel;
    }

    public void setBatteryLevel(int i) {
        this.batteryLevel = i;
    }

    public int getBatteryVoltage() {
        return this.batteryVoltage;
    }

    public void setBatteryVoltage(int i) {
        this.batteryVoltage = i;
    }

    public float getBatteryTemperature() {
        return this.batteryTemperature;
    }

    public void setBatteryTemperature(float f) {
        this.batteryTemperature = f;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String str) {
        this.state = str;
    }
}
