package com.sobot.chat.core.http.f;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

/* compiled from: SobotBaseDao */
public abstract class b<T> {
    protected static String a;
    protected Lock b = c.a;
    protected SQLiteOpenHelper c;
    protected SQLiteDatabase d;

    public abstract ContentValues a(T t);

    public abstract T b(Cursor cursor);

    public abstract String b();

    public b(SQLiteOpenHelper sQLiteOpenHelper) {
        a = getClass().getSimpleName();
        this.c = sQLiteOpenHelper;
        this.d = d();
    }

    public SQLiteDatabase d() {
        try {
            return this.c.getWritableDatabase();
        } catch (Exception unused) {
            return null;
        }
    }

    /* access modifiers changed from: protected */
    public final void a(SQLiteDatabase sQLiteDatabase, Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        if (sQLiteDatabase != null && sQLiteDatabase.isOpen()) {
            sQLiteDatabase.close();
        }
    }

    public boolean a(String str, String[] strArr) {
        if (this.d == null) {
            return false;
        }
        System.currentTimeMillis();
        this.b.lock();
        try {
            this.d.beginTransaction();
            this.d.delete(b(), str, strArr);
            this.d.setTransactionSuccessful();
            return true;
        } catch (Exception unused) {
            return false;
        } finally {
            this.d.endTransaction();
            this.b.unlock();
        }
    }

    public boolean b(T t) {
        if (t == null || this.d == null) {
            return false;
        }
        System.currentTimeMillis();
        this.b.lock();
        try {
            this.d.beginTransaction();
            this.d.replace(b(), null, a(t));
            this.d.setTransactionSuccessful();
            return true;
        } catch (Exception unused) {
            return false;
        } finally {
            this.d.endTransaction();
            this.b.unlock();
        }
    }

    public boolean a(ContentValues contentValues, String str, String[] strArr) {
        if (this.d == null) {
            return false;
        }
        System.currentTimeMillis();
        this.b.lock();
        try {
            this.d.beginTransaction();
            this.d.update(b(), contentValues, str, strArr);
            this.d.setTransactionSuccessful();
            return true;
        } catch (Exception unused) {
            return false;
        } finally {
            this.d.endTransaction();
            this.b.unlock();
        }
    }

    public T b(String str, String[] strArr) {
        System.currentTimeMillis();
        List<T> a2 = a(null, str, strArr, null, null, null, "1");
        if (a2.size() > 0) {
            return a2.get(0);
        }
        return null;
    }

    public List<T> a(String[] strArr, String str, String[] strArr2, String str2, String str3, String str4, String str5) {
        Cursor cursor;
        Throwable th;
        if (this.d == null) {
            return new ArrayList();
        }
        System.currentTimeMillis();
        this.b.lock();
        ArrayList arrayList = new ArrayList();
        try {
            this.d.beginTransaction();
            cursor = this.d.query(b(), strArr, str, strArr2, str2, str3, str4, str5);
            while (!cursor.isClosed() && cursor.moveToNext()) {
                try {
                    arrayList.add(b(cursor));
                } catch (Exception unused) {
                } catch (Throwable th2) {
                    th = th2;
                    a((SQLiteDatabase) null, cursor);
                    this.d.endTransaction();
                    this.b.unlock();
                    throw th;
                }
            }
            this.d.setTransactionSuccessful();
        } catch (Exception unused2) {
            cursor = null;
        } catch (Throwable th3) {
            th = th3;
            cursor = null;
            a((SQLiteDatabase) null, cursor);
            this.d.endTransaction();
            this.b.unlock();
            throw th;
        }
        a((SQLiteDatabase) null, cursor);
        this.d.endTransaction();
        this.b.unlock();
        return arrayList;
    }
}
