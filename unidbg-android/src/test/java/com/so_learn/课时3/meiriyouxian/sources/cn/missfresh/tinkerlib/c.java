package cn.missfresh.tinkerlib;

import android.util.Log;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.tinker.lib.util.TinkerLog;

/* compiled from: TinkerLogUtil */
public class c implements TinkerLog.TinkerLogImp {
    private static int a;
    private static volatile c b;

    private c() {
    }

    public static c a() {
        AppMethodBeat.i(12829, false);
        if (b == null) {
            synchronized (c.class) {
                try {
                    if (b == null) {
                        b = new c();
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(12829);
                    throw th;
                }
            }
        }
        c cVar = b;
        AppMethodBeat.o(12829);
        return cVar;
    }

    @Override // com.tencent.tinker.lib.util.TinkerLog.TinkerLogImp
    public void v(String str, String str2, Object... objArr) {
        AppMethodBeat.i(12833, false);
        if (a <= 0) {
            if (objArr != null) {
                str2 = String.format(str2, objArr);
            }
            Log.v(str, str2);
        }
        AppMethodBeat.o(12833);
    }

    @Override // com.tencent.tinker.lib.util.TinkerLog.TinkerLogImp
    public void i(String str, String str2, Object... objArr) {
        AppMethodBeat.i(12834, false);
        if (a <= 2) {
            if (objArr != null) {
                str2 = String.format(str2, objArr);
            }
            Log.i(str, str2);
        }
        AppMethodBeat.o(12834);
    }

    @Override // com.tencent.tinker.lib.util.TinkerLog.TinkerLogImp
    public void w(String str, String str2, Object... objArr) {
        AppMethodBeat.i(12835, false);
        if (a <= 3) {
            if (objArr != null) {
                str2 = String.format(str2, objArr);
            }
            Log.w(str, str2);
        }
        AppMethodBeat.o(12835);
    }

    @Override // com.tencent.tinker.lib.util.TinkerLog.TinkerLogImp
    public void d(String str, String str2, Object... objArr) {
        AppMethodBeat.i(12838, false);
        if (a <= 1) {
            if (objArr != null) {
                str2 = String.format(str2, objArr);
            }
            Log.d(str, str2);
        }
        AppMethodBeat.o(12838);
    }

    @Override // com.tencent.tinker.lib.util.TinkerLog.TinkerLogImp
    public void e(String str, String str2, Object... objArr) {
        AppMethodBeat.i(12840, false);
        if (a <= 4) {
            if (objArr != null) {
                str2 = String.format(str2, objArr);
            }
            Log.e(str, str2);
        }
        AppMethodBeat.o(12840);
    }

    @Override // com.tencent.tinker.lib.util.TinkerLog.TinkerLogImp
    public void printErrStackTrace(String str, Throwable th, String str2, Object... objArr) {
        AppMethodBeat.i(12841, false);
        if (objArr != null) {
            str2 = String.format(str2, objArr);
        }
        if (str2 == null) {
            str2 = "";
        }
        Log.e(str, str2 + "  " + Log.getStackTraceString(th));
        AppMethodBeat.o(12841);
    }
}
