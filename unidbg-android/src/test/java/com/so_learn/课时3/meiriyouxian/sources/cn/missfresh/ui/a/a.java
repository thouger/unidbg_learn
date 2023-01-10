package cn.missfresh.ui.a;

import android.app.Application;
import android.content.AbstractThreadedSyncAdapter;
import android.view.View;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.hjq.toast.ToastUtils;

/* compiled from: MFToast */
public class a {
    public static void a(Application application) {
        AppMethodBeat.i(2726, false);
        ToastUtils.init(application);
        AppMethodBeat.o(2726);
    }

    public static void a(String str) {
        AppMethodBeat.i(2730, false);
        ToastUtils.initStyle(new b());
        ToastUtils.show((CharSequence) str);
        AppMethodBeat.o(2730);
    }

    public static void b(String str) {
        AppMethodBeat.i(2735, false);
        ToastUtils.initStyle(new b());
        ToastUtils.setGravity(17, 0, 0);
        ToastUtils.show((CharSequence) str);
        AppMethodBeat.o(2735);
    }

    public static void a(View view, int i, int i2, int i3, CharSequence charSequence) {
        AppMethodBeat.i(AbstractThreadedSyncAdapter.LOG_SYNC_DETAILS, false);
        a(view);
        a(i, i2, i3);
        a(charSequence);
        AppMethodBeat.o(AbstractThreadedSyncAdapter.LOG_SYNC_DETAILS);
    }

    public static void a(View view, int i, int i2, int i3, int i4) {
        AppMethodBeat.i(2748, false);
        a(view);
        a(i, i2, i3);
        a(i4);
        AppMethodBeat.o(2748);
    }

    private static void a(View view) {
        AppMethodBeat.i(2753, false);
        if (view != null) {
            ToastUtils.setView(view);
        }
        AppMethodBeat.o(2753);
    }

    private static void a(int i, int i2, int i3) {
        AppMethodBeat.i(2757, false);
        ToastUtils.setGravity(i, i2, i3);
        AppMethodBeat.o(2757);
    }

    private static void a(CharSequence charSequence) {
        AppMethodBeat.i(2760, false);
        ToastUtils.show(charSequence);
        AppMethodBeat.o(2760);
    }

    private static void a(int i) {
        AppMethodBeat.i(2762, false);
        ToastUtils.show(i);
        AppMethodBeat.o(2762);
    }
}
