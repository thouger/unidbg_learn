package com.google.android.exoplayer2.drm;

import android.media.NotProvisionedException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Pair;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.ExoMediaDrm;
import com.google.android.exoplayer2.drm.d;
import com.google.android.exoplayer2.util.f;
import com.google.android.exoplayer2.util.i;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/* access modifiers changed from: package-private */
public class DefaultDrmSession<T extends d> implements DrmSession<T> {
    public final List<DrmInitData.SchemeData> a;
    final f b;
    final UUID c;
    final DefaultDrmSession<T>.b d;
    private final ExoMediaDrm<T> e;
    private final c<T> f;
    private final int g;
    private final HashMap<String, String> h;
    private final f<a> i;
    private final int j;
    private int k;
    private int l;
    private HandlerThread m;
    private DefaultDrmSession<T>.a n;
    private T o;
    private DrmSession.DrmSessionException p;
    private byte[] q;
    private byte[] r;
    private ExoMediaDrm.a s;
    private ExoMediaDrm.b t;

    public interface c<T extends d> {
        void a();

        void a(DefaultDrmSession<T> defaultDrmSession);

        void a(Exception exc);
    }

    public DefaultDrmSession(UUID uuid, ExoMediaDrm<T> exoMediaDrm, c<T> cVar, List<DrmInitData.SchemeData> list, int i, byte[] bArr, HashMap<String, String> hashMap, f fVar, Looper looper, f<a> fVar2, int i2) {
        this.c = uuid;
        this.f = cVar;
        this.e = exoMediaDrm;
        this.g = i;
        this.r = bArr;
        this.a = bArr == null ? Collections.unmodifiableList(list) : null;
        this.h = hashMap;
        this.b = fVar;
        this.j = i2;
        this.i = fVar2;
        this.k = 2;
        this.d = new b(looper);
        this.m = new HandlerThread("DrmRequestHandler");
        this.m.start();
        this.n = new a(this.m.getLooper());
    }

    public void a() {
        int i = this.l + 1;
        this.l = i;
        if (i == 1 && this.k != 1 && a(true)) {
            b(true);
        }
    }

    public boolean b() {
        int i = this.l - 1;
        this.l = i;
        if (i != 0) {
            return false;
        }
        this.k = 0;
        this.d.removeCallbacksAndMessages(null);
        this.n.removeCallbacksAndMessages(null);
        this.n = null;
        this.m.quit();
        this.m = null;
        this.o = null;
        this.p = null;
        this.s = null;
        this.t = null;
        byte[] bArr = this.q;
        if (bArr != null) {
            this.e.a(bArr);
            this.q = null;
            this.i.a($$Lambda$VzISwywgB_cts86lmJHUVQ25JDs.INSTANCE);
        }
        return true;
    }

    public boolean a(byte[] bArr) {
        return Arrays.equals(this.q, bArr);
    }

    public void onMediaDrmEvent(int i) {
        if (l()) {
            if (i == 1) {
                this.k = 3;
                this.f.a(this);
            } else if (i == 2) {
                b(false);
            } else if (i == 3) {
                k();
            }
        }
    }

    public void c() {
        this.t = this.e.b();
        this.n.a(0, this.t, true);
    }

    public void d() {
        if (a(false)) {
            b(true);
        }
    }

