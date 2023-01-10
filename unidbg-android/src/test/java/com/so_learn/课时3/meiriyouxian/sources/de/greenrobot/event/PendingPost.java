package de.greenrobot.event;

import java.util.ArrayList;
import java.util.List;

/* access modifiers changed from: package-private */
public final class PendingPost {
    private static final List<PendingPost> pendingPostPool = new ArrayList();
    Object event;
    PendingPost next;
    Subscription subscription;

    private PendingPost(Object obj, Subscription subscription) {
        this.event = obj;
        this.subscription = subscription;
    }

    static PendingPost obtainPendingPost(Subscription subscription, Object obj) {
        synchronized (pendingPostPool) {
            int size = pendingPostPool.size();
            if (size <= 0) {
                return new PendingPost(obj, subscription);
            }
            PendingPost remove = pendingPostPool.remove(size - 1);
            remove.event = obj;
            remove.subscription = subscription;
            remove.next = null;
            return remove;
        }
    }

    static void releasePendingPost(PendingPost pendingPost) {
        pendingPost.event = null;
        pendingPost.subscription = null;
        pendingPost.next = null;
        synchronized (pendingPostPool) {
            if (pendingPostPool.size() < 10000) {
                pendingPostPool.add(pendingPost);
            }
        }
    }
}
