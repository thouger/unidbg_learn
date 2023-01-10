package cn.missfresh.ui.skeleton.manual.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.skeleton.ShimmerLayout;

public class SkeletonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int a;
    private int b;
    private int[] c;
    private int d;
    private boolean e;
    private int f;
    private int g;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int i) {
        return (long) i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(2120, false);
        LayoutInflater from = LayoutInflater.from(viewGroup.getContext());
        if (a()) {
            this.b = i;
        }
        if (this.e) {
            ShimmerViewHolder shimmerViewHolder = new ShimmerViewHolder(from, viewGroup, this.b);
            AppMethodBeat.o(2120);
            return shimmerViewHolder;
        }
        AnonymousClass1 r6 = new AnonymousClass1(from.inflate(this.b, viewGroup, false));
        AppMethodBeat.o(2120);
        return r6;
    }

    /* renamed from: cn.missfresh.ui.skeleton.manual.recyclerview.SkeletonAdapter$1  reason: invalid class name */
    class AnonymousClass1 extends RecyclerView.ViewHolder {
        AnonymousClass1(View view) {
            super(view);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int i) {
        AppMethodBeat.i(2122, false);
        if (this.e) {
            ShimmerLayout shimmerLayout = (ShimmerLayout) viewHolder.itemView;
            shimmerLayout.setShimmerAnimationDuration(this.f);
            shimmerLayout.setShimmerAngle(this.g);
            shimmerLayout.setShimmerColor(this.d);
            shimmerLayout.a();
        }
        AppMethodBeat.o(2122);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int i) {
        AppMethodBeat.i(2124, false);
        if (a()) {
            int a = a(i);
            AppMethodBeat.o(2124);
            return a;
        }
        int itemViewType = super.getItemViewType(i);
        AppMethodBeat.o(2124);
        return itemViewType;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.a;
    }

    public int a(int i) {
        AppMethodBeat.i(2147, false);
        if (a()) {
            int[] iArr = this.c;
            int i2 = iArr[i % iArr.length];
            AppMethodBeat.o(2147);
            return i2;
        }
        int i3 = this.b;
        AppMethodBeat.o(2147);
        return i3;
    }

    private boolean a() {
        int[] iArr = this.c;
        return (iArr == null || iArr.length == 0) ? false : true;
    }
}
