package com.sobot.chat.widget.kpswitch.view.emoticon;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.sobot.chat.utils.q;
import com.sobot.chat.utils.r;
import com.sobot.chat.widget.kpswitch.widget.data.PageSetEntity;
import java.util.ArrayList;
import java.util.Iterator;

public class EmoticonsIndicatorView extends LinearLayout {
    protected Context a;
    protected ArrayList<ImageView> b;
    protected Drawable c;
    protected Drawable d;
    protected LinearLayout.LayoutParams e;

    public EmoticonsIndicatorView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.a = context;
        setOrientation(0);
        if (this.d == null) {
            this.d = getResources().getDrawable(q.a(context, "drawable", "sobot_indicator_point_nomal"));
        }
        if (this.c == null) {
            this.c = getResources().getDrawable(q.a(context, "drawable", "sobot_indicator_point_select"));
        }
        this.e = new LinearLayout.LayoutParams(-2, -2);
        this.e.height = r.a(context, 5.0f);
        this.e.width = r.a(context, 5.0f);
        this.e.leftMargin = r.a(context, 4.0f);
    }

    public void a(int i, PageSetEntity pageSetEntity) {
        if (a(pageSetEntity)) {
            a(pageSetEntity.getPageCount());
            ArrayList<ImageView> arrayList = this.b;
            if (arrayList != null && arrayList.size() > 0) {
                Iterator<ImageView> it2 = this.b.iterator();
                while (it2.hasNext()) {
                    it2.next().setImageDrawable(this.d);
                }
                if (this.b.get(i) != null) {
                    this.b.get(i).setImageDrawable(this.c);
                }
            }
        }
    }

    public void a(int i, int i2, PageSetEntity pageSetEntity) {
        if (a(pageSetEntity)) {
            a(pageSetEntity.getPageCount());
            if (i < 0 || i2 < 0 || i2 == i) {
                i = 0;
                i2 = 0;
            }
            if (i < 0) {
                i = 0;
                i2 = 0;
            }
            this.b.get(i).setImageDrawable(this.d);
            this.b.get(i2).setImageDrawable(this.c);
        }
    }

    /* access modifiers changed from: protected */
    public boolean a(PageSetEntity pageSetEntity) {
        if (pageSetEntity == null || !pageSetEntity.isShowIndicator()) {
            setVisibility(8);
            return false;
        }
        setVisibility(0);
        return true;
    }

    /* access modifiers changed from: protected */
    public void a(int i) {
        if (this.b == null) {
            this.b = new ArrayList<>();
        }
        if (i > this.b.size()) {
            int size = this.b.size();
            while (size < i) {
                ImageView imageView = new ImageView(this.a);
                imageView.setImageDrawable(size == 0 ? this.c : this.d);
                addView(imageView, this.e);
                this.b.add(imageView);
                size++;
            }
        }
        if (i == 1) {
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                this.b.get(i2).setVisibility(8);
            }
            return;
        }
        for (int i3 = 0; i3 < this.b.size(); i3++) {
            if (i3 >= i) {
                this.b.get(i3).setVisibility(8);
            } else {
                this.b.get(i3).setVisibility(0);
            }
        }
    }
}
