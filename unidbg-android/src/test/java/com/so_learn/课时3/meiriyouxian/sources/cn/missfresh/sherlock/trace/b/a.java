package cn.missfresh.sherlock.trace.b;

import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import cn.missfresh.sherlock.tool.j;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/* compiled from: ActivityThreadHacker */
public class a {
    public static AppMethodBeat.d a = new AppMethodBeat.d();
    public static int b = -100;
    private static boolean c;
    private static long d;
    private static long e;

    /* compiled from: ActivityThreadHacker */
    /* access modifiers changed from: private */
    /* renamed from: cn.missfresh.sherlock.trace.b.a$a  reason: collision with other inner class name */
    public static final class C0047a implements Handler.Callback {
        private static boolean a;
        private final Handler.Callback b;
        private Method c = null;

        C0047a(Handler.Callback callback) {
            this.b = callback;
        }

        private boolean a(Message message) {
            if (Build.VERSION.SDK_INT <= 27) {
                return message.what == 100;
            }
            if (message.what == 159 && message.obj != null) {
                try {
                    if (this.c == null) {
                        this.c = Class.forName("android.app.servertransaction.ClientTransaction").getDeclaredMethod("getCallbacks", new Class[0]);
                        this.c.setAccessible(true);
                    }
                    List list = (List) this.c.invoke(message.obj, new Object[0]);
                    if (!list.isEmpty()) {
                        return list.get(0).getClass().getName().endsWith(".LaunchActivityItem");
                    }
                } catch (Exception e) {
                    j.d("ActivityThreadHacker", "[isLaunchActivity] %s", e);
                }
            }
            return message.what == 100;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(Message message) {
            int i;
            boolean a2 = a(message);
            if (a2) {
                a.a(SystemClock.uptimeMillis());
            }
            if (!a && (a2 || (i = message.what) == 114 || i == 113)) {
                long unused = a.e = SystemClock.uptimeMillis();
                a.b = message.what;
                a = true;
            }
            Handler.Callback callback = this.b;
            if (callback == null || !callback.handleMessage(message)) {
                return false;
            }
            return true;
        }
    }

    static /* synthetic */ long a(long j) {
        return j;
    }

    public static void a() {
        if (!c) {
            c = true;
            try {
                d = SystemClock.uptimeMillis();
                a = AppMethodBeat.getInstance().maskIndex("ApplicationCreateBeginMethodIndex");
                Class<?> cls = Class.forName("android.app.ActivityThread");
                Field declaredField = cls.getDeclaredField("sCurrentActivityThread");
                declaredField.setAccessible(true);
                Object obj = declaredField.get(cls);
                Field declaredField2 = cls.getDeclaredField("mH");
                declaredField2.setAccessible(true);
                Object obj2 = declaredField2.get(obj);
                Field declaredField3 = obj2.getClass().getSuperclass().getDeclaredField("mCallback");
                declaredField3.setAccessible(true);
                declaredField3.set(obj2, new C0047a((Handler.Callback) declaredField3.get(obj2)));
                j.a("ActivityThreadHacker", String.format("hook system handler completed. start:%s SDK_INT:%s", Long.valueOf(d), Integer.valueOf(Build.VERSION.SDK_INT)));
            } catch (Exception e2) {
                j.e("ActivityThreadHacker", String.format("hook system handler err! %s", e2.getCause().toString()));
            }
        }
    }

    public static long b() {
        return e - d;
    }

    public static long c() {
        return d;
    }

    public static long d() {
        return e;
    }
}
