package com.umeng.commonsdk.stateless;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.FileObserver;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Base64;
import com.umeng.analytics.pro.ai;
import com.umeng.commonsdk.debug.UMRTLog;
import com.umeng.commonsdk.internal.crash.UMCrashManager;
import com.umeng.commonsdk.statistics.UMServerURL;
import com.umeng.commonsdk.statistics.common.DeviceConfig;
import com.umeng.commonsdk.statistics.common.ULog;
import java.io.File;
import java.util.LinkedList;

/* compiled from: UMSLNetWorkSender */
public class b {
    public static final int a = 273;
    private static Context b = null;
    private static HandlerThread c = null;
    private static Handler d = null;
    private static Object e = null;
    private static final int f = 274;
    private static final int g = 275;
    private static final int h = 512;
    private static a i;
    private static IntentFilter j;
    private static boolean k = false;
    private static LinkedList<String> l = new LinkedList<>();
    private static BroadcastReceiver m = new AnonymousClass1();

    /* access modifiers changed from: private */
    public static void r() {
    }

    static {
        e = new Object();
    }

    /* compiled from: UMSLNetWorkSender */
    /* renamed from: com.umeng.commonsdk.stateless.b$1  reason: invalid class name */
    static class AnonymousClass1 extends BroadcastReceiver {
        AnonymousClass1() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager connectivityManager;
            if (context != null && intent != null) {
                try {
                    if (intent.getAction() != null && intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
                        Context unused = b.b = context.getApplicationContext();
                        if (b.b != null && (connectivityManager = (ConnectivityManager) b.b.getSystemService(Context.CONNECTIVITY_SERVICE)) != null) {
                            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                            if (activeNetworkInfo == null || !activeNetworkInfo.isAvailable()) {
                                UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>>\u7f51\u7edc\u65ad\u8fde\uff1a 2\u53f7\u6570\u636e\u4ed3");
                                boolean unused2 = b.k = false;
                                return;
                            }
                            boolean unused3 = b.k = true;
                            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>>\u7f51\u7edc\u53ef\u7528\uff1a \u89e6\u53d12\u53f7\u6570\u636e\u4ed3\u4fe1\u5c01\u6d88\u8d39\u52a8\u4f5c\u3002");
                            b.b(274);
                        }
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(context, th);
                }
            }
        }
    }

    /* compiled from: UMSLNetWorkSender */
    /* access modifiers changed from: package-private */
    public static class a extends FileObserver {
        public a(String str) {
            super(str);
        }

        @Override // android.os.FileObserver
        public void onEvent(int i, String str) {
            if ((i & 8) == 8) {
                UMRTLog.i(UMRTLog.RTLOG_TAG, "--->>> envelope file created >>> " + str);
                b.a(274);
            }
        }
    }

    public static boolean a() {
        synchronized (e) {
            if (i != null) {
                return true;
            }
            return false;
        }
    }

