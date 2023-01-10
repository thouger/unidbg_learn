package com.umeng.message.inapp;

import android.content.ContentValues;
import android.database.Cursor;
import com.umeng.message.proguard.m;

/* compiled from: InAppMsgLog */
/* access modifiers changed from: package-private */
public class a {
    long a;
    String b;
    int c;
    int d;
    int e;
    int f;
    int g;
    int h;
    int i;
    int j;

    a(String str, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        this.a = System.currentTimeMillis();
        this.b = str;
        this.c = i;
        this.d = i2;
        this.e = i3;
        this.f = i4;
        this.g = i5;
        this.h = i6;
        this.i = i7;
        this.j = i8;
    }

    a(Cursor cursor) {
        this.b = cursor.getString(cursor.getColumnIndex(m.j));
        this.c = cursor.getInt(cursor.getColumnIndex(m.k));
        this.d = cursor.getInt(cursor.getColumnIndex(m.t));
        this.e = cursor.getInt(cursor.getColumnIndex(m.u));
        this.f = cursor.getInt(cursor.getColumnIndex(m.v));
        this.g = cursor.getInt(cursor.getColumnIndex(m.w));
        this.h = cursor.getInt(cursor.getColumnIndex(m.x));
        this.i = cursor.getInt(cursor.getColumnIndex(m.y));
        this.j = cursor.getInt(cursor.getColumnIndex(m.z));
    }

    /* access modifiers changed from: package-private */
    public ContentValues a() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(m.n, Long.valueOf(this.a));
        contentValues.put(m.j, this.b);
        contentValues.put(m.k, Integer.valueOf(this.c));
        contentValues.put(m.t, Integer.valueOf(this.d));
        contentValues.put(m.u, Integer.valueOf(this.e));
        contentValues.put(m.v, Integer.valueOf(this.f));
        contentValues.put(m.w, Integer.valueOf(this.g));
        contentValues.put(m.x, Integer.valueOf(this.h));
        contentValues.put(m.y, Integer.valueOf(this.i));
        contentValues.put(m.z, Integer.valueOf(this.j));
        return contentValues;
    }
}
