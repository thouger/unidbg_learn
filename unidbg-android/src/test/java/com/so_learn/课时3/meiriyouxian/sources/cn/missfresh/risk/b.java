package cn.missfresh.risk;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Camera;
import androidx.core.app.ActivityCompat;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;

/* compiled from: CameraUtil */
public class b {
    public static int a() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_HIDE_APP_DISAMBIG_APP_FEATURED, false);
        try {
            int numberOfCameras = Camera.getNumberOfCameras();
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_HIDE_APP_DISAMBIG_APP_FEATURED);
            return numberOfCameras;
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_HIDE_APP_DISAMBIG_APP_FEATURED);
            return 0;
        }
    }

    public static int a(Context context) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_HIDE_APP_DISAMBIG_NONE_FEATURED, false);
        try {
            boolean hasSystemFeature = context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_HIDE_APP_DISAMBIG_NONE_FEATURED);
            return hasSystemFeature ? 1 : 0;
        } catch (Exception e) {
            e.printStackTrace();
            AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_HIDE_APP_DISAMBIG_NONE_FEATURED);
            return -1;
        }
    }

    public static boolean b(Context context) {
        boolean z = false;
        AppMethodBeat.i(MetricsProto.MetricsEvent.ACTION_APP_DISAMBIG_TAP, false);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != 0) {
            z = true;
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.ACTION_APP_DISAMBIG_TAP);
        return z;
    }
}
