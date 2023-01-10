package com.sobot.chat.core.http.g;

import android.content.Context;
import com.sobot.chat.api.apiUtils.SobotApp;
import com.sobot.chat.core.http.d.e;
import com.sobot.chat.core.http.h.b;
import com.sobot.chat.core.http.model.SobotProgress;
import com.sobot.chat.utils.d;
import java.io.File;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* compiled from: SobotDownload */
public class a {
    private static a a;
    private String b;
    private d c = new d();
    private ConcurrentHashMap<String, c> d = new ConcurrentHashMap<>();

    public static a a() {
        if (a == null) {
            synchronized (a.class) {
                if (a == null) {
                    a = new a(SobotApp.getApplicationContext());
                }
            }
        }
        return a;
    }

    private a(Context context) {
        this.b = d.b(context) + Context.DOWNLOAD_SERVICE + File.separator;
        b.a(this.b);
        com.sobot.chat.core.http.f.a.a().c();
    }

    public static c a(String str, e eVar) {
        Map<String, c> d = a().d();
        c cVar = d.get(str);
        if (cVar != null) {
            return cVar;
        }
        c cVar2 = new c(str, eVar);
        d.put(str, cVar2);
        return cVar2;
    }

    public static c a(SobotProgress sobotProgress) {
        Map<String, c> d = a().d();
        c cVar = d.get(sobotProgress.tag);
        if (cVar != null) {
            return cVar;
        }
        c cVar2 = new c(sobotProgress);
        d.put(sobotProgress.tag, cVar2);
        return cVar2;
    }

    public String b() {
        return this.b;
    }

    public a a(String str) {
        this.b = str;
        return this;
    }

    public d c() {
        return this.c;
    }

    public Map<String, c> d() {
        return this.d;
    }

    public c b(String str) {
        return this.d.get(str);
    }

    public c c(String str) {
        return this.d.remove(str);
    }

    public void d(String str) {
        a().d();
        for (c cVar : this.d.values()) {
            cVar.b(str);
        }
    }
}
