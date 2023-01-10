package cn.missfresh.module.base.order.widget;

import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.support.dialog.BaseTipDialog;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.android.arouter.b.a;

public class RemindUnavailableDialog extends BaseTipDialog implements View.OnClickListener {
    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public double d() {
        return 0.67d;
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public int e() {
        return -1;
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

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public int R_() {
        return R.layout.order_dialog_remind_unavailable;
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public void a(View view) {
        AppMethodBeat.i(16350, false);
        ((TextView) view.findViewById(R.id.txt_contact)).setOnClickListener(this);
        ((TextView) view.findViewById(R.id.txt_wait)).setOnClickListener(this);
        AppMethodBeat.o(16350);
    }

    public static void a(FragmentActivity fragmentActivity) {
        AppMethodBeat.i(16352, false);
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        RemindUnavailableDialog remindUnavailableDialog = (RemindUnavailableDialog) supportFragmentManager.findFragmentByTag(RemindUnavailableDialog.class.getName());
        if (remindUnavailableDialog != null) {
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            beginTransaction.show(remindUnavailableDialog);
            beginTransaction.commitAllowingStateLoss();
        } else {
            new RemindUnavailableDialog().show(supportFragmentManager, RemindUnavailableDialog.class.getName());
        }
        AppMethodBeat.o(16352);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(16353, false);
        if (view.getId() == R.id.txt_contact) {
            if (getActivity() != null) {
                a.a().a("/order/custom_service").navigation();
            }
            dismiss();
            StatisticsManager.B("click_btn", "btn_type", 2);
        } else if (view.getId() == R.id.txt_wait) {
            StatisticsManager.B("click_btn", "btn_type", 3);
            dismiss();
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(16353);
    }
}
