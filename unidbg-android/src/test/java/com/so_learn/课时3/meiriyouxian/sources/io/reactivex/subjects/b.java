package io.reactivex.subjects;

import io.reactivex.q;
import io.reactivex.v;

/* compiled from: Subject */
public abstract class b<T> extends q<T> implements v<T> {
    public final b<T> b() {
        if (this instanceof a) {
            return this;
        }
        return new a(this);
    }
}
