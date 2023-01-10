package cn.missfresh.sherlock.tool;

import android.content.Context;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.text.TextUtils;
import java.util.Locale;

/* compiled from: CommonUtils */
public class c {
    private static String a;

    public static String a(Context context) {
        if (!TextUtils.isEmpty(a)) {
            return a;
        }
        return b(context);
    }

    private static String b(Context context) {
        try {
            Location lastKnownLocation = ((LocationManager) context.getSystemService("location")).getLastKnownLocation("network");
            a = new Geocoder(context, Locale.getDefault()).getFromLocation(lastKnownLocation.getLatitude(), lastKnownLocation.getLongitude(), 1).get(0).getLocality();
        } catch (Exception e) {
            j.a("CommonUtils", "getLocation" + e.getMessage());
            a = "unknown";
        }
        return a;
    }
}
