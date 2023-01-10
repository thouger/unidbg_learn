package com.google.common.hash;

import com.google.common.base.j;
import com.google.common.base.m;
import com.google.common.base.n;
import com.google.common.hash.BloomFilterStrategies;
import com.google.common.math.a;
import com.google.common.primitives.SignedBytes;
import com.google.common.primitives.UnsignedBytes;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.math.RoundingMode;

public final class BloomFilter<T> implements n<T>, Serializable {
    private final BloomFilterStrategies.a bits;
    private final Funnel<? super T> funnel;
    private final int numHashFunctions;
    private final Strategy strategy;

    /* access modifiers changed from: package-private */
    public interface Strategy extends Serializable {
        <T> boolean mightContain(T t, Funnel<? super T> funnel, int i, BloomFilterStrategies.a aVar);

        int ordinal();

        <T> boolean put(T t, Funnel<? super T> funnel, int i, BloomFilterStrategies.a aVar);
    }

    private BloomFilter(BloomFilterStrategies.a aVar, int i, Funnel<? super T> funnel, Strategy strategy) {
        boolean z = true;
        m.a(i > 0, "numHashFunctions (%s) must be > 0", i);
        m.a(i > 255 ? false : z, "numHashFunctions (%s) must be <= 255", i);
        this.bits = (BloomFilterStrategies.a) m.a(aVar);
        this.numHashFunctions = i;
        this.funnel = (Funnel) m.a(funnel);
        this.strategy = (Strategy) m.a(strategy);
    }

    public BloomFilter<T> copy() {
        return new BloomFilter<>(this.bits.c(), this.numHashFunctions, this.funnel, this.strategy);
    }

    public boolean mightContain(T t) {
        return this.strategy.mightContain(t, this.funnel, this.numHashFunctions, this.bits);
    }

    @Override // com.google.common.base.n
    @Deprecated
    public boolean apply(T t) {
        return mightContain(t);
    }

    public boolean put(T t) {
        return this.strategy.put(t, this.funnel, this.numHashFunctions, this.bits);
    }

    public double expectedFpp() {
        return Math.pow(((double) this.bits.b()) / ((double) bitSize()), (double) this.numHashFunctions);
    }

    public long approximateElementCount() {
        double a = (double) this.bits.a();
        return a.b(((-Math.log1p(-(((double) this.bits.b()) / a))) * a) / ((double) this.numHashFunctions), RoundingMode.HALF_UP);
    }

    /* access modifiers changed from: package-private */
    public long bitSize() {
        return this.bits.a();
    }

    public boolean isCompatible(BloomFilter<T> bloomFilter) {
        m.a(bloomFilter);
        return this != bloomFilter && this.numHashFunctions == bloomFilter.numHashFunctions && bitSize() == bloomFilter.bitSize() && this.strategy.equals(bloomFilter.strategy) && this.funnel.equals(bloomFilter.funnel);
    }

    public void putAll(BloomFilter<T> bloomFilter) {
        m.a(bloomFilter);
        m.a(this != bloomFilter, "Cannot combine a BloomFilter with itself.");
        m.a(this.numHashFunctions == bloomFilter.numHashFunctions, "BloomFilters must have the same number of hash functions (%s != %s)", this.numHashFunctions, bloomFilter.numHashFunctions);
        m.a(bitSize() == bloomFilter.bitSize(), "BloomFilters must have the same size underlying bit arrays (%s != %s)", bitSize(), bloomFilter.bitSize());
        m.a(this.strategy.equals(bloomFilter.strategy), "BloomFilters must have equal strategies (%s != %s)", this.strategy, bloomFilter.strategy);
        m.a(this.funnel.equals(bloomFilter.funnel), "BloomFilters must have equal funnels (%s != %s)", this.funnel, bloomFilter.funnel);
        this.bits.a(bloomFilter.bits);
    }

