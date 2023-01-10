package cn.missfresh.module.base.widget.tag.bean;

public class TagInfo {
    private int bgColor;
    private int borderColor;
    private String tagIcon;
    private String tagName;
    private int textColor;
    private int useType;

    public String getTagName() {
        return this.tagName;
    }

    public void setTagName(String str) {
        this.tagName = str;
    }

    public int getTextColor() {
        return this.textColor;
    }

    public void setTextColor(int i) {
        this.textColor = i;
    }

    public String getTagIcon() {
        return this.tagIcon;
    }

    public void setTagIcon(String str) {
        this.tagIcon = str;
    }

    public int getUseType() {
        return this.useType;
    }

    public void setUseType(int i) {
        this.useType = i;
    }

    public int getBgColor() {
        return this.bgColor;
    }

    public void setBgColor(int i) {
        this.bgColor = i;
    }

    public int getBorderColor() {
        return this.borderColor;
    }

    public void setBorderColor(int i) {
        this.borderColor = i;
    }
}
