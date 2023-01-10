package cn.missfresh.push;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import cn.missfresh.lib.image.c;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.support.dialog.BaseTipDialog;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class PushGuideDialog extends BaseTipDialog {
    private ImageView a;
    private View.OnClickListener d = new AnonymousClass1();

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public boolean c() {
        return false;
    }

    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog, androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        AppMethodBeat.onHiddenChanged(this, z);
    }

    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        AppMethodBeat.onResume(this);
    }

    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog, androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        AppMethodBeat.setUserVisibleHint(this, z);
    }

    public PushGuideDialog() {
        AppMethodBeat.i(24476, false);
        AppMethodBeat.o(24476);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public int R_() {
        return R.layout.dialog_push_guide_tip;
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public void a(View view) {
        AppMethodBeat.i(24477, false);
        this.a = (ImageView) view.findViewById(R.id.guide_img);
        view.findViewById(R.id.left_tv).setOnClickListener(this.d);
        view.findViewById(R.id.right_tv).setOnClickListener(this.d);
        c.a(getActivity()).a(Integer.valueOf(R.drawable.base_img_push_guide)).a(this.a);
        AppMethodBeat.o(24477);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        AppMethodBeat.i(24478, false);
        super.onActivityCreated(bundle);
        AppMethodBeat.o(24478);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public WindowManager.LayoutParams a(WindowManager.LayoutParams layoutParams) {
        AppMethodBeat.i(24479, false);
        layoutParams.width = getResources().getDimensionPixelSize(R.dimen.margin_250);
        AppMethodBeat.o(24479);
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public boolean b() {
        AppMethodBeat.i(24480, false);
        dismissAllowingStateLoss();
        AppMethodBeat.o(24480);
        return true;
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismissAllowingStateLoss() {
        AppMethodBeat.i(24481, false);
        super.dismissAllowingStateLoss();
        AppMethodBeat.o(24481);
    }

    /* renamed from: cn.missfresh.push.PushGuideDialog$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(24475, false);
            if (PushGuideDialog.this.c != null) {
                if (view.getId() == R.id.left_tv) {
                    PushGuideDialog.this.c.a(1, null);
                    PushGuideDialog.this.dismissAllowingStateLoss();
                    StatisticsManager.T("click_authority_pop", "button", "1");
                } else if (view.getId() == R.id.right_tv) {
                    PushGuideDialog.this.c.a(2, null);
                    StatisticsManager.T("click_authority_pop", "button", "2");
                    PushGuideDialog.this.dismissAllowingStateLoss();
                }
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(24475);
        }
    }

    public static void a(FragmentActivity fragmentActivity, BaseTipDialog.a aVar) {
        AppMethodBeat.i(24482, false);
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        PushGuideDialog pushGuideDialog = (PushGuideDialog) supportFragmentManager.findFragmentByTag(PushGuideDialog.class.getName());
        if (pushGuideDialog == null) {
            PushGuideDialog pushGuideDialog2 = new PushGuideDialog();
            pushGuideDialog2.c = aVar;
            pushGuideDialog2.show(supportFragmentManager, PushGuideDialog.class.getName());
        } else {
            pushGuideDialog.c = aVar;
            supportFragmentManager.beginTransaction().show(pushGuideDialog).commitAllowingStateLoss();
        }
        StatisticsManager.T("exposure_authority_pop", new Object[0]);
        AppMethodBeat.o(24482);
    }
}
