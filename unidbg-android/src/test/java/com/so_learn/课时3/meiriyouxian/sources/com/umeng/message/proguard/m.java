package com.umeng.message.proguard;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import com.umeng.message.MessageSharedPrefs;
import com.umeng.message.MsgConstant;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import org.json.JSONObject;

/* compiled from: MsgLogStore */
public class m {
    private static final String A = m.class.getName();
    private static m B = null;
    private static final String D = " Asc ";
    private static final String E = " Desc ";
    public static final String a = " And ";
    public static final String b = "MsgLogStore.db";
    public static final int c = 7;
    public static final String d = "MsgLogStore";
    public static final String e = "MsgLogIdTypeStore";
    public static final String f = "MsgLogStoreForAgoo";
    public static final String g = "MsgLogIdTypeStoreForAgoo";
    public static final String h = "MsgConfigInfo";
    public static final String i = "InAppLogStore";
    public static final String j = "MsgId";
    public static final String k = "MsgType";
    public static final String l = "ActionType";
    public static final String m = "pa";
    public static final String n = "Time";
    public static final String o = "TaskId";
    public static final String p = "MsgStatus";
    public static final String q = "SerialNo";
    public static final String r = "AppLaunchAt";
    public static final String s = "UpdateResponse";
    public static final String t = "NumDisplay";
    public static final String u = "NumOpenFull";
    public static final String v = "NumOpenTop";
    public static final String w = "NumOpenBottom";
    public static final String x = "NumClose";
    public static final String y = "NumDuration";
    public static final String z = "NumCustom";
    private final Context C;

    public boolean a(String str, String str2, String str3, long j2) {
        return false;
    }

    public static m a(Context context) {
        if (B == null) {
            B = new m(context);
            B.h();
        }
        return B;
    }

    private m(Context context) {
        this.C = context.getApplicationContext();
    }

    private void h() {
        if (!MessageSharedPrefs.getInstance(this.C).hasTransferedCacheFileDataToSQL()) {
            File[] listFiles = this.C.getCacheDir().listFiles(new AnonymousClass1());
            if (listFiles != null) {
                for (File file : listFiles) {
                    a(file);
                    file.delete();
                }
            }
            MessageSharedPrefs.getInstance(this.C).finishTransferedCacheFileDataToSQL();
        }
    }

    /* compiled from: MsgLogStore */
    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.message.proguard.m$1  reason: invalid class name */
    public class AnonymousClass1 implements FilenameFilter {
        AnonymousClass1() {
        }

        @Override // java.io.FilenameFilter
        public boolean accept(File file, String str) {
            return !TextUtils.isEmpty(str) && str.startsWith(MsgConstant.CACHE_LOG_FILE_PREFIX);
        }
    }

