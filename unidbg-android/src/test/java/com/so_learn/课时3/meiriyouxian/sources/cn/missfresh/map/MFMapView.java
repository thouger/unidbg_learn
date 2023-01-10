package cn.missfresh.map;

import android.content.Context;
import android.util.AttributeSet;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.mapsdk.raster.model.CameraPosition;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.TencentMapOptions;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;

public class MFMapView extends MapView implements TencentMap.OnMapCameraChangeListener, TencentMap.OnMapClickListener, TencentMap.OnMarkerClickListener {
    private h a;
    private i b;
    private j c;
    private k d = new k();

    @Override // com.tencent.tencentmap.mapsdk.map.TencentMap.OnMapCameraChangeListener
    public void onCameraChange(CameraPosition cameraPosition) {
    }

    public MFMapView(Context context) {
        super(context);
        AppMethodBeat.i(3703, false);
        AppMethodBeat.o(3703);
    }

    public MFMapView(Context context, TencentMapOptions tencentMapOptions) {
        super(context, tencentMapOptions);
        AppMethodBeat.i(3708, false);
        AppMethodBeat.o(3708);
    }

    public MFMapView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(3711, false);
        AppMethodBeat.o(3711);
    }

    public void a(b bVar) {
        AppMethodBeat.i(3713, false);
        getMap().animateTo(new LatLng(bVar.b(), bVar.c()));
        AppMethodBeat.o(3713);
    }

    public void setScaleControlsEnabled(boolean z) {
        AppMethodBeat.i(3718, false);
        getUiSettings().setScaleControlsEnabled(z);
        AppMethodBeat.o(3718);
    }

    public void setCenter(k kVar) {
        AppMethodBeat.i(3728, false);
        getMap().setCenter(new LatLng(kVar.a(), kVar.b()));
        AppMethodBeat.o(3728);
    }

    public void setZoom(int i) {
        AppMethodBeat.i(3730, false);
        getMap().setZoom(i);
        AppMethodBeat.o(3730);
    }

    public void setOnMapCameraChangeListener(h hVar) {
        AppMethodBeat.i(3743, false);
        this.a = hVar;
        getMap().setOnMapCameraChangeListener(this);
        AppMethodBeat.o(3743);
    }

    public void setOnMapClickListener(i iVar) {
        AppMethodBeat.i(3747, false);
        this.b = iVar;
        getMap().setOnMapClickListener(this);
        AppMethodBeat.o(3747);
    }

    public void setOnMarkerClickListener(j jVar) {
        AppMethodBeat.i(3750, false);
        this.c = jVar;
        getMap().setOnMarkerClickListener(this);
        AppMethodBeat.o(3750);
    }

    @Override // com.tencent.tencentmap.mapsdk.map.TencentMap.OnMapCameraChangeListener
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        AppMethodBeat.i(3755, false);
        if (this.a != null) {
            this.d.a(cameraPosition.getTarget().getLatitude());
            this.d.b(cameraPosition.getTarget().getLongitude());
            this.a.a(this.d);
        }
        AppMethodBeat.o(3755);
    }

    @Override // com.tencent.tencentmap.mapsdk.map.TencentMap.OnMapClickListener
    public void onMapClick(LatLng latLng) {
        AppMethodBeat.i(3758, false);
        i iVar = this.b;
        if (iVar != null) {
            iVar.a(new b(latLng.getLatitude(), latLng.getLongitude()));
        }
        AppMethodBeat.o(3758);
    }

    @Override // com.tencent.tencentmap.mapsdk.map.TencentMap.OnMarkerClickListener
    public boolean onMarkerClick(Marker marker) {
        AppMethodBeat.i(3762, false);
        j jVar = this.c;
        if (jVar != null) {
            jVar.onMarkerClick(marker);
        }
        AppMethodBeat.o(3762);
        return false;
    }
}
