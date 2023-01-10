package cn.missfresh.flutter.flutter_video;

import cn.missfresh.module.product.detailnew.widget.CustomProductDetailJzStd;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.HashMap;

/* access modifiers changed from: package-private */
public class FlutterVideoPlayer$2 implements CustomProductDetailJzStd.JZUserActionListener {
    final /* synthetic */ a a;

    public void onEvent(int i, Object obj, int i2, Object... objArr) {
    }

    FlutterVideoPlayer$2(a aVar) {
        this.a = aVar;
    }

    public void onEvent(int i) {
        AppMethodBeat.i(21534, false);
        HashMap hashMap = new HashMap();
        if (i == 1001) {
            hashMap.put("playing", true);
            this.a.h.invokeMethod("playerPlayStateChanged", hashMap);
            if (this.a.a.getVoiceStatus() == 0) {
                this.a.j.sendEmptyMessageDelayed(1, 200);
            }
        } else if (i == 1003) {
            hashMap.put("playing", false);
            this.a.h.invokeMethod("playerPlayStateChanged", hashMap);
        }
        AppMethodBeat.o(21534);
    }
}
