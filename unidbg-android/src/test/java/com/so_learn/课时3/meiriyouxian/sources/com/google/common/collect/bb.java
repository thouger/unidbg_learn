package com.google.common.collect;

import com.google.common.base.m;
import com.google.common.math.c;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/* compiled from: TopKSelector */
/* access modifiers changed from: package-private */
public final class bb<T> {
    private final int a;
    private final Comparator<? super T> b;
    private final T[] c;
    private int d;
    private T e;

    public static <T> bb<T> a(int i, Comparator<? super T> comparator) {
        return new bb<>(comparator, i);
    }

    private bb(Comparator<? super T> comparator, int i) {
        this.b = (Comparator) m.a(comparator, "comparator");
        this.a = i;
        m.a(i >= 0, "k must be nonnegative, was %s", i);
        this.c = (T[]) new Object[(i * 2)];
        this.d = 0;
        this.e = null;
    }

    public void a(T t) {
        int i = this.a;
        if (i != 0) {
            int i2 = this.d;
            if (i2 == 0) {
                this.c[0] = t;
                this.e = t;
                this.d = 1;
            } else if (i2 < i) {
                T[] tArr = this.c;
                this.d = i2 + 1;
                tArr[i2] = t;
                if (this.b.compare(t, this.e) > 0) {
                    this.e = t;
                }
            } else if (this.b.compare(t, this.e) < 0) {
                T[] tArr2 = this.c;
                int i3 = this.d;
                this.d = i3 + 1;
                tArr2[i3] = t;
                if (this.d == this.a * 2) {
                    b();
                }
            }
        }
    }

    private void b() {
        int i = (this.a * 2) - 1;
        int a = c.a(i + 0, RoundingMode.CEILING) * 3;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            if (i2 < i) {
                int a2 = a(i2, i, ((i2 + i) + 1) >>> 1);
                int i5 = this.a;
                if (a2 <= i5) {
                    if (a2 >= i5) {
                        break;
                    }
                    i2 = Math.max(a2, i2 + 1);
                    i4 = a2;
                } else {
                    i = a2 - 1;
                }
                i3++;
                if (i3 >= a) {
                    Arrays.sort(this.c, i2, i, this.b);
                    break;
                }
            } else {
                break;
            }
        }
        this.d = this.a;
        this.e = this.c[i4];
        while (true) {
            i4++;
            if (i4 >= this.a) {
                return;
            }
            if (this.b.compare(this.c[i4], this.e) > 0) {
                this.e = this.c[i4];
            }
        }
    }

    private int a(int i, int i2, int i3) {
        T[] tArr = this.c;
        T t = tArr[i3];
        tArr[i3] = tArr[i2];
        int i4 = i;
        while (i < i2) {
            if (this.b.compare(this.c[i], t) < 0) {
                a(i4, i);
                i4++;
            }
            i++;
        }
        T[] tArr2 = this.c;
        tArr2[i2] = tArr2[i4];
        tArr2[i4] = t;
        return i4;
    }

    private void a(int i, int i2) {
        T[] tArr = this.c;
        T t = tArr[i];
        tArr[i] = tArr[i2];
        tArr[i2] = t;
    }

    public void a(Iterator<? extends T> it2) {
        while (it2.hasNext()) {
            a((bb<T>) it2.next());
        }
    }

    public List<T> a() {
        Arrays.sort(this.c, 0, this.d, this.b);
        int i = this.d;
        int i2 = this.a;
        if (i > i2) {
            T[] tArr = this.c;
            Arrays.fill(tArr, i2, tArr.length, (Object) null);
            int i3 = this.a;
            this.d = i3;
            this.e = this.c[i3 - 1];
        }
        return Collections.unmodifiableList(Arrays.asList(Arrays.copyOf(this.c, this.d)));
    }
}
