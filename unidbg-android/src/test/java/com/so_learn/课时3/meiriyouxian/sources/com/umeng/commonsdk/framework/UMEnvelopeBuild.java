package com.umeng.commonsdk.framework;

import android.content.ContentValues;
import android.content.Context;
import android.text.TextUtils;
import com.umeng.analytics.pro.ai;
import com.umeng.analytics.pro.ak;
import com.umeng.analytics.pro.am;
import com.umeng.analytics.pro.h;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.statistics.UMServerURL;
import com.umeng.commonsdk.statistics.b;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

public class UMEnvelopeBuild {
    public static boolean transmissionSendFlag;

    public static boolean isOnline(Context context) {
        boolean z = !UMConfigure.needSendZcfgEnv(context);
        if (!UMFrUtils.isOnline(context) || !z) {
            return false;
        }
        return true;
    }

    public static long getLastSuccessfulBuildTime(Context context) {
        if (context == null) {
            return 0;
        }
        return UMFrUtils.getLastSuccessfulBuildTime(context.getApplicationContext());
    }

    public static long getLastInstantBuildTime(Context context) {
        if (context == null) {
            return 0;
        }
        return UMFrUtils.getLastInstantBuildTime(context.getApplicationContext());
    }

    public static boolean isReadyBuildNew(Context context, UMLogDataProtocol.UMBusinessType uMBusinessType) {
        if (!getTransmissionSendFlag()) {
            return false;
        }
        return isRet(context, uMBusinessType, false);
    }

    public static boolean isReadyBuild(Context context, UMLogDataProtocol.UMBusinessType uMBusinessType) {
        return isRet(context, uMBusinessType, false);
    }

