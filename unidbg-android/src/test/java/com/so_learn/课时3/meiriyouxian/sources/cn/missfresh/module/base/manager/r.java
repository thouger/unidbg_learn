package cn.missfresh.module.base.manager;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import cn.missfresh.buttomline.logtrace.bean.LogBean;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.module.base.bean.MiniAppShareInfo;
import cn.missfresh.module.base.bean.ShareInfo;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.network.d;
import cn.missfresh.module.base.support.share.MiniRoutineHelper;
import cn.missfresh.module.base.support.share.WeiboShareHelper;
import cn.missfresh.module.base.support.share.b;
import cn.missfresh.module.base.utils.l;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import com.alibaba.fastjson.JSONObject;
import com.android.internal.logging.nano.MetricsProto;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXImageObject;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import io.reactivex.q;
import io.reactivex.s;
import io.reactivex.v;

/* compiled from: ThirdPartShareManager */
public class r {
    private static volatile r e;
    IWXAPI a;
    private Context b;
    private int c = 1146;
    private int d = 190;

    private int a(int i) {
        if (i == 1) {
            return 1;
        }
        return i == 2 ? 5 : 3;
    }

    static /* synthetic */ void a(r rVar) {
        AppMethodBeat.i(14795, false);
        rVar.d();
        AppMethodBeat.o(14795);
    }

    static /* synthetic */ void a(r rVar, a aVar) {
        AppMethodBeat.i(14797, false);
        rVar.b(aVar);
        AppMethodBeat.o(14797);
    }

    static /* synthetic */ void b(r rVar, a aVar) {
        AppMethodBeat.i(14799, false);
        rVar.d(aVar);
        AppMethodBeat.o(14799);
    }

    private r(Context context) {
        AppMethodBeat.i(14743, false);
        if (context != null) {
            this.b = context.getApplicationContext();
            AppMethodBeat.o(14743);
            return;
        }
        NullPointerException nullPointerException = new NullPointerException("context is null");
        AppMethodBeat.o(14743);
        throw nullPointerException;
    }

    public static r a(Context context) {
        AppMethodBeat.i(14745, false);
        if (e == null) {
            synchronized (r.class) {
                try {
                    if (e == null) {
                        e = new r(context);
                    }
                } catch (Throwable th) {
                    AppMethodBeat.o(14745);
                    throw th;
                }
            }
        }
        r rVar = e;
        AppMethodBeat.o(14745);
        return rVar;
    }

    /* compiled from: ThirdPartShareManager */
    public static class a {
        int a;
        int b;
        String c;
        String d;
        String e;
        Bitmap f;
        byte[] g;
        String h;
        Bitmap i;
        byte[] j;
        String k;
        Bitmap l;
        String m;
        String n;
        String o;
        String p;
        String q;
        String r;

        private a() {
        }

        public static a a(boolean z, String str, String str2, String str3, Bitmap bitmap, String str4, Bitmap bitmap2) {
            AppMethodBeat.i(14726, false);
            a aVar = new a();
            aVar.a = z ? 1 : 2;
            aVar.b = 1;
            aVar.e = str3;
            aVar.f = bitmap;
            aVar.h = str4;
            aVar.i = bitmap2;
            AppMethodBeat.o(14726);
            return aVar;
        }

        public static a a(boolean z, ShareInfo shareInfo) {
            AppMethodBeat.i(14728, false);
            a a = a(z ? 1 : 2, 3, null, null, shareInfo);
            AppMethodBeat.o(14728);
            return a;
        }

