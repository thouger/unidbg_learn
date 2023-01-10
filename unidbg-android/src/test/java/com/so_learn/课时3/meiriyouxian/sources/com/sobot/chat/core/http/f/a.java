package com.sobot.chat.core.http.f;

import android.content.ContentValues;
import android.database.Cursor;
import com.sobot.chat.core.http.model.SobotProgress;

/* compiled from: SobotDownloadManager */
public class a extends b<SobotProgress> {
    private static a e;

    @Override // com.sobot.chat.core.http.f.b
    public String b() {
        return "fileCache";
    }

    private a() {
        super(new c());
    }

    public static a a() {
        if (e == null) {
            synchronized (a.class) {
                if (e == null) {
                    e = new a();
                }
            }
        }
        return e;
    }

    /* renamed from: a */
    public SobotProgress b(Cursor cursor) {
        return SobotProgress.parseCursorToBean(cursor);
    }

    public ContentValues a(SobotProgress sobotProgress) {
        return SobotProgress.buildContentValues(sobotProgress);
    }

    public SobotProgress a(String str) {
        return b("tag=?", new String[]{str});
    }

    public void b(String str) {
        a("tag=?", new String[]{str});
    }

    public boolean a(ContentValues contentValues, String str) {
        return a(contentValues, "tag=?", new String[]{str});
    }

    public void c() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (Integer) 0);
        a(contentValues, "status not in(?,?) and isUpload=?", new String[]{"5", "0", "0"});
    }
}
