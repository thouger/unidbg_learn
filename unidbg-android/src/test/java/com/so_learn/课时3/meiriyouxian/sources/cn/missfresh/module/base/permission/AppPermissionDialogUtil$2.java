package cn.missfresh.module.base.permission;

import android.view.View;
import androidx.appcompat.app.AlertDialog;
import cn.missfresh.basiclib.ui.permission.b;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class AppPermissionDialogUtil$2 implements View.OnClickListener {
    final /* synthetic */ b a;
    final /* synthetic */ String b;
    final /* synthetic */ AlertDialog c;

    AppPermissionDialogUtil$2(b bVar, String str, AlertDialog alertDialog) {
        this.a = bVar;
        this.b = str;
        this.c = alertDialog;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(18650, false);
        this.a.l_(this.b);
        this.c.dismiss();
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(18650);
    }
}
