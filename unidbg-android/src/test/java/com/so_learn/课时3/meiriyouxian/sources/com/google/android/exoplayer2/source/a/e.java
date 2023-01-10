package com.google.android.exoplayer2.source.a;

import android.util.SparseArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.f;
import com.google.android.exoplayer2.extractor.g;
import com.google.android.exoplayer2.extractor.h;
import com.google.android.exoplayer2.extractor.i;
import com.google.android.exoplayer2.extractor.o;
import com.google.android.exoplayer2.extractor.q;
import java.io.IOException;

/* compiled from: ChunkExtractorWrapper */
public final class e implements i {
    public final g a;
    private final int b;
    private final Format c;
    private final SparseArray<a> d = new SparseArray<>();
    private boolean e;
    private b f;
    private long g;
    private o h;
    private Format[] i;

    /* compiled from: ChunkExtractorWrapper */
    public interface b {
        q a(int i, int i2);
    }

    public e(g gVar, int i, Format format) {
        this.a = gVar;
        this.b = i;
        this.c = format;
    }

    public o b() {
        return this.h;
    }

    public Format[] c() {
        return this.i;
    }

    public void a(b bVar, long j, long j2) {
        this.f = bVar;
        this.g = j2;
        if (!this.e) {
            this.a.a(this);
            if (j != -9223372036854775807L) {
                this.a.a(0, j);
            }
            this.e = true;
            return;
        }
        g gVar = this.a;
        if (j == -9223372036854775807L) {
            j = 0;
        }
        gVar.a(0, j);
        for (int i = 0; i < this.d.size(); i++) {
            ((a) this.d.valueAt(i)).a(bVar, j2);
        }
    }

    @Override // com.google.android.exoplayer2.extractor.i
    public q a(int i, int i2) {
        a aVar = (a) this.d.get(i);
        if (aVar == null) {
            com.google.android.exoplayer2.util.a.b(this.i == null);
            aVar = new a(i, i2, i2 == this.b ? this.c : null);
            aVar.a(this.f, this.g);
            this.d.put(i, aVar);
        }
        return aVar;
    }

    @Override // com.google.android.exoplayer2.extractor.i
    public void a() {
        Format[] formatArr = new Format[this.d.size()];
        for (int i = 0; i < this.d.size(); i++) {
            formatArr[i] = ((a) this.d.valueAt(i)).a;
        }
        this.i = formatArr;
    }

    @Override // com.google.android.exoplayer2.extractor.i
    public void a(o oVar) {
        this.h = oVar;
    }

    /* compiled from: ChunkExtractorWrapper */
    private static final class a implements q {
        public Format a;
        private final int b;
        private final int c;
        private final Format d;
        private final f e = new f();
        private q f;
        private long g;

        public a(int i, int i2, Format format) {
            this.b = i;
            this.c = i2;
            this.d = format;
        }

        public void a(b bVar, long j) {
            if (bVar == null) {
                this.f = this.e;
                return;
            }
            this.g = j;
            this.f = bVar.a(this.b, this.c);
            Format format = this.a;
            if (format != null) {
                this.f.a(format);
            }
        }

        @Override // com.google.android.exoplayer2.extractor.q
        public void a(Format format) {
            Format format2 = this.d;
            if (format2 != null) {
                format = format.a(format2);
            }
            this.a = format;
            this.f.a(this.a);
        }

        @Override // com.google.android.exoplayer2.extractor.q
        public int a(h hVar, int i, boolean z) throws IOException, InterruptedException {
            return this.f.a(hVar, i, z);
        }

        @Override // com.google.android.exoplayer2.extractor.q
        public void a(com.google.android.exoplayer2.util.o oVar, int i) {
            this.f.a(oVar, i);
        }

        @Override // com.google.android.exoplayer2.extractor.q
        public void a(long j, int i, int i2, int i3, q.a aVar) {
            long j2 = this.g;
            if (j2 != -9223372036854775807L && j >= j2) {
                this.f = this.e;
            }
            this.f.a(j, i, i2, i3, aVar);
        }
    }
}
