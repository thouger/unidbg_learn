package cn.missfresh.module.base.support.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.support.AlignTextView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: NewEnsureDialog */
public class h extends Dialog {
    private TextView a;
    private boolean b = true;

    public h(Context context, String str, String str2, View.OnClickListener onClickListener, AlignTextView.Align align) {
        super(context, R.style.my_dialog);
        AppMethodBeat.i(21601, false);
        a(context, str, str2, null, onClickListener, null, align);
        AppMethodBeat.o(21601);
    }

    public h(Context context, String str, String str2, String str3, View.OnClickListener onClickListener, AlignTextView.Align align) {
        super(context, R.style.my_dialog);
        AppMethodBeat.i(21603, false);
        a(context, str2, str3, null, onClickListener, null, align);
        AppMethodBeat.o(21603);
    }

    public h(Context context, String str, String str2, String str3, String str4, View.OnClickListener onClickListener) {
        super(context, R.style.my_dialog);
        AppMethodBeat.i(21605, false);
        a(context, str, str2, str3, str4, onClickListener);
        AppMethodBeat.o(21605);
    }

    private void a(Context context, String str, String str2, String str3, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, AlignTextView.Align align) {
        AppMethodBeat.i(21609, false);
        setContentView(R.layout.dialog_new_missfresh);
        setCancelable(false);
        Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = (int) (((double) defaultDisplay.getWidth()) * 0.75d);
        getWindow().setAttributes(attributes);
        this.a = (TextView) findViewById(R.id.tv_dialog_message);
        this.a.setText(str);
        TextView textView = (TextView) findViewById(R.id.tv_ensure);
        textView.setText(str2);
        textView.setOnClickListener(new NewEnsureDialog$5(this, onClickListener));
        AppMethodBeat.o(21609);
    }

    private void a(Context context, String str, String str2, String str3, String str4, View.OnClickListener onClickListener) {
        AppMethodBeat.i(21611, false);
        setContentView(R.layout.dialog_new_missfresh);
        setCancelable(false);
        Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = (int) (((double) defaultDisplay.getWidth()) * 0.75d);
        getWindow().setAttributes(attributes);
        this.a = (TextView) findViewById(R.id.tv_dialog_message);
        this.a.setText(str2);
        TextView textView = (TextView) findViewById(R.id.tv_ensure);
        TextView textView2 = (TextView) findViewById(R.id.tv_cancel);
        TextView textView3 = (TextView) findViewById(R.id.tv_title);
        textView3.setVisibility(0);
        textView3.setText(str);
        textView.setText(str3);
        textView2.setText(str4);
        textView2.setVisibility(0);
        textView.setOnClickListener(new NewEnsureDialog$6(this, onClickListener));
        textView2.setOnClickListener(new NewEnsureDialog$7(this));
        AppMethodBeat.o(21611);
    }
}
