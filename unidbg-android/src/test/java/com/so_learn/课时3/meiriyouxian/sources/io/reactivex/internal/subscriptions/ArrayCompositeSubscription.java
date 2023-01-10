package io.reactivex.internal.subscriptions;

import io.reactivex.disposables.b;
import java.util.concurrent.atomic.AtomicReferenceArray;
import org.a.d;

public final class ArrayCompositeSubscription extends AtomicReferenceArray<d> implements b {
    private static final long serialVersionUID = 2746389416410565408L;

    public ArrayCompositeSubscription(int i) {
        super(i);
    }

    public boolean setResource(int i, d dVar) {
        SubscriptionHelper subscriptionHelper;
        do {
            subscriptionHelper = (d) get(i);
            if (subscriptionHelper == SubscriptionHelper.CANCELLED) {
                if (dVar == null) {
                    return false;
                }
                dVar.cancel();
                return false;
            }
        } while (!compareAndSet(i, subscriptionHelper, dVar));
        if (subscriptionHelper == null) {
            return true;
        }
        subscriptionHelper.cancel();
        return true;
    }

    public d replaceResource(int i, d dVar) {
        SubscriptionHelper subscriptionHelper;
        do {
            subscriptionHelper = (d) get(i);
            if (subscriptionHelper == SubscriptionHelper.CANCELLED) {
                if (dVar == null) {
                    return null;
                }
                dVar.cancel();
                return null;
            }
        } while (!compareAndSet(i, subscriptionHelper, dVar));
        return subscriptionHelper;
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        SubscriptionHelper subscriptionHelper;
        if (get(0) != SubscriptionHelper.CANCELLED) {
            int length = length();
            for (int i = 0; i < length; i++) {
                if (!(((d) get(i)) == SubscriptionHelper.CANCELLED || (subscriptionHelper = (d) getAndSet(i, SubscriptionHelper.CANCELLED)) == SubscriptionHelper.CANCELLED || subscriptionHelper == null)) {
                    subscriptionHelper.cancel();
                }
            }
        }
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return get(0) == SubscriptionHelper.CANCELLED;
    }
}
