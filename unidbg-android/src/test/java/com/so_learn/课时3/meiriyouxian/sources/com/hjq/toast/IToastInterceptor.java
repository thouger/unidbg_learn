package com.hjq.toast;

import android.widget.Toast;

public interface IToastInterceptor {
    boolean intercept(Toast toast, CharSequence charSequence);
}
