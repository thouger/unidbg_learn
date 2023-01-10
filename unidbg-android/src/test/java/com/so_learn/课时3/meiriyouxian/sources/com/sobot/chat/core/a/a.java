package com.sobot.chat.core.a;

import android.app.job.JobInfo;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.sobot.chat.core.a.a.b;
import com.sobot.chat.core.a.a.c;
import com.sobot.chat.core.a.a.d;
import com.sobot.chat.core.a.a.e;
import com.sobot.chat.core.a.a.f;
import com.sobot.chat.core.a.a.g;
import com.sobot.chat.core.a.a.h;
import com.sobot.chat.core.a.a.i;
import com.sobot.chat.core.a.a.j;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: SocketClient */
public class a {
    public static final String b = a.class.getSimpleName();
    private f A;
    private Timer B;
    private TimerTask C;
    final a a;
    private ExecutorService c;
    private com.sobot.chat.core.a.a.a d;
    private String e;
    private i f;
    private f g;
    private Socket h;
    private g i;
    private e j;
    private e k;
    private boolean l;
    private LinkedBlockingQueue<h> m;
    private long n;
    private long o;
    private long p;
    private h q;
    private j r;
    private long s;
    private C0143a t;
    private b u;
    private d v;
    private c w;
    private ArrayList<b> x;
    private ArrayList<d> y;
    private ArrayList<c> z;

    /* compiled from: SocketClient */
    public enum e {
        Disconnected,
        Connecting,
        Connected
    }

