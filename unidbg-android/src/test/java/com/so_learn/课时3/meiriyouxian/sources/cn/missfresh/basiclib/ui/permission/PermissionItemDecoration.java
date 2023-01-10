package cn.missfresh.basiclib.ui.permission;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class PermissionItemDecoration extends RecyclerView.ItemDecoration {
    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        AppMethodBeat.i(4657, false);
        super.getItemOffsets(rect, view, recyclerView, state);
        AppMethodBeat.o(4657);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        AppMethodBeat.i(4659, false);
        super.onDraw(canvas, recyclerView, state);
        AppMethodBeat.o(4659);
    }
}
