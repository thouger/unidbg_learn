package cn.missfresh.sherlock.okhttp;

import android.provider.Downloads;
import android.text.TextUtils;
import cn.missfresh.sherlock.bo.ApiBO;
import cn.missfresh.sherlock.config.Config;
import cn.missfresh.sherlock.config.Type;
import cn.missfresh.sherlock.e;
import cn.missfresh.sherlock.to.CommonTO;
import cn.missfresh.sherlock.to.NetApiBO;
import cn.missfresh.sherlock.to.NetworkParamTO;
import cn.missfresh.sherlock.to.NetworkTO;
import cn.missfresh.sherlock.tool.Utils;
import cn.missfresh.sherlock.tool.c;
import cn.missfresh.sherlock.tool.f;
import cn.missfresh.sherlock.tool.j;
import com.huawei.hms.support.api.push.pushselfshow.prepare.NotificationIconUtil;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.taobao.accs.common.Constants;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okio.Buffer;

/* compiled from: HttpManager */
public class a {
    private int a;
    private List<NetworkParamTO> b;

    /* compiled from: HttpManager */
    public static class b {
        private static final a a = new a();
    }

    public static a a() {
        return b.a;
    }

    private boolean a(String str) {
        return TextUtils.isEmpty(str) || str.contains("apm.missfresh.net");
    }

    private boolean b(String str) {
        return str.toUpperCase().contains(".JPG") || str.toUpperCase().contains(".PNG") || str.toUpperCase().contains(".GIF");
    }

    private boolean d(String str) {
        if (Utils.a(Config.getInstance().whiteList) && Utils.a(e.j())) {
            return true;
        }
        if (!Utils.a(Config.getInstance().whiteList)) {
            for (String str2 : Config.getInstance().whiteList) {
                if (str.contains(str2)) {
                    return true;
                }
            }
        }
        if (Utils.a(e.j())) {
            return false;
        }
        for (String str3 : e.j()) {
            if (str.contains(str3)) {
                return true;
            }
        }
        return false;
    }

    public void a(int i) {
        this.a = i;
    }

    public void b() {
        j.b("HttpManager", "initHttpManager");
    }

    private a() {
        this.a = 0;
        this.b = new ArrayList();
    }

    private String a(Response response) {
        String str;
        if (response.request().method().equals("HEAD")) {
            return null;
        }
        try {
            if (f.a(response.headers())) {
                j.a("HttpManager", "obtain body gzip");
                str = f.a(response.peekBody(1048576).bytes());
            } else {
                str = response.peekBody(1048576).string();
            }
            return str;
        } catch (Exception e) {
            j.a("HttpManager", "obtain body exception :" + e.getMessage());
            return null;
        }
    }

    private NetworkTO c(NetworkTO networkTO) {
        networkTO.setEventType(Type.NETWORK.getValue());
        networkTO.setTimestamp(Long.valueOf(System.currentTimeMillis()));
        networkTO.setNetwork(cn.missfresh.sherlock.d.a.a(e.e()));
        networkTO.setNetworkOperator(cn.missfresh.sherlock.d.a.b(e.e()));
        if (!TextUtils.isEmpty(e.g())) {
            networkTO.setUserId(e.g());
        }
        networkTO.setPhoneNumber(e.h());
        networkTO.setRequestMode(1);
        networkTO.setRequestSource(1);
        networkTO.setRegion(c.a(e.e()));
        networkTO.setVc(cn.missfresh.sherlock.d.a.b());
        if (!TextUtils.isEmpty(networkTO.getUrl()) && TextUtils.isEmpty(networkTO.getDomain())) {
            networkTO.setDomain(f(networkTO.getUrl()));
        }
        return networkTO;
    }

