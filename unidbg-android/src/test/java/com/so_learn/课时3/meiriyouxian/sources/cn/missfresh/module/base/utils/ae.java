package cn.missfresh.module.base.utils;

import android.Manifest;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import cn.missfresh.basiclib.ui.permission.InformDialog;
import cn.missfresh.basiclib.ui.permission.PermissionExplainDialog;
import cn.missfresh.basiclib.ui.permission.a;
import cn.missfresh.basiclib.utils.permission.c;
import cn.missfresh.module.base.common.config.h;
import cn.missfresh.module.base.permission.AppPermissionDialog;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

/* compiled from: MFPermissionUtil */
public class ae {
    private c a = new c();
    private AppPermissionDialog b;
    private PermissionExplainDialog c;

    public ae() {
        AppMethodBeat.i(23348, false);
        AppMethodBeat.o(23348);
    }

    public boolean a(String... strArr) {
        AppMethodBeat.i(23349, false);
        boolean a = this.a.a(strArr);
        AppMethodBeat.o(23349);
        return a;
    }

    public void a(FragmentActivity fragmentActivity, a aVar, String... strArr) {
        AppMethodBeat.i(23350, false);
        if (fragmentActivity == null || fragmentActivity.isFinishing()) {
            if (aVar != null) {
                aVar.l_("");
            }
            AppMethodBeat.o(23350);
            return;
        }
        String[] b = this.a.b(strArr);
        if (b == null || b.length <= 0) {
            a(fragmentActivity);
            if (aVar != null) {
                aVar.t_();
            }
            AppMethodBeat.o(23350);
            return;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, b);
        this.b = (AppPermissionDialog) fragmentActivity.getSupportFragmentManager().findFragmentByTag("fragment_inform");
        AppPermissionDialog appPermissionDialog = this.b;
        if (appPermissionDialog == null) {
            this.b = AppPermissionDialog.a(arrayList);
            this.b.setCancelable(false);
            this.b.a(new AnonymousClass1(aVar, fragmentActivity, b));
            fragmentActivity.getSupportFragmentManager().beginTransaction().add(this.b, "inform").commitAllowingStateLoss();
        } else {
            appPermissionDialog.b(arrayList);
        }
        a(fragmentActivity.getSupportFragmentManager());
        fragmentActivity.getSupportFragmentManager().beginTransaction().show(this.b).commitAllowingStateLoss();
        AppMethodBeat.o(23350);
    }

    /* compiled from: MFPermissionUtil */
    /* renamed from: cn.missfresh.module.base.utils.ae$1  reason: invalid class name */
    class AnonymousClass1 implements InformDialog.a {
        final /* synthetic */ a a;
        final /* synthetic */ FragmentActivity b;
        final /* synthetic */ String[] c;

        AnonymousClass1(a aVar, FragmentActivity fragmentActivity, String[] strArr) {
            this.a = aVar;
            this.b = fragmentActivity;
            this.c = strArr;
        }

        @Override // cn.missfresh.basiclib.ui.permission.InformDialog.a
        public void a() {
            AppMethodBeat.i(23347, false);
            a aVar = this.a;
            if (aVar != null) {
                aVar.G_();
            }
            ae.this.a.a(this.b, this.a, this.c);
            AppMethodBeat.o(23347);
        }
    }

    public boolean a() {
        AppMethodBeat.i(23351, false);
        boolean a = a(h.b());
        AppMethodBeat.o(23351);
        return a;
    }

    public boolean b() {
        boolean z = false;
        AppMethodBeat.i(23352, false);
        if (a(Manifest.permission.ACCESS_FINE_LOCATION) || a(Manifest.permission.ACCESS_COARSE_LOCATION)) {
            z = true;
        }
        AppMethodBeat.o(23352);
        return z;
    }

    public boolean c() {
        AppMethodBeat.i(23353, false);
        boolean a = a("android.permission.WRITE_EXTERNAL_STORAGE");
        AppMethodBeat.o(23353);
        return a;
    }

    private void a(FragmentActivity fragmentActivity) {
        AppMethodBeat.i(23354, false);
        this.b = (AppPermissionDialog) fragmentActivity.getSupportFragmentManager().findFragmentByTag("fragment_inform");
        AppPermissionDialog appPermissionDialog = this.b;
        if (appPermissionDialog != null && appPermissionDialog.isAdded() && this.b.isVisible()) {
            this.b.dismissAllowingStateLoss();
        }
        AppMethodBeat.o(23354);
    }

    private void a(FragmentManager fragmentManager) {
        AppMethodBeat.i(23355, false);
        if (fragmentManager == null || fragmentManager.getFragments() == null || fragmentManager.getFragments().size() <= 0) {
            AppMethodBeat.o(23355);
            return;
        }
        Iterator<Fragment> it2 = fragmentManager.getFragments().iterator();
        while (true) {
            if (it2.hasNext()) {
                if (it2.next() instanceof PermissionExplainDialog) {
                    this.c = (PermissionExplainDialog) fragmentManager.findFragmentByTag("fragment_explain");
                    fragmentManager.beginTransaction().remove(this.c).commitNowAllowingStateLoss();
                    fragmentManager.getFragments().remove(this.c);
                    break;
                }
            } else {
                break;
            }
        }
        AppMethodBeat.o(23355);
    }
}
