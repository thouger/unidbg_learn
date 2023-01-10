package com.umeng.analytics.pro;

import com.umeng.analytics.pro.aq;
import com.umeng.analytics.pro.ax;
import java.io.Serializable;

/* compiled from: TBase */
public interface aq<T extends aq<?, ?>, F extends ax> extends Serializable {
    void clear();

    aq<T, F> deepCopy();

    F fieldForId(int i);

    void read(bp bpVar) throws aw;

    void write(bp bpVar) throws aw;
}
