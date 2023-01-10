package cn.missfresh.tencent;

import android.os.Looper;
import cn.missfresh.location_api.StickyLiveData;
import cn.missfresh.location_api.b;
import cn.missfresh.location_api.bean.Location;
import cn.missfresh.location_api.bean.LocationStatus;
import cn.missfresh.location_api.d;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

/* compiled from: MFTencentLocation */
public class a extends b<b> implements TencentLocationListener {
    private TencentLocationManager a;
    private StickyLiveData<Location> b = new StickyLiveData<>();
    private StickyLiveData<LocationStatus> c = new StickyLiveData<>();

    public String b(int i) {
        return i != 1 ? i != 2 ? i != 4 ? "\u672a\u77e5\u539f\u56e0\u5f15\u8d77\u7684\u5b9a\u4f4d\u5931\u8d25" : "\u65e0\u6cd5\u5c06WGS84\u5750\u6807\u8f6c\u6362\u6210GCJ-02\u5750\u6807\u65f6\u7684\u5b9a\u4f4d\u5931\u8d25" : "GPS, Wi-Fi \u6216\u57fa\u7ad9\u9519\u8bef\u5f15\u8d77\u7684\u5b9a\u4f4d\u5931\u8d25\uff1a\n1\u3001\u7528\u6237\u7684\u624b\u673a\u786e\u5b9e\u91c7\u96c6\u4e0d\u5230\u5b9a\u4f4d\u51ed\u636e\uff0c\u6bd4\u5982\u504f\u8fdc\u5730\u533a\u6bd4\u5982\u5730\u4e0b\u8f66\u5e93\u7535\u68af\u5185\u7b49;\n2\u3001\u5f00\u5173\u8ddf\u6743\u9650\u95ee\u9898\uff0c\u6bd4\u5982\u7528\u6237\u5173\u95ed\u4e86\u4f4d\u7f6e\u4fe1\u606f\uff0c\u5173\u95ed\u4e86wifi\uff0c\u672a\u6388\u4e88app\u5b9a\u4f4d\u6743\u9650\u7b49" : "\u7f51\u7edc\u95ee\u9898\u5f15\u8d77\u7684\u5b9a\u4f4d\u5931\u8d25";
    }

    public a() {
        AppMethodBeat.i(11911, false);
        AppMethodBeat.o(11911);
    }

    @Override // cn.missfresh.location_api.b
    public /* synthetic */ void a(cn.missfresh.location_api.a aVar, d dVar) {
        AppMethodBeat.i(11934, false);
        a((b) aVar, dVar);
        AppMethodBeat.o(11934);
    }

    public void a(b bVar, d dVar) {
        AppMethodBeat.i(11913, false);
        if (bVar != null && bVar.a() != null) {
            this.a = TencentLocationManager.getInstance(bVar.a());
            if (dVar != null) {
                dVar.a();
            }
        } else if (dVar != null) {
            dVar.a(1, "config data error");
        } else {
            NullPointerException nullPointerException = new NullPointerException("config data error");
            AppMethodBeat.o(11913);
            throw nullPointerException;
        }
        AppMethodBeat.o(11913);
    }

    public int a() {
        AppMethodBeat.i(11914, false);
        TencentLocationRequest create = TencentLocationRequest.create();
        create.setRequestLevel(3);
        create.setAllowCache(true);
        create.setAllowDirection(true);
        create.setAllowGPS(true);
        this.a.setCoordinateType(1);
        int requestSingleFreshLocation = this.a.requestSingleFreshLocation(create, this, Looper.myLooper());
        AppMethodBeat.o(11914);
        return requestSingleFreshLocation;
    }

    public int a(int i) {
        AppMethodBeat.i(11916, false);
        TencentLocationRequest create = TencentLocationRequest.create();
        create.setRequestLevel(3);
        create.setAllowCache(true);
        create.setAllowDirection(true);
        create.setAllowGPS(true);
        create.setInterval((long) i);
        this.a.setCoordinateType(1);
        int requestLocationUpdates = this.a.requestLocationUpdates(create, this, Looper.myLooper());
        AppMethodBeat.o(11916);
        return requestLocationUpdates;
    }

    public StickyLiveData<Location> b() {
        return this.b;
    }

    public void c() {
        AppMethodBeat.i(11921, false);
        this.a.removeUpdates(this);
        AppMethodBeat.o(11921);
    }

    @Override // com.tencent.map.geolocation.TencentLocationListener
    public void onLocationChanged(TencentLocation tencentLocation, int i, String str) {
        AppMethodBeat.i(11928, false);
        Location location = new Location();
        location.setCode(i);
        location.setMessage(str);
        location.setLat(tencentLocation.getLatitude());
        location.setLng(tencentLocation.getLongitude());
        location.setTime(tencentLocation.getTime());
        location.setCity(tencentLocation.getCity());
        location.setCityCode(tencentLocation.getCityCode());
        location.setAddress(tencentLocation.getAddress());
        location.setProvince(tencentLocation.getProvince());
        if (d()) {
            this.b.b(location);
        } else {
            this.b.a(location);
        }
        AppMethodBeat.o(11928);
    }

    @Override // com.tencent.map.geolocation.TencentLocationListener
    public void onStatusUpdate(String str, int i, String str2) {
        AppMethodBeat.i(11930, false);
        LocationStatus locationStatus = new LocationStatus();
        locationStatus.setName(str);
        locationStatus.setStatus(i);
        locationStatus.setDesc(str2);
        if (d()) {
            this.c.b(locationStatus);
        } else {
            this.c.a(locationStatus);
        }
        AppMethodBeat.o(11930);
    }

    private boolean d() {
        boolean z = false;
        AppMethodBeat.i(11932, false);
        if (Looper.getMainLooper() == Looper.myLooper()) {
            z = true;
        }
        AppMethodBeat.o(11932);
        return z;
    }
}
