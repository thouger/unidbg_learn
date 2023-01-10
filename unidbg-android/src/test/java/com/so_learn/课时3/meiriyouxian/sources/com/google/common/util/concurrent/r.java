package com.google.common.util.concurrent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/* compiled from: ListenerCallQueue */
/* access modifiers changed from: package-private */
public final class r<L> {
    private static final Logger a = Logger.getLogger(r.class.getName());
    private final List<Object<L>> b = Collections.synchronizedList(new ArrayList());

    /* compiled from: ListenerCallQueue */
    interface a<L> {
    }

    r() {
    }
}
