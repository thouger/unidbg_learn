package cn.missfresh.module.base.widget.recycleview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class ExRecyclerView extends RecyclerView implements View.OnClickListener {
    public AdapterView.OnItemLongClickListener a = null;
    protected final String b = getClass().getSimpleName();
    private AdapterView.OnItemClickListener c = null;
    private int d = 4;
    private b e;
    private View f = null;
    private View g = null;
    private View h;
    private TextView i;
    private TextView j;
    private Button k;
    private boolean l;

    public interface b {
        void a(ExRecyclerView exRecyclerView);
    }

    public ExRecyclerView(Context context) {
        super(context);
        AppMethodBeat.i(24242, false);
        AppMethodBeat.o(24242);
    }

    public ExRecyclerView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(24243, false);
        a(context, attributeSet);
        AppMethodBeat.o(24243);
    }

    public ExRecyclerView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(24244, false);
        a(context, attributeSet);
        AppMethodBeat.o(24244);
    }

    private void a(Context context, AttributeSet attributeSet) {
        AppMethodBeat.i(24245, false);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ExRecyclerView);
        this.l = obtainStyledAttributes.getBoolean(R.styleable.ExRecyclerView_useCustomLayoutManager, true);
        obtainStyledAttributes.recycle();
        AppMethodBeat.o(24245);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        AppMethodBeat.i(24246, false);
        super.setLayoutManager(layoutManager);
        if (this.l) {
            AppMethodBeat.o(24246);
            return;
        }
        if (layoutManager instanceof GridLayoutManager) {
            GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
            gridLayoutManager.setSpanSizeLookup(new a(gridLayoutManager.getSpanCount()));
        } else if (layoutManager instanceof ExStaggeredGridLayoutManager) {
            ExStaggeredGridLayoutManager exStaggeredGridLayoutManager = (ExStaggeredGridLayoutManager) layoutManager;
            exStaggeredGridLayoutManager.a(new a(exStaggeredGridLayoutManager.getSpanCount()));
        }
        AppMethodBeat.o(24246);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(24247, false);
        b bVar = this.e;
        if (bVar != null) {
            bVar.a(this);
            a();
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(24247);
    }

    public void a(View view) {
        this.f = view;
    }

    public View getHeaderView() {
        return this.f;
    }

    public View getFooterView() {
        return this.g;
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter adapter) {
        AppMethodBeat.i(24248, false);
        super.setAdapter(adapter);
        if (adapter instanceof BaseRecyclerAdapter) {
            BaseRecyclerAdapter baseRecyclerAdapter = (BaseRecyclerAdapter) adapter;
            baseRecyclerAdapter.d = this.c;
            baseRecyclerAdapter.e = this.a;
            baseRecyclerAdapter.b = this.f;
            baseRecyclerAdapter.c = this.g;
        }
        AppMethodBeat.o(24248);
    }

    public void setDefaultNoMoreMsg(String str) {
        AppMethodBeat.i(24252, false);
        TextView textView = this.i;
        if (textView != null) {
            textView.setText(str);
        }
        AppMethodBeat.o(24252);
    }

    public void a(int i) {
        AppMethodBeat.i(24253, false);
        this.d = i;
        if (i == 1) {
            this.i.setVisibility(8);
            this.h.setVisibility(0);
            this.j.setVisibility(8);
            this.k.setVisibility(8);
        } else if (i == 2) {
            this.i.setVisibility(8);
            this.h.setVisibility(8);
            this.j.setVisibility(8);
            this.k.setVisibility(0);
        } else if (i == 3) {
            this.i.setVisibility(0);
            this.h.setVisibility(8);
            this.j.setVisibility(8);
            this.k.setVisibility(8);
        } else if (i == 4) {
            this.i.setVisibility(8);
            this.h.setVisibility(8);
            this.j.setVisibility(0);
            this.k.setVisibility(8);
        }
        AppMethodBeat.o(24253);
    }

    public void a() {
        AppMethodBeat.i(24257, false);
        if (this.d != 1) {
            a(1);
        }
        AppMethodBeat.o(24257);
    }

    public void setOnLoadMoreListener(b bVar) {
        this.e = bVar;
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.c = onItemClickListener;
    }

    public void setOnItemLongClickListener(AdapterView.OnItemLongClickListener onItemLongClickListener) {
        this.a = onItemLongClickListener;
    }

    private class a extends GridLayoutManager.SpanSizeLookup {
        private int b = 1;

        public a(int i) {
            this.b = i;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int i) {
            AppMethodBeat.i(24241, false);
            BaseRecyclerAdapter baseRecyclerAdapter = (BaseRecyclerAdapter) ExRecyclerView.this.getAdapter();
            if (baseRecyclerAdapter.getItemViewType(i) == 7 || baseRecyclerAdapter.getItemViewType(i) == 8) {
                int i2 = this.b;
                AppMethodBeat.o(24241);
                return i2;
            }
            AppMethodBeat.o(24241);
            return 1;
        }
    }
}
