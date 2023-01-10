package cn.missfresh.ui.a;

import android.util.TypedValue;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.hjq.toast.IToastStyle;
import com.hjq.toast.ToastUtils;

/* compiled from: ToastDefaultStyle */
public class b implements IToastStyle {
    @Override // com.hjq.toast.IToastStyle
    public int getBackgroundColor() {
        return -429891488;
    }

    @Override // com.hjq.toast.IToastStyle
    public int getGravity() {
        return 80;
    }

    @Override // com.hjq.toast.IToastStyle
    public int getMaxLines() {
        return 3;
    }

    @Override // com.hjq.toast.IToastStyle
    public int getTextColor() {
        return -285212673;
    }

    @Override // com.hjq.toast.IToastStyle
    public int getXOffset() {
        return 0;
    }

    @Override // com.hjq.toast.IToastStyle
    public int getYOffset() {
        return 160;
    }

    @Override // com.hjq.toast.IToastStyle
    public int getZ() {
        return 30;
    }

    @Override // com.hjq.toast.IToastStyle
    public int getCornerRadius() {
        AppMethodBeat.i(2791, false);
        int applyDimension = (int) TypedValue.applyDimension(1, 10.0f, ToastUtils.getToast().getView().getResources().getDisplayMetrics());
        AppMethodBeat.o(2791);
        return applyDimension;
    }

    @Override // com.hjq.toast.IToastStyle
    public float getTextSize() {
        AppMethodBeat.i(2802, false);
        float applyDimension = TypedValue.applyDimension(2, 13.0f, ToastUtils.getToast().getView().getResources().getDisplayMetrics());
        AppMethodBeat.o(2802);
        return applyDimension;
    }

    @Override // com.hjq.toast.IToastStyle
    public int getPaddingStart() {
        AppMethodBeat.i(2810, false);
        int applyDimension = (int) TypedValue.applyDimension(1, 12.0f, ToastUtils.getToast().getView().getResources().getDisplayMetrics());
        AppMethodBeat.o(2810);
        return applyDimension;
    }

    @Override // com.hjq.toast.IToastStyle
    public int getPaddingTop() {
        AppMethodBeat.i(2814, false);
        int applyDimension = (int) TypedValue.applyDimension(1, 12.0f, ToastUtils.getToast().getView().getResources().getDisplayMetrics());
        AppMethodBeat.o(2814);
        return applyDimension;
    }

    @Override // com.hjq.toast.IToastStyle
    public int getPaddingEnd() {
        AppMethodBeat.i(2817, false);
        int paddingStart = getPaddingStart();
        AppMethodBeat.o(2817);
        return paddingStart;
    }

    @Override // com.hjq.toast.IToastStyle
    public int getPaddingBottom() {
        AppMethodBeat.i(2823, false);
        int paddingTop = getPaddingTop();
        AppMethodBeat.o(2823);
        return paddingTop;
    }
}
