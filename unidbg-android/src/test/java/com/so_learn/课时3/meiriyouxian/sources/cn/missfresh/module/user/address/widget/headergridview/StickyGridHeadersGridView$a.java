package cn.missfresh.module.user.address.widget.headergridview;

import android.view.View;
import cn.missfresh.module.user.address.widget.headergridview.StickyGridHeadersGridView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

class StickyGridHeadersGridView$a extends StickyGridHeadersGridView.f implements Runnable {
    final /* synthetic */ StickyGridHeadersGridView a;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    private StickyGridHeadersGridView$a(StickyGridHeadersGridView stickyGridHeadersGridView) {
        super(stickyGridHeadersGridView, (StickyGridHeadersGridView.1) null);
        this.a = stickyGridHeadersGridView;
    }

    @Override // java.lang.Runnable
    public void run() {
        AppMethodBeat.i(6328, false);
        StickyGridHeadersGridView stickyGridHeadersGridView = this.a;
        View a = stickyGridHeadersGridView.a(stickyGridHeadersGridView.e);
        if (a != null) {
            StickyGridHeadersGridView stickyGridHeadersGridView2 = this.a;
            if ((!b() || this.a.d) ? false : this.a.b(a, StickyGridHeadersGridView.a(stickyGridHeadersGridView2, stickyGridHeadersGridView2.e))) {
                StickyGridHeadersGridView stickyGridHeadersGridView3 = this.a;
                stickyGridHeadersGridView3.f = -2;
                stickyGridHeadersGridView3.setPressed(false);
                a.setPressed(false);
            } else {
                this.a.f = 2;
            }
        }
        AppMethodBeat.o(6328);
    }
}
