package cn.missfresh.sherlock.trace.tracer;

import android.os.Handler;
import android.text.TextUtils;
import android.util.TimeUtils;
import cn.missfresh.sherlock.bo.DropBO;
import cn.missfresh.sherlock.config.Config;
import cn.missfresh.sherlock.config.Type;
import cn.missfresh.sherlock.e;
import cn.missfresh.sherlock.to.CommonTO;
import cn.missfresh.sherlock.to.FpsTO;
import cn.missfresh.sherlock.tool.Utils;
import cn.missfresh.sherlock.tool.g;
import cn.missfresh.sherlock.tool.j;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class FrameTracer extends h {
    private HashSet<cn.missfresh.sherlock.trace.f.b> a = new HashSet<>();
    private final long b;
    private final cn.missfresh.sherlock.trace.a.a c;
    private boolean d;
    private long e;
    private long f;
    private long g;
    private long h;
    private long i;

    /* access modifiers changed from: private */
    public enum DropStatus {
        DROPPED_FROZEN(4),
        DROPPED_HIGH(3),
        DROPPED_MIDDLE(2),
        DROPPED_NORMAL(1),
        DROPPED_BEST(0);
        
        int index;

        private DropStatus(int i) {
            this.index = i;
        }
    }

    /* access modifiers changed from: package-private */
    public class a implements Runnable {
        final /* synthetic */ cn.missfresh.sherlock.trace.f.b a;
        final /* synthetic */ String b;
        final /* synthetic */ long c;
        final /* synthetic */ int d;

        a(FrameTracer frameTracer, cn.missfresh.sherlock.trace.f.b bVar, String str, long j, int i) {
            this.a = bVar;
            this.b = str;
            this.c = j;
            this.d = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.a.a(this.b, this.c, this.d);
        }
    }

    private class b extends cn.missfresh.sherlock.trace.f.b {
        private Handler b;
        private HashMap<String, c> c;

        private b() {
            this.b = new Handler(g.b().getLooper());
            this.c = new HashMap<>();
        }

        @Override // cn.missfresh.sherlock.trace.f.b
        public Handler a() {
            return this.b;
        }

        @Override // cn.missfresh.sherlock.trace.f.b
        public void a(String str, long j, int i) {
            super.a(str, j, i);
            if (!Utils.b(str)) {
                c cVar = this.c.get(str);
                if (cVar == null) {
                    cVar = new c(str);
                    this.c.put(str, cVar);
                }
                cVar.a(i);
                if (cVar.b >= ((long) (Config.getInstance().timeStep * 1000))) {
                    this.c.remove(str);
                    cVar.a();
                }
            }
        }

        /* synthetic */ b(FrameTracer frameTracer, a aVar) {
            this();
        }
    }

    public FrameTracer(cn.missfresh.sherlock.trace.a.a aVar) {
        j.b("FrameTracer", "init frame tracer");
        this.c = aVar;
        this.b = TimeUnit.MILLISECONDS.convert(cn.missfresh.sherlock.trace.core.b.a().f(), TimeUnit.NANOSECONDS) + 1;
        this.d = aVar.a();
        this.e = (long) aVar.j();
        this.f = (long) aVar.k();
        this.h = (long) aVar.m();
        this.g = (long) aVar.l();
        if (this.d) {
            a(new b(this, null));
        }
    }

    @Override // cn.missfresh.sherlock.trace.tracer.h
    public void c() {
        super.c();
        cn.missfresh.sherlock.trace.core.b.a().b(this);
    }

    public void a(cn.missfresh.sherlock.trace.f.b bVar) {
        synchronized (this.a) {
            this.a.add(bVar);
        }
    }

    @Override // cn.missfresh.sherlock.trace.tracer.h
    public void b() {
        super.b();
        cn.missfresh.sherlock.trace.core.b.a().a(this);
    }

    @Override // cn.missfresh.sherlock.trace.f.c
    public void a(String str, long j, long j2, long j3) {
        a(str, j3);
    }

    @Override // cn.missfresh.sherlock.trace.tracer.h, cn.missfresh.sherlock.trace.tracer.d
    public void a(boolean z) {
        super.a(z);
        if (z) {
            long j = this.i;
            if (j > 300) {
                j.e("FrameTracer", String.format("wrong! why do frame[%s] in background!!!", Long.valueOf(j)));
            }
            this.i = 0;
        }
    }

    private void a(String str, long j) {
        int i;
        Throwable th;
        HashSet<cn.missfresh.sherlock.trace.f.b> hashSet;
        Throwable th2;
        long currentTimeMillis = System.currentTimeMillis();
        int i2 = 1;
        try {
            HashSet<cn.missfresh.sherlock.trace.f.b> hashSet2 = this.a;
            synchronized (hashSet2) {
                try {
                    Iterator<cn.missfresh.sherlock.trace.f.b> it2 = this.a.iterator();
                    while (it2.hasNext()) {
                        cn.missfresh.sherlock.trace.f.b next = it2.next();
                        int i3 = (int) (j / this.b);
                        next.b(str, j, i3);
                        if (next.a() != null) {
                            hashSet = hashSet2;
                            i = i2;
                            try {
                                next.a().post(new a(this, next, str, j, i3));
                                hashSet2 = hashSet;
                                i2 = i;
                            } catch (Throwable th3) {
                                th2 = th3;
                                try {
                                    throw th2;
                                } catch (Throwable th4) {
                                    th = th4;
                                }
                            }
                        }
                    }
                    long currentTimeMillis2 = System.currentTimeMillis() - currentTimeMillis;
                    if (currentTimeMillis2 > this.b) {
                        Object[] objArr = new Object[i2];
                        objArr[0] = Long.valueOf(currentTimeMillis2);
                        j.d("FrameTracer", String.format("[notifyListener] warm! maybe do heavy work in doFrameSync,but you can replace with doFrameAsync! cost:%sms", objArr));
                    }
                    if (this.c.b() && !f()) {
                        this.i++;
                    }
                } catch (Throwable th5) {
                    th2 = th5;
                    hashSet = hashSet2;
                    i = 1;
                    throw th2;
                }
            }
        } catch (Throwable th6) {
            th = th6;
            i = 1;
            long currentTimeMillis3 = System.currentTimeMillis() - currentTimeMillis;
            if (currentTimeMillis3 > this.b) {
                Object[] objArr2 = new Object[i];
                objArr2[0] = Long.valueOf(currentTimeMillis3);
                j.d("FrameTracer", String.format("[notifyListener] warm! maybe do heavy work in doFrameSync,but you can replace with doFrameAsync! cost:%sms", objArr2));
            }
            if (this.c.b() && !f()) {
                this.i++;
            }
            throw th;
        }
    }

    private class c {
        String a;
        long b;
        long c;
        int d = 0;
        int e;
        int[] f = new int[DropStatus.values().length];
        int[] g = new int[DropStatus.values().length];

        c(String str) {
            this.a = str;
        }

        /* access modifiers changed from: package-private */
        public void a(int i) {
            long f = cn.missfresh.sherlock.trace.core.b.a().f();
            long j = this.b;
            if (j > 1000) {
                this.b = j + ((((long) (i + 1)) * f) / TimeUtils.NANOS_PER_MS);
                return;
            }
            this.c += (((long) (i + 1)) * f) / TimeUtils.NANOS_PER_MS;
            int i2 = this.d;
            this.b = (long) i2;
            this.e += i;
            this.d = i2 + 1;
            long j2 = (long) i;
            if (j2 >= FrameTracer.this.e) {
                int[] iArr = this.f;
                int i3 = DropStatus.DROPPED_FROZEN.index;
                iArr[i3] = iArr[i3] + 1;
                int[] iArr2 = this.g;
                iArr2[i3] = iArr2[i3] + i;
            } else if (j2 >= FrameTracer.this.f) {
                int[] iArr3 = this.f;
                int i4 = DropStatus.DROPPED_HIGH.index;
                iArr3[i4] = iArr3[i4] + 1;
                int[] iArr4 = this.g;
                iArr4[i4] = iArr4[i4] + i;
            } else if (j2 >= FrameTracer.this.g) {
                int[] iArr5 = this.f;
                int i5 = DropStatus.DROPPED_MIDDLE.index;
                iArr5[i5] = iArr5[i5] + 1;
                int[] iArr6 = this.g;
                iArr6[i5] = iArr6[i5] + i;
            } else if (j2 >= FrameTracer.this.h) {
                int[] iArr7 = this.f;
                int i6 = DropStatus.DROPPED_NORMAL.index;
                iArr7[i6] = iArr7[i6] + 1;
                int[] iArr8 = this.g;
                iArr8[i6] = iArr8[i6] + i;
            } else {
                int[] iArr9 = this.f;
                int i7 = DropStatus.DROPPED_BEST.index;
                iArr9[i7] = iArr9[i7] + 1;
                int[] iArr10 = this.g;
                int i8 = iArr10[i7];
                if (i < 0) {
                    i = 0;
                }
                iArr10[i7] = i8 + i;
            }
        }

        public String toString() {
            return "focusedActivityName=" + this.a + ", sumFrame=" + this.d + ", sumDroppedFrames=" + this.e + ", sumFrameCost=" + this.c + ", dropLevel=" + Arrays.toString(this.f);
        }

        /* access modifiers changed from: package-private */
        public void a() {
            try {
                float min = Math.min(60.0f, (((float) this.d) * 1000.0f) / ((float) this.c));
                j.b("FrameTracer", "emerge fps: " + min);
                FpsTO fpsTO = new FpsTO();
                fpsTO.setEventType(Type.DROPFPS.getValue());
                fpsTO.setTimestamp(Long.valueOf(System.currentTimeMillis()));
                fpsTO.setNetwork(cn.missfresh.sherlock.d.a.a(e.e()));
                fpsTO.setNetworkOperator(cn.missfresh.sherlock.d.a.b(e.e()));
                if (TextUtils.isEmpty(e.g())) {
                    fpsTO.setUserId(Utils.e(e.e()));
                } else {
                    fpsTO.setUserId(e.g());
                }
                fpsTO.setPhoneNumber(e.h());
                fpsTO.setRegion(cn.missfresh.sherlock.tool.c.a(e.e()));
                ArrayList arrayList = new ArrayList();
                DropBO dropBO = new DropBO();
                dropBO.setDropLevel("FROZEN");
                dropBO.setDropCount(this.f[DropStatus.DROPPED_FROZEN.index]);
                dropBO.setDropSum(this.g[DropStatus.DROPPED_FROZEN.index]);
                arrayList.add(dropBO);
                DropBO dropBO2 = new DropBO();
                dropBO2.setDropLevel("HIGH");
                dropBO2.setDropCount(this.f[DropStatus.DROPPED_HIGH.index]);
                dropBO2.setDropSum(this.g[DropStatus.DROPPED_HIGH.index]);
                arrayList.add(dropBO2);
                DropBO dropBO3 = new DropBO();
                dropBO3.setDropLevel("MIDDLE");
                dropBO3.setDropCount(this.f[DropStatus.DROPPED_MIDDLE.index]);
                dropBO3.setDropSum(this.g[DropStatus.DROPPED_MIDDLE.index]);
                arrayList.add(dropBO3);
                DropBO dropBO4 = new DropBO();
                dropBO4.setDropLevel("NORMAL");
                dropBO4.setDropCount(this.f[DropStatus.DROPPED_NORMAL.index]);
                dropBO4.setDropSum(this.g[DropStatus.DROPPED_NORMAL.index]);
                arrayList.add(dropBO4);
                DropBO dropBO5 = new DropBO();
                dropBO5.setDropLevel("BEST");
                dropBO5.setDropCount(this.f[DropStatus.DROPPED_BEST.index]);
                dropBO5.setDropSum(this.g[DropStatus.DROPPED_BEST.index]);
                arrayList.add(dropBO5);
                fpsTO.setDrops(arrayList);
                fpsTO.setActivity(this.a);
                fpsTO.setVc(this.a);
                fpsTO.setFps(min);
                fpsTO.setCpu(b.a().a(e.e()));
                if (e.d() && e.m() != null) {
                    j.b("FrameTracer", "emerge fps: " + min);
                    e.m().a(fpsTO);
                }
                cn.missfresh.sherlock.c.a().a((CommonTO) fpsTO);
            } catch (Exception e) {
                j.d("FrameTracer", "json error", e);
            } catch (Throwable th) {
                this.d = 0;
                this.e = 0;
                this.c = 0;
                throw th;
            }
            this.d = 0;
            this.e = 0;
            this.c = 0;
        }
    }
}
