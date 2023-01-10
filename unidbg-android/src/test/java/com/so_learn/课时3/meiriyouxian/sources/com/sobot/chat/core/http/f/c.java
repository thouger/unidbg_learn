package com.sobot.chat.core.http.f;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.sobot.chat.api.apiUtils.SobotApp;
import com.sobot.chat.core.http.model.SobotProgress;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: SobotDBHelper */
/* access modifiers changed from: package-private */
public class c extends SQLiteOpenHelper {
    static final Lock a = new ReentrantLock();
    private e b;

    private void a(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i == 1) {
        }
    }

    c() {
        this(SobotApp.getApplicationContext());
    }

    c(Context context) {
        super(context, "sobot.db", null, 1);
        this.b = new e("fileCache");
        this.b.a(new d("tag", "VARCHAR", true, true)).a(new d("url", "VARCHAR")).a(new d(SobotProgress.IS_UPLOAD, "INTEGER")).a(new d("folder", "VARCHAR")).a(new d("filePath", "VARCHAR")).a(new d(SobotProgress.FILE_NAME, "VARCHAR")).a(new d(SobotProgress.FRACTION, "VARCHAR")).a(new d(SobotProgress.TOTAL_SIZE, "INTEGER")).a(new d(SobotProgress.CURRENT_SIZE, "INTEGER")).a(new d("status", "INTEGER")).a(new d("priority", "INTEGER")).a(new d("date", "INTEGER"));
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        a(sQLiteDatabase);
        onUpgrade(sQLiteDatabase, 1, 1);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        sQLiteDatabase.beginTransaction();
        while (i < i2) {
            try {
                a(sQLiteDatabase, i, i2);
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            } catch (Throwable th) {
                sQLiteDatabase.endTransaction();
                throw th;
            }
        }
        sQLiteDatabase.setTransactionSuccessful();
        sQLiteDatabase.endTransaction();
    }

    private void a(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL(this.b.a());
    }
}
