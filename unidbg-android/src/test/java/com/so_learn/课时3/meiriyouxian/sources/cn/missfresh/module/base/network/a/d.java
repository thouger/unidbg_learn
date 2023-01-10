package cn.missfresh.module.base.network.a;

import android.text.TextUtils;
import cn.missfresh.module.base.manager.c;
import cn.missfresh.module.base.utils.f;
import cn.missfresh.module.base.utils.r;
import cn.missfresh.module.security.e;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.alibaba.fastjson.JSONObject;
import com.bangcle.andjni.JniLib;
import com.taobao.accs.common.Constants;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

/* compiled from: InterceptorHelper */
public class d {
    public static final ArrayList<String> a = new ArrayList<>();
    public static List<String> b = new ArrayList();
    public static List<String> c = new ArrayList();
    String d;
    volatile String e;

    private d() {
        JniLib.cV(this, 42);
    }

    public static d a() {
        return (d) JniLib.cL(43);
    }

    public String a(RequestBody requestBody) {
        return (String) JniLib.cL(this, requestBody, 28);
    }

    public String a(RequestBody requestBody, Map<String, String> map) {
        return (String) JniLib.cL(this, requestBody, map, 29);
    }

    public boolean a(String str) {
        return JniLib.cZ(this, str, 30);
    }

    public boolean a(HttpUrl httpUrl) {
        return JniLib.cZ(this, httpUrl, 31);
    }

    public boolean a(Request request) {
        return JniLib.cZ(this, request, 32);
    }

    public MediaType b() {
        return (MediaType) JniLib.cL(this, 33);
    }

    /* access modifiers changed from: package-private */
    public boolean b(HttpUrl httpUrl) {
        return JniLib.cZ(this, httpUrl, 34);
    }

    public boolean b(Request request) {
        return JniLib.cZ(this, request, 35);
    }

    public boolean b(RequestBody requestBody) {
        return JniLib.cZ(this, requestBody, 36);
    }

    public String c(RequestBody requestBody) {
        return (String) JniLib.cL(this, requestBody, 37);
    }

    public HttpUrl c(Request request) {
        return (HttpUrl) JniLib.cL(this, request, 38);
    }

    public JSONObject d(RequestBody requestBody) {
        return (JSONObject) JniLib.cL(this, requestBody, 39);
    }

    public synchronized Map<String, String> e() {
        return (Map) JniLib.cL(this, 40);
    }

    public boolean f(HttpUrl httpUrl) {
        return JniLib.cZ(this, httpUrl, 41);
    }

    static {
        AppMethodBeat.i(15906, false);
        AppMethodBeat.o(15906);
    }

