package com.alipay.sdk.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.view.KeyEvent;
import com.alipay.sdk.util.e;

public class b {
    public static Dialog a(Context context, String str, String str2, String str3, DialogInterface.OnClickListener onClickListener, String str4, DialogInterface.OnClickListener onClickListener2) {
        AlertDialog.Builder a = a(context, str, str3, onClickListener, str4, onClickListener2);
        a.setTitle(str);
        a.setMessage(str2);
        AlertDialog create = a.create();
        create.setCanceledOnTouchOutside(false);
        create.setOnKeyListener(new AnonymousClass1());
        try {
            create.show();
        } catch (Throwable th) {
            e.a("mspl", "showDialog ", th);
        }
        return create;
    }

    /* renamed from: com.alipay.sdk.widget.b$1  reason: invalid class name */
    static class AnonymousClass1 implements DialogInterface.OnKeyListener {
        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            return i == 4;
        }

        AnonymousClass1() {
        }
    }

    private static AlertDialog.Builder a(Context context, String str, String str2, DialogInterface.OnClickListener onClickListener, String str3, DialogInterface.OnClickListener onClickListener2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (!TextUtils.isEmpty(str3) && onClickListener2 != null) {
            builder.setPositiveButton(str3, onClickListener2);
        }
        if (!TextUtils.isEmpty(str2) && onClickListener != null) {
            builder.setNegativeButton(str2, onClickListener);
        }
        return builder;
    }
}
