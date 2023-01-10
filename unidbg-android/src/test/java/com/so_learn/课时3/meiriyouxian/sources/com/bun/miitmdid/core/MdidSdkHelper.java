package com.bun.miitmdid.core;

import android.content.Context;
import android.util.Log;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MdidSdkHelper {
    public static String TAG = MdidSdkHelper.class.getSimpleName();

    static {
        AppMethodBeat.i(5152, false);
        AppMethodBeat.o(5152);
    }

    public static int InitSdk(Context context, boolean z, IIdentifierListener iIdentifierListener) {
        AppMethodBeat.i(5145, false);
        try {
            Class<?> cls = Class.forName("com.bun.miitmdid.core.MdidSdk");
            if (cls == null) {
                logd(z, "not found class:com.bun.miitmdid.core.MdidSdk");
                AppMethodBeat.o(5145);
                return ErrorCode.INIT_HELPER_CALL_ERROR;
            }
            Constructor<?> constructor = cls.getConstructor(Boolean.TYPE);
            if (constructor == null) {
                logd(z, "not found MdidSdk Constructor");
                AppMethodBeat.o(5145);
                return ErrorCode.INIT_HELPER_CALL_ERROR;
            }
            Object newInstance = constructor.newInstance(Boolean.valueOf(z));
            if (newInstance == null) {
                logd(z, "Create MdidSdk Instance failed");
                AppMethodBeat.o(5145);
                return ErrorCode.INIT_HELPER_CALL_ERROR;
            }
            Method declaredMethod = cls.getDeclaredMethod("InitSdk", Context.class, IIdentifierListener.class);
            if (declaredMethod == null) {
                logd(z, "not found MdidSdk InitSdk function");
                AppMethodBeat.o(5145);
                return ErrorCode.INIT_HELPER_CALL_ERROR;
            }
            int intValue = ((Integer) declaredMethod.invoke(newInstance, context, iIdentifierListener)).intValue();
            logd(z, "call and retvalue:" + intValue);
            AppMethodBeat.o(5145);
            return intValue;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
            loge(z, e);
            logd(z, "exception exit");
            AppMethodBeat.o(5145);
            return ErrorCode.INIT_HELPER_CALL_ERROR;
        }
    }

    public static void logd(boolean z, String str) {
        AppMethodBeat.i(5148, false);
        if (!z) {
            AppMethodBeat.o(5148);
            return;
        }
        Log.d(TAG, str);
        AppMethodBeat.o(5148);
    }

    public static void loge(boolean z, Exception exc) {
        AppMethodBeat.i(5150, false);
        if (!z) {
            AppMethodBeat.o(5150);
            return;
        }
        Log.e(TAG, exc.getClass().getSimpleName(), exc);
        AppMethodBeat.o(5150);
    }
}
