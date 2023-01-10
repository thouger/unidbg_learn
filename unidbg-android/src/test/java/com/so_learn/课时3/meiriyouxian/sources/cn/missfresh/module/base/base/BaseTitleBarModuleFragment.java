package cn.missfresh.module.base.base;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.missfresh.module.base.helper.m;
import cn.missfresh.module.base.support.view.TitleBar;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public abstract class BaseTitleBarModuleFragment extends BaseModuleFragment {
    protected TitleBar c;
    private m d;

    /* access modifiers changed from: protected */
    public abstract int g();

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.base.base.BaseTitleBarModuleFragment */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseModuleFragment
    public void onHiddenChanged(boolean z) {
        BaseTitleBarModuleFragment.super.onHiddenChanged(z);
        AppMethodBeat.onHiddenChanged(this, z);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.base.base.BaseTitleBarModuleFragment */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseModuleFragment
    public void onResume() {
        BaseTitleBarModuleFragment.super.onResume();
        AppMethodBeat.onResume(this);
    }

    /* JADX DEBUG: Multi-variable search result rejected for r0v0, resolved type: cn.missfresh.module.base.base.BaseTitleBarModuleFragment */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // cn.missfresh.module.base.base.BaseModuleFragment
    public void setUserVisibleHint(boolean z) {
        BaseTitleBarModuleFragment.super.setUserVisibleHint(z);
        AppMethodBeat.setUserVisibleHint(this, z);
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        return a(g());
    }

    private View a(int i) {
        this.d = new m(getActivity(), i);
        this.c = this.d.a();
        return this.d.b();
    }

    /* access modifiers changed from: protected */
    public void a_(boolean z) {
        this.c.setVisibility(z ? 0 : 8);
    }
}
