package cn.missfresh.module.base.base;

import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class BaseVpModuleFragment extends BaseModuleFragment {
    protected String c = getClass().getSimpleName();

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.base.base.BaseVpModuleFragment */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseModuleFragment
    public void onHiddenChanged(boolean z) {
        BaseVpModuleFragment.super.onHiddenChanged(z);
        AppMethodBeat.onHiddenChanged(this, z);
    }

    public BaseVpModuleFragment() {
        AppMethodBeat.i(3298, false);
        AppMethodBeat.o(3298);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r3v0, resolved type: cn.missfresh.module.base.base.BaseVpModuleFragment */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseModuleFragment
    public void onResume() {
        AppMethodBeat.i(3303, false);
        super.onResume();
        if (getUserVisibleHint()) {
            a(true, false);
        }
        AppMethodBeat.onResume(this);
        AppMethodBeat.o(3303);
    }

    public void onPause() {
        AppMethodBeat.i(3307, false);
        super.onPause();
        if (getUserVisibleHint()) {
            a(false, false);
        }
        AppMethodBeat.o(3307);
    }

    @Override // cn.missfresh.module.base.base.BaseModuleFragment
    public void setUserVisibleHint(boolean z) {
        AppMethodBeat.i(3310, false);
        super.setUserVisibleHint(z);
        if (isResumed()) {
            a(z, true);
        }
        AppMethodBeat.o(3310);
    }

    public void a(boolean z, boolean z2) {
        if (z) {
            String str = this.c;
        } else {
            String str2 = this.c;
        }
    }
}
