package com.sina.weibo.sdk.a;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.Toast;

/* compiled from: UIUtils */
public class j {
    public static void a(Context context, String str, String str2) {
        if (context != null) {
            new AlertDialog.Builder(context).setTitle(str).setMessage(str2).create().show();
        }
    }

    public static void a(Context context, CharSequence charSequence, int i) {
        if (context != null) {
            Toast.makeText(context, charSequence, i).show();
        }
    }
}
