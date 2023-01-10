package cn.missfresh.module.base.base;

import android.app.ProgressDialog;
import android.content.Context;
import cn.missfresh.module.mvp.base.BaseFragment;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import de.greenrobot.event.EventBus;

public class BaseModuleFragment extends BaseFragment {
    protected String a = getClass().getSimpleName();
    protected Context b;
    private ProgressDialog c;

    public void E_() {
    }

    public boolean F_() {
        return false;
    }

    public void N_() {
    }

    /* access modifiers changed from: protected */
    public int f() {
        return 0;
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.base.base.BaseModuleFragment */
    /* JADX WARN: Multi-variable type inference failed */
    public void onHiddenChanged(boolean z) {
        BaseModuleFragment.super.onHiddenChanged(z);
        AppMethodBeat.onHiddenChanged(this, z);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.base.base.BaseModuleFragment */
    /* JADX WARN: Multi-variable type inference failed */
    public void onResume() {
        BaseModuleFragment.super.onResume();
        AppMethodBeat.onResume(this);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.base.base.BaseModuleFragment */
    /* JADX WARN: Multi-variable type inference failed */
    public void setUserVisibleHint(boolean z) {
        BaseModuleFragment.super.setUserVisibleHint(z);
        AppMethodBeat.setUserVisibleHint(this, z);
    }

    public void w_() {
    }

    public void z_() {
    }

    public BaseModuleFragment() {
        AppMethodBeat.i(3238, false);
        AppMethodBeat.o(3238);
    }

    public void onStart() {
        AppMethodBeat.i(3242, false);
        BaseModuleFragment.super.onStart();
        AppMethodBeat.o(3242);
    }

    public void onStop() {
        AppMethodBeat.i(3245, false);
        BaseModuleFragment.super.onStop();
        AppMethodBeat.o(3245);
    }

    public void X_() {
        AppMethodBeat.i(3250, false);
        g();
        if (o() && !this.c.isShowing()) {
            try {
                this.c.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        AppMethodBeat.o(3250);
    }

    public void P_() {
        AppMethodBeat.i(3254, false);
        g();
        if (o() && this.c.isShowing()) {
            try {
                this.c.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        AppMethodBeat.o(3254);
    }

    private void g() {
        AppMethodBeat.i(3258, false);
        if (this.c == null) {
            this.c = new ProgressDialog(getActivity());
            this.c.setCanceledOnTouchOutside(false);
            this.c.setMessage("\u8bf7\u7a0d\u5019...");
            this.c.setCancelable(true);
            this.c.dismiss();
        }
        AppMethodBeat.o(3258);
    }

    public void onAttach(Context context) {
        AppMethodBeat.i(3259, false);
        BaseModuleFragment.super.onAttach(context);
        this.b = context;
        AppMethodBeat.o(3259);
    }

    public void onDestroyView() {
        AppMethodBeat.i(3263, false);
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        BaseModuleFragment.super.onDestroyView();
        AppMethodBeat.o(3263);
    }
}
