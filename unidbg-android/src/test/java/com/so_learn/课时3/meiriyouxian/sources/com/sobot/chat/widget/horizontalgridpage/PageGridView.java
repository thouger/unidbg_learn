package com.sobot.chat.widget.horizontalgridpage;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import com.sobot.chat.widget.horizontalgridpage.PagerGridLayoutManager;

public class PageGridView extends RecyclerView {
    private PageGridAdapter a;
    private int b;
    private PageIndicatorView c;
    private float d = 0.0f;
    private int e = 1;
    private float f = 0.0f;
    private int g;
    private int h;
    private int i;
    private int j = 0;
    private int k;
    private PagerGridLayoutManager l;

    public PageGridView(Context context, int[] iArr, int i, int i2) {
        super(context);
        this.g = iArr[0];
        this.h = iArr[1];
        this.i = i;
        this.k = i2;
        setOverScrollMode(2);
    }

    public void setIndicator(PageIndicatorView pageIndicatorView) {
        this.c = pageIndicatorView;
    }

    public void setLayoutManager(PagerGridLayoutManager pagerGridLayoutManager) {
        this.l = pagerGridLayoutManager;
        super.setLayoutManager((RecyclerView.LayoutManager) pagerGridLayoutManager);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public PagerGridLayoutManager getLayoutManager() {
        return this.l;
    }

    /* access modifiers changed from: protected */
    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View
    public void onMeasure(int i, int i2) {
        super.onMeasure(i, this.k * this.g);
    }

    @Override // androidx.recyclerview.widget.RecyclerView
    public void setAdapter(RecyclerView.Adapter adapter) {
        super.setAdapter(adapter);
        this.a = (PageGridAdapter) adapter;
        this.l.a(new AnonymousClass1());
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.horizontalgridpage.PageGridView$1  reason: invalid class name */
    public class AnonymousClass1 implements PagerGridLayoutManager.a {
        @Override // com.sobot.chat.widget.horizontalgridpage.PagerGridLayoutManager.a
        public void a(int i) {
        }

        AnonymousClass1() {
        }

        @Override // com.sobot.chat.widget.horizontalgridpage.PagerGridLayoutManager.a
        public void b(int i) {
            if (PageGridView.this.l.getChildCount() != 0) {
                PageGridView.this.c.b(i);
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView, android.view.View, android.view.ViewParent
    public void requestLayout() {
        super.requestLayout();
        if (this.a != null) {
            a();
        }
    }

    public void a() {
        int ceil = (int) Math.ceil(((double) this.a.a().size()) / ((double) (this.g * this.h)));
        if (ceil != this.b) {
            this.c.a(ceil);
            int i = this.b;
            if (ceil < i && this.e == i) {
                this.e = ceil;
            }
            this.c.b(this.e - 1);
            this.b = ceil;
        }
        if (this.b > 1) {
            this.c.setVisibility(0);
        } else {
            this.c.setVisibility(8);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.widget.horizontalgridpage.PageGridView$2  reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ int a;

        AnonymousClass2(int i) {
            this.a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            PageGridView.this.e = this.a + 1;
            PageGridView.this.c.b(this.a);
            PageGridView.this.l.c(this.a);
        }
    }

    public void setSelectItem(int i) {
        postDelayed(new AnonymousClass2(i), 100);
    }

    public int getCurrentPage() {
        return this.e;
    }

    public void setCurrentPage(int i) {
        this.e = i;
    }
}
