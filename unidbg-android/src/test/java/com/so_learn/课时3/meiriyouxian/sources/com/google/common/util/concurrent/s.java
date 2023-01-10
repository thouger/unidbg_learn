package com.google.common.util.concurrent;

import com.google.common.base.m;
import com.taobao.accs.common.Constants;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/* compiled from: Monitor */
public final class s {
    private final boolean a;
    private final ReentrantLock b;
    private a c;

    /* compiled from: Monitor */
    public static abstract class a {
        final s b;
        final Condition c;
        int d = 0;

        protected a(s sVar) {
            this.b = (s) m.a(sVar, Constants.KEY_MONIROT);
            this.c = sVar.b.newCondition();
        }
    }

    public s() {
        this(false);
    }

    public s(boolean z) {
        this.c = null;
        this.a = z;
        this.b = new ReentrantLock(z);
    }
}
