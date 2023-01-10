package cn.missfresh.module.user.address.widget;

import cn.missfresh.module.base.widget.wheelview.widget.WheelView;
import cn.missfresh.module.base.widget.wheelview.widget.b;
import cn.missfresh.module.user.address.bean.Address;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

class ChangeAddressDialog$8 implements b {
    final /* synthetic */ ChangeAddressDialog a;

    ChangeAddressDialog$8(ChangeAddressDialog changeAddressDialog) {
        this.a = changeAddressDialog;
    }

    @Override // cn.missfresh.module.base.widget.wheelview.widget.b
    public void a(WheelView wheelView, int i, int i2) {
        AppMethodBeat.i(6192, false);
        ChangeAddressDialog changeAddressDialog = this.a;
        changeAddressDialog.a((String) ChangeAddressDialog.m(this.a).b(wheelView.getCurrentItem()), ChangeAddressDialog.m(changeAddressDialog));
        ChangeAddressDialog changeAddressDialog2 = this.a;
        ChangeAddressDialog.c(changeAddressDialog2, (Address) ChangeAddressDialog.k(changeAddressDialog2).get(i2));
        AppMethodBeat.o(6192);
    }
}
