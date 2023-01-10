package com.umeng.message.inapp;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.MsgConstant;
import com.umeng.message.UTrack;
import com.umeng.message.common.e;
import com.umeng.message.common.impl.json.JUtrack;
import com.umeng.message.common.inter.ITagManager;
import com.umeng.message.entity.UInAppMessage;
import com.umeng.message.proguard.h;
import java.io.File;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: UmengInAppMessageTracker */
/* access modifiers changed from: package-private */
public class d {
    private static final String a = d.class.getName();
    private static boolean c = false;
    private static d d;
    private Context b;

    private d(Context context) {
        this.b = context.getApplicationContext();
    }

    public static d a(Context context) {
        if (d == null) {
            synchronized (d.class) {
                if (d == null) {
                    d = new d(context.getApplicationContext());
                }
            }
        }
        return d;
    }

    /* compiled from: UmengInAppMessageTracker */
    /* renamed from: com.umeng.message.inapp.d$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ IUmengInAppMessageCallback a;

        AnonymousClass1(IUmengInAppMessageCallback iUmengInAppMessageCallback) {
            this.a = iUmengInAppMessageCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            UInAppMessage uInAppMessage;
            UMLog.mutlInfo(d.a, 2, "get splash message begin");
            try {
                JSONObject sendRequest = JUtrack.sendRequest(d.this.b(), MsgConstant.SPLASH_MSG_ENDPOINT);
                if (sendRequest != null && TextUtils.equals(sendRequest.getString("success"), ITagManager.SUCCESS)) {
                    UMLog.mutlInfo(d.a, 2, "get splash message success" + sendRequest);
                    JSONObject jSONObject = sendRequest.getJSONObject("data");
                    InAppMessageManager.b = jSONObject.getInt("pduration") * 1000;
                    InAppMessageManager.c = jSONObject.getInt("sduration") * 1000;
                    this.a.onSplashMessage(new UInAppMessage(jSONObject.getJSONObject("launch")));
                    InAppMessageManager.getInstance(d.this.b).c();
                } else if (sendRequest == null || !TextUtils.equals(sendRequest.getString("success"), "fail") || !TextUtils.equals(sendRequest.getString("error"), "no message")) {
                    this.a.onSplashMessage(null);
                } else {
                    String e = InAppMessageManager.getInstance(d.this.b).e();
                    if (!TextUtils.isEmpty(e)) {
                        try {
                            uInAppMessage = new UInAppMessage(new JSONObject(e));
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                            uInAppMessage = null;
                        }
                        if (uInAppMessage != null) {
                            InAppMessageManager.getInstance(d.this.b).a(new File(h.d(d.this.b, uInAppMessage.msg_id)));
                            InAppMessageManager.getInstance(d.this.b).a((UInAppMessage) null);
                        }
                    }
                }
            } catch (Exception e3) {
                this.a.onSplashMessage(null);
                e3.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(IUmengInAppMessageCallback iUmengInAppMessageCallback) {
        c();
        e.a(new AnonymousClass1(iUmengInAppMessageCallback));
    }

    /* compiled from: UmengInAppMessageTracker */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.inapp.d$2  reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ IUmengInAppMessageCallback b;

