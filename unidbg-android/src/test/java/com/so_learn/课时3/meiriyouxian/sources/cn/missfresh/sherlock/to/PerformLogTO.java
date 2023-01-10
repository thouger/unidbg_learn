package cn.missfresh.sherlock.to;

public class PerformLogTO extends CommonTO {
    private Long costedMillis;
    private Long netMillis;
    private String pageType;

    public Long getCostedMillis() {
        return this.costedMillis;
    }

    public Long getNetMillis() {
        return this.netMillis;
    }

    public String getPageType() {
        return this.pageType;
    }

    public void setCostedMillis(Long l) {
        this.costedMillis = l;
    }

    public void setNetMillis(Long l) {
        this.netMillis = l;
    }

    public void setPageType(String str) {
        this.pageType = str;
    }
}
