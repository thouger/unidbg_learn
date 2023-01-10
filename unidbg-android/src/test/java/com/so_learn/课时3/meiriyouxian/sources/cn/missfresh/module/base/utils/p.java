package cn.missfresh.module.base.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.alibaba.android.arouter.b.a;

/* compiled from: ClipboardUtil */
public class p {
    public static boolean a(TextView textView) {
        AppMethodBeat.i(23146, false);
        if (textView == null) {
            AppMethodBeat.o(23146);
            return false;
        }
        boolean a = a(textView.getText());
        AppMethodBeat.o(23146);
        return a;
    }

    public static boolean a(String str) {
        AppMethodBeat.i(23147, false);
        boolean a = a((CharSequence) str);
        AppMethodBeat.o(23147);
        return a;
    }

    public static boolean a(CharSequence charSequence) {
        AppMethodBeat.i(23148, false);
        if (b.a(charSequence)) {
            AppMethodBeat.o(23148);
            return false;
        }
        IApplicationDelegateService iApplicationDelegateService = (IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation();
        if (iApplicationDelegateService == null || iApplicationDelegateService.getApplication() == null) {
            AppMethodBeat.o(23148);
            return false;
        }
        ClipboardManager clipboardManager = (ClipboardManager) iApplicationDelegateService.getApplication().getSystemService(Context.CLIPBOARD_SERVICE);
        if (clipboardManager == null) {
            AppMethodBeat.o(23148);
            return false;
        }
        clipboardManager.setPrimaryClip(ClipData.newPlainText(iApplicationDelegateService.getApplication().getString(R.string.app_name), charSequence));
        AppMethodBeat.o(23148);
        return true;
    }
}
