package com.google.android.exoplayer2.text;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.b;
import com.google.android.exoplayer2.b.e;
import com.google.android.exoplayer2.k;
import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.util.l;
import com.google.android.exoplayer2.util.z;
import java.util.Collections;
import java.util.List;

/* compiled from: TextRenderer */
public final class j extends b implements Handler.Callback {
    private final Handler a;
    private final i b;
    private final f c;
    private final k d;
    private boolean e;
    private boolean f;
    private int g;
    private Format h;
    private e i;
    private g j;
    private h k;
    private h l;
    private int m;

    @Override // com.google.android.exoplayer2.t
    public boolean u() {
        return true;
    }

    public j(i iVar, Looper looper) {
        this(iVar, looper, f.a);
    }

    public j(i iVar, Looper looper, f fVar) {
        super(3);
        Handler handler;
        this.b = (i) a.a(iVar);
        if (looper == null) {
            handler = null;
        } else {
            handler = z.a(looper, (Handler.Callback) this);
        }
        this.a = handler;
        this.c = fVar;
        this.d = new k();
    }

    @Override // com.google.android.exoplayer2.u
    public int a(Format format) {
        return this.c.a(format) ? a(null, format.j) ? 4 : 2 : l.c(format.g) ? 1 : 0;
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.b
    public void a(Format[] formatArr, long j) throws ExoPlaybackException {
        this.h = formatArr[0];
        if (this.i != null) {
            this.g = 1;
        } else {
            this.i = this.c.b(this.h);
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.b
    public void a(long j, boolean z) {
        A();
        this.e = false;
        this.f = false;
        if (this.g != 0) {
            y();
            return;
        }
        w();
        this.i.c();
    }

    @Override // com.google.android.exoplayer2.t
    public void a(long j, long j2) throws ExoPlaybackException {
        boolean z;
        if (!this.f) {
            if (this.l == null) {
                this.i.a(j);
                try {
                    this.l = this.i.b();
                } catch (SubtitleDecoderException e) {
                    throw ExoPlaybackException.createForRenderer(e, s());
                }
            }
            if (ac_() == 2) {
                if (this.k != null) {
                    long z2 = z();
                    z = false;
                    while (z2 <= j) {
                        this.m++;
                        z2 = z();
                        z = true;
                    }
                } else {
                    z = false;
                }
                h hVar = this.l;
                if (hVar != null) {
                    if (hVar.c()) {
                        if (!z && z() == Long.MAX_VALUE) {
                            if (this.g == 2) {
                                y();
                            } else {
                                w();
                                this.f = true;
                            }
                        }
                    } else if (this.l.a <= j) {
                        h hVar2 = this.k;
                        if (hVar2 != null) {
                            hVar2.e();
                        }
                        this.k = this.l;
                        this.l = null;
                        this.m = this.k.a(j);
                        z = true;
                    }
                }
                if (z) {
                    a(this.k.b(j));
                }
                if (this.g != 2) {
                    while (!this.e) {
                        try {
                            if (this.j == null) {
                                this.j = this.i.a();
                                if (this.j == null) {
                                    return;
                                }
                            }
                            if (this.g == 1) {
                                this.j.b_(4);
                                this.i.a((e) this.j);
                                this.j = null;
                                this.g = 2;
                                return;
                            }
                            int a = a(this.d, (e) this.j, false);
                            if (a == -4) {
                                if (this.j.c()) {
                                    this.e = true;
                                } else {
                                    this.j.d = this.d.a.k;
                                    this.j.h();
                                }
                                this.i.a((e) this.j);
                                this.j = null;
                            } else if (a == -3) {
                                return;
                            }
                        } catch (SubtitleDecoderException e2) {
                            throw ExoPlaybackException.createForRenderer(e2, s());
                        }
                    }
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.android.exoplayer2.b
    public void p() {
        this.h = null;
        A();
        x();
    }

    @Override // com.google.android.exoplayer2.t
    public boolean v() {
        return this.f;
    }

    private void w() {
        this.j = null;
        this.m = -1;
        h hVar = this.k;
        if (hVar != null) {
            hVar.e();
            this.k = null;
        }
        h hVar2 = this.l;
        if (hVar2 != null) {
            hVar2.e();
            this.l = null;
        }
    }

    private void x() {
        w();
        this.i.d();
        this.i = null;
        this.g = 0;
    }

    private void y() {
        x();
        this.i = this.c.b(this.h);
    }

    private long z() {
        int i = this.m;
        if (i == -1 || i >= this.k.b()) {
            return Long.MAX_VALUE;
        }
        return this.k.a(this.m);
    }

    private void a(List<a> list) {
        Handler handler = this.a;
        if (handler != null) {
            handler.obtainMessage(0, list).sendToTarget();
        } else {
            b(list);
        }
    }

    private void A() {
        a(Collections.emptyList());
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (message.what == 0) {
            b((List) message.obj);
            return true;
        }
        throw new IllegalStateException();
    }

    private void b(List<a> list) {
        this.b.a(list);
    }
}
