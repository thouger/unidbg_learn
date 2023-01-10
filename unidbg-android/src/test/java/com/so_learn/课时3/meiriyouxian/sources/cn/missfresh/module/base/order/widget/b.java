package cn.missfresh.module.base.order.widget;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.android.arouter.b.a;

/* compiled from: OrderToastUtil */
public class b {
    public static void a(int i, String str) {
        AppMethodBeat.i(16320, false);
        a(i, str, -1);
        AppMethodBeat.o(16320);
    }

    private static void a(int i, String str, int i2) {
        AppMethodBeat.i(16325, false);
        View inflate = LayoutInflater.from(((IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()).getApplication()).inflate(R.layout.order_toast_layout, (ViewGroup) null);
        ((ImageView) inflate.findViewById(R.id.icon)).setImageResource(i);
        if (cn.missfresh.utils.b.a(str)) {
            cn.missfresh.ui.a.a.a(inflate, 17, 0, 0, i2);
        }
        cn.missfresh.ui.a.a.a(inflate, 17, 0, 0, str);
        AppMethodBeat.o(16325);
    }
}
