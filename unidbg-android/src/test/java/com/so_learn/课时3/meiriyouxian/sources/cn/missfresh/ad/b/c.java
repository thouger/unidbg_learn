package cn.missfresh.ad.b;

import android.text.TextUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.imsdk.BaseConstants;

/* compiled from: ValidCheckOutHelper */
public class c {
    public static void a(Object obj, String str) {
        AppMethodBeat.i(BaseConstants.ERR_LOGIN_OPENMSG_RSP_PARSE_FAILED, false);
        if (obj == null) {
            b(str);
        }
        AppMethodBeat.o(BaseConstants.ERR_LOGIN_OPENMSG_RSP_PARSE_FAILED);
    }

    public static void a(String str, String str2) {
        AppMethodBeat.i(BaseConstants.ERR_LOGIN_TLS_DECRYPT_FAILED, false);
        if (TextUtils.isEmpty(str)) {
            a(str2);
        }
        AppMethodBeat.o(BaseConstants.ERR_LOGIN_TLS_DECRYPT_FAILED);
    }

    private static void b(String str) {
        AppMethodBeat.i(BaseConstants.ERR_REVOKE_TIME_LIMIT_EXCEED, false);
        IllegalArgumentException illegalArgumentException = new IllegalArgumentException(str);
        AppMethodBeat.o(BaseConstants.ERR_REVOKE_TIME_LIMIT_EXCEED);
        throw illegalArgumentException;
    }

    public static void a(String str) {
        AppMethodBeat.i(BaseConstants.ERR_AUTOLOGIN_NEED_USERSIG, false);
        NullPointerException nullPointerException = new NullPointerException(str);
        AppMethodBeat.o(BaseConstants.ERR_AUTOLOGIN_NEED_USERSIG);
        throw nullPointerException;
    }
}
