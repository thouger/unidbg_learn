package cn.com.chinatelecom.account.api.b;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.SSLCertificateSocketFactory;
import android.text.TextUtils;
import cn.com.chinatelecom.account.api.CtAuth;
import cn.com.chinatelecom.account.api.CtSetting;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class d {
    private static final String a = d.class.getSimpleName();

    /* access modifiers changed from: package-private */
    /* renamed from: cn.com.chinatelecom.account.api.b.d$1  reason: invalid class name */
    public static class AnonymousClass1 implements HostnameVerifier {
        final /* synthetic */ String a;

        AnonymousClass1(String str) {
            this.a = str;
        }

        @Override // javax.net.ssl.HostnameVerifier
        public boolean verify(String str, SSLSession sSLSession) {
            AppMethodBeat.i(7940, false);
            boolean verify = HttpsURLConnection.getDefaultHostnameVerifier().verify(this.a, sSLSession);
            AppMethodBeat.o(7940);
            return verify;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.com.chinatelecom.account.api.b.d$2  reason: invalid class name */
    public static class AnonymousClass2 extends SSLSocketFactory {
        final /* synthetic */ String a;

        AnonymousClass2(String str) {
            this.a = str;
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(String str, int i) {
            return null;
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(String str, int i, InetAddress inetAddress, int i2) {
            return null;
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(InetAddress inetAddress, int i) {
            return null;
        }

        @Override // javax.net.SocketFactory
        public Socket createSocket(InetAddress inetAddress, int i, InetAddress inetAddress2, int i2) {
            return null;
        }

        @Override // javax.net.ssl.SSLSocketFactory
        public Socket createSocket(Socket socket, String str, int i, boolean z) {
            SSLSocket sSLSocket;
            Throwable th;
            AppMethodBeat.i(7948, false);
            try {
                SSLCertificateSocketFactory sSLCertificateSocketFactory = (SSLCertificateSocketFactory) SSLCertificateSocketFactory.getDefault(0);
                sSLSocket = (SSLSocket) sSLCertificateSocketFactory.createSocket(socket, this.a, i, z);
                try {
                    sSLSocket.setEnabledProtocols(sSLSocket.getSupportedProtocols());
                    sSLCertificateSocketFactory.setHostname(sSLSocket, this.a);
                } catch (Throwable th2) {
                    th = th2;
                }
            } catch (Throwable th3) {
                th = th3;
                sSLSocket = null;
                th.printStackTrace();
                AppMethodBeat.o(7948);
                return sSLSocket;
            }
            AppMethodBeat.o(7948);
            return sSLSocket;
        }

        @Override // javax.net.ssl.SSLSocketFactory
        public String[] getDefaultCipherSuites() {
            return new String[0];
        }

        @Override // javax.net.ssl.SSLSocketFactory
        public String[] getSupportedCipherSuites() {
            return new String[0];
        }
    }

    static {
        AppMethodBeat.i(7993, false);
        AppMethodBeat.o(7993);
    }

    /* JADX WARNING: Removed duplicated region for block: B:102:0x02fb  */
    /* JADX WARNING: Removed duplicated region for block: B:105:0x0303  */
    /* JADX WARNING: Removed duplicated region for block: B:107:0x0308  */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01e8  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x01ed  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x01f2  */
    /* JADX WARNING: Removed duplicated region for block: B:84:0x0244  */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0249  */
    /* JADX WARNING: Removed duplicated region for block: B:88:0x024e  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x02a0  */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x02a5  */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x02aa  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static cn.com.chinatelecom.account.api.b.f a(android.content.Context r17, java.lang.String r18, android.net.Network r19, java.lang.String r20) {
        /*
        // Method dump skipped, instructions count: 819
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.chinatelecom.account.api.b.d.a(android.content.Context, java.lang.String, android.net.Network, java.lang.String):cn.com.chinatelecom.account.api.b.f");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v1, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v2 */
    /* JADX WARN: Type inference failed for: r2v3, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v4 */
    /* JADX WARN: Type inference failed for: r2v5, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Type inference failed for: r2v11, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r12v3 */
    /* JADX WARN: Type inference failed for: r2v18 */
    /* JADX WARN: Type inference failed for: r2v19 */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v21 */
    /* JADX WARN: Type inference failed for: r12v6 */
    /* JADX WARN: Type inference failed for: r12v7 */
    /* JADX WARN: Type inference failed for: r12v8 */
    /* JADX WARN: Type inference failed for: r12v9 */
    /* JADX WARN: Type inference failed for: r12v10 */
    /* JADX WARN: Type inference failed for: r12v11 */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v13 */
    /* JADX WARN: Type inference failed for: r2v34, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r2v56 */
    /* JADX WARN: Type inference failed for: r2v57 */
    /* JADX WARN: Type inference failed for: r2v58 */
    /* JADX WARN: Type inference failed for: r2v59 */
    /* JADX WARN: Type inference failed for: r2v60 */
    /* JADX WARN: Type inference failed for: r2v61 */
    /* JADX WARN: Type inference failed for: r2v62 */
    /* JADX WARN: Type inference failed for: r2v63 */
    /* JADX WARN: Type inference failed for: r2v64 */
    /* JADX WARN: Type inference failed for: r2v65 */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0360, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x0361, code lost:
        r3 = r11;
        r17 = 7975;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:178:0x036a, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x036b, code lost:
        r3 = r11;
        r17 = 7975;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x0374, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x0375, code lost:
        r3 = r11;
        r17 = 7975;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:186:0x037e, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:0x037f, code lost:
        r3 = r11;
        r17 = 7975;
        r16 = "-";
        r15 = com.tencent.imsdk.BaseConstants.ERR_SVR_COMM_SENSITIVE_TEXT;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:100:0x01d7  */
    /* JADX WARNING: Removed duplicated region for block: B:155:0x0328 A[SYNTHETIC, Splitter:B:155:0x0328] */
    /* JADX WARNING: Removed duplicated region for block: B:158:0x032d A[Catch:{ IOException -> 0x0557 }] */
    /* JADX WARNING: Removed duplicated region for block: B:186:0x037e A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x0019] */
    /* JADX WARNING: Removed duplicated region for block: B:194:0x03ee  */
    /* JADX WARNING: Removed duplicated region for block: B:196:0x03f3  */
    /* JADX WARNING: Removed duplicated region for block: B:202:0x0465  */
    /* JADX WARNING: Removed duplicated region for block: B:204:0x046a  */
    /* JADX WARNING: Removed duplicated region for block: B:210:0x04dc  */
    /* JADX WARNING: Removed duplicated region for block: B:212:0x04e1  */
    /* JADX WARNING: Removed duplicated region for block: B:218:0x0553  */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x055c  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00b4  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00ee  */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x010c  */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x0117 A[SYNTHETIC, Splitter:B:56:0x0117] */
    /* JADX WARNING: Unknown variable types count: 7 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static cn.com.chinatelecom.account.api.b.f a(android.content.Context r22, java.lang.String r23, java.lang.String r24, cn.com.chinatelecom.account.api.CtSetting r25, android.net.Network r26, boolean r27, int r28, java.lang.String r29, java.lang.String r30, int r31) {
        /*
        // Method dump skipped, instructions count: 1407
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.chinatelecom.account.api.b.d.a(android.content.Context, java.lang.String, java.lang.String, cn.com.chinatelecom.account.api.CtSetting, android.net.Network, boolean, int, java.lang.String, java.lang.String, int):cn.com.chinatelecom.account.api.b.f");
    }

    private static f a(Context context, String str, String str2, CtSetting ctSetting, Network network, boolean z, int i, String str3, String str4, int i2, boolean z2, boolean z3) {
        AppMethodBeat.i(7964, false);
        f b = a(str) ? b(context, str, str2, ctSetting, network, z, i, str3, str4, i2, z2, z3) : a(context, str, str2, ctSetting, network, z, i, str3, str4, i2);
        AppMethodBeat.o(7964);
        return b;
    }

    public static f a(Context context, String str, String str2, CtSetting ctSetting, Network network, boolean z, int i, String str3, String str4, boolean z2, boolean z3) {
        AppMethodBeat.i(7962, false);
        f a2 = a(context, str, str2, ctSetting, network, z, i, str3, str4, 1, z2, z3);
        AppMethodBeat.o(7962);
        return a2;
    }

    public static void a(Context context, String str) {
        AppMethodBeat.i(7981, false);
        if (TextUtils.isEmpty(str)) {
            AppMethodBeat.o(7981);
            return;
        }
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager.getNetworkInfo(5).getState().compareTo(NetworkInfo.State.CONNECTED) == 0) {
                ((Boolean) Class.forName("android.net.ConnectivityManager").getMethod("requestRouteToHost", Integer.TYPE, Integer.TYPE).invoke(connectivityManager, 5, Integer.valueOf(e.a(e.b(str))))).booleanValue();
            }
        } catch (Throwable th) {
            CtAuth.warn(a, "http doPost > requestUrlToRoute error", th);
        }
        AppMethodBeat.o(7981);
    }

    private static void a(HttpsURLConnection httpsURLConnection, String str) {
        AppMethodBeat.i(7979, false);
        httpsURLConnection.setHostnameVerifier(new AnonymousClass1(str));
        httpsURLConnection.setSSLSocketFactory(new AnonymousClass2(str));
        AppMethodBeat.o(7979);
    }

    private static boolean a(String str) {
        AppMethodBeat.i(7958, false);
        boolean startsWith = str.startsWith("https");
        AppMethodBeat.o(7958);
        return startsWith;
    }

    /* JADX DEBUG: Failed to insert an additional move for type inference into block B:157:0x02f7 */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r18v30, types: [java.io.BufferedReader] */
    /* JADX WARN: Type inference failed for: r18v31 */
    /* JADX WARN: Type inference failed for: r3v17 */
    /* JADX WARN: Type inference failed for: r3v18 */
    /* JADX WARN: Type inference failed for: r3v19 */
    /* JADX WARN: Type inference failed for: r3v21 */
    /* JADX WARN: Type inference failed for: r18v74 */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0364, code lost:
        r0 = th;
        r18 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0366, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x0367, code lost:
        r1 = r0;
        r5 = r16;
        r3 = r18;
        r4 = true;
        r18 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:169:0x036f, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x0370, code lost:
        r1 = r0;
        r5 = r16;
        r3 = r18;
        r4 = true;
        r18 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x0378, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0379, code lost:
        r1 = r0;
        r5 = r16;
        r3 = r18;
        r4 = true;
        r18 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:193:0x03ce, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:0x03cf, code lost:
        r6 = false;
        r17 = 7971;
        r16 = "-";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x03df, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:198:0x03e0, code lost:
        r6 = false;
        r17 = 7971;
        r16 = "-";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x03f0, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x03f1, code lost:
        r6 = false;
        r17 = 7971;
        r16 = "-";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x0401, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x0402, code lost:
        r6 = false;
        r17 = 7971;
        r16 = "-";
        r2 = com.tencent.imsdk.BaseConstants.ERR_SVR_COMM_SENSITIVE_TEXT;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:142:0x02b5, B:157:0x02f7] */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:124:0x0239  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x035a A[SYNTHETIC, Splitter:B:162:0x035a] */
    /* JADX WARNING: Removed duplicated region for block: B:165:0x035f A[Catch:{ IOException -> 0x0602 }] */
    /* JADX WARNING: Removed duplicated region for block: B:167:0x0366 A[ExcHandler: IOException (r0v28 'e' java.io.IOException A[CUSTOM_DECLARE]), PHI: r15 r16 r17 r18 
      PHI: (r15v30 cn.com.chinatelecom.account.api.b.f) = (r15v31 cn.com.chinatelecom.account.api.b.f), (r15v34 cn.com.chinatelecom.account.api.b.f), (r15v34 cn.com.chinatelecom.account.api.b.f) binds: [B:157:0x02f7, B:142:0x02b5, B:143:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r16v29 java.lang.String) = (r16v30 java.lang.String), (r16v36 java.lang.String), (r16v36 java.lang.String) binds: [B:157:0x02f7, B:142:0x02b5, B:143:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r17v44 int) = (r17v45 int), (r17v51 int), (r17v51 int) binds: [B:157:0x02f7, B:142:0x02b5, B:143:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r18v35 java.io.InputStream) = (r18v36 java.io.InputStream), (r18v43 java.io.InputStream), (r18v43 java.io.InputStream) binds: [B:157:0x02f7, B:142:0x02b5, B:143:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:142:0x02b5] */
    /* JADX WARNING: Removed duplicated region for block: B:169:0x036f A[ExcHandler: UnknownHostException (r0v27 'e' java.net.UnknownHostException A[CUSTOM_DECLARE]), PHI: r15 r16 r17 r18 
      PHI: (r15v29 cn.com.chinatelecom.account.api.b.f) = (r15v31 cn.com.chinatelecom.account.api.b.f), (r15v34 cn.com.chinatelecom.account.api.b.f), (r15v34 cn.com.chinatelecom.account.api.b.f) binds: [B:157:0x02f7, B:142:0x02b5, B:143:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r16v28 java.lang.String) = (r16v30 java.lang.String), (r16v36 java.lang.String), (r16v36 java.lang.String) binds: [B:157:0x02f7, B:142:0x02b5, B:143:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r17v43 int) = (r17v45 int), (r17v51 int), (r17v51 int) binds: [B:157:0x02f7, B:142:0x02b5, B:143:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r18v34 java.io.InputStream) = (r18v36 java.io.InputStream), (r18v43 java.io.InputStream), (r18v43 java.io.InputStream) binds: [B:157:0x02f7, B:142:0x02b5, B:143:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:142:0x02b5] */
    /* JADX WARNING: Removed duplicated region for block: B:171:0x0378 A[ExcHandler: SocketTimeoutException (r0v26 'e' java.net.SocketTimeoutException A[CUSTOM_DECLARE]), PHI: r15 r16 r17 r18 
      PHI: (r15v28 cn.com.chinatelecom.account.api.b.f) = (r15v31 cn.com.chinatelecom.account.api.b.f), (r15v34 cn.com.chinatelecom.account.api.b.f), (r15v34 cn.com.chinatelecom.account.api.b.f) binds: [B:157:0x02f7, B:142:0x02b5, B:143:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r16v27 java.lang.String) = (r16v30 java.lang.String), (r16v36 java.lang.String), (r16v36 java.lang.String) binds: [B:157:0x02f7, B:142:0x02b5, B:143:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r17v42 int) = (r17v45 int), (r17v51 int), (r17v51 int) binds: [B:157:0x02f7, B:142:0x02b5, B:143:?] A[DONT_GENERATE, DONT_INLINE]
      PHI: (r18v33 java.io.InputStream) = (r18v36 java.io.InputStream), (r18v43 java.io.InputStream), (r18v43 java.io.InputStream) binds: [B:157:0x02f7, B:142:0x02b5, B:143:?] A[DONT_GENERATE, DONT_INLINE], Splitter:B:142:0x02b5] */
    /* JADX WARNING: Removed duplicated region for block: B:205:0x0401 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:1:0x001c] */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x047b  */
    /* JADX WARNING: Removed duplicated region for block: B:218:0x0480  */
    /* JADX WARNING: Removed duplicated region for block: B:227:0x04fc  */
    /* JADX WARNING: Removed duplicated region for block: B:229:0x0501  */
    /* JADX WARNING: Removed duplicated region for block: B:238:0x057d  */
    /* JADX WARNING: Removed duplicated region for block: B:240:0x0582  */
    /* JADX WARNING: Removed duplicated region for block: B:249:0x05fe  */
    /* JADX WARNING: Removed duplicated region for block: B:253:0x0607  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a9 A[Catch:{ SocketTimeoutException -> 0x00ce, UnknownHostException -> 0x00c4, IOException -> 0x00ba, all -> 0x00ad }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x010e  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0115  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0152  */
    /* JADX WARNING: Removed duplicated region for block: B:77:0x0170  */
    /* JADX WARNING: Removed duplicated region for block: B:80:0x017b A[SYNTHETIC, Splitter:B:80:0x017b] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static cn.com.chinatelecom.account.api.b.f b(android.content.Context r22, java.lang.String r23, java.lang.String r24, cn.com.chinatelecom.account.api.CtSetting r25, android.net.Network r26, boolean r27, int r28, java.lang.String r29, java.lang.String r30, int r31, boolean r32, boolean r33) {
        /*
        // Method dump skipped, instructions count: 1578
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.chinatelecom.account.api.b.d.b(android.content.Context, java.lang.String, java.lang.String, cn.com.chinatelecom.account.api.CtSetting, android.net.Network, boolean, int, java.lang.String, java.lang.String, int, boolean, boolean):cn.com.chinatelecom.account.api.b.f");
    }
}