    public static boolean isReadyBuildStateless() {
        return getTransmissionSendFlag();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0039, code lost:
        if (com.umeng.commonsdk.UMConfigure.needSendZcfgEnv(r5) == false) goto L_0x001d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001a, code lost:
        if (com.umeng.commonsdk.framework.UMFrUtils.hasEnvelopeFile(r0, r6) != false) goto L_0x002d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean isRet(android.content.Context r5, com.umeng.commonsdk.framework.UMLogDataProtocol.UMBusinessType r6, boolean r7) {
        /*
            if (r5 == 0) goto L_0x0043
            android.content.Context r0 = r5.getApplicationContext()
            boolean r1 = com.umeng.commonsdk.framework.UMFrUtils.isOnline(r0)
            int r2 = com.umeng.commonsdk.framework.UMFrUtils.envelopeFileNumber(r0)
            r3 = 1
            r4 = 0
            if (r1 == 0) goto L_0x003c
            com.umeng.commonsdk.framework.UMLogDataProtocol$UMBusinessType r7 = com.umeng.commonsdk.framework.UMLogDataProtocol.UMBusinessType.U_INTERNAL
            if (r6 != r7) goto L_0x001f
            boolean r5 = com.umeng.commonsdk.framework.UMFrUtils.hasEnvelopeFile(r0, r6)
            if (r5 == 0) goto L_0x001d
            goto L_0x002d
        L_0x001d:
            r7 = r3
            goto L_0x003c
        L_0x001f:
            boolean r7 = com.umeng.commonsdk.framework.a.a()
            if (r7 == 0) goto L_0x002f
            int r5 = com.umeng.commonsdk.framework.a.b()
            long r5 = (long) r5
            com.umeng.commonsdk.framework.UMWorkDispatch.sendDelayProcessMsg(r5)
        L_0x002d:
            r7 = r4
            goto L_0x003c
        L_0x002f:
            boolean r6 = com.umeng.commonsdk.framework.UMFrUtils.hasEnvelopeFile(r0, r6)
            if (r6 != 0) goto L_0x002d
            boolean r5 = com.umeng.commonsdk.UMConfigure.needSendZcfgEnv(r5)
            if (r5 == 0) goto L_0x001d
            goto L_0x002d
        L_0x003c:
            if (r1 == 0) goto L_0x0043
            if (r2 <= 0) goto L_0x0043
            com.umeng.commonsdk.framework.a.d()
        L_0x0043:
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.framework.UMEnvelopeBuild.isRet(android.content.Context, com.umeng.commonsdk.framework.UMLogDataProtocol$UMBusinessType, boolean):boolean");
    }

    private static JSONObject add2CacheTable(Context context, JSONObject jSONObject, JSONObject jSONObject2, String str, String str2, String str3) {
        if (jSONObject == null || jSONObject2 == null) {
            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> [\u6709\u72b6\u6001]\u6784\u5efa\u4fe1\u5c01\u4f20\u5165 header \u6216 body \u5b57\u6bb5\u4e3a\u7a7a\uff0c\u76f4\u63a5\u8fd4\u56de");
            return null;
        }
        h a = h.a(context);
        long currentTimeMillis = System.currentTimeMillis();
        UUID randomUUID = UUID.randomUUID();
        ContentValues contentValues = new ContentValues();
        contentValues.put(am.e, str2);
        contentValues.put(am.f, a.c(jSONObject.toString()));
        contentValues.put(am.g, a.c(jSONObject2.toString()));
        contentValues.put(am.h, String.valueOf(currentTimeMillis));
        contentValues.put(am.i, randomUUID.toString());
        contentValues.put(am.j, str);
        contentValues.put(am.k, str3);
        ak.a(context).a(am.c, contentValues);
        if ("i".equalsIgnoreCase(str2)) {
            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> [\u6709\u72b6\u6001]inner\u4e1a\u52a1\uff0c\u8fd4\u56de\u7a7a JSONObject\u3002");
        } else if (ai.az.equalsIgnoreCase(str2)) {
            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> [\u6709\u72b6\u6001]\u5206\u4eab\u4e1a\u52a1 \u8fd4\u56debody\u3002");
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put("header", new JSONObject());
                jSONObject3.put("share", jSONObject2.getJSONObject("share"));
                return jSONObject3;
            } catch (JSONException unused) {
            }
        } else if (!"p".equalsIgnoreCase(str2)) {
            if ("st".equalsIgnoreCase(str2)) {
                UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> [\u6709\u72b6\u6001]\u7edf\u8ba1\u4e1a\u52a1 \u534a\u5f00\u62a5\u6587\uff0c\u8fd4\u56debody\u3002");
                JSONObject jSONObject4 = new JSONObject();
                try {
                    jSONObject4.put("header", new JSONObject());
                    jSONObject4.put("analytics", jSONObject2.getJSONObject("analytics"));
                    return jSONObject4;
                } catch (JSONException unused2) {
                    return jSONObject2;
                }
            } else {
                UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> [\u6709\u72b6\u6001]\u7edf\u8ba1\u4e1a\u52a1 \u95ed\u5408\u62a5\u6587\uff0c\u8fd4\u56debody\u3002");
                JSONObject jSONObject5 = new JSONObject();
                jSONObject5.put("header", new JSONObject());
                jSONObject5.put("analytics", jSONObject2.getJSONObject("analytics"));
                return jSONObject5;
            }
        }
        return new JSONObject();
    }

    public static JSONObject buildEnvelopeWithExtHeader(Context context, JSONObject jSONObject, JSONObject jSONObject2) {
        String str;
        if (jSONObject.has("st")) {
            str = "t";
        } else {
            str = jSONObject2.has(ai.as) ? "i" : "a";
        }
        return buildEnvelopeWithExtHeader(context, jSONObject, jSONObject2, UMServerURL.PATH_ANALYTICS, str, "9.3.8");
    }

    public static JSONObject buildEnvelopeWithExtHeader(Context context, JSONObject jSONObject, JSONObject jSONObject2, String str, String str2, String str3) {
        UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> [\u6709\u72b6\u6001]\u4e1a\u52a1\u53d1\u8d77\u6784\u5efa\u666e\u901a\u6709\u72b6\u6001\u4fe1\u5c01\u8bf7\u6c42\u3002");
        if (TextUtils.isEmpty(str)) {
            try {
                JSONObject jSONObject3 = new JSONObject();
                try {
                    jSONObject3.put("exception", 121);
                    return jSONObject3;
                } catch (JSONException unused) {
                    return jSONObject3;
                }
            } catch (JSONException unused2) {
                return null;
            }
        } else if (!UMUtils.isMainProgress(context)) {
            try {
                JSONObject jSONObject4 = new JSONObject();
                try {
                    jSONObject4.put("exception", 120);
                    return jSONObject4;
                } catch (JSONException unused3) {
                    return jSONObject4;
                }
            } catch (JSONException unused4) {
                return null;
            }
        } else if (UMConfigure.needSendZcfgEnv(context)) {
            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> [\u6709\u72b6\u6001]\u96f6\u53f7\u62a5\u6587\u5e94\u7b54\u6570\u636e \u672a\u83b7\u53d6\u5230\uff0c\u5199\u5165\u4e8c\u7ea7\u7f13\u5b58");
            return add2CacheTable(context, jSONObject, jSONObject2, str, str2, str3);
        } else {
            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> [\u6709\u72b6\u6001]\u96f6\u53f7\u62a5\u6587\u5e94\u7b54\u6570\u636e \u5df2\u83b7\u53d6\u5230\uff0c\u5224\u65ad\u4e8c\u7ea7\u7f13\u5b58\u662f\u5426\u4e3a\u7a7a");
            if (ak.a(context).c()) {
                UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> [\u6709\u72b6\u6001]\u4e8c\u7ea7\u7f13\u5b58\u4e3a\u7a7a\uff0c\u76f4\u63a5\u6253\u4fe1\u5c01");
                return new b().a(context.getApplicationContext(), jSONObject, jSONObject2, str, str2, str3);
            }
            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> [\u6709\u72b6\u6001]\u4e8c\u7ea7\u7f13\u5b58\u4e0d\u4e3a\u7a7a\uff0c\u5199\u5165\u4e8c\u7ea7\u7f13\u5b58");
            JSONObject add2CacheTable = add2CacheTable(context, jSONObject, jSONObject2, str, str2, str3);
            if (!UMWorkDispatch.eventHasExist(32785)) {
                UMWorkDispatch.sendEvent(context, 32785, com.umeng.commonsdk.internal.b.a(context).a(), null);
            }
            return add2CacheTable;
        }
    }

    public static JSONObject buildZeroEnvelopeWithExtHeader(Context context, JSONObject jSONObject, JSONObject jSONObject2, String str) {
        if (UMUtils.isMainProgress(context)) {
            return new b().a(context.getApplicationContext(), jSONObject, jSONObject2, str);
        }
        try {
            JSONObject jSONObject3 = new JSONObject();
            try {
                jSONObject3.put("exception", 120);
                return jSONObject3;
            } catch (JSONException unused) {
                return jSONObject3;
            }
        } catch (JSONException unused2) {
            return null;
        }
    }

    public static String imprintProperty(Context context, String str, String str2) {
        return context == null ? str2 : ImprintHandler.getImprintService(context.getApplicationContext()).c().a(str, str2);
    }

    public static long maxDataSpace(Context context) {
        if (context == null) {
            return 0;
        }
        return b.a(context.getApplicationContext());
    }

    public static synchronized boolean getTransmissionSendFlag() {
        boolean z;
        synchronized (UMEnvelopeBuild.class) {
            z = transmissionSendFlag;
        }
        return z;
    }

    public static synchronized void setTransmissionSendFlag(boolean z) {
        synchronized (UMEnvelopeBuild.class) {
            transmissionSendFlag = z;
        }
    }
}
