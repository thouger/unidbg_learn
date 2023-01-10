package cn.missfresh.sherlock;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import cn.missfresh.sherlock.config.Config;
import cn.missfresh.sherlock.to.CommonTO;
import cn.missfresh.sherlock.tool.j;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: SendManager */
public class c {
    private static cn.missfresh.sherlock.d.c a;
    private final List<CommonTO> b;

    /* compiled from: SendManager */
    public static class b {
        private static final c a = new c();
    }

    public static c a() {
        return b.a;
    }

    private synchronized void e() {
        if (!this.b.isEmpty()) {
            ArrayList arrayList = new ArrayList();
            arrayList.addAll(this.b);
            this.b.clear();
            a(arrayList, false);
        }
    }

    public synchronized void a(CommonTO commonTO) {
        if (commonTO != null) {
            if (System.currentTimeMillis() - commonTO.getTimestamp().longValue() >= b.a.longValue()) {
                j.b("SendManager", "time out, type :" + commonTO.getEventType());
                return;
            }
            this.b.add(c(commonTO));
            j.b("SendManager", "send common info, type :" + commonTO.getEventType() + ",serial num: " + commonTO.getSerialNum() + ", current size : " + this.b.size());
            if (this.b.size() >= Config.getInstance().memoryCacheCount) {
                e();
            }
        }
    }

    public synchronized void b() {
        j.a("SendManager", "send right by Timer");
        e();
    }

    public synchronized void b(CommonTO commonTO) {
        if (commonTO != null) {
            this.b.add(c(commonTO));
            j.b("SendManager", "send right by start, type : " + commonTO.getEventType() + ", serial num: " + commonTO.getSerialNum() + ", list size : " + this.b.size());
            e();
        }
    }

    private c() {
        this.b = new ArrayList();
        a = new cn.missfresh.sherlock.d.c();
    }

    private CommonTO c(CommonTO commonTO) {
        AtomicInteger o = e.o();
        if (o != null) {
            o.incrementAndGet();
            SharedPreferences defaultSharedPreferences = PreferenceManager.getDefaultSharedPreferences(e.e());
            commonTO.setSerialNum(Long.valueOf((long) o.get()));
            defaultSharedPreferences.edit().putInt("serial_num", o.get()).commit();
        }
        return commonTO;
    }

    public synchronized void d() {
        if (!this.b.isEmpty()) {
            j.b("SendManager", "save list, size : " + this.b.size());
            cn.missfresh.sherlock.b.a.a().a(cn.missfresh.sherlock.d.a.a(this.b));
            this.b.clear();
        }
    }

    private void b(List<CommonTO> list, boolean z) {
        cn.missfresh.sherlock.d.b bVar = new cn.missfresh.sherlock.d.b(a, cn.missfresh.sherlock.d.a.a(list));
        bVar.b(z);
        a.a(bVar);
    }

    public synchronized void a(Object obj) {
        if (obj != null) {
            try {
                a((List) obj);
            } catch (Exception unused) {
                j.b("SendManager", "cast to list exception");
            }
            return;
        }
        return;
    }

    public synchronized void a(Runnable runnable) {
        a.a(runnable);
    }

    private synchronized void a(List<CommonTO> list) {
        if (list != null) {
            if (!list.isEmpty()) {
                j.b("SendManager", "send from db ,list size :" + list.size());
                ArrayList arrayList = new ArrayList();
                for (CommonTO commonTO : list) {
                    if (System.currentTimeMillis() - commonTO.getTimestamp().longValue() >= b.a.longValue()) {
                        j.b("SendManager", "db time out, type :" + commonTO.getEventType());
                    } else {
                        arrayList.add(c(commonTO));
                    }
                }
                a(arrayList, true);
            }
        }
    }

    public synchronized void c() {
        j.b("SendManager", "clear");
        d();
    }

    private synchronized void a(List<CommonTO> list, boolean z) {
        if (list != null) {
            if (!list.isEmpty()) {
                int i = Config.getInstance().maxReportCount;
                j.b("SendManager", "enqueue size : " + list.size() + " ,fromDB : " + z + " ,maxCount : " + i);
                if (list.size() <= i) {
                    b(list, z);
                } else {
                    int size = list.size() / i;
                    int i2 = 0;
                    while (i2 < size) {
                        int i3 = i * i2;
                        i2++;
                        b(list.subList(i3, i * i2), z);
                    }
                    int i4 = size * i;
                    if (i4 < list.size()) {
                        b(list.subList(i4, list.size()), z);
                    }
                }
            }
        }
    }

    public synchronized void b(Runnable runnable) {
        a.b(runnable);
    }
}
