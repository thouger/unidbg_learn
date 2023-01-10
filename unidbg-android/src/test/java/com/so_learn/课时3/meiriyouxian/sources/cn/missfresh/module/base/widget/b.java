package cn.missfresh.module.base.widget;

import androidx.recyclerview.widget.GridLayoutManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: SpanSizeManager */
public class b {

    /* compiled from: SpanSizeManager */
    public interface a {
        int a(int i, int i2);
    }

    public static void a(GridLayoutManager gridLayoutManager, a aVar) {
        AppMethodBeat.i(23864, false);
        if (gridLayoutManager == null) {
            AppMethodBeat.o(23864);
            return;
        }
        gridLayoutManager.setSpanSizeLookup(new AnonymousClass1(aVar, gridLayoutManager));
        AppMethodBeat.o(23864);
    }

    /* compiled from: SpanSizeManager */
    /* renamed from: cn.missfresh.module.base.widget.b$1  reason: invalid class name */
    static class AnonymousClass1 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ a a;
        final /* synthetic */ GridLayoutManager b;

        AnonymousClass1(a aVar, GridLayoutManager gridLayoutManager) {
            this.a = aVar;
            this.b = gridLayoutManager;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int i) {
            AppMethodBeat.i(23863, false);
            a aVar = this.a;
            if (aVar != null) {
                int a = aVar.a(i, this.b.getSpanCount());
                AppMethodBeat.o(23863);
                return a;
            }
            AppMethodBeat.o(23863);
            return 0;
        }
    }
}