    public b(Context context) {
        synchronized (e) {
            if (context != null) {
                try {
                    b = context.getApplicationContext();
                    if (b != null && c == null) {
                        c = new HandlerThread("SL-NetWorkSender");
                        c.start();
                        if (i == null) {
                            String str = b.getFilesDir() + File.separator + a.f;
                            File file = new File(str);
                            if (!file.exists()) {
                                UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> 2\u53f7\u6570\u636e\u4ed3\u76ee\u5f55\u4e0d\u5b58\u5728\uff0c\u521b\u5efa\u4e4b\u3002");
                                file.mkdir();
                            }
                            i = new a(str);
                            i.startWatching();
                            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> 2\u53f7\u6570\u636e\u4ed3File Monitor\u542f\u52a8.");
                        }
                        if (d == null) {
                            d = new AnonymousClass2(c.getLooper());
                        }
                        if (DeviceConfig.checkPermission(b, "android.permission.ACCESS_NETWORK_STATE")) {
                            ULog.i("walle", "[stateless] begin register receiver");
                            if (j == null) {
                                j = new IntentFilter();
                                j.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
                                if (m != null) {
                                    UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> 2\u53f7\u6570\u636e\u4ed3\uff1a\u6ce8\u518c\u7f51\u7edc\u72b6\u6001\u76d1\u542c\u5668\u3002");
                                    b.registerReceiver(m, j);
                                }
                            }
                        }
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(context, th);
                }
            }
        }
    }

    /* compiled from: UMSLNetWorkSender */
    /* renamed from: com.umeng.commonsdk.stateless.b$2  reason: invalid class name */
    class AnonymousClass2 extends Handler {
        AnonymousClass2(Looper looper) {
            super(looper);
        }

        /* JADX INFO: Can't fix incorrect switch cases order, some code will duplicate */
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i != 512) {
                switch (i) {
                    case 273:
                        b.m();
                        return;
                    case 274:
                        b.o();
                        return;
                    case 275:
                        b.q();
                        break;
                    default:
                        return;
                }
            }
            b.r();
        }
    }

    public static void a(int i2) {
        Handler handler;
        if (k && (handler = d) != null) {
            Message obtainMessage = handler.obtainMessage();
            obtainMessage.what = i2;
            d.sendMessage(obtainMessage);
        }
    }

    public static void b(int i2) {
        try {
            if (k && d != null && !d.hasMessages(i2)) {
                Message obtainMessage = d.obtainMessage();
                obtainMessage.what = i2;
                d.sendMessage(obtainMessage);
            }
        } catch (Throwable th) {
            UMCrashManager.reportCrash(b, th);
        }
    }

    private static void j() {
        File[] c2 = d.c(b);
        if (c2 != null) {
            if (l.size() > 0) {
                l.clear();
            }
            for (File file : c2) {
                l.add(file.getAbsolutePath());
            }
        }
    }

    private static String k() {
        try {
            String peek = l.peek();
            if (peek == null) {
                return peek;
            }
            try {
                l.removeFirst();
                return peek;
            } catch (Throwable unused) {
                return peek;
            }
        } catch (Throwable unused2) {
            return null;
        }
    }

    private static void l() {
        String str;
        if (l.size() <= 0) {
            UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> todoList\u65e0\u5185\u5bb9\uff0c\u65e0\u9700\u5904\u7406\u3002");
            return;
        }
        do {
            if (Build.VERSION.SDK_INT >= 9) {
                str = l.pollFirst();
            } else {
                str = k();
            }
            if (!TextUtils.isEmpty(str)) {
                File file = new File(str);
                if (!file.exists()) {
                    UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> \u4fe1\u5c01\u6587\u4ef6\u4e0d\u5b58\u5728\uff0c\u5904\u7406\u4e0b\u4e00\u4e2a\u6587\u4ef6\u3002");
                    continue;
                } else {
                    c cVar = new c(b);
                    byte[] bArr = null;
                    try {
                        bArr = d.a(str);
                    } catch (Exception unused) {
                    }
                    String name = file.getName();
                    String substring = !TextUtils.isEmpty(name) ? name.substring(0, 1) : ai.aE;
                    String d2 = d.d(name);
                    String str2 = a.j;
                    String c2 = d.c(d2);
                    if (com.umeng.commonsdk.vchannel.a.c.equalsIgnoreCase(c2)) {
                        str2 = com.umeng.commonsdk.vchannel.a.a;
                    }
                    if (cVar.a(bArr, c2, str2, substring) && !file.delete()) {
                        file.delete();
                        continue;
                    }
                }
            }
        } while (str != null);
        l.clear();
    }

    /* access modifiers changed from: private */
    public static void m() {
        File a2;
        if (k && b != null) {
            do {
                try {
                    a2 = d.a(b);
                    if (!(a2 == null || a2.getParentFile() == null || TextUtils.isEmpty(a2.getParentFile().getName()))) {
                        c cVar = new c(b);
                        String str = new String(Base64.decode(a2.getParentFile().getName(), 0));
                        if (!com.umeng.commonsdk.internal.a.a.equalsIgnoreCase(str) && !com.umeng.commonsdk.internal.a.b.equalsIgnoreCase(str)) {
                            if (!com.umeng.commonsdk.internal.a.A.equalsIgnoreCase(str)) {
                                ULog.i("walle", "[stateless] handleProcessNext, pathUrl is " + str);
                                byte[] bArr = null;
                                try {
                                    bArr = d.a(a2.getAbsolutePath());
                                } catch (Exception unused) {
                                }
                                String str2 = "";
                                if (com.umeng.commonsdk.vchannel.a.c.equalsIgnoreCase(str)) {
                                    str2 = com.umeng.commonsdk.vchannel.a.a;
                                }
                                String str3 = ai.aE;
                                if (UMServerURL.PATH_SHARE.equalsIgnoreCase(str)) {
                                    str3 = ai.az;
                                }
                                if (UMServerURL.PATH_PUSH_LAUNCH.equalsIgnoreCase(str) || UMServerURL.PATH_PUSH_REGIST.equalsIgnoreCase(str) || UMServerURL.PATH_PUSH_LOG.equalsIgnoreCase(str)) {
                                    str3 = "p";
                                }
                                if (cVar.a(bArr, str, str2, str3)) {
                                    ULog.i("walle", "[stateless] Send envelope file success, delete it.");
                                    File file = new File(a2.getAbsolutePath());
                                    if (!file.delete()) {
                                        ULog.i("walle", "[stateless] Failed to delete already processed file. We try again after delete failed.");
                                        file.delete();
                                        continue;
                                    } else {
                                        continue;
                                    }
                                } else {
                                    ULog.i("walle", "[stateless] Send envelope file failed, abandon and wait next trigger!");
                                    return;
                                }
                            }
                        }
                        new File(a2.getAbsolutePath()).delete();
                        continue;
                    }
                } catch (Throwable th) {
                    UMCrashManager.reportCrash(b, th);
                }
            } while (a2 != null);
            n();
        }
    }

    private static void n() {
        try {
            File file = new File(b.getFilesDir() + File.separator + a.e);
            if (file.exists() && file.isDirectory()) {
                UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>> 2\u53f7\u6570\u636e\u4ed3\uff1a\u5220\u9664stateless\u76ee\u5f55\u3002");
                d.a(file);
            }
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: private */
    public static void o() {
        if (k && b != null) {
            j();
            l();
            c();
        }
    }

    private static void p() {
        try {
            File file = new File(b.getFilesDir() + File.separator + a.e);
            if (file.exists() && file.isDirectory()) {
                UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>>2\u53f7\u6570\u636e\u4ed3\uff1a\u68c0\u6d4b\u5230stateless\u76ee\u5f55\u3002");
                b(273);
            }
        } catch (Throwable unused) {
        }
    }

    /* access modifiers changed from: private */
    public static void q() {
        p();
    }

    public static void b() {
        UMRTLog.e(UMRTLog.RTLOG_TAG, "--->>>\u4fe1\u5c01\u6784\u5efa\u6210\u529f\uff1a \u89e6\u53d12\u53f7\u6570\u636e\u4ed3\u4fe1\u5c01\u6d88\u8d39\u52a8\u4f5c\u3002");
        b(274);
    }

    public static void c() {
        b(275);
    }

    public static void d() {
        b(512);
    }
}
