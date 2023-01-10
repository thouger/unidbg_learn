package cn.missfresh.flutter.flutter_video;

import android.app.Activity;
import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import io.flutter.plugin.common.BinaryMessenger;
import io.flutter.plugin.common.StandardMessageCodec;
import io.flutter.plugin.platform.PlatformView;
import io.flutter.plugin.platform.PlatformViewFactory;
import java.util.Map;

/* compiled from: FlutterVideoPlayerFactory */
public class b extends PlatformViewFactory {
    private BinaryMessenger a;
    private Activity b;

    @Override // io.flutter.plugin.platform.PlatformViewFactory
    public /* synthetic */ PlatformView create(Context context, int i, Object obj) {
        AppMethodBeat.i(21562, false);
        a a = a(context, i, obj);
        AppMethodBeat.o(21562);
        return a;
    }

    public b(BinaryMessenger binaryMessenger, Activity activity) {
        super(StandardMessageCodec.INSTANCE);
        this.b = activity;
        this.a = binaryMessenger;
    }

    public a a(Context context, int i, Object obj) {
        AppMethodBeat.i(21561, false);
        a aVar = new a(this.b, i, (Map) obj, this.a);
        AppMethodBeat.o(21561);
        return aVar;
    }
}
