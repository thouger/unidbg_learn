package cn.missfresh.module.abtest;

import androidx.lifecycle.Observer;
import cn.missfresh.buttomline.abtest.ABTest;
import cn.missfresh.buttomline.logtrace.a;
import cn.missfresh.module.base.bean.LoginEvent;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.HashMap;

class AbTestManager$2 implements Observer<LoginEvent> {
    final /* synthetic */ AbTestManager this$0;

    AbTestManager$2(AbTestManager abTestManager) {
        this.this$0 = abTestManager;
    }

    @Override // androidx.lifecycle.Observer
    public /* synthetic */ void onChanged(Object obj) {
        AppMethodBeat.i(22102, false);
        onChanged((LoginEvent) obj);
        AppMethodBeat.o(22102);
    }

    public void onChanged(LoginEvent loginEvent) {
        AppMethodBeat.i(22100, false);
        if (loginEvent == null) {
            AppMethodBeat.o(22100);
            return;
        }
        if (200 == loginEvent.getType()) {
            AbTestManager.access$100(this.this$0);
            HashMap hashMap = new HashMap();
            hashMap.put(ABTest.KEY_MOBILE, e.p().getPhone_number());
            hashMap.put("userId", String.valueOf(e.p().getUser_id()));
            a.a((HashMap<String, String>) hashMap);
        } else if (400 == loginEvent.getType()) {
            AbTestManager.access$100(this.this$0);
            HashMap hashMap2 = new HashMap();
            hashMap2.put(ABTest.KEY_MOBILE, "");
            hashMap2.put("userId", "");
            a.a((HashMap<String, String>) hashMap2);
        }
        AppMethodBeat.o(22100);
    }
}
