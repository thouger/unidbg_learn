package com.umeng.commonsdk;

import android.content.Context;
import android.content.SharedPreferences;
import com.umeng.commonsdk.framework.UMEnvelopeBuild;
import com.umeng.commonsdk.utils.onMessageSendListener;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UMConfigureImpl {
    private static String a = "delayed_transmission_flag_new";
    private static CopyOnWriteArrayList<onMessageSendListener> b = null;
    private static int c = 0;
    private static boolean d = false;
    private static final int e = 1000;
    private static ScheduledExecutorService f;
    private static Context g;
    private static int h = 0;
    private static Runnable i = new AnonymousClass1();

    static /* synthetic */ int f() {
        int i2 = h;
        h = i2 + 1;
        return i2;
    }

    static {
        b = new CopyOnWriteArrayList<>();
        c = 0;
        d = false;
    }

    public static void init(Context context) {
        if (context != null) {
            g = context;
            try {
                if (c < 1) {
                    UMEnvelopeBuild.setTransmissionSendFlag(true);
                } else if (!d(context)) {
                    UMEnvelopeBuild.setTransmissionSendFlag(false);
                    c(context);
                    if (f == null) {
                        f = Executors.newScheduledThreadPool(1);
                        f.scheduleAtFixedRate(i, 0, 100, TimeUnit.MILLISECONDS);
                    }
                } else {
                    UMEnvelopeBuild.setTransmissionSendFlag(true);
                }
            } catch (Exception unused) {
            }
        }
    }

    /* renamed from: com.umeng.commonsdk.UMConfigureImpl$1  reason: invalid class name */
    static class AnonymousClass1 implements Runnable {
        AnonymousClass1() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                if (UMConfigureImpl.c == 0 || UMConfigureImpl.h >= 10) {
                    if (!UMConfigureImpl.d) {
                        boolean unused = UMConfigureImpl.d = true;
                        UMConfigureImpl.b(UMConfigureImpl.g);
                    }
                    if (UMConfigureImpl.f != null) {
                        UMConfigureImpl.f.shutdown();
                        ScheduledExecutorService unused2 = UMConfigureImpl.f = null;
                    }
                }
                UMConfigureImpl.f();
            } catch (Exception unused3) {
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void b(android.content.Context r2) {
        /*
            java.lang.Class<com.umeng.commonsdk.UMConfigureImpl> r2 = com.umeng.commonsdk.UMConfigureImpl.class
            monitor-enter(r2)
            r0 = 1
            com.umeng.commonsdk.framework.UMEnvelopeBuild.setTransmissionSendFlag(r0)     // Catch:{ Exception -> 0x002c, all -> 0x0029 }
            java.util.concurrent.CopyOnWriteArrayList<com.umeng.commonsdk.utils.onMessageSendListener> r0 = com.umeng.commonsdk.UMConfigureImpl.b     // Catch:{ Exception -> 0x002c, all -> 0x0029 }
            if (r0 == 0) goto L_0x002c
            java.util.concurrent.CopyOnWriteArrayList<com.umeng.commonsdk.utils.onMessageSendListener> r0 = com.umeng.commonsdk.UMConfigureImpl.b     // Catch:{ Exception -> 0x002c, all -> 0x0029 }
            int r0 = r0.size()     // Catch:{ Exception -> 0x002c, all -> 0x0029 }
            if (r0 <= 0) goto L_0x002c
            java.util.concurrent.CopyOnWriteArrayList<com.umeng.commonsdk.utils.onMessageSendListener> r0 = com.umeng.commonsdk.UMConfigureImpl.b     // Catch:{ Exception -> 0x002c, all -> 0x0029 }
            java.util.Iterator r0 = r0.iterator()     // Catch:{ Exception -> 0x002c, all -> 0x0029 }
        L_0x0019:
            boolean r1 = r0.hasNext()     // Catch:{ Exception -> 0x002c, all -> 0x0029 }
            if (r1 == 0) goto L_0x002c
            java.lang.Object r1 = r0.next()     // Catch:{ Exception -> 0x002c, all -> 0x0029 }
            com.umeng.commonsdk.utils.onMessageSendListener r1 = (com.umeng.commonsdk.utils.onMessageSendListener) r1     // Catch:{ Exception -> 0x002c, all -> 0x0029 }
            r1.onMessageSend()     // Catch:{ Exception -> 0x002c, all -> 0x0029 }
            goto L_0x0019
        L_0x0029:
            r0 = move-exception
            monitor-exit(r2)
            throw r0
        L_0x002c:
            monitor-exit(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.UMConfigureImpl.b(android.content.Context):void");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void registerMessageSendListener(com.umeng.commonsdk.utils.onMessageSendListener r2) {
        /*
            java.lang.Class<com.umeng.commonsdk.UMConfigureImpl> r0 = com.umeng.commonsdk.UMConfigureImpl.class
            monitor-enter(r0)
            java.util.concurrent.CopyOnWriteArrayList<com.umeng.commonsdk.utils.onMessageSendListener> r1 = com.umeng.commonsdk.UMConfigureImpl.b     // Catch:{ Exception -> 0x0037, all -> 0x0034 }
            if (r1 == 0) goto L_0x000c
            java.util.concurrent.CopyOnWriteArrayList<com.umeng.commonsdk.utils.onMessageSendListener> r1 = com.umeng.commonsdk.UMConfigureImpl.b     // Catch:{ Exception -> 0x0037, all -> 0x0034 }
            r1.add(r2)     // Catch:{ Exception -> 0x0037, all -> 0x0034 }
        L_0x000c:
            boolean r2 = com.umeng.commonsdk.framework.UMEnvelopeBuild.getTransmissionSendFlag()     // Catch:{ Exception -> 0x0037, all -> 0x0034 }
            if (r2 == 0) goto L_0x0037
            java.util.concurrent.CopyOnWriteArrayList<com.umeng.commonsdk.utils.onMessageSendListener> r2 = com.umeng.commonsdk.UMConfigureImpl.b     // Catch:{ Exception -> 0x0037, all -> 0x0034 }
            if (r2 == 0) goto L_0x0037
            java.util.concurrent.CopyOnWriteArrayList<com.umeng.commonsdk.utils.onMessageSendListener> r2 = com.umeng.commonsdk.UMConfigureImpl.b     // Catch:{ Exception -> 0x0037, all -> 0x0034 }
            int r2 = r2.size()     // Catch:{ Exception -> 0x0037, all -> 0x0034 }
            if (r2 <= 0) goto L_0x0037
            java.util.concurrent.CopyOnWriteArrayList<com.umeng.commonsdk.utils.onMessageSendListener> r2 = com.umeng.commonsdk.UMConfigureImpl.b     // Catch:{ Exception -> 0x0037, all -> 0x0034 }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ Exception -> 0x0037, all -> 0x0034 }
        L_0x0024:
            boolean r1 = r2.hasNext()     // Catch:{ Exception -> 0x0037, all -> 0x0034 }
            if (r1 == 0) goto L_0x0037
            java.lang.Object r1 = r2.next()     // Catch:{ Exception -> 0x0037, all -> 0x0034 }
            com.umeng.commonsdk.utils.onMessageSendListener r1 = (com.umeng.commonsdk.utils.onMessageSendListener) r1     // Catch:{ Exception -> 0x0037, all -> 0x0034 }
            r1.onMessageSend()     // Catch:{ Exception -> 0x0037, all -> 0x0034 }
            goto L_0x0024
        L_0x0034:
            r2 = move-exception
            monitor-exit(r0)
            throw r2
        L_0x0037:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.UMConfigureImpl.registerMessageSendListener(com.umeng.commonsdk.utils.onMessageSendListener):void");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void removeMessageSendListener(com.umeng.commonsdk.utils.onMessageSendListener r2) {
        /*
            java.lang.Class<com.umeng.commonsdk.UMConfigureImpl> r0 = com.umeng.commonsdk.UMConfigureImpl.class
            monitor-enter(r0)
            java.util.concurrent.CopyOnWriteArrayList<com.umeng.commonsdk.utils.onMessageSendListener> r1 = com.umeng.commonsdk.UMConfigureImpl.b     // Catch:{ Exception -> 0x0010, all -> 0x000d }
            if (r1 == 0) goto L_0x0010
            java.util.concurrent.CopyOnWriteArrayList<com.umeng.commonsdk.utils.onMessageSendListener> r1 = com.umeng.commonsdk.UMConfigureImpl.b     // Catch:{ Exception -> 0x0010, all -> 0x000d }
            r1.remove(r2)     // Catch:{ Exception -> 0x0010, all -> 0x000d }
            goto L_0x0010
        L_0x000d:
            r2 = move-exception
            monitor-exit(r0)
            throw r2
        L_0x0010:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.UMConfigureImpl.removeMessageSendListener(com.umeng.commonsdk.utils.onMessageSendListener):void");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void registerInterruptFlag() {
        /*
            java.lang.Class<com.umeng.commonsdk.UMConfigureImpl> r0 = com.umeng.commonsdk.UMConfigureImpl.class
            monitor-enter(r0)
            int r1 = com.umeng.commonsdk.UMConfigureImpl.c     // Catch:{ Exception -> 0x000d, all -> 0x000a }
            int r1 = r1 + 1
            com.umeng.commonsdk.UMConfigureImpl.c = r1     // Catch:{ Exception -> 0x000d, all -> 0x000a }
            goto L_0x000d
        L_0x000a:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        L_0x000d:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.UMConfigureImpl.registerInterruptFlag():void");
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void removeInterruptFlag() {
        /*
            java.lang.Class<com.umeng.commonsdk.UMConfigureImpl> r0 = com.umeng.commonsdk.UMConfigureImpl.class
            monitor-enter(r0)
            int r1 = com.umeng.commonsdk.UMConfigureImpl.c     // Catch:{ Exception -> 0x000d, all -> 0x000a }
            int r1 = r1 + -1
            com.umeng.commonsdk.UMConfigureImpl.c = r1     // Catch:{ Exception -> 0x000d, all -> 0x000a }
            goto L_0x000d
        L_0x000a:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        L_0x000d:
            monitor-exit(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.umeng.commonsdk.UMConfigureImpl.removeInterruptFlag():void");
    }

    private static void c(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(a, 0);
            if (sharedPreferences != null && sharedPreferences != null) {
                SharedPreferences.Editor edit = sharedPreferences.edit();
                edit.putBoolean(a, true);
                edit.commit();
            }
        } catch (Throwable unused) {
        }
    }

    private static boolean d(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(a, 0);
            if (sharedPreferences == null || sharedPreferences == null) {
                return false;
            }
            return sharedPreferences.getBoolean(a, false);
        } catch (Throwable unused) {
            return false;
        }
    }
}
