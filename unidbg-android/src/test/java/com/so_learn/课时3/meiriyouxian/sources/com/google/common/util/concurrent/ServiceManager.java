package com.google.common.util.concurrent;

import com.google.common.base.Predicates;
import com.google.common.base.i;
import com.google.common.base.n;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.o;
import com.google.common.util.concurrent.r;
import java.util.Collection;
import java.util.logging.Logger;

public final class ServiceManager {
    private static final Logger a = Logger.getLogger(ServiceManager.class.getName());
    private static final r.a<Object> b = new AnonymousClass1();
    private static final r.a<Object> c = new AnonymousClass2();
    private final ImmutableList<Service> d;

    /* renamed from: com.google.common.util.concurrent.ServiceManager$1  reason: invalid class name */
    static class AnonymousClass1 implements r.a<Object> {
        public String toString() {
            return "healthy()";
        }

        AnonymousClass1() {
        }
    }

    /* renamed from: com.google.common.util.concurrent.ServiceManager$2  reason: invalid class name */
    static class AnonymousClass2 implements r.a<Object> {
        public String toString() {
            return "stopped()";
        }

        AnonymousClass2() {
        }
    }

    public String toString() {
        return i.a((Class<?>) ServiceManager.class).a("services", o.a((Collection) this.d, Predicates.a((n) Predicates.a((Class<?>) a.class)))).toString();
    }

    private static final class a extends b {
        private a() {
        }
    }

    private static final class EmptyServiceManagerWarning extends Throwable {
        private EmptyServiceManagerWarning() {
        }

        /* synthetic */ EmptyServiceManagerWarning(AnonymousClass1 r1) {
            this();
        }
    }
}
