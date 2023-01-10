package com.google.common.hash;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;

/* compiled from: Hasher */
public interface f extends i {
    HashCode a();

    f a(int i);

    f a(long j);

    f a(CharSequence charSequence);

    f a(CharSequence charSequence, Charset charset);

    <T> f a(T t, Funnel<? super T> funnel);

    f b(byte b);

    f b(ByteBuffer byteBuffer);

    f b(byte[] bArr, int i, int i2);
}