        public static a a(int i, int i2, Bitmap bitmap, Bitmap bitmap2, ShareInfo shareInfo) {
            AppMethodBeat.i(14730, false);
            a aVar = new a();
            aVar.a = i;
            aVar.b = i2;
            aVar.c = shareInfo.title;
            aVar.d = shareInfo.content;
            MiniAppShareInfo miniAppShareInfo = shareInfo.miniAppShareInfo;
            if (miniAppShareInfo != null) {
                aVar.m = miniAppShareInfo.miniTitle;
                aVar.o = miniAppShareInfo.miniOriginalId;
                aVar.n = miniAppShareInfo.miniDesc;
                aVar.p = miniAppShareInfo.miniImgUrl;
                aVar.r = miniAppShareInfo.miniPath;
            }
            if (i == 1) {
                aVar.k = shareInfo.wx_url;
            } else if (i == 2) {
                aVar.k = shareInfo.friend_url;
            } else if (i == 3) {
                aVar.k = shareInfo.sina_url;
            } else if (i == 4) {
                aVar.k = shareInfo.qqFriendUrl;
            }
            aVar.q = aVar.k;
            if (i2 == 1) {
                aVar.h = shareInfo.origin_image_url;
                aVar.e = shareInfo.origin_image_url;
            } else {
                aVar.h = shareInfo.image_url;
                aVar.e = shareInfo.image_url;
            }
            aVar.f = bitmap;
            aVar.i = bitmap2;
            if (i2 == 5) {
                aVar.f = null;
                aVar.j = null;
            }
            AppMethodBeat.o(14730);
            return aVar;
        }

        public static a a(int i, int i2, Bitmap bitmap, byte[] bArr) {
            AppMethodBeat.i(14733, false);
            a aVar = new a();
            aVar.a = i;
            aVar.b = i2;
            aVar.f = bitmap;
            aVar.j = bArr;
            AppMethodBeat.o(14733);
            return aVar;
        }

        public String toString() {
            AppMethodBeat.i(14735, false);
            String jSONString = JSONObject.toJSONString(this);
            AppMethodBeat.o(14735);
            return jSONString;
        }
    }

    public void a(int i, ShareInfo shareInfo, boolean z) {
        AppMethodBeat.i(14749, false);
        a(i, null, null, shareInfo, z);
        AppMethodBeat.o(14749);
    }

    public void a(int i, Bitmap bitmap, Bitmap bitmap2, ShareInfo shareInfo, boolean z) {
        AppMethodBeat.i(14751, false);
        if (shareInfo == null) {
            AppMethodBeat.o(14751);
            return;
        }
        int b = b(i, shareInfo, z);
        d.d("ThirdPartShareManager", "doShare...shareToType:" + i + ",mediaType:" + b + ",shareInfo:" + shareInfo);
        a a2 = a.a(i, b, bitmap, bitmap2, shareInfo);
        if (b.a(shareInfo.bottom_image_url)) {
            a(a2);
        } else {
            cn.missfresh.module.base.network.d.a(this.b, shareInfo.bottom_image_url, 0, 0, new AnonymousClass1(a2));
        }
        AppMethodBeat.o(14751);
    }

    /* compiled from: ThirdPartShareManager */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.manager.r$1  reason: invalid class name */
    public class AnonymousClass1 implements d.b<Bitmap> {
        final /* synthetic */ a a;

        AnonymousClass1(a aVar) {
            this.a = aVar;
        }

        @Override // cn.missfresh.module.base.network.d.b
        public /* synthetic */ void a(Object obj) {
            AppMethodBeat.i(14649, false);
            a((Bitmap) obj);
            AppMethodBeat.o(14649);
        }

        public void a(Bitmap bitmap) {
            AppMethodBeat.i(14646, false);
            if (bitmap != null) {
                this.a.l = bitmap;
                cn.missfresh.utils.a.d.d("ThirdPartShareManager", "dowload bottomimage success");
            }
            r.this.a(this.a);
            AppMethodBeat.o(14646);
        }

        @Override // cn.missfresh.module.base.network.d.b
        public void a(Exception exc, Drawable drawable) {
            AppMethodBeat.i(14647, false);
            r.this.a(this.a);
            cn.missfresh.utils.a.d.d("ThirdPartShareManager", "dowload bottomimage fail");
            r.a("dowload bottomimage fail");
            AppMethodBeat.o(14647);
        }
    }

    private int b(int i, ShareInfo shareInfo, boolean z) {
        AppMethodBeat.i(14757, false);
        int i2 = 3;
        if (!z) {
            if (i == 1) {
                i2 = a(shareInfo.getWx_share_type());
            } else if (i == 2) {
                i2 = a(shareInfo.getFriend_share_type());
            } else if (i != 3) {
                if (i == 4) {
                    i2 = a(shareInfo.getQqFriendShareType());
                }
            }
            AppMethodBeat.o(14757);
            return i2;
        }
        i2 = 1;
        AppMethodBeat.o(14757);
        return i2;
    }

