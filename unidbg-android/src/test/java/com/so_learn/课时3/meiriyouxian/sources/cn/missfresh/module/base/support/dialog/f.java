package cn.missfresh.module.base.support.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.support.AlignTextView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;

/* compiled from: EnsureDialog */
public class f extends Dialog {
    private TextView a;
    private boolean b = true;

    public f(Context context, int i, String str) {
        super(context, R.style.my_dialog);
        AppMethodBeat.i(21411, false);
        setContentView(R.layout.dialog_binding_success);
        if (i != 0) {
            ((ImageView) findViewById(R.id.dialog_icon)).setImageResource(i);
        }
        if (!TextUtils.isEmpty(str)) {
            ((TextView) findViewById(R.id.dialog_content)).setText(str);
        }
        AppMethodBeat.o(21411);
    }

    public f(Context context, String str, String str2, String str3, String str4, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        super(context, R.style.my_dialog);
        AppMethodBeat.i(21414, false);
        setContentView(R.layout.dialog_missfresh_with_title);
        setCancelable(false);
        Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = (int) (((double) defaultDisplay.getWidth()) * 0.75d);
        getWindow().setAttributes(attributes);
        ((TextView) findViewById(R.id.tv_dialog_title)).setText(str);
        ((TextView) findViewById(R.id.tv_dialog_message)).setText(str2);
        TextView textView = (TextView) findViewById(R.id.tv_ensure);
        textView.setText(str3);
        textView.setOnClickListener(new EnsureDialog$1(this, onClickListener));
        TextView textView2 = (TextView) findViewById(R.id.tv_search);
        View findViewById = findViewById(R.id.v_split_line);
        if (b.a(str4)) {
            findViewById.setVisibility(8);
            textView2.setVisibility(8);
        } else {
            findViewById.setVisibility(0);
            textView2.setVisibility(0);
            textView2.setText(str4);
            textView2.setOnClickListener(new EnsureDialog$2(this, onClickListener2));
        }
        AppMethodBeat.o(21414);
    }

    public f(Context context, String str, String str2, String str3, String str4, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, String str5) {
        super(context, R.style.my_dialog);
        AppMethodBeat.i(21420, false);
        setContentView(R.layout.dialog_missfresh_with_title_new);
        setCancelable(false);
        Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = (int) (((double) defaultDisplay.getWidth()) * 0.75d);
        getWindow().setAttributes(attributes);
        ((TextView) findViewById(R.id.tv_dialog_title)).setText(str);
        ((TextView) findViewById(R.id.tv_dialog_message)).setText(str2);
        TextView textView = (TextView) findViewById(R.id.tv_ensure);
        textView.setText(str3);
        textView.setOnClickListener(new EnsureDialog$5(this, onClickListener));
        TextView textView2 = (TextView) findViewById(R.id.tv_search);
        View findViewById = findViewById(R.id.v_split_line);
        if (b.a(str4)) {
            findViewById.setVisibility(8);
            textView2.setVisibility(8);
        } else {
            findViewById.setVisibility(0);
            textView2.setVisibility(0);
            textView2.setText(str4);
            textView2.setOnClickListener(new EnsureDialog$6(this, onClickListener2));
        }
        AppMethodBeat.o(21420);
    }

    public f(Context context, String str, String str2, View.OnClickListener onClickListener) {
        super(context, R.style.my_dialog);
        AppMethodBeat.i(21424, false);
        a(context, str, str2, null, onClickListener, null, AlignTextView.Align.ALIGN_CENTER);
        AppMethodBeat.o(21424);
    }

    public f(Context context, String str, String str2, View.OnClickListener onClickListener, AlignTextView.Align align) {
        super(context, R.style.my_dialog);
        AppMethodBeat.i(21427, false);
        a(context, str, str2, null, onClickListener, null, align);
        AppMethodBeat.o(21427);
    }

    public f(Context context, String str, String str2, String str3, View.OnClickListener onClickListener, View.OnClickListener onClickListener2) {
        super(context, R.style.my_dialog);
        AppMethodBeat.i(21430, false);
        a(context, str, str2, str3, onClickListener, onClickListener2, AlignTextView.Align.ALIGN_CENTER);
        AppMethodBeat.o(21430);
    }

    private void a(Context context, String str, String str2, String str3, View.OnClickListener onClickListener, View.OnClickListener onClickListener2, AlignTextView.Align align) {
        AppMethodBeat.i(21435, false);
        setContentView(R.layout.dialog_missfresh);
        setCancelable(false);
        Display defaultDisplay = ((Activity) context).getWindowManager().getDefaultDisplay();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = (int) (((double) defaultDisplay.getWidth()) * 0.75d);
        getWindow().setAttributes(attributes);
        this.a = (TextView) findViewById(R.id.tv_dialog_message);
        this.a.setText(str);
        TextView textView = (TextView) findViewById(R.id.tv_ensure);
        textView.setText(str2);
        textView.setOnClickListener(new EnsureDialog$9(this, onClickListener));
        TextView textView2 = (TextView) findViewById(R.id.tv_search);
        View findViewById = findViewById(R.id.v_split_line);
        if (b.a(str3)) {
            findViewById.setVisibility(8);
            textView2.setVisibility(8);
        } else {
            findViewById.setVisibility(0);
            textView2.setVisibility(0);
            textView2.setText(str3);
            textView2.setOnClickListener(new EnsureDialog$10(this, onClickListener2));
        }
        AppMethodBeat.o(21435);
    }

    public void a(boolean z) {
        this.b = z;
    }
}
