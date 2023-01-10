package cn.missfresh.flutter.helper;

import android.app.Activity;
import android.text.TextUtils;
import cn.missfresh.module.base.im.a;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: OrderListSkipHelper */
class b$2 implements a {
    final /* synthetic */ Activity a;

    b$2(Activity activity) {
        this.a = activity;
    }

    @Override // cn.missfresh.module.base.im.a
    public void a(String str) {
        AppMethodBeat.i(21638, false);
        if (TextUtils.isEmpty(str)) {
            cn.missfresh.ui.a.a.a("\u7f51\u7edc\u5f02\u5e38\uff0c\u8bf7\u91cd\u8bd5");
        } else {
            q.a(this.a, str);
        }
        AppMethodBeat.o(21638);
    }
}
