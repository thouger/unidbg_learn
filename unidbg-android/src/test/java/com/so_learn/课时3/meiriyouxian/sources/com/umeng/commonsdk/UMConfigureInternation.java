package com.umeng.commonsdk;

import android.content.Context;
import android.text.TextUtils;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.framework.UMFrUtils;
import com.umeng.commonsdk.framework.UMLogDataProtocol;
import com.umeng.commonsdk.framework.UMModuleRegister;
import com.umeng.commonsdk.framework.UMWorkDispatch;
import com.umeng.commonsdk.internal.a;
import com.umeng.commonsdk.internal.b;
import com.umeng.commonsdk.statistics.common.ULog;

public class UMConfigureInternation {
    private static boolean isInternal;

    /* renamed from: com.umeng.commonsdk.UMConfigureInternation$1  reason: invalid class name */
    static class AnonymousClass1 implements Runnable {
        final /* synthetic */ Context a;

        AnonymousClass1(Context context) {
            this.a = context;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                String currentProcessName = UMFrUtils.getCurrentProcessName(this.a);
                String packageName = this.a.getPackageName();
                if (!TextUtils.isEmpty(currentProcessName) && !TextUtils.isEmpty(packageName) && currentProcessName.equals(packageName) && UMEnvelopeBuild.isReadyBuild(this.a, UMLogDataProtocol.UMBusinessType.U_INTERNAL)) {
                    UMWorkDispatch.sendEvent(this.a, 32777, b.a(this.a).a(), null);
                }
            } catch (Throwable unused) {
            }
        }
    }

    public static synchronized void sendInternal(Context context) {
        synchronized (UMConfigureInternation.class) {
            if (context != null) {
                try {
                    if (!isInternal) {
                        new Thread(new AnonymousClass1(context)).start();
                        isInternal = true;
                    }
                } catch (Throwable th) {
                    ULog.e(UMModuleRegister.INNER, "e is " + th.getMessage());
                }
            }
        }
        return;
    }

    public static void doSelfCheck(Context context) {
        try {
            String currentProcessName = UMFrUtils.getCurrentProcessName(context);
            String packageName = context.getPackageName();
            if (!TextUtils.isEmpty(currentProcessName) && !TextUtils.isEmpty(packageName) && currentProcessName.equals(packageName)) {
                UMWorkDispatch.sendEvent(context, a.z, b.a(context).a(), null);
            }
        } catch (Throwable unused) {
        }
    }
}
