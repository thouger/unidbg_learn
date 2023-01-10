package com.sobot.chat.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

public class SobotEditTextLayout extends LinearLayout {
    private ScrollView a;
    private EditText b;
    private int c = 0;

    public void setParentScrollview(ScrollView scrollView) {
        this.a = scrollView;
    }

    public void setEditeText(EditText editText) {
        this.b = editText;
        this.c = ((LinearLayout.LayoutParams) editText.getLayoutParams()).height / editText.getLineHeight();
    }

    public SobotEditTextLayout(Context context) {
        super(context);
    }

    public SobotEditTextLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.a == null) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() == 0 && this.b.getLineCount() >= this.c) {
            setParentScrollAble(false);
        } else if (motionEvent.getAction() == 1) {
            setParentScrollAble(true);
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    private void setParentScrollAble(boolean z) {
        this.a.requestDisallowInterceptTouchEvent(!z);
    }
}
