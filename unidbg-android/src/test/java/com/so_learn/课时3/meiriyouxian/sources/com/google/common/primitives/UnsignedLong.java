package com.google.common.primitives;

import com.google.common.base.m;
import java.io.Serializable;
import java.math.BigInteger;

public final class UnsignedLong extends Number implements Serializable, Comparable<UnsignedLong> {
    public static final UnsignedLong MAX_VALUE = new UnsignedLong(-1);
    public static final UnsignedLong ONE = new UnsignedLong(1);
    private static final long UNSIGNED_MASK = Long.MAX_VALUE;
    public static final UnsignedLong ZERO = new UnsignedLong(0);
    private final long value;

    private UnsignedLong(long j) {
        this.value = j;
    }

    public static UnsignedLong fromLongBits(long j) {
        return new UnsignedLong(j);
    }

    public static UnsignedLong valueOf(long j) {
        m.a(j >= 0, "value (%s) is outside the range for an unsigned long value", j);
        return fromLongBits(j);
    }

    public static UnsignedLong valueOf(BigInteger bigInteger) {
        m.a(bigInteger);
        m.a(bigInteger.signum() >= 0 && bigInteger.bitLength() <= 64, "value (%s) is outside the range for an unsigned long value", bigInteger);
        return fromLongBits(bigInteger.longValue());
    }

    public static UnsignedLong valueOf(String str) {
        return valueOf(str, 10);
    }

    public static UnsignedLong valueOf(String str, int i) {
        return fromLongBits(UnsignedLongs.a(str, i));
    }

    public UnsignedLong plus(UnsignedLong unsignedLong) {
        return fromLongBits(this.value + ((UnsignedLong) m.a(unsignedLong)).value);
    }

    public UnsignedLong minus(UnsignedLong unsignedLong) {
        return fromLongBits(this.value - ((UnsignedLong) m.a(unsignedLong)).value);
    }

    public UnsignedLong times(UnsignedLong unsignedLong) {
        return fromLongBits(this.value * ((UnsignedLong) m.a(unsignedLong)).value);
    }

    public UnsignedLong dividedBy(UnsignedLong unsignedLong) {
        return fromLongBits(UnsignedLongs.b(this.value, ((UnsignedLong) m.a(unsignedLong)).value));
    }

    public UnsignedLong mod(UnsignedLong unsignedLong) {
        return fromLongBits(UnsignedLongs.c(this.value, ((UnsignedLong) m.a(unsignedLong)).value));
    }

    @Override // java.lang.Number
    public int intValue() {
        return (int) this.value;
    }

    @Override // java.lang.Number
    public long longValue() {
        return this.value;
    }

    @Override // java.lang.Number
    public float floatValue() {
        long j = this.value;
        float f = (float) (Long.MAX_VALUE & j);
        return j < 0 ? f + 9.223372E18f : f;
    }

    @Override // java.lang.Number
    public double doubleValue() {
        long j = this.value;
        double d = (double) (Long.MAX_VALUE & j);
        return j < 0 ? d + 9.223372036854776E18d : d;
    }

    public BigInteger bigIntegerValue() {
        BigInteger valueOf = BigInteger.valueOf(this.value & Long.MAX_VALUE);
        return this.value < 0 ? valueOf.setBit(63) : valueOf;
    }

    public int compareTo(UnsignedLong unsignedLong) {
        m.a(unsignedLong);
        return UnsignedLongs.a(this.value, unsignedLong.value);
    }

    @Override // java.lang.Object
    public int hashCode() {
        return Longs.a(this.value);
    }

    @Override // java.lang.Object
    public boolean equals(Object obj) {
        if (!(obj instanceof UnsignedLong) || this.value != ((UnsignedLong) obj).value) {
            return false;
        }
        return true;
    }

    @Override // java.lang.Object
    public String toString() {
        return UnsignedLongs.a(this.value);
    }

    public String toString(int i) {
        return UnsignedLongs.a(this.value, i);
    }
}
