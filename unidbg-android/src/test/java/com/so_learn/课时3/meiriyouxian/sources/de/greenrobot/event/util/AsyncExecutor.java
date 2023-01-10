package de.greenrobot.event.util;

import android.app.Activity;
import android.util.Log;
import de.greenrobot.event.EventBus;
import java.lang.reflect.Constructor;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AsyncExecutor {
    private final EventBus eventBus;
    private final Constructor<?> failureEventConstructor;
    private final Object scope;
    private final Executor threadPool;

    public interface RunnableEx {
        void run() throws Exception;
    }

    /* synthetic */ AsyncExecutor(Executor executor, EventBus eventBus, Class cls, Object obj, AnonymousClass1 r5) {
        this(executor, eventBus, cls, obj);
    }

    public static class Builder {
        private EventBus eventBus;
        private Class<?> failureEventType;
        private Executor threadPool;

        /* synthetic */ Builder(AnonymousClass1 r1) {
            this();
        }

        private Builder() {
        }

        public Builder threadPool(Executor executor) {
            this.threadPool = executor;
            return this;
        }

        public Builder failureEventType(Class<?> cls) {
            this.failureEventType = cls;
            return this;
        }

        public Builder eventBus(EventBus eventBus) {
            this.eventBus = eventBus;
            return this;
        }

        public AsyncExecutor build() {
            return buildForScope(null);
        }

        public AsyncExecutor buildForActivityScope(Activity activity) {
            return buildForScope(activity.getClass());
        }

        public AsyncExecutor buildForScope(Object obj) {
            if (this.eventBus == null) {
                this.eventBus = EventBus.getDefault();
            }
            if (this.threadPool == null) {
                this.threadPool = Executors.newCachedThreadPool();
            }
            if (this.failureEventType == null) {
                this.failureEventType = ThrowableFailureEvent.class;
            }
            return new AsyncExecutor(this.threadPool, this.eventBus, this.failureEventType, obj, null);
        }
    }

    public static Builder builder() {
        return new Builder(null);
    }

    public static AsyncExecutor create() {
        return new Builder(null).build();
    }

    private AsyncExecutor(Executor executor, EventBus eventBus, Class<?> cls, Object obj) {
        this.threadPool = executor;
        this.eventBus = eventBus;
        this.scope = obj;
        try {
            this.failureEventConstructor = cls.getConstructor(Throwable.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("Failure event class must have a constructor with one parameter of type Throwable", e);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: de.greenrobot.event.util.AsyncExecutor$1  reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ RunnableEx val$runnable;

        AnonymousClass1(RunnableEx runnableEx) {
            this.val$runnable = runnableEx;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                this.val$runnable.run();
            } catch (Exception e) {
                try {
                    Object newInstance = AsyncExecutor.this.failureEventConstructor.newInstance(e);
                    if (newInstance instanceof HasExecutionScope) {
                        ((HasExecutionScope) newInstance).setExecutionScope(AsyncExecutor.this.scope);
                    }
                    AsyncExecutor.this.eventBus.post(newInstance);
                } catch (Exception e2) {
                    Log.e(EventBus.TAG, "Original exception:", e);
                    throw new RuntimeException("Could not create failure event", e2);
                }
            }
        }
    }

    public void execute(RunnableEx runnableEx) {
        this.threadPool.execute(new AnonymousClass1(runnableEx));
    }
}
