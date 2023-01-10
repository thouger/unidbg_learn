package com.umeng.message.inapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.PushAgent;
import com.umeng.message.common.UmengMessageDeviceConfig;
import com.umeng.message.entity.UInAppMessage;
import com.umeng.message.inapp.UImageLoadTask;
import java.io.ByteArrayOutputStream;
import java.util.Calendar;
import org.json.JSONArray;
import org.json.JSONException;

/* compiled from: UmengCardMessageBuilder */
final class b implements IUmengInAppMessageCallback, UImageLoadTask.ImageLoaderCallback {
    private static final String a = b.class.getName();
    private static final int f = 10;
    private Context b;
    private String c;
    private boolean d;
    private UInAppMessage e;
    private IUmengInAppMsgCloseCallback g;

    @Override // com.umeng.message.inapp.IUmengInAppMessageCallback
    public void onSplashMessage(UInAppMessage uInAppMessage) {
    }

    public b(Activity activity, String str, IUmengInAppMsgCloseCallback iUmengInAppMsgCloseCallback) {
        this.b = activity;
        this.c = str;
        this.g = iUmengInAppMsgCloseCallback;
    }

    public b(Context context, String str) {
        this.b = context.getApplicationContext();
        this.c = str;
        this.d = true;
    }

    private boolean a(String str) {
        if (!UmengMessageDeviceConfig.getAppVersionCode(this.b).equals(InAppMessageManager.getInstance(this.b).g())) {
            InAppMessageManager.getInstance(this.b).d("");
        }
        InAppMessageManager.getInstance(this.b).e(UmengMessageDeviceConfig.getAppVersionCode(this.b));
        String f2 = InAppMessageManager.getInstance(this.b).f();
        JSONArray jSONArray = null;
        if (!TextUtils.isEmpty(f2)) {
            try {
                jSONArray = new JSONArray(f2);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        if (jSONArray == null) {
            JSONArray jSONArray2 = new JSONArray();
            jSONArray2.put(str);
            InAppMessageManager.getInstance(this.b).d(jSONArray2.toString());
            return true;
        } else if (a(jSONArray, str)) {
            return true;
        } else {
            if (jSONArray.length() >= 10) {
                return false;
            }
            jSONArray.put(str);
            InAppMessageManager.getInstance(this.b).d(jSONArray.toString());
            return true;
        }
    }

    private boolean a(JSONArray jSONArray, String str) {
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                if (jSONArray.getString(i).equals(str)) {
                    return true;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        if (TextUtils.isEmpty(this.c.trim())) {
            if (PushAgent.DEBUG) {
                Toast.makeText(this.b, "\u63d2\u5c4f\u6d88\u606f\u7684\u6807\u7b7e\u4e0d\u80fd\u4e3a\u7a7a", 1).show();
            }
            UMLog.mutlInfo(a, 0, "\u63d2\u5c4f\u6d88\u606f\u7684\u6807\u7b7e\u4e0d\u80fd\u4e3a\u7a7a");
        } else if (!a(this.c)) {
            UMLog.mutlInfo(a, 0, "\u63d2\u5c4f\u6d88\u606f\u7684\u6700\u5927\u6807\u7b7e\u6570\u4e3a 10");
        } else if (InAppMessageManager.a) {
            d.a(this.b).a(this.c, this);
        } else if (System.currentTimeMillis() - InAppMessageManager.getInstance(this.b).b(this.c) > ((long) InAppMessageManager.b)) {
            d.a(this.b).a(this.c, this);
        } else {
            onCardMessage(null);
        }
    }

    private void a(Bitmap bitmap) {
        if (bitmap != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                UmengCardMessage umengCardMessage = new UmengCardMessage();
                umengCardMessage.a(this.g);
                Bundle bundle = new Bundle();
                bundle.putString("label", this.c);
                bundle.putString("msg", this.e.getRaw().toString());
                bundle.putByteArray("bitmapByte", byteArray);
                umengCardMessage.setArguments(bundle);
                umengCardMessage.show(((Activity) this.b).getFragmentManager(), this.c);
                InAppMessageManager.getInstance(this.b).a(this.e.msg_id, 1);
                InAppMessageManager.getInstance(this.b).f(this.c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void b() {
        try {
            UmengCardMessage umengCardMessage = new UmengCardMessage();
            umengCardMessage.a(this.g);
            Bundle bundle = new Bundle();
            bundle.putString("label", this.c);
            bundle.putString("msg", this.e.getRaw().toString());
            umengCardMessage.setArguments(bundle);
            umengCardMessage.show(((Activity) this.b).getFragmentManager(), this.c);
            InAppMessageManager.getInstance(this.b).a(this.e.msg_id, 1);
            InAppMessageManager.getInstance(this.b).f(this.c);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024 A[ADDED_TO_REGION] */
    @Override // com.umeng.message.inapp.IUmengInAppMessageCallback
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onCardMessage(com.umeng.message.entity.UInAppMessage r5) {
        /*
            r4 = this;
            android.content.Context r0 = r4.b
            com.umeng.message.inapp.InAppMessageManager r0 = com.umeng.message.inapp.InAppMessageManager.getInstance(r0)
            java.lang.String r1 = r4.c
            java.lang.String r0 = r0.c(r1)
            boolean r1 = android.text.TextUtils.isEmpty(r0)
            if (r1 != 0) goto L_0x0021
            com.umeng.message.entity.UInAppMessage r1 = new com.umeng.message.entity.UInAppMessage     // Catch:{ JSONException -> 0x001d }
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch:{ JSONException -> 0x001d }
            r2.<init>(r0)     // Catch:{ JSONException -> 0x001d }
            r1.<init>(r2)     // Catch:{ JSONException -> 0x001d }
            goto L_0x0022
        L_0x001d:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0021:
            r1 = 0
        L_0x0022:
            if (r5 == 0) goto L_0x0049
            if (r1 == 0) goto L_0x0046
            java.lang.String r0 = r5.msg_id
            java.lang.String r2 = r1.msg_id
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0046
            java.io.File r0 = new java.io.File
            android.content.Context r2 = r4.b
            java.lang.String r1 = r1.msg_id
            java.lang.String r1 = com.umeng.message.proguard.h.d(r2, r1)
            r0.<init>(r1)
            android.content.Context r1 = r4.b
            com.umeng.message.inapp.InAppMessageManager r1 = com.umeng.message.inapp.InAppMessageManager.getInstance(r1)
            r1.a(r0)
        L_0x0046:
            r4.e = r5
            goto L_0x004d
        L_0x0049:
            if (r1 == 0) goto L_0x00be
            r4.e = r1
        L_0x004d:
            com.umeng.message.entity.UInAppMessage r5 = r4.e
            int r5 = r5.show_type
            r0 = 0
            r1 = 1
            if (r5 != r1) goto L_0x006a
            java.lang.String r5 = r4.c
            boolean r5 = r4.b(r5)
            if (r5 != 0) goto L_0x006a
            android.content.Context r5 = r4.b
            com.umeng.message.inapp.InAppMessageManager r5 = com.umeng.message.inapp.InAppMessageManager.getInstance(r5)
            com.umeng.message.entity.UInAppMessage r2 = r4.e
            java.lang.String r2 = r2.msg_id
            r5.a(r2, r0)
        L_0x006a:
            android.content.Context r5 = r4.b
            com.umeng.message.inapp.InAppMessageManager r5 = com.umeng.message.inapp.InAppMessageManager.getInstance(r5)
            com.umeng.message.entity.UInAppMessage r2 = r4.e
            boolean r5 = r5.b(r2)
            if (r5 == 0) goto L_0x00be
            android.content.Context r5 = r4.b
            com.umeng.message.inapp.InAppMessageManager r5 = com.umeng.message.inapp.InAppMessageManager.getInstance(r5)
            com.umeng.message.entity.UInAppMessage r2 = r4.e
            boolean r5 = r5.c(r2)
            if (r5 != 0) goto L_0x0087
            goto L_0x00be
        L_0x0087:
            com.umeng.message.entity.UInAppMessage r5 = r4.e
            int r5 = r5.msg_type
            r2 = 5
            if (r5 == r2) goto L_0x00ae
            com.umeng.message.entity.UInAppMessage r5 = r4.e
            int r5 = r5.msg_type
            r2 = 6
            if (r5 != r2) goto L_0x0096
            goto L_0x00ae
        L_0x0096:
            com.umeng.message.inapp.UImageLoadTask r5 = new com.umeng.message.inapp.UImageLoadTask
            android.content.Context r2 = r4.b
            com.umeng.message.entity.UInAppMessage r3 = r4.e
            r5.<init>(r2, r3)
            r5.a(r4)
            java.lang.String[] r1 = new java.lang.String[r1]
            com.umeng.message.entity.UInAppMessage r2 = r4.e
            java.lang.String r2 = r2.image_url
            r1[r0] = r2
            r5.execute(r1)
            goto L_0x00be
        L_0x00ae:
            android.content.Context r5 = r4.b
            com.umeng.message.inapp.InAppMessageManager r5 = com.umeng.message.inapp.InAppMessageManager.getInstance(r5)
            com.umeng.message.entity.UInAppMessage r0 = r4.e
            java.lang.String r1 = r4.c
            r5.a(r0, r1)
            r4.b()
        L_0x00be:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.inapp.b.onCardMessage(com.umeng.message.entity.UInAppMessage):void");
    }

    @Override // com.umeng.message.inapp.UImageLoadTask.ImageLoaderCallback
    public void onLoadImage(Bitmap[] bitmapArr) {
        if (!this.d) {
            a(bitmapArr[0]);
        }
        InAppMessageManager.getInstance(this.b).a(this.e, this.c);
    }

    private boolean b(String str) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(InAppMessageManager.getInstance(this.b).g(str));
        Calendar instance2 = Calendar.getInstance();
        if (instance.get(6) == instance2.get(6) && instance.get(1) == instance2.get(1)) {
            return true;
        }
        return false;
    }
}
