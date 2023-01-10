package cn.missfresh.ui.titlebar.a;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.R;
import cn.missfresh.ui.titlebar.TitleBar;

/* compiled from: TitleBarHelper */
public class a {
    private Context a;
    private LinearLayout b;
    private TitleBar c;
    private View d;
    private LayoutInflater e;

    public a(Context context, int i) {
        AppMethodBeat.i(2698, false);
        this.a = context;
        this.e = LayoutInflater.from(context);
        d();
        c();
        b(i);
        AppMethodBeat.o(2698);
    }

    private void c() {
        AppMethodBeat.i(2701, false);
        this.c = new TitleBar(this.a);
        this.c.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) this.a.getResources().getDimension(R.dimen.title_bar_height)));
        this.b.addView(this.c);
        AppMethodBeat.o(2701);
    }

    private void b(int i) {
        AppMethodBeat.i(2704, false);
        if (i <= 0) {
            AppMethodBeat.o(2704);
            return;
        }
        this.d = this.e.inflate(i, (ViewGroup) null, false);
        this.d.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.b.addView(this.d);
        AppMethodBeat.o(2704);
    }

    private void d() {
        AppMethodBeat.i(2708, false);
        this.b = new LinearLayout(this.a);
        this.b.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.b.setOrientation(1);
        this.b.setFitsSystemWindows(true);
        AppMethodBeat.o(2708);
    }

    public TitleBar a() {
        return this.c;
    }

    public View b() {
        return this.b;
    }

    public void a(int i) {
        AppMethodBeat.i(2715, false);
        this.c.setVisibility(i);
        AppMethodBeat.o(2715);
    }
}
