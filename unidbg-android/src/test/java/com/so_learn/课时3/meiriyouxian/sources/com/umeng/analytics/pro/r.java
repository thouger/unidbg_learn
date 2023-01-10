package com.umeng.analytics.pro;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.CoreProtocol;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.HelperUtils;
import com.umeng.commonsdk.statistics.common.MLog;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: EventTracker */
public class r {
    private static final String a = "fs_lc_tl_uapp";
    private static final String f = "-1";
    private static Context g;
    private final int b;
    private final int c;
    private final int d;
    private final int e;
    private JSONObject h;

    private r() {
        this.b = 128;
        this.c = 256;
        this.d = 1024;
        this.e = 10;
        this.h = null;
        try {
            if (this.h == null) {
                b(g);
            }
        } catch (Throwable unused) {
        }
    }

    /* compiled from: EventTracker */
    /* access modifiers changed from: private */
    public static class a {
        private static final r a = new r();

        private a() {
        }
    }

    public static r a(Context context) {
        if (g == null && context != null) {
            g = context.getApplicationContext();
        }
        return a.a;
    }

    public void a(String str, String str2, long j, int i, String str3) {
        String str4;
        try {
            if (!a(str) || !b(str2)) {
                UMLog.aq(i.l, 0, "\\|");
            } else if (Arrays.asList(c.aG).contains(str)) {
                MLog.e("key is " + str + ", please check key, illegal");
                UMLog.aq(i.m, 0, "\\|");
            } else {
                long currentTimeMillis = System.currentTimeMillis();
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", str);
                jSONObject.put("ts", currentTimeMillis);
                if (j > 0) {
                    jSONObject.put(c.W, j);
                }
                jSONObject.put("__t", 2049);
                if (!TextUtils.isEmpty(str2)) {
                    jSONObject.put(str, str2);
                }
                if (UMUtils.isMainProgress(g)) {
                    str4 = x.a().d(UMGlobalContext.getAppContext(g));
                } else {
                    str4 = x.a().a(UMGlobalContext.getAppContext(g), currentTimeMillis);
                }
                if (TextUtils.isEmpty(str4)) {
                    str4 = "-1";
                }
                jSONObject.put("__i", str4);
                if (!TextUtils.isEmpty(str3)) {
                    try {
                        JSONObject jSONObject2 = new JSONObject(str3);
                        if (jSONObject2.length() > 0) {
                            jSONObject.put(c.au, jSONObject2);
                        }
                    } catch (JSONException unused) {
                    }
                }
                jSONObject.put("ds", 0);
                jSONObject.put("pn", UMGlobalContext.getInstance(g).getProcessName(g));
                a();
                if (this.h != null && this.h.has(str) && !((Boolean) this.h.get(str)).booleanValue()) {
                    jSONObject.put(c.Y, 1);
                    this.h.put(str, true);
                    c(g);
                }
                UMWorkDispatch.sendEvent(g, 4097, CoreProtocol.getInstance(g), jSONObject);
            }
        } catch (Throwable unused2) {
        }
    }

