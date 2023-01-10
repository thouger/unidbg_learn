package com.sina.weibo.sdk.statistic;

import android.text.TextUtils;
import com.huawei.hms.support.hianalytics.HiAnalyticsConstant;
import com.sina.weibo.sdk.a.d;
import com.xiaomi.mipush.sdk.Constants;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

/* compiled from: LogBuilder */
/* access modifiers changed from: package-private */
public class b {
    private static /* synthetic */ int[] a;

    static /* synthetic */ int[] a() {
        int[] iArr = a;
        if (iArr != null) {
            return iArr;
        }
        int[] iArr2 = new int[LogType.values().length];
        try {
            iArr2[LogType.ACTIVITY.ordinal()] = 5;
        } catch (NoSuchFieldError unused) {
        }
        try {
            iArr2[LogType.EVENT.ordinal()] = 4;
        } catch (NoSuchFieldError unused2) {
        }
        try {
            iArr2[LogType.FRAGMENT.ordinal()] = 3;
        } catch (NoSuchFieldError unused3) {
        }
        try {
            iArr2[LogType.SESSION_END.ordinal()] = 2;
        } catch (NoSuchFieldError unused4) {
        }
        try {
            iArr2[LogType.SESSION_START.ordinal()] = 1;
        } catch (NoSuchFieldError unused5) {
        }
        a = iArr2;
        return iArr2;
    }

    public static String a(List<e> list) {
        StringBuilder sb = new StringBuilder();
        for (e eVar : list) {
            sb.append(a(eVar).toString());
            sb.append(Constants.ACCEPT_TIME_SEPARATOR_SP);
        }
        return sb.toString();
    }

    private static JSONObject a(e eVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            int i = a()[eVar.c().ordinal()];
            if (i == 1) {
                jSONObject.put("type", 0);
                jSONObject.put("time", eVar.e() / 1000);
            } else if (i == 2) {
                jSONObject.put("type", 1);
                jSONObject.put("time", eVar.f() / 1000);
                jSONObject.put("duration", eVar.g() / 1000);
            } else if (i == 3) {
                jSONObject.put("type", 2);
                jSONObject.put("page_id", eVar.d());
                jSONObject.put("time", eVar.e() / 1000);
                jSONObject.put("duration", eVar.g() / 1000);
            } else if (i == 4) {
                jSONObject.put("type", 3);
                jSONObject.put("page_id", eVar.d());
                jSONObject.put("time", eVar.e() / 1000);
                a(jSONObject, (a) eVar);
            } else if (i == 5) {
                jSONObject.put("type", 4);
                jSONObject.put("page_id", eVar.d());
                jSONObject.put("time", eVar.e() / 1000);
                jSONObject.put("duration", eVar.g() / 1000);
            }
        } catch (Exception e) {
            d.c("WBAgent", "get page log error." + e);
        }
        return jSONObject;
    }

    private static JSONObject a(JSONObject jSONObject, a aVar) {
        try {
            jSONObject.put("event_id", aVar.a());
            if (aVar.b() != null) {
                Map<String, String> b = aVar.b();
                StringBuilder sb = new StringBuilder();
                int i = 0;
                Iterator<String> it2 = b.keySet().iterator();
                while (true) {
                    if (it2.hasNext()) {
                        String next = it2.next();
                        if (i >= 10) {
                            break;
                        } else if (!TextUtils.isEmpty(b.get(next))) {
                            if (sb.length() > 0) {
                                sb.append(HiAnalyticsConstant.REPORT_VAL_SEPARATOR);
                            }
                            sb.append(next);
                            sb.append(":");
                            sb.append(b.get(next));
                            i++;
                        }
                    } else {
                        break;
                    }
                }
                jSONObject.put("extend", sb.toString());
            }
        } catch (Exception e) {
            d.c("WBAgent", "add event log error." + e);
        }
        return jSONObject;
    }
}
