package com.cmic.sso.wy.utils;

import android.content.Context;
import android.os.Bundle;
import java.lang.Thread;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* compiled from: ThreadUtils */
public class v {
    private static ExecutorService a = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue());

    public static void a(a aVar) {
        a.execute(aVar);
    }

    /* compiled from: ThreadUtils */
    public static abstract class a implements Runnable {
        private Thread.UncaughtExceptionHandler a;

        /* access modifiers changed from: protected */
        public abstract void a();

        /* compiled from: ThreadUtils */
        /* renamed from: com.cmic.sso.wy.utils.v$a$1  reason: invalid class name */
        class AnonymousClass1 implements Thread.UncaughtExceptionHandler {
            AnonymousClass1() {
            }

            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                th.printStackTrace();
            }
        }

        protected a() {
            this.a = new AnonymousClass1();
        }

        /* compiled from: ThreadUtils */
        /* renamed from: com.cmic.sso.wy.utils.v$a$2  reason: invalid class name */
        class AnonymousClass2 implements Thread.UncaughtExceptionHandler {
            final /* synthetic */ Context a;
            final /* synthetic */ Bundle b;

            AnonymousClass2(Context context, Bundle bundle) {
                this.a = context;
                this.b = bundle;
            }

            @Override // java.lang.Thread.UncaughtExceptionHandler
            public void uncaughtException(Thread thread, Throwable th) {
                th.printStackTrace();
                com.cmic.sso.wy.b.a.a(this.a).a("200025", "\u53d1\u751f\u672a\u77e5\u9519\u8bef", this.b, null, th);
            }
        }

        protected a(Context context, Bundle bundle) {
            this.a = new AnonymousClass2(context, bundle);
        }

        @Override // java.lang.Runnable
        public void run() {
            Thread.currentThread().setUncaughtExceptionHandler(this.a);
            a();
            Thread.currentThread().setUncaughtExceptionHandler(null);
        }
    }
}
