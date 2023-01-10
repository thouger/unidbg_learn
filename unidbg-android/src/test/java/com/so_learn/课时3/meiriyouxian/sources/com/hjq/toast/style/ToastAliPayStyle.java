package com.hjq.toast.style;

import android.content.Context;

public class ToastAliPayStyle extends BaseToastStyle {
    @Override // com.hjq.toast.IToastStyle
    public int getBackgroundColor() {
        return -296265897;
    }

    @Override // com.hjq.toast.style.BaseToastStyle, com.hjq.toast.IToastStyle
    public int getGravity() {
        return 81;
    }

    @Override // com.hjq.toast.IToastStyle
    public int getTextColor() {
        return -1;
    }

    public ToastAliPayStyle(Context context) {
        super(context);
    }

    @Override // com.hjq.toast.style.BaseToastStyle, com.hjq.toast.IToastStyle
    public int getYOffset() {
        return dp2px(100.0f);
    }

    @Override // com.hjq.toast.IToastStyle
    public int getCornerRadius() {
        return dp2px(5.0f);
    }

    @Override // com.hjq.toast.IToastStyle
    public float getTextSize() {
        return (float) sp2px(16.0f);
    }

    @Override // com.hjq.toast.IToastStyle
    public int getPaddingStart() {
        return dp2px(16.0f);
    }

    @Override // com.hjq.toast.IToastStyle
    public int getPaddingTop() {
        return dp2px(10.0f);
    }
}
