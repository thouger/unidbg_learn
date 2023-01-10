package cn.missfresh.unitepoplib.view;

import android.content.DialogInterface;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.unitepoplib.UnitePopManager;
import cn.missfresh.unitepoplib.b.a;
import cn.missfresh.unitepoplib.bean.DialogBean;
import cn.missfresh.unitepoplib.view.dialog.ConfirmAlertDialog;

public class UniteDialogFragment extends DialogFragment {
    private DialogBean mDialogBean;
    private String mQueueTag;

    @Override // androidx.fragment.app.Fragment
    public void onHiddenChanged(boolean z) {
        super.onHiddenChanged(z);
        AppMethodBeat.onHiddenChanged(this, z);
    }

    @Override // androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        AppMethodBeat.onResume(this);
    }

    @Override // androidx.fragment.app.Fragment
    public void setUserVisibleHint(boolean z) {
        super.setUserVisibleHint(z);
        AppMethodBeat.setUserVisibleHint(this, z);
    }

    public static ConfirmAlertDialog getInstance(DialogBean dialogBean, String str) {
        AppMethodBeat.i(15635, false);
        ConfirmAlertDialog confirmAlertDialog = new ConfirmAlertDialog();
        Bundle bundle = new Bundle();
        bundle.putSerializable("extra_dialog_bean", dialogBean);
        bundle.putString("queue_tag", str);
        confirmAlertDialog.setArguments(bundle);
        AppMethodBeat.o(15635);
        return confirmAlertDialog;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(15637, false);
        super.onCreate(bundle);
        a.a("dialogSDk", "UniteDialogFragment -- onCreate");
        if (getArguments() != null) {
            this.mDialogBean = (DialogBean) getArguments().getSerializable("extra_dialog_bean");
            this.mQueueTag = getArguments().getString("queue_tag");
        }
        AppMethodBeat.o(15637);
    }

    @Override // androidx.fragment.app.DialogFragment, android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialogInterface) {
        AppMethodBeat.i(15639, false);
        super.onDismiss(dialogInterface);
        a.a("dialogSDk", "UniteDialogFragment --- onDismiss --start");
        if (this.mDialogBean == null) {
            a.a("dialogSDk", "UniteDialogFragment --- onDismiss --end");
        } else {
            a.a("dialogSDk", "UniteDialogFragment --- mDialogBean === " + getDialogBean());
        }
        DialogBean dialogBean = this.mDialogBean;
        if (dialogBean != null && !dialogBean.isBlockingUp()) {
            UnitePopManager.showNextDialog(this.mQueueTag);
        }
        AppMethodBeat.o(15639);
    }

    public String getQueueTag() {
        return this.mQueueTag;
    }

    public DialogBean getDialogBean() {
        return this.mDialogBean;
    }
}
