package cn.missfresh.module.base.manager;

import android.content.Context;
import android.database.Cursor;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.db.sqlite.WhereBuilder;
import com.lidroid.xutils.exception.DbException;
import java.util.List;

/* compiled from: DbManager */
public class f {
    private static DbUtils a;
    private static f b;

    public static synchronized void a(Context context) {
        synchronized (f.class) {
            AppMethodBeat.i(14426, false);
            if (b == null) {
                b = new f();
                a = DbUtils.create(context, "mryx", 7, new a());
                a.configAllowTransaction(true);
            }
            AppMethodBeat.o(14426);
        }
    }

    public static f b(Context context) {
        AppMethodBeat.i(14428, false);
        if (context == null) {
            AppMethodBeat.o(14428);
            return null;
        }
        if (b == null) {
            synchronized (f.class) {
                try {
                    if (b == null) {
                        a(context.getApplicationContext());
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(14428);
                    throw th;
                }
            }
        }
        f fVar = b;
        AppMethodBeat.o(14428);
        return fVar;
    }

    public static synchronized Object a(Class cls, String str, String str2, Object obj) {
        Object obj2;
        synchronized (f.class) {
            AppMethodBeat.i(14429, false);
            obj2 = null;
            try {
                obj2 = a.findFirst(Selector.from(cls).where(str, str2, obj));
            } catch (DbException e) {
                e.printStackTrace();
            }
            AppMethodBeat.o(14429);
        }
        return obj2;
    }

    public static synchronized <T> List<T> a(Class<T> cls) {
        List<T> list;
        synchronized (f.class) {
            AppMethodBeat.i(14431, false);
            list = null;
            try {
                list = a.findAll(cls);
            } catch (DbException e) {
                e.printStackTrace();
            }
            AppMethodBeat.o(14431);
        }
        return list;
    }

    public static synchronized boolean a(Object obj) {
        boolean z;
        synchronized (f.class) {
            z = false;
            AppMethodBeat.i(14436, false);
            try {
                a.saveOrUpdate(obj);
                z = true;
            } catch (DbException unused) {
            }
            AppMethodBeat.o(14436);
        }
        return z;
    }

    public static synchronized boolean a(List<?> list) {
        boolean z;
        synchronized (f.class) {
            z = false;
            AppMethodBeat.i(14437, false);
            if (!b.a(list)) {
                try {
                    a.saveOrUpdateAll(list);
                } catch (DbException unused) {
                }
            }
            z = true;
            AppMethodBeat.o(14437);
        }
        return z;
    }

    public static synchronized boolean b(Object obj) {
        boolean z;
        synchronized (f.class) {
            z = false;
            AppMethodBeat.i(14444, false);
            try {
                a.delete(obj);
                z = true;
            } catch (DbException unused) {
            }
            AppMethodBeat.o(14444);
        }
        return z;
    }

    public static synchronized boolean a(Class cls, WhereBuilder whereBuilder) {
        boolean z;
        synchronized (f.class) {
            z = false;
            AppMethodBeat.i(14447, false);
            try {
                a.delete(cls, whereBuilder);
                z = true;
            } catch (DbException unused) {
            }
            AppMethodBeat.o(14447);
        }
        return z;
    }

    public static synchronized boolean b(Class cls) {
        boolean z;
        synchronized (f.class) {
            z = false;
            AppMethodBeat.i(14451, false);
            try {
                a.dropTable(cls);
                z = true;
            } catch (DbException unused) {
            }
            AppMethodBeat.o(14451);
        }
        return z;
    }

    public static synchronized Cursor a(String str) {
        Cursor cursor;
        synchronized (f.class) {
            AppMethodBeat.i(14453, false);
            cursor = null;
            try {
                cursor = a.execQuery(str);
            } catch (Exception e) {
                e.printStackTrace();
            }
            AppMethodBeat.o(14453);
        }
        return cursor;
    }

    public static synchronized boolean c(Class<?> cls) {
        boolean z;
        synchronized (f.class) {
            z = false;
            AppMethodBeat.i(14454, false);
            try {
                z = a.tableIsExist(cls);
            } catch (Exception e) {
                d.a("DbManager", e);
            }
            AppMethodBeat.o(14454);
        }
        return z;
    }

    public static synchronized long d(Class<?> cls) {
        long count;
        synchronized (f.class) {
            AppMethodBeat.i(14455, false);
            try {
                count = a.count(cls);
                AppMethodBeat.o(14455);
            } catch (DbException e) {
                e.printStackTrace();
                d.a("DbManager", (Exception) e);
                AppMethodBeat.o(14455);
                return 0;
            }
        }
        return count;
    }

    public static synchronized List a(Class<?> cls, int i, int i2, String str, boolean z) {
        List list;
        synchronized (f.class) {
            AppMethodBeat.i(14456, false);
            list = null;
            try {
                Selector from = Selector.from(cls);
                from.limit(i);
                from.offset(i2);
                from.orderBy(str, z);
                list = a.findAll(from);
            } catch (DbException e) {
                e.printStackTrace();
                d.a("DbManager", (Exception) e);
            }
            AppMethodBeat.o(14456);
        }
        return list;
    }

    /* compiled from: DbManager */
    /* access modifiers changed from: private */
    public static class a implements DbUtils.DbUpgradeListener {
        private a() {
        }

        @Override // com.lidroid.xutils.DbUtils.DbUpgradeListener
        public void onUpgrade(DbUtils dbUtils, int i, int i2) {
            AppMethodBeat.i(14422, false);
            d.d("DbManager", "DbManager old version is " + i + " & new version is " + i2);
            if (i == i2) {
                AppMethodBeat.o(14422);
                return;
            }
            switch (i) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                    try {
                        dbUtils.execNonQuery(" drop table if exists buycar_active_new ");
                        dbUtils.execNonQuery(" drop table if exists buycar_inactive_new ");
                        break;
                    } catch (Exception e) {
                        d.a("DbManager", e);
                        break;
                    }
            }
            AppMethodBeat.o(14422);
        }
    }

    public void c(Context context) {
        AppMethodBeat.i(14457, false);
        if (context == null) {
            AppMethodBeat.o(14457);
            return;
        }
        try {
            context.getApplicationContext().deleteDatabase("mryx");
        } catch (Exception unused) {
        }
        AppMethodBeat.o(14457);
    }
}
