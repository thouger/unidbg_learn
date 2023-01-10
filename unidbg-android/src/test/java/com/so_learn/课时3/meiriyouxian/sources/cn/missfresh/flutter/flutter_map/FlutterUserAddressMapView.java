package cn.missfresh.flutter.flutter_map;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import cn.missfresh.flutter.R;
import cn.missfresh.map.b;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.alibaba.fastjson.JSONObject;
import com.tencent.mapsdk.raster.model.BitmapDescriptorFactory;
import com.tencent.mapsdk.raster.model.CameraPosition;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.map.MapView;
import com.tencent.tencentmap.mapsdk.map.TencentMap;
import com.xiaomi.mipush.sdk.Constants;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.platform.PlatformView;
import java.util.Map;

public class FlutterUserAddressMapView implements View.OnClickListener, DefaultLifecycleObserver, TencentMap.OnMapCameraChangeListener, ActivityPluginBinding.OnSaveInstanceStateListener, MethodChannel.MethodCallHandler, PlatformView {
    boolean a;
    private final MethodChannel b;
    private MapView c = ((MapView) this.f.findViewById(R.id.mapview));
    private final a d;
    private Marker e;
    private View f;
    private double g;
    private double h;

    @Override // com.tencent.tencentmap.mapsdk.map.TencentMap.OnMapCameraChangeListener
    public void onCameraChange(CameraPosition cameraPosition) {
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public /* synthetic */ void onFlutterViewAttached(View view) {
        PlatformView.CC.$default$onFlutterViewAttached(this, view);
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public /* synthetic */ void onFlutterViewDetached() {
        PlatformView.CC.$default$onFlutterViewDetached(this);
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public /* synthetic */ void onInputConnectionLocked() {
        PlatformView.CC.$default$onInputConnectionLocked(this);
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public /* synthetic */ void onInputConnectionUnlocked() {
        PlatformView.CC.$default$onInputConnectionUnlocked(this);
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
    }

    public FlutterUserAddressMapView(Context context, int i, Map<String, Object> map, BinaryMessenger binaryMessenger, a aVar) {
        AppMethodBeat.i(21335, false);
        this.d = aVar;
        this.f = LayoutInflater.from(context).inflate(R.layout.user_layout_map, (ViewGroup) null, false);
        this.c.getUiSettings().setScaleControlsEnabled(false);
        this.c.getMap().setZoom(16);
        this.f.findViewById(R.id.btn_my_location).setOnClickListener(this);
        try {
            this.g = ((Double) map.get("userLat")).doubleValue();
            this.h = ((Double) map.get("userLng")).doubleValue();
            a(new b(this.g, this.h));
            String str = (String) map.get("latlng");
            if (cn.missfresh.utils.b.a(str) || !str.contains(Constants.ACCEPT_TIME_SEPARATOR_SP)) {
                this.c.getMap().setCenter(new LatLng(this.g, this.h));
            } else {
                String[] split = str.split(Constants.ACCEPT_TIME_SEPARATOR_SP);
                this.c.getMap().setCenter(new LatLng(Double.parseDouble(split[0]), Double.parseDouble(split[1])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.b = new MethodChannel(binaryMessenger, "address_native_map_plugin_" + i);
        this.b.setMethodCallHandler(this);
        this.c.getMap().setOnMapCameraChangeListener(this);
        a();
        AppMethodBeat.o(21335);
    }

    /* access modifiers changed from: package-private */
    public void a() {
        AppMethodBeat.i(21336, false);
        this.d.a().addObserver(this);
        AppMethodBeat.o(21336);
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public View getView() {
        return this.f;
    }

    private void a(b bVar) {
        AppMethodBeat.i(21341, false);
        if (bVar == null) {
            AppMethodBeat.o(21341);
            return;
        }
        Marker marker = this.e;
        if (marker == null) {
            this.e = this.c.getMap().addMarker(new MarkerOptions().position(new LatLng(bVar.b(), bVar.c())).anchor(0.5f, 0.5f).icon(BitmapDescriptorFactory.fromResource(R.drawable.icon_map_my)));
        } else {
            marker.setPosition(new LatLng(bVar.b(), bVar.c()));
        }
        AppMethodBeat.o(21341);
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public void dispose() {
        AppMethodBeat.i(21342, false);
        if (this.a) {
            AppMethodBeat.o(21342);
            return;
        }
        this.a = true;
        this.b.setMethodCallHandler(null);
        b();
        Lifecycle a = this.d.a();
        if (a != null) {
            a.removeObserver(this);
        }
        AppMethodBeat.o(21342);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding.OnSaveInstanceStateListener
    public void onSaveInstanceState(Bundle bundle) {
        AppMethodBeat.i(21344, false);
        if (this.a) {
            AppMethodBeat.o(21344);
            return;
        }
        this.c.onSaveInstanceState(bundle);
        AppMethodBeat.o(21344);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding.OnSaveInstanceStateListener
    public void onRestoreInstanceState(Bundle bundle) {
        AppMethodBeat.i(21346, false);
        if (this.a) {
            AppMethodBeat.o(21346);
            return;
        }
        d.d("lhj", "onRestoreInstanceState");
        this.c.onCreate(bundle);
        AppMethodBeat.o(21346);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onCreate(LifecycleOwner lifecycleOwner) {
        AppMethodBeat.i(21347, false);
        d.d("lhj", "onCreate");
        if (this.a) {
            AppMethodBeat.o(21347);
            return;
        }
        this.c.onCreate(null);
        AppMethodBeat.o(21347);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onStart(LifecycleOwner lifecycleOwner) {
        AppMethodBeat.i(21349, false);
        d.d("lhj", "onStart");
        AppMethodBeat.o(21349);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onResume(LifecycleOwner lifecycleOwner) {
        AppMethodBeat.i(21351, false);
        d.d("lhj", "onResume");
        if (this.a) {
            AppMethodBeat.o(21351);
            return;
        }
        this.c.onResume();
        AppMethodBeat.o(21351);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onPause(LifecycleOwner lifecycleOwner) {
        AppMethodBeat.i(21352, false);
        d.d("lhj", "onPause");
        if (this.a) {
            AppMethodBeat.o(21352);
            return;
        }
        this.c.onPause();
        AppMethodBeat.o(21352);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onStop(LifecycleOwner lifecycleOwner) {
        AppMethodBeat.i(21354, false);
        d.d("lhj", "onStop");
        if (this.a) {
            AppMethodBeat.o(21354);
            return;
        }
        this.c.onStop();
        AppMethodBeat.o(21354);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onDestroy(LifecycleOwner lifecycleOwner) {
        AppMethodBeat.i(21355, false);
        d.d("lhj", "onDestroy" + this.a);
        lifecycleOwner.getLifecycle().removeObserver(this);
        if (this.a) {
            AppMethodBeat.o(21355);
            return;
        }
        b();
        AppMethodBeat.o(21355);
    }

    private void b() {
        AppMethodBeat.i(21356, false);
        MapView mapView = this.c;
        if (mapView == null) {
            AppMethodBeat.o(21356);
            return;
        }
        mapView.onDestroy();
        this.c = null;
        AppMethodBeat.o(21356);
    }

    @Override // com.tencent.tencentmap.mapsdk.map.TencentMap.OnMapCameraChangeListener
    public void onCameraChangeFinish(CameraPosition cameraPosition) {
        AppMethodBeat.i(21360, false);
        if (cameraPosition == null || cameraPosition.getTarget() == null || this.b == null) {
            AppMethodBeat.o(21360);
            return;
        }
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("lat", Double.valueOf(cameraPosition.getTarget().getLatitude()));
        jSONObject.put("lng", Double.valueOf(cameraPosition.getTarget().getLongitude()));
        this.b.invokeMethod("locationCallback", jSONObject);
        AppMethodBeat.o(21360);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(21361, false);
        if (this.c == null) {
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(21361);
            return;
        }
        if (view.getId() == R.id.btn_my_location) {
            this.c.getMap().setCenter(new LatLng(this.g, this.h));
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(21361);
    }
}
