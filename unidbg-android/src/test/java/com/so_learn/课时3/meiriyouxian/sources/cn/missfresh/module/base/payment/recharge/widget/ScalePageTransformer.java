package cn.missfresh.module.base.payment.recharge.widget;

import android.view.View;
import androidx.viewpager.widget.ViewPager;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;

public class ScalePageTransformer implements ViewPager.PageTransformer {
    public ScalePageTransformer() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.PROVISIONING_TOTAL_TASK_TIME_MS));
    }

    @Override // androidx.viewpager.widget.ViewPager.PageTransformer
    public void transformPage(View view, float f) {
        JniLib.cV(this, view, Float.valueOf(f), Integer.valueOf((int) MetricsProto.MetricsEvent.PROVISIONING_COPY_ACCOUNT_STATUS));
    }
}
