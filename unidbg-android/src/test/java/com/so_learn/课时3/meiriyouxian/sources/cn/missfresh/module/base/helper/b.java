package cn.missfresh.module.base.helper;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.List;

/* compiled from: BackHandlerHelper */
public class b {

    /* compiled from: BackHandlerHelper */
    public interface a {
        boolean Q_();
    }

    public static boolean a(FragmentManager fragmentManager) {
        AppMethodBeat.i(12951, false);
        List<Fragment> fragments = fragmentManager.getFragments();
        if (fragments == null) {
            AppMethodBeat.o(12951);
            return false;
        }
        for (int size = fragments.size() - 1; size >= 0; size--) {
            if (b(fragments.get(size))) {
                AppMethodBeat.o(12951);
                return true;
            }
        }
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
            AppMethodBeat.o(12951);
            return true;
        }
        AppMethodBeat.o(12951);
        return false;
    }

    public static boolean a(Fragment fragment) {
        AppMethodBeat.i(12952, false);
        boolean a2 = a(fragment.getChildFragmentManager());
        AppMethodBeat.o(12952);
        return a2;
    }

    public static boolean a(FragmentActivity fragmentActivity) {
        AppMethodBeat.i(12954, false);
        boolean a2 = a(fragmentActivity.getSupportFragmentManager());
        AppMethodBeat.o(12954);
        return a2;
    }

    public static boolean b(Fragment fragment) {
        boolean z = false;
        AppMethodBeat.i(12956, false);
        if (fragment != null && fragment.isVisible() && fragment.getUserVisibleHint() && (fragment instanceof a) && ((a) fragment).Q_()) {
            z = true;
        }
        AppMethodBeat.o(12956);
        return z;
    }
}
