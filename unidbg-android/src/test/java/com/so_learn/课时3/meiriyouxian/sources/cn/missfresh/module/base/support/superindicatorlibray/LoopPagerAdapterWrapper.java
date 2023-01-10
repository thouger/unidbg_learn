package cn.missfresh.module.base.support.superindicatorlibray;

import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class LoopPagerAdapterWrapper extends PagerAdapter {
    private PagerAdapter a;
    private SparseArray<a> b = new SparseArray<>();
    private boolean c = true;
    private boolean d = true;

    /* access modifiers changed from: package-private */
    public void a(boolean z) {
        this.c = z;
    }

    /* access modifiers changed from: package-private */
    public void b(boolean z) {
        this.d = z;
    }

    LoopPagerAdapterWrapper(PagerAdapter pagerAdapter) {
        AppMethodBeat.i(22591, false);
        this.a = pagerAdapter;
        AppMethodBeat.o(22591);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void notifyDataSetChanged() {
        AppMethodBeat.i(22593, false);
        this.b = new SparseArray<>();
        super.notifyDataSetChanged();
        AppMethodBeat.o(22593);
    }

    /* access modifiers changed from: package-private */
    public int a(int i) {
        AppMethodBeat.i(22596, false);
        int a2 = a();
        if (a2 == 0) {
            AppMethodBeat.o(22596);
            return 0;
        }
        if (this.d && (i = (i - 1) % a2) < 0) {
            i += a2;
        }
        AppMethodBeat.o(22596);
        return i;
    }

    public int b(int i) {
        return this.d ? i + 1 : i;
    }

    private int c() {
        return this.d ? 1 : 0;
    }

    private int d() {
        AppMethodBeat.i(22597, false);
        int c = (c() + a()) - 1;
        AppMethodBeat.o(22597);
        return c;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        AppMethodBeat.i(22598, false);
        int a2 = a();
        if (this.d) {
            a2 += 2;
        }
        AppMethodBeat.o(22598);
        return a2;
    }

    public int a() {
        AppMethodBeat.i(22599, false);
        int count = this.a.getCount();
        AppMethodBeat.o(22599);
        return count;
    }

    public PagerAdapter b() {
        return this.a;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        a aVar;
        AppMethodBeat.i(22600, false);
        PagerAdapter pagerAdapter = this.a;
        int a2 = ((pagerAdapter instanceof FragmentPagerAdapter) || (pagerAdapter instanceof FragmentStatePagerAdapter)) ? i : a(i);
        if (!this.c || (aVar = (a) this.b.get(i)) == null) {
            Object instantiateItem = this.a.instantiateItem(viewGroup, a2);
            AppMethodBeat.o(22600);
            return instantiateItem;
        }
        this.b.remove(i);
        Object obj = aVar.c;
        AppMethodBeat.o(22600);
        return obj;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        AppMethodBeat.i(22602, false);
        int c = c();
        int d = d();
        PagerAdapter pagerAdapter = this.a;
        int a2 = ((pagerAdapter instanceof FragmentPagerAdapter) || (pagerAdapter instanceof FragmentStatePagerAdapter)) ? i : a(i);
        if (!this.c || !(i == c || i == d)) {
            this.a.destroyItem(viewGroup, a2, obj);
        } else {
            this.b.put(i, new a(viewGroup, a2, obj));
        }
        AppMethodBeat.o(22602);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void finishUpdate(ViewGroup viewGroup) {
        AppMethodBeat.i(22604, false);
        this.a.finishUpdate(viewGroup);
        AppMethodBeat.o(22604);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        AppMethodBeat.i(22606, false);
        boolean isViewFromObject = this.a.isViewFromObject(view, obj);
        AppMethodBeat.o(22606);
        return isViewFromObject;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
        AppMethodBeat.i(22608, false);
        this.a.restoreState(parcelable, classLoader);
        AppMethodBeat.o(22608);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Parcelable saveState() {
        AppMethodBeat.i(22609, false);
        Parcelable saveState = this.a.saveState();
        AppMethodBeat.o(22609);
        return saveState;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void startUpdate(ViewGroup viewGroup) {
        AppMethodBeat.i(22610, false);
        this.a.startUpdate(viewGroup);
        AppMethodBeat.o(22610);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        AppMethodBeat.i(22611, false);
        this.a.setPrimaryItem(viewGroup, i, obj);
        AppMethodBeat.o(22611);
    }

    static class a {
        ViewGroup a;
        int b;
        Object c;

        public a(ViewGroup viewGroup, int i, Object obj) {
            this.a = viewGroup;
            this.b = i;
            this.c = obj;
        }
    }
}
