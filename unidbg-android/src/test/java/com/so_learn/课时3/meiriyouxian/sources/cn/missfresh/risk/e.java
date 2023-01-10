package cn.missfresh.risk;

import android.Manifest;
import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import androidx.core.app.ActivityCompat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.xiaomi.mipush.sdk.Constants;

/* compiled from: GpsUtil */
public class e {
    public static int a(Context context) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_RUNNIGN_SERVICE, false);
        try {
            boolean isProviderEnabled = ((LocationManager) context.getSystemService("location")).isProviderEnabled("gps");
            AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_RUNNIGN_SERVICE);
            return isProviderEnabled ? 1 : 0;
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_RUNNIGN_SERVICE);
            return -1;
        }
    }

    public static String b(Context context) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_BLUETOOTH_PAIRED_DEVICE_PROFILE, false);
        LocationManager locationManager = (LocationManager) context.getSystemService("location");
        Criteria criteria = new Criteria();
        criteria.setAccuracy(1);
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(1);
        String bestProvider = locationManager.getBestProvider(criteria, true);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == 0 || ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == 0) {
            try {
                Location lastKnownLocation = locationManager.getLastKnownLocation(bestProvider);
                if (lastKnownLocation != null) {
                    String str = lastKnownLocation.getLatitude() + Constants.ACCEPT_TIME_SEPARATOR_SP + lastKnownLocation.getLongitude() + Constants.ACCEPT_TIME_SEPARATOR_SP + lastKnownLocation.getAccuracy() + Constants.ACCEPT_TIME_SEPARATOR_SP + lastKnownLocation.getSpeed() + Constants.ACCEPT_TIME_SEPARATOR_SP + lastKnownLocation.getTime();
                    AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_BLUETOOTH_PAIRED_DEVICE_PROFILE);
                    return str;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_BLUETOOTH_PAIRED_DEVICE_PROFILE);
            return "";
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_BLUETOOTH_PAIRED_DEVICE_PROFILE);
        return "";
    }

    public static int c(Context context) {
        int i = 0;
        AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_KEYBOARD_LAYOUT, false);
        if (!(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == 0 || ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == 0)) {
            i = 1;
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_KEYBOARD_LAYOUT);
        return i;
    }
}
