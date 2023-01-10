package com.hjq.toast.style;

import android.content.Context;

public class ToastWhiteStyle extends ToastBlackStyle {
    @Override // com.hjq.toast.style.ToastBlackStyle, com.hjq.toast.IToastStyle
    public int getBackgroundColor() {
        return -1381654;
    }

    @Override // com.hjq.toast.style.ToastBlackStyle, com.hjq.toast.IToastStyle
    public int getTextColor() {
        return -1157627904;
    }

    public ToastWhiteStyle(Context context) {
        super(context);
    }
}
