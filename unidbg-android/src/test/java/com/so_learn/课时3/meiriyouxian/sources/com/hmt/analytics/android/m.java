package com.hmt.analytics.android;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.hmt.analytics.util.c;
import com.hmt.analytics.util.d;
import com.hmt.analytics.util.e;
import java.io.IOException;
import java.util.ArrayList;

/* compiled from: HMTInfoService */
public class m {
    private static final String a = m.class.getSimpleName();
    private static Context b;

    /* compiled from: HMTInfoService */
    private static class a {
        public static final m a = new m();
    }

    private m() {
        c.a(d.a(b));
    }

    public static m a(Context context) {
        b = context.getApplicationContext();
        return a.a;
    }

    private void a() {
        String str = a;
        a.a(str, "Lock HashCode = " + m.class.hashCode());
    }

    public synchronized void a(String str, String str2, String str3) {
        String str4;
        String str5;
        a.a(a, "------save");
        a();
        try {
            str2 = l.d(str2);
        } catch (IOException e) {
            a.a(a, "Collected:" + e.getMessage());
        }
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = c.a();
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("insert into " + str3 + "(type,info)values(?,?)", new Object[]{str, str2});
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                try {
                    if (sQLiteDatabase.inTransaction()) {
                        sQLiteDatabase.endTransaction();
                    }
                } catch (Exception e2) {
                    a.a(a, "Collected:" + e2.getMessage());
                }
                try {
                    c.b();
                } catch (Exception e3) {
                    str4 = a;
                    str5 = "Collected:" + e3.getMessage();
                }
            }
        } catch (Exception e4) {
            a.a(a, "Collected:" + e4.getMessage());
            if (sQLiteDatabase != null) {
                try {
                    if (sQLiteDatabase.inTransaction()) {
                        sQLiteDatabase.endTransaction();
                    }
                } catch (Exception e5) {
                    a.a(a, "Collected:" + e5.getMessage());
                }
                try {
                    c.b();
                } catch (Exception e6) {
                    str4 = a;
                    str5 = "Collected:" + e6.getMessage();
                }
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                try {
                    if (sQLiteDatabase.inTransaction()) {
                        sQLiteDatabase.endTransaction();
                    }
                } catch (Exception e7) {
                    a.a(a, "Collected:" + e7.getMessage());
                }
                try {
                    c.b();
                } catch (Exception e8) {
                    a.a(a, "Collected:" + e8.getMessage());
                }
            }
            throw th;
        }
        a.a(str4, str5);
    }

    public synchronized void a(String str) {
        String str2;
        String str3;
        a.a(a, "------resetTableSequence");
        a();
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = c.a();
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("UPDATE sqlite_sequence SET seq = 0 WHERE name='" + str + "'");
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                try {
                    if (sQLiteDatabase.inTransaction()) {
                        sQLiteDatabase.endTransaction();
                    }
                } catch (Exception e) {
                    a.a(a, "Collected:" + e.getMessage());
                }
                try {
                    c.b();
                } catch (Exception e2) {
                    str2 = a;
                    str3 = "Collected:" + e2.getMessage();
                }
            }
        } catch (Exception e3) {
            a.a(a, "Collected:" + e3.getMessage());
            if (sQLiteDatabase != null) {
                try {
                    if (sQLiteDatabase.inTransaction()) {
                        sQLiteDatabase.endTransaction();
                    }
                } catch (Exception e4) {
                    a.a(a, "Collected:" + e4.getMessage());
                }
                try {
                    c.b();
                } catch (Exception e5) {
                    str2 = a;
                    str3 = "Collected:" + e5.getMessage();
                }
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                try {
                    if (sQLiteDatabase.inTransaction()) {
                        sQLiteDatabase.endTransaction();
                    }
                } catch (Exception e6) {
                    a.a(a, "Collected:" + e6.getMessage());
                }
                try {
                    c.b();
                } catch (Exception e7) {
                    a.a(a, "Collected:" + e7.getMessage());
                }
            }
            throw th;
        }
        a.a(str2, str3);
    }

    public synchronized ArrayList<e> a(String str, int i) {
        ArrayList<e> arrayList;
        String str2;
        String str3;
        a.a(a, "------getScrollData");
        a();
        arrayList = new ArrayList<>();
        Cursor cursor = null;
        try {
            Cursor rawQuery = c.a().rawQuery("select * from " + str + " order by id asc limit ?", new String[]{String.valueOf(i)});
            while (rawQuery.moveToNext()) {
                try {
                    arrayList.add(new e(Integer.valueOf(rawQuery.getInt(0)), rawQuery.getString(1), l.e(rawQuery.getString(2))));
                } catch (IOException e) {
                    a.a(a, "Collected:" + e.toString());
                }
            }
            if (rawQuery != null) {
                try {
                    rawQuery.close();
                } catch (Exception e2) {
                    a.a(a, "Collected:" + e2.getMessage());
                }
            }
            try {
                c.b();
            } catch (Exception e3) {
                str2 = a;
                str3 = "Collected:" + e3.getMessage();
            }
        } catch (Exception e4) {
            a.a(a, "Collected:" + e4.getMessage());
            if (0 != 0) {
                try {
                    cursor.close();
                } catch (Exception e5) {
                    a.a(a, "Collected:" + e5.getMessage());
                }
            }
            try {
                c.b();
            } catch (Exception e6) {
                str2 = a;
                str3 = "Collected:" + e6.getMessage();
            }
        } catch (Error e7) {
            a.a(a, "Collected:" + e7.getMessage());
            if (0 != 0) {
                try {
                    cursor.close();
                } catch (Exception e8) {
                    a.a(a, "Collected:" + e8.getMessage());
                }
            }
            try {
                c.b();
            } catch (Exception e9) {
                str2 = a;
                str3 = "Collected:" + e9.getMessage();
            }
        } catch (Throwable th) {
            if (0 != 0) {
                try {
                    cursor.close();
                } catch (Exception e10) {
                    a.a(a, "Collected:" + e10.getMessage());
                }
            }
            try {
                c.b();
            } catch (Exception e11) {
                a.a(a, "Collected:" + e11.getMessage());
            }
            throw th;
        }
        return arrayList;
        a.a(str2, str3);
        return arrayList;
    }

    public synchronized void b(String str, int i) {
        String str2;
        String str3;
        a.a(a, "------deleteData");
        a();
        SQLiteDatabase sQLiteDatabase = null;
        try {
            sQLiteDatabase = c.a();
        } catch (Exception e) {
            a.a(a, "Collected:" + e.getMessage());
        }
        try {
            sQLiteDatabase.beginTransaction();
            sQLiteDatabase.execSQL("delete from " + str + " where id<=" + i);
            sQLiteDatabase.setTransactionSuccessful();
            if (sQLiteDatabase != null) {
                try {
                    if (sQLiteDatabase.inTransaction()) {
                        sQLiteDatabase.endTransaction();
                    }
                } catch (Exception e2) {
                    a.a(a, "Collected:" + e2.getMessage());
                }
                try {
                    c.b();
                } catch (Exception e3) {
                    str2 = a;
                    str3 = "Collected:" + e3.getMessage();
                }
            }
        } catch (Exception e4) {
            a.a(a, "Collected:" + e4.getMessage());
            if (sQLiteDatabase != null) {
                try {
                    if (sQLiteDatabase.inTransaction()) {
                        sQLiteDatabase.endTransaction();
                    }
                } catch (Exception e5) {
                    a.a(a, "Collected:" + e5.getMessage());
                }
                try {
                    c.b();
                } catch (Exception e6) {
                    str2 = a;
                    str3 = "Collected:" + e6.getMessage();
                }
            }
        } catch (Throwable th) {
            if (sQLiteDatabase != null) {
                try {
                    if (sQLiteDatabase.inTransaction()) {
                        sQLiteDatabase.endTransaction();
                    }
                } catch (Exception e7) {
                    a.a(a, "Collected:" + e7.getMessage());
                }
                try {
                    c.b();
                } catch (Exception e8) {
                    a.a(a, "Collected:" + e8.getMessage());
                }
            }
            throw th;
        }
        a.a(str2, str3);
    }
}
