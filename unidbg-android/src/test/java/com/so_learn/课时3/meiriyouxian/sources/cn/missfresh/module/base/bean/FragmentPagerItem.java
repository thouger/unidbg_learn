package cn.missfresh.module.base.bean;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class FragmentPagerItem extends PagerItem {
    private static final String KEY_POSITION = "FragmentPagerItem:Position";
    private static final String TAG = "FragmentPagerItem";
    private final Bundle args;
    private final String className;

    protected FragmentPagerItem(CharSequence charSequence, float f, String str, Bundle bundle) {
        super(charSequence, f);
        this.className = str;
        this.args = bundle;
    }

    public static FragmentPagerItem of(CharSequence charSequence, Class<? extends Fragment> cls) {
        AppMethodBeat.i(4726, false);
        FragmentPagerItem of = of(charSequence, 1.0f, cls);
        AppMethodBeat.o(4726);
        return of;
    }

    public static FragmentPagerItem of(CharSequence charSequence, Class<? extends Fragment> cls, Bundle bundle) {
        AppMethodBeat.i(4729, false);
        FragmentPagerItem of = of(charSequence, 1.0f, cls, bundle);
        AppMethodBeat.o(4729);
        return of;
    }

    public static FragmentPagerItem of(CharSequence charSequence, float f, Class<? extends Fragment> cls) {
        AppMethodBeat.i(4731, false);
        FragmentPagerItem of = of(charSequence, f, cls, new Bundle());
        AppMethodBeat.o(4731);
        return of;
    }

    public static FragmentPagerItem of(CharSequence charSequence, float f, Class<? extends Fragment> cls, Bundle bundle) {
        AppMethodBeat.i(4734, false);
        FragmentPagerItem fragmentPagerItem = new FragmentPagerItem(charSequence, f, cls.getName(), bundle);
        AppMethodBeat.o(4734);
        return fragmentPagerItem;
    }

    public static boolean hasPosition(Bundle bundle) {
        boolean z = false;
        AppMethodBeat.i(4737, false);
        if (bundle != null && bundle.containsKey(KEY_POSITION)) {
            z = true;
        }
        AppMethodBeat.o(4737);
        return z;
    }

    public static int getPosition(Bundle bundle) {
        int i = 0;
        AppMethodBeat.i(4738, false);
        if (hasPosition(bundle)) {
            i = bundle.getInt(KEY_POSITION);
        }
        AppMethodBeat.o(4738);
        return i;
    }

    static void setPosition(Bundle bundle, int i) {
        AppMethodBeat.i(4740, false);
        bundle.putInt(KEY_POSITION, i);
        AppMethodBeat.o(4740);
    }

    public Fragment instantiate(Context context, int i) {
        AppMethodBeat.i(4744, false);
        setPosition(this.args, i);
        Fragment instantiate = Fragment.instantiate(context, this.className, this.args);
        AppMethodBeat.o(4744);
        return instantiate;
    }
}
