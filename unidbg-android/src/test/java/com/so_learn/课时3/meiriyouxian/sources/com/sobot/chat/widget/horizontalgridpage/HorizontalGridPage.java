package com.sobot.chat.widget.horizontalgridpage;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.sobot.chat.R;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import com.sobot.chat.widget.horizontalgridpage.PagerGridLayoutManager;
import com.sobot.chat.widget.horizontalgridpage.a;

public class HorizontalGridPage extends LinearLayout {
    PageGridView a;
    PageIndicatorView b;
    Context c;
    int d;

    public HorizontalGridPage(Context context) {
        this(context, null);
    }

    public HorizontalGridPage(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public HorizontalGridPage(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.c = context;
    }

    public void a(a aVar, int i) {
        this.d = i;
        setOrientation(1);
        if (aVar == null) {
            aVar = new a.C0145a().a();
        }
        int[] d = aVar.d();
        this.a = new PageGridView(getContext(), d, aVar.e(), aVar.h());
        this.b = new PageIndicatorView(getContext(), a(6), new int[]{a(aVar.a()[0]), a(aVar.a()[1]), a(aVar.a()[2]), a(aVar.a()[3])}, new int[]{R.drawable.sobot_indicator_oval_normal_bg, R.drawable.sobot_indicator_oval_focus_bg}, aVar.b());
        this.b.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
        this.b.a(d[1]);
        this.a.setIndicator(this.b);
        this.a.addItemDecoration(new SpaceItemDecoration(0, a(aVar.g())));
        PagerGridLayoutManager pagerGridLayoutManager = new PagerGridLayoutManager(d[0], d[1], 1);
        pagerGridLayoutManager.a(false);
        this.a.setLayoutManager(pagerGridLayoutManager);
        addView(this.a);
        if (aVar.f()) {
            addView(this.b);
        } else {
            removeView(this.b);
        }
    }

    public void a(PageGridAdapter pageGridAdapter, ZhiChiMessageBase zhiChiMessageBase) {
        new PagerGridSnapHelper().attachToRecyclerView(this.a);
        this.a.setAdapter(pageGridAdapter);
        this.b.setMessage(zhiChiMessageBase);
    }

    private int a(int i) {
        return (int) ((((float) i) * getContext().getResources().getDisplayMetrics().density) + 0.5f);
    }

    public void setSelectItem(int i) {
        PageGridView pageGridView = this.a;
        if (pageGridView != null) {
            pageGridView.setSelectItem(i);
        }
    }

    public void a() {
        PageGridView pageGridView = this.a;
        if (pageGridView != null) {
            pageGridView.setSelectItem(this.d);
        }
    }

    public void b() {
        PageGridView pageGridView = this.a;
        if (pageGridView != null) {
            pageGridView.getLayoutManager().e();
        }
    }

    public void c() {
        PageGridView pageGridView = this.a;
        if (pageGridView != null) {
            pageGridView.getLayoutManager().g();
        }
    }

    public boolean d() {
        PageGridView pageGridView = this.a;
        if (pageGridView != null) {
            return pageGridView.getLayoutManager().f();
        }
        return false;
    }

    public boolean e() {
        PageGridView pageGridView = this.a;
        if (pageGridView != null) {
            return pageGridView.getLayoutManager().h();
        }
        return false;
    }

    public void setPageListener(PagerGridLayoutManager.a aVar) {
        PageGridView pageGridView = this.a;
        if (pageGridView != null) {
            pageGridView.getLayoutManager().a(aVar);
        }
    }
}
