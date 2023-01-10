package cn.com.chinatelecom.account.api;

import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public final class Helper {
    static {
        AppMethodBeat.i(7886, false);
        System.loadLibrary("CtaApiLib");
        AppMethodBeat.o(7886);
    }

    public static native String cemppmul();

    public static native String cepahsul();

    public static native String dnepah(Context context, String str, String str2, String str3, long j, String str4);

    public static native String dnepmo(Context context, String str, String str2, String str3, long j, String str4);

    public static native byte[] dnepmret(byte[] bArr, String str);

    public static native byte[] dneulret(byte[] bArr);

    public static native String eneulret(String str);

    public static native String gscret(Context context, String str);

    public static native String guulam(Context context, String str);

    public static native String sgwret(String str);

    public static native String testEncrypt(String str, String str2);
}
