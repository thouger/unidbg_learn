package com.google.android.exoplayer2;

import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.trackselection.f;

/* compiled from: Player */
public interface r {

    /* compiled from: Player */
    public interface a {

        /* compiled from: Player */
        /* renamed from: com.google.android.exoplayer2.r$a$-CC  reason: invalid class name */
        public final /* synthetic */ class CC {
            public static void $default$a(a aVar) {
            }

            public static void $default$a(a aVar, q qVar) {
            }

            public static void $default$a(a aVar, TrackGroupArray trackGroupArray, f fVar) {
            }

            public static void $default$a(a aVar, z zVar, Object obj, int i) {
            }

            public static void $default$a(a aVar, boolean z) {
            }

            public static void $default$a_(a aVar, int i) {
            }

            public static void $default$b(a aVar, int i) {
            }

            public static void $default$onPlayerError(a aVar, ExoPlaybackException exoPlaybackException) {
            }

            public static void $default$onPlayerStateChanged(a aVar, boolean z, int i) {
            }
        }

        void a();

        void a(q qVar);

        void a(TrackGroupArray trackGroupArray, f fVar);

        void a(z zVar, Object obj, int i);

        void a(boolean z);

        void a_(int i);

        void b(int i);

        void onPlayerError(ExoPlaybackException exoPlaybackException);

        void onPlayerStateChanged(boolean z, int i);
    }

    void a(int i, long j);

    void a(boolean z);

    int h();

    long j();

    long l();

    int n();

    int o();

    long p();

    z r();
}
