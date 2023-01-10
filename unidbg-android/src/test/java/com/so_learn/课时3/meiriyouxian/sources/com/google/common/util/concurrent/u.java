package com.google.common.util.concurrent;

import com.google.common.util.concurrent.a;

/* compiled from: SettableFuture */
public final class u<V> extends a.i<V> {
    public static <V> u<V> h() {
        return new u<>();
    }

    @Override // com.google.common.util.concurrent.a
    public boolean a(V v) {
        return super.a((u<V>) v);
    }

    @Override // com.google.common.util.concurrent.a
    public boolean a(Throwable th) {
        return super.a(th);
    }

    @Override // com.google.common.util.concurrent.a
    public boolean a(p<? extends V> pVar) {
        return super.a((p) pVar);
    }

    private u() {
    }
}
