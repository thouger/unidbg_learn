package com.google.android.exoplayer2.source.dash.a;

import android.util.TimeUtils;
import com.google.android.exoplayer2.util.z;
import java.util.List;

/* compiled from: SegmentBase */
public abstract class j {
    final h a;
    final long b;
    final long c;

    public j(h hVar, long j, long j2) {
        this.a = hVar;
        this.b = j;
        this.c = j2;
    }

    public h a(i iVar) {
        return this.a;
    }

    public long a() {
        return z.d(this.c, TimeUtils.NANOS_PER_MS, this.b);
    }

    /* compiled from: SegmentBase */
    public static class e extends j {
        final long d;
        final long e;

        public e(h hVar, long j, long j2, long j3, long j4) {
            super(hVar, j, j2);
            this.d = j3;
            this.e = j4;
        }

        public e() {
            this(null, 1, 0, 0, 0);
        }

        public h b() {
            long j = this.e;
            if (j <= 0) {
                return null;
            }
            return new h(null, this.d, j);
        }
    }

    /* compiled from: SegmentBase */
    public static abstract class a extends j {
        final long d;
        final long e;
        final List<d> f;

        public abstract h a(i iVar, long j);

        public abstract int b(long j);

        public a(h hVar, long j, long j2, long j3, long j4, List<d> list) {
            super(hVar, j, j2);
            this.d = j3;
            this.e = j4;
            this.f = list;
        }

        public long a(long j, long j2) {
            long b = b();
            long b2 = (long) b(j2);
            if (b2 == 0) {
                return b;
            }
            if (this.f == null) {
                long j3 = (j / ((this.e * TimeUtils.NANOS_PER_MS) / this.b)) + this.d;
                if (j3 < b) {
                    return b;
                }
                return b2 == -1 ? j3 : Math.min(j3, (b + b2) - 1);
            }
            long j4 = (b2 + b) - 1;
            long j5 = b;
            while (j5 <= j4) {
                long j6 = ((j4 - j5) / 2) + j5;
                int i = (a(j6) > j ? 1 : (a(j6) == j ? 0 : -1));
                if (i < 0) {
                    j5 = j6 + 1;
                } else if (i <= 0) {
                    return j6;
                } else {
                    j4 = j6 - 1;
                }
            }
            return j5 == b ? j5 : j4;
        }

        public final long b(long j, long j2) {
            List<d> list = this.f;
            if (list != null) {
                return (list.get((int) (j - this.d)).b * TimeUtils.NANOS_PER_MS) / this.b;
            }
            int b = b(j2);
            return (b == -1 || j != (b() + ((long) b)) - 1) ? (this.e * TimeUtils.NANOS_PER_MS) / this.b : j2 - a(j);
        }

        public final long a(long j) {
            long j2;
            List<d> list = this.f;
            if (list != null) {
                j2 = list.get((int) (j - this.d)).a - this.c;
            } else {
                j2 = (j - this.d) * this.e;
            }
            return z.d(j2, TimeUtils.NANOS_PER_MS, this.b);
        }

        public long b() {
            return this.d;
        }

        public boolean c() {
            return this.f != null;
        }
    }

    /* compiled from: SegmentBase */
    public static class b extends a {
        final List<h> g;

        @Override // com.google.android.exoplayer2.source.dash.a.j.a
        public boolean c() {
            return true;
        }

        public b(h hVar, long j, long j2, long j3, long j4, List<d> list, List<h> list2) {
            super(hVar, j, j2, j3, j4, list);
            this.g = list2;
        }

        @Override // com.google.android.exoplayer2.source.dash.a.j.a
        public h a(i iVar, long j) {
            return this.g.get((int) (j - this.d));
        }

        @Override // com.google.android.exoplayer2.source.dash.a.j.a
        public int b(long j) {
            return this.g.size();
        }
    }

    /* compiled from: SegmentBase */
    public static class c extends a {
        final l g;
        final l h;

        public c(h hVar, long j, long j2, long j3, long j4, List<d> list, l lVar, l lVar2) {
            super(hVar, j, j2, j3, j4, list);
            this.g = lVar;
            this.h = lVar2;
        }

        @Override // com.google.android.exoplayer2.source.dash.a.j
        public h a(i iVar) {
            l lVar = this.g;
            if (lVar != null) {
                return new h(lVar.a(iVar.c.a, 0, iVar.c.c, 0), 0, -1);
            }
            return super.a(iVar);
        }

        @Override // com.google.android.exoplayer2.source.dash.a.j.a
        public h a(i iVar, long j) {
            long j2;
            if (this.f != null) {
                j2 = ((d) this.f.get((int) (j - this.d))).a;
            } else {
                j2 = (j - this.d) * this.e;
            }
            return new h(this.h.a(iVar.c.a, j, iVar.c.c, j2), 0, -1);
        }

        @Override // com.google.android.exoplayer2.source.dash.a.j.a
        public int b(long j) {
            if (this.f != null) {
                return this.f.size();
            }
            if (j != -9223372036854775807L) {
                return (int) z.a(j, (this.e * TimeUtils.NANOS_PER_MS) / this.b);
            }
            return -1;
        }
    }

    /* compiled from: SegmentBase */
    public static class d {
        final long a;
        final long b;

        public d(long j, long j2) {
            this.a = j;
            this.b = j2;
        }
    }
}
