package com.google.common.hash;

import java.io.Serializable;

public interface Funnel<T> extends Serializable {
    void funnel(T t, i iVar);
}
