package cn.missfresh.module.base.payment.recharge.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.missfresh.lib.image.c;
import cn.missfresh.module.base.R;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.roundiv.MFRoundedImageView;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;

public class StoredPaysLayout extends LinearLayout implements View.OnClickListener {
    public static int a = 1;
    public static int b = 2;
    public a c;
    private View d;
    private View e;
    private MFRoundedImageView f;
    private ImageView g;
    private TextView h;
    private ImageView i;
    private int j;

    public interface a {
        void a(int i);
    }

    public void setOtherAccount(String str) {
        JniLib.cV(this, str, Integer.valueOf((int) MetricsProto.MetricsEvent.ENTERPRISE_PRIVACY_SETTINGS));
    }

    public StoredPaysLayout(Context context) {
        super(context);
    }

    public StoredPaysLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public StoredPaysLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onFinishInflate() {
        AppMethodBeat.i(18549, false);
        super.onFinishInflate();
        this.d = findViewById(R.id.rl_my_account);
        this.f = (MFRoundedImageView) findViewById(R.id.iv_self);
        this.g = (ImageView) findViewById(R.id.v_self);
        this.e = findViewById(R.id.rl_other_account);
        this.h = (TextView) findViewById(R.id.tv_account_other);
        this.i = (ImageView) findViewById(R.id.v_other);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        a(a);
        AppMethodBeat.o(18549);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(18550, false);
        int id = view.getId();
        if (id == R.id.rl_my_account) {
            a(a);
        } else if (id == R.id.rl_other_account) {
            a(b);
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(18550);
    }

    public void setOnSelectAccountListener(a aVar) {
        this.c = aVar;
    }

    public void setMySelfPortrait(String str) {
        AppMethodBeat.i(18553, false);
        c.a(this).a(str).b(R.drawable.ic_round_logo).a(R.drawable.ic_round_logo).a(this.f);
        AppMethodBeat.o(18553);
    }

    public void a(int i) {
        AppMethodBeat.i(18556, false);
        this.j = i;
        if (i == a) {
            this.g.setImageResource(R.drawable.ic_address_selected);
            this.i.setImageResource(R.drawable.ic_address_normal);
        } else if (i == b) {
            this.g.setImageResource(R.drawable.ic_address_normal);
            this.i.setImageResource(R.drawable.ic_address_selected);
        }
        a aVar = this.c;
        if (aVar != null) {
            aVar.a(i);
        }
        AppMethodBeat.o(18556);
    }

    public int getAccountType() {
        return this.j;
    }
}
