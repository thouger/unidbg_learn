package com.umeng.analytics.pro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.database.sqlite.SQLiteOpenHelper;
import com.umeng.commonsdk.debug.UMRTLog;

/* compiled from: CacheDBHelper */
public class ak extends SQLiteOpenHelper {
    private static final Object b = new Object();
    private static ak c = null;
    private static final String d = "CREATE TABLE IF NOT EXISTS stf(_id INTEGER PRIMARY KEY AUTOINCREMENT, _tp TEXT, _hd TEXT, _bd TEXT, _ts TEXT, _uuid TEXT, _re1 TEXT, _re2 TEXT)";
    private static final String e = "DROP TABLE IF EXISTS stf";
    private static final String f = "DELETE FROM stf WHERE _id IN( SELECT _id FROM stf ORDER BY _id LIMIT 1)";
    private final Context a;

    public static final int a() {
        return 1;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public static ak a(Context context) {
        ak akVar;
        synchronized (b) {
            if (c == null) {
                c = new ak(context, am.b, null, 1);
            }
            akVar = c;
        }
        return akVar;
    }

    private ak(Context context, String str, SQLiteDatabase.CursorFactory cursorFactory, int i) {
        super(context, str, cursorFactory, i);
        this.a = context;
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(e);
            sQLiteDatabase.execSQL(d);
        } catch (SQLException unused) {
        }
    }

