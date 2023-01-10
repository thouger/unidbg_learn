package cn.missfresh.flutter.flutter_map;

import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;
import java.util.Map;

/* compiled from: TencentMapUserAddressPlatFormView */
public class d extends PlatformViewFactory {
    private final BinaryMessenger a;
    private final a b;

    public d(BinaryMessenger binaryMessenger, a aVar) {
        super(StandardMessageCodec.INSTANCE);
        this.a = binaryMessenger;
        this.b = aVar;
    }

    @Override // io.flutter.plugin.platform.PlatformViewFactory
    public PlatformView create(Context context, int i, Object obj) {
        AppMethodBeat.i(21426, false);
        FlutterUserAddressMapView flutterUserAddressMapView = new FlutterUserAddressMapView(context, i, (Map) obj, this.a, this.b);
        AppMethodBeat.o(21426);
        return flutterUserAddressMapView;
    }
}
