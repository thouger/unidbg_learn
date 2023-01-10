package cn.missfresh.risk;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.xiaomi.mipush.sdk.Constants;
import java.util.ArrayList;
import java.util.List;

/* compiled from: SensorUtil */
public class l implements SensorEventListener {
    private Sensor a;
    private Sensor b;
    private SensorManager c;
    private float[] d;
    private float[] e;
    private float[] f = new float[3];

    @Override // android.hardware.SensorEventListener
    public void onAccuracyChanged(Sensor sensor, int i) {
    }

    public l() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.FINGERPRINT_REMOVE_SIDECAR, false);
        AppMethodBeat.o(MetricsProto.MetricsEvent.FINGERPRINT_REMOVE_SIDECAR);
    }

    public void a(Context context) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.ENTERPRISE_PRIVACY_INSTALLED_APPS, false);
        try {
            this.c = (SensorManager) context.getSystemService("sensor");
            this.a = this.c.getDefaultSensor(1);
            this.b = this.c.getDefaultSensor(2);
            this.c.registerListener(this, this.a, 3);
            this.c.registerListener(this, this.b, 3);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.ENTERPRISE_PRIVACY_INSTALLED_APPS);
    }

    private void c() {
        AppMethodBeat.i(MetricsProto.MetricsEvent.NOTIFICATION_CHANNEL_IMPORTANCE, false);
        float[] fArr = new float[3];
        float[] fArr2 = new float[9];
        SensorManager.getRotationMatrix(fArr2, null, this.d, this.e);
        SensorManager.getOrientation(fArr2, fArr);
        float degrees = (float) Math.toDegrees((double) fArr[0]);
        if (degrees < 0.0f) {
            degrees += 360.0f;
        }
        float f = (degrees / 5.0f) * 5.0f;
        float degrees2 = (float) Math.toDegrees((double) fArr[1]);
        float degrees3 = (float) Math.toDegrees((double) fArr[2]);
        float[] fArr3 = this.f;
        fArr3[0] = f;
        fArr3[1] = degrees2;
        fArr3[2] = degrees3;
        Log.e("aaaa", "oritation:" + f + Constants.ACCEPT_TIME_SEPARATOR_SP + degrees2 + Constants.ACCEPT_TIME_SEPARATOR_SP + degrees3);
        AppMethodBeat.o(MetricsProto.MetricsEvent.NOTIFICATION_CHANNEL_IMPORTANCE);
    }

    public static List<String> b(Context context) {
        AppMethodBeat.i(MetricsProto.MetricsEvent.RUNNING_BACKGROUND_APPS_DIALOG, false);
        try {
            List<Sensor> sensorList = ((SensorManager) context.getSystemService("sensor")).getSensorList(-1);
            if (sensorList != null && sensorList.size() > 0) {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < sensorList.size(); i++) {
                    arrayList.add(sensorList.get(i).getType() + Constants.ACCEPT_TIME_SEPARATOR_SP + sensorList.get(i).getVendor());
                }
                AppMethodBeat.o(MetricsProto.MetricsEvent.RUNNING_BACKGROUND_APPS_DIALOG);
                return arrayList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.RUNNING_BACKGROUND_APPS_DIALOG);
        return null;
    }

    public float[] a() {
        return this.f;
    }

    public void b() {
        AppMethodBeat.i(950, false);
        try {
            this.c.unregisterListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(950);
    }

    @Override // android.hardware.SensorEventListener
    public void onSensorChanged(SensorEvent sensorEvent) {
        AppMethodBeat.i(952, false);
        try {
            if (sensorEvent.sensor.getType() == 1) {
                if (this.d == null) {
                    this.d = new float[3];
                }
                this.d = sensorEvent.values;
            }
            if (sensorEvent.sensor.getType() == 2) {
                if (this.e == null) {
                    this.e = new float[3];
                }
                this.e = sensorEvent.values;
            }
            if (!(this.d == null || this.e == null)) {
                b();
            }
            if (!(this.d == null || this.e == null)) {
                b();
                c();
            }
        } catch (Exception e) {
            e.printStackTrace();
            b();
        }
        AppMethodBeat.o(952);
    }
}
