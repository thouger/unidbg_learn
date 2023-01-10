package cn.missfresh.risk.bean;

public class CellBean {
    private int cid;
    private int lac;

    public CellBean(int i, int i2) {
        this.lac = i;
        this.cid = i2;
    }

    public int getLac() {
        return this.lac;
    }

    public void setLac(int i) {
        this.lac = i;
    }

    public int getCid() {
        return this.cid;
    }

    public void setCid(int i) {
        this.cid = i;
    }
}
