package cn.missfresh.risk;

import android.content.Context;
import android.view.inputmethod.InputMethodInfo;
import android.view.inputmethod.InputMethodManager;
import cn.missfresh.risk.b.a;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import java.util.ArrayList;
import java.util.List;

/* compiled from: InputUtil */
public class f {
    public static List<String> a(Context context) {
        int i = 0;
        AppMethodBeat.i(MetricsProto.MetricsEvent.DIALOG_VPN_REPLACE_EXISTING, false);
        try {
            List<InputMethodInfo> inputMethodList = ((InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE)).getInputMethodList();
            if (inputMethodList != null && inputMethodList.size() > 0) {
                ArrayList arrayList = new ArrayList();
                for (InputMethodInfo inputMethodInfo : inputMethodList) {
                    arrayList.add(inputMethodInfo.loadLabel(context.getPackageManager()).toString());
                    i++;
                    if (i >= a.f) {
                        break;
                    }
                }
                AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_VPN_REPLACE_EXISTING);
                return arrayList;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(MetricsProto.MetricsEvent.DIALOG_VPN_REPLACE_EXISTING);
        return null;
    }
}
