package cn.com.chinatelecom.account.a;

import android.content.Context;
import android.text.TextUtils;
import cn.com.chinatelecom.account.api.Helper;
import cn.com.chinatelecom.account.api.b.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import io.flutter.plugin.editing.FlutterTextUtils;
import java.net.URLEncoder;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONArray;
import org.json.JSONObject;

public class c extends a {
    private static final String b = c.class.getSimpleName();

    /* access modifiers changed from: package-private */
    /* renamed from: cn.com.chinatelecom.account.a.c$1  reason: invalid class name */
    public static class AnonymousClass1 implements Runnable {
        final /* synthetic */ Context a;
        final /* synthetic */ List b;
        final /* synthetic */ int c;

        AnonymousClass1(Context context, List list, int i) {
            this.a = context;
            this.b = list;
            this.c = i;
        }

        /* JADX WARNING: Removed duplicated region for block: B:19:0x003c A[ADDED_TO_REGION] */
        @Override // java.lang.Runnable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r6 = this;
                r0 = 8400(0x20d0, float:1.1771E-41)
                r1 = 0
                cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r1)
                android.content.Context r1 = r6.a     // Catch:{ all -> 0x004f }
                java.util.List r2 = r6.b     // Catch:{ all -> 0x004f }
                int r3 = r6.c     // Catch:{ all -> 0x004f }
                java.util.Queue r1 = cn.com.chinatelecom.account.a.c.a(r1, r2, r3)     // Catch:{ all -> 0x004f }
                boolean r2 = r1.isEmpty()     // Catch:{ all -> 0x004f }
                if (r2 != 0) goto L_0x0053
                android.content.Context r2 = r6.a     // Catch:{ all -> 0x004f }
                java.lang.String r2 = cn.com.chinatelecom.account.a.c.a(r2, r1)     // Catch:{ all -> 0x004f }
                r3 = 0
                r4 = -1
                boolean r5 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Exception -> 0x0036 }
                if (r5 != 0) goto L_0x003a
                org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ Exception -> 0x0036 }
                r5.<init>(r2)     // Catch:{ Exception -> 0x0036 }
                java.lang.String r2 = "code"
                int r2 = r5.getInt(r2)     // Catch:{ Exception -> 0x0033 }
                r4 = r2
                r3 = r5
                goto L_0x003a
            L_0x0033:
                r2 = move-exception
                r3 = r5
                goto L_0x0037
            L_0x0036:
                r2 = move-exception
            L_0x0037:
                r2.printStackTrace()
            L_0x003a:
                if (r3 == 0) goto L_0x0047
                if (r4 != 0) goto L_0x0047
                android.content.Context r2 = r6.a
                cn.com.chinatelecom.account.a.c.a(r2)
                r1.clear()
                goto L_0x0053
            L_0x0047:
                android.content.Context r2 = r6.a
                int r3 = r6.c
                cn.com.chinatelecom.account.a.c.a(r2, r1, r3)
                goto L_0x0053
            L_0x004f:
                r1 = move-exception
                r1.printStackTrace()
            L_0x0053:
                cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.com.chinatelecom.account.a.c.AnonymousClass1.run():void");
        }
    }

    static {
        AppMethodBeat.i(8443, false);
        AppMethodBeat.o(8443);
    }

    static /* synthetic */ String a(Context context, Queue queue) {
        AppMethodBeat.i(8434, false);
        String b2 = b(context, queue);
        AppMethodBeat.o(8434);
        return b2;
    }

    static /* synthetic */ Queue a(Context context, List list, int i) {
        AppMethodBeat.i(8432, false);
        Queue<String> c = c(context, list, i);
        AppMethodBeat.o(8432);
        return c;
    }

    static /* synthetic */ void a(Context context) {
        AppMethodBeat.i(8437, false);
        c(context);
        AppMethodBeat.o(8437);
    }

    private static void a(Context context, int i) {
        AppMethodBeat.i(8410, false);
        try {
            cn.com.chinatelecom.account.api.c.c.a(context, "key_c_l_l_v", i);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(8410);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0041  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.content.Context r7, java.lang.String r8) {
        /*
            r0 = 8412(0x20dc, float:1.1788E-41)
            r1 = 0
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r1)
            int r2 = r8.hashCode()
            r3 = 64897(0xfd81, float:9.094E-41)
            r4 = 2
            r5 = 1
            r6 = -1
            if (r2 == r3) goto L_0x0033
            r3 = 78159(0x1314f, float:1.09524E-40)
            if (r2 == r3) goto L_0x0028
            r3 = 66247144(0x3f2d9e8, float:1.42735105E-36)
            if (r2 == r3) goto L_0x001d
            goto L_0x003e
        L_0x001d:
            java.lang.String r2 = "ERROR"
            boolean r8 = r8.equals(r2)
            if (r8 == 0) goto L_0x003e
            r8 = r5
            goto L_0x003f
        L_0x0028:
            java.lang.String r2 = "OFF"
            boolean r8 = r8.equals(r2)
            if (r8 == 0) goto L_0x003e
            r8 = r4
            goto L_0x003f
        L_0x0033:
            java.lang.String r2 = "ALL"
            boolean r8 = r8.equals(r2)
            if (r8 == 0) goto L_0x003e
            r8 = r1
            goto L_0x003f
        L_0x003e:
            r8 = r6
        L_0x003f:
            if (r8 == 0) goto L_0x0049
            if (r8 == r5) goto L_0x0048
            if (r8 == r4) goto L_0x0046
            goto L_0x0049
        L_0x0046:
            r1 = -2
            goto L_0x0049
        L_0x0048:
            r1 = r6
        L_0x0049:
            a(r7, r1)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.com.chinatelecom.account.a.c.a(android.content.Context, java.lang.String):void");
    }

    public static void a(Context context, List<String> list) {
        AppMethodBeat.i(8406, false);
        int b2 = b(context);
        if (b2 == -2) {
            AppMethodBeat.o(8406);
            return;
        }
        b(context, list, b2);
        AppMethodBeat.o(8406);
    }

    static /* synthetic */ void a(Context context, Queue queue, int i) {
        AppMethodBeat.i(8440, false);
        b(context, queue, i);
        AppMethodBeat.o(8440);
    }

    private static int b(Context context) {
        int i = 0;
        AppMethodBeat.i(8415, false);
        try {
            i = cn.com.chinatelecom.account.api.c.c.b(context, "key_c_l_l_v", 0);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        AppMethodBeat.o(8415);
        return i;
    }

    private static String b(Context context, String str) {
        AppMethodBeat.i(8421, false);
        String a = a.a(context, "https://collect.ux.21cn.com/collect/custom/accountMsg", str);
        AppMethodBeat.o(8421);
        return a;
    }

    private static String b(Context context, Queue<String> queue) {
        AppMethodBeat.i(8425, false);
        JSONArray jSONArray = new JSONArray();
        String jSONArray2 = jSONArray.toString();
        if (!queue.isEmpty()) {
            for (String str : queue) {
                try {
                    jSONArray.put(new JSONObject(str));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (jSONArray.length() <= 0) {
            AppMethodBeat.o(8425);
            return "";
        }
        String jSONArray3 = jSONArray.toString();
        if (!TextUtils.isEmpty(jSONArray3)) {
            try {
                jSONArray2 = URLEncoder.encode(Helper.guulam(context, jSONArray3), "UTF-8");
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        String b2 = b(context, jSONArray2);
        AppMethodBeat.o(8425);
        return b2;
    }

    private static void b(Context context, List<String> list, int i) {
        AppMethodBeat.i(8408, false);
        a(new AnonymousClass1(context, list, i));
        AppMethodBeat.o(8408);
    }

    private static void b(Context context, Queue<String> queue, int i) {
        String str;
        int i2 = 0;
        AppMethodBeat.i(8430, false);
        JSONArray jSONArray = new JSONArray();
        if (queue != null && !queue.isEmpty()) {
            for (String str2 : queue) {
                try {
                    JSONObject jSONObject = new JSONObject(str2);
                    if (i != -1 || jSONObject.getInt("rt") != 0) {
                        jSONArray.put(jSONObject);
                        i2++;
                        if (i2 > 10) {
                            break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (jSONArray.length() > 0) {
            try {
                str = Helper.eneulret(jSONArray.toString());
            } catch (Exception e2) {
                e2.printStackTrace();
                str = null;
            }
        } else {
            str = "";
        }
        if (!TextUtils.isEmpty(str)) {
            b.a(context, str);
        }
        AppMethodBeat.o(8430);
    }

    private static synchronized Queue<String> c(Context context, List<String> list, int i) {
        ConcurrentLinkedQueue concurrentLinkedQueue;
        synchronized (c.class) {
            int i2 = 0;
            AppMethodBeat.i(FlutterTextUtils.COMBINING_ENCLOSING_KEYCAP, false);
            concurrentLinkedQueue = new ConcurrentLinkedQueue();
            String a = b.a(context);
            if (!TextUtils.isEmpty(a)) {
                try {
                    JSONArray jSONArray = new JSONArray(new String(Helper.dneulret(cn.com.chinatelecom.account.api.a.c.a(a))));
                    int length = jSONArray.length();
                    while (i2 < length && i2 <= 10) {
                        JSONObject jSONObject = jSONArray.getJSONObject(i2);
                        if (jSONObject != null) {
                            concurrentLinkedQueue.add(jSONObject.toString());
                        }
                        i2++;
                    }
                    b.a(context, "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (i == -1) {
                for (String str : list) {
                    try {
                        if (new JSONObject(str).getInt("rt") != 0) {
                            concurrentLinkedQueue.add(str);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
            } else if (i == 0) {
                concurrentLinkedQueue.addAll(list);
            }
            while (concurrentLinkedQueue.size() > 10) {
                concurrentLinkedQueue.poll();
            }
            AppMethodBeat.o(FlutterTextUtils.COMBINING_ENCLOSING_KEYCAP);
        }
        return concurrentLinkedQueue;
    }

    private static void c(Context context) {
        AppMethodBeat.i(8428, false);
        b.a(context, "");
        AppMethodBeat.o(8428);
    }
}
