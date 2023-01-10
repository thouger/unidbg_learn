package cn.missfresh.module.base.helper;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BrowserContract;
import android.provider.Downloads;
import android.text.TextUtils;
import android.text.format.DateUtils;
import cn.missfresh.buttomline.logtrace.bean.LogBean;
import cn.missfresh.linksdk.api.MFLinkCallBack;
import cn.missfresh.linksdk.bean.MFLinkConfig;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.common.config.BottomTabEnum;
import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.common.providers.IProductDetailService;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.manager.g;
import cn.missfresh.module.base.network.c;
import cn.missfresh.module.base.network.m;
import cn.missfresh.module.base.utils.ag;
import cn.missfresh.module.base.utils.j;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import com.alibaba.fastjson.JSON;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* compiled from: MLinkHelper */
public class e {
    private static long a;
    private a b;
    private final String c = "commonKey";
    private final String d = "jsonParam";

    /* compiled from: MLinkHelper */
    public interface a {
        void a();
    }

    static /* synthetic */ void a(e eVar) {
        AppMethodBeat.i(13024, false);
        eVar.b();
        AppMethodBeat.o(13024);
    }

    static /* synthetic */ void a(e eVar, Map map, String str) {
        AppMethodBeat.i(13025, false);
        eVar.c(map, str);
        AppMethodBeat.o(13025);
    }

    static /* synthetic */ void b(e eVar, Map map, String str) {
        AppMethodBeat.i(13026, false);
        eVar.a(map, str);
        AppMethodBeat.o(13026);
    }

    public e(a aVar) {
        this.b = aVar;
    }

    public static void a(Context context) {
        AppMethodBeat.i(12998, false);
        ArrayList arrayList = new ArrayList();
        arrayList.add("mrfresh");
        arrayList.add("http");
        arrayList.add("https");
        arrayList.add("mryx");
        ArrayList arrayList2 = new ArrayList();
        arrayList2.add("ul.missfresh.cn");
        arrayList2.add("z.missfresh.cn");
        arrayList2.add("s.missfresh.net");
        arrayList2.add("mryx.top");
        arrayList2.add("imryx.cn");
        cn.missfresh.linksdk.a.a().a(new MFLinkConfig.Builder().openDebugMode().setAlias("missfresh").setAppID("missfresh").setHosts(arrayList2).setSchemes(arrayList).setContext(context).build());
        AppMethodBeat.o(12998);
    }

    /* compiled from: MLinkHelper */
    /* renamed from: cn.missfresh.module.base.helper.e$1  reason: invalid class name */
    class AnonymousClass1 implements MFLinkCallBack {
        final /* synthetic */ Context a;

        AnonymousClass1(Context context) {
            this.a = context;
        }

        public void execute(Map<String, String> map, Uri uri) {
            AppMethodBeat.i(12989, false);
            d.c("paramMap=>", map == null ? "map is called" : map.toString());
            if (map == null) {
                e.a(e.this);
                AppMethodBeat.o(12989);
                return;
            }
            String str = map.get("path");
            if (!TextUtils.isEmpty(str) && "commonKey".equalsIgnoreCase(str)) {
                map.put("jsonParam", JSON.toJSONString(map));
            }
            e.a(e.this, map, str);
            AppMethodBeat.o(12989);
        }

        public void report(String str, Map<String, String> map) {
            AppMethodBeat.i(12990, false);
            if ("statistic".equals(str)) {
                a(map);
            } else {
                e.b(e.this, map, "");
            }
            AppMethodBeat.o(12990);
        }

        private void a(Map<String, String> map) {
            AppMethodBeat.i(12991, false);
            if (map == null || map.isEmpty()) {
                AppMethodBeat.o(12991);
                return;
            }
            String str = map.get("selfOpen");
            String str2 = map.get("bucket_id");
            if (TextUtils.isEmpty(str) || !"isTrue".equalsIgnoreCase(str)) {
                StatisticsManager.f("open_app", "open_type", "3", "push_id", str2, "push_access", Integer.valueOf(ag.a(this.a)));
            } else {
                StatisticsManager.f("open_app", "open_type", "4", "push_id", str2, "push_access", Integer.valueOf(ag.a(this.a)));
            }
            AppMethodBeat.o(12991);
        }
    }

