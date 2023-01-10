package com.google.android.exoplayer2.offline;

/* compiled from: StreamKey */
public final class c implements Comparable<c> {
    public final int a;
    public final int b;
    public final int c;

    public c(int i, int i2, int i3) {
        this.a = i;
        this.b = i2;
        this.c = i3;
    }

    @Override // java.lang.Object
    public String toString() {
        return this.a + "." + this.b + "." + this.c;
    }

    @Override // java.lang.Object
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        c cVar = (c) obj;
        return this.a == cVar.a && this.b == cVar.b && this.c == cVar.c;
    }

    @Override // java.lang.Object
    public int hashCode() {
        return (((this.a * 31) + this.b) * 31) + this.c;
    }

    /* renamed from: a */
    public int compareTo(c cVar) {
        int i = this.a - cVar.a;
        if (i != 0) {
            return i;
        }
        int i2 = this.b - cVar.b;
        return i2 == 0 ? this.c - cVar.c : i2;
    }
}
