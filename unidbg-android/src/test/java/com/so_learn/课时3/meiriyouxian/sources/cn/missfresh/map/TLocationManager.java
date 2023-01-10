package cn.missfresh.map;

import android.content.Context;
import android.os.Looper;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import java.util.Observable;
import java.util.Observer;

public class TLocationManager extends Observable implements a, TencentLocationListener {
    private Context a;
    private e b = new e();
    private f c = new f();

    public TLocationManager(Context context) {
        AppMethodBeat.i(3789, false);
        this.a = context.getApplicationContext();
        TencentLocationManager.getInstance(context);
        AppMethodBeat.o(3789);
    }

    public int a() {
        AppMethodBeat.i(3795, false);
        TencentLocationManager instance = TencentLocationManager.getInstance(this.a);
        TencentLocationRequest create = TencentLocationRequest.create();
        create.setRequestLevel(3);
        create.setAllowCache(true);
        create.setAllowDirection(true);
        create.setAllowGPS(true);
        instance.setCoordinateType(1);
        int requestSingleFreshLocation = instance.requestSingleFreshLocation(create, this, Looper.myLooper());
        AppMethodBeat.o(3795);
        return requestSingleFreshLocation;
    }

    public void b() {
        AppMethodBeat.i(3797, false);
        TencentLocationManager.getInstance(this.a).removeUpdates(this);
        AppMethodBeat.o(3797);
    }

    public void a(Observer observer) {
        AppMethodBeat.i(3801, false);
        if (observer != null) {
            addObserver(observer);
        }
        AppMethodBeat.o(3801);
    }

    public void b(Observer observer) {
        AppMethodBeat.i(3804, false);
        if (observer != null) {
            deleteObserver(observer);
        }
        AppMethodBeat.o(3804);
    }

    @Override // com.tencent.map.geolocation.TencentLocationListener
    public void onLocationChanged(TencentLocation tencentLocation, int i, String str) {
        AppMethodBeat.i(3806, false);
        this.b.a(g.a(tencentLocation));
        this.b.a(i);
        this.b.a(str);
        setChanged();
        notifyObservers(this.b);
        AppMethodBeat.o(3806);
    }

    @Override // com.tencent.map.geolocation.TencentLocationListener
    public void onStatusUpdate(String str, int i, String str2) {
        AppMethodBeat.i(3808, false);
        this.c.a(str);
        this.c.a(i);
        this.c.b(str2);
        setChanged();
        notifyObservers(this.c);
        AppMethodBeat.o(3808);
    }
}
