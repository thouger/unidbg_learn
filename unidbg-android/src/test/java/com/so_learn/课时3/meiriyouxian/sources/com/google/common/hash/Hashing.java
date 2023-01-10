package com.google.common.hash;

import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

public final class Hashing {
    static final int a = ((int) System.currentTimeMillis());

    public static e a() {
        return Murmur3_128HashFunction.MURMUR3_128;
    }

    enum ChecksumType implements g<Checksum> {
        CRC_32("Hashing.crc32()") {
            @Override // com.google.common.base.q
            public Checksum get() {
                return new CRC32();
            }
        },
        ADLER_32("Hashing.adler32()") {
            @Override // com.google.common.base.q
            public Checksum get() {
                return new Adler32();
            }
        };
        
        public final e hashFunction;

        private ChecksumType(String str) {
            this.hashFunction = new ChecksumHashFunction(this, 32, str);
        }
    }
}
