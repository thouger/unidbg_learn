package cn.missfresh.ui.skeleton.auto;

import android.view.View;
import android.view.ViewGroup;

/* compiled from: LayeredViewHolder */
public class a {
    private View a;
    private int b;

    public void a(View view, int i) {
        this.a = view;
        this.b = i;
    }

    public void a() {
        this.a = null;
        this.b = 0;
    }

    public View b() {
        return this.a;
    }

    public int c() {
        return this.b;
    }

    public boolean d() {
        return !(this.a instanceof ViewGroup);
    }
}
