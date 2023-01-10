package cn.missfresh.module.user.mine.view;

import android.app.Activity;
import android.os.Handler;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.manager.k;
import cn.missfresh.module.base.support.dialog.BaseTipDialog;
import cn.missfresh.module.base.support.dialog.TipsDialog;
import cn.missfresh.module.base.support.view.TitleBar;
import cn.missfresh.module.base.utils.as;
import cn.missfresh.module.mvvm.BaseMVVMActivity;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.mine.model.UserConfigViewModel;
import cn.missfresh.module.user.mine.widget.SwitchButton;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.a.a;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import com.tencent.imsdk.BaseConstants;

public class IndividuationSettingActivity extends BaseMVVMActivity implements SwitchButton.a {
    private static int d = 1;
    private static int e = 2;
    private TitleBar a;
    private SwitchButton b;
    private UserConfigViewModel c;
    private boolean f;
    private boolean h;
    private boolean i;

    /* access modifiers changed from: protected */
    public void g() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.mine.view.IndividuationSettingActivity */
    /* JADX WARN: Multi-variable type inference failed */
    public void onWindowFocusChanged(boolean z) {
        IndividuationSettingActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    static /* synthetic */ void c(IndividuationSettingActivity individuationSettingActivity) {
        AppMethodBeat.i(BaseConstants.ERR_SDK_NET_NET_UNREACH, false);
        individuationSettingActivity.r();
        AppMethodBeat.o(BaseConstants.ERR_SDK_NET_NET_UNREACH);
    }

    static /* synthetic */ void d(IndividuationSettingActivity individuationSettingActivity) {
        AppMethodBeat.i(BaseConstants.ERR_SDK_NET_RESET_BY_PEER, false);
        individuationSettingActivity.s();
        AppMethodBeat.o(BaseConstants.ERR_SDK_NET_RESET_BY_PEER);
    }

    /* access modifiers changed from: protected */
    public int M_() {
        return R.layout.activity_individuation_setting;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r4v0, resolved type: cn.missfresh.module.user.mine.view.IndividuationSettingActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void a() {
        AppMethodBeat.i(9487, false);
        as.a((Activity) this, getResources().getColor(cn.missfresh.module.base.R.color.black));
        this.a = (TitleBar) findViewById(R.id.title_bar);
        this.a.setLeftButtonVisibility(0);
        this.a.setCenterTxt("\u4e2a\u6027\u5316\u63a8\u8350\u8bbe\u7f6e");
        this.a.setCenterVisibility(0);
        this.a.setRightButtonVisibility(8);
        this.b = findViewById(R.id.toggle_btn);
        if (e.o()) {
            this.f = e.aw();
            this.b.setChecked(this.f);
            this.b.setEnabled(true);
        } else {
            this.b.setEnabled(false);
            this.b.setCheckedColor(-1118482);
        }
        this.a.setLeftButtonOnClickListener(new 1(this));
        AppMethodBeat.o(9487);
    }

    /* renamed from: cn.missfresh.module.user.mine.view.IndividuationSettingActivity$2  reason: invalid class name */
    class AnonymousClass2 implements Observer<Integer> {
        final /* synthetic */ IndividuationSettingActivity a;

        AnonymousClass2(IndividuationSettingActivity individuationSettingActivity) {
            JniLib.cV(this, individuationSettingActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.FIELD_NAV_ACTION));
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(9463, false);
            a((Integer) obj);
            AppMethodBeat.o(9463);
        }

        public void a(Integer num) {
            AppMethodBeat.i(9462, false);
            this.a.b.setEnabled(true);
            this.a.i = false;
            e.A(this.a.h);
            AppMethodBeat.o(9462);
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.user.mine.view.IndividuationSettingActivity */
    /* JADX WARN: Multi-variable type inference failed */
    /* access modifiers changed from: protected */
    public void f() {
        AppMethodBeat.i(9489, false);
        this.c = (UserConfigViewModel) a(UserConfigViewModel.class);
        this.c.b().observe(this, new AnonymousClass2(this));
        this.c.a().observe(this, new AnonymousClass3(this));
        AppMethodBeat.o(9489);
    }

    /* renamed from: cn.missfresh.module.user.mine.view.IndividuationSettingActivity$3  reason: invalid class name */
    class AnonymousClass3 implements Observer<String> {
        final /* synthetic */ IndividuationSettingActivity a;

        AnonymousClass3(IndividuationSettingActivity individuationSettingActivity) {
            JniLib.cV(this, individuationSettingActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.FINGERPRINT_REMOVE_SIDECAR));
        }

        @Override // androidx.lifecycle.Observer
        public /* synthetic */ void onChanged(Object obj) {
            AppMethodBeat.i(9471, false);
            a((String) obj);
            AppMethodBeat.o(9471);
        }

        public void a(String str) {
            AppMethodBeat.i(9468, false);
            this.a.b.setEnabled(true);
            this.a.i = true;
            this.a.b.setChecked(true ^ this.a.h);
            a.a(str);
            AppMethodBeat.o(9468);
        }
    }

    /* access modifiers changed from: protected */
    public void k() {
        AppMethodBeat.i(9492, false);
        this.b.setOnCheckedChangeListener(this);
        AppMethodBeat.o(9492);
    }

    public void a(SwitchButton switchButton, boolean z) {
        AppMethodBeat.i(9494, false);
        this.h = z;
        if (this.i) {
            this.i = false;
            AppMethodBeat.o(9494);
            return;
        }
        this.b.setEnabled(false);
        if (!z) {
            p();
        } else {
            this.c.a(d);
        }
        AppMethodBeat.o(9494);
    }

    private void n() {
        AppMethodBeat.i(9496, false);
        k.a().a(this.h);
        AppMethodBeat.o(9496);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.user.mine.view.IndividuationSettingActivity */
    /* JADX WARN: Multi-variable type inference failed */
    private void p() {
        AppMethodBeat.i(9499, false);
        TipsDialog.l().a("\u662f\u5426\u786e\u8ba4\u5173\u95ed\u4e2a\u6027\u5316\u63a8\u8350\u8bbe\u7f6e").b("\u786e\u5b9a").c("\u53d6\u6d88").a(new AnonymousClass4(this)).a((FragmentActivity) this).k();
        AppMethodBeat.o(9499);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.mine.view.IndividuationSettingActivity$4  reason: invalid class name */
    public class AnonymousClass4 implements BaseTipDialog.a {
        final /* synthetic */ IndividuationSettingActivity a;

        AnonymousClass4(IndividuationSettingActivity individuationSettingActivity) {
            JniLib.cV(this, individuationSettingActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.APPLICATIONS_STORAGE_MOVIES));
        }

        @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog.a
        public void a(int i, Object obj) {
            AppMethodBeat.i(9474, false);
            this.a.b.setEnabled(true);
            if (i == 100) {
                IndividuationSettingActivity.c(this.a);
            } else if (i == 101) {
                this.a.i = false;
                this.a.b.setChecked(!this.a.h);
            }
            AppMethodBeat.o(9474);
        }
    }

    private void r() {
        AppMethodBeat.i(BaseConstants.ERR_SDK_NET_ENCODE_FAILED, false);
        new Handler().postDelayed(new 5(this), 200);
        AppMethodBeat.o(BaseConstants.ERR_SDK_NET_ENCODE_FAILED);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.user.mine.view.IndividuationSettingActivity */
    /* JADX WARN: Multi-variable type inference failed */
    private void s() {
        AppMethodBeat.i(BaseConstants.ERR_SDK_NET_AUTH_INVALID, false);
        TipsDialog.l().a("\u5173\u95ed\u540e\u53ef\u80fd\u4f1a\u5f71\u54cd\u60a8\u5728app\u4e2d\u7684\u8d2d\u4e70\u4f53\u9a8c\uff0c\u5efa\u8bae\u60a8\u5bf9\u8be5\u529f\u80fd\u4fdd\u6301\u5f00\u542f").b("\u4ecd\u7136\u5173\u95ed").c("\u53d6\u6d88").a(new AnonymousClass6(this)).a((FragmentActivity) this).k();
        AppMethodBeat.o(BaseConstants.ERR_SDK_NET_AUTH_INVALID);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.mine.view.IndividuationSettingActivity$6  reason: invalid class name */
    public class AnonymousClass6 implements BaseTipDialog.a {
        final /* synthetic */ IndividuationSettingActivity a;

        AnonymousClass6(IndividuationSettingActivity individuationSettingActivity) {
            JniLib.cV(this, individuationSettingActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_TEXT_SELECTION_MENU_ITEM_ASSIST));
        }

        @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog.a
        public void a(int i, Object obj) {
            AppMethodBeat.i(9478, false);
            this.a.b.setEnabled(true);
            if (i == 100) {
                this.a.c.a(this.a.h ? IndividuationSettingActivity.d : IndividuationSettingActivity.e);
            } else if (i == 101) {
                this.a.i = false;
                this.a.b.setChecked(!this.a.h);
            }
            AppMethodBeat.o(9478);
        }
    }

    /* access modifiers changed from: protected */
    public void onDestroy() {
        AppMethodBeat.i(BaseConstants.ERR_SDK_NET_UNCOMPRESS_FAILED, false);
        IndividuationSettingActivity.super.onDestroy();
        if (e.aw() != this.f) {
            n();
        }
        AppMethodBeat.o(BaseConstants.ERR_SDK_NET_UNCOMPRESS_FAILED);
    }
}
