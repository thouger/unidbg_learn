package cn.missfresh.utils.a;

import android.text.format.DateFormat;
import android.util.Log;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.umeng.message.proguard.l;

/* compiled from: MFLogUtils */
public class d {
    public static boolean a;
    private static boolean b;
    private static boolean c = false;
    private static a d;
    private static char e = 'v';
    private static StringBuffer f = new StringBuffer();

    static {
        AppMethodBeat.i(14055, false);
        AppMethodBeat.o(14055);
    }

    private d() {
    }

    public static void a(boolean z, boolean z2, a aVar, boolean z3) {
        a = z;
        b = z2;
        c = z3;
        d = aVar;
    }

    public static void a(String str, Object obj) {
        AppMethodBeat.i(14013, false);
        a(str, obj, null);
        AppMethodBeat.o(14013);
    }

    public static void a(String str, Object obj, Exception exc) {
        AppMethodBeat.i(14014, false);
        a(str, obj, exc, 'w');
        AppMethodBeat.o(14014);
    }

    public static void b(String str, Object obj) {
        AppMethodBeat.i(14016, false);
        b(str, obj, null);
        AppMethodBeat.o(14016);
    }

    public static void b(String str, Object obj, Exception exc) {
        AppMethodBeat.i(14018, false);
        a(str, obj, exc, 'e');
        AppMethodBeat.o(14018);
    }

    public static void a(String str, Exception exc) {
        AppMethodBeat.i(14020, false);
        b(str, "", exc);
        AppMethodBeat.o(14020);
    }

    public static void c(String str, Object obj) {
        AppMethodBeat.i(14022, false);
        c(str, obj, null);
        AppMethodBeat.o(14022);
    }

    public static void c(String str, Object obj, Exception exc) {
        AppMethodBeat.i(14024, false);
        a(str, obj, exc, (char) DateFormat.DATE);
        AppMethodBeat.o(14024);
    }

    public static void d(String str, Object obj) {
        AppMethodBeat.i(14025, false);
        d(str, obj, null);
        AppMethodBeat.o(14025);
    }

    public static void d(String str, Object obj, Exception exc) {
        AppMethodBeat.i(14026, false);
        a(str, obj, exc, 'i');
        AppMethodBeat.o(14026);
    }

    public static void a(Object obj) {
        AppMethodBeat.i(14033, false);
        a aVar = d;
        if (aVar != null) {
            aVar.a(obj);
        }
        AppMethodBeat.o(14033);
    }

    @Deprecated
    public static void b(Object obj) {
        AppMethodBeat.i(14036, false);
        a aVar = d;
        if (aVar != null) {
            aVar.b(obj);
        }
        AppMethodBeat.o(14036);
    }

    private static void a(String str, Object obj, Exception exc, char c2) {
        a aVar;
        AppMethodBeat.i(14042, false);
        if (obj == null) {
            AppMethodBeat.o(14042);
            return;
        }
        if (c && (aVar = d) != null) {
            aVar.a(str, String.valueOf(obj));
        }
        if (!a) {
            AppMethodBeat.o(14042);
            return;
        }
        int length = b(obj.toString(), exc).length();
        int length2 = 4000 - (a() != null ? a().length() : 0);
        int length3 = obj.toString().length();
        if (length < length2) {
            a(str, obj.toString(), exc, c2);
        } else {
            for (int i = 0; i < length; i += length2) {
                int min = Math.min(length - i, length2) + i;
                if (length3 > min) {
                    a(str, obj.toString().substring(i, min), exc, c2);
                }
            }
        }
        AppMethodBeat.o(14042);
    }

    private static String b(String str, Exception exc) {
        AppMethodBeat.i(14045, false);
        f.setLength(0);
        f.append(str);
        if (exc != null) {
            StringBuffer stringBuffer = f;
            stringBuffer.append("\r\n");
            stringBuffer.append(exc.toString());
            StackTraceElement[] stackTrace = exc.getStackTrace();
            if (stackTrace != null && stackTrace.length > 0) {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    f.append("\n\tat " + stackTraceElement);
                }
            }
        }
        String stringBuffer2 = f.toString();
        AppMethodBeat.o(14045);
        return stringBuffer2;
    }

    private static void a(String str, String str2, Exception exc, char c2) {
        String str3;
        char c3;
        char c4;
        char c5;
        char c6;
        AppMethodBeat.i(14047, false);
        if ('e' == c2 && ('e' == (c6 = e) || 'v' == c6)) {
            Log.e(str, a(str2), exc);
        } else if ('w' == c2 && ('w' == (c5 = e) || 'v' == c5)) {
            Log.w(str, a(str2), exc);
        } else if ('d' == c2 && ('d' == (c4 = e) || 'v' == c4)) {
            Log.d(str, a(str2), exc);
        } else if ('i' == c2 && ('i' == (c3 = e) || 'v' == c3)) {
            Log.i(str, a(str2), exc);
        } else {
            Log.v(str, a(str2), exc);
        }
        if (b) {
            if (exc == null) {
                str3 = "";
            } else {
                str3 = "\n" + Log.getStackTraceString(exc);
            }
            c.a(String.valueOf(c2), str, str2 + str3);
        }
        AppMethodBeat.o(14047);
    }

    private static String a(String str) {
        AppMethodBeat.i(14049, false);
        String a2 = a();
        if (a2 != null) {
            str = a2 + " - " + str;
        }
        AppMethodBeat.o(14049);
        return str;
    }

    private static String a() {
        AppMethodBeat.i(14051, false);
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace == null) {
            AppMethodBeat.o(14051);
            return "[FunctionName]";
        }
        for (StackTraceElement stackTraceElement : stackTrace) {
            if (stackTraceElement != null && !stackTraceElement.isNativeMethod()) {
                String className = stackTraceElement.getClassName();
                if (!className.contains(Thread.class.getName()) && !className.contains(d.class.getSimpleName())) {
                    String str = "[" + Thread.currentThread().getName() + l.s + Thread.currentThread().getId() + "): " + className + "." + stackTraceElement.getMethodName() + "():" + stackTraceElement.getLineNumber() + "]";
                    AppMethodBeat.o(14051);
                    return str;
                }
            }
        }
        AppMethodBeat.o(14051);
        return "[FunctionName]";
    }

    public static boolean a(String str, int i) {
        AppMethodBeat.i(14053, false);
        boolean isLoggable = Log.isLoggable(str, i);
        AppMethodBeat.o(14053);
        return isLoggable;
    }
}
