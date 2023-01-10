package cn.missfresh.sherlock.b;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import cn.missfresh.sherlock.bo.SherlockBO;
import cn.missfresh.sherlock.config.Type;
import cn.missfresh.sherlock.to.ApmTO;
import cn.missfresh.sherlock.to.CommonTO;
import cn.missfresh.sherlock.to.CrashTO;
import cn.missfresh.sherlock.to.NetworkTO;
import cn.missfresh.sherlock.to.ScreenShotTO;
import cn.missfresh.sherlock.tool.e;
import cn.missfresh.sherlock.tool.j;
import java.util.ArrayList;
import java.util.List;

/* compiled from: DBManager */
public class a {
    private b a;
    private SQLiteDatabase b;

    /* compiled from: DBManager */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.sherlock.b.a$a  reason: collision with other inner class name */
    public class RunnableC0038a implements Runnable {
        RunnableC0038a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                j.a("DBManager", "query db start");
                if (!a.this.b.isOpen()) {
                    a.this.b = a.this.a.getWritableDatabase();
                }
                ArrayList arrayList = new ArrayList();
                Cursor query = a.this.b.query("type", null, null, null, null, null, null);
                if (query != null && query.getCount() > 0) {
                    j.b("DBManager", "cursor count :" + query.getCount());
                    while (query.moveToNext()) {
                        SherlockBO sherlockBO = new SherlockBO();
                        sherlockBO.setEventType(query.getInt(query.getColumnIndex("type")));
                        sherlockBO.setCommon(query.getString(query.getColumnIndex(ContactsContract.CommonDataKinds.PACKAGE_COMMON)));
                        sherlockBO.setExtend(query.getString(query.getColumnIndex("extend")));
                        arrayList.add(sherlockBO);
                    }
                    a.this.b.delete("type", null, null);
                    query.close();
                    j.a("DBManager", "query db end");
                    a.this.b(arrayList);
                }
            } catch (Exception e) {
                j.b("DBManager", "load all data exception message :" + e.getMessage());
            }
        }
    }

    /* compiled from: DBManager */
    /* access modifiers changed from: package-private */
    public class b implements Runnable {
        final /* synthetic */ List a;

        b(List list) {
            this.a = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (!a.this.b.isOpen()) {
                    a.this.b = a.this.a.getWritableDatabase();
                }
                a.this.b.beginTransaction();
                for (SherlockBO sherlockBO : this.a) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("type", Integer.valueOf(sherlockBO.getEventType()));
                    contentValues.put(ContactsContract.CommonDataKinds.PACKAGE_COMMON, sherlockBO.getCommon());
                    contentValues.put("extend", sherlockBO.getExtend());
                    long insert = a.this.b.insert("type", null, contentValues);
                    j.a("DBManager", "insert list result rowid : " + insert);
                }
                a.this.b.setTransactionSuccessful();
                j.b("DBManager", "insert list transaction successful");
                try {
                    a.this.b.endTransaction();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (Exception e2) {
                j.b("DBManager", "insert list exception message : " + e2.getMessage());
                try {
                    a.this.b.endTransaction();
                } catch (Exception e3) {
                    e3.printStackTrace();
                }
            } catch (Throwable th) {
                try {
                    a.this.b.endTransaction();
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
                throw th;
            }
        }
    }

    /* compiled from: DBManager */
    public static class c {
        private static final a a = new a(null);
    }

    /* synthetic */ a(RunnableC0038a aVar) {
        this();
    }

    private void c() {
        j.a("DBManager", "loadAll");
        cn.missfresh.sherlock.c.a().a((Runnable) new RunnableC0038a());
    }

    private a() {
    }

    public static a a() {
        return c.a;
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(List<SherlockBO> list) {
        if (!(list == null || list.isEmpty())) {
            j.b("DBManager", "report data form db, size :" + list.size());
            List<CommonTO> arrayList = new ArrayList<>();
            for (SherlockBO sherlockBO : list) {
                if (sherlockBO.getEventType() == Type.NETWORK.getValue()) {
                    arrayList.add(e.a.fromJson(sherlockBO.getCommon(), (Class<Object>) NetworkTO.class));
                } else if (sherlockBO.getEventType() == Type.CRASH.getValue()) {
                    arrayList.add(e.a.fromJson(sherlockBO.getCommon(), (Class<Object>) CrashTO.class));
                } else if (sherlockBO.getEventType() == Type.SCREENSHOT.getValue()) {
                    arrayList.add(e.a.fromJson(sherlockBO.getCommon(), (Class<Object>) ScreenShotTO.class));
                } else if (sherlockBO.getEventType() == Type.START.getValue()) {
                    arrayList.add(e.a.fromJson(sherlockBO.getCommon(), (Class<Object>) CommonTO.class));
                }
            }
            c(arrayList);
        }
    }

    private void c(List<CommonTO> list) {
        if (list != null && !list.isEmpty()) {
            j.b("DBManager", "send data form db");
            cn.missfresh.sherlock.c.a().a((Object) list);
        }
    }

    public synchronized void b() {
        try {
            j.b("DBManager", "initDataBase");
            this.a = new b(cn.missfresh.sherlock.e.e());
            this.b = this.a.getWritableDatabase();
            c();
        } catch (Exception e) {
            j.b("DBManager", "initDataBase Exception : " + e.getMessage());
        }
        return;
    }

    public void a(ApmTO apmTO) {
        if (!(apmTO == null || apmTO.getData() == null || apmTO.getData().isEmpty())) {
            j.b("DBManager", "save report data, size : " + apmTO.getData().size());
            ArrayList arrayList = new ArrayList();
            for (CommonTO commonTO : apmTO.getData()) {
                arrayList.add(b(commonTO));
            }
            a(arrayList);
        }
    }

    public synchronized void a(CommonTO commonTO) {
        if (!(this.a == null || this.b == null)) {
            if (commonTO != null) {
                j.b("DBManager", "inset crash data");
                if (!this.b.isOpen()) {
                    this.b = this.a.getWritableDatabase();
                }
                SherlockBO b2 = b(commonTO);
                try {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("type", Integer.valueOf(b2.getEventType()));
                    contentValues.put(ContactsContract.CommonDataKinds.PACKAGE_COMMON, b2.getCommon());
                    contentValues.put("extend", b2.getExtend());
                    long insert = this.b.insert("type", null, contentValues);
                    j.b("DBManager", "insert result rowid : " + insert);
                } catch (Exception e) {
                    j.b("DBManager", "insert exception message : " + e.getMessage());
                }
                return;
            }
        }
        j.a("DBManager", "insert return");
    }

    private SherlockBO b(CommonTO commonTO) {
        SherlockBO sherlockBO = new SherlockBO();
        sherlockBO.setEventType(commonTO.getEventType());
        sherlockBO.setCommon(e.a.toJson(commonTO));
        return sherlockBO;
    }

    private synchronized void a(List<SherlockBO> list) {
        if (!(this.a == null || this.b == null || list == null)) {
            if (!list.isEmpty()) {
                j.b("DBManager", "insert list, size :" + list.size() + "\uff0cthread name :" + Thread.currentThread().getName());
                cn.missfresh.sherlock.c.a().a((Runnable) new b(list));
                return;
            }
        }
        j.a("DBManager", "insert return");
    }
}
