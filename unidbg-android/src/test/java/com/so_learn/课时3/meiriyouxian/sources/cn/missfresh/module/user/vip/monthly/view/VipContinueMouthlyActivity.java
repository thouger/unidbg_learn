package cn.missfresh.module.user.vip.monthly.view;

import android.os.Bundle;
import android.widget.TextView;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.module.base.bean.AutoPayInfo;
import cn.missfresh.module.base.manager.bean.MineInfo;
import cn.missfresh.module.base.widget.SwitchButton;
import cn.missfresh.module.user.R;
import cn.missfresh.module.user.vip.monthly.a.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import io.reactivex.c.g;
import io.reactivex.q;
import java.util.concurrent.TimeUnit;

public class VipContinueMouthlyActivity extends BaseFragmentActivity implements a.d {
    private SwitchButton a;
    private TextView j;
    private TextView k;
    private a.c l;
    private AutoPayInfo m;

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.user.vip.monthly.view.VipContinueMouthlyActivity */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        VipContinueMouthlyActivity.super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(10151, false);
        super.onCreate(bundle);
        setContentView(R.layout.activity_continue_mouthly);
        r();
        s();
        t();
        AppMethodBeat.o(10151);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.user.vip.monthly.view.VipContinueMouthlyActivity */
    /* JADX WARN: Multi-variable type inference failed */
    private void r() {
        AppMethodBeat.i(10152, false);
        this.a = (SwitchButton) findViewById(R.id.switch_btn);
        this.j = (TextView) findViewById(R.id.tv_continuous_monthly);
        this.k = (TextView) findViewById(R.id.tv_vip_continuous_monthly);
        this.l = new cn.missfresh.module.user.vip.monthly.c.a(this, this);
        this.a.setOnSwitchListener(new AnonymousClass1(this));
        AppMethodBeat.o(10152);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.user.vip.monthly.view.VipContinueMouthlyActivity$1  reason: invalid class name */
    public class AnonymousClass1 implements SwitchButton.a {
        final /* synthetic */ VipContinueMouthlyActivity a;

        AnonymousClass1(VipContinueMouthlyActivity vipContinueMouthlyActivity) {
            JniLib.cV(this, vipContinueMouthlyActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.RUNNING_BACKGROUND_APPS_DIALOG));
        }

        /* JADX WARN: Type inference failed for: r2v0, types: [cn.missfresh.module.user.vip.monthly.view.VipContinueMouthlyActivity, android.app.Activity] */
        /* JADX WARNING: Unknown variable types count: 1 */
        @Override // cn.missfresh.module.base.widget.SwitchButton.a
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void a(int r14) {
            /*
                r13 = this;
                r0 = 10116(0x2784, float:1.4176E-41)
                r1 = 0
                cn.missfresh.sherlock.trace.core.AppMethodBeat.i(r0, r1)
                r1 = 1
                if (r1 != r14) goto L_0x003a
                cn.missfresh.module.user.vip.monthly.view.VipContinueMouthlyActivity$1$1 r6 = new cn.missfresh.module.user.vip.monthly.view.VipContinueMouthlyActivity$1$1
                r6.<init>(r13)
                cn.missfresh.module.user.vip.monthly.view.VipContinueMouthlyActivity$1$2 r8 = new cn.missfresh.module.user.vip.monthly.view.VipContinueMouthlyActivity$1$2
                r8.<init>(r13)
                cn.missfresh.module.user.vip.monthly.view.VipContinueMouthlyActivity r2 = r13.a
                int r14 = cn.missfresh.module.user.R.string.vip_auto_diallog_title
                java.lang.String r3 = r2.getString(r14)
                cn.missfresh.module.user.vip.monthly.view.VipContinueMouthlyActivity r14 = r13.a
                int r1 = cn.missfresh.module.user.R.string.vip_auto_diallog_content
                java.lang.String r4 = r14.getString(r1)
                cn.missfresh.module.user.vip.monthly.view.VipContinueMouthlyActivity r14 = r13.a
                int r1 = cn.missfresh.module.user.R.string.vip_auto_diallog_close
                java.lang.String r5 = r14.getString(r1)
                cn.missfresh.module.user.vip.monthly.view.VipContinueMouthlyActivity r14 = r13.a
                int r1 = cn.missfresh.module.user.R.string.vip_auto_diallog_ok
                java.lang.String r7 = r14.getString(r1)
                r9 = 0
                r10 = 0
                r11 = 1
                r12 = 0
                cn.missfresh.module.base.support.dialog.e.a(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12)
            L_0x003a:
                cn.missfresh.sherlock.trace.core.AppMethodBeat.o(r0)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.user.vip.monthly.view.VipContinueMouthlyActivity.AnonymousClass1.a(int):void");
        }
    }

    private void s() {
        AppMethodBeat.i(10156, false);
        this.l.a();
        AppMethodBeat.o(10156);
    }

    private void t() {
        AppMethodBeat.i(10158, false);
        e_(getString(R.string.continue_mouthly_title));
        this.e.setLeftButtonVisibility(0);
        this.e.setCenterVisibility(0);
        this.e.setRightButtonVisibility(0);
        this.e.a(R.drawable.icon_vip_explain_title_bar, "");
        this.e.setRightButtonOnClickListener(new 2(this));
        AppMethodBeat.o(10158);
    }

    public void a(AutoPayInfo autoPayInfo) {
        AppMethodBeat.i(10160, false);
        this.m = autoPayInfo;
        if (!b.a(autoPayInfo.getData().getMonth_fee())) {
            TextView textView = this.j;
            textView.setText(autoPayInfo.getData().getMonth_fee() + getString(R.string.vip_auto_pay_month_fee));
        }
        if (!b.a(autoPayInfo.getData().getPay_type())) {
            this.k.setText(autoPayInfo.getData().getPay_type());
        }
        AppMethodBeat.o(10160);
    }

    public void a() {
        AppMethodBeat.i(10163, false);
        K_();
        q.a(MineInfo.MineOrderStatus.DELAY).c(2, TimeUnit.SECONDS).b(io.reactivex.f.a.b()).a(io.reactivex.a.b.a.a()).e(new AnonymousClass3(this));
        AppMethodBeat.o(10163);
    }

    /* renamed from: cn.missfresh.module.user.vip.monthly.view.VipContinueMouthlyActivity$3  reason: invalid class name */
    class AnonymousClass3 implements g<String> {
        final /* synthetic */ VipContinueMouthlyActivity a;

        AnonymousClass3(VipContinueMouthlyActivity vipContinueMouthlyActivity) {
            JniLib.cV(this, vipContinueMouthlyActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.FIELD_NOTIFICATION_GROUP_ID));
        }

        @Override // io.reactivex.c.g
        public /* synthetic */ void accept(Object obj) throws Exception {
            AppMethodBeat.i(10139, false);
            a((String) obj);
            AppMethodBeat.o(10139);
        }

        public void a(String str) throws Exception {
            AppMethodBeat.i(10136, false);
            this.a.l();
            this.a.finish();
            AppMethodBeat.o(10136);
        }
    }

    public void b() {
        AppMethodBeat.i(10168, false);
        this.a.setCurrentStatus(1);
        AppMethodBeat.o(10168);
    }
}
