package com.sina.weibo.sdk.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.webkit.URLUtil;
import com.sina.weibo.sdk.a.d;
import com.sina.weibo.sdk.a.f;
import com.sina.weibo.sdk.a.k;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.exception.WeiboHttpException;
import com.tencent.connect.common.Constants;
import com.umeng.message.util.HttpRequest;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpProtocolParams;

public class HttpManager {
    private static final String a = b();
    private static final String b = ("--" + a);
    private static final String c = ("--" + a + "--");
    private static SSLSocketFactory d;

    private static native String calcOauthSignNative(Context context, String str, String str2);

    static {
        System.loadLibrary("weibosdkcore");
    }

    public static String a(Context context, String str, String str2, e eVar) throws WeiboException {
        String a2 = a(c(context, str, str2, eVar));
        d.a("HttpManager", "Response : " + a2);
        return a2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:55:0x0136 A[SYNTHETIC, Splitter:B:55:0x0136] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static org.apache.http.HttpResponse c(android.content.Context r6, java.lang.String r7, java.lang.String r8, com.sina.weibo.sdk.net.e r9) {
        /*
        // Method dump skipped, instructions count: 317
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sina.weibo.sdk.net.HttpManager.c(android.content.Context, java.lang.String, java.lang.String, com.sina.weibo.sdk.net.e):org.apache.http.HttpResponse");
    }

    private static void a(Context context, e eVar) {
        String str;
        String str2 = "";
        if (!TextUtils.isEmpty(eVar.a())) {
            str = k.b(context, eVar.a());
            if (!TextUtils.isEmpty(str)) {
                eVar.a("aid", str);
            }
        } else {
            str = str2;
        }
        String d2 = d();
        eVar.a("oauth_timestamp", d2);
        Object a2 = eVar.a(Constants.PARAM_ACCESS_TOKEN);
        Object a3 = eVar.a("refresh_token");
        Object a4 = eVar.a("phone");
        if (a2 != null && (a2 instanceof String)) {
            str2 = (String) a2;
        } else if (a3 != null && (a3 instanceof String)) {
            str2 = (String) a3;
        } else if (a4 != null && (a4 instanceof String)) {
            str2 = (String) a4;
        }
        eVar.a("oauth_sign", a(context, str, str2, eVar.a(), d2));
    }

    public static void a(HttpClient httpClient) {
        if (httpClient != null) {
            try {
                httpClient.getConnectionManager().closeExpiredConnections();
            } catch (Exception unused) {
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sina.weibo.sdk.net.HttpManager$1  reason: invalid class name */
    public class AnonymousClass1 extends a {
        @Override // com.sina.weibo.sdk.net.a
        public void a() {
        }

        @Override // com.sina.weibo.sdk.net.a
        public boolean a(String str) {
            return true;
        }

        AnonymousClass1() {
        }
    }

    public static String b(Context context, String str, String str2, e eVar) {
        Throwable th;
        IOException e;
        HttpGet httpGet = null;
        try {
            AnonymousClass1 r1 = new AnonymousClass1();
            HttpGet httpGet2 = (DefaultHttpClient) a();
            try {
                httpGet2.setRedirectHandler(r1);
                a(context, eVar);
                httpGet2.getParams().setParameter("http.route.default-proxy", NetStateManager.a());
                if (str2.equals("GET")) {
                    httpGet = new HttpGet(String.valueOf(str) + "?" + eVar.c());
                } else if (str2.equals("POST")) {
                    httpGet = new HttpPost(str);
                }
                httpGet.setHeader(HttpRequest.HEADER_USER_AGENT, f.f(context));
                httpGet2.execute(httpGet);
                String b2 = r1.b();
                a((HttpClient) httpGet2);
                return b2;
            } catch (IOException e2) {
                e = e2;
                httpGet = httpGet2;
                try {
                    throw new WeiboException(e);
                } catch (Throwable th2) {
                    th = th2;
                    httpGet2 = httpGet;
                    a((HttpClient) httpGet2);
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                a((HttpClient) httpGet2);
                throw th;
            }
        } catch (IOException e3) {
            e = e3;
            throw new WeiboException(e);
        }
    }

    public static synchronized String a(Context context, String str, String str2, String str3) throws WeiboException {
        ClientConnectionManager connectionManager;
        TimeUnit timeUnit;
        long j;
        long j2;
        InputStream inputStream;
        synchronized (HttpManager.class) {
            File file = new File(str2);
            if (!file.exists()) {
                file.mkdirs();
            }
            File file2 = new File(file, str3);
            if (file2.exists()) {
                return file2.getPath();
            } else if (!URLUtil.isValidUrl(str)) {
                return "";
            } else {
                HttpClient a2 = a();
                File file3 = new File(str2, String.valueOf(str3) + "_temp");
                try {
                    if (file3.exists()) {
                        j = file3.length();
                    } else {
                        file3.createNewFile();
                        j = 0;
                    }
                    HttpGet httpGet = new HttpGet(str);
                    httpGet.setHeader("RANGE", "bytes=" + j + "-");
                    HttpResponse execute = a2.execute(httpGet);
                    int statusCode = execute.getStatusLine().getStatusCode();
                    if (statusCode == 206) {
                        Header[] headers = execute.getHeaders("Content-Range");
                        if (headers == null || headers.length == 0) {
                            j2 = 0;
                        } else {
                            String value = headers[0].getValue();
                            j2 = Long.parseLong(value.substring(value.indexOf(47) + 1));
                        }
                    } else if (statusCode == 200) {
                        Header firstHeader = execute.getFirstHeader("Content-Length");
                        if (firstHeader != null) {
                            j2 = (long) Integer.valueOf(firstHeader.getValue()).intValue();
                            j = 0;
                        } else {
                            j = 0;
                            j2 = 0;
                        }
                    } else {
                        throw new WeiboHttpException(a(execute), statusCode);
                    }
                    HttpEntity entity = execute.getEntity();
                    Header firstHeader2 = execute.getFirstHeader("Content-Encoding");
                    if (firstHeader2 == null || firstHeader2.getValue().toLowerCase().indexOf("gzip") <= -1) {
                        inputStream = entity.getContent();
                    } else {
                        inputStream = new GZIPInputStream(entity.getContent());
                    }
                    RandomAccessFile randomAccessFile = new RandomAccessFile(file3, "rw");
                    randomAccessFile.seek(j);
                    byte[] bArr = new byte[1024];
                    while (true) {
                        int read = inputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        randomAccessFile.write(bArr, 0, read);
                    }
                    randomAccessFile.close();
                    inputStream.close();
                    if (j2 != 0) {
                        if (file3.length() >= j2) {
                            file3.renameTo(file2);
                            String path = file2.getPath();
                            if (a2 != null) {
                                a2.getConnectionManager().closeExpiredConnections();
                                a2.getConnectionManager().closeIdleConnections(300, TimeUnit.SECONDS);
                            }
                            return path;
                        }
                    }
                    file3.delete();
                    if (a2 != null) {
                        a2.getConnectionManager().closeExpiredConnections();
                        connectionManager = a2.getConnectionManager();
                        timeUnit = TimeUnit.SECONDS;
                        connectionManager.closeIdleConnections(300, timeUnit);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    file3.delete();
                    if (a2 != null) {
                        a2.getConnectionManager().closeExpiredConnections();
                        connectionManager = a2.getConnectionManager();
                        timeUnit = TimeUnit.SECONDS;
                    }
                } catch (Throwable th) {
                    if (a2 != null) {
                        a2.getConnectionManager().closeExpiredConnections();
                        a2.getConnectionManager().closeIdleConnections(300, TimeUnit.SECONDS);
                    }
                    throw th;
                }
                return "";
            }
        }
    }

    public static HttpClient a() {
        try {
            BasicHttpParams basicHttpParams = new BasicHttpParams();
            HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(basicHttpParams, "UTF-8");
            SchemeRegistry schemeRegistry = new SchemeRegistry();
            schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schemeRegistry.register(new Scheme("https", c(), (int) com.taobao.accs.common.Constants.PORT));
            ThreadSafeClientConnManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, 25000);
            HttpConnectionParams.setSoTimeout(basicHttpParams, 20000);
            return new DefaultHttpClient(threadSafeClientConnManager, basicHttpParams);
        } catch (Exception unused) {
            return new DefaultHttpClient();
        }
    }

    public static void a(OutputStream outputStream, e eVar) throws WeiboException {
        try {
            Set<String> b2 = eVar.b();
            for (String str : b2) {
                if (eVar.a(str) instanceof String) {
                    StringBuilder sb = new StringBuilder(100);
                    sb.setLength(0);
                    sb.append(b);
                    sb.append("\r\n");
                    sb.append("content-disposition: form-data; name=\"");
                    sb.append(str);
                    sb.append("\"\r\n\r\n");
                    sb.append(eVar.a(str));
                    sb.append("\r\n");
                    outputStream.write(sb.toString().getBytes());
                }
            }
            for (String str2 : b2) {
                Object a2 = eVar.a(str2);
                if (a2 instanceof Bitmap) {
                    outputStream.write((b + "\r\ncontent-disposition: form-data; name=\"" + str2 + "\"; filename=\"file\"\r\nContent-Type: application/octet-stream; charset=utf-8\r\n\r\n").getBytes());
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    ((Bitmap) a2).compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                    outputStream.write(byteArrayOutputStream.toByteArray());
                    outputStream.write("\r\n".getBytes());
                } else if (a2 instanceof ByteArrayOutputStream) {
                    outputStream.write((b + "\r\ncontent-disposition: form-data; name=\"" + str2 + "\"; filename=\"file\"\r\nContent-Type: application/octet-stream; charset=utf-8\r\n\r\n").getBytes());
                    ByteArrayOutputStream byteArrayOutputStream2 = (ByteArrayOutputStream) a2;
                    outputStream.write(byteArrayOutputStream2.toByteArray());
                    outputStream.write("\r\n".getBytes());
                    byteArrayOutputStream2.close();
                }
            }
            outputStream.write(("\r\n" + c).getBytes());
        } catch (IOException e) {
            throw new WeiboException(e);
        }
    }

    public static String a(HttpResponse httpResponse) throws WeiboException {
        InputStream inputStream = null;
        if (httpResponse == null) {
            return null;
        }
        HttpEntity entity = httpResponse.getEntity();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            InputStream content = entity.getContent();
            Header firstHeader = httpResponse.getFirstHeader("Content-Encoding");
            if (firstHeader != null && firstHeader.getValue().toLowerCase().indexOf("gzip") > -1) {
                content = new GZIPInputStream(content);
            }
            byte[] bArr = new byte[8192];
            while (true) {
                int read = content.read(bArr);
                if (read == -1) {
                    break;
                }
                byteArrayOutputStream.write(bArr, 0, read);
            }
            String str = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
            d.a("HttpManager", "readRsponse result : " + str);
            if (content != null) {
                try {
                    content.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                byteArrayOutputStream.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
            return str;
        } catch (IOException e3) {
            throw new WeiboException(e3);
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    inputStream.close();
                } catch (IOException e4) {
                    e4.printStackTrace();
                }
            }
            try {
                byteArrayOutputStream.close();
            } catch (IOException e5) {
                e5.printStackTrace();
            }
            throw th;
        }
    }

    public static String b() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 1; i < 12; i++) {
            long currentTimeMillis = System.currentTimeMillis() + ((long) i);
            long j = currentTimeMillis % 3;
            if (j == 0) {
                stringBuffer.append(((char) ((int) currentTimeMillis)) % '\t');
            } else if (j == 1) {
                stringBuffer.append((char) ((int) ((currentTimeMillis % 26) + 65)));
            } else {
                stringBuffer.append((char) ((int) ((currentTimeMillis % 26) + 97)));
            }
        }
        return stringBuffer.toString();
    }

