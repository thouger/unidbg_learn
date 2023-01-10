package cn.missfresh.module.base.support.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.media.midi.MidiDeviceInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.HomeAssetBean;
import cn.missfresh.module.base.bean.ShareInfo;
import cn.missfresh.module.base.common.providers.IProductDetailService;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.payment.pwd.widget.GridPassWordView;
import cn.missfresh.module.base.support.adapter.HomeDialogAssetAdapter;
import cn.missfresh.module.base.support.dialog.ShareDialog;
import cn.missfresh.module.base.support.modelsupport.event.EventOrderResData;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.module.base.utils.q;
import cn.missfresh.module.base.widget.recycleview.divider.DividerItemDecoration;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import com.umeng.analytics.pro.ai;
import java.text.SimpleDateFormat;
import java.util.Date;

/* compiled from: DialogUtils */
public class e {
    private static String a = "DialogUtils";

    /* compiled from: DialogUtils */
    public interface a {
        void a();

        void a(String str);
    }

    /* compiled from: DialogUtils */
    public interface b {
        void a();
    }

    /* compiled from: DialogUtils */
    public interface c {
        void a();

        void b();

        void c();
    }

    public static c a(Activity activity, a aVar) {
        AppMethodBeat.i(21282, false);
        c cVar = new c(activity);
        GridPassWordView gridPassWordView = null;
        View inflate = LayoutInflater.from(activity).inflate(R.layout.layout_input_mryx_pay_password, (ViewGroup) null);
        if (aVar != null) {
            gridPassWordView = (GridPassWordView) inflate.findViewById(R.id.v_pwd);
            View findViewById = inflate.findViewById(R.id.v_forget_password);
            gridPassWordView.setOnPasswordChangedListener(new AnonymousClass1(aVar));
            findViewById.setOnClickListener(new DialogUtils$2(cVar, aVar));
        }
        cVar.a(inflate, false);
        cVar.a();
        if (gridPassWordView != null) {
            ViewTreeObserver viewTreeObserver = gridPassWordView.getViewTreeObserver();
            viewTreeObserver.addOnGlobalLayoutListener(new AnonymousClass3(gridPassWordView, viewTreeObserver));
        }
        AppMethodBeat.o(21282);
        return cVar;
    }

    /* compiled from: DialogUtils */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.dialog.e$1  reason: invalid class name */
    public static class AnonymousClass1 implements GridPassWordView.a {
        final /* synthetic */ a a;

        @Override // cn.missfresh.module.base.payment.pwd.widget.GridPassWordView.a
        public void a(String str) {
        }

        AnonymousClass1(a aVar) {
            this.a = aVar;
        }

        @Override // cn.missfresh.module.base.payment.pwd.widget.GridPassWordView.a
        public void b(String str) {
            AppMethodBeat.i(21095, false);
            this.a.a(str);
            AppMethodBeat.o(21095);
        }
    }

    /* compiled from: DialogUtils */
    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.dialog.e$3  reason: invalid class name */
    public static class AnonymousClass3 implements ViewTreeObserver.OnGlobalLayoutListener {
        final /* synthetic */ GridPassWordView a;
        final /* synthetic */ ViewTreeObserver b;

