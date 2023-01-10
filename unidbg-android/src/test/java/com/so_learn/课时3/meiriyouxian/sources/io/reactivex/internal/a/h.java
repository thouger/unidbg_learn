package io.reactivex.internal.a;

/* compiled from: SimpleQueue */
public interface h<T> {
    void clear();

    boolean isEmpty();

    boolean offer(T t);

    T poll() throws Exception;
}