        AnonymousClass2(String str, IUmengInAppMessageCallback iUmengInAppMessageCallback) {
            this.a = str;
            this.b = iUmengInAppMessageCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            UInAppMessage uInAppMessage;
            UMLog.mutlInfo(d.a, 2, "get card message begin");
            try {
                JSONObject b = d.this.b();
                b.put("label", this.a);
                JSONObject sendRequest = JUtrack.sendRequest(b, MsgConstant.CARD_MSG_ENDPOINT);
                if (sendRequest != null && TextUtils.equals(sendRequest.getString("success"), ITagManager.SUCCESS)) {
                    UMLog.mutlInfo(d.a, 2, "get card message success" + sendRequest);
                    JSONObject jSONObject = sendRequest.getJSONObject("data");
                    InAppMessageManager.b = jSONObject.getInt("pduration") * 1000;
                    InAppMessageManager.c = jSONObject.getInt("sduration") * 1000;
                    this.b.onCardMessage(new UInAppMessage(jSONObject.getJSONObject("card")));
                    InAppMessageManager.getInstance(d.this.b).a(b.optString("label", ""));
                } else if (sendRequest == null || !TextUtils.equals(sendRequest.getString("success"), "fail") || !TextUtils.equals(sendRequest.getString("error"), "no message")) {
                    this.b.onCardMessage(null);
                } else {
                    String c = InAppMessageManager.getInstance(d.this.b).c(this.a);
                    if (!TextUtils.isEmpty(c)) {
                        try {
                            uInAppMessage = new UInAppMessage(new JSONObject(c));
                        } catch (JSONException e) {
                            e.printStackTrace();
                            uInAppMessage = null;
                        }
                        if (uInAppMessage != null) {
                            InAppMessageManager.getInstance(d.this.b).a(new File(h.d(d.this.b, uInAppMessage.msg_id)));
                            InAppMessageManager.getInstance(d.this.b).a((UInAppMessage) null, this.a);
                        }
                    }
                }
            } catch (Exception e2) {
                this.b.onCardMessage(null);
                e2.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, IUmengInAppMessageCallback iUmengInAppMessageCallback) {
        c();
        e.a(new AnonymousClass2(str, iUmengInAppMessageCallback));
    }

    /* compiled from: UmengInAppMessageTracker */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.inapp.d$3  reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ int b;
        final /* synthetic */ int c;
        final /* synthetic */ int d;
        final /* synthetic */ int e;
        final /* synthetic */ int f;
        final /* synthetic */ int g;
        final /* synthetic */ int h;
        final /* synthetic */ int i;

        AnonymousClass3(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
            this.a = str;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
            this.f = i5;
            this.g = i6;
            this.h = i7;
            this.i = i8;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                UMLog.mutlInfo(d.a, 2, "track in app msg begin");
                JSONObject b = d.this.b(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i);
                if (b != null && TextUtils.equals(b.getString("success"), ITagManager.SUCCESS)) {
                    UMLog.mutlInfo(d.a, 2, "track in app msg success");
                }
            } catch (Exception e) {
                InAppMessageManager.getInstance(d.this.b).a(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i);
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        e.a(new AnonymousClass3(str, i, i2, i3, i4, i5, i6, i7, i8));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private JSONObject b() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("header", UTrack.getInstance(this.b).getHeader());
        if (InAppMessageManager.a) {
            jSONObject.put(MsgConstant.KEY_INAPP_PMODE, "0");
        } else {
            jSONObject.put(MsgConstant.KEY_INAPP_PMODE, "1");
        }
        return jSONObject;
    }

    private void c() {
        if (c) {
            UMLog.mutlInfo(a, 2, "sendInAppCacheLog\u5df2\u7ecf\u5728\u961f\u5217\u91cc\uff0c\u5ffd\u7565\u8be5\u8bf7\u6c42");
            return;
        }
        c = true;
        UMLog.mutlInfo(a, 2, "sendInAppCacheLog\u5f00\u59cb");
        e.a(new AnonymousClass4());
    }

    /* compiled from: UmengInAppMessageTracker */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.inapp.d$4  reason: invalid class name */
    public class AnonymousClass4 implements Runnable {
        AnonymousClass4() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Iterator<a> it2 = InAppMessageManager.getInstance(d.this.b).j().iterator();
                while (it2.hasNext()) {
                    a next = it2.next();
                    JSONObject b = d.this.b(next.b, next.c, next.d, next.e, next.f, next.g, next.h, next.i, next.j);
                    if (b != null && TextUtils.equals(b.getString("success"), ITagManager.SUCCESS)) {
                        InAppMessageManager.getInstance(d.this.b).h(next.b);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                boolean unused = d.c = false;
                throw th;
            }
            boolean unused2 = d.c = false;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private JSONObject b(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) throws Exception {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("header", UTrack.getInstance(this.b).getHeader());
        jSONObject.put("msg_id", str);
        jSONObject.put("msg_type", i);
        jSONObject.put(MsgConstant.INAPP_NUM_DISPLAY, i2);
        jSONObject.put(MsgConstant.INAPP_NUM_OPEN_FULL, i3);
        jSONObject.put(MsgConstant.INAPP_NUM_OPEN_TOP, i4);
        jSONObject.put(MsgConstant.INAPP_NUM_OPEN_BUTTOM, i5);
        jSONObject.put(MsgConstant.INAPP_NUM_CLOSE, i6);
        jSONObject.put(MsgConstant.INAPP_NUM_DURATION, i7);
        jSONObject.put(MsgConstant.INAPP_NUM_CUSTOM, i8);
        return JUtrack.sendRequest(jSONObject, MsgConstant.STATS_ENDPOINT);
    }
}
