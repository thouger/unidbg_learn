package cn.missfresh.module.base.support.dialog;

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

/* compiled from: CommonEnsureDialog */
public class d extends Dialog {
    public d(Context context, String str, String str2, String str3, String str4, View.OnClickListener onClickListener) {
        super(context, R.style.my_dialog);
        AppMethodBeat.i(21007, false);
        a(context, str, str2, str3, str4, onClickListener);
        AppMethodBeat.o(21007);
    }

    public void a(Context context, String str, String str2, String str3, String str4, View.OnClickListener onClickListener) {
        AppMethodBeat.i(21010, false);
        setContentView(R.layout.dialog_integral_layout);
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
        if (b.a(str4)) {
            textView3.setVisibility(8);
        } else {
            textView3.setVisibility(0);
            textView3.setText(str4);
        }
        textView3.setOnClickListener(new CommonEnsureDialog$1(this, onClickListener));
        TextView textView4 = (TextView) findViewById(R.id.tv_search);
        if (b.a(str3)) {
            textView4.setVisibility(8);
        } else {
            textView4.setVisibility(0);
            textView4.setText(str3);
        }
        if (textView3.getVisibility() == 0 && textView4.getVisibility() == 0) {
            textView3.setBackgroundResource(R.drawable.shape_corners_8_ff4891_b_r);
            textView4.setBackgroundResource(R.drawable.shape_corners_8_c6_b_l);
        } else if (textView3.getVisibility() == 0 && textView4.getVisibility() == 8) {
            textView3.setBackgroundResource(R.drawable.shape_corners_8_ff4891_b);
        } else if (textView3.getVisibility() == 8 && textView4.getVisibility() == 0) {
            textView4.setBackgroundResource(R.drawable.shape_corners_8_c6_b);
        }
        textView4.setOnClickListener(new CommonEnsureDialog$2(this));
        setCanceledOnTouchOutside(true);
        setCancelable(true);
        AppMethodBeat.o(21010);
    }
}
