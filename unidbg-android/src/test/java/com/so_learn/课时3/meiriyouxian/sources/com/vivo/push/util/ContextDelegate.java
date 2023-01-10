package com.vivo.push.util;

import android.content.ContentResolver;
import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.reflect.Method;

public class ContextDelegate {
    private static final String TAG = "ContextDelegate";
    private static ContextDelegate mContextDelegate;
    private Method mCreateCredentialProtectedStorageContext;
    private Method mCreateDeviceProtectedStorageContext;
    private Boolean mIsFbeProj = null;

    private ContextDelegate() {
    }

    public static ContextDelegate getInstance() {
        AppMethodBeat.i(1355, false);
        if (mContextDelegate == null) {
            synchronized (ContextDelegate.class) {
                try {
                    if (mContextDelegate == null) {
                        mContextDelegate = new ContextDelegate();
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(1355);
                    throw th;
                }
            }
        }
        ContextDelegate contextDelegate = mContextDelegate;
        AppMethodBeat.o(1355);
        return contextDelegate;
    }

    public Context createDeviceProtectedStorageContext(Context context) {
        AppMethodBeat.i(1359, false);
        try {
            if (this.mCreateDeviceProtectedStorageContext == null) {
                this.mCreateDeviceProtectedStorageContext = Context.class.getMethod("createDeviceProtectedStorageContext", new Class[0]);
            }
            Context context2 = (Context) this.mCreateDeviceProtectedStorageContext.invoke(context, new Object[0]);
            AppMethodBeat.o(1359);
            return context2;
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(1359);
            return context;
        }
    }

    public Context createCredentialProtectedStorageContext(Context context) {
        AppMethodBeat.i(1362, false);
        try {
            if (this.mCreateCredentialProtectedStorageContext == null) {
                this.mCreateCredentialProtectedStorageContext = Context.class.getMethod("createCredentialProtectedStorageContext", new Class[0]);
            }
            Context context2 = (Context) this.mCreateCredentialProtectedStorageContext.invoke(context, new Object[0]);
            AppMethodBeat.o(1362);
            return context2;
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(1362);
            return context;
        }
    }

    public static Context getContext(Context context) {
        AppMethodBeat.i(1366, false);
        if (!getInstance().isFBEProject()) {
            AppMethodBeat.o(1366);
            return context;
        }
        Context createDeviceProtectedStorageContext = getInstance().createDeviceProtectedStorageContext(context);
        AppMethodBeat.o(1366);
        return createDeviceProtectedStorageContext;
    }

    public boolean isFBEProject() {
        AppMethodBeat.i(1370, false);
        if (this.mIsFbeProj == null) {
            this.mIsFbeProj = Boolean.valueOf(ContentResolver.SCHEME_FILE.equals(i.a("ro.crypto.type", "unknow")));
            n.b(TAG, "mIsFbeProj = " + this.mIsFbeProj.toString());
        }
        Boolean bool = this.mIsFbeProj;
        if (bool == null) {
            AppMethodBeat.o(1370);
            return false;
        }
        boolean booleanValue = bool.booleanValue();
        AppMethodBeat.o(1370);
        return booleanValue;
    }
}
