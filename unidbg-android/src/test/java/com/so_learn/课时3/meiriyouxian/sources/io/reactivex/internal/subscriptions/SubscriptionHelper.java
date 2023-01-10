package io.reactivex.internal.subscriptions;

import io.reactivex.e.a;
import io.reactivex.exceptions.ProtocolViolationException;
import io.reactivex.internal.util.b;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.a.d;

public enum SubscriptionHelper implements d {
    CANCELLED;

    public void cancel() {
    }

    public void request(long j) {
    }

    public static boolean validate(d dVar, d dVar2) {
        if (dVar2 == null) {
            a.a(new NullPointerException("next is null"));
            return false;
        } else if (dVar == null) {
            return true;
        } else {
            dVar2.cancel();
            reportSubscriptionSet();
            return false;
        }
    }

    public static void reportSubscriptionSet() {
        a.a(new ProtocolViolationException("Subscription already set!"));
    }

    public static boolean validate(long j) {
        if (j > 0) {
            return true;
        }
        a.a(new IllegalArgumentException("n > 0 required but it was " + j));
        return false;
    }

    public static void reportMoreProduced(long j) {
        a.a(new ProtocolViolationException("More produced than requested: " + j));
    }

    public static boolean set(AtomicReference<d> atomicReference, d dVar) {
        SubscriptionHelper subscriptionHelper;
        do {
            subscriptionHelper = (d) atomicReference.get();
            if (subscriptionHelper == CANCELLED) {
                if (dVar == null) {
                    return false;
                }
                dVar.cancel();
                return false;
            }
        } while (!atomicReference.compareAndSet(subscriptionHelper, dVar));
        if (subscriptionHelper == null) {
            return true;
        }
        subscriptionHelper.cancel();
        return true;
    }

    public static boolean setOnce(AtomicReference<d> atomicReference, d dVar) {
        io.reactivex.internal.functions.a.a(dVar, "s is null");
        if (atomicReference.compareAndSet(null, dVar)) {
            return true;
        }
        dVar.cancel();
        if (atomicReference.get() == CANCELLED) {
            return false;
        }
        reportSubscriptionSet();
        return false;
    }

    public static boolean replace(AtomicReference<d> atomicReference, d dVar) {
        SubscriptionHelper subscriptionHelper;
        do {
            subscriptionHelper = (d) atomicReference.get();
            if (subscriptionHelper == CANCELLED) {
                if (dVar == null) {
                    return false;
                }
                dVar.cancel();
                return false;
            }
        } while (!atomicReference.compareAndSet(subscriptionHelper, dVar));
        return true;
    }

    public static boolean cancel(AtomicReference<d> atomicReference) {
        SubscriptionHelper subscriptionHelper;
        SubscriptionHelper subscriptionHelper2 = (d) atomicReference.get();
        SubscriptionHelper subscriptionHelper3 = CANCELLED;
        if (subscriptionHelper2 == subscriptionHelper3 || (subscriptionHelper = (d) atomicReference.getAndSet(subscriptionHelper3)) == CANCELLED) {
            return false;
        }
        if (subscriptionHelper == null) {
            return true;
        }
        subscriptionHelper.cancel();
        return true;
    }

    public static boolean deferredSetOnce(AtomicReference<d> atomicReference, AtomicLong atomicLong, d dVar) {
        if (!setOnce(atomicReference, dVar)) {
            return false;
        }
        long andSet = atomicLong.getAndSet(0);
        if (andSet == 0) {
            return true;
        }
        dVar.request(andSet);
        return true;
    }

    public static void deferredRequest(AtomicReference<d> atomicReference, AtomicLong atomicLong, long j) {
        d dVar = atomicReference.get();
        if (dVar != null) {
            dVar.request(j);
        } else if (validate(j)) {
            b.a(atomicLong, j);
            d dVar2 = atomicReference.get();
            if (dVar2 != null) {
                long andSet = atomicLong.getAndSet(0);
                if (andSet != 0) {
                    dVar2.request(andSet);
                }
            }
        }
    }

    public static boolean setOnce(AtomicReference<d> atomicReference, d dVar, long j) {
        if (!setOnce(atomicReference, dVar)) {
            return false;
        }
        dVar.request(j);
        return true;
    }
}
