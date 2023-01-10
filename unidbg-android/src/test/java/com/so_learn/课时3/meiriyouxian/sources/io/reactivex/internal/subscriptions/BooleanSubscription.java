package io.reactivex.internal.subscriptions;

import com.umeng.message.proguard.l;
import java.util.concurrent.atomic.AtomicBoolean;
import org.a.d;

public final class BooleanSubscription extends AtomicBoolean implements d {
    private static final long serialVersionUID = -8127758972444290902L;

    public void request(long j) {
        SubscriptionHelper.validate(j);
    }

    public void cancel() {
        lazySet(true);
    }

    public boolean isCancelled() {
        return get();
    }

    @Override // java.util.concurrent.atomic.AtomicBoolean, java.lang.Object
    public String toString() {
        return "BooleanSubscription(cancelled=" + get() + l.t;
    }
}