    public ExecutorService a() {
        if (this.c == null) {
            synchronized (a.class) {
                if (this.c == null) {
                    this.c = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), a("sobot SocketClient", false));
                }
            }
        }
        return this.c;
    }

    /* compiled from: SocketClient */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.a.a$1  reason: invalid class name */
    public class AnonymousClass1 implements ThreadFactory {
        final /* synthetic */ String a;
        final /* synthetic */ boolean b;

        AnonymousClass1(String str, boolean z) {
            this.a = str;
            this.b = z;
        }

        @Override // java.util.concurrent.ThreadFactory
        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(runnable, this.a);
            thread.setDaemon(this.b);
            return thread;
        }
    }

    public ThreadFactory a(String str, boolean z) {
        return new AnonymousClass1(str, z);
    }

    public a() {
        this(new com.sobot.chat.core.a.a.a());
    }

    public a(com.sobot.chat.core.a.a.a aVar) {
        this.a = this;
        this.p = -1;
        this.B = null;
        this.C = null;
        this.d = aVar;
    }

    public synchronized void b() {
        if (e()) {
            if (h() != null) {
                h().b();
                j().b();
                n().a(i()).a(h()).a(k()).a(j());
                a(e.Connecting);
                u().start();
                return;
            }
            throw new IllegalArgumentException("we need a SocketClientAddress to connect");
        }
    }

    public void c() {
        if (!e() && !p()) {
            a(true);
            v().start();
        }
    }

    public boolean d() {
        return o() == e.Connected;
    }

    public boolean e() {
        return o() == e.Disconnected;
    }

    public boolean f() {
        return o() == e.Connecting;
    }

    public com.sobot.chat.core.a.a.a g() {
        return n().b();
    }

    public h a(byte[] bArr) {
        if (!d()) {
            return null;
        }
        h hVar = new h(bArr);
        a(hVar);
        return hVar;
    }

    public h a(h hVar) {
        if (!d() || hVar == null) {
            return null;
        }
        a().execute(new AnonymousClass3(hVar));
        return hVar;
    }

    /* compiled from: SocketClient */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.a.a$3  reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ h a;

        AnonymousClass3(h hVar) {
            this.a = hVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.a.c(this.a);
        }
    }

    public a a(b bVar) {
        if (!y().contains(bVar)) {
            y().add(bVar);
        }
        return this;
    }

    public com.sobot.chat.core.a.a.a h() {
        if (this.d == null) {
            this.d = new com.sobot.chat.core.a.a.a();
        }
        return this.d;
    }

    public a a(String str) {
        this.e = str;
        return this;
    }

    public String i() {
        return this.e;
    }

    public i j() {
        if (this.f == null) {
            this.f = new i();
        }
        return this.f;
    }

    public f k() {
        if (this.g == null) {
            this.g = new f();
        }
        return this.g;
    }

    public Socket l() {
        if (this.h == null) {
            this.h = new Socket();
        }
        return this.h;
    }

    /* access modifiers changed from: protected */
    public a a(Socket socket) {
        this.h = socket;
        return this;
    }

    /* access modifiers changed from: protected */
    public a a(g gVar) {
        this.i = gVar;
        return this;
    }

    /* access modifiers changed from: protected */
    public g m() throws IOException {
        if (this.i == null) {
            this.i = new g(l().getInputStream());
        }
        return this.i;
    }

    /* access modifiers changed from: protected */
    public e n() {
        if (this.j == null) {
            this.j = new e();
        }
        return this.j;
    }

    /* access modifiers changed from: protected */
    public a a(e eVar) {
        this.k = eVar;
        return this;
    }

    public e o() {
        e eVar = this.k;
        return eVar == null ? e.Disconnected : eVar;
    }

    /* access modifiers changed from: protected */
    public a a(boolean z) {
        this.l = z;
        return this;
    }

    public boolean p() {
        return this.l;
    }

    /* access modifiers changed from: protected */
    public LinkedBlockingQueue<h> q() {
        if (this.m == null) {
            this.m = new LinkedBlockingQueue<>();
        }
        return this.m;
    }

    /* access modifiers changed from: protected */
    public a a(long j) {
        this.n = j;
        return this;
    }

    /* access modifiers changed from: protected */
    public a b(long j) {
        this.o = j;
        return this;
    }

    /* access modifiers changed from: protected */
    public a c(long j) {
        this.p = j;
        return this;
    }

    /* access modifiers changed from: protected */
    public a b(h hVar) {
        this.q = hVar;
        return this;
    }

    /* access modifiers changed from: protected */
    public h r() {
        return this.q;
    }

    /* access modifiers changed from: protected */
    public a a(j jVar) {
        this.r = jVar;
        return this;
    }

    /* access modifiers changed from: protected */
    public j s() {
        return this.r;
    }

    /* access modifiers changed from: protected */
    public a d(long j) {
        this.s = j;
        return this;
    }

    /* access modifiers changed from: protected */
    public long t() {
        return this.s;
    }

    /* access modifiers changed from: protected */
    public a a(C0143a aVar) {
        this.t = aVar;
        return this;
    }

    /* access modifiers changed from: protected */
    public C0143a u() {
        if (this.t == null) {
            this.t = new C0143a(this, null);
        }
        return this.t;
    }

    /* access modifiers changed from: protected */
    public a a(b bVar) {
        this.u = bVar;
        return this;
    }

    /* access modifiers changed from: protected */
    public b v() {
        if (this.u == null) {
            this.u = new b(this, null);
        }
        return this.u;
    }

    /* access modifiers changed from: protected */
    public a a(d dVar) {
        this.v = dVar;
        return this;
    }

    /* access modifiers changed from: protected */
    public d w() {
        if (this.v == null) {
            this.v = new d();
        }
        return this.v;
    }

    /* access modifiers changed from: protected */
    public a a(c cVar) {
        this.w = cVar;
        return this;
    }

    /* access modifiers changed from: protected */
    public c x() {
        if (this.w == null) {
            this.w = new c(this, null);
        }
        return this.w;
    }

    /* access modifiers changed from: protected */
    public ArrayList<b> y() {
        if (this.x == null) {
            this.x = new ArrayList<>();
        }
        return this.x;
    }

    /* access modifiers changed from: protected */
    public ArrayList<d> z() {
        if (this.y == null) {
            this.y = new ArrayList<>();
        }
        return this.y;
    }

    /* access modifiers changed from: protected */
    public ArrayList<c> A() {
        if (this.z == null) {
            this.z = new ArrayList<>();
        }
        return this.z;
    }

    /* access modifiers changed from: protected */
    public f B() {
        if (this.A == null) {
            this.A = new f(this);
        }
        return this.A;
    }

    /* compiled from: SocketClient */
    /* access modifiers changed from: private */
    public static class f extends Handler {
        private WeakReference<a> a;

        public f(a aVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference<>(aVar);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void c(h hVar) {
        if (d()) {
            synchronized (q()) {
                try {
                    q().put(hVar);
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }

    private void C() {
        if (d() && n() != null && n().d() != null && n().d().i()) {
            a().execute(new AnonymousClass4(new h(n().d().b(), true)));
        }
    }

    /* compiled from: SocketClient */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.a.a$4  reason: invalid class name */
    public class AnonymousClass4 implements Runnable {
        final /* synthetic */ h a;

        AnonymousClass4(h hVar) {
            this.a = hVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.a.c(this.a);
        }
    }

    /* compiled from: SocketClient */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.a.a$5  reason: invalid class name */
    public class AnonymousClass5 implements Runnable {
        AnonymousClass5() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.a.D();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void D() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            B().post(new AnonymousClass5());
            return;
        }
        ArrayList arrayList = (ArrayList) y().clone();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((b) arrayList.get(i)).a(this);
        }
        try {
            w().start();
            x().start();
            E();
        } catch (Exception unused) {
            this.a.c();
        }
    }

    private void E() {
        F();
        this.B = new Timer();
        this.C = new AnonymousClass6();
        this.B.schedule(this.C, JobInfo.MIN_BACKOFF_MILLIS, JobInfo.MIN_BACKOFF_MILLIS);
    }

    /* compiled from: SocketClient */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.a.a$6  reason: invalid class name */
    public class AnonymousClass6 extends TimerTask {
        AnonymousClass6() {
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            a.this.H();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void F() {
        Timer timer = this.B;
        if (timer != null) {
            timer.cancel();
            this.B = null;
        }
        TimerTask timerTask = this.C;
        if (timerTask != null) {
            timerTask.cancel();
            this.C = null;
        }
    }

    /* compiled from: SocketClient */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.a.a$7  reason: invalid class name */
    public class AnonymousClass7 implements Runnable {
        AnonymousClass7() {
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.a.G();
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void G() {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            B().post(new AnonymousClass7());
            return;
        }
        ArrayList arrayList = (ArrayList) y().clone();
        int size = arrayList.size();
        for (int i = 0; i < size; i++) {
            ((b) arrayList.get(i)).b(this);
        }
    }

    /* compiled from: SocketClient */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.a.a$8  reason: invalid class name */
    public class AnonymousClass8 implements Runnable {
        final /* synthetic */ j a;

        AnonymousClass8(j jVar) {
            this.a = jVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.a.b(this.a);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(j jVar) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            B().post(new AnonymousClass8(jVar));
            return;
        }
        b(System.currentTimeMillis());
        if (y().size() > 0) {
            ArrayList arrayList = (ArrayList) y().clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((b) arrayList.get(i)).a(this, jVar);
            }
        }
    }

    /* compiled from: SocketClient */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.a.a$9  reason: invalid class name */
    public class AnonymousClass9 implements Runnable {
        final /* synthetic */ h a;

        AnonymousClass9(h hVar) {
            this.a = hVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.a.d(this.a);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void d(h hVar) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            B().post(new AnonymousClass9(hVar));
        } else if (y().size() > 0) {
            ArrayList arrayList = (ArrayList) z().clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((d) arrayList.get(i)).a(this, hVar);
            }
        }
    }

    /* compiled from: SocketClient */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.a.a$10  reason: invalid class name */
    public class AnonymousClass10 implements Runnable {
        final /* synthetic */ h a;

        AnonymousClass10(h hVar) {
            this.a = hVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.a.e(this.a);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void e(h hVar) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            B().post(new AnonymousClass10(hVar));
        } else if (y().size() > 0) {
            ArrayList arrayList = (ArrayList) z().clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((d) arrayList.get(i)).b(this, hVar);
            }
        }
    }

    /* compiled from: SocketClient */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.a.a$11  reason: invalid class name */
    public class AnonymousClass11 implements Runnable {
        final /* synthetic */ h a;

        AnonymousClass11(h hVar) {
            this.a = hVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.a.f(this.a);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void f(h hVar) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            B().post(new AnonymousClass11(hVar));
        } else if (y().size() > 0) {
            ArrayList arrayList = (ArrayList) z().clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((d) arrayList.get(i)).c(this, hVar);
            }
        }
    }

    /* compiled from: SocketClient */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.a.a$12  reason: invalid class name */
    public class AnonymousClass12 implements Runnable {
        final /* synthetic */ h a;
        final /* synthetic */ int b;
        final /* synthetic */ int c;
        final /* synthetic */ int d;
        final /* synthetic */ int e;
        final /* synthetic */ int f;

        AnonymousClass12(h hVar, int i, int i2, int i3, int i4, int i5) {
            this.a = hVar;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
            this.f = i5;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.a.a(this.a, this.b, this.c, this.d, this.e, this.f);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(h hVar, int i, int i2, int i3, int i4, int i5) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            B().post(new AnonymousClass12(hVar, i, i2, i3, i4, i5));
            return;
        }
        float f2 = ((float) i) / ((float) (((i2 + i3) + i4) + i5));
        if (y().size() > 0) {
            ArrayList arrayList = (ArrayList) z().clone();
            int size = arrayList.size();
            for (int i6 = 0; i6 < size; i6++) {
                ((d) arrayList.get(i6)).a(this, hVar, f2, i);
            }
        }
    }

    /* compiled from: SocketClient */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.a.a$13  reason: invalid class name */
    public class AnonymousClass13 implements Runnable {
        final /* synthetic */ j a;

        AnonymousClass13(j jVar) {
            this.a = jVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.a.c(this.a);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void c(j jVar) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            B().post(new AnonymousClass13(jVar));
        } else if (A().size() > 0) {
            ArrayList arrayList = (ArrayList) A().clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((c) arrayList.get(i)).a(this, jVar);
            }
        }
    }

    /* compiled from: SocketClient */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.a.a$14  reason: invalid class name */
    public class AnonymousClass14 implements Runnable {
        final /* synthetic */ j a;

        AnonymousClass14(j jVar) {
            this.a = jVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.a.d(this.a);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void d(j jVar) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            B().post(new AnonymousClass14(jVar));
        } else if (A().size() > 0) {
            ArrayList arrayList = (ArrayList) A().clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((c) arrayList.get(i)).b(this, jVar);
            }
        }
    }

    /* compiled from: SocketClient */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.a.a$15  reason: invalid class name */
    public class AnonymousClass15 implements Runnable {
        final /* synthetic */ j a;

        AnonymousClass15(j jVar) {
            this.a = jVar;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.a.e(this.a);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void e(j jVar) {
        if (Looper.myLooper() != Looper.getMainLooper()) {
            B().post(new AnonymousClass15(jVar));
        } else if (A().size() > 0) {
            ArrayList arrayList = (ArrayList) A().clone();
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                ((c) arrayList.get(i)).c(this, jVar);
            }
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(j jVar, int i, int i2, int i3, int i4, int i5) {
        if (System.currentTimeMillis() - t() >= 41) {
            if (Looper.myLooper() != Looper.getMainLooper()) {
                B().post(new AnonymousClass2(jVar, i, i2, i3, i4, i5));
                return;
            }
            float f2 = ((float) i) / ((float) (((i2 + i3) + i4) + i5));
            if (A().size() > 0) {
                ArrayList arrayList = (ArrayList) A().clone();
                int size = arrayList.size();
                for (int i6 = 0; i6 < size; i6++) {
                    ((c) arrayList.get(i6)).a(this, jVar, f2, i);
                }
            }
            d(System.currentTimeMillis());
        }
    }

    /* compiled from: SocketClient */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.core.a.a$2  reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ j a;
        final /* synthetic */ int b;
        final /* synthetic */ int c;
        final /* synthetic */ int d;
        final /* synthetic */ int e;
        final /* synthetic */ int f;

        AnonymousClass2(j jVar, int i, int i2, int i3, int i4, int i5) {
            this.a = jVar;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = i4;
            this.f = i5;
        }

        @Override // java.lang.Runnable
        public void run() {
            a.this.a.a(this.a, this.b, this.c, this.d, this.e, this.f);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void H() {
        if (d()) {
            try {
                long currentTimeMillis = System.currentTimeMillis();
                if (n().d().i()) {
                    C();
                    a(currentTimeMillis);
                }
            } catch (Exception unused) {
                c();
            }
        }
    }

    /* compiled from: SocketClient */
    /* access modifiers changed from: private */
    /* renamed from: com.sobot.chat.core.a.a$a  reason: collision with other inner class name */
    public class C0143a extends Thread {
        private C0143a() {
        }

        /* synthetic */ C0143a(a aVar, AnonymousClass1 r2) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            try {
                com.sobot.chat.core.a.a.a b = a.this.a.n().b();
                if (!Thread.interrupted()) {
                    a.this.a.l().setTcpNoDelay(true);
                    a.this.a.l().setKeepAlive(true);
                    a.this.a.l().setSoTimeout(50000);
                    a.this.a.l().connect(b.d(), b.g());
                    if (!Thread.interrupted()) {
                        a.this.a.a(e.Connected);
                        a.this.a.a(System.currentTimeMillis());
                        a.this.a.b(System.currentTimeMillis());
                        a.this.a.c(-1);
                        a.this.a.b((h) null);
                        a.this.a.a((j) null);
                        a.this.a.a((C0143a) null);
                        a.this.a.D();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                a.this.a.c();
            }
        }
    }

    /* compiled from: SocketClient */
    /* access modifiers changed from: private */
    public class b extends Thread {
        private b() {
        }

        /* synthetic */ b(a aVar, AnonymousClass1 r2) {
            this();
        }

        /* JADX WARNING: Removed duplicated region for block: B:27:0x009e  */
        /* JADX WARNING: Removed duplicated region for block: B:30:0x00ba  */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x00e2  */
        /* JADX WARNING: Removed duplicated region for block: B:36:0x0108 A[LOOP:0: B:34:0x00f8->B:36:0x0108, LOOP_END] */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x011a  */
        /* JADX WARNING: Removed duplicated region for block: B:42:0x0110 A[EDGE_INSN: B:42:0x0110->B:37:0x0110 ?: BREAK  , SYNTHETIC] */
        @Override // java.lang.Thread, java.lang.Runnable
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            // Method dump skipped, instructions count: 336
            */
            throw new UnsupportedOperationException("Method not decompiled: com.sobot.chat.core.a.a.b.run():void");
        }
    }

    /* compiled from: SocketClient */
    /* access modifiers changed from: private */
    public class d extends Thread {
        public d() {
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            h take;
            int i;
            int i2;
            int i3;
            int i4;
            super.run();
            while (a.this.a.d() && !Thread.interrupted() && (take = a.this.a.q().take()) != null) {
                try {
                    a.this.a.b(take);
                    a.this.a.c(System.currentTimeMillis());
                    if (take.a() == null && take.b() != null) {
                        if (a.this.a.n().a() != null) {
                            take.a(a.this.a.n().a());
                        } else {
                            throw new IllegalArgumentException("we need string charset to send string type message");
                        }
                    }
                    if (take.a() == null) {
                        a.this.a.f(take);
                        a.this.a.b((h) null);
                    } else {
                        byte[] d = a.this.a.n().c().d();
                        int i5 = 0;
                        if (d == null) {
                            i = 0;
                        } else {
                            i = d.length;
                        }
                        byte[] f = a.this.a.n().c().f();
                        if (f == null) {
                            i2 = 0;
                        } else {
                            i2 = f.length;
                        }
                        byte[] a = a.this.a.n().c().a(take.a().length + i2);
                        if (a == null) {
                            i3 = 0;
                        } else {
                            i3 = a.length;
                        }
                        take.a(d);
                        take.c(f);
                        take.b(a);
                        if (i + i3 + take.a().length + i2 <= 0) {
                            a.this.a.f(take);
                            a.this.a.b((h) null);
                        } else {
                            a.this.a.d(take);
                            a.this.a.a(take, 0, i, i3, take.a().length, i2);
                            if (i > 0) {
                                try {
                                    a.this.a.l().getOutputStream().write(d);
                                    a.this.a.l().getOutputStream().flush();
                                    a.this.a.c(System.currentTimeMillis());
                                    i4 = i + 0;
                                    a.this.a.a(take, i4, i, i3, take.a().length, i2);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    if (a.this.a.r() != null) {
                                        a.this.a.f(a.this.a.r());
                                        a.this.a.b((h) null);
                                    }
                                }
                            } else {
                                i4 = 0;
                            }
                            if (i3 > 0) {
                                a.this.a.l().getOutputStream().write(a);
                                a.this.a.l().getOutputStream().flush();
                                a.this.a.c(System.currentTimeMillis());
                                i4 += i3;
                                a.this.a.a(take, i4, i, i3, take.a().length, i2);
                            }
                            if (take.a().length > 0) {
                                int sendBufferSize = a.this.a.l().getSendBufferSize();
                                if (a.this.a.n().c().h()) {
                                    sendBufferSize = Math.min(sendBufferSize, a.this.a.n().c().g());
                                }
                                while (i5 < take.a().length) {
                                    int min = Math.min(i5 + sendBufferSize, take.a().length);
                                    int i6 = min - i5;
                                    a.this.a.l().getOutputStream().write(take.a(), i5, i6);
                                    a.this.a.l().getOutputStream().flush();
                                    a.this.a.c(System.currentTimeMillis());
                                    i4 += i6;
                                    a.this.a.a(take, i4, i, i3, take.a().length, i2);
                                    i5 = min;
                                }
                            }
                            if (i2 > 0) {
                                a.this.a.l().getOutputStream().write(f);
                                a.this.a.l().getOutputStream().flush();
                                a.this.a.c(System.currentTimeMillis());
                                a.this.a.a(take, i4 + i2, i, i3, take.a().length, i2);
                            }
                            a.this.a.e(take);
                            a.this.a.b((h) null);
                            a.this.a.c(-1);
                        }
                    }
                } catch (InterruptedException unused) {
                    if (a.this.a.r() != null) {
                        a.this.a.f(a.this.a.r());
                        a.this.a.b((h) null);
                        return;
                    }
                    return;
                } catch (IllegalMonitorStateException unused2) {
                    a.this.m = null;
                    a.this.a.c();
                    if (a.this.a.r() != null) {
                        a.this.a.f(a.this.a.r());
                        a.this.a.b((h) null);
                        return;
                    }
                    return;
                }
            }
        }
    }

    /* compiled from: SocketClient */
    /* access modifiers changed from: private */
    public class c extends Thread {
        private c() {
        }

        /* synthetic */ c(a aVar, AnonymousClass1 r2) {
            this();
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            int i;
            int i2;
            int i3;
            super.run();
            try {
                if (a.this.a.n().c().k() != i.a.Manually) {
                    while (a.this.a.d() && a.this.a.m() != null && !Thread.interrupted()) {
                        j jVar = new j();
                        a.this.a.a(jVar);
                        byte[] l = a.this.a.n().c().l();
                        if (l == null) {
                            i = 0;
                        } else {
                            i = l.length;
                        }
                        byte[] o = a.this.a.n().c().o();
                        if (o == null) {
                            i2 = 0;
                        } else {
                            i2 = o.length;
                        }
                        int m = a.this.a.n().c().m();
                        a.this.a.c(jVar);
                        if (i > 0) {
                            byte[] a = a.this.a.m().a(l, true);
                            a.this.a.b(System.currentTimeMillis());
                            jVar.c(a);
                            i3 = i + 0;
                        } else {
                            i3 = 0;
                        }
                        if (a.this.a.n().c().k() == i.a.AutoReadByLength) {
                            if (m < 0) {
                                a.this.a.e(jVar);
                                a.this.a.a((j) null);
                            } else if (m == 0) {
                                a.this.a.d(jVar);
                                a.this.a.a((j) null);
                            }
                            byte[] a2 = a.this.a.m().a(m);
                            a.this.a.b(System.currentTimeMillis());
                            jVar.d(a2);
                            int i4 = i3 + m;
                            int a3 = a.this.a.n().c().a(a2) - i2;
                            if (a3 > 0) {
                                int receiveBufferSize = a.this.a.l().getReceiveBufferSize();
                                if (a.this.a.n().c().q()) {
                                    receiveBufferSize = Math.min(receiveBufferSize, a.this.a.n().c().p());
                                }
                                int i5 = i4;
                                int i6 = 0;
                                while (i6 < a3) {
                                    int min = Math.min(i6 + receiveBufferSize, a3);
                                    int i7 = min - i6;
                                    byte[] a4 = a.this.a.m().a(i7);
                                    a.this.a.b(System.currentTimeMillis());
                                    if (jVar.a() == null) {
                                        jVar.b(a4);
                                    } else {
                                        byte[] bArr = new byte[(jVar.a().length + a4.length)];
                                        System.arraycopy(jVar.a(), 0, bArr, 0, jVar.a().length);
                                        System.arraycopy(a4, 0, bArr, jVar.a().length, a4.length);
                                        jVar.b(bArr);
                                    }
                                    int i8 = i5 + i7;
                                    a.this.a.a(jVar, i8, i, m, a3, i2);
                                    i6 = min;
                                    i5 = i8;
                                }
                                i4 = i5;
                            } else if (a3 < 0) {
                                a.this.a.e(jVar);
                                a.this.a.a((j) null);
                            }
                            if (i2 > 0) {
                                byte[] a5 = a.this.a.m().a(i2);
                                a.this.a.b(System.currentTimeMillis());
                                jVar.e(a5);
                                a.this.a.a(jVar, i4 + i2, i, m, a3, i2);
                            }
                        } else if (a.this.a.n().c().k() == i.a.AutoReadToTrailer) {
                            if (i2 > 0) {
                                byte[] a6 = a.this.a.m().a(o, false);
                                a.this.a.b(System.currentTimeMillis());
                                jVar.b(a6);
                                jVar.e(o);
                                int length = a6.length;
                            } else {
                                a.this.a.e(jVar);
                                a.this.a.a((j) null);
                            }
                        }
                        jVar.a(a.this.a.n().d().a(jVar));
                        if (a.this.a.n().a() != null) {
                            jVar.a(a.this.a.n().a());
                        }
                        a.this.a.d(jVar);
                        a.this.a.b(jVar);
                        a.this.a.a((j) null);
                    }
                }
            } catch (Exception unused) {
                a.this.a.c();
                if (a.this.a.s() != null) {
                    a.this.a.e(a.this.a.s());
                    a.this.a.a((j) null);
                }
            }
        }
    }
}
