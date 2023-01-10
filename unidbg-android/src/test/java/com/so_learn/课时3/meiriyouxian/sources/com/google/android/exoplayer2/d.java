package com.google.android.exoplayer2;

import android.telephony.PreciseDisconnectCause;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.f;
import com.google.android.exoplayer2.upstream.b;
import com.google.android.exoplayer2.upstream.i;
import com.google.android.exoplayer2.util.PriorityTaskManager;
import com.google.android.exoplayer2.util.a;
import com.google.android.exoplayer2.util.z;

/* compiled from: DefaultLoadControl */
public class d implements l {
    private final i a;
    private final long b;
    private final long c;
    private final long d;
    private final long e;
    private final int f;
    private final boolean g;
    private final PriorityTaskManager h;
    private final long i;
    private final boolean j;
    private int k;
    private boolean l;

    public d() {
        this(new i(true, 65536));
    }

    @Deprecated
    public d(i iVar) {
        this(iVar, 15000, 50000, PreciseDisconnectCause.EPDG_TUNNEL_ESTABLISH_FAILURE, 5000, -1, true);
    }

    @Deprecated
    public d(i iVar, int i, int i2, int i3, int i4, int i5, boolean z) {
        this(iVar, i, i2, i3, i4, i5, z, null);
    }

    @Deprecated
    public d(i iVar, int i, int i2, int i3, int i4, int i5, boolean z, PriorityTaskManager priorityTaskManager) {
        this(iVar, i, i2, i3, i4, i5, z, priorityTaskManager, 0, false);
    }

    protected d(i iVar, int i, int i2, int i3, int i4, int i5, boolean z, PriorityTaskManager priorityTaskManager, int i6, boolean z2) {
        a(i3, 0, "bufferForPlaybackMs", "0");
        a(i4, 0, "bufferForPlaybackAfterRebufferMs", "0");
        a(i, i3, "minBufferMs", "bufferForPlaybackMs");
        a(i, i4, "minBufferMs", "bufferForPlaybackAfterRebufferMs");
        a(i2, i, "maxBufferMs", "minBufferMs");
        a(i6, 0, "backBufferDurationMs", "0");
        this.a = iVar;
        this.b = c.b((long) i);
        this.c = c.b((long) i2);
        this.d = c.b((long) i3);
        this.e = c.b((long) i4);
        this.f = i5;
        this.g = z;
        this.h = priorityTaskManager;
        this.i = c.b((long) i6);
        this.j = z2;
    }

    @Override // com.google.android.exoplayer2.l
    public void a() {
        a(false);
    }

    @Override // com.google.android.exoplayer2.l
    public void a(t[] tVarArr, TrackGroupArray trackGroupArray, f fVar) {
        int i = this.f;
        if (i == -1) {
            i = a(tVarArr, fVar);
        }
        this.k = i;
        this.a.a(this.k);
    }

    @Override // com.google.android.exoplayer2.l
    public void b() {
        a(true);
    }

    @Override // com.google.android.exoplayer2.l
    public void c() {
        a(true);
    }

    @Override // com.google.android.exoplayer2.l
    public b d() {
        return this.a;
    }

    @Override // com.google.android.exoplayer2.l
    public long e() {
        return this.i;
    }

    @Override // com.google.android.exoplayer2.l
    public boolean f() {
        return this.j;
    }

    @Override // com.google.android.exoplayer2.l
    public boolean a(long j, float f) {
        boolean z;
        boolean z2 = true;
        boolean z3 = this.a.e() >= this.k;
        boolean z4 = this.l;
        long j2 = this.b;
        if (f > 1.0f) {
            j2 = Math.min(z.a(j2, f), this.c);
        }
        if (j < j2) {
            if (!this.g && z3) {
                z2 = false;
            }
            this.l = z2;
        } else if (j >= this.c || z3) {
            this.l = false;
        }
        PriorityTaskManager priorityTaskManager = this.h;
        if (!(priorityTaskManager == null || (z = this.l) == z4)) {
            if (z) {
                priorityTaskManager.a(0);
            } else {
                priorityTaskManager.b(0);
            }
        }
        return this.l;
    }

    @Override // com.google.android.exoplayer2.l
    public boolean a(long j, float f, boolean z) {
        long b = z.b(j, f);
        long j2 = z ? this.e : this.d;
        return j2 <= 0 || b >= j2 || (!this.g && this.a.e() >= this.k);
    }

    /* access modifiers changed from: protected */
    public int a(t[] tVarArr, f fVar) {
        int i = 0;
        for (int i2 = 0; i2 < tVarArr.length; i2++) {
            if (fVar.a(i2) != null) {
                i += z.i(tVarArr[i2].a());
            }
        }
        return i;
    }

    private void a(boolean z) {
        this.k = 0;
        PriorityTaskManager priorityTaskManager = this.h;
        if (priorityTaskManager != null && this.l) {
            priorityTaskManager.b(0);
        }
        this.l = false;
        if (z) {
            this.a.d();
        }
    }

    private static void a(int i, int i2, String str, String str2) {
        boolean z = i >= i2;
        a.a(z, str + " cannot be less than " + str2);
    }
}