    private void a(File file) {
        try {
            JSONObject jSONObject = new JSONObject(b(file));
            a(jSONObject.optString("msg_id"), jSONObject.optInt(MsgConstant.KEY_ACTION_TYPE), jSONObject.optLong("ts"), jSONObject.optString("pa"));
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x002d A[SYNTHETIC, Splitter:B:18:0x002d] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String b(java.io.File r4) throws java.io.IOException {
        /*
            r3 = this;
            r0 = 0
            java.io.BufferedReader r1 = new java.io.BufferedReader     // Catch:{ all -> 0x0029 }
            java.io.FileReader r2 = new java.io.FileReader     // Catch:{ all -> 0x0029 }
            r2.<init>(r4)     // Catch:{ all -> 0x0029 }
            r1.<init>(r2)     // Catch:{ all -> 0x0029 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0027 }
            r4.<init>()     // Catch:{ all -> 0x0027 }
        L_0x0010:
            java.lang.String r0 = r1.readLine()     // Catch:{ all -> 0x0027 }
            if (r0 == 0) goto L_0x001a
            r4.append(r0)     // Catch:{ all -> 0x0027 }
            goto L_0x0010
        L_0x001a:
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0027 }
            r1.close()     // Catch:{ IOException -> 0x0022 }
            goto L_0x0026
        L_0x0022:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0026:
            return r4
        L_0x0027:
            r4 = move-exception
            goto L_0x002b
        L_0x0029:
            r4 = move-exception
            r1 = r0
        L_0x002b:
            if (r1 == 0) goto L_0x0035
            r1.close()     // Catch:{ IOException -> 0x0031 }
            goto L_0x0035
        L_0x0031:
            r0 = move-exception
            r0.printStackTrace()
        L_0x0035:
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.message.proguard.m.b(java.io.File):java.lang.String");
    }

    public boolean a(String str, int i2, long j2, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str2 == null) {
            str2 = "";
        }
        if (this.C.getContentResolver().insert(com.umeng.message.provider.a.a(this.C).f, new a(str, i2, j2, str2).a()) != null) {
            return true;
        }
        return false;
    }

    public boolean a(String str, int i2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (this.C.getContentResolver().delete(com.umeng.message.provider.a.a(this.C).f, "MsgId=? And ActionType=?", new String[]{str, i2 + ""}) == 1) {
            return true;
        }
        return false;
    }

    public a a(String str) {
        a aVar = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Cursor query = this.C.getContentResolver().query(com.umeng.message.provider.a.a(this.C).f, null, "MsgId=?", new String[0], null);
        if (query.moveToFirst()) {
            aVar = new a(query);
        }
        if (query != null) {
            query.close();
        }
        return aVar;
    }

    public ArrayList<a> a(int i2) {
        if (i2 < 1) {
            return null;
        }
        ArrayList<a> arrayList = new ArrayList<>();
        Cursor query = this.C.getContentResolver().query(com.umeng.message.provider.a.a(this.C).f, null, null, null, "Time Asc  limit " + i2);
        if (query != null) {
            for (boolean moveToFirst = query.moveToFirst(); moveToFirst; moveToFirst = query.moveToNext()) {
                arrayList.add(new a(query));
            }
            query.close();
        }
        return arrayList;
    }

    public ArrayList<a> a() {
        ArrayList<a> arrayList = new ArrayList<>();
        Cursor query = this.C.getContentResolver().query(com.umeng.message.provider.a.a(this.C).f, null, null, null, "Time Asc ");
        if (query == null) {
            return arrayList;
        }
        for (boolean moveToFirst = query.moveToFirst(); moveToFirst; moveToFirst = query.moveToNext()) {
            arrayList.add(new a(query));
        }
        query.close();
        return arrayList;
    }

    public boolean a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (this.C.getContentResolver().insert(com.umeng.message.provider.a.a(this.C).g, new c(str, str2).a()) != null) {
            return true;
        }
        return false;
    }

    public boolean b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.C.getContentResolver().delete(com.umeng.message.provider.a.a(this.C).g, "MsgId=?", new String[]{str}) == 1;
    }

    public ArrayList<c> b(int i2) {
        if (i2 < 1) {
            return null;
        }
        ArrayList<c> arrayList = new ArrayList<>();
        Uri.Builder buildUpon = com.umeng.message.provider.a.a(this.C).g.buildUpon();
        Cursor query = this.C.getContentResolver().query(buildUpon.appendQueryParameter("limit", i2 + "").build(), null, null, null, "MsgId Asc ");
        if (query != null) {
            for (boolean moveToFirst = query.moveToFirst(); moveToFirst; moveToFirst = query.moveToNext()) {
                arrayList.add(new c(query));
            }
            query.close();
        }
        return arrayList;
    }

    public ArrayList<c> b() {
        ArrayList<c> arrayList = new ArrayList<>();
        Cursor query = this.C.getContentResolver().query(com.umeng.message.provider.a.a(this.C).g, null, null, null, "MsgId Asc ");
        if (query != null) {
            for (boolean moveToFirst = query.moveToFirst(); moveToFirst; moveToFirst = query.moveToNext()) {
                arrayList.add(new c(query));
            }
            query.close();
        }
        return arrayList;
    }

    public boolean b(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.C.getContentResolver().delete(com.umeng.message.provider.a.a(this.C).h, "MsgId=? And MsgStatus=?", new String[]{str, str2}) == 1;
    }

    public b c(String str) {
        b bVar = null;
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Cursor query = this.C.getContentResolver().query(com.umeng.message.provider.a.a(this.C).h, null, "MsgId=?", new String[]{str}, null);
        if (query != null) {
            if (query.moveToFirst()) {
                bVar = new b(query);
            }
            query.close();
        }
        return bVar;
    }

    public ArrayList<b> c(int i2) {
        if (i2 < 1) {
            return null;
        }
        ArrayList<b> arrayList = new ArrayList<>();
        Uri.Builder buildUpon = com.umeng.message.provider.a.a(this.C).h.buildUpon();
        Cursor query = this.C.getContentResolver().query(buildUpon.appendQueryParameter("limit", i2 + "").build(), null, null, null, "Time Asc ");
        for (boolean moveToFirst = query.moveToFirst(); moveToFirst; moveToFirst = query.moveToNext()) {
            arrayList.add(new b(query));
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
    }

    public ArrayList<b> c() {
        ArrayList<b> arrayList = new ArrayList<>();
        Cursor query = this.C.getContentResolver().query(com.umeng.message.provider.a.a(this.C).h, null, null, null, "Time Asc ");
        for (boolean moveToFirst = query.moveToFirst(); moveToFirst; moveToFirst = query.moveToNext()) {
            arrayList.add(new b(query));
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
    }

    public boolean a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (this.C.getContentResolver().insert(com.umeng.message.provider.a.a(this.C).i, new d(str, str2, str3).a()) != null) {
            return true;
        }
        return false;
    }

    public boolean d(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return this.C.getContentResolver().delete(com.umeng.message.provider.a.a(this.C).i, "MsgId=?", new String[]{str}) == 1;
    }

    public ArrayList<d> d(int i2) {
        if (i2 < 1) {
            return null;
        }
        ArrayList<d> arrayList = new ArrayList<>();
        Uri.Builder buildUpon = com.umeng.message.provider.a.a(this.C).i.buildUpon();
        Cursor query = this.C.getContentResolver().query(buildUpon.appendQueryParameter("limit", i2 + "").build(), null, null, null, "MsgId Asc ");
        for (boolean moveToFirst = query.moveToFirst(); moveToFirst; moveToFirst = query.moveToNext()) {
            arrayList.add(new d(query));
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
    }

    public ArrayList<d> d() {
        ArrayList<d> arrayList = new ArrayList<>();
        Cursor query = this.C.getContentResolver().query(com.umeng.message.provider.a.a(this.C).i, null, null, null, "MsgId Asc ");
        for (boolean moveToFirst = query.moveToFirst(); moveToFirst; moveToFirst = query.moveToNext()) {
            arrayList.add(new d(query));
        }
        if (query != null) {
            query.close();
        }
        return arrayList;
    }

    public int e() {
        Cursor query = this.C.getContentResolver().query(com.umeng.message.provider.a.a(this.C).j, new String[]{q}, null, null, null);
        int i2 = query.moveToFirst() ? query.getInt(query.getColumnIndex(q)) : 0;
        if (query != null) {
            query.close();
        }
        return i2;
    }

    public void e(int i2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(q, i2 + "");
        this.C.getContentResolver().update(com.umeng.message.provider.a.a(this.C).j, contentValues, null, null);
    }

    public long f() {
        Cursor query = this.C.getContentResolver().query(com.umeng.message.provider.a.a(this.C).j, new String[]{r}, null, null, null);
        long j2 = 0;
        if (query == null) {
            return 0;
        }
        if (query.moveToFirst()) {
            j2 = query.getLong(query.getColumnIndex(r));
        }
        if (query != null) {
            query.close();
        }
        String str = A;
        Log.d(str, "appLaunchAt=" + j2);
        return j2;
    }

    public void a(long j2) {
        Cursor query = this.C.getContentResolver().query(com.umeng.message.provider.a.a(this.C).j, new String[]{r}, null, null, null);
        int count = query.getCount();
        if (query != null) {
            query.close();
        }
        if (count > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put(r, j2 + "");
            this.C.getContentResolver().update(com.umeng.message.provider.a.a(this.C).j, contentValues, null, null);
            return;
        }
        ContentValues contentValues2 = new ContentValues();
        contentValues2.put(r, j2 + "");
        this.C.getContentResolver().insert(com.umeng.message.provider.a.a(this.C).j, contentValues2);
    }

    public Object g() {
        Cursor query = this.C.getContentResolver().query(com.umeng.message.provider.a.a(this.C).j, new String[]{s}, null, null, null);
        String string = query.moveToFirst() ? query.getString(query.getColumnIndex(s)) : null;
        if (query != null) {
            query.close();
        }
        String str = A;
        Log.d(str, "updateResponse=" + string);
        return h.f(string);
    }

    public void a(Object obj) {
        String a2 = h.a(obj);
        ContentValues contentValues = new ContentValues();
        contentValues.put(s, a2);
        this.C.getContentResolver().update(com.umeng.message.provider.a.a(this.C).j, contentValues, null, null);
    }

    /* compiled from: MsgLogStore */
    public class a {
        public String a;
        public long b;
        public int c;
        public String d;

        public a(String str, int i, long j, String str2) {
            this.a = str;
            this.c = i;
            this.b = j;
            this.d = str2;
        }

        public a(Cursor cursor) {
            this.a = cursor.getString(cursor.getColumnIndex(m.j));
            this.b = cursor.getLong(cursor.getColumnIndex(m.n));
            this.c = cursor.getInt(cursor.getColumnIndex("ActionType"));
            this.d = cursor.getString(cursor.getColumnIndex("pa"));
        }

        public ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put(m.j, this.a);
            contentValues.put(m.n, Long.valueOf(this.b));
            contentValues.put("ActionType", Integer.valueOf(this.c));
            contentValues.put("pa", this.d);
            return contentValues;
        }
    }

    /* compiled from: MsgLogStore */
    public class c {
        public String a;
        public String b;

        public c(String str, String str2) {
            this.a = str;
            this.b = str2;
        }

        public c(Cursor cursor) {
            this.a = cursor.getString(cursor.getColumnIndex(m.j));
            this.b = cursor.getString(cursor.getColumnIndex(m.k));
        }

        public ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put(m.j, this.a);
            contentValues.put(m.k, this.b);
            return contentValues;
        }
    }

    /* compiled from: MsgLogStore */
    public class b {
        public String a;
        public String b;
        public String c;
        public long d;

        public b(String str, String str2, String str3, long j) {
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = j;
        }

        public b(Cursor cursor) {
            this.a = cursor.getString(cursor.getColumnIndex(m.j));
            this.b = cursor.getString(cursor.getColumnIndex(m.o));
            this.c = cursor.getString(cursor.getColumnIndex(m.p));
            this.d = cursor.getLong(cursor.getColumnIndex(m.n));
        }

        public ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put(m.j, this.a);
            contentValues.put(m.o, this.b);
            contentValues.put(m.p, this.c);
            contentValues.put(m.n, Long.valueOf(this.d));
            return contentValues;
        }
    }

    /* compiled from: MsgLogStore */
    public class d {
        public String a;
        public String b;
        public String c;

        public d(String str, String str2, String str3) {
            this.a = str;
            this.b = str2;
            this.c = str3;
        }

        public d(Cursor cursor) {
            this.a = cursor.getString(cursor.getColumnIndex(m.j));
            this.b = cursor.getString(cursor.getColumnIndex(m.o));
            this.c = cursor.getString(cursor.getColumnIndex(m.p));
        }

        public ContentValues a() {
            ContentValues contentValues = new ContentValues();
            contentValues.put(m.j, this.a);
            contentValues.put(m.o, this.b);
            contentValues.put(m.p, this.c);
            return contentValues;
        }
    }
}
