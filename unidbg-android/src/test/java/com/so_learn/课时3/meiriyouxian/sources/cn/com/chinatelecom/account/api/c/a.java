package cn.com.chinatelecom.account.api.c;

import android.content.Context;
import android.net.Network;
import android.os.Build;
import android.text.TextUtils;
import anet.channel.util.HttpConstant;
import cn.com.chinatelecom.account.api.CtAuth;
import cn.com.chinatelecom.account.api.b.c;
import cn.com.chinatelecom.account.api.b.d;
import cn.com.chinatelecom.account.api.b.f;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.huawei.hms.framework.common.ContainerUtils;
import com.tencent.imsdk.BaseConstants;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class a {
    private static final String a = a.class.getSimpleName();
    private static HashMap<String, String> b = new HashMap<>();

    static {
        AppMethodBeat.i(8144, false);
        AppMethodBeat.o(8144);
    }

    public static long a(Context context) {
        AppMethodBeat.i(8115, false);
        long b2 = c.b(context, "key_difference_time", 0L);
        AppMethodBeat.o(8115);
        return b2;
    }

    public static c a(Context context, HttpURLConnection httpURLConnection, boolean z) {
        AppMethodBeat.i(8134, false);
        if (!z) {
            AppMethodBeat.o(8134);
            return null;
        }
        c cVar = new c();
        try {
            Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
            List<String> list = headerFields.get("p");
            if (list != null && list.size() > 0) {
                String str = list.get(0);
                CtAuth.info(a, "request protocol : " + str);
                cVar.b = a(context, str);
            }
            List<String> list2 = headerFields.get(HttpConstant.SET_COOKIE);
            if (list2 != null && list2.size() > 0) {
                int i = 0;
                while (true) {
                    if (i >= list2.size()) {
                        break;
                    }
                    String str2 = list2.get(0);
                    if (!TextUtils.isEmpty(str2) && str2.contains("gw_auth")) {
                        cVar.a = a(str2, "gw_auth");
                        break;
                    }
                    i++;
                }
            }
            List<String> list3 = headerFields.get("Log-Level");
            if (list3 != null && !list3.isEmpty()) {
                for (int i2 = 0; i2 < list3.size(); i2++) {
                    String str3 = list3.get(0);
                    if (!TextUtils.isEmpty(str3)) {
                        f.a(context, str3);
                    }
                }
            }
            List<String> list4 = headerFields.get("p-reset");
            if (list4 != null && !list4.isEmpty()) {
                String str4 = list4.get(0);
                if (!TextUtils.isEmpty(str4)) {
                    b(context, str4);
                }
            }
            List<String> list5 = headerFields.get("p-ikgx");
            if (list5 != null && !list5.isEmpty()) {
                String str5 = list5.get(0);
                if (!TextUtils.isEmpty(str5)) {
                    cVar.c = str5;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        AppMethodBeat.o(8134);
        return cVar;
    }

    public static c a(HttpURLConnection httpURLConnection) {
        AppMethodBeat.i(8137, false);
        c cVar = new c();
        try {
            Map<String, List<String>> headerFields = httpURLConnection.getHeaderFields();
            List<String> list = headerFields.get("rdt_allow");
            if (list != null && list.size() > 0) {
                cVar.d = list.get(0);
                String str = a;
                CtAuth.info(str, "request method : " + cVar.d);
            }
            List<String> list2 = headerFields.get("p-ikgx");
            if (list2 != null && !list2.isEmpty()) {
                String str2 = list2.get(0);
                if (!TextUtils.isEmpty(str2)) {
                    cVar.c = str2;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        AppMethodBeat.o(8137);
        return cVar;
    }

    public static String a(Context context, f fVar, String str, Network network, boolean z, String str2) {
        String b2;
        AppMethodBeat.i(8109, false);
        if (fVar == null || TextUtils.isEmpty(fVar.b)) {
            b2 = j.b();
        } else {
            if (fVar.a != -1) {
                try {
                    JSONObject jSONObject = new JSONObject(fVar.b);
                    int optInt = jSONObject.optInt("result");
                    String optString = jSONObject.optString("data");
                    if ((optInt == 0 || optInt == 30002) && !TextUtils.isEmpty(optString)) {
                        String a2 = h.a(optString, str);
                        if (!TextUtils.isEmpty(a2)) {
                            try {
                                JSONObject jSONObject2 = new JSONObject(a2);
                                jSONObject.put("data", jSONObject2);
                                jSONObject2.put("gwAuth", fVar.d);
                            } catch (JSONException e) {
                                e.printStackTrace();
                                jSONObject.put("data", (Object) null);
                            }
                            if (optInt != 30002 || !z) {
                                String jSONObject3 = jSONObject.toString();
                                AppMethodBeat.o(8109);
                                return jSONObject3;
                            }
                            ArrayList arrayList = new ArrayList();
                            JSONArray optJSONArray = ((JSONObject) jSONObject.opt("data")).optJSONArray("urls");
                            if (optJSONArray != null) {
                                for (int i = 0; i < optJSONArray.length(); i++) {
                                    arrayList.add(optJSONArray.getString(i));
                                }
                            }
                            if (arrayList.isEmpty()) {
                                AppMethodBeat.o(8109);
                                return null;
                            }
                            String a3 = a(context, arrayList, str, network, str2);
                            AppMethodBeat.o(8109);
                            return a3;
                        }
                    } else if (optInt == -10009 || optInt == -30001) {
                        long optLong = jSONObject.optLong("timeStamp", -1);
                        if (optLong == -1) {
                            d(context);
                        } else {
                            a(context, optLong);
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
            b2 = fVar.b;
        }
        AppMethodBeat.o(8109);
        return b2;
    }

    private static String a(Context context, List<String> list, String str, Network network, String str2) {
        AppMethodBeat.i(8112, false);
        for (int i = 0; i < list.size(); i++) {
            try {
                String str3 = list.get(i);
                if (!TextUtils.isEmpty(str3)) {
                    if (Build.VERSION.SDK_INT < 21) {
                        d.a(context, str3);
                    }
                    String a2 = a(context, d.a(context, str3, network, str2), str, network, false, str2);
                    try {
                        if (new JSONObject(a2).optInt("result") == 0) {
                            AppMethodBeat.o(8112);
                            return a2;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        String a3 = j.a(BaseConstants.ERR_SVR_COMM_SENSITIVE_TEXT, "\u8bf7\u6c42\u7f51\u7edc\u5f02\u5e38- redirect 30002 ");
        AppMethodBeat.o(8112);
        return a3;
    }

    private static String a(String str, String str2) {
        int i = 0;
        AppMethodBeat.i(8142, false);
        String str3 = "";
        try {
            String[] split = str.split(";");
            while (true) {
                if (i >= split.length) {
                    break;
                } else if (split[i].contains(str2)) {
                    str3 = split[i].split(ContainerUtils.KEY_VALUE_DELIMITER)[1];
                    break;
                } else {
                    i++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(8142);
        return str3;
    }

    private static synchronized void a(Context context, int i) {
        synchronized (a.class) {
            AppMethodBeat.i(8131, false);
            try {
                c.a(context, "key_p_a_p_v3.7.0", i);
            } catch (Exception e) {
                e.printStackTrace();
            }
            AppMethodBeat.o(8131);
        }
    }

    private static void a(Context context, long j) {
        AppMethodBeat.i(8117, false);
        if (j > 0) {
            c.a(context, "key_difference_time", j - System.currentTimeMillis());
        }
        AppMethodBeat.o(8117);
    }

    public static synchronized boolean a(Context context, String str) {
        synchronized (a.class) {
            AppMethodBeat.i(8127, false);
            if (str == null) {
                AppMethodBeat.o(8127);
                return false;
            }
            try {
                if ("http".equals(str.toLowerCase()) || "https".equals(str.toLowerCase())) {
                    if ((e(context) == 1 ? "https" : "http").equals(str.toLowerCase())) {
                        AppMethodBeat.o(8127);
                        return false;
                    }
                    a(context, str.toLowerCase().equals("https") ? 1 : 0);
                    AppMethodBeat.o(8127);
                    return true;
                }
                AppMethodBeat.o(8127);
                return false;
            } catch (Exception e) {
                e.printStackTrace();
                AppMethodBeat.o(8127);
                return false;
            }
        }
    }

    private static void b(Context context, String str) {
        AppMethodBeat.i(8140, false);
        c.a(context, "key_p_rset_v3.7.0", str);
        AppMethodBeat.o(8140);
    }

    public static synchronized boolean b(Context context) {
        synchronized (a.class) {
            AppMethodBeat.i(8123, false);
            try {
                if (e(context) == 0) {
                    AppMethodBeat.o(8123);
                    return true;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            AppMethodBeat.o(8123);
            return false;
        }
    }

    public static synchronized String c(Context context) {
        synchronized (a.class) {
            AppMethodBeat.i(8125, false);
            if (b(context)) {
                AppMethodBeat.o(8125);
                return "pe";
            }
            AppMethodBeat.o(8125);
            return "presdk";
        }
    }

    private static void d(Context context) {
        AppMethodBeat.i(8120, false);
        String str = d.a(context, "https://open.e.189.cn/openapi/special/getTimeStamp.do", "", null, null, false, 0, "reqTimestamp", d.a(), false, false).b;
        if (!TextUtils.isEmpty(str)) {
            JSONObject jSONObject = null;
            try {
                jSONObject = new JSONObject(str);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (jSONObject != null) {
                a(context, jSONObject.optLong("msg", -1));
            }
        }
        AppMethodBeat.o(8120);
    }

    private static synchronized int e(Context context) {
        int b2;
        synchronized (a.class) {
            AppMethodBeat.i(8133, false);
            b2 = c.b(context, "key_p_a_p_v3.7.0", 1);
            AppMethodBeat.o(8133);
        }
        return b2;
    }
}
