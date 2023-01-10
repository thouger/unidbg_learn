package com.hjq.toast;

import android.widget.Toast;

public interface IToastStrategy {
    public static final int LONG_DURATION_TIMEOUT = 3500;
    public static final int SHORT_DURATION_TIMEOUT = 2000;

    void bind(Toast toast);

    void cancel();

    void show(CharSequence charSequence);
}
