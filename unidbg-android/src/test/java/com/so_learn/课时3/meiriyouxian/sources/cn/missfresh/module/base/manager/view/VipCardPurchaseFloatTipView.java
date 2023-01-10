package cn.missfresh.module.base.manager.view;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import cn.missfresh.module.base.R;
import cn.missfresh.module.base.common.config.i;
import cn.missfresh.module.base.h5.a.a;
import cn.missfresh.module.base.manager.bean.VipCardOrderShowBean;
import cn.missfresh.module.base.manager.u;
import cn.missfresh.module.base.network.d;
import cn.missfresh.module.base.utils.aw;
import cn.missfresh.sherlock.trace.core.AppMethodBeat;
import de.greenrobot.event.EventBus;
import de.greenrobot.event.Subscribe;

public class VipCardPurchaseFloatTipView extends LinearLayout implements Runnable {
    private ImageView a;
    private TextView b;
    private Animation c;
    private Location d = Location.OTHER;

    public VipCardPurchaseFloatTipView(Context context) {
        super(context);
    }

    public VipCardPurchaseFloatTipView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public VipCardPurchaseFloatTipView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.View
    public void onFinishInflate() {
        AppMethodBeat.i(15162, false);
        super.onFinishInflate();
        this.a = (ImageView) findViewById(R.id.avator);
        this.b = (TextView) findViewById(R.id.content);
        this.c = new AlphaAnimation(1.0f, 0.0f);
        this.c.setDuration(1500);
        this.c.setAnimationListener(new AnonymousClass1());
        setOnClickListener(new AnonymousClass2());
        setVisibility(8);
        AppMethodBeat.o(15162);
    }

    /* renamed from: cn.missfresh.module.base.manager.view.VipCardPurchaseFloatTipView$1  reason: invalid class name */
    class AnonymousClass1 implements Animation.AnimationListener {
        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationStart(Animation animation) {
        }

        AnonymousClass1() {
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationEnd(Animation animation) {
            AppMethodBeat.i(15153, false);
            VipCardPurchaseFloatTipView.this.setVisibility(4);
            AppMethodBeat.o(15153);
        }

        @Override // android.view.animation.Animation.AnimationListener
        public void onAnimationRepeat(Animation animation) {
            AppMethodBeat.i(15154, false);
            VipCardPurchaseFloatTipView.this.setVisibility(4);
            AppMethodBeat.o(15154);
        }
    }

    /* renamed from: cn.missfresh.module.base.manager.view.VipCardPurchaseFloatTipView$2  reason: invalid class name */
    class AnonymousClass2 implements View.OnClickListener {
        AnonymousClass2() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AppMethodBeat.i(15155, false);
            a.a(view.getContext(), "", i.cF);
            VipCardOrderShowBean.InfoBean infoBean = (VipCardOrderShowBean.InfoBean) view.getTag();
            AppMethodBeat.onClick(this, view);
            AppMethodBeat.o(15155);
        }
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        AppMethodBeat.i(15164, false);
        super.onAttachedToWindow();
        EventBus.getDefault().register(this);
        u.a().b();
        AppMethodBeat.o(15164);
    }

    /* access modifiers changed from: protected */
    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        AppMethodBeat.i(15165, false);
        super.onDetachedFromWindow();
        u.a().c();
        EventBus.getDefault().unregister(this);
        AppMethodBeat.o(15165);
    }

    public void setLocation(Location location) {
        this.d = location;
    }

    public enum Location {
        OTHER(-1),
        HOME(1),
        VIP_PLUS(3),
        MINE(5);
        
        private int value;

        public static Location valueOf(String str) {
            AppMethodBeat.i(15158, false);
            Location location = (Location) Enum.valueOf(Location.class, str);
            AppMethodBeat.o(15158);
            return location;
        }

        static {
            AppMethodBeat.i(15159, false);
            AppMethodBeat.o(15159);
        }

        private Location(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }
    }

    @Subscribe
    public void onHandlerEvent(VipCardOrderShowBean.InfoBean infoBean) {
        AppMethodBeat.i(15166, false);
        if (infoBean == null) {
            setVisibility(8);
        } else if (!(infoBean.getHome_page() == 1 && this.d == Location.HOME) && (infoBean.getOther_page() != 1 || this.d == Location.HOME || this.d == Location.OTHER)) {
            setVisibility(8);
        } else {
            setVisibility(0);
            postDelayed(this, 2500);
            d.d(getContext(), infoBean.getImg(), this.a);
            if (this.d == Location.HOME) {
                aw.a(this.b, infoBean.getText(), Color.parseColor("#FF297F"), new String[]{"#_$", "#_$"}, true);
            } else {
                aw.a(this.b, infoBean.getText(), getContext().getResources().getColor(R.color.color_ff4891), new String[]{"#_$", "#_$"}, true);
            }
            setTag(infoBean);
        }
        AppMethodBeat.o(15166);
    }

    @Override // java.lang.Runnable
    public void run() {
        AppMethodBeat.i(15168, false);
        clearAnimation();
        startAnimation(this.c);
        AppMethodBeat.o(15168);
    }
}
