package cn.missfresh.module.base.support.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.common.api.IApplicationDelegateService;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.module.base.widget.BadgeView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.utils.a.d;
import cn.missfresh.utils.b;
import com.alibaba.android.arouter.b.a;

public class TitleBar extends FrameLayout {
    private String a;
    private View b;
    private View c;
    private View d;
    private View e;
    private TextView f;
    private View g;
    private View h;
    private ImageView i;
    private ImageView j;
    private ImageView k;
    private ImageView l;
    private ImageView m;
    private TextView n;
    private TextView o;
    private View p;
    private String q;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TitleBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(22784, false);
        this.a = "TitleBar";
        a();
        AppMethodBeat.o(22784);
    }

    private void a() {
        AppMethodBeat.i(22785, false);
        this.b = LayoutInflater.from(getContext()).inflate(R.layout.layout_title_bar, (ViewGroup) this, false);
        this.c = this.b.findViewById(R.id.ll_title_bar_main_bar);
        this.d = this.b.findViewById(R.id.ll_title_bar_chrome_container);
        this.e = this.b.findViewById(R.id.ll_title_bar_search);
        this.m = (ImageView) this.b.findViewById(R.id.iv_sweep_code);
        this.f = (TextView) this.b.findViewById(R.id.tv_title_bar_chrome_address);
        this.n = (TextView) this.b.findViewById(R.id.tv_title_bar_right_txt);
        this.o = (TextView) this.b.findViewById(R.id.tv_title_bar_center_txt);
        this.i = (ImageView) this.b.findViewById(R.id.iv_title_bar_left_icon);
        this.j = (ImageView) this.b.findViewById(R.id.iv_title_bar_right_icon);
        this.k = (ImageView) this.b.findViewById(R.id.iv_delivery_mode_logo);
        this.l = (ImageView) this.b.findViewById(R.id.iv_title_bar_center_logo);
        this.g = this.b.findViewById(R.id.ll_title_bar_left_button);
        this.h = this.b.findViewById(R.id.rl_title_bar_right_button);
        addView(this.b);
        AppMethodBeat.o(22785);
    }

    public void setMainBarVisibility(int i) {
        AppMethodBeat.i(22786, false);
        this.c.setVisibility(i);
        AppMethodBeat.o(22786);
    }

    public void setChromeOnClickListener(View.OnClickListener onClickListener) {
        AppMethodBeat.i(22787, false);
        this.d.setOnClickListener(onClickListener);
        AppMethodBeat.o(22787);
    }

    public void setSweepQrCodeOnClickListener(View.OnClickListener onClickListener) {
        AppMethodBeat.i(22788, false);
        this.m.setOnClickListener(onClickListener);
        AppMethodBeat.o(22788);
    }

    public void setChromeAddress(String str) {
        AppMethodBeat.i(22789, false);
        this.f.setText(str);
        AppMethodBeat.o(22789);
    }

    public void setLeftButtonOnClickListener(View.OnClickListener onClickListener) {
        AppMethodBeat.i(22790, false);
        this.g.setOnClickListener(onClickListener);
        AppMethodBeat.o(22790);
    }

    public void setRightButtonOnClickListener(View.OnClickListener onClickListener) {
        AppMethodBeat.i(22791, false);
        this.h.setOnClickListener(onClickListener);
        AppMethodBeat.o(22791);
    }

    public void setChromeVisibility(int i) {
        AppMethodBeat.i(22792, false);
        this.d.setVisibility(i);
        AppMethodBeat.o(22792);
    }

    public void setLeftButtonVisibility(int i) {
        AppMethodBeat.i(22793, false);
        this.g.setVisibility(i);
        AppMethodBeat.o(22793);
    }

    public void setRightButtonVisibility(int i) {
        AppMethodBeat.i(22794, false);
        this.h.setVisibility(i);
        AppMethodBeat.o(22794);
    }

    public void setCenterVisibility(int i) {
        AppMethodBeat.i(22795, false);
        this.o.setVisibility(i);
        AppMethodBeat.o(22795);
    }

    public String getCenterTxt() {
        return this.q;
    }

    public void setCenterTxt(String str) {
        AppMethodBeat.i(22796, false);
        this.q = str;
        this.o.setText(str);
        AppMethodBeat.o(22796);
    }

    public void setRightButtonJustText(String str) {
        AppMethodBeat.i(22797, false);
        this.j.setVisibility(8);
        this.n.setText(str);
        d.d(getClass().getSimpleName(), "setRightButtonJustText");
        AppMethodBeat.o(22797);
    }

    public void setBadgeViewVisible(boolean z) {
        AppMethodBeat.i(22799, false);
        BadgeView badgeView = (BadgeView) this.b.findViewById(R.id.badgeView);
        if (z) {
            badgeView.setVisibility(0);
        } else {
            badgeView.setVisibility(4);
        }
        AppMethodBeat.o(22799);
    }

    public void setRedTipLayoutOnClickListener(View.OnClickListener onClickListener) {
        AppMethodBeat.i(22800, false);
        this.b.findViewById(R.id.layout_right_red_tip).setOnClickListener(onClickListener);
        AppMethodBeat.o(22800);
    }

    public View getBtnLeft() {
        return this.g;
    }

    public void setRightButtonColor(int i) {
        AppMethodBeat.i(22801, false);
        this.n.setTextColor(i);
        AppMethodBeat.o(22801);
    }

    public View getRightButton() {
        return this.h;
    }

    public void a(int i, String str) {
        AppMethodBeat.i(22802, false);
        if (i > -1) {
            this.j.setImageResource(i);
        }
        if (!TextUtils.isEmpty(str)) {
            this.n.setText(str);
        } else {
            ((LinearLayout.LayoutParams) this.j.getLayoutParams()).setMargins(aw.b(15), 0, aw.b(15), 0);
            this.n.setVisibility(8);
        }
        d.d(getClass().getSimpleName(), "setRightBtnIcon");
        AppMethodBeat.o(22802);
    }

    public void setLeftBtb(int i) {
        AppMethodBeat.i(22803, false);
        if (i > -1) {
            this.i.setImageResource(i);
        }
        AppMethodBeat.o(22803);
    }

    public void setDeliveryLogo(int i) {
        AppMethodBeat.i(22804, false);
        this.k.setVisibility(0);
        this.k.setImageResource(i);
        AppMethodBeat.o(22804);
    }

    public void setDeliveryLogo(String str) {
        AppMethodBeat.i(22805, false);
        this.k.setVisibility(0);
        if (!b.a(str)) {
            cn.missfresh.module.base.network.d.b(((IApplicationDelegateService) a.a().a("/common/application_delegate_service").navigation()).getApplication(), str, this.k);
        } else {
            this.k.setImageResource(R.drawable.ic_common_delivery_logo);
        }
        AppMethodBeat.o(22805);
    }

    public void setCenterTextLogo(int i) {
        AppMethodBeat.i(22806, false);
        this.l.setVisibility(0);
        this.l.setImageResource(i);
        AppMethodBeat.o(22806);
    }

    public View getChromeView() {
        return this.d;
    }

    public View getSearchLl() {
        return this.e;
    }

    public void setRootBackgroundColor(int i) {
        AppMethodBeat.i(22807, false);
        this.b.setBackgroundColor(i);
        AppMethodBeat.o(22807);
    }

    public void setRootAlpha(float f) {
        AppMethodBeat.i(22808, false);
        this.b.setAlpha(f);
        AppMethodBeat.o(22808);
    }

    public void setCenterTextColor(int i) {
        AppMethodBeat.i(22809, false);
        this.o.setTextColor(i);
        AppMethodBeat.o(22809);
    }

    public void setRightButtonAlpha(float f) {
        AppMethodBeat.i(22810, false);
        this.h.setAlpha(f);
        AppMethodBeat.o(22810);
    }

    public View getBottomDivider() {
        AppMethodBeat.i(22811, false);
        if (this.p == null) {
            this.p = this.b.findViewById(R.id.v_title_bar_divider);
        }
        View view = this.p;
        AppMethodBeat.o(22811);
        return view;
    }

    /* access modifiers changed from: protected */
    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int i, int i2) {
        AppMethodBeat.i(22813, false);
        super.onMeasure(i, i2);
        AppMethodBeat.o(22813);
    }
}