    public RequestBody a(Map<String, String> map) {
        AppMethodBeat.i(15855, false);
        FormBody.Builder builder = new FormBody.Builder();
        if (b.a(map)) {
            FormBody build = builder.build();
            AppMethodBeat.o(15855);
            return build;
        }
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (!b.a(entry.getKey()) && !b.a(entry.getValue())) {
                builder.add(entry.getKey(), entry.getValue());
            }
        }
        FormBody build2 = builder.build();
        AppMethodBeat.o(15855);
        return build2;
    }

    /* access modifiers changed from: package-private */
    public void c() {
        int i = 15864;
        AppMethodBeat.i(15864, false);
        if (b.a(this.e)) {
            synchronized (d.class) {
                try {
                    if (b.a(this.e)) {
                        try {
                            this.d = e.a(16);
                            this.e = e.a(this.d);
                        } catch (Exception e) {
                            this.d = "";
                            this.e = "";
                            cn.missfresh.utils.a.d.b("tag", "getExtraKey, e=" + e);
                        }
                    }
                } finally {
                    AppMethodBeat.o(i);
                }
            }
        }
    }

    /* access modifiers changed from: package-private */
    public String d() {
        AppMethodBeat.i(15868, false);
        if (b.a(this.e)) {
            AppMethodBeat.o(15868);
            return "";
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("currentLng", c.g());
        jSONObject.put("currentLat", c.f());
        jSONObject.put("userLat", c.q().u());
        jSONObject.put("userLng", c.q().v());
        jSONObject.put(Constants.KEY_IMEI, r.h());
        jSONObject.put(Constant.KEY_MAC, r.k());
        jSONObject.put("sourceDeviceId", r.j());
        jSONObject.put("androidId", r.i());
        jSONObject.put("deviceId", r.j());
        jSONObject.put("vaid", r.a);
        jSONObject.put("oaid", r.c);
        jSONObject.put("aaid", r.b);
        try {
            String a2 = e.a(jSONObject.toJSONString(), this.d);
            AppMethodBeat.o(15868);
            return a2;
        } catch (Exception e) {
            cn.missfresh.utils.a.d.b("tag", "getExtraData, e=" + e);
            AppMethodBeat.o(15868);
            return "";
        }
    }

    public HttpUrl a(Request request, Map<String, String> map) {
        AppMethodBeat.i(15878, false);
        if (request == null) {
            NullPointerException nullPointerException = new NullPointerException("request can not be null!");
            AppMethodBeat.o(15878);
            throw nullPointerException;
        } else if (b.a(map)) {
            HttpUrl url = request.url();
            AppMethodBeat.o(15878);
            return url;
        } else {
            HttpUrl.Builder newBuilder = request.url().newBuilder();
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (!b.a(entry.getKey()) && !b.a(entry.getValue())) {
                    newBuilder.addQueryParameter(entry.getKey(), entry.getValue());
                }
            }
            HttpUrl build = newBuilder.build();
            AppMethodBeat.o(15878);
            return build;
        }
    }

    public boolean c(HttpUrl httpUrl) {
        AppMethodBeat.i(15891, false);
        if (httpUrl == null) {
            AppMethodBeat.o(15891);
            return false;
        }
        boolean z = true;
        if (!f.j()) {
            if (b(httpUrl)) {
                AppMethodBeat.o(15891);
                return true;
            }
            String httpUrl2 = httpUrl.toString();
            if (!TextUtils.isEmpty(httpUrl2)) {
                Iterator<String> it2 = a.iterator();
                while (true) {
                    if (it2.hasNext()) {
                        if (httpUrl2.toLowerCase().contains(it2.next().toLowerCase())) {
                            break;
                        }
                    } else {
                        break;
                    }
                }
            }
            z = false;
        }
        cn.missfresh.utils.a.d.d("gechen", " isWhiteList " + httpUrl.toString());
        AppMethodBeat.o(15891);
        return z;
    }

    public boolean d(HttpUrl httpUrl) {
        boolean z = false;
        AppMethodBeat.i(15894, false);
        if (httpUrl == null) {
            AppMethodBeat.o(15894);
            return false;
        }
        String httpUrl2 = httpUrl.toString();
        if (!TextUtils.isEmpty(httpUrl2)) {
            Iterator<String> it2 = b.iterator();
            while (true) {
                if (it2.hasNext()) {
                    if (httpUrl2.toLowerCase().contains(it2.next().toLowerCase())) {
                        z = true;
                        break;
                    }
                } else {
                    break;
                }
            }
        }
        AppMethodBeat.o(15894);
        return z;
    }

    public String e(HttpUrl httpUrl) {
        AppMethodBeat.i(15897, false);
        if (httpUrl == null) {
            String d = r.d();
            AppMethodBeat.o(15897);
            return d;
        }
        String httpUrl2 = httpUrl.toString();
        if (!TextUtils.isEmpty(httpUrl2)) {
            for (String str : c) {
                if (httpUrl2.toLowerCase().contains(str.toLowerCase())) {
                    String p = r.p();
                    AppMethodBeat.o(15897);
                    return p;
                }
            }
        }
        String d2 = r.d();
        AppMethodBeat.o(15897);
        return d2;
    }

    /* compiled from: InterceptorHelper */
    private static final class a {
        private static final d a = new d();

        static {
            AppMethodBeat.i(15822, false);
            AppMethodBeat.o(15822);
        }
    }
}
