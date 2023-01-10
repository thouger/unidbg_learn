package com.google.android.exoplayer2.source.hls.playlist;

import android.net.Uri;
import com.google.android.exoplayer2.source.hls.e;
import com.google.android.exoplayer2.source.hls.playlist.c;
import com.google.android.exoplayer2.source.n;
import com.google.android.exoplayer2.upstream.o;
import java.io.IOException;

public interface HlsPlaylistTracker {

    public interface a {
        HlsPlaylistTracker createTracker(e eVar, o oVar, g gVar);
    }

    public interface b {
        boolean a(c.a aVar, long j);

        void h();
    }

    public interface c {
        void a(d dVar);
    }

    d a(c.a aVar, boolean z);

    void a();

    void a(Uri uri, n.a aVar, c cVar);

    void a(b bVar);

    boolean a(c.a aVar);

    c b();

    void b(b bVar);

    void b(c.a aVar) throws IOException;

    long c();

    void c(c.a aVar);

    void d() throws IOException;

    boolean e();

    public static final class PlaylistStuckException extends IOException {
        public final String url;

        public PlaylistStuckException(String str) {
            this.url = str;
        }
    }

    public static final class PlaylistResetException extends IOException {
        public final String url;

        public PlaylistResetException(String str) {
            this.url = str;
        }
    }
}
