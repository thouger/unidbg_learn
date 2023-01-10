package cn.missfresh.ui.titlebar;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import cn.missfresh.ui.R;

public class TitleBar extends FrameLayout implements View.OnClickListener {
    private View a;
    private View b;
    private View c;
    private TextView d;
    private View e;
    private View f;
    private ImageView g;
    private ImageView h;
    private ImageView i;
    private ImageView j;
    private TextView k;
    private TextView l;
    private View m;
    private String n;
    private View o;
    private a p;

    public interface a {
        void F();

        void H();

        void I();

        void J();
    }

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public TitleBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        AppMethodBeat.i(2632, false);
        a();
        AppMethodBeat.o(2632);
    }

    private void a() {
        AppMethodBeat.i(2634, false);
        LayoutInflater.from(getContext()).inflate(R.layout.ui_layout_title_bar, (ViewGroup) this, true);
        this.a = findViewById(R.id.ll_title_bar_main_bar);
        this.b = findViewById(R.id.ll_title_bar_chrome_container);
        this.b.setTag(2);
        this.b.setOnClickListener(this);
        this.c = findViewById(R.id.ll_title_bar_search);
        this.d = (TextView) findViewById(R.id.tv_title_bar_chrome_address);
        this.k = (TextView) findViewById(R.id.tv_title_bar_right_txt);
        this.l = (TextView) findViewById(R.id.tv_title_bar_center_txt);
        this.g = (ImageView) findViewById(R.id.iv_title_bar_left_icon);
        this.h = (ImageView) findViewById(R.id.iv_title_bar_right_icon);
        this.i = (ImageView) findViewById(R.id.iv_delivery_mode_logo);
        this.j = (ImageView) findViewById(R.id.iv_title_bar_center_logo);
        this.e = findViewById(R.id.ll_title_bar_left_button);
        this.e.setTag(0);
        this.e.setOnClickListener(this);
        this.f = findViewById(R.id.rl_title_bar_right_button);
        this.f.setTag(1);
        this.f.setOnClickListener(this);
        this.o = findViewById(R.id.layout_right_red_tip);
        this.o.setTag(3);
        this.o.setOnClickListener(this);
        AppMethodBeat.o(2634);
    }

    public void setMainBarVisibility(int i) {
        AppMethodBeat.i(2636, false);
        this.a.setVisibility(i);
        AppMethodBeat.o(2636);
    }

    public void setChromeAddress(String str) {
        AppMethodBeat.i(2638, false);
        this.d.setText(str);
        AppMethodBeat.o(2638);
    }

    public void setChromeVisibility(int i) {
        AppMethodBeat.i(2639, false);
        this.b.setVisibility(i);
        AppMethodBeat.o(2639);
    }

    public void setLeftButtonVisibility(int i) {
        AppMethodBeat.i(2640, false);
        this.e.setVisibility(i);
        AppMethodBeat.o(2640);
    }

    public void setRightButtonVisibility(int i) {
        AppMethodBeat.i(2641, false);
        this.f.setVisibility(i);
        AppMethodBeat.o(2641);
    }

    public void setCenterVisibility(int i) {
        AppMethodBeat.i(2642, false);
        this.l.setVisibility(i);
        AppMethodBeat.o(2642);
    }

    public void setCenterTxt(String str) {
        AppMethodBeat.i(2643, false);
        this.n = str;
        this.l.setText(str);
        AppMethodBeat.o(2643);
    }

    public String getCenterTxt() {
        return this.n;
    }

    public void setRightButtonJustText(String str) {
        AppMethodBeat.i(2645, false);
        this.h.setVisibility(8);
        this.k.setText(str);
        AppMethodBeat.o(2645);
    }

    public void setBadgeViewVisible(boolean z) {
        AppMethodBeat.i(2650, false);
        BadgeView badgeView = (BadgeView) findViewById(R.id.badgeView);
        if (z) {
            badgeView.setVisibility(0);
        } else {
            badgeView.setVisibility(4);
        }
        AppMethodBeat.o(2650);
    }

    public View getBtnLeft() {
        return this.e;
    }

    public void setRightButtonColor(int i) {
        AppMethodBeat.i(2655, false);
        this.k.setTextColor(i);
        AppMethodBeat.o(2655);
    }

    public View getRightButton() {
        return this.f;
    }

    public void setDeliveryLogo(int i) {
        AppMethodBeat.i(2662, false);
        this.i.setVisibility(0);
        this.i.setImageResource(i);
        AppMethodBeat.o(2662);
    }

    public void setDeliveryLogo(Drawable drawable) {
        AppMethodBeat.i(2664, false);
        this.i.setVisibility(0);
        if (drawable != null) {
            this.i.setImageDrawable(drawable);
        } else {
            this.i.setImageResource(R.drawable.ic_common_delivery_logo);
        }
        AppMethodBeat.o(2664);
    }

    public ImageView getDeliveryLogoView() {
        return this.i;
    }

    public void setCenterTextLogo(int i) {
        AppMethodBeat.i(2667, false);
        this.j.setVisibility(0);
        this.j.setImageResource(i);
        AppMethodBeat.o(2667);
    }

    public View getChromeView() {
        return this.b;
    }

    public View getSearchLl() {
        return this.c;
    }

    public void setCenterTextColor(int i) {
        AppMethodBeat.i(2671, false);
        this.l.setTextColor(i);
        AppMethodBeat.o(2671);
    }

    public void setRightButtonAlpha(float f) {
        AppMethodBeat.i(2673, false);
        this.f.setAlpha(f);
        AppMethodBeat.o(2673);
    }

    public View getBottomDivider() {
        AppMethodBeat.i(2675, false);
        if (this.m == null) {
            this.m = findViewById(R.id.v_title_bar_divider);
        }
        View view = this.m;
        AppMethodBeat.o(2675);
        return view;
    }

    public ImageView getRightIconBtn() {
        return this.h;
    }

    public void setRightBtnIcon(Drawable drawable) {
        AppMethodBeat.i(2679, false);
        if (drawable != null) {
            this.h.setImageDrawable(null);
            if (Build.VERSION.SDK_INT >= 16) {
                this.h.setBackground(drawable);
            } else {
                this.h.setBackgroundDrawable(drawable);
            }
        }
        AppMethodBeat.o(2679);
    }

    public void setRightBtnText(String str) {
        AppMethodBeat.i(2682, false);
        if (!TextUtils.isEmpty(str)) {
            this.k.setText(str);
            ((LinearLayout.LayoutParams) this.h.getLayoutParams()).setMargins(0, 0, 0, 0);
        } else {
            ((LinearLayout.LayoutParams) this.h.getLayoutParams()).setMargins((int) a(getContext(), 15.0f), 0, (int) a(getContext(), 15.0f), 0);
            this.k.setVisibility(8);
        }
        AppMethodBeat.o(2682);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        a aVar;
        AppMethodBeat.i(2684, false);
        int intValue = ((Integer) view.getTag()).intValue();
        if (intValue == 0) {
            a aVar2 = this.p;
            if (aVar2 != null) {
                aVar2.F();
            }
        } else if (intValue == 1) {
            a aVar3 = this.p;
            if (aVar3 != null) {
                aVar3.H();
            }
        } else if (intValue == 2) {
            a aVar4 = this.p;
            if (aVar4 != null) {
                aVar4.I();
            }
        } else if (intValue == 3 && (aVar = this.p) != null) {
            aVar.J();
        }
        AppMethodBeat.onClick(this, view);
        AppMethodBeat.o(2684);
    }

    public void setOnOptionClickListener(a aVar) {
        this.p = aVar;
    }

    public float a(Context context, float f) {
        AppMethodBeat.i(2688, false);
        float applyDimension = TypedValue.applyDimension(1, f, context.getApplicationContext().getResources().getDisplayMetrics());
        AppMethodBeat.o(2688);
        return applyDimension;
    }
}
