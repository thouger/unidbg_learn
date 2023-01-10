package cn.missfresh.module.base.widget.banner.adapter;

import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import cn.missfresh.module.base.widget.banner.bean.RecycleBin;

public abstract class RecyclingPageAdapter extends PagerAdapter {
    private final RecycleBin a;

    public int a(int i) {
        return 0;
    }

    public abstract View a(int i, View view, ViewGroup viewGroup);

    public int c() {
        return 1;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public RecyclingPageAdapter() {
        this(new RecycleBin());
    }

    public RecyclingPageAdapter(RecycleBin recycleBin) {
        this.a = recycleBin;
        recycleBin.setViewTypeCount(c());
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void notifyDataSetChanged() {
        this.a.scrapActiveViews();
        super.notifyDataSetChanged();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        int a = a(i);
        View a2 = a(i, a != -1 ? this.a.getScrapView(i, a) : null, viewGroup);
        viewGroup.addView(a2);
        return a2;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        View view = (View) obj;
        viewGroup.removeView(view);
        int a = a(i);
        if (a != -1) {
            this.a.addScrapView(view, i, a);
        }
    }
}
