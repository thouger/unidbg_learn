package cn.missfresh.ui.tablayout;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.R;

public final class MFTabLayout extends FrameLayout {
    private RecyclerView a;
    private TabLayoutManager b;
    private MFTabLayoutAdapter c;
    private c d;
    private b e;
    private int f;
    private a g;
    private int h;

    public void setOnTabSelectListener(b bVar) {
        this.e = bVar;
    }

    public MFTabLayout(Context context) {
        this(context, null);
    }

    public MFTabLayout(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public MFTabLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(2360, false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.MFTabLayout, i, 0);
        this.h = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MFTabLayout_tl_tabSpacing, 0);
        this.f = 0;
        this.b = new TabLayoutManager(context, obtainStyledAttributes.getInt(R.styleable.MFTabLayout_tl_orientation, 0) == 0 ? 0 : 1, false);
        this.b.a(obtainStyledAttributes.getInt(R.styleable.MFTabLayout_tl_layoutMode, 0));
        this.b.b(obtainStyledAttributes.getDimensionPixelSize(R.styleable.MFTabLayout_tl_centerOffset, 0));
        this.d = new c();
        this.d.a(this);
        this.g = new a();
        obtainStyledAttributes.recycle();
        AppMethodBeat.o(2360);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        ViewGroup.LayoutParams layoutParams;
        AppMethodBeat.i(2366, false);
        super.onAttachedToWindow();
        if (this.a != null) {
            AppMethodBeat.o(2366);
            return;
        }
        this.a = new AnonymousClass1(getContext());
        this.a.setLayoutManager(this.b);
        this.a.addItemDecoration(this.d);
        MFTabLayoutAdapter mFTabLayoutAdapter = this.c;
        if (mFTabLayoutAdapter != null) {
            this.a.setAdapter(mFTabLayoutAdapter);
        }
        ViewGroup.LayoutParams layoutParams2 = getLayoutParams();
        if (layoutParams2 != null) {
            layoutParams = new ViewGroup.LayoutParams(layoutParams2.width, layoutParams2.height);
        } else if (getOrientation() == 0) {
            layoutParams = new ViewGroup.LayoutParams(-2, -2);
        } else {
            layoutParams = new ViewGroup.LayoutParams(-1, -2);
        }
        addView(this.a, 0, layoutParams);
        AppMethodBeat.o(2366);
    }

    /* renamed from: cn.missfresh.ui.tablayout.MFTabLayout$1  reason: invalid class name */
    class AnonymousClass1 extends RecyclerView {
        AnonymousClass1(Context context) {
            super(context);
        }

        @Override // androidx.recyclerview.widget.RecyclerView
        public void onScrolled(int i, int i2) {
            AppMethodBeat.i(2339, false);
            super.onScrolled(i, i2);
            MFTabLayout.this.g.a(MFTabLayout.this.a, i, i2);
            AppMethodBeat.o(2339);
        }
    }

    @Override // android.view.ViewGroup
    public void setLayoutMode(int i) {
        AppMethodBeat.i(2370, false);
        this.b.a(i);
        AppMethodBeat.o(2370);
    }

    public void setTabLayoutAdapter(MFTabLayoutAdapter mFTabLayoutAdapter) {
        AppMethodBeat.i(2373, false);
        if (this.c == mFTabLayoutAdapter) {
            AppMethodBeat.o(2373);
            return;
        }
        this.g.a(this.a);
        this.c = mFTabLayoutAdapter;
        mFTabLayoutAdapter.a(this);
        this.d.a(mFTabLayoutAdapter);
        RecyclerView recyclerView = this.a;
        if (recyclerView != null) {
            recyclerView.setAdapter(mFTabLayoutAdapter);
        }
        AppMethodBeat.o(2373);
    }

    public int getSelectTab() {
        return this.f;
    }

    public void a(int i, boolean z) {
        b bVar;
        AppMethodBeat.i(2383, false);
        if (i == this.f) {
            AppMethodBeat.o(2383);
            return;
        }
        this.f = i;
        if (i < 0) {
            AppMethodBeat.o(2383);
            return;
        }
        MFTabLayoutAdapter mFTabLayoutAdapter = this.c;
        if (mFTabLayoutAdapter != null && i < mFTabLayoutAdapter.getItemCount()) {
            this.c.notifyDataSetChanged();
            RecyclerView recyclerView = this.a;
            if (recyclerView != null) {
                recyclerView.smoothScrollToPosition(i);
            }
            if (z && (bVar = this.e) != null) {
                bVar.a(this.f);
            }
        }
        AppMethodBeat.o(2383);
    }

    public int getOrientation() {
        int i = 0;
        AppMethodBeat.i(2385, false);
        TabLayoutManager tabLayoutManager = this.b;
        if (tabLayoutManager != null) {
            i = tabLayoutManager.getOrientation();
        }
        AppMethodBeat.o(2385);
        return i;
    }

    public void setOrientation(int i) {
        AppMethodBeat.i(2389, false);
        TabLayoutManager tabLayoutManager = this.b;
        if (tabLayoutManager != null) {
            tabLayoutManager.setOrientation(i);
        }
        AppMethodBeat.o(2389);
    }

    /* access modifiers changed from: package-private */
    public void a(View view) {
        AppMethodBeat.i(2393, false);
        RecyclerView recyclerView = this.a;
        if (recyclerView != null) {
            a(recyclerView.getChildAdapterPosition(view), true);
        }
        AppMethodBeat.o(2393);
    }

    public int getTabSpacing() {
        return this.h;
    }

    public void setTabSpacing(int i) {
        this.h = i;
    }

    public void setCenterOffset(int i) {
        AppMethodBeat.i(2403, false);
        TabLayoutManager tabLayoutManager = this.b;
        if (tabLayoutManager != null) {
            tabLayoutManager.b(i);
        }
        AppMethodBeat.o(2403);
    }

    /* access modifiers changed from: package-private */
    public void a(int i, View view, int i2, int i3) {
        AppMethodBeat.i(2407, false);
        this.g.a(this.a, i, view, i2, i3);
        AppMethodBeat.o(2407);
    }
}
