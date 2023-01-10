package cn.missfresh.module.base.widget.banner.adapter;

import android.app.backup.FullBackup;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import cn.missfresh.utils.a.d;
import java.util.ArrayList;

public abstract class CustomFragmentStatePagerAdapter extends PagerAdapter {
    private final FragmentManager a;
    private FragmentTransaction b;
    private ArrayList<Fragment.SavedState> c;
    private ArrayList<Fragment> d;
    private Fragment e;

    public abstract Fragment a(int i);

    @Override // androidx.viewpager.widget.PagerAdapter
    public void startUpdate(ViewGroup viewGroup) {
        if (viewGroup.getId() == -1) {
            throw new IllegalStateException("ViewPager with adapter " + this + " requires a view id");
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Object instantiateItem(ViewGroup viewGroup, int i) {
        Fragment.SavedState savedState;
        Fragment fragment;
        if (this.d.size() > i && (fragment = this.d.get(i)) != null) {
            return fragment;
        }
        if (this.b == null) {
            this.b = this.a.beginTransaction();
        }
        Fragment a = a(i);
        if (this.c.size() > i && (savedState = this.c.get(i)) != null) {
            a.setInitialSavedState(savedState);
        }
        while (this.d.size() <= i) {
            this.d.add(null);
        }
        a.setMenuVisibility(false);
        a.setUserVisibleHint(false);
        this.d.set(i, a);
        this.b.add(viewGroup.getId(), a);
        return a;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        if (this.b == null) {
            this.b = this.a.beginTransaction();
        }
        while (this.c.size() <= i) {
            this.c.add(null);
        }
        this.c.set(i, null);
        this.d.set(i, null);
        this.b.remove(fragment);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void setPrimaryItem(ViewGroup viewGroup, int i, Object obj) {
        Fragment fragment = (Fragment) obj;
        Fragment fragment2 = this.e;
        if (fragment != fragment2) {
            if (fragment2 != null) {
                fragment2.setMenuVisibility(false);
                this.e.setUserVisibleHint(false);
            }
            if (fragment != null) {
                fragment.setMenuVisibility(true);
                fragment.setUserVisibleHint(true);
            }
            this.e = fragment;
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void finishUpdate(ViewGroup viewGroup) {
        FragmentTransaction fragmentTransaction = this.b;
        if (fragmentTransaction != null) {
            fragmentTransaction.commitNowAllowingStateLoss();
            this.b = null;
        }
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public boolean isViewFromObject(View view, Object obj) {
        return ((Fragment) obj).getView() == view;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public Parcelable saveState() {
        Bundle bundle;
        if (this.c.size() > 0) {
            bundle = new Bundle();
            Fragment.SavedState[] savedStateArr = new Fragment.SavedState[this.c.size()];
            this.c.toArray(savedStateArr);
            bundle.putParcelableArray("states", savedStateArr);
        } else {
            bundle = null;
        }
        for (int i = 0; i < this.d.size(); i++) {
            Fragment fragment = this.d.get(i);
            if (fragment != null && fragment.isAdded()) {
                if (bundle == null) {
                    bundle = new Bundle();
                }
                this.a.putFragment(bundle, FullBackup.FILES_TREE_TOKEN + i, fragment);
            }
        }
        return bundle;
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public void restoreState(Parcelable parcelable, ClassLoader classLoader) {
        if (parcelable != null) {
            Bundle bundle = (Bundle) parcelable;
            bundle.setClassLoader(classLoader);
            Parcelable[] parcelableArray = bundle.getParcelableArray("states");
            this.c.clear();
            this.d.clear();
            if (parcelableArray != null) {
                for (Parcelable parcelable2 : parcelableArray) {
                    this.c.add((Fragment.SavedState) parcelable2);
                }
            }
            for (String str : bundle.keySet()) {
                if (str.startsWith(FullBackup.FILES_TREE_TOKEN)) {
                    int parseInt = Integer.parseInt(str.substring(1));
                    Fragment fragment = this.a.getFragment(bundle, str);
                    if (fragment != null) {
                        while (this.d.size() <= parseInt) {
                            this.d.add(null);
                        }
                        fragment.setMenuVisibility(false);
                        this.d.set(parseInt, fragment);
                    } else {
                        d.a("FragmentStatePagerAdapt", "Bad fragment at key " + str);
                    }
                }
            }
        }
    }
}
