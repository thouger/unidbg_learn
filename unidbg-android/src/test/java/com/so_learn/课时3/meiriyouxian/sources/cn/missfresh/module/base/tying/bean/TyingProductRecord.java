package cn.missfresh.module.base.tying.bean;

public class TyingProductRecord {
    private String channel;
    private int position;
    private String sku;

    public TyingProductRecord(String str, String str2, int i) {
        this.channel = str;
        this.sku = str2;
        this.position = i;
    }

    public String getChannel() {
        return this.channel;
    }

    public void setChannel(String str) {
        this.channel = str;
    }

    public String getSku() {
        return this.sku;
    }

    public void setSku(String str) {
        this.sku = str;
    }

    public int getPosition() {
        return this.position;
    }

    public void setPosition(int i) {
        this.position = i;
    }
}