    public void a(a aVar) {
        AppMethodBeat.i(14758, false);
        cn.missfresh.utils.a.d.d("ThirdPartShareManager", "doShare....shareContent:" + aVar);
        int i = aVar.a;
        if (i == 1 || i == 2) {
            a();
            if (!this.a.isWXAppInstalled()) {
                cn.missfresh.ui.a.a.a(this.b.getResources().getString(R.string.wechat_client_inavailable));
                a("doShare 560 \u5fae\u4fe1\u7248\u672c\u8fc7\u4f4e");
                AppMethodBeat.o(14758);
                return;
            }
            c();
            d(aVar);
        } else if (i == 3) {
            WeiboShareHelper.b bVar = new WeiboShareHelper.b(aVar.c, aVar.d, aVar.h, aVar.k, aVar.i);
            if (aVar.j != null) {
                bVar.f = aVar.j;
            }
            WeiboShareHelper.a(this.b, bVar);
        } else if (i == 4) {
            if (aVar.b != 0) {
                c(aVar);
            } else {
                b(aVar);
            }
        }
        AppMethodBeat.o(14758);
    }

    public void a(int i, ShareInfo shareInfo, Bitmap bitmap, boolean z) {
        AppMethodBeat.i(14759, false);
        a();
        a a2 = a.a(i, b(i, shareInfo, z), null, bitmap, shareInfo);
        d();
        e(a2);
        AppMethodBeat.o(14759);
    }

    private void b(a aVar) {
        AppMethodBeat.i(14760, false);
        cn.missfresh.module.base.support.share.b.a(this.b, new b.a(aVar.b, aVar.c, aVar.d, aVar.k, aVar.h, aVar.i));
        AppMethodBeat.o(14760);
    }

    private void c(a aVar) {
        AppMethodBeat.i(14763, false);
        if (aVar.i != null) {
            b(aVar);
            AppMethodBeat.o(14763);
            return;
        }
        String str = aVar.h;
        if (cn.missfresh.utils.b.a(str)) {
            AppMethodBeat.o(14763);
            return;
        }
        c();
        cn.missfresh.module.base.network.d.a(this.b, str, 0, 0, new AnonymousClass2(aVar));
        AppMethodBeat.o(14763);
    }

    /* compiled from: ThirdPartShareManager */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.manager.r$2  reason: invalid class name */
    public class AnonymousClass2 implements d.b<Bitmap> {
        final /* synthetic */ a a;

        AnonymousClass2(a aVar) {
            this.a = aVar;
        }

        @Override // cn.missfresh.module.base.network.d.b
        public /* synthetic */ void a(Object obj) {
            AppMethodBeat.i(14665, false);
            a((Bitmap) obj);
            AppMethodBeat.o(14665);
        }

        public void a(Bitmap bitmap) {
            AppMethodBeat.i(14661, false);
            if (this.a.i == null) {
                this.a.i = bitmap;
            }
            r.a(r.this);
            r.a(r.this, this.a);
            AppMethodBeat.o(14661);
        }

