package com.alipay.android.phone.mrpc.core;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.huawei.hms.framework.common.ContainerUtils;
import com.umeng.message.common.inter.ITagManager;
import com.umeng.message.util.HttpRequest;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Callable;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

public final class q implements Callable<u> {
    private static final HttpRequestRetryHandler e = new ad();
    protected l a;
    protected Context b;
    protected o c;
    String d;
    private HttpUriRequest f;
    private HttpContext g = new BasicHttpContext();
    private CookieStore h = new BasicCookieStore();
    private CookieManager i;
    private AbstractHttpEntity j;
    private HttpHost k;
    private URL l;
    private int m = 0;
    private boolean n = false;
    private boolean o = false;
    private String p = null;
    private String q;

    public q(l lVar, o oVar) {
        this.a = lVar;
        this.b = this.a.a;
        this.c = oVar;
    }

    private static long a(String[] strArr) {
        for (int i = 0; i < strArr.length; i++) {
            if ("max-age".equalsIgnoreCase(strArr[i])) {
                int i2 = i + 1;
                if (strArr[i2] != null) {
                    try {
                        return Long.parseLong(strArr[i2]);
                    } catch (Exception unused) {
                        continue;
                    }
                } else {
                    continue;
                }
            }
        }
        return 0;
    }

    private static HttpUrlHeader a(HttpResponse httpResponse) {
        HttpUrlHeader httpUrlHeader = new HttpUrlHeader();
        Header[] allHeaders = httpResponse.getAllHeaders();
        for (Header header : allHeaders) {
            httpUrlHeader.setHead(header.getName(), header.getValue());
        }
        return httpUrlHeader;
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x00c6 A[SYNTHETIC, Splitter:B:23:0x00c6] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.alipay.android.phone.mrpc.core.u a(org.apache.http.HttpResponse r10, int r11, java.lang.String r12) {
        /*
            r9 = this;
            java.lang.String r0 = "ArrayOutputStream close error!"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "\u5f00\u59cbhandle\uff0chandleResponse-1,"
            r1.<init>(r2)
            java.lang.Thread r2 = java.lang.Thread.currentThread()
            long r2 = r2.getId()
            r1.append(r2)
            org.apache.http.HttpEntity r1 = r10.getEntity()
            r2 = 0
            if (r1 == 0) goto L_0x00d6
            org.apache.http.StatusLine r3 = r10.getStatusLine()
            int r3 = r3.getStatusCode()
            r4 = 200(0xc8, float:2.8E-43)
            if (r3 != r4) goto L_0x00d6
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "200\uff0c\u5f00\u59cb\u5904\u7406\uff0chandleResponse-2,threadid = "
            r3.<init>(r4)
            java.lang.Thread r4 = java.lang.Thread.currentThread()
            long r4 = r4.getId()
            r3.append(r4)
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream     // Catch:{ all -> 0x00c2 }
            r3.<init>()     // Catch:{ all -> 0x00c2 }
            long r4 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00c0 }
            r9.a(r1, r3)     // Catch:{ all -> 0x00c0 }
            byte[] r1 = r3.toByteArray()     // Catch:{ all -> 0x00c0 }
            r6 = 0
            r9.o = r6     // Catch:{ all -> 0x00c0 }
            com.alipay.android.phone.mrpc.core.l r6 = r9.a     // Catch:{ all -> 0x00c0 }
            long r7 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00c0 }
            long r7 = r7 - r4
            r6.c(r7)     // Catch:{ all -> 0x00c0 }
            com.alipay.android.phone.mrpc.core.l r4 = r9.a     // Catch:{ all -> 0x00c0 }
            int r5 = r1.length     // Catch:{ all -> 0x00c0 }
            long r5 = (long) r5     // Catch:{ all -> 0x00c0 }
            r4.a(r5)     // Catch:{ all -> 0x00c0 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00c0 }
            java.lang.String r5 = "res:"
            r4.<init>(r5)     // Catch:{ all -> 0x00c0 }
            int r5 = r1.length     // Catch:{ all -> 0x00c0 }
            r4.append(r5)     // Catch:{ all -> 0x00c0 }
            com.alipay.android.phone.mrpc.core.p r4 = new com.alipay.android.phone.mrpc.core.p     // Catch:{ all -> 0x00c0 }
            com.alipay.android.phone.mrpc.core.HttpUrlHeader r5 = a(r10)     // Catch:{ all -> 0x00c0 }
            r4.<init>(r5, r11, r12, r1)     // Catch:{ all -> 0x00c0 }
            long r11 = b(r10)     // Catch:{ all -> 0x00c0 }
            org.apache.http.HttpEntity r10 = r10.getEntity()     // Catch:{ all -> 0x00c0 }
            org.apache.http.Header r10 = r10.getContentType()     // Catch:{ all -> 0x00c0 }
            if (r10 == 0) goto L_0x009f
            java.lang.String r10 = r10.getValue()     // Catch:{ all -> 0x00c0 }
            java.util.HashMap r10 = a(r10)     // Catch:{ all -> 0x00c0 }
            java.lang.String r1 = "charset"
            java.lang.Object r1 = r10.get(r1)     // Catch:{ all -> 0x00c0 }
            r2 = r1
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ all -> 0x00c0 }
            java.lang.String r1 = "Content-Type"
            java.lang.Object r10 = r10.get(r1)     // Catch:{ all -> 0x00c0 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ all -> 0x00c0 }
            goto L_0x00a0
        L_0x009f:
            r10 = r2
        L_0x00a0:
            r4.b(r10)     // Catch:{ all -> 0x00c0 }
            r4.a(r2)     // Catch:{ all -> 0x00c0 }
            long r1 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x00c0 }
            r4.a(r1)     // Catch:{ all -> 0x00c0 }
            r4.b(r11)     // Catch:{ all -> 0x00c0 }
            r3.close()     // Catch:{ IOException -> 0x00b5 }
            r2 = r4
            goto L_0x00df
        L_0x00b5:
            r10 = move-exception
            java.lang.RuntimeException r11 = new java.lang.RuntimeException
            java.lang.Throwable r10 = r10.getCause()
            r11.<init>(r0, r10)
            throw r11
        L_0x00c0:
            r10 = move-exception
            goto L_0x00c4
        L_0x00c2:
            r10 = move-exception
            r3 = r2
        L_0x00c4:
            if (r3 == 0) goto L_0x00d5
            r3.close()     // Catch:{ IOException -> 0x00ca }
            goto L_0x00d5
        L_0x00ca:
            r10 = move-exception
            java.lang.RuntimeException r11 = new java.lang.RuntimeException
            java.lang.Throwable r10 = r10.getCause()
            r11.<init>(r0, r10)
            throw r11
        L_0x00d5:
            throw r10
        L_0x00d6:
            if (r1 != 0) goto L_0x00df
            org.apache.http.StatusLine r10 = r10.getStatusLine()
            r10.getStatusCode()
        L_0x00df:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.android.phone.mrpc.core.q.a(org.apache.http.HttpResponse, int, java.lang.String):com.alipay.android.phone.mrpc.core.u");
    }

    private static HashMap<String, String> a(String str) {
        HashMap<String, String> hashMap = new HashMap<>();
        String[] split = str.split(";");
        for (String str2 : split) {
            String[] split2 = str2.indexOf(61) == -1 ? new String[]{"Content-Type", str2} : str2.split(ContainerUtils.KEY_VALUE_DELIMITER);
            hashMap.put(split2[0], split2[1]);
        }
        return hashMap;
    }

    private void a(HttpEntity httpEntity, OutputStream outputStream) {
        byte[] bArr;
        InputStream a = b.a(httpEntity);
        long contentLength = httpEntity.getContentLength();
        try {
            bArr = new byte[2048];
            outputStream.flush();
            r.a(a);
            return;
        } catch (Exception e2) {
            e2.getCause();
            throw new IOException("HttpWorker Request Error!" + e2.getLocalizedMessage());
        } catch (Throwable th) {
            r.a(a);
            throw th;
        }
        while (true) {
            int read = a.read(bArr);
            if (read == -1 || this.c.h()) {
                break;
            }
            outputStream.write(bArr, 0, read);
            if (this.c.f() != null) {
                int i = (contentLength > 0 ? 1 : (contentLength == 0 ? 0 : -1));
            }
        }
    }

    private static long b(HttpResponse httpResponse) {
        Header firstHeader = httpResponse.getFirstHeader("Cache-Control");
        if (firstHeader != null) {
            String[] split = firstHeader.getValue().split(ContainerUtils.KEY_VALUE_DELIMITER);
            if (split.length >= 2) {
                try {
                    return a(split);
                } catch (NumberFormatException unused) {
                }
            }
        }
        Header firstHeader2 = httpResponse.getFirstHeader(HttpRequest.HEADER_EXPIRES);
        if (firstHeader2 != null) {
            return b.b(firstHeader2.getValue()) - System.currentTimeMillis();
        }
        return 0;
    }

    private URI b() {
        String a = this.c.a();
        String str = this.d;
        if (str != null) {
            a = str;
        }
        if (a != null) {
            return new URI(a);
        }
        throw new RuntimeException("url should not be null");
    }

    private HttpUriRequest c() {
        HttpUriRequest httpUriRequest = this.f;
        if (httpUriRequest != null) {
            return httpUriRequest;
        }
        if (this.j == null) {
            byte[] b = this.c.b();
            String b2 = this.c.b("gzip");
            if (b != null) {
                if (TextUtils.equals(b2, ITagManager.STATUS_TRUE)) {
                    this.j = b.a(b);
                } else {
                    this.j = new ByteArrayEntity(b);
                }
                this.j.setContentType(this.c.c());
            }
        }
        AbstractHttpEntity abstractHttpEntity = this.j;
        if (abstractHttpEntity != null) {
            HttpPost httpPost = new HttpPost(b());
            httpPost.setEntity(abstractHttpEntity);
            this.f = httpPost;
        } else {
            this.f = new HttpGet(b());
        }
        return this.f;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0292, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x0293, code lost:
        e();
        r2 = r14.m;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0298, code lost:
        if (r2 <= 0) goto L_0x029a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x029a, code lost:
        r14.m = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x02a0, code lost:
        new java.lang.StringBuilder().append(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x02b5, code lost:
        throw new com.alipay.android.phone.mrpc.core.HttpException(0, java.lang.String.valueOf(r1));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x02b6, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x02b7, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x02c0, code lost:
        if (r14.c.f() != null) goto L_0x02c2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x02c2, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x02ca, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x02df, code lost:
        throw new com.alipay.android.phone.mrpc.core.HttpException(6, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x02e0, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x02e1, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x02ea, code lost:
        if (r14.c.f() != null) goto L_0x02ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x02ec, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x02f4, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x030b, code lost:
        throw new com.alipay.android.phone.mrpc.core.HttpException(9, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x030c, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x030d, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x0316, code lost:
        if (r14.c.f() != null) goto L_0x0318;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x0318, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x032f, code lost:
        throw new com.alipay.android.phone.mrpc.core.HttpException(8, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x0330, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x0331, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x033a, code lost:
        if (r14.c.f() != null) goto L_0x033c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x033c, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x0344, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x035a, code lost:
        throw new com.alipay.android.phone.mrpc.core.HttpException(5, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x035b, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x035c, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x0365, code lost:
        if (r14.c.f() != null) goto L_0x0367;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0367, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x036f, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0385, code lost:
        throw new com.alipay.android.phone.mrpc.core.HttpException(4, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0386, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x0387, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:138:0x0390, code lost:
        if (r14.c.f() != null) goto L_0x0392;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0392, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x039a, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x03af, code lost:
        throw new com.alipay.android.phone.mrpc.core.HttpException(3, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x03b0, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x03b1, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x03ba, code lost:
        if (r14.c.f() != null) goto L_0x03bc;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:145:0x03bc, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x03c4, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x03d9, code lost:
        throw new com.alipay.android.phone.mrpc.core.HttpException(3, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x03da, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:149:0x03db, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x03e4, code lost:
        if (r14.c.f() != null) goto L_0x03e6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x03e6, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x03ee, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x0403, code lost:
        throw new com.alipay.android.phone.mrpc.core.HttpException(6, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x0404, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x0405, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:156:0x040e, code lost:
        if (r14.c.f() != null) goto L_0x0410;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:0x0410, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0418, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x042d, code lost:
        throw new com.alipay.android.phone.mrpc.core.HttpException(2, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x042e, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:161:0x042f, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:0x0438, code lost:
        if (r14.c.f() != null) goto L_0x043a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x043a, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x0442, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x0457, code lost:
        throw new com.alipay.android.phone.mrpc.core.HttpException(2, java.lang.String.valueOf(r0));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0458, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x0465, code lost:
        throw new java.lang.RuntimeException("Url parser error!", r0.getCause());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x0466, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x0467, code lost:
        e();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x0470, code lost:
        if (r14.c.f() != null) goto L_0x0472;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0472, code lost:
        r0.getCode();
        r0.getMsg();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:173:0x0478, code lost:
        new java.lang.StringBuilder().append(r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0480, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x0292 A[ExcHandler: NullPointerException (r1v46 'e' java.lang.NullPointerException A[CUSTOM_DECLARE]), Splitter:B:2:0x0007] */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x02b6 A[ExcHandler: IOException (r0v22 'e' java.io.IOException A[CUSTOM_DECLARE]), Splitter:B:2:0x0007] */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x02e0 A[ExcHandler: UnknownHostException (r0v20 'e' java.net.UnknownHostException A[CUSTOM_DECLARE]), Splitter:B:2:0x0007] */
    /* JADX WARNING: Removed duplicated region for block: B:118:0x030c A[ExcHandler: HttpHostConnectException (r0v18 'e' org.apache.http.conn.HttpHostConnectException A[CUSTOM_DECLARE]), Splitter:B:2:0x0007] */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0330 A[ExcHandler: NoHttpResponseException (r0v16 'e' org.apache.http.NoHttpResponseException A[CUSTOM_DECLARE]), Splitter:B:2:0x0007] */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x035b A[ExcHandler: SocketTimeoutException (r0v14 'e' java.net.SocketTimeoutException A[CUSTOM_DECLARE]), Splitter:B:2:0x0007] */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x0386 A[ExcHandler: ConnectTimeoutException (r0v12 'e' org.apache.http.conn.ConnectTimeoutException A[CUSTOM_DECLARE]), Splitter:B:2:0x0007] */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x03b0 A[ExcHandler: ConnectionPoolTimeoutException (r0v10 'e' org.apache.http.conn.ConnectionPoolTimeoutException A[CUSTOM_DECLARE]), Splitter:B:2:0x0007] */
    /* JADX WARNING: Removed duplicated region for block: B:148:0x03da A[ExcHandler: SSLException (r0v8 'e' javax.net.ssl.SSLException A[CUSTOM_DECLARE]), Splitter:B:2:0x0007] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0404 A[ExcHandler: SSLPeerUnverifiedException (r0v6 'e' javax.net.ssl.SSLPeerUnverifiedException A[CUSTOM_DECLARE]), Splitter:B:2:0x0007] */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x042e A[ExcHandler: SSLHandshakeException (r0v4 'e' javax.net.ssl.SSLHandshakeException A[CUSTOM_DECLARE]), Splitter:B:2:0x0007] */
    /* JADX WARNING: Removed duplicated region for block: B:166:0x0458 A[ExcHandler: URISyntaxException (r0v2 'e' java.net.URISyntaxException A[CUSTOM_DECLARE]), Splitter:B:2:0x0007] */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x0466 A[ExcHandler: HttpException (r0v1 'e' com.alipay.android.phone.mrpc.core.HttpException A[CUSTOM_DECLARE]), Splitter:B:2:0x0007] */
    /* JADX WARNING: Removed duplicated region for block: B:179:0x0263 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0033 A[Catch:{ HttpException -> 0x0466, URISyntaxException -> 0x0458, SSLHandshakeException -> 0x042e, SSLPeerUnverifiedException -> 0x0404, SSLException -> 0x03da, ConnectionPoolTimeoutException -> 0x03b0, ConnectTimeoutException -> 0x0386, SocketTimeoutException -> 0x035b, NoHttpResponseException -> 0x0330, HttpHostConnectException -> 0x030c, UnknownHostException -> 0x02e0, IOException -> 0x02b6, NullPointerException -> 0x0292, Exception -> 0x0270 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0110 A[Catch:{ HttpException -> 0x0466, URISyntaxException -> 0x0458, SSLHandshakeException -> 0x042e, SSLPeerUnverifiedException -> 0x0404, SSLException -> 0x03da, ConnectionPoolTimeoutException -> 0x03b0, ConnectTimeoutException -> 0x0386, SocketTimeoutException -> 0x035b, NoHttpResponseException -> 0x0330, HttpHostConnectException -> 0x030c, UnknownHostException -> 0x02e0, IOException -> 0x02b6, NullPointerException -> 0x0292, Exception -> 0x0270 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0130 A[Catch:{ HttpException -> 0x0466, URISyntaxException -> 0x0458, SSLHandshakeException -> 0x042e, SSLPeerUnverifiedException -> 0x0404, SSLException -> 0x03da, ConnectionPoolTimeoutException -> 0x03b0, ConnectTimeoutException -> 0x0386, SocketTimeoutException -> 0x035b, NoHttpResponseException -> 0x0330, HttpHostConnectException -> 0x030c, UnknownHostException -> 0x02e0, IOException -> 0x02b6, NullPointerException -> 0x0292, Exception -> 0x0270 }] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0163 A[Catch:{ HttpException -> 0x0466, URISyntaxException -> 0x0458, SSLHandshakeException -> 0x042e, SSLPeerUnverifiedException -> 0x0404, SSLException -> 0x03da, ConnectionPoolTimeoutException -> 0x03b0, ConnectTimeoutException -> 0x0386, SocketTimeoutException -> 0x035b, NoHttpResponseException -> 0x0330, HttpHostConnectException -> 0x030c, UnknownHostException -> 0x02e0, IOException -> 0x02b6, NullPointerException -> 0x0292, Exception -> 0x0270 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0170 A[Catch:{ HttpException -> 0x0466, URISyntaxException -> 0x0458, SSLHandshakeException -> 0x042e, SSLPeerUnverifiedException -> 0x0404, SSLException -> 0x03da, ConnectionPoolTimeoutException -> 0x03b0, ConnectTimeoutException -> 0x0386, SocketTimeoutException -> 0x035b, NoHttpResponseException -> 0x0330, HttpHostConnectException -> 0x030c, UnknownHostException -> 0x02e0, IOException -> 0x02b6, NullPointerException -> 0x0292, Exception -> 0x0270 }] */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x01e9 A[Catch:{ HttpException -> 0x0466, URISyntaxException -> 0x0458, SSLHandshakeException -> 0x042e, SSLPeerUnverifiedException -> 0x0404, SSLException -> 0x03da, ConnectionPoolTimeoutException -> 0x03b0, ConnectTimeoutException -> 0x0386, SocketTimeoutException -> 0x035b, NoHttpResponseException -> 0x0330, HttpHostConnectException -> 0x030c, UnknownHostException -> 0x02e0, IOException -> 0x02b6, NullPointerException -> 0x0292, Exception -> 0x0270 }] */
    /* renamed from: d */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.alipay.android.phone.mrpc.core.u call() {
        /*
        // Method dump skipped, instructions count: 1153
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.android.phone.mrpc.core.q.call():com.alipay.android.phone.mrpc.core.u");
    }

    private void e() {
        HttpUriRequest httpUriRequest = this.f;
        if (httpUriRequest != null) {
            httpUriRequest.abort();
        }
    }

    private String f() {
        if (!TextUtils.isEmpty(this.q)) {
            return this.q;
        }
        this.q = this.c.b("operationType");
        return this.q;
    }

    private int g() {
        URL h = h();
        return h.getPort() == -1 ? h.getDefaultPort() : h.getPort();
    }

    private URL h() {
        URL url = this.l;
        if (url != null) {
            return url;
        }
        this.l = new URL(this.c.a());
        return this.l;
    }

    private CookieManager i() {
        CookieManager cookieManager = this.i;
        if (cookieManager != null) {
            return cookieManager;
        }
        this.i = CookieManager.getInstance();
        return this.i;
    }

    public final o a() {
        return this.c;
    }
}
