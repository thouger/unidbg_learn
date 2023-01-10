package cn.missfresh.basiclib.tool;

import android.Manifest;
import android.content.Context;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.security.keystore.KeyProperties;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import androidx.core.app.ActivityCompat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.android.internal.telephony.gsm.SmsCbConstants;
import java.security.MessageDigest;
import java.util.Locale;
import java.util.UUID;

public class SystemUtils {
    private static final byte[] a = new byte[128];
    private static String b;

    public enum NetworkType {
        NETWORK_ETHERNET,
        NETWORK_WIFI,
        NETWORK_4G,
        NETWORK_3G,
        NETWORK_2G,
        NETWORK_UNKNOWN,
        NETWORK_NO;

        public static NetworkType valueOf(String str) {
            AppMethodBeat.i(4360, false);
            NetworkType networkType = (NetworkType) Enum.valueOf(NetworkType.class, str);
            AppMethodBeat.o(4360);
            return networkType;
        }

        static {
            AppMethodBeat.i(4364, false);
            AppMethodBeat.o(4364);
        }
    }

    public static String a(Context context) {
        String str;
        AppMethodBeat.i(SmsCbConstants.MESSAGE_ID_CMAS_ALERT_EXTREME_EXPECTED_LIKELY, false);
        try {
            str = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (Exception e) {
            e.printStackTrace();
            str = null;
        }
        if (TextUtils.isEmpty(str)) {
            str = "unknown";
        }
        AppMethodBeat.o(SmsCbConstants.MESSAGE_ID_CMAS_ALERT_EXTREME_EXPECTED_LIKELY);
        return str;
    }

    public static String a() {
        return Build.MANUFACTURER;
    }

    public static String b() {
        return Build.VERSION.RELEASE;
    }

    public static String b(Context context) {
        AppMethodBeat.i(SmsCbConstants.MESSAGE_ID_CMAS_ALERT_SEVERE_EXPECTED_LIKELY_LANGUAGE, false);
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService("phone");
        if (telephonyManager == null) {
            AppMethodBeat.o(SmsCbConstants.MESSAGE_ID_CMAS_ALERT_SEVERE_EXPECTED_LIKELY_LANGUAGE);
            return "";
        }
        String networkOperatorName = telephonyManager.getNetworkOperatorName();
        AppMethodBeat.o(SmsCbConstants.MESSAGE_ID_CMAS_ALERT_SEVERE_EXPECTED_LIKELY_LANGUAGE);
        return networkOperatorName;
    }

    public static String c(Context context) {
        AppMethodBeat.i(4396, false);
        try {
            LocationManager locationManager = (LocationManager) context.getSystemService("location");
            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == 0 || ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == 0) {
                Location lastKnownLocation = locationManager.getLastKnownLocation("network");
                String locality = new Geocoder(context, Locale.getDefault()).getFromLocation(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude(), 1).get(0).getLocality();
                AppMethodBeat.o(4396);
                return locality;
            }
            AppMethodBeat.o(4396);
            return "";
        } catch (Exception e) {
            d.d("SystemUtils", "getLocation" + e.getMessage());
            AppMethodBeat.o(4396);
            return null;
        }
    }

    public static String c() {
        AppMethodBeat.i(4411, false);
        String valueOf = String.valueOf(System.currentTimeMillis());
        String d = d();
        try {
            String a2 = a(valueOf.substring(5, valueOf.length()) + d.substring(0, 8));
            if (!TextUtils.isEmpty(a2)) {
                String substring = a2.substring(8, 24);
                AppMethodBeat.o(4411);
                return substring;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String str = valueOf + d.substring(0, 3);
        AppMethodBeat.o(4411);
        return str;
    }

    public static String d() {
        AppMethodBeat.i(4416, false);
        String uuid = UUID.randomUUID().toString();
        AppMethodBeat.o(4416);
        return uuid;
    }

    public static String a(String str) {
        String str2 = "";
        AppMethodBeat.i(4418, false);
        if (TextUtils.isEmpty(str)) {
            AppMethodBeat.o(4418);
            return null;
        }
        try {
            str2 = a(str.getBytes("UTF-8"));
        } catch (Exception e) {
            d.b("SystemUtils", str2, e);
        }
        AppMethodBeat.o(4418);
        return str2;
    }

    public static String a(byte[] bArr) {
        AppMethodBeat.i(4421, false);
        StringBuffer stringBuffer = new StringBuffer();
        try {
            MessageDigest instance = MessageDigest.getInstance(KeyProperties.DIGEST_MD5);
            instance.reset();
            instance.update(bArr);
            byte[] digest = instance.digest();
            for (int i = 0; i < digest.length; i++) {
                if (Integer.toHexString(digest[i] & 255).length() == 1) {
                    stringBuffer.append("0");
                    stringBuffer.append(Integer.toHexString(digest[i] & 255));
                } else {
                    stringBuffer.append(Integer.toHexString(digest[i] & 255));
                }
            }
        } catch (Exception e) {
            d.b("SystemUtils", "", e);
        }
        String stringBuffer2 = stringBuffer.toString();
        AppMethodBeat.o(4421);
        return stringBuffer2;
    }

    public static String d(Context context) {
        AppMethodBeat.i(4427, false);
        if (TextUtils.isEmpty(b)) {
            b = e(context);
        }
        String str = b;
        AppMethodBeat.o(4427);
        return str;
    }

    private static String e(Context context) {
        AppMethodBeat.i(4435, false);
        String string = Settings.Secure.getString(context.getContentResolver(), "android_id");
        AppMethodBeat.o(4435);
        return string;
    }
}
