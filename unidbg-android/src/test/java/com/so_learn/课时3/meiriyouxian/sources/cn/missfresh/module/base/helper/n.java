package cn.missfresh.module.base.helper;

import android.app.Activity;
import android.text.TextUtils;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;
import cn.missfresh.module.base.common.providers.IWebPromotionService;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.android.arouter.b.a;

/* compiled from: UserPrivacyManger */
public class n {
    public boolean a() {
        boolean z = false;
        AppMethodBeat.i(13223, false);
        if (e.ad() == 1) {
            z = true;
        }
        AppMethodBeat.o(13223);
        return z;
    }

    public void a(int i) {
        AppMethodBeat.i(13225, false);
        e.c(i);
        AppMethodBeat.o(13225);
    }

    public void a(Activity activity) {
        boolean z = false;
        AppMethodBeat.i(13228, false);
        if (activity == null) {
            AppMethodBeat.o(13228);
        } else if (e.o()) {
            e.c(1);
            AppMethodBeat.o(13228);
        } else if (e.ad() == 3 || e.ad() == 1) {
            AppMethodBeat.o(13228);
        } else {
            IWebPromotionService iWebPromotionService = (IWebPromotionService) a.a().a("/promotion/web_service").navigation();
            if (iWebPromotionService != null && iWebPromotionService.a(activity)) {
                z = true;
            }
            if (!TextUtils.equals("MainUserPrivacyProtocolActivity", activity.getClass().getSimpleName()) && !z && (activity instanceof FragmentActivity)) {
                DialogFragment dialogFragment = (DialogFragment) a.a().a("/main/user_privacy_protocol").navigation();
                if (dialogFragment == null || activity.getFragmentManager() == null || dialogFragment.isAdded()) {
                    AppMethodBeat.o(13228);
                    return;
                } else if (activity.getFragmentManager().findFragmentByTag("isUserProtocol") != null) {
                    AppMethodBeat.o(13228);
                    return;
                } else {
                    dialogFragment.show(((FragmentActivity) activity).getSupportFragmentManager(), "isUserProtocol");
                }
            }
            AppMethodBeat.o(13228);
        }
    }

    public void b(Activity activity) {
        DialogFragment dialogFragment;
        AppMethodBeat.i(13231, false);
        if (activity == null || !(activity instanceof FragmentActivity)) {
            AppMethodBeat.o(13231);
            return;
        }
        if (e.ad() == 1 && (dialogFragment = (DialogFragment) ((FragmentActivity) activity).getSupportFragmentManager().findFragmentByTag("isUserProtocol")) != null && dialogFragment.isVisible() && !dialogFragment.isRemoving()) {
            dialogFragment.dismissAllowingStateLoss();
        }
        AppMethodBeat.o(13231);
    }
}
