package cn.missfresh.module.base.common.api;

import android.app.Activity;
import android.app.Application;
import android.os.Handler;
import cn.missfresh.module.base.base.AppStartType;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.tencent.tinker.entry.ApplicationLike;
import com.tencent.tinker.lib.service.AbstractResultService;

public interface IApplicationDelegateService extends IProvider {
    void addTopActivity(Activity activity);

    AppStartType getAppStartType();

    String getAppVersion();

    Application getApplication();

    String getApplicationID();

    ApplicationLike getApplicationLike();

    Handler getHandler();

    String getPackageName();

    String getPreSeat();

    Class<? extends AbstractResultService> getResultServiceClass();

    boolean getShowSuperOptions();

    Activity getTopActivity();

    String getWXActionLogin();

    String getWXScope();

    String getWXState();

    void initSDKOnPrivacyAuthorized();

    boolean isAppInForeGround();

    boolean isDebug();

    void setPreSeat(String str);

    void setmAppStartType(AppStartType appStartType);

    void showNewMsgView(String str, String str2);
}
