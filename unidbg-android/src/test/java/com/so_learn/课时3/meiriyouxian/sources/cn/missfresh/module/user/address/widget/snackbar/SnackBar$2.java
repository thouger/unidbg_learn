package cn.missfresh.module.user.address.widget.snackbar;

import android.view.View;
import cn.missfresh.module.user.address.widget.snackbar.SnackBar;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

class SnackBar$2 implements SnackBar.SnackBarLayout.a {
    final /* synthetic */ SnackBar a;

    SnackBar$2(SnackBar snackBar) {
        this.a = snackBar;
    }

    public void a(View view, int i, int i2, int i3, int i4) {
        AppMethodBeat.i(6803, false);
        SnackBar.a(this.a);
        SnackBar.b(this.a).setOnLayoutChangeListener((SnackBar.SnackBarLayout.a) null);
        AppMethodBeat.o(6803);
    }
}
