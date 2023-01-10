package cn.missfresh.sherlock.trace.tracer;

import android.os.Looper;
import android.text.TextUtils;
import cn.missfresh.sherlock.config.Type;
import cn.missfresh.sherlock.e;
import cn.missfresh.sherlock.to.CommonTO;
import cn.missfresh.sherlock.to.TraceTO;
import cn.missfresh.sherlock.tool.Utils;
import cn.missfresh.sherlock.tool.j;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.sherlock.trace.core.b;
import cn.missfresh.sherlock.trace.tracer.g;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/* compiled from: EvilMethodTracer */
public class c extends h {
    private AppMethodBeat.d a;
    private long b;
    private boolean c;

    /* compiled from: EvilMethodTracer */
    private class a implements Runnable {
        long[] a;
        long b;
        long c;
        long d;

        /* compiled from: EvilMethodTracer */
        /* access modifiers changed from: package-private */
        /* renamed from: cn.missfresh.sherlock.trace.tracer.c$a$a  reason: collision with other inner class name */
        public class C0053a implements g.b {
            C0053a(a aVar) {
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

        a(c cVar, long[] jArr, long j, long j2, long j3) {
            this.c = j2;
            this.b = j;
            this.d = j3;
            this.a = jArr;
        }

        /* access modifiers changed from: package-private */
        public void a() {
            Utils.a(this.b, this.c);
            LinkedList linkedList = new LinkedList();
            long[] jArr = this.a;
            if (jArr.length > 0) {
                g.a(jArr, linkedList, false, this.d);
                g.a(linkedList, 30, new C0053a(this));
            }
            StringBuilder sb = new StringBuilder();
            long max = Math.max(this.c, g.a(linkedList, sb));
            String a = g.a(linkedList, max);
            if (!TextUtils.isEmpty(a)) {
                String a2 = Utils.a(Looper.getMainLooper().getThread().getStackTrace(), "|*\t\t", 12);
                try {
                    j.b("EvilMethodTracer", "emerge evil methoad");
                    TraceTO traceTO = new TraceTO();
                    traceTO.setAnr(0);
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
                    traceTO.setRegion(cn.missfresh.sherlock.tool.c.a(e.e()));
                    traceTO.setThreadBlock(0);
                    traceTO.setElapsedTime(max);
                    traceTO.setStack(sb.toString());
                    traceTO.setStackKey(a);
                    traceTO.setStackSize(linkedList.size());
                    traceTO.setDumpStack(a2);
                    cn.missfresh.sherlock.c.a().a((CommonTO) traceTO);
                } catch (Exception e) {
                    j.e("EvilMethodTracer", "[Exception error: " + e);
                }
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            a();
        }
    }

    public c(cn.missfresh.sherlock.trace.a.a aVar) {
        j.b("EvilMethodTracer", "init evi tracer");
        this.b = (long) aVar.g();
        this.c = aVar.c();
    }

    @Override // cn.missfresh.sherlock.trace.f.c
    public void a(long j, long j2, long j3) {
        super.a(j, j2, j3);
        this.a = AppMethodBeat.getInstance().maskIndex("EvilMethodTracer#dispatchBegin");
    }

    @Override // cn.missfresh.sherlock.trace.f.c
    public void a(String str, long j, long j2, long j3) {
    }

    @Override // cn.missfresh.sherlock.trace.tracer.h
    public void b() {
        super.b();
        if (this.c) {
            b.a().a(this);
        }
    }

    @Override // cn.missfresh.sherlock.trace.tracer.h
    public void c() {
        super.c();
        if (this.c) {
            b.a().b(this);
        }
    }

    @Override // cn.missfresh.sherlock.trace.f.c
    public void a(long j, long j2, long j3, long j4, long j5) {
        super.a(j, j2, j3, j4, j5);
        long j6 = j3 - j;
        try {
            if (j6 >= this.b) {
                cn.missfresh.sherlock.tool.g.c().post(new a(this, AppMethodBeat.getInstance().copyData(this.a), j4 - j2, j6, j3));
            }
        } finally {
            this.a.a();
        }
    }
}
