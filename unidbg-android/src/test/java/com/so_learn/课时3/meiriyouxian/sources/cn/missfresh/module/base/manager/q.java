package cn.missfresh.module.base.manager;

import android.content.Context;
import android.media.TtmlUtils;
import cn.missfresh.module.base.bean.TencentSearchData;
import cn.missfresh.module.base.bean.TencentSuggestionAddress;
import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.network.m;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.alibaba.fastjson.JSON;
import com.umeng.message.proguard.l;
import com.xiaomi.mipush.sdk.Constants;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import okhttp3.Request;
import org.json.JSONObject;

/* compiled from: TencentApiManager */
public class q {
    public static int a = 15;
    private static volatile q b = null;
    private static String c = null;
    private static int d = 1000;
    private static String e = "_distance";

    /* compiled from: TencentApiManager */
    public interface a {
        void a();

        void a(String str);

        void a(String str, List<TencentSearchData> list, int i);
    }

    /* compiled from: TencentApiManager */
    public interface b {
        void a(int i);

        void a(List<TencentSearchData> list);
    }

    /* compiled from: TencentApiManager */
    public interface c {
        void a();

        void a(String str);

        void a(String str, List<TencentSuggestionAddress> list);
    }

    private q() {
        AppMethodBeat.i(14637, false);
        c = j.a("TencentMapSDK");
        AppMethodBeat.o(14637);
    }

    public static void a(Context context) {
        int i = 14638;
        AppMethodBeat.i(14638, false);
        if (b == null) {
            synchronized (q.class) {
                try {
                    if (b == null) {
                        b = new q();
                    }
                } finally {
                    AppMethodBeat.o(i);
                }
            }
        }
    }

    public static void a(String str, String str2, int i, Object obj, int i2, b bVar) {
        AppMethodBeat.i(14639, false);
        HashMap hashMap = new HashMap();
        hashMap.put("boundary", "nearby(" + str + Constants.ACCEPT_TIME_SEPARATOR_SP + str2 + Constants.ACCEPT_TIME_SEPARATOR_SP + i + l.t);
        hashMap.put("key", c);
        hashMap.put("page_index", "1");
        hashMap.put("page_size", String.valueOf(i2));
        cn.missfresh.module.base.network.c.a(obj, i.D, hashMap, new AnonymousClass1(bVar));
        AppMethodBeat.o(14639);
    }

    /* compiled from: TencentApiManager */
    /* renamed from: cn.missfresh.module.base.manager.q$1  reason: invalid class name */
    static class AnonymousClass1 extends m {
        final /* synthetic */ b a;

        AnonymousClass1(b bVar) {
            this.a = bVar;
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            AppMethodBeat.i(14619, false);
            super.a(i);
            this.a.a(0);
            AppMethodBeat.o(14619);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            AppMethodBeat.i(14620, false);
            super.a(request, exc);
            this.a.a(0);
            d.d("DefalutAddressManager", "e=" + exc);
            AppMethodBeat.o(14620);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            AppMethodBeat.i(14621, false);
            super.a(str);
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.getInt("status") != 0) {
                    this.a.a(1);
                    AppMethodBeat.o(14621);
                    return;
                }
                this.a.a(JSON.parseArray(jSONObject.getJSONArray("data").toString(), TencentSearchData.class));
                AppMethodBeat.o(14621);
            } catch (Exception e) {
                e.printStackTrace();
                d.d("DefalutAddressManager", "suc e=" + e);
                this.a.a(1);
            }
        }
    }

    public static void a(Object obj, String str, String str2, c cVar, String str3, String str4) {
        AppMethodBeat.i(14640, false);
        try {
            HashMap hashMap = new HashMap();
            hashMap.put(TtmlUtils.TAG_REGION, URLEncoder.encode(str, "UTF-8"));
            hashMap.put("keyword", URLEncoder.encode(str2, "UTF-8"));
            hashMap.put("region_fix", "1");
            hashMap.put("policy", "1");
            hashMap.put("key", c);
            hashMap.put("latitude", str3);
            hashMap.put("longitude", str4);
            cVar.a();
            cn.missfresh.module.base.network.c.a(obj, i.E, hashMap, new AnonymousClass2(cVar, str2));
        } catch (Exception e2) {
            e2.printStackTrace();
            cVar.a(str2);
        }
        AppMethodBeat.o(14640);
    }

    /* compiled from: TencentApiManager */
    /* renamed from: cn.missfresh.module.base.manager.q$2  reason: invalid class name */
    static class AnonymousClass2 extends m {
        final /* synthetic */ c a;
        final /* synthetic */ String b;

        AnonymousClass2(c cVar, String str) {
            this.a = cVar;
            this.b = str;
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            AppMethodBeat.i(14624, false);
            super.a(i);
            this.a.a(this.b);
            AppMethodBeat.o(14624);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            AppMethodBeat.i(14626, false);
            super.a(request, exc);
            this.a.a(this.b);
            AppMethodBeat.o(14626);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            AppMethodBeat.i(14628, false);
            super.a(str);
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.getInt("status") != 0) {
                    this.a.a(this.b);
                    AppMethodBeat.o(14628);
                    return;
                }
                this.a.a(this.b, JSON.parseArray(jSONObject.getJSONArray("data").toString(), TencentSuggestionAddress.class));
                AppMethodBeat.o(14628);
            } catch (Exception e) {
                e.printStackTrace();
                this.a.a(this.b);
            }
        }
    }

    public static void a(Object obj, String str, String str2, String str3, int i, a aVar, String str4, String str5) {
        AppMethodBeat.i(14642, false);
        try {
            String str6 = "nearby(" + str + Constants.ACCEPT_TIME_SEPARATOR_SP + str2 + Constants.ACCEPT_TIME_SEPARATOR_SP + d + l.t;
            HashMap hashMap = new HashMap();
            hashMap.put("boundary", URLEncoder.encode(str6, "UTF-8"));
            hashMap.put("page_index", String.valueOf(i));
            hashMap.put("page_size", String.valueOf(a));
            hashMap.put("key", c);
            hashMap.put("orderby", e);
            aVar.a();
            cn.missfresh.module.base.network.c.a(obj, i.D, hashMap, new AnonymousClass3(aVar, str3));
        } catch (Exception e2) {
            e2.printStackTrace();
            aVar.a(str3);
        }
        AppMethodBeat.o(14642);
    }

    /* compiled from: TencentApiManager */
    /* renamed from: cn.missfresh.module.base.manager.q$3  reason: invalid class name */
    static class AnonymousClass3 extends m {
        final /* synthetic */ a a;
        final /* synthetic */ String b;

        AnonymousClass3(a aVar, String str) {
            this.a = aVar;
            this.b = str;
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            AppMethodBeat.i(14631, false);
            super.a(i);
            this.a.a(this.b);
            AppMethodBeat.o(14631);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            AppMethodBeat.i(14632, false);
            super.a(request, exc);
            this.a.a(this.b);
            AppMethodBeat.o(14632);
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(String str) {
            int i = 0;
            AppMethodBeat.i(14633, false);
            super.a(str);
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (jSONObject.getInt("status") != 0) {
                    this.a.a(this.b);
                    AppMethodBeat.o(14633);
                    return;
                }
                if (jSONObject.has("count")) {
                    i = jSONObject.getInt("count");
                }
                this.a.a(this.b, JSON.parseArray(jSONObject.getJSONArray("data").toString(), TencentSearchData.class), i);
                AppMethodBeat.o(14633);
            } catch (Exception e) {
                e.printStackTrace();
                this.a.a(this.b);
            }
        }
    }
}
