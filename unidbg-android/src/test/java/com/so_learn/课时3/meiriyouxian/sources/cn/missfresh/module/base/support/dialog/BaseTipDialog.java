package cn.missfresh.module.base.support.dialog;

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
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public abstract class BaseTipDialog extends DialogFragment {
    protected Dialog b;
    protected a c;

    public interface a {
        void a(int i, Object obj);
    }

    /* access modifiers changed from: protected */
    public abstract int R_();

    /* access modifiers changed from: protected */
    public WindowManager.LayoutParams a(WindowManager.LayoutParams layoutParams) {
        return null;
    }

    /* access modifiers changed from: protected */
    public abstract void a(View view);

    /* access modifiers changed from: protected */
    public boolean b() {
        return false;
    }

    /* access modifiers changed from: protected */
    public boolean c() {
        return true;
    }

    /* access modifiers changed from: protected */
    public double d() {
        return -1.0d;
    }

    /* access modifiers changed from: protected */
    public int f() {
        return 17;
    }

    /* access modifiers changed from: protected */
    public float g() {
        return 0.6f;
    }

    /* access modifiers changed from: protected */
    public int h() {
        return -1;
    }

    /* access modifiers changed from: protected */
    public int i() {
        return 0;
    }

    /* access modifiers changed from: protected */
    public int j() {
        return 0;
    }

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

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle bundle) {
        this.b = new Dialog(getActivity(), R.style.style_dialog_transparent_bg);
        this.b.setCanceledOnTouchOutside(c());
        View inflate = LayoutInflater.from(getActivity()).inflate(R_(), (ViewGroup) null);
        a(inflate);
        this.b.setContentView(inflate);
        Window window = this.b.getWindow();
        if (window != null) {
            window.setGravity(f());
            WindowManager.LayoutParams attributes = window.getAttributes();
            if (h() >= 0) {
                attributes.width = aw.a() - h();
            }
            if (d() >= 0.0d) {
                attributes.width = (int) (((double) aw.a()) * d());
            }
            if (a(attributes) != null) {
                attributes = a(attributes);
            }
            if (j() > 0) {
                attributes.height = j();
            }
            attributes.y = i();
            attributes.dimAmount = g();
            window.addFlags(2);
            window.setAttributes(attributes);
            if (e() != -1) {
                window.setWindowAnimations(e());
            }
        }
        this.b.setOnKeyListener(new AnonymousClass1());
        return this.b;
    }

    /* renamed from: cn.missfresh.module.base.support.dialog.BaseTipDialog$1  reason: invalid class name */
    class AnonymousClass1 implements DialogInterface.OnKeyListener {
        AnonymousClass1() {
        }

        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            AppMethodBeat.i(20726, false);
            if (i == 4 && keyEvent.getRepeatCount() == 0) {
                boolean b = BaseTipDialog.this.b();
                AppMethodBeat.o(20726);
                return b;
            }
            AppMethodBeat.o(20726);
            return false;
        }
    }

    /* access modifiers changed from: protected */
    public int e() {
        return R.style.dialog_window_anim;
    }
}
