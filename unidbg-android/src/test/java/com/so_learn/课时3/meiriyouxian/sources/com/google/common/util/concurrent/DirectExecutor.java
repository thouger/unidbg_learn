package com.google.common.util.concurrent;

import java.util.concurrent.Executor;

/* access modifiers changed from: package-private */
public enum DirectExecutor implements Executor {
    INSTANCE;

    @Override // java.lang.Enum, java.lang.Object
    public String toString() {
        return "MoreExecutors.directExecutor()";
    }

    @Override // java.util.concurrent.Executor
    public void execute(Runnable runnable) {
        runnable.run();
    }
}
