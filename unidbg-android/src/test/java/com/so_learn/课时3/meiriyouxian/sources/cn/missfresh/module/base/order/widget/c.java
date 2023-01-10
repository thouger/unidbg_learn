package cn.missfresh.module.base.order.widget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;

/* compiled from: RemindEnsureDialog */
public class c extends Dialog {
    public c(Context context, String str, String str2, String str3, View.OnClickListener onClickListener) {
        super(context, R.style.my_dialog);
        AppMethodBeat.i(16338, false);
        a(context, str, str2, str3, onClickListener);
        AppMethodBeat.o(16338);
    }

    public void a(Context context, String str, String str2, String str3, View.OnClickListener onClickListener) {
        AppMethodBeat.i(16343, false);
        setContentView(R.layout.order_dialog_ensure_layout);
        setCancelable(false);
        Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = (int) (((double) defaultDisplay.getWidth()) * 0.75d);
        attributes.height = -2;
        getWindow().setAttributes(attributes);
        TextView textView = (TextView) findViewById(R.id.dialogTitle);
        TextView textView2 = (TextView) findViewById(R.id.dialogContent);
        if (!b.a(str)) {
            textView.setText(str);
        } else {
            textView.setVisibility(8);
            LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) textView2.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.topMargin = aw.b(50);
            }
        }
        textView2.setText(str2);
        TextView textView3 = (TextView) findViewById(R.id.tv_ensure);
        if (b.a(str3)) {
            textView3.setVisibility(8);
        } else {
            textView3.setVisibility(0);
            textView3.setText(str3);
        }
        textView3.setOnClickListener(new RemindEnsureDialog$1(this, onClickListener));
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        AppMethodBeat.o(16343);
    }
}
