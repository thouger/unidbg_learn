package cn.missfresh.basiclib.net.bean;

public class ApiServiceInfo {
    private String from;
    private String page;
    private String path;
    private String startNum;
    private int type;

    public ApiServiceInfo(String str, int i, String str2, String str3, String str4) {
        this.path = str;
        this.type = i;
        this.startNum = str2;
        this.page = str3;
        this.from = str4;
    }

    public String getPath() {
        return this.path;
    }

    public int getType() {
        return this.type;
    }

    public String getStartNum() {
        return this.startNum;
    }

    public String getPage() {
        return this.page;
    }

    public String getFrom() {
        return this.from;
    }
}
