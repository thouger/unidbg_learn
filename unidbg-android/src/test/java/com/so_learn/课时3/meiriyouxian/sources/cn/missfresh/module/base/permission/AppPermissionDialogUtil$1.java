package cn.missfresh.module.base.permission;

import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import cn.missfresh.basiclib.ui.permission.b;
import cn.missfresh.basiclib.utils.permission.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class AppPermissionDialogUtil$1 implements View.OnClickListener {
    final /* synthetic */ AlertDialog a;
    final /* synthetic */ c b;
    final /* synthetic */ Fragment c;
    final /* synthetic */ b d;
    final /* synthetic */ String e;

    AppPermissionDialogUtil$1(AlertDialog alertDialog, c cVar, Fragment fragment, b bVar, String str) {
        this.a = alertDialog;
        this.b = cVar;
        this.c = fragment;
        this.d = bVar;
        this.e = str;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(18641, false);
        this.a.dismiss();
        this.b.a(this.c, this.d, this.e);
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(18641);
    }
}
