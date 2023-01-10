package cn.missfresh.flutter.flutter_video;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.missfresh.lib.image.c;
import cn.missfresh.module.product.api.R;
import cn.missfresh.module.product.detailnew.widget.CustomProductDetailJzStd;
import cn.missfresh.player.MediaUtils;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.platform.PlatformView;
import java.util.Map;

/* compiled from: FlutterVideoPlayer */
public class a implements MethodChannel.MethodCallHandler, PlatformView {
    private CustomProductDetailJzStd a;
    private String b = AudioManager.VOLUME_CHANGED_ACTION;
    private final int c = 1;
    private final int d = 2;
    private final int e = 3;
    private Context f;
    private a g;
    private MethodChannel h;
    private boolean i;
    private Handler j = new 1(this);

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

    public a(Context context, int i, Map<String, Object> map, BinaryMessenger binaryMessenger) {
        AppMethodBeat.i(21544, false);
        this.f = context;
        this.h = new MethodChannel(binaryMessenger, "native_player_plugin_" + i);
        this.h.setMethodCallHandler(this);
        this.a = LayoutInflater.from(context).inflate(R.layout.product_detail_video_item, (ViewGroup) null, false);
        this.a.b();
        c();
        String str = (String) map.get("videoUrl");
        String str2 = (String) map.get("imageUrl");
        this.i = ((Boolean) map.get("autoPlay")).booleanValue();
        this.a.setUp(str, "");
        this.a.setTag(str);
        this.a.setmJzUserActionListener(new FlutterVideoPlayer$2(this));
        if (MediaUtils.isWifiConnected(this.f) && this.i) {
            if (this.a.getmJzUserActionListener() != null) {
                this.a.getmJzUserActionListener().onEvent(1001);
            }
            this.a.startVideo();
            this.j.sendEmptyMessageDelayed(1, 200);
        }
        c.b(context).a(str2).a(this.a.posterImageView);
        AppMethodBeat.o(21544);
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public View getView() {
        return this.a;
    }

    @Override // io.flutter.plugin.platform.PlatformView
    public void dispose() {
        AppMethodBeat.i(21546, false);
        a();
        AppMethodBeat.o(21546);
    }

    public void a() {
        AppMethodBeat.i(21548, false);
        b();
        this.a = null;
        this.j.removeMessages(1);
        AppMethodBeat.o(21548);
    }

    private void c() {
        AppMethodBeat.i(21549, false);
        b();
        this.g = new a(this, (1) null);
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(this.b);
        this.f.registerReceiver(this.g, intentFilter);
        AppMethodBeat.o(21549);
    }

    public void b() {
        AppMethodBeat.i(21551, false);
        BroadcastReceiver broadcastReceiver = this.g;
        if (broadcastReceiver != null) {
            this.f.unregisterReceiver(broadcastReceiver);
            this.g = null;
        }
        AppMethodBeat.o(21551);
    }

    @Override // io.flutter.plugin.common.MethodChannel.MethodCallHandler
    public void onMethodCall(MethodCall methodCall, MethodChannel.Result result) {
        AppMethodBeat.i(21553, false);
        if (methodCall.method.equals("pause")) {
            Message obtain = Message.obtain();
            obtain.what = 2;
            this.j.sendMessage(obtain);
        } else if (methodCall.method.equals("release")) {
            Message obtain2 = Message.obtain();
            obtain2.what = 3;
            this.j.sendMessage(obtain2);
        } else {
            methodCall.method.equals("setDataSource");
        }
        AppMethodBeat.o(21553);
    }
}
