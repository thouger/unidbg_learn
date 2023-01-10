package com.google.common.math;

import com.google.common.base.i;
import com.google.common.base.j;
import com.google.common.base.m;
import com.google.common.primitives.Doubles;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Iterator;

public final class Stats implements Serializable {
    static final int BYTES = 40;
    private static final long serialVersionUID = 0;
    private final long count;
    private final double max;
    private final double mean;
    private final double min;
    private final double sumOfSquaresOfDeltas;

    Stats(long j, double d, double d2, double d3, double d4) {
        this.count = j;
        this.mean = d;
        this.sumOfSquaresOfDeltas = d2;
        this.min = d3;
        this.max = d4;
    }

    public static Stats of(Iterable<? extends Number> iterable) {
        f fVar = new f();
        fVar.a(iterable);
        return fVar.a();
    }

    public static Stats of(Iterator<? extends Number> it2) {
        f fVar = new f();
        fVar.a(it2);
        return fVar.a();
    }

    public static Stats of(double... dArr) {
        f fVar = new f();
        fVar.a(dArr);
        return fVar.a();
    }

    public static Stats of(int... iArr) {
        f fVar = new f();
        fVar.a(iArr);
        return fVar.a();
    }

    public static Stats of(long... jArr) {
        f fVar = new f();
        fVar.a(jArr);
        return fVar.a();
    }

    public long count() {
        return this.count;
    }

    public double mean() {
        m.b(this.count != 0);
        return this.mean;
    }

    public double sum() {
        return this.mean * ((double) this.count);
    }

    public double populationVariance() {
        m.b(this.count > 0);
        if (Double.isNaN(this.sumOfSquaresOfDeltas)) {
            return Double.NaN;
        }
        if (this.count == 1) {
            return 0.0d;
        }
        return b.c(this.sumOfSquaresOfDeltas) / ((double) count());
    }

    public double populationStandardDeviation() {
        return Math.sqrt(populationVariance());
    }

    public double sampleVariance() {
        m.b(this.count > 1);
        if (Double.isNaN(this.sumOfSquaresOfDeltas)) {
            return Double.NaN;
        }
        return b.c(this.sumOfSquaresOfDeltas) / ((double) (this.count - 1));
    }

    public double sampleStandardDeviation() {
        return Math.sqrt(sampleVariance());
    }

    public double min() {
        m.b(this.count != 0);
        return this.min;
    }

    public double max() {
        m.b(this.count != 0);
        return this.max;
    }

    @Override // java.lang.Object
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Stats stats = (Stats) obj;
        if (this.count == stats.count && Double.doubleToLongBits(this.mean) == Double.doubleToLongBits(stats.mean) && Double.doubleToLongBits(this.sumOfSquaresOfDeltas) == Double.doubleToLongBits(stats.sumOfSquaresOfDeltas) && Double.doubleToLongBits(this.min) == Double.doubleToLongBits(stats.min) && Double.doubleToLongBits(this.max) == Double.doubleToLongBits(stats.max)) {
            return true;
        }
        return false;
    }

    @Override // java.lang.Object
    public int hashCode() {
        return j.a(Long.valueOf(this.count), Double.valueOf(this.mean), Double.valueOf(this.sumOfSquaresOfDeltas), Double.valueOf(this.min), Double.valueOf(this.max));
    }

    @Override // java.lang.Object
    public String toString() {
        if (count() > 0) {
            return i.a(this).a("count", this.count).a("mean", this.mean).a("populationStandardDeviation", populationStandardDeviation()).a("min", this.min).a("max", this.max).toString();
        }
        return i.a(this).a("count", this.count).toString();
    }

    /* access modifiers changed from: package-private */
    public double sumOfSquaresOfDeltas() {
        return this.sumOfSquaresOfDeltas;
    }

    public static double meanOf(Iterable<? extends Number> iterable) {
        return meanOf(iterable.iterator());
    }

    public static double meanOf(Iterator<? extends Number> it2) {
        m.a(it2.hasNext());
        double doubleValue = ((Number) it2.next()).doubleValue();
        long j = 1;
        while (it2.hasNext()) {
            double doubleValue2 = ((Number) it2.next()).doubleValue();
            j++;
            if (!Doubles.b(doubleValue2) || !Doubles.b(doubleValue)) {
                doubleValue = f.a(doubleValue, doubleValue2);
            } else {
                doubleValue += (doubleValue2 - doubleValue) / ((double) j);
            }
        }
        return doubleValue;
    }

    public static double meanOf(double... dArr) {
        m.a(dArr.length > 0);
        double d = dArr[0];
        for (int i = 1; i < dArr.length; i++) {
            double d2 = dArr[i];
            if (!Doubles.b(d2) || !Doubles.b(d)) {
                d = f.a(d, d2);
            } else {
                d += (d2 - d) / ((double) (i + 1));
            }
        }
        return d;
    }

    public static double meanOf(int... iArr) {
        m.a(iArr.length > 0);
        double d = (double) iArr[0];
        for (int i = 1; i < iArr.length; i++) {
            double d2 = (double) iArr[i];
            if (!Doubles.b(d2) || !Doubles.b(d)) {
                d = f.a(d, d2);
            } else {
                d += (d2 - d) / ((double) (i + 1));
            }
        }
        return d;
    }

    public static double meanOf(long... jArr) {
        m.a(jArr.length > 0);
        double d = (double) jArr[0];
        for (int i = 1; i < jArr.length; i++) {
            double d2 = (double) jArr[i];
            if (!Doubles.b(d2) || !Doubles.b(d)) {
                d = f.a(d, d2);
            } else {
                d += (d2 - d) / ((double) (i + 1));
            }
        }
        return d;
    }

    public byte[] toByteArray() {
        ByteBuffer order = ByteBuffer.allocate(40).order(ByteOrder.LITTLE_ENDIAN);
        writeTo(order);
        return order.array();
    }

    /* access modifiers changed from: package-private */
    public void writeTo(ByteBuffer byteBuffer) {
        m.a(byteBuffer);
        m.a(byteBuffer.remaining() >= 40, "Expected at least Stats.BYTES = %s remaining , got %s", 40, byteBuffer.remaining());
        byteBuffer.putLong(this.count).putDouble(this.mean).putDouble(this.sumOfSquaresOfDeltas).putDouble(this.min).putDouble(this.max);
    }

    public static Stats fromByteArray(byte[] bArr) {
        m.a(bArr);
        m.a(bArr.length == 40, "Expected Stats.BYTES = %s remaining , got %s", 40, bArr.length);
        return readFrom(ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN));
    }

    static Stats readFrom(ByteBuffer byteBuffer) {
        m.a(byteBuffer);
        m.a(byteBuffer.remaining() >= 40, "Expected at least Stats.BYTES = %s remaining , got %s", 40, byteBuffer.remaining());
        return new Stats(byteBuffer.getLong(), byteBuffer.getDouble(), byteBuffer.getDouble(), byteBuffer.getDouble(), byteBuffer.getDouble());
    }
}
