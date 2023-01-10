package cn.missfresh.sherlock.b;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/* compiled from: SherlockSQLiteHelper */
/* access modifiers changed from: package-private */
public final class b extends SQLiteOpenHelper {
    b(Context context) {
        super(context, "sherlock.db", null, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE type (id INTEGER PRIMARY KEY AUTOINCREMENT, type INTEGER NOT NULL, common TEXT NOT NULL, extend TEXT);");
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS type");
        sQLiteDatabase.execSQL("CREATE TABLE type (id INTEGER PRIMARY KEY AUTOINCREMENT, type INTEGER NOT NULL, common TEXT NOT NULL, extend TEXT);");
    }
}
