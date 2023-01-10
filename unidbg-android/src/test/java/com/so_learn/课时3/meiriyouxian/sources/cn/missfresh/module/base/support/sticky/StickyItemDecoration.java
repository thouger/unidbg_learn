package cn.missfresh.module.base.support.sticky;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.List;

public class StickyItemDecoration extends RecyclerView.ItemDecoration {
    private View a;
    private int b;
    private int c;
    private c d;
    private boolean e;
    private RecyclerView.Adapter<RecyclerView.ViewHolder> f;
    private RecyclerView.ViewHolder g;
    private List<Integer> h = new ArrayList();
    private LinearLayoutManager i;
    private int j = -1;
    private Paint k;

    public StickyItemDecoration() {
        AppMethodBeat.i(22537, false);
        a();
        AppMethodBeat.o(22537);
    }

    private void a() {
        AppMethodBeat.i(22539, false);
        this.k = new Paint();
        this.k.setAntiAlias(true);
        AppMethodBeat.o(22539);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        int i;
        int i2;
        AppMethodBeat.i(22542, false);
        super.onDrawOver(canvas, recyclerView, state);
        if (recyclerView.getAdapter().getItemCount() <= 0) {
            AppMethodBeat.o(22542);
            return;
        }
        this.i = (LinearLayoutManager) recyclerView.getLayoutManager();
        this.e = false;
        int childCount = recyclerView.getChildCount();
        int i3 = 0;
        while (true) {
            if (i3 >= childCount) {
                break;
            }
            View childAt = recyclerView.getChildAt(i3);
            if (this.d.a(childAt)) {
                this.e = true;
                b(recyclerView);
                a(i3);
                if (childAt.getTop() <= 0) {
                    a(this.i.findFirstVisibleItemPosition(), recyclerView.getMeasuredWidth());
                } else if (this.h.size() > 0) {
                    if (this.h.size() == 1) {
                        a(this.h.get(0).intValue(), recyclerView.getMeasuredWidth());
                    } else {
                        int lastIndexOf = this.h.lastIndexOf(Integer.valueOf(b(i3)));
                        if (lastIndexOf >= 1) {
                            a(this.h.get(lastIndexOf - 1).intValue(), recyclerView.getMeasuredWidth());
                        }
                    }
                }
                if (childAt.getTop() <= 0 || childAt.getTop() > (i2 = this.c)) {
                    this.b = 0;
                    View a = a(recyclerView);
                    if (a != null && a.getTop() <= (i = this.c)) {
                        this.b = i - a.getTop();
                    }
                } else {
                    this.b = i2 - childAt.getTop();
                }
                a(canvas);
            } else {
                i3++;
            }
        }
        if (!this.e) {
            this.b = 0;
            if (this.i.findFirstVisibleItemPosition() + recyclerView.getChildCount() == recyclerView.getAdapter().getItemCount() && this.h.size() > 0) {
                List<Integer> list = this.h;
                a(list.get(list.size() - 1).intValue(), recyclerView.getMeasuredWidth());
            }
            a(canvas);
        }
        AppMethodBeat.o(22542);
    }

    private View a(RecyclerView recyclerView) {
        AppMethodBeat.i(22544, false);
        int childCount = recyclerView.getChildCount();
        View view = null;
        int i = 0;
        View view2 = null;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = recyclerView.getChildAt(i2);
            if (this.d.a(childAt)) {
                i++;
                view2 = childAt;
            }
            if (i == 2) {
                break;
            }
        }
        if (i >= 2) {
            view = view2;
        }
        AppMethodBeat.o(22544);
        return view;
    }

    private void a(int i, int i2) {
        RecyclerView.ViewHolder viewHolder;
        AppMethodBeat.i(22546, false);
        if (this.j == i || (viewHolder = this.g) == null) {
            AppMethodBeat.o(22546);
            return;
        }
        this.j = i;
        this.f.onBindViewHolder(viewHolder, this.j);
        c(i2);
        this.c = this.g.itemView.getBottom() - this.g.itemView.getTop();
        AppMethodBeat.o(22546);
    }

    private void a(int i) {
        AppMethodBeat.i(22547, false);
        int b = b(i);
        if (!this.h.contains(Integer.valueOf(b))) {
            this.h.add(Integer.valueOf(b));
        }
        AppMethodBeat.o(22547);
    }

    private int b(int i) {
        AppMethodBeat.i(22548, false);
        int findFirstVisibleItemPosition = this.i.findFirstVisibleItemPosition() + i;
        AppMethodBeat.o(22548);
        return findFirstVisibleItemPosition;
    }

    private void b(RecyclerView recyclerView) {
        AppMethodBeat.i(22550, false);
        if (this.f != null) {
            AppMethodBeat.o(22550);
            return;
        }
        this.f = recyclerView.getAdapter();
        this.g = this.f.onCreateViewHolder(recyclerView, this.d.a());
        this.a = this.g.itemView;
        AppMethodBeat.o(22550);
    }

    private void c(int i) {
        int i2;
        AppMethodBeat.i(22551, false);
        View view = this.a;
        if (view == null || !view.isLayoutRequested()) {
            AppMethodBeat.o(22551);
            return;
        }
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(i, 1073741824);
        ViewGroup.LayoutParams layoutParams = this.a.getLayoutParams();
        if (layoutParams == null || layoutParams.height <= 0) {
            i2 = View.MeasureSpec.makeMeasureSpec(0, 0);
        } else {
            i2 = View.MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
        }
        this.a.measure(makeMeasureSpec, i2);
        View view2 = this.a;
        view2.layout(0, 0, view2.getMeasuredWidth(), this.a.getMeasuredHeight());
        AppMethodBeat.o(22551);
    }

    private void a(Canvas canvas) {
        AppMethodBeat.i(22552, false);
        if (this.a == null) {
            AppMethodBeat.o(22552);
            return;
        }
        int save = canvas.save();
        canvas.translate(0.0f, (float) (-this.b));
        this.a.draw(canvas);
        canvas.restoreToCount(save);
        AppMethodBeat.o(22552);
    }
}
