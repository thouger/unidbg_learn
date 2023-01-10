package com.sobot.chat.widget.kpswitch;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import com.sobot.chat.widget.kpswitch.view.BaseChattingPanelView;
import com.sobot.chat.widget.kpswitch.view.a;
import java.util.HashMap;

public class CustomeChattingPanel extends RelativeLayout {
    private HashMap<Integer, BaseChattingPanelView> a;
    private String b;

    public CustomeChattingPanel(Context context) {
        this(context, null);
    }

    public CustomeChattingPanel(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public CustomeChattingPanel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.a = new HashMap<>();
    }

    public void a(int i, Bundle bundle, BaseChattingPanelView.a aVar) {
        int childCount = getChildCount();
        this.b = a.b(getContext(), i);
        if (childCount > 0) {
            for (int i2 = 0; i2 < childCount; i2++) {
                View childAt = getChildAt(i2);
                if (childAt.getTag().toString().equals(this.b)) {
                    childAt.setVisibility(0);
                } else {
                    childAt.setVisibility(8);
                }
            }
        }
        BaseChattingPanelView baseChattingPanelView = this.a.get(Integer.valueOf(i));
        if (baseChattingPanelView == null) {
            BaseChattingPanelView a = a.a(getContext(), i);
            this.a.put(Integer.valueOf(i), a);
            addView(a.c());
            a.a();
            a.b();
            a.a(aVar);
            a.a(bundle);
            return;
        }
        baseChattingPanelView.a(bundle);
    }

    public String getPanelViewTag() {
        return this.b;
    }
}
