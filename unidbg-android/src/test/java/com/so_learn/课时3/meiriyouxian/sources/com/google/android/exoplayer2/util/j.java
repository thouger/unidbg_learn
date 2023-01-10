package com.google.android.exoplayer2.util;

import java.util.Arrays;

/* compiled from: LongArray */
public final class j {
    private int a;
    private long[] b;

    public j() {
        this(32);
    }

    public j(int i) {
        this.b = new long[i];
    }

    public void a(long j) {
        int i = this.a;
        long[] jArr = this.b;
        if (i == jArr.length) {
            this.b = Arrays.copyOf(jArr, i * 2);
        }
        long[] jArr2 = this.b;
        int i2 = this.a;
        this.a = i2 + 1;
        jArr2[i2] = j;
    }

    public long a(int i) {
        if (i >= 0 && i < this.a) {
            return this.b[i];
        }
        throw new IndexOutOfBoundsException("Invalid index " + i + ", size is " + this.a);
    }

    public int a() {
        return this.a;
    }

    public long[] b() {
        return Arrays.copyOf(this.b, this.a);
    }
}
