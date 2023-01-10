package com.hmt.analytics.util;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* compiled from: DataBaseManager */
public class c {
    private static SQLiteOpenHelper a;
    private static SQLiteDatabase b;

    public static synchronized void a(SQLiteOpenHelper sQLiteOpenHelper) {
        synchronized (c.class) {
            a = sQLiteOpenHelper;
        }
    }

    public static synchronized SQLiteDatabase a() {
        SQLiteDatabase sQLiteDatabase;
        synchronized (c.class) {
            if (b == null || !b.isOpen()) {
                b = a.getWritableDatabase();
            }
            sQLiteDatabase = b;
        }
        return sQLiteDatabase;
    }

    public static synchronized void b() {
        synchronized (c.class) {
            if (b != null && b.isOpen()) {
                b.close();
            }
        }
    }
}
