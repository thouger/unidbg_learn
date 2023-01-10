package cn.missfresh.module.base.widget;

import android.content.Context;
import android.media.MediaMetadataEditor;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class LoadMoreListView extends ListView implements View.OnClickListener, AbsListView.OnScrollListener {
    private int a = 4;
    private a b;
    private AbsListView.OnScrollListener c;
    private View d;
    private View e;
    private TextView f;
    private TextView g;
    private Button h;
    private boolean i = false;

    public interface a {
        void a(LoadMoreListView loadMoreListView);
    }

    public LoadMoreListView(Context context) {
        super(context);
        AppMethodBeat.i(23690, false);
        a(context);
        AppMethodBeat.o(23690);
    }

    public LoadMoreListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        AppMethodBeat.i(23691, false);
        a(context);
        AppMethodBeat.o(23691);
    }

    public AbsListView.OnScrollListener getOuterOnScrollListener() {
        return this.c;
    }

    public void setOuterOnScrollListener(AbsListView.OnScrollListener onScrollListener) {
        this.c = onScrollListener;
    }

    public void setIsInScrollView(boolean z) {
        this.i = z;
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.ListView, android.widget.AbsListView, android.view.View
    public void onMeasure(int i, int i2) {
        AppMethodBeat.i(23692, false);
        if (this.i) {
            i2 = View.MeasureSpec.makeMeasureSpec(MediaMetadataEditor.KEY_EDITABLE_MASK, Integer.MIN_VALUE);
        }
        super.onMeasure(i, i2);
        AppMethodBeat.o(23692);
    }

    private void a(Context context) {
        AppMethodBeat.i(23693, false);
        this.d = LayoutInflater.from(context).inflate(R.layout.layout_loading_more, (ViewGroup) this, false);
        this.e = this.d.findViewById(R.id.pb_loading_more_progress);
        this.f = (TextView) this.d.findViewById(R.id.tv_no_more);
        this.g = (TextView) this.d.findViewById(R.id.tv_load_more_hint);
        this.h = (Button) this.d.findViewById(R.id.btn_load_more_error);
        this.h.setOnClickListener(this);
        addFooterView(this.d, null, false);
        c();
        setOnScrollListener(this);
        AppMethodBeat.o(23693);
    }

    public int getState() {
        return this.a;
    }

    private void a(int i) {
        AppMethodBeat.i(23694, false);
        this.a = i;
        if (i == 1) {
            this.f.setVisibility(8);
            this.e.setVisibility(0);
            this.g.setVisibility(8);
            this.h.setVisibility(8);
        } else if (i == 2) {
            this.f.setVisibility(8);
            this.e.setVisibility(8);
            this.g.setVisibility(8);
            this.h.setVisibility(0);
        } else if (i == 3) {
            this.f.setVisibility(0);
            this.e.setVisibility(8);
            this.g.setVisibility(8);
            this.h.setVisibility(8);
        } else if (i == 4) {
            this.f.setVisibility(8);
            this.e.setVisibility(8);
            this.g.setVisibility(0);
            this.h.setVisibility(8);
        }
        AppMethodBeat.o(23694);
    }

    public void a() {
        AppMethodBeat.i(23695, false);
        a(4);
        AppMethodBeat.o(23695);
    }

    public void b() {
        AppMethodBeat.i(23696, false);
        a(2);
        AppMethodBeat.o(23696);
    }

    public void c() {
        AppMethodBeat.i(23697, false);
        a(3);
        AppMethodBeat.o(23697);
    }

    public void d() {
        AppMethodBeat.i(23698, false);
        a(1);
        AppMethodBeat.o(23698);
    }

    public void setFooterVisible(int i) {
        AppMethodBeat.i(23699, false);
        this.d.setVisibility(i);
        AppMethodBeat.o(23699);
    }

    public void setOnLoadMoreListener(a aVar) {
        this.b = aVar;
    }

    public View getVFooter() {
        return this.d;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(23700, false);
        a aVar = this.b;
        if (aVar != null) {
            aVar.a(this);
            d();
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(23700);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScrollStateChanged(AbsListView absListView, int i) {
        a aVar;
        AppMethodBeat.i(23701, false);
        AbsListView.OnScrollListener onScrollListener = this.c;
        if (onScrollListener != null) {
            onScrollListener.onScrollStateChanged(absListView, i);
        }
        if (i == 0 && getLastVisiblePosition() == getCount() - 1 && (aVar = this.b) != null) {
            aVar.a(this);
            if (this.a == 4) {
                d();
            }
        }
        AppMethodBeat.o(23701);
    }

    @Override // android.widget.AbsListView.OnScrollListener
    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        AppMethodBeat.i(23702, false);
        AbsListView.OnScrollListener onScrollListener = this.c;
        if (onScrollListener != null) {
            onScrollListener.onScroll(absListView, i, i2, i3);
        }
        AppMethodBeat.o(23702);
    }

    public int getLoadMoreState() {
        return this.a;
    }
}
