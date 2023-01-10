package cn.missfresh.ui.skeleton.manual.a;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: ViewReplacer */
public class a {
    private static final String a = a.class.getName();
    private final View b;
    private View c;
    private int d = -1;
    private View e;
    private ViewGroup f;
    private final ViewGroup.LayoutParams g;
    private int h = 0;
    private final int i;

    static {
        AppMethodBeat.i(2174, false);
        AppMethodBeat.o(2174);
    }

    public a(View view) {
        AppMethodBeat.i(2152, false);
        this.b = view;
        this.g = this.b.getLayoutParams();
        View view2 = this.b;
        this.e = view2;
        this.i = view2.getId();
        AppMethodBeat.o(2152);
    }

    public void a(View view) {
        AppMethodBeat.i(2158, false);
        if (this.e == view) {
            AppMethodBeat.o(2158);
            return;
        }
        if (view.getParent() != null) {
            ((ViewGroup) view.getParent()).removeView(view);
        }
        if (c()) {
            this.c = view;
            this.f.removeView(this.e);
            this.c.setId(this.i);
            this.f.addView(this.c, this.h, this.g);
            this.e = this.c;
        }
        AppMethodBeat.o(2158);
    }

    public void a() {
        AppMethodBeat.i(2161, false);
        ViewGroup viewGroup = this.f;
        if (viewGroup != null) {
            viewGroup.removeView(this.e);
            this.f.addView(this.b, this.h, this.g);
            this.e = this.b;
            this.c = null;
            this.d = -1;
        }
        AppMethodBeat.o(2161);
    }

    public View b() {
        return this.c;
    }

    private boolean c() {
        int i = 0;
        AppMethodBeat.i(2170, false);
        if (this.f == null) {
            this.f = (ViewGroup) this.b.getParent();
            ViewGroup viewGroup = this.f;
            if (viewGroup == null) {
                Log.e(a, "the source view have not attach to any view");
                AppMethodBeat.o(2170);
                return false;
            }
            int childCount = viewGroup.getChildCount();
            while (true) {
                if (i >= childCount) {
                    break;
                } else if (this.b == this.f.getChildAt(i)) {
                    this.h = i;
                    break;
                } else {
                    i++;
                }
            }
        }
        AppMethodBeat.o(2170);
        return true;
    }
}
