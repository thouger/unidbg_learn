package cn.missfresh.application;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import cn.missfresh.module.base.base.AppStartType;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.manager.m;
import cn.missfresh.module.promotion.h5.view.H5Activity;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.tinker.entry.ApplicationLike;
import com.tencent.tinker.lib.service.AbstractResultService;
import java.util.ArrayList;
import java.util.List;

/* compiled from: ApplicationDelegateService */
public class a implements IApplicationDelegateService {
    private List<Activity> a = new ArrayList();
    private String b;
    private Handler c = new Handler(Looper.myLooper());

    @Override // cn.missfresh.module.base.common.api.IApplicationDelegateService
    public String getAppVersion() {
        return "9.9.42";
    }

    @Override // cn.missfresh.module.base.common.api.IApplicationDelegateService
    public String getApplicationID() {
        return "cn.missfresh.application";
    }

    @Override // cn.missfresh.module.base.common.api.IApplicationDelegateService
    public String getPackageName() {
        return "cn.missfresh.application";
    }

    @Override // cn.missfresh.module.base.common.api.IApplicationDelegateService
    public boolean getShowSuperOptions() {
        return false;
    }

    @Override // cn.missfresh.module.base.common.api.IApplicationDelegateService
    public String getWXActionLogin() {
        return "mryx_login";
    }

    @Override // cn.missfresh.module.base.common.api.IApplicationDelegateService
    public String getWXScope() {
        return "snsapi_userinfo";
    }

    @Override // cn.missfresh.module.base.common.api.IApplicationDelegateService
    public String getWXState() {
        return "mryx_wweichat_login";
    }

    public void init(Context context) {
    }

    @Override // cn.missfresh.module.base.common.api.IApplicationDelegateService
    public boolean isDebug() {
        return false;
    }

    public a() {
        AppMethodBeat.i(66, false);
        AppMethodBeat.o(66);
    }

    @Override // cn.missfresh.module.base.common.api.IApplicationDelegateService
    public Application getApplication() {
        AppMethodBeat.i(69, false);
        Application c = b.c();
        AppMethodBeat.o(69);
        return c;
    }

    @Override // cn.missfresh.module.base.common.api.IApplicationDelegateService
    public void setPreSeat(String str) {
        this.b = str;
    }

    @Override // cn.missfresh.module.base.common.api.IApplicationDelegateService
    public String getPreSeat() {
        return this.b;
    }

    @Override // cn.missfresh.module.base.common.api.IApplicationDelegateService
    public void addTopActivity(Activity activity) {
        AppMethodBeat.i(91, false);
        if (activity != null) {
            if (!this.a.isEmpty()) {
                List<Activity> list = this.a;
                if (!(list.get(list.size() - 1) instanceof H5Activity) && !(activity instanceof H5Activity)) {
                    m.a().b();
                }
            }
            this.a.clear();
            this.a.add(activity);
        }
        AppMethodBeat.o(91);
    }

    @Override // cn.missfresh.module.base.common.api.IApplicationDelegateService
    public Activity getTopActivity() {
        AppMethodBeat.i(96, false);
        Activity activity = this.a.size() > 0 ? this.a.get(0) : null;
        AppMethodBeat.o(96);
        return activity;
    }

    @Override // cn.missfresh.module.base.common.api.IApplicationDelegateService
    public boolean isAppInForeGround() {
        AppMethodBeat.i(110, false);
        boolean isAppInForeGround = MissFreshApplicationLike.getInstance().isAppInForeGround();
        AppMethodBeat.o(110);
        return isAppInForeGround;
    }

    @Override // cn.missfresh.module.base.common.api.IApplicationDelegateService
    public Handler getHandler() {
        return this.c;
    }

    @Override // cn.missfresh.module.base.common.api.IApplicationDelegateService
    public AppStartType getAppStartType() {
        AppMethodBeat.i(122, false);
        AppStartType b = b.b();
        AppMethodBeat.o(122);
        return b;
    }

    @Override // cn.missfresh.module.base.common.api.IApplicationDelegateService
    public void setmAppStartType(AppStartType appStartType) {
        AppMethodBeat.i(131, false);
        b.a(appStartType);
        AppMethodBeat.o(131);
    }

    @Override // cn.missfresh.module.base.common.api.IApplicationDelegateService
    public void initSDKOnPrivacyAuthorized() {
        AppMethodBeat.i(134, false);
        MissFreshApplicationLike.getInstance().initLimitedSDKAfterPrivacyAuthorized();
        AppMethodBeat.o(134);
    }

    @Override // cn.missfresh.module.base.common.api.IApplicationDelegateService
    public void showNewMsgView(String str, String str2) {
        AppMethodBeat.i(137, false);
        MissFreshApplicationLike.getInstance().showNewMsgView(str, str2);
        AppMethodBeat.o(137);
    }

    @Override // cn.missfresh.module.base.common.api.IApplicationDelegateService
    public ApplicationLike getApplicationLike() {
        AppMethodBeat.i(141, false);
        MissFreshApplicationLike instance = MissFreshApplicationLike.getInstance();
        AppMethodBeat.o(141);
        return instance;
    }

    @Override // cn.missfresh.module.base.common.api.IApplicationDelegateService
    public Class<? extends AbstractResultService> getResultServiceClass() {
        AppMethodBeat.i(145, false);
        Class<? extends AbstractResultService> resultServiceClass = MissFreshApplicationLike.getInstance().getResultServiceClass();
        AppMethodBeat.o(145);
        return resultServiceClass;
    }
}
