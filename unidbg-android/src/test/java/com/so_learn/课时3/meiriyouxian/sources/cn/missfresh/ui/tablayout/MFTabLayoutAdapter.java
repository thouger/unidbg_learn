package cn.missfresh.ui.tablayout;

import android.graphics.Point;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public abstract class MFTabLayoutAdapter<VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    private MFTabLayout a;
    private View.OnClickListener b;
    private SparseArray<View> c = new SparseArray<>();
    private SparseArray<Point> d = new SparseArray<>();
    private boolean e = false;
    private int f = -1;
    private int g = -46959;
    private int h = -1;
    private int i = -1;
    private boolean j = true;
    private boolean k = false;
    private int l = -1;
    private int m = -1644826;
    private int n = -1;
    private int o = -1;

    public abstract int a();

    public int a(int i) {
        return 0;
    }

    public abstract VH a(ViewGroup viewGroup, int i);

    public abstract void a(VH vh, int i, boolean z);

    /* access modifiers changed from: package-private */
    public void a(MFTabLayout mFTabLayout) {
        this.a = mFTabLayout;
        for (int i = 0; i < this.c.size(); i++) {
            Point point = (Point) this.d.valueAt(i);
            mFTabLayout.a(this.c.keyAt(i), (View) this.c.valueAt(i), point.x, point.y);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final VH onCreateViewHolder(ViewGroup viewGroup, int i) {
        VH vh = (VH) a(viewGroup, i);
        vh.itemView.setOnClickListener(n());
        return vh;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final void onBindViewHolder(VH vh, int i) {
        a(vh, i, b() != null && b().getSelectTab() == i);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemCount() {
        return a();
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public final int getItemViewType(int i) {
        return a(i);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.ui.tablayout.MFTabLayoutAdapter$1  reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(2431, false);
            if (MFTabLayoutAdapter.this.a != null) {
                MFTabLayoutAdapter.this.a.a(view);
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(2431);
        }
    }

    private View.OnClickListener n() {
        if (this.b == null) {
            this.b = new AnonymousClass1();
        }
        return this.b;
    }

    public MFTabLayout b() {
        return this.a;
    }

    public boolean c() {
        return this.e;
    }

    public int d() {
        return this.f;
    }

    public int e() {
        return this.g;
    }

    public int f() {
        return this.h;
    }

    public int g() {
        return this.i;
    }

    public void b(int i) {
        this.i = i;
    }

    public boolean h() {
        return this.j;
    }

    public boolean i() {
        return this.k;
    }

    public int j() {
        return this.l;
    }

    public int k() {
        return this.o;
    }

    public void c(int i) {
        this.o = i;
    }

    public int l() {
        return this.n;
    }

    public int m() {
        return this.m;
    }
}
