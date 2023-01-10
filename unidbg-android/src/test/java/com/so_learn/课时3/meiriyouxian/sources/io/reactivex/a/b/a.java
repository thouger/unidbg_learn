package io.reactivex.a.b;

import android.os.Handler;
import android.os.Looper;
import io.reactivex.w;
import java.util.concurrent.Callable;

/* compiled from: AndroidSchedulers */
public final class a {
    private static final w a = io.reactivex.a.a.a.a(new AnonymousClass1());

    /* compiled from: AndroidSchedulers */
    /* access modifiers changed from: private */
    /* renamed from: io.reactivex.a.b.a$a  reason: collision with other inner class name */
    public static final class C0203a {
        static final w a = new b(new Handler(Looper.getMainLooper()), false);
    }

    /* compiled from: AndroidSchedulers */
    /* renamed from: io.reactivex.a.b.a$1  reason: invalid class name */
    static class AnonymousClass1 implements Callable<w> {
        AnonymousClass1() {
        }

        /* renamed from: a */
        public w call() throws Exception {
            return C0203a.a;
        }
    }

    public static w a() {
        return io.reactivex.a.a.a.a(a);
    }
}
