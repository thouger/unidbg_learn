package com.sina.weibo.sdk.auth.sso;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.provider.Telephony;
import android.text.TextUtils;
import com.sina.a.a;
import com.sina.weibo.sdk.a.d;
import com.sina.weibo.sdk.a.i;
import com.sina.weibo.sdk.a.k;
import com.sina.weibo.sdk.auth.a;
import com.sina.weibo.sdk.auth.c;
import com.sina.weibo.sdk.b;
import com.sina.weibo.sdk.cmd.e;
import com.sina.weibo.sdk.exception.WeiboDialogException;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.statistic.WBAgent;
import com.xiaomi.mipush.sdk.PushMessageHelper;
import java.util.HashMap;

public class SsoHandler {
    private a a;
    private c b;
    private Activity c;
    private int d;
    private b.a e;
    private a f;
    private ServiceConnection g = new AnonymousClass1();

    /* access modifiers changed from: private */
    public enum AuthType {
        ALL,
        SsoOnly,
        WebOnly
    }

    /* renamed from: com.sina.weibo.sdk.auth.sso.SsoHandler$1  reason: invalid class name */
    class AnonymousClass1 implements ServiceConnection {
        AnonymousClass1() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            SsoHandler.this.a.a(SsoHandler.this.b);
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            com.sina.a.a a = a.AbstractBinderC0132a.a(iBinder);
            try {
                String a2 = a.a();
                String b = a.b();
                SsoHandler.this.c.getApplicationContext().unbindService(SsoHandler.this.g);
                if (!SsoHandler.this.a(a2, b)) {
                    SsoHandler.this.a.a(SsoHandler.this.b);
                }
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    public SsoHandler(Activity activity, com.sina.weibo.sdk.auth.a aVar) {
        this.c = activity;
        this.f = aVar;
        this.a = new a(activity, aVar);
        this.e = b.a(activity).a();
        com.sina.weibo.sdk.a.b.a(this.c).a(aVar.a());
    }

    public void a(c cVar) {
        a(32973, cVar, AuthType.ALL);
        e.a(this.c, this.f.a()).a();
    }

    private void a(int i, c cVar, AuthType authType) {
        this.d = i;
        this.b = cVar;
        boolean z = authType == AuthType.SsoOnly;
        if (authType == AuthType.WebOnly) {
            if (cVar != null) {
                this.a.a(cVar);
            }
        } else if (a(this.c.getApplicationContext())) {
        } else {
            if (z) {
                c cVar2 = this.b;
                if (cVar2 != null) {
                    cVar2.a(new WeiboException("not install weibo client!!!!!"));
                    return;
                }
                return;
            }
            this.a.a(this.b);
        }
    }

    public void a(int i, int i2, Intent intent) {
        d.a("Weibo_SSO_login", "requestCode: " + i + ", resultCode: " + i2 + ", data: " + intent);
        if (i == this.d) {
            if (i2 == -1) {
                if (i.a(this.c, this.e, intent)) {
                    String stringExtra = intent.getStringExtra("error");
                    if (stringExtra == null) {
                        stringExtra = intent.getStringExtra(PushMessageHelper.ERROR_TYPE);
                    }
                    if (stringExtra == null) {
                        Bundle extras = intent.getExtras();
                        com.sina.weibo.sdk.auth.b a = com.sina.weibo.sdk.auth.b.a(extras);
                        if (a == null || !a.a()) {
                            d.a("Weibo_SSO_login", "Failed to receive access token by SSO");
                            this.a.a(this.b);
                            return;
                        }
                        d.a("Weibo_SSO_login", "Login Success! " + a.toString());
                        this.b.a(extras);
                    } else if (stringExtra.equals("access_denied") || stringExtra.equals("OAuthAccessDeniedException")) {
                        d.a("Weibo_SSO_login", "Login canceled by user.");
                        this.b.a();
                    } else {
                        String stringExtra2 = intent.getStringExtra("error_description");
                        if (stringExtra2 != null) {
                            stringExtra = String.valueOf(stringExtra) + ":" + stringExtra2;
                        }
                        d.a("Weibo_SSO_login", "Login failed: " + stringExtra);
                        this.b.a(new WeiboDialogException(stringExtra, i2, stringExtra2));
                    }
                }
            } else if (i2 != 0) {
            } else {
                if (intent != null) {
                    d.a("Weibo_SSO_login", "Login failed: " + intent.getStringExtra("error"));
                    this.b.a(new WeiboDialogException(intent.getStringExtra("error"), intent.getIntExtra(Telephony.TextBasedSmsColumns.ERROR_CODE, -1), intent.getStringExtra("failing_url")));
                    return;
                }
                d.a("Weibo_SSO_login", "Login canceled by user.");
                this.b.a();
            }
        } else if (i != 40000) {
        } else {
            if (i2 == -1) {
                Bundle extras2 = intent.getExtras();
                com.sina.weibo.sdk.auth.b a2 = com.sina.weibo.sdk.auth.b.a(extras2);
                if (a2 != null && a2.a()) {
                    d.a("Weibo_SSO_login", "Login Success! " + a2.toString());
                    this.b.a(extras2);
                }
            } else if (i2 != 0) {
            } else {
                if (intent != null) {
                    d.a("Weibo_SSO_login", "Login failed: " + intent.getStringExtra("error"));
                    String stringExtra3 = intent.getStringExtra("error");
                    if (stringExtra3 == null) {
                        stringExtra3 = intent.getStringExtra(PushMessageHelper.ERROR_TYPE);
                    }
                    if (stringExtra3 != null) {
                        this.b.a(new WeiboDialogException(intent.getStringExtra("error"), intent.getIntExtra(Telephony.TextBasedSmsColumns.ERROR_CODE, -1), intent.getStringExtra("error_description")));
                        return;
                    }
                    return;
                }
                d.a("Weibo_SSO_login", "Login canceled by user.");
                this.b.a();
            }
        }
    }

    private boolean a(Context context) {
        if (!a()) {
            return false;
        }
        String a = this.e.a();
        Intent intent = new Intent("com.sina.weibo.remotessoservice");
        intent.setPackage(a);
        return context.bindService(intent, this.g, 1);
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private boolean a(String str, String str2) {
        Intent intent = new Intent();
        intent.setClassName(str, str2);
        intent.putExtras(this.a.a().f());
        intent.putExtra("_weibo_command_type", 3);
        String valueOf = String.valueOf(System.currentTimeMillis());
        intent.putExtra("_weibo_transaction", valueOf);
        a(this.c, valueOf, "sso");
        intent.putExtra("aid", k.b(this.c, this.f.a()));
        if (!i.a(this.c, intent)) {
            return false;
        }
        String b = k.b(this.c, this.f.a());
        if (!TextUtils.isEmpty(b)) {
            intent.putExtra("aid", b);
        }
        try {
            this.c.startActivityForResult(intent, this.d);
            return true;
        } catch (ActivityNotFoundException unused) {
            return false;
        }
    }

    public boolean a() {
        b.a aVar = this.e;
        return aVar != null && aVar.c();
    }

    public void a(Context context, String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("other_app_action_start_time", str);
        try {
            WBAgent.onEvent(context, str2, hashMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
