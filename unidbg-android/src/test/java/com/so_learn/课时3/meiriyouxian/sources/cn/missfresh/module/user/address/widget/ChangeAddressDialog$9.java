package cn.missfresh.module.user.address.widget;

import cn.missfresh.module.base.widget.wheelview.widget.WheelView;
import cn.missfresh.module.base.widget.wheelview.widget.d;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

class ChangeAddressDialog$9 implements d {
    final /* synthetic */ ChangeAddressDialog a;

    @Override // cn.missfresh.module.base.widget.wheelview.widget.d
    public void a(WheelView wheelView) {
    }

    ChangeAddressDialog$9(ChangeAddressDialog changeAddressDialog) {
        this.a = changeAddressDialog;
    }

    @Override // cn.missfresh.module.base.widget.wheelview.widget.d
    public void b(WheelView wheelView) {
        AppMethodBeat.i(6199, false);
        ChangeAddressDialog changeAddressDialog = this.a;
        changeAddressDialog.a((String) ChangeAddressDialog.m(this.a).b(wheelView.getCurrentItem()), ChangeAddressDialog.m(changeAddressDialog));
        AppMethodBeat.o(6199);
    }
}
