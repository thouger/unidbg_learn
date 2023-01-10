package cn.missfresh.module.user.address.widget;

import cn.missfresh.module.base.widget.wheelview.widget.WheelView;
import cn.missfresh.module.base.widget.wheelview.widget.d;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

class ChangeAddressDialog$5 implements d {
    final /* synthetic */ ChangeAddressDialog a;

    @Override // cn.missfresh.module.base.widget.wheelview.widget.d
    public void a(WheelView wheelView) {
    }

    ChangeAddressDialog$5(ChangeAddressDialog changeAddressDialog) {
        this.a = changeAddressDialog;
    }

    @Override // cn.missfresh.module.base.widget.wheelview.widget.d
    public void b(WheelView wheelView) {
        AppMethodBeat.i(6173, false);
        ChangeAddressDialog changeAddressDialog = this.a;
        changeAddressDialog.a((String) ChangeAddressDialog.a(this.a).b(wheelView.getCurrentItem()), ChangeAddressDialog.a(changeAddressDialog));
        AppMethodBeat.o(6173);
    }
}
