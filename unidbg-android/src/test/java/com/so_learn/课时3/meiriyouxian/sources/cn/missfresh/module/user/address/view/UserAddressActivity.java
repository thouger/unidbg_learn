package cn.missfresh.module.user.address.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.module.base.bean.UserAddress;
import cn.missfresh.module.base.common.event.k;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.manager.c;
import cn.missfresh.module.base.support.dialog.f;
import cn.missfresh.module.base.widget.MultiStateLayout;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.address.adapter.UserAddressAdapter;
import cn.missfresh.module.user.address.presenter.UserAddressPresenter;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.alibaba.android.arouter.b.a;
import com.tencent.imsdk.BaseConstants;
import de.greenrobot.event.EventBus;
import java.util.List;

public class UserAddressActivity extends BaseFragmentActivity implements MultiStateLayout.a, MultiStateLayout.d, f {
    boolean a = false;
    int j = 0;
    private ListView k;
    private UserAddressAdapter l;
    private UserAddressPresenter m;
    private MultiStateLayout n;
    private boolean o = false;
    private int p = -1;
    private TextView v;
    private View w;
    private String x = "";
    private int y = 0;
    private String z = "";

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.address.view.UserAddressActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        UserAddressActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(6045, false);
        super.onCreate(bundle);
        a.a().a(this);
        setContentView(R.layout.user_activity_user_address);
        s();
        t();
        AppMethodBeat.o(6045);
    }

    private void s() {
        AppMethodBeat.i(6047, false);
        this.e.getBottomDivider().setVisibility(8);
        this.e.setLeftButtonVisibility(0);
        this.e.setVisibility(0);
        this.e.setCenterVisibility(0);
        this.v = (TextView) findViewById(R.id.tv_top_tips);
        this.w = findViewById(R.id.v_divider);
        findViewById(R.id.btn_add_address).setOnClickListener(this);
        this.k = (ListView) findViewById(R.id.lv_address);
        this.n = (MultiStateLayout) findViewById(R.id.multi_state_layout_new);
        this.n.setOnRefreshListener(this);
        this.n.setOnAddListener(this);
        this.m = new UserAddressPresenter(this);
        this.m.g();
        AppMethodBeat.o(6047);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r10v0, resolved type: cn.missfresh.module.user.address.view.UserAddressActivity */
    /* JADX WARN: Multi-variable type inference failed */
    private void t() {
        AppMethodBeat.i(6050, false);
        Intent intent = getIntent();
        if (intent != null) {
            this.p = intent.getIntExtra("cur_address_id", -1);
            int intExtra = intent.getIntExtra("from", 0);
            this.o = intent.getBooleanExtra("isdatasort", false);
            this.x = intent.getStringExtra("lat_lng");
            this.y = intent.getIntExtra("match_scope", 0);
            this.z = intent.getStringExtra("sub_title");
            this.m.b(intExtra);
            if (intExtra == 0) {
                this.e.setCenterTxt("\u9009\u62e9\u6536\u8d27\u5730\u5740");
            } else {
                this.e.setCenterTxt("\u6211\u7684\u6536\u8d27\u5730\u5740");
            }
        } else {
            r();
        }
        this.m.a(this.p);
        this.l = new UserAddressAdapter(this, this.m, this.z, this.x, this.y);
        this.k.setAdapter((ListAdapter) this.l);
        a(String.valueOf(this.p), this.o, this.x, String.valueOf(this.y));
        if (!this.a) {
            StatisticsManager.x("page_view", "isChoose", "0");
        }
        AppMethodBeat.o(6050);
    }

    private void a(String str, boolean z, String str2, String str3) {
        AppMethodBeat.i(6053, false);
        this.m.a(str, z, str2, str3);
        AppMethodBeat.o(6053);
    }

    public void c() {
        AppMethodBeat.i(6059, false);
        this.n.setViewState(1);
        if (this.a) {
            StatisticsManager.x("page_view", "isChoose", "1");
        }
        AppMethodBeat.o(6059);
    }

    public void a(List<UserAddress> list, int i, int i2, int i3) {
        AppMethodBeat.i(6062, false);
        if (!b.a(list)) {
            this.l.notifyDataSetChanged();
            this.n.setViewState(0);
            if (this.a) {
                StatisticsManager.x("page_view", "isChoose", "1", "validNum", Integer.valueOf(i), "invalidNum", Integer.valueOf(i2), "outsideNum", Integer.valueOf(i3));
            }
        } else {
            this.n.a();
            if (this.a) {
                StatisticsManager.x("page_view", "isChoose", "1");
            }
        }
        AppMethodBeat.o(6062);
    }

    public void r() {
        AppMethodBeat.i(6066, false);
        this.l.notifyDataSetChanged();
        AppMethodBeat.o(6066);
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x0036: APUT  (r2v3 java.lang.Object[]), (3 ??[int, float, short, byte, char]), (r0v4 java.lang.String) */
    public void a(UserAddress userAddress, int i) {
        AppMethodBeat.i(6071, false);
        c.q().b(userAddress);
        b(userAddress);
        if (this.a) {
            Object[] objArr = new Object[6];
            objArr[0] = "pos";
            objArr[1] = Integer.valueOf(i + 1);
            objArr[2] = "isDefault";
            objArr[3] = userAddress.defaultAddress ? "1" : "0";
            objArr[4] = "tag";
            objArr[5] = userAddress.getTagFormat();
            StatisticsManager.x("click_address", objArr);
        }
        AppMethodBeat.o(6071);
    }

    public void a(UserAddress userAddress) {
        AppMethodBeat.i(6073, false);
        new f(this.f, "\u786e\u5b9a\u4f7f\u7528\u8be5\u6536\u8d27\u5730\u5740", "\u786e\u5b9a", "\u53d6\u6d88", new 1(this), null).show();
        AppMethodBeat.o(6073);
    }

    public void d() {
        AppMethodBeat.i(6075, false);
        StatisticsManager.x("click_editAddress", new Object[0]);
        AppMethodBeat.o(6075);
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(6077, true);
        super.onClick(view);
        if (view.getId() == R.id.btn_add_address) {
            StatisticsManager.x("click_addAddress", new Object[0]);
            u();
        }
        AppMethodBeat.o(6077);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r8v0, resolved type: cn.missfresh.module.user.address.view.UserAddressActivity */
    /* JADX WARN: Multi-variable type inference failed */
    private void u() {
        AppMethodBeat.i(6079, false);
        if (this.m.c() == null || this.m.c().size() <= 0) {
            a.a().a("/user/edit_address").withInt("is_edit_mode", cn.missfresh.module.base.common.c.a.h).withBoolean("have_address", false).withString("lat_lng", this.x).withInt("match_scope", this.y).navigation(this, 0);
        } else {
            a.a().a("/user/edit_address").withInt("is_edit_mode", cn.missfresh.module.base.common.c.a.h).withString("lat_lng", this.x).withInt("match_scope", this.y).withBoolean("have_address", true).navigation(this, 0);
        }
        AppMethodBeat.o(6079);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        AppMethodBeat.i(6083, false);
        super.onActivityResult(i, i2, intent);
        if (i2 == cn.missfresh.module.base.common.c.a.e) {
            UserAddress userAddress = (UserAddress) intent.getParcelableExtra("add_address");
            if (!(userAddress == null || userAddress.id <= 0 || userAddress.usable == 2)) {
                k kVar = new k();
                kVar.a = userAddress;
                kVar.b = this.m.a;
                EventBus.getDefault().post(kVar);
                this.m.a(userAddress.id);
            }
            a(String.valueOf(this.p), this.o, this.x, String.valueOf(this.y));
        } else if (i2 == cn.missfresh.module.base.common.c.a.d) {
            UserAddress userAddress2 = (UserAddress) intent.getParcelableExtra("edit_address");
            if (!(userAddress2 == null || userAddress2.id <= 0 || userAddress2.usable == 2)) {
                k kVar2 = new k();
                kVar2.a = userAddress2;
                kVar2.b = this.m.a;
                EventBus.getDefault().post(kVar2);
            }
            a(String.valueOf(this.p), this.o, this.x, String.valueOf(this.y));
        } else if (i2 == cn.missfresh.module.base.common.c.a.f) {
            if (intent != null) {
                int intExtra = intent.getIntExtra("delete_address_id", -2);
                UserAddress k = c.q().k();
                if (k != null && k.id == intExtra) {
                    c.q().b((UserAddress) null);
                }
                if (intExtra == this.m.a()) {
                    this.m.a(-2);
                }
            }
            a(String.valueOf(this.p), this.o, this.x, String.valueOf(this.y));
        }
        AppMethodBeat.o(6083);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onDestroy() {
        AppMethodBeat.i(6085, false);
        super.onDestroy();
        this.m.h();
        this.m.i();
        AppMethodBeat.o(6085);
    }

    private void b(UserAddress userAddress) {
        AppMethodBeat.i(6086, false);
        Intent intent = new Intent();
        intent.putExtra("selected_address", userAddress);
        setResult(10, intent);
        k kVar = new k();
        kVar.a = userAddress;
        kVar.b = this.m.a;
        EventBus.getDefault().post(kVar);
        finish();
        AppMethodBeat.o(6086);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void J_() {
        AppMethodBeat.i(6087, false);
        v();
        AppMethodBeat.o(6087);
    }

    private void v() {
        boolean z = false;
        AppMethodBeat.i(6089, false);
        if (this.m.a() > 0) {
            z = true;
        }
        UserAddress userAddress = null;
        if (z) {
            userAddress = this.m.e();
        }
        Intent intent = new Intent();
        intent.putExtra("selected_address", userAddress);
        setResult(10, intent);
        finish();
        AppMethodBeat.o(6089);
    }

    @Override // cn.missfresh.module.base.widget.MultiStateLayout.d
    public void f_() {
        AppMethodBeat.i(6091, false);
        this.n.setViewState(3);
        w();
        AppMethodBeat.o(6091);
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void z_() {
        AppMethodBeat.i(6092, false);
        if (this.m == null) {
            AppMethodBeat.o(6092);
            return;
        }
        w();
        AppMethodBeat.o(6092);
    }

    private void w() {
        AppMethodBeat.i(6093, false);
        a(String.valueOf(this.p), this.o, this.x, String.valueOf(this.y));
        AppMethodBeat.o(6093);
    }

    @Override // cn.missfresh.module.base.widget.MultiStateLayout.a
    public void b() {
        AppMethodBeat.i(6097, false);
        u();
        AppMethodBeat.o(6097);
    }

    public void e() {
        AppMethodBeat.i(BaseConstants.ERR_BIND_FAIL_UNKNOWN, false);
        this.v.setVisibility(8);
        this.w.setVisibility(0);
        AppMethodBeat.o(BaseConstants.ERR_BIND_FAIL_UNKNOWN);
    }

    public void a(String str) {
        AppMethodBeat.i(BaseConstants.ERR_BIND_FAIL_TINYID_NULL, false);
        this.v.setText(str);
        this.v.setVisibility(0);
        this.w.setVisibility(8);
        AppMethodBeat.o(BaseConstants.ERR_BIND_FAIL_TINYID_NULL);
    }
}
