package cn.missfresh.module.base.common.ministart.model;

import cn.missfresh.basiclib.net.c;
import cn.missfresh.module.base.bean.UserConfig;
import cn.missfresh.module.base.common.api.RequestParam;
import cn.missfresh.module.base.common.ministart.api.MiniStartApiManager;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.manager.k;
import cn.missfresh.module.base.network.j;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.taobao.accs.common.Constants;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* compiled from: MiniStartModel */
public class a {
    public void a() {
        AppMethodBeat.i(12206, false);
        HashMap hashMap = new HashMap(2);
        hashMap.put("typeId", 1);
        hashMap.put(Constants.KEY_BUSINESSID, 1);
        RequestParam<Map> requestParam = new RequestParam<>();
        requestParam.setParam(hashMap);
        MiniStartApiManager.getMiniStartApi().getUserConfig(requestParam).b(io.reactivex.f.a.b()).a(io.reactivex.f.a.b()).b(new c(new C0022a()));
        AppMethodBeat.o(12206);
    }

    /* compiled from: MiniStartModel */
    /* renamed from: cn.missfresh.module.base.common.ministart.model.a$a  reason: collision with other inner class name */
    public static class C0022a extends j<UserConfig> {
        @Override // cn.missfresh.module.base.network.j
        public void a(int i, String str) {
        }

        @Override // cn.missfresh.basiclib.net.a.a
        public /* synthetic */ void onSuccess(Object obj) {
            AppMethodBeat.i(12203, false);
            a((UserConfig) obj);
            AppMethodBeat.o(12203);
        }

        public void a(UserConfig userConfig) {
            AppMethodBeat.i(12201, false);
            b(userConfig);
            AppMethodBeat.o(12201);
        }

        private void b(UserConfig userConfig) {
            boolean z = false;
            AppMethodBeat.i(12202, false);
            if (userConfig == null) {
                AppMethodBeat.o(12202);
                return;
            }
            List<UserConfig.UserConfigList> list = userConfig.userConfigList;
            if (b.a(list)) {
                AppMethodBeat.o(12202);
                return;
            }
            Iterator<UserConfig.UserConfigList> it2 = list.iterator();
            while (true) {
                if (!it2.hasNext()) {
                    break;
                }
                UserConfig.UserConfigList next = it2.next();
                if (next != null && next.typeId == 1) {
                    boolean aw = e.aw();
                    if (next.statusId == 1) {
                        z = true;
                    }
                    if (aw != z) {
                        e.A(z);
                        k.a().a(z);
                    }
                }
            }
            AppMethodBeat.o(12202);
        }
    }
}
