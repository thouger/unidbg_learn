package cn.missfresh.module.base.permission;

import android.view.View;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentActivity;
import cn.missfresh.basiclib.ui.permission.b;
import cn.missfresh.basiclib.utils.permission.c;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class AppPermissionDialogUtil$3 implements View.OnClickListener {
    final /* synthetic */ AlertDialog a;
    final /* synthetic */ c b;
    final /* synthetic */ FragmentActivity c;
    final /* synthetic */ b d;
    final /* synthetic */ String e;

    AppPermissionDialogUtil$3(AlertDialog alertDialog, c cVar, FragmentActivity fragmentActivity, b bVar, String str) {
        this.a = alertDialog;
        this.b = cVar;
        this.c = fragmentActivity;
        this.d = bVar;
        this.e = str;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(18655, false);
        this.a.dismiss();
        this.b.a(this.c, this.d, this.e);
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(18655);
    }
}
