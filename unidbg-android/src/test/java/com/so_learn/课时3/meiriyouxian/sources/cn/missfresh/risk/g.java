package cn.missfresh.risk;

import cn.missfresh.risk.bean.RiskResultCcgBean;
import cn.missfresh.risk.f.c;
import cn.missfresh.risk.f.d;
import cn.missfresh.risk.f.i;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.e;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.android.internal.logging.nano.MetricsProto;
import com.xiaomi.mipush.sdk.Constants;
import io.reactivex.c.h;
import io.reactivex.f.a;
import io.reactivex.q;
import io.reactivex.t;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.a.b;

/* compiled from: RiskCcgUploadUtil */
public class g {
    public static void a(String str) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.PROVISIONING_COPY_ACCOUNT_TASK_MS, false);
        if (e.a(str)) {
            AppMethodBeat.o(MetricsProto.MetricsEvent.PROVISIONING_COPY_ACCOUNT_TASK_MS);
            return;
        }
        io.reactivex.g.a(str).a((h) new AnonymousClass5(str)).f().ag_().a(a.a()).a((h) new AnonymousClass4()).a((h) new AnonymousClass3()).a(cn.missfresh.basiclib.net.e.a.a).a(new AnonymousClass1(), new AnonymousClass2());
        AppMethodBeat.o(MetricsProto.MetricsEvent.PROVISIONING_COPY_ACCOUNT_TASK_MS);
    }

    /* compiled from: RiskCcgUploadUtil */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.risk.g$5  reason: invalid class name */
    public static class AnonymousClass5 implements h<String, b<RiskResultCcgBean>> {
        final /* synthetic */ String a;

        AnonymousClass5(String str) {
            this.a = str;
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(MetricsProto.MetricsEvent.PROVISIONING_ENTRY_POINT_NFC, false);
            b<RiskResultCcgBean> a = a((String) obj);
            AppMethodBeat.o(MetricsProto.MetricsEvent.PROVISIONING_ENTRY_POINT_NFC);
            return a;
        }

        public b<RiskResultCcgBean> a(String str) throws Exception {
            AppMethodBeat.i(MetricsProto.MetricsEvent.PROVISIONING_EXTRA, false);
            if (!e.a(str)) {
                StringBuilder sb = new StringBuilder(this.a.length() - 2);
                sb.append(str.substring(0, 1));
                sb.append(str.substring(2, 3));
                sb.append(str.substring(4));
                io.reactivex.g b = io.reactivex.g.a((Iterable) JSON.parseArray(g.b(sb.toString()), RiskResultCcgBean.class)).a(8).a(a.b()).a(new AnonymousClass1()).b();
                AppMethodBeat.o(MetricsProto.MetricsEvent.PROVISIONING_EXTRA);
                return b;
            }
            io.reactivex.g b2 = io.reactivex.g.b();
            AppMethodBeat.o(MetricsProto.MetricsEvent.PROVISIONING_EXTRA);
            return b2;
        }

        /* compiled from: RiskCcgUploadUtil */
        /* access modifiers changed from: package-private */
        /* renamed from: cn.missfresh.risk.g$5$1  reason: invalid class name */
        public class AnonymousClass1 implements h<RiskResultCcgBean, b<RiskResultCcgBean>> {
            AnonymousClass1() {
            }

            @Override // io.reactivex.c.h
            public /* synthetic */ Object apply(Object obj) throws Exception {
                AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_DATE_PICKER, false);
                b<RiskResultCcgBean> a = a((RiskResultCcgBean) obj);
                AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_DATE_PICKER);
                return a;
            }

            public b<RiskResultCcgBean> a(RiskResultCcgBean riskResultCcgBean) throws Exception {
                AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_WIFI_AP_EDIT, false);
                if (riskResultCcgBean == null || e.a(riskResultCcgBean.getPath()) || e.a(riskResultCcgBean.getKeyword())) {
                    io.reactivex.g b = io.reactivex.g.b();
                    AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_WIFI_AP_EDIT);
                    return b;
                }
                List asList = Arrays.asList(riskResultCcgBean.getKeyword().split(Constants.ACCEPT_TIME_SEPARATOR_SP));
                ArrayList arrayList = new ArrayList();
                if (g.a(riskResultCcgBean.getPath(), asList, arrayList)) {
                    riskResultCcgBean.setRisk(true);
                    riskResultCcgBean.setHitKeyWords(arrayList);
                }
                io.reactivex.g a = io.reactivex.g.a(riskResultCcgBean);
                AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_WIFI_AP_EDIT);
                return a;
            }
        }
    }

    /* compiled from: RiskCcgUploadUtil */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.risk.g$4  reason: invalid class name */
    public static class AnonymousClass4 implements h<List<RiskResultCcgBean>, t<JSONObject>> {
        AnonymousClass4() {
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_USER_ENABLE_CALLING_AND_SMS, false);
            t<JSONObject> a = a((List) obj);
            AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_USER_ENABLE_CALLING_AND_SMS);
            return a;
        }

        public t<JSONObject> a(List<RiskResultCcgBean> list) throws Exception {
            AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_USER_REMOVE, false);
            JSONObject jSONObject = new JSONObject();
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            if (!cn.missfresh.risk.f.b.a(list)) {
                for (RiskResultCcgBean riskResultCcgBean : list) {
                    if (hashMap.get(riskResultCcgBean.getField()) == null) {
                        HashMap hashMap3 = new HashMap();
                        hashMap3.put(riskResultCcgBean.getKey(), Integer.valueOf(riskResultCcgBean.isRisk() ? 1 : 0));
                        hashMap.put(riskResultCcgBean.getField(), hashMap3);
                    } else if (!((Map) hashMap.get(riskResultCcgBean.getField())).containsKey(riskResultCcgBean.getKey())) {
                        ((Map) hashMap.get(riskResultCcgBean.getField())).put(riskResultCcgBean.getKey(), Integer.valueOf(riskResultCcgBean.isRisk() ? 1 : 0));
                    } else if (((Integer) ((Map) hashMap.get(riskResultCcgBean.getField())).get(riskResultCcgBean.getKey())).intValue() != 1) {
                        ((Map) hashMap.get(riskResultCcgBean.getField())).put(riskResultCcgBean.getKey(), Integer.valueOf(riskResultCcgBean.isRisk() ? 1 : 0));
                    }
                    if (!cn.missfresh.utils.b.a(riskResultCcgBean.getHitKeyWords())) {
                        hashMap2.put(riskResultCcgBean.getPath(), riskResultCcgBean.getHitKeyWords());
                    }
                }
            }
            jSONObject.putAll(hashMap);
            jSONObject.put("token", d.a());
            jSONObject.put("hitKeyWords", hashMap2);
            cn.missfresh.utils.a.d.d("RiskCcgUploadUtil", jSONObject.toJSONString());
            q a = q.a(jSONObject).b(a.b()).a(a.b());
            AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_USER_REMOVE);
            return a;
        }
    }

    /* compiled from: RiskCcgUploadUtil */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.risk.g$3  reason: invalid class name */
    public static class AnonymousClass3 implements h<JSONObject, t<String>> {
        AnonymousClass3() {
        }

        @Override // io.reactivex.c.h
        public /* synthetic */ Object apply(Object obj) throws Exception {
            AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_ACCESSIBILITY_SERVICE_ENABLE, false);
            t<String> a = a((JSONObject) obj);
            AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_ACCESSIBILITY_SERVICE_ENABLE);
            return a;
        }

        public t<String> a(JSONObject jSONObject) throws Exception {
            AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_APN_RESTORE_DEFAULT, false);
            q a = q.a(i.a(c.a(jSONObject.toJSONString()), h.c)).b(a.b()).a(a.b());
            AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_APN_RESTORE_DEFAULT);
            return a;
        }
    }

    /* compiled from: RiskCcgUploadUtil */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.risk.g$1  reason: invalid class name */
    public static class AnonymousClass1 implements io.reactivex.c.g<String> {
        AnonymousClass1() {
        }

        @Override // io.reactivex.c.g
        public /* synthetic */ void accept(Object obj) throws Exception {
            AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_VOLUME_FORGET, false);
            a((String) obj);
            AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_VOLUME_FORGET);
        }

        public void a(String str) throws Exception {
            AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_ZEN_ACCESS_GRANT, false);
            new cn.missfresh.risk.f.h().a(str);
            AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_ZEN_ACCESS_GRANT);
        }
    }

    /* compiled from: RiskCcgUploadUtil */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.risk.g$2  reason: invalid class name */
    public static class AnonymousClass2 implements io.reactivex.c.g<Throwable> {
        AnonymousClass2() {
        }

        @Override // io.reactivex.c.g
        public /* synthetic */ void accept(Object obj) throws Exception {
            AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_FINGERPINT_EDIT, false);
            a((Throwable) obj);
            AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_FINGERPINT_EDIT);
        }

        public void a(Throwable th) throws Exception {
            AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_FINGERPRINT_ICON_TOUCH, false);
            th.printStackTrace();
            AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_FINGERPRINT_ICON_TOUCH);
        }
    }

    public static boolean a(String str, List<String> list, List<String> list2) {
        boolean z = false;
        z = false;
        AppMethodBeat.i(MetricsProto.MetricsEvent.PROVISIONING_ERROR, false);
        if (e.a(str)) {
            AppMethodBeat.o(MetricsProto.MetricsEvent.PROVISIONING_ERROR);
            return false;
        }
        if (list2 == null) {
            list2 = new ArrayList<>();
        }
        File file = new File(str);
        if (!file.exists()) {
            AppMethodBeat.o(MetricsProto.MetricsEvent.PROVISIONING_ERROR);
            return false;
        }
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            boolean z2 = false;
            for (File file2 : listFiles) {
                if (b(file2.getAbsolutePath(), list, list2)) {
                    z2 = true;
                }
            }
            z = z2;
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.PROVISIONING_ERROR);
        return z;
    }

    private static boolean b(String str, List<String> list, List<String> list2) {
        boolean z = false;
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_READ_CALENDAR, false);
        if (list2 == null) {
            list2 = new ArrayList<>();
        }
        Iterator<String> it2 = list.iterator();
        while (true) {
            if (!it2.hasNext()) {
                break;
            }
            String next = it2.next();
            if (str.contains(next)) {
                z = true;
                if (!list2.contains(next)) {
                    list2.add(next);
                }
            }
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_READ_CALENDAR);
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0054, code lost:
        if (r4 == null) goto L_0x0039;
     */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0051 A[SYNTHETIC, Splitter:B:36:0x0051] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x005e A[SYNTHETIC, Splitter:B:45:0x005e] */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0063 A[SYNTHETIC, Splitter:B:49:0x0063] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String b(java.lang.String r8) {
        /*
            r0 = 0
            r1 = 639(0x27f, float:8.95E-43)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r0)
            r2 = 0
            if (r8 != 0) goto L_0x000d
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r2
        L_0x000d:
            java.io.ByteArrayOutputStream r3 = new java.io.ByteArrayOutputStream
            r3.<init>()
            byte[] r8 = cn.missfresh.risk.f.a.a(r8)     // Catch:{ IOException -> 0x0049, all -> 0x0045 }
            java.io.ByteArrayInputStream r4 = new java.io.ByteArrayInputStream     // Catch:{ IOException -> 0x0049, all -> 0x0045 }
            r4.<init>(r8)     // Catch:{ IOException -> 0x0049, all -> 0x0045 }
            java.util.zip.GZIPInputStream r8 = new java.util.zip.GZIPInputStream     // Catch:{ IOException -> 0x0042, all -> 0x003f }
            r8.<init>(r4)     // Catch:{ IOException -> 0x0042, all -> 0x003f }
            r5 = 1024(0x400, float:1.435E-42)
            byte[] r5 = new byte[r5]     // Catch:{ IOException -> 0x003d }
        L_0x0024:
            int r6 = r8.read(r5)     // Catch:{ IOException -> 0x003d }
            r7 = -1
            if (r6 == r7) goto L_0x002f
            r3.write(r5, r0, r6)     // Catch:{ IOException -> 0x003d }
            goto L_0x0024
        L_0x002f:
            java.lang.String r2 = r3.toString()     // Catch:{ IOException -> 0x003d }
            r8.close()     // Catch:{ IOException -> 0x0036 }
        L_0x0036:
            r4.close()     // Catch:{ IOException -> 0x0039 }
        L_0x0039:
            r3.close()     // Catch:{ IOException -> 0x0057 }
            goto L_0x0057
        L_0x003d:
            r0 = move-exception
            goto L_0x004c
        L_0x003f:
            r0 = move-exception
            r8 = r2
            goto L_0x005c
        L_0x0042:
            r0 = move-exception
            r8 = r2
            goto L_0x004c
        L_0x0045:
            r0 = move-exception
            r8 = r2
            r4 = r8
            goto L_0x005c
        L_0x0049:
            r0 = move-exception
            r8 = r2
            r4 = r8
        L_0x004c:
            r0.printStackTrace()     // Catch:{ all -> 0x005b }
            if (r8 == 0) goto L_0x0054
            r8.close()     // Catch:{ IOException -> 0x0054 }
        L_0x0054:
            if (r4 == 0) goto L_0x0039
            goto L_0x0036
        L_0x0057:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r2
        L_0x005b:
            r0 = move-exception
        L_0x005c:
            if (r8 == 0) goto L_0x0061
            r8.close()     // Catch:{ IOException -> 0x0061 }
        L_0x0061:
            if (r4 == 0) goto L_0x0066
            r4.close()     // Catch:{ IOException -> 0x0066 }
        L_0x0066:
            r3.close()     // Catch:{ IOException -> 0x0069 }
        L_0x0069:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.risk.g.b(java.lang.String):java.lang.String");
    }
}
