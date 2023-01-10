package io.reactivex.internal.util;

import io.reactivex.disposables.b;
import io.reactivex.exceptions.ProtocolViolationException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.a;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import java.util.concurrent.atomic.AtomicReference;

/* compiled from: EndConsumerHelper */
public final class d {
    public static boolean a(AtomicReference<b> atomicReference, b bVar, Class<?> cls) {
        a.a(bVar, "next is null");
        if (atomicReference.compareAndSet(null, bVar)) {
            return true;
        }
        bVar.dispose();
        if (atomicReference.get() == DisposableHelper.DISPOSED) {
            return false;
        }
        a(cls);
        return false;
    }

    public static boolean a(AtomicReference<org.a.d> atomicReference, org.a.d dVar, Class<?> cls) {
        a.a(dVar, "next is null");
        if (atomicReference.compareAndSet(null, dVar)) {
            return true;
        }
        dVar.cancel();
        if (atomicReference.get() == SubscriptionHelper.CANCELLED) {
            return false;
        }
        a(cls);
        return false;
    }

    public static String a(String str) {
        return "It is not allowed to subscribe with a(n) " + str + " multiple times. Please create a fresh instance of " + str + " and subscribe that to the target source instead.";
    }

    public static void a(Class<?> cls) {
        io.reactivex.e.a.a(new ProtocolViolationException(a(cls.getName())));
    }
}
