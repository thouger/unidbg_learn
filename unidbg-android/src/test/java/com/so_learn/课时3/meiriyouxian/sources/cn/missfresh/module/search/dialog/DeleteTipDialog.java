package cn.missfresh.module.search.dialog;

import android.view.View;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import cn.missfresh.module.base.support.dialog.BaseTipDialog;
import cn.missfresh.module.search.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class DeleteTipDialog extends BaseTipDialog {
    private View.OnClickListener a = new 1(this);

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

    public DeleteTipDialog() {
        AppMethodBeat.i(11698, false);
        AppMethodBeat.o(11698);
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public int R_() {
        return R.layout.search_dialog_search_clear_history_tip;
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.support.dialog.BaseTipDialog
    public void a(View view) {
        AppMethodBeat.i(11699, false);
        ((TextView) view.findViewById(R.id.cancel_tv)).setOnClickListener(this.a);
        ((TextView) view.findViewById(R.id.submit_tv)).setOnClickListener(this.a);
        AppMethodBeat.o(11699);
    }

    public static void a(FragmentActivity fragmentActivity, BaseTipDialog.a aVar) {
        AppMethodBeat.i(11700, false);
        FragmentManager supportFragmentManager = fragmentActivity.getSupportFragmentManager();
        DeleteTipDialog deleteTipDialog = (DeleteTipDialog) supportFragmentManager.findFragmentByTag(DeleteTipDialog.class.getName());
        if (deleteTipDialog != null) {
            deleteTipDialog.c = aVar;
            FragmentTransaction beginTransaction = supportFragmentManager.beginTransaction();
            beginTransaction.show(deleteTipDialog);
            beginTransaction.commitAllowingStateLoss();
        } else {
            DeleteTipDialog deleteTipDialog2 = new DeleteTipDialog();
            deleteTipDialog2.c = aVar;
            deleteTipDialog2.show(supportFragmentManager, DeleteTipDialog.class.getName());
        }
        AppMethodBeat.o(11700);
    }
}
