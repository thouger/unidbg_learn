package com.google.android.exoplayer2.b;

import com.umeng.message.proguard.l;
import java.nio.ByteBuffer;

/* compiled from: DecoderInputBuffer */
public class e extends a {
    public final b a = new b();
    public ByteBuffer b;
    public long c;
    private final int d;

    public static e e() {
        return new e(0);
    }

    public e(int i) {
        this.d = i;
    }

    public void e(int i) {
        ByteBuffer byteBuffer = this.b;
        if (byteBuffer == null) {
            this.b = f(i);
            return;
        }
        int capacity = byteBuffer.capacity();
        int position = this.b.position();
        int i2 = i + position;
        if (capacity < i2) {
            ByteBuffer f = f(i2);
            if (position > 0) {
                this.b.position(0);
                this.b.limit(position);
                f.put(this.b);
            }
            this.b = f;
        }
    }

    public final boolean f() {
        return this.b == null && this.d == 0;
    }

    public final boolean g() {
        return d(1073741824);
    }

    public final void h() {
        this.b.flip();
    }

    @Override // com.google.android.exoplayer2.b.a
    public void a() {
        super.a();
        ByteBuffer byteBuffer = this.b;
        if (byteBuffer != null) {
            byteBuffer.clear();
        }
    }

    private ByteBuffer f(int i) {
        int i2 = this.d;
        if (i2 == 1) {
            return ByteBuffer.allocate(i);
        }
        if (i2 == 2) {
            return ByteBuffer.allocateDirect(i);
        }
        ByteBuffer byteBuffer = this.b;
        int capacity = byteBuffer == null ? 0 : byteBuffer.capacity();
        throw new IllegalStateException("Buffer too small (" + capacity + " < " + i + l.t);
    }
}
