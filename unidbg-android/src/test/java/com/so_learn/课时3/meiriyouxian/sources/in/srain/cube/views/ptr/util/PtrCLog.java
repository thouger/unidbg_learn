package in.srain.cube.views.ptr.util;

public class PtrCLog {
    public static final int LEVEL_DEBUG = 1;
    public static final int LEVEL_ERROR = 4;
    public static final int LEVEL_FATAL = 5;
    public static final int LEVEL_INFO = 2;
    public static final int LEVEL_VERBOSE = 0;
    public static final int LEVEL_WARNING = 3;
    private static int sLevel;

    public static void setLogLevel(int i) {
        sLevel = i;
    }

    public static void v(String str, String str2) {
        if (sLevel > 0) {
        }
    }

    public static void v(String str, String str2, Throwable th) {
        if (sLevel > 0) {
        }
    }

    public static void v(String str, String str2, Object... objArr) {
        if (sLevel <= 0 && objArr.length > 0) {
            String.format(str2, objArr);
        }
    }

    public static void d(String str, String str2) {
        if (sLevel > 1) {
        }
    }

    public static void d(String str, String str2, Object... objArr) {
        if (sLevel <= 1 && objArr.length > 0) {
            String.format(str2, objArr);
        }
    }

    public static void d(String str, String str2, Throwable th) {
        if (sLevel > 1) {
        }
    }

    public static void i(String str, String str2) {
        if (sLevel > 2) {
        }
    }

    public static void i(String str, String str2, Object... objArr) {
        if (sLevel <= 2 && objArr.length > 0) {
            String.format(str2, objArr);
        }
    }

    public static void i(String str, String str2, Throwable th) {
        if (sLevel > 2) {
        }
    }

    public static void w(String str, String str2) {
        if (sLevel > 3) {
        }
    }

    public static void w(String str, String str2, Object... objArr) {
        if (sLevel <= 3 && objArr.length > 0) {
            String.format(str2, objArr);
        }
    }

    public static void w(String str, String str2, Throwable th) {
        if (sLevel > 3) {
        }
    }

    public static void e(String str, String str2) {
        if (sLevel > 4) {
        }
    }

    public static void e(String str, String str2, Object... objArr) {
        if (sLevel <= 4 && objArr.length > 0) {
            String.format(str2, objArr);
        }
    }

    public static void e(String str, String str2, Throwable th) {
        if (sLevel > 4) {
        }
    }

    public static void f(String str, String str2) {
        if (sLevel > 5) {
        }
    }

    public static void f(String str, String str2, Object... objArr) {
        if (sLevel <= 5 && objArr.length > 0) {
            String.format(str2, objArr);
        }
    }

    public static void f(String str, String str2, Throwable th) {
        if (sLevel > 5) {
        }
    }
}
