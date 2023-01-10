package com.sobot.chat.adapter;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.sobot.chat.fragment.SobotBaseFragment;
import java.util.List;

public class StViewPagerAdapter extends SmartFragmentStatePagerAdapter {
    private String[] a;
    private List<SobotBaseFragment> b;
    private Context c;

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getItemPosition(Object obj) {
        return -2;
    }

    public StViewPagerAdapter(Context context, FragmentManager fragmentManager, String[] strArr, List<SobotBaseFragment> list) {
        super(fragmentManager);
        this.a = strArr;
        this.b = list;
        this.c = context;
    }

    @Override // androidx.fragment.app.FragmentStatePagerAdapter
    public Fragment getItem(int i) {
        return this.b.get(i);
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public int getCount() {
        return this.b.size();
    }

    @Override // androidx.viewpager.widget.PagerAdapter
    public CharSequence getPageTitle(int i) {
        String[] strArr = this.a;
        return (strArr == null || i >= strArr.length) ? "" : strArr[i];
    }
}
