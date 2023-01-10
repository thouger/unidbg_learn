package com.sobot.chat.listener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import com.sobot.chat.widget.dialog.SobotPermissionDialog;

/* compiled from: PermissionListenerImpl */
public class e implements d {
    @Override // com.sobot.chat.listener.d
    public void a() {
    }

    /* compiled from: PermissionListenerImpl */
    /* renamed from: com.sobot.chat.listener.e$1  reason: invalid class name */
    class AnonymousClass1 implements SobotPermissionDialog.a {
        AnonymousClass1() {
        }

        @Override // com.sobot.chat.widget.dialog.SobotPermissionDialog.a
        public void a(Context context, SobotPermissionDialog sobotPermissionDialog) {
            context.startActivity(new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS, Uri.parse("package:" + context.getPackageName())));
            sobotPermissionDialog.dismiss();
        }

        @Override // com.sobot.chat.widget.dialog.SobotPermissionDialog.a
        public void b(Context context, SobotPermissionDialog sobotPermissionDialog) {
            sobotPermissionDialog.dismiss();
        }
    }

    @Override // com.sobot.chat.listener.d
    public void a(Activity activity, String str) {
        if (activity != null) {
            new SobotPermissionDialog(activity, str, new AnonymousClass1()).show();
        }
    }
}
