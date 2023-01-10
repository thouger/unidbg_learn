package cn.missfresh.sherlock.trace.tracer;

import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.TimedRemoteCaller;
import cn.missfresh.sherlock.config.Type;
import cn.missfresh.sherlock.e;
import cn.missfresh.sherlock.to.CommonTO;
import cn.missfresh.sherlock.to.TraceTO;
import cn.missfresh.sherlock.tool.Utils;
import cn.missfresh.sherlock.tool.c;
import cn.missfresh.sherlock.tool.d;
import cn.missfresh.sherlock.tool.j;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.sherlock.trace.core.b;
import cn.missfresh.sherlock.trace.tracer.g;
import java.lang.Thread;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/* compiled from: AnrTracer */
public class a extends h {
    private Handler a;
    private volatile RunnableC0050a b;
    private boolean c;

    /* compiled from: AnrTracer */
    /* renamed from: cn.missfresh.sherlock.trace.tracer.a$a  reason: collision with other inner class name */
    class RunnableC0050a implements Runnable {
        AppMethodBeat.d a;

        /* compiled from: AnrTracer */
        /* renamed from: cn.missfresh.sherlock.trace.tracer.a$a$a  reason: collision with other inner class name */
        class C0051a implements g.b {
            C0051a(RunnableC0050a aVar) {
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

        RunnableC0050a(AppMethodBeat.d dVar) {
            this.a = dVar;
        }

        private void a(long[] jArr, Thread.State state, StringBuilder sb, long j, String str, String str2) {
        }

        public AppMethodBeat.d a() {
            return this.a;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                long uptimeMillis = SystemClock.uptimeMillis();
                long[] copyData = AppMethodBeat.getInstance().copyData(this.a);
                this.a.a();
                long[] g = a.this.g();
                Thread.State state = Looper.getMainLooper().getThread().getState();
                String a = Utils.a(Looper.getMainLooper().getThread().getStackTrace(), "|*\t\t", 12);
                LinkedList linkedList = new LinkedList();
                if (copyData.length > 0) {
                    g.a(copyData, linkedList, false, uptimeMillis);
                    g.a(linkedList, 30, new C0051a(this));
                }
                StringBuilder sb = new StringBuilder();
                long max = Math.max((long) TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS, g.a(linkedList, sb));
                String a2 = g.a(linkedList, max);
                a(g, state, sb, (long) linkedList.size(), a2, a);
                a(sb, a2, a, linkedList.size(), max);
            } catch (Exception e) {
                j.e("AnrTracer", "Exception error: " + e);
            }
        }

        private void a(StringBuilder sb, String str, String str2, int i, long j) {
            try {
                j.b("AnrTracer", "emerge anr");
                TraceTO traceTO = new TraceTO();
                traceTO.setAnr(1);
                traceTO.setEventType(Type.TRACE.getValue());
                traceTO.setActivity(cn.missfresh.sherlock.d.a.b());
                traceTO.setVc(cn.missfresh.sherlock.d.a.b());
                traceTO.setTimestamp(Long.valueOf(System.currentTimeMillis()));
                traceTO.setNetwork(cn.missfresh.sherlock.d.a.a(e.e()));
                traceTO.setNetworkOperator(cn.missfresh.sherlock.d.a.b(e.e()));
                if (TextUtils.isEmpty(e.g())) {
                    traceTO.setUserId(Utils.e(e.e()));
                } else {
                    traceTO.setUserId(e.g());
                }
                traceTO.setPhoneNumber(e.h());
                traceTO.setRegion(c.a(e.e()));
                traceTO.setThreadBlock(1);
                traceTO.setElapsedTime(j);
                traceTO.setStack(sb.toString());
                traceTO.setStackKey(str);
                traceTO.setDumpStack(str2);
                traceTO.setStackSize(i);
                cn.missfresh.sherlock.c.a().a((CommonTO) traceTO);
            } catch (Exception e) {
                j.e("AnrTracer", "Exception error: " + e);
            }
        }
    }

    public a(cn.missfresh.sherlock.trace.a.a aVar) {
        j.b("AnrTracer", "init anr tracer");
        this.c = aVar.e();
    }

    @Override // cn.missfresh.sherlock.trace.f.c
    public void a(String str, long j, long j2, long j3) {
    }

    @Override // cn.missfresh.sherlock.trace.tracer.h
    public void b() {
        super.b();
        if (this.c) {
            b.a().a(this);
            this.a = new Handler(cn.missfresh.sherlock.tool.g.a("Sherlock#AnrTracer").getLooper());
        }
    }

    @Override // cn.missfresh.sherlock.trace.tracer.h
    public void c() {
        super.c();
        if (this.c) {
            b.a().b(this);
            if (this.b != null) {
                this.b.a().a();
            }
            this.a.removeCallbacksAndMessages(null);
            this.a.getLooper().quit();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private long[] g() {
        return new long[]{d.a(), d.b(), d.c()};
    }

    @Override // cn.missfresh.sherlock.trace.f.c
    public void a(long j, long j2, long j3) {
        super.a(j, j2, j3);
        if (this.b != null) {
            this.a.removeCallbacks(this.b);
        }
        this.b = new RunnableC0050a(AppMethodBeat.getInstance().maskIndex("AnrTracer#dispatchBegin"));
        this.a.postDelayed(this.b, TimedRemoteCaller.DEFAULT_CALL_TIMEOUT_MILLIS - (SystemClock.uptimeMillis() - j3));
    }

    @Override // cn.missfresh.sherlock.trace.f.c
    public void a(long j, long j2, long j3, long j4, long j5) {
        super.a(j, j2, j3, j4, j5);
        if (this.b != null) {
            this.b.a().a();
            this.a.removeCallbacks(this.b);
        }
    }
}
