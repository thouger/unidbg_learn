package cn.missfresh.module.base.payment.recharge.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.base.BaseFragmentActivity;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.module.base.widget.PriceTextView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import com.android.internal.logging.nano.MetricsProto;
import com.bangcle.andjni.JniLib;

public class CardGiftClaimActivity extends BaseFragmentActivity {
    private static String a = "EXTRA_IMAGE_URL";
    private static String j = "EXTRA_CARD_PRICE";
    private static String k = "EXTRA_CARD_TYPE";
    private TextView l;
    private TextView m;
    private EditText n;
    private PriceTextView o;
    private ImageView p;
    private c v;
    private d w;
    private ImageView x;

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.payment.recharge.view.CardGiftClaimActivity$1  reason: invalid class name */
    public class AnonymousClass1 implements TextWatcher {
        final /* synthetic */ CardGiftClaimActivity a;

        AnonymousClass1(CardGiftClaimActivity cardGiftClaimActivity) {
            JniLib.cV(this, cardGiftClaimActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_HIDE_APP_DISAMBIG_APP_FEATURED));
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            JniLib.cV(this, editable, 449);
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            JniLib.cV(this, charSequence, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), 450);
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            JniLib.cV(this, charSequence, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SHOW_APP_DISAMBIG_APP_FEATURED));
        }
    }

    /* access modifiers changed from: package-private */
    /* renamed from: cn.missfresh.module.base.payment.recharge.view.CardGiftClaimActivity$2  reason: invalid class name */
    public class AnonymousClass2 implements View.OnClickListener {
        final /* synthetic */ CardGiftClaimActivity a;

        AnonymousClass2(CardGiftClaimActivity cardGiftClaimActivity) {
            JniLib.cV(this, cardGiftClaimActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_HIDE_APP_DISAMBIG_NONE_FEATURED));
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            JniLib.cV(this, view, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_SHOW_APP_DISAMBIG_NONE_FEATURED));
        }
    }

    class a implements d {
        final /* synthetic */ CardGiftClaimActivity a;

        a(CardGiftClaimActivity cardGiftClaimActivity) {
            JniLib.cV(this, cardGiftClaimActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.SETTINGS_GESTURES));
        }

        @Override // cn.missfresh.module.base.payment.recharge.view.CardGiftClaimActivity.d
        public void a() {
            JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_APP_DISAMBIG_ALWAYS));
        }

        @Override // cn.missfresh.module.base.payment.recharge.view.CardGiftClaimActivity.d
        public void a(TextView textView) {
            JniLib.cV(this, textView, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_APP_DISAMBIG_JUST_ONCE));
        }

        @Override // cn.missfresh.module.base.payment.recharge.view.CardGiftClaimActivity.d
        public void a(TextView textView, TextView textView2, TextWatcher textWatcher, int i) {
            JniLib.cV(this, textView, textView2, textWatcher, Integer.valueOf(i), Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_APP_DISAMBIG_TAP));
        }

        @Override // cn.missfresh.module.base.payment.recharge.view.CardGiftClaimActivity.d
        public void b() {
            JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.STORAGE_MANAGER_SETTINGS));
        }
    }

    class b implements d {
        final /* synthetic */ CardGiftClaimActivity a;

        b(CardGiftClaimActivity cardGiftClaimActivity) {
            JniLib.cV(this, cardGiftClaimActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_DELETION_APPS_COLLAPSED));
        }

        @Override // cn.missfresh.module.base.payment.recharge.view.CardGiftClaimActivity.d
        public void a() {
            JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_DELETION_SELECTION_PHOTOS));
        }

        @Override // cn.missfresh.module.base.payment.recharge.view.CardGiftClaimActivity.d
        public void a(TextView textView) {
            JniLib.cV(this, textView, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_DELETION_SELECTION_ALL_APPS));
        }

        @Override // cn.missfresh.module.base.payment.recharge.view.CardGiftClaimActivity.d
        public void a(TextView textView, TextView textView2, TextWatcher textWatcher, int i) {
            JniLib.cV(this, textView, textView2, textWatcher, Integer.valueOf(i), Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_DELETION_SELECTION_APP_ON));
        }

        @Override // cn.missfresh.module.base.payment.recharge.view.CardGiftClaimActivity.d
        public void b() {
            JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_DELETION_SELECTION_APP_OFF));
        }
    }

    interface d {
        void a();

        void a(TextView textView);

        void a(TextView textView, TextView textView2, TextWatcher textWatcher, int i);

        void b();
    }

    public static void a(Context context, Type type, String str, int i) {
        JniLib.cV(context, type, str, Integer.valueOf(i), Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_DELETION_HELPER_CANCEL));
    }

    private void r() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_DELETION_HELPER_REMOVE_CONFIRM));
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void finish() {
        JniLib.cV(this, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_DELETION_DOWNLOADS_COLLAPSED));
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onWindowFocusChanged(boolean z) {
        JniLib.cV(this, Boolean.valueOf(z), Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_DELETION_HELPER_CLEAR));
    }

    enum Type {
        Gift,
        Claim;

        public static Type valueOf(String str) {
            AppMethodBeat.i(17398, false);
            Type type = (Type) Enum.valueOf(Type.class, str);
            AppMethodBeat.o(17398);
            return type;
        }

        static {
            AppMethodBeat.i(17402, false);
            AppMethodBeat.o(17402);
        }
    }

    /* access modifiers changed from: protected */
    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void onCreate(Bundle bundle) {
        AppMethodBeat.i(17411, false);
        super.onCreate(bundle);
        setContentView(R.layout.activity_gift_claim_layout);
        a();
        r();
        AppMethodBeat.o(17411);
    }

    private void a() {
        AppMethodBeat.i(17412, false);
        this.e.setCenterVisibility(0);
        this.e.setLeftButtonVisibility(0);
        this.p = (ImageView) findViewById(R.id.purchaseCardImage);
        this.x = (ImageView) findViewById(R.id.bottomImage);
        this.o = (PriceTextView) findViewById(R.id.cardPrice);
        this.n = (EditText) findViewById(R.id.contentInputET);
        this.m = (TextView) findViewById(R.id.textNumberIndicator);
        this.l = (TextView) findViewById(R.id.giftButton);
        this.p.setImageResource(R.drawable.background_vip_silver_card);
        this.n.addTextChangedListener(new AnonymousClass1(this));
        this.l.setOnClickListener(new AnonymousClass2(this));
        ViewGroup.LayoutParams layoutParams = this.p.getLayoutParams();
        layoutParams.width = aw.a(this.f) - 72;
        layoutParams.height = (layoutParams.width * 100) / 158;
        this.p.setLayoutParams(layoutParams);
        View findViewById = findViewById(R.id.buttonShadow);
        ViewGroup.LayoutParams layoutParams2 = findViewById.getLayoutParams();
        layoutParams2.width = layoutParams.width;
        findViewById.setLayoutParams(layoutParams2);
        AppMethodBeat.o(17412);
    }

    @Override // cn.missfresh.module.base.base.BaseFragmentActivity
    public void a(Intent intent) {
        AppMethodBeat.i(17414, false);
        super.a(intent);
        overridePendingTransition(R.anim.anim_base_activity_exit, R.anim.anim_base_activity_close);
        AppMethodBeat.o(17414);
    }

    private class c {
        final /* synthetic */ CardGiftClaimActivity a;
        private Type b;
        private String c;
        private int d;

        private c(CardGiftClaimActivity cardGiftClaimActivity) {
            JniLib.cV(this, cardGiftClaimActivity, Integer.valueOf((int) MetricsProto.MetricsEvent.ACTION_DELETION_SELECTION_DOWNLOADS));
        }

        /* synthetic */ c(CardGiftClaimActivity cardGiftClaimActivity, AnonymousClass1 r2) {
            this(cardGiftClaimActivity);
        }

        public String a() {
            return this.c;
        }

        public void a(String str) {
            this.c = str;
        }

        public int b() {
            return this.d;
        }

        public void a(int i) {
            this.d = i;
        }

        public Type c() {
            return this.b;
        }

        public void a(Type type) {
            this.b = type;
        }
    }
}
