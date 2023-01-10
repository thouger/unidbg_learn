package com.umeng.message;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.text.format.DateUtils;
import com.taobao.agoo.TaobaoRegister;
import com.umeng.analytics.pro.ai;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.common.c;
import com.umeng.message.common.e;
import com.umeng.message.common.impl.json.JUtrack;
import com.umeng.message.common.inter.IUtrack;
import com.umeng.message.entity.UMessage;
import com.umeng.message.proguard.h;
import com.umeng.message.proguard.m;
import com.umeng.message.provider.a;
import com.umeng.message.service.UMJobIntentService;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UTrack {
    private static final String a = UTrack.class.getName();
    public static boolean appLaunchSending = false;
    private static UTrack d;
    private static IUtrack e;
    private static Context f;
    private static boolean i = false;
    public static boolean registerSending = false;
    private JSONObject b;
    private JSONObject c;
    private boolean g;
    private final String h = "appkey";

    public interface ICallBack {
        void onMessage(boolean z, String str);
    }

    enum SuccessState {
        SUCCESS_CACHE,
        SUCCESS,
        FAIL_REQUEST,
        FAIL_PARAM
    }

    public void setClearPrevMessage(boolean z) {
        this.g = z;
    }

    private UTrack(Context context) {
        f = context.getApplicationContext();
        e();
    }

    public static synchronized UTrack getInstance(Context context) {
        UTrack uTrack;
        synchronized (UTrack.class) {
            if (d == null) {
                d = new UTrack(context);
                e = new JUtrack(context);
            }
            uTrack = d;
        }
        return uTrack;
    }

    public void trackMsgArrival(UMessage uMessage) {
        if (uMessage != null && uMessage.msg_id != null) {
            c(uMessage.msg_id, 0, DateUtils.MINUTE_IN_MILLIS * uMessage.random_min, uMessage.pulledWho);
        }
    }

    public void trackMsgClick(UMessage uMessage) {
        if (!(uMessage == null || uMessage.msg_id == null)) {
            c(uMessage.msg_id, 1, DateUtils.MINUTE_IN_MILLIS * uMessage.random_min, uMessage.pulledWho);
        }
        if (!(uMessage == null || uMessage.message_id == null)) {
            a(uMessage.message_id, uMessage.task_id, "8");
        }
        if (!this.g) {
            return;
        }
        if (uMessage == null || !UMessage.DISPLAY_TYPE_NOTIFICATIONPULLAPP.equals(uMessage.display_type)) {
            ((UmengMessageHandler) PushAgent.getInstance(f).getMessageHandler()).setPrevMessage(null);
        } else {
            ((UmengAdHandler) PushAgent.getInstance(f).getAdHandler()).setPrevMessage(null);
        }
    }

    public void trackMiPushMsgClick(UMessage uMessage) {
        if (!(uMessage == null || uMessage.msg_id == null)) {
            c(uMessage.msg_id, 21, DateUtils.MINUTE_IN_MILLIS * uMessage.random_min, uMessage.pulledWho);
        }
        if (!this.g) {
            return;
        }
        if (uMessage == null || !UMessage.DISPLAY_TYPE_NOTIFICATIONPULLAPP.equals(uMessage.display_type)) {
            ((UmengMessageHandler) PushAgent.getInstance(f).getMessageHandler()).setPrevMessage(null);
        } else {
            ((UmengAdHandler) PushAgent.getInstance(f).getAdHandler()).setPrevMessage(null);
        }
    }

    public void trackMsgDismissed(UMessage uMessage) {
        if (!(uMessage == null || uMessage.msg_id == null)) {
            c(uMessage.msg_id, 2, DateUtils.MINUTE_IN_MILLIS * uMessage.random_min, uMessage.pulledWho);
        }
        if (!(uMessage == null || uMessage.message_id == null)) {
            a(uMessage.message_id, uMessage.task_id, "9");
        }
        if (uMessage == null || !UMessage.DISPLAY_TYPE_NOTIFICATIONPULLAPP.equals(uMessage.display_type)) {
            ((UmengMessageHandler) PushAgent.getInstance(f).getMessageHandler()).setPrevMessage(null);
        } else {
            ((UmengAdHandler) PushAgent.getInstance(f).getAdHandler()).setPrevMessage(null);
        }
    }

    /* access modifiers changed from: package-private */
    public void a(UMessage uMessage) {
        c(uMessage.recall, 4, uMessage.random_min * DateUtils.MINUTE_IN_MILLIS, uMessage.pulledWho);
    }

    /* access modifiers changed from: package-private */
    public void b(UMessage uMessage) {
        c(uMessage.recall, 5, uMessage.random_min * DateUtils.MINUTE_IN_MILLIS, uMessage.pulledWho);
    }

    public void trackMsgPulled(UMessage uMessage, int i2) {
        if (uMessage != null && uMessage.msg_id != null) {
            c(uMessage.msg_id, i2, uMessage.random_min * DateUtils.MINUTE_IN_MILLIS, uMessage.pulledWho);
        }
    }

    private void a(String str, int i2, long j, String str2) {
        if (f()) {
            if (TextUtils.isEmpty(str)) {
                UMLog.mutlInfo(a, 0, "trackMsgLog: msgId\u4e3a\u7a7a");
                return;
            }
            long currentTimeMillis = System.currentTimeMillis();
            try {
                m.a(f).a(str, i2, currentTimeMillis, str2);
            } catch (Exception e2) {
                e2.printStackTrace();
                UMLog.mutlInfo(a, 2, "trackMsgLog: ", e2.toString());
            }
            AnonymousClass1 r0 = new AnonymousClass1(str, i2, currentTimeMillis, str2);
            long j2 = 0;
            if (!(j <= 0 || i2 == 1 || i2 == 21)) {
                j2 = Math.abs(new Random().nextLong() % j);
            }
            UMLog.mutlInfo(a, 2, String.format("trackMsgLog(msgId=%s, actionType=%d, random=%d, delay=%d)", str, Integer.valueOf(i2), Long.valueOf(j), Long.valueOf(j2)));
            e.a(r0, j2, TimeUnit.MILLISECONDS);
        }
    }

    /* renamed from: com.umeng.message.UTrack$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ int b;
        final /* synthetic */ long c;
        final /* synthetic */ String d;

        AnonymousClass1(String str, int i, long j, String str2) {
            this.a = str;
            this.b = i;
            this.c = j;
            this.d = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            UTrack.this.d(this.a, this.b, this.c, this.d);
        }
    }

    private void b(String str, int i2, long j, String str2) {
        if (f()) {
            if (TextUtils.isEmpty(str)) {
                UMLog.mutlInfo(a, 0, "trackMsgLog: msgId\u4e3a\u7a7a");
            } else {
                d(str, i2, System.currentTimeMillis(), str2);
            }
        }
    }

    private void c(String str, int i2, long j, String str2) {
        b(str, i2, j, str2);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.UTrack$5  reason: invalid class name */
    public class AnonymousClass5 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ String c;

        AnonymousClass5(String str, String str2, String str3) {
            this.a = str;
            this.b = str2;
            this.c = str3;
        }

        @Override // java.lang.Runnable
        public void run() {
            UTrack.this.sendMsgLogForAgoo(this.a, this.b, this.c);
        }
    }

    private void a(String str, String str2, String str3) {
        e.a(new AnonymousClass5(str, str2, str3));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private synchronized void d(String str, int i2, long j, String str2) {
        if (str2 == null) {
            str2 = "";
        }
        try {
            JSONObject i3 = i();
            i3.put("msg_id", str);
            i3.put(MsgConstant.KEY_ACTION_TYPE, i2);
            i3.put("ts", j);
            i3.put("pa", str2);
            e.sendMsgLog(i3, str, i2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public synchronized void sendMsgLogForAgoo(String str, String str2, String str3) {
        String str4 = a;
        UMLog.mutlInfo(str4, 2, "sendMsgLogForAgoo-->msgId:" + str + ",taskId:" + str2);
        if (str3.equalsIgnoreCase("8")) {
            TaobaoRegister.clickMessage(f, str, str2);
        } else {
            TaobaoRegister.dismissMessage(f, str, str2);
        }
        m.a(f).b(str, str3);
        if (!str3.equals("7")) {
            m.a(f).d(str);
        }
    }

    private void a(long j) {
        if (f()) {
            if (i) {
                UMLog.mutlInfo(a, 2, "appCachedPushlog\u5df2\u7ecf\u5728\u961f\u5217\u91cc, \u5ffd\u7565\u8fd9\u6b21\u8bf7\u6c42");
                return;
            }
            UMLog.mutlInfo(a, 2, "appCachedPushlog\u5f00\u59cb, \u8bbe\u7f6eappLaunchSending\u6807\u5fd7\u4f4d");
            i = true;
            if (h.d(f)) {
                new Thread(new AnonymousClass6()).start();
            }
            e.a(new AnonymousClass7());
            e.a(new AnonymousClass8());
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.UTrack$6  reason: invalid class name */
    public class AnonymousClass6 implements Runnable {
        AnonymousClass6() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                Thread.sleep(30000);
                boolean unused = UTrack.i = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.UTrack$7  reason: invalid class name */
    public class AnonymousClass7 implements Runnable {
        AnonymousClass7() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                JSONArray jSONArray = new JSONArray();
                ArrayList<m.a> a = m.a(UTrack.f).a();
                if (a != null && a.size() > 0) {
                    for (int i = 0; i < a.size(); i++) {
                        m.a aVar = a.get(i);
                        JSONObject jSONObject = new JSONObject();
                        jSONObject.put("ts", aVar.b);
                        jSONObject.put("pa", aVar.d);
                        jSONObject.put("device_token", MessageSharedPrefs.getInstance(UTrack.f).getDeviceToken());
                        jSONObject.put("msg_id", aVar.a);
                        jSONObject.put(MsgConstant.KEY_ACTION_TYPE, aVar.c);
                        jSONArray.put(jSONObject);
                    }
                    JSONObject jSONObject2 = new JSONObject();
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put(c.c, UmengMessageDeviceConfig.getDINAes(UTrack.f));
                    jSONObject3.put(ai.aI, MsgConstant.SDK_VERSION);
                    jSONObject3.put("umid", UmengMessageDeviceConfig.getUmid(UTrack.f));
                    jSONObject2.put("header", jSONObject3);
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("push", jSONArray);
                    jSONObject2.put("content", jSONObject4);
                    if (h.d(UTrack.f)) {
                        UMWorkDispatch.sendEvent(UTrack.f, 16389, PushAgent.getInstance(UTrack.f).getUpushLogDataProtocol(), jSONObject2.toString());
                        return;
                    }
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put("jsonHeader", jSONObject3);
                    jSONObject5.put("jsonBody", jSONObject4);
                    Intent intent = new Intent();
                    intent.setPackage(UTrack.f.getPackageName());
                    intent.setAction(MsgConstant.MESSAGE_MESSAGE_SEND_ACTION);
                    intent.putExtra(MsgConstant.KEY_UMPX_PATH, MsgConstant.UNPX_PUSH_LOGS);
                    intent.putExtra(MsgConstant.KEY_SENDMESSAGE, jSONObject5.toString());
                    UMJobIntentService.enqueueWork(UTrack.f, UmengMessageCallbackHandlerService.class, intent);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.UTrack$8  reason: invalid class name */
    public class AnonymousClass8 implements Runnable {
        AnonymousClass8() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                ArrayList<m.b> c = m.a(UTrack.f).c();
                for (int i = 0; i < c.size(); i++) {
                    m.b bVar = c.get(i);
                    UTrack.this.sendMsgLogForAgoo(bVar.a, bVar.b, bVar.c);
                }
            } catch (Throwable th) {
                th.printStackTrace();
                UMLog.mutlInfo(UTrack.a, 2, th.toString());
            }
        }
    }

    public static void removeCacheLog(JSONArray jSONArray) {
        if (jSONArray != null) {
            try {
                ArrayList<ContentProviderOperation> arrayList = new ArrayList<>();
                if (jSONArray.length() > 0) {
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        JSONObject jSONObject = (JSONObject) jSONArray.get(i2);
                        String optString = jSONObject.optString("msg_id");
                        int optInt = jSONObject.optInt(MsgConstant.KEY_ACTION_TYPE);
                        arrayList.add(ContentProviderOperation.newDelete(a.a(f).f).withSelection("MsgId=? And ActionType=?", new String[]{optString, optInt + ""}).build());
                        if (optInt != 0) {
                            arrayList.add(ContentProviderOperation.newDelete(a.a(f).g).withSelection("MsgId=?", new String[]{optString}).build());
                        }
                    }
                }
                f.getContentResolver().applyBatch(a.a(f).a, arrayList);
            } catch (Exception unused) {
            }
        }
    }

    public void sendCachedMsgLog(long j) {
        a(j);
    }

    public void trackAppLaunch(long j) {
        if (f()) {
            if (MessageSharedPrefs.getInstance(f).getAppLaunchLogSendPolicy() == 1) {
                UMLog.mutlInfo(a, 2, "launch_policy=1, \u8df3\u8fc7\u53d1\u9001\u5e94\u7528\u7a0b\u5e8f\u542f\u52a8\u4fe1\u606f");
            } else if (!MessageSharedPrefs.getInstance(f).hasAppLaunchLogSentToday()) {
                b(j);
            }
        }
    }

    private void b(long j) {
        if (appLaunchSending) {
            UMLog.mutlInfo(a, 2, "trackAppLaunch\u5df2\u7ecf\u5728\u961f\u5217\u91cc, \u5ffd\u7565\u8fd9\u6b21\u8bf7\u6c42");
            return;
        }
        UMLog.mutlInfo(a, 2, "trackAppLaunch\u5f00\u59cb, \u8bbe\u7f6eappLaunchSending\u6807\u5fd7\u4f4d");
        appLaunchSending = true;
        AnonymousClass9 r2 = new AnonymousClass9();
        UMLog.mutlInfo(a, 2, String.format("trackAppLaunch(delay=%d)", Long.valueOf(j)));
        e.a(r2, j, TimeUnit.MILLISECONDS);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.UTrack$9  reason: invalid class name */
    public class AnonymousClass9 implements Runnable {
        AnonymousClass9() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                JSONObject h = UTrack.this.h();
                JSONArray d = UTrack.this.d();
                if (d != null) {
                    h.put(MsgConstant.KEY_UCODE, com.umeng.message.proguard.c.a(d.toString()));
                }
                UTrack.e.trackAppLaunch(h);
            } catch (Exception unused) {
                UTrack.appLaunchSending = false;
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x002d A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x002e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private org.json.JSONArray d() {
        /*
            r6 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "["
            r0.append(r1)
            android.content.Context r1 = com.umeng.message.UTrack.f
            com.umeng.message.MessageSharedPrefs r1 = com.umeng.message.MessageSharedPrefs.getInstance(r1)
            java.lang.String r1 = r1.getUcode()
            r2 = 0
            if (r1 == 0) goto L_0x002a
            java.lang.String r3 = ""
            boolean r3 = r1.equals(r3)
            if (r3 != 0) goto L_0x002a
            java.util.List r1 = com.umeng.message.proguard.h.g(r1)     // Catch:{ Exception -> 0x0026 }
            goto L_0x002b
        L_0x0026:
            r1 = move-exception
            r1.printStackTrace()
        L_0x002a:
            r1 = r2
        L_0x002b:
            if (r1 != 0) goto L_0x002e
            return r2
        L_0x002e:
            r3 = 0
        L_0x002f:
            int r4 = r1.size()
            if (r3 >= r4) goto L_0x0080
            java.lang.String r4 = "{"
            r0.append(r4)
            java.lang.String r4 = "\"p\":"
            r0.append(r4)
            java.lang.String r4 = "\""
            r0.append(r4)
            java.lang.Object r5 = r1.get(r3)
            com.umeng.message.entity.Ucode r5 = (com.umeng.message.entity.Ucode) r5
            java.lang.String r5 = r5.p
            r0.append(r5)
            r0.append(r4)
            java.lang.String r4 = ","
            r0.append(r4)
            java.lang.String r5 = "\"t\":"
            r0.append(r5)
            java.lang.Object r5 = r1.get(r3)
            com.umeng.message.entity.Ucode r5 = (com.umeng.message.entity.Ucode) r5
            boolean r5 = r5.b
            r0.append(r5)
            java.lang.String r5 = "}"
            r0.append(r5)
            int r5 = r1.size()
            int r5 = r5 + -1
            if (r3 == r5) goto L_0x007d
            r0.append(r4)
        L_0x007d:
            int r3 = r3 + 1
            goto L_0x002f
        L_0x0080:
            java.lang.String r1 = "]"
            r0.append(r1)
            org.json.JSONArray r1 = new org.json.JSONArray     // Catch:{ Exception -> 0x0090 }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x0090 }
            r1.<init>(r0)     // Catch:{ Exception -> 0x0090 }
            goto L_0x0095
        L_0x0090:
            r0 = move-exception
            r0.printStackTrace()
            r1 = r2
        L_0x0095:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.UTrack.d():org.json.JSONArray");
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        if (!f() || MessageSharedPrefs.getInstance(f).getHasRegister()) {
            return;
        }
        if (registerSending) {
            UMLog.mutlInfo(a, 2, "sendRegisterLog\u5df2\u7ecf\u5728\u961f\u5217\u91cc\uff0c\u5ffd\u7565\u8fd9\u6b21\u8bf7\u6c42");
            return;
        }
        UMLog.mutlInfo(a, 2, "trackRegisterLog\u5f00\u59cb, \u8bbe\u7f6eregisterSending\u6807\u5fd7\u4f4d");
        registerSending = true;
        AnonymousClass10 r2 = new AnonymousClass10(str);
        UMLog.mutlInfo(a, 2, String.format("trackRegister(delay=%d)", 0));
        e.a(r2, 0, TimeUnit.MILLISECONDS);
    }

    /* renamed from: com.umeng.message.UTrack$10  reason: invalid class name */
    class AnonymousClass10 implements Runnable {
        final /* synthetic */ String a;

        AnonymousClass10(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                JSONObject h = UTrack.this.h();
                String str = UTrack.a;
                UMLog.mutlInfo(str, 2, "trackRegister-->request:" + h.toString());
                String g = UTrack.this.g();
                if (!h.d(g)) {
                    String str2 = UTrack.a;
                    UMLog.mutlInfo(str2, 2, "TestDevice sign =" + g);
                    h.put("TD", g);
                }
                UTrack.e.trackRegister(h, this.a);
            } catch (Exception e) {
                UTrack.registerSending = false;
                e.printStackTrace();
            }
        }
    }

    private void e() {
        if (this.b == null) {
            c cVar = new c(f);
            cVar.b(f, new String[0]);
            Context context = f;
            cVar.a(context, PushAgent.getInstance(context).getMessageAppkey(), PushAgent.getInstance(f).getMessageChannel());
            this.b = new JSONObject();
            try {
                cVar.b(this.b);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (this.c == null) {
            c cVar2 = new c(f);
            cVar2.c(f, new String[0]);
            Context context2 = f;
            cVar2.a(context2, PushAgent.getInstance(context2).getMessageAppkey(), PushAgent.getInstance(f).getMessageChannel());
            this.c = new JSONObject();
            try {
                cVar2.c(this.c);
            } catch (Exception e3) {
                e3.printStackTrace();
            }
        }
    }

    public JSONObject getHeader() {
        updateDeviceHeader(this.b);
        return this.b;
    }

    /* renamed from: com.umeng.message.UTrack$11  reason: invalid class name */
    class AnonymousClass11 implements Runnable {
        AnonymousClass11() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                UTrack.e.sendAliasFailLog(UTrack.this.h());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendAliasFailLog() {
        e.a(new AnonymousClass11());
    }

    /* renamed from: com.umeng.message.UTrack$12  reason: invalid class name */
    class AnonymousClass12 implements Runnable {
        final /* synthetic */ String a;

        AnonymousClass12(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (MessageSharedPrefs.getInstance(UTrack.f).getDaRegisterSendPolicy() == 1) {
                UMLog.mutlInfo(UTrack.a, 2, "da_register_policy=1, skip sending da_register info");
                return;
            }
            try {
                JSONObject h = UTrack.this.h();
                h.put("registerLog", this.a);
                UTrack.e.sendRegisterLog(h);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void sendRegisterLog(String str) {
        e.a(new AnonymousClass12(str));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.UTrack$2  reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ ICallBack c;

        AnonymousClass2(String str, String str2, ICallBack iCallBack) {
            this.a = str;
            this.b = str2;
            this.c = iCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            SuccessState successState = null;
            try {
                String str = MessageSharedPrefs.getInstance(UTrack.f).get_addAliasInterval();
                String a = UTrack.this.a(this.a, this.b, (str == null || str.length() <= 0) ? null : new JSONObject(str));
                if (a != null && a.length() > 0) {
                    this.c.onMessage(false, a);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            StringBuilder sb = new StringBuilder();
            String str2 = "";
            sb.append(str2);
            sb.append("utdid:");
            sb.append(UmengMessageDeviceConfig.getUtdid(UTrack.f));
            sb.append(",deviceToken:");
            sb.append(MessageSharedPrefs.getInstance(UTrack.f).getDeviceToken());
            sb.append(";");
            String sb2 = sb.toString();
            if (TextUtils.isEmpty(this.b) || TextUtils.isEmpty(this.a)) {
                UMLog.mutlInfo(UTrack.a, 0, "addAlias: type\u6216alias\u4e3a\u7a7a");
                sb2 = sb2 + "addAlias: empty type or alias;";
                successState = SuccessState.FAIL_PARAM;
            }
            if (TextUtils.isEmpty(UmengMessageDeviceConfig.getUtdid(UTrack.f))) {
                UMLog.mutlInfo(UTrack.a, 0, "UTDID\u4e3a\u7a7a");
                sb2 = sb2 + "UTDID is empty;";
                successState = SuccessState.FAIL_PARAM;
            }
            if (TextUtils.isEmpty(MessageSharedPrefs.getInstance(UTrack.f).getDeviceToken())) {
                UMLog.mutlInfo(UTrack.a, 0, "Device token\u4e3a\u7a7a");
                sb2 = sb2 + "RegistrationId is empty;";
                successState = SuccessState.FAIL_PARAM;
            }
            if (MessageSharedPrefs.getInstance(UTrack.f).isAliasSet(0, this.a, this.b)) {
                UMLog.mutlInfo(UTrack.a, 2, String.format("addAlias: <%s, %s> \u5df2\u7ecf\u540c\u6b65\u81f3\u670d\u52a1\u5668\uff0c\u5ffd\u7565\u8be5\u8bf7\u6c42", this.a, this.b));
                str2 = str2 + String.format("addAlias: <%s, %s> has been synced to the server before. Ingore this request;", this.a, this.b);
                successState = SuccessState.SUCCESS_CACHE;
            }
            try {
                JSONObject h = UTrack.this.h();
                if (successState == null) {
                    h.put("alias", this.a);
                    h.put("type", this.b);
                    h.put(MsgConstant.KEY_LAST_ALIAS, MessageSharedPrefs.getInstance(UTrack.f).getLastAlias(0, this.b));
                    h.put("ts", System.currentTimeMillis());
                } else if (successState == SuccessState.FAIL_PARAM) {
                    h.put("fail", sb2);
                } else if (successState == SuccessState.SUCCESS_CACHE) {
                    h.put("success", str2);
                }
                UTrack.e.addAlias(this.a, this.b, h, this.c);
            } catch (Exception e2) {
                if (e2.getMessage() != null) {
                    ICallBack iCallBack = this.c;
                    if (iCallBack != null) {
                        iCallBack.onMessage(false, "alias:" + this.a + "\u6dfb\u52a0\u5931\u8d25:" + e2.getMessage());
                        MessageSharedPrefs.getInstance(UTrack.f).addAlias(this.a, this.b, 0, 1, e2.getMessage());
                        return;
                    }
                    return;
                }
                ICallBack iCallBack2 = this.c;
                if (iCallBack2 != null) {
                    iCallBack2.onMessage(false, "alias:" + this.a + "\u6dfb\u52a0\u5931\u8d25");
                    MessageSharedPrefs.getInstance(UTrack.f).addAlias(this.a, this.b, 0, 1, "\u6dfb\u52a0\u5931\u8d25");
                }
            }
        }
    }

    public boolean addAlias(String str, String str2, ICallBack iCallBack) {
        e.a(new AnonymousClass2(str, str2, iCallBack));
        return false;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String a(String str, String str2, JSONObject jSONObject) {
        try {
            byte[] bytes = str.getBytes("UTF-8");
            byte[] bytes2 = str2.getBytes("UTF-8");
            boolean z = true;
            boolean z2 = bytes.length <= 128 && bytes.length >= 0;
            if (bytes2.length > 64 || bytes2.length < 0) {
                z = false;
            }
            if (!z2 || !z) {
                return "alias\u957f\u5ea6\u4e0d\u57280~128\u4e4b\u95f4\u6216aliasType\u957f\u5ea6\u4e0d\u57280~64\u4e4b\u95f4";
            }
            if (jSONObject == null) {
                return null;
            }
            long optLong = jSONObject.optLong("interval", 0);
            long optLong2 = jSONObject.optLong("last_requestTime", 0);
            long currentTimeMillis = System.currentTimeMillis();
            if (optLong == 0 || (currentTimeMillis - optLong2) / 1000 >= optLong) {
                return null;
            }
            return "interval\u9650\u5236";
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.UTrack$3  reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ ICallBack c;

        AnonymousClass3(String str, String str2, ICallBack iCallBack) {
            this.a = str;
            this.b = str2;
            this.c = iCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            SuccessState successState;
            String str;
            try {
                String str2 = MessageSharedPrefs.getInstance(UTrack.f).get_setAliasInterval();
                String a = UTrack.this.a(this.a, this.b, (str2 == null || str2.length() <= 0) ? null : new JSONObject(str2));
                if (a != null && a.length() > 0) {
                    this.c.onMessage(false, a);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            String str3 = "";
            if (TextUtils.isEmpty(this.b)) {
                UMLog.mutlInfo(UTrack.a, 0, "addExclusiveAlias: type\u4e3a\u7a7a");
                str = str3 + "addExclusiveAlias: empty type";
                successState = SuccessState.FAIL_PARAM;
            } else {
                successState = null;
                str = str3;
            }
            if (TextUtils.isEmpty(UmengMessageDeviceConfig.getUtdid(UTrack.f))) {
                UMLog.mutlInfo(UTrack.a, 0, "UTDID\u4e3a\u7a7a");
                str = str + "UTDID is empty;";
                successState = SuccessState.FAIL_PARAM;
            }
            if (TextUtils.isEmpty(MessageSharedPrefs.getInstance(UTrack.f).getDeviceToken())) {
                UMLog.mutlInfo(UTrack.a, 0, "Device token\u4e3a\u7a7a");
                str = str + "RegistrationId is empty;";
                successState = SuccessState.FAIL_PARAM;
            }
            if (MessageSharedPrefs.getInstance(UTrack.f).isAliasSet(1, this.a, this.b)) {
                UMLog.mutlInfo(UTrack.a, 2, String.format("addExclusiveAlias: <%s, %s> \u5df2\u7ecf\u540c\u6b65\u81f3\u670d\u52a1\u5668\uff0c\u5ffd\u7565\u8be5\u8bf7\u6c42", this.a, this.b));
                str3 = str3 + String.format("addExclusiveAlias: <%s, %s> has been synced to the server before. Ingore this request.", this.a, this.b);
                successState = SuccessState.SUCCESS_CACHE;
            }
            try {
                JSONObject h = UTrack.this.h();
                if (successState == null) {
                    h.put("alias", this.a);
                    h.put("type", this.b);
                    h.put(MsgConstant.KEY_LAST_ALIAS, MessageSharedPrefs.getInstance(UTrack.f).getLastAlias(1, this.b));
                    h.put("ts", System.currentTimeMillis());
                } else if (successState == SuccessState.FAIL_PARAM) {
                    h.put("fail", str);
                } else if (successState == SuccessState.SUCCESS_CACHE) {
                    h.put("success", str3);
                }
                UTrack.e.setAlias(this.a, this.b, h, this.c);
            } catch (Exception e2) {
                if (e2.getMessage() != null) {
                    this.c.onMessage(false, "alias:" + this.a + "\u6dfb\u52a0\u5931\u8d25:" + e2.getMessage());
                    MessageSharedPrefs.getInstance(UTrack.f).addAlias(this.a, this.b, 1, 1, e2.getMessage());
                    return;
                }
                this.c.onMessage(false, "alias:" + this.a + "\u6dfb\u52a0\u5931\u8d25");
                MessageSharedPrefs.getInstance(UTrack.f).addAlias(this.a, this.b, 1, 1, "\u6dfb\u52a0\u5931\u8d25");
            }
        }
    }

    public void setAlias(String str, String str2, ICallBack iCallBack) {
        e.a(new AnonymousClass3(str, str2, iCallBack));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.UTrack$4  reason: invalid class name */
    public class AnonymousClass4 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;
        final /* synthetic */ ICallBack c;

        AnonymousClass4(String str, String str2, ICallBack iCallBack) {
            this.a = str;
            this.b = str2;
            this.c = iCallBack;
        }

        @Override // java.lang.Runnable
        public void run() {
            SuccessState successState;
            String str;
            try {
                String str2 = MessageSharedPrefs.getInstance(UTrack.f).get_deleteALiasInterval();
                String a = UTrack.this.a(this.a, this.b, (str2 == null || str2.length() <= 0) ? null : new JSONObject(str2));
                if (a != null && a.length() > 0) {
                    this.c.onMessage(false, a);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (TextUtils.isEmpty(this.b)) {
                UMLog.mutlInfo(UTrack.a, 0, "removeAlias: type\u4e3a\u7a7a");
                str = "removeAlias: empty type";
                successState = SuccessState.FAIL_PARAM;
            } else {
                successState = null;
                str = "";
            }
            if (TextUtils.isEmpty(UmengMessageDeviceConfig.getUtdid(UTrack.f))) {
                UMLog.mutlInfo(UTrack.a, 0, "UTDID\u4e3a\u7a7a");
                str = str + "UTDID is empty;";
                successState = SuccessState.FAIL_PARAM;
            }
            if (TextUtils.isEmpty(MessageSharedPrefs.getInstance(UTrack.f).getDeviceToken())) {
                UMLog.mutlInfo(UTrack.a, 0, "Device token\u4e3a\u7a7a");
                str = str + "RegistrationId is empty;";
                successState = SuccessState.FAIL_PARAM;
            }
            try {
                JSONObject h = UTrack.this.h();
                if (successState == null) {
                    h.put("alias", this.a);
                    h.put("type", this.b);
                    h.put("ts", System.currentTimeMillis());
                } else if (successState == SuccessState.FAIL_PARAM) {
                    h.put("fail", str);
                } else if (successState == SuccessState.SUCCESS_CACHE) {
                    h.put("success", "");
                }
                UTrack.e.deleteAlias(this.a, this.b, h, this.c);
            } catch (Exception e2) {
                if (e2.getMessage() != null) {
                    this.c.onMessage(false, "alias:" + this.a + "\u79fb\u9664\u5931\u8d25:" + e2.getMessage());
                    return;
                }
                this.c.onMessage(false, "alias:" + this.a + "\u79fb\u9664\u5931\u8d25");
            }
        }
    }

    public void deleteAlias(String str, String str2, ICallBack iCallBack) {
        e.a(new AnonymousClass4(str, str2, iCallBack));
    }

    private boolean f() {
        if (TextUtils.isEmpty(UmengMessageDeviceConfig.getUtdid(f))) {
            UMLog.mutlInfo(a, 0, "UTDID\u4e3a\u7a7a");
            return false;
        } else if (!TextUtils.isEmpty(MessageSharedPrefs.getInstance(f).getDeviceToken())) {
            return true;
        } else {
            UMLog.mutlInfo(a, 0, "Device token\u4e3a\u7a7a");
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a3 A[SYNTHETIC, Splitter:B:34:0x00a3] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00b0 A[SYNTHETIC, Splitter:B:41:0x00b0] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00b9 A[SYNTHETIC, Splitter:B:46:0x00b9] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String g() {
        /*
            r8 = this;
            r0 = 0
            java.lang.String r1 = android.os.Environment.getExternalStorageState()     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r2 = "mounted"
            boolean r1 = r1.equals(r2)     // Catch:{ Exception -> 0x00c2 }
            if (r1 != 0) goto L_0x000f
            return r0
        L_0x000f:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00c2 }
            r1.<init>()     // Catch:{ Exception -> 0x00c2 }
            java.io.File r2 = android.os.Environment.getExternalStorageDirectory()     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r2 = r2.getPath()     // Catch:{ Exception -> 0x00c2 }
            r1.append(r2)     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r2 = "/data/"
            r1.append(r2)     // Catch:{ Exception -> 0x00c2 }
            android.content.Context r2 = com.umeng.message.UTrack.f     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r2 = r2.getPackageName()     // Catch:{ Exception -> 0x00c2 }
            r1.append(r2)     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r2 = "/"
            r1.append(r2)     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r2 = com.umeng.message.UTrack.a     // Catch:{ Exception -> 0x00c2 }
            r3 = 2
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ Exception -> 0x00c2 }
            r5 = 0
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x00c2 }
            r6.<init>()     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r7 = "path="
            r6.append(r7)     // Catch:{ Exception -> 0x00c2 }
            r6.append(r1)     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r6 = r6.toString()     // Catch:{ Exception -> 0x00c2 }
            r4[r5] = r6     // Catch:{ Exception -> 0x00c2 }
            com.umeng.commonsdk.debug.UMLog.mutlInfo(r2, r3, r4)     // Catch:{ Exception -> 0x00c2 }
            java.io.File r2 = new java.io.File     // Catch:{ Exception -> 0x00c2 }
            java.lang.String r3 = "umeng-message.config"
            r2.<init>(r1, r3)     // Catch:{ Exception -> 0x00c2 }
            boolean r1 = r2.exists()     // Catch:{ Exception -> 0x00c2 }
            if (r1 != 0) goto L_0x0065
            return r0
        L_0x0065:
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ FileNotFoundException -> 0x00a9, IOException -> 0x009c, all -> 0x0099 }
            java.io.FileReader r3 = new java.io.FileReader     // Catch:{ FileNotFoundException -> 0x00a9, IOException -> 0x009c, all -> 0x0099 }
            r3.<init>(r2)     // Catch:{ FileNotFoundException -> 0x00a9, IOException -> 0x009c, all -> 0x0099 }
            r1.<init>(r3)     // Catch:{ FileNotFoundException -> 0x00a9, IOException -> 0x009c, all -> 0x0099 }
        L_0x006f:
            java.lang.String r2 = r1.readLine()     // Catch:{ FileNotFoundException -> 0x0097, IOException -> 0x0095 }
            if (r2 == 0) goto L_0x008c
            java.lang.String r3 = "sign="
            boolean r3 = r2.startsWith(r3)     // Catch:{ FileNotFoundException -> 0x0097, IOException -> 0x0095 }
            if (r3 == 0) goto L_0x006f
            r3 = 5
            java.lang.String r2 = r2.substring(r3)     // Catch:{ FileNotFoundException -> 0x0097, IOException -> 0x0095 }
            r1.close()     // Catch:{ IOException -> 0x0087 }
            goto L_0x008b
        L_0x0087:
            r1 = move-exception
            r1.printStackTrace()
        L_0x008b:
            return r2
        L_0x008c:
            r1.close()     // Catch:{ IOException -> 0x0090 }
            goto L_0x00c6
        L_0x0090:
            r1 = move-exception
        L_0x0091:
            r1.printStackTrace()
            goto L_0x00c6
        L_0x0095:
            r2 = move-exception
            goto L_0x009e
        L_0x0097:
            r2 = move-exception
            goto L_0x00ab
        L_0x0099:
            r2 = move-exception
            r1 = r0
            goto L_0x00b7
        L_0x009c:
            r2 = move-exception
            r1 = r0
        L_0x009e:
            r2.printStackTrace()     // Catch:{ all -> 0x00b6 }
            if (r1 == 0) goto L_0x00c6
            r1.close()     // Catch:{ IOException -> 0x00a7 }
            goto L_0x00c6
        L_0x00a7:
            r1 = move-exception
            goto L_0x0091
        L_0x00a9:
            r2 = move-exception
            r1 = r0
        L_0x00ab:
            r2.printStackTrace()
            if (r1 == 0) goto L_0x00c6
            r1.close()     // Catch:{ IOException -> 0x00b4 }
            goto L_0x00c6
        L_0x00b4:
            r1 = move-exception
            goto L_0x0091
        L_0x00b6:
            r2 = move-exception
        L_0x00b7:
            if (r1 == 0) goto L_0x00c1
            r1.close()     // Catch:{ IOException -> 0x00bd }
            goto L_0x00c1
        L_0x00bd:
            r1 = move-exception
            r1.printStackTrace()
        L_0x00c1:
            throw r2
        L_0x00c2:
            r1 = move-exception
            r1.printStackTrace()
        L_0x00c6:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.UTrack.g():java.lang.String");
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private JSONObject h() throws JSONException {
        String deviceToken = MessageSharedPrefs.getInstance(f).getDeviceToken();
        String utdid = UmengMessageDeviceConfig.getUtdid(f);
        JSONObject jSONObject = new JSONObject();
        updateDeviceHeader(this.b);
        this.b.put("umid", UmengMessageDeviceConfig.getUmid(f));
        jSONObject.put("header", this.b);
        jSONObject.put("utdid", utdid);
        jSONObject.put("device_token", deviceToken);
        return jSONObject;
    }

    private JSONObject i() throws JSONException {
        String deviceToken = MessageSharedPrefs.getInstance(f).getDeviceToken();
        String utdid = UmengMessageDeviceConfig.getUtdid(f);
        JSONObject jSONObject = new JSONObject();
        updateDeviceHeader(this.b);
        JSONObject jSONObject2 = this.b;
        jSONObject2.put("umid", UmengMessageDeviceConfig.getUmid(f));
        jSONObject.put("header", jSONObject2);
        jSONObject.put("utdid", utdid);
        jSONObject.put("device_token", deviceToken);
        return jSONObject;
    }

    private JSONObject j() throws JSONException {
        String deviceToken = MessageSharedPrefs.getInstance(f).getDeviceToken();
        String utdid = UmengMessageDeviceConfig.getUtdid(f);
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("appkey", PushAgent.getInstance(f).getMessageAppkey());
        jSONObject.put("utdid", utdid);
        jSONObject.put("device_token", deviceToken);
        return jSONObject;
    }

    public void updateHeader() {
        c cVar = new c(f);
        cVar.b(f, new String[0]);
        Context context = f;
        cVar.a(context, PushAgent.getInstance(context).getMessageAppkey(), PushAgent.getInstance(f).getMessageChannel());
        this.b = new JSONObject();
        try {
            cVar.b(this.b);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        c cVar2 = new c(f);
        cVar2.c(f, new String[0]);
        Context context2 = f;
        cVar2.a(context2, PushAgent.getInstance(context2).getMessageAppkey(), PushAgent.getInstance(f).getMessageChannel());
        this.c = new JSONObject();
        try {
            cVar2.c(this.c);
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void updateDeviceHeader(JSONObject jSONObject) {
        if (jSONObject != null) {
            try {
                if (jSONObject.has("device_id")) {
                    jSONObject.put("device_id", UmengMessageDeviceConfig.getDeviceIDAes(f));
                }
                if (jSONObject.has(c.c)) {
                    jSONObject.put(c.c, UmengMessageDeviceConfig.getDINAes(f));
                }
                if (jSONObject.has("idmd5")) {
                    jSONObject.put("idmd5", UmengMessageDeviceConfig.getDeviceIdMD5(f));
                }
                if (jSONObject.has("android_id")) {
                    jSONObject.put("android_id", UmengMessageDeviceConfig.getAndroidId(f));
                }
                if (jSONObject.has("serial_number")) {
                    jSONObject.put("serial_number", UmengMessageDeviceConfig.getSerial_number());
                }
            } catch (Throwable unused) {
            }
        }
    }
}
