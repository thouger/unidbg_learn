package cn.missfresh.module.base.manager.bean;

public class MyService {
    private String icon_link;
    private String jump_link;
    private int nineRedPointSwitch = 0;
    private String operationMarkPic;
    private String title;
    private int type;

    public String getOperationMarkPic() {
        return this.operationMarkPic;
    }

    public void setOperationMarkPic(String str) {
        this.operationMarkPic = str;
    }

    public String getIcon_link() {
        return this.icon_link;
    }

    public void setIcon_link(String str) {
        this.icon_link = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getJump_link() {
        return this.jump_link;
    }

    public void setJump_link(String str) {
        this.jump_link = str;
    }

    public int getNineRedPointSwitch() {
        return this.nineRedPointSwitch;
    }

    public void setNineRedPointSwitch(int i) {
        this.nineRedPointSwitch = i;
    }
}
