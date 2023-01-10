package cn.missfresh.sherlock.trace.tracer;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import cn.missfresh.sherlock.Sherlock;
import cn.missfresh.sherlock.config.Config;
import cn.missfresh.sherlock.config.Type;
import cn.missfresh.sherlock.e;
import cn.missfresh.sherlock.to.StartAppTO;
import cn.missfresh.sherlock.tool.Utils;
import cn.missfresh.sherlock.tool.c;
import cn.missfresh.sherlock.tool.j;
import cn.missfresh.sherlock.trace.b;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.sherlock.trace.tracer.g;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;

/* compiled from: StartupTracer */
public class f extends h implements Application.ActivityLifecycleCallbacks, cn.missfresh.sherlock.trace.f.a {
    private long a = 0;
    private long b = 0;
    private int c;
    private boolean d;
    private boolean e;
    private Set<String> f;
    private long g;
    private long h;

    /* compiled from: StartupTracer */
    /* access modifiers changed from: private */
    public class a implements Runnable {
        long a;
        long b;
        long c;
        boolean d;
        int e;

        /* compiled from: StartupTracer */
        /* renamed from: cn.missfresh.sherlock.trace.tracer.f$a$a  reason: collision with other inner class name */
        class C0054a implements g.b {
            C0054a(a aVar) {
            }

            @Override // cn.missfresh.sherlock.trace.tracer.g.b
            public int a() {
                return 60;
            }

            @Override // cn.missfresh.sherlock.trace.tracer.g.b
            public void a(List<cn.missfresh.sherlock.trace.c.a> list, int i) {
                ListIterator<cn.missfresh.sherlock.trace.c.a> listIterator = list.listIterator(Math.min(i, 30));
                while (listIterator.hasNext()) {
                    listIterator.next();
                    listIterator.remove();
                }
            }

            @Override // cn.missfresh.sherlock.trace.tracer.g.b
            public boolean a(long j, int i) {
                return j < ((long) (i * 5));
            }
        }

        a(long j, long j2, long j3, boolean z, int i) {
            this.e = i;
            this.a = j;
            this.b = j2;
            this.c = j3;
            this.d = z;
        }

        private void a(long j, long j2, StringBuilder sb, String str, long j3, boolean z, int i) {
            j.b("StartupTracer", "start isWarm: " + z + ",has show splash: " + f.this.d);
            try {
                long j4 = f.this.d ? j2 : 0;
                long j5 = (j3 - j4) - j;
                int i2 = 3;
                j.b("StartupTracer", String.format("aCost:%s bCost:%s cCost:%s", Long.valueOf(j), Long.valueOf(j4), Long.valueOf(j5)));
                StartAppTO startAppTO = new StartAppTO();
                startAppTO.setEventType(Type.START.getValue());
                startAppTO.setTimestamp(Long.valueOf(System.currentTimeMillis()));
                startAppTO.setNetwork(cn.missfresh.sherlock.d.a.a(e.e()));
                startAppTO.setNetworkOperator(cn.missfresh.sherlock.d.a.b(e.e()));
                if (TextUtils.isEmpty(e.g())) {
                    startAppTO.setUserId(Utils.e(e.e()));
                } else {
                    startAppTO.setUserId(e.g());
                }
                startAppTO.setPhoneNumber(e.h());
                startAppTO.setRegion(c.a(e.e()));
                if (!z) {
                    i2 = 1;
                }
                startAppTO.setLaunchType(i2);
                startAppTO.setApplicationCreateTime(j < 1 ? 0 : j);
                if (j4 < 1) {
                    j4 = 0;
                }
                startAppTO.setAdPageCreateTime(j4);
                if (j5 < 1) {
                    j5 = 0;
                }
                startAppTO.setHomePageCreateTime(j5);
                startAppTO.setScene(f.this.a(i));
                startAppTO.setCpuName(cn.missfresh.sherlock.trace.a.c());
                startAppTO.setCpuNum(cn.missfresh.sherlock.trace.a.b());
                startAppTO.setCpuFreqKHz(cn.missfresh.sherlock.trace.a.d());
                startAppTO.setTotalRam(cn.missfresh.sherlock.trace.a.a((Context) e.e(), true));
                startAppTO.setAvailRam(cn.missfresh.sherlock.trace.a.a((Context) e.e(), false));
                startAppTO.setDensity((int) cn.missfresh.sherlock.trace.a.c(e.e()));
                startAppTO.setWidth(cn.missfresh.sherlock.trace.a.d(e.e()));
                startAppTO.setHeight(cn.missfresh.sherlock.trace.a.e(e.e()));
                startAppTO.setSize(cn.missfresh.sherlock.trace.a.h(e.e()));
                startAppTO.setName(cn.missfresh.sherlock.trace.a.a(Config.getInstance().mDeviceLevels, e.e()));
                if (Sherlock.sRequestPermissionsTime > 0) {
                    long uptimeMillis = SystemClock.uptimeMillis() - Sherlock.sRequestPermissionsTime;
                    if (uptimeMillis > 1000) {
                        if (startAppTO.getHomePageCreateTime() > uptimeMillis) {
                            startAppTO.setHomePageCreateTime(startAppTO.getHomePageCreateTime() - uptimeMillis);
                        } else {
                            startAppTO.setHomePageCreateTime(0);
                        }
                    }
                }
                if ((j3 > f.this.g && !z) || (j3 > f.this.h && z)) {
                    startAppTO.setStack(sb.toString());
                    startAppTO.setStackKey(str);
                }
                cn.missfresh.sherlock.c.a().b(startAppTO);
            } catch (Exception e) {
                j.e("StartupTracer", "StartUpReportTask error: " + e.getMessage());
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            long[] jArr = new long[0];
            if (!this.d && this.c >= f.this.g) {
                jArr = AppMethodBeat.getInstance().copyData(cn.missfresh.sherlock.trace.b.a.a);
                cn.missfresh.sherlock.trace.b.a.a.a();
            }
            LinkedList linkedList = new LinkedList();
            if (jArr.length > 0) {
                g.a(jArr, linkedList, false, -1);
                g.a(linkedList, 30, new C0054a(this));
            }
            StringBuilder sb = new StringBuilder();
            long max = Math.max(this.c, g.a(linkedList, sb));
            a(this.a, this.b, sb, g.a(linkedList, max), max, this.d, this.e);
        }
    }

