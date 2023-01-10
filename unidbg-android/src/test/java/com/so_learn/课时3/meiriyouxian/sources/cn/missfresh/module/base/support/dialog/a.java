package cn.missfresh.module.base.support.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.BannerEntity;
import cn.missfresh.module.base.common.interfaces.b;
import cn.missfresh.module.base.datastatistics.StatisticsManager;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.support.adapter.PicAdapter;
import cn.missfresh.module.base.support.superindicatorlibray.CircleIndicator;
import cn.missfresh.module.base.support.superindicatorlibray.LoopViewPager;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* compiled from: AdDialog */
public class a {
    private Activity a;
    private AlertDialog b;
    private LoopViewPager c;
    private PicAdapter d;
    private ImageView e;
    private CircleIndicator f;
    private List<BannerEntity> g;
    private int h = 0;
    private int i;
    private Map<String, Object> j;

    public a(Activity activity, b bVar) {
        AppMethodBeat.i(20566, false);
        this.a = activity;
        View inflate = View.inflate(activity, R.layout.ad_dialog_layout, null);
        this.c = (LoopViewPager) inflate.findViewById(R.id.viewpager);
        this.e = (ImageView) inflate.findViewById(R.id.ad_close);
        this.f = (CircleIndicator) inflate.findViewById(R.id.indicator);
        this.b = new AlertDialog.Builder(activity, R.style.my_dialog).setView(inflate).create();
        this.b.setOnCancelListener(new AnonymousClass1());
        this.b.setOnDismissListener(new AnonymousClass2(bVar));
        this.e.setOnClickListener(new AdDialog$3(this, activity));
        AppMethodBeat.o(20566);
    }

    /* compiled from: AdDialog */
    /* renamed from: cn.missfresh.module.base.support.dialog.a$1  reason: invalid class name */
    class AnonymousClass1 implements DialogInterface.OnCancelListener {
        AnonymousClass1() {
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            AppMethodBeat.i(20537, false);
            a.this.a();
            AppMethodBeat.o(20537);
        }
    }

    /* compiled from: AdDialog */
    /* renamed from: cn.missfresh.module.base.support.dialog.a$2  reason: invalid class name */
    class AnonymousClass2 implements DialogInterface.OnDismissListener {
        final /* synthetic */ b a;

        AnonymousClass2(b bVar) {
            this.a = bVar;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            AppMethodBeat.i(20544, false);
            b bVar = this.a;
            if (bVar != null) {
                bVar.a();
            }
            AppMethodBeat.o(20544);
        }
    }

    public void a(boolean z) {
        Map<String, Object> map;
        AppMethodBeat.i(20569, false);
        if (!c()) {
            AppMethodBeat.o(20569);
            return;
        }
        if (this.i == 1 && (map = this.j) != null) {
            StatisticsManager.c((Context) null, "exposure_pop", map);
        }
        this.b.show();
        if (!z && this.i != 1) {
            e.x(new SimpleDateFormat("yyyyMMdd", Locale.getDefault()).format(new Date()));
        }
        AppMethodBeat.o(20569);
    }

    public void a() {
        AppMethodBeat.i(20571, false);
        this.b.cancel();
        if (this.h == 1) {
            this.h = 0;
        } else {
            d.d("AdDialog", "other");
        }
        AppMethodBeat.o(20571);
    }

    public void b() {
        AppMethodBeat.i(20573, false);
        this.b.dismiss();
        d.d("AdDialog", "X");
        AppMethodBeat.o(20573);
    }

    public boolean c() {
        AppMethodBeat.i(20576, false);
        boolean z = !cn.missfresh.utils.b.a(this.g);
        AppMethodBeat.o(20576);
        return z;
    }

    public void a(List<BannerEntity> list, int i, int i2) {
        AppMethodBeat.i(20582, false);
        this.g = list;
        this.i = i;
        if (list == null || list.isEmpty()) {
            AppMethodBeat.o(20582);
            return;
        }
        this.d = new PicAdapter(this.a, i);
        this.d.a(list);
        this.c.setAdapter(this.d);
        this.c.setLooperPic(false);
        this.f.setViewPager(this.c);
        if (this.d.getCount() < 2) {
            this.f.setVisibility(8);
            this.c.setScrollable(false);
        }
        this.d.a(new AnonymousClass3(i, i2));
        AppMethodBeat.o(20582);
    }

    /* compiled from: AdDialog */
    /* renamed from: cn.missfresh.module.base.support.dialog.a$3  reason: invalid class name */
    class AnonymousClass3 implements PicAdapter.a {
        final /* synthetic */ int a;
        final /* synthetic */ int b;

        AnonymousClass3(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        @Override // cn.missfresh.module.base.support.adapter.PicAdapter.a
        public void a() {
            AppMethodBeat.i(20560, false);
            a.this.h = 1;
            a.this.b.cancel();
            int i = this.a;
            if (i != 1) {
                StatisticsManager.c("click_redPacketPop", "pop_type", Integer.valueOf(i), "businessType", Integer.valueOf(this.b));
            } else if (a.this.j != null) {
                a.this.j.put("btn_type", 0);
                StatisticsManager.c((Context) null, "click_btn", a.this.j);
            }
            AppMethodBeat.o(20560);
        }
    }

    public void a(Map<String, Object> map) {
        this.j = map;
    }
}
