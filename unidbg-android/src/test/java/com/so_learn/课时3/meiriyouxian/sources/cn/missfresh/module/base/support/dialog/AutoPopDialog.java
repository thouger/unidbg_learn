package cn.missfresh.module.base.support.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import cn.missfresh.lib.image.c;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.AdvertInfoBean;
import cn.missfresh.module.base.bean.AutoPopPicBean;
import cn.missfresh.module.base.bean.BannerEntity;
import cn.missfresh.module.base.common.interfaces.b;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.helper.j;
import cn.missfresh.module.base.helper.k;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.support.dialog.adapter.AutoPopListAdapter;
import cn.missfresh.module.base.support.divider.SpaceItemDecoration;
import cn.missfresh.module.base.utils.at;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AutoPopDialog implements View.OnClickListener {
    private Context a;
    private b b;
    private AlertDialog c;
    private View d;
    private AdvertInfoBean e;
    private AutoPopPicBean f;
    private int g;

    static /* synthetic */ void b(AutoPopDialog autoPopDialog) {
        AppMethodBeat.i(20720, false);
        autoPopDialog.g();
        AppMethodBeat.o(20720);
    }

    public AutoPopDialog(Context context, b bVar) {
        this.a = context;
        this.b = bVar;
    }

    public void a(boolean z) {
        AppMethodBeat.i(20692, false);
        if (!b()) {
            AppMethodBeat.o(20692);
            return;
        }
        StatisticsManager.c((Context) null, "exposure_pop", this.e.getAdvertInfoParam((String) null));
        this.c = new AlertDialog.Builder(this.a, R.style.my_dialog).setView(this.d).setCancelable(false).create();
        this.c.setOnDismissListener(new AnonymousClass1());
        this.c.show();
        if (!z && 1 != this.e.adver_type) {
            e.x(new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date()));
        }
        AppMethodBeat.o(20692);
    }

    /* renamed from: cn.missfresh.module.base.support.dialog.AutoPopDialog$1  reason: invalid class name */
    class AnonymousClass1 implements DialogInterface.OnDismissListener {
        AnonymousClass1() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            AppMethodBeat.i(20664, false);
            if (AutoPopDialog.this.b != null) {
                AutoPopDialog.this.b.a();
            }
            AppMethodBeat.o(20664);
        }
    }

    public void a() {
        AppMethodBeat.i(20696, false);
        AlertDialog alertDialog = this.c;
        if (alertDialog != null && alertDialog.isShowing()) {
            this.c.dismiss();
        }
        AppMethodBeat.o(20696);
    }

    public boolean b() {
        AppMethodBeat.i(20697, false);
        AutoPopPicBean autoPopPicBean = this.f;
        if (autoPopPicBean == null || this.d == null) {
            AppMethodBeat.o(20697);
            return false;
        } else if (autoPopPicBean.getTemplateType() == 2 && cn.missfresh.utils.b.a(this.f.getVoucherDesc())) {
            AppMethodBeat.o(20697);
            return false;
        } else if (this.f.getTemplateType() != 3 || !cn.missfresh.utils.b.a(this.f.getVoucherInfo())) {
            AppMethodBeat.o(20697);
            return true;
        } else {
            AppMethodBeat.o(20697);
            return false;
        }
    }

    public void a(AdvertInfoBean advertInfoBean, int i, int i2) {
        AppMethodBeat.i(20701, false);
        this.e = advertInfoBean;
        AdvertInfoBean advertInfoBean2 = this.e;
        if (advertInfoBean2 == null) {
            AppMethodBeat.o(20701);
            return;
        }
        this.f = advertInfoBean2.autoPopPic;
        this.g = advertInfoBean.businessType;
        AutoPopPicBean autoPopPicBean = this.f;
        if (autoPopPicBean == null) {
            AppMethodBeat.o(20701);
            return;
        }
        if (autoPopPicBean.getTemplateType() == 1) {
            f();
        } else if (this.f.getTemplateType() == 2) {
            d();
        } else if (this.f.getTemplateType() == 3) {
            c();
        }
        AppMethodBeat.o(20701);
    }

    private void c() {
        AppMethodBeat.i(20703, false);
        this.d = LayoutInflater.from(this.a).inflate(R.layout.dialog_list_auto_pop, (ViewGroup) null, false);
        ImageView imageView = (ImageView) this.d.findViewById(R.id.iv_content_list_autoPop);
        RecyclerView recyclerView = (RecyclerView) this.d.findViewById(R.id.rv_list_autoPop);
        recyclerView.setAdapter(new AutoPopListAdapter(this.f.getVoucherInfo()));
        recyclerView.addItemDecoration(new SpaceItemDecoration(1, 8.0f));
        j.a(recyclerView, new AnonymousClass2());
        c.a(imageView).a(this.f.getBgPicUrl()).a(imageView);
        this.d.setOnClickListener(this);
        this.d.findViewById(R.id.iv_close_list_autoPop).setOnClickListener(this);
        AppMethodBeat.o(20703);
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.support.dialog.AutoPopDialog$2  reason: invalid class name */
    public class AnonymousClass2 extends k {
        AnonymousClass2() {
        }

        @Override // cn.missfresh.module.base.helper.g
        public void a(View view, int i) {
            AppMethodBeat.i(20674, false);
            AutoPopDialog.b(AutoPopDialog.this);
            AppMethodBeat.o(20674);
        }
    }

    private void d() {
        int i;
        int i2 = 0;
        AppMethodBeat.i(20704, false);
        this.d = LayoutInflater.from(this.a).inflate(R.layout.dialog_detail_auto_pop, (ViewGroup) null, false);
        ImageView imageView = (ImageView) this.d.findViewById(R.id.iv_content_detail_autoPop);
        LinearLayout linearLayout = (LinearLayout) this.d.findViewById(R.id.layout_voucher_desc_detail_auto_pop);
        this.d.setOnClickListener(this);
        this.d.findViewById(R.id.iv_close_detail_autoPop).setOnClickListener(this);
        c.a(imageView).a(this.f.getBgPicUrl()).a(imageView);
        ((TextView) this.d.findViewById(R.id.tv_cash_detail_autoPop)).setText(at.b(this.f.getTotalPrice()));
        ((TextView) this.d.findViewById(R.id.tv_timeLimit_detail_autoPop)).setText(this.f.getTimeLimit());
        ((TextView) this.d.findViewById(R.id.tv_baseDesc_detail_autoPop)).setText(this.f.getBaseDesc());
        if (cn.missfresh.utils.b.a(this.f.getVoucherDesc())) {
            linearLayout.setVisibility(8);
        } else {
            linearLayout.setVisibility(0);
            int size = this.f.getVoucherDesc().size();
            LinearLayout e = e();
            while (true) {
                if (i2 >= size || i2 >= 3) {
                    break;
                }
                e.addView(a((String) this.f.getVoucherDesc().get(i2)));
                i2++;
            }
            linearLayout.addView(e);
            if (size > 3) {
                LinearLayout e2 = e();
                for (i = 3; i < size; i++) {
                    e2.addView(a((String) this.f.getVoucherDesc().get(i)));
                }
                linearLayout.addView(e2);
            }
        }
        AppMethodBeat.o(20704);
    }

    private LinearLayout e() {
        AppMethodBeat.i(20706, false);
        LinearLayout linearLayout = new LinearLayout(this.a);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -2));
        linearLayout.setOrientation(0);
        linearLayout.setGravity(17);
        linearLayout.setDividerPadding(10);
        linearLayout.setShowDividers(2);
        linearLayout.setDividerDrawable(ContextCompat.getDrawable(this.a, R.drawable.shape_divider_transparent));
        AppMethodBeat.o(20706);
        return linearLayout;
    }

    private View a(String str) {
        AppMethodBeat.i(20709, false);
        TextView textView = new TextView(this.a);
        textView.setTextSize(11.0f);
        textView.setTextColor(this.a.getResources().getColor(R.color.color_FF1741));
        textView.setText(str);
        textView.setBackgroundResource(R.drawable.shape_bg_4_ffe7e0);
        textView.setPadding(aw.b(7), aw.b(2), aw.b(7), aw.b(2));
        AppMethodBeat.o(20709);
        return textView;
    }

    private void f() {
        AppMethodBeat.i(20711, false);
        this.d = LayoutInflater.from(this.a).inflate(R.layout.dialog_sum_auto_pop, (ViewGroup) null, false);
        ImageView imageView = (ImageView) this.d.findViewById(R.id.iv_content_sum_autoPop);
        this.d.setOnClickListener(this);
        this.d.findViewById(R.id.iv_close_sum_autoPop).setOnClickListener(this);
        c.a(imageView).a(this.f.getBgPicUrl()).a(imageView);
        ((TextView) this.d.findViewById(R.id.tv_cash_sum_autoPop)).setText(at.b(this.f.getTotalPrice()));
        ((TextView) this.d.findViewById(R.id.tv_timeLimit_sum_autoPop)).setText(this.f.getTimeLimit());
        ((TextView) this.d.findViewById(R.id.tv_baseDesc_sum_autoPop)).setText(this.f.getBaseDesc());
        AppMethodBeat.o(20711);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(20713, false);
        if (view.getId() == R.id.iv_close_sum_autoPop || view.getId() == R.id.iv_close_detail_autoPop || view.getId() == R.id.iv_close_list_autoPop) {
            StatisticsManager.c((Context) null, "click_close", this.e.getAdvertInfoParam((String) null));
            a();
        } else if (view.getId() == R.id.cl_sum_autoPop || view.getId() == R.id.cl_detail_autoPop || view.getId() == R.id.cl_list_autoPop) {
            g();
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(20713);
    }

    private void g() {
        AppMethodBeat.i(20716, false);
        StatisticsManager.c((Context) null, "click_btn", this.e.getAdvertInfoParam("0"));
        a();
        AdvertInfoBean advertInfoBean = this.e;
        if (advertInfoBean == null || cn.missfresh.utils.b.a(advertInfoBean.getAdvert_list()) || this.e.getAdvert_list().get(0) == null) {
            AppMethodBeat.o(20716);
            return;
        }
        cn.missfresh.module.base.utils.j.a((BannerEntity) this.e.getAdvert_list().get(0));
        AppMethodBeat.o(20716);
    }
}