    public f(cn.missfresh.sherlock.trace.a.a aVar) {
        j.b("StartupTracer", "init start up tracer");
        this.e = aVar.d();
        this.f = aVar.f();
        this.g = (long) aVar.h();
        this.h = (long) aVar.i();
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private String a(int i) {
        return i != 113 ? i != 114 ? "Activity" : "Service" : "Receiver";
    }

    private boolean g() {
        return this.b == 0;
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.sherlock.trace.tracer.h
    public void c() {
        super.c();
        if (this.e) {
            AppMethodBeat.getInstance().removeListener(this);
            b.i().a().unregisterActivityLifecycleCallbacks(this);
        }
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityCreated(Activity activity, Bundle bundle) {
        this.c++;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityDestroyed(Activity activity) {
        this.c--;
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityPaused(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityResumed(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStarted(Activity activity) {
    }

    @Override // android.app.Application.ActivityLifecycleCallbacks
    public void onActivityStopped(Activity activity) {
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.sherlock.trace.tracer.h
    public void b() {
        super.b();
        if (this.e) {
            AppMethodBeat.getInstance().addListener(this);
            b.i().a().registerActivityLifecycleCallbacks(this);
        }
    }

    @Override // cn.missfresh.sherlock.trace.f.a
    public void a(String str) {
        long j;
        long j2;
        j.a("StartupTracer", "onActivityFocused :" + str);
        if (g()) {
            if (this.a == 0) {
                this.a = SystemClock.uptimeMillis() - cn.missfresh.sherlock.trace.b.a.d();
            }
            if (this.d) {
                j2 = SystemClock.uptimeMillis() - cn.missfresh.sherlock.trace.b.a.c();
                this.b = j2;
            } else {
                if (this.f.contains(str)) {
                    this.d = true;
                } else if (this.f.isEmpty()) {
                    j.b("StartupTracer", String.format("default care activity[%s]", str));
                    j2 = this.a;
                    this.b = j2;
                } else {
                    j.b("StartupTracer", String.format("pass this activity[%s] in duration of startup!", str));
                }
                j2 = 0;
            }
            if (this.f.isEmpty() || !str.contains("MainActivity") || j2 != 0) {
                j = j2;
            } else {
                j.b("StartupTracer", "splash activity no show," + str);
                long uptimeMillis = SystemClock.uptimeMillis() - cn.missfresh.sherlock.trace.b.a.c();
                this.b = uptimeMillis;
                j = uptimeMillis;
            }
        } else {
            j = 0;
        }
        if (j > 0) {
            a(str, cn.missfresh.sherlock.trace.b.a.b(), this.a, j, false);
        }
    }

    private void a(String str, long j, long j2, long j3, boolean z) {
        cn.missfresh.sherlock.tool.g.c().post(new a(j, j2, j3, z, cn.missfresh.sherlock.trace.b.a.b));
    }
}
