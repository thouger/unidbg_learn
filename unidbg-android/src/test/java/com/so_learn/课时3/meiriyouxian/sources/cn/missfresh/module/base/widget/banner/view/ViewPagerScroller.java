package cn.missfresh.module.base.widget.banner.view;

import android.content.Context;
import android.widget.Scroller;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class ViewPagerScroller extends Scroller {
    private int a = 800;
    private boolean b;

    public ViewPagerScroller(Context context) {
        super(context);
    }

    @Override // android.widget.Scroller
    public void startScroll(int i, int i2, int i3, int i4) {
        int i5 = 0;
        AppMethodBeat.i(24006, false);
        if (!this.b) {
            i5 = this.a;
        }
        super.startScroll(i, i2, i3, i4, i5);
        AppMethodBeat.o(24006);
    }

    @Override // android.widget.Scroller
    public void startScroll(int i, int i2, int i3, int i4, int i5) {
        int i6 = 0;
        AppMethodBeat.i(24007, false);
        if (!this.b) {
            i6 = this.a;
        }
        super.startScroll(i, i2, i3, i4, i6);
        AppMethodBeat.o(24007);
    }

    public int a() {
        return this.a;
    }

    public void a(int i) {
        this.a = i;
    }
}
