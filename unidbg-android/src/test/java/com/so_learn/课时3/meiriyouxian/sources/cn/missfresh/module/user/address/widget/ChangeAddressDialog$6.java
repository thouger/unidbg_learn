package cn.missfresh.module.user.address.widget;

import cn.missfresh.module.base.widget.wheelview.widget.WheelView;
import cn.missfresh.module.base.widget.wheelview.widget.b;
import cn.missfresh.module.user.address.bean.Address;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

class ChangeAddressDialog$6 implements b {
    final /* synthetic */ ChangeAddressDialog a;

    ChangeAddressDialog$6(ChangeAddressDialog changeAddressDialog) {
        this.a = changeAddressDialog;
    }

    @Override // cn.missfresh.module.base.widget.wheelview.widget.b
    public void a(WheelView wheelView, int i, int i2) {
        AppMethodBeat.i(6182, false);
        ChangeAddressDialog changeAddressDialog = this.a;
        changeAddressDialog.a((String) ChangeAddressDialog.i(this.a).b(wheelView.getCurrentItem()), ChangeAddressDialog.i(changeAddressDialog));
        ChangeAddressDialog changeAddressDialog2 = this.a;
        ChangeAddressDialog.b(changeAddressDialog2, (List) ChangeAddressDialog.j(changeAddressDialog2).get(((Address) ChangeAddressDialog.e(this.a).get(i2)).code));
        ChangeAddressDialog changeAddressDialog3 = this.a;
        ChangeAddressDialog.b(changeAddressDialog3, new ChangeAddressDialog$a(changeAddressDialog3, ChangeAddressDialog.d(changeAddressDialog3), ChangeAddressDialog.k(this.a), 0, ChangeAddressDialog.f(this.a), ChangeAddressDialog.g(this.a)));
        ChangeAddressDialog.l(this.a).setVisibleItems(5);
        ChangeAddressDialog.l(this.a).setViewAdapter(ChangeAddressDialog.m(this.a));
        ChangeAddressDialog.l(this.a).setCurrentItem(0);
        ChangeAddressDialog changeAddressDialog4 = this.a;
        ChangeAddressDialog.b(changeAddressDialog4, (Address) ChangeAddressDialog.e(changeAddressDialog4).get(i2));
        if (!cn.missfresh.utils.b.a(ChangeAddressDialog.k(this.a))) {
            ChangeAddressDialog changeAddressDialog5 = this.a;
            ChangeAddressDialog.c(changeAddressDialog5, (Address) ChangeAddressDialog.k(changeAddressDialog5).get(0));
        } else {
            ChangeAddressDialog.c(this.a, (Address) null);
        }
        AppMethodBeat.o(6182);
    }
}
