package cn.missfresh.sherlock.trace.core;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import androidx.fragment.app.Fragment;
import cn.missfresh.sherlock.SherlockViewClickListener;
import cn.missfresh.sherlock.e;
import cn.missfresh.sherlock.tool.Utils;
import cn.missfresh.sherlock.tool.g;
import cn.missfresh.sherlock.tool.j;
import cn.missfresh.sherlock.trace.core.a;
import com.umeng.message.proguard.l;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AppMethodBeat {
    public static final int METHOD_ID_DISPATCH = 1048574;
    private static final int METHOD_ID_MAX = 1048575;
    private static final int STATUS_DEFAULT = Integer.MAX_VALUE;
    private static final int STATUS_STARTED = 1;
    private static final int STATUS_STOPPED = -1;
    private static final String TAG = "AppMethodBeat";
    private static boolean assertIn = false;
    private static boolean isPauseUpdateTime = false;
    private static HashSet<cn.missfresh.sherlock.trace.f.a> listeners = new HashSet<>();
    private static a.b looperMonitorListener = new a();
    private static long[] sBuffer = new long[1000000];
    private static volatile long sCurrentDiffTime = SystemClock.uptimeMillis();
    private static volatile long sDiffTime = sCurrentDiffTime;
    private static Set<String> sFocusActivitySet = new HashSet();
    private static Handler sHandler = new Handler(sTimerUpdateThread.getLooper());
    private static int sIndex = 0;
    private static d sIndexRecordHead = null;
    private static AppMethodBeat sInstance = new AppMethodBeat();
    private static int sLastIndex = -1;
    private static Thread sMainThread = Looper.getMainLooper().getThread();
    private static HandlerThread sTimerUpdateThread = g.a("sherlock_time_update_thread");
    private static Runnable sUpdateDiffTimeRunnable = new b();
    private static volatile int status = Integer.MAX_VALUE;
    private static Object statusLock = new Object();
    private static Object updateTimeLock = new Object();

    static class a extends a.b {
        a() {
        }

        @Override // cn.missfresh.sherlock.trace.core.a.b
        public boolean a() {
            return AppMethodBeat.status != 1;
        }

        @Override // cn.missfresh.sherlock.trace.core.a.b
        public void b() {
            super.b();
            AppMethodBeat.dispatchBegin();
        }

        @Override // cn.missfresh.sherlock.trace.core.a.b
        public void c() {
            super.c();
            AppMethodBeat.dispatchEnd();
        }
    }

    static class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            while (true) {
                try {
                    if (AppMethodBeat.isPauseUpdateTime || AppMethodBeat.status <= -1) {
                        synchronized (AppMethodBeat.updateTimeLock) {
                            AppMethodBeat.updateTimeLock.wait();
                        }
                    } else {
                        long unused = AppMethodBeat.sCurrentDiffTime = SystemClock.uptimeMillis() - AppMethodBeat.sDiffTime;
                        SystemClock.sleep(5);
                    }
                } catch (Exception e) {
                    j.e(AppMethodBeat.TAG, "" + e.toString());
                    return;
                }
            }
        }
    }

    static class c implements Runnable {
        c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.realRelease();
        }
    }

    static {
        sHandler.postDelayed(new c(), 15000);
    }

    public static void at(Activity activity, boolean z) {
        String name = activity.getClass().getName();
        if (z) {
            if (sFocusActivitySet.add(name)) {
                synchronized (listeners) {
                    Iterator<cn.missfresh.sherlock.trace.f.a> it2 = listeners.iterator();
                    while (it2.hasNext()) {
                        it2.next().a(name);
                    }
                }
                j.a(TAG, "[at] visibleScene[%s] has %s focus!", name, "attach");
            }
        } else if (sFocusActivitySet.remove(name)) {
            j.a(TAG, "[at] visibleScene[%s] has %s focus!", name, "detach");
        }
    }

    private static void checkPileup(int i) {
        d dVar = sIndexRecordHead;
        while (dVar != null) {
            int i2 = dVar.a;
            if (i2 == i || (i2 == -1 && sLastIndex == 999999)) {
                dVar.b = false;
                j.d(TAG, String.format("[checkPileup] %s", dVar.toString()));
                dVar = dVar.d;
                sIndexRecordHead = dVar;
            } else {
                return;
            }
        }
    }

    /* access modifiers changed from: private */
    public static void dispatchBegin() {
        sCurrentDiffTime = SystemClock.uptimeMillis() - sDiffTime;
        isPauseUpdateTime = false;
        synchronized (updateTimeLock) {
            updateTimeLock.notify();
        }
    }

    /* access modifiers changed from: private */
    public static void dispatchEnd() {
        isPauseUpdateTime = true;
    }

    public static long getDiffTime() {
        return sDiffTime;
    }

    private static String getFragmentTag(Fragment fragment) {
        if (fragment == null) {
            return null;
        }
        try {
            String[] split = fragment.getClass().getName().split("\\.");
            return split[split.length - 1];
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static AppMethodBeat getInstance() {
        return sInstance;
    }

    private String getResourceId(Context context, int i) {
        try {
            return context.getResources().getResourceEntryName(i);
        } catch (Exception unused) {
            return "";
        }
    }

    public static void i(int i) {
        i(i, false);
    }

    private static void insetMethodId(int i, boolean z) {
        if (z) {
            cn.missfresh.sherlock.crash.a.a().a(i);
        }
    }

    public static boolean isRealTrace() {
        return status == 1;
    }

    private static void mergeData(int i, int i2, boolean z) {
        sBuffer[i2] = (z ? Long.MIN_VALUE : 0) | (((long) i) << 43) | (sCurrentDiffTime & 8796093022207L);
        checkPileup(i2);
        sLastIndex = i2;
    }

    public static void o(int i) {
        if (status == 1 && sBuffer != null && i < METHOD_ID_MAX) {
            j.c(TAG, "out - " + i);
            if (Thread.currentThread().getId() == sMainThread.getId()) {
                int i2 = sIndex;
                if (i2 < 1000000) {
                    mergeData(i, i2, false);
                } else {
                    sIndex = -1;
                }
                sIndex++;
            }
        }
    }

    public static void onClick(Object obj, View view) {
        if (view != null) {
            if (e.n() != null && !e.n().isEmpty()) {
                for (SherlockViewClickListener sherlockViewClickListener : e.n()) {
                    sherlockViewClickListener.callback(view);
                }
            }
            if (obj != null) {
                cn.missfresh.sherlock.crash.a a2 = cn.missfresh.sherlock.crash.a.a();
                String name = obj.getClass().getName();
                a2.a(name, "onClick(" + view.getId() + l.t);
            }
        }
    }

    public static void onHiddenChanged(Fragment fragment, boolean z) {
        if (!z) {
            cn.missfresh.sherlock.e.a.a().a(getFragmentTag(fragment));
        }
    }

    public static void onResume(Fragment fragment) {
        cn.missfresh.sherlock.e.a.a().a(getFragmentTag(fragment));
    }

    private static void realExecute() {
        j.b(TAG, String.format("[realExecute] timestamp:%s", Long.valueOf(System.currentTimeMillis())));
        sCurrentDiffTime = SystemClock.uptimeMillis() - sDiffTime;
        sHandler.removeCallbacksAndMessages(null);
        sHandler.postDelayed(sUpdateDiffTimeRunnable, 5);
        cn.missfresh.sherlock.trace.b.a.a();
        a.a(looperMonitorListener);
    }

    /* access modifiers changed from: private */
    public static void realRelease() {
        synchronized (statusLock) {
            if (status == Integer.MAX_VALUE) {
                j.b(TAG, String.format("[realRelease] timestamp:%s", Long.valueOf(System.currentTimeMillis())));
                sHandler.removeCallbacksAndMessages(null);
                sTimerUpdateThread.quit();
                sBuffer = null;
            }
        }
    }

    public static void setUserVisibleHint(Fragment fragment, boolean z) {
        if (!z) {
            cn.missfresh.sherlock.e.a.a().a(getFragmentTag(fragment));
        }
    }

    public void addListener(cn.missfresh.sherlock.trace.f.a aVar) {
        synchronized (listeners) {
            listeners.add(aVar);
        }
    }

    public long[] copyData(d dVar) {
        return copyData(dVar, new d(sIndex - 1));
    }

    public boolean isAlive() {
        return status == 1;
    }

    public d maskIndex(String str) {
        try {
            if (sIndexRecordHead == null) {
                sIndexRecordHead = new d(sIndex - 1);
                sIndexRecordHead.c = str;
                return sIndexRecordHead;
            }
            d dVar = new d(sIndex - 1);
            dVar.c = str;
            d dVar2 = sIndexRecordHead;
            d dVar3 = null;
            while (true) {
                dVar3 = dVar2;
                if (dVar3 == null) {
                    dVar3.d = dVar;
                    return dVar;
                } else if (dVar.a <= dVar3.a) {
                    if (dVar3 == null) {
                        d dVar4 = sIndexRecordHead;
                        sIndexRecordHead = dVar;
                        dVar.d = dVar4;
                    } else {
                        d dVar5 = dVar3.d;
                        if (dVar3.d != null) {
                            dVar3.d = dVar;
                        }
                        dVar.d = dVar5;
                    }
                    return dVar;
                } else {
                    dVar2 = dVar3.d;
                }
            }
        } catch (Exception e) {
            j.b(TAG, e.getMessage());
            sIndexRecordHead = new d(sIndex - 1);
            d dVar6 = sIndexRecordHead;
            dVar6.c = str;
            return dVar6;
        }
    }

    public void onStart() {
        synchronized (statusLock) {
            if (sBuffer != null) {
                j.b(TAG, String.format("[onStart] preStatus: %s", Integer.valueOf(status)));
                if (status == Integer.MAX_VALUE) {
                    realExecute();
                    status = 1;
                } else {
                    j.b(TAG, String.format("[onStart] current status:%s", Integer.valueOf(status)));
                }
            } else {
                throw new RuntimeException("AppMethodBeat sBuffer == null");
            }
        }
    }

    public void onStop() {
        synchronized (statusLock) {
            if (status != 1) {
                j.b(TAG, String.format("[onStop] %s", Utils.f()));
                status = -1;
            } else {
                j.b(TAG, String.format("[onStop] current status:%s", Integer.valueOf(status)));
            }
        }
    }

    public void removeListener(cn.missfresh.sherlock.trace.f.a aVar) {
        synchronized (listeners) {
            listeners.remove(aVar);
        }
    }

    public static final class d {
        public int a;
        public boolean b;
        public String c;
        private d d;

        public d(int i) {
            this.b = true;
            this.a = i;
        }

        public String toString() {
            return "index: " + this.a + ", isValid: " + this.b + ", source: " + this.c;
        }

        public void a() {
            this.b = false;
            try {
                d dVar = null;
                for (d dVar2 = AppMethodBeat.sIndexRecordHead; dVar2 != null; dVar2 = dVar2.d) {
                    if (dVar2 == this) {
                        if (dVar != null) {
                            dVar.d = dVar2.d;
                        } else {
                            d unused = AppMethodBeat.sIndexRecordHead = dVar2.d;
                        }
                        dVar2.d = null;
                        return;
                    }
                    dVar = dVar2;
                }
            } catch (Exception e) {
                j.b(AppMethodBeat.TAG, e.getMessage());
            }
        }

        public d() {
            this.b = true;
            this.b = false;
        }
    }

    public static void i(int i, boolean z) {
        insetMethodId(i, z);
        if (status == 1 && sBuffer != null && i < METHOD_ID_MAX) {
            j.c(TAG, "in - " + i);
            if (Thread.currentThread().getId() != sMainThread.getId()) {
                return;
            }
            if (assertIn) {
                j.e(TAG, "ERROR!!! AppMethodBeat.i Recursive calls!!!");
                return;
            }
            assertIn = true;
            int i2 = sIndex;
            if (i2 < 1000000) {
                mergeData(i, i2, true);
            } else {
                sIndex = -1;
            }
            sIndex++;
            assertIn = false;
        }
    }

    public long[] copyData(d dVar, d dVar2) {
        long[] jArr = new long[0];
        try {
            if (dVar.b && dVar2.b) {
                int max = Math.max(0, dVar.a);
                int max2 = Math.max(0, dVar2.a);
                if (max2 > max) {
                    int i = (max2 - max) + 1;
                    long[] jArr2 = new long[i];
                    System.arraycopy(sBuffer, max, jArr2, 0, i);
                    return jArr2;
                } else if (max2 >= max) {
                    return jArr;
                } else {
                    int i2 = max2 + 1;
                    long[] jArr3 = new long[((sBuffer.length - max) + i2)];
                    System.arraycopy(sBuffer, max, jArr3, 0, sBuffer.length - max);
                    System.arraycopy(sBuffer, 0, jArr3, sBuffer.length - max, i2);
                    return jArr3;
                }
            }
        } catch (OutOfMemoryError e) {
            j.e(TAG, "copy data" + e.getMessage());
        } catch (Exception e2) {
            j.e(TAG, "copy data" + e2.getMessage());
        }
        return jArr;
    }
}
