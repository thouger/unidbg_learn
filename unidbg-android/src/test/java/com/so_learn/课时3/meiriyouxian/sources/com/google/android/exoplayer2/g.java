package com.google.android.exoplayer2;

import android.content.Context;
import android.os.Looper;
import com.google.android.exoplayer2.a.a;
import com.google.android.exoplayer2.drm.b;
import com.google.android.exoplayer2.drm.e;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.c;
import com.google.android.exoplayer2.util.z;

/* compiled from: ExoPlayerFactory */
public final class g {
    private static c a;

    public static y a(Context context, com.google.android.exoplayer2.trackselection.g gVar) {
        return a(context, new DefaultRenderersFactory(context), gVar);
    }

    public static y a(Context context, w wVar, com.google.android.exoplayer2.trackselection.g gVar) {
        return a(context, wVar, gVar, new d());
    }

    public static y a(Context context, w wVar, com.google.android.exoplayer2.trackselection.g gVar, l lVar) {
        return a(context, wVar, gVar, lVar, null, z.a());
    }

    public static y a(Context context, w wVar, com.google.android.exoplayer2.trackselection.g gVar, l lVar, b<e> bVar, Looper looper) {
        return a(context, wVar, gVar, lVar, bVar, new a.C0078a(), looper);
    }

    public static y a(Context context, w wVar, com.google.android.exoplayer2.trackselection.g gVar, l lVar, b<e> bVar, a.C0078a aVar, Looper looper) {
        return a(context, wVar, gVar, lVar, bVar, a(), aVar, looper);
    }

    public static y a(Context context, w wVar, com.google.android.exoplayer2.trackselection.g gVar, l lVar, b<e> bVar, c cVar, a.C0078a aVar, Looper looper) {
        return new y(context, wVar, gVar, lVar, bVar, cVar, aVar, looper);
    }

    private static synchronized c a() {
        c cVar;
        synchronized (g.class) {
            if (a == null) {
                a = new DefaultBandwidthMeter.Builder().a();
            }
            cVar = a;
        }
        return cVar;
    }
}
