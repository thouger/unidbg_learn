package cn.missfresh.module.base.common.listener;

import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class RecycleViewScrollListener extends RecyclerView.OnScrollListener {
    private a a;

    public interface a {
        void a();

        void a(int i, int i2);
    }

    public void a(a aVar) {
        this.a = aVar;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrollStateChanged(RecyclerView recyclerView, int i) {
        AppMethodBeat.i(12078, false);
        super.onScrollStateChanged(recyclerView, i);
        a aVar = this.a;
        if (aVar != null && i == 0) {
            aVar.a();
        }
        AppMethodBeat.o(12078);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(RecyclerView recyclerView, int i, int i2) {
        AppMethodBeat.i(12079, false);
        super.onScrolled(recyclerView, i, i2);
        this.a.a(i, i2);
        AppMethodBeat.o(12079);
    }
}
