package cn.missfresh.module.base.bean;

import android.content.Context;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;

public class FragmentPagerItems extends PagerItems<FragmentPagerItem> {
    public FragmentPagerItems(Context context) {
        super(context);
    }

    public static Creator with(Context context) {
        AppMethodBeat.i(4781, false);
        Creator creator = new Creator(context);
        AppMethodBeat.o(4781);
        return creator;
    }
}
