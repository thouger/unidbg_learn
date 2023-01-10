package cn.missfresh.module.user.mine.qucikpay.view;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.android.arouter.b.a;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;

public class QuickPayActivity$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public QuickPayActivity$$ARouter$$Autowired() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.AUTOFILL_SAVE_UI));
    }

    public void inject(Object obj) {
        AppMethodBeat.i(9034, false);
        this.serializationService = (SerializationService) a.a().a(SerializationService.class);
        QuickPayActivity quickPayActivity = (QuickPayActivity) obj;
        quickPayActivity.a = quickPayActivity.getIntent().getIntExtra("from", quickPayActivity.a);
        AppMethodBeat.o(9034);
    }
}
