package cn.missfresh.module.user.address.view;

import cn.missfresh.module.base.support.dialog.e;
import cn.missfresh.module.base.utils.SobotCustomerServiceHelper;
import cn.missfresh.module.user.address.view.EditAddressModuleFragment;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

class EditAddressModuleFragment$11$1 implements SobotCustomerServiceHelper.a {
    final /* synthetic */ EditAddressModuleFragment.11 a;

    EditAddressModuleFragment$11$1(EditAddressModuleFragment.11 r1) {
        this.a = r1;
    }

    @Override // cn.missfresh.module.base.utils.SobotCustomerServiceHelper.a
    public void a(String str) {
        AppMethodBeat.i(4968, false);
        e.a(this.a.a.getActivity(), "", str, "", null, "\u6211\u77e5\u9053\u4e86", new 1(this), "", null);
        AppMethodBeat.o(4968);
    }
}
