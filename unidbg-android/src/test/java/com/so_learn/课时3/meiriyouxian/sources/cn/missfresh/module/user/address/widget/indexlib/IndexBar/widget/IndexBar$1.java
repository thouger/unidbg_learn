package cn.missfresh.module.user.address.widget.indexlib.IndexBar.widget;

import cn.missfresh.module.user.address.widget.indexlib.IndexBar.widget.IndexBar;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

class IndexBar$1 implements IndexBar.onIndexPressedListener {
    final /* synthetic */ IndexBar a;

    IndexBar$1(IndexBar indexBar) {
        this.a = indexBar;
    }

    public void a(int i, String str) {
        int a;
        AppMethodBeat.i(6599, false);
        if (IndexBar.a(this.a) != null) {
            IndexBar.a(this.a).setVisibility(0);
            IndexBar.a(this.a).setText(str);
        }
        if (!(IndexBar.b(this.a) == null || (a = IndexBar.a(this.a, str)) == -1)) {
            IndexBar.b(this.a).scrollToPositionWithOffset(a, 0);
        }
        AppMethodBeat.o(6599);
    }

    public void onMotionEventEnd() {
        AppMethodBeat.i(6600, false);
        if (IndexBar.a(this.a) != null) {
            IndexBar.a(this.a).setVisibility(8);
        }
        AppMethodBeat.o(6600);
    }
}
