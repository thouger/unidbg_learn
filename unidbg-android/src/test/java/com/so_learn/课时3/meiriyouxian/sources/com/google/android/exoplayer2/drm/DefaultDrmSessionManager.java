package com.google.android.exoplayer2.drm;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.exoplayer2.c;
import com.google.android.exoplayer2.drm.DefaultDrmSession;
import com.google.android.exoplayer2.drm.DrmInitData;
import com.google.android.exoplayer2.drm.DrmSession;
import com.google.android.exoplayer2.drm.ExoMediaDrm;
import com.google.android.exoplayer2.drm.d;
import com.google.android.exoplayer2.util.f;
import com.google.android.exoplayer2.util.i;
import com.google.android.exoplayer2.util.z;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class DefaultDrmSessionManager<T extends d> implements DefaultDrmSession.c<T>, b<T> {
    volatile DefaultDrmSessionManager<T>.a a;
    private final UUID b;
    private final ExoMediaDrm<T> c;
    private final f d;
    private final HashMap<String, String> e;
    private final f<a> f;
    private final boolean g;
    private final int h;
    private final List<DefaultDrmSession<T>> i;
    private final List<DefaultDrmSession<T>> j;
    private Looper k;
    private int l;
    private byte[] m;

    public static final class MissingSchemeDataException extends Exception {
        private MissingSchemeDataException(UUID uuid) {
            super("Media does not support uuid: " + uuid);
        }
    }

    public final void a(Handler handler, a aVar) {
        this.f.a(handler, aVar);
    }

    @Override // com.google.android.exoplayer2.drm.b
    public boolean a(DrmInitData drmInitData) {
        if (this.m != null) {
            return true;
        }
        if (a(drmInitData, this.b, true).isEmpty()) {
            if (drmInitData.b != 1 || !drmInitData.a(0).a(c.b)) {
                return false;
            }
            i.c("DefaultDrmSessionMgr", "DrmInitData only contains common PSSH SchemeData. Assuming support for: " + this.b);
        }
        String str = drmInitData.a;
        if (str == null || "cenc".equals(str)) {
            return true;
        }
        if (("cbc1".equals(str) || "cbcs".equals(str) || "cens".equals(str)) && z.a < 25) {
            return false;
        }
        return true;
    }

    @Override // com.google.android.exoplayer2.drm.b
    public DrmSession<T> a(Looper looper, DrmInitData drmInitData) {
        List<DrmInitData.SchemeData> list;
        DefaultDrmSession<T> defaultDrmSession;
        Looper looper2 = this.k;
        com.google.android.exoplayer2.util.a.b(looper2 == null || looper2 == looper);
        if (this.i.isEmpty()) {
            this.k = looper;
            if (this.a == null) {
                this.a = new a(looper);
            }
        }
        DefaultDrmSession<T> defaultDrmSession2 = null;
        if (this.m == null) {
            List<DrmInitData.SchemeData> a2 = a(drmInitData, this.b, false);
            if (a2.isEmpty()) {
                MissingSchemeDataException missingSchemeDataException = new MissingSchemeDataException(this.b);
                this.f.a(new $$Lambda$DefaultDrmSessionManager$dlGaPQEkG1T1KVYIhiWlBuOEMM(missingSchemeDataException));
                return new c(new DrmSession.DrmSessionException(missingSchemeDataException));
            }
            list = a2;
        } else {
            list = null;
        }
        if (this.g) {
            Iterator<DefaultDrmSession<T>> it2 = this.i.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                DefaultDrmSession<T> next = it2.next();
                if (z.a(next.a, list)) {
                    defaultDrmSession2 = next;
                    break;
                }
            }
        } else if (!this.i.isEmpty()) {
            defaultDrmSession2 = this.i.get(0);
        }
        if (defaultDrmSession2 == null) {
            defaultDrmSession = new DefaultDrmSession<>(this.b, this.c, this, list, this.l, this.m, this.e, this.d, looper, this.f, this.h);
            this.i.add(defaultDrmSession);
        } else {
            defaultDrmSession = defaultDrmSession2;
        }
        defaultDrmSession.a();
        return defaultDrmSession;
    }

    @Override // com.google.android.exoplayer2.drm.b
    public void a(DrmSession<T> drmSession) {
        if (!(drmSession instanceof c)) {
            DefaultDrmSession<T> defaultDrmSession = (DefaultDrmSession) drmSession;
            if (defaultDrmSession.b()) {
                this.i.remove(defaultDrmSession);
                if (this.j.size() > 1 && this.j.get(0) == defaultDrmSession) {
                    this.j.get(1).c();
                }
                this.j.remove(defaultDrmSession);
            }
        }
    }

    @Override // com.google.android.exoplayer2.drm.DefaultDrmSession.c
    public void a(DefaultDrmSession<T> defaultDrmSession) {
        this.j.add(defaultDrmSession);
        if (this.j.size() == 1) {
            defaultDrmSession.c();
        }
    }

    @Override // com.google.android.exoplayer2.drm.DefaultDrmSession.c
    public void a() {
        for (DefaultDrmSession<T> defaultDrmSession : this.j) {
            defaultDrmSession.d();
        }
        this.j.clear();
    }

    @Override // com.google.android.exoplayer2.drm.DefaultDrmSession.c
    public void a(Exception exc) {
        for (DefaultDrmSession<T> defaultDrmSession : this.j) {
            defaultDrmSession.a(exc);
        }
        this.j.clear();
    }

    private static List<DrmInitData.SchemeData> a(DrmInitData drmInitData, UUID uuid, boolean z) {
        ArrayList arrayList = new ArrayList(drmInitData.b);
        for (int i = 0; i < drmInitData.b; i++) {
            DrmInitData.SchemeData a2 = drmInitData.a(i);
            if ((a2.a(uuid) || (c.c.equals(uuid) && a2.a(c.b))) && (a2.c != null || z)) {
                arrayList.add(a2);
            }
        }
        return arrayList;
    }

    private class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            byte[] bArr = (byte[]) message.obj;
            for (DefaultDrmSession defaultDrmSession : DefaultDrmSessionManager.this.i) {
                if (defaultDrmSession.a(bArr)) {
                    defaultDrmSession.onMediaDrmEvent(message.what);
                    return;
                }
            }
        }
    }

    private class MediaDrmEventListener implements ExoMediaDrm.OnEventListener<T> {
        final /* synthetic */ DefaultDrmSessionManager a;

        @Override // com.google.android.exoplayer2.drm.ExoMediaDrm.OnEventListener
        public void onEvent(ExoMediaDrm<? extends T> exoMediaDrm, byte[] bArr, int i, int i2, byte[] bArr2) {
            if (this.a.l == 0) {
                this.a.a.obtainMessage(i, bArr).sendToTarget();
            }
        }
    }
}
