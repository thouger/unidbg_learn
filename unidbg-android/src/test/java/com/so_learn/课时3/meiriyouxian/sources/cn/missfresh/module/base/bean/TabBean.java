package cn.missfresh.module.base.bean;

public class TabBean {
    public String imageUrl;
    public String name;
    public String selectImageUrl;
    public int size;
    public String tag;
    public String topImageUrl;

    public String getIconUrl() {
        return this.imageUrl;
    }

    public String getIconSelectedUrl() {
        return this.selectImageUrl;
    }

    public String getText() {
        return this.name;
    }

    public String getTopImageUrl() {
        return this.topImageUrl;
    }

    public void setTopImageUrl(String str) {
        this.topImageUrl = str;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int i) {
        this.size = i;
    }
}
