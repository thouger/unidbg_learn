package cn.missfresh.ui.tablayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

/* compiled from: TabDecoration */
class c extends RecyclerView.ItemDecoration {
    private MFTabLayout a;
    private MFTabLayoutAdapter b;
    private Paint c = new Paint();
    private Paint d = new Paint();
    private RectF e = new RectF();

    public c() {
        AppMethodBeat.i(2453, false);
        this.c.setAntiAlias(true);
        this.d.setAntiAlias(true);
        AppMethodBeat.o(2453);
    }

    /* access modifiers changed from: package-private */
    public void a(MFTabLayout mFTabLayout) {
        this.a = mFTabLayout;
    }

    /* access modifiers changed from: package-private */
    public void a(MFTabLayoutAdapter mFTabLayoutAdapter) {
        this.b = mFTabLayoutAdapter;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View view, RecyclerView recyclerView, RecyclerView.State state) {
        AppMethodBeat.i(2464, false);
        super.getItemOffsets(rect, view, recyclerView, state);
        if (this.a == null) {
            AppMethodBeat.o(2464);
            return;
        }
        if (recyclerView.getChildAdapterPosition(view) > 0) {
            if (this.a.getOrientation() == 0) {
                rect.left = this.a.getTabSpacing();
            } else {
                rect.top = this.a.getTabSpacing();
            }
        }
        AppMethodBeat.o(2464);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDrawOver(Canvas canvas, RecyclerView recyclerView, RecyclerView.State state) {
        MFTabLayoutAdapter mFTabLayoutAdapter;
        AppMethodBeat.i(2469, false);
        super.onDrawOver(canvas, recyclerView, state);
        if (this.a == null || (mFTabLayoutAdapter = this.b) == null) {
            AppMethodBeat.o(2469);
            return;
        }
        this.d.setColor(mFTabLayoutAdapter.m());
        for (int i = 0; i < recyclerView.getChildCount(); i++) {
            View childAt = recyclerView.getChildAt(i);
            if (!this.b.c() && this.a.getSelectTab() == recyclerView.getChildAdapterPosition(childAt)) {
                a(childAt, this.e);
                this.c.setColor(this.b.e());
                if (this.b.h()) {
                    canvas.drawRoundRect(this.e, ((float) a(childAt.getContext())) / 2.0f, ((float) a(childAt.getContext())) / 2.0f, this.c);
                } else {
                    canvas.drawRect(this.e.left, this.e.top, this.e.right, this.e.bottom, this.c);
                }
            }
            if (!this.b.i() && i < recyclerView.getChildCount() - 1) {
                a(childAt, recyclerView.getChildAt(i + 1), this.e);
                canvas.drawRect(this.e.left, this.e.top, this.e.right, this.e.bottom, this.d);
            }
        }
        AppMethodBeat.o(2469);
    }

    private void a(View view, RectF rectF) {
        AppMethodBeat.i(2475, false);
        if (this.a.getOrientation() == 0) {
            rectF.bottom = view.getY() + ((float) view.getHeight());
            rectF.top = rectF.bottom - ((float) a(view.getContext()));
            if (this.b.g() < 0) {
                rectF.left = view.getX() + ((float) view.getPaddingLeft());
                rectF.right = (view.getX() + ((float) view.getWidth())) - ((float) view.getPaddingRight());
                int a = a(view.getContext(), 2);
                if (rectF.right - rectF.left > ((float) (a * 2))) {
                    float f = (float) a;
                    rectF.left += f;
                    rectF.right -= f;
                }
            } else if (this.b.d() < 0) {
                rectF.left = view.getX() + (((float) (((view.getWidth() + view.getPaddingLeft()) - view.getPaddingRight()) - this.b.g())) / 2.0f);
                rectF.right = rectF.left + ((float) this.b.g());
            } else {
                rectF.left = view.getX() + ((float) this.b.d());
                rectF.right = rectF.left + ((float) this.b.g());
            }
        } else {
            rectF.left = (float) view.getLeft();
            rectF.right = rectF.left + ((float) a(view.getContext()));
            if (this.b.g() < 0) {
                rectF.top = view.getY() + ((float) view.getPaddingTop());
                rectF.bottom = (view.getY() + ((float) view.getHeight())) - ((float) view.getPaddingBottom());
            } else if (this.b.d() < 0) {
                rectF.top = view.getY() + (((float) (((view.getHeight() + view.getPaddingTop()) - view.getPaddingBottom()) - this.b.g())) / 2.0f);
                rectF.bottom = rectF.top + ((float) this.b.g());
            } else {
                rectF.top = view.getY() + ((float) this.b.d());
                rectF.bottom = rectF.top + ((float) this.b.g());
            }
        }
        AppMethodBeat.o(2475);
    }

    private void a(View view, View view2, RectF rectF) {
        AppMethodBeat.i(2478, false);
        Context context = view.getContext();
        if (this.a.getOrientation() == 0) {
            rectF.left = (((view.getX() + ((float) view.getWidth())) + view2.getX()) - ((float) c(context))) / 2.0f;
            int j = this.b.j();
            if (j >= 0) {
                rectF.top = view.getY() + ((float) j);
            } else {
                rectF.top = view.getY() + ((float) view.getPaddingTop()) + (((float) (((view.getHeight() - view.getPaddingTop()) - view.getPaddingBottom()) - b(context))) / 2.0f);
            }
            rectF.right = rectF.left + ((float) c(context));
            rectF.bottom = rectF.top + ((float) b(context));
        } else {
            rectF.top = (((view.getY() + ((float) view.getHeight())) + view2.getY()) - ((float) c(context))) / 2.0f;
            int j2 = this.b.j();
            if (j2 >= 0) {
                rectF.left = view.getX() + ((float) j2);
            } else {
                rectF.left = view.getX() + ((float) view.getPaddingLeft()) + (((float) (((view.getWidth() - view.getPaddingLeft()) - view.getPaddingRight()) - b(context))) / 2.0f);
            }
            rectF.bottom = rectF.top + ((float) c(context));
            rectF.right = rectF.left + ((float) b(context));
        }
        AppMethodBeat.o(2478);
    }

    private int a(Context context) {
        AppMethodBeat.i(2480, false);
        MFTabLayoutAdapter mFTabLayoutAdapter = this.b;
        if (mFTabLayoutAdapter == null || mFTabLayoutAdapter.f() < 0) {
            int a = a(context, 2);
            AppMethodBeat.o(2480);
            return a;
        }
        int f = this.b.f();
        AppMethodBeat.o(2480);
        return f;
    }

    private int b(Context context) {
        AppMethodBeat.i(2484, false);
        MFTabLayoutAdapter mFTabLayoutAdapter = this.b;
        if (mFTabLayoutAdapter == null || mFTabLayoutAdapter.k() < 0) {
            int a = a(context, 12);
            AppMethodBeat.o(2484);
            return a;
        }
        int k = this.b.k();
        AppMethodBeat.o(2484);
        return k;
    }

    private int c(Context context) {
        AppMethodBeat.i(2487, false);
        MFTabLayoutAdapter mFTabLayoutAdapter = this.b;
        if (mFTabLayoutAdapter == null || mFTabLayoutAdapter.l() < 0) {
            int a = a(context, 1);
            AppMethodBeat.o(2487);
            return a;
        }
        int l = this.b.l();
        AppMethodBeat.o(2487);
        return l;
    }

    private static int a(Context context, int i) {
        AppMethodBeat.i(2489, false);
        int i2 = (int) ((((float) i) * context.getResources().getDisplayMetrics().density) + 0.5f);
        AppMethodBeat.o(2489);
        return i2;
    }
}
