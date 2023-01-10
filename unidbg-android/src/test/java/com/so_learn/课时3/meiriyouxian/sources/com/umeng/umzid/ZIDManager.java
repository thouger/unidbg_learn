package com.umeng.umzid;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.unionpay.tsmservice.mi.data.Constant;
import java.util.UUID;
import org.json.JSONObject;

public class ZIDManager {
    public static ZIDManager c;
    public boolean a = false;
    public boolean b = false;

    public class a implements Runnable {
        public final /* synthetic */ Context a;
        public final /* synthetic */ IZIDCompletionCallback b;

        public a(Context context, IZIDCompletionCallback iZIDCompletionCallback) {
            this.a = context;
            this.b = iZIDCompletionCallback;
        }

        @Override // java.lang.Runnable
        public void run() {
            String a = ZIDManager.a(ZIDManager.this, this.a);
            if (TextUtils.isEmpty(a)) {
                IZIDCompletionCallback iZIDCompletionCallback = this.b;
                if (iZIDCompletionCallback != null) {
                    iZIDCompletionCallback.onFailure("1002", "\u83b7\u53d6zid\u5931\u8d25");
                    return;
                }
                return;
            }
            IZIDCompletionCallback iZIDCompletionCallback2 = this.b;
            if (iZIDCompletionCallback2 != null) {
                iZIDCompletionCallback2.onSuccess(a);
            }
        }
    }

    public class b implements Runnable {
        public final /* synthetic */ Context a;

        public b(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            ZIDManager.b(ZIDManager.this, this.a);
        }
    }

    public class c implements Runnable {
        public final /* synthetic */ Context a;

        public c(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            ZIDManager.a(ZIDManager.this, this.a);
        }
    }

    public class d implements Runnable {
        public final /* synthetic */ Context a;

        public d(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            ZIDManager.b(ZIDManager.this, this.a);
        }
    }

    public static /* synthetic */ String a(ZIDManager zIDManager, Context context) {
        String str = null;
        if (!zIDManager.a) {
            zIDManager.a = true;
            JSONObject jSONObject = new JSONObject();
            try {
                String id = Spy.getID();
                jSONObject.put("zdata", id);
                String c2 = c.c(context);
                jSONObject.put(Constant.KEY_MAC, c2);
                String d2 = c.d(context);
                jSONObject.put("oaid", d2);
                zIDManager.a(context, jSONObject);
                String a2 = a.a("https://aaid.umeng.com/api/postZdata", jSONObject.toString());
                if (!TextUtils.isEmpty(a2)) {
                    JSONObject jSONObject2 = new JSONObject(a2);
                    if (Boolean.valueOf(jSONObject2.optBoolean("suc")).booleanValue()) {
                        c.f(context, id);
                        c.a(context, c2);
                        c.b(context, d2);
                        str = jSONObject2.optString("aaid");
                        if (!TextUtils.isEmpty(str)) {
                            c.e(context, str);
                        }
                        String string = jSONObject2.getString("uabc");
                        if (!TextUtils.isEmpty(string)) {
                            c.d(context, string);
                        }
                        String string2 = jSONObject2.getString("resetToken");
                        if (!TextUtils.isEmpty(string2)) {
                            c.c(context, string2);
                        }
                    }
                }
            } catch (Throwable unused) {
            }
            zIDManager.a = false;
        }
        return str;
    }

