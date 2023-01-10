package cn.missfresh.sherlock.okhttp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import okhttp3.Call;
import okhttp3.Dns;
import okhttp3.EventListener;
import okhttp3.Interceptor;

public class OkHttpHooker {
    public static Dns globalDns = Dns.SYSTEM;
    public static EventListener.Factory globalEventFactory = new a();
    public static List<Interceptor> globalInterceptors = new ArrayList();
    public static List<Interceptor> globalNetworkInterceptors = new ArrayList();

    static class a implements EventListener.Factory {
        AtomicLong a = new AtomicLong(1);

        a() {
        }

        public EventListener create(Call call) {
            return new b(this.a.getAndIncrement());
        }
    }

    static {
        globalNetworkInterceptors.add(new c());
    }

    public static void installDns(Dns dns) {
        globalDns = dns;
    }

    public static void installEventListenerFactory(EventListener.Factory factory) {
        globalEventFactory = factory;
    }

    public static void installInterceptor(Interceptor interceptor) {
        if (interceptor != null) {
            globalInterceptors.add(interceptor);
        }
    }

    public static void installNetworkInterceptors(Interceptor interceptor) {
        if (interceptor != null) {
            globalNetworkInterceptors.add(interceptor);
        }
    }
}
