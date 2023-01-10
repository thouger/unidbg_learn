package cn.missfresh.picture.internal.ui.widget;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import cn.missfresh.picture.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class IncapableDialog extends DialogFragment {
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

    public static IncapableDialog a(String str, String str2) {
        AppMethodBeat.i(19025, false);
        IncapableDialog incapableDialog = new IncapableDialog();
        Bundle bundle = new Bundle();
        bundle.putString("extra_title", str);
        bundle.putString("extra_message", str2);
        incapableDialog.setArguments(bundle);
        AppMethodBeat.o(19025);
        return incapableDialog;
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        AppMethodBeat.i(19028, false);
        String string = getArguments().getString("extra_title");
        String string2 = getArguments().getString("extra_message");
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        if (!TextUtils.isEmpty(string)) {
            builder.setTitle(string);
        }
        if (!TextUtils.isEmpty(string2)) {
            builder.setMessage(string2);
        }
        builder.setPositiveButton(R.string.button_ok, new AnonymousClass1());
        AlertDialog create = builder.create();
        AppMethodBeat.o(19028);
        return create;
    }

    /* renamed from: cn.missfresh.picture.internal.ui.widget.IncapableDialog$1  reason: invalid class name */
    class AnonymousClass1 implements DialogInterface.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            AppMethodBeat.i(18911, false);
            dialogInterface.dismiss();
            AppMethodBeat.o(18911);
        }
    }
}