    public synchronized void a(Response response, long j) {
        if (response != null) {
            if (Config.getInstance().enableHttpSwitch()) {
                String httpUrl = response.request().url().toString();
                if (!a(httpUrl)) {
                    if (!b(httpUrl)) {
                        String a = a(response);
                        int c = c(a);
                        NetworkTO a2 = a(response, a, c, j);
                        if (e.m() != null) {
                            e.m().a(a2);
                        }
                        if (d(httpUrl)) {
                            if (Config.getInstance().resouceSwitch == 1 && a2.getResponseTime() > ((long) Config.getInstance().apiLevel)) {
                                NetApiBO apiBO = a2.getApiBO();
                                apiBO.setEventType(Type.API.getValue());
                                cn.missfresh.sherlock.c.a().a((CommonTO) apiBO);
                                j.b("HttpManager", "net api to request url : " + a2.getUrl());
                            }
                            if (this.b.size() >= 50) {
                                this.b.remove(0);
                            }
                            a2.setIp(Utils.g(e.e()));
                            this.b.add(b(a2));
                            if (a(response, c)) {
                                j.b("HttpManager", "interceptor, request url : " + a2.getUrl());
                                cn.missfresh.sherlock.c.a().a((CommonTO) a2);
                            }
                        }
                    }
                }
            }
        }
    }

    private NetworkParamTO b(NetworkTO networkTO) {
        NetworkParamTO networkParamTO = new NetworkParamTO();
        networkParamTO.setUrl(networkTO.getUrl());
        networkParamTO.setRequestId(networkTO.getRequestId());
        if (TextUtils.isEmpty(networkParamTO.getRequestId()) || Config.getInstance().enableNetowrkSwitch()) {
            networkParamTO.setRequestBody(networkTO.getRequestBody());
            networkParamTO.setRequestHeaders(networkTO.getRequestHeaders());
            networkParamTO.setRequestParameter(networkTO.getRequestParameter());
            networkParamTO.setResponseBody(networkTO.getResponseBody());
        }
        networkParamTO.setVc(networkTO.getVc());
        networkParamTO.setResponseTime(networkTO.getResponseTime());
        return networkParamTO;
    }

    private int c(String str) {
        try {
            if (TextUtils.isEmpty(str) || !str.contains(Constants.KEY_HTTP_CODE)) {
                return 0;
            }
            ApiBO apiBO = (ApiBO) cn.missfresh.sherlock.tool.e.a.fromJson(str, (Class<Object>) ApiBO.class);
            if (apiBO != null) {
                return apiBO.getCode();
            }
            return -1;
        } catch (Exception e) {
            j.a("HttpManager", "get response code exception : " + e.getMessage());
            return -1;
        }
    }

    private String c(Request request) {
        if (request == null) {
            return null;
        }
        try {
            String httpUrl = request.url().toString();
            return httpUrl.contains("?") ? httpUrl.split("\\?")[0] : httpUrl;
        } catch (Exception e) {
            j.a("HttpManager", "request id : " + e.getMessage());
            return null;
        }
    }

    private boolean b(Response response, int i) {
        return response.isSuccessful() && i == this.a;
    }

    private String b(Request request) {
        if (request == null) {
            return null;
        }
        try {
            return request.header("request-id");
        } catch (Exception e) {
            j.a("HttpManager", "request id : " + e.getMessage());
            return null;
        }
    }