    private void b(SQLiteDatabase sQLiteDatabase) {
        try {
            sQLiteDatabase.execSQL(d);
        } catch (SQLiteDatabaseCorruptException unused) {
            a(sQLiteDatabase);
        } catch (Throwable th) {
            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> [\u6709\u72b6\u6001]\u521b\u5efa\u4e8c\u7ea7\u7f13\u5b58\u6570\u636e\u5e93\u5931\u8d25: " + th.getMessage());
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        b(sQLiteDatabase);
    }

    public void b() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null && writableDatabase.isOpen()) {
                writableDatabase.close();
            }
        } catch (Throwable unused) {
        }
    }

    public void a(String str, ContentValues contentValues) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null && writableDatabase.isOpen()) {
                try {
                    writableDatabase.beginTransaction();
                    writableDatabase.insert(str, null, contentValues);
                    writableDatabase.setTransactionSuccessful();
                    UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> [\u6709\u72b6\u6001]\u63d2\u5165\u4e8c\u7ea7\u7f13\u5b58\u6570\u636e\u8bb0\u5f55 \u6210\u529f\u3002");
                    if (writableDatabase == null) {
                        return;
                    }
                } catch (Throwable unused) {
                    if (writableDatabase == null) {
                        return;
                    }
                }
                writableDatabase.endTransaction();
                writableDatabase.close();
            }
        } catch (Throwable unused2) {
        }
    }

    public void a(String str, String str2, String[] strArr) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null && writableDatabase.isOpen()) {
                try {
                    writableDatabase.beginTransaction();
                    writableDatabase.delete(str, str2, strArr);
                    writableDatabase.setTransactionSuccessful();
                    if (writableDatabase == null) {
                        return;
                    }
                } catch (Throwable unused) {
                    if (writableDatabase == null) {
                        return;
                    }
                }
                writableDatabase.endTransaction();
                writableDatabase.close();
            }
        } catch (Throwable unused2) {
        }
    }

    private void d() {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase != null && writableDatabase.isOpen()) {
                try {
                    writableDatabase.execSQL(f);
                    if (writableDatabase == null) {
                        return;
                    }
                } catch (Throwable unused) {
                    if (writableDatabase == null) {
                        return;
                    }
                }
                writableDatabase.close();
            }
        } catch (Throwable unused2) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0074, code lost:
        if (r1 != null) goto L_0x0076;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0076, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x007e, code lost:
        if (r1 == null) goto L_0x0081;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0081, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.umeng.analytics.pro.al a(java.lang.String r11) {
        /*
            r10 = this;
            r0 = 0
            java.lang.String r1 = "_uuid"
            java.lang.String r2 = "_tp"
            java.lang.String r3 = "_hd"
            java.lang.String r4 = "_bd"
            java.lang.String r5 = "_re1"
            java.lang.String r6 = "_re2"
            java.lang.String[] r3 = new java.lang.String[]{r1, r2, r3, r4, r5, r6}     // Catch:{ all -> 0x007a }
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r9 = "1"
            r1 = r10
            r2 = r11
            android.database.Cursor r1 = r1.a(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x007a }
            if (r1 == 0) goto L_0x0074
            boolean r2 = r1.moveToFirst()     // Catch:{ all -> 0x007b }
            if (r2 == 0) goto L_0x0074
            com.umeng.analytics.pro.al r2 = new com.umeng.analytics.pro.al     // Catch:{ all -> 0x007b }
            r2.<init>()     // Catch:{ all -> 0x007b }
            r0 = 0
            java.lang.String r0 = r1.getString(r0)     // Catch:{ all -> 0x0072 }
            r2.a = r0     // Catch:{ all -> 0x0072 }
            r0 = 1
            java.lang.String r0 = r1.getString(r0)     // Catch:{ all -> 0x0072 }
            r2.b = r0     // Catch:{ all -> 0x0072 }
            r0 = 2
            java.lang.String r0 = r1.getString(r0)     // Catch:{ all -> 0x0072 }
            r3 = 3
            java.lang.String r3 = r1.getString(r3)     // Catch:{ all -> 0x0072 }
            android.content.Context r4 = r10.a     // Catch:{ all -> 0x0072 }
            com.umeng.analytics.pro.h r4 = com.umeng.analytics.pro.h.a(r4)     // Catch:{ all -> 0x0072 }
            java.lang.String r0 = r4.d(r0)     // Catch:{ all -> 0x0072 }
            r2.c = r0     // Catch:{ all -> 0x0072 }
            android.content.Context r0 = r10.a     // Catch:{ all -> 0x0072 }
            com.umeng.analytics.pro.h r0 = com.umeng.analytics.pro.h.a(r0)     // Catch:{ all -> 0x0072 }
            java.lang.String r0 = r0.d(r3)     // Catch:{ all -> 0x0072 }
            r2.d = r0     // Catch:{ all -> 0x0072 }
            r0 = 4
            java.lang.String r0 = r1.getString(r0)     // Catch:{ all -> 0x0072 }
            r2.e = r0     // Catch:{ all -> 0x0072 }
            r0 = 5
            java.lang.String r0 = r1.getString(r0)     // Catch:{ all -> 0x0072 }
            r2.f = r0     // Catch:{ all -> 0x0072 }
            r0 = r2
            goto L_0x0074
        L_0x0072:
            r0 = r2
            goto L_0x007b
        L_0x0074:
            if (r1 == 0) goto L_0x0081
        L_0x0076:
            r1.close()
            goto L_0x0081
        L_0x007a:
            r1 = r0
        L_0x007b:
            r10.d()     // Catch:{ all -> 0x0082 }
            if (r1 == 0) goto L_0x0081
            goto L_0x0076
        L_0x0081:
            return r0
        L_0x0082:
            r0 = move-exception
            r2 = r0
            if (r1 == 0) goto L_0x0089
            r1.close()
        L_0x0089:
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.ak.a(java.lang.String):com.umeng.analytics.pro.al");
    }

    public void a(String str, String str2) {
        a(str, "_uuid=?", new String[]{str2});
    }

    public Cursor a(String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            if (writableDatabase == null || !writableDatabase.isOpen()) {
                return null;
            }
            return writableDatabase.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
        } catch (Throwable unused) {
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x003a  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean b(java.lang.String r12) {
        /*
            r11 = this;
            r0 = 0
            android.database.sqlite.SQLiteDatabase r10 = r11.getWritableDatabase()     // Catch:{ all -> 0x0037 }
            if (r10 == 0) goto L_0x001b
            boolean r1 = r10.isOpen()     // Catch:{ all -> 0x0038 }
            if (r1 == 0) goto L_0x001b
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r1 = r10
            r2 = r12
            android.database.Cursor r12 = r1.query(r2, r3, r4, r5, r6, r7, r8, r9)     // Catch:{ all -> 0x0038 }
            r0 = r12
        L_0x001b:
            if (r0 == 0) goto L_0x002f
            int r12 = r0.getCount()     // Catch:{ all -> 0x0038 }
            if (r12 <= 0) goto L_0x002f
            r12 = 1
            if (r0 == 0) goto L_0x0029
            r0.close()
        L_0x0029:
            if (r10 == 0) goto L_0x002e
            r10.close()
        L_0x002e:
            return r12
        L_0x002f:
            if (r0 == 0) goto L_0x0034
            r0.close()
        L_0x0034:
            if (r10 == 0) goto L_0x0042
            goto L_0x003f
        L_0x0037:
            r10 = r0
        L_0x0038:
            if (r0 == 0) goto L_0x003d
            r0.close()
        L_0x003d:
            if (r10 == 0) goto L_0x0042
        L_0x003f:
            r10.close()
        L_0x0042:
            r12 = 0
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.analytics.pro.ak.b(java.lang.String):boolean");
    }

    public boolean c() {
        return !b(am.c);
    }
}
