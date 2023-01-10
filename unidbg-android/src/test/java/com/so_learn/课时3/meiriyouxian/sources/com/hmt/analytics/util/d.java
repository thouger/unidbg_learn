package com.hmt.analytics.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* compiled from: DataBaseOpenHelper */
public class d extends SQLiteOpenHelper {
    private static String a = "hmt_analytics";
    private static int b = 1;
    private static Context c;

    /* compiled from: DataBaseOpenHelper */
    /* access modifiers changed from: private */
    public static class a {
        private static final d a = new d();
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    private d() {
        super(c, a, null, b);
    }

    public static synchronized d a(Context context) {
        d dVar;
        synchronized (d.class) {
            c = context.getApplicationContext();
            dVar = a.a;
        }
        return dVar;
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS hmtInfo (id integer primary key autoincrement, type varchar(64), info text)");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS reqInfo (id integer primary key autoincrement, type varchar(64), info text)");
    }
}
