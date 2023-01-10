package cn.missfresh.module.user.login.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.base.ActionBarActivity;
import cn.missfresh.module.base.bean.BindingInfo;
import cn.missfresh.module.base.common.providers.IOrderDetailsService;
import cn.missfresh.module.base.support.dialog.f;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.module.user.login.presenter.a;
import cn.missfresh.module.user.login.view.BindingPhoneNumActivity;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import de.greenrobot.event.EventBus;

public class AbstBindingNumActivity extends ActionBarActivity implements a.a {
    protected a j;
    private Handler k = new Handler();
    private String l;
    private String m;
    private boolean n;

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.login.view.AbstBindingNumActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.ActionBarActivity, cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        AbstBindingNumActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    public AbstBindingNumActivity() {
        AppMethodBeat.i(8254, false);
        AppMethodBeat.o(8254);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(8257, false);
        super.onCreate(bundle);
        this.j = new a(this);
        AppMethodBeat.o(8257);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onDestroy() {
        AppMethodBeat.i(8258, false);
        super.onDestroy();
        AppMethodBeat.o(8258);
    }

    public void a(String str) {
        AppMethodBeat.i(8260, false);
        String str2 = this.b;
        d.d(str2, "\u7ed1\u5b9a\u6210\u529f...fromPage:" + this.l);
        l();
        cn.missfresh.module.user.login.b.a.a().a(2);
        String str3 = ((IOrderDetailsService) com.alibaba.android.arouter.b.a.a().a("/order/details_service").navigation()) != null ? "OrderDetailsActivity" : "";
        if (b.a(this.l) || !this.l.equalsIgnoreCase(str3)) {
            f fVar = new f(this.f, R.drawable.ic_binding_success, str);
            fVar.setOnDismissListener(new 1(this));
            fVar.show();
            this.k.postDelayed(new 2(this, fVar), 2000);
            AppMethodBeat.o(8260);
            return;
        }
        EventBus.getDefault().post(new BindingPhoneNumActivity.a());
        EventBus.getDefault().post(new BindingInfo(1, v(), t()));
        finish();
        AppMethodBeat.o(8260);
    }

    public void r() {
        AppMethodBeat.i(8263, false);
        finish();
        AppMethodBeat.o(8263);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r6v0, resolved type: cn.missfresh.module.user.login.view.AbstBindingNumActivity */
    /* JADX WARN: Multi-variable type inference failed */
    public void b(String str) {
        AppMethodBeat.i(8266, false);
        EventBus.getDefault().post(new BindingInfo(3, v(), t()));
        if (q.a((Activity) this)) {
            l();
            if (!TextUtils.isEmpty(u()) && "canteen".equalsIgnoreCase(u())) {
                str = "\u60a8\u767b\u5f55\u7684\u5fae\u4fe1\u5df2\u88ab\u7ed1\u5b9a\uff0c\u4e0d\u80fd\u53d1\u8d77\u9080\u8bf7";
            }
            f fVar = new f(this.f, str, "\u6211\u77e5\u9053\u4e86", null);
            fVar.a(true);
            fVar.show();
        }
        AppMethodBeat.o(8266);
    }

    /* access modifiers changed from: protected */
    public void s() {
        AppMethodBeat.i(8267, false);
        Intent intent = getIntent();
        if (intent != null) {
            boolean booleanExtra = intent.getBooleanExtra("is_bind_event", false);
            String stringExtra = intent.getStringExtra("from_page");
            d(booleanExtra);
            e(stringExtra);
            if (intent.hasExtra("from_source")) {
                f(intent.getStringExtra("from_source"));
            }
        }
        AppMethodBeat.o(8267);
    }

    public String t() {
        String str = this.l;
        return str == null ? "" : str;
    }

    public void e(String str) {
        this.l = str;
    }

    public String u() {
        return this.m;
    }

    public void f(String str) {
        this.m = str;
    }

    public boolean v() {
        return this.n;
    }

    public void d(boolean z) {
        this.n = z;
    }
}
