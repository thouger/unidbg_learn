package cn.missfresh.flutter;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.android.arouter.b.a;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.bangcle.andjni.JniLib;

public class FlutterPageActivity$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public FlutterPageActivity$$ARouter$$Autowired() {
        JniLib.cV(this, 0);
    }

    public void inject(Object obj) {
        AppMethodBeat.i(20708, false);
        this.serializationService = (SerializationService) a.a().a(SerializationService.class);
        FlutterPageActivity flutterPageActivity = (FlutterPageActivity) obj;
        flutterPageActivity.b = flutterPageActivity.getIntent().getStringExtra("BackgroundModel");
        flutterPageActivity.d = flutterPageActivity.getIntent().getStringExtra("Router");
        flutterPageActivity.e = flutterPageActivity.getIntent().getBooleanExtra("NeedRefresh", flutterPageActivity.e);
        flutterPageActivity.f = flutterPageActivity.getIntent().getBooleanExtra("isNeedJumpToMain", flutterPageActivity.f);
        AppMethodBeat.o(20708);
    }
}
