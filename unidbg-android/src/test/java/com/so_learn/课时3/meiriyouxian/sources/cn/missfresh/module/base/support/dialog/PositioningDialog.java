package cn.missfresh.module.base.support.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.WindowManager;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class PositioningDialog extends Dialog {
    public PositioningDialog(Context context) {
        super(context, R.style.my_dialog);
        AppMethodBeat.i(21617, false);
        a(context);
        AppMethodBeat.o(21617);
    }

    private void a(Context context) {
        AppMethodBeat.i(21619, false);
        setContentView(R.layout.dialog_positioning);
        Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = (int) (((double) defaultDisplay.getWidth()) * 0.75d);
        getWindow().setAttributes(attributes);
        AppMethodBeat.o(21619);
    }
}
