package cn.missfresh.module.user.address.widget.indexlib.suspension;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import java.util.List;

public class SuspensionDecoration extends RecyclerView.ItemDecoration {
    private static int f = Color.parseColor("#FFDFDFDF");
    private static int g = Color.parseColor("#FF999999");
    private static int h;
    private List<? extends a> a;
    private Paint b;
    private Rect c;
    private LayoutInflater d;
    private int e;
    private int i = 0;
    private a j;

    static {
        AppMethodBeat.i(6768, false);
        AppMethodBeat.o(6768);
    }

    public SuspensionDecoration(Context context, List<? extends a> list) {
        AppMethodBeat.i(6738, false);
        this.a = list;
        this.b = new Paint();
        this.c = new Rect();
        this.e = (int) TypedValue.applyDimension(1, 30.0f, context.getResources().getDisplayMetrics());
        h = (int) TypedValue.applyDimension(2, 16.0f, context.getResources().getDisplayMetrics());
        this.b.setTextSize((float) h);
        this.b.setAntiAlias(true);
        this.d = LayoutInflater.from(context);
        AppMethodBeat.o(6738);
    }

    public SuspensionDecoration a(int i) {
        this.e = i;
        return this;
    }

    public SuspensionDecoration b(int i) {
        f = i;
        return this;
    }

    public SuspensionDecoration c(int i) {
        g = i;
        return this;
    }

    public SuspensionDecoration d(int i) {
        AppMethodBeat.i(6745, false);
        this.b.setTextSize((float) i);
        AppMethodBeat.o(6745);
        return this;
    }

    public SuspensionDecoration a(List<? extends a> list) {
        this.a = list;
        return this;
    }

    public void a(a aVar) {
        this.j = aVar;
    }

    public String e(int i) {
        AppMethodBeat.i(6750, false);
        if (b.a(this.a) || i >= this.a.size()) {
            AppMethodBeat.o(6750);
            return "";
        }
        String suspensionTag = ((a) this.a.get(i)).getSuspensionTag();
        a aVar = this.j;
        if (aVar != null) {
            String a = aVar.a(this.i, i, suspensionTag);
            AppMethodBeat.o(6750);
            return a;
        }
        AppMethodBeat.o(6750);
        return suspensionTag;
    }

    public int a() {
        return this.i;
    }

    public SuspensionDecoration f(int i) {
        this.i = i;
        return this;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        AppMethodBeat.i(6753, false);
        super.onDraw(canvas, recyclerView, state);
        int paddingLeft = recyclerView.getPaddingLeft();
        int width = recyclerView.getWidth() - recyclerView.getPaddingRight();
        int childCount = recyclerView.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childAt = recyclerView.getChildAt(i);
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) childAt.getLayoutParams();
            int viewLayoutPosition = layoutParams.getViewLayoutPosition() - a();
            List<? extends a> list = this.a;
            if (list != null && !list.isEmpty() && viewLayoutPosition <= this.a.size() - 1 && viewLayoutPosition >= 0 && ((a) this.a.get(viewLayoutPosition)).isShowSuspension() && viewLayoutPosition > -1) {
                if (viewLayoutPosition == 0) {
                    a(canvas, paddingLeft, width, childAt, layoutParams, viewLayoutPosition);
                } else if (((a) this.a.get(viewLayoutPosition)).getSuspensionTag() != null && !((a) this.a.get(viewLayoutPosition)).getSuspensionTag().equals(((a) this.a.get(viewLayoutPosition - 1)).getSuspensionTag())) {
                    a(canvas, paddingLeft, width, childAt, layoutParams, viewLayoutPosition);
                }
            }
        }
        AppMethodBeat.o(6753);
    }

    private void a(Canvas canvas, int i, int i2, View view, RecyclerView.LayoutParams layoutParams, int i3) {
        AppMethodBeat.i(6756, false);
        this.b.setColor(f);
        canvas.drawRect((float) i, (float) ((view.getTop() - layoutParams.topMargin) - this.e), (float) i2, (float) (view.getTop() - layoutParams.topMargin), this.b);
        this.b.setColor(g);
        String e = e(i3);
        this.b.getTextBounds(e, 0, ((a) this.a.get(i3)).getSuspensionTag().length(), this.c);
        canvas.drawText(e, (float) view.getPaddingLeft(), (float) ((view.getTop() - layoutParams.topMargin) - ((this.e / 2) - (this.c.height() / 2))), this.b);
        AppMethodBeat.o(6756);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        AppMethodBeat.i(6761, false);
        int findFirstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstVisibleItemPosition() - a();
        List<? extends a> list = this.a;
        if (list != null && !list.isEmpty()) {
            boolean z = true;
            if (findFirstVisibleItemPosition <= this.a.size() - 1 && findFirstVisibleItemPosition >= 0 && ((a) this.a.get(findFirstVisibleItemPosition)).isShowSuspension()) {
                String e = e(findFirstVisibleItemPosition);
                View view = recyclerView.findViewHolderForLayoutPosition(a() + findFirstVisibleItemPosition).itemView;
                int i = findFirstVisibleItemPosition + 1;
                if (i >= this.a.size() || e == null || e.equals(e(i)) || view.getHeight() + view.getTop() >= this.e) {
                    z = false;
                } else {
                    canvas.save();
                    canvas.translate(0.0f, (float) ((view.getHeight() + view.getTop()) - this.e));
                }
                this.b.setColor(f);
                canvas.drawRect((float) recyclerView.getPaddingLeft(), (float) recyclerView.getPaddingTop(), (float) (recyclerView.getRight() - recyclerView.getPaddingRight()), (float) (recyclerView.getPaddingTop() + this.e), this.b);
                this.b.setColor(g);
                this.b.getTextBounds(e, 0, e == null ? 0 : e.length(), this.c);
                int paddingTop = recyclerView.getPaddingTop();
                int i2 = this.e;
                canvas.drawText(e, (float) view.getPaddingLeft(), (float) ((paddingTop + i2) - ((i2 / 2) - (this.c.height() / 2))), this.b);
                if (z) {
                    canvas.restore();
                }
                AppMethodBeat.o(6761);
                return;
            }
        }
        AppMethodBeat.o(6761);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        AppMethodBeat.i(6765, false);
        super.getItemOffsets(rect, view, recyclerView, state);
        int viewLayoutPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition() - a();
        List<? extends a> list = this.a;
        if (list == null || list.isEmpty() || viewLayoutPosition > this.a.size() - 1) {
            AppMethodBeat.o(6765);
            return;
        }
        if (viewLayoutPosition > -1) {
            a aVar = (a) this.a.get(viewLayoutPosition);
            if (aVar.isShowSuspension()) {
                if (viewLayoutPosition == 0) {
                    rect.set(0, this.e, 0, 0);
                } else if (aVar.getSuspensionTag() != null && !aVar.getSuspensionTag().equals(((a) this.a.get(viewLayoutPosition - 1)).getSuspensionTag())) {
                    rect.set(0, this.e, 0, 0);
                }
            }
        }
        AppMethodBeat.o(6765);
    }
}
