package cn.missfresh.risk.a;

import android.accessibilityservice.AccessibilityServiceInfo;
import android.content.Context;
import android.view.accessibility.AccessibilityManager;
import cn.missfresh.risk.bean.RiskBean;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.util.ArrayList;
import java.util.List;

/* compiled from: AccUtil */
public class a {
    public static void a(Context context, RiskBean riskBean) {
        int i = 0;
        AppMethodBeat.i(1084, false);
        if (context == null || riskBean == null) {
            AppMethodBeat.o(1084);
            return;
        }
        try {
            AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
            riskBean.setAccEnable(accessibilityManager.isEnabled() ? 1 : 0);
            List<AccessibilityServiceInfo> enabledAccessibilityServiceList = accessibilityManager.getEnabledAccessibilityServiceList(-1);
            if (enabledAccessibilityServiceList != null && enabledAccessibilityServiceList.size() > 0) {
                ArrayList arrayList = new ArrayList();
                for (AccessibilityServiceInfo accessibilityServiceInfo : enabledAccessibilityServiceList) {
                    arrayList.add(accessibilityServiceInfo.getSettingsActivityName());
                    i++;
                    if (i >= cn.missfresh.risk.b.a.e) {
                        break;
                    }
                }
                riskBean.setAccService(arrayList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppMethodBeat.o(1084);
    }
}
