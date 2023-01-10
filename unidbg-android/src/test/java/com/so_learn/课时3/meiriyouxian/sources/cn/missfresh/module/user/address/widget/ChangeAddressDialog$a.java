package cn.missfresh.module.user.address.widget;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import cn.missfresh.module.base.widget.wheelview.a.b;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.address.bean.Address;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.tencent.imsdk.BaseConstants;
import java.util.ArrayList;
import java.util.List;

/* access modifiers changed from: private */
public class ChangeAddressDialog$a extends b {
    List<Address> f;
    final /* synthetic */ ChangeAddressDialog g;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    protected ChangeAddressDialog$a(ChangeAddressDialog changeAddressDialog, Context context, List<Address> list, int i, int i2, int i3) {
        super(context, R.layout.item_support_address, 0, i, i2, i3);
        this.g = changeAddressDialog;
        AppMethodBeat.i(BaseConstants.ERR_USER_SIG_EXPIRED, false);
        this.f = list == null ? new ArrayList<>() : list;
        a(R.id.tempValue);
        AppMethodBeat.o(BaseConstants.ERR_USER_SIG_EXPIRED);
    }

    @Override // cn.missfresh.module.base.widget.wheelview.a.b, cn.missfresh.module.base.widget.wheelview.a.c
    public View a(int i, View view, ViewGroup viewGroup) {
        AppMethodBeat.i(BaseConstants.ERR_NEVER_CONNECT_AFTER_LAUNCH, false);
        View a = super.a(i, view, viewGroup);
        AppMethodBeat.o(BaseConstants.ERR_NEVER_CONNECT_AFTER_LAUNCH);
        return a;
    }

    @Override // cn.missfresh.module.base.widget.wheelview.a.c
    public int b() {
        AppMethodBeat.i(BaseConstants.ERR_REQ_OVERLOADED, false);
        int size = this.f.size();
        AppMethodBeat.o(BaseConstants.ERR_REQ_OVERLOADED);
        return size;
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.widget.wheelview.a.b
    public CharSequence b(int i) {
        AppMethodBeat.i(BaseConstants.ERR_REQ_SERVICE_SUSPEND, false);
        String str = (!(i == 0 && b() == 0) && i <= b()) ? this.f.get(i).name : "";
        AppMethodBeat.o(BaseConstants.ERR_REQ_SERVICE_SUSPEND);
        return str;
    }
}
