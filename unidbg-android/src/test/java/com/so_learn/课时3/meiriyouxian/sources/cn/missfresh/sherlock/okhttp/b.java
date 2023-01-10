package cn.missfresh.sherlock.okhttp;

import android.os.SystemClock;
import cn.missfresh.sherlock.Sherlock;
import cn.missfresh.sherlock.to.NetworkTO;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.concurrent.atomic.AtomicLong;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.Protocol;

/* compiled from: SherlockGlobalEventListener */
public class b extends EventListener {
    private long a = 0;

    /* compiled from: SherlockGlobalEventListener */
    static class a implements EventListener.Factory {
        AtomicLong a = new AtomicLong(1);

        a() {
        }

        public EventListener create(Call call) {
            return new b(this.a.getAndIncrement());
        }
    }

    static {
        new a();
    }

    public b(long j) {
    }

    private void a(boolean z, Call call, long j, IOException iOException) {
        try {
            NetworkTO networkTO = new NetworkTO();
            networkTO.setUrl(call.request().url().toString());
            networkTO.setRequestMethod(call.request().method());
            networkTO.setDomain(call.request().url().host());
            networkTO.setRequestParameter(call.request().url().query());
            networkTO.setRequestHeaders(call.request().headers().toString());
            networkTO.setResponseCode(z ? "70001" : "70002");
            networkTO.setResponseTime(j);
            networkTO.setResponseMessage(iOException.toString());
            Sherlock.reportNetworkInfo(networkTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void callFailed(Call call, IOException iOException) {
        b.super.callFailed(call, iOException);
        a(false, call, System.currentTimeMillis() - this.a, iOException);
    }

    public void callStart(Call call) {
        this.a = SystemClock.elapsedRealtime();
    }

    public void connectFailed(Call call, InetSocketAddress inetSocketAddress, Proxy proxy, Protocol protocol, IOException iOException) {
        b.super.connectFailed(call, inetSocketAddress, proxy, protocol, iOException);
        a(true, call, System.currentTimeMillis() - this.a, iOException);
    }
}
