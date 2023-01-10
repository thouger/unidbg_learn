package cn.missfresh.module.base.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.support.view.TitleBar;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: TitleBarHelper */
public class m {
    private Context a;
    private LinearLayout b;
    private TitleBar c;
    private View d;
    private LayoutInflater e;

    public m(Context context, int i) {
        AppMethodBeat.i(13208, false);
        this.a = context;
        this.e = LayoutInflater.from(context);
        d();
        c();
        b(i);
        AppMethodBeat.o(13208);
    }

    private void c() {
        AppMethodBeat.i(13211, false);
        this.c = new TitleBar(this.a);
        this.c.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) this.a.getResources().getDimension(R.dimen.title_bar_height)));
        this.b.addView(this.c);
        AppMethodBeat.o(13211);
    }

    private void b(int i) {
        AppMethodBeat.i(13213, false);
        if (i <= 0) {
            AppMethodBeat.o(13213);
            return;
        }
        this.d = this.e.inflate(i, (ViewGroup) null, false);
        this.d.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.b.addView(this.d);
        AppMethodBeat.o(13213);
    }

    private void d() {
        AppMethodBeat.i(13215, false);
        this.b = new LinearLayout(this.a);
        this.b.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        this.b.setOrientation(1);
        this.b.setFitsSystemWindows(true);
        AppMethodBeat.o(13215);
    }

    public TitleBar a() {
        return this.c;
    }

    public View b() {
        return this.b;
    }

    public void a(int i) {
        AppMethodBeat.i(13218, false);
        this.c.setVisibility(i);
        AppMethodBeat.o(13218);
    }
}
