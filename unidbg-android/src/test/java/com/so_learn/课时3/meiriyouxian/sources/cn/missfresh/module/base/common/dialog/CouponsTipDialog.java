package cn.missfresh.module.base.common.dialog;

import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.NewUserVoucherBean;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.support.dialog.BaseTipDialog;
import cn.missfresh.module.base.utils.at;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class CouponsTipDialog extends BaseTipDialog {
    public NewUserVoucherBean a;
    private TextView d;
    private TextView e;
    private TextView f;
    private TextView g;
    private String h;
    private View.OnClickListener i = new AnonymousClass1();

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

    public CouponsTipDialog() {
        AppMethodBeat.i(11233, false);
        AppMethodBeat.o(11233);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public int R_() {
        return R.layout.dialog_coupons_tip;
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public void a(View view) {
        AppMethodBeat.i(11235, false);
        this.d = (TextView) view.findViewById(R.id.title_tv);
        this.e = (TextView) view.findViewById(R.id.coupons_number_tv);
        this.f = (TextView) view.findViewById(R.id.coupons_title_tv);
        this.g = (TextView) view.findViewById(R.id.coupons_sub_tv);
        view.findViewById(R.id.left_tv).setOnClickListener(this.i);
        view.findViewById(R.id.right_tv).setOnClickListener(this.i);
        AppMethodBeat.o(11235);
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        AppMethodBeat.i(11236, false);
        super.onActivityCreated(bundle);
        k();
        AppMethodBeat.o(11236);
    }

    private void k() {
        AppMethodBeat.i(11237, false);
        Bundle arguments = getArguments();
        if (arguments == null) {
            dismissAllowingStateLoss();
        } else {
            this.a = arguments.getSerializable("dataBean");
            NewUserVoucherBean newUserVoucherBean = this.a;
            if (newUserVoucherBean != null) {
                this.d.setText(Html.fromHtml(at.c(newUserVoucherBean.newUserVoucherMessage)));
                this.e.setText(this.a.voucherPrice);
                SpannableString spannableString = new SpannableString(String.format(getString(R.string.format_RMB), this.a.voucherPrice));
                spannableString.setSpan(new AbsoluteSizeSpan(getResources().getDimensionPixelSize(R.dimen.text_size_16)), 0, 1, 33);
                this.e.setText(spannableString);
                this.f.setText(this.a.voucherType);
                this.g.setText(this.a.voucherLimit);
            } else {
                dismissAllowingStateLoss();
            }
        }
        AppMethodBeat.o(11237);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public WindowManager.LayoutParams a(WindowManager.LayoutParams layoutParams) {
        AppMethodBeat.i(11239, false);
        layoutParams.width = getResources().getDimensionPixelSize(R.dimen.margin_250);
        AppMethodBeat.o(11239);
        return layoutParams;
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public boolean b() {
        AppMethodBeat.i(11241, false);
        dismissAllowingStateLoss();
        AppMethodBeat.o(11241);
        return true;
    }

    @Override // androidx.fragment.app.DialogFragment
    public void dismissAllowingStateLoss() {
        AppMethodBeat.i(11243, false);
        e.z(this.h);
        super.dismissAllowingStateLoss();
        AppMethodBeat.o(11243);
    }

    /* renamed from: cn.missfresh.module.base.common.dialog.CouponsTipDialog$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(11231, false);
            if (view.getId() == R.id.left_tv) {
                CouponsTipDialog.this.c.a(1, null);
                CouponsTipDialog.this.dismissAllowingStateLoss();
            } else if (view.getId() == R.id.right_tv) {
                CouponsTipDialog.this.c.a(2, null);
                CouponsTipDialog.this.dismissAllowingStateLoss();
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(11231);
        }
    }

    private void a(NewUserVoucherBean newUserVoucherBean) {
        AppMethodBeat.i(11246, false);
        Bundle arguments = getArguments();
        if (arguments == null) {
            arguments = new Bundle();
        }
        arguments.putSerializable("dataBean", newUserVoucherBean);
        setArguments(arguments);
        AppMethodBeat.o(11246);
    }

    public static void a(FragmentActivity fragmentActivity, NewUserVoucherBean newUserVoucherBean, BaseTipDialog.a aVar) {
        AppMethodBeat.i(11248, false);
        if (newUserVoucherBean == null) {
            AppMethodBeat.o(11248);
            return;
        }
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        CouponsTipDialog couponsTipDialog = (CouponsTipDialog) supportFragmentManager.findFragmentByTag(CouponsTipDialog.class.getName());
        if (couponsTipDialog == null) {
            CouponsTipDialog couponsTipDialog2 = new CouponsTipDialog();
            couponsTipDialog2.a(newUserVoucherBean);
            couponsTipDialog2.a = newUserVoucherBean;
            couponsTipDialog2.c = aVar;
            couponsTipDialog2.h = fragmentActivity.getClass().getSimpleName();
            couponsTipDialog2.show(supportFragmentManager, CouponsTipDialog.class.getName());
        } else {
            couponsTipDialog.a(newUserVoucherBean);
            couponsTipDialog.c = aVar;
            couponsTipDialog.h = fragmentActivity.getClass().getSimpleName();
            supportFragmentManager.beginTransaction().show(couponsTipDialog).commitAllowingStateLoss();
        }
        AppMethodBeat.o(11248);
    }
}
