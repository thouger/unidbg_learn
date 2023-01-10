package cn.missfresh.sherlock.to;

public class NetApiBO extends CommonTO {
    private long costedMillis;
    private String domain;
    private String requestMethod;
    private String url;

    public long getCostedMillis() {
        return this.costedMillis;
    }

    public String getDomain() {
        return this.domain;
    }

    @Override // cn.missfresh.sherlock.to.CommonTO
    public int getNetwork() {
        return this.network;
    }

    @Override // cn.missfresh.sherlock.to.CommonTO
    public String getRegion() {
        return this.region;
    }

    public String getRequestMethod() {
        return this.requestMethod;
    }

    @Override // cn.missfresh.sherlock.to.CommonTO
    public Long getTimestamp() {
        return this.timestamp;
    }

    public String getUrl() {
        return this.url;
    }

    @Override // cn.missfresh.sherlock.to.CommonTO
    public String getVc() {
        return this.vc;
    }

    public void setCostedMillis(long j) {
        this.costedMillis = j;
    }

    public void setDomain(String str) {
        this.domain = str;
    }

    @Override // cn.missfresh.sherlock.to.CommonTO
    public void setNetwork(int i) {
        this.network = i;
    }

    @Override // cn.missfresh.sherlock.to.CommonTO
    public void setRegion(String str) {
        this.region = str;
    }

    public void setRequestMethod(String str) {
        this.requestMethod = str;
    }

    @Override // cn.missfresh.sherlock.to.CommonTO
    public void setTimestamp(Long l) {
        this.timestamp = l;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    @Override // cn.missfresh.sherlock.to.CommonTO
    public void setVc(String str) {
        this.vc = str;
    }
}
