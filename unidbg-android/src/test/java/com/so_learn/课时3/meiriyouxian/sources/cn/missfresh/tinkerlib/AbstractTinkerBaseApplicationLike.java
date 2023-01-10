package cn.missfresh.tinkerlib;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import com.tencent.tinker.entry.DefaultApplicationLike;
import com.tencent.tinker.lib.service.AbstractResultService;
import com.tencent.tinker.lib.tinker.TinkerInstaller;

public abstract class AbstractTinkerBaseApplicationLike extends DefaultApplicationLike {
    public abstract Class<? extends AbstractResultService> getResultServiceClass();

    public abstract void onContextAttached();

    public AbstractTinkerBaseApplicationLike(Application application, int i, boolean z, long j, long j2, Intent intent) {
        super(application, i, z, j, j2, intent);
    }

    @Override // com.tencent.tinker.entry.DefaultApplicationLike, com.tencent.tinker.entry.ApplicationLike, com.tencent.tinker.entry.ApplicationLifeCycle
    public void onBaseContextAttached(Context context) {
        super.onBaseContextAttached(context);
        onContextAttached();
        d.a(this);
        d.b();
        d.a(true);
        TinkerInstaller.setLogIml(c.a());
        d.a(this, getResultServiceClass());
    }

    public void registerActivityLifecycleCallbacks(Application.ActivityLifecycleCallbacks activityLifecycleCallbacks) {
        getApplication().registerActivityLifecycleCallbacks(activityLifecycleCallbacks);
    }
}
