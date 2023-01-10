package com.sobot.chat.widget.kpswitch.view.plus;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.media.TtmlUtils;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.GridView;
import android.widget.RelativeLayout;
import com.sobot.chat.utils.q;

public class SobotPlusPageView extends RelativeLayout {
    private GridView a;

    public GridView getGridView() {
        return this.a;
    }

    public SobotPlusPageView(Context context) {
        this(context, null);
    }

    public SobotPlusPageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (layoutInflater != null) {
            this.a = (GridView) layoutInflater.inflate(q.a(context, TtmlUtils.TAG_LAYOUT, "sobot_item_pluspage"), this).findViewById(q.a(context, "id", "sobot_gv"));
            if (Build.VERSION.SDK_INT > 11) {
                this.a.setMotionEventSplittingEnabled(false);
            }
            this.a.setStretchMode(2);
            this.a.setCacheColorHint(0);
            this.a.setSelector(new ColorDrawable(0));
            this.a.setVerticalScrollBarEnabled(false);
        }
    }

    public void setNumColumns(int i) {
        this.a.setNumColumns(i);
    }
}
