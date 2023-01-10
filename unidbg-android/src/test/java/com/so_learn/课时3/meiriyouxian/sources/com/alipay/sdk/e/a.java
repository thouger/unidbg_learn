package com.alipay.sdk.e;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.CookieManager;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.Map;

public final class a {
    private static final CookieManager a = new CookieManager();

    /* renamed from: com.alipay.sdk.e.a$a  reason: collision with other inner class name */
    public static final class C0065a {
        public final String a;
        public final byte[] b;
        public final Map<String, String> c;

        public C0065a(String str, Map<String, String> map, byte[] bArr) {
            this.a = str;
            this.b = bArr;
            this.c = map;
        }

        public String toString() {
            return String.format("<UrlConnectionConfigure url=%s headers=%s>", this.a, this.c);
        }
    }

    public static final class b {
        public final Map<String, List<String>> a;
        public final String b;
        public final byte[] c;

        public b(Map<String, List<String>> map, String str, byte[] bArr) {
            this.a = map;
            this.b = str;
            this.c = bArr;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [java.io.InputStream] */
    /* JADX WARN: Type inference failed for: r0v14 */
    /* JADX WARN: Type inference failed for: r0v18 */
    /* JADX WARNING: Removed duplicated region for block: B:106:0x01d8 A[SYNTHETIC, Splitter:B:106:0x01d8] */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x01bd A[SYNTHETIC, Splitter:B:84:0x01bd] */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x01c2 A[SYNTHETIC, Splitter:B:88:0x01c2] */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x01c7 A[SYNTHETIC, Splitter:B:92:0x01c7] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.alipay.sdk.e.a.b a(android.content.Context r12, com.alipay.sdk.e.a.C0065a r13) {
        /*
        // Method dump skipped, instructions count: 476
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.sdk.e.a.a(android.content.Context, com.alipay.sdk.e.a$a):com.alipay.sdk.e.a$b");
    }

    private static Proxy a(Context context) {
        String c = c(context);
        if (c != null && !c.contains("wap")) {
            return null;
        }
        try {
            String property = System.getProperty("https.proxyHost");
            String property2 = System.getProperty("https.proxyPort");
            if (!TextUtils.isEmpty(property)) {
                return new Proxy(Proxy.Type.HTTP, new InetSocketAddress(property, Integer.parseInt(property2)));
            }
            return null;
        } catch (Throwable unused) {
            return null;
        }
    }

    private static NetworkInfo b(Context context) {
        if (context == null) {
            return null;
        }
        try {
            return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        } catch (Exception unused) {
            return null;
        }
    }

    private static String c(Context context) {
        try {
            NetworkInfo b2 = b(context);
            if (b2 != null && b2.isAvailable()) {
                if (b2.getType() == 1) {
                    return "wifi";
                }
                return b2.getExtraInfo().toLowerCase();
            }
        } catch (Exception unused) {
        }
        return "none";
    }

    private static byte[] a(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        while (true) {
            int read = inputStream.read(bArr, 0, bArr.length);
            if (read != -1) {
                byteArrayOutputStream.write(bArr, 0, read);
            } else {
                byteArrayOutputStream.flush();
                return byteArrayOutputStream.toByteArray();
            }
        }
    }
}
