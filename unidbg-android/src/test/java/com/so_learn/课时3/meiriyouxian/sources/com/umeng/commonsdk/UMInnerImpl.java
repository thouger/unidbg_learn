package com.umeng.commonsdk;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.framework.UMModuleRegister;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.internal.d;
import com.umeng.commonsdk.internal.utils.a;
import com.umeng.commonsdk.internal.utils.b;
import com.umeng.commonsdk.internal.utils.c;
import com.umeng.commonsdk.internal.utils.k;
import com.umeng.commonsdk.statistics.common.ULog;

public class UMInnerImpl {
    private static boolean isInternal;

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.UMInnerImpl$1  reason: invalid class name */
    public static class AnonymousClass1 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass1(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                String currentProcessName = UMFrUtils.getCurrentProcessName(this.a);
                String packageName = this.a.getPackageName();
                if (!TextUtils.isEmpty(currentProcessName) && !TextUtils.isEmpty(packageName) && currentProcessName.equals(packageName)) {
                    try {
                        d.b(this.a);
                    } catch (Throwable th) {
                        ULog.e(UMModuleRegister.INNER, "e is " + th);
                    }
                }
            } catch (Throwable th2) {
                UMCrashManager.reportCrash(this.a, th2);
            }
        }
    }

    private static synchronized void sendInternal(Context context) {
        synchronized (UMInnerImpl.class) {
            if (context != null) {
                try {
                    new Thread(new AnonymousClass1(context)).start();
                    isInternal = true;
                } catch (Throwable th) {
                    ULog.e(UMModuleRegister.INNER, "e is " + th.getMessage());
                    UMCrashManager.reportCrash(context, th);
                }
            }
        }
        return;
    }

    /* renamed from: com.umeng.commonsdk.UMInnerImpl$2  reason: invalid class name */
    static class AnonymousClass2 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass2(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                String currentProcessName = UMFrUtils.getCurrentProcessName(this.a);
                String packageName = this.a.getPackageName();
                if (!TextUtils.isEmpty(currentProcessName) && !TextUtils.isEmpty(packageName) && currentProcessName.equals(packageName)) {
                    try {
                        if (!c.a(this.a).a()) {
                            c.a(this.a).b();
                        }
                    } catch (Throwable th) {
                        ULog.e(UMModuleRegister.INNER, "e is " + th);
                    }
                    try {
                        k.b(this.a);
                    } catch (Throwable th2) {
                        ULog.e(UMModuleRegister.INNER, "e is " + th2);
                    }
                    try {
                        a.c(this.a);
                    } catch (Throwable th3) {
                        ULog.e(UMModuleRegister.INNER, "e is " + th3);
                    }
                    try {
                        if (!b.a(this.a).a()) {
                            b.a(this.a).b();
                        }
                    } catch (Throwable unused) {
                        ULog.e(UMModuleRegister.INNER, "get station is null ");
                    }
                }
            } catch (Throwable th4) {
                UMCrashManager.reportCrash(this.a, th4);
            }
        }
    }

    public static synchronized void initAndSendInternal(Context context) {
        synchronized (UMInnerImpl.class) {
            if (context != null) {
                try {
                    if (!isInternal) {
                        new Thread(new AnonymousClass2(context)).start();
                        isInternal = true;
                        sendInternal(context);
                    }
                } catch (Throwable th) {
                    ULog.e(UMModuleRegister.INNER, "e is " + th.getMessage());
                    UMCrashManager.reportCrash(context, th);
                }
            }
        }
        return;
    }
}
