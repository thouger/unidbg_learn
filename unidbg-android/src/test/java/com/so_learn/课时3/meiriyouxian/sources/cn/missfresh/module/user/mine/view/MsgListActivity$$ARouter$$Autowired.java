package cn.missfresh.module.user.mine.view;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.android.arouter.b.a;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import com.tencent.imsdk.BaseConstants;

public class MsgListActivity$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public MsgListActivity$$ARouter$$Autowired() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ENTERPRISE_PRIVACY_INSTALLED_APPS));
    }

    public void inject(Object obj) {
        AppMethodBeat.i(BaseConstants.ERR_SDK_NET_WAIT_ACK_TIMEOUT, false);
        this.serializationService = (SerializationService) a.a().a(SerializationService.class);
        MsgListActivity msgListActivity = (MsgListActivity) obj;
        msgListActivity.a = msgListActivity.getIntent().getIntExtra("type", msgListActivity.a);
        AppMethodBeat.o(BaseConstants.ERR_SDK_NET_WAIT_ACK_TIMEOUT);
    }
}