    public void a(String str, Map<String, Object> map, long j, String str2, boolean z) {
        String str3;
        try {
            if (!a(str)) {
                UMLog.aq(i.f, 0, "\\|");
            } else if (b(map)) {
                if (map.size() > 100) {
                    MLog.e("map size is " + map.size() + ", please check");
                } else if (Arrays.asList(c.aG).contains(str)) {
                    MLog.e("key is " + str + ", please check key, illegal");
                    UMLog.aq(i.b, 0, "\\|");
                } else {
                    JSONObject jSONObject = new JSONObject();
                    jSONObject.put("id", str);
                    jSONObject.put("ts", System.currentTimeMillis());
                    if (j > 0) {
                        jSONObject.put(c.W, j);
                    }
                    jSONObject.put("__t", 2049);
                    ULog.i("befort ekv map, event is " + jSONObject.toString());
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        if (!Arrays.asList(c.aG).contains(entry.getKey())) {
                            Object value = entry.getValue();
                            if ((value instanceof String) || (value instanceof Integer) || (value instanceof Long) || (value instanceof Short) || (value instanceof Float) || (value instanceof Double)) {
                                jSONObject.put(entry.getKey(), value);
                            } else if (!value.getClass().isArray()) {
                                MLog.e("please check key or value, illegal type!");
                                return;
                            } else if (value instanceof int[]) {
                                int[] iArr = (int[]) value;
                                if (iArr.length > 10) {
                                    MLog.e("please check key or value, size overlength!");
                                    return;
                                }
                                JSONArray jSONArray = new JSONArray();
                                for (int i : iArr) {
                                    jSONArray.put(i);
                                }
                                jSONObject.put(entry.getKey(), jSONArray);
                            } else if (value instanceof double[]) {
                                double[] dArr = (double[]) value;
                                if (dArr.length > 10) {
                                    MLog.e("please check key or value, size overlength!");
                                    return;
                                }
                                JSONArray jSONArray2 = new JSONArray();
                                for (double d : dArr) {
                                    jSONArray2.put(d);
                                }
                                jSONObject.put(entry.getKey(), jSONArray2);
                            } else if (value instanceof long[]) {
                                long[] jArr = (long[]) value;
                                if (jArr.length > 10) {
                                    MLog.e("please check key or value, size overlength!");
                                    return;
                                }
                                JSONArray jSONArray3 = new JSONArray();
                                for (long j2 : jArr) {
                                    jSONArray3.put(j2);
                                }
                                jSONObject.put(entry.getKey(), jSONArray3);
                            } else if (value instanceof float[]) {
                                float[] fArr = (float[]) value;
                                if (fArr.length > 10) {
                                    MLog.e("please check key or value, size overlength!");
                                    return;
                                }
                                JSONArray jSONArray4 = new JSONArray();
                                for (float f2 : fArr) {
                                    jSONArray4.put((double) f2);
                                }
                                jSONObject.put(entry.getKey(), jSONArray4);
                            } else if (value instanceof short[]) {
                                short[] sArr = (short[]) value;
                                if (sArr.length > 10) {
                                    MLog.e("please check key or value, size overlength!");
                                    return;
                                }
                                JSONArray jSONArray5 = new JSONArray();
                                for (short s : sArr) {
                                    jSONArray5.put((int) s);
                                }
                                jSONObject.put(entry.getKey(), jSONArray5);
                            } else if (value instanceof String[]) {
                                String[] strArr = (String[]) value;
                                if (strArr.length > 10) {
                                    MLog.e("please check key or value, size overlength!");
                                    return;
                                }
                                JSONArray jSONArray6 = new JSONArray();
                                for (int i2 = 0; i2 < strArr.length; i2++) {
                                    if (strArr[i2] == null) {
                                        MLog.e("please check array, null item!");
                                        return;
                                    } else if (b(strArr[i2])) {
                                        jSONArray6.put(strArr[i2]);
                                    } else {
                                        return;
                                    }
                                }
                                jSONObject.put(entry.getKey(), jSONArray6);
                            } else {
                                MLog.e("please check key or value, illegal type!");
                                return;
                            }
                        } else {
                            UMLog.aq(i.e, 0, "\\|");
                            return;
                        }
                    }
                    if (UMUtils.isMainProgress(g)) {
                        str3 = x.a().d(UMGlobalContext.getAppContext(g));
                    } else {
                        str3 = x.a().a(UMGlobalContext.getAppContext(g), jSONObject.getLong("ts"));
                    }
                    if (TextUtils.isEmpty(str3)) {
                        str3 = "-1";
                    }
                    jSONObject.put("__i", str3);
                    if (!TextUtils.isEmpty(str2)) {
                        try {
                            JSONObject jSONObject2 = new JSONObject(str2);
                            if (jSONObject2.length() > 0) {
                                jSONObject.put(c.au, jSONObject2);
                            }
                        } catch (JSONException unused) {
                        }
                    }
                    jSONObject.put("ds", 0);
                    jSONObject.put("pn", UMGlobalContext.getInstance(g).getProcessName(g));
                    a();
                    if (this.h != null && this.h.has(str) && !((Boolean) this.h.get(str)).booleanValue()) {
                        jSONObject.put(c.Y, 1);
                        this.h.put(str, true);
                        c(g);
                    }
                    ULog.i("----->>>>>ekv event json is " + jSONObject.toString());
                    if (!z) {
                        UMWorkDispatch.sendEvent(g, 4097, CoreProtocol.getInstance(g), jSONObject);
                    } else {
                        UMWorkDispatch.sendEvent(g, 4355, CoreProtocol.getInstance(g), jSONObject);
                    }
                }
            }
        } catch (Throwable unused2) {
        }
    }

    public void a(String str, Map<String, Object> map, String str2) {
        try {
            if (a(str)) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("id", str);
                jSONObject.put("ts", System.currentTimeMillis());
                jSONObject.put(c.W, 0);
                jSONObject.put("__t", 2050);
                ULog.i("befort gkv map, event is " + jSONObject.toString());
                Iterator<Map.Entry<String, Object>> it2 = map.entrySet().iterator();
                for (int i = 0; i < 10 && it2.hasNext(); i++) {
                    Map.Entry<String, Object> next = it2.next();
                    if (!c.Y.equals(next.getKey()) && !c.W.equals(next.getKey()) && !"id".equals(next.getKey()) && !"ts".equals(next.getKey())) {
                        Object value = next.getValue();
                        if ((value instanceof String) || (value instanceof Integer) || (value instanceof Long)) {
                            jSONObject.put(next.getKey(), value);
                        }
                    }
                }
                String d = x.a().d(UMGlobalContext.getAppContext(g));
                if (TextUtils.isEmpty(d)) {
                    d = "-1";
                }
                jSONObject.put("__i", d);
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        JSONObject jSONObject2 = new JSONObject(str2);
                        if (jSONObject2.length() > 0) {
                            jSONObject.put(c.au, jSONObject2);
                        }
                    } catch (JSONException unused) {
                    }
                }
                jSONObject.put("ds", 0);
                jSONObject.put("pn", UMGlobalContext.getInstance(g).getProcessName(g));
                ULog.i("----->>>>>gkv event json is " + jSONObject.toString());
                UMWorkDispatch.sendEvent(g, 4098, CoreProtocol.getInstance(g), jSONObject);
            }
        } catch (Throwable unused2) {
        }
    }

    private void b(Context context) {
        try {
            String string = PreferenceWrapper.getDefault(context).getString(a, null);
            if (!TextUtils.isEmpty(string)) {
                this.h = new JSONObject(string);
            }
            a();
        } catch (Exception unused) {
        }
    }

    private void a() {
        try {
            String imprintProperty = UMEnvelopeBuild.imprintProperty(g, "track_list", "");
            if (!TextUtils.isEmpty(imprintProperty)) {
                String[] split = imprintProperty.split("!");
                JSONObject jSONObject = new JSONObject();
                int i = 0;
                if (this.h != null) {
                    for (String str : split) {
                        String subStr = HelperUtils.subStr(str, 128);
                        if (this.h.has(subStr)) {
                            jSONObject.put(subStr, this.h.get(subStr));
                        }
                    }
                }
                this.h = new JSONObject();
                if (split.length >= 10) {
                    while (i < 10) {
                        a(split[i], jSONObject);
                        i++;
                    }
                } else {
                    while (i < split.length) {
                        a(split[i], jSONObject);
                        i++;
                    }
                }
                c(g);
            }
        } catch (Exception unused) {
        }
    }

    private void a(String str, JSONObject jSONObject) throws JSONException {
        String subStr = HelperUtils.subStr(str, 128);
        if (jSONObject.has(subStr)) {
            a(subStr, ((Boolean) jSONObject.get(subStr)).booleanValue());
        } else {
            a(subStr, false);
        }
    }

    private void a(String str, boolean z) {
        try {
            if (!c.Y.equals(str) && !c.W.equals(str) && !"id".equals(str) && !"ts".equals(str) && !this.h.has(str)) {
                this.h.put(str, z);
            }
        } catch (Exception unused) {
        }
    }

    private void c(Context context) {
        try {
            if (this.h != null) {
                PreferenceWrapper.getDefault(g).edit().putString(a, this.h.toString()).commit();
            }
        } catch (Throwable unused) {
        }
    }

    public void a(List<String> list) {
        if (list != null) {
            try {
                if (list.size() > 0) {
                    a();
                    if (this.h == null) {
                        this.h = new JSONObject();
                        int size = list.size();
                        int i = 0;
                        while (true) {
                            if (i >= size) {
                                break;
                            }
                            if (this.h != null) {
                                if (this.h.length() >= 5) {
                                    break;
                                }
                            } else {
                                this.h = new JSONObject();
                            }
                            String str = list.get(i);
                            if (!TextUtils.isEmpty(str)) {
                                a(HelperUtils.subStr(str, 128), false);
                            }
                            i++;
                        }
                        c(g);
                        return;
                    } else if (this.h.length() >= 5) {
                        MLog.d("already setFistLaunchEvent, igone.");
                        return;
                    } else {
                        for (int i2 = 0; i2 < list.size(); i2++) {
                            if (this.h.length() >= 5) {
                                MLog.d(" add setFistLaunchEvent over.");
                                return;
                            } else {
                                a(HelperUtils.subStr(list.get(i2), 128), false);
                            }
                        }
                        c(g);
                        return;
                    }
                }
            } catch (Exception unused) {
                return;
            }
        }
        UMLog.aq(i.ak, 0, "\\|");
    }

    private JSONObject a(Map<String, Object> map) {
        JSONObject jSONObject = new JSONObject();
        try {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                try {
                    String key = entry.getKey();
                    if (key != null) {
                        String subStr = HelperUtils.subStr(key, 128);
                        Object value = entry.getValue();
                        if (value != null) {
                            int i = 0;
                            if (value.getClass().isArray()) {
                                if (value instanceof int[]) {
                                    int[] iArr = (int[]) value;
                                    JSONArray jSONArray = new JSONArray();
                                    while (i < iArr.length) {
                                        jSONArray.put(iArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(subStr, jSONArray);
                                } else if (value instanceof double[]) {
                                    double[] dArr = (double[]) value;
                                    JSONArray jSONArray2 = new JSONArray();
                                    while (i < dArr.length) {
                                        jSONArray2.put(dArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(subStr, jSONArray2);
                                } else if (value instanceof long[]) {
                                    long[] jArr = (long[]) value;
                                    JSONArray jSONArray3 = new JSONArray();
                                    while (i < jArr.length) {
                                        jSONArray3.put(jArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(subStr, jSONArray3);
                                } else if (value instanceof float[]) {
                                    float[] fArr = (float[]) value;
                                    JSONArray jSONArray4 = new JSONArray();
                                    while (i < fArr.length) {
                                        jSONArray4.put((double) fArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(subStr, jSONArray4);
                                } else if (value instanceof short[]) {
                                    short[] sArr = (short[]) value;
                                    JSONArray jSONArray5 = new JSONArray();
                                    while (i < sArr.length) {
                                        jSONArray5.put((int) sArr[i]);
                                        i++;
                                    }
                                    jSONObject.put(subStr, jSONArray5);
                                }
                            } else if (value instanceof List) {
                                List list = (List) value;
                                JSONArray jSONArray6 = new JSONArray();
                                while (i < list.size()) {
                                    Object obj = list.get(i);
                                    if ((obj instanceof String) || (obj instanceof Long) || (obj instanceof Integer) || (obj instanceof Float) || (obj instanceof Double) || (obj instanceof Short)) {
                                        jSONArray6.put(list.get(i));
                                    }
                                    i++;
                                }
                                if (jSONArray6.length() > 0) {
                                    jSONObject.put(subStr, jSONArray6);
                                }
                            } else if (value instanceof String) {
                                jSONObject.put(subStr, HelperUtils.subStr(value.toString(), 256));
                            } else {
                                if (!(value instanceof Long) && !(value instanceof Integer) && !(value instanceof Float) && !(value instanceof Double)) {
                                    if (!(value instanceof Short)) {
                                        MLog.e("The param has not support type. please check !");
                                    }
                                }
                                jSONObject.put(subStr, value);
                            }
                        }
                    }
                } catch (Exception e) {
                    MLog.e(e);
                }
            }
        } catch (Exception unused) {
        }
        return jSONObject;
    }

    private boolean a(String str) {
        if (str != null) {
            try {
                int length = str.trim().getBytes().length;
                if (length > 0 && length <= 128) {
                    return true;
                }
            } catch (Exception unused) {
            }
        }
        MLog.e("key is " + str + ", please check key, illegal");
        return false;
    }

    private boolean b(String str) {
        if (str == null) {
            return true;
        }
        try {
            if (str.trim().getBytes().length <= 256) {
                return true;
            }
        } catch (Exception unused) {
        }
        MLog.e("value is " + str + ", please check value, illegal");
        return false;
    }

    private boolean c(String str) {
        if (str == null) {
            return true;
        }
        try {
            return str.trim().getBytes().length <= 1024;
        } catch (Exception unused) {
            return false;
        }
    }

    private boolean b(Map<String, Object> map) {
        if (map != null) {
            try {
                if (!map.isEmpty()) {
                    for (Map.Entry<String, Object> entry : map.entrySet()) {
                        if (!a(entry.getKey())) {
                            UMLog.aq(i.h, 0, "\\|");
                            return false;
                        } else if (entry.getValue() == null) {
                            UMLog.aq(i.i, 0, "\\|");
                            return false;
                        } else if (entry.getValue() instanceof String) {
                            if (c.aE.equals(entry.getKey())) {
                                if (!c(entry.getValue().toString())) {
                                    UMLog.aq(i.P, 0, "\\|");
                                    return false;
                                }
                            } else if ("_$!url".equals(entry.getKey())) {
                                if (!c(entry.getValue().toString())) {
                                    UMLog.aq("url\u53c2\u6570\u957f\u5ea6\u8d85\u8fc7\u9650\u5236\u3002|\u53c2\u6570url\u957f\u5ea6\u4e0d\u80fd\u8d85\u8fc71024\u5b57\u7b26\u3002", 0, "\\|");
                                    return false;
                                }
                            } else if (!b(entry.getValue().toString())) {
                                UMLog.aq(i.j, 0, "\\|");
                                return false;
                            }
                        }
                    }
                    return true;
                }
            } catch (Exception unused) {
                return true;
            }
        }
        UMLog.aq(i.g, 0, "\\|");
        return false;
    }
}
