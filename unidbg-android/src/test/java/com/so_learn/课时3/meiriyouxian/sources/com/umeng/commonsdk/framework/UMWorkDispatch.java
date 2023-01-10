package com.umeng.commonsdk.framework;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.common.ULog;
import com.umeng.commonsdk.utils.UMUtils;
import org.json.JSONObject;

public class UMWorkDispatch {
    public static final String GENERAL_CONTENT = "content";
    public static final String GENERAL_HEADER = "header";
    public static final String KEY_EXCEPTION = "exception";
    private static final int MSG_AUTO_PROCESS = 769;
    private static final int MSG_CHECKER_TIMER = 771;
    private static final int MSG_DELAY_PROCESS = 770;
    private static final int MSG_QUIT = 784;
    private static final int MSG_SEND_EVENT = 768;
    private static HandlerThread mNetTask;
    private static a mSender;
    private static Object mSenderInitLock = new Object();
    private static Handler mTaskHandler;

    public static void registerConnStateObserver(UMSenderStateNotify uMSenderStateNotify) {
        if (mSender != null) {
            a.a(uMSenderStateNotify);
        }
    }

    public static void sendEventInternal(Context context, int i, int i2, UMLogDataProtocol uMLogDataProtocol, Object obj, long j) {
        if (context == null || uMLogDataProtocol == null) {
            ULog.d("--->>> Context or UMLogDataProtocol parameter cannot be null!");
            return;
        }
        UMModuleRegister.registerAppContext(context.getApplicationContext());
        if (UMModuleRegister.registerCallback(i2, uMLogDataProtocol)) {
            if (mNetTask == null || mTaskHandler == null) {
                init();
            }
            try {
                if (mTaskHandler != null) {
                    if (UMUtils.isMainProgress(context)) {
                        synchronized (mSenderInitLock) {
                            if (mSender == null) {
                                UMFrUtils.syncLegacyEnvelopeIfNeeded(context);
                                mSender = new a(context, mTaskHandler);
                            }
                        }
                    }
                    Message obtainMessage = mTaskHandler.obtainMessage();
                    obtainMessage.what = i;
                    obtainMessage.arg1 = i2;
                    obtainMessage.obj = obj;
                    mTaskHandler.sendMessageDelayed(obtainMessage, j);
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(UMModuleRegister.getAppContext(), th);
            }
        }
    }

    public static void sendEvent(Context context, int i, UMLogDataProtocol uMLogDataProtocol, Object obj) {
        sendEventInternal(context, 768, i, uMLogDataProtocol, obj, 0);
    }

    public static void sendEvent(Context context, int i, UMLogDataProtocol uMLogDataProtocol, Object obj, long j) {
        sendEventInternal(context, 768, i, uMLogDataProtocol, obj, j);
    }

    public static void sendEventEx(Context context, int i, UMLogDataProtocol uMLogDataProtocol, Object obj, long j) {
        sendEventInternal(context, 771, i, uMLogDataProtocol, obj, j);
    }

    private UMWorkDispatch() {
    }

    /* access modifiers changed from: private */
    public static void delayProcess() {
        JSONObject buildEnvelopeWithExtHeader;
        ULog.d("--->>> delayProcess Enter...");
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> delayProcess Enter...");
        Context appContext = UMModuleRegister.getAppContext();
        if (appContext != null && UMFrUtils.isOnline(appContext)) {
            long maxDataSpace = UMEnvelopeBuild.maxDataSpace(appContext);
            UMLogDataProtocol callbackFromModuleName = UMModuleRegister.getCallbackFromModuleName("analytics");
            JSONObject jSONObject = null;
            if (callbackFromModuleName != null) {
                try {
                    jSONObject = callbackFromModuleName.setupReportData(maxDataSpace);
                    if (jSONObject == null) {
                        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> analyticsCB.setupReportData() return null");
                        return;
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(appContext, th);
                    return;
                }
            }
            if (jSONObject != null && jSONObject.length() > 0) {
                JSONObject jSONObject2 = (JSONObject) jSONObject.opt("header");
                JSONObject jSONObject3 = (JSONObject) jSONObject.opt("content");
                if (appContext != null && jSONObject2 != null && jSONObject3 != null && (buildEnvelopeWithExtHeader = UMEnvelopeBuild.buildEnvelopeWithExtHeader(appContext, jSONObject2, jSONObject3)) != null) {
                    try {
                        if (buildEnvelopeWithExtHeader.has("exception")) {
                            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> autoProcess: Build envelope error code: " + buildEnvelopeWithExtHeader.getInt("exception"));
                        }
                    } catch (Throwable unused) {
                    }
                    UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> autoProcess: removeCacheData ... ");
                    callbackFromModuleName.removeCacheData(buildEnvelopeWithExtHeader);
                }
            }
        }
    }

    public static void sendDelayProcessMsg(long j) {
        Handler handler = mTaskHandler;
        if (handler == null) {
            return;
        }
        if (handler.hasMessages(770)) {
            UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> MSG_DELAY_PROCESS has exist. do nothing.");
            return;
        }
        UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> MSG_DELAY_PROCESS not exist. send it.");
        Message obtainMessage = mTaskHandler.obtainMessage();
        obtainMessage.what = 770;
        mTaskHandler.sendMessageDelayed(obtainMessage, j);
    }

    private static synchronized void init() {
        synchronized (UMWorkDispatch.class) {
            ULog.d("--->>> Dispatch: init Enter...");
            try {
                if (mNetTask == null) {
                    mNetTask = new HandlerThread("work_thread");
                    mNetTask.start();
                    if (mTaskHandler == null) {
                        mTaskHandler = new AnonymousClass1(mNetTask.getLooper());
                    }
                }
            } catch (Throwable th) {
                UMCrashManager.reportCrash(UMModuleRegister.getAppContext(), th);
            }
            ULog.d("--->>> Dispatch: init Exit...");
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.umeng.commonsdk.framework.UMWorkDispatch$1  reason: invalid class name */
    public static class AnonymousClass1 extends Handler {
        AnonymousClass1(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 784) {
                switch (i) {
                    case 768:
                        UMWorkDispatch.handleEvent(message);
                        return;
                    case 769:
                    default:
                        return;
                    case 770:
                        UMWorkDispatch.delayProcess();
                        return;
                    case 771:
                        UMWorkDispatch.handleEvent(message);
                        return;
                }
            } else {
                UMWorkDispatch.handleQuit();
            }
        }
    }

    public static synchronized boolean eventHasExist(int i) {
        synchronized (UMWorkDispatch.class) {
            if (mTaskHandler == null) {
                return false;
            }
            return mTaskHandler.hasMessages(i);
        }
    }

    public static synchronized boolean eventHasExist() {
        synchronized (UMWorkDispatch.class) {
            if (mTaskHandler == null) {
                return false;
            }
            return mTaskHandler.hasMessages(771);
        }
    }

    public static synchronized void removeEvent() {
        synchronized (UMWorkDispatch.class) {
            if (mTaskHandler != null) {
                mTaskHandler.removeMessages(771);
            }
        }
    }

    /* access modifiers changed from: private */
    public static void handleEvent(Message message) {
        int i = message.arg1;
        Object obj = message.obj;
        UMLogDataProtocol callbackFromModuleName = UMModuleRegister.getCallbackFromModuleName(UMModuleRegister.eventType2ModuleName(i));
        if (callbackFromModuleName != null) {
            ULog.d("--->>> dispatch:handleEvent: call back workEvent with msg type [ 0x" + Integer.toHexString(i) + "]");
            callbackFromModuleName.workEvent(obj, i);
        }
    }

    private static void teardown() {
        if (mNetTask != null) {
            mNetTask = null;
        }
        if (mTaskHandler != null) {
            mTaskHandler = null;
        }
        if (mSender != null) {
            mSender = null;
        }
    }

    /* access modifiers changed from: private */
    public static void handleQuit() {
        if (mSender != null && mNetTask != null) {
            a.c();
            ULog.d("--->>> handleQuit: Quit dispatch thread.");
            mNetTask.quit();
            teardown();
        }
    }

    public static void Quit() {
        Handler handler = mTaskHandler;
        if (handler != null) {
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = 784;
            mTaskHandler.sendMessage(obtainMessage);
        }
    }
}