        @Override // cn.missfresh.module.base.network.d.b
        public void a(Exception exc, Drawable drawable) {
            AppMethodBeat.i(14663, false);
            r.a(r.this);
            cn.missfresh.ui.a.a.b(((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getApplication().getString(R.string.net_work_error));
            r.a("downLoadImageForQQ 630 \u56fe\u7247\u4e0b\u8f7d\u5931\u8d25:");
            AppMethodBeat.o(14663);
        }
    }

    private void a() {
        AppMethodBeat.i(14765, false);
        this.a = WXAPIFactory.createWXAPI(this.b, "wx31562d0a467cb40d", true);
        AppMethodBeat.o(14765);
    }

    private void d(a aVar) {
        AppMethodBeat.i(14768, false);
        if (aVar.f == null) {
            a(aVar, true);
            AppMethodBeat.o(14768);
            return;
        }
        aVar.f = new l().a(aVar.f);
        if (aVar.i == null && aVar.j == null) {
            a(aVar, false);
            AppMethodBeat.o(14768);
            return;
        }
        if (aVar.l != null) {
            cn.missfresh.utils.a.d.d("ThirdPartShareManager", "-------------combanBitmap");
            aVar.i = a(aVar.i, aVar.l, true);
        }
        d();
        cn.missfresh.utils.a.d.d("ThirdPartShareManager", "-----------checkWxImageContent");
        e(aVar);
        AppMethodBeat.o(14768);
    }

    private Bitmap a(Bitmap bitmap, Bitmap bitmap2, boolean z) {
        AppMethodBeat.i(14769, false);
        try {
            Bitmap createScaledBitmap = Bitmap.createScaledBitmap(bitmap, MetricsProto.MetricsEvent.SETTINGS_LANGUAGE_CATEGORY, this.c, false);
            Bitmap createScaledBitmap2 = Bitmap.createScaledBitmap(bitmap2, MetricsProto.MetricsEvent.SETTINGS_LANGUAGE_CATEGORY, this.d, false);
            int i = this.c + this.d;
            Bitmap createBitmap = Bitmap.createBitmap(MetricsProto.MetricsEvent.SETTINGS_LANGUAGE_CATEGORY, i, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(createBitmap);
            Rect rect = new Rect(0, 0, createScaledBitmap.getWidth(), createScaledBitmap.getHeight());
            Rect rect2 = new Rect(0, 0, createScaledBitmap2.getWidth(), createScaledBitmap2.getHeight());
            Rect rect3 = new Rect(0, createScaledBitmap.getHeight(), MetricsProto.MetricsEvent.SETTINGS_LANGUAGE_CATEGORY, i);
            canvas.drawBitmap(createScaledBitmap, rect, rect, (Paint) null);
            canvas.drawBitmap(createScaledBitmap2, rect2, rect3, (Paint) null);
            bitmap.recycle();
            bitmap2.recycle();
            AppMethodBeat.o(14769);
            return createBitmap;
        } catch (Exception unused) {
            AppMethodBeat.o(14769);
            return bitmap;
        }
    }

    private void e(a aVar) {
        WXMediaMessage.IMediaObject iMediaObject;
        AppMethodBeat.i(14771, false);
        if (aVar.b == 5) {
            new MiniRoutineHelper(this.b).a(aVar.q, aVar.o, aVar.r, aVar.m, aVar.n, aVar.i);
            AppMethodBeat.o(14771);
            return;
        }
        int i = aVar.b;
        if (i != 1) {
            if (i != 3) {
                iMediaObject = new WXWebpageObject(aVar.k);
            } else {
                iMediaObject = new WXWebpageObject(aVar.k);
            }
        } else if (aVar.j != null) {
            iMediaObject = new WXImageObject(aVar.j);
        } else {
            iMediaObject = new WXImageObject(aVar.i);
        }
        WXMediaMessage wXMediaMessage = new WXMediaMessage(iMediaObject);
        wXMediaMessage.title = aVar.c;
        wXMediaMessage.description = aVar.d;
        if (aVar.g != null) {
            wXMediaMessage.thumbData = aVar.g;
        } else {
            wXMediaMessage.setThumbImage(aVar.f);
        }
        a(b(aVar.a), wXMediaMessage);
        AppMethodBeat.o(14771);
    }

    public void a(ShareInfo shareInfo, int i) {
        AppMethodBeat.i(14772, false);
        if (shareInfo != null) {
            if (i != 1) {
                if (i != 2) {
                    if (i == 3) {
                        a(shareInfo, shareInfo.poster_img_url, 3);
                    } else if (i == 4) {
                        a(shareInfo, shareInfo.poster_img_url, 4);
                    }
                } else if (shareInfo.friend_share_type == 1) {
                    a(shareInfo, shareInfo.poster_img_url, 2);
                } else {
                    a(2, shareInfo, false);
                }
            } else if (shareInfo.wx_share_type == 1) {
                a(shareInfo, shareInfo.poster_img_url, 1);
            } else {
                a(1, shareInfo, false);
            }
        }
        AppMethodBeat.o(14772);
    }

    private void a(a aVar, boolean z) {
        AppMethodBeat.i(14775, false);
        String str = z ? aVar.e : aVar.h;
        if ((aVar.a == 1 || aVar.a == 2) && aVar.b == 5) {
            str = aVar.p;
        }
        cn.missfresh.module.base.network.d.a(this.b, str, 0, 0, new AnonymousClass3(z, aVar));
        AppMethodBeat.o(14775);
    }

    /* compiled from: ThirdPartShareManager */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.manager.r$3  reason: invalid class name */
    public class AnonymousClass3 implements d.b<Bitmap> {
        final /* synthetic */ boolean a;
        final /* synthetic */ a b;

        AnonymousClass3(boolean z, a aVar) {
            this.a = z;
            this.b = aVar;
        }

        @Override // cn.missfresh.module.base.network.d.b
        public /* synthetic */ void a(Object obj) {
            AppMethodBeat.i(14677, false);
            a((Bitmap) obj);
            AppMethodBeat.o(14677);
        }

        public void a(Bitmap bitmap) {
            AppMethodBeat.i(14670, false);
            if (bitmap == null) {
                bitmap = BitmapFactory.decodeResource(r.this.b.getResources(), R.mipmap.ic_launcher);
            }
            if (this.a) {
                this.b.f = bitmap;
            } else {
                this.b.i = bitmap;
            }
            r.b(r.this, this.b);
            AppMethodBeat.o(14670);
        }

        @Override // cn.missfresh.module.base.network.d.b
        public void a(Exception exc, Drawable drawable) {
            AppMethodBeat.i(14674, false);
            r.a(r.this);
            cn.missfresh.ui.a.a.b(((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getApplication().getString(R.string.net_work_error));
            r.a("downLoadImage 801 \u56fe\u7247\u4e0b\u8f7d\u5931\u8d25");
            AppMethodBeat.o(14674);
        }
    }

    private BaseFragmentActivity b() {
        AppMethodBeat.i(14777, false);
        Activity topActivity = ((IApplicationDelegateService) com.alibaba.android.arouter.b.a.a().a("/common/application_delegate_service").navigation()).getTopActivity();
        if (topActivity == null || topActivity.isFinishing() || !(topActivity instanceof BaseFragmentActivity)) {
            AppMethodBeat.o(14777);
            return null;
        }
        BaseFragmentActivity baseFragmentActivity = (BaseFragmentActivity) topActivity;
        AppMethodBeat.o(14777);
        return baseFragmentActivity;
    }

    private void c() {
        AppMethodBeat.i(14779, false);
        BaseFragmentActivity b = b();
        if (b != null) {
            b.b(true);
        }
        AppMethodBeat.o(14779);
    }

    private void d() {
        AppMethodBeat.i(14781, false);
        BaseFragmentActivity b = b();
        if (b != null) {
            b.c(true);
        }
        AppMethodBeat.o(14781);
    }

    private int b(int i) {
        int i2 = 0;
        AppMethodBeat.i(14787, false);
        if (i != 1 && i == 2) {
            i2 = 1;
        }
        cn.missfresh.utils.a.d.d("ThirdPartShareManager", "shareType:" + i + ", SendMessageToWX.Req.(type):" + i2);
        AppMethodBeat.o(14787);
        return i2;
    }

    private void a(int i, WXMediaMessage wXMediaMessage) {
        AppMethodBeat.i(14789, false);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = wXMediaMessage;
        req.scene = i;
        this.a.sendReq(req);
        AppMethodBeat.o(14789);
    }

    private void a(ShareInfo shareInfo, String str, int i) {
        AppMethodBeat.i(14791, false);
        if (cn.missfresh.utils.b.a(str)) {
            cn.missfresh.ui.a.a.a(this.b.getResources().getString(R.string.net_work_error));
            a("downloadImage 868 \u56fe\u7247poster_img_url\u4e3a\u7a7a");
        } else {
            try {
                cn.missfresh.module.base.network.d.a(this.b, str, -1, -1, new AnonymousClass4(i, shareInfo));
            } catch (Exception unused) {
                cn.missfresh.ui.a.a.a(this.b.getResources().getString(R.string.net_work_error));
                a("downloadImage 956 \u56fe\u7247\u4e0b\u8f7d\u5931\u8d25\uff1a");
            }
        }
        AppMethodBeat.o(14791);
    }

    /* compiled from: ThirdPartShareManager */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.manager.r$4  reason: invalid class name */
    public class AnonymousClass4 implements d.b<Bitmap> {
        final /* synthetic */ int a;
        final /* synthetic */ ShareInfo b;

        AnonymousClass4(int i, ShareInfo shareInfo) {
            this.a = i;
            this.b = shareInfo;
        }

        @Override // cn.missfresh.module.base.network.d.b
        public /* synthetic */ void a(Object obj) {
            AppMethodBeat.i(14706, false);
            a((Bitmap) obj);
            AppMethodBeat.o(14706);
        }

        /* compiled from: ThirdPartShareManager */
        /* access modifiers changed from: package-private */
        /* renamed from: cn.missfresh.module.base.manager.r$4$2  reason: invalid class name */
        public class AnonymousClass2 implements s<Bitmap> {
            final /* synthetic */ Bitmap a;

            AnonymousClass2(Bitmap bitmap) {
                this.a = bitmap;
            }

            @Override // io.reactivex.s
            public void a(io.reactivex.r<Bitmap> rVar) throws Exception {
                AppMethodBeat.i(14693, false);
                byte[] c = new l().c(this.a.copy(Bitmap.Config.ARGB_8888, false), 65436);
                rVar.onNext(BitmapFactory.decodeByteArray(c, 0, c.length));
                rVar.onComplete();
                AppMethodBeat.o(14693);
            }
        }

        public void a(Bitmap bitmap) {
            AppMethodBeat.i(14702, false);
            if (bitmap != null) {
                q.a((s) new AnonymousClass2(bitmap)).a(cn.missfresh.basiclib.net.e.a.a).subscribe(new AnonymousClass1(bitmap));
            } else {
                cn.missfresh.ui.a.a.a(r.this.b.getResources().getString(R.string.net_work_error));
                r.a("downloadImage 944 \u56fe\u7247\u4e3a\u7a7a");
            }
            AppMethodBeat.o(14702);
        }

        /* compiled from: ThirdPartShareManager */
        /* access modifiers changed from: package-private */
        /* renamed from: cn.missfresh.module.base.manager.r$4$1  reason: invalid class name */
        public class AnonymousClass1 implements v<Bitmap> {
            final /* synthetic */ Bitmap a;

            @Override // io.reactivex.v
            public void onComplete() {
            }

            @Override // io.reactivex.v
            public void onSubscribe(io.reactivex.disposables.b bVar) {
            }

            AnonymousClass1(Bitmap bitmap) {
                this.a = bitmap;
            }

            @Override // io.reactivex.v
            public /* synthetic */ void onNext(Object obj) {
                AppMethodBeat.i(14687, false);
                a((Bitmap) obj);
                AppMethodBeat.o(14687);
            }

            public void a(Bitmap bitmap) {
                AppMethodBeat.i(14685, false);
                r.this.a(AnonymousClass4.this.a, bitmap, this.a, AnonymousClass4.this.b, true);
                AppMethodBeat.o(14685);
            }

            @Override // io.reactivex.v
            public void onError(Throwable th) {
                AppMethodBeat.i(14686, false);
                r.a("downloadImage 934 \u56fe\u7247\u538b\u7f29\u5931\u8d25");
                AppMethodBeat.o(14686);
            }
        }

        @Override // cn.missfresh.module.base.network.d.b
        public void a(Exception exc, Drawable drawable) {
            AppMethodBeat.i(14704, false);
            cn.missfresh.ui.a.a.a(r.this.b.getResources().getString(R.string.net_work_error));
            r.a("downloadImage 951 \u56fe\u7247\u4e0b\u8f7d\u5931\u8d25");
            AppMethodBeat.o(14704);
        }
    }

    public static void a(String str) {
        AppMethodBeat.i(14793, false);
        LogBean logBean = new LogBean();
        logBean.setType("share_error_sdk");
        logBean.setStr_value_0(str);
        cn.missfresh.utils.a.d.a(logBean);
        AppMethodBeat.o(14793);
    }
}
