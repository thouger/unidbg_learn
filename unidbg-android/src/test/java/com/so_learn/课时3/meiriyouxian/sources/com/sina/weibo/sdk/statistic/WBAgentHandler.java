package com.sina.weibo.sdk.statistic;

import com.sina.weibo.sdk.a.d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* access modifiers changed from: package-private */
public class WBAgentHandler {
    private static WBAgentHandler a = null;
    private static List<e> b = null;
    private static Map<String, e> c = null;
    private static int d = 5;

    public static synchronized WBAgentHandler a() {
        WBAgentHandler wBAgentHandler;
        synchronized (WBAgentHandler.class) {
            if (a == null) {
                a = new WBAgentHandler();
            }
            wBAgentHandler = a;
        }
        return wBAgentHandler;
    }

    private WBAgentHandler() {
        b = new ArrayList();
        c = new HashMap();
        d.b("WBAgent", "init handler");
    }

    public void onEvent(String str, String str2, Map<String, String> map) {
        a aVar = new a(str, str2, map);
        aVar.a(LogType.EVENT);
        synchronized (b) {
            b.add(aVar);
        }
        if (map == null) {
            d.a("WBAgent", "event--- page:" + str + " ,event name:" + str2);
        } else {
            d.a("WBAgent", "event--- page:" + str + " ,event name:" + str2 + " ,extend:" + map.toString());
        }
        if (b.size() >= d) {
            a(b);
            b.clear();
        }
    }

    private synchronized void a(List<e> list) {
        f.a(new AnonymousClass1(b.a(list)));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sina.weibo.sdk.statistic.WBAgentHandler$1  reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        private final /* synthetic */ String b;

        AnonymousClass1(String str) {
            this.b = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            c.a(c.a("app_logs"), this.b, true);
        }
    }
}
