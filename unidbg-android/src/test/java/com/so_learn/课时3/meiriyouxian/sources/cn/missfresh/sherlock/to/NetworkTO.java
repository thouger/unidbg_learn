package cn.missfresh.sherlock.to;

import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import java.io.Serializable;
import java.util.regex.Pattern;

public class NetworkTO extends CommonTO implements Serializable {
    private static final long serialVersionUID = -4381820877437858822L;
    private long cost;
    private String domain;
    private String ip;
    private String requestBody;
    private String requestHeaders;
    private String requestId;
    private String requestMethod;
    private int requestMode;
    private String requestParameter;
    private int requestSource;
    private String responseBody;
    private String responseCode;
    private String responseHeaders;
    private String responseMessage;
    private long responseTime;
    private String statusCode;
    private boolean success;
    private String url;

    private String obtainUrlNoNum(String str) {
        try {
            int lastIndexOf = str.lastIndexOf(NotificationIconUtil.SPLIT_CHAR);
            if (Pattern.compile("[0-9]*").matcher(str.substring(lastIndexOf + 1, str.length())).matches()) {
                return str.substring(0, lastIndexOf);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    public NetApiBO getApiBO() {
        NetApiBO netApiBO = new NetApiBO();
        netApiBO.setRequestMethod(this.requestMethod);
        netApiBO.setCostedMillis(this.responseTime);
        netApiBO.setUrl(obtainUrlNoNum(this.url));
        netApiBO.setDomain(this.domain);
        netApiBO.setVc(this.vc);
        netApiBO.setRegion(this.region);
        netApiBO.setTimestamp(this.timestamp);
        netApiBO.setNetwork(this.network);
        return netApiBO;
    }

    public long getCost() {
        return this.cost;
    }

    public String getDomain() {
        return this.domain;
    }

    public String getIp() {
        return this.ip;
    }

    public String getRequestBody() {
        return this.requestBody;
    }

    public String getRequestHeaders() {
        return this.requestHeaders;
    }

    public String getRequestId() {
        return this.requestId;
    }

    public String getRequestMethod() {
        return this.requestMethod;
    }

    public int getRequestMode() {
        return this.requestMode;
    }

    public String getRequestParameter() {
        return this.requestParameter;
    }

    public int getRequestSource() {
        return this.requestSource;
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public String getResponseHeaders() {
        return this.responseHeaders;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

    public long getResponseTime() {
        return this.responseTime;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public String getUrl() {
        return this.url;
    }

    public boolean isSuccess() {
        return this.success;
    }

    public void setCost(long j) {
        this.cost = j;
    }

    public void setDomain(String str) {
        this.domain = str;
    }

    public void setIp(String str) {
        this.ip = str;
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

    public void setRequestMethod(String str) {
        this.requestMethod = str;
    }

    public void setRequestMode(int i) {
        this.requestMode = i;
    }

    public void setRequestParameter(String str) {
        this.requestParameter = str;
    }

    public void setRequestSource(int i) {
        this.requestSource = i;
    }

    public void setResponseBody(String str) {
        this.responseBody = str;
    }

    public void setResponseCode(String str) {
        this.responseCode = str;
    }

    public void setResponseHeaders(String str) {
        this.responseHeaders = str;
    }

    public void setResponseMessage(String str) {
        this.responseMessage = str;
    }

    public void setResponseTime(long j) {
        this.responseTime = j;
    }

    public void setStatusCode(String str) {
        this.statusCode = str;
    }

    public void setSuccess(boolean z) {
        this.success = z;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    @Override // java.lang.Object
    public String toString() {
        return " \n# SherlockNetwork\n* requestMethod:\t" + this.requestMethod + "\n* url:\t" + this.url + "\n* domain:\t" + this.domain + "\n* statusCode:\t" + this.statusCode + "\n* requestHeaders:\t" + this.requestHeaders + "\n* requestParameter:\t" + this.requestParameter + "\n* requestBody:\t" + this.requestBody + "\n* responseHeaders:\t" + this.responseHeaders + "\n* responseCode:\t" + this.responseCode + "\n* responseMessage:\t" + this.responseMessage + "\n* responseBody:\t" + this.responseBody + "\n* responseTime:\t" + this.responseTime + "\n* cost:\t" + this.cost + "\n";
    }
}
