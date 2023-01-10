package com.sobot.chat.widget.kpswitch.widget.adpater;

import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import androidx.viewpager.widget.PagerAdapter;
import com.sobot.chat.widget.kpswitch.widget.data.PageSetEntity;
import com.sobot.chat.widget.kpswitch.widget.data.a;
import java.util.ArrayList;
import java.util.Iterator;

public class PageSetAdapter extends PagerAdapter {
    private final ArrayList<PageSetEntity> a = new ArrayList<>();

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return view == obj;
    }

    public ArrayList<PageSetEntity> a() {
        return this.a;
    }

    public int a(PageSetEntity pageSetEntity) {
        if (pageSetEntity == null || TextUtils.isEmpty(pageSetEntity.getUuid())) {
            return 0;
        }
        int i = 0;
        for (int i2 = 0; i2 < this.a.size(); i2++) {
            if (i2 == this.a.size() - 1 && !pageSetEntity.getUuid().equals(this.a.get(i2).getUuid())) {
                return 0;
            }
            if (pageSetEntity.getUuid().equals(this.a.get(i2).getUuid())) {
                return i;
            }
            i += this.a.get(i2).getPageCount();
        }
        return i;
    }

    public void b(PageSetEntity pageSetEntity) {
        a(this.a.size(), pageSetEntity);
    }

    public void a(int i, PageSetEntity pageSetEntity) {
        if (pageSetEntity != null) {
            this.a.add(i, pageSetEntity);
        }
    }

    public a a(int i) {
        Iterator<PageSetEntity> it2 = this.a.iterator();
        while (it2.hasNext()) {
            PageSetEntity next = it2.next();
            if (next.getPageCount() > i) {
                return (a) next.getPageEntityList().get(i);
            }
            i -= next.getPageCount();
        }
        return null;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        Iterator<PageSetEntity> it2 = this.a.iterator();
        int i = 0;
        while (it2.hasNext()) {
            i += it2.next().getPageCount();
        }
        return i;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        View a = a(i).a(viewGroup, i, null);
        if (a == null) {
            return null;
        }
        viewGroup.addView(a);
        return a;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        viewGroup.removeView((View) obj);
    }
}