    private static SSLSocketFactory c() {
        if (d == null) {
            try {
                KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
                instance.load(null, null);
                Certificate a2 = a("cacert_cn.cer");
                Certificate a3 = a("cacert_com.cer");
                instance.setCertificateEntry("cnca", a2);
                instance.setCertificateEntry("comca", a3);
                d = new d(instance);
                d.a("HttpManager", "getSSLSocketFactory noraml !!!!!");
            } catch (Exception e) {
                e.printStackTrace();
                d = SSLSocketFactory.getSocketFactory();
                d.a("HttpManager", "getSSLSocketFactory error default !!!!!");
            }
        }
        return d;
    }

    private static Certificate a(String str) throws CertificateException, IOException {
        CertificateFactory instance = CertificateFactory.getInstance("X.509");
        InputStream resourceAsStream = HttpManager.class.getResourceAsStream(str);
        try {
            return instance.generateCertificate(resourceAsStream);
        } finally {
            if (resourceAsStream != null) {
                resourceAsStream.close();
            }
        }
    }

    private static String d() {
        return String.valueOf(System.currentTimeMillis() / 1000);
    }

    private static String a(Context context, String str, String str2, String str3, String str4) {
        StringBuilder sb = new StringBuilder("");
        if (!TextUtils.isEmpty(str)) {
            sb.append(str);
        }
        if (!TextUtils.isEmpty(str2)) {
            sb.append(str2);
        }
        if (!TextUtils.isEmpty(str3)) {
            sb.append(str3);
        }
        return calcOauthSignNative(context, sb.toString(), str4);
    }
}
