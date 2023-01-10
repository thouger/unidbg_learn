package com.sobot.chat.adapter.base;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import java.util.ArrayList;

public class SobotBasePagerAdapter<T> extends PagerAdapter {
    protected ArrayList<T> a;
    protected Context b;

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public SobotBasePagerAdapter(Context context, ArrayList<T> arrayList) {
        this.a = arrayList;
        this.b = context;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.a.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }
}
