package com.sobot.chat.widget.horizontalgridpage;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import com.sobot.chat.api.model.ZhiChiMessageBase;
import java.util.ArrayList;

public class PageIndicatorView extends LinearLayout {
    private String a = "PageIndicatorView";
    private ArrayList<View> b;
    private int c;
    private int[] d;
    private int[] e;
    private ZhiChiMessageBase f;

    public PageIndicatorView(Context context, int i, int[] iArr, int[] iArr2, int i2) {
        super(context);
        this.c = i;
        this.d = iArr;
        this.e = iArr2;
        setGravity(i2);
        setOrientation(0);
    }

    public void a(int i) {
        ArrayList<View> arrayList = this.b;
        if (arrayList == null) {
            this.b = new ArrayList<>();
        } else {
            arrayList.clear();
            removeAllViews();
        }
        int i2 = this.c;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(i2, i2);
        int[] iArr = this.d;
        layoutParams.setMargins(iArr[0], iArr[1], iArr[2], iArr[3]);
        for (int i3 = 0; i3 < i; i3++) {
            View view = new View(getContext());
            view.setBackgroundResource(this.e[0]);
            addView(view, layoutParams);
            this.b.add(view);
        }
        if (this.b.size() > 0) {
            this.b.get(0).setBackgroundResource(this.e[1]);
        }
    }

    public void setMessage(ZhiChiMessageBase zhiChiMessageBase) {
        this.f = zhiChiMessageBase;
    }

    public void b(int i) {
        if (this.b != null) {
            ZhiChiMessageBase zhiChiMessageBase = this.f;
            if (zhiChiMessageBase != null) {
                zhiChiMessageBase.setCurrentPageNum(i);
            }
            for (int i2 = 0; i2 < this.b.size(); i2++) {
                if (i2 == i) {
                    this.b.get(i2).setBackgroundResource(this.e[1]);
                } else {
                    this.b.get(i2).setBackgroundResource(this.e[0]);
                }
            }
        }
    }

    public int getCurrIndex() {
        if (this.f == null) {
            return 0;
        }
        String str = this.a;
        Log.e(str, "getCurrIndex: " + this.f.getCurrentPageNum());
        return this.f.getCurrentPageNum();
    }
}
