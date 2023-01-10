package com.sina.weibo.sdk.cmd;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.sina.weibo.sdk.a.h;
import java.util.List;

/* access modifiers changed from: package-private */
public class AppInvokeCmdExecutor {
    private Context a;
    private a b = new a(this.a.getMainLooper());

    /* access modifiers changed from: private */
    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (message.what == 1) {
                AppInvokeCmdExecutor.b(AppInvokeCmdExecutor.this.a, (b) message.obj);
            }
        }
    }

    public AppInvokeCmdExecutor(Context context) {
        this.a = context.getApplicationContext();
    }

    public boolean a(b bVar) {
        if (bVar == null || TextUtils.isEmpty(bVar.e()) || TextUtils.isEmpty(bVar.b())) {
            return false;
        }
        Message obtainMessage = this.b.obtainMessage();
        obtainMessage.what = 1;
        obtainMessage.obj = bVar;
        this.b.sendMessageDelayed(obtainMessage, bVar.g());
        return true;
    }

    /* access modifiers changed from: private */
    public static void b(Context context, b bVar) {
        h.a.a().c(bVar.e()).a(c(context, bVar)).b(bVar.f()).a(bVar.e()).a(context).a(2);
    }

    private static PendingIntent c(Context context, b bVar) {
        List<ResolveInfo> queryIntentActivities;
        String b = bVar.b();
        String c = bVar.c();
        Intent a2 = a(b, bVar.a());
        if (a2 == null || (queryIntentActivities = context.getPackageManager().queryIntentActivities(a2, 65536)) == null || queryIntentActivities.isEmpty()) {
            a2 = null;
        }
        if (a2 == null) {
            a2 = a(c);
        }
        if (a2 == null) {
            return null;
        }
        a2.setFlags(268435456);
        return PendingIntent.getActivity(context, 0, a2, 134217728);
    }

    private static Intent a(String str, String str2) {
        if (TextUtils.isEmpty(str) || !Uri.parse(str).isHierarchical()) {
            return null;
        }
        Uri parse = Uri.parse(str);
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(parse);
        intent.setPackage(str2);
        return intent;
    }

    private static Intent a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        Uri parse = Uri.parse(str);
        String scheme = parse.getScheme();
        if (!scheme.equalsIgnoreCase("http") && !scheme.equalsIgnoreCase("https")) {
            return null;
        }
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        intent.setData(parse);
        return intent;
    }
}
