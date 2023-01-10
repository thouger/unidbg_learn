package com.sina.weibo.sdk.component.view;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.sina.weibo.sdk.a.g;

public class TitleBar extends RelativeLayout {
    private TextView a;
    private TextView b;
    private a c;

    public interface a {
        void a();
    }

    public TitleBar(Context context) {
        super(context);
        a();
    }

    private void a() {
        this.a = new TextView(getContext());
        this.a.setClickable(true);
        this.a.setTextSize(2, 17.0f);
        this.a.setTextColor(g.a(-32256, 1728020992));
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(5);
        layoutParams.addRule(15);
        layoutParams.leftMargin = g.a(getContext(), 10);
        layoutParams.rightMargin = g.a(getContext(), 10);
        this.a.setLayoutParams(layoutParams);
        this.a.setOnClickListener(new AnonymousClass1());
        addView(this.a);
        this.b = new TextView(getContext());
        this.b.setTextSize(2, 18.0f);
        this.b.setTextColor(-11382190);
        this.b.setEllipsize(TextUtils.TruncateAt.END);
        this.b.setSingleLine(true);
        this.b.setGravity(17);
        this.b.setMaxWidth(g.a(getContext(), 160));
        RelativeLayout.LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13);
        this.b.setLayoutParams(layoutParams2);
        addView(this.b);
        setLayoutParams(new ViewGroup.LayoutParams(-1, g.a(getContext(), 45)));
        setBackgroundDrawable(g.b(getContext(), "weibosdk_navigationbar_background.9.png"));
    }

    /* access modifiers changed from: package-private */
    /* renamed from: com.sina.weibo.sdk.component.view.TitleBar$1  reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        AnonymousClass1() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (TitleBar.this.c != null) {
                TitleBar.this.c.a();
            }
        }
    }

    public void setTitleBarText(String str) {
        this.b.setText(str);
    }

    public void setLeftBtnText(String str) {
        this.a.setText(str);
    }

    public void setLeftBtnBg(Drawable drawable) {
        this.a.setBackgroundDrawable(drawable);
    }

    public void setTitleBarClickListener(a aVar) {
        this.c = aVar;
    }
}
