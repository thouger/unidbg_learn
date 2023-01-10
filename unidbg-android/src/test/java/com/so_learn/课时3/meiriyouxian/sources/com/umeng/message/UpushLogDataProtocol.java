package com.umeng.message;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.message.proguard.m;
import com.umeng.message.provider.a;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class UpushLogDataProtocol implements UMLogDataProtocol {
    private static final String a = UpushLogDataProtocol.class.getSimpleName();
    private Context b;

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public JSONObject setupReportData(long j) {
        return null;
    }

    public UpushLogDataProtocol(Context context) {
        this.b = context;
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public void workEvent(Object obj, int i) {
        switch (i) {
            case 16385:
                e(obj, i);
                return;
            case 16386:
                d(obj, i);
                return;
            case 16387:
                c(obj, i);
                return;
            case 16388:
                b(obj, i);
                return;
            case 16389:
                a(obj, i);
                return;
            default:
                return;
        }
    }

    private void a(Object obj, int i) {
        JSONObject buildEnvelopeWithExtHeader;
        if (obj != null) {
            try {
                JSONObject jSONObject = new JSONObject(obj.toString());
                JSONObject optJSONObject = jSONObject.optJSONObject("header");
                JSONObject jSONObject2 = jSONObject.getJSONObject("content");
                if (this.b != null && optJSONObject != null && jSONObject2 != null && (buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(this.b, optJSONObject, jSONObject2, MsgConstant.UNPX_PUSH_LOGS, "p", MsgConstant.SDK_VERSION)) != null && !buildEnvelopeWithExtHeader.has("exception")) {
                    UTrack.removeCacheLog(jSONObject2.getJSONArray("push"));
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private void b(Object obj, int i) {
        JSONObject buildEnvelopeWithExtHeader;
        if (obj != null) {
            try {
                JSONObject jSONObject = new JSONObject(obj.toString());
                JSONObject optJSONObject = jSONObject.optJSONObject("jsonHeader");
                JSONObject optJSONObject2 = jSONObject.optJSONObject("jsonBody");
                String optString = jSONObject.optString("umpxPath");
                if (this.b != null && optJSONObject != null && optJSONObject2 != null && (buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(this.b, optJSONObject, optJSONObject2, optString, "p", MsgConstant.SDK_VERSION)) != null && !buildEnvelopeWithExtHeader.has("exception")) {
                    UmengMessageCallbackHandlerService.removeCacheLog(optJSONObject2.getJSONArray("push"));
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private void c(Object obj, int i) {
        JSONObject buildEnvelopeWithExtHeader;
        if (obj != null) {
            try {
                JSONObject jSONObject = new JSONObject(obj.toString());
                JSONObject optJSONObject = jSONObject.optJSONObject("header");
                JSONObject optJSONObject2 = jSONObject.optJSONObject("content");
                if (!(this.b == null || optJSONObject == null || optJSONObject2 == null || (buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(this.b, optJSONObject, optJSONObject2, MsgConstant.UMPX_PUSH_REGISTER, "p", MsgConstant.SDK_VERSION)) == null || buildEnvelopeWithExtHeader.has("exception"))) {
                    MessageSharedPrefs.getInstance(this.b).setHasResgister(true);
                    if (TextUtils.isEmpty(MessageSharedPrefs.getInstance(this.b).getDeviceToken())) {
                        UMLog.mutlInfo(a, 0, "setRegisteredToUmeng: device token\u4e3a\u7a7a");
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        UTrack.registerSending = false;
    }

    private void d(Object obj, int i) {
        JSONObject buildEnvelopeWithExtHeader;
        if (obj != null) {
            try {
                JSONObject jSONObject = new JSONObject(obj.toString());
                JSONObject optJSONObject = jSONObject.optJSONObject("header");
                JSONObject optJSONObject2 = jSONObject.optJSONObject("content");
                if (!(this.b == null || optJSONObject == null || optJSONObject2 == null || (buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(this.b, optJSONObject, optJSONObject2, MsgConstant.UMPX_PUSH_LAUNCH, "p", MsgConstant.SDK_VERSION)) == null || buildEnvelopeWithExtHeader.has("exception"))) {
                    m.a(this.b).a(System.currentTimeMillis());
                    int parseInt = Integer.parseInt(UMEnvelopeBuild.imprintProperty(this.b, "launch_policy", "-1"));
                    String str = a;
                    UMLog.mutlInfo(str, 2, "launch_policy:" + parseInt);
                    int parseInt2 = Integer.parseInt(UMEnvelopeBuild.imprintProperty(this.b, "tag_policy", "-1"));
                    String str2 = a;
                    UMLog.mutlInfo(str2, 2, "tag_policy:" + parseInt2);
                    if (parseInt > 0) {
                        MessageSharedPrefs.getInstance(this.b).setAppLaunchLogSendPolicy(parseInt);
                    }
                    if (parseInt2 > 0) {
                        MessageSharedPrefs.getInstance(this.b).setTagSendPolicy(parseInt2);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        UTrack.appLaunchSending = false;
    }

    private void e(Object obj, int i) {
        JSONObject buildEnvelopeWithExtHeader;
        if (obj != null) {
            try {
                JSONObject jSONObject = new JSONObject(obj.toString());
                JSONObject optJSONObject = jSONObject.optJSONObject("content");
                JSONArray optJSONArray = optJSONObject.optJSONArray("push");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    JSONObject jSONObject2 = (JSONObject) optJSONArray.get(0);
                    m.a(this.b).a(jSONObject2.getString("msg_id"), jSONObject2.getInt(MsgConstant.KEY_ACTION_TYPE), jSONObject2.getLong("ts"), jSONObject2.getString("pa"));
                }
                JSONObject optJSONObject2 = jSONObject.optJSONObject("header");
                if (this.b != null && optJSONObject2 != null && optJSONObject != null && (buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(this.b, optJSONObject2, optJSONObject, MsgConstant.UNPX_PUSH_LOGS, "p", MsgConstant.SDK_VERSION)) != null && !buildEnvelopeWithExtHeader.has("exception")) {
                    removeCacheData(jSONObject);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    @Override // com.umeng.commonsdk.framework.UMLogDataProtocol
    public void removeCacheData(Object obj) {
        if (obj != null) {
            try {
                JSONObject optJSONObject = new JSONObject(obj.toString()).optJSONObject("content");
                if (optJSONObject != null) {
                    ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
                    JSONArray optJSONArray = optJSONObject.optJSONArray("push");
                    if (optJSONArray != null && optJSONArray.length() > 0) {
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            JSONObject jSONObject = (JSONObject) optJSONArray.get(i);
                            String optString = jSONObject.optString("msg_id");
                            int optInt = jSONObject.optInt(MsgConstant.KEY_ACTION_TYPE);
                            arrayList.add(ContentProviderOperation.newDelete(a.a(this.b).f).withSelection("MsgId=? And ActionType=?", new String[]{optString, optInt + ""}).build());
                            if (optInt != 0) {
                                arrayList.add(ContentProviderOperation.newDelete(a.a(this.b).g).withSelection("MsgId=?", new String[]{optString}).build());
                            }
                        }
                    }
                    this.b.getContentResolver().applyBatch(a.a(this.b).a, arrayList);
                }
            } catch (Throwable th) {
                th.printStackTrace();
                th.getMessage();
            }
        }
    }
}
