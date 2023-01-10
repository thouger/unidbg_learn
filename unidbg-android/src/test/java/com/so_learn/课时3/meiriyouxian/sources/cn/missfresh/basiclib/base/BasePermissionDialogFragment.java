package cn.missfresh.basiclib.base;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import androidx.fragment.app.DialogFragment;
import cn.missfresh.basiclib.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public abstract class BasePermissionDialogFragment extends DialogFragment {
    /* access modifiers changed from: protected */
    public int a() {
        return 17;
    }

    /* access modifiers changed from: protected */
    public abstract void a(Bundle bundle);

    /* access modifiers changed from: protected */
    public abstract void a(View view);

    /* access modifiers changed from: protected */
    public abstract int b();

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

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setStyle(1, R.style.style_dialog_transparent_bg);
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        View inflate = layoutInflater.inflate(b(), viewGroup, false);
        a(inflate);
        return inflate;
    }

    @Override // androidx.fragment.app.Fragment
    public void onViewCreated(View view, Bundle bundle) {
        super.onViewCreated(view, bundle);
        a(getDialog());
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        a(bundle);
    }

    /* access modifiers changed from: protected */
    public void a(Dialog dialog) {
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().requestWindowFeature(1);
        dialog.setOnKeyListener(new AnonymousClass1());
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = 0.6f;
        attributes.gravity = a();
        attributes.width = -1;
        attributes.height = -2;
        window.setAttributes(attributes);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.basiclib.base.BasePermissionDialogFragment$1  reason: invalid class name */
    public class AnonymousClass1 implements DialogInterface.OnKeyListener {
        AnonymousClass1() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            AppMethodBeat.i(3167, false);
            if (keyEvent.getKeyCode() == 4) {
                AppMethodBeat.o(3167);
                return true;
            }
            AppMethodBeat.o(3167);
            return false;
        }
    }
}
