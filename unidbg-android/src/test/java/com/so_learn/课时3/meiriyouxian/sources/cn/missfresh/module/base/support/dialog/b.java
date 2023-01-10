package cn.missfresh.module.base.support.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.bean.BannerEntity;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.module.base.widget.FitHeightImageView;
import cn.missfresh.module.base.widget.PriceTextView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import java.text.SimpleDateFormat;
import java.util.Date;

/* compiled from: AdSimpleDialog */
public class b {
    private Context a;
    private AlertDialog b;
    private View c;
    private View d = this.c.findViewById(R.id.ll_ad_desc_parent);
    private TextView e = ((TextView) this.c.findViewById(R.id.tv_ad_card_type));
    private FitHeightImageView f = ((FitHeightImageView) this.c.findViewById(R.id.iv_ad_bg));
    private PriceTextView g = ((PriceTextView) this.c.findViewById(R.id.tv_return_price));
    private BannerEntity h;
    private int i;

    public b(Context context, cn.missfresh.module.base.common.interfaces.b bVar) {
        AppMethodBeat.i(20618, false);
        this.a = context;
        this.c = View.inflate(context, R.layout.dialog_ad_simple, null);
        this.b = new AlertDialog.Builder(context, R.style.my_dialog).setView(this.c).create();
        this.b.setOnCancelListener(new AnonymousClass1());
        this.b.setOnDismissListener(new AnonymousClass2(bVar));
        Window window = this.b.getWindow();
        if (window != null) {
            int a = aw.a(context);
            WindowManager.LayoutParams attributes = window.getAttributes();
            int b = a - aw.b(60);
            this.f.setFixedWidth(b);
            attributes.width = b;
            attributes.height = -2;
            window.setAttributes(attributes);
        }
        AppMethodBeat.o(20618);
    }

    /* compiled from: AdSimpleDialog */
    /* renamed from: cn.missfresh.module.base.support.dialog.b$1  reason: invalid class name */
    class AnonymousClass1 implements DialogInterface.OnCancelListener {
        AnonymousClass1() {
        }

        @Override // android.content.DialogInterface.OnCancelListener
        public void onCancel(DialogInterface dialogInterface) {
            AppMethodBeat.i(20602, false);
            b.this.a();
            AppMethodBeat.o(20602);
        }
    }

    /* compiled from: AdSimpleDialog */
    /* renamed from: cn.missfresh.module.base.support.dialog.b$2  reason: invalid class name */
    class AnonymousClass2 implements DialogInterface.OnDismissListener {
        final /* synthetic */ cn.missfresh.module.base.common.interfaces.b a;

        AnonymousClass2(cn.missfresh.module.base.common.interfaces.b bVar) {
            this.a = bVar;
        }

        @Override // android.content.DialogInterface.OnDismissListener
        public void onDismiss(DialogInterface dialogInterface) {
            AppMethodBeat.i(20607, false);
            this.a.a();
            AppMethodBeat.o(20607);
        }
    }

    public void a(boolean z) {
        AppMethodBeat.i(20621, false);
        if (!c()) {
            AppMethodBeat.o(20621);
            return;
        }
        this.b.show();
        if (!z) {
            e.x(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        }
        AppMethodBeat.o(20621);
    }

    public void a() {
        AppMethodBeat.i(20625, false);
        this.b.cancel();
        AppMethodBeat.o(20625);
    }

    public void b() {
        AppMethodBeat.i(20629, false);
        this.b.dismiss();
        AppMethodBeat.o(20629);
    }

    public boolean c() {
        boolean z = false;
        AppMethodBeat.i(20632, false);
        BannerEntity bannerEntity = this.h;
        if (bannerEntity != null && !cn.missfresh.utils.b.a(bannerEntity.getCard_type())) {
            z = true;
        }
        AppMethodBeat.o(20632);
        return z;
    }

    public void a(BannerEntity bannerEntity, int i, int i2) {
        AppMethodBeat.i(20643, false);
        this.h = bannerEntity;
        this.i = i2;
        if (this.h.getCard_type() != null) {
            if (this.h.getBack_cash() > 0) {
                this.d.setVisibility(0);
                this.e.setText(this.h.getCard_type());
                this.g.setPrice(this.h.getBack_cash());
            } else {
                this.d.setVisibility(8);
            }
            this.c.setOnClickListener(new AdSimpleDialog$3(this));
            this.f.a(bannerEntity.getPath());
        }
        AppMethodBeat.o(20643);
    }
}
