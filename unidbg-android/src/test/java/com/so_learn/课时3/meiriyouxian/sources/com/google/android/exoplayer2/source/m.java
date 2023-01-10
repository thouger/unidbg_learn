package com.google.android.exoplayer2.source;

import android.os.Handler;
import com.android.internal.logging.nano.MetricsProto;
import com.google.android.exoplayer2.upstream.s;
import com.google.android.exoplayer2.z;
import java.io.IOException;

/* compiled from: MediaSource */
public interface m {

    /* compiled from: MediaSource */
    public interface b {
        void onSourceInfoRefreshed(m mVar, z zVar, Object obj);
    }

    l a(a aVar, com.google.android.exoplayer2.upstream.b bVar, long j);

    void a(Handler handler, n nVar);

    void a(l lVar);

    void a(b bVar);

    void a(b bVar, s sVar);

    void a(n nVar);

    void b() throws IOException;

    /* compiled from: MediaSource */
    public static final class a {
        public final Object a;
        public final int b;
        public final int c;
        public final long d;
        public final long e;

        public a(Object obj) {
            this(obj, -1);
        }

        public a(Object obj, long j) {
            this(obj, -1, -1, j, Long.MIN_VALUE);
        }

        public a(Object obj, long j, long j2) {
            this(obj, -1, -1, j, j2);
        }

        public a(Object obj, int i, int i2, long j) {
            this(obj, i, i2, j, Long.MIN_VALUE);
        }

        private a(Object obj, int i, int i2, long j, long j2) {
            this.a = obj;
            this.b = i;
            this.c = i2;
            this.d = j;
            this.e = j2;
        }

        public a a(Object obj) {
            return this.a.equals(obj) ? this : new a(obj, this.b, this.c, this.d, this.e);
        }

        public boolean a() {
            return this.b != -1;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            a aVar = (a) obj;
            return this.a.equals(aVar.a) && this.b == aVar.b && this.c == aVar.c && this.d == aVar.d && this.e == aVar.e;
        }

        public int hashCode() {
            return ((((((((MetricsProto.MetricsEvent.DIALOG_SUPPORT_PHONE + this.a.hashCode()) * 31) + this.b) * 31) + this.c) * 31) + ((int) this.d)) * 31) + ((int) this.e);
        }
    }
}
