package cn.missfresh.module.base.support.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import java.util.List;

/* compiled from: CenterNoticeDialog */
public class c {
    private Activity a;
    private LayoutInflater b;
    private View c;
    private ImageView d;
    private View e;
    private LinearLayout f;
    private LinearLayout g;
    private Dialog h = new Dialog(this.a, R.style.my_dialog);

    public c(Activity activity) {
        AppMethodBeat.i(20764, false);
        this.a = activity;
        e();
        AppMethodBeat.o(20764);
    }

    public void a() {
        AppMethodBeat.i(20767, false);
        Activity activity = this.a;
        if (activity == null || activity.isFinishing()) {
            AppMethodBeat.o(20767);
            return;
        }
        try {
            this.h.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(20767);
    }

    public void b() {
        AppMethodBeat.i(20768, false);
        Activity activity = this.a;
        if (activity != null && !activity.isFinishing()) {
            this.h.dismiss();
        }
        AppMethodBeat.o(20768);
    }

    public boolean c() {
        AppMethodBeat.i(20770, false);
        boolean isShowing = this.h.isShowing();
        AppMethodBeat.o(20770);
        return isShowing;
    }

    private void e() {
        AppMethodBeat.i(20773, false);
        this.b = LayoutInflater.from(this.a);
        this.c = this.b.inflate(R.layout.layout_pop_center_view_notice, (ViewGroup) null);
        this.g = (LinearLayout) this.c.findViewById(R.id.ll_dialog_content);
        this.f = (LinearLayout) this.c.findViewById(R.id.notice_box_ll);
        this.d = (ImageView) this.c.findViewById(R.id.title_iv);
        this.e = this.c.findViewById(R.id.btn_intergral_task_close);
        this.h.setContentView(this.c);
        Window window = this.h.getWindow();
        window.setBackgroundDrawable(this.a.getResources().getDrawable(R.color.transparent));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = 0.5f;
        attributes.gravity = 17;
        this.e.setOnClickListener(new CenterNoticeDialog$1(this));
        AppMethodBeat.o(20773);
    }

    public void a(boolean z) {
        AppMethodBeat.i(20974, false);
        Dialog dialog = this.h;
        if (dialog != null) {
            dialog.setCancelable(z);
            this.h.setCanceledOnTouchOutside(z);
        }
        AppMethodBeat.o(20974);
    }

    public c a(List<a> list) {
        AppMethodBeat.i(20978, false);
        if (list != null && list.size() > 0) {
            for (a aVar : list) {
                d.d("CenterNoticeDialog", "add notice:" + aVar.d);
                TextView textView = new TextView(this.a);
                textView.setTextColor(aVar.c);
                textView.setGravity(aVar.f);
                textView.setText(aVar.d);
                textView.setTextSize((float) aVar.a);
                textView.getPaint().setFakeBoldText(aVar.e);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
                layoutParams.setMargins(0, aw.b(aVar.b), 0, 0);
                textView.setLayoutParams(layoutParams);
                this.f.addView(textView);
            }
        }
        AppMethodBeat.o(20978);
        return this;
    }

    public c a(View view) {
        AppMethodBeat.i(20981, false);
        a(view, true);
        AppMethodBeat.o(20981);
        return this;
    }

    public c a(View view, boolean z) {
        AppMethodBeat.i(20985, false);
        a(view, z, false);
        AppMethodBeat.o(20985);
        return this;
    }

    public c a(View view, boolean z, boolean z2) {
        AppMethodBeat.i(20986, false);
        this.g.removeAllViews();
        this.g.addView(view);
        if (z) {
            this.e.setVisibility(0);
        } else {
            this.e.setVisibility(8);
        }
        if (z2) {
            this.g.setBackgroundColor(0);
        }
        AppMethodBeat.o(20986);
        return this;
    }

    public Dialog d() {
        return this.h;
    }

    /* compiled from: CenterNoticeDialog */
    public static class a {
        int a;
        int b;
        int c;
        String d;
        boolean e = false;
        int f;

        public a(String str, int i, int i2, int i3, boolean z, int i4) {
            this.d = str;
            this.a = i;
            this.c = i2;
            this.b = i3;
            this.e = z;
            this.f = i4;
        }
    }
}
