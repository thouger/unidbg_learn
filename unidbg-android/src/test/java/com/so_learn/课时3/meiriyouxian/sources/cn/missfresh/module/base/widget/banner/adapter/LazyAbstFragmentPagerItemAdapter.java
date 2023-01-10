package cn.missfresh.module.base.widget.banner.adapter;

import android.os.Bundle;
import android.view.ViewGroup;
import androidx.collection.SparseArrayCompat;
import androidx.fragment.app.Fragment;
import cn.missfresh.module.base.bean.FragmentPagerItem;
import cn.missfresh.module.base.bean.FragmentPagerItems;
import cn.missfresh.module.base.common.d.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.lang.ref.WeakReference;

public class LazyAbstFragmentPagerItemAdapter extends CustomFragmentStatePagerAdapter {
    protected final SparseArrayCompat<WeakReference<Fragment>> a;
    protected a b;
    private final FragmentPagerItems c;

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        AppMethodBeat.i(23906, false);
        int size = this.c.size();
        AppMethodBeat.o(23906);
        return size;
    }

    @Override // cn.missfresh.module.base.widget.banner.adapter.CustomFragmentStatePagerAdapter
    public Fragment a(int i) {
        AppMethodBeat.i(23907, false);
        Fragment instantiate = b(i).instantiate(this.c.getContext(), i);
        AppMethodBeat.o(23907);
        return instantiate;
    }

    @Override // cn.missfresh.module.base.widget.banner.adapter.CustomFragmentStatePagerAdapter, androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        AppMethodBeat.i(23908, false);
        Fragment fragment = (Fragment) super.instantiateItem(viewGroup, i);
        Bundle arguments = fragment.getArguments();
        if (this.b.a() != null && this.b.a().size() > i) {
            arguments.putString("tab_id", this.b.a().get(i).b());
            arguments.putString("tab_name", this.b.a().get(i).a());
            arguments.putInt("is_home", this.b.a().get(i).c());
        }
        this.a.put(i, new WeakReference(fragment));
        AppMethodBeat.o(23908);
        return fragment;
    }

    @Override // cn.missfresh.module.base.widget.banner.adapter.CustomFragmentStatePagerAdapter, androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        AppMethodBeat.i(23909, false);
        this.a.remove(i);
        super.destroyItem(viewGroup, i, obj);
        AppMethodBeat.o(23909);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int i) {
        AppMethodBeat.i(23910, false);
        CharSequence title = b(i).getTitle();
        AppMethodBeat.o(23910);
        return title;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public float getPageWidth(int i) {
        AppMethodBeat.i(23911, false);
        float pageWidth = super.getPageWidth(i);
        AppMethodBeat.o(23911);
        return pageWidth;
    }

    /* access modifiers changed from: protected */
    public FragmentPagerItem b(int i) {
        AppMethodBeat.i(23913, false);
        FragmentPagerItem fragmentPagerItem = (FragmentPagerItem) this.c.get(i);
        AppMethodBeat.o(23913);
        return fragmentPagerItem;
    }
}
