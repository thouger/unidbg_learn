package com.google.common.cache;

import com.google.common.cache.Striped64;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/* access modifiers changed from: package-private */
public final class LongAdder extends Striped64 implements f, Serializable {
    private static final long serialVersionUID = 7249069246863182397L;

    /* access modifiers changed from: package-private */
    @Override // com.google.common.cache.Striped64
    public final long fn(long j, long j2) {
        return j + j2;
    }

    @Override // com.google.common.cache.f
    public void add(long j) {
        int length;
        Striped64.a aVar;
        Striped64.a[] aVarArr = this.cells;
        if (aVarArr == null) {
            long j2 = this.base;
            if (casBase(j2, j2 + j)) {
                return;
            }
        }
        int[] iArr = (int[]) threadHashCode.get();
        boolean z = true;
        if (!(iArr == null || aVarArr == null || (length = aVarArr.length) < 1 || (aVar = aVarArr[(length - 1) & iArr[0]]) == null)) {
            long j3 = aVar.a;
            z = aVar.a(j3, j3 + j);
            if (z) {
                return;
            }
        }
        retryUpdate(j, iArr, z);
    }

    @Override // com.google.common.cache.f
    public void increment() {
        add(1);
    }

    public void decrement() {
        add(-1);
    }

    @Override // com.google.common.cache.f
    public long sum() {
        long j = this.base;
        Striped64.a[] aVarArr = this.cells;
        if (aVarArr != null) {
            for (Striped64.a aVar : aVarArr) {
                if (aVar != null) {
                    j += aVar.a;
                }
            }
        }
        return j;
    }

    public void reset() {
        internalReset(0);
    }

    public long sumThenReset() {
        long j = this.base;
        Striped64.a[] aVarArr = this.cells;
        this.base = 0;
        if (aVarArr != null) {
            for (Striped64.a aVar : aVarArr) {
                if (aVar != null) {
                    j += aVar.a;
                    aVar.a = 0;
                }
            }
        }
        return j;
    }

    @Override // java.lang.Object
    public String toString() {
        return Long.toString(sum());
    }

    @Override // java.lang.Number
    public long longValue() {
        return sum();
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) sum();
    }

    @Override // java.lang.Number
    public float floatValue() {
        return (float) sum();
    }

    @Override // java.lang.Number
    public double doubleValue() {
        return (double) sum();
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        objectOutputStream.writeLong(sum());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        objectInputStream.defaultReadObject();
        this.busy = 0;
        this.cells = null;
        this.base = objectInputStream.readLong();
    }
}