    @Override // com.google.common.base.n, java.lang.Object
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BloomFilter)) {
            return false;
        }
        BloomFilter bloomFilter = (BloomFilter) obj;
        return this.numHashFunctions == bloomFilter.numHashFunctions && this.funnel.equals(bloomFilter.funnel) && this.bits.equals(bloomFilter.bits) && this.strategy.equals(bloomFilter.strategy);
    }

    @Override // java.lang.Object
    public int hashCode() {
        return j.a(Integer.valueOf(this.numHashFunctions), this.funnel, this.strategy, this.bits);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, int i, double d) {
        return create(funnel, (long) i, d);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, long j, double d) {
        return create(funnel, j, d, BloomFilterStrategies.MURMUR128_MITZ_64);
    }

    static <T> BloomFilter<T> create(Funnel<? super T> funnel, long j, double d, Strategy strategy) {
        m.a(funnel);
        int i = (j > 0 ? 1 : (j == 0 ? 0 : -1));
        boolean z = true;
        m.a(i >= 0, "Expected insertions (%s) must be >= 0", j);
        m.a(d > 0.0d, "False positive probability (%s) must be > 0.0", Double.valueOf(d));
        if (d >= 1.0d) {
            z = false;
        }
        m.a(z, "False positive probability (%s) must be < 1.0", Double.valueOf(d));
        m.a(strategy);
        if (i == 0) {
            j = 1;
        }
        long optimalNumOfBits = optimalNumOfBits(j, d);
        try {
            return new BloomFilter<>(new BloomFilterStrategies.a(optimalNumOfBits), optimalNumOfHashFunctions(j, optimalNumOfBits), funnel, strategy);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Could not create BloomFilter of " + optimalNumOfBits + " bits", e);
        }
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, int i) {
        return create(funnel, (long) i);
    }

    public static <T> BloomFilter<T> create(Funnel<? super T> funnel, long j) {
        return create(funnel, j, 0.03d);
    }

    static int optimalNumOfHashFunctions(long j, long j2) {
        return Math.max(1, (int) Math.round((((double) j2) / ((double) j)) * Math.log(2.0d)));
    }

    static long optimalNumOfBits(long j, double d) {
        if (d == 0.0d) {
            d = Double.MIN_VALUE;
        }
        return (long) ((((double) (-j)) * Math.log(d)) / (Math.log(2.0d) * Math.log(2.0d)));
    }

    private Object writeReplace() {
        return new SerialForm(this);
    }

    private static class SerialForm<T> implements Serializable {
        private static final long serialVersionUID = 1;
        final long[] data;
        final Funnel<? super T> funnel;
        final int numHashFunctions;
        final Strategy strategy;

        SerialForm(BloomFilter<T> bloomFilter) {
            this.data = BloomFilterStrategies.a.a(((BloomFilter) bloomFilter).bits.a);
            this.numHashFunctions = ((BloomFilter) bloomFilter).numHashFunctions;
            this.funnel = ((BloomFilter) bloomFilter).funnel;
            this.strategy = ((BloomFilter) bloomFilter).strategy;
        }

        /* access modifiers changed from: package-private */
        public Object readResolve() {
            return new BloomFilter(new BloomFilterStrategies.a(this.data), this.numHashFunctions, this.funnel, this.strategy);
        }
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        dataOutputStream.writeByte(SignedBytes.a((long) this.strategy.ordinal()));
        dataOutputStream.writeByte(UnsignedBytes.a((long) this.numHashFunctions));
        dataOutputStream.writeInt(this.bits.a.length());
        for (int i = 0; i < this.bits.a.length(); i++) {
            dataOutputStream.writeLong(this.bits.a.get(i));
        }
    }

    public static <T> BloomFilter<T> readFrom(InputStream inputStream, Funnel<? super T> funnel) throws IOException {
        RuntimeException e;
        byte b;
        int i;
        m.a(inputStream, "InputStream");
        m.a(funnel, "Funnel");
        try {
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            b = dataInputStream.readByte();
            try {
                i = UnsignedBytes.a(dataInputStream.readByte());
                try {
                    int readInt = dataInputStream.readInt();
                    BloomFilterStrategies bloomFilterStrategies = BloomFilterStrategies.values()[b];
                    long[] jArr = new long[readInt];
                    for (int i2 = 0; i2 < jArr.length; i2++) {
                        jArr[i2] = dataInputStream.readLong();
                    }
                    return new BloomFilter<>(new BloomFilterStrategies.a(jArr), i, funnel, bloomFilterStrategies);
                } catch (RuntimeException e2) {
                    e = e2;
                    throw new IOException("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: " + ((int) b) + " numHashFunctions: " + i + " dataLength: -1", e);
                }
            } catch (RuntimeException e3) {
                e = e3;
                i = -1;
                throw new IOException("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: " + ((int) b) + " numHashFunctions: " + i + " dataLength: -1", e);
            }
        } catch (RuntimeException e4) {
            e = e4;
            b = -1;
            i = -1;
            throw new IOException("Unable to deserialize BloomFilter from InputStream. strategyOrdinal: " + ((int) b) + " numHashFunctions: " + i + " dataLength: -1", e);
        }
    }
}