        AnonymousClass3(GridPassWordView gridPassWordView, ViewTreeObserver viewTreeObserver) {
            this.a = gridPassWordView;
            this.b = viewTreeObserver;
        }

        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            AppMethodBeat.i(21157, false);
            Object tag = this.a.getTag(R.string.randomKey);
            String str = e.a;
            d.d(str, "onGlobalLayout...obj:" + tag);
            if (tag == null) {
                this.a.setTag(R.string.randomKey, 1);
            } else if (!(tag instanceof Integer) || ((Integer) tag).intValue() != 1) {
                AppMethodBeat.o(21157);
                return;
            } else {
                this.a.setTag(R.string.randomKey, 2);
            }
            this.a.c();
            String str2 = e.a;
            d.d(str2, "onGlobalLayout...observer.isAlive():" + this.b.isAlive());
            if (this.b.isAlive()) {
                if (Build.VERSION.SDK_INT >= 16) {
                    this.b.removeOnGlobalLayoutListener(this);
                } else {
                    this.b.removeGlobalOnLayoutListener(this);
                }
            }
            AppMethodBeat.o(21157);
        }
    }

    public static c a(Activity activity, String str, String str2, ShareInfo shareInfo) {
        AppMethodBeat.i(21287, false);
        d.d(a, "popChargeSucDialog...");
        c cVar = new c(activity);
        View inflate = LayoutInflater.from(activity).inflate(R.layout.layout_pop_charge_suc, (ViewGroup) null);
        View findViewById = inflate.findViewById(R.id.v_share_btn);
        ((TextView) inflate.findViewById(R.id.tv_pop_title)).setText(str);
        ((TextView) inflate.findViewById(R.id.tv_pop_content)).setText(str2);
        cVar.a(inflate);
        findViewById.setOnClickListener(new DialogUtils$7(activity, shareInfo));
        cVar.a();
        AppMethodBeat.o(21287);
        return cVar;
    }

    public static c a(Activity activity, EventOrderResData.VipTipsRes vipTipsRes, View.OnClickListener onClickListener) {
        AppMethodBeat.i(21295, false);
        c cVar = new c(activity);
        View inflate = LayoutInflater.from(activity).inflate(R.layout.layout_pop_vip_notice_welcome, (ViewGroup) null);
        cVar.a(inflate, false);
        ((TextView) inflate.findViewById(R.id.tv_vip_info_1)).setText(vipTipsRes.getContent_up());
        ((TextView) inflate.findViewById(R.id.tv_vip_info_2)).setText(vipTipsRes.getContent_down());
        ((TextView) inflate.findViewById(R.id.tv_notice_content)).setText(vipTipsRes.getFirsttime_content());
        View findViewById = inflate.findViewById(R.id.v_sub_btn);
        if (onClickListener != null) {
            findViewById.setOnClickListener(new DialogUtils$8(onClickListener, cVar));
        }
        cVar.a();
        AppMethodBeat.o(21295);
        return cVar;
    }

    public static void a(Context context, String str) {
        AppMethodBeat.i(21302, false);
        View inflate = LayoutInflater.from(context).inflate(R.layout.layout_tip_dialog, (ViewGroup) null);
        AlertDialog create = new AlertDialog.Builder(context).setView(inflate).create();
        Window window = create.getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(R.color.transparent);
        }
        create.setCanceledOnTouchOutside(false);
        inflate.findViewById(R.id.imgClose).setOnClickListener(new DialogUtils$9(create));
        create.show();
        if (window != null) {
            int b2 = aw.b();
            int a2 = aw.a();
            window.setLayout(a2 - aw.b(80), b2 - aw.b(420));
        }
        ((TextView) inflate.findViewById(R.id.tv_content_msg)).setText(str);
        AppMethodBeat.o(21302);
    }

    public static c a(Activity activity, boolean z, String str, c cVar) {
        AppMethodBeat.i(21304, false);
        c cVar2 = new c(activity);
        View inflate = LayoutInflater.from(activity).inflate(R.layout.layout_upgrade_dialog, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_detail);
        View findViewById = inflate.findViewById(R.id.v_double_btn_box);
        View findViewById2 = inflate.findViewById(R.id.tv_double_btn_cancel);
        View findViewById3 = inflate.findViewById(R.id.tv_double_btn_ok);
        View findViewById4 = inflate.findViewById(R.id.tv_single_btn_ok);
        if (!cn.missfresh.utils.b.a(str)) {
            textView.setText(str);
        }
        if (z) {
            findViewById.setVisibility(8);
            cVar2.a(false);
        } else {
            findViewById4.setVisibility(8);
            cVar2.a(true);
        }
        if (cVar != null) {
            DialogUtils$10 dialogUtils$10 = new DialogUtils$10(z, activity, cVar, cVar2);
            findViewById2.setOnClickListener(dialogUtils$10);
            findViewById3.setOnClickListener(dialogUtils$10);
            findViewById4.setOnClickListener(dialogUtils$10);
        }
        cVar2.d().setCanceledOnTouchOutside(false);
        cVar2.a(inflate, false, true);
        cVar2.a();
        d.d(a, "popUpgradeOpt show....");
        AppMethodBeat.o(21304);
        return cVar2;
    }

    public static void a(Activity activity, String str, String str2, String str3, View.OnClickListener onClickListener, String str4, View.OnClickListener onClickListener2, String str5, View.OnClickListener onClickListener3, boolean z, boolean z2) {
        AppMethodBeat.i(21309, false);
        c cVar = new c(activity);
        View inflate = LayoutInflater.from(activity).inflate(R.layout.layout_upgrade_dialog, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_title);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tv_detail);
        View findViewById = inflate.findViewById(R.id.v_double_btn_box);
        TextView textView3 = (TextView) inflate.findViewById(R.id.tv_double_btn_cancel);
        TextView textView4 = (TextView) inflate.findViewById(R.id.tv_double_btn_ok);
        TextView textView5 = (TextView) inflate.findViewById(R.id.tv_single_btn_ok);
        if (z) {
            inflate.findViewById(R.id.iv_bell).setVisibility(8);
            ViewGroup.LayoutParams layoutParams = textView.getLayoutParams();
            if (layoutParams != null && (layoutParams instanceof LinearLayout.LayoutParams)) {
                ((LinearLayout.LayoutParams) layoutParams).setMargins(0, aw.b(35), 0, 0);
            }
        }
        if (!cn.missfresh.utils.b.a(str)) {
            textView.setText(str);
        } else {
            textView.setVisibility(8);
        }
        if (!cn.missfresh.utils.b.a(str2)) {
            textView2.setText(str2);
        } else {
            textView2.setVisibility(8);
        }
        if (cn.missfresh.utils.b.a(str3) && cn.missfresh.utils.b.a(str4)) {
            findViewById.setVisibility(8);
        }
        if (!cn.missfresh.utils.b.a(str3)) {
            textView3.setText(str3);
            textView3.setOnClickListener(new DialogUtils$11(cVar, onClickListener));
        } else {
            textView3.setVisibility(8);
        }
        if (!cn.missfresh.utils.b.a(str4)) {
            textView4.setText(str4);
            textView4.setOnClickListener(new DialogUtils$12(cVar, onClickListener2));
        } else {
            textView4.setVisibility(8);
        }
        if (!cn.missfresh.utils.b.a(str5)) {
            textView5.setText(str5);
            textView5.setOnClickListener(new DialogUtils$13(cVar, onClickListener3));
        } else {
            textView5.setVisibility(8);
        }
        cVar.a(z2);
        cVar.a(inflate, false, true);
        cVar.a();
        AppMethodBeat.o(21309);
    }

    public static void a(Activity activity, String str, String str2, String str3, View.OnClickListener onClickListener, String str4, View.OnClickListener onClickListener2, String str5, View.OnClickListener onClickListener3) {
        AppMethodBeat.i(21312, false);
        c cVar = new c(activity);
        View inflate = LayoutInflater.from(activity).inflate(R.layout.layout_contact_customerservice_dialog, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_service_title);
        TextView textView2 = (TextView) inflate.findViewById(R.id.tv_service_detail);
        View findViewById = inflate.findViewById(R.id.v_service_double_btn_box);
        TextView textView3 = (TextView) inflate.findViewById(R.id.tv_service_double_btn_cancel);
        TextView textView4 = (TextView) inflate.findViewById(R.id.tv_service_double_btn_ok);
        TextView textView5 = (TextView) inflate.findViewById(R.id.tv_service_single_btn_ok);
        if (!cn.missfresh.utils.b.a(str)) {
            textView.setText(str);
        } else {
            textView.setVisibility(8);
        }
        if (!cn.missfresh.utils.b.a(str2)) {
            textView2.setText(str2);
        } else {
            textView2.setVisibility(8);
        }
        if (cn.missfresh.utils.b.a(str3) && cn.missfresh.utils.b.a(str4)) {
            findViewById.setVisibility(8);
        }
        if (!cn.missfresh.utils.b.a(str3)) {
            textView3.setText(str3);
            textView3.setOnClickListener(new DialogUtils$14(cVar, onClickListener));
        } else {
            textView3.setVisibility(8);
        }
        if (!cn.missfresh.utils.b.a(str4)) {
            textView4.setText(str4);
            textView4.setOnClickListener(new DialogUtils$15(cVar, onClickListener2));
        } else {
            textView4.setVisibility(8);
        }
        if (!cn.missfresh.utils.b.a(str5)) {
            textView5.setText(str5);
            textView5.setOnClickListener(new DialogUtils$16(cVar, onClickListener3));
        } else {
            textView5.setVisibility(8);
        }
        cVar.a(inflate, false, true);
        cVar.a();
        AppMethodBeat.o(21312);
    }

    public static Dialog a(Activity activity, String str, String str2, String str3, String str4, View.OnClickListener onClickListener) {
        AppMethodBeat.i(21316, false);
        d dVar = new d(activity, str, str2, str3, str4, onClickListener);
        dVar.show();
        AppMethodBeat.o(21316);
        return dVar;
    }

    public static Dialog a(Activity activity, ShareDialog.a aVar, int i) {
        AppMethodBeat.i(21318, false);
        Dialog dialog = new Dialog(activity, R.style.my_dialog);
        dialog.setContentView(R.layout.layout_float_share_dialog);
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        attributes.gravity = 80;
        attributes.height = -2;
        attributes.width = -1;
        attributes.dimAmount = 0.0f;
        if (aVar != null) {
            dialog.findViewById(R.id.v_share_wechat).setOnClickListener(new DialogUtils$20(aVar));
            dialog.findViewById(R.id.v_share_wechat_moments).setOnClickListener(new DialogUtils$21(aVar));
            dialog.findViewById(R.id.v_share_sina).setOnClickListener(new DialogUtils$22(aVar));
            dialog.findViewById(R.id.v_share_qq).setOnClickListener(new DialogUtils$23(aVar));
        }
        if (q.a(activity)) {
            dialog.show();
        }
        new Handler(Looper.getMainLooper()).postDelayed(new AnonymousClass2(activity, dialog), (long) (i * 1000));
        AppMethodBeat.o(21318);
        return dialog;
    }

    /* compiled from: DialogUtils */
    /* renamed from: cn.missfresh.module.base.support.dialog.e$2  reason: invalid class name */
    static class AnonymousClass2 implements Runnable {
        final /* synthetic */ Activity a;
        final /* synthetic */ Dialog b;

        AnonymousClass2(Activity activity, Dialog dialog) {
            this.a = activity;
            this.b = dialog;
        }

        @Override // java.lang.Runnable
        public void run() {
            AppMethodBeat.i(21133, false);
            if (q.a(this.a)) {
                this.b.dismiss();
            }
            AppMethodBeat.o(21133);
        }
    }

    public static c a(Activity activity) {
        AppMethodBeat.i(21321, false);
        c a2 = a(activity, "");
        AppMethodBeat.o(21321);
        return a2;
    }

    public static c a(Activity activity, String str) {
        AppMethodBeat.i(21322, false);
        c cVar = new c(activity);
        View inflate = LayoutInflater.from(activity).inflate(R.layout.layout_open_quick_loading, (ViewGroup) null);
        TextView textView = (TextView) inflate.findViewById(R.id.tv_loading_text);
        if (textView != null && !cn.missfresh.utils.b.a(str)) {
            textView.setText(str);
        }
        inflate.setLayoutParams(new ViewGroup.LayoutParams(aw.b(200), aw.b(140)));
        cVar.a(inflate, false, true);
        cVar.a(false);
        cVar.d().getWindow().getAttributes().dimAmount = 0.0f;
        AppMethodBeat.o(21322);
        return cVar;
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0080  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00a3  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00b2  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00d4  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00ed  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0106  */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x0116  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(android.app.Activity r17, java.lang.String r18, java.lang.String r19, java.lang.String r20, android.view.View.OnClickListener r21, java.lang.String r22, android.view.View.OnClickListener r23, java.lang.String r24, android.view.View.OnClickListener r25, boolean r26, int r27, int r28, int r29, int r30, int r31, int r32) {
        /*
        // Method dump skipped, instructions count: 297
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.missfresh.module.base.support.dialog.e.a(android.app.Activity, java.lang.String, java.lang.String, java.lang.String, android.view.View$OnClickListener, java.lang.String, android.view.View$OnClickListener, java.lang.String, android.view.View$OnClickListener, boolean, int, int, int, int, int, int):void");
    }

    public static void a(Context context, String str, String str2, String str3, String str4) {
        AppMethodBeat.i(21328, false);
        int color = context.getResources().getColor(R.color.vip_color);
        int color2 = context.getResources().getColor(R.color.white);
        a((Activity) context, "\u4f1a\u5458\u5546\u54c1", str, str3, new DialogUtils$32(), str2, new DialogUtils$33(context), "", null, true, color, context.getResources().getColor(R.color.black_474346), color2, color2, color, 0);
        AppMethodBeat.o(21328);
    }

    public static void a(Activity activity, HomeAssetBean homeAssetBean, int i, cn.missfresh.module.base.common.interfaces.b bVar) {
        boolean z;
        AppMethodBeat.i(21330, false);
        if (homeAssetBean != null && !cn.missfresh.utils.b.a(homeAssetBean.coupon_list)) {
            Dialog dialog = new Dialog(activity, R.style.my_dialog);
            View inflate = LayoutInflater.from(activity).inflate(R.layout.dialog_home_assets, (ViewGroup) null);
            dialog.setContentView(inflate);
            dialog.setOnDismissListener(new AnonymousClass4(bVar));
            inflate.setOnClickListener(new DialogUtils$35(dialog, homeAssetBean, activity));
            ImageView imageView = (ImageView) inflate.findViewById(R.id.iv_home_assets_close);
            ImageView imageView2 = (ImageView) inflate.findViewById(R.id.iv_home_assets_bg);
            ImageView imageView3 = (ImageView) inflate.findViewById(R.id.iv_home_assets_top_shadow);
            ImageView imageView4 = (ImageView) inflate.findViewById(R.id.iv_home_assets_bottom_shadow);
            cn.missfresh.module.base.network.d.a(activity, homeAssetBean.bg_img, -1, R.drawable.ico_home_assets_bg, -1, -1, imageView2);
            RecyclerView recyclerView = (RecyclerView) inflate.findViewById(R.id.lv_home_assets);
            recyclerView.addItemDecoration(new DividerItemDecoration(activity.getResources().getDrawable(R.drawable.item_home_asset_divider)));
            recyclerView.setLayoutManager(new LinearLayoutManager(activity, 1, false));
            if (homeAssetBean.coupon_list.size() > 2) {
                if (!cn.missfresh.utils.b.a(homeAssetBean.bg_up_img)) {
                    z = true;
                    cn.missfresh.module.base.network.d.a(activity, homeAssetBean.bg_up_img, -1, -1, -1, -1, imageView3);
                } else {
                    z = true;
                }
                if (!cn.missfresh.utils.b.a(homeAssetBean.bg_down_img)) {
                    cn.missfresh.module.base.network.d.a(activity, homeAssetBean.bg_down_img, -1, -1, -1, -1, imageView4);
                }
                recyclerView.addOnScrollListener(new AnonymousClass5(imageView3, recyclerView, imageView4));
            } else {
                z = true;
            }
            HomeDialogAssetAdapter homeDialogAssetAdapter = new HomeDialogAssetAdapter(activity, homeAssetBean.coupon_list);
            recyclerView.setAdapter(homeDialogAssetAdapter);
            homeDialogAssetAdapter.a(new AnonymousClass6(activity, dialog));
            imageView.setOnClickListener(new DialogUtils$38(dialog));
            dialog.setCancelable(z);
            dialog.show();
            cn.missfresh.module.base.manager.e.x(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        }
        AppMethodBeat.o(21330);
    }

    /* compiled from: DialogUtils */
    /* renamed from: cn.missfresh.module.base.support.dialog.e$4  reason: invalid class name */
    static class AnonymousClass4 implements DialogInterface.OnDismissListener {
        final /* synthetic */ cn.missfresh.module.base.common.interfaces.b a;

        AnonymousClass4(cn.missfresh.module.base.common.interfaces.b bVar) {
            this.a = bVar;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            AppMethodBeat.i(21180, false);
            this.a.a();
            AppMethodBeat.o(21180);
        }
    }

    /* compiled from: DialogUtils */
    /* renamed from: cn.missfresh.module.base.support.dialog.e$5  reason: invalid class name */
    static class AnonymousClass5 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ImageView a;
        final /* synthetic */ RecyclerView b;
        final /* synthetic */ ImageView c;

        AnonymousClass5(ImageView imageView, RecyclerView recyclerView, ImageView imageView2) {
            this.a = imageView;
            this.b = recyclerView;
            this.c = imageView2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView recyclerView, int i, int i2) {
            AppMethodBeat.i(21193, false);
            super.onScrolled(recyclerView, i, i2);
            if (i2 > 0) {
                if (this.a.getVisibility() != 0) {
                    this.a.setVisibility(0);
                }
                if (!this.b.canScrollVertically(1) && this.c.getVisibility() == 0) {
                    this.c.setVisibility(8);
                }
            } else {
                if (this.c.getVisibility() != 0) {
                    this.c.setVisibility(0);
                }
                if (!this.b.canScrollVertically(-1) && this.a.getVisibility() == 0) {
                    this.a.setVisibility(8);
                }
            }
            AppMethodBeat.o(21193);
        }
    }

    /* compiled from: DialogUtils */
    /* renamed from: cn.missfresh.module.base.support.dialog.e$6  reason: invalid class name */
    static class AnonymousClass6 implements HomeDialogAssetAdapter.a {
        final /* synthetic */ Activity a;
        final /* synthetic */ Dialog b;

        AnonymousClass6(Activity activity, Dialog dialog) {
            this.a = activity;
            this.b = dialog;
        }

        @Override // cn.missfresh.module.base.support.adapter.HomeDialogAssetAdapter.a
        public void a(HomeAssetBean.CouponList couponList) {
            AppMethodBeat.i(21198, false);
            if (MidiDeviceInfo.PROPERTY_PRODUCT.equals(couponList.c_type)) {
                ((IProductDetailService) com.alibaba.android.arouter.b.a.a().a("/product/product_detail_service").navigation()).routerProductDetail(couponList.productSku, "", "", 0, "");
            } else if (couponList.packetType == 1) {
                cn.missfresh.module.base.h5.a.a.a(this.a, "", couponList.linkUrl);
            }
            StatisticsManager.c("click_use", ai.e, "couponNoticePop");
            this.b.dismiss();
            AppMethodBeat.o(21198);
        }
    }

    public static void a(Activity activity, String str, String str2, b bVar) {
        AppMethodBeat.i(21334, false);
        c cVar = new c(activity);
        View inflate = LayoutInflater.from(activity).inflate(R.layout.layout_confirm_dialog, (ViewGroup) null);
        inflate.findViewById(R.id.tv_single_btn_ok).setOnClickListener(new DialogUtils$39(bVar, cVar));
        ((TextView) inflate.findViewById(R.id.tv_title)).setText(str2);
        ((TextView) inflate.findViewById(R.id.tv_detail)).setText(str);
        cVar.a(inflate, false, false);
        cVar.a();
        AppMethodBeat.o(21334);
    }

    public static void a(Activity activity, View.OnClickListener onClickListener) {
        AppMethodBeat.i(21345, false);
        c cVar = new c(activity);
        View inflate = LayoutInflater.from(activity).inflate(R.layout.layout_checksign_dialog, (ViewGroup) null);
        ((TextView) inflate.findViewById(R.id.tv_single_btn_ok)).setOnClickListener(new DialogUtils$49(cVar, onClickListener));
        cVar.a(false);
        cVar.a(inflate, false, true);
        cVar.a();
        AppMethodBeat.o(21345);
    }
}
