package com.google.common.collect;

import java.util.Arrays;

/* compiled from: ObjectCountLinkedHashMap */
class am<K> extends al<K> {
    transient long[] f;
    private transient int g;
    private transient int h;

    am() {
        this(3);
    }

    am(int i) {
        this(i, 1.0f);
    }

    am(int i, float f) {
        super(i, f);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.al
    public void a(int i, float f) {
        super.a(i, f);
        this.g = -2;
        this.h = -2;
        this.f = new long[i];
        Arrays.fill(this.f, -1L);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.al
    public int b() {
        int i = this.g;
        if (i == -2) {
            return -1;
        }
        return i;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.al
    public int b(int i) {
        int k = k(i);
        if (k == -2) {
            return -1;
        }
        return k;
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.al
    public int a(int i, int i2) {
        return i == c() ? i2 : i;
    }

    private int j(int i) {
        return (int) (this.f[i] >>> 32);
    }

    private int k(int i) {
        return (int) this.f[i];
    }

    private void c(int i, int i2) {
        long[] jArr = this.f;
        jArr[i] = (jArr[i] & -4294967296L) | (((long) i2) & 4294967295L);
    }

    private void d(int i, int i2) {
        long[] jArr = this.f;
        jArr[i] = (jArr[i] & 4294967295L) | (((long) i2) << 32);
    }

    private void e(int i, int i2) {
        if (i == -2) {
            this.g = i2;
        } else {
            c(i, i2);
        }
        if (i2 == -2) {
            this.h = i;
        } else {
            d(i2, i);
        }
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.al
    public void a(int i, K k, int i2, int i3) {
        super.a(i, k, i2, i3);
        e(this.h, i);
        e(i, -2);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.al
    public void i(int i) {
        int c = c() - 1;
        e(j(i), k(i));
        if (i < c) {
            e(j(c), i);
            e(i, k(c));
        }
        super.i(i);
    }

    /* access modifiers changed from: package-private */
    @Override // com.google.common.collect.al
    public void g(int i) {
        super.g(i);
        long[] jArr = this.f;
        int length = jArr.length;
        this.f = Arrays.copyOf(jArr, i);
        Arrays.fill(this.f, length, i, -1L);
    }

    @Override // com.google.common.collect.al
    public void d() {
        super.d();
        this.g = -2;
        this.h = -2;
    }
}
