package com.google.android.exoplayer2.a;

import android.view.Surface;
import com.google.android.exoplayer2.ExoPlaybackException;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.b.d;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.q;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.m;
import com.google.android.exoplayer2.source.n;
import com.google.android.exoplayer2.trackselection.f;
import com.google.android.exoplayer2.z;
import java.io.IOException;

/* compiled from: AnalyticsListener */
public interface b {

    /* compiled from: AnalyticsListener */
    /* renamed from: com.google.android.exoplayer2.a.b$-CC  reason: invalid class name */
    public final /* synthetic */ class CC {
        public static void $default$a(b bVar, a aVar) {
        }

        public static void $default$a(b bVar, a aVar, float f) {
        }

        public static void $default$a(b bVar, a aVar, int i) {
        }

        public static void $default$a(b bVar, a aVar, int i, int i2) {
        }

        public static void $default$a(b bVar, a aVar, int i, int i2, int i3, float f) {
        }

        public static void $default$a(b bVar, a aVar, int i, long j) {
        }

        public static void $default$a(b bVar, a aVar, int i, long j, long j2) {
        }

        public static void $default$a(b bVar, a aVar, int i, Format format) {
        }

        public static void $default$a(b bVar, a aVar, int i, d dVar) {
        }

        public static void $default$a(b bVar, a aVar, int i, String str, long j) {
        }

        public static void $default$a(b bVar, a aVar, Surface surface) {
        }

        public static void $default$a(b bVar, a aVar, ExoPlaybackException exoPlaybackException) {
        }

        public static void $default$a(b bVar, a aVar, com.google.android.exoplayer2.audio.b bVar2) {
        }

        public static void $default$a(b bVar, a aVar, Metadata metadata) {
        }

        public static void $default$a(b bVar, a aVar, q qVar) {
        }

        public static void $default$a(b bVar, a aVar, TrackGroupArray trackGroupArray, f fVar) {
        }

        public static void $default$a(b bVar, a aVar, n.b bVar2, n.c cVar) {
        }

        public static void $default$a(b bVar, a aVar, n.b bVar2, n.c cVar, IOException iOException, boolean z) {
        }

        public static void $default$a(b bVar, a aVar, n.c cVar) {
        }

        public static void $default$a(b bVar, a aVar, Exception exc) {
        }

        public static void $default$a(b bVar, a aVar, boolean z) {
        }

        public static void $default$a(b bVar, a aVar, boolean z, int i) {
        }

        public static void $default$b(b bVar, a aVar) {
        }

        public static void $default$b(b bVar, a aVar, int i) {
        }

        public static void $default$b(b bVar, a aVar, int i, long j, long j2) {
        }

        public static void $default$b(b bVar, a aVar, int i, d dVar) {
        }

        public static void $default$b(b bVar, a aVar, n.b bVar2, n.c cVar) {
        }

        public static void $default$b(b bVar, a aVar, n.c cVar) {
        }

        public static void $default$c(b bVar, a aVar) {
        }

        public static void $default$c(b bVar, a aVar, int i) {
        }

        public static void $default$c(b bVar, a aVar, n.b bVar2, n.c cVar) {
        }

        public static void $default$d(b bVar, a aVar) {
        }

        public static void $default$d(b bVar, a aVar, int i) {
        }

        public static void $default$e(b bVar, a aVar) {
        }

        public static void $default$f(b bVar, a aVar) {
        }

        public static void $default$g(b bVar, a aVar) {
        }

        public static void $default$h(b bVar, a aVar) {
        }

        public static void $default$i(b bVar, a aVar) {
        }
    }

    void a(a aVar);

    void a(a aVar, float f);

    void a(a aVar, int i);

    void a(a aVar, int i, int i2);

    void a(a aVar, int i, int i2, int i3, float f);

    void a(a aVar, int i, long j);

    void a(a aVar, int i, long j, long j2);

    void a(a aVar, int i, Format format);

    void a(a aVar, int i, d dVar);

    void a(a aVar, int i, String str, long j);

    void a(a aVar, Surface surface);

    void a(a aVar, ExoPlaybackException exoPlaybackException);

    void a(a aVar, com.google.android.exoplayer2.audio.b bVar);

    void a(a aVar, Metadata metadata);

    void a(a aVar, q qVar);

    void a(a aVar, TrackGroupArray trackGroupArray, f fVar);

    void a(a aVar, n.b bVar, n.c cVar);

    void a(a aVar, n.b bVar, n.c cVar, IOException iOException, boolean z);

    void a(a aVar, n.c cVar);

    void a(a aVar, Exception exc);

    void a(a aVar, boolean z);

    void a(a aVar, boolean z, int i);

    void b(a aVar);

    void b(a aVar, int i);

    void b(a aVar, int i, long j, long j2);

    void b(a aVar, int i, d dVar);

    void b(a aVar, n.b bVar, n.c cVar);

    void b(a aVar, n.c cVar);

    void c(a aVar);

    void c(a aVar, int i);

    void c(a aVar, n.b bVar, n.c cVar);

    void d(a aVar);

    void d(a aVar, int i);

    void e(a aVar);

    void f(a aVar);

    void g(a aVar);

    void h(a aVar);

    void i(a aVar);

    /* compiled from: AnalyticsListener */
    public static final class a {
        public final long a;
        public final z b;
        public final int c;
        public final m.a d;
        public final long e;
        public final long f;
        public final long g;

        public a(long j, z zVar, int i, m.a aVar, long j2, long j3, long j4) {
            this.a = j;
            this.b = zVar;
            this.c = i;
            this.d = aVar;
            this.e = j2;
            this.f = j3;
            this.g = j4;
        }
    }
}
