package com.umeng.analytics.pro;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.pro.d;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.service.UMGlobalContext;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.internal.PreferenceWrapper;
import com.umeng.commonsdk.utils.UMUtils;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: UMStoreManager */
public class h {
    public static final int a = 2049;
    public static final int b = 2050;
    private static final int c = 1000;
    private static Context d = null;
    private static String e = null;
    private static final String f = "umeng+";
    private static final String g = "ek__id";
    private static final String h = "ek_key";
    private List<String> i;
    private List<Integer> j;
    private String k;
    private List<String> l;

    /* compiled from: UMStoreManager */
    public enum a {
        AUTOPAGE,
        PAGE,
        BEGIN,
        END,
        NEWSESSION,
        INSTANTSESSIONBEGIN
    }

    /* compiled from: UMStoreManager */
    /* access modifiers changed from: private */
    public static class b {
        private static final h a = new h();

        private b() {
        }
    }

    private h() {
        this.i = new ArrayList();
        this.j = new ArrayList();
        this.k = null;
        this.l = new ArrayList();
    }

    public static h a(Context context) {
        h hVar = b.a;
        if (d == null && context != null) {
            d = context.getApplicationContext();
            hVar.k();
        }
        return hVar;
    }

    private void k() {
        synchronized (this) {
            l();
            this.i.clear();
            this.l.clear();
            this.j.clear();
        }
    }

    public void a() {
        this.i.clear();
    }

    public void b() {
        this.l.clear();
    }

