package cn.missfresh.module.base.support.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatDialog;
import cn.missfresh.lib.image.c;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.PosterInfo;
import cn.missfresh.module.base.bean.ShareExtraDescBean;
import cn.missfresh.module.base.bean.ShareInfo;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.network.d;
import cn.missfresh.module.base.support.share.WeiboShareHelper;
import cn.missfresh.module.base.support.share.b;
import cn.missfresh.module.base.support.view.InviteNewPosterLayout;
import cn.missfresh.module.base.support.view.ShareExtraDescView;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.module.base.utils.r;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.b;
import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import io.reactivex.c.g;

public class ShareDialog extends AppCompatDialog implements View.OnClickListener {
    private Bitmap A;
    private ProgressDialog B;
    private ShareInfo C;
    private String D;
    private int a;
    private String b;
    private String c;
    private String d;
    private String e;
    private String f;
    private String g;
    private String h;
    private boolean i;
    private View j;
    private TextView k;
    private TextView l;
    private InviteNewPosterLayout m;
    private ImageView n;
    private float o;
    private a p;
    private int q;
    private boolean r;
    private boolean s;
    private boolean t;
    private boolean u;
    private int v;
    private int w;
    private int x;
    private int y;
    private ShareExtraDescBean z;

    public interface a {
        void onCancelClick();

        void onShareClick(int i);
    }

    static /* synthetic */ void a(int i, WXMediaMessage wXMediaMessage, IWXAPI iwxapi) {
        AppMethodBeat.i(21761, false);
        b(i, wXMediaMessage, iwxapi);
        AppMethodBeat.o(21761);
    }

    public ShareDialog(Context context) {
        this(context, true);
    }

    public ShareDialog(Context context, boolean z) {
        super(context, R.style.style_dialog_transparent_bg);
        this.i = false;
        this.o = -1.0f;
        this.q = 0;
        this.r = true;
        this.s = false;
        this.t = true;
        this.u = true;
        this.v = -1;
        this.w = -1;
        this.x = -1;
        this.y = -1;
        this.i = z;
    }

    public void a(int i, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.a = i;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = str6;
        this.h = str7;
    }

    public void a(PosterInfo posterInfo) {
        AppMethodBeat.i(21727, false);
        if (posterInfo == null) {
            AppMethodBeat.o(21727);
            return;
        }
        findViewById(R.id.ll_bottom_close).setVisibility(8);
        findViewById(R.id.ll_share_content_root).setOnClickListener(this);
        e();
        if (this.m == null) {
            this.m = new InviteNewPosterLayout(getContext());
        }
        this.m.setData(posterInfo);
        this.m.getBitmap().b(io.reactivex.f.a.b()).a(io.reactivex.a.b.a.a()).a(new AnonymousClass1(), new AnonymousClass2());
        AppMethodBeat.o(21727);
    }

    /* renamed from: cn.missfresh.module.base.support.dialog.ShareDialog$1  reason: invalid class name */
    class AnonymousClass1 implements g<Bitmap> {
        AnonymousClass1() {
        }

        @Override // io.reactivex.c.g
        public /* synthetic */ void accept(Object obj) throws Exception {
            AppMethodBeat.i(21674, false);
            a((Bitmap) obj);
            AppMethodBeat.o(21674);
        }

        public void a(Bitmap bitmap) throws Exception {
            AppMethodBeat.i(21673, false);
            ShareDialog.this.A = bitmap;
            if (ShareDialog.this.A != null) {
                ShareDialog.this.n.setVisibility(0);
                ShareDialog.this.n.setImageBitmap(bitmap);
            } else {
                ShareDialog.this.A = null;
                cn.missfresh.ui.a.a.a(ShareDialog.this.getContext().getString(R.string.invite_fail));
                ShareDialog.this.dismiss();
            }
            ShareDialog.this.b();
            AppMethodBeat.o(21673);
        }
    }

    /* renamed from: cn.missfresh.module.base.support.dialog.ShareDialog$2  reason: invalid class name */
    class AnonymousClass2 implements g<Throwable> {
        AnonymousClass2() {
        }

