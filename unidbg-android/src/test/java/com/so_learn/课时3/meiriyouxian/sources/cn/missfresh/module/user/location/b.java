package cn.missfresh.module.user.location;

import android.content.Context;
import cn.missfresh.module.base.common.providers.IUserStartLocationService;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: UserStartLocationDelegateService */
public class b implements IUserStartLocationService {
    private a a;

    @Override // cn.missfresh.module.base.common.providers.IUserStartLocationService
    public void a() {
        AppMethodBeat.i(7428, false);
        this.a.a();
        AppMethodBeat.o(7428);
    }

    @Override // cn.missfresh.module.base.common.providers.IUserStartLocationService
    public void a(boolean z) {
        AppMethodBeat.i(7429, false);
        this.a.a(z);
        AppMethodBeat.o(7429);
    }

    @Override // cn.missfresh.module.base.common.providers.IUserStartLocationService
    public void b() {
        AppMethodBeat.i(7430, false);
        a aVar = this.a;
        if (aVar != null) {
            aVar.b();
        }
        AppMethodBeat.o(7430);
    }

    public void init(Context context) {
        AppMethodBeat.i(7431, false);
        this.a = new a();
        AppMethodBeat.o(7431);
    }
}
