package cn.missfresh.module.base.network;

import android.net.wifi.WifiEnterpriseConfig;
import cn.missfresh.buttomline.logtrace.bean.LogBean;
import cn.missfresh.module.base.utils.f;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.bangcle.andjni.JniLib;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import java.util.Locale;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.EventListener;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;

/* compiled from: HttpEventListener */
public class b extends EventListener {
    public static final EventListener.Factory a = new AnonymousClass1();
    private final long b;
    private StringBuilder c;
    private LogBean d;

    /* compiled from: HttpEventListener */
    /* renamed from: cn.missfresh.module.base.network.b$1  reason: invalid class name */
    static class AnonymousClass1 implements EventListener.Factory {
        AnonymousClass1() {
            JniLib.cV(this, 72);
        }

        public EventListener create(Call call) {
            return (EventListener) JniLib.cL(this, call, 71);
        }
    }

    private void a(boolean z, Call call, long j, String str) {
        JniLib.cV(this, Boolean.valueOf(z), call, Long.valueOf(j), str, 93);
    }

    public void callEnd(Call call) {
        JniLib.cV(this, call, 73);
    }

    public void callFailed(Call call, IOException iOException) {
        JniLib.cV(this, call, iOException, 74);
    }

    public void callStart(Call call) {
        JniLib.cV(this, call, 75);
    }

    public void connectEnd(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol) {
        JniLib.cV(this, call, inetSocketAddress, proxy, protocol, 76);
    }

    public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol, IOException iOException) {
        JniLib.cV(this, call, inetSocketAddress, proxy, protocol, iOException, 77);
    }

    public void connectStart(Call call, InetSocketAddress inetSocketAddress, Proxy proxy) {
        JniLib.cV(this, call, inetSocketAddress, proxy, 78);
    }

    public void connectionAcquired(Call call, Connection connection) {
        JniLib.cV(this, call, connection, 79);
    }

    public void connectionReleased(Call call, Connection connection) {
        JniLib.cV(this, call, connection, 80);
    }

    public void dnsEnd(Call call, String str, List<InetAddress> list) {
        JniLib.cV(this, call, str, list, 81);
    }

    public void dnsStart(Call call, String str) {
        JniLib.cV(this, call, str, 82);
    }

    public void requestBodyEnd(Call call, long j) {
        JniLib.cV(this, call, Long.valueOf(j), 83);
    }

    public void requestBodyStart(Call call) {
        JniLib.cV(this, call, 84);
    }

    public void requestHeadersEnd(Call call, Request request) {
        JniLib.cV(this, call, request, 85);
    }

    public void requestHeadersStart(Call call) {
        JniLib.cV(this, call, 86);
    }

    public void responseBodyEnd(Call call, long j) {
        JniLib.cV(this, call, Long.valueOf(j), 87);
    }

    public void responseBodyStart(Call call) {
        JniLib.cV(this, call, 88);
    }

    public void responseHeadersEnd(Call call, Response response) {
        JniLib.cV(this, call, response, 89);
    }

    public void responseHeadersStart(Call call) {
        JniLib.cV(this, call, 90);
    }

    public void secureConnectEnd(Call call, Handshake handshake) {
        JniLib.cV(this, call, handshake, 91);
    }

    public void secureConnectStart(Call call) {
        JniLib.cV(this, call, 92);
    }

    static {
        AppMethodBeat.i(15210, false);
        AppMethodBeat.o(15210);
    }

    public b(long j, HttpUrl httpUrl, long j2) {
        AppMethodBeat.i(15177, false);
        this.d = null;
        this.d = new LogBean();
        this.d.setType("NetReq");
        this.b = j2;
        StringBuilder sb = new StringBuilder(httpUrl.toString());
        sb.append(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
        sb.append(j);
        sb.append(" : ");
        this.c = sb;
        AppMethodBeat.o(15177);
    }

    private void a(String str) {
        AppMethodBeat.i(15179, false);
        long currentTimeMillis = System.currentTimeMillis() - this.b;
        StringBuilder sb = this.c;
        sb.append(String.format(Locale.CHINA, "%s(%d)", str, Long.valueOf(currentTimeMillis)));
        sb.append(";");
        if (str.equalsIgnoreCase("callEnd") || str.equalsIgnoreCase("callFailed")) {
            d.d("NetReq", this.c.toString());
            if (f.e()) {
                d.a(this.d);
            }
        }
        AppMethodBeat.o(15179);
    }
}