    public boolean c() {
        return this.l.isEmpty();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0099, code lost:
        if (r3 != null) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x009d, code lost:
        if (r3 != null) goto L_0x009f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:?, code lost:
        r3.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00b2, code lost:
        if (r3 != null) goto L_0x009f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(org.json.JSONArray r11) {
        /*
            r10 = this;
            java.lang.String r0 = "__t"
            java.lang.String r1 = "__i"
            r2 = 0
            android.content.Context r3 = com.umeng.analytics.pro.h.d     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ac, all -> 0x009c }
            com.umeng.analytics.pro.f r3 = com.umeng.analytics.pro.f.a(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ac, all -> 0x009c }
            android.database.sqlite.SQLiteDatabase r3 = r3.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ac, all -> 0x009c }
            r3.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, all -> 0x009d }
            r4 = 0
        L_0x0015:
            int r5 = r11.length()     // Catch:{ SQLiteDatabaseCorruptException -> 0x00ad, all -> 0x009d }
            if (r4 >= r5) goto L_0x0096
            org.json.JSONObject r5 = r11.getJSONObject(r4)     // Catch:{ Exception -> 0x0093 }
            android.content.ContentValues r6 = new android.content.ContentValues     // Catch:{ Exception -> 0x0093 }
            r6.<init>()     // Catch:{ Exception -> 0x0093 }
            java.lang.String r7 = r5.optString(r1)     // Catch:{ Exception -> 0x0093 }
            boolean r8 = android.text.TextUtils.isEmpty(r7)     // Catch:{ Exception -> 0x0093 }
            java.lang.String r9 = "-1"
            if (r8 != 0) goto L_0x0037
            boolean r8 = r9.equals(r7)
            if (r8 == 0) goto L_0x0046
        L_0x0037:
            com.umeng.analytics.pro.t r7 = com.umeng.analytics.pro.t.a()
            java.lang.String r7 = r7.b()
            boolean r8 = android.text.TextUtils.isEmpty(r7)
            if (r8 == 0) goto L_0x0046
            r7 = r9
        L_0x0046:
            r6.put(r1, r7)
            java.lang.String r7 = "__e"
            java.lang.String r8 = "id"
            java.lang.String r8 = r5.optString(r8)
            r6.put(r7, r8)
            int r7 = r5.optInt(r0)
            java.lang.Integer r7 = java.lang.Integer.valueOf(r7)
            r6.put(r0, r7)
            java.lang.String r7 = "__av"
            android.content.Context r8 = com.umeng.analytics.pro.h.d
            java.lang.String r8 = com.umeng.commonsdk.utils.UMUtils.getAppVersionName(r8)
            r6.put(r7, r8)
            java.lang.String r7 = "__vc"
            android.content.Context r8 = com.umeng.analytics.pro.h.d
            java.lang.String r8 = com.umeng.commonsdk.utils.UMUtils.getAppVersionCode(r8)
            r6.put(r7, r8)
            r5.remove(r1)
            r5.remove(r0)
            java.lang.String r7 = "__s"
            java.lang.String r5 = r5.toString()
            java.lang.String r5 = r10.c(r5)
            r6.put(r7, r5)
            java.lang.String r5 = "__et"
            r3.insert(r5, r2, r6)
        L_0x0093:
            int r4 = r4 + 1
            goto L_0x0015
        L_0x0096:
            r3.setTransactionSuccessful()
            if (r3 == 0) goto L_0x00a2
            goto L_0x009f
        L_0x009c:
            r3 = r2
        L_0x009d:
            if (r3 == 0) goto L_0x00a2
        L_0x009f:
            r3.endTransaction()     // Catch:{ all -> 0x00a2 }
        L_0x00a2:
            android.content.Context r11 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r11 = com.umeng.analytics.pro.f.a(r11)
            r11.b()
            goto L_0x00b5
        L_0x00ac:
            r3 = r2
        L_0x00ad:
            android.content.Context r11 = com.umeng.analytics.pro.h.d     // Catch:{ all -> 0x00b6 }
            com.umeng.analytics.pro.g.a(r11)     // Catch:{ all -> 0x00b6 }
            if (r3 == 0) goto L_0x00a2
            goto L_0x009f
        L_0x00b5:
            return
        L_0x00b6:
            r11 = move-exception
            if (r3 == 0) goto L_0x00bc
            r3.endTransaction()     // Catch:{ all -> 0x00bc }
        L_0x00bc:
            android.content.Context r0 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r0 = com.umeng.analytics.pro.f.a(r0)
            r0.b()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.h.a(org.json.JSONArray):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0058, code lost:
        if (r1 != null) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x006d, code lost:
        if (r1 != null) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0054, code lost:
        if (r1 != null) goto L_0x005a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.lang.String r5, java.lang.String r6, int r7) {
        /*
            r4 = this;
            r0 = 0
            android.content.Context r1 = com.umeng.analytics.pro.h.d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0067, all -> 0x0057 }
            com.umeng.analytics.pro.f r1 = com.umeng.analytics.pro.f.a(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0067, all -> 0x0057 }
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0067, all -> 0x0057 }
            r1.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, all -> 0x0058 }
            android.content.ContentValues r2 = new android.content.ContentValues     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, all -> 0x0058 }
            r2.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, all -> 0x0058 }
            java.lang.String r3 = "__i"
            r2.put(r3, r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, all -> 0x0058 }
            java.lang.String r5 = r4.c(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, all -> 0x0058 }
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, all -> 0x0058 }
            if (r6 != 0) goto L_0x0051
            java.lang.String r6 = "__a"
            r2.put(r6, r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, all -> 0x0058 }
            java.lang.String r5 = "__t"
            java.lang.Integer r6 = java.lang.Integer.valueOf(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, all -> 0x0058 }
            r2.put(r5, r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, all -> 0x0058 }
            java.lang.String r5 = "__av"
            android.content.Context r6 = com.umeng.analytics.pro.h.d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, all -> 0x0058 }
            java.lang.String r6 = com.umeng.commonsdk.utils.UMUtils.getAppVersionName(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, all -> 0x0058 }
            r2.put(r5, r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, all -> 0x0058 }
            java.lang.String r5 = "__vc"
            android.content.Context r6 = com.umeng.analytics.pro.h.d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, all -> 0x0058 }
            java.lang.String r6 = com.umeng.commonsdk.utils.UMUtils.getAppVersionCode(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, all -> 0x0058 }
            r2.put(r5, r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, all -> 0x0058 }
            java.lang.String r5 = "__er"
            r1.insert(r5, r0, r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, all -> 0x0058 }
        L_0x0051:
            r1.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0068, all -> 0x0058 }
            if (r1 == 0) goto L_0x005d
            goto L_0x005a
        L_0x0057:
            r1 = r0
        L_0x0058:
            if (r1 == 0) goto L_0x005d
        L_0x005a:
            r1.endTransaction()     // Catch:{ all -> 0x005d }
        L_0x005d:
            android.content.Context r5 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r5 = com.umeng.analytics.pro.f.a(r5)
            r5.b()
            goto L_0x0070
        L_0x0067:
            r1 = r0
        L_0x0068:
            android.content.Context r5 = com.umeng.analytics.pro.h.d     // Catch:{ all -> 0x0072 }
            com.umeng.analytics.pro.g.a(r5)     // Catch:{ all -> 0x0072 }
            if (r1 == 0) goto L_0x005d
            goto L_0x005a
        L_0x0070:
            r5 = 0
            return r5
        L_0x0072:
            r5 = move-exception
            if (r1 == 0) goto L_0x0078
            r1.endTransaction()     // Catch:{ all -> 0x0078 }
        L_0x0078:
            android.content.Context r6 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r6 = com.umeng.analytics.pro.f.a(r6)
            r6.b()
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.h.a(java.lang.String, java.lang.String, int):boolean");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0071, code lost:
        if (r0 != null) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0074, code lost:
        if (r0 == null) goto L_0x0079;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0088, code lost:
        if (r0 == null) goto L_0x0079;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void d() {
        /*
            r6 = this;
            r0 = 0
            android.content.Context r1 = com.umeng.analytics.pro.h.d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0083, all -> 0x0074 }
            com.umeng.analytics.pro.f r1 = com.umeng.analytics.pro.f.a(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0083, all -> 0x0074 }
            android.database.sqlite.SQLiteDatabase r0 = r1.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0083, all -> 0x0074 }
            r0.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0083, all -> 0x0074 }
            com.umeng.analytics.pro.t r1 = com.umeng.analytics.pro.t.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0083, all -> 0x0074 }
            java.lang.String r1 = r1.c()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0083, all -> 0x0074 }
            boolean r2 = android.text.TextUtils.isEmpty(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0083, all -> 0x0074 }
            if (r2 == 0) goto L_0x002b
            if (r0 == 0) goto L_0x0021
            r0.endTransaction()     // Catch:{ all -> 0x0021 }
        L_0x0021:
            android.content.Context r0 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r0 = com.umeng.analytics.pro.f.a(r0)
            r0.b()
            return
        L_0x002b:
            java.lang.String r2 = ""
            java.lang.String r3 = "-1"
            java.lang.String[] r2 = new java.lang.String[]{r2, r3}
            r3 = 0
        L_0x0036:
            int r4 = r2.length
            if (r3 >= r4) goto L_0x006e
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            r4.<init>()
            java.lang.String r5 = "update __et set __i=\""
            r4.append(r5)
            r4.append(r1)
            java.lang.String r5 = "\" where "
            r4.append(r5)
            java.lang.String r5 = "__i"
            r4.append(r5)
            java.lang.String r5 = "=\""
            r4.append(r5)
            r5 = r2[r3]
            r4.append(r5)
            java.lang.String r5 = "\""
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r0.execSQL(r4)
            int r3 = r3 + 1
            goto L_0x0036
        L_0x006e:
            r0.setTransactionSuccessful()
            if (r0 == 0) goto L_0x0079
            goto L_0x0076
        L_0x0074:
            if (r0 == 0) goto L_0x0079
        L_0x0076:
            r0.endTransaction()     // Catch:{ all -> 0x0079 }
        L_0x0079:
            android.content.Context r0 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r0 = com.umeng.analytics.pro.f.a(r0)
            r0.b()
            goto L_0x008b
        L_0x0083:
            android.content.Context r1 = com.umeng.analytics.pro.h.d     // Catch:{ all -> 0x008c }
            com.umeng.analytics.pro.g.a(r1)     // Catch:{ all -> 0x008c }
            if (r0 == 0) goto L_0x0079
            goto L_0x0076
        L_0x008b:
            return
        L_0x008c:
            r1 = move-exception
            if (r0 == 0) goto L_0x0092
            r0.endTransaction()     // Catch:{ all -> 0x0092 }
        L_0x0092:
            android.content.Context r0 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r0 = com.umeng.analytics.pro.f.a(r0)
            r0.b()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.h.d():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0084, code lost:
        if (r3 != null) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0088, code lost:
        if (r3 != null) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r3.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x009d, code lost:
        if (r3 != null) goto L_0x008a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(java.lang.String r7, org.json.JSONObject r8, com.umeng.analytics.pro.h.a r9) {
        /*
            r6 = this;
            java.lang.String r0 = "__e"
            r1 = 0
            if (r8 != 0) goto L_0x0007
            return r1
        L_0x0007:
            r2 = 0
            android.content.Context r3 = com.umeng.analytics.pro.h.d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0097, all -> 0x0087 }
            com.umeng.analytics.pro.f r3 = com.umeng.analytics.pro.f.a(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0097, all -> 0x0087 }
            android.database.sqlite.SQLiteDatabase r3 = r3.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0097, all -> 0x0087 }
            r3.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            com.umeng.analytics.pro.h$a r4 = com.umeng.analytics.pro.h.a.BEGIN     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            if (r9 != r4) goto L_0x0054
            java.lang.Object r8 = r8.opt(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            java.lang.Long r8 = (java.lang.Long) r8     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            long r8 = r8.longValue()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            android.content.ContentValues r4 = new android.content.ContentValues     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            r4.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            java.lang.String r5 = "__ii"
            r4.put(r5, r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            java.lang.String r7 = java.lang.String.valueOf(r8)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            r4.put(r0, r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            java.lang.String r7 = "__av"
            android.content.Context r8 = com.umeng.analytics.pro.h.d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            java.lang.String r8 = com.umeng.commonsdk.utils.UMUtils.getAppVersionName(r8)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            r4.put(r7, r8)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            java.lang.String r7 = "__vc"
            android.content.Context r8 = com.umeng.analytics.pro.h.d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            java.lang.String r8 = com.umeng.commonsdk.utils.UMUtils.getAppVersionCode(r8)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            r4.put(r7, r8)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            java.lang.String r7 = "__sd"
            r3.insert(r7, r2, r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            goto L_0x0081
        L_0x0054:
            com.umeng.analytics.pro.h$a r0 = com.umeng.analytics.pro.h.a.INSTANTSESSIONBEGIN     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            if (r9 != r0) goto L_0x005c
            r6.b(r7, r8, r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            goto L_0x0081
        L_0x005c:
            com.umeng.analytics.pro.h$a r0 = com.umeng.analytics.pro.h.a.END     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            if (r9 != r0) goto L_0x0064
            r6.a(r7, r8, r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            goto L_0x0081
        L_0x0064:
            com.umeng.analytics.pro.h$a r0 = com.umeng.analytics.pro.h.a.PAGE     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            if (r9 != r0) goto L_0x006f
            java.lang.String r9 = "__a"
            r6.a(r7, r8, r3, r9)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            goto L_0x0081
        L_0x006f:
            com.umeng.analytics.pro.h$a r0 = com.umeng.analytics.pro.h.a.AUTOPAGE     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            if (r9 != r0) goto L_0x007a
            java.lang.String r9 = "__b"
            r6.a(r7, r8, r3, r9)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            goto L_0x0081
        L_0x007a:
            com.umeng.analytics.pro.h$a r0 = com.umeng.analytics.pro.h.a.NEWSESSION     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            if (r9 != r0) goto L_0x0081
            r6.c(r7, r8, r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
        L_0x0081:
            r3.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0098, all -> 0x0088 }
            if (r3 == 0) goto L_0x008d
            goto L_0x008a
        L_0x0087:
            r3 = r2
        L_0x0088:
            if (r3 == 0) goto L_0x008d
        L_0x008a:
            r3.endTransaction()     // Catch:{ all -> 0x008d }
        L_0x008d:
            android.content.Context r7 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r7 = com.umeng.analytics.pro.f.a(r7)
            r7.b()
            goto L_0x00a0
        L_0x0097:
            r3 = r2
        L_0x0098:
            android.content.Context r7 = com.umeng.analytics.pro.h.d     // Catch:{ all -> 0x00a1 }
            com.umeng.analytics.pro.g.a(r7)     // Catch:{ all -> 0x00a1 }
            if (r3 == 0) goto L_0x008d
            goto L_0x008a
        L_0x00a0:
            return r1
        L_0x00a1:
            r7 = move-exception
            if (r3 == 0) goto L_0x00a7
            r3.endTransaction()     // Catch:{ all -> 0x00a7 }
        L_0x00a7:
            android.content.Context r8 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r8 = com.umeng.analytics.pro.f.a(r8)
            r8.b()
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.h.a(java.lang.String, org.json.JSONObject, com.umeng.analytics.pro.h$a):boolean");
    }

    private void a(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase) {
        try {
            long longValue = ((Long) jSONObject.opt(d.C0178d.a.g)).longValue();
            long j = 0;
            Object opt = jSONObject.opt(d.C0178d.a.h);
            if (opt != null && (opt instanceof Long)) {
                j = ((Long) opt).longValue();
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("__sp");
            JSONObject optJSONObject2 = jSONObject.optJSONObject("__pp");
            String str2 = "";
            String c2 = (optJSONObject == null || optJSONObject.length() <= 0) ? str2 : c(optJSONObject.toString());
            if (optJSONObject2 != null && optJSONObject2.length() > 0) {
                str2 = c(optJSONObject2.toString());
            }
            sQLiteDatabase.execSQL("update __sd set __f=\"" + longValue + "\", " + d.C0178d.a.h + "=\"" + j + "\", __sp=\"" + c2 + "\", __pp=\"" + str2 + "\" where __ii=\"" + str + "\"");
        } catch (Throwable unused) {
        }
    }

    private void b(String str, JSONObject jSONObject, SQLiteDatabase sQLiteDatabase) {
        try {
            long longValue = ((Long) jSONObject.get("__e")).longValue();
            JSONObject optJSONObject = jSONObject.optJSONObject("__sp");
            JSONObject optJSONObject2 = jSONObject.optJSONObject("__pp");
            String str2 = "";
            String c2 = (optJSONObject == null || optJSONObject.length() <= 0) ? str2 : c(optJSONObject.toString());
            if (optJSONObject2 != null && optJSONObject2.length() > 0) {
                str2 = c(optJSONObject2.toString());
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put("__ii", str);
            contentValues.put("__e", String.valueOf(longValue));
            contentValues.put("__sp", c2);
            contentValues.put("__pp", str2);
            contentValues.put("__av", UMGlobalContext.getInstance(d).getAppVersion());
            contentValues.put("__vc", UMUtils.getAppVersionCode(d));
            sQLiteDatabase.insert(d.c.a, null, contentValues);
        } catch (Throwable unused) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0032, code lost:
        if (r4 != null) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0053, code lost:
        if (r4 == null) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0055, code lost:
        r4.endTransaction();
     */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x003b A[SYNTHETIC, Splitter:B:15:0x003b] */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0040 A[Catch:{ Exception -> 0x0043 }] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0050  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long a(java.lang.String r8) {
        /*
            r7 = this;
            java.lang.String r0 = "select __f from __sd where __ii=?"
            r1 = 0
            r2 = 0
            android.content.Context r4 = com.umeng.analytics.pro.h.d     // Catch:{ Exception -> 0x004d, all -> 0x0037 }
            com.umeng.analytics.pro.f r4 = com.umeng.analytics.pro.f.a(r4)     // Catch:{ Exception -> 0x004d, all -> 0x0037 }
            android.database.sqlite.SQLiteDatabase r4 = r4.a()     // Catch:{ Exception -> 0x004d, all -> 0x0037 }
            r4.beginTransaction()     // Catch:{ Exception -> 0x004e, all -> 0x0035 }
            r5 = 1
            java.lang.String[] r5 = new java.lang.String[r5]     // Catch:{ Exception -> 0x004e, all -> 0x0035 }
            r6 = 0
            r5[r6] = r8     // Catch:{ Exception -> 0x004e, all -> 0x0035 }
            android.database.Cursor r1 = r4.rawQuery(r0, r5)     // Catch:{ Exception -> 0x004e, all -> 0x0035 }
            if (r1 == 0) goto L_0x002d
            r1.moveToFirst()     // Catch:{ Exception -> 0x004e, all -> 0x0035 }
            java.lang.String r8 = "__f"
            int r8 = r1.getColumnIndex(r8)     // Catch:{ Exception -> 0x004e, all -> 0x0035 }
            long r2 = r1.getLong(r8)     // Catch:{ Exception -> 0x004e, all -> 0x0035 }
        L_0x002d:
            if (r1 == 0) goto L_0x0032
            r1.close()     // Catch:{ Exception -> 0x0058 }
        L_0x0032:
            if (r4 == 0) goto L_0x0058
            goto L_0x0055
        L_0x0035:
            r8 = move-exception
            goto L_0x0039
        L_0x0037:
            r8 = move-exception
            r4 = r1
        L_0x0039:
            if (r1 == 0) goto L_0x003e
            r1.close()     // Catch:{ Exception -> 0x0043 }
        L_0x003e:
            if (r4 == 0) goto L_0x0043
            r4.endTransaction()     // Catch:{ Exception -> 0x0043 }
        L_0x0043:
            android.content.Context r0 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r0 = com.umeng.analytics.pro.f.a(r0)
            r0.b()
            throw r8
        L_0x004d:
            r4 = r1
        L_0x004e:
            if (r1 == 0) goto L_0x0053
            r1.close()
        L_0x0053:
            if (r4 == 0) goto L_0x0058
        L_0x0055:
            r4.endTransaction()
        L_0x0058:
            android.content.Context r8 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r8 = com.umeng.analytics.pro.f.a(r8)
            r8.b()
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.h.a(java.lang.String):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void c(java.lang.String r10, org.json.JSONObject r11, android.database.sqlite.SQLiteDatabase r12) {
        /*
            r9 = this;
            java.lang.String r0 = "__d"
            r1 = 0
            org.json.JSONObject r2 = r11.optJSONObject(r0)     // Catch:{ all -> 0x00ed }
            if (r2 == 0) goto L_0x002c
            java.lang.String r3 = "select __d from __sd where __ii=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch:{ all -> 0x00ed }
            r5 = 0
            r4[r5] = r10     // Catch:{ all -> 0x00ed }
            android.database.Cursor r3 = r12.rawQuery(r3, r4)     // Catch:{ all -> 0x00ed }
            if (r3 == 0) goto L_0x002d
        L_0x0019:
            boolean r4 = r3.moveToNext()     // Catch:{ all -> 0x00ee }
            if (r4 == 0) goto L_0x002d
            int r1 = r3.getColumnIndex(r0)     // Catch:{ all -> 0x00ee }
            java.lang.String r1 = r3.getString(r1)     // Catch:{ all -> 0x00ee }
            java.lang.String r1 = r9.d(r1)     // Catch:{ all -> 0x00ee }
            goto L_0x0019
        L_0x002c:
            r3 = r1
        L_0x002d:
            java.lang.String r0 = "\""
            java.lang.String r4 = "=\""
            java.lang.String r5 = "__ii"
            java.lang.String r6 = "\" where "
            if (r2 == 0) goto L_0x0080
            org.json.JSONArray r7 = new org.json.JSONArray
            r7.<init>()
            boolean r8 = android.text.TextUtils.isEmpty(r1)
            if (r8 != 0) goto L_0x004b
            org.json.JSONArray r7 = new org.json.JSONArray
            r7.<init>(r1)
        L_0x004b:
            r7.put(r2)
            java.lang.String r1 = r7.toString()
            java.lang.String r1 = r9.c(r1)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x0080
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r7 = "update  __sd set __d=\""
            r2.append(r7)
            r2.append(r1)
            r2.append(r6)
            r2.append(r5)
            r2.append(r4)
            r2.append(r10)
            r2.append(r0)
            java.lang.String r1 = r2.toString()
            r12.execSQL(r1)
        L_0x0080:
            java.lang.String r1 = "__c"
            org.json.JSONObject r1 = r11.optJSONObject(r1)
            if (r1 == 0) goto L_0x00bb
            java.lang.String r1 = r1.toString()
            java.lang.String r1 = r9.c(r1)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x00bb
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r7 = "update  __sd set __c=\""
            r2.append(r7)
            r2.append(r1)
            r2.append(r6)
            r2.append(r5)
            r2.append(r4)
            r2.append(r10)
            r2.append(r0)
            java.lang.String r1 = r2.toString()
            r12.execSQL(r1)
        L_0x00bb:
            java.lang.String r1 = "__f"
            long r1 = r11.optLong(r1)
            java.lang.StringBuilder r11 = new java.lang.StringBuilder
            r11.<init>()
            java.lang.String r7 = "update  __sd set __f=\""
            r11.append(r7)
            java.lang.String r1 = java.lang.String.valueOf(r1)
            r11.append(r1)
            r11.append(r6)
            r11.append(r5)
            r11.append(r4)
            r11.append(r10)
            r11.append(r0)
            java.lang.String r10 = r11.toString()
            r12.execSQL(r10)
            if (r3 == 0) goto L_0x00f3
            goto L_0x00f0
        L_0x00ed:
            r3 = r1
        L_0x00ee:
            if (r3 == 0) goto L_0x00f3
        L_0x00f0:
            r3.close()
        L_0x00f3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.h.c(java.lang.String, org.json.JSONObject, android.database.sqlite.SQLiteDatabase):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:58:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r8, org.json.JSONObject r9, android.database.sqlite.SQLiteDatabase r10, java.lang.String r11) throws org.json.JSONException {
        /*
            r7 = this;
            java.lang.String r0 = "=\""
            java.lang.String r1 = "__ii"
            java.lang.String r2 = "__b"
            java.lang.String r3 = "__a"
            r4 = 0
            boolean r5 = r3.equals(r11)     // Catch:{ all -> 0x00f1 }
            if (r5 == 0) goto L_0x0020
            org.json.JSONArray r9 = r9.optJSONArray(r3)     // Catch:{ all -> 0x00f1 }
            if (r9 == 0) goto L_0x001f
            int r2 = r9.length()     // Catch:{ all -> 0x00f1 }
            if (r2 > 0) goto L_0x0034
        L_0x001f:
            return
        L_0x0020:
            boolean r3 = r2.equals(r11)     // Catch:{ all -> 0x00f1 }
            if (r3 == 0) goto L_0x0033
            org.json.JSONArray r9 = r9.optJSONArray(r2)     // Catch:{ all -> 0x00f1 }
            if (r9 == 0) goto L_0x0032
            int r2 = r9.length()     // Catch:{ all -> 0x00f1 }
            if (r2 > 0) goto L_0x0034
        L_0x0032:
            return
        L_0x0033:
            r9 = r4
        L_0x0034:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x00f1 }
            r2.<init>()     // Catch:{ all -> 0x00f1 }
            java.lang.String r3 = "select "
            r2.append(r3)     // Catch:{ all -> 0x00f1 }
            r2.append(r11)     // Catch:{ all -> 0x00f1 }
            java.lang.String r3 = " from "
            r2.append(r3)     // Catch:{ all -> 0x00f1 }
            java.lang.String r3 = "__sd"
            r2.append(r3)     // Catch:{ all -> 0x00f1 }
            java.lang.String r3 = " where "
            r2.append(r3)     // Catch:{ all -> 0x00f1 }
            r2.append(r1)     // Catch:{ all -> 0x00f1 }
            java.lang.String r3 = "=?"
            r2.append(r3)     // Catch:{ all -> 0x00f1 }
            java.lang.String r2 = r2.toString()     // Catch:{ all -> 0x00f1 }
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ all -> 0x00f1 }
            r5 = 0
            r3[r5] = r8     // Catch:{ all -> 0x00f1 }
            android.database.Cursor r2 = r10.rawQuery(r2, r3)     // Catch:{ all -> 0x00f1 }
            if (r2 == 0) goto L_0x0080
        L_0x006d:
            boolean r3 = r2.moveToNext()     // Catch:{ all -> 0x00f2 }
            if (r3 == 0) goto L_0x0080
            int r3 = r2.getColumnIndex(r11)     // Catch:{ all -> 0x00f2 }
            java.lang.String r3 = r2.getString(r3)     // Catch:{ all -> 0x00f2 }
            java.lang.String r4 = r7.d(r3)     // Catch:{ all -> 0x00f2 }
            goto L_0x006d
        L_0x0080:
            org.json.JSONArray r3 = new org.json.JSONArray     // Catch:{ all -> 0x00f2 }
            r3.<init>()     // Catch:{ all -> 0x00f2 }
            boolean r6 = android.text.TextUtils.isEmpty(r4)     // Catch:{ all -> 0x00f2 }
            if (r6 != 0) goto L_0x0090
            org.json.JSONArray r3 = new org.json.JSONArray     // Catch:{ all -> 0x00f2 }
            r3.<init>(r4)     // Catch:{ all -> 0x00f2 }
        L_0x0090:
            int r4 = r3.length()     // Catch:{ all -> 0x00f2 }
            r6 = 1000(0x3e8, float:1.401E-42)
            if (r4 <= r6) goto L_0x009e
            if (r2 == 0) goto L_0x009d
            r2.close()
        L_0x009d:
            return
        L_0x009e:
            int r4 = r9.length()
            if (r5 >= r4) goto L_0x00b0
            org.json.JSONObject r4 = r9.getJSONObject(r5)     // Catch:{ JSONException -> 0x00ad }
            if (r4 == 0) goto L_0x00ad
            r3.put(r4)
        L_0x00ad:
            int r5 = r5 + 1
            goto L_0x009e
        L_0x00b0:
            java.lang.String r9 = r3.toString()
            java.lang.String r9 = r7.c(r9)
            boolean r3 = android.text.TextUtils.isEmpty(r9)
            if (r3 != 0) goto L_0x00ee
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = "update __sd set "
            r3.append(r4)
            r3.append(r11)
            r3.append(r0)
            r3.append(r9)
            java.lang.String r9 = "\" where "
            r3.append(r9)
            r3.append(r1)
            r3.append(r0)
            r3.append(r8)
            java.lang.String r8 = "\""
            r3.append(r8)
            java.lang.String r8 = r3.toString()
            r10.execSQL(r8)
        L_0x00ee:
            if (r2 == 0) goto L_0x00f7
            goto L_0x00f4
        L_0x00f1:
            r2 = r4
        L_0x00f2:
            if (r2 == 0) goto L_0x00f7
        L_0x00f4:
            r2.close()
        L_0x00f7:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.h.a(java.lang.String, org.json.JSONObject, android.database.sqlite.SQLiteDatabase, java.lang.String):void");
    }

    public boolean e() {
        return this.i.isEmpty();
    }

    public JSONObject a(boolean z) {
        a();
        this.j.clear();
        JSONObject jSONObject = new JSONObject();
        if (!z) {
            a(jSONObject, z);
            b(jSONObject, (String) null);
            a(jSONObject, (String) null);
        } else {
            String a2 = a(jSONObject, z);
            if (!TextUtils.isEmpty(a2)) {
                b(jSONObject, a2);
                a(jSONObject, a2);
            }
        }
        return jSONObject;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x007f, code lost:
        if (r2 == null) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r2.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x009a, code lost:
        if (r2 == null) goto L_0x0084;
     */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0097  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject f() {
        /*
            r8 = this;
            java.lang.String r0 = "__vc"
            java.lang.String r1 = "__av"
            java.util.List<java.lang.String> r2 = r8.l
            boolean r2 = r2.isEmpty()
            r3 = 0
            if (r2 == 0) goto L_0x0010
            return r3
        L_0x0010:
            android.content.Context r2 = com.umeng.analytics.pro.h.d     // Catch:{ SQLiteDatabaseCorruptException -> 0x008e, all -> 0x0078 }
            com.umeng.analytics.pro.f r2 = com.umeng.analytics.pro.f.a(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x008e, all -> 0x0078 }
            android.database.sqlite.SQLiteDatabase r2 = r2.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x008e, all -> 0x0078 }
            r2.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0076, all -> 0x0074 }
            java.util.List<java.lang.String> r4 = r8.l     // Catch:{ SQLiteDatabaseCorruptException -> 0x0076, all -> 0x0074 }
            r5 = 0
            java.lang.Object r4 = r4.get(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0076, all -> 0x0074 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ SQLiteDatabaseCorruptException -> 0x0076, all -> 0x0074 }
            java.lang.String r6 = "select *  from __is where __ii=?"
            r7 = 1
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch:{ SQLiteDatabaseCorruptException -> 0x0076, all -> 0x0074 }
            r7[r5] = r4     // Catch:{ SQLiteDatabaseCorruptException -> 0x0076, all -> 0x0074 }
            android.database.Cursor r4 = r2.rawQuery(r6, r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0076, all -> 0x0074 }
            if (r4 == 0) goto L_0x0056
            boolean r5 = r4.moveToNext()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0071, all -> 0x006e }
            if (r5 == 0) goto L_0x0056
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ SQLiteDatabaseCorruptException -> 0x0071, all -> 0x006e }
            r5.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0071, all -> 0x006e }
            int r3 = r4.getColumnIndex(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0072, all -> 0x006f }
            java.lang.String r3 = r4.getString(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0072, all -> 0x006f }
            int r6 = r4.getColumnIndex(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0072, all -> 0x006f }
            java.lang.String r6 = r4.getString(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0072, all -> 0x006f }
            r5.put(r1, r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0072, all -> 0x006f }
            r5.put(r0, r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0072, all -> 0x006f }
            r3 = r5
        L_0x0056:
            r2.setTransactionSuccessful()
            if (r4 == 0) goto L_0x005e
            r4.close()
        L_0x005e:
            if (r2 == 0) goto L_0x0063
            r2.endTransaction()     // Catch:{ all -> 0x0063 }
        L_0x0063:
            android.content.Context r0 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r0 = com.umeng.analytics.pro.f.a(r0)
            r0.b()
            r5 = r3
            goto L_0x009d
        L_0x006e:
            r5 = r3
        L_0x006f:
            r3 = r4
            goto L_0x007a
        L_0x0071:
            r5 = r3
        L_0x0072:
            r3 = r4
            goto L_0x0090
        L_0x0074:
            r5 = r3
            goto L_0x007a
        L_0x0076:
            r5 = r3
            goto L_0x0090
        L_0x0078:
            r2 = r3
            r5 = r2
        L_0x007a:
            if (r3 == 0) goto L_0x007f
            r3.close()
        L_0x007f:
            if (r2 == 0) goto L_0x0084
        L_0x0081:
            r2.endTransaction()     // Catch:{ all -> 0x0084 }
        L_0x0084:
            android.content.Context r0 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r0 = com.umeng.analytics.pro.f.a(r0)
            r0.b()
            goto L_0x009d
        L_0x008e:
            r2 = r3
            r5 = r2
        L_0x0090:
            android.content.Context r0 = com.umeng.analytics.pro.h.d     // Catch:{ all -> 0x009e }
            com.umeng.analytics.pro.g.a(r0)     // Catch:{ all -> 0x009e }
            if (r3 == 0) goto L_0x009a
            r3.close()
        L_0x009a:
            if (r2 == 0) goto L_0x0084
            goto L_0x0081
        L_0x009d:
            return r5
        L_0x009e:
            r0 = move-exception
            if (r3 == 0) goto L_0x00a4
            r3.close()
        L_0x00a4:
            if (r2 == 0) goto L_0x00a9
            r2.endTransaction()     // Catch:{ all -> 0x00a9 }
        L_0x00a9:
            android.content.Context r1 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r1 = com.umeng.analytics.pro.f.a(r1)
            r1.b()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.h.f():org.json.JSONObject");
    }

    public JSONObject b(boolean z) {
        JSONObject jSONObject = new JSONObject();
        b(jSONObject, z);
        return jSONObject;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:64:0x016a, code lost:
        if (r1 != null) goto L_0x0188;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0178, code lost:
        if (r1 == null) goto L_0x018b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0186, code lost:
        if (r1 == null) goto L_0x018b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Removed duplicated region for block: B:71:0x0175  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0183  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(org.json.JSONObject r11, java.lang.String r12) {
        /*
        // Method dump skipped, instructions count: 426
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.h.a(org.json.JSONObject, java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0069, code lost:
        if (r1 != null) goto L_0x0087;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0077, code lost:
        if (r1 == null) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0085, code lost:
        if (r1 == null) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x0074  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0082  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void b(org.json.JSONObject r6, java.lang.String r7) {
        /*
            r5 = this;
            r0 = 0
            android.content.Context r1 = com.umeng.analytics.pro.h.d     // Catch:{ SQLiteDatabaseCorruptException -> 0x007a, all -> 0x006c }
            com.umeng.analytics.pro.f r1 = com.umeng.analytics.pro.f.a(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x007a, all -> 0x006c }
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x007a, all -> 0x006c }
            r1.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x007b, all -> 0x006d }
            java.lang.String r2 = "select *  from __er"
            boolean r3 = android.text.TextUtils.isEmpty(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x007b, all -> 0x006d }
            if (r3 != 0) goto L_0x0025
            java.lang.String r2 = "select *  from __er where __i=?"
            r3 = 1
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteDatabaseCorruptException -> 0x007b, all -> 0x006d }
            r4 = 0
            r3[r4] = r7     // Catch:{ SQLiteDatabaseCorruptException -> 0x007b, all -> 0x006d }
            android.database.Cursor r7 = r1.rawQuery(r2, r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x007b, all -> 0x006d }
            goto L_0x0029
        L_0x0025:
            android.database.Cursor r7 = r1.rawQuery(r2, r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x007b, all -> 0x006d }
        L_0x0029:
            r0 = r7
            if (r0 == 0) goto L_0x0061
            org.json.JSONArray r7 = new org.json.JSONArray     // Catch:{ SQLiteDatabaseCorruptException -> 0x007b, all -> 0x006d }
            r7.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x007b, all -> 0x006d }
        L_0x0031:
            boolean r2 = r0.moveToNext()     // Catch:{ SQLiteDatabaseCorruptException -> 0x007b, all -> 0x006d }
            if (r2 == 0) goto L_0x0055
            java.lang.String r2 = "__a"
            int r2 = r0.getColumnIndex(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x007b, all -> 0x006d }
            java.lang.String r2 = r0.getString(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x007b, all -> 0x006d }
            boolean r3 = android.text.TextUtils.isEmpty(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x007b, all -> 0x006d }
            if (r3 != 0) goto L_0x0031
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ SQLiteDatabaseCorruptException -> 0x007b, all -> 0x006d }
            java.lang.String r2 = r5.d(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x007b, all -> 0x006d }
            r3.<init>(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x007b, all -> 0x006d }
            r7.put(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x007b, all -> 0x006d }
            goto L_0x0031
        L_0x0055:
            int r2 = r7.length()     // Catch:{ SQLiteDatabaseCorruptException -> 0x007b, all -> 0x006d }
            if (r2 <= 0) goto L_0x0061
            java.lang.String r2 = "error"
            r6.put(r2, r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x007b, all -> 0x006d }
        L_0x0061:
            r1.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x007b, all -> 0x006d }
            if (r0 == 0) goto L_0x0069
            r0.close()
        L_0x0069:
            if (r1 == 0) goto L_0x008a
            goto L_0x0087
        L_0x006c:
            r1 = r0
        L_0x006d:
            android.content.Context r6 = com.umeng.analytics.pro.h.d     // Catch:{ all -> 0x0094 }
            com.umeng.analytics.pro.g.a(r6)     // Catch:{ all -> 0x0094 }
            if (r0 == 0) goto L_0x0077
            r0.close()
        L_0x0077:
            if (r1 == 0) goto L_0x008a
            goto L_0x0087
        L_0x007a:
            r1 = r0
        L_0x007b:
            android.content.Context r6 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.g.a(r6)
            if (r0 == 0) goto L_0x0085
            r0.close()
        L_0x0085:
            if (r1 == 0) goto L_0x008a
        L_0x0087:
            r1.endTransaction()     // Catch:{ all -> 0x008a }
        L_0x008a:
            android.content.Context r6 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r6 = com.umeng.analytics.pro.f.a(r6)
            r6.b()
            return
        L_0x0094:
            r6 = move-exception
            if (r0 == 0) goto L_0x009a
            r0.close()
        L_0x009a:
            if (r1 == 0) goto L_0x009f
            r1.endTransaction()     // Catch:{ all -> 0x009f }
        L_0x009f:
            android.content.Context r7 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r7 = com.umeng.analytics.pro.f.a(r7)
            r7.b()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.h.b(org.json.JSONObject, java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:39:0x007f, code lost:
        if (r2 == null) goto L_0x0084;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:?, code lost:
        r2.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x009a, code lost:
        if (r2 == null) goto L_0x0084;
     */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x007c  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x0097  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public org.json.JSONObject g() {
        /*
            r8 = this;
            java.lang.String r0 = "__vc"
            java.lang.String r1 = "__av"
            java.util.List<java.lang.String> r2 = r8.i
            boolean r2 = r2.isEmpty()
            r3 = 0
            if (r2 == 0) goto L_0x0010
            return r3
        L_0x0010:
            android.content.Context r2 = com.umeng.analytics.pro.h.d     // Catch:{ SQLiteDatabaseCorruptException -> 0x008e, all -> 0x0078 }
            com.umeng.analytics.pro.f r2 = com.umeng.analytics.pro.f.a(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x008e, all -> 0x0078 }
            android.database.sqlite.SQLiteDatabase r2 = r2.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x008e, all -> 0x0078 }
            r2.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0076, all -> 0x0074 }
            java.util.List<java.lang.String> r4 = r8.i     // Catch:{ SQLiteDatabaseCorruptException -> 0x0076, all -> 0x0074 }
            r5 = 0
            java.lang.Object r4 = r4.get(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0076, all -> 0x0074 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ SQLiteDatabaseCorruptException -> 0x0076, all -> 0x0074 }
            java.lang.String r6 = "select *  from __sd where __ii=?"
            r7 = 1
            java.lang.String[] r7 = new java.lang.String[r7]     // Catch:{ SQLiteDatabaseCorruptException -> 0x0076, all -> 0x0074 }
            r7[r5] = r4     // Catch:{ SQLiteDatabaseCorruptException -> 0x0076, all -> 0x0074 }
            android.database.Cursor r4 = r2.rawQuery(r6, r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0076, all -> 0x0074 }
            if (r4 == 0) goto L_0x0056
            boolean r5 = r4.moveToNext()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0071, all -> 0x006e }
            if (r5 == 0) goto L_0x0056
            org.json.JSONObject r5 = new org.json.JSONObject     // Catch:{ SQLiteDatabaseCorruptException -> 0x0071, all -> 0x006e }
            r5.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0071, all -> 0x006e }
            int r3 = r4.getColumnIndex(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0072, all -> 0x006f }
            java.lang.String r3 = r4.getString(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0072, all -> 0x006f }
            int r6 = r4.getColumnIndex(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0072, all -> 0x006f }
            java.lang.String r6 = r4.getString(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0072, all -> 0x006f }
            r5.put(r1, r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0072, all -> 0x006f }
            r5.put(r0, r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0072, all -> 0x006f }
            r3 = r5
        L_0x0056:
            r2.setTransactionSuccessful()
            if (r4 == 0) goto L_0x005e
            r4.close()
        L_0x005e:
            if (r2 == 0) goto L_0x0063
            r2.endTransaction()     // Catch:{ all -> 0x0063 }
        L_0x0063:
            android.content.Context r0 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r0 = com.umeng.analytics.pro.f.a(r0)
            r0.b()
            r5 = r3
            goto L_0x009d
        L_0x006e:
            r5 = r3
        L_0x006f:
            r3 = r4
            goto L_0x007a
        L_0x0071:
            r5 = r3
        L_0x0072:
            r3 = r4
            goto L_0x0090
        L_0x0074:
            r5 = r3
            goto L_0x007a
        L_0x0076:
            r5 = r3
            goto L_0x0090
        L_0x0078:
            r2 = r3
            r5 = r2
        L_0x007a:
            if (r3 == 0) goto L_0x007f
            r3.close()
        L_0x007f:
            if (r2 == 0) goto L_0x0084
        L_0x0081:
            r2.endTransaction()     // Catch:{ all -> 0x0084 }
        L_0x0084:
            android.content.Context r0 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r0 = com.umeng.analytics.pro.f.a(r0)
            r0.b()
            goto L_0x009d
        L_0x008e:
            r2 = r3
            r5 = r2
        L_0x0090:
            android.content.Context r0 = com.umeng.analytics.pro.h.d     // Catch:{ all -> 0x009e }
            com.umeng.analytics.pro.g.a(r0)     // Catch:{ all -> 0x009e }
            if (r3 == 0) goto L_0x009a
            r3.close()
        L_0x009a:
            if (r2 == 0) goto L_0x0084
            goto L_0x0081
        L_0x009d:
            return r5
        L_0x009e:
            r0 = move-exception
            if (r3 == 0) goto L_0x00a4
            r3.close()
        L_0x00a4:
            if (r2 == 0) goto L_0x00a9
            r2.endTransaction()     // Catch:{ all -> 0x00a9 }
        L_0x00a9:
            android.content.Context r1 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r1 = com.umeng.analytics.pro.f.a(r1)
            r1.b()
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.h.g():org.json.JSONObject");
    }

    private JSONArray b(JSONArray jSONArray) {
        JSONArray jSONArray2 = new JSONArray();
        int length = jSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null && optJSONObject.optLong("duration") > 0) {
                jSONArray2.put(optJSONObject);
            }
        }
        return jSONArray2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:109:0x023c, code lost:
        if (r12 != null) goto L_0x024e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x024c, code lost:
        if (r12 != null) goto L_0x024e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:?, code lost:
        r12.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:0x0224, code lost:
        if (r12 != null) goto L_0x024e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0239  */
    /* JADX WARNING: Removed duplicated region for block: B:115:0x0249  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String a(org.json.JSONObject r22, boolean r23) {
        /*
        // Method dump skipped, instructions count: 624
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.h.a(org.json.JSONObject, boolean):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00b3, code lost:
        if (r1 != null) goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x00c6, code lost:
        if (r1 != null) goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00d5, code lost:
        if (r1 != null) goto L_0x00d7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Removed duplicated region for block: B:39:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00d2  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String b(org.json.JSONObject r11, boolean r12) {
        /*
            r10 = this;
            r0 = 0
            android.content.Context r1 = com.umeng.analytics.pro.h.d     // Catch:{ SQLiteDatabaseCorruptException -> 0x00c9, all -> 0x00ba }
            com.umeng.analytics.pro.f r1 = com.umeng.analytics.pro.f.a(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00c9, all -> 0x00ba }
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x00c9, all -> 0x00ba }
            r1.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x00b8, all -> 0x00b6 }
            java.lang.String r2 = "select *  from __is"
            android.database.Cursor r2 = r1.rawQuery(r2, r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00b8, all -> 0x00b6 }
            if (r2 == 0) goto L_0x00ab
            org.json.JSONArray r3 = new org.json.JSONArray     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            r3.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
        L_0x001c:
            boolean r4 = r2.moveToNext()     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            if (r4 == 0) goto L_0x009f
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            r4.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            java.lang.String r5 = "__e"
            int r5 = r2.getColumnIndex(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            java.lang.String r5 = r2.getString(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            java.lang.String r6 = "__ii"
            int r6 = r2.getColumnIndex(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            java.lang.String r0 = r2.getString(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            java.util.List<java.lang.String> r6 = r10.l     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            r6.add(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            java.lang.String r6 = "__sp"
            int r6 = r2.getColumnIndex(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            java.lang.String r6 = r2.getString(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            java.lang.String r7 = "__pp"
            int r7 = r2.getColumnIndex(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            java.lang.String r7 = r2.getString(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            boolean r8 = android.text.TextUtils.isEmpty(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            if (r8 != 0) goto L_0x006d
            java.lang.String r8 = "_$sp"
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            java.lang.String r6 = r10.d(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            r9.<init>(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            r4.put(r8, r9)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
        L_0x006d:
            boolean r6 = android.text.TextUtils.isEmpty(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            if (r6 != 0) goto L_0x0082
            java.lang.String r6 = "_$pp"
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            java.lang.String r7 = r10.d(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            r8.<init>(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            r4.put(r6, r8)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
        L_0x0082:
            boolean r6 = android.text.TextUtils.isEmpty(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            if (r6 != 0) goto L_0x001c
            java.lang.String r6 = "id"
            r4.put(r6, r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            java.lang.String r6 = "start_time"
            r4.put(r6, r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            int r5 = r4.length()     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            if (r5 <= 0) goto L_0x009d
            r3.put(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
        L_0x009d:
            if (r12 == 0) goto L_0x001c
        L_0x009f:
            int r12 = r3.length()     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            if (r12 <= 0) goto L_0x00ab
            java.lang.String r12 = "sessions"
            r11.put(r12, r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
        L_0x00ab:
            r1.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x00cb, all -> 0x00bc }
            if (r2 == 0) goto L_0x00b3
            r2.close()
        L_0x00b3:
            if (r1 == 0) goto L_0x00da
            goto L_0x00d7
        L_0x00b6:
            r2 = r0
            goto L_0x00bc
        L_0x00b8:
            r2 = r0
            goto L_0x00cb
        L_0x00ba:
            r1 = r0
            r2 = r1
        L_0x00bc:
            android.content.Context r11 = com.umeng.analytics.pro.h.d     // Catch:{ all -> 0x00e4 }
            com.umeng.analytics.pro.g.a(r11)     // Catch:{ all -> 0x00e4 }
            if (r2 == 0) goto L_0x00c6
            r2.close()
        L_0x00c6:
            if (r1 == 0) goto L_0x00da
            goto L_0x00d7
        L_0x00c9:
            r1 = r0
            r2 = r1
        L_0x00cb:
            android.content.Context r11 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.g.a(r11)
            if (r2 == 0) goto L_0x00d5
            r2.close()
        L_0x00d5:
            if (r1 == 0) goto L_0x00da
        L_0x00d7:
            r1.endTransaction()     // Catch:{ all -> 0x00da }
        L_0x00da:
            android.content.Context r11 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r11 = com.umeng.analytics.pro.f.a(r11)
            r11.b()
            return r0
        L_0x00e4:
            r11 = move-exception
            if (r2 == 0) goto L_0x00ea
            r2.close()
        L_0x00ea:
            if (r1 == 0) goto L_0x00ef
            r1.endTransaction()     // Catch:{ all -> 0x00ef }
        L_0x00ef:
            android.content.Context r12 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r12 = com.umeng.analytics.pro.f.a(r12)
            r12.b()
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.h.b(org.json.JSONObject, boolean):java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x005a, code lost:
        if (r0 != null) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x006a, code lost:
        if (0 == 0) goto L_0x006f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(boolean r6, boolean r7) {
        /*
            r5 = this;
            r0 = 0
            android.content.Context r1 = com.umeng.analytics.pro.h.d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x005d }
            com.umeng.analytics.pro.f r1 = com.umeng.analytics.pro.f.a(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x005d }
            android.database.sqlite.SQLiteDatabase r0 = r1.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x005d }
            r0.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x005d }
            if (r7 == 0) goto L_0x0019
            if (r6 == 0) goto L_0x0057
            java.lang.String r6 = "delete from __is"
            r0.execSQL(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x005d }
            goto L_0x0057
        L_0x0019:
            java.util.List<java.lang.String> r6 = r5.l     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x005d }
            int r6 = r6.size()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x005d }
            r7 = 0
            if (r6 <= 0) goto L_0x004f
            r1 = r7
        L_0x0023:
            if (r7 >= r6) goto L_0x004e
            java.util.List<java.lang.String> r2 = r5.l     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x005d }
            java.lang.Object r2 = r2.get(r7)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x005d }
            java.lang.String r2 = (java.lang.String) r2     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x005d }
            if (r2 != 0) goto L_0x0030
            r1 = 1
        L_0x0030:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x005d }
            r3.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x005d }
            java.lang.String r4 = "delete from __is where __ii=\""
            r3.append(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x005d }
            r3.append(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x005d }
            java.lang.String r2 = "\""
            r3.append(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x005d }
            java.lang.String r2 = r3.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x005d }
            r0.execSQL(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x005d }
            int r7 = r7 + 1
            goto L_0x0023
        L_0x004e:
            r7 = r1
        L_0x004f:
            if (r7 == 0) goto L_0x0057
            java.lang.String r6 = "delete from __is where __ii is null"
            r0.execSQL(r6)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x005d }
        L_0x0057:
            r0.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x005d }
            if (r0 == 0) goto L_0x006f
            goto L_0x006c
        L_0x005d:
            android.content.Context r6 = com.umeng.analytics.pro.h.d     // Catch:{ all -> 0x0079 }
            com.umeng.analytics.pro.g.a(r6)     // Catch:{ all -> 0x0079 }
            if (r0 == 0) goto L_0x006f
            goto L_0x006c
        L_0x0065:
            android.content.Context r6 = com.umeng.analytics.pro.h.d     // Catch:{ all -> 0x0079 }
            com.umeng.analytics.pro.g.a(r6)     // Catch:{ all -> 0x0079 }
            if (r0 == 0) goto L_0x006f
        L_0x006c:
            r0.endTransaction()     // Catch:{ all -> 0x006f }
        L_0x006f:
            android.content.Context r6 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r6 = com.umeng.analytics.pro.f.a(r6)
            r6.b()
            return
        L_0x0079:
            r6 = move-exception
            if (r0 == 0) goto L_0x007f
            r0.endTransaction()     // Catch:{ all -> 0x007f }
        L_0x007f:
            android.content.Context r7 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r7 = com.umeng.analytics.pro.f.a(r7)
            r7.b()
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.h.a(boolean, boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0053, code lost:
        if (r0 != null) goto L_0x0058;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0056, code lost:
        if (0 == 0) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x006a, code lost:
        if (0 == 0) goto L_0x005b;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(boolean r3, boolean r4) {
        /*
            r2 = this;
            r0 = 0
            android.content.Context r1 = com.umeng.analytics.pro.h.d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x0056 }
            com.umeng.analytics.pro.f r1 = com.umeng.analytics.pro.f.a(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x0056 }
            android.database.sqlite.SQLiteDatabase r0 = r1.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x0056 }
            r0.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x0056 }
            if (r4 == 0) goto L_0x0019
            if (r3 == 0) goto L_0x0050
            java.lang.String r3 = "delete from __sd"
            r0.execSQL(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x0056 }
            goto L_0x0050
        L_0x0019:
            java.util.List<java.lang.String> r3 = r2.i     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x0056 }
            int r3 = r3.size()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x0056 }
            if (r3 <= 0) goto L_0x0050
            r3 = 0
        L_0x0022:
            java.util.List<java.lang.String> r4 = r2.i     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x0056 }
            int r4 = r4.size()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x0056 }
            if (r3 >= r4) goto L_0x0050
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x0056 }
            r4.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x0056 }
            java.lang.String r1 = "delete from __sd where __ii=\""
            r4.append(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x0056 }
            java.util.List<java.lang.String> r1 = r2.i     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x0056 }
            java.lang.Object r1 = r1.get(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x0056 }
            java.lang.String r1 = (java.lang.String) r1     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x0056 }
            r4.append(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x0056 }
            java.lang.String r1 = "\""
            r4.append(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x0056 }
            java.lang.String r4 = r4.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x0056 }
            r0.execSQL(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x0056 }
            int r3 = r3 + 1
            goto L_0x0022
        L_0x0050:
            r0.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0065, all -> 0x0056 }
            if (r0 == 0) goto L_0x005b
            goto L_0x0058
        L_0x0056:
            if (r0 == 0) goto L_0x005b
        L_0x0058:
            r0.endTransaction()     // Catch:{ all -> 0x005b }
        L_0x005b:
            android.content.Context r3 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r3 = com.umeng.analytics.pro.f.a(r3)
            r3.b()
            goto L_0x006d
        L_0x0065:
            android.content.Context r3 = com.umeng.analytics.pro.h.d     // Catch:{ all -> 0x006e }
            com.umeng.analytics.pro.g.a(r3)     // Catch:{ all -> 0x006e }
            if (r0 == 0) goto L_0x005b
            goto L_0x0058
        L_0x006d:
            return
        L_0x006e:
            r3 = move-exception
            if (r0 == 0) goto L_0x0074
            r0.endTransaction()     // Catch:{ all -> 0x0074 }
        L_0x0074:
            android.content.Context r4 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r4 = com.umeng.analytics.pro.f.a(r4)
            r4.b()
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.h.b(boolean, boolean):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0048, code lost:
        if (0 == 0) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x005c, code lost:
        if (0 == 0) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0045, code lost:
        if (r0 != null) goto L_0x004a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void h() {
        /*
            r4 = this;
            r0 = 0
            android.content.Context r1 = com.umeng.analytics.pro.h.d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0057, all -> 0x0048 }
            com.umeng.analytics.pro.f r1 = com.umeng.analytics.pro.f.a(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0057, all -> 0x0048 }
            android.database.sqlite.SQLiteDatabase r0 = r1.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0057, all -> 0x0048 }
            r0.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0057, all -> 0x0048 }
            java.util.List<java.lang.Integer> r1 = r4.j     // Catch:{ SQLiteDatabaseCorruptException -> 0x0057, all -> 0x0048 }
            int r1 = r1.size()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0057, all -> 0x0048 }
            if (r1 <= 0) goto L_0x003d
            r1 = 0
        L_0x0017:
            java.util.List<java.lang.Integer> r2 = r4.j     // Catch:{ SQLiteDatabaseCorruptException -> 0x0057, all -> 0x0048 }
            int r2 = r2.size()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0057, all -> 0x0048 }
            if (r1 >= r2) goto L_0x003d
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x0057, all -> 0x0048 }
            r2.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0057, all -> 0x0048 }
            java.lang.String r3 = "delete from __et where rowid="
            r2.append(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0057, all -> 0x0048 }
            java.util.List<java.lang.Integer> r3 = r4.j     // Catch:{ SQLiteDatabaseCorruptException -> 0x0057, all -> 0x0048 }
            java.lang.Object r3 = r3.get(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0057, all -> 0x0048 }
            r2.append(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0057, all -> 0x0048 }
            java.lang.String r2 = r2.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0057, all -> 0x0048 }
            r0.execSQL(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0057, all -> 0x0048 }
            int r1 = r1 + 1
            goto L_0x0017
        L_0x003d:
            java.util.List<java.lang.Integer> r1 = r4.j     // Catch:{ SQLiteDatabaseCorruptException -> 0x0057, all -> 0x0048 }
            r1.clear()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0057, all -> 0x0048 }
            r0.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0057, all -> 0x0048 }
            if (r0 == 0) goto L_0x004d
            goto L_0x004a
        L_0x0048:
            if (r0 == 0) goto L_0x004d
        L_0x004a:
            r0.endTransaction()     // Catch:{ all -> 0x004d }
        L_0x004d:
            android.content.Context r0 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r0 = com.umeng.analytics.pro.f.a(r0)
            r0.b()
            goto L_0x005f
        L_0x0057:
            android.content.Context r1 = com.umeng.analytics.pro.h.d     // Catch:{ all -> 0x0060 }
            com.umeng.analytics.pro.g.a(r1)     // Catch:{ all -> 0x0060 }
            if (r0 == 0) goto L_0x004d
            goto L_0x004a
        L_0x005f:
            return
        L_0x0060:
            r1 = move-exception
            if (r0 == 0) goto L_0x0066
            r0.endTransaction()     // Catch:{ all -> 0x0066 }
        L_0x0066:
            android.content.Context r0 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r0 = com.umeng.analytics.pro.f.a(r0)
            r0.b()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.h.h():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002e, code lost:
        if (r0 == null) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:3:0x0017, code lost:
        if (r0 != null) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:4:0x001a, code lost:
        if (r0 == null) goto L_0x001f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
        r0.endTransaction();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void i() {
        /*
            r2 = this;
            r0 = 0
            android.content.Context r1 = com.umeng.analytics.pro.h.d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0029, all -> 0x001a }
            com.umeng.analytics.pro.f r1 = com.umeng.analytics.pro.f.a(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0029, all -> 0x001a }
            android.database.sqlite.SQLiteDatabase r0 = r1.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0029, all -> 0x001a }
            r0.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0029, all -> 0x001a }
            java.lang.String r1 = "delete from __er"
            r0.execSQL(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0029, all -> 0x001a }
            r0.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0029, all -> 0x001a }
            if (r0 == 0) goto L_0x001f
            goto L_0x001c
        L_0x001a:
            if (r0 == 0) goto L_0x001f
        L_0x001c:
            r0.endTransaction()     // Catch:{ all -> 0x001f }
        L_0x001f:
            android.content.Context r0 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r0 = com.umeng.analytics.pro.f.a(r0)
            r0.b()
            goto L_0x0031
        L_0x0029:
            android.content.Context r1 = com.umeng.analytics.pro.h.d     // Catch:{ all -> 0x0032 }
            com.umeng.analytics.pro.g.a(r1)     // Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x001f
            goto L_0x001c
        L_0x0031:
            return
        L_0x0032:
            r1 = move-exception
            if (r0 == 0) goto L_0x0038
            r0.endTransaction()     // Catch:{ all -> 0x0038 }
        L_0x0038:
            android.content.Context r0 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r0 = com.umeng.analytics.pro.f.a(r0)
            r0.b()
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.h.i():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:?, code lost:
        r1.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0069, code lost:
        if (r1 != null) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0050, code lost:
        if (r1 != null) goto L_0x0056;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0054, code lost:
        if (r1 != null) goto L_0x0056;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void j() {
        /*
            r5 = this;
            java.lang.String r0 = "\""
            java.lang.String r1 = r5.k
            boolean r1 = android.text.TextUtils.isEmpty(r1)
            r2 = 0
            if (r1 != 0) goto L_0x007c
            android.content.Context r1 = com.umeng.analytics.pro.h.d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0063, all -> 0x0053 }
            com.umeng.analytics.pro.f r1 = com.umeng.analytics.pro.f.a(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0063, all -> 0x0053 }
            android.database.sqlite.SQLiteDatabase r1 = r1.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0063, all -> 0x0053 }
            r1.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0064, all -> 0x0054 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x0064, all -> 0x0054 }
            r3.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0064, all -> 0x0054 }
            java.lang.String r4 = "delete from __er where __i=\""
            r3.append(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0064, all -> 0x0054 }
            java.lang.String r4 = r5.k     // Catch:{ SQLiteDatabaseCorruptException -> 0x0064, all -> 0x0054 }
            r3.append(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0064, all -> 0x0054 }
            r3.append(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0064, all -> 0x0054 }
            java.lang.String r3 = r3.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0064, all -> 0x0054 }
            r1.execSQL(r3)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0064, all -> 0x0054 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x0064, all -> 0x0054 }
            r3.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0064, all -> 0x0054 }
            java.lang.String r4 = "delete from __et where __i=\""
            r3.append(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0064, all -> 0x0054 }
            java.lang.String r4 = r5.k     // Catch:{ SQLiteDatabaseCorruptException -> 0x0064, all -> 0x0054 }
            r3.append(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0064, all -> 0x0054 }
            r3.append(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0064, all -> 0x0054 }
            java.lang.String r0 = r3.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0064, all -> 0x0054 }
            r1.execSQL(r0)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0064, all -> 0x0054 }
            r1.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0064, all -> 0x0054 }
            if (r1 == 0) goto L_0x0059
            goto L_0x0056
        L_0x0053:
            r1 = r2
        L_0x0054:
            if (r1 == 0) goto L_0x0059
        L_0x0056:
            r1.endTransaction()     // Catch:{ all -> 0x0059 }
        L_0x0059:
            android.content.Context r0 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r0 = com.umeng.analytics.pro.f.a(r0)
            r0.b()
            goto L_0x007c
        L_0x0063:
            r1 = r2
        L_0x0064:
            android.content.Context r0 = com.umeng.analytics.pro.h.d     // Catch:{ all -> 0x006c }
            com.umeng.analytics.pro.g.a(r0)     // Catch:{ all -> 0x006c }
            if (r1 == 0) goto L_0x0059
            goto L_0x0056
        L_0x006c:
            r0 = move-exception
            if (r1 == 0) goto L_0x0072
            r1.endTransaction()     // Catch:{ all -> 0x0072 }
        L_0x0072:
            android.content.Context r1 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r1 = com.umeng.analytics.pro.f.a(r1)
            r1.b()
            throw r0
        L_0x007c:
            r5.k = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.h.j():void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x007e, code lost:
        if (0 == 0) goto L_0x006f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0067, code lost:
        if (r0 != null) goto L_0x006c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x006a, code lost:
        if (0 == 0) goto L_0x006f;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(boolean r4, java.lang.String r5) {
        /*
            r3 = this;
            java.lang.String r4 = "\""
            r0 = 0
            android.content.Context r1 = com.umeng.analytics.pro.h.d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            com.umeng.analytics.pro.f r1 = com.umeng.analytics.pro.f.a(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            android.database.sqlite.SQLiteDatabase r0 = r1.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            r0.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            boolean r1 = android.text.TextUtils.isEmpty(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            if (r1 != 0) goto L_0x0064
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            r1.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            java.lang.String r2 = "delete from __er where __i=\""
            r1.append(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            r1.append(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            r1.append(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            java.lang.String r1 = r1.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            r0.execSQL(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            r1.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            java.lang.String r2 = "delete from __et where __i=\""
            r1.append(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            r1.append(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            r1.append(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            java.lang.String r1 = r1.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            r0.execSQL(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            java.util.List<java.lang.Integer> r1 = r3.j     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            r1.clear()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            r1.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            java.lang.String r2 = "delete from __sd where __ii=\""
            r1.append(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            r1.append(r5)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            r1.append(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            java.lang.String r4 = r1.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            r0.execSQL(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
        L_0x0064:
            r0.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0079, all -> 0x006a }
            if (r0 == 0) goto L_0x006f
            goto L_0x006c
        L_0x006a:
            if (r0 == 0) goto L_0x006f
        L_0x006c:
            r0.endTransaction()     // Catch:{ all -> 0x006f }
        L_0x006f:
            android.content.Context r4 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r4 = com.umeng.analytics.pro.f.a(r4)
            r4.b()
            goto L_0x0081
        L_0x0079:
            android.content.Context r4 = com.umeng.analytics.pro.h.d     // Catch:{ all -> 0x0082 }
            com.umeng.analytics.pro.g.a(r4)     // Catch:{ all -> 0x0082 }
            if (r0 == 0) goto L_0x006f
            goto L_0x006c
        L_0x0081:
            return
        L_0x0082:
            r4 = move-exception
            if (r0 == 0) goto L_0x0088
            r0.endTransaction()     // Catch:{ all -> 0x0088 }
        L_0x0088:
            android.content.Context r5 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r5 = com.umeng.analytics.pro.f.a(r5)
            r5.b()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.h.a(boolean, java.lang.String):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r0.endTransaction();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0049, code lost:
        if (0 == 0) goto L_0x003a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0032, code lost:
        if (r0 != null) goto L_0x0037;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:7:0x0035, code lost:
        if (0 == 0) goto L_0x003a;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b(java.lang.String r4) {
        /*
            r3 = this;
            r0 = 0
            android.content.Context r1 = com.umeng.analytics.pro.h.d     // Catch:{ SQLiteDatabaseCorruptException -> 0x0044, all -> 0x0035 }
            com.umeng.analytics.pro.f r1 = com.umeng.analytics.pro.f.a(r1)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0044, all -> 0x0035 }
            android.database.sqlite.SQLiteDatabase r0 = r1.a()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0044, all -> 0x0035 }
            r0.beginTransaction()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0044, all -> 0x0035 }
            boolean r1 = android.text.TextUtils.isEmpty(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0044, all -> 0x0035 }
            if (r1 != 0) goto L_0x002f
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ SQLiteDatabaseCorruptException -> 0x0044, all -> 0x0035 }
            r1.<init>()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0044, all -> 0x0035 }
            java.lang.String r2 = "delete from __is where __ii=\""
            r1.append(r2)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0044, all -> 0x0035 }
            r1.append(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0044, all -> 0x0035 }
            java.lang.String r4 = "\""
            r1.append(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0044, all -> 0x0035 }
            java.lang.String r4 = r1.toString()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0044, all -> 0x0035 }
            r0.execSQL(r4)     // Catch:{ SQLiteDatabaseCorruptException -> 0x0044, all -> 0x0035 }
        L_0x002f:
            r0.setTransactionSuccessful()     // Catch:{ SQLiteDatabaseCorruptException -> 0x0044, all -> 0x0035 }
            if (r0 == 0) goto L_0x003a
            goto L_0x0037
        L_0x0035:
            if (r0 == 0) goto L_0x003a
        L_0x0037:
            r0.endTransaction()     // Catch:{ all -> 0x003a }
        L_0x003a:
            android.content.Context r4 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r4 = com.umeng.analytics.pro.f.a(r4)
            r4.b()
            goto L_0x004c
        L_0x0044:
            android.content.Context r4 = com.umeng.analytics.pro.h.d     // Catch:{ all -> 0x004d }
            com.umeng.analytics.pro.g.a(r4)     // Catch:{ all -> 0x004d }
            if (r0 == 0) goto L_0x003a
            goto L_0x0037
        L_0x004c:
            return
        L_0x004d:
            r4 = move-exception
            if (r0 == 0) goto L_0x0053
            r0.endTransaction()     // Catch:{ all -> 0x0053 }
        L_0x0053:
            android.content.Context r0 = com.umeng.analytics.pro.h.d
            com.umeng.analytics.pro.f r0 = com.umeng.analytics.pro.f.a(r0)
            r0.b()
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.h.b(java.lang.String):void");
    }

    private void l() {
        try {
            if (TextUtils.isEmpty(e)) {
                String multiProcessSP = UMUtils.getMultiProcessSP(d, g);
                if (TextUtils.isEmpty(multiProcessSP)) {
                    multiProcessSP = PreferenceWrapper.getDefault(d).getString(g, null);
                    if (TextUtils.isEmpty(multiProcessSP)) {
                        multiProcessSP = UMUtils.genId();
                    }
                    if (!TextUtils.isEmpty(multiProcessSP)) {
                        UMUtils.setMultiProcessSP(d, g, multiProcessSP);
                    }
                }
                if (!TextUtils.isEmpty(multiProcessSP)) {
                    String substring = multiProcessSP.substring(1, 9);
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < substring.length(); i++) {
                        char charAt = substring.charAt(i);
                        if (!Character.isDigit(charAt)) {
                            sb.append(charAt);
                        } else if (Integer.parseInt(Character.toString(charAt)) == 0) {
                            sb.append(0);
                        } else {
                            sb.append(10 - Integer.parseInt(Character.toString(charAt)));
                        }
                    }
                    e = sb.toString();
                }
                if (!TextUtils.isEmpty(e)) {
                    e += new StringBuilder(e).reverse().toString();
                    String multiProcessSP2 = UMUtils.getMultiProcessSP(d, h);
                    if (TextUtils.isEmpty(multiProcessSP2)) {
                        UMUtils.setMultiProcessSP(d, h, c(f));
                    } else if (!f.equals(d(multiProcessSP2))) {
                        b(true, false);
                        a(true, false);
                        h();
                        i();
                    }
                }
            }
        } catch (Throwable unused) {
        }
    }

    public String c(String str) {
        try {
            return TextUtils.isEmpty(e) ? str : Base64.encodeToString(DataHelper.encrypt(str.getBytes(), e.getBytes()), 0);
        } catch (Exception unused) {
            return null;
        }
    }

    public String d(String str) {
        try {
            if (TextUtils.isEmpty(e)) {
                return str;
            }
            return new String(DataHelper.decrypt(Base64.decode(str.getBytes(), 0), e.getBytes()));
        } catch (Exception unused) {
            if (Build.VERSION.SDK_INT >= 29 && !TextUtils.isEmpty(str)) {
                new JSONObject(str);
                UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> UMStoreManager decrypt failed, return origin data.");
                return str;
            }
        } catch (Throwable unused2) {
        }
        return null;
    }
}
