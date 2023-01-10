package cn.missfresh.sherlock.to;

public class NetworkParamTO {
    private String requestBody;
    private String requestHeaders;
    private String requestId;
    private String requestParameter;
    private String responseBody;
    private long responseTime;
    private String url;
    private String vc;

    public String getRequestBody() {
        return this.requestBody;
    }

    public String getRequestHeaders() {
        return this.requestHeaders;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public String getRequestParameter() {
        return this.requestParameter;
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public long getResponseTime() {
        return this.responseTime;
    }

    public String getUrl() {
        return this.url;
    }

    public String getVc() {
        return this.vc;
    }

    public void setRequestBody(String str) {
        this.requestBody = str;
    }

    public void setRequestHeaders(String str) {
        this.requestHeaders = str;
    }

    public void setRequestId(String str) {
        this.requestId = str;
    }

    public void setRequestParameter(String str) {
        this.requestParameter = str;
    }

    public void setResponseBody(String str) {
        this.responseBody = str;
    }

    public void setResponseTime(long j) {
        this.responseTime = j;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setVc(String str) {
        this.vc = str;
    }
}
