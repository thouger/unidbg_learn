package cn.missfresh.ui.tablayout;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

class TabLayoutManager extends LinearLayoutManager {
    private int a = 0;
    private int b = 0;

    public TabLayoutManager(Context context, int i, boolean z) {
        super(context, i, z);
    }

    public void a(int i) {
        boolean z = false;
        AppMethodBeat.i(2509, false);
        this.a = i;
        if (2 == i) {
            z = true;
        }
        setStackFromEnd(z);
        AppMethodBeat.o(2509);
    }

    public void b(int i) {
        this.b = i;
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        AppMethodBeat.i(2511, false);
        super.onLayoutChildren(recycler, state);
        int i = this.a;
        if (1 == i) {
            a();
        } else if (3 == i) {
            b();
        } else if (4 == i) {
            c();
        }
        AppMethodBeat.o(2511);
    }

    private void a() {
        int i = 0;
        AppMethodBeat.i(2513, false);
        if (getChildCount() <= 0) {
            AppMethodBeat.o(2513);
            return;
        }
        View childAt = getChildAt(getChildCount() - 1);
        View childAt2 = getChildAt(0);
        if (!(childAt == null || childAt2 == null)) {
            if (getOrientation() == 0) {
                float width = (((float) (((getWidth() - (childAt.getRight() - childAt2.getLeft())) + getPaddingLeft()) - getPaddingRight())) / 2.0f) - ((float) childAt2.getLeft());
                while (i < getChildCount()) {
                    View childAt3 = getChildAt(i);
                    if (childAt3 != null) {
                        childAt3.setTranslationX(width);
                    }
                    i++;
                }
            } else {
                float height = (((float) (((getHeight() - (childAt.getBottom() - childAt2.getTop())) + getPaddingTop()) - getPaddingBottom())) / 2.0f) - ((float) childAt2.getTop());
                while (i < getChildCount()) {
                    View childAt4 = getChildAt(i);
                    if (childAt4 != null) {
                        childAt4.setTranslationY(height);
                    }
                    i++;
                }
            }
        }
        AppMethodBeat.o(2513);
    }

    private void b() {
        AppMethodBeat.i(2516, false);
        int i = 1;
        if (getChildCount() <= 1) {
            AppMethodBeat.o(2516);
            return;
        }
        View childAt = getChildAt(getChildCount() - 1);
        View childAt2 = getChildAt(0);
        if (!(childAt == null || childAt2 == null)) {
            if (getOrientation() == 0) {
                int width = (((getWidth() - getPaddingLeft()) - getPaddingRight()) - (childAt.getRight() - childAt2.getLeft())) / (getChildCount() - 1);
                int paddingLeft = getPaddingLeft() - childAt2.getLeft();
                childAt2.setTranslationX((float) paddingLeft);
                while (i < getChildCount()) {
                    View childAt3 = getChildAt(i);
                    if (childAt3 != null) {
                        childAt3.setTranslationX((float) ((width * i) + paddingLeft));
                    }
                    i++;
                }
            } else {
                int height = (((getHeight() - getPaddingTop()) - getPaddingBottom()) - (childAt.getBottom() - childAt2.getTop())) / (getChildCount() - 1);
                int paddingTop = getPaddingTop() - childAt2.getTop();
                childAt2.setTranslationY((float) paddingTop);
                while (i < getChildCount()) {
                    View childAt4 = getChildAt(i);
                    if (childAt4 != null) {
                        childAt4.setTranslationY((float) ((height * i) + paddingTop));
                    }
                    i++;
                }
            }
        }
        AppMethodBeat.o(2516);
    }

    private void c() {
        int i = 0;
        AppMethodBeat.i(2529, false);
        if (getChildCount() <= 0) {
            AppMethodBeat.o(2529);
            return;
        }
        if (getOrientation() == 0) {
            float width = ((float) ((getWidth() - getPaddingLeft()) - getPaddingRight())) / ((float) getChildCount());
            while (i < getChildCount()) {
                View childAt = getChildAt(i);
                if (childAt != null) {
                    childAt.setX(((float) getPaddingLeft()) + (((float) i) * width) + ((width - ((float) childAt.getWidth())) / 2.0f));
                }
                i++;
            }
        } else {
            float height = ((float) ((getHeight() - getPaddingTop()) - getPaddingBottom())) / ((float) getChildCount());
            while (i < getChildCount()) {
                View childAt2 = getChildAt(i);
                if (childAt2 != null) {
                    childAt2.setY(((float) getPaddingTop()) + (((float) i) * height) + ((height - ((float) childAt2.getHeight())) / 2.0f));
                }
                i++;
            }
        }
        AppMethodBeat.o(2529);
    }

    @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i) {
        AppMethodBeat.i(2533, false);
        int i2 = this.a;
        if (i2 == 1 || i2 == 3 || i2 == 4) {
            AppMethodBeat.o(2533);
            return;
        }
        CenterSmoothScroller centerSmoothScroller = new CenterSmoothScroller(recyclerView.getContext());
        centerSmoothScroller.setTargetPosition(i);
        centerSmoothScroller.a(this.b);
        startSmoothScroll(centerSmoothScroller);
        AppMethodBeat.o(2533);
    }

    private static class CenterSmoothScroller extends LinearSmoothScroller {
        private int a = 0;

        public void a(int i) {
            this.a = i;
        }

        public CenterSmoothScroller(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public int calculateDtToFit(int i, int i2, int i3, int i4, int i5) {
            return ((i3 + ((i4 - i3) / 2)) - (i + ((i2 - i) / 2))) + this.a;
        }

        /* access modifiers changed from: protected */
        @Override // androidx.recyclerview.widget.LinearSmoothScroller
        public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
            return 100.0f / ((float) displayMetrics.densityDpi);
        }
    }
}
