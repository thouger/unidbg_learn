package cn.missfresh.module.base.support.a;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.wifi.WifiEnterpriseConfig;
import cn.missfresh.module.base.bean.ShareInfo;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.manager.g;
import cn.missfresh.module.base.manager.r;
import cn.missfresh.module.base.network.d;
import cn.missfresh.module.base.support.dialog.ShareDialog;
import cn.missfresh.module.base.support.dialog.e;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;

/* compiled from: GlobalOnScreenShotListener */
public class a implements c {
    private final String a = getClass().getSimpleName();

    public a() {
        AppMethodBeat.i(22396, false);
        AppMethodBeat.o(22396);
    }

    static /* synthetic */ void a(a aVar, int i, String str, Bitmap bitmap) {
        AppMethodBeat.i(22402, false);
        aVar.a(i, str, bitmap);
        AppMethodBeat.o(22402);
    }

    @Override // cn.missfresh.module.base.support.a.c
    public void j_(String str) {
        AppMethodBeat.i(22397, false);
        String str2 = this.a;
        d.d(str2, "onShot = " + str);
        Activity topActivity = ((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getTopActivity();
        if (topActivity == null) {
            AppMethodBeat.o(22397);
            return;
        }
        if (topActivity instanceof b) {
            b bVar = (b) topActivity;
            if ("screenShotStrategyDefault".equals(bVar.y())) {
                b(str);
            } else if (!bVar.g(str)) {
                AppMethodBeat.o(22397);
                return;
            } else {
                b(str);
            }
        } else {
            d.c(this.a, "Current Activity is not belong to missfresh app");
        }
        AppMethodBeat.o(22397);
    }

    /* compiled from: GlobalOnScreenShotListener */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.a.a$1  reason: invalid class name */
    public class AnonymousClass1 implements ShareDialog.a {
        final /* synthetic */ String a;

        @Override // cn.missfresh.module.base.support.dialog.ShareDialog.a
        public void onCancelClick() {
        }

        AnonymousClass1(String str) {
            this.a = str;
        }

        @Override // cn.missfresh.module.base.support.dialog.ShareDialog.a
        public void onShareClick(int i) {
            AppMethodBeat.i(22391, false);
            String str = a.this.a;
            d.d(str, "initScreenShot click shareType:" + i);
            if (!g.a().f() || b.a(g.a().e())) {
                a.a(a.this, i, this.a, null);
            } else {
                cn.missfresh.module.base.network.d.a(((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getTopActivity(), g.a().e(), -1, -1, new AnonymousClass1(i));
            }
            AppMethodBeat.o(22391);
        }

        /* compiled from: GlobalOnScreenShotListener */
        /* renamed from: cn.missfresh.module.base.support.a.a$1$1  reason: invalid class name */
        class AnonymousClass1 implements d.b<Bitmap> {
            final /* synthetic */ int a;

            AnonymousClass1(int i) {
                this.a = i;
            }

            @Override // cn.missfresh.module.base.network.d.b
            public /* synthetic */ void a(Object obj) {
                AppMethodBeat.i(22388, false);
                a((Bitmap) obj);
                AppMethodBeat.o(22388);
            }

            public void a(Bitmap bitmap) {
                AppMethodBeat.i(22386, false);
                a.a(a.this, this.a, null, aw.a(((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getTopActivity(), BitmapFactory.decodeFile(AnonymousClass1.this.a), bitmap));
                AppMethodBeat.o(22386);
            }

            @Override // cn.missfresh.module.base.network.d.b
            public void a(Exception exc, Drawable drawable) {
                AppMethodBeat.i(22387, false);
                a.a(a.this, this.a, AnonymousClass1.this.a, null);
                AppMethodBeat.o(22387);
            }
        }
    }

    private void b(String str) {
        AppMethodBeat.i(22398, false);
        e.a(((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getTopActivity(), new AnonymousClass1(str), 5);
        AppMethodBeat.o(22398);
    }

    private void a(int i, String str, Bitmap bitmap) {
        boolean z = false;
        AppMethodBeat.i(22399, false);
        if (i == 1) {
            r.a(a()).a(r.a.a(true, "", "", str, bitmap, str, bitmap));
        } else if (i == 2) {
            r.a(a()).a(r.a.a(false, "", "", str, bitmap, str, bitmap));
        } else if (i == 3) {
            ShareInfo shareInfo = new ShareInfo();
            shareInfo.setImage_url(str);
            shareInfo.setOrigin_image_url(str);
            shareInfo.setTitle(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
            r.a(a()).a(3, bitmap, bitmap, shareInfo, true);
        } else if (i != 4) {
            String str2 = this.a;
            StringBuilder sb = new StringBuilder();
            sb.append("Screen shot : share image failed ! share type: ");
            sb.append(i);
            sb.append(" image path: ");
            sb.append(str);
            sb.append(" bitmap is null ");
            if (bitmap == null) {
                z = true;
            }
            sb.append(z);
            cn.missfresh.utils.a.d.d(str2, sb.toString());
        } else {
            ShareInfo shareInfo2 = new ShareInfo();
            shareInfo2.setImage_url(str);
            shareInfo2.setOrigin_image_url(str);
            shareInfo2.setTitle(WifiEnterpriseConfig.CA_CERT_ALIAS_DELIMITER);
            r.a(a()).a(4, bitmap, bitmap, shareInfo2, true);
        }
        AppMethodBeat.o(22399);
    }

    private Context a() {
        AppMethodBeat.i(22400, false);
        Application application = ((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getApplication();
        AppMethodBeat.o(22400);
        return application;
    }
}
