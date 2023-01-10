package cn.missfresh.module.user.address.view;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.android.arouter.b.a;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import com.tencent.imsdk.BaseConstants;

public class UserAddressActivity$$ARouter$$Autowired implements ISyringe {
    private SerializationService serializationService;

    public UserAddressActivity$$ARouter$$Autowired() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.APP_TRANSITION_CALLING_PACKAGE_NAME));
    }

    public void inject(Object obj) {
        AppMethodBeat.i(BaseConstants.ERR_OUT_OF_MEMORY, false);
        this.serializationService = (SerializationService) a.a().a(SerializationService.class);
        UserAddressActivity userAddressActivity = (UserAddressActivity) obj;
        userAddressActivity.a = userAddressActivity.getIntent().getBooleanExtra("fromorder", userAddressActivity.a);
        userAddressActivity.j = userAddressActivity.getIntent().getIntExtra("gift_id", userAddressActivity.j);
        AppMethodBeat.o(BaseConstants.ERR_OUT_OF_MEMORY);
    }
}
