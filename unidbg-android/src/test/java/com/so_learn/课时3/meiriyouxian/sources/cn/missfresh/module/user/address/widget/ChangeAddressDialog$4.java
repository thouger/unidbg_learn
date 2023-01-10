package cn.missfresh.module.user.address.widget;

import cn.missfresh.module.base.widget.wheelview.widget.WheelView;
import cn.missfresh.module.base.widget.wheelview.widget.b;
import cn.missfresh.module.user.address.bean.Address;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.imsdk.BaseConstants;
import java.util.List;

class ChangeAddressDialog$4 implements b {
    final /* synthetic */ ChangeAddressDialog a;

    ChangeAddressDialog$4(ChangeAddressDialog changeAddressDialog) {
        this.a = changeAddressDialog;
    }

    @Override // cn.missfresh.module.base.widget.wheelview.widget.b
    public void a(WheelView wheelView, int i, int i2) {
        AppMethodBeat.i(BaseConstants.ERR_GROUP_INVALID_FIELD, false);
        ChangeAddressDialog changeAddressDialog = this.a;
        changeAddressDialog.a((String) ChangeAddressDialog.a(this.a).b(wheelView.getCurrentItem()), ChangeAddressDialog.a(changeAddressDialog));
        ChangeAddressDialog changeAddressDialog2 = this.a;
        ChangeAddressDialog.a(changeAddressDialog2, (List) ChangeAddressDialog.c(changeAddressDialog2).get(((Address) ChangeAddressDialog.b(this.a).get(i2)).code));
        ChangeAddressDialog changeAddressDialog3 = this.a;
        ChangeAddressDialog.a(changeAddressDialog3, new ChangeAddressDialog$a(changeAddressDialog3, ChangeAddressDialog.d(changeAddressDialog3), ChangeAddressDialog.e(this.a), 0, ChangeAddressDialog.f(this.a), ChangeAddressDialog.g(this.a)));
        ChangeAddressDialog.h(this.a).setVisibleItems(5);
        ChangeAddressDialog.h(this.a).setViewAdapter(ChangeAddressDialog.i(this.a));
        ChangeAddressDialog.h(this.a).setCurrentItem(0);
        ChangeAddressDialog changeAddressDialog4 = this.a;
        ChangeAddressDialog.b(changeAddressDialog4, (List) ChangeAddressDialog.j(changeAddressDialog4).get(((Address) ChangeAddressDialog.e(this.a).get(0)).code));
        ChangeAddressDialog changeAddressDialog5 = this.a;
        ChangeAddressDialog.b(changeAddressDialog5, new ChangeAddressDialog$a(changeAddressDialog5, ChangeAddressDialog.d(changeAddressDialog5), ChangeAddressDialog.k(this.a), 0, ChangeAddressDialog.f(this.a), ChangeAddressDialog.g(this.a)));
        ChangeAddressDialog.l(this.a).setVisibleItems(5);
        ChangeAddressDialog.l(this.a).setViewAdapter(ChangeAddressDialog.m(this.a));
        ChangeAddressDialog.l(this.a).setCurrentItem(0);
        ChangeAddressDialog changeAddressDialog6 = this.a;
        ChangeAddressDialog.a(changeAddressDialog6, (Address) ChangeAddressDialog.b(changeAddressDialog6).get(i2));
        if (!cn.missfresh.utils.b.a(ChangeAddressDialog.e(this.a))) {
            ChangeAddressDialog changeAddressDialog7 = this.a;
            ChangeAddressDialog.b(changeAddressDialog7, (Address) ChangeAddressDialog.e(changeAddressDialog7).get(0));
        } else {
            ChangeAddressDialog.b(this.a, (Address) null);
        }
        if (!cn.missfresh.utils.b.a(ChangeAddressDialog.k(this.a))) {
            ChangeAddressDialog changeAddressDialog8 = this.a;
            ChangeAddressDialog.c(changeAddressDialog8, (Address) ChangeAddressDialog.k(changeAddressDialog8).get(0));
        } else {
            ChangeAddressDialog.c(this.a, (Address) null);
        }
        AppMethodBeat.o(BaseConstants.ERR_GROUP_INVALID_FIELD);
    }
}
