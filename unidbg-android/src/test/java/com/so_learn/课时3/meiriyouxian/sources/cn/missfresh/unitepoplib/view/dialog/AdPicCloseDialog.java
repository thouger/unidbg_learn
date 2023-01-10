package cn.missfresh.unitepoplib.view.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AlertDialog;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.unitepoplib.R;
import cn.missfresh.unitepoplib.UnitePopManager;
import cn.missfresh.unitepoplib.bean.DialogBean;
import cn.missfresh.unitepoplib.bean.PicDialogBean;
import cn.missfresh.unitepoplib.listener.a;
import cn.missfresh.unitepoplib.listener.b;
import cn.missfresh.unitepoplib.view.viewpager.CircleIndicator;
import cn.missfresh.unitepoplib.view.viewpager.LoopViewPager;
import java.util.ArrayList;
import java.util.List;

public class AdPicCloseDialog {
    private Context a;
    private AlertDialog b;
    private LoopViewPager c;
    private AdPicAdapter d;
    private ImageView e;
    private CircleIndicator f;
    private List<PicDialogBean> g;
    private a h;
    private String i;

    static /* synthetic */ PicDialogBean a(AdPicCloseDialog adPicCloseDialog) {
        AppMethodBeat.i(15730, false);
        PicDialogBean d = adPicCloseDialog.d();
        AppMethodBeat.o(15730);
        return d;
    }

    public AdPicCloseDialog(Context context) {
        AppMethodBeat.i(15697, false);
        this.a = context;
        View inflate = View.inflate(context, R.layout.pic_dialog_layout, null);
        this.c = (LoopViewPager) inflate.findViewById(R.id.viewpager);
        this.e = (ImageView) inflate.findViewById(R.id.ad_close);
        this.f = (CircleIndicator) inflate.findViewById(R.id.indicator);
        this.b = new AlertDialog.Builder(context, R.style.my_dialog).setView(inflate).create();
        this.b.setOnCancelListener(new AnonymousClass1());
        this.b.setOnDismissListener(new AnonymousClass2());
        this.e.setOnClickListener(new AnonymousClass3());
        AppMethodBeat.o(15697);
    }

    /* renamed from: cn.missfresh.unitepoplib.view.dialog.AdPicCloseDialog$1  reason: invalid class name */
    class AnonymousClass1 implements DialogInterface.OnCancelListener {
        AnonymousClass1() {
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            AppMethodBeat.i(15684, false);
            cn.missfresh.unitepoplib.b.a.a("AdPicCloseDialog", "setOnCancelListener");
            b.a().a(1004, UnitePopManager.a(), AdPicCloseDialog.a(AdPicCloseDialog.this));
            AppMethodBeat.o(15684);
        }
    }

    /* renamed from: cn.missfresh.unitepoplib.view.dialog.AdPicCloseDialog$2  reason: invalid class name */
    class AnonymousClass2 implements DialogInterface.OnDismissListener {
        AnonymousClass2() {
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            AppMethodBeat.i(15689, false);
            cn.missfresh.unitepoplib.b.a.a("AdPicCloseDialog", "onDismiss");
            b.a().a(1008, UnitePopManager.a(), AdPicCloseDialog.a(AdPicCloseDialog.this));
            AppMethodBeat.o(15689);
        }
    }

    /* renamed from: cn.missfresh.unitepoplib.view.dialog.AdPicCloseDialog$3  reason: invalid class name */
    class AnonymousClass3 implements View.OnClickListener {
        AnonymousClass3() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(15692, false);
            cn.missfresh.unitepoplib.b.a.a("AdPicCloseDialog", "click close icon");
            b.a().a(1002, UnitePopManager.a(), (DialogBean) AdPicCloseDialog.this.g.get(0));
            PicDialogBean a = AdPicCloseDialog.a(AdPicCloseDialog.this);
            if (a == null) {
                AdPicCloseDialog.this.b();
                AppMethodBeat.onClick(this, view);
                AppMethodBeat.o(15692);
                return;
            }
            if (a.isAutoDismiss()) {
                AdPicCloseDialog.this.b();
            }
            if (!a.isBlockingUp()) {
                UnitePopManager.showNextDialog(AdPicCloseDialog.this.i);
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(15692);
        }
    }

    public void a(a aVar) {
        this.h = aVar;
    }

    public void a() {
        AppMethodBeat.i(15701, false);
        if (!c()) {
            AppMethodBeat.o(15701);
            return;
        }
        this.b.show();
        b.a().a(1007, UnitePopManager.a(), this.g.get(0));
        AppMethodBeat.o(15701);
    }

    public void b() {
        AppMethodBeat.i(15705, false);
        cn.missfresh.unitepoplib.b.a.a("AdPicCloseDialog", "cancel()");
        this.b.dismiss();
        cn.missfresh.unitepoplib.b.a.b("AdDialog", "X");
        AppMethodBeat.o(15705);
    }

    public boolean c() {
        AppMethodBeat.i(15708, false);
        List<PicDialogBean> list = this.g;
        if (list == null || list.size() == 0) {
            AppMethodBeat.o(15708);
            return false;
        }
        a aVar = this.h;
        if (aVar != null) {
            boolean a = aVar.a(this.i);
            AppMethodBeat.o(15708);
            return a;
        }
        AppMethodBeat.o(15708);
        return true;
    }

    public void a(List<PicDialogBean> list) {
        AppMethodBeat.i(15716, false);
        this.g = list;
        if (list == null || list.isEmpty()) {
            AppMethodBeat.o(15716);
            return;
        }
        this.d = new AdPicAdapter(this.a);
        this.d.a(list);
        this.c.setAdapter(this.d);
        this.c.setLooperPic(false);
        this.f.setViewPager(this.c);
        if (this.d.getCount() < 2) {
            this.f.setVisibility(8);
            this.c.setScrollable(false);
        }
        AppMethodBeat.o(15716);
    }

    public void a(PicDialogBean picDialogBean) {
        AppMethodBeat.i(15720, false);
        if (this.g == null) {
            this.g = new ArrayList();
        }
        if (this.g.size() > 0) {
            this.g.clear();
        }
        this.g.add(picDialogBean);
        a(this.g);
        AppMethodBeat.o(15720);
    }

    public void a(String str) {
        this.i = str;
    }

    private PicDialogBean d() {
        AppMethodBeat.i(15723, false);
        List<PicDialogBean> list = this.g;
        if (list == null || list.isEmpty()) {
            AppMethodBeat.o(15723);
            return null;
        }
        PicDialogBean picDialogBean = this.g.get(0);
        if (picDialogBean == null) {
            AppMethodBeat.o(15723);
            return null;
        }
        AppMethodBeat.o(15723);
        return picDialogBean;
    }
}
