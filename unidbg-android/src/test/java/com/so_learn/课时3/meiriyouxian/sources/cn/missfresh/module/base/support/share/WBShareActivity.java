package cn.missfresh.module.base.support.share;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiEnterpriseConfig;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.KeyEvent;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.manager.g;
import cn.missfresh.module.base.network.d;
import cn.missfresh.module.base.support.share.WeiboShareHelper;
import cn.missfresh.module.base.utils.l;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import com.sina.weibo.sdk.api.ImageObject;
import com.sina.weibo.sdk.api.TextObject;
import com.sina.weibo.sdk.api.WeiboMultiMessage;
import com.sina.weibo.sdk.api.share.BaseResponse;
import com.sina.weibo.sdk.api.share.IWeiboHandler;
import com.sina.weibo.sdk.api.share.IWeiboShareAPI;
import com.sina.weibo.sdk.api.share.SendMultiMessageToWeiboRequest;
import com.sina.weibo.sdk.api.share.WeiboShareSDK;
import com.sina.weibo.sdk.auth.b;
import com.sina.weibo.sdk.auth.c;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.taobao.accs.common.Constants;
import de.greenrobot.event.EventBus;

public class WBShareActivity extends Activity implements WeiboShareHelper.a, IWeiboHandler.Response {
    protected ProgressDialog a;
    int b = 0;
    boolean c = false;
    private String d = "WBShareActivity";
    private IWeiboShareAPI e;
    private WeiboShareHelper f;
    private com.sina.weibo.sdk.auth.a g;
    private SsoHandler h;
    private WeiboShareHelper.b i;
    private Handler j;

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        AppMethodBeat.at(this, z);
    }

    static /* synthetic */ void a(WBShareActivity wBShareActivity) {
        AppMethodBeat.i(22523, false);
        wBShareActivity.c();
        AppMethodBeat.o(22523);
    }

    static /* synthetic */ void a(WBShareActivity wBShareActivity, ImageObject imageObject) {
        AppMethodBeat.i(22520, false);
        wBShareActivity.a(imageObject);
        AppMethodBeat.o(22520);
    }

    static /* synthetic */ void a(WBShareActivity wBShareActivity, ImageObject imageObject, Bitmap bitmap) {
        AppMethodBeat.i(22519, false);
        wBShareActivity.a(imageObject, bitmap);
        AppMethodBeat.o(22519);
    }

    static /* synthetic */ void a(WBShareActivity wBShareActivity, boolean z) {
        AppMethodBeat.i(22522, false);
        wBShareActivity.b(z);
        AppMethodBeat.o(22522);
    }

    static /* synthetic */ void b(WBShareActivity wBShareActivity, ImageObject imageObject) {
        AppMethodBeat.i(22521, false);
        wBShareActivity.b(imageObject);
        AppMethodBeat.o(22521);
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(22498, false);
        requestWindowFeature(1);
        super.onCreate(bundle);
        setTheme(R.style.style_weibo_share_dialog);
        if (Build.VERSION.SDK_INT < 26) {
            setRequestedOrientation(1);
        }
        String str = this.d;
        d.d(str, "onCreate " + bundle);
        this.j = new Handler();
        this.i = new WeiboShareHelper.b();
        this.i.a = getIntent().getStringExtra("mTitle");
        this.i.b = getIntent().getStringExtra("mText");
        this.i.c = getIntent().getStringExtra("mImageUrl");
        this.i.d = getIntent().getStringExtra("mWebUrl");
        this.i.e = g.a().d();
        this.i.f = getIntent().getByteArrayExtra("mBitmapBytes");
        this.f = new WeiboShareHelper(this);
        if (bundle != null) {
            IWeiboShareAPI iWeiboShareAPI = this.e;
            if (iWeiboShareAPI != null) {
                iWeiboShareAPI.handleWeiboResponse(getIntent(), this);
            }
            AppMethodBeat.o(22498);
            return;
        }
        b a2 = a.a(getApplicationContext());
        if (a2 == null || TextUtils.isEmpty(a2.c()) || !a2.a()) {
            this.g = new com.sina.weibo.sdk.auth.a(this, "2503419066", "https://www.missfresh.cn/", "email,direct_messages_read,direct_messages_write,friendships_groups_read,friendships_groups_write,statuses_to_me_read,follow_app_official_microblog,invitation_write");
            this.h = new SsoHandler(this, this.g);
            this.h.a(new a(this));
        } else {
            a();
            c();
        }
        AppMethodBeat.o(22498);
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onResume() {
        AppMethodBeat.i(22500, false);
        super.onResume();
        String str = this.d;
        StringBuilder sb = new StringBuilder();
        sb.append(this);
        sb.append(" resume count = ");
        int i = this.b + 1;
        this.b = i;
        sb.append(i);
        d.d(str, sb.toString());
        if (this.b == 3 && this.c) {
            finish();
        } else if (this.b == 2 && !this.c) {
            finish();
        }
        AppMethodBeat.o(22500);
    }

    private void c() {
        AppMethodBeat.i(22501, false);
        this.f.a(a(this.i.d), this);
        AppMethodBeat.o(22501);
    }

    private TextObject d() {
        AppMethodBeat.i(22502, false);
        TextObject textObject = new TextObject();
        textObject.text = a(this.i.a) + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + a(this.i.b) + WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER + a(this.i.d);
        AppMethodBeat.o(22502);
        return textObject;
    }

    private String a(String str) {
        AppMethodBeat.i(22503, false);
        if (cn.missfresh.utils.b.a(str)) {
            str = WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER;
        }
        AppMethodBeat.o(22503);
        return str;
    }

    private void e() {
        AppMethodBeat.i(22504, false);
        d.d(this.d, "onCreate...1");
        ImageObject imageObject = new ImageObject();
        if (this.i.f != null) {
            imageObject.imageData = this.i.f;
            a(imageObject);
            AppMethodBeat.o(22504);
        } else if (this.i.e != null) {
            a(imageObject, this.i.e);
            AppMethodBeat.o(22504);
        } else {
            if (cn.missfresh.utils.b.a(this.i.c)) {
                imageObject.setImageObject(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                a(imageObject);
            } else {
                cn.missfresh.module.base.network.d.a(this, this.i.c, -1, -1, new AnonymousClass1(this, imageObject));
            }
            AppMethodBeat.o(22504);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.share.WBShareActivity$1  reason: invalid class name */
    public class AnonymousClass1 implements d.b<Bitmap> {
        final /* synthetic */ ImageObject a;
        final /* synthetic */ WBShareActivity b;

        AnonymousClass1(WBShareActivity wBShareActivity, ImageObject imageObject) {
            JniLib.cV(this, wBShareActivity, imageObject, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_UNKNOWN));
        }

        @Override // cn.missfresh.module.base.network.d.b
        public /* synthetic */ void a(Object obj) {
            AppMethodBeat.i(22459, false);
            a((Bitmap) obj);
            AppMethodBeat.o(22459);
        }

        public void a(Bitmap bitmap) {
            AppMethodBeat.i(22456, false);
            WBShareActivity.a(this.b, this.a, bitmap);
            AppMethodBeat.o(22456);
        }

        @Override // cn.missfresh.module.base.network.d.b
        public void a(Exception exc, Drawable drawable) {
            AppMethodBeat.i(22458, false);
            this.a.setImageObject(BitmapFactory.decodeResource(this.b.getResources(), R.mipmap.ic_launcher));
            WBShareActivity.a(this.b, this.a);
            AppMethodBeat.o(22458);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.share.WBShareActivity$2  reason: invalid class name */
    public class AnonymousClass2 extends Thread {
        final /* synthetic */ Bitmap a;
        final /* synthetic */ ImageObject b;

        AnonymousClass2(Bitmap bitmap, ImageObject imageObject) {
            this.a = bitmap;
            this.b = imageObject;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            AppMethodBeat.i(22461, false);
            super.run();
            if (this.a != null) {
                this.b.imageData = new l().b(this.a, 500000);
            } else {
                this.b.setImageObject(BitmapFactory.decodeResource(WBShareActivity.this.getResources(), R.mipmap.ic_launcher));
            }
            WBShareActivity.a(WBShareActivity.this, this.b);
            AppMethodBeat.o(22461);
        }
    }

    private void a(ImageObject imageObject, Bitmap bitmap) {
        AppMethodBeat.i(22505, false);
        new AnonymousClass2(bitmap, imageObject).start();
        AppMethodBeat.o(22505);
    }

    private void a(ImageObject imageObject) {
        AppMethodBeat.i(22507, false);
        b();
        this.j.post(new AnonymousClass3(this, imageObject));
        AppMethodBeat.o(22507);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.share.WBShareActivity$3  reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ ImageObject a;
        final /* synthetic */ WBShareActivity b;

        AnonymousClass3(WBShareActivity wBShareActivity, ImageObject imageObject) {
            JniLib.cV(this, wBShareActivity, imageObject, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_UNKNOWN));
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(22464, false);
            WBShareActivity.b(this.b, this.a);
            AppMethodBeat.o(22464);
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onActivityResult(int i, int i2, Intent intent) {
        AppMethodBeat.i(22508, false);
        super.onActivityResult(i, i2, intent);
        SsoHandler ssoHandler = this.h;
        if (ssoHandler != null) {
            ssoHandler.a(i, i2, intent);
        }
        AppMethodBeat.o(22508);
    }

    /* access modifiers changed from: protected */
    @Override // android.app.Activity
    public void onNewIntent(Intent intent) {
        AppMethodBeat.i(22509, false);
        super.onNewIntent(intent);
        cn.missfresh.utils.a.d.d(this.d, "onNewIntent...");
        this.e.handleWeiboResponse(intent, this);
        AppMethodBeat.o(22509);
    }

    @Override // com.sina.weibo.sdk.api.share.IWeiboHandler.Response
    public void onResponse(BaseResponse baseResponse) {
        AppMethodBeat.i(22510, false);
        cn.missfresh.utils.a.d.d(this.d, "onResponse-----------------------");
        int i = baseResponse.errCode;
        if (i == 0) {
            cn.missfresh.ui.a.a.a("\u5206\u4eab\u6210\u529f");
            EventBus.getDefault().post(baseResponse);
        } else if (i == 1) {
            cn.missfresh.ui.a.a.a("\u5206\u4eab\u53d6\u6d88");
            EventBus.getDefault().post(baseResponse);
        } else if (i == 2) {
            cn.missfresh.ui.a.a.a("\u5206\u4eab\u5931\u8d25");
        }
        finish();
        AppMethodBeat.o(22510);
    }

    private void b(ImageObject imageObject) {
        String str;
        AppMethodBeat.i(22511, false);
        try {
            WeiboMultiMessage weiboMultiMessage = new WeiboMultiMessage();
            weiboMultiMessage.textObject = d();
            weiboMultiMessage.imageObject = imageObject;
            SendMultiMessageToWeiboRequest sendMultiMessageToWeiboRequest = new SendMultiMessageToWeiboRequest();
            sendMultiMessageToWeiboRequest.transaction = String.valueOf(System.currentTimeMillis());
            sendMultiMessageToWeiboRequest.multiMessage = weiboMultiMessage;
            if (!this.e.isWeiboAppSupportAPI()) {
                com.sina.weibo.sdk.auth.a aVar = new com.sina.weibo.sdk.auth.a(this, "2503419066", "https://www.missfresh.cn/", "email,direct_messages_read,direct_messages_write,friendships_groups_read,friendships_groups_write,statuses_to_me_read,follow_app_official_microblog,invitation_write");
                b a2 = a.a(getApplicationContext());
                if (a2 != null) {
                    str = a2.c();
                } else {
                    str = "";
                }
                this.e.sendRequest(this, sendMultiMessageToWeiboRequest, aVar, str, new AnonymousClass4(this));
            } else if (!this.e.sendRequest(this, sendMultiMessageToWeiboRequest)) {
                cn.missfresh.ui.a.a.a("\u5206\u4eab\u5931\u8d25");
                finish();
            }
        } catch (Exception unused) {
            finish();
        }
        AppMethodBeat.o(22511);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.share.WBShareActivity$4  reason: invalid class name */
    public class AnonymousClass4 implements c {
        final /* synthetic */ WBShareActivity a;

        AnonymousClass4(WBShareActivity wBShareActivity) {
            JniLib.cV(this, wBShareActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_READ_CALENDAR));
        }

        @Override // com.sina.weibo.sdk.auth.c
        public void a() {
        }

        @Override // com.sina.weibo.sdk.auth.c
        public void a(WeiboException weiboException) {
        }

        @Override // com.sina.weibo.sdk.auth.c
        public void a(Bundle bundle) {
            AppMethodBeat.i(22469, false);
            a.a(this.a.getApplicationContext(), b.a(bundle));
            AppMethodBeat.o(22469);
        }
    }

    private void f() {
        AppMethodBeat.i(22512, false);
        if (this.i == null) {
            b();
            finish();
            AppMethodBeat.o(22512);
            return;
        }
        this.e = WeiboShareSDK.createWeiboAPI(this, "2503419066");
        this.e.registerApp();
        if (this.i.a()) {
            e();
        } else {
            a((ImageObject) null);
        }
        AppMethodBeat.o(22512);
    }

    @Override // cn.missfresh.module.base.support.share.WeiboShareHelper.a
    public void a(String str, boolean z) {
        AppMethodBeat.i(22513, false);
        this.i.d = str;
        f();
        AppMethodBeat.o(22513);
    }

    /* access modifiers changed from: protected */
    public void a(boolean z) {
        AppMethodBeat.i(22514, false);
        runOnUiThread(new AnonymousClass5(this, z));
        AppMethodBeat.o(22514);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.share.WBShareActivity$5  reason: invalid class name */
    public class AnonymousClass5 implements Runnable {
        final /* synthetic */ boolean a;
        final /* synthetic */ WBShareActivity b;

        AnonymousClass5(WBShareActivity wBShareActivity, boolean z) {
            JniLib.cV(this, wBShareActivity, Boolean.valueOf(z), Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_READ_CALENDAR));
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(22474, false);
            WBShareActivity.a(this.b, this.a);
            if (!this.b.isFinishing() && !this.b.a.isShowing()) {
                this.b.a.show();
            }
            AppMethodBeat.o(22474);
        }
    }

    public void a() {
        AppMethodBeat.i(22515, false);
        a(true);
        AppMethodBeat.o(22515);
    }

    public void b() {
        AppMethodBeat.i(22517, false);
        runOnUiThread(new AnonymousClass6(this));
        AppMethodBeat.o(22517);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.share.WBShareActivity$6  reason: invalid class name */
    public class AnonymousClass6 implements Runnable {
        final /* synthetic */ WBShareActivity a;

        AnonymousClass6(WBShareActivity wBShareActivity) {
            JniLib.cV(this, wBShareActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_PERMISSION_DENIED_READ_CALENDAR));
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(22481, false);
            WBShareActivity.a(this.a, true);
            if (this.a.a.isShowing()) {
                this.a.a.dismiss();
            }
            AppMethodBeat.o(22481);
        }
    }

    private void b(boolean z) {
        AppMethodBeat.i(22518, false);
        runOnUiThread(new AnonymousClass7(this, z));
        AppMethodBeat.o(22518);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.share.WBShareActivity$7  reason: invalid class name */
    public class AnonymousClass7 implements Runnable {
        final /* synthetic */ boolean a;
        final /* synthetic */ WBShareActivity b;

        /* renamed from: cn.missfresh.module.base.support.share.WBShareActivity$7$1  reason: invalid class name */
        class AnonymousClass1 implements DialogInterface.OnKeyListener {
            final /* synthetic */ AnonymousClass7 a;

            AnonymousClass1(AnonymousClass7 r5) {
                JniLib.cV(this, r5, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_PERMISSION_REVOKE_READ_CALENDAR));
            }

            @Override // android.content.DialogInterface.OnKeyListener
            public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
                return true;
            }
        }

        AnonymousClass7(WBShareActivity wBShareActivity, boolean z) {
            JniLib.cV(this, wBShareActivity, Boolean.valueOf(z), Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_PERMISSION_REQUEST_WRITE_CALENDAR));
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(22486, false);
            if (this.b.a == null) {
                WBShareActivity wBShareActivity = this.b;
                wBShareActivity.a = new ProgressDialog(wBShareActivity);
                this.b.a.setCanceledOnTouchOutside(this.a);
                this.b.a.setOnKeyListener(new AnonymousClass1(this));
                this.b.a.setMessage("\u8bf7\u7a0d\u5019...");
                this.b.a.setCancelable(this.a);
                this.b.a.dismiss();
            }
            AppMethodBeat.o(22486);
        }
    }

    class a implements c {
        final /* synthetic */ WBShareActivity a;
        private b b;

        a(WBShareActivity wBShareActivity) {
            JniLib.cV(this, wBShareActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_PERMISSION_GRANT_WRITE_CALENDAR));
        }

        @Override // com.sina.weibo.sdk.auth.c
        public void a(Bundle bundle) {
            AppMethodBeat.i(22490, false);
            cn.missfresh.ui.a.a.a("\u6388\u6743\u5b8c\u6210,\u8bf7\u7a0d\u540e");
            this.b = b.a(bundle);
            this.a.c = true;
            if (this.b.a()) {
                a.a(this.a.getApplicationContext(), this.b);
                WBShareActivity.a(this.a);
            } else {
                String string = bundle.getString(Constants.KEY_HTTP_CODE, "");
                String str = this.a.d;
                cn.missfresh.utils.a.d.d(str, "mAccessToken.isSessionValid() = false , code = " + string);
                cn.missfresh.ui.a.a.a("\u6388\u6743\u8fc7\u7a0b\u9047\u5230\u5f02\u5e38");
                this.a.finish();
            }
            AppMethodBeat.o(22490);
        }

        @Override // com.sina.weibo.sdk.auth.c
        public void a(WeiboException weiboException) {
            AppMethodBeat.i(22492, false);
            cn.missfresh.ui.a.a.a("\u6388\u6743\u8fc7\u7a0b\u9047\u5230\u5f02\u5e38");
            this.a.finish();
            AppMethodBeat.o(22492);
        }

        @Override // com.sina.weibo.sdk.auth.c
        public void a() {
            AppMethodBeat.i(22494, false);
            cn.missfresh.ui.a.a.a("\u6388\u6743\u53d6\u6d88");
            this.a.finish();
            AppMethodBeat.o(22494);
        }
    }
}