    public void b(Context context) {
        AppMethodBeat.i(12999, false);
        cn.missfresh.linksdk.a.a().a(getClass().getSimpleName(), new AnonymousClass1(context));
        AppMethodBeat.o(12999);
    }

    private void a(Map<String, String> map, String str) {
        AppMethodBeat.i(13001, false);
        if (map == null || map.isEmpty()) {
            AppMethodBeat.o(13001);
            return;
        }
        map.put(Downloads.Impl.COLUMN_ERROR_MSG, str);
        LogBean logBean = new LogBean();
        logBean.setType("android-link");
        logBean.setInfo_str(JSON.toJSONString(map));
        d.a(logBean);
        AppMethodBeat.o(13001);
    }

    public void a() {
        AppMethodBeat.i(13003, false);
        cn.missfresh.linksdk.a.a().a(getClass().getSimpleName());
        AppMethodBeat.o(13003);
    }

    public void a(Uri uri, Context context) {
        AppMethodBeat.i(13005, false);
        Activity b = cn.missfresh.module.base.manager.a.a().b();
        if (uri == null || b == null) {
            b();
        } else {
            b(uri, context);
            String scheme = uri.getScheme();
            if ("missfresh".equalsIgnoreCase(scheme)) {
                j.a(b, uri);
                String queryParameter = uri.getQueryParameter("backurl");
                if (!TextUtils.isEmpty(queryParameter) && "toutiao".equals(uri.getQueryParameter("from_source"))) {
                    h.a().a("\u56de\u5934\u6761", queryParameter);
                    h.a().a(R.drawable.shape_corners_20_ff4891_half_rt_rb);
                    ((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getHandler().postDelayed(new AnonymousClass2(), DateUtils.MINUTE_IN_MILLIS);
                }
            } else if ("qn4134b50915e0".equalsIgnoreCase(scheme)) {
                h.a().a("\u817e\u8baf\u65b0\u95fb", "qqnews://article_9528?act=restore&from=meiriyouxian");
                j.a(b, uri);
            } else {
                b();
            }
        }
        AppMethodBeat.o(13005);
    }

    /* compiled from: MLinkHelper */
    /* renamed from: cn.missfresh.module.base.helper.e$2  reason: invalid class name */
    class AnonymousClass2 implements Runnable {
        AnonymousClass2() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(12993, false);
            if (((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).isAppInForeGround()) {
                c.a("MLinkHelper", i.co, new HashMap(), new m());
            }
            AppMethodBeat.o(12993);
        }
    }

    public void b(Uri uri, Context context) {
        AppMethodBeat.i(13007, false);
        try {
            String queryParameter = uri.getQueryParameter("from_source");
            g a2 = g.a();
            if (b.a(queryParameter)) {
                queryParameter = "";
            }
            a2.b(queryParameter);
            StatisticsManager.f("open_app", "open_type", "1", "push_id", uri.getQueryParameter("bucketId"), "push_access", Integer.valueOf(ag.a(context)));
        } catch (Exception e) {
            d.a("MLinkHelper", e);
            g.a().b("");
        }
        AppMethodBeat.o(13007);
    }

    private void b() {
        AppMethodBeat.i(13008, false);
        StatisticsManager.f("open_app", "open_type", "0");
        a aVar = this.b;
        if (aVar != null) {
            aVar.a();
        }
        AppMethodBeat.o(13008);
    }

    private boolean b(Map map, String str) {
        AppMethodBeat.i(13009, false);
        if (b.a(map) || TextUtils.isEmpty(str) || !map.containsKey(str) || map.get(str) == null) {
            AppMethodBeat.o(13009);
            return false;
        }
        AppMethodBeat.o(13009);
        return true;
    }

    /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0114, code lost:
        if (r10.equals("commonKey") != false) goto L_0x0123;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(java.util.Map r9, java.lang.String r10) {
        /*
        // Method dump skipped, instructions count: 510
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.helper.e.c(java.util.Map, java.lang.String):void");
    }

    @Deprecated
    private void a(Activity activity) {
        AppMethodBeat.i(13011, false);
        if (cn.missfresh.module.base.manager.e.T()) {
            com.alibaba.android.arouter.b.a.a().a("/main/mall").withInt("INTENT_EXTRA_POSITION", BottomTabEnum.DELICACY.getPos()).withAction("action_nothing").addFlags(67108864).navigation();
            b(activity);
        } else {
            b();
        }
        AppMethodBeat.o(13011);
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0087  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(java.util.Map r11, android.app.Activity r12) {
        /*
            r10 = this;
            r0 = 13012(0x32d4, float:1.8234E-41)
            r1 = 0
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r1)
            java.lang.String r1 = "performH5Route=>"
            java.lang.String r2 = "performH5Route is called"
            cn.missfresh.utils.a.d.c(r1, r2)
            java.lang.String r1 = "h5_title"
            boolean r2 = r10.b(r11, r1)
            java.lang.String r3 = ""
            if (r2 == 0) goto L_0x0024
            java.lang.Object r2 = r11.get(r1)
            java.lang.String r2 = r2.toString()
            goto L_0x0025
        L_0x0024:
            r2 = r3
        L_0x0025:
            java.lang.String r4 = "host"
            boolean r5 = r10.b(r11, r4)
            if (r5 == 0) goto L_0x0037
            java.lang.Object r4 = r11.get(r4)
            java.lang.String r4 = r4.toString()
            goto L_0x0038
        L_0x0037:
            r4 = r3
        L_0x0038:
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            if (r5 == 0) goto L_0x0042
            java.lang.String r5 = "host is null"
            goto L_0x0043
        L_0x0042:
            r5 = r4
        L_0x0043:
            java.lang.String r6 = "host=>"
            cn.missfresh.utils.a.d.c(r6, r5)
            boolean r5 = android.text.TextUtils.isEmpty(r4)
            java.lang.String r6 = "thirdParty"
            java.lang.String r7 = "h5_url"
            if (r5 != 0) goto L_0x006d
            boolean r5 = r4.contains(r6)
            if (r5 == 0) goto L_0x006d
            java.lang.String r5 = "url"
            boolean r8 = r10.b(r11, r5)
            if (r8 == 0) goto L_0x007c
            java.lang.Object r5 = r11.get(r5)
            java.lang.String r5 = r5.toString()
            goto L_0x007d
        L_0x006d:
            boolean r5 = r10.b(r11, r7)
            if (r5 == 0) goto L_0x007c
            java.lang.Object r5 = r11.get(r7)
            java.lang.String r5 = r5.toString()
            goto L_0x007d
        L_0x007c:
            r5 = r3
        L_0x007d:
            boolean r8 = android.text.TextUtils.isEmpty(r5)
            if (r8 == 0) goto L_0x0087
            java.lang.String r8 = "h5Url is null"
            goto L_0x0088
        L_0x0087:
            r8 = r5
        L_0x0088:
            java.lang.String r9 = "h5Url=>"
            cn.missfresh.utils.a.d.c(r9, r8)
            boolean r8 = android.text.TextUtils.isEmpty(r5)
            if (r8 == 0) goto L_0x00a1
            r10.b()
            java.lang.String r12 = "\"H5 : h5_url is null\""
            r10.a(r11, r12)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        L_0x00a1:
            java.lang.String r8 = "h5_promotion_id"
            boolean r9 = r10.b(r11, r8)
            if (r9 == 0) goto L_0x00b1
            java.lang.Object r11 = r11.get(r8)
            r11.toString()
        L_0x00b1:
            java.lang.String r11 = "H5Activity=>"
            java.lang.String r8 = "go H5Activity"
            cn.missfresh.utils.a.d.c(r11, r8)
            boolean r11 = android.text.TextUtils.isEmpty(r4)
            if (r11 != 0) goto L_0x00d9
            boolean r11 = r4.contains(r6)
            if (r11 == 0) goto L_0x00d9
            r10.a(r5)
            android.os.Handler r11 = new android.os.Handler
            r11.<init>()
            cn.missfresh.module.base.helper.e$3 r1 = new cn.missfresh.module.base.helper.e$3
            r1.<init>(r5)
            r2 = 1000(0x3e8, double:4.94E-321)
            r11.postDelayed(r1, r2)
            goto L_0x00f6
        L_0x00d9:
            com.alibaba.android.arouter.b.a r11 = com.alibaba.android.arouter.b.a.a()
            java.lang.String r4 = "/promotion/new_h5event"
            com.alibaba.android.arouter.facade.Postcard r11 = r11.a(r4)
            com.alibaba.android.arouter.facade.Postcard r11 = r11.withString(r1, r2)
            com.alibaba.android.arouter.facade.Postcard r11 = r11.withString(r7, r5)
            java.lang.String r1 = "H5_promotion_id"
            com.alibaba.android.arouter.facade.Postcard r11 = r11.withString(r1, r3)
            r11.navigation()
        L_0x00f6:
            r10.b(r12)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.helper.e.b(java.util.Map, android.app.Activity):void");
    }

    /* compiled from: MLinkHelper */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.helper.e$3  reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ String a;

        AnonymousClass3(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(12994, false);
            com.alibaba.android.arouter.b.a.a().a("/promotion/new_h5event").withString("h5_url", this.a).navigation();
            AppMethodBeat.o(12994);
        }
    }

    private void a(String str) {
        AppMethodBeat.i(13013, false);
        LogBean logBean = new LogBean();
        logBean.setType("ThirdH5");
        logBean.setStr_value_0(str);
        d.a(logBean);
        AppMethodBeat.o(13013);
    }

    private void c(Map map, Activity activity) {
        Object obj;
        AppMethodBeat.i(13016, false);
        try {
            String str = "-1";
            String obj2 = b(map, BrowserContract.Bookmarks.POSITION) ? map.get(BrowserContract.Bookmarks.POSITION).toString() : str;
            if (b(map, "subPosition") && (obj = map.get("subPosition")) != null) {
                str = obj.toString();
            }
            com.alibaba.android.arouter.b.a.a().a("/main/mall").withInt("INTENT_EXTRA_POSITION", Integer.valueOf(obj2).intValue()).withString("INTENT_EXTRA_SUBPOSITION", str).addFlags(67108864).navigation();
            b(activity);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            b();
            a(map, "ShoppingCartRoute position format error");
        }
        AppMethodBeat.o(13016);
    }

    private void d(Map map, Activity activity) {
        AppMethodBeat.i(13018, false);
        String str = "";
        try {
            if (b(map, "sku")) {
                str = map.get("sku").toString();
            } else {
                a(map, "ProductDetail : sku is null");
            }
            if (TextUtils.isEmpty(str)) {
                b();
                AppMethodBeat.o(13018);
                return;
            }
            String str2 = "0";
            if (b(map, "product_type")) {
                str2 = map.get("product_type").toString();
            }
            if (Integer.parseInt(str2) == 0) {
                ((IProductDetailService) com.alibaba.android.arouter.b.a.a().a("/product/product_detail_service").navigation()).routerProductDetail(str, null, null, 0, "");
            } else {
                com.alibaba.android.arouter.b.a.a().a("/promotion/exchange_product").withString("sku", str).navigation();
            }
            b(activity);
            AppMethodBeat.o(13018);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            b();
            a(map, "ProductDetailRoute productType format error");
        }
    }

    /* access modifiers changed from: protected */
    public void a(Map map, Activity activity) {
        AppMethodBeat.i(13020, false);
        Bundle bundle = new Bundle();
        if (b(map, "jsonParam")) {
            String decode = URLDecoder.decode(map.get("jsonParam").toString());
            bundle = q.a(decode);
            d.c("MLinkHelper", "Switch page: commonKey link params " + decode);
        } else {
            a(map, "Commonkey : json paramas is null");
        }
        com.alibaba.android.arouter.b.a.a().a("/main/mall").withInt("skipToType", 3).with(bundle).addFlags(67108864).navigation();
        b(activity);
        AppMethodBeat.o(13020);
    }

    private void b(Activity activity) {
        AppMethodBeat.i(13022, false);
        if (activity != null && !"MainActivity".equals(activity.getClass().getSimpleName())) {
            activity.finish();
        }
        AppMethodBeat.o(13022);
    }
}
