package cn.missfresh.ad.b;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.security.keystore.KeyProperties;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.imsdk.BaseConstants;
import java.security.MessageDigest;

/* compiled from: MFADUtils */
public class b {
    private static int a;
    private static int b;

    public static String a(String str) {
        AppMethodBeat.i(6193, false);
        try {
            MessageDigest instance = MessageDigest.getInstance(KeyProperties.DIGEST_MD5);
            char[] charArray = str.toCharArray();
            byte[] bArr = new byte[charArray.length];
            for (int i = 0; i < charArray.length; i++) {
                bArr[i] = (byte) charArray[i];
            }
            byte[] digest = instance.digest(bArr);
            StringBuffer stringBuffer = new StringBuffer();
            for (byte b2 : digest) {
                int i2 = b2 & 255;
                if (i2 < 16) {
                    stringBuffer.append("0");
                }
                stringBuffer.append(Integer.toHexString(i2));
            }
            String stringBuffer2 = stringBuffer.toString();
            AppMethodBeat.o(6193);
            return stringBuffer2;
        } catch (Exception e) {
            a.a("MFADUtils", e.getMessage());
            AppMethodBeat.o(6193);
            return "";
        }
    }

    public static String a(Context context) {
        AppMethodBeat.i(6198, false);
        if (context.getCacheDir() != null) {
            String absolutePath = context.getCacheDir().getAbsolutePath();
            AppMethodBeat.o(6198);
            return absolutePath;
        }
        AppMethodBeat.o(6198);
        return null;
    }

    public static String b(Context context) {
        AppMethodBeat.i(6203, false);
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        if (TextUtils.isEmpty(string)) {
            string = "";
        }
        AppMethodBeat.o(6203);
        return string;
    }

    private static void e(Context context) {
        AppMethodBeat.i(BaseConstants.ERR_SERIVCE_NOT_READY, false);
        if ((b == 0 || a == 0) && (a == 0 || b == 0)) {
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (windowManager == null) {
                AppMethodBeat.o(BaseConstants.ERR_SERIVCE_NOT_READY);
                return;
            }
            DisplayMetrics displayMetrics = new DisplayMetrics();
            if (Build.VERSION.SDK_INT >= 17) {
                windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
            } else {
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            }
            a = displayMetrics.widthPixels;
            b = displayMetrics.heightPixels;
        }
        AppMethodBeat.o(BaseConstants.ERR_SERIVCE_NOT_READY);
    }

    public static int c(Context context) {
        AppMethodBeat.i(BaseConstants.ERR_LOGIN_AUTH_FAILED, false);
        int i = a;
        if (i == 0 && i == 0) {
            e(context);
        }
        int i2 = a;
        AppMethodBeat.o(BaseConstants.ERR_LOGIN_AUTH_FAILED);
        return i2;
    }

    public static int d(Context context) {
        AppMethodBeat.i(BaseConstants.ERR_REQ_FAILED, false);
        int i = b;
        if (i == 0 && i == 0) {
            e(context);
        }
        int i2 = b;
        AppMethodBeat.o(BaseConstants.ERR_REQ_FAILED);
        return i2;
    }
}
