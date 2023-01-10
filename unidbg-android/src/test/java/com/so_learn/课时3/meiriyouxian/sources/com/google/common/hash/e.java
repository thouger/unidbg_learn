package com.google.common.hash;

/* compiled from: HashFunction */
public interface e {
    <T> HashCode hashObject(T t, Funnel<? super T> funnel);

    f newHasher();
}
