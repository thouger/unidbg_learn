package com.umeng.message.inapp;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.text.TextUtils;
import com.umeng.commonsdk.debug.UMLog;
import com.umeng.message.MsgConstant;
import com.umeng.message.common.e;
import com.umeng.message.entity.UInAppMessage;
import com.umeng.message.provider.a;
import com.xiaomi.mipush.sdk.Constants;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class InAppMessageManager {
    static boolean a = false;
    static int b = 0;
    static int c = 0;
    private static final String d = InAppMessageManager.class.getName();
    private static InAppMessageManager e = null;
    private static final String i = "tempkey";
    private static final String j = "tempvalue";
    private Context f;
    private String g;
    private UInAppHandler h = new UmengInAppClickHandler();

    static {
        a = false;
        b = 1800000;
        c = 1000;
    }

    private InAppMessageManager(Context context) {
        this.f = context;
    }

    public static InAppMessageManager getInstance(Context context) {
        if (e == null) {
            synchronized (InAppMessageManager.class) {
                if (e == null) {
                    e = new InAppMessageManager(context.getApplicationContext());
                }
            }
        }
        return e;
    }

    public void showCardMessage(Activity activity, String str, IUmengInAppMsgCloseCallback iUmengInAppMsgCloseCallback) {
        new b(activity, str, iUmengInAppMsgCloseCallback).a();
    }

    public void setInAppMsgDebugMode(boolean z) {
        a = z;
    }

    public void setMainActivityPath(String str) {
        this.g = str;
    }

    /* access modifiers changed from: package-private */
    public String a() {
        return this.g;
    }

    public UInAppHandler getInAppHandler() {
        return this.h;
    }

    public void setInAppHandler(UInAppHandler uInAppHandler) {
        this.h = uInAppHandler;
    }

    public void setPlainTextSize(int i2, int i3, int i4) {
        if (i2 <= 0 || i3 <= 0 || i4 <= 0) {
            UMLog.mutlInfo(d, 0, "\u7eaf\u6587\u672c\u5b57\u4f53\u5927\u5c0f\u4e0d\u80fd\u5c0f\u4e8e0");
            return;
        }
        b(MsgConstant.KEY_PLAIN_TEXT_SIZE, i2 + Constants.ACCEPT_TIME_SEPARATOR_SP + i3 + Constants.ACCEPT_TIME_SEPARATOR_SP + i4);
    }

    /* access modifiers changed from: package-private */
    public String[] b() {
        String a2 = a(MsgConstant.KEY_PLAIN_TEXT_SIZE, "");
        if (!TextUtils.isEmpty(a2)) {
            return a2.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public void c() {
        b(MsgConstant.KEY_SPLASH_TS, System.currentTimeMillis() + "");
    }

    /* access modifiers changed from: package-private */
    public long d() {
        return Long.valueOf(a(MsgConstant.KEY_SPLASH_TS, "0")).longValue();
    }

    /* access modifiers changed from: package-private */
    public void a(String str) {
        b("KEY_CARD_TS_" + str, System.currentTimeMillis() + "");
    }

    /* access modifiers changed from: package-private */
    public long b(String str) {
        return Long.valueOf(a("KEY_CARD_TS_" + str, "0")).longValue();
    }

    /* access modifiers changed from: package-private */
    public void a(UInAppMessage uInAppMessage) {
        if (uInAppMessage == null) {
            b(MsgConstant.KEY_LAST_SPLASH_ID, "");
        } else if (uInAppMessage.getRaw() != null) {
            b(MsgConstant.KEY_LAST_SPLASH_ID, uInAppMessage.getRaw().toString());
        }
    }

    /* access modifiers changed from: package-private */
    public String e() {
        return a(MsgConstant.KEY_LAST_SPLASH_ID, "");
    }

    /* access modifiers changed from: package-private */
    public void a(UInAppMessage uInAppMessage, String str) {
        if (uInAppMessage == null) {
            b("KEY_LAST_CARD_ID_" + str, "");
        } else if (uInAppMessage.getRaw() != null) {
            b("KEY_LAST_CARD_ID_" + str, uInAppMessage.getRaw().toString());
        }
    }

    /* access modifiers changed from: package-private */
    public String c(String str) {
        return a("KEY_LAST_CARD_ID_" + str, "");
    }

    /* access modifiers changed from: package-private */
    public void a(String str, int i2) {
        if (i2 == 0) {
            b(str, "0");
        }
        if (i2 == 1) {
            b(str, (j(str) + 1) + "");
        }
    }

    private int j(String str) {
        return Integer.valueOf(a(str, "0")).intValue();
    }

    /* access modifiers changed from: package-private */
    public void d(String str) {
        b(MsgConstant.KEY_CARD_LABEL_LIST, str);
    }

    /* access modifiers changed from: package-private */
    public String f() {
        return a(MsgConstant.KEY_CARD_LABEL_LIST, "");
    }

    /* access modifiers changed from: package-private */
    public void e(String str) {
        b(MsgConstant.KEY_LAST_VERSION_CODE, str);
    }

    /* access modifiers changed from: package-private */
    public String g() {
        return a(MsgConstant.KEY_LAST_VERSION_CODE, "");
    }

    /* access modifiers changed from: package-private */
    public void h() {
        b(MsgConstant.KEY_LAST_SHOW_SPLASH_TS, System.currentTimeMillis() + "");
    }

    /* access modifiers changed from: package-private */
    public long i() {
        return Long.parseLong(a(MsgConstant.KEY_LAST_SHOW_SPLASH_TS, "0"));
    }

    /* access modifiers changed from: package-private */
    public void f(String str) {
        b("KEY_LAST_SHOW_CARD_TS_" + str, System.currentTimeMillis() + "");
    }

    /* access modifiers changed from: package-private */
    public long g(String str) {
        return Long.parseLong(a("KEY_LAST_SHOW_CARD_TS_" + str, "0"));
    }

    /* access modifiers changed from: package-private */
    public boolean b(UInAppMessage uInAppMessage) {
        try {
            if (System.currentTimeMillis() < new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA).parse(uInAppMessage.expire_time).getTime()) {
                return true;
            }
            return false;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* access modifiers changed from: package-private */
    public boolean c(UInAppMessage uInAppMessage) {
        if (uInAppMessage.show_times != 0 && j(uInAppMessage.msg_id) >= uInAppMessage.show_times) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: package-private */
    public void a(String str, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9) {
        if (!TextUtils.isEmpty(str)) {
            e.a(new AnonymousClass1(str, i2, i3, i4, i5, i6, i7, i8, i9));
        }
    }

    /* renamed from: com.umeng.message.inapp.InAppMessageManager$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ int b;
        final /* synthetic */ int c;
        final /* synthetic */ int d;
        final /* synthetic */ int e;
        final /* synthetic */ int f;
        final /* synthetic */ int g;
        final /* synthetic */ int h;
        final /* synthetic */ int i;

        AnonymousClass1(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
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
                a k = InAppMessageManager.this.k(this.a);
                if (k != null) {
                    InAppMessageManager.this.f.getContentResolver().update(a.a(InAppMessageManager.this.f).k, new a(this.a, this.b, k.d + this.c, k.e + this.d, k.f + this.e, k.g + this.f, k.h + this.g, k.i + this.h, k.j).a(), "MsgId=?", new String[]{this.a});
                } else {
                    InAppMessageManager.this.f.getContentResolver().insert(a.a(InAppMessageManager.this.f).k, new a(this.a, this.b, this.c, this.d, this.e, this.f, this.g, this.h, this.i).a());
                }
                UMLog.mutlInfo(InAppMessageManager.d, 2, "store in app cache log success");
            } catch (Exception e) {
                UMLog.mutlInfo(InAppMessageManager.d, 0, "store in app cache log fail");
                e.printStackTrace();
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private a k(String str) {
        Cursor query = this.f.getContentResolver().query(a.a(this.f).k, null, "MsgId=?", new String[]{str}, null);
        a aVar = query.moveToFirst() ? new a(query) : null;
        if (query != null) {
            query.close();
        }
        return aVar;
    }

    /* access modifiers changed from: package-private */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x003b, code lost:
        if (0 == 0) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003d, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0040, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0032, code lost:
        if (r1 != null) goto L_0x003d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<com.umeng.message.inapp.a> j() {
        /*
            r9 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            android.content.Context r2 = r9.f     // Catch:{ Exception -> 0x0037 }
            android.content.ContentResolver r3 = r2.getContentResolver()     // Catch:{ Exception -> 0x0037 }
            android.content.Context r2 = r9.f     // Catch:{ Exception -> 0x0037 }
            com.umeng.message.provider.a r2 = com.umeng.message.provider.a.a(r2)     // Catch:{ Exception -> 0x0037 }
            android.net.Uri r4 = r2.k     // Catch:{ Exception -> 0x0037 }
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r1 = r3.query(r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x0037 }
            r2 = 0
            if (r1 == 0) goto L_0x0023
            boolean r2 = r1.moveToFirst()     // Catch:{ Exception -> 0x0037 }
        L_0x0023:
            if (r2 == 0) goto L_0x0032
            com.umeng.message.inapp.a r2 = new com.umeng.message.inapp.a     // Catch:{ Exception -> 0x0037 }
            r2.<init>(r1)     // Catch:{ Exception -> 0x0037 }
            r0.add(r2)     // Catch:{ Exception -> 0x0037 }
            boolean r2 = r1.moveToNext()     // Catch:{ Exception -> 0x0037 }
            goto L_0x0023
        L_0x0032:
            if (r1 == 0) goto L_0x0040
            goto L_0x003d
        L_0x0035:
            r0 = move-exception
            goto L_0x0041
        L_0x0037:
            r2 = move-exception
            r2.printStackTrace()     // Catch:{ all -> 0x0035 }
            if (r1 == 0) goto L_0x0040
        L_0x003d:
            r1.close()
        L_0x0040:
            return r0
        L_0x0041:
            if (r1 == 0) goto L_0x0046
            r1.close()
        L_0x0046:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.inapp.InAppMessageManager.j():java.util.ArrayList");
    }

    /* access modifiers changed from: package-private */
    public boolean h(String str) {
        return this.f.getContentResolver().delete(a.a(this.f).k, "MsgId=?", new String[]{str}) == 1;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0046, code lost:
        if (r1 != null) goto L_0x0048;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0048, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0052, code lost:
        if (0 == 0) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0055, code lost:
        return r12;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(java.lang.String r11, java.lang.String r12) {
        /*
            r10 = this;
            java.lang.String r0 = "tempvalue"
            r1 = 0
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch:{ Exception -> 0x004e }
            r2.<init>()     // Catch:{ Exception -> 0x004e }
            java.lang.String r3 = "tempkey"
            r2.put(r3, r11)     // Catch:{ Exception -> 0x004e }
            java.lang.String r7 = "tempkey=?"
            r2 = 1
            java.lang.String[] r8 = new java.lang.String[r2]     // Catch:{ Exception -> 0x004e }
            r2 = 0
            r8[r2] = r11     // Catch:{ Exception -> 0x004e }
            android.content.Context r11 = r10.f     // Catch:{ Exception -> 0x004e }
            android.content.ContentResolver r4 = r11.getContentResolver()     // Catch:{ Exception -> 0x004e }
            android.content.Context r11 = r10.f     // Catch:{ Exception -> 0x004e }
            com.umeng.message.provider.a r11 = com.umeng.message.provider.a.a(r11)     // Catch:{ Exception -> 0x004e }
            android.net.Uri r5 = r11.c     // Catch:{ Exception -> 0x004e }
            java.lang.String[] r6 = new java.lang.String[]{r0}     // Catch:{ Exception -> 0x004e }
            r9 = 0
            android.database.Cursor r1 = r4.query(r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x004e }
            if (r1 != 0) goto L_0x0037
            if (r1 == 0) goto L_0x0036
            r1.close()
        L_0x0036:
            return r12
        L_0x0037:
            boolean r11 = r1.moveToFirst()
            if (r11 == 0) goto L_0x0046
            int r11 = r1.getColumnIndex(r0)
            java.lang.String r11 = r1.getString(r11)
            r12 = r11
        L_0x0046:
            if (r1 == 0) goto L_0x0055
        L_0x0048:
            r1.close()
            goto L_0x0055
        L_0x004c:
            r11 = move-exception
            goto L_0x0056
        L_0x004e:
            r11 = move-exception
            r11.printStackTrace()     // Catch:{ all -> 0x004c }
            if (r1 == 0) goto L_0x0055
            goto L_0x0048
        L_0x0055:
            return r12
        L_0x0056:
            if (r1 == 0) goto L_0x005b
            r1.close()
        L_0x005b:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.inapp.InAppMessageManager.a(java.lang.String, java.lang.String):java.lang.String");
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.inapp.InAppMessageManager$2  reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ String b;

        AnonymousClass2(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cursor cursor = null;
            try {
                String[] strArr = {this.a};
                cursor = InAppMessageManager.this.f.getContentResolver().query(a.a(InAppMessageManager.this.f).c, new String[]{InAppMessageManager.j}, "tempkey=?", strArr, null);
                if (cursor == null) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put(InAppMessageManager.i, this.a);
                    contentValues.put(InAppMessageManager.j, this.b);
                    InAppMessageManager.this.f.getContentResolver().insert(a.a(InAppMessageManager.this.f).c, contentValues);
                } else if (cursor.moveToFirst()) {
                    ContentValues contentValues2 = new ContentValues();
                    contentValues2.put(InAppMessageManager.j, this.b);
                    InAppMessageManager.this.f.getContentResolver().update(a.a(InAppMessageManager.this.f).c, contentValues2, "tempkey=?", strArr);
                } else {
                    ContentValues contentValues3 = new ContentValues();
                    contentValues3.put(InAppMessageManager.i, this.a);
                    contentValues3.put(InAppMessageManager.j, this.b);
                    InAppMessageManager.this.f.getContentResolver().insert(a.a(InAppMessageManager.this.f).c, contentValues3);
                }
                if (cursor == null) {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (0 == 0) {
                    return;
                }
            } catch (Throwable th) {
                if (0 != 0) {
                    cursor.close();
                }
                throw th;
            }
            cursor.close();
        }
    }

    private void b(String str, String str2) {
        e.a(new AnonymousClass2(str, str2));
    }

    /* renamed from: com.umeng.message.inapp.InAppMessageManager$3  reason: invalid class name */
    class AnonymousClass3 implements Runnable {
        final /* synthetic */ String a;

        AnonymousClass3(String str) {
            this.a = str;
        }

        @Override // java.lang.Runnable
        public void run() {
            Cursor cursor = null;
            try {
                new ContentValues().put(InAppMessageManager.i, this.a);
                cursor = InAppMessageManager.this.f.getContentResolver().query(a.a(InAppMessageManager.this.f).c, new String[]{InAppMessageManager.j}, null, null, null);
                if (cursor != null) {
                    InAppMessageManager.this.f.getContentResolver().delete(a.a(InAppMessageManager.this.f).c, "tempkey=?", new String[]{this.a});
                }
                if (cursor == null) {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                if (0 == 0) {
                    return;
                }
            } catch (Throwable th) {
                if (0 != 0) {
                    cursor.close();
                }
                throw th;
            }
            cursor.close();
        }
    }

    /* access modifiers changed from: package-private */
    public void i(String str) {
        e.a(new AnonymousClass3(str));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.inapp.InAppMessageManager$4  reason: invalid class name */
    public class AnonymousClass4 implements Runnable {
        final /* synthetic */ File a;

        AnonymousClass4(File file) {
            this.a = file;
        }

        @Override // java.lang.Runnable
        public void run() {
            File file = this.a;
            if (file != null && file.exists() && this.a.canWrite() && this.a.isDirectory()) {
                File[] listFiles = this.a.listFiles();
                for (File file2 : listFiles) {
                    if (!file2.isDirectory()) {
                        file2.delete();
                    }
                }
                this.a.delete();
            }
        }
    }

    /* access modifiers changed from: package-private */
    public void a(File file) {
        e.a(new AnonymousClass4(file));
    }
}