        @Override // io.reactivex.c.g
        public /* synthetic */ void accept(Object obj) throws Exception {
            AppMethodBeat.i(21682, false);
            a((Throwable) obj);
            AppMethodBeat.o(21682);
        }

        public void a(Throwable th) throws Exception {
            AppMethodBeat.i(21680, false);
            cn.missfresh.ui.a.a.a(ShareDialog.this.getContext().getString(R.string.invite_fail));
            ShareDialog.this.dismiss();
            ShareDialog.this.b();
            AppMethodBeat.o(21680);
        }
    }

    /* access modifiers changed from: protected */
    @Override // androidx.appcompat.app.AppCompatDialog, android.app.Dialog
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(21728, false);
        super.onCreate(bundle);
        setContentView(R.layout.dialog_share);
        c();
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        attributes.gravity = 80;
        float f = this.o;
        if (((double) f) > -0.5d) {
            attributes.dimAmount = f;
        }
        getWindow().setAttributes(attributes);
        getWindow().setWindowAnimations(R.style.dialog_window_anim);
        AppMethodBeat.o(21728);
    }

    private void c() {
        AppMethodBeat.i(21730, false);
        TextView textView = (TextView) findViewById(R.id.btn_share_wechat_session);
        TextView textView2 = (TextView) findViewById(R.id.btn_share_wechat_timeline);
        TextView textView3 = (TextView) findViewById(R.id.btn_share_weibo);
        TextView textView4 = (TextView) findViewById(R.id.btn_share_qq);
        if (this.i) {
            textView2.setOnClickListener(this);
            textView3.setOnClickListener(this);
        } else {
            textView2.setVisibility(8);
            textView3.setVisibility(8);
        }
        textView.setOnClickListener(this);
        textView4.setOnClickListener(this);
        findViewById(R.id.btn_share_cancle).setOnClickListener(this);
        if (-1 != this.v) {
            Drawable drawable = getContext().getResources().getDrawable(this.v);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawables(null, drawable, null, null);
        }
        if (-1 != this.x) {
            Drawable drawable2 = getContext().getResources().getDrawable(this.x);
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
            textView2.setCompoundDrawables(null, drawable2, null, null);
        }
        if (-1 != this.w) {
            Drawable drawable3 = getContext().getResources().getDrawable(this.w);
            drawable3.setBounds(0, 0, drawable3.getMinimumWidth(), drawable3.getMinimumHeight());
            textView4.setCompoundDrawables(null, drawable3, null, null);
        }
        if (-1 != this.y) {
            Drawable drawable4 = getContext().getResources().getDrawable(this.y);
            drawable4.setBounds(0, 0, drawable4.getMinimumWidth(), drawable4.getMinimumHeight());
            textView3.setCompoundDrawables(null, drawable4, null, null);
        }
        if (!this.s) {
            textView4.setVisibility(8);
        }
        if (!this.r) {
            textView3.setVisibility(8);
        }
        if (!this.t) {
            textView.setVisibility(8);
        }
        if (!this.u) {
            textView2.setVisibility(8);
        }
        this.j = findViewById(R.id.ll_share_extra_intro_box);
        this.k = (TextView) findViewById(R.id.tv_to_login_label);
        this.l = (TextView) findViewById(R.id.tv_share_desc);
        this.n = (ImageView) findViewById(R.id.iv_share_poster);
        ShareInfo shareInfo = this.C;
        if (shareInfo != null && !b.a(shareInfo.origin_image_url)) {
            c.a(this.n).a(this.C.origin_image_url).a(R.drawable.ic_default_200).a(this.n);
        }
        if (this.z != null) {
            ShareExtraDescView shareExtraDescView = new ShareExtraDescView(getContext());
            shareExtraDescView.setData(this.z);
            ((LinearLayout) findViewById(R.id.ll_share_content_root)).addView(shareExtraDescView, 0);
        }
        d();
        AppMethodBeat.o(21730);
    }

    private void d() {
        AppMethodBeat.i(21732, false);
        if (b.a(this.D)) {
            findViewById(R.id.layout_lable_share_dialog).setVisibility(8);
        } else {
            findViewById(R.id.layout_lable_share_dialog).setVisibility(0);
            ((TextView) findViewById(R.id.tv_label_share_dialog)).setText(this.D);
        }
        AppMethodBeat.o(21732);
    }

    public void a(boolean z) {
        this.s = z;
    }

    public void b(boolean z) {
        this.t = z;
    }

    public void c(boolean z) {
        this.u = z;
    }

    public void d(boolean z) {
        this.r = z;
    }

    public void a(int i, int i2, int i3, int i4) {
        this.v = i;
        this.x = i2;
        this.w = i3;
        this.y = i4;
    }

    public void a(boolean z, String str, int i, String str2, int i2, View.OnClickListener onClickListener) {
        AppMethodBeat.i(21737, false);
        if (z) {
            this.k.setText(str);
            this.k.getPaint().setFlags(8);
            if (i != 0) {
                this.k.setTextColor(q.a(i));
            }
            r.a(this.l, str2, getContext().getResources().getColor(R.color.pink_48));
            if (i2 != 0) {
                this.l.setTextColor(q.a(i2));
            }
            this.j.setVisibility(0);
            if (!e.o()) {
                if (b.a(str)) {
                    this.k.setVisibility(8);
                } else {
                    this.k.setVisibility(0);
                }
                if (onClickListener != null) {
                    this.k.setOnClickListener(onClickListener);
                }
            } else {
                this.k.setVisibility(8);
            }
            if (b.a(str) && b.a(str2)) {
                this.j.setVisibility(8);
            }
        } else {
            this.j.setVisibility(8);
        }
        this.j.requestLayout();
        AppMethodBeat.o(21737);
    }

    public void a(ShareExtraDescBean shareExtraDescBean) {
        this.z = shareExtraDescBean;
    }

    public void a(int i) {
        this.q = i;
    }

    private void b(int i) {
        AppMethodBeat.i(21739, false);
        a(getContext(), i, this.c, this.d, this.e, this.f, this.g);
        dismiss();
        AppMethodBeat.o(21739);
    }

    public static void a(Context context, String str, String str2, String str3, String str4) {
        AppMethodBeat.i(21742, false);
        a(context, 1, str, str2, str3, str4, "");
        AppMethodBeat.o(21742);
    }

    public static void a(Context context, int i, String str, String str2, String str3, String str4, String str5) {
        AppMethodBeat.i(21745, false);
        Looper myLooper = Looper.myLooper();
        if (myLooper == null) {
            AppMethodBeat.o(21745);
            return;
        }
        Handler handler = new Handler(myLooper);
        IWXAPI createWXAPI = WXAPIFactory.createWXAPI(context, "wx31562d0a467cb40d", true);
        if (!createWXAPI.isWXAppInstalled()) {
            cn.missfresh.ui.a.a.a(context.getString(R.string.wechat_client_inavailable));
            AppMethodBeat.o(21745);
            return;
        }
        WXWebpageObject wXWebpageObject = new WXWebpageObject();
        if (i == 1) {
            wXWebpageObject.webpageUrl = str4;
        } else {
            wXWebpageObject.webpageUrl = str5;
        }
        WXMediaMessage wXMediaMessage = new WXMediaMessage(wXWebpageObject);
        wXMediaMessage.title = str;
        wXMediaMessage.description = str2;
        if (b.a(str3)) {
            wXMediaMessage.setThumbImage(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher));
            handler.post(new AnonymousClass3(i, wXMediaMessage, createWXAPI));
        } else {
            d.a(context, str3, 150, 150, new AnonymousClass4(wXMediaMessage, context, handler, i, createWXAPI));
        }
        AppMethodBeat.o(21745);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.dialog.ShareDialog$3  reason: invalid class name */
    public static class AnonymousClass3 implements Runnable {
        final /* synthetic */ int a;
        final /* synthetic */ WXMediaMessage b;
        final /* synthetic */ IWXAPI c;

        AnonymousClass3(int i, WXMediaMessage wXMediaMessage, IWXAPI iwxapi) {
            this.a = i;
            this.b = wXMediaMessage;
            this.c = iwxapi;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(21688, false);
            ShareDialog.a(this.a, this.b, this.c);
            AppMethodBeat.o(21688);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.dialog.ShareDialog$4  reason: invalid class name */
    public static class AnonymousClass4 implements d.b<Bitmap> {
        final /* synthetic */ WXMediaMessage a;
        final /* synthetic */ Context b;
        final /* synthetic */ Handler c;
        final /* synthetic */ int d;
        final /* synthetic */ IWXAPI e;

        @Override // cn.missfresh.module.base.network.d.b
        public void a(Exception exc, Drawable drawable) {
        }

        AnonymousClass4(WXMediaMessage wXMediaMessage, Context context, Handler handler, int i, IWXAPI iwxapi) {
            this.a = wXMediaMessage;
            this.b = context;
            this.c = handler;
            this.d = i;
            this.e = iwxapi;
        }

        @Override // cn.missfresh.module.base.network.d.b
        public /* synthetic */ void a(Object obj) {
            AppMethodBeat.i(21705, false);
            a((Bitmap) obj);
            AppMethodBeat.o(21705);
        }

        public void a(Bitmap bitmap) {
            AppMethodBeat.i(21701, false);
            if (bitmap != null) {
                this.a.setThumbImage(bitmap);
            } else {
                this.a.setThumbImage(BitmapFactory.decodeResource(this.b.getResources(), R.mipmap.ic_launcher));
            }
            this.c.post(new AnonymousClass1());
            AppMethodBeat.o(21701);
        }

        /* access modifiers changed from: package-private */
        /* renamed from: cn.missfresh.module.base.support.dialog.ShareDialog$4$1  reason: invalid class name */
        public class AnonymousClass1 implements Runnable {
            AnonymousClass1() {
            }

            @Override // java.lang.Runnable
            public void run() {
                AppMethodBeat.i(21693, false);
                ShareDialog.a(AnonymousClass4.this.d, AnonymousClass4.this.a, AnonymousClass4.this.e);
                AppMethodBeat.o(21693);
            }
        }
    }

    private static void b(int i, WXMediaMessage wXMediaMessage, IWXAPI iwxapi) {
        AppMethodBeat.i(21749, false);
        SendMessageToWX.Req req = new SendMessageToWX.Req();
        req.transaction = String.valueOf(System.currentTimeMillis());
        req.message = wXMediaMessage;
        req.scene = i;
        iwxapi.sendReq(req);
        AppMethodBeat.o(21749);
    }

    public void a(a aVar) {
        this.p = aVar;
    }

    public Bitmap a() {
        return this.A;
    }

    private void e() {
        AppMethodBeat.i(21753, false);
        if (this.B == null) {
            this.B = new ProgressDialog(getContext());
            this.B.setCanceledOnTouchOutside(false);
            this.B.setOnKeyListener(new AnonymousClass5());
            this.B.setMessage("");
            this.B.setCancelable(false);
        }
        this.B.show();
        AppMethodBeat.o(21753);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.dialog.ShareDialog$5  reason: invalid class name */
    public class AnonymousClass5 implements DialogInterface.OnKeyListener {
        @Override // android.content.DialogInterface.OnKeyListener
        public boolean onKey(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
            return true;
        }

        AnonymousClass5() {
        }
    }

    public void b() {
        AppMethodBeat.i(21755, false);
        ProgressDialog progressDialog = this.B;
        if (progressDialog != null && progressDialog.isShowing()) {
            this.B.dismiss();
        }
        AppMethodBeat.o(21755);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(21756, false);
        if (view.getId() == R.id.btn_share_wechat_timeline) {
            c(2);
        } else if (view.getId() == R.id.btn_share_wechat_session) {
            c(1);
        } else if (view.getId() == R.id.btn_share_weibo) {
            if (this.q == 0) {
                WeiboShareHelper.a(getContext(), new WeiboShareHelper.b(this.c, this.d, this.e, this.h, null));
                dismiss();
            }
            a aVar = this.p;
            if (aVar != null) {
                aVar.onShareClick(3);
            }
        } else if (view.getId() == R.id.btn_share_qq) {
            if (this.q == 0) {
                cn.missfresh.module.base.support.share.b.a(getContext(), new b.a(this.a, this.c, this.d, this.b, this.e));
                dismiss();
            }
            a aVar2 = this.p;
            if (aVar2 != null) {
                aVar2.onShareClick(4);
            }
        } else if (view.getId() == R.id.btn_share_cancle || view.getId() == R.id.ll_share_content_root) {
            dismiss();
            a aVar3 = this.p;
            if (aVar3 != null) {
                aVar3.onCancelClick();
            }
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(21756);
    }

    private void c(int i) {
        AppMethodBeat.i(21757, false);
        if (this.C != null) {
            cn.missfresh.module.base.manager.r.a(getContext()).a(i == 1 ? 1 : 2, this.C, true);
            a aVar = this.p;
            if (aVar != null) {
                aVar.onShareClick(i);
            }
            AppMethodBeat.o(21757);
            return;
        }
        if (this.q == 0) {
            b(i);
        }
        a aVar2 = this.p;
        if (aVar2 != null) {
            if (this.m == null) {
                aVar2.onShareClick(i);
                b();
            } else if (this.A == null) {
                e();
                this.m.getBitmap().b(io.reactivex.f.a.b()).a(io.reactivex.a.b.a.a()).a(new AnonymousClass6(i), new AnonymousClass7());
            } else {
                aVar2.onShareClick(i);
                b();
            }
        }
        AppMethodBeat.o(21757);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.dialog.ShareDialog$6  reason: invalid class name */
    public class AnonymousClass6 implements g<Bitmap> {
        final /* synthetic */ int a;

        AnonymousClass6(int i) {
            this.a = i;
        }

        @Override // io.reactivex.c.g
        public /* synthetic */ void accept(Object obj) throws Exception {
            AppMethodBeat.i(21714, false);
            a((Bitmap) obj);
            AppMethodBeat.o(21714);
        }

        public void a(Bitmap bitmap) throws Exception {
            AppMethodBeat.i(21711, false);
            ShareDialog.this.A = bitmap;
            if (ShareDialog.this.A != null) {
                ShareDialog.this.n.setVisibility(0);
                ShareDialog.this.n.setImageBitmap(bitmap);
                ShareDialog.this.p.onShareClick(this.a);
            } else {
                ShareDialog.this.A = null;
                cn.missfresh.ui.a.a.a(ShareDialog.this.getContext().getString(R.string.invite_fail));
            }
            ShareDialog.this.b();
            AppMethodBeat.o(21711);
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.dialog.ShareDialog$7  reason: invalid class name */
    public class AnonymousClass7 implements g<Throwable> {
        AnonymousClass7() {
        }

        @Override // io.reactivex.c.g
        public /* synthetic */ void accept(Object obj) throws Exception {
            AppMethodBeat.i(21721, false);
            a((Throwable) obj);
            AppMethodBeat.o(21721);
        }

        public void a(Throwable th) throws Exception {
            AppMethodBeat.i(21719, false);
            cn.missfresh.ui.a.a.a(ShareDialog.this.getContext().getString(R.string.invite_fail));
            ShareDialog.this.b();
            AppMethodBeat.o(21719);
        }
    }

    public void a(ShareInfo shareInfo) {
        AppMethodBeat.i(21758, false);
        this.C = shareInfo;
        b(true);
        c(true);
        a(false);
        d(false);
        setCanceledOnTouchOutside(false);
        AppMethodBeat.o(21758);
    }

    public void a(String str) {
        this.D = str;
    }
}
