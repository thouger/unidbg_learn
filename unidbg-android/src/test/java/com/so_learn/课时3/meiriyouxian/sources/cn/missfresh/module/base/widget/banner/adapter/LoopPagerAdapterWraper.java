package cn.missfresh.module.base.widget.banner.adapter;

import android.database.DataSetObserver;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class LoopPagerAdapterWraper extends PagerAdapter {
    private PagerAdapter a;
    private SparseArray<a> b = new SparseArray<>();
    private boolean c;
    private boolean d;

    public LoopPagerAdapterWraper(PagerAdapter pagerAdapter, boolean z) {
        AppMethodBeat.i(23915, false);
        this.a = pagerAdapter;
        this.d = z;
        pagerAdapter.registerDataSetObserver(new AnonymousClass1());
        AppMethodBeat.o(23915);
    }

    /* renamed from: cn.missfresh.module.base.widget.banner.adapter.LoopPagerAdapterWraper$1  reason: invalid class name */
    class AnonymousClass1 extends DataSetObserver {
        AnonymousClass1() {
        }

        @Override // android.database.DataSetObserver
        public void onChanged() {
            AppMethodBeat.i(23914, false);
            LoopPagerAdapterWraper.this.notifyDataSetChanged();
            AppMethodBeat.o(23914);
        }
    }

    public void a(boolean z) {
        this.c = z;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void notifyDataSetChanged() {
        AppMethodBeat.i(23916, false);
        this.b.clear();
        super.notifyDataSetChanged();
        AppMethodBeat.o(23916);
    }

    public int a(int i) {
        AppMethodBeat.i(23917, false);
        if (!this.d) {
            AppMethodBeat.o(23917);
            return i;
        }
        int a2 = a();
        if (a2 == 0) {
            AppMethodBeat.o(23917);
            return 0;
        }
        int i2 = (i - 1) % a2;
        if (i2 < 0) {
            i2 += a2;
        }
        AppMethodBeat.o(23917);
        return i2;
    }

    public int b(int i) {
        return !this.d ? i : i + 1;
    }

    private int c() {
        return this.d ? 1 : 0;
    }

    private int d() {
        AppMethodBeat.i(23918, false);
        int c = (this.d ? c() + a() : a()) - 1;
        AppMethodBeat.o(23918);
        return c;
    }

    public int a() {
        AppMethodBeat.i(23919, false);
        int count = this.a.getCount();
        AppMethodBeat.o(23919);
        return count;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        AppMethodBeat.i(23920, false);
        int count = this.d ? this.a.getCount() + 2 : a();
        AppMethodBeat.o(23920);
        return count;
    }

    public PagerAdapter b() {
        return this.a;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        a aVar;
        AppMethodBeat.i(23921, false);
        PagerAdapter pagerAdapter = this.a;
        int a2 = ((pagerAdapter instanceof FragmentPagerItemAdapter) || (pagerAdapter instanceof FragmentStatePagerAdapter)) ? i : a(i);
        if (!this.c || (aVar = (a) this.b.get(i)) == null) {
            Object instantiateItem = this.a.instantiateItem(viewGroup, a2);
            AppMethodBeat.o(23921);
            return instantiateItem;
        }
        this.b.remove(i);
        Object obj = aVar.a;
        AppMethodBeat.o(23921);
        return obj;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        AppMethodBeat.i(23922, false);
        int c = c();
        int d = d();
        PagerAdapter pagerAdapter = this.a;
        int a2 = ((pagerAdapter instanceof FragmentPagerItemAdapter) || (pagerAdapter instanceof FragmentStatePagerAdapter)) ? i : a(i);
        if (!this.c || !(i == c || i == d)) {
            this.a.destroyItem(viewGroup, a2, obj);
        } else {
            this.b.put(i, new a(obj));
        }
        AppMethodBeat.o(23922);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void finishUpdate(ViewGroup viewGroup) {
        AppMethodBeat.i(23923, false);
        this.a.finishUpdate(viewGroup);
        AppMethodBeat.o(23923);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        AppMethodBeat.i(23924, false);
        boolean isViewFromObject = this.a.isViewFromObject(view, obj);
        AppMethodBeat.o(23924);
        return isViewFromObject;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
        AppMethodBeat.i(23925, false);
        this.a.restoreState(parcelable, classLoader);
        AppMethodBeat.o(23925);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Parcelable saveState() {
        AppMethodBeat.i(23926, false);
        Parcelable saveState = this.a.saveState();
        AppMethodBeat.o(23926);
        return saveState;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void startUpdate(ViewGroup viewGroup) {
        AppMethodBeat.i(23927, false);
        this.a.startUpdate(viewGroup);
        AppMethodBeat.o(23927);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        AppMethodBeat.i(23928, false);
        this.a.setPrimaryItem(viewGroup, i, obj);
        AppMethodBeat.o(23928);
    }

    public static class a {
        Object a;

        public a(Object obj) {
            this.a = obj;
        }
    }
}
