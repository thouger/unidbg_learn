package cn.missfresh.map;

import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.tencentmap.mapsdk.map.TencentMap;

/* compiled from: MFOnMarkerClickListener */
public interface j extends TencentMap.OnMarkerClickListener {
    @Override // com.tencent.tencentmap.mapsdk.map.TencentMap.OnMarkerClickListener
    boolean onMarkerClick(Marker marker);
}