    public static /* synthetic */ String b(ZIDManager zIDManager, Context context) {
        SharedPreferences a2;
        SharedPreferences a3;
        SharedPreferences a4;
        SharedPreferences a5;
        String str = null;
        if (!zIDManager.b) {
            zIDManager.b = true;
            JSONObject jSONObject = new JSONObject();
            try {
                Object b2 = c.b(context);
                String id = Spy.getID();
                jSONObject.put("zdata", id);
                jSONObject.put("old_zdata", b2);
                String str2 = "";
                Object string = (context == null || (a5 = a.a(context)) == null) ? str2 : a5.getString("oaid", str2);
                String d2 = c.d(context);
                jSONObject.put("old_oaid", string);
                jSONObject.put("oaid", d2);
                Object string2 = (context == null || (a4 = a.a(context)) == null) ? str2 : a4.getString(Constant.KEY_MAC, str2);
                String c2 = c.c(context);
                jSONObject.put(Constant.KEY_MAC, c2);
                jSONObject.put("old_mac", string2);
                zIDManager.a(context, jSONObject);
                jSONObject.put("aaid", c.a(context));
                jSONObject.put("uabc", (context == null || (a3 = a.a(context)) == null) ? str2 : a3.getString("uabc", str2));
                if (!(context == null || (a2 = a.a(context)) == null)) {
                    str2 = a2.getString("resetToken", str2);
                }
                if (!TextUtils.isEmpty(str2)) {
                    jSONObject.put("resetToken", str2);
                }
                String a6 = a.a("https://aaid.umeng.com/api/updateZdata", jSONObject.toString());
                if (!TextUtils.isEmpty(a6)) {
                    JSONObject jSONObject2 = new JSONObject(a6);
                    if (Boolean.valueOf(jSONObject2.optBoolean("suc")).booleanValue()) {
                        c.f(context, id);
                        c.a(context, c2);
                        c.b(context, d2);
                        str = jSONObject2.optString("aaid");
                        if (!TextUtils.isEmpty(str)) {
                            c.e(context, str);
                        }
                        String string3 = jSONObject2.getString("uabc");
                        if (!TextUtils.isEmpty(string3)) {
                            c.d(context, string3);
                        }
                        String string4 = jSONObject2.getString("resetToken");
                        if (!TextUtils.isEmpty(string4)) {
                            c.c(context, string4);
                        }
                    }
                }
            } catch (Throwable unused) {
            }
            zIDManager.b = false;
        }
        return str;
    }

    public static synchronized ZIDManager getInstance() {
        ZIDManager zIDManager;
        synchronized (ZIDManager.class) {
            if (c == null) {
                c = new ZIDManager();
            }
            zIDManager = c;
        }
        return zIDManager;
    }

    public static String getSDKVersion() {
        return "1.2.2";
    }

    public synchronized String getZID(Context context) {
        SharedPreferences a2;
        if (context == null) {
            return "";
        }
        Context applicationContext = context.getApplicationContext();
        String a3 = c.a(applicationContext);
        if (TextUtils.isEmpty(a3)) {
            b.a(new c(applicationContext));
            return "";
        }
        if (!((applicationContext == null || (a2 = a.a(applicationContext)) == null) ? "" : a2.getString("zdata", null)).equals(Spy.getID())) {
            b.a(new d(applicationContext));
        }
        return a3;
    }

    public synchronized void init(Context context, String str, IZIDCompletionCallback iZIDCompletionCallback) {
        SharedPreferences a2;
        SharedPreferences.Editor edit;
        if (context == null) {
            if (iZIDCompletionCallback != null) {
                iZIDCompletionCallback.onFailure("1001", "\u4f20\u5165\u53c2\u6570Context\u4e3anull");
            }
        } else if (TextUtils.isEmpty(str)) {
            if (iZIDCompletionCallback != null) {
                iZIDCompletionCallback.onFailure("1003", "\u4f20\u5165\u53c2\u6570appkey\u4e3a\u7a7a");
            }
        } else {
            Context applicationContext = context.getApplicationContext();
            if (!(applicationContext == null || str == null || TextUtils.isEmpty(str) || (a2 = a.a(applicationContext)) == null || (edit = a2.edit()) == null)) {
                edit.putString("appkey", str).commit();
            }
            String a3 = c.a(applicationContext);
            if (a3 == null || TextUtils.isEmpty(a3)) {
                b.a(new a(applicationContext, iZIDCompletionCallback));
            } else {
                b.a(new b(applicationContext));
                if (iZIDCompletionCallback != null) {
                    iZIDCompletionCallback.onSuccess(a3);
                }
            }
            String str2 = "";
            SharedPreferences a4 = a.a(context);
            if (a4 != null) {
                str2 = a4.getString("uuid", "");
            }
            if (TextUtils.isEmpty(str2)) {
                String str3 = "";
                SharedPreferences a5 = a.a(context);
                try {
                    str3 = UUID.randomUUID().toString();
                } catch (Throwable unused) {
                }
                if (a5 != null) {
                    a5.edit().putString("uuid", str3).commit();
                }
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:25:0x0084 A[SYNTHETIC, Splitter:B:25:0x0084] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0117 A[SYNTHETIC, Splitter:B:52:0x0117] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x015f  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0161  */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x017d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final org.json.JSONObject a(android.content.Context r9, org.json.JSONObject r10) {
        /*
        // Method dump skipped, instructions count: 504
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.umzid.ZIDManager.a(android.content.Context, org.json.JSONObject):org.json.JSONObject");
    }
}
