package com.umeng.commonsdk.statistics.noise;

import android.content.Context;
import com.umeng.analytics.pro.ai;
import com.umeng.commonsdk.statistics.a;
import com.umeng.commonsdk.statistics.common.DataHelper;
import com.umeng.commonsdk.statistics.idtracking.Envelope;
import com.umeng.commonsdk.statistics.idtracking.ImprintHandler;
import com.umeng.commonsdk.statistics.internal.StatTracer;
import com.umeng.commonsdk.statistics.internal.d;

public class ImLatent implements d {
    private static ImLatent instanse;
    private final int LATENT_MAX = 1800000;
    private final int LATENT_WINDOW = 10;
    private final long _360HOURS_IN_MS = 1296000000;
    private final long _36HOURS_IN_MS = 129600000;
    private final int _DEFAULT_HOURS = 360;
    private final int _DEFAULT_MAX_LATENT = 1800;
    private final int _DEFAULT_MIN_HOURS = 36;
    private final int _DEFAULT_MIN_LATENT = 1;
    private final long _ONE_HOURS_IN_MS = 3600000;
    private Context context;
    private long latentHour = 1296000000;
    private int latentWindow = 10;
    private long mDelay = 0;
    private long mElapsed = 0;
    private boolean mLatentActivite = false;
    private Object mLatentLock = new Object();
    private StatTracer statTracer;
    private com.umeng.commonsdk.statistics.common.d storeHelper;

    public static synchronized ImLatent getService(Context context, StatTracer statTracer) {
        ImLatent imLatent;
        synchronized (ImLatent.class) {
            if (instanse == null) {
                instanse = new ImLatent(context, statTracer);
                instanse.onImprintChanged(ImprintHandler.getImprintService(context).c());
            }
            imLatent = instanse;
        }
        return imLatent;
    }

    private ImLatent(Context context, StatTracer statTracer) {
        this.context = context;
        this.storeHelper = com.umeng.commonsdk.statistics.common.d.a(context);
        this.statTracer = statTracer;
    }

    public boolean shouldStartLatency() {
        if (this.storeHelper.c() || this.statTracer.isFirstRequest()) {
            return false;
        }
        synchronized (this.mLatentLock) {
            if (this.mLatentActivite) {
                return false;
            }
        }
        long currentTimeMillis = System.currentTimeMillis() - this.statTracer.getLastReqTime();
        if (currentTimeMillis > this.latentHour) {
            String signature = Envelope.getSignature(this.context);
            synchronized (this.mLatentLock) {
                this.mDelay = (long) DataHelper.random(this.latentWindow, signature);
                this.mElapsed = currentTimeMillis;
                this.mLatentActivite = true;
            }
            return true;
        } else if (currentTimeMillis <= 129600000) {
            return false;
        } else {
            synchronized (this.mLatentLock) {
                this.mDelay = 0;
                this.mElapsed = currentTimeMillis;
                this.mLatentActivite = true;
            }
            return true;
        }
    }

    public boolean isLatentActivite() {
        boolean z;
        synchronized (this.mLatentLock) {
            z = this.mLatentActivite;
        }
        return z;
    }

    public void latentDeactivite() {
        synchronized (this.mLatentLock) {
            this.mLatentActivite = false;
        }
    }

    public long getDelayTime() {
        long j;
        synchronized (this.mLatentLock) {
            j = this.mDelay;
        }
        return j;
    }

    public long getElapsedTime() {
        return this.mElapsed;
    }

    @Override // com.umeng.commonsdk.statistics.internal.d
    public void onImprintChanged(ImprintHandler.a aVar) {
        int i = 360;
        int intValue = Integer.valueOf(aVar.a("latent_hours", String.valueOf(360))).intValue();
        if (intValue > 36) {
            i = intValue;
        }
        this.latentHour = ((long) i) * 3600000;
        int intValue2 = Integer.valueOf(aVar.a(ai.aP, "0")).intValue();
        if (intValue2 < 1 || intValue2 > 1800) {
            intValue2 = 0;
        }
        if (intValue2 != 0) {
            this.latentWindow = intValue2;
        } else if (a.c <= 0 || a.c > 1800000) {
            this.latentWindow = 10;
        } else {
            this.latentWindow = a.c;
        }
    }
}
