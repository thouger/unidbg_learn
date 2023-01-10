package cn.missfresh.module.base.support.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;

public class ProcessDialog {
    private View a;
    private TextView b;
    private TextView c;
    private Handler d;
    private Dialog e;
    private int f = 0;

    static /* synthetic */ void a(ProcessDialog processDialog, int i) {
        AppMethodBeat.i(21670, false);
        processDialog.b(i);
        AppMethodBeat.o(21670);
    }

    public ProcessDialog(Context context) {
        AppMethodBeat.i(21658, false);
        this.e = new Dialog(context, R.style.my_dialog);
        a(context);
        this.d = new Handler(Looper.getMainLooper());
        AppMethodBeat.o(21658);
    }

    public void a() {
        AppMethodBeat.i(21659, false);
        this.e.show();
        AppMethodBeat.o(21659);
    }

    public void b() {
        AppMethodBeat.i(21660, false);
        this.e.dismiss();
        AppMethodBeat.o(21660);
    }

    private void a(Context context) {
        AppMethodBeat.i(21663, false);
        this.a = new LinearLayout(context);
        this.a.setBackgroundResource(R.drawable.shape_round_write);
        ((LinearLayout) this.a).setGravity(17);
        ((LinearLayout) this.a).setOrientation(1);
        this.e.setContentView(this.a, new RelativeLayout.LayoutParams(aw.b(150), aw.b(94)));
        this.b = new TextView(context);
        this.b.setGravity(17);
        this.b.setTextColor(context.getResources().getColor(R.color.gray_4b));
        this.b.setTextSize(27.0f);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-2, -2);
        layoutParams.gravity = 1;
        this.b.setLayoutParams(layoutParams);
        this.b.setText("1%");
        ((LinearLayout) this.a).addView(this.b);
        this.c = new TextView(context);
        this.c.setGravity(17);
        this.b.setTextColor(context.getResources().getColor(R.color.gray_4b));
        this.b.setTextSize(13.0f);
        LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-2, -2);
        layoutParams2.gravity = 1;
        layoutParams2.setMargins(0, aw.b(10), 0, 0);
        this.b.setLayoutParams(layoutParams2);
        ((LinearLayout) this.a).addView(this.c);
        Window window = this.e.getWindow();
        window.setBackgroundDrawable(context.getResources().getDrawable(R.color.transparent));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = 0.5f;
        attributes.gravity = 17;
        AppMethodBeat.o(21663);
    }

    public void a(String str) {
        AppMethodBeat.i(21665, false);
        TextView textView = this.c;
        if (!(textView == null || str == null)) {
            textView.setText(str);
        }
        AppMethodBeat.o(21665);
    }

    public void a(int i) {
        AppMethodBeat.i(21667, false);
        this.f = i;
        if (this.b != null) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                d.d("ProcessDialog", " updateProcess main thread");
                b(i);
            } else {
                this.d.post(new AnonymousClass1(i));
            }
        }
        AppMethodBeat.o(21667);
    }

    /* renamed from: cn.missfresh.module.base.support.dialog.ProcessDialog$1  reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ int a;

        AnonymousClass1(int i) {
            this.a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(21653, false);
            ProcessDialog.a(ProcessDialog.this, this.a);
            AppMethodBeat.o(21653);
        }
    }

    private void b(int i) {
        AppMethodBeat.i(21669, false);
        TextView textView = this.b;
        textView.setText(this.f + "%");
        if (i == 100) {
            this.e.dismiss();
        }
        AppMethodBeat.o(21669);
    }
}
