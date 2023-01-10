package cn.missfresh.module.base.im;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.android.arouter.b.a;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.bangcle.andjni.JniLib;

public class IMChatActivity$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public IMChatActivity$$ARouter$$Autowired() {
        JniLib.cV(this, 5);
    }

    public void inject(Object obj) {
        AppMethodBeat.i(13302, false);
        this.serializationService = (SerializationService) a.a().a(SerializationService.class);
        IMChatActivity iMChatActivity = (IMChatActivity) obj;
        iMChatActivity.a = iMChatActivity.getIntent().getStringExtra("ChatId");
        AppMethodBeat.o(13302);
    }
}
