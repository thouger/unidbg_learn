package cn.missfresh.module.user.address.widget.headergridview;

import android.view.View;
import cn.missfresh.module.user.address.widget.headergridview.StickyGridHeadersGridView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

class StickyGridHeadersGridView$e extends StickyGridHeadersGridView.f implements Runnable {
    int a;
    final /* synthetic */ StickyGridHeadersGridView b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private StickyGridHeadersGridView$e(StickyGridHeadersGridView stickyGridHeadersGridView) {
        super(stickyGridHeadersGridView, (StickyGridHeadersGridView.1) null);
        this.b = stickyGridHeadersGridView;
    }

    @Override // java.lang.Runnable
    public void run() {
        int i;
        View a;
        AppMethodBeat.i(6330, false);
        if (this.b.d) {
            AppMethodBeat.o(6330);
            return;
        }
        if (this.b.c != null && this.b.c.getCount() > 0 && (i = this.a) != -1 && i < this.b.c.getCount() && b() && (a = this.b.a(this.a)) != null) {
            StickyGridHeadersGridView stickyGridHeadersGridView = this.b;
            stickyGridHeadersGridView.a(a, StickyGridHeadersGridView.a(stickyGridHeadersGridView, this.a));
        }
        AppMethodBeat.o(6330);
    }
}
