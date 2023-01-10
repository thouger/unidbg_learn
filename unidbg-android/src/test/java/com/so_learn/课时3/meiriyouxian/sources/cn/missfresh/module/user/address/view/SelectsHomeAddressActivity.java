package cn.missfresh.module.user.address.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import cn.missfresh.buttomline.logtrace.bean.LogBean;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.module.base.bean.UserAddress;
import cn.missfresh.module.base.common.event.o;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.manager.c;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.utils.as;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.address.bean.SelectAddressResult;
import cn.missfresh.module.user.address.presenter.d;
import cn.missfresh.module.user.address.widget.InputLayout;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.alibaba.android.arouter.b.a;

public class SelectsHomeAddressActivity extends BaseFragmentActivity implements d, g, h, InputLayout.a {
    private TextView a;
    private InputLayout j;
    private TextView k;
    private SelectsAddressModuleFragment l;
    private SearchesAddressModuleFragment m;
    private d n;
    private boolean o = false;

    @Override // cn.missfresh.basiclib.ui.permission.b
    public void l_(String str) {
    }

    /* access modifiers changed from: protected */
    public void onSaveInstanceState(Bundle bundle) {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.address.view.SelectsHomeAddressActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        SelectsHomeAddressActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(5615, false);
        super.onCreate(bundle);
        setContentView(R.layout.user_activity_select_shipping_adress);
        s();
        t();
        AppMethodBeat.o(5615);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: cn.missfresh.module.user.address.view.SelectsHomeAddressActivity */
    /* JADX WARN: Multi-variable type inference failed */
    private void s() {
        AppMethodBeat.i(5619, false);
        as.a((Activity) this, getResources().getColor(R.color.white_f5));
        this.e.setLeftButtonVisibility(0);
        this.e.setCenterVisibility(0);
        this.e.setCenterTxt("\u9009\u62e9\u6536\u8d27\u5730\u5740");
        this.e.getBottomDivider().setVisibility(8);
        if (e.o()) {
            this.e.setRightButtonColor(getResources().getColor(R.color.gray_26));
            this.e.setRightButtonJustText("\u65b0\u589e\u5730\u5740");
            this.e.setRightButtonVisibility(0);
            this.e.setRightButtonOnClickListener(new 1(this));
        }
        this.a = (TextView) findViewById(R.id.tv_select_support_city);
        this.j = findViewById(R.id.cv_input_layout);
        this.a.setOnClickListener(this);
        this.k = (TextView) findViewById(R.id.tv_cancel_search);
        this.k.setOnClickListener(this);
        this.j.getEtInput().setCompoundDrawables(null, null, null, null);
        this.j.getEtInput().setHint("\u8bf7\u8f93\u5165\u60a8\u7684\u6536\u8d27\u5730\u5740");
        this.j.setAfterTextChangedListener(this);
        this.j.setOnClickListener(this);
        this.j.setInterceptFirstTouch(true);
        this.j.setOnFirstClickListener(new 2(this));
        AppMethodBeat.o(5619);
    }

    private void t() {
        AppMethodBeat.i(5622, false);
        this.n = new d(this);
        String h = c.h();
        String a = c.a();
        if (b.a(a)) {
            a = getString(R.string.default_city);
        }
        this.n.a(h);
        this.n.b(a);
        this.a.setText(a);
        a(1);
        a(0);
        AppMethodBeat.o(5622);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.user.address.view.SelectsHomeAddressActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(5626, true);
        super.onClick(view);
        int id = view.getId();
        if (id == R.id.tv_select_support_city) {
            StatisticsManager.s("click_city", new Object[0]);
            a.a().a("/user/select_city").withString("current_name", this.n.a()).navigation(this, 17);
        } else if (id == R.id.tv_cancel_search) {
            this.j.getEtInput().setText("");
        }
        AppMethodBeat.o(5626);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onDestroy() {
        AppMethodBeat.i(5627, false);
        super.onDestroy();
        this.n.e();
        AppMethodBeat.o(5627);
    }

    /* access modifiers changed from: protected */
    public void onActivityResult(int i, int i2, Intent intent) {
        AppMethodBeat.i(5630, false);
        super.onActivityResult(i, i2, intent);
        if (i == 17) {
            this.a.getText().toString();
            if (1 != i2) {
                cn.missfresh.utils.a.d.c(this.b, "\u6ca1\u6709\u9009\u62e9\u57ce\u5e02");
            } else if (intent != null) {
                String stringExtra = intent.getStringExtra("city_name");
                String stringExtra2 = intent.getStringExtra("city_code");
                boolean booleanExtra = intent.getBooleanExtra("is_chrome_city", false);
                this.a.setText(stringExtra);
                this.n.b(stringExtra);
                this.n.a(stringExtra2);
                a(stringExtra, stringExtra2);
                String str = this.b;
                cn.missfresh.utils.a.d.c(str, "\u9009\u62e9\u57ce\u5e02\u7684\u4e3a : " + stringExtra + " code \u7801 : " + stringExtra2 + " is chrome city " + booleanExtra);
                c.q().b((UserAddress) null);
            }
        } else if (i2 == cn.missfresh.module.base.common.c.a.e && intent != null) {
            UserAddress userAddress = (UserAddress) intent.getParcelableExtra("add_address");
            if (userAddress != null) {
                c.q().b(userAddress);
                a(userAddress);
            } else {
                cn.missfresh.ui.a.a.a("\u6dfb\u52a0\u5730\u5740\u5931\u8d25");
            }
        }
        AppMethodBeat.o(5630);
    }

    private void a(String str, String str2) {
        AppMethodBeat.i(5633, false);
        c.b(str2, "", str, "", "");
        c.b(str);
        c.a("selectCityForAddress");
        c.n();
        this.n.a(true);
        AppMethodBeat.o(5633);
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void finish() {
        AppMethodBeat.i(5637, false);
        if (this.n.f()) {
            setResult(21);
            o oVar = new o(102);
            oVar.a(21);
            cn.missfresh.basiclib.utils.c.a().a(oVar);
            u();
        }
        super.finish();
        AppMethodBeat.o(5637);
    }

    private void u() {
        String str;
        AppMethodBeat.i(5639, false);
        try {
            LogBean logBean = new LogBean();
            logBean.setType("select_city");
            logBean.setInfo_str("just_select_city");
            String str2 = "";
            if (e.p() != null) {
                str = e.p().getUser_id() + str2;
            } else {
                str = "0";
            }
            logBean.setUid(str);
            if (e.p() != null) {
                str2 = e.p().getPhone_number();
            }
            logBean.setStr_value_9(str2);
            cn.missfresh.utils.a.d.a(logBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(5639);
    }

    private void b(int i) {
        AppMethodBeat.i(5642, false);
        SelectAddressResult c = this.n.c();
        if (c != null) {
            this.n.a(false);
            c.a(c.mRegionCode, c.mCityName, c.mAreaName, c.mLatitude, c.mLongitude);
            c(i);
        }
        AppMethodBeat.o(5642);
    }

    private void c(int i) {
        AppMethodBeat.i(5645, false);
        setResult(i);
        o oVar = new o(102);
        oVar.a(i);
        cn.missfresh.basiclib.utils.c.a().a(oVar);
        finish();
        AppMethodBeat.o(5645);
    }

    public void r() {
        AppMethodBeat.i(5647, false);
        v();
        AppMethodBeat.o(5647);
    }

    private void v() {
        AppMethodBeat.i(5650, false);
        int b = this.n.b();
        if (b == 16) {
            b(b);
        } else if (b == 19) {
            b(b);
        } else if (b == 17) {
            b(b);
        }
        AppMethodBeat.o(5650);
    }

    private void a(UserAddress userAddress) {
        AppMethodBeat.i(5654, false);
        if (b.a(userAddress.latLng)) {
            cn.missfresh.ui.a.a.a("\u5f53\u524d\u5730\u5740\u4e0d\u53ef\u4f7f\u7528!");
            AppMethodBeat.o(5654);
            return;
        }
        UserAddress.LatAndLng latAndLng = userAddress.getLatAndLng();
        if (latAndLng == null || !userAddress.isAvailable()) {
            cn.missfresh.ui.a.a.a("\u5f53\u524d\u5730\u5740\u4e0d\u53ef\u4f7f\u7528!");
            AppMethodBeat.o(5654);
            return;
        }
        SelectAddressResult selectAddressResult = new SelectAddressResult();
        selectAddressResult.mRegionCode = userAddress.area_code;
        selectAddressResult.mCityName = userAddress.city;
        selectAddressResult.mAreaName = userAddress.address_1;
        selectAddressResult.mLatitude = latAndLng.lat;
        selectAddressResult.mLongitude = latAndLng.lng;
        this.n.a(userAddress);
        this.n.a(selectAddressResult);
        this.n.a(16);
        r();
        String str = this.b;
        cn.missfresh.utils.a.d.c(str, "on history item click : " + userAddress.toString());
        AppMethodBeat.o(5654);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r2v0, resolved type: cn.missfresh.module.user.address.view.SelectsHomeAddressActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onPause() {
        AppMethodBeat.i(5657, false);
        super.onPause();
        q.a((FragmentActivity) this);
        AppMethodBeat.o(5657);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r1v0, resolved type: cn.missfresh.module.user.address.view.SelectsHomeAddressActivity */
    /* JADX WARN: Multi-variable type inference failed */
    public void a(SelectAddressResult selectAddressResult, int i) {
        AppMethodBeat.i(5660, false);
        this.n.d();
        this.n.a(selectAddressResult);
        this.n.a(19);
        r();
        q.a((FragmentActivity) this);
        AppMethodBeat.o(5660);
    }

    public void c(String str) {
        AppMethodBeat.i(5663, false);
        if (TextUtils.isEmpty(str)) {
            a(0);
            this.k.setVisibility(8);
        } else {
            a(1);
            this.k.setVisibility(0);
            this.e.setCenterTxt("\u786e\u8ba4\u6536\u8d27\u5730\u5740");
        }
        SearchesAddressModuleFragment searchesAddressModuleFragment = this.m;
        if (searchesAddressModuleFragment != null) {
            searchesAddressModuleFragment.a(this.n.a(), str, c.f(), c.g());
        }
        AppMethodBeat.o(5663);
    }

    /* JADX WARN: Type inference failed for: r6v1, types: [cn.missfresh.module.user.address.view.SearchesAddressModuleFragment, androidx.fragment.app.Fragment] */
    /* JADX WARN: Type inference failed for: r6v2, types: [androidx.fragment.app.Fragment, cn.missfresh.module.user.address.view.SelectsAddressModuleFragment] */
    /* JADX WARN: Type inference failed for: r2v4, types: [cn.missfresh.module.user.address.view.SearchesAddressModuleFragment, androidx.fragment.app.Fragment] */
    /* JADX WARN: Type inference failed for: r6v6, types: [androidx.fragment.app.Fragment, cn.missfresh.module.user.address.view.SelectsAddressModuleFragment] */
    /* JADX WARN: Type inference failed for: r6v7, types: [cn.missfresh.module.user.address.view.SearchesAddressModuleFragment, androidx.fragment.app.Fragment] */
    /* JADX WARN: Type inference failed for: r2v5, types: [androidx.fragment.app.Fragment, cn.missfresh.module.user.address.view.SelectsAddressModuleFragment] */
    /* JADX WARNING: Unknown variable types count: 6 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(int r6) {
        /*
            r5 = this;
            r0 = 5669(0x1625, float:7.944E-42)
            r1 = 0
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r1)
            androidx.fragment.app.FragmentManager r1 = r5.getSupportFragmentManager()
            androidx.fragment.app.FragmentTransaction r1 = r1.beginTransaction()
            if (r6 != 0) goto L_0x002e
            cn.missfresh.module.user.address.view.SelectsAddressModuleFragment r6 = r5.l
            if (r6 != 0) goto L_0x0023
            cn.missfresh.module.user.address.view.SelectsAddressModuleFragment r6 = new cn.missfresh.module.user.address.view.SelectsAddressModuleFragment
            r6.<init>()
            r5.l = r6
            int r6 = cn.missfresh.module.user.R.id.fl_select_address_container
            cn.missfresh.module.user.address.view.SelectsAddressModuleFragment r2 = r5.l
            r1.add(r6, r2)
            goto L_0x0026
        L_0x0023:
            r1.show(r6)
        L_0x0026:
            cn.missfresh.module.user.address.view.SearchesAddressModuleFragment r6 = r5.m
            if (r6 == 0) goto L_0x007b
            r1.hide(r6)
            goto L_0x007b
        L_0x002e:
            r2 = 1
            if (r6 != r2) goto L_0x007b
            cn.missfresh.module.user.address.view.SearchesAddressModuleFragment r6 = r5.m
            if (r6 != 0) goto L_0x0071
            cn.missfresh.module.user.address.view.SearchesAddressModuleFragment r6 = new cn.missfresh.module.user.address.view.SearchesAddressModuleFragment
            r6.<init>()
            r5.m = r6
            android.os.Bundle r6 = new android.os.Bundle
            r6.<init>()
            java.lang.String r3 = "EXTRAL_LABLE"
            java.lang.String r4 = "home_select_address"
            r6.putString(r3, r4)
            java.lang.String r3 = "is_use_history"
            r6.putBoolean(r3, r2)
            java.lang.String r2 = cn.missfresh.module.base.manager.c.f()
            java.lang.String r3 = "EXTRAL_LATITUDE"
            r6.putString(r3, r2)
            java.lang.String r2 = cn.missfresh.module.base.manager.c.g()
            java.lang.String r3 = "EXTRAL_LONGITUDE"
            r6.putString(r3, r2)
            cn.missfresh.module.user.address.view.SearchesAddressModuleFragment r2 = r5.m
            r2.setArguments(r6)
            int r6 = cn.missfresh.module.user.R.id.fl_select_address_container
            cn.missfresh.module.user.address.view.SearchesAddressModuleFragment r2 = r5.m
            r1.add(r6, r2)
            goto L_0x0074
        L_0x0071:
            r1.show(r6)
        L_0x0074:
            cn.missfresh.module.user.address.view.SelectsAddressModuleFragment r6 = r5.l
            if (r6 == 0) goto L_0x007b
            r1.hide(r6)
        L_0x007b:
            r1.commitAllowingStateLoss()
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.user.address.view.SelectsHomeAddressActivity.a(int):void");
    }

    public void a() {
        AppMethodBeat.i(5671, false);
        a(0);
        AppMethodBeat.o(5671);
    }

    public void d(boolean z) {
        this.o = z;
    }

    /* access modifiers changed from: protected */
    public void z() {
        AppMethodBeat.i(5677, false);
        if (Build.VERSION.SDK_INT >= 23 && this.r == null) {
            this.r = new cn.missfresh.basiclib.utils.permission.c();
        }
        AppMethodBeat.o(5677);
    }
}
