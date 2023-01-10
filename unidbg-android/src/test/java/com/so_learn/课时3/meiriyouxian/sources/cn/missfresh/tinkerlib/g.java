package cn.missfresh.tinkerlib;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerLoadResult;
import com.tencent.tinker.lib.util.TinkerLog;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

/* compiled from: TinkerUtils */
public class g {
    private static boolean a;

    public static boolean a() {
        return false;
    }

    public static boolean b() {
        return a;
    }

    public static void a(boolean z) {
        a = z;
    }

    public static int a(long j, int i) {
        AppMethodBeat.i(12940, false);
        if (a()) {
            AppMethodBeat.o(12940);
            return -20;
        } else if (i < 45) {
            AppMethodBeat.o(12940);
            return -22;
        } else if (!a(j)) {
            AppMethodBeat.o(12940);
            return -21;
        } else {
            AppMethodBeat.o(12940);
            return 0;
        }
    }

    public static boolean a(Throwable th) {
        AppMethodBeat.i(12942, false);
        for (StackTraceElement stackTraceElement : th.getStackTrace()) {
            String className = stackTraceElement.getClassName();
            if (className != null && className.contains("de.robv.android.xposed.XposedBridge")) {
                AppMethodBeat.o(12942);
                return true;
            }
        }
        AppMethodBeat.o(12942);
        return false;
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0032  */
    @java.lang.Deprecated
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static boolean a(long r10) {
        /*
            r0 = 0
            r1 = 12945(0x3291, float:1.814E-41)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r0)
            r2 = 0
            java.io.File r4 = android.os.Environment.getDataDirectory()     // Catch:{ Exception -> 0x002c }
            android.os.StatFs r5 = new android.os.StatFs     // Catch:{ Exception -> 0x002c }
            java.lang.String r4 = r4.getPath()     // Catch:{ Exception -> 0x002c }
            r5.<init>(r4)     // Catch:{ Exception -> 0x002c }
            int r4 = r5.getAvailableBlocks()     // Catch:{ Exception -> 0x002c }
            long r6 = (long) r4     // Catch:{ Exception -> 0x002c }
            int r4 = r5.getBlockSize()     // Catch:{ Exception -> 0x002c }
            long r8 = (long) r4
            long r6 = r6 * r8
            int r4 = r5.getBlockCount()     // Catch:{ Exception -> 0x002d }
            long r8 = (long) r4     // Catch:{ Exception -> 0x002d }
            int r4 = r5.getBlockSize()     // Catch:{ Exception -> 0x002d }
            long r4 = (long) r4
            long r4 = r4 * r8
            goto L_0x002e
        L_0x002c:
            r6 = r2
        L_0x002d:
            r4 = r2
        L_0x002e:
            int r2 = (r4 > r2 ? 1 : (r4 == r2 ? 0 : -1))
            if (r2 == 0) goto L_0x003b
            int r10 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1))
            if (r10 <= 0) goto L_0x003b
            r10 = 1
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r10
        L_0x003b:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.tinkerlib.g.a(long):boolean");
    }

    public static String b(Throwable th) {
        int i = 12947;
        AppMethodBeat.i(12947, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(byteArrayOutputStream);
        while (th.getCause() != null) {
            try {
                th = th.getCause();
            } finally {
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                AppMethodBeat.o(i);
            }
        }
        th.printStackTrace(printStream);
        return a(byteArrayOutputStream.toString());
    }

    private static String a(String str) {
        boolean z;
        AppMethodBeat.i(12948, false);
        if (str == null) {
            AppMethodBeat.o(12948);
            return null;
        }
        char[] charArray = str.toCharArray();
        if (charArray == null) {
            AppMethodBeat.o(12948);
            return null;
        }
        int i = 0;
        while (true) {
            if (i >= charArray.length) {
                z = false;
                break;
            } else if (charArray[i] > '\u007f') {
                charArray[i] = 0;
                z = true;
                break;
            } else {
                i++;
            }
        }
        if (z) {
            String str2 = new String(charArray, 0, i);
            AppMethodBeat.o(12948);
            return str2;
        }
        AppMethodBeat.o(12948);
        return str;
    }

    /* compiled from: TinkerUtils */
    public static class a {

        /* compiled from: TinkerUtils */
        /* renamed from: cn.missfresh.tinkerlib.g$a$a  reason: collision with other inner class name */
        public interface AbstractC0055a {
            void a();
        }

        public a(Context context, AbstractC0055a aVar) {
            AppMethodBeat.i(12937, false);
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
            context.registerReceiver(new AnonymousClass1(aVar), intentFilter);
            AppMethodBeat.o(12937);
        }

        /* compiled from: TinkerUtils */
        /* renamed from: cn.missfresh.tinkerlib.g$a$1  reason: invalid class name */
        class AnonymousClass1 extends BroadcastReceiver {
            final /* synthetic */ AbstractC0055a a;

            AnonymousClass1(AbstractC0055a aVar) {
                this.a = aVar;
            }

            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                AbstractC0055a aVar;
                AppMethodBeat.i(12931, false);
                String action = intent == null ? "" : intent.getAction();
                TinkerLog.i(ContentValues.TAG, "ScreenReceiver action [%s] ", action);
                if (Intent.ACTION_SCREEN_OFF.equals(action) && (aVar = this.a) != null) {
                    aVar.a();
                }
                context.unregisterReceiver(this);
                AppMethodBeat.o(12931);
            }
        }
    }

    public static String a(Context context, int i) {
        AppMethodBeat.i(12949, false);
        if (context == null) {
            AppMethodBeat.o(12949);
            return null;
        }
        List<ActivityManager.RunningAppProcessInfo> runningAppProcesses = ((ActivityManager) context.getSystemService("activity")).getRunningAppProcesses();
        if (runningAppProcesses == null) {
            AppMethodBeat.o(12949);
            return null;
        }
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : runningAppProcesses) {
            if (runningAppProcessInfo.pid == i) {
                String str = runningAppProcessInfo.processName;
                AppMethodBeat.o(12949);
                return str;
            }
        }
        AppMethodBeat.o(12949);
        return null;
    }

    public static String a(Context context, String str) {
        TinkerLoadResult tinkerLoadResultIfPresent;
        AppMethodBeat.i(12950, false);
        Tinker with = Tinker.with(context);
        if (with == null || (tinkerLoadResultIfPresent = with.getTinkerLoadResultIfPresent()) == null || tinkerLoadResultIfPresent.packageConfig == null) {
            AppMethodBeat.o(12950);
            return "";
        }
        String str2 = tinkerLoadResultIfPresent.packageConfig.get(str);
        if (str2 == null) {
            str2 = "";
        }
        AppMethodBeat.o(12950);
        return str2;
    }
}
