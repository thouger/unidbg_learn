package cn.missfresh.sherlock.bo;

public class DropBO {
    private int dropCount;
    private String dropLevel;
    private int dropSum;

    public int getDropCount() {
        return this.dropCount;
    }

    public String getDropLevel() {
        return this.dropLevel;
    }

    public int getDropSum() {
        return this.dropSum;
    }

    public void setDropCount(int i) {
        this.dropCount = i;
    }

    public void setDropLevel(String str) {
        this.dropLevel = str;
    }

    public void setDropSum(int i) {
        this.dropSum = i;
    }
}
