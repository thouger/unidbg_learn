package com.hmt.analytics.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: ActivityEventProcessor */
public class b {
    private static final String a = b.class.getSimpleName();
    private static String b = null;
    private static AtomicReference<String> c = new AtomicReference<>("");
    private static AtomicReference<String> d = new AtomicReference<>("");
    private static AtomicReference<Integer> e = new AtomicReference<>(-1);
    private static AtomicBoolean f = new AtomicBoolean(false);
    private static List<Object> g = new ArrayList();
    private List<Object> h = Collections.synchronizedList(new ArrayList());
}
