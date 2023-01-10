package cn.missfresh.module.base.support.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

public class SubMenusView extends Dialog {
    private Context a;
    private View b;
    private a c;

    public interface a {
        void a();

        void a(String str);
    }

    public static class b {
        public String a;
        public int b = -1;
    }

    public SubMenusView(Context context) {
        super(context, R.style.my_dialog);
        AppMethodBeat.i(21805, false);
        this.a = context;
        setCancelable(false);
        a(context);
        AppMethodBeat.o(21805);
    }

    private void a(Context context) {
        AppMethodBeat.i(21806, false);
        this.b = new LinearLayout(context);
        this.b.setBackgroundColor(context.getResources().getColor(R.color.gray_f0));
        setContentView(this.b, new RelativeLayout.LayoutParams(((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth(), -2));
        ((LinearLayout) this.b).setOrientation(1);
        Window window = getWindow();
        window.setBackgroundDrawable(context.getResources().getDrawable(R.color.transparent));
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.dimAmount = 0.5f;
        attributes.gravity = 80;
        AppMethodBeat.o(21806);
    }

    public SubMenusView a(a aVar) {
        this.c = aVar;
        return this;
    }

    /* renamed from: cn.missfresh.module.base.support.dialog.SubMenusView$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(21804, false);
            SubMenusView.this.dismiss();
            String charSequence = ((TextView) view).getText().toString();
            if ("\u53d6\u6d88".equals(charSequence)) {
                if (SubMenusView.this.c != null) {
                    SubMenusView.this.c.a();
                }
            } else if (SubMenusView.this.c != null) {
                SubMenusView.this.c.a(charSequence);
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(21804);
        }
    }

    public SubMenusView a(List<b> list) {
        AppMethodBeat.i(21807, false);
        AnonymousClass1 r2 = new AnonymousClass1();
        if (!cn.missfresh.utils.b.a(list)) {
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, aw.b(50));
            LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(-1, 1);
            for (b bVar : list) {
                if (list.indexOf(bVar) != 0) {
                    View view = new View(this.a);
                    view.setLayoutParams(layoutParams2);
                    view.setBackgroundColor(this.a.getResources().getColor(R.color.gray_f0));
                    ((LinearLayout) this.b).addView(view);
                }
                TextView textView = new TextView(this.a);
                if (bVar.b == -1) {
                    textView.setTextColor(this.a.getResources().getColor(R.color.gray_4b));
                } else {
                    textView.setTextColor(bVar.b);
                }
                textView.setBackgroundColor(-1);
                textView.setText(bVar.a);
                textView.setTextSize(17.0f);
                textView.setGravity(17);
                textView.setOnClickListener(r2);
                textView.setLayoutParams(layoutParams);
                ((LinearLayout) this.b).addView(textView);
            }
            TextView textView2 = new TextView(this.a);
            textView2.setTextColor(this.a.getResources().getColor(R.color.gray_4b));
            textView2.setBackgroundColor(-1);
            textView2.setText("\u53d6\u6d88");
            textView2.setTextSize(17.0f);
            textView2.setGravity(17);
            textView2.setOnClickListener(r2);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(-1, aw.b(50));
            layoutParams3.setMargins(0, aw.b(5), 0, 0);
            textView2.setLayoutParams(layoutParams3);
            ((LinearLayout) this.b).addView(textView2);
        }
        AppMethodBeat.o(21807);
        return this;
    }
}
