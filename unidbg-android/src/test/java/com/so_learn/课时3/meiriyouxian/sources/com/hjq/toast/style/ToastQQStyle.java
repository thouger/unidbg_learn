package com.hjq.toast.style;

import android.content.Context;

public class ToastQQStyle extends BaseToastStyle {
    @Override // com.hjq.toast.IToastStyle
    public int getBackgroundColor() {
        return -13421773;
    }

    @Override // com.hjq.toast.IToastStyle
    public int getTextColor() {
        return -1842205;
    }

    @Override // com.hjq.toast.style.BaseToastStyle, com.hjq.toast.IToastStyle
    public int getZ() {
        return 0;
    }

    public ToastQQStyle(Context context) {
        super(context);
    }

    @Override // com.hjq.toast.IToastStyle
    public int getCornerRadius() {
        return dp2px(4.0f);
    }

    @Override // com.hjq.toast.IToastStyle
    public float getTextSize() {
        return (float) sp2px(12.0f);
    }

    @Override // com.hjq.toast.IToastStyle
    public int getPaddingStart() {
        return dp2px(16.0f);
    }

    @Override // com.hjq.toast.IToastStyle
    public int getPaddingTop() {
        return dp2px(14.0f);
    }
}