    private String e(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            return str.contains("?") ? str.split("\\?")[0] : str;
        } catch (Exception e) {
            j.a("HttpManager", "request id : " + e.getMessage());
            return null;
        }
    }

    public synchronized void a(Map<String, Object> map, int i) {
        if (map != null) {
            if (!map.isEmpty()) {
                try {
                    NetworkTO networkTO = new NetworkTO();
                    networkTO.setUrl((String) map.get("url"));
                    networkTO.setRequestParameter((String) map.get("queryParameters"));
                    networkTO.setRequestBody(cn.missfresh.sherlock.tool.e.b.toJson(map.get("requestData").toString()));
                    networkTO.setRequestHeaders(cn.missfresh.sherlock.tool.e.b.toJson(map.get(Downloads.Impl.RequestHeaders.URI_SEGMENT).toString()));
                    networkTO.setRequestMethod((String) map.get("method"));
                    networkTO.setStatusCode(String.valueOf(map.get(HiAnalyticsConstant.HaKey.BI_KEY_RESULT)));
                    networkTO.setResponseCode(String.valueOf(map.get("responseCode")));
                    networkTO.setResponseMessage((String) map.get("statusMessage"));
                    networkTO.setResponseBody((String) map.get("data"));
                    networkTO.setResponseTime(((Integer) map.get("responseTime")).longValue());
                    networkTO.setResponseHeaders((String) map.get("responseHeaders"));
                    boolean z = true;
                    if (i != 1) {
                        z = false;
                    }
                    networkTO.setSuccess(z);
                    a(networkTO);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void a(NetworkTO networkTO) {
        if (Config.getInstance().enableHttpSwitch()) {
            String e = e(networkTO.getUrl());
            if (!a(e)) {
                if (!b(e)) {
                    c(networkTO);
                    if (e.m() != null) {
                        e.m().a(networkTO);
                    }
                    if (d(e)) {
                        if (Config.getInstance().resouceSwitch == 1 && networkTO.getResponseTime() > ((long) Config.getInstance().apiLevel)) {
                            NetApiBO apiBO = networkTO.getApiBO();
                            apiBO.setEventType(Type.API.getValue());
                            cn.missfresh.sherlock.c.a().a((CommonTO) apiBO);
                            j.b("HttpManager", "net api to request url : " + e);
                        }
                        if (this.b.size() >= 50) {
                            this.b.remove(0);
                        }
                        networkTO.setIp(Utils.g(e.e()));
                        this.b.add(b(networkTO));
                        if (!networkTO.isSuccess()) {
                            j.b("HttpManager", "interceptor, request url : " + e);
                            cn.missfresh.sherlock.c.a().a((CommonTO) networkTO);
                        }
                    }
                }
            }
        }
    }

    private NetworkTO a(Response response, String str, int i, long j) {
        NetworkTO networkTO = new NetworkTO();
        networkTO.setEventType(Type.NETWORK.getValue());
        networkTO.setTimestamp(Long.valueOf(System.currentTimeMillis()));
        networkTO.setNetwork(cn.missfresh.sherlock.d.a.a(e.e()));
        networkTO.setNetworkOperator(cn.missfresh.sherlock.d.a.b(e.e()));
        if (!TextUtils.isEmpty(e.g())) {
            networkTO.setUserId(e.g());
        }
        networkTO.setPhoneNumber(e.h());
        networkTO.setRequestMode(1);
        networkTO.setRequestSource(1);
        networkTO.setRequestMethod(response.request().method());
        networkTO.setUrl(c(response.request()));
        networkTO.setDomain(response.request().url().host());
        networkTO.setStatusCode(String.valueOf(response.code()));
        networkTO.setRequestParameter(response.request().url().query());
        networkTO.setResponseCode(String.valueOf(i));
        networkTO.setResponseMessage(response.message());
        networkTO.setRequestBody(a(response.request()));
        networkTO.setResponseBody(str);
        networkTO.setRequestHeaders(response.request().headers().toString());
        networkTO.setRequestId(b(response.request()));
        networkTO.setRegion(c.a(e.e()));
        networkTO.setVc(cn.missfresh.sherlock.d.a.b());
        networkTO.setResponseHeaders(b(response));
        networkTO.setResponseTime(j);
        return networkTO;
    }

    private String b(Response response) {
        if (response == null) {
            return null;
        }
        try {
            return response.headers().toString();
        } catch (Exception e) {
            j.a("HttpManager", "get response header exception : " + e.getMessage());
            return null;
        }
    }

    private boolean a(Response response, int i) {
        return !b(response, i);
    }

    public List<NetworkParamTO> c() {
        return this.b;
    }

    private String a(Request request) {
        if (request == null) {
            return null;
        }
        try {
            Request build = request.newBuilder().build();
            if (build.body() == null) {
                return null;
            }
            RequestBody body = build.body();
            Buffer buffer = new Buffer();
            body.writeTo(buffer);
            Charset forName = Charset.forName("UTF-8");
            MediaType contentType = body.contentType();
            if (contentType != null) {
                forName = contentType.charset(Charset.defaultCharset());
            }
            return buffer.readString(forName);
        } catch (Exception e) {
            j.a("HttpManager", "request body to string exception : " + e.getMessage());
            return "did not work";
        }
    }

    private String f(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        try {
            String substring = str.substring(str.indexOf(NotificationIconUtil.SPLIT_CHAR) + 2, str.length());
            return substring.substring(0, substring.indexOf(NotificationIconUtil.SPLIT_CHAR));
        } catch (Exception e) {
            j.a("HttpManager", "request id : " + e.getMessage());
            return null;
        }
    }
}
