package cn.missfresh.module.user.vip.experience.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.module.base.bean.ShareInfo;
import cn.missfresh.module.base.support.dialog.ShareDialog;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.module.base.widget.FitHeightImageView;
import cn.missfresh.module.base.widget.MultiStateLayout;
import cn.missfresh.module.base.widget.recycleview.ExRecyclerView;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.vip.experience.adapter.VipExperienceAdapter;
import cn.missfresh.module.user.vip.experience.b.a;
import cn.missfresh.module.user.vip.experience.bean.VipExperience;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;

public class VipExperienceActivity extends BaseFragmentActivity implements View.OnClickListener, AdapterView.OnItemClickListener, MultiStateLayout.d, a {
    private MultiStateLayout a;
    private ExRecyclerView j;
    private TextView k;
    private FitHeightImageView l;
    private Button m;
    private View n;
    private VipExperienceAdapter o;
    private a p;

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.vip.experience.view.VipExperienceActivity$1  reason: invalid class name */
    public class AnonymousClass1 implements ShareDialog.a {
        final /* synthetic */ VipExperienceActivity a;

        AnonymousClass1(VipExperienceActivity vipExperienceActivity) {
            JniLib.cV(this, vipExperienceActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.NOTIFICATION_CHANNEL_IMPORTANCE));
        }

        @Override // cn.missfresh.module.base.support.dialog.ShareDialog.a
        public void onCancelClick() {
        }

        @Override // cn.missfresh.module.base.support.dialog.ShareDialog.a
        public void onShareClick(int i) {
            if (i != 1 && i != 2) {
            }
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.vip.experience.view.VipExperienceActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        VipExperienceActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(9985, false);
        super.onCreate(bundle);
        setContentView(R.layout.activity_vip_exerience);
        s();
        r();
        AppMethodBeat.o(9985);
    }

    private void r() {
        AppMethodBeat.i(9986, false);
        this.p = new a(this);
        this.p.a();
        AppMethodBeat.o(9986);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r5v0, resolved type: cn.missfresh.module.user.vip.experience.view.VipExperienceActivity */
    /* JADX WARN: Multi-variable type inference failed */
    private void s() {
        AppMethodBeat.i(9988, false);
        this.e.setCenterTxt(getString(R.string.invite_to_experience_vip));
        this.e.setCenterVisibility(0);
        this.e.setLeftButtonVisibility(0);
        this.a = (MultiStateLayout) findViewById(R.id.multi_state_layout_new);
        this.m = (Button) findViewById(R.id.btn_invite_now);
        this.n = findViewById(R.id.v_bottom_divider);
        this.m.setOnClickListener(this);
        this.j = (ExRecyclerView) findViewById(R.id.rv_invite_experience);
        View inflate = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_vip_experience_header, (ViewGroup) null, false);
        this.l = (FitHeightImageView) inflate.findViewById(R.id.iv_vip_experience_card);
        this.l.setFixedWidth(aw.a((Context) this));
        this.k = (TextView) inflate.findViewById(R.id.tv_vip_experience_desc);
        this.j.a(inflate);
        this.j.setLayoutManager(new GridLayoutManager(this, 3));
        this.o = new VipExperienceAdapter(this);
        this.j.setOnItemClickListener(this);
        this.j.setAdapter(this.o);
        this.a.setOnRefreshListener(this);
        AppMethodBeat.o(9988);
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(9992, true);
        super.onClick(view);
        if (view.getId() == R.id.btn_invite_now) {
            t();
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(9992);
    }

    private void t() {
        AppMethodBeat.i(9995, false);
        ShareInfo c = this.p.c();
        if (c != null) {
            a(c);
        }
        AppMethodBeat.o(9995);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r11v0, resolved type: cn.missfresh.module.user.vip.experience.view.VipExperienceActivity */
    /* JADX WARN: Multi-variable type inference failed */
    public void a(ShareInfo shareInfo) {
        AppMethodBeat.i(9999, false);
        ShareDialog shareDialog = new ShareDialog(this, true);
        shareDialog.setCanceledOnTouchOutside(true);
        shareDialog.a(shareInfo.qqFriendShareType, shareInfo.qqFriendUrl, shareInfo.title, shareInfo.content, shareInfo.image_url, shareInfo.wx_url, shareInfo.friend_url, shareInfo.sina_url);
        shareDialog.a(new AnonymousClass1(this));
        shareDialog.show();
        AppMethodBeat.o(9999);
    }

    public void a(VipExperience vipExperience) {
        AppMethodBeat.i(10005, false);
        this.a.c();
        b(vipExperience);
        this.o.a(vipExperience.getMy_invites());
        if (vipExperience.getCan_invite() == 1) {
            this.m.setVisibility(0);
            this.n.setVisibility(0);
        } else {
            this.m.setVisibility(8);
            this.n.setVisibility(8);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) this.j.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.setMargins(0, 0, 0, 0);
            }
        }
        AppMethodBeat.o(10005);
    }

    private void b(VipExperience vipExperience) {
        AppMethodBeat.i(10006, false);
        this.a.c();
        this.k.setText(vipExperience.getInvite_doc());
        this.l.a(vipExperience.getCard_img());
        AppMethodBeat.o(10006);
    }

    public void a(String str) {
        AppMethodBeat.i(10008, false);
        this.a.b();
        cn.missfresh.ui.a.a.a(str);
        AppMethodBeat.o(10008);
    }

    @Override // android.widget.AdapterView.OnItemClickListener
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
        AppMethodBeat.i(10012, false);
        VipExperience.MyInvitesEntity a = this.p.a(i);
        if (a != null && !a.getIsValid() && this.p.b()) {
            t();
        }
        AppMethodBeat.o(10012);
    }

    @Override // cn.missfresh.module.base.widget.MultiStateLayout.d
    public void f_() {
        AppMethodBeat.i(10015, false);
        this.p.a();
        AppMethodBeat.o(10015);
    }
}
