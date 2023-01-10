package com.google.common.util.concurrent;

import com.google.common.util.concurrent.h;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

final class TimeoutFuture<V> extends h.a<V> {
    private p<V> a;
    private ScheduledFuture<?> b;

    private static final class TimeoutFutureException extends TimeoutException {
        private TimeoutFutureException(String str) {
            super(str);
        }

        @Override // java.lang.Throwable
        public synchronized Throwable fillInStackTrace() {
            setStackTrace(new StackTraceElement[0]);
            return this;
        }
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.a
    public String e() {
        p<V> pVar = this.a;
        ScheduledFuture<?> scheduledFuture = this.b;
        if (pVar == null) {
            return null;
        }
        String str = "inputFuture=[" + pVar + "]";
        if (scheduledFuture == null) {
            return str;
        }
        long delay = scheduledFuture.getDelay(TimeUnit.MILLISECONDS);
        if (delay <= 0) {
            return str;
        }
        return str + ", remaining delay=[" + delay + " ms]";
    }

    /* access modifiers changed from: protected */
    @Override // com.google.common.util.concurrent.a
    public void c() {
        a((Future<?>) this.a);
        ScheduledFuture<?> scheduledFuture = this.b;
        if (scheduledFuture != null) {
            scheduledFuture.cancel(false);
        }
        this.a = null;
        this.b = null;
    }
}
