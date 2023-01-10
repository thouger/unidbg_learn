package cn.missfresh.module.base.support.dialog;

import android.view.View;
import androidx.appcompat.app.AlertDialog;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* access modifiers changed from: package-private */
public class DialogUtils$9 implements View.OnClickListener {
    final /* synthetic */ AlertDialog a;

    DialogUtils$9(AlertDialog alertDialog) {
        this.a = alertDialog;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(21267, false);
        this.a.dismiss();
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(21267);
    }
}
