package cn.missfresh.flutter.flutter_map;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import cn.missfresh.flutter.R;
import cn.missfresh.flutter.flutter_map.bean.PostMainLocation;
import cn.missfresh.module.base.utils.av;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import com.alibaba.fastjson.JSONObject;
import com.tencent.mapsdk.raster.model.LatLng;
import com.tencent.mapsdk.raster.model.Marker;
import com.tencent.mapsdk.raster.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.map.MapView;
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.platform.PlatformView;
import java.util.Map;

public class FlutterMapView implements DefaultLifecycleObserver, ActivityPluginBinding.OnSaveInstanceStateListener, MethodChannel.MethodCallHandler, PlatformView {
    boolean a;
    private MapView b;
    private final MethodChannel c;
    private Marker d;
    private View e;
    private ImageView f;
    private TextView g;
    private TextView h;
    private View i;
    private TextView j;
    private Marker k;
    private LinearLayout l;
    private ImageView m;
    private Context n;
    private int o = 100;
    private final a p;

    @Override // io.flutter.plugin.platform.PlatformView
    public /* synthetic */ void onFlutterViewDetached() {
        PlatformView.CC.$default$onFlutterViewDetached(this);
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public void onInputConnectionLocked() {
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public void onInputConnectionUnlocked() {
    }

    static /* synthetic */ void a(FlutterMapView flutterMapView, LatLng latLng) {
        AppMethodBeat.i(21325, false);
        flutterMapView.b(latLng);
        AppMethodBeat.o(21325);
    }

    static /* synthetic */ void b(FlutterMapView flutterMapView, LatLng latLng) {
        AppMethodBeat.i(21326, false);
        flutterMapView.a(latLng);
        AppMethodBeat.o(21326);
    }

    public FlutterMapView(Context context, int i, Map<String, Object> map, BinaryMessenger binaryMessenger, a aVar) {
        AppMethodBeat.i(21279, false);
        this.n = context;
        this.p = aVar;
        this.b = new MapView(context);
        this.b.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.c = new MethodChannel(binaryMessenger, "tmap_plugin_" + i);
        this.c.setMethodCallHandler(this);
        this.e = View.inflate(this.b.getContext(), R.layout.order_layout_express_map_view_flutter, null);
        this.f = (ImageView) this.e.findViewById(R.id.iv_sender);
        this.g = (TextView) this.e.findViewById(R.id.tv_map_sender_distance);
        this.i = this.e.findViewById(R.id.map_sender);
        this.h = (TextView) this.e.findViewById(R.id.tv_map_sender_status);
        this.j = (TextView) this.e.findViewById(R.id.tv_post_man_state_desc);
        this.l = new LinearLayout(this.b.getContext());
        this.l.setLayoutParams(new LinearLayout.LayoutParams(a(context, 18), a(context, 21)));
        this.m = new ImageView(this.b.getContext());
        this.m.setLayoutParams(new ViewGroup.LayoutParams(a(context, 18), a(context, 21)));
        this.m.setScaleType(ImageView.ScaleType.FIT_XY);
        this.m.setImageResource(R.drawable.ico_order_detail_my_location);
        this.l.addView(this.m);
        this.i.setOnClickListener(new 1(this));
        a();
        AppMethodBeat.o(21279);
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public View getView() {
        return this.b;
    }

    /* access modifiers changed from: package-private */
    public void a() {
        AppMethodBeat.i(21280, false);
        this.p.a().addObserver(this);
        AppMethodBeat.o(21280);
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public void dispose() {
        AppMethodBeat.i(21281, false);
        d.d("lhj", "dispose");
        if (this.a) {
            AppMethodBeat.o(21281);
            return;
        }
        this.a = true;
        this.c.setMethodCallHandler(null);
        b();
        Lifecycle a = this.p.a();
        if (a != null) {
            a.removeObserver(this);
        }
        AppMethodBeat.o(21281);
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        char c = 0;
        AppMethodBeat.i(21283, false);
        String str = methodCall.method;
        if (str.hashCode() != -699308152 || !str.equals("upDataLocationInfo")) {
            c = '\uffff';
        }
        if (c == 0) {
            PostMainLocation postMainLocation = (PostMainLocation) JSONObject.parseObject((String) methodCall.argument("jsonData"), PostMainLocation.class);
            if (!b.a(postMainLocation.getPostmanIcon())) {
                cn.missfresh.module.base.network.d.e(this.b.getContext(), postMainLocation.getPostmanIcon(), this.f);
            }
            if (!b.a(postMainLocation.getDestinationIcon())) {
                cn.missfresh.module.base.network.d.e(this.b.getContext(), postMainLocation.getDestinationIcon(), this.m);
            }
            LatLng latLng = new LatLng(postMainLocation.getPostmanLatitude(), postMainLocation.getPostmanLongitude());
            LatLng latLng2 = new LatLng(postMainLocation.getDestinationLatitude(), postMainLocation.getDestinationLongitude());
            a(postMainLocation.getOrderStatusDesc());
            a(this.g, postMainLocation.getDistanceDesc(), -46959, new String[]{"#_$", "#_$"}, true, 14);
            av.a(this.j, postMainLocation.getPostmanStatusDesc());
            this.g.requestLayout();
            a(latLng2, latLng);
        }
        AppMethodBeat.o(21283);
    }

    public void a(LatLng latLng, LatLng latLng2) {
        AppMethodBeat.i(21285, false);
        MapView mapView = this.b;
        if (mapView == null || latLng == null || latLng2 == null) {
            AppMethodBeat.o(21285);
            return;
        }
        mapView.post(new 2(this, latLng2, latLng));
        AppMethodBeat.o(21285);
    }

    public void a(String str) {
        AppMethodBeat.i(21286, false);
        if (str == null || str.length() <= 0) {
            this.h.setVisibility(8);
        } else {
            this.h.setVisibility(0);
            this.h.setText(str);
        }
        AppMethodBeat.o(21286);
    }

    private void a(LatLng latLng) {
        AppMethodBeat.i(21288, false);
        if (!(this.b == null || latLng == null)) {
            Marker marker = this.k;
            if (marker == null) {
                this.k = this.b.addMarker(new MarkerOptions().markerView(this.l).position(latLng));
            } else if (!latLng.equals(marker.getPosition())) {
                this.k.setPosition(new LatLng(latLng.getLatitude(), latLng.getLongitude()));
            }
        }
        AppMethodBeat.o(21288);
    }

    private void b(LatLng latLng) {
        AppMethodBeat.i(21290, false);
        if (!(this.b == null || latLng == null)) {
            Marker marker = this.d;
            if (marker != null) {
                marker.remove();
                this.d = null;
            }
            this.d = this.b.addMarker(new MarkerOptions().markerView(this.e).position(latLng));
        }
        AppMethodBeat.o(21290);
    }

    public static int a(Context context, int i) {
        AppMethodBeat.i(21294, false);
        int i2 = (int) ((((float) i) * context.getResources().getDisplayMetrics().density) + 0.5f);
        AppMethodBeat.o(21294);
        return i2;
    }

    public static void a(TextView textView, String str, int i, String[] strArr, boolean z, int i2) {
        AppMethodBeat.i(21297, false);
        if (textView == null || TextUtils.isEmpty(str) || strArr.length != 2) {
            AppMethodBeat.o(21297);
            return;
        }
        try {
            textView.setText("");
            int i3 = 1;
            AbsoluteSizeSpan absoluteSizeSpan = new AbsoluteSizeSpan(i2, true);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            int i4 = 0;
            int i5 = 0;
            boolean z2 = true;
            while (z2) {
                int indexOf = str.indexOf(strArr[0], i4);
                int indexOf2 = str.indexOf(strArr[i3], (strArr[0].length() + indexOf) - i3);
                if (indexOf2 <= indexOf || indexOf <= -1) {
                    spannableStringBuilder.append((CharSequence) str.substring(i5));
                    z2 = false;
                    i4 = indexOf2;
                } else {
                    int length = indexOf2 + strArr[0].length();
                    spannableStringBuilder.append((CharSequence) str.substring(i5, indexOf));
                    String substring = str.substring(indexOf, length);
                    if (z) {
                        substring = substring.replace(strArr[0], "").replace(strArr[i3], "");
                    }
                    int length2 = substring.length();
                    SpannableString spannableString = new SpannableString(substring);
                    spannableString.setSpan(new ForegroundColorSpan(i), 0, length2, 33);
                    if (i2 != 0) {
                        spannableString.setSpan(absoluteSizeSpan, 0, length2, 33);
                    }
                    spannableStringBuilder.append((CharSequence) spannableString);
                    i4 = length;
                    i5 = i4;
                }
                i3 = 1;
            }
            textView.setText(spannableStringBuilder);
        } catch (Exception unused) {
            textView.setText(str);
        }
        AppMethodBeat.o(21297);
    }

    public int a(Context context) {
        AppMethodBeat.i(21300, false);
        WindowManager windowManager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        if (windowManager == null) {
            AppMethodBeat.o(21300);
            return 1024;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 17) {
            windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        } else {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        int i = displayMetrics.widthPixels;
        AppMethodBeat.o(21300);
        return i;
    }

    public int b(Context context) {
        AppMethodBeat.i(21303, false);
        WindowManager windowManager = (WindowManager) context.getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        if (windowManager == null) {
            AppMethodBeat.o(21303);
            return 1980;
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        if (Build.VERSION.SDK_INT >= 17) {
            windowManager.getDefaultDisplay().getRealMetrics(displayMetrics);
        } else {
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        }
        int i = displayMetrics.heightPixels;
        AppMethodBeat.o(21303);
        return i;
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding.OnSaveInstanceStateListener
    public void onSaveInstanceState(Bundle bundle) {
        AppMethodBeat.i(21305, false);
        if (this.a) {
            AppMethodBeat.o(21305);
            return;
        }
        this.b.onSaveInstanceState(bundle);
        AppMethodBeat.o(21305);
    }

    @Override // io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding.OnSaveInstanceStateListener
    public void onRestoreInstanceState(Bundle bundle) {
        AppMethodBeat.i(21306, false);
        if (this.a) {
            AppMethodBeat.o(21306);
            return;
        }
        d.d("lhj", "onRestoreInstanceState");
        this.b.onCreate(bundle);
        AppMethodBeat.o(21306);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onCreate(LifecycleOwner lifecycleOwner) {
        AppMethodBeat.i(21308, false);
        d.d("lhj", "onCreate");
        if (this.a) {
            AppMethodBeat.o(21308);
            return;
        }
        this.b.onCreate(null);
        AppMethodBeat.o(21308);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onStart(LifecycleOwner lifecycleOwner) {
        AppMethodBeat.i(21310, false);
        d.d("lhj", "onStart");
        if (this.a) {
            AppMethodBeat.o(21310);
        } else {
            AppMethodBeat.o(21310);
        }
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onResume(LifecycleOwner lifecycleOwner) {
        AppMethodBeat.i(21311, false);
        d.d("lhj", "onResume");
        if (this.a) {
            AppMethodBeat.o(21311);
            return;
        }
        this.b.onResume();
        AppMethodBeat.o(21311);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onPause(LifecycleOwner lifecycleOwner) {
        AppMethodBeat.i(21313, false);
        d.d("lhj", "onPause");
        if (this.a) {
            AppMethodBeat.o(21313);
            return;
        }
        this.b.onPause();
        AppMethodBeat.o(21313);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onStop(LifecycleOwner lifecycleOwner) {
        AppMethodBeat.i(21315, false);
        d.d("lhj", "onStop");
        if (this.a) {
            AppMethodBeat.o(21315);
            return;
        }
        this.b.onStop();
        AppMethodBeat.o(21315);
    }

    @Override // androidx.lifecycle.DefaultLifecycleObserver, androidx.lifecycle.FullLifecycleObserver
    public void onDestroy(LifecycleOwner lifecycleOwner) {
        AppMethodBeat.i(21317, false);
        d.d("lhj", "onDestroy" + this.a);
        lifecycleOwner.getLifecycle().removeObserver(this);
        if (this.a) {
            AppMethodBeat.o(21317);
            return;
        }
        b();
        AppMethodBeat.o(21317);
    }

    private void b() {
        AppMethodBeat.i(21319, false);
        MapView mapView = this.b;
        if (mapView == null) {
            AppMethodBeat.o(21319);
            return;
        }
        mapView.onDestroy();
        this.b = null;
        AppMethodBeat.o(21319);
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public void onFlutterViewAttached(View view) {
        AppMethodBeat.i(21320, false);
        d.d("lhj", "onFlutterViewAttached");
        AppMethodBeat.o(21320);
    }
}
