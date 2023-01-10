package io.reactivex.processors;

import io.reactivex.g;
import io.reactivex.j;

/* compiled from: FlowableProcessor */
public abstract class a<T> extends g<T> implements j<T>, org.a.a<T, T> {
    public final a<T> g() {
        if (this instanceof b) {
            return this;
        }
        return new b(this);
    }
}
