package com.sobot.chat.presenter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.TextUtils;
import com.sobot.chat.activity.SobotPostMsgActivity;
import com.sobot.chat.api.ZhiChiApi;
import com.sobot.chat.api.model.SobotLeaveMsgConfig;
import com.sobot.chat.api.model.SobotPostMsgTemplate;
import com.sobot.chat.utils.ae;
import com.sobot.chat.widget.dialog.SobotPostMsgTmpListActivity;
import com.sobot.chat.widget.dialog.SobotPostMsgTmpListDialog;
import java.util.ArrayList;

/* compiled from: StPostMsgPresenter */
public class b {
    ZhiChiApi a;
    private Object b;
    private Context c;
    private SobotPostMsgTmpListDialog d;
    private a e;
    private boolean f;
    private boolean g = true;

    /* compiled from: StPostMsgPresenter */
    public interface a {
        void a(Intent intent);
    }

    private b() {
    }

    private b(Object obj, Context context) {
        this.b = obj;
        this.c = context;
        this.g = true;
        this.a = com.sobot.chat.core.channel.a.a(this.c).a();
    }

    public static b a(Object obj, Context context) {
        return new b(obj, context);
    }

    public void a(String str, boolean z, boolean z2, a aVar) {
        if (!TextUtils.isEmpty(str) && !this.f) {
            this.f = true;
            this.e = aVar;
            this.a.getWsTemplate(this.b, str, new AnonymousClass1(str, z, z2));
        }
    }

    /* compiled from: StPostMsgPresenter */
    /* renamed from: com.sobot.chat.presenter.b$1  reason: invalid class name */
    class AnonymousClass1 implements com.sobot.chat.core.http.c.a<ArrayList<SobotPostMsgTemplate>> {
        final /* synthetic */ String a;
        final /* synthetic */ boolean b;
        final /* synthetic */ boolean c;

        AnonymousClass1(String str, boolean z, boolean z2) {
            this.a = str;
            this.b = z;
            this.c = z2;
        }

        public void a(ArrayList<SobotPostMsgTemplate> arrayList) {
            if (!b.this.g) {
                b.this.f = false;
            } else if (arrayList != null && arrayList.size() > 0) {
                if (arrayList.size() == 1) {
                    b.this.a(this.a, arrayList.get(0).getTemplateId());
                } else if (!com.sobot.chat.b.a(1) || !com.sobot.chat.b.a(4)) {
                    b bVar = b.this;
                    bVar.d = bVar.a((Activity) bVar.c, arrayList, new AnonymousClass1());
                    b.this.d.setOnDismissListener(new AnonymousClass2());
                } else {
                    b.this.f = false;
                    Intent intent = new Intent(b.this.c, SobotPostMsgTmpListActivity.class);
                    intent.putExtra("sobotPostMsgTemplateList", arrayList);
                    intent.putExtra("uid", this.a);
                    intent.putExtra("flag_exit_sdk", this.b);
                    intent.putExtra("isShowTicket", this.c);
                    b.this.c.startActivity(intent);
                }
            }
        }

        /* compiled from: StPostMsgPresenter */
        /* access modifiers changed from: package-private */
        /* renamed from: com.sobot.chat.presenter.b$1$1  reason: invalid class name */
        public class AnonymousClass1 implements SobotPostMsgTmpListDialog.a {
            AnonymousClass1() {
            }

            @Override // com.sobot.chat.widget.dialog.SobotPostMsgTmpListDialog.a
            public void a(SobotPostMsgTemplate sobotPostMsgTemplate) {
                b.this.a(AnonymousClass1.this.a, sobotPostMsgTemplate.getTemplateId());
            }
        }

        /* compiled from: StPostMsgPresenter */
        /* access modifiers changed from: package-private */
        /* renamed from: com.sobot.chat.presenter.b$1$2  reason: invalid class name */
        public class AnonymousClass2 implements DialogInterface.OnDismissListener {
            AnonymousClass2() {
            }

            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                b.this.f = false;
            }
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            b.this.a(exc, str);
        }
    }

    public Intent a(String str, SobotLeaveMsgConfig sobotLeaveMsgConfig) {
        Intent intent = new Intent(this.c, SobotPostMsgActivity.class);
        intent.putExtra("intent_key_uid", str);
        intent.putExtra("intent_key_config", sobotLeaveMsgConfig);
        return intent;
    }

    public SobotPostMsgTmpListDialog a(Activity activity, ArrayList<SobotPostMsgTemplate> arrayList, SobotPostMsgTmpListDialog.a aVar) {
        if (activity == null || arrayList == null || aVar == null) {
            return null;
        }
        SobotPostMsgTmpListDialog sobotPostMsgTmpListDialog = new SobotPostMsgTmpListDialog(activity, arrayList, aVar);
        sobotPostMsgTmpListDialog.setCanceledOnTouchOutside(true);
        sobotPostMsgTmpListDialog.show();
        return sobotPostMsgTmpListDialog;
    }

    /* compiled from: StPostMsgPresenter */
    /* access modifiers changed from: package-private */
    /* renamed from: com.sobot.chat.presenter.b$2  reason: invalid class name */
    public class AnonymousClass2 implements com.sobot.chat.core.http.c.a<SobotLeaveMsgConfig> {
        final /* synthetic */ String a;

        AnonymousClass2(String str) {
            this.a = str;
        }

        public void a(SobotLeaveMsgConfig sobotLeaveMsgConfig) {
            if (!b.this.g) {
                b.this.f = false;
                return;
            }
            if (!(sobotLeaveMsgConfig == null || b.this.e == null)) {
                b.this.e.a(b.this.a(this.a, sobotLeaveMsgConfig));
            }
            b.this.f = false;
        }

        @Override // com.sobot.chat.core.http.c.a
        public void a(Exception exc, String str) {
            b.this.a(exc, str);
        }
    }

    public void a(String str, String str2) {
        this.a.getMsgTemplateConfig(this.b, str, str2, new AnonymousClass2(str));
    }

    /* access modifiers changed from: private */
    /* access modifiers changed from: public */
    private void a(Exception exc, String str) {
        this.f = false;
        if (this.g) {
            ae.a(this.c, str);
        }
    }

    public void a() {
        SobotPostMsgTmpListDialog sobotPostMsgTmpListDialog = this.d;
        if (sobotPostMsgTmpListDialog != null && sobotPostMsgTmpListDialog.isShowing()) {
            this.d.dismiss();
        }
        this.g = false;
        com.sobot.chat.core.http.a.a().a(this.b);
    }
}
