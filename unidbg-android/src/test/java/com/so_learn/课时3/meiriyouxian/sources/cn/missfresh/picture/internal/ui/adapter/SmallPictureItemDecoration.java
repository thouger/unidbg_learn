package cn.missfresh.picture.internal.ui.adapter;

import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class SmallPictureItemDecoration extends RecyclerView.ItemDecoration {
    private int a;

    public SmallPictureItemDecoration(int i) {
        this.a = i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        AppMethodBeat.i(18489, false);
        if (recyclerView.getChildAdapterPosition(view) == 0) {
            rect.left = (int) (((float) this.a) * 1.6f);
        } else if (recyclerView.getAdapter() == null || recyclerView.getChildAdapterPosition(view) != recyclerView.getAdapter().getItemCount() - 1) {
            rect.left = this.a;
        } else {
            int i = this.a;
            rect.left = i;
            rect.right = (int) (((float) i) * 1.6f);
        }
        AppMethodBeat.o(18489);
    }
}