    public void a(Exception exc) {
        c(exc);
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public final int e() {
        return this.k;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public final DrmSession.DrmSessionException f() {
        if (this.k == 1) {
            return this.p;
        }
        return null;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public final T g() {
        return this.o;
    }

    @Override // com.google.android.exoplayer2.drm.DrmSession
    public Map<String, String> h() {
        byte[] bArr = this.q;
        if (bArr == null) {
            return null;
        }
        return this.e.c(bArr);
    }

    private boolean a(boolean z) {
        if (l()) {
            return true;
        }
        try {
            this.q = this.e.a();
            this.i.a($$Lambda$3VqJHu7zfniNl4yIHVIpUrWWZLA.INSTANCE);
            this.o = (T) this.e.d(this.q);
            this.k = 3;
            return true;
        } catch (NotProvisionedException e) {
            if (z) {
                this.f.a(this);
                return false;
            }
            c(e);
            return false;
        } catch (Exception e2) {
            c(e2);
            return false;
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(Object obj, Object obj2) {
        if (obj != this.t) {
            return;
        }
        if (this.k == 2 || l()) {
            this.t = null;
            if (obj2 instanceof Exception) {
                this.f.a((Exception) obj2);
                return;
            }
            try {
                this.e.b((byte[]) obj2);
                this.f.a();
            } catch (Exception e) {
                this.f.a(e);
            }
        }
    }

    private void b(boolean z) {
        int i = this.g;
        if (i == 0 || i == 1) {
            if (this.r == null) {
                a(1, z);
            } else if (this.k == 4 || i()) {
                long j = j();
                if (this.g == 0 && j <= 60) {
                    i.a("DefaultDrmSession", "Offline license has expired or will expire soon. Remaining seconds: " + j);
                    a(2, z);
                } else if (j <= 0) {
                    c(new KeysExpiredException());
                } else {
                    this.k = 4;
                    this.i.a($$Lambda$dVpUG7eEATPLSH8Ms9SBlInfReE.INSTANCE);
                }
            }
        } else if (i != 2) {
            if (i == 3 && i()) {
                a(3, z);
            }
        } else if (this.r == null) {
            a(2, z);
        } else if (i()) {
            a(2, z);
        }
    }

    private boolean i() {
        try {
            this.e.b(this.q, this.r);
            return true;
        } catch (Exception e) {
            i.b("DefaultDrmSession", "Error trying to restore Widevine keys.", e);
            c(e);
            return false;
        }
    }

    private long j() {
        if (!com.google.android.exoplayer2.c.d.equals(this.c)) {
            return Long.MAX_VALUE;
        }
        Pair<Long, Long> a2 = g.a(this);
        return Math.min(a2.first.longValue(), a2.second.longValue());
    }

    private void a(int i, boolean z) {
        try {
            this.s = this.e.a(i == 3 ? this.r : this.q, this.a, i, this.h);
            this.n.a(1, this.s, z);
        } catch (Exception e) {
            b(e);
        }
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void b(Object obj, Object obj2) {
        if (obj == this.s && l()) {
            this.s = null;
            if (obj2 instanceof Exception) {
                b((Exception) obj2);
                return;
            }
            try {
                byte[] bArr = (byte[]) obj2;
                if (this.g == 3) {
                    this.e.a(this.r, bArr);
                    this.i.a($$Lambda$dVpUG7eEATPLSH8Ms9SBlInfReE.INSTANCE);
                    return;
                }
                byte[] a2 = this.e.a(this.q, bArr);
                if (!((this.g != 2 && (this.g != 0 || this.r == null)) || a2 == null || a2.length == 0)) {
                    this.r = a2;
                }
                this.k = 4;
                this.i.a($$Lambda$eN3TUhzTg7nTmPepf_O9vxBJ0SA.INSTANCE);
            } catch (Exception e) {
                b(e);
            }
        }
    }

    private void k() {
        if (this.k == 4) {
            this.k = 3;
            c(new KeysExpiredException());
        }
    }

    private void b(Exception exc) {
        if (exc instanceof NotProvisionedException) {
            this.f.a(this);
        } else {
            c(exc);
        }
    }

    private void c(Exception exc) {
        this.p = new DrmSession.DrmSessionException(exc);
        this.i.a(new $$Lambda$DefaultDrmSession$JMuOPwszg0z6O5ZF2iy5sdfvg(exc));
        if (this.k != 4) {
            this.k = 1;
        }
    }

    private boolean l() {
        int i = this.k;
        return i == 3 || i == 4;
    }

    private class b extends Handler {
        public b(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Pair pair = (Pair) message.obj;
            F f = pair.first;
            S s = pair.second;
            int i = message.what;
            if (i == 0) {
                DefaultDrmSession.this.a((Object) f, (Object) s);
            } else if (i == 1) {
                DefaultDrmSession.this.b(f, s);
            }
        }
    }

    /* access modifiers changed from: private */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        /* access modifiers changed from: package-private */
        public void a(int i, Object obj, boolean z) {
            obtainMessage(i, z ? 1 : 0, 0, obj).sendToTarget();
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Object e;
            Object obj = message.obj;
            try {
                int i = message.what;
                if (i == 0) {
                    e = DefaultDrmSession.this.b.a(DefaultDrmSession.this.c, (ExoMediaDrm.b) obj);
                } else if (i == 1) {
                    e = DefaultDrmSession.this.b.a(DefaultDrmSession.this.c, (ExoMediaDrm.a) obj);
                } else {
                    throw new RuntimeException();
                }
            } catch (Exception e2) {
                e = e2;
                if (a(message)) {
                    return;
                }
            }
            DefaultDrmSession.this.d.obtainMessage(message.what, Pair.create(obj, e)).sendToTarget();
        }

        private boolean a(Message message) {
            int i;
            if (!(message.arg1 == 1) || (i = message.arg2 + 1) > DefaultDrmSession.this.j) {
                return false;
            }
            Message obtain = Message.obtain(message);
            obtain.arg2 = i;
            sendMessageDelayed(obtain, a(i));
            return true;
        }

        private long a(int i) {
            return (long) Math.min((i - 1) * 1000, 5000);
        }
    }
}
