package com.alipay.android.phone.mrpc.core;

import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

final class m extends FutureTask<u> {
    final /* synthetic */ q a;
    final /* synthetic */ l b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    m(l lVar, Callable callable, q qVar) {
        super(callable);
        this.b = lVar;
        this.a = qVar;
    }

    /* access modifiers changed from: protected */
    @Override // java.util.concurrent.FutureTask
    public final void done() {
        o a = this.a.a();
        if (a.f() == null) {
            super.done();
            return;
        }
        try {
            get();
            if (isCancelled() || a.h()) {
                a.g();
                if (!isCancelled() || !isDone()) {
                    cancel(false);
                }
            }
        } catch (InterruptedException e) {
            new StringBuilder().append(e);
        } catch (ExecutionException e2) {
            if (e2.getCause() == null || !(e2.getCause() instanceof HttpException)) {
                new StringBuilder().append(e2);
                return;
            }
            HttpException httpException = (HttpException) e2.getCause();
            httpException.getCode();
            httpException.getMsg();
        } catch (CancellationException unused) {
            a.g();
        } catch (Throwable th) {
            throw new RuntimeException("An error occured while executing http request", th);
        }
    }
}
