package cn.missfresh.module.user.address.view;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.opengl.GLES20;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import androidx.appcompat.widget.AppCompatRadioButton;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import cn.missfresh.basiclib.utils.permission.c;
import cn.missfresh.location_api.bean.Location;
import cn.missfresh.module.base.base.BaseModuleFragment;
import cn.missfresh.module.base.bean.UserAddress;
import cn.missfresh.module.base.common.providers.ILocationService;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.support.dialog.PositioningDialog;
import cn.missfresh.module.base.support.dialog.e;
import cn.missfresh.module.base.utils.at;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.address.bean.Address;
import cn.missfresh.module.user.address.bean.SelectAddressResult;
import cn.missfresh.module.user.address.presenter.a;
import cn.missfresh.module.user.address.widget.ChangeAddressDialog;
import cn.missfresh.module.user.address.widget.superswitchbutton.SuperSwitchButton;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.tencent.smtt.sdk.TbsReaderView;
import com.xiaomi.mipush.sdk.Constants;
import io.reactivex.c.g;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EditAddressModuleFragment extends BaseModuleFragment implements TextWatcher, View.OnClickListener, a {
    private boolean A;
    private boolean B;
    private String C = "0";
    private String D = "1";
    private c E;
    private b F;
    private RadioGroup G;
    private AppCompatRadioButton H;
    private AppCompatRadioButton I;
    private int J;
    private int K;
    private RadioGroup L;
    private String M;
    private RadioButton N;
    private RadioButton O;
    private RadioButton P;
    private RadioButton Q;
    private RadioButton R;
    private ILocationService S;
    private boolean T = false;
    private String U = "";
    private int V = 0;
    private EditText c;
    private EditText d;
    private EditText e;
    private View f;
    private View g;
    private View h;
    private View i;
    private View j;
    private TextView k;
    private SuperSwitchButton l;
    private View m;
    private View n;
    private View o;
    private View p;
    private ViewGroup q;
    private a r;
    private a s;
    private ChangeAddressDialog t;
    private String u;
    private PositioningDialog v;
    private double w = 0.0d;
    private double x = 0.0d;
    private UserAddress y;
    private boolean z;

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable editable) {
    }

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.address.view.EditAddressModuleFragment */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseModuleFragment
    public void onHiddenChanged(boolean z) {
        EditAddressModuleFragment.super.onHiddenChanged(z);
        AppMethodBeat.onHiddenChanged(this, z);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.address.view.EditAddressModuleFragment */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseModuleFragment
    public void onResume() {
        EditAddressModuleFragment.super.onResume();
        AppMethodBeat.onResume(this);
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.address.view.EditAddressModuleFragment */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseModuleFragment
    public void setUserVisibleHint(boolean z) {
        EditAddressModuleFragment.super.setUserVisibleHint(z);
        AppMethodBeat.setUserVisibleHint(this, z);
    }

    static /* synthetic */ void a(EditAddressModuleFragment editAddressModuleFragment) {
        AppMethodBeat.i(5195, false);
        editAddressModuleFragment.A();
        AppMethodBeat.o(5195);
    }

    static /* synthetic */ void b(EditAddressModuleFragment editAddressModuleFragment) {
        AppMethodBeat.i(5198, false);
        editAddressModuleFragment.B();
        AppMethodBeat.o(5198);
    }

    static /* synthetic */ void c(EditAddressModuleFragment editAddressModuleFragment) {
        AppMethodBeat.i(5201, false);
        editAddressModuleFragment.C();
        AppMethodBeat.o(5201);
    }

    static /* synthetic */ void d(EditAddressModuleFragment editAddressModuleFragment) {
        AppMethodBeat.i(5204, false);
        editAddressModuleFragment.H();
        AppMethodBeat.o(5204);
    }

    static /* synthetic */ UserAddress g(EditAddressModuleFragment editAddressModuleFragment) {
        AppMethodBeat.i(5212, false);
        UserAddress T = editAddressModuleFragment.T();
        AppMethodBeat.o(5212);
        return T;
    }

    @Override // cn.missfresh.module.base.base.BaseModuleFragment
    public void onAttach(Context context) {
        AppMethodBeat.i(5064, false);
        super.onAttach(context);
        this.s = (a) context;
        AppMethodBeat.o(5064);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        AppMethodBeat.i(5066, false);
        View inflate = layoutInflater.inflate(R.layout.user_fragment_edit_address, viewGroup, false);
        AppMethodBeat.o(5066);
        return inflate;
    }

    public void onViewCreated(View view, Bundle bundle) {
        AppMethodBeat.i(5067, false);
        if (view != null) {
            a(view);
            D();
        }
        AppMethodBeat.o(5067);
    }

    private void a(View view) {
        AppMethodBeat.i(5070, false);
        this.N = (RadioButton) view.findViewById(R.id.rb_home_address_tags);
        this.O = (RadioButton) view.findViewById(R.id.rb_parent_address_tags);
        this.P = (RadioButton) view.findViewById(R.id.rb_company_address_tags);
        this.Q = (RadioButton) view.findViewById(R.id.rb_school_address_tags);
        this.R = (RadioButton) view.findViewById(R.id.rb_other_address_tags);
        this.N.setOnClickListener(this);
        this.O.setOnClickListener(this);
        this.P.setOnClickListener(this);
        this.Q.setOnClickListener(this);
        this.R.setOnClickListener(this);
        this.L = (RadioGroup) view.findViewById(R.id.cv_edit_address_tags);
        this.G = (RadioGroup) view.findViewById(R.id.rg_select_sex);
        this.G.setOnCheckedChangeListener(new 1(this));
        this.H = (AppCompatRadioButton) view.findViewById(R.id.rg_sex_lady);
        this.I = (AppCompatRadioButton) view.findViewById(R.id.rg_sex_sir);
        this.q = (ViewGroup) view.findViewById(R.id.fl_container);
        this.c = (EditText) view.findViewById(R.id.et_edit_address_receiver);
        this.d = (EditText) view.findViewById(R.id.et_edit_address_tel);
        this.e = (EditText) view.findViewById(R.id.et_edit_doorplate);
        this.k = (TextView) view.findViewById(R.id.tv_edit_detail_address);
        this.l = view.findViewById(R.id.ssb_default_address_switch);
        this.l.setOnCheckedChangeListener(new 5(this));
        this.m = view.findViewById(R.id.btn_save_address);
        this.n = view.findViewById(R.id.btn_delete_address);
        this.o = view.findViewById(R.id.rl_address_receiver_parent);
        this.p = view.findViewById(R.id.rl_address_mobile_parent);
        this.j = view.findViewById(R.id.ll_receiver);
        view.findViewById(R.id.ll_edit_detail_address).setOnClickListener(this);
        this.m.setOnClickListener(this);
        this.n.setOnClickListener(this);
        this.f = view.findViewById(R.id.iv_receiver_delete);
        this.g = view.findViewById(R.id.iv_tel_delete);
        this.i = view.findViewById(R.id.iv_doorplate_delete);
        this.h = view.findViewById(R.id.ll_contacts);
        this.c.setOnFocusChangeListener(new 6(this));
        this.d.setOnFocusChangeListener(new 7(this));
        this.e.setOnFocusChangeListener(new 8(this));
        this.c.addTextChangedListener(new 9(this));
        this.d.addTextChangedListener(new 10(this));
        this.e.addTextChangedListener(new 12(this));
        this.f.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.i.setOnClickListener(this);
        view.findViewById(R.id.tv_contacts).setOnClickListener(this);
        AppMethodBeat.o(5070);
    }

    private boolean x() {
        AppMethodBeat.i(5071, false);
        boolean z = !TextUtils.isEmpty(this.c.getText().toString());
        AppMethodBeat.o(5071);
        return z;
    }

    private boolean y() {
        AppMethodBeat.i(5072, false);
        boolean z = !TextUtils.isEmpty(this.d.getText().toString());
        AppMethodBeat.o(5072);
        return z;
    }

    private boolean z() {
        AppMethodBeat.i(5074, false);
        boolean z = !TextUtils.isEmpty(this.e.getText().toString());
        AppMethodBeat.o(5074);
        return z;
    }

    private void A() {
        AppMethodBeat.i(5075, false);
        if (!this.z || !x()) {
            this.f.setVisibility(8);
        } else {
            this.f.setVisibility(0);
        }
        AppMethodBeat.o(5075);
    }

    private void B() {
        AppMethodBeat.i(5077, false);
        if (!this.A || !y()) {
            this.g.setVisibility(8);
        } else {
            this.g.setVisibility(0);
        }
        AppMethodBeat.o(5077);
    }

    private void C() {
        AppMethodBeat.i(5079, false);
        if (!this.B || !z()) {
            this.i.setVisibility(8);
        } else {
            this.i.setVisibility(0);
        }
        AppMethodBeat.o(5079);
    }

    private void D() {
        AppMethodBeat.i(5080, false);
        this.r = new a(this);
        Bundle arguments = getArguments();
        int i = cn.missfresh.module.base.common.c.a.h;
        if (arguments != null) {
            this.U = arguments.getString("lat_lng");
            this.V = arguments.getInt("match_scope", 0);
            boolean z = arguments.getBoolean("is_new_user");
            this.l.setChecked(!arguments.getBoolean("have_address"));
            int i2 = arguments.getInt("is_edit_mode");
            String string = arguments.getString("order_id");
            if (!z || i2 != cn.missfresh.module.base.common.c.a.j) {
                this.h.setVisibility(0);
            }
            this.r.b(z);
            if (i2 == cn.missfresh.module.base.common.c.a.j || i2 == cn.missfresh.module.base.common.c.a.i) {
                UserAddress userAddress = (UserAddress) arguments.getParcelable("user_address");
                if (userAddress != null) {
                    this.M = userAddress.tag;
                    b(userAddress);
                }
            } else {
                this.H.setChecked(true);
                this.r.a(new UserAddress());
                this.n.setVisibility(8);
                this.r.d();
            }
            if (i2 == cn.missfresh.module.base.common.c.a.i) {
                this.n.setVisibility(0);
            }
            if (i2 == cn.missfresh.module.base.common.c.a.j) {
                this.c.setEnabled(false);
                this.c.setTextColor(getResources().getColor(R.color.gray_96));
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
                this.p.setLayoutParams(layoutParams);
                this.o.setLayoutParams(layoutParams);
                this.h.setVisibility(8);
            }
            this.r.a(string);
            i = i2;
        }
        if (i == cn.missfresh.module.base.common.c.a.h) {
            this.N.performClick();
        }
        this.r.a(i);
        this.r.a(true);
        AppMethodBeat.o(5080);
    }

    private void b(UserAddress userAddress) {
        AppMethodBeat.i(5082, false);
        this.r.a(userAddress);
        this.c.setText(userAddress.name);
        if (userAddress.gender == 1) {
            this.H.setChecked(true);
        } else if (userAddress.gender == 2) {
            this.I.setChecked(true);
        } else {
            this.H.setChecked(false);
            this.I.setChecked(false);
        }
        if (userAddress.optimal == 1) {
            this.l.setChecked(true);
            this.C = "1";
        } else {
            this.l.setChecked(false);
            this.C = "0";
        }
        String str = userAddress.tag;
        if ("COMPANY".equals(str)) {
            this.D = "3";
            this.P.setChecked(true);
        } else if ("HOME".equals(str)) {
            this.D = "1";
            this.N.setChecked(true);
        } else if ("SCHOOL".equals(str)) {
            this.D = "4";
            this.Q.setChecked(true);
        } else if ("PARENT".equals(str)) {
            this.D = "2";
            this.O.setChecked(true);
        } else if ("OTHER".equals(str)) {
            this.D = "5";
            this.R.setChecked(true);
        }
        this.d.setText(userAddress.phone_number);
        this.k.setText(userAddress.address_1);
        this.e.setText(userAddress.address_2);
        this.u = userAddress.area_code;
        if (userAddress.getLatAndLng() != null) {
            this.w = Double.valueOf(userAddress.getLatAndLng().lat).doubleValue();
            this.x = Double.valueOf(userAddress.getLatAndLng().lng).doubleValue();
        }
        String str2 = this.a;
        d.d(str2, "serLatAndLng:" + this.w + Constants.ACCEPT_TIME_SEPARATOR_SP + this.x);
        AppMethodBeat.o(5082);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(5085, true);
        int id = view.getId();
        if (id == R.id.ll_edit_detail_address) {
            I();
        } else if (id == R.id.btn_save_address) {
            L();
            StatisticsManager.t("click_save", "isDefault", this.C, "tag", this.D);
            if (this.r.a() == cn.missfresh.module.base.common.c.a.i) {
                d("edit");
            } else if (this.r.a() == cn.missfresh.module.base.common.c.a.h) {
                if (this.r.h()) {
                    StatisticsManager.b("click_saveAddress", new Object[0]);
                }
                d("add");
            }
        } else if (id == R.id.btn_delete_address) {
            StatisticsManager.t("click_delete", "isDefault", this.C, "tag", this.D);
            K();
            StatisticsManager.f(getContext(), "click_deleteAddress", null);
        } else if (id == R.id.iv_receiver_delete) {
            this.c.setText("");
        } else if (id == R.id.iv_tel_delete) {
            this.d.setText("");
        } else if (id == R.id.iv_doorplate_delete) {
            this.e.setText("");
        } else if (id == R.id.tv_contacts) {
            E();
        } else if (id == R.id.rb_home_address_tags) {
            a("HOME", "1");
        } else if (id == R.id.rb_parent_address_tags) {
            a("PARENT", "2");
        } else if (id == R.id.rb_company_address_tags) {
            a("COMPANY", "3");
        } else if (id == R.id.rb_school_address_tags) {
            a("SCHOOL", "4");
        } else if (id == R.id.rb_other_address_tags) {
            a("OTHER", "5");
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(5085);
    }

    private void d(String str) {
        AppMethodBeat.i(5087, false);
        HashMap hashMap = new HashMap();
        hashMap.put("page_type", str);
        StatisticsManager.f(getContext(), "click_saveAddress", hashMap);
        AppMethodBeat.o(5087);
    }

    private void E() {
        AppMethodBeat.i(5088, false);
        F();
        AppMethodBeat.o(5088);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: cn.missfresh.module.user.address.view.EditAddressModuleFragment */
    /* JADX WARN: Multi-variable type inference failed */
    private void F() {
        AppMethodBeat.i(5090, false);
        G();
        if (this.F == null) {
            this.F = new b();
        }
        cn.missfresh.module.base.permission.a.a((Fragment) this, Manifest.permission.READ_CONTACTS, (cn.missfresh.basiclib.ui.permission.b) this.F, this.E);
        AppMethodBeat.o(5090);
    }

    private void G() {
        AppMethodBeat.i(5091, false);
        if (this.E == null) {
            this.E = new c();
        }
        AppMethodBeat.o(5091);
    }

    /* access modifiers changed from: private */
    public class b implements cn.missfresh.basiclib.ui.permission.b {
        private b() {
        }

        @Override // cn.missfresh.basiclib.ui.permission.b
        public void l_(String str) {
            AppMethodBeat.i(5055, false);
            cn.missfresh.ui.a.a.b("\u9700\u8981\u5728\u624b\u673a\u8bbe\u7f6e\u4e2d\u6253\u5f00\u6bcf\u65e5\u4f18\u9c9c\u7684\u901a\u8baf\u5f55\u6743\u9650");
            AppMethodBeat.o(5055);
        }

        @Override // cn.missfresh.basiclib.ui.permission.b
        public void t_() {
            AppMethodBeat.i(5056, false);
            EditAddressModuleFragment.d(EditAddressModuleFragment.this);
            AppMethodBeat.o(5056);
        }
    }

    private void H() {
        AppMethodBeat.i(5092, false);
        startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), 258);
        AppMethodBeat.o(5092);
    }

    private void I() {
        AppMethodBeat.i(5094, false);
        J();
        AppMethodBeat.o(5094);
    }

    private void J() {
        AppMethodBeat.i(5096, false);
        X();
        this.T = true;
        this.r.a(this.S);
        G();
        AppMethodBeat.o(5096);
    }

    private void K() {
        AppMethodBeat.i(5097, false);
        if (this.r.a() == cn.missfresh.module.base.common.c.a.i) {
            this.r.e();
        }
        AppMethodBeat.o(5097);
    }

    private void L() {
        AppMethodBeat.i(5099, false);
        if (!S()) {
            AppMethodBeat.o(5099);
            return;
        }
        if (this.r.a() == cn.missfresh.module.base.common.c.a.h) {
            M();
        } else if (this.r.a() == cn.missfresh.module.base.common.c.a.i) {
            N();
        } else if (this.r.a() == cn.missfresh.module.base.common.c.a.j) {
            this.r.f();
        }
        AppMethodBeat.o(5099);
    }

    private void M() {
        AppMethodBeat.i(5100, false);
        this.r.a(O(), this.U, String.valueOf(this.V));
        AppMethodBeat.o(5100);
    }

    private void N() {
        AppMethodBeat.i(5101, false);
        this.r.b(O(), this.U, String.valueOf(this.V));
        AppMethodBeat.o(5101);
    }

    private UserAddress O() {
        AppMethodBeat.i(5104, false);
        String obj = this.c.getText().toString();
        String obj2 = this.d.getText().toString();
        String obj3 = this.e.getText().toString();
        UserAddress T = T();
        T.name = obj;
        T.phone_number = obj2;
        T.defaultAddress = false;
        T.tag = this.M;
        T.area_code = this.u;
        T.address_2 = obj3;
        T.address_detail = T.address_1 + obj3;
        T.latLng = T().latLng;
        T.gender = this.J;
        T.optimal = this.K;
        String str = this.a;
        d.d(str, "defalutAddress:" + T.defaultAddress + " tag:" + T.tag + " areacode:" + T.area_code + " address2:" + obj3 + " addressdetail:" + T.address_detail);
        AppMethodBeat.o(5104);
        return T;
    }

    public UserAddress g() {
        AppMethodBeat.i(5106, false);
        UserAddress O = O();
        AppMethodBeat.o(5106);
        return O;
    }

    private void e(String str) {
        AppMethodBeat.i(5109, false);
        if (this.t != null || getActivity() == null || getActivity().isFinishing()) {
            P();
        } else {
            this.t = new ChangeAddressDialog(getActivity(), str, this.y);
            this.t.a(new AnonymousClass13());
            P();
        }
        AppMethodBeat.o(5109);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.address.view.EditAddressModuleFragment$13  reason: invalid class name */
    public class AnonymousClass13 implements ChangeAddressDialog.b {
        AnonymousClass13() {
        }

        public void a(Address address, Address address2, Address address3) {
            AppMethodBeat.i(5046, true);
            StringBuilder sb = new StringBuilder();
            if (address != null) {
                sb.append(address.name);
                EditAddressModuleFragment.this.r.b().province = address.name;
                EditAddressModuleFragment.this.u = address.code;
            }
            if (address2 != null) {
                sb.append(address2.name);
                EditAddressModuleFragment.this.u = address2.code;
                EditAddressModuleFragment.this.r.b().city = address2.name;
            }
            EditAddressModuleFragment.this.k.setText("");
            EditAddressModuleFragment.g(EditAddressModuleFragment.this).address_1 = "";
            AppMethodBeat.o(5046);
        }
    }

    private void P() {
        AppMethodBeat.i(5111, false);
        if (getActivity() != null && !getActivity().isFinishing() && !this.t.isShowing()) {
            this.t.show();
        }
        AppMethodBeat.o(5111);
    }

    public void k() {
        AppMethodBeat.i(5112, false);
        X_();
        AppMethodBeat.o(5112);
    }

    public void a(String str) {
        AppMethodBeat.i(5114, false);
        cn.missfresh.ui.a.a.a(str);
        P_();
        AppMethodBeat.o(5114);
    }

    public void a(UserAddress userAddress, String str) {
        AppMethodBeat.i(5115, false);
        P_();
        if (!cn.missfresh.utils.b.a(str)) {
            cn.missfresh.ui.a.a.a(str);
        }
        a aVar = this.s;
        if (aVar != null) {
            aVar.a(userAddress);
        }
        AppMethodBeat.o(5115);
    }

    public void l() {
        AppMethodBeat.i(5118, false);
        X_();
        AppMethodBeat.o(5118);
    }

    public void m() {
        AppMethodBeat.i(5119, false);
        Q();
        P_();
        AppMethodBeat.o(5119);
    }

    private void Q() {
        AppMethodBeat.i(5121, false);
        cn.missfresh.ui.a.a.a("\u7f51\u7edc\u72b6\u51b5\u4e0d\u597d\uff0c\u8bf7\u91cd\u8bd5");
        AppMethodBeat.o(5121);
    }

    private void f(String str) {
        AppMethodBeat.i(GLES20.GL_INT, false);
        cn.missfresh.ui.a.a.a(str);
        AppMethodBeat.o(GLES20.GL_INT);
    }

    public void r() {
        AppMethodBeat.i(GLES20.GL_UNSIGNED_INT, false);
        P_();
        this.r.b((UserAddress) null);
        if (getActivity() != null) {
            Intent intent = new Intent();
            intent.putExtra("delete_address_id", this.r.b().id);
            getActivity().setResult(cn.missfresh.module.base.common.c.a.f, intent);
            getActivity().finish();
        }
        AppMethodBeat.o(GLES20.GL_UNSIGNED_INT);
    }

    public void s() {
        AppMethodBeat.i(5127, false);
        X_();
        AppMethodBeat.o(5127);
    }

    public void t() {
        AppMethodBeat.i(5130, false);
        Q();
        P_();
        AppMethodBeat.o(5130);
    }

    public void b(String str) {
        AppMethodBeat.i(5132, false);
        f(str);
        P_();
        AppMethodBeat.o(5132);
    }

    public void b(UserAddress userAddress, String str) {
        AppMethodBeat.i(5134, false);
        P_();
        if (!cn.missfresh.utils.b.a(str)) {
            cn.missfresh.ui.a.a.a(str);
        }
        this.r.b(userAddress);
        if (getActivity() != null) {
            Intent intent = new Intent();
            intent.putExtra("edit_address", userAddress);
            getActivity().setResult(cn.missfresh.module.base.common.c.a.d, intent);
            getActivity().finish();
        }
        AppMethodBeat.o(5134);
    }

    public void b(boolean z) {
        AppMethodBeat.i(5136, false);
        if (!z) {
            X_();
        }
        AppMethodBeat.o(5136);
    }

    public void c(boolean z) {
        AppMethodBeat.i(5137, false);
        P_();
        e(this.r.c());
        AppMethodBeat.o(5137);
    }

    public void d(boolean z) {
        AppMethodBeat.i(5138, false);
        Q();
        P_();
        AppMethodBeat.o(5138);
    }

    public void u() {
        AppMethodBeat.i(5141, false);
        U();
        AppMethodBeat.o(5141);
    }

    /* JADX DEBUG: Can't convert new array creation: APUT found in different block: 0x00bc: APUT  (r14v3 java.lang.Object[]), (1 ??[boolean, int, float, short, byte, char]), (r9v2 java.lang.String) */
    public void a(double d, double d2, String str, String str2) {
        AppMethodBeat.i(5143, false);
        V();
        int i = (T() == null || !at.a(str2, T().city)) ? 1 : 0;
        if (TextUtils.isEmpty(this.k.getText())) {
            this.w = 0.0d;
            this.x = 0.0d;
        }
        String str3 = this.r.b().city != null ? this.r.b().city : str2;
        com.alibaba.android.arouter.b.a.a().a("/user/search_address").withString("region_code", str3).withString("city_name", str3).withInt("EXTRA_SHOW_TYPE", i).withDouble("EXTRA_SER_LAT", this.w).withDouble("EXTRA_SER_LNG", this.x).withDouble("EXTRA_CUR_LAT", d).withDouble("EXTRA_CUR_LNG", d2).withString("EXTRA_ERAM_NAME", T().address_1).withString("EXTRA_LOCATION_CITY", str2).navigation(getActivity(), 257);
        R();
        Object[] objArr = new Object[2];
        objArr[0] = "isValid";
        objArr[1] = (d == 0.0d && d2 == 0.0d) ? "0" : "1";
        StatisticsManager.u("page_view", objArr);
        AppMethodBeat.o(5143);
    }

    public void v() {
        AppMethodBeat.i(5146, false);
        V();
        com.alibaba.android.arouter.b.a.a().a("/user/search_address").withString("region_code", this.r.b().city).withString("city_name", this.r.b().city).withInt("EXTRA_SHOW_TYPE", 1).withDouble("EXTRA_SER_LAT", this.w).withDouble("EXTRA_SER_LNG", this.x).withDouble("EXTRA_CUR_LAT", 0.0d).withDouble("EXTRA_CUR_LNG", 0.0d).withString("EXTRA_ERAM_NAME", T().address_1).withString("EXTRA_LOCATION_CITY", "").navigation(getActivity(), 257);
        R();
        AppMethodBeat.o(5146);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.address.view.EditAddressModuleFragment$2  reason: invalid class name */
    public class AnonymousClass2 implements g<cn.missfresh.module.user.a.b> {
        AnonymousClass2() {
        }

        @Override // io.reactivex.c.g
        public /* synthetic */ void accept(Object obj) throws Exception {
            AppMethodBeat.i(4960, false);
            a((cn.missfresh.module.user.a.b) obj);
            AppMethodBeat.o(4960);
        }

        public void a(cn.missfresh.module.user.a.b bVar) throws Exception {
            AppMethodBeat.i(4958, false);
            try {
                SelectAddressResult a = bVar.a();
                if (a != null) {
                    EditAddressModuleFragment.this.k.setText(a.mAreaName);
                    EditAddressModuleFragment.g(EditAddressModuleFragment.this).full_address = a.mAddress;
                    EditAddressModuleFragment.g(EditAddressModuleFragment.this).latLng = a.getLocation();
                    EditAddressModuleFragment.g(EditAddressModuleFragment.this).address_1 = a.mAreaName;
                    EditAddressModuleFragment.g(EditAddressModuleFragment.this).area = a.mDistrict;
                    EditAddressModuleFragment.g(EditAddressModuleFragment.this).area_code = a.mRegionCode;
                    EditAddressModuleFragment.g(EditAddressModuleFragment.this).province = a.mProvinceName;
                    EditAddressModuleFragment.g(EditAddressModuleFragment.this).city = a.mCityName;
                    EditAddressModuleFragment.g(EditAddressModuleFragment.this).poi_id = a.mPoiId;
                    EditAddressModuleFragment.this.u = a.mRegionCode;
                    EditAddressModuleFragment.this.w = Double.valueOf(a.mLatitude).doubleValue();
                    EditAddressModuleFragment.this.x = Double.valueOf(a.mLongitude).doubleValue();
                    if (!cn.missfresh.utils.b.a(a.mDistrict)) {
                        String str = a.mDistrict;
                    }
                }
            } catch (Exception e) {
                d.b("TAG", e.getMessage());
            }
            AppMethodBeat.o(4958);
        }
    }

    private void R() {
        AppMethodBeat.i(5149, false);
        a(cn.missfresh.basiclib.utils.c.a().a(cn.missfresh.module.user.a.b.class, new AnonymousClass2()));
        AppMethodBeat.o(5149);
    }

    public void w() {
        AppMethodBeat.i(5153, false);
        Q();
        AppMethodBeat.o(5153);
    }

    public void a(UserAddress userAddress) {
        AppMethodBeat.i(5155, false);
        if (userAddress != null) {
            this.y = userAddress;
            if (!TextUtils.isEmpty(userAddress.name)) {
                this.c.setText(userAddress.name);
                this.c.setSelection(userAddress.name.length());
            }
            try {
                if (!TextUtils.isEmpty(userAddress.phone_number)) {
                    this.d.setText(userAddress.phone_number);
                    this.d.setSelection(userAddress.phone_number.length());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            StringBuilder sb = new StringBuilder();
            if (!TextUtils.isEmpty(userAddress.province)) {
                sb.append(userAddress.province);
            }
            if (!TextUtils.isEmpty(userAddress.city)) {
                sb.append(userAddress.city);
            }
            this.r.b().province = userAddress.province;
            this.r.b().city = userAddress.city;
        }
        AppMethodBeat.o(5155);
    }

    public void c(String str) {
        AppMethodBeat.i(5158, false);
        cn.missfresh.ui.a.a.a(str);
        if (getActivity() != null) {
            Intent intent = new Intent();
            intent.putExtra("change_receiver", true);
            getActivity().setResult(cn.missfresh.module.base.common.c.a.g, intent);
            getActivity().finish();
        }
        AppMethodBeat.o(5158);
    }

    public void a(String str, boolean z, String str2, String str3) {
        AppMethodBeat.i(5160, false);
        if (!z) {
            cn.missfresh.ui.a.a.a(str);
        } else if (q.a((Activity) getActivity())) {
            e.a(getActivity(), str2, str3, "\u53d6\u6d88", null, "\u8054\u7cfb\u5ba2\u670d", new 11(this), null, null, true, true);
        }
        AppMethodBeat.o(5160);
    }

    private boolean S() {
        AppMethodBeat.i(5165, false);
        if (TextUtils.isEmpty(this.c.getText().toString())) {
            cn.missfresh.ui.a.a.b(getString(R.string.address_submit_receiver_error));
            AppMethodBeat.o(5165);
            return false;
        }
        String obj = this.d.getText().toString();
        if (TextUtils.isEmpty(obj) || !at.a(obj)) {
            cn.missfresh.ui.a.a.b(getString(R.string.address_phone_error));
            AppMethodBeat.o(5165);
            return false;
        } else if (TextUtils.isEmpty(this.k.getText().toString())) {
            cn.missfresh.ui.a.a.b(getString(R.string.hint_address_search_chooseaddress));
            AppMethodBeat.o(5165);
            return false;
        } else if (TextUtils.isEmpty(this.e.getText().toString())) {
            cn.missfresh.ui.a.a.b(getString(R.string.hint_address_search_writehomenubr));
            AppMethodBeat.o(5165);
            return false;
        } else {
            AppMethodBeat.o(5165);
            return true;
        }
    }

    private UserAddress T() {
        AppMethodBeat.i(5168, false);
        UserAddress b2 = this.r.b();
        AppMethodBeat.o(5168);
        return b2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x006e, code lost:
        if (r0 == null) goto L_0x0092;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0070, code lost:
        r0.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x007d, code lost:
        if (r0 != null) goto L_0x0070;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0086, code lost:
        if (r11 == null) goto L_0x0097;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0092, code lost:
        if (r11 != null) goto L_0x0094;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0094, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0097, code lost:
        cn.missfresh.sherlock.trace.core.AppMethodBeat.o(5171);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x009a, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String[] a(android.net.Uri r11) {
        /*
            r10 = this;
            r0 = 0
            r1 = 5171(0x1433, float:7.246E-42)
            cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r1, r0)
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]
            android.content.Context r3 = r10.getContext()
            android.content.ContentResolver r3 = r3.getContentResolver()
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            r4 = r3
            r5 = r11
            android.database.Cursor r11 = r4.query(r5, r6, r7, r8, r9)
            if (r11 == 0) goto L_0x0092
            r11.moveToFirst()     // Catch:{ Exception -> 0x0082 }
            java.lang.String r4 = "display_name"
            int r4 = r11.getColumnIndex(r4)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r4 = r11.getString(r4)     // Catch:{ Exception -> 0x0082 }
            r2[r0] = r4     // Catch:{ Exception -> 0x0082 }
            java.lang.String r0 = "_id"
            int r0 = r11.getColumnIndex(r0)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r0 = r11.getString(r0)     // Catch:{ Exception -> 0x0082 }
            android.net.Uri r5 = android.provider.ContactsContract.CommonDataKinds.Phone.CONTENT_URI     // Catch:{ Exception -> 0x0082 }
            r6 = 0
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0082 }
            r4.<init>()     // Catch:{ Exception -> 0x0082 }
            java.lang.String r7 = "contact_id="
            r4.append(r7)     // Catch:{ Exception -> 0x0082 }
            r4.append(r0)     // Catch:{ Exception -> 0x0082 }
            java.lang.String r7 = r4.toString()     // Catch:{ Exception -> 0x0082 }
            r8 = 0
            r9 = 0
            r4 = r3
            android.database.Cursor r0 = r4.query(r5, r6, r7, r8, r9)     // Catch:{ Exception -> 0x0082 }
            if (r0 == 0) goto L_0x007d
            r0.moveToFirst()     // Catch:{ Exception -> 0x006a }
            java.lang.String r3 = "data1"
            int r3 = r0.getColumnIndex(r3)     // Catch:{ Exception -> 0x006a }
            r4 = 1
            java.lang.String r3 = r0.getString(r3)     // Catch:{ Exception -> 0x006a }
            r2[r4] = r3     // Catch:{ Exception -> 0x006a }
            goto L_0x007d
        L_0x0068:
            r3 = move-exception
            goto L_0x0074
        L_0x006a:
            r3 = move-exception
            r3.printStackTrace()     // Catch:{ all -> 0x0068 }
            if (r0 == 0) goto L_0x0092
        L_0x0070:
            r0.close()
            goto L_0x0092
        L_0x0074:
            if (r0 == 0) goto L_0x0079
            r0.close()
        L_0x0079:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            throw r3
        L_0x007d:
            if (r0 == 0) goto L_0x0092
            goto L_0x0070
        L_0x0080:
            r0 = move-exception
            goto L_0x0089
        L_0x0082:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ all -> 0x0080 }
            if (r11 == 0) goto L_0x0097
            goto L_0x0094
        L_0x0089:
            if (r11 == 0) goto L_0x008e
            r11.close()
        L_0x008e:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            throw r0
        L_0x0092:
            if (r11 == 0) goto L_0x0097
        L_0x0094:
            r11.close()
        L_0x0097:
            cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r1)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.user.address.view.EditAddressModuleFragment.a(android.net.Uri):java.lang.String[]");
    }

    private String g(String str) {
        AppMethodBeat.i(5174, false);
        if (TextUtils.isEmpty(str)) {
            AppMethodBeat.o(5174);
            return "";
        }
        Matcher matcher = Pattern.compile("[0-9]+").matcher(str);
        StringBuilder sb = new StringBuilder("");
        while (matcher.find()) {
            sb.append(matcher.group());
        }
        String sb2 = sb.toString();
        if (sb2.startsWith("86")) {
            sb2 = sb2.substring(2);
        }
        if (sb2.trim().length() > 11) {
            String substring = sb2.substring(sb2.trim().length() - 11, sb2.trim().length());
            AppMethodBeat.o(5174);
            return substring;
        }
        AppMethodBeat.o(5174);
        return sb2;
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        AppMethodBeat.i(5177, false);
        super.onActivityResult(i, i2, intent);
        if (i == 258 && i2 == -1) {
            if (intent != null) {
                String[] a = a(intent.getData());
                try {
                    if (!TextUtils.isEmpty(a[0])) {
                        this.c.setText(a[0]);
                        this.c.setSelection(a[0].length());
                    } else {
                        this.c.setText("");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    String g = g(a[1]);
                    if (!TextUtils.isEmpty(g)) {
                        this.d.setText(g);
                        this.d.setSelection(g.length());
                    } else {
                        this.d.setText("");
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                this.j.requestFocus();
            }
            AppMethodBeat.o(5177);
            return;
        }
        AppMethodBeat.o(5177);
    }

    public void onDestroy() {
        AppMethodBeat.i(5181, false);
        super.onDestroy();
        this.r.i();
        this.r.g();
        ILocationService iLocationService = this.S;
        if (iLocationService != null) {
            iLocationService.a().c();
        }
        AppMethodBeat.o(5181);
    }

    private void U() {
        AppMethodBeat.i(5183, false);
        if (W()) {
            if (this.v == null) {
                this.v = new PositioningDialog(getActivity());
            }
            if (o()) {
                try {
                    this.v.setOnDismissListener(new 3(this));
                    this.v.show();
                } catch (Exception unused) {
                }
            }
        }
        AppMethodBeat.o(5183);
    }

    private void a(String str, String str2) {
        AppMethodBeat.i(5184, false);
        if (cn.missfresh.utils.b.a(this.M) || !this.M.equals(str)) {
            this.M = str;
            this.D = str2;
            StatisticsManager.t("click_tag", "tag", this.D);
        } else {
            this.M = "";
            this.D = "";
            this.L.clearCheck();
        }
        AppMethodBeat.o(5184);
    }

    private void V() {
        PositioningDialog positioningDialog;
        AppMethodBeat.i(5186, false);
        if (W() && (positioningDialog = this.v) != null && positioningDialog.isShowing()) {
            this.v.dismiss();
        }
        AppMethodBeat.o(5186);
    }

    private boolean W() {
        boolean z = false;
        AppMethodBeat.i(5189, false);
        if (o() && isAdded() && !isHidden()) {
            z = true;
        }
        AppMethodBeat.o(5189);
        return z;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.user.address.view.EditAddressModuleFragment */
    /* JADX WARN: Multi-variable type inference failed */
    private void X() {
        AppMethodBeat.i(5191, false);
        if (this.S == null) {
            this.S = (ILocationService) com.alibaba.android.arouter.b.a.a().a("/user/location_service").navigation();
            this.S.a().b().a(this, new AnonymousClass4());
        }
        AppMethodBeat.o(5191);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.address.view.EditAddressModuleFragment$4  reason: invalid class name */
    public class AnonymousClass4 implements Observer<Location> {
        AnonymousClass4() {
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(TbsReaderView.ReaderCallback.SHOW_DIALOG, false);
            a((Location) obj);
            AppMethodBeat.o(TbsReaderView.ReaderCallback.SHOW_DIALOG);
        }

        public void a(Location location) {
            AppMethodBeat.i(4984, false);
            if (!EditAddressModuleFragment.this.T) {
                AppMethodBeat.o(4984);
                return;
            }
            EditAddressModuleFragment.this.T = false;
            EditAddressModuleFragment.this.r.a(location);
            AppMethodBeat.o(4984);
        }
    }
}
