package cn.missfresh.module.user.login.c;

import android.app.Activity;
import android.content.Context;
import android.mtp.MtpConstants;
import cn.missfresh.module.base.bean.LoginEvent;
import cn.missfresh.module.base.common.livedata.MFStickyLiveData;
import cn.missfresh.module.base.common.providers.ILoginService;
import cn.missfresh.module.user.login.view.LoginActivity;
import cn.missfresh.module.user.login.view.QuickLoginActivity;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: LoginService */
public class a implements ILoginService {
    private MFStickyLiveData<LoginEvent> a = new MFStickyLiveData<>();

    public void init(Context context) {
    }

    public a() {
        AppMethodBeat.i(MtpConstants.RESPONSE_INVALID_DEVICE_PROP_VALUE, false);
        AppMethodBeat.o(MtpConstants.RESPONSE_INVALID_DEVICE_PROP_VALUE);
    }

    @Override // cn.missfresh.module.base.common.providers.ILoginService
    public void a(int i) {
        AppMethodBeat.i(MtpConstants.RESPONSE_INVALID_PARAMETER, false);
        cn.missfresh.module.user.login.b.a.a().a(2);
        AppMethodBeat.o(MtpConstants.RESPONSE_INVALID_PARAMETER);
    }

    @Override // cn.missfresh.module.base.common.providers.ILoginService
    public boolean a(Activity activity) {
        return activity != null && ((activity instanceof LoginActivity) || (activity instanceof QuickLoginActivity));
    }

    @Override // cn.missfresh.module.base.common.providers.ILoginService
    public MFStickyLiveData<LoginEvent> a() {
        return this.a;
    }
}
