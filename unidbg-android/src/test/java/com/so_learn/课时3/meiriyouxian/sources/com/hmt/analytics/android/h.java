package com.hmt.analytics.android;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.text.TextUtils;
import com.hmt.analytics.a.a;
import com.hmt.analytics.util.i;
import com.hmt.analytics.util.j;
import java.util.Iterator;
import java.util.TimeZone;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: HMTTool */
public class h {
    private static final String a = h.class.getSimpleName();

    public static void c(String str) {
    }

    public static void a(Context context, JSONObject jSONObject, String str, String str2, String str3, a aVar) {
        String str4 = a;
        a.a(str4, "Trigger send " + str + " type of data!");
        a(str, a(context, jSONObject, str), context, str2);
        c(str + "-end");
        if (1 == a.i(context) && a.c(context)) {
            a.a(a, "Policy mode one!");
            j.a().execute(new c(context));
        }
        if (aVar != null) {
            aVar.a();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0073  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00ae  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b0  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00e3 A[Catch:{ JSONException -> 0x00f9 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static org.json.JSONObject a(android.content.Context r6, org.json.JSONObject r7, java.lang.String r8) {
        /*
        // Method dump skipped, instructions count: 278
        */
        throw new UnsupportedOperationException("Method not decompiled: com.hmt.analytics.android.h.a(android.content.Context, org.json.JSONObject, java.lang.String):org.json.JSONObject");
    }

    public static void a(String str, JSONObject jSONObject, Context context, String str2) {
        JSONArray jSONArray = new JSONArray();
        try {
            jSONArray.put(0, jSONObject);
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put(str, jSONArray);
            o.a(context, jSONObject2, str2);
        } catch (JSONException e) {
            String str3 = a;
            a.a(str3, "Collected:" + e.getMessage());
        }
    }

    public static void a(JSONObject jSONObject, JSONObject jSONObject2) {
        if (jSONObject != null && jSONObject2 != null) {
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    jSONObject.put(next, jSONObject2.optString(next));
                } catch (JSONException e) {
                    String str = a;
                    a.a(str, "Collected:" + e.getMessage());
                }
            }
        }
    }

    public static JSONObject a(JSONObject jSONObject, com.hmt.analytics.util.a aVar) {
        if (aVar != null) {
            JSONObject a2 = aVar.a();
            Iterator<String> keys = a2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                try {
                    Object obj = a2.get(next);
                    if (obj instanceof JSONArray) {
                        jSONObject.put("p_" + next, obj);
                    } else {
                        JSONArray jSONArray = new JSONArray();
                        jSONArray.put(obj);
                        jSONObject.put("p_" + next, jSONArray);
                    }
                } catch (JSONException e) {
                    String str = a;
                    a.a(str, "Collected:" + e.getMessage());
                }
            }
        }
        return jSONObject;
    }

    public static void a(String str) {
        if (g.w != null && !TextUtils.isEmpty(str)) {
            g.w.a(str);
        }
    }

    public static void b(String str) {
        if (g.w != null && !TextUtils.isEmpty(str)) {
            g.w.b(str);
        }
    }

    public static void a(String str, int i) {
        if (g.w != null && !TextUtils.isEmpty(str)) {
            g.w.a(str, i);
        }
    }

    public static boolean a(long j, long j2) {
        long j3 = j - j2;
        return j3 < 86400000 && j3 > -86400000 && a(j) == a(j2);
    }

    private static long a(long j) {
        return (j + ((long) TimeZone.getDefault().getOffset(j))) / 86400000;
    }

    public static boolean a(Context context) {
        return !a(System.currentTimeMillis(), ((Long) i.b(context, "hmt_init_savetime", "upload_save_time", 0L)).longValue());
    }

    public static boolean a(Long l) {
        return System.currentTimeMillis() - l.longValue() > DevicePolicyManager.DEFAULT_STRONG_AUTH_TIMEOUT_MS;
    }

    public static String a(Context context, String str) {
        String d = a.d(context);
        String str2 = (String) i.b(context, "hmt_send_url", "");
        if (!TextUtils.isEmpty(str2)) {
            return str2 + "/hmt?_z=m&jsonp=hmt&_ua=" + d + g.z;
        }
        return str + "&_ua=" + d;
    }

    public static String d(String str) {
        StringBuilder sb = new StringBuilder();
        int length = str.length() / 2;
        String str2 = str;
        for (int i = 0; i < length; i++) {
            for (char c : Character.toChars(Integer.parseInt(str2.substring(0, 2), 16))) {
                sb.append(c);
            }
            if (str2.length() > 2) {
                str2 = str2.substring(2, str2.length());
            }
        }
        return sb.toString();
    }
}
