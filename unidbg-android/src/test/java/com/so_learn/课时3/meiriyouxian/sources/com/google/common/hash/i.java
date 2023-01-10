package com.google.common.hash;

import java.nio.charset.Charset;

/* compiled from: PrimitiveSink */
public interface i {
    i b(int i);

    i b(long j);

    i b(CharSequence charSequence);

    i b(CharSequence charSequence, Charset charset);

    i c(byte[] bArr);
}
