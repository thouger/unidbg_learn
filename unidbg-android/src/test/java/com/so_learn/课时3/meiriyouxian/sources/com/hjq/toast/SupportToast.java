package com.hjq.toast;

import android.app.Application;

/* access modifiers changed from: package-private */
public final class SupportToast extends BaseToast {
    private final ToastHelper mToastHelper;

    SupportToast(Application application) {
        super(application);
        this.mToastHelper = new ToastHelper(this, application);
    }

    @Override // android.widget.Toast
    public void show() {
        this.mToastHelper.show();
    }

    @Override // android.widget.Toast
    public void cancel() {
        this.mToastHelper.cancel();
    }
}
