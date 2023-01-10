package cn.missfresh.module.base.payment.recharge.view;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.module.base.helper.o;
import cn.missfresh.module.base.manager.e;
import cn.missfresh.module.base.network.d;
import cn.missfresh.module.base.network.k;
import cn.missfresh.module.base.payment.recharge.view.CardGiftClaimActivity;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.module.base.widget.MultiStateLayout;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.alibaba.fastjson.JSONObject;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;
import java.util.List;
import okhttp3.Request;

public class ValueCardActivity extends BaseFragmentActivity implements View.OnClickListener {
    private String a = getClass().getSimpleName();
    private EditText j;
    private TableLayout k;
    private MultiStateLayout l;
    private ImageView m;
    private ImageView n;
    private a o;
    private View p;
    private View.OnClickListener v = new AnonymousClass1(this);
    private int w;

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.payment.recharge.view.ValueCardActivity$2  reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ ValueCardActivity a;

        AnonymousClass2(ValueCardActivity valueCardActivity) {
            JniLib.cV(this, valueCardActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_ACCESSIBILITY_SERVICE_ENABLE));
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            JniLib.cV(this, view, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_AP_SETTINGS));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.payment.recharge.view.ValueCardActivity$3  reason: invalid class name */
    public class AnonymousClass3 implements TextWatcher {
        final /* synthetic */ ValueCardActivity a;

        AnonymousClass3(ValueCardActivity valueCardActivity) {
            JniLib.cV(this, valueCardActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_ACCOUNT_SYNC_CANNOT_ONETIME_SYNC));
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            JniLib.cV(this, editable, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_ACCESSIBILITY_SERVICE_DISABLE));
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            JniLib.cV(this, charSequence, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_ACCOUNT_SYNC_REMOVE));
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            JniLib.cV(this, charSequence, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_ACCOUNT_SYNC_FAILED_REMOVAL));
        }
    }

    public static void a(Context context) {
        JniLib.cV(context, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_USER_SETUP));
    }

    private void u() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_USER_SETUP_PROFILE));
    }

    public void a() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_USER_ENABLE_CALLING));
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        JniLib.cV(this, Boolean.valueOf(z), Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_USER_ENABLE_CALLING_AND_SMS));
    }

    public void r() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_USER_CANNOT_MANAGE));
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void w_() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_USER_ADD));
    }

    public ValueCardActivity() {
        AppMethodBeat.i(17836, false);
        AppMethodBeat.o(17836);
    }

    /* renamed from: cn.missfresh.module.base.payment.recharge.view.ValueCardActivity$1  reason: invalid class name */
    class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ ValueCardActivity a;

        AnonymousClass1(ValueCardActivity valueCardActivity) {
            JniLib.cV(this, valueCardActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_ENCRYPTION_INTERSTITIAL_ACCESSIBILITY));
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(17782, true);
            if (this.a.p != null) {
                this.a.p.setBackgroundDrawable(null);
            }
            this.a.p = view;
            this.a.p.setBackgroundResource(R.drawable.shape_corners_2_ff2741_no_solid);
            CharSequence contentDescription = view.getContentDescription();
            d.e(this.a.f, contentDescription.toString(), this.a.m);
            this.a.o.a(contentDescription.toString());
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(17782);
        }
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(17841, false);
        super.onCreate(bundle);
        setContentView(R.layout.activity_value_card_layout);
        t();
        u();
        AppMethodBeat.o(17841);
    }

    private void t() {
        AppMethodBeat.i(17846, false);
        this.e.setLeftButtonVisibility(0);
        this.e.setCenterTxt("\u4f18\u9c9c\u50a8\u503c\u5361");
        this.e.setCenterVisibility(0);
        this.e.setRightButtonJustText("\u5361\u7247\u8bb0\u5f55");
        this.e.setRightButtonVisibility(0);
        this.e.setRightButtonOnClickListener(new AnonymousClass2(this));
        findViewById(R.id.buy).setOnClickListener(this);
        findViewById(R.id.present).setOnClickListener(this);
        this.j = (EditText) findViewById(R.id.et_customPrice);
        this.j.addTextChangedListener(new AnonymousClass3(this));
        this.k = (TableLayout) findViewById(R.id.cardTableLayout);
        this.m = (ImageView) findViewById(R.id.selectedCardImage);
        this.n = (ImageView) findViewById(R.id.buttonShadow);
        ViewGroup.LayoutParams layoutParams = this.m.getLayoutParams();
        layoutParams.width = aw.a(this.f) - 72;
        layoutParams.height = (layoutParams.width * 100) / 158;
        this.m.setLayoutParams(layoutParams);
        ViewGroup.LayoutParams layoutParams2 = this.n.getLayoutParams();
        layoutParams2.width = layoutParams.width;
        this.n.setLayoutParams(layoutParams2);
        this.w = aw.a(this.f, 10);
        this.l = (MultiStateLayout) findViewById(R.id.multi_state_layout);
        this.l.d();
        AppMethodBeat.o(17846);
    }

    /* renamed from: cn.missfresh.module.base.payment.recharge.view.ValueCardActivity$4  reason: invalid class name */
    class AnonymousClass4 extends k {
        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(int i) {
            JniLib.cV(this, Integer.valueOf(i), Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_NIGHT_DISPLAY_SET_START_TIME));
        }

        /* access modifiers changed from: protected */
        @Override // cn.missfresh.module.base.network.k
        public void a(JSONObject jSONObject) {
            JniLib.cV(this, jSONObject, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_NIGHT_DISPLAY_SET_END_TIME));
        }

        @Override // cn.missfresh.module.base.network.m, cn.missfresh.module.base.network.c.b
        public void a(Request request, Exception exc) {
            JniLib.cV(this, request, exc, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_USER_EDIT));
        }

        AnonymousClass4() {
        }
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity, android.view.View.OnClickListener
    public void onClick(View view) {
        AppMethodBeat.i(17853, true);
        super.onClick(view);
        String obj = this.j.getText().toString();
        if (TextUtils.isEmpty(obj)) {
            cn.missfresh.ui.a.a.a("\u8bf7\u8f93\u5165\u91d1\u989d");
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(17853);
        } else if (!e.o()) {
            o.a(1);
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(17853);
        } else {
            int id = view.getId();
            if (id == R.id.buy) {
                this.o.a((int) (Float.valueOf(obj).floatValue() * 100.0f));
                CardPurchaseActivity.a(this.f, this.o.b(), this.o.c());
            } else if (id == R.id.present) {
                CardGiftClaimActivity.a(this.f, CardGiftClaimActivity.Type.Claim, this.o.b(), this.o.c());
            }
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(17853);
        }
    }

    public void s() {
        AppMethodBeat.i(17863, false);
        List<String> a2 = this.o.a();
        int size = a2.size();
        TableRow tableRow = null;
        for (int i = 0; i < size; i++) {
            if (i % 3 == 0) {
                tableRow = new TableRow(this.f);
                TableLayout.LayoutParams layoutParams = new TableLayout.LayoutParams(-1, -2);
                int i2 = this.w;
                layoutParams.setMargins(0, i2, i2, 0);
                this.k.addView(tableRow, layoutParams);
            }
            View c = c(a2.get(i));
            if (tableRow != null) {
                tableRow.addView(c);
            }
            if (i == 0) {
                this.v.onClick(c);
            }
        }
        this.k.requestLayout();
        this.l.c();
        AppMethodBeat.o(17863);
    }

    private View c(String str) {
        AppMethodBeat.i(17867, false);
        ImageView imageView = new ImageView(this.f);
        int a2 = (aw.a(this.f) - (this.w * 4)) / 3;
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(a2, (a2 * 100) / 158);
        layoutParams.setMargins(this.w, 0, 0, 0);
        imageView.setLayoutParams(layoutParams);
        imageView.setPadding(1, 1, 1, 1);
        imageView.setAdjustViewBounds(true);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setOnClickListener(this.v);
        d.e(this.f, str, imageView);
        imageView.setContentDescription(str);
        AppMethodBeat.o(17867);
        return imageView;
    }

    /* access modifiers changed from: private */
    public class a {
        List<String> a;
        int b;
        String c;
        final /* synthetic */ ValueCardActivity d;

        private a(ValueCardActivity valueCardActivity) {
            JniLib.cV(this, valueCardActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.DIALOG_USER_REMOVE));
        }

        /* synthetic */ a(ValueCardActivity valueCardActivity, AnonymousClass1 r2) {
            this(valueCardActivity);
        }

        public List<String> a() {
            return this.a;
        }

        public void a(List<String> list) {
            this.a = list;
        }

        public String b() {
            return this.c;
        }

        public void a(String str) {
            this.c = str;
        }

        public int c() {
            return this.b;
        }

        public void a(int i) {
            this.b = i;
        }
    }
}
