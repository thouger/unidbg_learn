package cn.missfresh.unitepoplib.view;

import android.app.Dialog;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.unitepoplib.UnitePopManager;
import cn.missfresh.unitepoplib.bean.DialogBean;

/* compiled from: UniteDialog */
public class a extends Dialog {
    private String a;
    private DialogBean b;

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        AppMethodBeat.i(15630, false);
        super.dismiss();
        DialogBean dialogBean = this.b;
        if (dialogBean != null && !dialogBean.isBlockingUp()) {
            UnitePopManager.showNextDialog(this.a);
        }
        AppMethodBeat.o(15630);
    }
}
